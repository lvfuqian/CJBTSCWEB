package com.elegps.tscweb.comm;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期格式化工具
 * @author Administrator
 *
 */
public class DateFormat {

	private static SimpleDateFormat sdf;
	private static String dateFormat = "yyyy-MM-dd HH:mm:ss";
	/**
	 * String时间转Date
	 * @param date日期时间
	 * @param format(""/null) 默认格式(yyyy-MM-dd HH:mm:ss) 
	 * @return Date
	 */
	public static Date parse(String date , String format){
		if(format=="" || format ==null){
			format = dateFormat;
		}
		sdf= new SimpleDateFormat(format);
		try {
			return sdf.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new Date();
	}
	
	/**
	 *Date转String
	 * @param date日期时间
	 * @param format(""/null) 默认格式(yyyy-MM-dd HH:mm:ss) 
	 * @return String
	 */
	public static String format(Date date,String format){
		if(format=="" || format == null){
			format = dateFormat;
		}
		sdf= new SimpleDateFormat(format);
		return sdf.format(date);
	}
	
	/**
	 *string转String
	 * @param date日期时间
	 * @param format(""/null) 默认格式(yyyy-MM-dd HH:mm:ss) 
	 * @return String
	 */
	public static String strToStr(String date,String format){
		if(format=="" || format ==null){
			format = dateFormat;
		}
		sdf= new SimpleDateFormat(format);
		String s =null;
		try {
			Date d = sdf.parse(date);
			s =sdf.format(d);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return s;
	}
	
	/**
	 * 去除�?��中文
	 * @param date
	 * @return
	 */
	public static String delZW(String date){
		date = date.replaceAll("[\u4e00-\u9fa5]", "");
		return date;
	}
	
	/**
	 * 时间加减运算
	 * @param date �?��算的时间
	 * @param format(""/null) 默认格式(yyyy-MM-dd HH:mm:ss) 
	 * @param year �?
	 * @param month �?
	 * @param day �?
	 * @param hour �?
	 * @param minute �?
	 * @param second �?
	 * @return String
	 */
	public static String nowDateComputeFormat(Date date,String format,Integer year,
			Integer month,Integer day,Integer hour,Integer minute,Integer second){
		if(format=="" || format ==null){
			format = dateFormat;
		}
		if(date == null){
			date=new Date();
		}
		sdf= new SimpleDateFormat(format);
	
		Date d = dateCompute(date, year, month, day, hour, minute, second); //加减后时�?
		
		return sdf.format(d);
	}
	
	/**
	 * 时间加减运算
	 * @param date �?��算的时间
	 * @param year �?
	 * @param month �?
	 * @param day �?
	 * @param hour �?
	 * @param minute �?
	 * @param second �?
	 * @return DateDate
	 */
	public static Date nowDateComputeFormat(Date date,Integer year,
			Integer month,Integer day,Integer hour,Integer minute,Integer second){
		if(date == null){
			date=new Date();
		}
		Date d = dateCompute(date, year, month, day, hour, minute, second); //加减后时�?
		return d;
	}
	
	private static Date dateCompute(Date date,Integer year,
			Integer month,Integer day,Integer hour,Integer minute,Integer second){
		Calendar c = Calendar.getInstance();//获得�?��日历的实�?
		c.setTime(date);//设置日历时间 
		c.add(Calendar.YEAR, year);	//在日历的月份上加减年
		c.add(Calendar.MONTH,month);//在日历的月份上加减月
		c.add(Calendar.DATE,day);	//在日历的月份上加减日
		c.add(Calendar.HOUR,hour);//在日历的月份上加减时
		c.add(Calendar.MINUTE,minute);//在日历的月份上加减分
		c.add(Calendar.SECOND,second);//在日历的月份上加减秒
		
		Date d = c.getTime(); //加减后时�?
		
		return d;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Date dd = new Date();
//		System.out.println(dd);
		System.out.print(format(dd,null));
//		System.out.print(DateFormat.nowDateComputeFormat(null, "yyyy-MM-dd HH:mm:ss", 0, 0, 1, 0, 0, 0));
	}
}
