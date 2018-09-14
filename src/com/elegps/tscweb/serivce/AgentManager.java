package com.elegps.tscweb.serivce;

import java.util.List;

import org.hibernate.Session;

import com.elegps.tscweb.model.TbAgentInfo;




public interface AgentManager 
{ 
	/**
	 * ���ݲ�ѯ��������������ȡ�ü�¼����
	 * @param agent_name
	 * @return ��ѯ����ȡ�ü�¼����
	 */
	int getAgent_SearchCount(String agent_name,String agent_one);
	
	/**
	 * ���ݲ�ѯ��������������ȡ�ü�¼����
	 * @param agent_name
	 * @return ��ѯ����ȡ�ü�¼����
	 */
	int getAgent_SearchCount(String agent_name,String agent_one,int pid);
	
	/**
	 * ����ÿҳ��¼�����ܼ�¼����ȡ��ҳ��
	 * 
	 * @param count
	 *            �ܼ�¼��
	 * @param pageSize
	 *            ÿҳ��ʾ�ļ�¼��
	 * @return ����õ�����ҳ��
	 */
	int getPageCount(int count, int pageSize);
	
	
	/**
	 * ����ָ��ҳ����ѯ����ȡ�ü�¼��Ϣ	 * 
	 * @param agent_name
	 * @return ָ��ҳ����ѯ����ȡ�ü�¼��Ϣ
	 */
	List getTbAgentInfoby_name(int pageNo, int pageSize,String agent_name ,
			String agent_type);
	
	/**
	 * ����ָ��ҳ����ѯ����ȡ�ü�¼��Ϣ	 * 
	 * @param agent_name
	 * @return ָ��ҳ����ѯ����ȡ�ü�¼��Ϣ
	 */
	List getTbAgentInfoby_name(int pageNo, int pageSize,String agent_name ,
			String agent_type,int pid);
	/**
	 * �����ض�ָ��������������ϸ��Ϣ
	 * @param agentId ������id
	 */
	TbAgentInfo getTbAgentinfo_byagentid(String agentId);
	/**
	 * ���һ����������Ϣ   zr
	 * @param agentid
	 * @param agentname
	 * @param agentaddress
	 * @param agenttel
	 * @param agentemail
	 * @param agenturl
	 * @param agentman
	 * @param agentqq
	 * @param agentpid
	 * @param agentremark
	 */
	public String saveagent(String agentname,
			String agentaddress, String agenttel,String agenttel1,String agentemail,
			String agenturl, String agentman,String agentman1, String agentqq, String agentpid,
			String agentremark);
	/**
	 * �޸Ĵ�������Ϣ,�����޸���������һ�������̵Ĺ�ϵ zr
	 * @param agentaddress
	 * @param agenttel
	 * @param agentemail
	 * @param agenturl
	 * @param agentman
	 * @param agentqq
	 * @param agentremark
	 */
	public String updateagent(String agentid,String agentname,String agentaddress, String agenttel,String agenttel1, String agentemail,
			String agenturl, String agentman,String agentman1,String agentqq,String agentremark);
	
	/**
	 * �޸Ĵ�������Ϣ
	 * @param sess
	 * @param aInfo
	 * @return id
	 */
	Boolean updateAgent(TbAgentInfo aInfo);
	
	/**
	 * ��ѯ���е�һ��������(�����̸�IDΪ0��) zr
	 * @return 
	 */
    public List getParentAgent(int agent_id,int r_id);
    
    /**
     * ��ȡ�����ܲ�����Ĵ�����
     */
    public List getParentAgentNotIncludeZB();
    
    /**
     * ���ݴ�����ID��ѯ��������Ϣ  zr
     * @return
     */
    public TbAgentInfo getAgent_ByAgentID(String agentid);
    /**
     * ��ѯ���д����� zr
     */
    List getAll_Agent();
    /**
     * ��������Ϣɾ�� zr
     */
    boolean deleteagentById(String[] agentid);
    
    /**
     * ����ID��ô���������
     */
    public TbAgentInfo getAgentName(String id);
    
    /**
     * ���ݴ��������Ʋ�ѯ��������Ϣzr
     * @param agentname
     * @return
     */
    public TbAgentInfo getAgentByName(String agentname);

    
    /**��DWR���ã���ѯָ��һ���������µĶ���������
     * ��id
     * @param paramentid
     * @return
     */
    public  List getChildAgentByParamentid(String paramentid);
    
    /**
     * �����������û�ʱ������������������ʾ��Ϣ
     * @param agent
     * @return
     */
    public List getChildAgentByAId(String agent);

    /**
     *���ݴ�����id����ѯ����������(һ�������̣����������̣� 
     * @param agent_Id
     * @return
     */
	int getAgenttype(String agent_Id);
}