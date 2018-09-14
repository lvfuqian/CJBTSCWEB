package com.elegps.tscweb.dao;

import org.hibernate.Session;

import com.elegps.tscweb.model.TbGrpInfo;
import com.elegps.tscweb.model.TbGrpMsListInfo;
import com.elegps.tscweb.model.TbMsInfo;

import java.util.List;

public interface GrpMsDaoFactory
{
	
	/**
	 * ��ȡȺ��������Ϣ����
	 * Ⱥ��������Ϣ�ܼ�¼��
	 * @return ��ȡȺ��������Ϣ���� 
	 */
	Integer findGrp_idAllCount(Session sess);
	

	/**
	 * ����Ⱥ���ն˶�Ӧ��ϵָ��ҳ������Ⱥ���ն˶�Ӧ��ϵ��Ϣ
	 * @param pageNo ָ��ҳ��
	 * @param  pagesize ÿҳ��ʾ������
	 * @return ����Ⱥ���ն˶�Ӧ��ϵָ��ҳ������Ⱥ���ն˶�Ӧ��ϵ��Ϣ
	 */ 
	List findAllGrpMs_InfoGrpidByPage(Session sess,int pageNo,int pagesize);
	
	
	/**
	 * ��ȡָ��Ⱥ���������Ϣ����
	 * @param grp_id Ⱥ���
	 * @return ��ȡָ��Ⱥ���������Ϣ����
	 */
	Integer findGrp_idCount(Session sess,String grp_id);
	
	/**
	 * ����Ⱥ���ն˶�Ӧ��ϵ�а�Ⱥ���ָ��ҳ������Ⱥ���ն˶�Ӧ��ϵ��Ϣ
	 * @param pageNo ָ��ҳ��
	 * @param  pagesize ÿҳ��ʾ������
	 * @param  grp_id Ⱥ���
	 * @return ����Ⱥ���ն˶�Ӧ��ϵ�а�Ⱥ���ָ��ҳ������Ⱥ���ն˶�Ӧ��ϵ��Ϣ
	 */ 
	List findAllGrpid_InfoByPage(Session sess,int pageNo,int pagesize,String grp_id);
	
	
	
	/**
	 * ��ȡ�ն�������Ϣ����
	 * Ⱥ��������Ϣ�ܼ�¼��
	 * @return ��ȡȺ��������Ϣ���� 
	 */
	Integer findMs_idAllCount(Session sess);
	
	
	/**
	 * ����Ⱥ���ն˶�Ӧ��ϵָ��ҳ������Ⱥ���ն˶�Ӧ��ϵ��Ϣ
	 * @param pageNo ָ��ҳ��
	 * @param  pagesize ÿҳ��ʾ������
	 * @return ����Ⱥ���ն˶�Ӧ��ϵָ��ҳ������Ⱥ���ն˶�Ӧ��ϵ��Ϣ
	 */ 
	List findAllGrpMs_InfoMsidByPage(Session sess,int pageNo,int pagesize);
	
	
	/**
	 * ��ȡָ���ն˺�������Ϣ����
	 * @param ms_id �ն˺�
	 * @return ��ȡָ���ն˺�������Ϣ����
	 */
	Integer findMs_idCount(Session sess,String ms_id);
	
	
	/**
	 * Ⱥ���ն˶�Ӧ��ϵ�а��ն˺�ָ��ҳ������Ⱥ���ն˶�Ӧ��ϵ��Ϣ
	 * @param pageNo ָ��ҳ��
	 * @param  pagesize ÿҳ��ʾ������
	 * @param  ms_id �ն˺�
	 * @return ����Ⱥ���ն˶�Ӧ��ϵ�а��ն˺�ָ��ҳ������Ⱥ���ն˶�Ӧ��ϵ��Ϣ
	 */ 
	List findAllMsid_InfoByPage(Session sess,int pageNo,int pagesize,String ms_id);
	
	
	
	/**
	 * ��������������Ⱥ����˶�Ӧ��ϵ��Ϣ
	 * @param sess �־û���������Ҫ��Hiberate Session
	 * @param ms_id �ն˺�
	 * @param grp_id �ն˺�
	 * @return ��������������Ⱥ����˶�Ӧ��ϵ��Ϣ
	 */
	TbGrpMsListInfo get(Session sess , String grp_id,String ms_id);  
	/**
	 * ����msid���Ҷ�ӦȺ���ϵ��Ϣ
	 * @param sess
	 * @param ms_id
	 * @return
	 */
	List getInfoByMs(Session sess ,String ms_id);
	/**
	 * ɾ����Ⱥ����˶�Ӧ��ϵ��Ϣ
	 * @param sess �־û���������Ҫ��Hiberate Session
	 * @param tbgrpmsinfo Ⱥ����˶�Ӧ����
	 */
	void delete(Session sess,TbGrpMsListInfo tbgrpmsinfo); 
	
	/**����grp_idɾ����¼
	 * @param grp_id Ⱥ�����
	 */
	void deleteGrpMsInfoByGrp_id(Session sess,String grp_id);
	
	
	/**����ms_idɾ����¼
	 * @param ms_id Ⱥ�����
	 */
	void deleteGrpMsInfoByMs_id(Session sess,String ms_id);
	
	
	/**
	 * ����Ⱥ���ն˶�Ӧ��ϵ
	 * @param sess �־û���������Ҫ��Hiberate Session
	 * @param m ��Ҫ�����Ⱥ����Ϣ
	 * @retun Ⱥ����Ϣ������
	 */
    String save(Session sess , TbGrpMsListInfo grpms);
    
    /**
	 * ���ݲ�ѯ����ȡ�ü�¼����
	 * @param grpid
	 * @param msid
	 * @return  ��ѯ����ȡ�ü�¼����
	 */
    Integer findGrpMs_SearchCount(Session sess,String grpid,String msid,String pagentid,String childagentid,String ep);
   
    
     /**
	    * ����ָ��ҳ����ѯ����ȡ�ü�¼��Ϣ
	    * @param pageNo
	    * @param pageSize
	    * @param grpid
	    * @param msid
	    * @return  ָ��ҳ����ѯ����ȡ�ü�¼��Ϣ
	    */
    List findGrpMs_SearchByPage(Session sess,int pageNo,int pageSize,String pagentid,String childagentid,String ep,String grpid,String msid);
    
    
    /**
     * ����Ⱥ��Ų�ѯ�Ѿ����ڵ��ն���Ϣ
     * @param grpid
     * @return
     */
    List findAllms_bygrpid(Session sess,String grpid);
    
    /**
     * �����ն˺Ų�ѯ�Ѿ����ڵ�Ⱥ����Ϣ
     * @param msid
     * @return
     */
    List findAllgrp_bymsid(Session sess,String msid);

    /**
     * ��������ն��ڱ��Ⱥ���Ƿ����
     * @param sess
     * @param ms_id
     * @return
     */
	TbGrpMsListInfo get(Session sess, String ms_id);

	/**
     * �����ն����ڵ���ҪȺ�飨��������0��MS_Id����GRP_Id  �� MS_Id��GRP_Id�е�ͨ�����ã�0������ͨ����
     * @param sess
     * @param ms_id
     * @return
     */
	TbGrpMsListInfo getGrp(Session sess, String ms_id);
	
	/**
	 * �޸�Ⱥ������Ϊ����ʱ��0��
	 * @param sess
	 * @param grpid
	 * @param msid
	 */
	void setConfig(Session sess, String grpid,String rgrpid, String msid,int config);


	/**
	 * ɾ��
	 * @param sess
	 * @param grpid
	 * @param msid
	 */
	void delete(Session sess, String grpid, String[] msid);

	
	/**
	 * ɾ��
	 * @param sess
	 * @param grpid
	 * @param msid
	 */
	void delete(Session sess, String[] grpid, String msid);

	/**
	 * ��ȡĳ��Ⱥ����ն�����
	 * @param sess
	 * @param grpId
	 * @return
	 */
	Integer grpMsCount(Session sess,String grpId);
	
	/**
	 * ��ȡĳ��Ⱥ��������ն���
	 * @param sess
	 * @param grpId
	 * @return
	 */
	Integer grpOnLineMsCount(Session sess,String grpId);
	
    /**
     * ����Ⱥ��Ų�ѯ�ն�״̬��Ϣ
     * @param grpid
     * @return
     */
    List findAllMsStatesByGrpid(Session sess,String grpid);
}