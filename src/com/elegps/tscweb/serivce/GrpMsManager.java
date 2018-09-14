package com.elegps.tscweb.serivce;

import java.util.List;

import org.hibernate.Session;

import com.elegps.tscweb.exception.MessageException;
import com.elegps.tscweb.model.TbGrpInfo;
import com.elegps.tscweb.model.TbGrpMsListInfo;




public interface GrpMsManager 
{ 
	/**
	 * ��ȡȺ��������Ϣ����
	 * Ⱥ��������Ϣ�ܼ�¼��
	 * @return ��ȡȺ��������Ϣ���� 
	 */
	int getAllGrp_idCount();

	/**
	 * ����ÿҳ��¼�����ܼ�¼����ȡ��ҳ��
	 * @param count �ܼ�¼��
	 * @param pageSize ÿҳ��ʾ�ļ�¼��
	 * @return ����õ�����ҳ��
	 */
	int getPageCount(int count , int pageSize);
	
	/**
	 * ����Ⱥ���ն˶�Ӧ��ϵָ��ҳ������Ⱥ���ն˶�Ӧ��ϵ��Ϣ
	 * @param pageNo ָ��ҳ��
	 * @param  pagesize ÿҳ��ʾ������
	 * @return ����Ⱥ���ն˶�Ӧ��ϵָ��ҳ������Ⱥ���ն˶�Ӧ��ϵ��Ϣ
	 */ 
	List <TbGrpMsListInfo> getAllGrpid_InfoByPage(int pageNo,int pagesize);
	
	/**
	 * ��ȡָ��Ⱥ���������Ϣ����
	 * @param grp_id Ⱥ���
	 * @return ��ȡָ��Ⱥ���������Ϣ����
	 */
	int getGrp_idCount(String grp_id);
	
	
	/**
	 * ����Ⱥ���ն˶�Ӧ��ϵ�а�Ⱥ���ָ��ҳ������Ⱥ���ն˶�Ӧ��ϵ��Ϣ
	 * @param pageNo ָ��ҳ��
	 * @param  pagesize ÿҳ��ʾ������
	 * @param  grp_id Ⱥ���
	 * @return ����Ⱥ���ն˶�Ӧ��ϵָ��ҳ������Ⱥ���ն˶�Ӧ��ϵ��Ϣ
	 */ 
	List <TbGrpMsListInfo> getGrpid_InfoByPage(int pageNo,int pagesize,String grp_id);
	
	
	/**
	 * ��ȡ�ն�������Ϣ����
	 * Ⱥ��������Ϣ�ܼ�¼��
	 * @return ��ȡȺ��������Ϣ���� 
	 */
	int getAllms_idCount();
	
	
	/**
	 * ����Ⱥ���ն˶�Ӧ��ϵָ��ҳ������Ⱥ���ն˶�Ӧ��ϵ��Ϣ
	 * @param pageNo ָ��ҳ��
	 * @param  pagesize ÿҳ��ʾ������
	 * @return ����Ⱥ���ն˶�Ӧ��ϵָ��ҳ������Ⱥ���ն˶�Ӧ��ϵ��Ϣ
	 */ 
	List <TbGrpMsListInfo> getAllMsid_InfoByPage(int pageNo,int pagesize);
	
	
	/**
	 * ��ȡָ���ն˺�������Ϣ����
	 * @param ms_id �ն˺�
	 * @return ��ȡָ���ն˺�������Ϣ����
	 */
	int getMs_idCount(String ms_id);
	
	
	
	/**
	 * Ⱥ���ն˶�Ӧ��ϵ�а��ն˺�ָ��ҳ������Ⱥ���ն˶�Ӧ��ϵ��Ϣ
	 * @param pageNo ָ��ҳ��
	 * @param  pagesize ÿҳ��ʾ������
	 * @param  ms_id �ն˺�
	 * @return ����Ⱥ���ն˶�Ӧ��ϵ�а��ն˺�ָ��ҳ������Ⱥ���ն˶�Ӧ��ϵ��Ϣ
	 */ 
	List <TbGrpMsListInfo> getMsid_InfoByPage(int pageNo,int pagesize,String ms_id);
	

	/**����grp_id,ms_id ɾ����¼
	 * @param grpmsidlist
	 */
	Boolean deleteGrpMs(String[] grpmsidlist);
	
	
	/**����grp_idɾ����¼
	 * @param grp_id Ⱥ�����
	 */
	void GrpMsInfodeletebyGrp_id(String grp_id);
	
	/**����ms_idɾ����¼
	 * @param ms_id Ⱥ�����
	 */
	void GrpMsInfodeletebyms_id(String ms_id);
	
	
	/**
	 * ����һ��Ⱥ���ն˶�Ӧ��ϵ��Ϣ
	 * @param grp_id Ⱥ�����
	 * @param ms_id �ն˺�
	 * @return �´��ն˵�����,�������ʧ�ܣ�����null��
	 */  
	String createGrpMsInfo(String grp_id , String ms_id);
	
	/**
	 * ���ݲ�ѯ����ȡ�ü�¼����
	 * @param grpid
	 * @param msid
	 * @return  ��ѯ����ȡ�ü�¼����
	 */
   int getGrpMs_SearchCount(String grpid,String msid,String pagentid,String childagentid,String ep);
   
  
  
   /**
    * ����Ⱥ��Ų�ѯ�Ѿ����ڵ��ն���Ϣ
    * @param grpid
    * @return
    */
   List  getMs_info(String grpid);
   
   
   /**
    * �����ն˺Ų�ѯ�Ѿ����ڵ�Ⱥ����Ϣ
    * @param msid
    * @return
    */
   List getGrp_info(String msid);
   
   /**
    * ����Ⱥ�����������ն˺�
    * @param grpid
    * @param msid
    * @return
    */
  String createGrpMsInfo_ByGrpid(String grpid,String[] msid);
  
  /**
   * �����ն˺��������Ⱥ���
   * @param grpid
   * @param msid
   * @return
   */
 String createGrpMsInfo_ByMsid(String msid,String[] grpid);

 
 List getGrpMs_SearchByPage(int pageNo,
			int pageSize,String grpid, String msid, String pagentid,String childagentid,String ep);
 
 
 /**
  * ����Ⱥ��id,�ն�id��ѯȺ���ն˶�Ӧ��ϵ
  * @param grpid
  * @param msid
  * @return
  */
 TbGrpMsListInfo getGrpMs_ByGrpMsid(String grpid,String msid);
 
 
 /**
  * �޸�Ⱥ��������Ϣ
  * @param grpid
  * @param msid
  * @param config
  * @return
  */
 String modifyGrpMs(String grpid,String rgrpid,String msid,String config);
 
 /**
  * �����ն����ڵ���ҪȺ�飨��������0��MS_Id����GRP_Id  �� MS_Id��GRP_Id�е�ͨ�����ã�0������ͨ����
  * @param sess
  * @param ms_id
  * @return
  */
	TbGrpMsListInfo getGrp(String ms_id);
	
	/**
	 * ��ȡĳ��Ⱥ����ն�����
	 * @param grpId
	 * @return
	 */
	Integer grpMsCount(String grpId);
	
	/**
	 * ��ȡĳ��Ⱥ��������ն���
	 * @param grpId
	 * @return
	 */
	Integer grpOnLineMsCount(String grpId);
	
	boolean deleteGrpMsByMsId(String msId);
	
	/**
	 * �л�Ⱥ�飨����Ⱥ�鶼��ֹͨ����
	 * @return
	 */
	boolean changeGrpMsConfig(String msId,String grpId);
	
	/**
    * ����Ⱥ��Ų�ѯ�ն�״̬��Ϣ
    * @param grpid
    * @return
    */
   List  getMsState(String grpid);
	
}