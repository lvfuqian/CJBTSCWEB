package com.elegps.tscweb.serivce;

import java.util.List;
import java.util.Map;

import com.elegps.tscweb.exception.MessageException;
import com.elegps.tscweb.model.TabBaseGrpextinfo;
import com.elegps.tscweb.model.TabSysconfig;
import com.elegps.tscweb.model.TbEnterpriseInfo;
import com.elegps.tscweb.model.TbGrpInfo;




public interface GrpManager 
{ 
	/**
	 * ����Ⱥ��id����
	 * @param grp Ⱥ���ǰ17λ����ģ����ѯ����ȡ1������
	 * @return ����Ⱥ��id
	 */
	String getGrpId(String grp);
	/**
	 * ��ȡ����Ⱥ��������Ϣ����
	 * @return ����Ⱥ��������Ϣ������ 
	 */
	int getAllgrp_typeCount();
	
	
	/**
	 * ����ÿҳ��¼�����ܼ�¼����ȡ��ҳ��
	 * @param count �ܼ�¼��
	 * @param pageSize ÿҳ��ʾ�ļ�¼��
	 * @return ����õ�����ҳ��
	 */
	int getPageCount(int count , int pageSize);
	
	
	
	/**
	 * �����ض�ҳ������Ⱥ����Ϣ
	 * @param pageNo ָ��ҳ��
	 * @param  pagesize ÿҳ��ʾ������
	 * @return ָ��ҳ��ȫ��Ⱥ����Ϣ
	 */
	List <TbGrpInfo> getAllGrp_typeByPage(int pageNo,int pagesize);
	
	
	/**
	 * ��ȡȺ��������Ϣ����
	 * @param grp_type �ն��û����Ͷ�Ӧ�ܼ�¼��
	 * @return �ն��û�������Ϣ������ 
	 */
	int getGrp_typeCount(int grp_type);
	
	
	/**
	 * �����ض�Ⱥ������ҳ�������ն��û���Ϣ
	 * @param pageNo ָ��ҳ��
	 * @param  pagesize ÿҳ��ʾ������
	 * @param  grp_type Ⱥ������
	 * @return ָ��Ⱥ������ҳ��ȫ��Ⱥ����Ϣ
	 */ 
	List <TbGrpInfo> getGrp_typeByPage(int pageNo,int pagesize,int grp_type);
	
	
	/**
	 * �����ض�ָ������Ⱥ����Ϣ
	 * @param grpid Ⱥ�����
	 */
	TbGrpInfo getGrpinfoby_grpid(String grpid);
	
	
	/**
	 * ��ȡ����Ⱥ������ͨ��̬��Ϣ����
	 * @return Ⱥ������ͨ��̬��Ϣ����
	 */
	int getAllGrpStatus_allCount();
	
	
	/**
	 * ��ȡ����Ⱥ������ͨ��̬��Ϣ
	 * @param pageNo ָ��ҳ��
	 * @param  pagesize ÿҳ��ʾ������
	 * @return ָ��ҳ��ȫ���ն��û��û�״̬��Ϣ  ������״̬����
	 */
	List <TbGrpInfo> getAllGrpStatus_allByPage(int pageNo,int pagesize);
	
	
	/**
	 * ��ȡȺ������ͨ��̬��Ϣ����
	 * @param stauts Ⱥ����ͨ��̬��Ϣ��Ӧ�ܼ�¼��
	 * @return Ⱥ������ͨ��̬��Ϣ����
	 */
	int getGrp_StauteCount(int stauts);
	
	
	/**
	 * ����ָ����ȡȺ������ͨ��̬��Ϣ
	 * @param pageNo ָ��ҳ��
	 * @param  pagesize ÿҳ��ʾ������
	 * @param  status Ⱥ������ͨ��̬
	 * @return ָ����ȡȺ������ͨ��̬��Ϣ
	 */
	List <TbGrpInfo> getGrp_StatusByPage(int pageNo,int pagesize,int status);

	
	/**����grpid
	 * ɾ����¼
	 * @param grpid_list
	 */
	Boolean deleteGrp(String grpid);
	
	/**
	 * ����grp_id��ģ����ѯ
	 * @return Ⱥ��grp_id��ģ����ѯ������
	 */
	int getGrp_idCount(String grp_id);
	
	
	
	/**
	 * ����grpid��ģ����ѯ
	 * @return Ⱥ��grpid��ģ����ѯҳ��ȫ��Ⱥ����Ϣ
	 */
	List <TbGrpInfo> getTbGrpinfoby_grpid(int pageNo,int pagesize,String grpid);
	
	
	
	/**
	 * ��ȡȺ����Ч״̬��Ϣ����
	 * Ⱥ����Ч״̬��Ӧ�ܼ�¼��
	 * @return Ⱥ����Ч״̬��Ϣ������ 
	 */
	int getAllGrp_flagCount()throws MessageException;
	
	
	/**
	 * ��������Ⱥ����Ч״̬ҳ������Ⱥ����Ϣ
	 * @param pageNo ָ��ҳ��
	 * @param  pagesize ÿҳ��ʾ������
	 * @return ����Ⱥ����Ч״̬ҳ������Ⱥ����Ϣ
	 */ 
	List <TbGrpInfo> getAllGrp_flagByPage(int pageNo,int pagesize);
	
	
	/**
	 * ��ȡָ��Ⱥ����Ч״̬ҳ������Ⱥ����Ϣ����
	 * @param flag �ն��û�״̬��Ӧ�ܼ�¼��
	 * @return ָ��Ⱥ����Ч״̬ҳ������Ⱥ����Ϣ����
	 */
	int getGrp_flagCount(int flag);
	
	
	/**
	 * ����ָ��Ⱥ����Ч״̬ҳ������Ⱥ����Ϣ
	 * @param pageNo ָ��ҳ��
	 * @param  pagesize ÿҳ��ʾ������
	 * @param  flag Ⱥ����Ч״̬
	 * @return ָ��Ⱥ����Ч״̬ҳ������Ⱥ����Ϣ
	 */ 
	List <TbGrpInfo> getGrp_flagByPage(int pageNo,int pagesize,int flag);

	
	/**
	 * ����������Ч״̬(״̬Ϊ1)���ն��û���Ϣ
	 * @return ����������Ч״̬(״̬Ϊ1)���ն��û���Ϣ
	 */ 
	public List getTbMsInfo();
	
	
	/**
	 * ����һ��Ⱥ����Ϣ
	 * @param grp_id Ⱥ�����
	 * @param grp_name Ⱥ������
	 * @param ms_id ��������ն˺�
	 * @param grp_type �ն���������
	 * @param Flag  �ն��û���״̬
	 * @return �´��ն˵�����,�������ʧ�ܣ�����null��
	 */  
	String createGrp(String grp_id , String grp_name , String ms_id,int grp_type,int flag,int talksc,String ep_id,int grp_lf,String grp_pid,String C03);
	
	
	/**����grp_id���¼�¼�б�
	 * ���¼�¼�б�
	 * @param grp_id
	 * @param grp_name
	 * @param grp_type
	 * @param grp_flag
	 */
	String modifyGrp(String grp_id,String ep_id,String grp_name,int grp_type,int grp_flag,int talksc,int grp_lf,String C03);
	
	/**
	 * �޸�Ⱥ����Ϣ
	 * @param grpInfo
	 */
	void update(TbGrpInfo grpInfo);
	
	/**
	 * ����Ⱥ����Ϣ
	 * @return ָ��ҳ��ȫ��Ⱥ����Ϣ
	 */
	List <TbGrpInfo> getAllGrp_Info(String pagentid,String childagentid,String ep);
	List <TbGrpInfo> getBaseGrp_Info(String pagentid,String childagentid,String ep);
		
	
	
	/**
	 * ����Ⱥ�����ն��û���ϵ�������ʱ(ȡ�ø�msidû����ӵ�Ⱥ����Ϣ)
	 * @param msid
	 * @return
	 */
	List getAllGrp_Info_not_Bymsid(String msid,String ep);

	
	/**Ⱥ����ѯ����
	 * @param grp_type  Ⱥ������
	 * @param statue  ͨ��״̬
	 * @param flag  ��Ч״̬
	 * @param ep2 
	 * @param ms_id  Ⱥ�����
	 * @param ms_id  Ⱥ������
	 * @return Ⱥ��ģ����ѯ������
	 */
	int getGrp_sertch(String grp_type,String statue,String flag,String grp_id,String grp_name,String pagentid,String childagentid, String ep);
	
	
	/**Ⱥ����ѯ��Ϣ
	 * @param grp_type  Ⱥ������
	 * @param statue  ͨ��״̬
	 * @param flag  ��Ч״̬
	 * @param grp_id  Ⱥ�����
	 * @param grp_name  Ⱥ������
	 * @param ep2 
	 * @return Ⱥ��ģ����ѯ����Ϣ
	 */
	List <TbGrpInfo> getTBGrpinfoby_grppage(int pageNo, int pagesize,
			String grp_type,String statue,String flag,String grp_id,String grp_name,String pagentid,String childagentid, String ep);
   /**
    * ��ѯ�����û���λ��Ϣ zr
    * @return
    */
	List selectEp();
	/**
	 * ����Ⱥ��ID��ѯȺ���������û���λ zr
	 * @param epid
	 * @return
	 */
	TbEnterpriseInfo getEpBygrpid(String grpid);
	
	
	/**
	 * ����Ⱥ�鴴����ID��MS_ID����������Ⱥ����Ϣ
	 * @param msid
	 * @return
	 */
	List findGrp_Info_byMsId(String msid);
	
	/**
	 * ����Ⱥ�����ն��û���ϵ����ҵ�������ʱ(ȡ�ø�Դ����ҵû����ӵ�Ⱥ����Ϣ)
	 * @param msid
	 * @return
	 */
	List getAllGrp_Info_not_BymsidGrp(String msid,String yep);
	public List listAll(int pageNo, int pageSize, TabBaseGrpextinfo baseGrp);

	public void create(TabBaseGrpextinfo baseGrp);

	public int update(String hql, Object... objects);

	public int delete(String grpid);

	public int totalCount(TabBaseGrpextinfo baseGrp);
	public <T> T getBean(String grpId);
	
	
	/**
	 * ����һ��Ⱥ����Ϣ
	 * @param grp_id Ⱥ�����
	 * @param grp_name Ⱥ������
	 * @param ms_id ��������ն˺�
	 * @param grp_type �ն���������
	 * @param Flag  �ն��û���״̬
	 * @return �´��ն˵�����,�������ʧ�ܣ�����null��
	 */  
	String createGrp(String grp_id , String grp_name , String ms_id,int grp_type,int flag,int talksc,String ep_id,int grp_lf,String grp_pid,String C03,String desc);
	
	/**
	 * Ⱥ������idģ����ѯ
	 * @param nameOrId
	 * @param epid
	 * @return
	 */
	public Map<String,String> getGrpByNameOrId(String nameOrId,int epid);
}