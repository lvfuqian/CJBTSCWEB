package com.elegps.tscweb.serivce;

import java.util.List;

import com.elegps.tscweb.model.TbMsInfo;


public interface MsChargeManager 
{ 

	int getMsCharge_sertch(String ms_id,String ms_name,String pagentid,String childagentid,String ep);
	
	
	int getPageCount(int count , int pageSize);
	
	
	List <TbMsInfo> getMsChargeinfo_bypage(int pageNo,int pagesize,String ms_id,String ms_name,String pagentid,String childagentid,String ep);
	
	
	/**
	 * 返回指定代理商中的信息(充过值）
	 * @param agent_id
	 * @return
	 */
	List getBalanceinfo_byagentid(String agent_id);
	 
	
	/**
	 * 返回指定企为中的信息(充过值）
	 * @param ep
	 * @return
	 */
	List getBalancefo_byeptid(String ep);
	
	
	/**
	 * 返回指定代理商的余额
	 * @param agent_id
	 * @return
	 */
	int getAgentBalance(String agent_id);
	
	
	/**
	 * 返回指定企业的余额
	 * @param ep_id
	 * @return
	 */
	int getEpBalance(String ep_id);
	
	String getMsCharge_byagentid(String changeagentid, String ep_id,String charge,String[] ms_id);
	
	String getMsCharge_byepid(String changeepid, String ep_id,String charge,String[] ms_id);
	
	
	/**
	  * 查询企业充值历史记录信息总数
	  * @param agent
	  * @param ep
	  * @return
	  */
	 int getEpCharge_SearchCount(String agent,String ep);
	 
	 /**
	  * 查询企业充值历史记录信息
	  * @param pageNo
	  * @param pageSize
	  * @param agent
	  * @param ep
	  * @return
	  */
	 List getEpCharge_SearchByPage(int pageNo,int pageSize,String agent,String ep);
	 
	 
	 /**
	  * 查询终端充值历史记录信息总数
	  * @param agent
	  * @param ep
	  * @param ms_id
	  * @param ms_name
	  * @return
	  */
	 int getMsCharge_SearchCount(String agent,String ep,String ms_id,String ms_name);
	 
	 
	 /**
	  * 查询终端充值历史记录信息
	  * @param pageNo
	  * @param pageSize
	  * @param agent
	  * @param ep
	  * @param ms_id
	  * @param ms_name
	  * @return
	  */
	 List getMsCharge_SearchByPage(int pageNo,int pageSize,String agent,String ep,String ms_id,String ms_name);
	 
	 
	 /**
	  * 查询终端扣租历史记录信息总数
	  * @param agent
	  * @param ep
	  * @param ms_id
	  * @param ms_name
	  * @return
	  */
	 int getMsDeduct_SearchCount(String agent,String ep,String ms_id,String ms_name);
	 
	 
	 /**
	  * 查询终端扣租历史记录信息
	  * @param pageNo
	  * @param pageSize
	  * @param agent
	  * @param ep
	  * @param ms_id
	  * @param ms_name
	  * @return
	  */
	 List getMsDeduct_SearchByPage(int pageNo,int pageSize,String agent,String ep,String ms_id,String ms_name);
	 
	
}