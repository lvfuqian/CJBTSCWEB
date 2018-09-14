package com.elegps.tscweb.dao;

import org.hibernate.Session;

import com.elegps.tscweb.model.TbMsInfo;
import com.elegps.tscweb.model.TbMsInfoExt;

import java.io.Serializable;
import java.util.List;

public interface MsDaoFactory
{
	/**
	 * �������������ն��û���Ϣ
	 * @param sess �־û���������Ҫ��Hiberate Session
	 * @param MS_Id ��Ҫ���ص��ն��û���Ϣ
	 * @return ���ص��ն��û���Ϣ
	 */
	TbMsInfo get(Session sess , String MS_Id);  //���ﲻ������ֻ��Ϊint,long����Ϊ�ַ���

	/**
	 * �����ն��û���Ϣ
	 * @param sess �־û���������Ҫ��Hiberate Session
	 * @param m ��Ҫ������ն��û���Ϣ
	 * @retun �ն��û���Ϣ������
	 */
    String save(Session sess , TbMsInfo m);
    
    /**
     * ��������
     * @param session �־û���������Ҫ��Hibernate Session
     * @param obj ��Ҫ����Ķ�����Ϣ
     * @author ACER
     * @date 2011-03-14
     */
    public void create(Session session,Object obj);
    /**
     * �޸Ķ���
     * @param session �־û���������Ҫ��Hibernate Session
     * @param hql
     * @param obj
     * @author ACER
     * @date 2011-03-14
     */
    public int update(Session session,String hql,Object...obj);
    
    /**
     * ����ID��ȡ��Ӧ������
     * @param session
     * @param hql
     * @param obj
     * @author ACER
     * @date 2011-03-14
     */
    public  Object getExtById(Session session,String hql,Object...obj);
    /**
     * ���������Ƿ����
     * @param session
     * @param hql
     * @param obj
     * @return
     */
     public int getMsById(Session session ,String hql,Object...obj);
    
    
	/**
	 * ɾ���ն��û���Ϣ
	 * @param sess �־û���������Ҫ��Hiberate Session
	 * @param m ��Ҫɾ�����ն��û���Ϣ
	 */
    void delete(Session sess , TbMsInfo m);

	/**
	 * ɾ���ն��û���Ϣ
	 * @param sess �־û���������Ҫ��Hiberate Session
	 * @param id ��Ҫɾ�����ն��û���ϢMS_Id
	 */
    void delete(Session sess , String MS_Id);

	/**
	 * �޸��ն��û���Ϣ
	 * @param sess �־û���������Ҫ��Hiberate Session
	 * @param m ��Ҫ�޸ĵ��ն��û���Ϣ
	 */
    void update(Session sess , TbMsInfo m);
    
	/**
	 * ��ѯָ���û�״̬Ϊ1��ָ��ҳ���ն��û���Ϣ
	 * @param sess �־û���������Ҫ��Hiberate Session
	 * @param pageNo ��Ҫ��ѯ��ָ��ҳ
	 * @param  pageSize  ҳ�ĸ���
	 * @return ��ѯ�����ն��û���Ϣ����  
	 */
    List findAllMs_typeByPage(Session sess , int pageNo , int pageSize);
    
    
    /**
	 * ��ѯָ���û���ָ��ҳ���ն��û���Ϣ
	 * @param sess �־û���������Ҫ��Hiberate Session
	 * @param pageNo ��Ҫ��ѯ��ָ��ҳ
	 * @param pageSize ��Ҫ��ѯ��ָ��ҳ����
	 * @param ms_type ��Ҫ��ѯ��ָ���ն��û�����
	 * @return ��ѯ�����ն��û���Ϣ����  findMs_typeByPage
	 */
    List findMs_typeByPage(Session sess , int pageNo , int pageSize,int ms_type);

	
	/**
	 * ��ѯ�ն��û�������Ϣ������
	 * @param sess �־û���������Ҫ��Hiberate Session
	 * @param ms_type �ն��û�����
	 * @return �ն��û�������Ϣ����
	 */
	Integer findMs_typeCount(Session sess,int ms_type);
	
	
	/**
	 * ��ѯ�ն��û�������Ϣ������
	 * @param sess �־û���������Ҫ��Hiberate Session
	 * @param ms_type �ն��û�����
	 * @return �����ն��û�������Ϣ����
	 */
	Integer findAllMs_typeCount(Session sess);
	
	/**
	 * �����ض�ָ�������ն��û���Ϣ
	 * @param msId �ն˺���
	 * @param msType �ն���������
	 * @param Flag  �ն��û���״̬
	 * @return ָ��ҳ��ȫ���ն��û���Ϣ
	 */
	List getTbMsInfo(Session sess,String msId,int msType,int Flag);
	
	
	/**
	 * ��ѯ�ն��û�����״̬��Ϣ������
	 * @param sess �־û���������Ҫ��Hiberate Session
	 * @param ms_online �ն��û�����״̬
	 * @return �ն��û�����״̬��Ϣ����
	 */
	Integer findMs_OnlineCount(Session sess,int ms_online);
	
	
	/**
	 * ��ѯ�����ն��û�״̬��Ϣ������
	 * @param sess �־û���������Ҫ��Hiberate Session
	 * @param ms_online �ն��û�״̬
	 * @return �����ն��û�״̬��Ϣ����
	 */
	Integer findMs_flagAllCount(Session sess);
	
	
	/**
	 * ��ѯ�����ն��û�����״̬��Ϣ������
	 * @param sess �־û���������Ҫ��Hiberate Session
	 * @param ms_online �ն��û�����״̬
	 * @return �����ն��û�����״̬��Ϣ����
	 */
	Integer  findAllMs_OnlineCount(Session sess);
	
	
	/**
	 * ����ms_id��ģ����ѯ
	 * @return �ն��û�ms_id��ģ����ѯ������
	 */
	Integer  findMs_idAllCount(Session sess,String ms_id);
	
	
	/**
	 * ��ȡ�ն��û�״̬��Ϣ����
	 * @param flag �ն��û�״̬��Ӧ�ܼ�¼��
	 * @return �ն��û�״̬Ϊflag��Ϣ������ 
	 */
	Integer findMs_flagAllCount(Session sess,int flag);
	
	
	/**
	 * ��ѯָ���û�����״̬Ϊ1��ָ��ҳ���ն��û���Ϣ
	 * @param sess �־û���������Ҫ��Hiberate Session
	 * @param pageNo ��Ҫ��ѯ��ָ��ҳ
	 * @param  pageSize  ҳ�ĸ���
	 * @return ��ѯ�����ն��û�����״̬��Ϣ���� ������״̬����
	 */
	List findAllOnlineByPage(Session sess , int pageNo , int pageSize);
	
	
	/**
	 * ����ms_id��ģ����ѯ
	 * @return �ն��û�ms_id��ģ����ѯҳ��ȫ���ն��û���Ϣ
	 */
	List findMs_idlistByPage(Session sess , int pageNo ,int pageSize,String ms_id);
	
	/**
	 * ��ѯָ���û�״̬Ϊ1��ָ��ҳ���ն��û���Ϣ
	 * @param sess �־û���������Ҫ��Hiberate Session
	 * @param pageNo ��Ҫ��ѯ��ָ��ҳ
	 * @param  pageSize  ҳ�ĸ���
	 * @param   onlineStatus �ն�����״̬
	 * @return ��ѯ�����ն��û���Ϣ���� ������״̬����
	 */
	List findMs_OnlineByPage(Session sess , int pageNo , int pageSize, int onlineStatus);
	
	
	/**
	 * ��ѯ�����ն��û�״̬��Ϣ
	 * @param sess �־û���������Ҫ��Hiberate Session
	 * @param ms_online �ն��û�����״̬
	 * @return �����ն��û�����״̬��Ϣ
	 */
	List findMs_flaglistByPage(Session sess , int pageNo , int pageSize);
	
	/**
	 * ��ѯָ���ն��û�״̬Ϊflag��Ϣ
	 * @param sess �־û���������Ҫ��Hiberate Session
	 * @param ms_online �ն��û�����״̬
	 * @return ָ���ն��û�״̬Ϊflag��Ϣ
	 */
	List findMs_flaglistByPage(Session sess , int pageNo , int pageSize,int falg);
	
	/**����ms����������Ϣ(falg=1)��¼�б�
	 */
	List findMs_Info(Session sess,String pagentid,String childagentid,String ep);
	
	
	/**
	 * ����Ⱥ�����ն��û���ϵ�������ʱ(ȡ�ø�grpidû����ӵ��ն���Ϣ
	 * @param sess
	 * @param grpid
	 * @return
	 */
	List findMs_Info_bygrpid(Session sess,String grpid,String ep);
	
	List findMs_Falg_bygrpid(Session sess,String grpid,String ep);
	
	/**
	 * �����ն��ײ�ת��ʱ(ȡ��ûǷ�ѵ��նˣ�
	 * @param sess
	 * @param grpid
	 * @return
	 */
	List find_notQF_Ms_Info_bygrpid(Session sess,String grpid,String ep,String dateNow);
	
	/**
	 * ����GPS���ն��û���ϵ�������ʱ(ȡ�ø�gpsidû����ӵ��ն���Ϣ
	 * @param sess
	 * @param gpsid
	 * @return
	 */
	List findMs_Info_bygpsid(Session sess,String ep,String gpsid);

	/**�ն˱��ѯ����
	 * @param user_type  �ն�����
	 * @param statue  ����(����)״̬
	 * @param flag  ��Ч״̬
	 * @param ms_id  �ն˺���
	 * @return �ն��û�ģ����ѯ������
	 * sess,user_type,statue,flag, ms_id
	 */
	Integer  findMs_sertchCount(Session sess,String user_type,String statue,String flag,String ms_id,String ms_name,String pagentid,String childagentid,String ep,String ismobile,String arrearage);
	

	/**�ն˱��ѯ����
	 * @param user_type  �ն�����
	 * @param statue  ����(����)״̬
	 * @param flag  ��Ч״̬
	 * @param ms_id  �ն˺���
	 * @return �ն��û�ģ����ѯ������
	 * sess,user_type,statue,flag, ms_id
	 */
	List  findMs_sertchByPage(Session sess, int pageNo, int pageSize,
			String user_type,String statue,String flag,String ms_id,String ms_name,String pagentid,String childagentid,String ep,String ismobile,String arrearage);
	
	/**�ն˱��ѯ����
	 * @author wanglei
	 * @param user_type  �ն�����
	 * @param statue  ����(����)״̬
	 * @param flag  ��Ч״̬
	 * @param ms_id  �ն˺���
	 * @return �ն��û�ģ����ѯ������
	 * sess,user_type,statue,flag, ms_id
	 */
	List  findMsListInfo_sertchByPage(Session sess, int pageNo, int pageSize,
			String user_type,String statue,String flag,String ms_id,String ms_name,String pagentid,String childagentid,String ep,String ismobile,String arrearage,int roleId,String agentid,String epId);
	
	
	/**
	 * �����û���λɾ���նˡ�Ⱥ�����նˡ�����
	 * @param sess
	 * @param epid
	 */
	void deleteMsInfoByEp_id(Session sess,String epid);
	/**
	 * ���ݴ�����ID��ѯ�����䵽��ҵ���ն�
	 * @param aid
	 * @return
	 */
	List getMsInfo_byAid(Session sess,String aid,int msCount);
	/**
	 * ���ݳ�ֵ��ҵ��ѯ�նˣ������������û���
	 * @param sess
	 * @param ep_id
	 * @return
	 */
	List getMsInfo_byEpidao(Session sess,String ep_id);
	List getMsInfo_byEpidao(Session sess,StringBuffer ep_id);
	List getMsInfo_byEpidao(Session sess,StringBuffer ep_id,String ms);
	
	
	//-------------------------------�ּ������õ���-----------------------------------
	/**epid����ҵID����grpid(Ⱥ��id) 
	 * 
	 */
	List getNonentityMsinfo_ByGrpid(Session sess,String epid,String grpid);

	List<TbMsInfo> listMsInfo(Session session);
	public List<TbMsInfo> listMsInfoByEpId(Session session,Object...objects);
	public List<TbMsInfo> listMsInfoIsByEpId(Session session,Object...objects);
	
	String findMsIdByPhone (Session sess,String phone);
	
}