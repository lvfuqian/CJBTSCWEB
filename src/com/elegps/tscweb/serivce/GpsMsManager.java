package com.elegps.tscweb.serivce;

import java.util.List;

import com.elegps.tscweb.exception.MessageException;
import com.elegps.tscweb.model.TbGpsMsListInfo;
import com.elegps.tscweb.model.TbGrpInfo;
import com.elegps.tscweb.model.TbGrpMsListInfo;




public interface GpsMsManager 
{ 
	/**
	 * ��ȡGPS�����̵�¼�˺�������Ϣ����
	 * GPS�����̵�¼�˺�������Ϣ�ܼ�¼��
	 * @return ��ȡGPS�����̵�¼�˺�������Ϣ���� 
	 */
	int getAllGps_idCount();
	
	
	
	/**
	 * ����ÿҳ��¼�����ܼ�¼����ȡ��ҳ��
	 * @param count �ܼ�¼��
	 * @param pageSize ÿҳ��ʾ�ļ�¼��
	 * @return ����õ�����ҳ��
	 */
	int getPageCount(int count , int pageSize);
	
	
	/**
	 * ��ȡָ��GPS����������Ϣ����
	 * @param gps_id GPS�����ʺ�
	 * @return ��ȡָ��GPS����������Ϣ����
	 */
	int getGps_idCount(String gps_id);
	
	
	/**
	 * ����GPS�����ն˶�Ӧ��ϵָ��ҳ������GPS�����ն˶�Ӧ��ϵ��Ϣ
	 * @param pageNo ָ��ҳ��
	 * @param  pagesize ÿҳ��ʾ������
	 * @return ����GPS�����ն˶�Ӧ��ϵָ��ҳ������GPS�����ն˶�Ӧ��ϵ��Ϣ
	 */ 
	List <TbGpsMsListInfo> getAllGrpid_InfoByPage(int pageNo,int pagesize);
	
	
	/**
	 * ����GPS�����ն˶�Ӧ��ϵ�а�GPS�����ʺ�ָ��ҳ������GPS�����ն˶�Ӧ��ϵ��Ϣ
	 * @param pageNo ָ��ҳ��
	 * @param  pagesize ÿҳ��ʾ������
	 * @param  gps_id GPS�����ʺ�
	 * @return ����GPS�����ն˶�Ӧ��ϵ�а�GPS�����ʺ�ָ��ҳ������GPS�����ն˶�Ӧ��ϵ��Ϣ
	 */ 
	List <TbGpsMsListInfo> getGpsid_InfoByPage(int pageNo,int pagesize,String gps_id);
	
	
	
	/**
	 * ����GPS�����ն˶�Ӧ��ϵָ��ҳ������GPS�����ն˶�Ӧ��ϵ��Ϣ
	 * @param pageNo ָ��ҳ��
	 * @param  pagesize ÿҳ��ʾ������
	 * @return ����GPS�����ն˶�Ӧ��ϵָ��ҳ������GPS�����ն˶�Ӧ��ϵ��Ϣ
	 */ 
	List <TbGpsMsListInfo> getAllMsid_InfoByPage(int pageNo,int pagesize);
	
	
	/**
	 * ��ȡָ���ն˺�������Ϣ����
	 * @param ms_id �ն˺�
	 * @return ��ȡָ���ն˺�������Ϣ����
	 */
	int getMs_idCount(String ms_id);
	
	
	/**
	 * GPS�����ն˶�Ӧ��ϵ�а��ն˺�ָ��ҳ������GPS�����ն˶�Ӧ��ϵ��Ϣ
	 * @param pageNo ָ��ҳ��
	 * @param  pagesize ÿҳ��ʾ������
	 * @param  ms_id �ն˺�
	 * @return ����GPS�����ն˶�Ӧ��ϵ�а��ն˺�ָ��ҳ������GPS�����ն˶�Ӧ��ϵ��Ϣ
	 */ 
	List <TbGpsMsListInfo> getMsid_InfoByPage(int pageNo,int pagesize,String ms_id);
	
	
	
	/**����gps_id,ms_id ɾ����¼
	 * @param gpsmsidlist
	 */
	Boolean deleteGpsMs(String[] gpsmsidlist);
	
	
	
	/**
	 * ����һ��GPS�����ն˶�Ӧ��ϵ��Ϣ
	 * @param gps_id GPS����
	 * @param ms_id �ն˺�
	 * @return �´��ն˵�����,�������ʧ�ܣ�����null��
	 */  
	String createGpsMsInfo(String gps_id , String ms_id);
	
	/**
	 * ���ݲ�ѯ����ȡ�ü�¼����
	 * @param gpsid
	 * @param msid
	 * @return  ��ѯ����ȡ�ü�¼����
	 */
   int getGpsMs_SearchCount(String gpsid,String msid,String pagentid,String childagentid,String ep);
   
   /**
    * ����ָ��ҳ����ѯ����ȡ�ü�¼��Ϣ
    * @param pageNo
    * @param pageSize
    * @param gpsid
    * @param msid
    * @return  ָ��ҳ����ѯ����ȡ�ü�¼��Ϣ
    */
   List getGpsMs_SearchByPage(int pageNo,int pageSize,String gpsid,String msid,String pagentid,String childagentid,String ep);
   
   
   /**
    * ����GPS�Ų�ѯ�Ѿ����ڵ��ն���Ϣ
    * @param gpsid
    * @return
    */
   List  getMs_info(String gpsid);
   
   /**
    * ����GPS����������ն˺�
    * @param gpsid
    * @param msid
    * @return
    */
  String createGpsMsInfo_ByGpsid(String gpsid,String[] msid);

}