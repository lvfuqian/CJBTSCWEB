package com.elegps.tscweb.serivce;

import java.util.List;

import com.elegps.tscweb.exception.MessageException;
import com.elegps.tscweb.model.TbGpsInfo;
import com.elegps.tscweb.model.TbGrpInfo;




public interface GpsManager 
{ 
	/**
	 * 获取所有GPS服务商登录账号数量
	 * @return 所有GPS服务商登录账号的数量 
	 */
	int getGps_idAllCount();
	
	
	/**
	 * 根据每页记录数，总记录数获取总页数
	 * @param count 总记录数
	 * @param pageSize 每页显示的记录数
	 * @return 计算得到的总页数
	 */
	int getPageCount(int count , int pageSize);
	
	
	/**
	 * 返回特定页面所有GPS服务商登录账号
	 * @param pageNo 指定页码
	 * @param  pagesize 每页显示的条数
	 * @return 指定页的全部GPS服务商登录账号信息
	 */
	List <TbGpsInfo> getAllGps_idyPage(int pageNo,int pagesize);
	
	
	
	/**
	 * 获取指定GPS服务商登录账号数量
	 * @return 指定GPS服务商登录账号的数量 
	 */
	int getGps_idCount(String gps_id,String gps_name);
	
	
	
	/**
	 * 返回特定页面指定GPS服务商登录账号
	 * @param pageNo 指定页码
	 * @param  pagesize 每页显示的条数
	 * @return 指定页的GPS服务商登录账号信息
	 */
	List <TbGpsInfo> getGps_idyPage(int pageNo,int pagesize,String gps_id,String gps_name);
	

	
	/**
	 * 创建一条GPS服务商登录账号信息
	 * @param gps_id 登录账号
	 * @param password 密码
	 * @return 新创登录账号的主键,如果创建失败，返回null。
	 */  
	String createGps(String gps_id , String password,String gps_name);
	
	
	
	/**根据gps_id列表
	 * 删除记录
	 * @param grpid_list
	 */
	Boolean deleteGps(String[] gpsid_list);
	
	
	/**
	 * 根据服务商登录账号加载信息
	 * @param gps_id
	 * @return
	 */
	TbGpsInfo getGps_bygrpid(String gps_id);
	
	
	/**根据gps_id更新记录列表
	 * 更新记录列表
	 * @param gps_id
	 * @param password
	 * @param gps_name
	 */
	String modifyGps(String gps_id,String password,String gps_name);
	
	
	/**
	 * 所有GPS厂商信息
	 * @return 指定页的全部GPS厂商信息
	 */
	List <TbGpsInfo> getAllGps_Info();
	
	
}