package com.elegps.tscweb.dao;

import java.util.List;

import org.hibernate.Session;



public interface MsChargeDaoFactory
{
	/**
	 * 查询终端余额记录个数
	 * @param sess
	 * @param ms_id
	 * @param ms_name
	 * @param agent
	 * @param ep
	 * @return
	 */
	Integer  getMsChareg_sertchCount(Session sess,String ms_id,String ms_name,String pagentid,String childagentid,String ep);
	
	
	/**
	 * 查询指定条件的终端余额信息
	 * @param sess
	 * @param pageNo
	 * @param pageSize
	 * @param ms_id
	 * @param ms_name
	 * @param agent
	 * @param ep
	 * @return
	 */
	List  getdMsCharge_sertchByPage(Session sess, int pageNo, int pageSize,String ms_id,String ms_name,String pagentid,String childagentid,String ep);
	
	/**
	 * 查询指定代理商的余额信息
	 * @param sess
	 * @param agent_id
	 * @return
	 */
	List getBalanceinfo_byagentid(Session sess,String agent_id);
	
	/**
	 * 查询指定企业的余额信息
	 * @param sess
	 * @param agent_id
	 * @return
	 */
	List getBalanceInfo_byepid(Session sess,String ep_id);
	
	/**
	 * 查询指定代理商的余额信息
	 * @param sess
	 * @param agent_id
	 * @return
	 */
	Integer getAgentBalancedao(Session sess,String agent_id);
	
	
	/**
	 *查询指定企业的余额信息
	 * @param sess
	 * @param agent_id
	 * @return
	 */
	Integer getEpBalancedao(Session sess,String ep_id);
	
	String getMsCharge_byagentiddao(Session sess,String changeagentid,String ep_id,String charge,String[] ms_id);
	
	String getMsCharge_byepiddao(Session sess,String changeepid,String ep_id,String charge,String[] ms_id);
	
	/**
     * 企业充值历史记录数
     * @param sess
     * @param agent
     * @param ep
     * @param advanceresult
     * @return
     */
    Integer getEpCharge_Count(Session sess,String agent,String ep);
    
    
    /**
     * 企业充值历史
     * @param sess
     * @param pageNo
     * @param pageSize
     * @param agent
     * @param ep
     * @return
     */
    List getEpCharge_SearchByPage(Session sess,int pageNo,int pageSize,String agent,String ep);
    
    
    /**
     * 终端充值历史记录数
     * @param sess
     * @param agent
     * @param ep
     * @param ms_id
     * @param ms_name     
     * @return
     */
    Integer getMsCharge_Count(Session sess,String agent,String ep,String ms_id,String ms_name);
    
    
    /**
     * 终端充值历史
     * @param sess
     * @param pageNo
     * @param pageSize
     * @param agent
     * @param ep
     * @param ms_id
     * @param ms_name  
     * @return
     */
    List getMsCharge_SearchByPage(Session sess,int pageNo,int pageSize,String agent,String ep,String ms_id,String ms_name);
    
    
    /**
     * 终端扣租历史记录数
     * @param sess
     * @param agent
     * @param ep
     * @param ms_id
     * @param ms_name
     * @return
     */
    Integer getMsDeduct_Count(Session sess,String agent,String ep,String ms_id,String ms_name);
    
    
    /**
     * 终端扣租历史记录信息
     * @param sess
     * @param pageNo
     * @param pageSize
     * @param agent
     * @param ep
     * @param ms_id
     * @param ms_name
     * @return
     */
    List getMsDeduct_SearchByPage(Session sess,int pageNo,int pageSize,String agent,String ep,String ms_id,String ms_name);

}