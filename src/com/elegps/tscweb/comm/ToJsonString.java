package com.elegps.tscweb.comm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
/**
 * http接口返回jsonString参数封装工具类
 * @author Leo
 *
 */
public class ToJsonString {

	/**
	 * 参数封装
	 * @param flag
	 * @param msg
	 * @param obj 参数格式（"key",value,"key",value,......）可以不传此参数
	 * @return Json格式字符串
	 */
	public static String packagePara(String flag,String msg,Object...obj){
		return format(flag,msg,new String[]{"password"},obj);
	}
	
	/**
	 * 参数封装
	 * @param flag
	 * @param msg
	 * @param excludes 需要过滤的参数名称
	 * @param obj 参数格式（"key",value,"key",value,......）可以不传此参数
	 * @return Json格式字符串
	 */
	public static String packagePara(String flag,String msg,String[] excludes,Object...obj){
		return format(flag,msg,excludes,obj);
	}
	
	/**
	 * 单参数转换jsonStr
	 * @param obj（数据bean、list、string...）
	 * @return
	 */
	public static String packageObjPara(Object obj){
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(Date.class,new JsonDateValueProcessor());
		JSONArray jsonArray = JSONArray.fromObject(obj,jsonConfig);
		return jsonArray.toString();
	}
	
	private static String format(String flag,String msg,String[] excludes,Object...obj){
		Map<String,Object> map = new HashMap<String, Object>();
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(Date.class,new JsonDateValueProcessor());
		jsonConfig.setExcludes(excludes);
		map.put("flag", flag);
		map.put("msg", msg);
		
		if(obj.length>0){
			if(obj.length%2!=0){
				return "封装参数错误";
			}
			String key ="";
			Object o =null;
			for (int i = 0; i < obj.length; i++) {
				if(i%2==0){
					key = ObjectToString(obj[i]);
				}
				o=obj[i];
				map.put(key, o);
			}
			
		}
		System.out.println(msg);
//		System.out.println(obj[0]+"///////"+obj[1]+"///////"+obj[2]);
		JSONArray jsonArray = JSONArray.fromObject(map,jsonConfig);
		return jsonArray.toString();
	}
	
    private static String ObjectToString(Object o) {
        try {
            if (o == null)
                return null;
            return o.toString();
        } catch (Exception e) {
            return null;

        }
    }
}