package com.work.auth.util;

/**
 * ClassName: StringUtil 
 * @Description: 字符串工具类
 * @author houxiaorui
 * @create 2019-11-22
 * @since 1.0.0
 */
public class StringUtil {

	/**
	 * 字符串为空判断
	 * @param str	字符串
	 * @return	boolean
	 */
	public static boolean isEmpty(String str){
		if(null == str || str.length() == 0){
			return true;
		}
		return false;
	}

	/**
	 * 字符串不为空判断
	 * @param str	字符串
	 * @return	boolean
	 */
	public static boolean isNotEmpty(String str){
		return !isEmpty(str);
	}

	/**
	 * 数据转换为字符串，默认返回“”
	 * @param obj 待转化数据
	 * @return	String
	 */
	public static String getString(Object obj){
		return getString(obj,"");
	}

	/**
	 * 数据转换为字符串,默认返回defaultStr
	 * @param obj	待转化数据
	 * @param defaultStr	默认返回字符串
	 * @return	String
	 */
	public static String getString(Object obj,String defaultStr){
		if(obj == null){
			return defaultStr;
		}
		return String.valueOf(obj).trim();
	}

	/**
	 * 两个字符串比较，区分大小写
	 * @param sourceStr	资源字符串
	 * @param targetStr	目标字符串
	 * @return	boolean
	 */
	public static boolean equals(String sourceStr,String targetStr){
		return equals(sourceStr,targetStr,false);
	}

	/**
	 * 两个字符串比较，不区分大小写
	 * @param sourceStr	资源字符串
	 * @param targetStr	目标字符串
	 * @return	boolean
	 */
	public static boolean equalsIgnoreCase(String sourceStr,String targetStr){
		return equals(sourceStr,targetStr,true);
	}

	/**
	 * 两个字符串比较
	 * @param sourceStr	资源字符串
	 * @param targetStr	目标字符串
	 * @param ignoreCase true 忽略大小写、false 不忽略
	 * @return	boolean
	 */
	public static boolean equals(String sourceStr,String targetStr,boolean ignoreCase){
		if(sourceStr == null && targetStr == null) {
			return true;
		}
		if(sourceStr == null || targetStr == null){
			return false;
		}
		if("".equals(sourceStr) && "".equals(targetStr)){
			return true;
		}
		if("".equals(sourceStr) || "".equals(targetStr)){
			return false;
		}else {
			if(ignoreCase){
				return sourceStr.equalsIgnoreCase(targetStr);
			}else{
				return sourceStr.equals(targetStr);
			}
		}
	}


	public static void main(String[] args) {
		System.out.println(equals("",""));
	}

}
