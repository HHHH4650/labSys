package com.util;
/**
 * 时间格式工具类
 */
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Util {
	/*
	 * 格式化日期
	 */
	public static Date newDate(String s) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");		//定义日期格式
		Date date = new Date();		
		date = sdf.parse(s);		//把String型的字符串转换成特定格式的date类型  
		return date;
	}

	public static Date newDate1(String s) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date date = new Date();
		date = sdf.parse(s);
		return date;
	}

	public static Date FormatFullDate(String s) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		date = sdf.parse(s);	
		return date;

	}

	public static String splitDate(Date d) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(d);
	}

	public static String splitDate1(Date d) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
		return sdf.format(d);
	}
	
	
	/*
	 * 通过时间获取星期几
	 */
	public static String getWeekStr(String sdate) throws ParseException
	{

		Date date = Util.newDate(sdate);		//获取指定日期格式
		Calendar c = Calendar.getInstance();	//返回一个 Calendar 对象，Calendar是一个抽象类
		c.setTime(date);						//使用给定的 Date 设置此 Calendar 的时间。
		String str = new SimpleDateFormat("EEEE").format(c.getTime());//返回一个表示此 Calendar 时间值的 Date 对象。
		
		if ("1".equals(str)) {
			str = "星期日";
		} else if ("2".equals(str)) {
			str = "星期一";
		} else if ("3".equals(str)) {
			str = "星期二";
		} else if ("4".equals(str)) {
			str = "星期三";
		} else if ("5".equals(str))
		{
			str = "星期四";
		} else if ("6".equals(str))
		{
			str = "星期五";
		} else if ("7".equals(str)) {
			str = "星期六";
		}
		return str;
	}

	/**
	 * 将字符串截短，取前n个字符，英文算半个字符。
	 */
	public static String chop(String orignalString, double length,
			String chopedString) {
		if (orignalString == null || orignalString.length() == 0) {
			return orignalString;
		}
		orignalString = orignalString.replaceAll(" ", " ");
		if (orignalString.length() < length) {
			return orignalString;
		}
		StringBuffer buffer = new StringBuffer((int) length);
		length = length * 2;
		int count = 0;
		int stringLength = orignalString.length();
		int i = 0;
		for (; count < length && i < stringLength; i++) {
			char c = orignalString.charAt(i);
			if (c < '\u00ff') {
				count++;
			} else {
				count += 2;
			}
			buffer.append(c);
		}
		if (i < stringLength) {
			buffer.append(chopedString);
		}
		return buffer.toString();
	}

	public static long getPrimeKey() {
		GregorianCalendar calendar = new GregorianCalendar();
		return calendar.getTimeInMillis();
	}

	public static long stringToLong(String source) {
		return Long.parseLong(source);
	}
}
