package com.elegps.tscweb.comm;


import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

/**
 * ȡ����һ�ο۷�����
 * @author Administrator
 *
 */

public class DeductMonthDay{
	private static HashMap monthofDays=new HashMap();
	public static void main(String[] args) throws IOException {	   
//		   SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
//	   		Date date=dateFormat.parse("2010-5-27");
//			String ts=dateFormat.format(new Date());
	//		System.out.println(date);
//			System.out.println(ts);
		//���п۷Ѽ�����
//		 if(!Deduct.isrun){
//			   Deduct.isrun=true;
//			   Thread deduct = null;
//			   deduct = new Thread(new Deduct());
//			   deduct.start();
//			   deduct.isAlive();
//		   }  
		 
//			System.out.println(DeductMonthDay.getnextDate(50,20)); //��һ���������⣬�ڶ������ 
	}
		
	/**
	 * level 
	 * ȡ����һ�ο۷�����(����)  ������ʱ
	 */
	public static String getnextDate(){
		 Calendar   cal   =   Calendar.getInstance();
		  int nextday;
		  int year=cal.get(cal.YEAR);
		  int currnetmonth=cal.get(cal.MONTH)+1;		  
		  int dat=cal.get(cal.DATE);
		  System.out.println("�������������"+cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		  String month;
		  if(currnetmonth+1<10){
			  month="0"+String.valueOf(currnetmonth+1);
		  }else if(currnetmonth==12){
			  month="01";
		  }else{
			  month=String.valueOf(currnetmonth+1);
		  }
		  if (currnetmonth==12){
			  getMonth_ofDay(year+1);
			  year+=1;
			  nextday=Integer.parseInt(monthofDays.get(Integer.toString(1)).toString());  //ȡ����һ���µ�����������
			  if(dat<=nextday){
				  if(dat==cal.getActualMaximum(Calendar.DAY_OF_MONTH)){
					  return year+"-"+month+"-"+nextday;
				  }else{
					  return year+"-"+month+"-"+dat;
				  }
				 
			  }else{
				  return year+"-"+month+"-"+nextday;
			  }
		  }else{
			  getMonth_ofDay(year);
			  nextday=Integer.parseInt(monthofDays.get(Integer.toString(currnetmonth+1)).toString());  //ȡ����һ���µ�����������
			  if(dat<=nextday){
				  if(dat==cal.getActualMaximum(Calendar.DAY_OF_MONTH)){
					  return year+"-"+month+"-"+nextday;
				  }else{
					  return year+"-"+month+"-"+dat;
				  }
				 
			  }else{
				  return year+"-"+month+"-"+nextday;
			  }
		  }	
		    
	}
	
	
	
	/**
	 * level 
	 * ȡ����һ�ο۷�����(����)  ��������ʱ
	 */
	public static String getnextDate(float Package_Fee,float Balance_cash){
		 Calendar   cal   =   Calendar.getInstance();
		  int nextday;
		  int year=cal.get(cal.YEAR);
		  int currnetmonth=cal.get(cal.MONTH)+1;		  
		  int dat=cal.get(cal.DATE);
		  BigDecimal sydate= new BigDecimal(String.valueOf(Balance_cash/Package_Fee*cal.getActualMaximum(Calendar.DAY_OF_MONTH))).setScale(0, BigDecimal.ROUND_HALF_UP);  //�����ն����ȡ���ն˻������õ�����
          int i=Integer.parseInt(sydate.toString());
          System.out.println(sydate);
          System.out.println(i);
		  System.out.println("�������������"+cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		  String month; 
		  if (currnetmonth==12){
			  getMonth_ofDay(year+1);
			  if(dat+i<=cal.getActualMaximum(Calendar.DAY_OF_MONTH)){
					 dat=dat+i;
				 }else{
					 dat=dat+i-cal.getActualMaximum(Calendar.DAY_OF_MONTH);
					 year+=1;
					 currnetmonth=1;
				 }
			 
		  }else{
			  getMonth_ofDay(year);
			  int j=cal.getActualMaximum(Calendar.DAY_OF_MONTH);
			  if(dat+i<=j){
					 dat=dat+i;
				 }else{
					 dat=dat+i-j;
					 currnetmonth+=1;
				 }
		  }
		  
		  if(currnetmonth<10){
			  month="0"+String.valueOf(currnetmonth);
		  }else{
			  month=String.valueOf(currnetmonth);
		  }
		return year+"-"+month+"-"+dat;			    
	}

	/**
	 * level 
	 * ȡ�ÿ��Կ۷ѵ�����  ��������ʱ
	 */
	public static int getDate(float Package_Fee,float Balance_cash){
		 Calendar   cal   =   Calendar.getInstance();
		  int nextday;
		  int year=cal.get(cal.YEAR);
		  int currnetmonth=cal.get(cal.MONTH)+1;		  
		  int dat=cal.get(cal.DATE);
		  BigDecimal sydate= new BigDecimal(String.valueOf(Balance_cash/Package_Fee*cal.getActualMaximum(Calendar.DAY_OF_MONTH))).setScale(0, BigDecimal.ROUND_HALF_UP);  //�����ն����ȡ���ն˻������õ�����
          int date=Integer.parseInt(sydate.toString());
          System.out.println(date);
		return date;			    
	}
	public static void getMonth_ofDay(int year){
		if   ((year % 4)>0) {
			monthofDays.put("1", 31);
			monthofDays.put("2", 28);
			monthofDays.put("3", 31);
			monthofDays.put("4", 30);
			monthofDays.put("5", 31);
			monthofDays.put("6", 30);
			monthofDays.put("7", 31);
			monthofDays.put("8", 31);
			monthofDays.put("9", 30);
			monthofDays.put("10", 31);
			monthofDays.put("11", 30);
			monthofDays.put("12", 31);
		}else{
			monthofDays.put("1", 31);
			monthofDays.put("2", 29);
			monthofDays.put("3", 31);
			monthofDays.put("4", 30);
			monthofDays.put("5", 31);
			monthofDays.put("6", 30);
			monthofDays.put("7", 31);
			monthofDays.put("8", 31);
			monthofDays.put("9", 30);
			monthofDays.put("10", 31);
			monthofDays.put("11", 30);
			monthofDays.put("12", 31);
		}
	     
	}
		


}
