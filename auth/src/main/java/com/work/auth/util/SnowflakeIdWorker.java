package com.work.auth.util;

import lombok.extern.slf4j.Slf4j;

import java.lang.management.ManagementFactory;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.HashSet;
import java.util.Set;

/**
 * Twitter公司雪花Id生成器。使用单例模式，不要重复实例化对象，不然ID冲突；
 *
 * @author zhaiyunpeng
 */
@Slf4j
public class SnowflakeIdWorker {
    public static void main(String[] args) throws IllegalAccessException {
        Set<String> idSet = new HashSet<>();
        SnowflakeIdWorker idWorkerMain = SnowflakeIdWorker.getIdWorker();
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 10; i++) {
            String id = idWorkerMain.nextId();
            System.out.println(id.length());
            System.out.println(id);
            idSet.add(id);
        }
        System.out.println(idSet.size());
        System.out.println(System.currentTimeMillis()-startTime);
//        Date date = DateUtil.formatStrToDate("2018-12-12",DateUtil.DATE_FORMAT_1);
//        System.out.println(date.getTime());
    }

    private static SnowflakeIdWorker idWorker;

    static {
        try {
            idWorker = new SnowflakeIdWorker();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * 单例模式创建ID生成器，防止多实例造成ID冲突
     * @return  SnowflakeIdWorker
     */
    public static SnowflakeIdWorker getIdWorker(){
        return idWorker;
    }

    /**
     * 开始日期毫秒（2018-12-12）
     */
    private final long startTimestamp = 1544544000000L;

    /**
     * 机器ID所占位数
     */
    private final long workerIdBits = 5L;
    /**
     * 数据标识ID所占位数
     */
    private final long dataCenterIdBits = 5L;
    /**
     * 支持最大机器ID，结果是31
     */
    private final long maxWorkerId = -1L ^ (-1L << workerIdBits);
    /**
     * 支持最大数据ID，结果是31
     */
    private final long maxDataCenterId = -1L ^ (-1L << dataCenterIdBits);
    /**
     * 序列在ID中占12位
     */
    private final long sequenceBits = 12L;
    /**
     * 机器ID向左移12位
     */
    private final long workerIdShift = sequenceBits;
    /**
     * 数据标识ID向左移17位
     */
    private final long dataCenterIdShift = sequenceBits + workerIdBits;
    /**
     * 时间截向左移22位
     */
    private final long timestampShift = sequenceBits + workerIdBits + dataCenterIdBits;
    /**
     * 生产序列的掩码，这里为4095
     */
    private final long sequenceMask = -1L ^ (-1L << sequenceBits);
    /**
     * 工作机器ID（0-31）
     */
//    @Value("${SnowflakeIdWorker.workerId}")
    private long workerId;
    /**
     * 数据中心ID
     */
//    @Value("${SnowflakeIdWorker.dataCenterId}")
    private long dataCenterId;
    /**
     * 毫秒内序列（0-4095）
     */
    private long sequence = 0L;
    /**
     * 上一次生成ID的时间戳
     */
    private long lastTimestamp = -1L;
    /**
     * 构造函数
     */
    public SnowflakeIdWorker() throws IllegalAccessException {
        this.dataCenterId = getDatacenterId(31L);
        this.workerId = getMaxWorkerId(this.dataCenterId, 31L);
        if (this.workerId > maxWorkerId || this.workerId < 0) {
            throw new IllegalAccessException(String.format("机器ID不能小于0，或者大于%d", maxWorkerId));
        }
        if (this.dataCenterId > maxDataCenterId || this.dataCenterId < 0) {
            throw new IllegalAccessException(String.format("数据中心ID不能小于0，或者大于%d", maxDataCenterId));
        }
    }

    /**
     * 有参构造函数，待改进(需自动绑定workerId，dataCenterId)，现使用无参构造方法，默认为0
     *
     * @param workerId     工作机器ID
     * @param dataCenterId 数据中心ID
     */
    public SnowflakeIdWorker(long workerId, long dataCenterId) throws IllegalAccessException {
        if (workerId > maxWorkerId || workerId < 0) {
            throw new IllegalAccessException(String.format("机器ID不能小于0，或者大于%d", maxWorkerId));
        }
        if (dataCenterId > maxDataCenterId || dataCenterId < 0) {
            throw new IllegalAccessException(String.format("数据中心ID不能小于0，或者大于%d", maxDataCenterId));
        }
        this.workerId = workerId;
        this.dataCenterId = dataCenterId;
    }

    /**
     * 获得下一个ID（线程安全的）
     *
     * @return SnowflakeId
     */
    public synchronized String nextId() {
        long timestamp = timeGen();
        // 如果当前时间小于上一次生成ID时间，说明系统时钟回退过，应当抛出异常
        if (timestamp < lastTimestamp) {
            throw new RuntimeException(String.format("系统时钟回退，拒绝生成ID For %d milliseconds", lastTimestamp));
        }
        //如果是同一毫秒内生成的，则进行毫秒内序列
        if (lastTimestamp == timestamp) {
            sequence = (sequence + 1) & sequenceMask;
            //毫秒内内存溢出
            if (sequence == 0) {
                //阻塞到下一毫秒，获得新的时间戳
                timestamp = tilNextMills(lastTimestamp);
            }
        } else {
            //时间发生改变，毫秒内序列清空
            sequence = 0L;
        }
        //上一次生成ID的时间戳
        lastTimestamp = timestamp;
        return StringUtil.getString(Long.valueOf(Long.toBinaryString(((timestamp - startTimestamp) << timestampShift)
                | (dataCenterId << dataCenterIdShift)
                | (workerId << workerIdShift)
                | sequence),2))
                ;
    }

    /**
     * 阻塞到下一毫秒，直到获取到新的时间戳
     *
     * @param lastTimestamp 上一次生成ID的时间戳
     * @return 当前时间戳
     */
    private long tilNextMills(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    /**
     * 已毫秒为单位返回当前时间
     *
     * @return 当前时间（毫秒）
     */
    private long timeGen() {
        return System.currentTimeMillis();
    }

    protected static long getMaxWorkerId(long datacenterId, long maxWorkerId) {
        StringBuffer mpid = new StringBuffer();
        mpid.append(datacenterId);
        String name = ManagementFactory.getRuntimeMXBean().getName();
        if (!name.isEmpty()) {
            mpid.append(name.split("@")[0]);
        }

        return (long)(mpid.toString().hashCode() & '\uffff') % (maxWorkerId + 1L);
    }

    public long getDatacenterId() {
        return this.dataCenterId;
    }

    public long getWorkerId() {
        return this.workerId;
    }

    protected static long getDatacenterId(long maxDatacenterId) {
        long id = 0L;

        try {
            InetAddress ip = InetAddress.getLocalHost();
            NetworkInterface network = NetworkInterface.getByInetAddress(ip);
            if (network == null) {
                id = 1L;
            } else {
                byte[] mac = network.getHardwareAddress();
                id = (255L & (long)mac[mac.length - 1] | 65280L & (long)mac[mac.length - 2] << 8) >> 6;
                id %= maxDatacenterId + 1L;
            }
        } catch (Exception var7) {
            var7.printStackTrace();
            System.out.println(" getDatacenterId: " + var7.getMessage());
        }

        return id;
    }
}











