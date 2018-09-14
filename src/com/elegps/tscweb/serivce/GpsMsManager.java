package com.elegps.tscweb.serivce;

import java.util.List;

import com.elegps.tscweb.exception.MessageException;
import com.elegps.tscweb.model.TbGpsMsListInfo;
import com.elegps.tscweb.model.TbGrpInfo;
import com.elegps.tscweb.model.TbGrpMsListInfo;




public interface GpsMsManager 
{ 
	/**
	 * 获取GPS服务商登录账号所有信息数量
	 * GPS服务商登录账号所有信息总记录数
	 * @return 获取GPS服务商登录账号所有信息数量 
	 */
	int getAllGps_idCount();
	
	
	
	/**
	 * 根据每页记录数，总记录数获取总页数
	 * @param count 总记录数
	 * @param pageSize 每页显示的记录数
	 * @return 计算得到的总页数
	 */
	int getPageCount(int count , int pageSize);
	
	
	/**
	 * 获取指定GPS厂商所有信息数量
	 * @param gps_id GPS厂商帐号
	 * @return 获取指定GPS厂商所有信息数量
	 */
	int getGps_idCount(String gps_id);
	
	
	/**
	 * 返回GPS厂商终端对应关系指定页面所有GPS厂商终端对应关系信息
	 * @param pageNo 指定页码
	 * @param  pagesize 每页显示的条数
	 * @return 返回GPS厂商终端对应关系指定页面所有GPS厂商终端对应关系信息
	 */ 
	List <TbGpsMsListInfo> getAllGrpid_InfoByPage(int pageNo,int pagesize);
	
	
	/**
	 * 返回GPS厂商终端对应关系中按GPS厂商帐号指定页面所有GPS厂商终端对应关系信息
	 * @param pageNo 指定页码
	 * @param  pagesize 每页显示的条数
	 * @param  gps_id GPS厂商帐号
	 * @return 返回GPS厂商终端对应关系中按GPS厂商帐号指定页面所有GPS厂商终端对应关系信息
	 */ 
	List <TbGpsMsListInfo> getGpsid_InfoByPage(int pageNo,int pagesize,String gps_id);
	
	
	
	/**
	 * 返回GPS厂商终端对应关系指定页面所有GPS厂商终端对应关系信息
	 * @param pageNo 指定页码
	 * @param  pagesize 每页显示的条数
	 * @return 返回GPS厂商终端对应关系指定页面所有GPS厂商终端对应关系信息
	 */ 
	List <TbGpsMsListInfo> getAllMsid_InfoByPage(int pageNo,int pagesize);
	
	
	/**
	 * 获取指定终端号所有信息数量
	 * @param ms_id 终端号
	 * @return 获取指定终端号所有信息数量
	 */
	int getMs_idCount(String ms_id);
	
	
	/**
	 * GPS厂商终端对应关系中按终端号指定页面所有GPS厂商终端对应关系信息
	 * @param pageNo 指定页码
	 * @param  pagesize 每页显示的条数
	 * @param  ms_id 终端号
	 * @return 返回GPS厂商终端对应关系中按终端号指定页面所有GPS厂商终端对应关系信息
	 */ 
	List <TbGpsMsListInfo> getMsid_InfoByPage(int pageNo,int pagesize,String ms_id);
	
	
	
	/**根据gps_id,ms_id 删除记录
	 * @param gpsmsidlist
	 */
	Boolean deleteGpsMs(String[] gpsmsidlist);
	
	
	
	/**
	 * 创建一条GPS厂商终端对应关系信息
	 * @param gps_id GPS号码
	 * @param ms_id 终端号
	 * @return 新创终端的主键,如果创建失败，返回null。
	 */  
	String createGpsMsInfo(String gps_id , String ms_id);
	
	/**
	 * 根据查询条件取得记录总数
	 * @param gpsid
	 * @param msid
	 * @return  查询条件取得记录总数
	 */
   int getGpsMs_SearchCount(String gpsid,String msid,String pagentid,String childagentid,String ep);
   
   /**
    * 根据指定页及查询条件取得记录信息
    * @param pageNo
    * @param pageSize
    * @param gpsid
    * @param msid
    * @return  指定页及查询条件取得记录信息
    */
   List getGpsMs_SearchByPage(int pageNo,int pageSize,String gpsid,String msid,String pagentid,String childagentid,String ep);
   
   
   /**
    * 跟据GPS号查询已经存在的终端信息
    * @param gpsid
    * @return
    */
   List  getMs_info(String gpsid);
   
   /**
    * 跟据GPS号批量添加终端号
    * @param gpsid
    * @param msid
    * @return
    */
  String createGpsMsInfo_ByGpsid(String gpsid,String[] msid);

}