package com.elegps.tscweb.dao;

import java.util.List;

import org.hibernate.Session;

import com.elegps.tscweb.model.TbAgentInfo;
import com.elegps.tscweb.model.TbEnterpriseInfo;
import com.elegps.tscweb.model.TbMsInfo;


public interface EnterPriseDaoFactory
{
	/**
	 * �û���λ������ѯ
	 * @param aggent_id  ������id
	 * @param ep_name  �û���λ����
	 * @return
	 */
	Integer  findEp_sertchCount(Session sess,String aggent_id,String childagentid,String ep_name, int ep_id,int r_id);
	
	/**
	 * ��ѯ�û���Ϣ
	 * @param sess
	 * @param pageNo
	 * @param pageSize
	 * @param agent_id
	 * @param ep_name
	 * @return
	 */
	List getEpInfo(Session sess,int pageNo,int pageSize,String agent_id,String childagentid,String ep_name, int ep_id,int r_id);
	
	
//	/**
//	 * ����ָ��������id�����е�һ������Ͷ�������
//	 * @param sess
//	 * @param agent_id
//	 * @return
//	 */
//	List getAgentInfo(Session sess,String agent_id);
	
	
	/**
	 * �������������û���λ��Ϣ
	 * @param sess �־û���������Ҫ��Hiberate Session
	 * @param epId ��Ҫ���ص��û���λ��Ϣ
	 * @return ���ص��û���λ��Ϣ
	 */
	TbEnterpriseInfo get(Session sess , String epId);  //���ﲻ������ֻ��Ϊint,long����Ϊ�ַ���
	
	/**
	 * �����û���Ϣ
	 * @param sess
	 * @param m
	 * @return
	 */
    String save(Session sess , TbEnterpriseInfo m);
    
    
    /**
     * ����ָ��������id�������û���λ
     * @param sess
     * @param agentid
     * @return
     */
    List getEpInfo_byagentid(Session sess , String pagentid,String childagentid,int a_id,int r_id);
    
    /**
     * ����ָ��������id��ָ���û���λ
     * @param sess
     * @param epid
     * @return
     */
    List getEpinfo_byeid(Session sess , String pagentid,String childagentid,int ep_id,int r_id);
    
    /**
     * �����û���λidɾ���û���λ��Ϣ
     * @param sess
     * @param epid
     */
    void delete(Session sess,String epid);
    /**
     * ������ҵ���Ʋ�ѯ
     * @param sess
     * @param ep_name
     * @return
     */
    TbEnterpriseInfo getEp_Byepname(Session sess, String ep_name);
    
    
    /**
     * ������ҵ���Ʋ�ѯ
     * @param sess
     * @param ep_name
     * @return
     */
    TbEnterpriseInfo getEp_Byepname(Session sess,String ep_id, String ep_name);
    /**
     * �����û�ID��ѯ����������
     * @param sess
     * @param epid
     * @return
     */
    TbAgentInfo getAgent_ByEpid(Session sess,String epid);
    
    
    /**
     * ������ҵ����е���ҵ�Ų�ѯ��ҵ��Ϣ
     * @param sess
     * @param epid
     * @return
     */
    List getEpInfo(Session sess,String epid);
    
    List getEpInfo_byagentid(Session sess , String agent);
    
    /**
     * �޸���Ϣ
     * @param sess
     * @param ep
     */
    void updateInfo(Session sess,TbEnterpriseInfo ep);
}
    