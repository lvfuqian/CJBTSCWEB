package com.elegps.tscweb.dao;

import org.hibernate.Session;

import com.elegps.tscweb.model.TbGpsMsListInfo;
import com.elegps.tscweb.model.TbGrpInfo;
import com.elegps.tscweb.model.TbGrpMsListInfo;
import com.elegps.tscweb.model.TbMsInfo;

import java.util.List;

public interface GpsMsDaoFactory
{
	/**
	 * 获取GPS服务商登录账号所有信息数量
	 * GPS服务商登录账号所有信息总记录数
	 * @return 获取GPS服务商登录账号所有信息数量 
	 */
	Integer findGps_idAllCount(Session sess);
	
	
	/**
	 * 返回GPS厂商终端对应关系指定页面所有GPS厂商终端对应关系信息
	 * @param pageNo 指定页码
	 * @param  pagesize 每页显示的条数
	 * @return 返回GPS厂商终端对应关系指定页面所有GPS厂商终端对应关系信息
	 */ 
	List findAllGpsMs_InfoGrpidByPage(Session sess,int pageNo,int pagesize);
	
	
	/**
	 * 获取指定GPS厂商所有信息数量
	 * @param gps_id GPS厂商帐号
	 * @return 获取指定GPS厂商所有信息数量
	 */
	Integer findGps_idCount(Session sess,String gps_id);
	
	
	/**
	 * 返回GPS厂商终端对应关系中按GPS厂商帐号指定页面所有GPS厂商终端对应关系信息
	 * @param pageNo 指定页码
	 * @param  pagesize 每页显示的条数
	 * @param  gps_id GPS厂商帐号
	 * @return 返回GPS厂商终端对应关系中按GPS厂商帐号指定页面所有GPS厂商终端对应关系信息
	 */ 
	List findAllGpsid_InfoByPage(Session sess,int pageNo,int pagesize,String gps_id);
	
	
	
	/**
	 * 返回GPS厂商终端对应关系指定页面所有GPS厂商终端对应关系信息
	 * @param pageNo 指定页码
	 * @param  pagesize 每页显示的条数
	 * @return 返回GPS厂商终端对应关系指定页面所有GPS厂商终端对应关系信息
	 */ 
	List findAllGpsMs_InfoMsidByPage(Session sess,int pageNo,int pagesize);
	
	
	
	/**
	 * 获取指定终端号所有信息数量
	 * @param ms_id 终端号
	 * @return 获取指定终端号所有信息数量
	 */
	Integer findMs_idCount(Session sess,String ms_id);
	
	
	
	/**
	 * GPS厂商终端对应关系中按终端号指定页面所有GPS厂商终端对应关系信息
	 * @param pageNo 指定页码
	 * @param  pagesize 每页显示的条数
	 * @param  ms_id 终端号
	 * @return 返回GPS厂商终端对应关系中按终端号指定页面所有GPS厂商终端对应关系信息
	 */ 
	List findAllMsid_InfoByPage(Session sess,int pageNo,int pagesize,String ms_id);
	
	
	
	/**
	 * 根据主键加载GPS厂商与终端对应关系信息
	 * @param sess 持久化操作所需要的Hiberate Session
	 * @param gps_id GPS帐号
	 * @param ms_id 终端号
	 * @return 根据主键加载终群组与端对应关系信息
	 */
	TbGpsMsListInfo get(Session sess , String gps_id,String ms_id);  
	
	
	/**
	 * 删除GPS厂商与终端对应关系信息
	 * @param sess 持久化操作所需要的Hiberate Session
	 * @param tbgrpmsinfo GPS厂商与终端对应关系信息对象
	 */
	void delete(Session sess,TbGpsMsListInfo tbgpsmsinfo); 
	
	
	/**
	 * 保存GPS厂商终端对应关系
	 * @param sess 持久化操作所需要的Hiberate Session
	 * @param m 需要保存的GPS厂商终端对应关系信息
	 * @retun GPS厂商信息的主键
	 */
    String save(Session sess , TbGpsMsListInfo gpsms);
    
    
    
    /**根据gps_id删除记录
	 * @param gps_id GPS号码
	 */
	void deleteGpsMsInfoByGps_id(Session sess,String gps_id);
	
	
	/**根据ms_id删除记录
	 * @param ms_id 群组号码
	 */
	void deleteGpsMsInfoByMs_id(Session sess,String ms_id);
	
	
	 /**
	 * 根据查询条件取得记录总数
	 * @param gpsid
	 * @param msid
	 * @return  查询条件取得记录总数
	 */
    Integer findGpsMs_SearchCount(Session sess,String gpsid,String msid,String pagentid,String childagentid,String ep);
   
    
     /**
	    * 根据指定页及查询条件取得记录信息
	    * @param pageNo
	    * @param pageSize
	    * @param gpsid
	    * @param msid
	    * @return  指定页及查询条件取得记录信息
	    */
    List findGpsMs_SearchByPage(Session sess,int pageNo,int pageSize,String gpsid,String msid,String pagentid,String childagentid,String ep);
    
    
    /**
     * 跟据GPS号查询已经存在的终端信息
     * @param gpsid
     * @return
     */
    List findAllms_bygpsid(Session sess,String gpsid);
   
    
}