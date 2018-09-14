package com.elegps.tscweb.dao;

import org.hibernate.Session;

import com.elegps.tscweb.model.TbGpsInfo;
import com.elegps.tscweb.model.TbGrpInfo;
import com.elegps.tscweb.model.TbMsInfo;

import java.util.List;

public interface GpsDaoFactory
{
	/**
	 * 获取所有GPS服务商登录账号数量
	 * @return 所有GPS服务商登录账号的数量 
	 */
	Integer getGps_idAllCount(Session sess);
	
	
	/**
	 * 返回特定页面所有GPS服务商登录账号
	 * @param pageNo 指定页码
	 * @param  pagesize 每页显示的条数
	 * @return 指定页的全部GPS服务商登录账号信息
	 */
    List findAllGps_typeByPage(Session sess , int pageNo , int pageSize);
    
    
    /**
	 * 获取所有GPS服务商登录账号数量
	 * @return 所有GPS服务商登录账号的数量 
	 */
    Integer getGps_idCount(Session sess,String gps_id,String gps_name);
    
    
    /**
	 * 返回特定页面指定GPS服务商登录账号
	 * @param pageNo 指定页码
	 * @param  pagesize 每页显示的条数
	 * @return 指定页的GPS服务商登录账号信息
	 */
    List findGps_typeByPage(Session sess , int pageNo , int pageSize,String gps_id,String gps_name);
    
    
    /**
	 * 根据主键加载服务商登录账号信息
	 * @param sess 持久化操作所需要的Hiberate Session
	 * @param gps_id 需要加载登录账号信息
	 * @return 加载服务商登录账号信息
	 */
    TbGpsInfo get(Session sess , String gps_id);  //这里不行主键只能为int,long不能为字符串
    
    
    /**
     * 查看有没有相同的gsp_id,gsp_name
     * @param sess
     * @param gps_id
     * @param gps_name
     * @return
     */
    TbGpsInfo getidorname(Session sess,String gps_id,String gps_name); 
    
    
    /**
     * 查找除gps_id以外名称为gps_name的记录
     * @param sess
     * @param gps_id
     * @param gps_name
     * @return
     */
    TbGpsInfo get_byname(Session sess,String gps_id,String gps_name); 
    
    
    /**
	 * 保存服务商登录账号信息
	 * @param sess 持久化操作所需要的Hiberate Session
	 * @param m 需要保存的服务商登录账号信息
	 * @retun 服务商登录账号信息的主键
	 */
    String save(Session sess , TbGpsInfo m);
    
    /**
     * 删除对象
     * @param sess
     * @param m
     */
    void delete(Session sess,TbGpsInfo m);
    
    
    /**
	 * 所有GPS厂商信息
	 * @return 指定页的全部GPS厂商信息
	 */
    List findAllGps_Info(Session sess);
}