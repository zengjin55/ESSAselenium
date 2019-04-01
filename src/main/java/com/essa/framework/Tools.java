package com.essa.framework;

import java.text.SimpleDateFormat;
import java.time.Year;
import java.util.Calendar;
import java.util.Date;

public class Tools {

    /**
     * 获取当前时间
     * @return MMdd_HHmmss
     */
    public static String getCurrentTime() {
		SimpleDateFormat format = new SimpleDateFormat("MMdd_HHmmss");
		Date today = new Date();
		String time = format.format(today);
		return time;
	}
    /**
     * 使用当前时间作为工厂货号
     * @return yyyyMMddHHmmss
     */
    public static String getFactoryNo() {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		Date today = new Date();
		String time = format.format(today);
		return time;
	}
	/**
	 *格式化当前时间，由于服务器时间与北京时间有误差，这里取值当前时间-5分钟
	 * @param
	 * @return  yyyy/MM/dd HH:mm:ss
	 */
	public static String getFormatTime(){
		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date today = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(today);
		cal.add(Calendar.MINUTE,-5);
		today = cal.getTime();
		String time = format.format(today);
		return time;
	}
    
    /**
     * 获取当前分和秒，另外加一个随机数，作为注册邮箱的区分的数字
     * @return
     */
    public static String getTime() {
		SimpleDateFormat format = new SimpleDateFormat("mmss");
		Date today = new Date();
		String time = format.format(today);
		int i = (int)(1+Math.random()*(10-1+1));
		return time+i;
	}
	/**
	 *获取当前时间的下一个月
	 * @param
	 * @return String 格式化后的时间
	 */
    public static String getNextMonth(){
		Date today = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(today);
		cal.add(Calendar.MONTH,1);
		return  myForm(cal);
	}
	/**
	 *获取当前时间+i天,传入的i为当前时间往后推的天数
	 * @param
	 * @return String
	 */
	public static String moreDays(int i){
		Date today = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(today);
		cal.add(Calendar.DATE,i);
		return myForm(cal);
	}

	/**
	 *获取格式化后的当天
	 * @param
	 * @return String
	 */
	public static String getToday(){
		Date today = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(today);
		return myForm(cal);
	}

	/**
	 *由于bpms系统遇到日期中“天”为0开头时无法输入，故自己写一个格式
	 * @param
	 * @return
	 */
	public static String myForm(Calendar cal){
		int day = cal.get(Calendar.DATE);
		int month = cal.get(Calendar.MONTH)+1;
		int year = cal.get(Calendar.YEAR);
		return month+"/"+day+"/"+ year;
	}
}
