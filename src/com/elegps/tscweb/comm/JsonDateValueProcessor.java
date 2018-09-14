package com.elegps.tscweb.comm;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

/**
 * JSON 日期格式处理（java转化为JSON�?
 * @author Administrator
 *
 */
public class JsonDateValueProcessor implements JsonValueProcessor {

	private String datePattern = "yyyy-MM-dd";
	private String dateTimePattern = "yyyy-MM-dd HH:mm:ss";
	
	public Object processArrayValue(Object value, JsonConfig jsonConfig) {
		// TODO Auto-generated method stub
		return process(value);
	}

	public Object processObjectValue(String key, Object value, JsonConfig jsonConfig) {
		// TODO Auto-generated method stub
		return process(value);
	}

	private Object process(Object value) {
		String format =dateTimePattern;
		if(String.valueOf(value).contains("00:00:00")){
			format = datePattern;
		}
	      try {    
	             if (value instanceof Date) {    
	                 SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.UK);    
	                 return sdf.format((Date) value);    
	             }    
	             return value == null ? "" : value.toString();    
	         } catch (Exception e) {    

	             return "";    
	         }    
	} 


}
