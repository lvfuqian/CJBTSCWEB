package com.elegps.tscweb.dao;

import org.hibernate.Session;

import com.elegps.tscweb.model.TbAgentInfo;
import com.elegps.tscweb.model.TbEnterpriseInfo;
import com.elegps.tscweb.model.TbGrpInfo;
import com.elegps.tscweb.model.TbMsInfo;

import java.util.List;
import java.util.Map;

public interface GrpDaoFactory
{
	/**
	 * ����Ⱥ��id����
	 * @param sess
	 * @param grp Ⱥ���ǰ17λ����ģ����ѯ����ȡ1������
	 * @return ����Ⱥ��id
	 */
	String getGrpId(Session sess ,String grp);
	
	/**
	 * ��ѯ����Ⱥ��������Ϣ������
	 * @param sess �־û���������Ҫ��Hiberate Session
	 * @return ����Ⱥ��������Ϣ����
	 */
	Integer findAllGrp_typeCount(Session sess);
	
	
	/**
	 * ��ѯָ��Ⱥ����Ч״̬Ϊ1��ָ��ҳ��Ⱥ����Ϣ
	 * @param sess �־û���������Ҫ��Hiberate Session
	 * @param pageNo ��Ҫ��ѯ��ָ��ҳ
	 * @param  pageSize  ҳ�ĸ���
	 * @return ��ѯ����Ⱥ����Ϣ����  
	 */
    List findAllGrp_typeByPage(Session sess , int pageNo , int pageSize);
    
    
    /**
	 * ��ѯȺ��������Ϣ������
	 * @param sess �־û���������Ҫ��Hiberate Session
	 * @param grp_type Ⱥ������
	 * @return Ⱥ��������Ϣ����
	 */
	Integer findGrp_typeCount(Session sess,int grp_type);
	
	
	/**
	 * ��ѯָ��Ⱥ�����͡�ָ��ҳ��Ⱥ����Ϣ
	 * @param sess �־û���������Ҫ��Hiberate Session
	 * @param pageNo ��Ҫ��ѯ��ָ��ҳ
	 * @param pageSize ��Ҫ��ѯ��ָ��ҳ����
	 * @param grp_type ��Ҫ��ѯ��ָ��Ⱥ������
	 * @return ��ѯ����Ⱥ����Ϣ����  
	 */
    List findGrp_typeByPage(Session sess , int pageNo , int pageSize,int grp_type);
    
    /**
	 * ������������Ⱥ����Ϣ
	 * @param sess �־û���������Ҫ��Hiberate Session
	 * @param grp_id ��Ҫ���ص�Ⱥ����Ϣ
	 * @return ���ص�Ⱥ����Ϣ
	 */
    TbGrpInfo get(Session sess , String grp_id);  //���ﲻ������ֻ��Ϊint,long����Ϊ�ַ���
    
    /**
	 * ��ѯ����Ⱥ������ͨ��״̬��Ϣ������
	 * @param sess �־û���������Ҫ��Hiberate Session
	 * @return ����Ⱥ������ͨ��״̬��Ϣ����
	 */
	Integer  findAllGrp_statusCount(Session sess);
	
	
	/**
	 * ��ѯ����Ⱥ����Ч����״̬Ϊ1��ָ��ҳ���ն��û���Ϣ
	 * @param sess �־û���������Ҫ��Hiberate Session
	 * @param pageNo ��Ҫ��ѯ��ָ��ҳ
	 * @param  pageSize  ҳ�ĸ���
	 * @return ��ѯ����Ⱥ��������Ч״̬��Ϣ���� ������ͨ��״̬����
	 */
	List findAllStatueByPage(Session sess , int pageNo , int pageSize);
	
	
	/**
	 * ��ѯȺ����Ч����״̬��Ϣ������
	 * @param sess �־û���������Ҫ��Hiberate Session
	 * @param status Ⱥ����Ч����״̬
	 * @return Ⱥ����Ч����״̬��Ϣ������
	 */
	Integer findGrp_StatusCount(Session sess,int status);
	
	
	
	/**
	 * ��ѯȺ����Ч����״̬��Ϣ
	 * @param sess �־û���������Ҫ��Hiberate Session
	 * @param pageNo ��Ҫ��ѯ��ָ��ҳ
	 * @param  pageSize  ҳ�ĸ���
	 * @param   status Ⱥ����Ч����״̬
	 * @return ��ѯ����Ⱥ����Ч����״̬��Ϣ���� ����Ч����״̬����
	 */
	List findGrp_StatusByPage(Session sess , int pageNo , int pageSize, int status);
	
	

	/**
	 * ����grpid��ģ����ѯ
	 * @return Ⱥ��grpid��ģ����ѯ������
	 */
	Integer  findGrp_idAllCount(Session sess,String grpid);
	
	

	/**
	 * ����grpid��ģ����ѯ
	 * @return Ⱥ��grpid��ģ����ѯҳ��ȫ��Ⱥ����Ϣ
	 */
	List findGrp_idlistByPage(Session sess , int pageNo ,int pageSize,String grpid);
	
	
	
	/**
	 * ��ѯ����Ⱥ����Ч״̬��Ϣ������
	 * @param sess �־û���������Ҫ��Hiberate Session
	 * @return ����Ⱥ����Ч״̬��Ϣ����
	 */
	Integer findGrp_flagAllCount(Session sess);
	
	/**
	 * ��ѯ����Ⱥ����Ч״̬��Ϣ
	 * @param sess �־û���������Ҫ��Hiberate Session
	 * @return ����Ⱥ����Ч״̬��Ϣ
	 */
	List findGrp_flaglistByPage(Session sess , int pageNo , int pageSize);
	
	
	/**
	 * ��ȡָ��Ⱥ����Ч״̬��Ϣ����
	 * @param flag Ⱥ����Ч״��Ӧ�ܼ�¼��
	 * @return ָ��Ⱥ����Ч״̬��Ϣ���� 
	 */  
	Integer findGrp_flagCount(Session sess,int flag);
	
	
	/**
	 * ��ѯָ��Ⱥ����Ч״̬��Ϣ
	 * @param sess �־û���������Ҫ��Hiberate Session
	 * @param falg Ⱥ����Ч״̬
	 * @return ָ��Ⱥ����Ч״̬��Ϣ
	 */
	List findGrp_flaglistByPage(Session sess , int pageNo , int pageSize,int falg);
	
	
	/**
	 * ����������Ч״̬(״̬Ϊ1)���ն��û���Ϣ
	 * @return ����������Ч״̬(״̬Ϊ1)���ն��û���Ϣ
	 */ 
	List findMs_flagList(Session sess);
	
	
	/**
	 * �����ն�Ⱥ��
	 * @param sess �־û���������Ҫ��Hiberate Session
	 * @param m ��Ҫ�����Ⱥ����Ϣ
	 * @retun Ⱥ����Ϣ������
	 */
    String save(Session sess , TbGrpInfo m);
	
    /**
	 * ����Ⱥ����Ϣ
	 * @return ָ��ҳ��ȫ��Ⱥ����Ϣ
	 */
    List findAllGrp_Info(Session sess,String pagentid,String childagentid,String ep);
    List findBaseGrp_Info(Session sess,String pagentid,String childagentid,String ep);
    
    
    /**
     * ����grp_id,ms_id��ѯ�ǲ�������ն˴�������
     * @param sess
     * @param grp_id
     * @param ms_id
     * @return
     */
    Boolean findGrp_InfobyMs_id(Session sess,String grp_id,String ms_id);
    
    
    /**Ⱥ����ѯ����
	 * @param grp_type  Ⱥ������
	 * @param statue  ͨ��״̬
	 * @param flag  ��Ч״̬
	 * @param ms_id  Ⱥ�����
	 * @param ms_id  Ⱥ������
     * @param ep2 
	 * @return Ⱥ��ģ����ѯ������
	 */
	Integer  findGrp_sertchCount(Session sess,String user_type,String statue,String flag,String ms_id,String ms_name,String pagentid,String childagentid, String ep);
	
	
	/**Ⱥ����ѯ��Ϣ
	 * @param grp_type  Ⱥ������
	 * @param statue  ͨ��״̬
	 * @param flag  ��Ч״̬
	 * @param grp_id  Ⱥ�����
	 * @param grp_name  Ⱥ������
	 * @param ep2 
	 * @return Ⱥ��ģ����ѯ����Ϣ
	 */
	List  findGrp_sertchByPage(Session sess, int pageNo, int pageSize,
			String grp_type,String statue,String flag,String grp_id,String grp_name,String pagentid,String childagentid, String ep);
	
	
	/**
	 * ����Ⱥ�����ն��û���ϵ�������ʱ(ȡ�ø�msidû����ӵ�Ⱥ����Ϣ)
	 * @param sess
	 * @param msid
	 * @return
	 */
	List findGrp_Info_bymsid(Session sess,String msid,String ep);
	
	/**
	 * �����û���λid,ɾ��Ⱥ��,��Ⱥ�����ն˶�Ӧ��ϵ
	 * @param sess
	 * @param epid
	 */
	void deleteGrpInfoByEp_id(Session sess,String epid);
	
	/**
	 * �����û���λID��ѯ�û���λ��Ϣ zr
	 * @param sess
	 * @param id
	 * @return
	 */
	TbEnterpriseInfo getEpNameByEpid(Session sess,Integer id);
	
	/**
	 * �����û���λ��ѯ zr
	 */
	List getAllEp(Session sess);
	
	/**
	 * ����Ⱥ��ID��ѯȺ���������û���λ zr
	 */
	TbEnterpriseInfo getNameByGrpid(Session sess,String grpid);
	
	/**
	 * ����Ⱥ�����ն��û���ϵ����ҵ�������ʱ(ȡ�ø�Դ��ҵû����ӵ�Ⱥ����Ϣ)
	 * @param sess
	 * @param msid
	 * @return
	 */
	List findGrp_Info_bymsidGrp(Session sess,String msid,String yep);
	
	/**
	 * ����Ⱥ�鴴����ID��MS_ID����������Ⱥ����Ϣ
	 * @param sess
	 * @param msid
	 * @return
	 */
	List findGrp_Info_byMsId(Session sess,String msid);
	
	//------�ּ������õ���ɾ��Ⱥ��-------------
	Boolean grpall_byid(Session sess,String grpid);
	
	//��Ӷ���
	public void create(Session session,Object objects);
	//�޸Ķ���
	public int update(Session session, String hql, Object...objects);
	public void update(Session session,TbGrpInfo grpInfo);
	//ɾ������
	public int executeQuery(Session session, String hql, Object...objects);
	//��ѯ����
	public List listObjectInfo(Session session, String hql, int pageNO,int pageSize);
	//����������ѯ����
	public List listObject(Session session, String hql, int pageNO,int pageSize,Object...objects);
	//ͳ������
	public int toaltCount(Session session , String hql, Object...objects);
	//���һ������
	public  Object getBean(Session session, String hql, Object...objcts);
	
	/**
	 * Ⱥ������idģ����ѯ
	 * @param nameOrId
	 * @param epid
	 * @return
	 */
	public Map<String,String> getGrpByNameOrId(Session session,String nameOrId,int epid);
}