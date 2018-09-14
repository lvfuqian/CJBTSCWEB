package com.elegps.tscweb.dao;

import java.util.List;

import org.hibernate.Session;



public interface MsChargeDaoFactory
{
	/**
	 * ��ѯ�ն�����¼����
	 * @param sess
	 * @param ms_id
	 * @param ms_name
	 * @param agent
	 * @param ep
	 * @return
	 */
	Integer  getMsChareg_sertchCount(Session sess,String ms_id,String ms_name,String pagentid,String childagentid,String ep);
	
	
	/**
	 * ��ѯָ���������ն������Ϣ
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
	 * ��ѯָ�������̵������Ϣ
	 * @param sess
	 * @param agent_id
	 * @return
	 */
	List getBalanceinfo_byagentid(Session sess,String agent_id);
	
	/**
	 * ��ѯָ����ҵ�������Ϣ
	 * @param sess
	 * @param agent_id
	 * @return
	 */
	List getBalanceInfo_byepid(Session sess,String ep_id);
	
	/**
	 * ��ѯָ�������̵������Ϣ
	 * @param sess
	 * @param agent_id
	 * @return
	 */
	Integer getAgentBalancedao(Session sess,String agent_id);
	
	
	/**
	 *��ѯָ����ҵ�������Ϣ
	 * @param sess
	 * @param agent_id
	 * @return
	 */
	Integer getEpBalancedao(Session sess,String ep_id);
	
	String getMsCharge_byagentiddao(Session sess,String changeagentid,String ep_id,String charge,String[] ms_id);
	
	String getMsCharge_byepiddao(Session sess,String changeepid,String ep_id,String charge,String[] ms_id);
	
	/**
     * ��ҵ��ֵ��ʷ��¼��
     * @param sess
     * @param agent
     * @param ep
     * @param advanceresult
     * @return
     */
    Integer getEpCharge_Count(Session sess,String agent,String ep);
    
    
    /**
     * ��ҵ��ֵ��ʷ
     * @param sess
     * @param pageNo
     * @param pageSize
     * @param agent
     * @param ep
     * @return
     */
    List getEpCharge_SearchByPage(Session sess,int pageNo,int pageSize,String agent,String ep);
    
    
    /**
     * �ն˳�ֵ��ʷ��¼��
     * @param sess
     * @param agent
     * @param ep
     * @param ms_id
     * @param ms_name     
     * @return
     */
    Integer getMsCharge_Count(Session sess,String agent,String ep,String ms_id,String ms_name);
    
    
    /**
     * �ն˳�ֵ��ʷ
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
     * �ն˿�����ʷ��¼��
     * @param sess
     * @param agent
     * @param ep
     * @param ms_id
     * @param ms_name
     * @return
     */
    Integer getMsDeduct_Count(Session sess,String agent,String ep,String ms_id,String ms_name);
    
    
    /**
     * �ն˿�����ʷ��¼��Ϣ
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