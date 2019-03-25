package com.bonc.process.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

	public static SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
	public static SimpleDateFormat sdfT=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//System.out.println(compareDate("2016-07-27 12:32:12"));
//		System.currentTimeMillis();
//		System.out.println(returnDateFromLong(System.currentTimeMillis()+""));
		
		System.out.println(returnNow());
	}
	//如果传过来的时间大于当前时间，返回true
	public static boolean compareDate(String anotherdate){
		
		boolean b=false;
		try{
			
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date date=sdf.parse(anotherdate);
		Calendar c=Calendar.getInstance();

		int i=date.compareTo(c.getTime());
		if(i>0){
			b=true;
		}
		System.out.println("i:"+i);
		}catch(Exception e){
			e.printStackTrace();
		}
		return b;
	}
	
	//把毫秒数处理为字符串
	public static String returnDateFromLong(String millions){
		
		String str="";
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		Date date=new Date(Long.parseLong(millions));
		str=sdf.format(date);
		
		return str;
		
	}
	
	
	//返回当前时间的yyyy-MM-dd形式
	public static String returnNow(){
		
		String str="";
		Calendar c=Calendar.getInstance();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		str=sdf.format(c.getTime());
		
		return str;
	}
	
	/**
	 * 字符串转成日期格式 yyyy-MM-dd 
	 * @param 字符串
	 * @return  Date
	 */
	public static Date transferDate(String time){
		try {
			return sdf.parse(time);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	/**
	 *  字符串转成日期格式  yyyy-MM-dd HH:mm:ss
	 * @param 字符串
	 * @return Date	  
	 */
	public static Date transferDateT(String time){
		try {
			if(time == null) return null;
			return sdfT.parse(time);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 日期转成字符串   yyyy-MM-dd 
	 * @param date
	 * @return 字符串
	 */
	public static String transferDate(Date date){
		return sdf.format(date);
	}
	
	/**
	 * 日期转成字符串  yyyy-MM-dd HH:mm:ss
	 * @param date
	 * @return 字符串
	 */
	public static String transferDateT(Date date){
		return sdfT.format(date);
	}
	
	
}
