package com.elegps.tscweb.serivce;

import java.util.List;



import com.elegps.tscweb.model.TbAgentInfo;
import com.elegps.tscweb.model.TbEnterpriseInfo;
import com.elegps.tscweb.model.TbMsInfo;




public interface EnterPriseManager 
{ 
	/**
	 * �û���λ������ѯ
	 * @param aggent_id  ������id
	 * @param ep_name  �û���λ����
	 * @return
	 */
	int getEp_sertch(String aggent_id,String childagentid,String ep_name,int ep_id,int r_id);
	
	/**
	 * ����ÿҳ��¼�����ܼ�¼����ȡ��ҳ��
	 * @param count �ܼ�¼��
	 * @param pageSize ÿҳ��ʾ�ļ�¼��
	 * @return ����õ�����ҳ��
	 */
	int getPageCount(int count , int pageSize);
	
  /**
   * 
   * @param pageNo
   * @param pageSize
   * @param agent_id
   * @param ep_name
   * @return  �����û���λep_name��ģ����ѯҳ��ȫ���û���λ��Ϣ
   */

	List <TbEnterpriseInfo> getEpinfoby_mspage(int pageNo,
			int pageSize, String agent_id,String childagentid,String ep_name, int ep_id,int r_id);
	
	
//	/**
//	 * ����ָ��������id�µ�һ���������������
//	 * @param agent_id
//	 * @return
//	 */
//	List getAgentinfo_byagentid(String agent_id);
	
	
	/**
	 * �����ض�ָ�������û���ϸ��Ϣ
	 * @param epId ��ҵid
	 */
	TbEnterpriseInfo getEpinfo_byepid(String epId);
	
	/**
	 * �����û���λ��Ϣ
	 * @param ep_name
	 * @param ep_address
	 * @param ep_tel
	 * @param ep_email
	 * @param ep_url
	 * @param ep_man
	 * @param ep_qq
	 * @param agent_id
	 * @param ep_remark
	 * @return
	 */
	String createEp(String ep_name,String ep_address,String ep_tel,String ep_tel1,String ep_email,String ep_url,String ep_man,String ep_man1,String ep_qq,String agent_id,String ep_remark);
	
	
	/**
	 * �޸��û���λ��Ϣ
	 * @param ep_id
	 * @param ep_name
	 * @param ep_address
	 * @param ep_tel
	 * @param ep_email
	 * @param ep_url
	 * @param ep_man
	 * @param ep_qq
	 * @param ep_remark
	 * @return
	 */
	String modifyEp(String agent_id,String ep_id,String ep_name,String ep_address,String ep_tel,String ep_tel1,String ep_email,String ep_url,String ep_man,String ep_man1,String ep_qq,String ep_remark);
	
	
	/**
	 *�����û���λidɾ���û���λ��Ϣ 
	 * @param epidlist
	 * @return
	 */
	Boolean deleteEp(String[] epidlist);
	
    /**
     * ����ָ�������������е��û���λ
     * @param agentid
     * @return
     */
	List <TbEnterpriseInfo> getEpinfo_byagentid(String pagentid,String childagentid,int a_id,int r_id);
	
	
	
	/**
     * ����ָ����������ָ�����û���λ
     * @param epid
     * @return
     */
	List <TbEnterpriseInfo> getEpinfo_byeid(String pagentid,String childagentid,int ep_id,int r_id);
	/**
     * ������ҵ���Ʋ�ѯ��Ϣ
     * @param agentname
     * @return
     */
    TbEnterpriseInfo getEpByName(String epname);
    /**
     * ������ҵID��ѯ����������
     */
    TbAgentInfo getAgent_ByEpName(String epid);
    
    
    /**
     * ָ����ҵ���ID����ѯ��ҵ��Ϣ
     * @param ep_id
     * @return
     */
    List getChargeEpinfo_byepid(String ep_id);
    

    /**
     * ����ָ�������������е��û���λ
     * @param agentid
     * @return
     */
	List <TbEnterpriseInfo> getEpinfo_byagentid(String agent);


	/**
	 * �޸�ep��Ϣ
	 * @param ep
	 * @return
	 */
	Boolean updateEp(TbEnterpriseInfo ep);
	
	/**
	 * �����û���λ��Ϣ
	 * @param ep_name
	 * @param ep_address
	 * @param ep_tel
	 * @param ep_email
	 * @return
	 */
	String createEp(String ep_name,String ep_address,
			String ep_tel,String ep_tel1,String productID,String sIID,String bizID,
			String areaCode,String custID,String custAccount,String summary,
			String agent_id);
}