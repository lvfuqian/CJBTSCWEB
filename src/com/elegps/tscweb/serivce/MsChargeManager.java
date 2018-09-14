package com.elegps.tscweb.serivce;

import java.util.List;

import com.elegps.tscweb.model.TbMsInfo;


public interface MsChargeManager 
{ 

	int getMsCharge_sertch(String ms_id,String ms_name,String pagentid,String childagentid,String ep);
	
	
	int getPageCount(int count , int pageSize);
	
	
	List <TbMsInfo> getMsChargeinfo_bypage(int pageNo,int pagesize,String ms_id,String ms_name,String pagentid,String childagentid,String ep);
	
	
	/**
	 * ����ָ���������е���Ϣ(���ֵ��
	 * @param agent_id
	 * @return
	 */
	List getBalanceinfo_byagentid(String agent_id);
	 
	
	/**
	 * ����ָ����Ϊ�е���Ϣ(���ֵ��
	 * @param ep
	 * @return
	 */
	List getBalancefo_byeptid(String ep);
	
	
	/**
	 * ����ָ�������̵����
	 * @param agent_id
	 * @return
	 */
	int getAgentBalance(String agent_id);
	
	
	/**
	 * ����ָ����ҵ�����
	 * @param ep_id
	 * @return
	 */
	int getEpBalance(String ep_id);
	
	String getMsCharge_byagentid(String changeagentid, String ep_id,String charge,String[] ms_id);
	
	String getMsCharge_byepid(String changeepid, String ep_id,String charge,String[] ms_id);
	
	
	/**
	  * ��ѯ��ҵ��ֵ��ʷ��¼��Ϣ����
	  * @param agent
	  * @param ep
	  * @return
	  */
	 int getEpCharge_SearchCount(String agent,String ep);
	 
	 /**
	  * ��ѯ��ҵ��ֵ��ʷ��¼��Ϣ
	  * @param pageNo
	  * @param pageSize
	  * @param agent
	  * @param ep
	  * @return
	  */
	 List getEpCharge_SearchByPage(int pageNo,int pageSize,String agent,String ep);
	 
	 
	 /**
	  * ��ѯ�ն˳�ֵ��ʷ��¼��Ϣ����
	  * @param agent
	  * @param ep
	  * @param ms_id
	  * @param ms_name
	  * @return
	  */
	 int getMsCharge_SearchCount(String agent,String ep,String ms_id,String ms_name);
	 
	 
	 /**
	  * ��ѯ�ն˳�ֵ��ʷ��¼��Ϣ
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
	  * ��ѯ�ն˿�����ʷ��¼��Ϣ����
	  * @param agent
	  * @param ep
	  * @param ms_id
	  * @param ms_name
	  * @return
	  */
	 int getMsDeduct_SearchCount(String agent,String ep,String ms_id,String ms_name);
	 
	 
	 /**
	  * ��ѯ�ն˿�����ʷ��¼��Ϣ
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