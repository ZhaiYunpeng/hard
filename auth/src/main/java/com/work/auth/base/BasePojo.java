package com.work.auth.base;

import com.work.auth.util.SnowflakeIdWorker;

/**
 * BasePojo  基础实体类
 *
 * @author ZhaiYunpeng
 */
public class BasePojo {
    /**
     * 使用雪花ID生成器，生成ID
     *
     * @return String Id
     */
    public static String nextId() {
        SnowflakeIdWorker idWorker = SnowflakeIdWorker.getIdWorker();
        return idWorker.nextId();
    }
}
