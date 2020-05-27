package com.work.auth.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.List;

/**
 * Decimal类 计算通用类
 *
 * @author zhaiyunpeng
 */
public class BigDecimalUtil {

    /**
     * 运算精度 10位小数
     */
    public static final int DEFAULT_DECIMAL_10 = 10;
    /**
     * 运算精度 4位小数
     */
    public static final int DEFAULT_DECIMAL_4 = 4;
    /**
     * 运算精度 2位小数
     */
    public static final int DEFAULT_DECIMAL_2 = 2;
    /**
     * 运算精度 0位小数
     */
    public static final int DEFAULT_DECIMAL_0 = 0;

    /**
     * 将数据转换为BigDecimal类型数据,如数据不可转化，则返回0
     *
     * @param obj 待处理数据
     * @return BigDecimal
     */
    public static BigDecimal getBigDecimal(Object obj) {
        try {
            return new BigDecimal(StringUtil.getString(obj, "0"));
        } catch (Exception e) {
            return new BigDecimal(0);
        }
    }

    /**
     * 返回2位小数，四舍五入
     *
     * @param decimal 待处理数据
     * @return BigDecimal
     */
    public static BigDecimal format(BigDecimal decimal) {
        return decimal.setScale(DEFAULT_DECIMAL_2, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 返回指定精度小数，四舍五入
     *
     * @param decimal      待处理数据
     * @param decimalPoint 小数位数
     * @return BigDecimal
     */
    public static BigDecimal format(BigDecimal decimal, int decimalPoint) {
        return decimal.setScale(decimalPoint, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 标准化处理数据（注意正负数，合理采用进位方法）
     *
     * @param decimal      待处理数据
     * @param decimalPoint 小数位数
     * @param formatRound  <p>取整方法 0:BigDecimal.ROUND_UP 向上取整；</p>
     *                     <p>1：BigDecimal.ROUND_DOWN 向下取整；</p>
     *                     <p>2：BigDecimal.ROUND_CEILING 取数轴的前一位，（例：1.2=2；-1.2=-1）；</p>
     *                     <p>3：BigDecimal.ROUND_FLOOR 取数轴的后一位，（例：1.2=1；-1.2=-2）；</p>
     *                     <p>4：BigDecimal.ROUND_HALF_UP 四舍五入 ；</p>
     *                     <p>5：BigDecimal.ROUND_HALF_DOWN 五舍六入 ；</p>
     *                     <p>6：BigDecimal.ROUND_HALF_EVEN；四舍六入，五取偶数（当最后一位为5时，取最近的偶数，入1.15》1.2；2.25》2；3.15》4；4.15》4）</p>
     *                     <p>7：BigDecimal.ROUND_UNNECESSARY；不许进行静度处理</p>
     * @return BigDecimal
     */
    public static BigDecimal format(BigDecimal decimal, int decimalPoint, int formatRound) {
        return decimal.setScale(decimalPoint, formatRound);
    }

    /**
     * 相加
     *
     * @param firstDecimal  加数
     * @param secondDecimal 加数
     * @return BigDecimal
     */
    public static BigDecimal add(BigDecimal firstDecimal, BigDecimal secondDecimal) {
        return firstDecimal.add(secondDecimal);
    }

    /**
     * 相加，保留位数
     *
     * @param firstDecimal  加数
     * @param secondDecimal 加数
     * @param decimalPoint  小数位数
     * @return BigDecimal
     */
    public static BigDecimal add(BigDecimal firstDecimal, BigDecimal secondDecimal, int decimalPoint) {
        return format(firstDecimal.add(secondDecimal), decimalPoint);
    }

    /**
     * 相减
     *
     * @param firstDecimal  减数
     * @param secondDecimal 减数
     * @return BigDecimal
     */
    public static BigDecimal subtract(BigDecimal firstDecimal, BigDecimal secondDecimal) {
        return firstDecimal.subtract(secondDecimal);
    }

    /**
     * 相减，保留位数
     *
     * @param firstDecimal  被减数
     * @param secondDecimal 减数
     * @param decimalPoint  小数位数
     * @return BigDecimal
     */
    public static BigDecimal subtract(BigDecimal firstDecimal, BigDecimal secondDecimal, int decimalPoint) {
        return format(firstDecimal.subtract(secondDecimal), decimalPoint);
    }

    /**
     * 相乘
     *
     * @param firstDecimal  被乘数
     * @param secondDecimal 乘数
     * @return BigDecimal
     */
    public static BigDecimal multiply(BigDecimal firstDecimal, BigDecimal secondDecimal) {
        return firstDecimal.multiply(secondDecimal);
    }

    /**
     * 相乘，保留位数
     *
     * @param firstDecimal  被乘数
     * @param secondDecimal 乘数
     * @param decimalPoint  小数位数
     * @return BigDecimal
     */
    public static BigDecimal multiply(BigDecimal firstDecimal, BigDecimal secondDecimal, int decimalPoint) {
        return format(firstDecimal.multiply(secondDecimal), decimalPoint);
    }

    /**
     * 相除,默认保留两位
     *
     * @param firstDecimal  被除数
     * @param secondDecimal 除数(不可为0，否者返回null,并出现异常)
     * @return BigDecimal
     */
    public static BigDecimal divide(BigDecimal firstDecimal, BigDecimal secondDecimal) {
        return divide(firstDecimal, secondDecimal, DEFAULT_DECIMAL_2);
    }

    /**
     * 相除，保留位数
     *
     * @param firstDecimal  被除数
     * @param secondDecimal 除数(不可为0，否者返回null,并出现异常)
     * @param decimalPoint  小数位数
     * @return BigDecimal
     */
    public static BigDecimal divide(BigDecimal firstDecimal, BigDecimal secondDecimal, int decimalPoint) {
        try {
            return format(firstDecimal.divide(secondDecimal), decimalPoint);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 两数比较，<p>return Int小于0: v1小于v2;</p>
     * <p>return Int=0: v1=v2 ;</p>
     * <p>return Int>0: v1>v2</p>
     *
     * @return int
     */
    public static int compare(BigDecimal v1, BigDecimal v2) {
        return v1.compareTo(v2);
    }

    /**
     * 取最大值
     *
     * @return BigDecimal
     */
    public static BigDecimal getMax(BigDecimal v1, BigDecimal v2) {
        if (v1.compareTo(v2) < 0) {
            return v2;
        } else {
            return v1;
        }
    }

    /**
     * 取最大值List
     *
     * @return BigDecimal
     */
    public static BigDecimal getMax(List<BigDecimal> v1) {
        BigDecimal[] decimals = new BigDecimal[v1.size()];
        decimals = v1.toArray(decimals);
        return getMax(decimals);
    }

    /**
     * 取最大值
     *
     * @return BigDecimal
     */
    public static BigDecimal getMax(BigDecimal[] v1) {
        BigDecimal maxBigDecimal = null;
        // 数组长度-1，防止最后一位进行取最大值时，出现数组越界异常
        for (int i = 0; i < v1.length -1; i++) {
            maxBigDecimal = getMax(v1[i], v1[i + 1]);
        }
        return maxBigDecimal;
    }

    /**
     * 取最小值
     *
     * @return BigDecimal
     */
    public static BigDecimal getMin(BigDecimal v1, BigDecimal v2) {
        if (v1.compareTo(v2) < 0) {
            return v1;
        } else {
            return v2;
        }
    }

    /**
     * 取最小值List
     *
     * @return BigDecimal
     */
    public static BigDecimal getMin(List<BigDecimal> v1) {
        BigDecimal[] decimals = new BigDecimal[v1.size()];
        decimals = v1.toArray(decimals);
        return getMin(decimals);
    }

    /**
     * 取最小值
     *
     * @return BigDecimal
     */
    public static BigDecimal getMin(BigDecimal[] v1) {
        BigDecimal maxBigDecimal = null;
        // 数组长度-1，防止最后一位进行取最大值时，出现数组越界异常
        for (int i = 0; i < v1.length -1; i++) {
            maxBigDecimal = getMin(v1[i], v1[i + 1]);
        }
        return maxBigDecimal;
    }
    /**
     * 格式化金额，带千位符，返回两位小数，四舍五入
     *
     * @param v1 数据
     * @return String
     */
    public static String moneyFormat(BigDecimal v1) {
        DecimalFormat decimalFormat = new DecimalFormat();
        decimalFormat.setMaximumFractionDigits(2);
        decimalFormat.setGroupingSize(3);
        decimalFormat.setRoundingMode(RoundingMode.HALF_UP);
        return decimalFormat.format(v1);
    }

    /**
     * 格式化金额，带千位符，返回指定位数，四舍五入
     *
     * @param v1           数据
     * @param decimalPoint 指定位数
     * @return String
     */
    public static String moneyFormat(BigDecimal v1, int decimalPoint) {
        DecimalFormat decimalFormat = new DecimalFormat();
        decimalFormat.setMaximumFractionDigits(decimalPoint);
        decimalFormat.setGroupingSize(3);
        decimalFormat.setRoundingMode(RoundingMode.HALF_UP);
        return decimalFormat.format(v1);
    }

    public BigDecimalUtil() {
    }

    public static void main(String[] args) {
        BigDecimal bigDecimal_1 = new BigDecimal(0);
        BigDecimal bigDecimal_2 = new BigDecimal(-11213.125355);
//        System.out.println(divide(bigDecimal_2,bigDecimal_1));
        System.out.println(BigDecimalUtil.moneyFormat(bigDecimal_2, 4));
//        System.out.println(new BigDecimal(0).equals(new BigDecimal(1)));

    }
}
