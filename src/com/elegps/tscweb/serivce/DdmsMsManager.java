package com.elegps.tscweb.serivce;

import java.util.List;

import com.elegps.tscweb.exception.MessageException;
import com.elegps.tscweb.model.TbDdmsMsListInfo;
import com.elegps.tscweb.model.TbGpsMsListInfo;
import com.elegps.tscweb.model.TbGrpInfo;
import com.elegps.tscweb.model.TbGrpMsListInfo;
import com.elegps.tscweb.model.TbMsInfo;




public interface DdmsMsManager 
{ 
	/**
	 * 根据查询条件取得记录总数
	 * @param ddmsid
	 * @param msid
	 * @return  查询条件取得记录总数
	 */
   int getDdmsMs_SearchCount(String ddmssid,String msid,String agent,String ep);
   
   /**
	 * 根据每页记录数，总记录数获取总页数
	 * @param count 总记录数
	 * @param pageSize 每页显示的记录数
	 * @return 计算得到的总页数
	 */
	int getPageCount(int count , int pageSize);
	
	
	/**根据TbDdmsMsListInfo表中记录列表的调度用户所有信息
	 */
	List <String> getAllDdms_Info();
	
	
	/**根据TbDdmsMsListInfo表中记录列表的非调度用户所有信息
	 */
	List <TbDdmsMsListInfo> getAllMs_Info();
	
	
	 /**
	    * 根据指定页及查询条件取得记录信息
	    * @param pageNo
	    * @param pageSize
	    * @param ddmsid
	    * @param msid
	    * @return  指定页及查询条件取得记录信息
	    */
	   List <TbDdmsMsListInfo> getDdmsMs_SearchByPage(int pageNo,int pageSize,String ddmsid,String msid,String agent,String ep);
	   
	   
	   
	   /**
		 * 创建一条调度对应关系信息
		 * @param ddms_id 
		 * @param ms_id 
		 * @return 新创终端的主键,如果创建失败，返回null。
		 */  
		String createDdmsMsInfo(String ddms_id , String ms_id);
		
		
		/**根据ddms_id,ms_id 删除记录
		 * @param gpsmsidlist
		 */
		Boolean deleteDdmsMs(String[] ddmsmsidlist);
		
		
		/**
		 * ms表中调度用户
		 * @return
		 */
		List <TbMsInfo> getAllinDdms_Info();
		
		
		/**
		 * ms表中非调度用户
		 * @return
		 */
		List <TbMsInfo> getAllnotDdms_Info();
		/**
		 * 调度管理批量添加
		 * @return
		 * 
		 */
		 String createDdMsInfo_Byddmsid(String ddmsid,String[] msid);
		 /**
		  * 获得ddms_id下非调度用户  zr
		  */
		 List getallms(String ddms_id);
		 /**
		  * 获得调度用户下的没有的非调度用户  zr
		  */
		 List getallms_notbyddmsid(String ddms_id);
}