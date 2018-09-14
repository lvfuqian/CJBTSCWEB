package com.elegps.tscweb.dao;

import org.hibernate.Session;

import com.elegps.tscweb.model.TbGrpInfo;
import com.elegps.tscweb.model.TbGrpMsListInfo;
import com.elegps.tscweb.model.TbMsInfo;

import java.util.List;

public interface GrpMsDaoFactory
{
	
	/**
	 * 获取群组所有信息数量
	 * 群组所有信息总记录数
	 * @return 获取群组所有信息数量 
	 */
	Integer findGrp_idAllCount(Session sess);
	

	/**
	 * 返回群组终端对应关系指定页面所有群组终端对应关系信息
	 * @param pageNo 指定页码
	 * @param  pagesize 每页显示的条数
	 * @return 返回群组终端对应关系指定页面所有群组终端对应关系信息
	 */ 
	List findAllGrpMs_InfoGrpidByPage(Session sess,int pageNo,int pagesize);
	
	
	/**
	 * 获取指定群组号所有信息数量
	 * @param grp_id 群组号
	 * @return 获取指定群组号所有信息数量
	 */
	Integer findGrp_idCount(Session sess,String grp_id);
	
	/**
	 * 返回群组终端对应关系中按群组号指定页面所有群组终端对应关系信息
	 * @param pageNo 指定页码
	 * @param  pagesize 每页显示的条数
	 * @param  grp_id 群组号
	 * @return 返回群组终端对应关系中按群组号指定页面所有群组终端对应关系信息
	 */ 
	List findAllGrpid_InfoByPage(Session sess,int pageNo,int pagesize,String grp_id);
	
	
	
	/**
	 * 获取终端所有信息数量
	 * 群组所有信息总记录数
	 * @return 获取群组所有信息数量 
	 */
	Integer findMs_idAllCount(Session sess);
	
	
	/**
	 * 返回群组终端对应关系指定页面所有群组终端对应关系信息
	 * @param pageNo 指定页码
	 * @param  pagesize 每页显示的条数
	 * @return 返回群组终端对应关系指定页面所有群组终端对应关系信息
	 */ 
	List findAllGrpMs_InfoMsidByPage(Session sess,int pageNo,int pagesize);
	
	
	/**
	 * 获取指定终端号所有信息数量
	 * @param ms_id 终端号
	 * @return 获取指定终端号所有信息数量
	 */
	Integer findMs_idCount(Session sess,String ms_id);
	
	
	/**
	 * 群组终端对应关系中按终端号指定页面所有群组终端对应关系信息
	 * @param pageNo 指定页码
	 * @param  pagesize 每页显示的条数
	 * @param  ms_id 终端号
	 * @return 返回群组终端对应关系中按终端号指定页面所有群组终端对应关系信息
	 */ 
	List findAllMsid_InfoByPage(Session sess,int pageNo,int pagesize,String ms_id);
	
	
	
	/**
	 * 根据主键加载终群组与端对应关系信息
	 * @param sess 持久化操作所需要的Hiberate Session
	 * @param ms_id 终端号
	 * @param grp_id 终端号
	 * @return 根据主键加载终群组与端对应关系信息
	 */
	TbGrpMsListInfo get(Session sess , String grp_id,String ms_id);  
	/**
	 * 根据msid查找对应群组关系信息
	 * @param sess
	 * @param ms_id
	 * @return
	 */
	List getInfoByMs(Session sess ,String ms_id);
	/**
	 * 删除终群组与端对应关系信息
	 * @param sess 持久化操作所需要的Hiberate Session
	 * @param tbgrpmsinfo 群组与端对应对象
	 */
	void delete(Session sess,TbGrpMsListInfo tbgrpmsinfo); 
	
	/**根据grp_id删除记录
	 * @param grp_id 群组号码
	 */
	void deleteGrpMsInfoByGrp_id(Session sess,String grp_id);
	
	
	/**根据ms_id删除记录
	 * @param ms_id 群组号码
	 */
	void deleteGrpMsInfoByMs_id(Session sess,String ms_id);
	
	
	/**
	 * 保存群组终端对应关系
	 * @param sess 持久化操作所需要的Hiberate Session
	 * @param m 需要保存的群组信息
	 * @retun 群组信息的主键
	 */
    String save(Session sess , TbGrpMsListInfo grpms);
    
    /**
	 * 根据查询条件取得记录总数
	 * @param grpid
	 * @param msid
	 * @return  查询条件取得记录总数
	 */
    Integer findGrpMs_SearchCount(Session sess,String grpid,String msid,String pagentid,String childagentid,String ep);
   
    
     /**
	    * 根据指定页及查询条件取得记录信息
	    * @param pageNo
	    * @param pageSize
	    * @param grpid
	    * @param msid
	    * @return  指定页及查询条件取得记录信息
	    */
    List findGrpMs_SearchByPage(Session sess,int pageNo,int pageSize,String pagentid,String childagentid,String ep,String grpid,String msid);
    
    
    /**
     * 跟据群组号查询已经存在的终端信息
     * @param grpid
     * @return
     */
    List findAllms_bygrpid(Session sess,String grpid);
    
    /**
     * 跟据终端号查询已经存在的群组信息
     * @param msid
     * @return
     */
    List findAllgrp_bymsid(Session sess,String msid);

    /**
     * 查找这个终端在别的群组是否存在
     * @param sess
     * @param ms_id
     * @return
     */
	TbGrpMsListInfo get(Session sess, String ms_id);

	/**
     * 查找终端所在的主要群组（隶属类型0：MS_Id隶属GRP_Id  ， MS_Id在GRP_Id中的通话设置：0：允许通话）
     * @param sess
     * @param ms_id
     * @return
     */
	TbGrpMsListInfo getGrp(Session sess, String ms_id);
	
	/**
	 * 修改群组配置为允许时（0）
	 * @param sess
	 * @param grpid
	 * @param msid
	 */
	void setConfig(Session sess, String grpid,String rgrpid, String msid,int config);


	/**
	 * 删除
	 * @param sess
	 * @param grpid
	 * @param msid
	 */
	void delete(Session sess, String grpid, String[] msid);

	
	/**
	 * 删除
	 * @param sess
	 * @param grpid
	 * @param msid
	 */
	void delete(Session sess, String[] grpid, String msid);

	/**
	 * 获取某个群组的终端数量
	 * @param sess
	 * @param grpId
	 * @return
	 */
	Integer grpMsCount(Session sess,String grpId);
	
	/**
	 * 获取某个群组的在线终端数
	 * @param sess
	 * @param grpId
	 * @return
	 */
	Integer grpOnLineMsCount(Session sess,String grpId);
	
    /**
     * 跟据群组号查询终端状态信息
     * @param grpid
     * @return
     */
    List findAllMsStatesByGrpid(Session sess,String grpid);
}