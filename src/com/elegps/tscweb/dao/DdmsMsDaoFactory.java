package com.elegps.tscweb.dao;

import org.hibernate.Session;

import com.elegps.tscweb.model.TbDdmsMsListInfo;
import com.elegps.tscweb.model.TbGpsMsListInfo;
import com.elegps.tscweb.model.TbGrpInfo;
import com.elegps.tscweb.model.TbGrpMsListInfo;
import com.elegps.tscweb.model.TbMsInfo;

import java.util.List;

public interface DdmsMsDaoFactory
{
	
	 /**
	 * 根据查询条件取得记录总数
	 * @param ddmsid
	 * @param msid
	 * @return  查询条件取得记录总数
	 */
    Integer findDdmsMs_SearchCount(Session sess,String ddmsid,String msid,String agent,String ep);	
    
    /**
     * 根据ddmsms表中所有信息记录列表(调度用户)
	 */
	List findDdms_Info(Session sess);
	
	
	 /**
     * 根据ddmsms表中所有信息记录列表(非调度用户)
	 */
	List findMs_Info(Session sess);
	
	
	
	/**
	    * 根据指定页及查询条件取得记录信息
	    * @param pageNo
	    * @param pageSize
	    * @param ddmsid
	    * @param msid
	    * @return  指定页及查询条件取得记录信息
	    */
     List findDdmsMs_SearchByPage(Session sess,int pageNo,int pageSize,String ddmsid,String msid,String agent,String ep);
     
     /**
      * 创建
      * @param sess
      * @param ddms_id
      * @param ms_id
      * @return
      */
     TbDdmsMsListInfo get(Session sess , String ddms_id,String ms_id); 
     
     /**
      * 保存
      * @param sess
      * @param ddmsms
      */
     String save(Session sess , TbDdmsMsListInfo ddmsms);
     
     
     /**
      * 删除
      * @param sess
     * @param tbddmsmsinfo 
      * @param 
      */
     void delete(Session sess, TbDdmsMsListInfo tbddmsmsinfo);
     
     
 	/**
 	 * 取得(ms)表中调度用户信息
 	 * @param sess
 	 * @return
 	 */
 	List findinDdms_Info(Session sess);
 	
	/**
 	 * 取得(ms)表中非调度用户信息
 	 * @param sess
 	 * @return
 	 */
 	List findnotDdms_Info (Session sess);
 	
 	
	/**根据ms_id删除记录
	 * @param ms_id 终端用户ID
	 */
	void deletems_id(Session sess,String ms_id);
	/**
	 *  根据调度用户ID查询已经存在的非调度用户 zr
	 * @param sess
	 * @param ddmsid
	 * @return
	 */
	List findAllms_bymsid(Session sess, String ddmsid);
	/**
	 * 根据调度用户ID查询其下没有的非调度用户 zr
	 * @param sess
	 * @param ddmsid
	 * @return
	 */
	List findms_notinddms(Session sess, String ddmsid);
	/**
	 *  删除调度用户ID下，本来存在的终端用户 zr
	 */
	void deleteexistms(Session sess, String ddms_id);
	
}