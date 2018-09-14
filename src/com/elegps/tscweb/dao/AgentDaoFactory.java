package com.elegps.tscweb.dao;

import org.hibernate.Session;

import com.elegps.tscweb.model.TbAgentInfo;
import com.elegps.tscweb.model.TbGrpInfo;
import com.elegps.tscweb.model.TbMsInfo;

import java.util.List;

public interface AgentDaoFactory
{
	/**
	 * ���ݲ�ѯ����ȡ�ü�¼����
	 * 
	 * @param agent_name
	 * @return ��ѯ����ȡ�ü�¼����
	 */
	Integer getAgent_SearchCount(Session sess, String agent_name,String agent_type);
	
	/**
	 * ���ݲ�ѯ����ȡ�ü�¼����
	 * 
	 * @param agent_name
	 * @return ��ѯ����ȡ�ü�¼����
	 */
	Integer getAgent_SearchCount(Session sess, String agent_name,String agent_type,int pid);
	
	
	/**
	 * ����ָ��ҳ����ѯ����ȡ�ü�¼��Ϣ
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @param agent_name
	 * @return ָ��ҳ����ѯ����ȡ�ü�¼��Ϣ
	 */
	List getTbAgentInfo_ByPage(Session sess, int pageNo, int pageSize,String agent_name,
			String agent_type);
	/**
	 * ����ָ��ҳ����ѯ����ȡ�ü�¼��Ϣ
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @param agent_name
	 * @return ָ��ҳ����ѯ����ȡ�ü�¼��Ϣ
	 */
	List getTbAgentInfo_ByPage(Session sess, int pageNo, int pageSize,String agent_name,
			String agent_type,int pid);
	
	/**
	 * �������������ն˴�������Ϣ
	 * @param sess �־û���������Ҫ��Hiberate Session
	 * @param agentId ��Ҫ���صĴ�������Ϣ
	 * @return ���صĴ�������Ϣ
	 */
	TbAgentInfo get(Session sess , String agentId);  //���ﲻ������ֻ��Ϊint,long����Ϊ�ַ���
	/**
	 * ��Ӵ�����  zr
	 * @param sess
	 * @param agentinfo
	 */
	String save(Session sess,TbAgentInfo agentinfo);
	/**
	 * �޸Ĵ����� zr
	 * @param sess
	 * @param agentinfo
	 */
	void update(Session sess,TbAgentInfo agentinfo);
	/**
	 * ��ѯ����һ��������(�����̸���IDΪ0��) zr
	 * @param sess
	 * @return
	 */
	List getAgentByagentpid(Session sess,int agent_id,int r_id);
	
	List getAgentNotIncludeZBByagentpid(Session sess);
	
	/**
	 * ��ѯ���д����� zr
	 * @param sess
	 * @return
	 */
	List getAllAgent(Session sess);
	
	/**
	 * ���ݴ�����IDɾ����������Ϣ
	 */
    void delete(Session sess,String id);
    /**
     * ���ݴ��������Ƽ�����Ϣ
     * @param sess
     * @param agent_name
     * @return
     */
    TbAgentInfo getagent_byname(Session sess, String agentid,String agent_name);
    /**
     * ����ID��ѯ���������̵�����
     */
    TbAgentInfo getName_byid(Session sess,String id);
    /**
     * ���ݴ��������Ʋ�ѯ��������Ϣ zr
     * @param sess
     * @param agent_name
     * @return
     */
    TbAgentInfo getAgent_Byagentname(Session sess, String agent_name);


    /**��DWR���ã���ѯָ��һ���������µĶ���������
     * ��id
     * @param sess
     * @param paramentid
     * @return
     */
	List getChildAgentByParamentid(Session sess, String paramentid);
	
	 /**
     * �����������û�ʱ������������������ʾ��Ϣ
     * @param agent
     * @return
     */
    List getChildAgentByAId(Session sess,String agent);

	 /**
     *���ݴ�����id����ѯ����������(һ�������̣����������̣� 
     * @param agent_Id
     * @return
     */
	int getAgentType(Session sess, String agent_Id);

}