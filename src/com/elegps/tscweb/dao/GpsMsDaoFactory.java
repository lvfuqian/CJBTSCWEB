package com.elegps.tscweb.dao;

import org.hibernate.Session;

import com.elegps.tscweb.model.TbGpsMsListInfo;
import com.elegps.tscweb.model.TbGrpInfo;
import com.elegps.tscweb.model.TbGrpMsListInfo;
import com.elegps.tscweb.model.TbMsInfo;

import java.util.List;

public interface GpsMsDaoFactory
{
	/**
	 * ��ȡGPS�����̵�¼�˺�������Ϣ����
	 * GPS�����̵�¼�˺�������Ϣ�ܼ�¼��
	 * @return ��ȡGPS�����̵�¼�˺�������Ϣ���� 
	 */
	Integer findGps_idAllCount(Session sess);
	
	
	/**
	 * ����GPS�����ն˶�Ӧ��ϵָ��ҳ������GPS�����ն˶�Ӧ��ϵ��Ϣ
	 * @param pageNo ָ��ҳ��
	 * @param  pagesize ÿҳ��ʾ������
	 * @return ����GPS�����ն˶�Ӧ��ϵָ��ҳ������GPS�����ն˶�Ӧ��ϵ��Ϣ
	 */ 
	List findAllGpsMs_InfoGrpidByPage(Session sess,int pageNo,int pagesize);
	
	
	/**
	 * ��ȡָ��GPS����������Ϣ����
	 * @param gps_id GPS�����ʺ�
	 * @return ��ȡָ��GPS����������Ϣ����
	 */
	Integer findGps_idCount(Session sess,String gps_id);
	
	
	/**
	 * ����GPS�����ն˶�Ӧ��ϵ�а�GPS�����ʺ�ָ��ҳ������GPS�����ն˶�Ӧ��ϵ��Ϣ
	 * @param pageNo ָ��ҳ��
	 * @param  pagesize ÿҳ��ʾ������
	 * @param  gps_id GPS�����ʺ�
	 * @return ����GPS�����ն˶�Ӧ��ϵ�а�GPS�����ʺ�ָ��ҳ������GPS�����ն˶�Ӧ��ϵ��Ϣ
	 */ 
	List findAllGpsid_InfoByPage(Session sess,int pageNo,int pagesize,String gps_id);
	
	
	
	/**
	 * ����GPS�����ն˶�Ӧ��ϵָ��ҳ������GPS�����ն˶�Ӧ��ϵ��Ϣ
	 * @param pageNo ָ��ҳ��
	 * @param  pagesize ÿҳ��ʾ������
	 * @return ����GPS�����ն˶�Ӧ��ϵָ��ҳ������GPS�����ն˶�Ӧ��ϵ��Ϣ
	 */ 
	List findAllGpsMs_InfoMsidByPage(Session sess,int pageNo,int pagesize);
	
	
	
	/**
	 * ��ȡָ���ն˺�������Ϣ����
	 * @param ms_id �ն˺�
	 * @return ��ȡָ���ն˺�������Ϣ����
	 */
	Integer findMs_idCount(Session sess,String ms_id);
	
	
	
	/**
	 * GPS�����ն˶�Ӧ��ϵ�а��ն˺�ָ��ҳ������GPS�����ն˶�Ӧ��ϵ��Ϣ
	 * @param pageNo ָ��ҳ��
	 * @param  pagesize ÿҳ��ʾ������
	 * @param  ms_id �ն˺�
	 * @return ����GPS�����ն˶�Ӧ��ϵ�а��ն˺�ָ��ҳ������GPS�����ն˶�Ӧ��ϵ��Ϣ
	 */ 
	List findAllMsid_InfoByPage(Session sess,int pageNo,int pagesize,String ms_id);
	
	
	
	/**
	 * ������������GPS�������ն˶�Ӧ��ϵ��Ϣ
	 * @param sess �־û���������Ҫ��Hiberate Session
	 * @param gps_id GPS�ʺ�
	 * @param ms_id �ն˺�
	 * @return ��������������Ⱥ����˶�Ӧ��ϵ��Ϣ
	 */
	TbGpsMsListInfo get(Session sess , String gps_id,String ms_id);  
	
	
	/**
	 * ɾ��GPS�������ն˶�Ӧ��ϵ��Ϣ
	 * @param sess �־û���������Ҫ��Hiberate Session
	 * @param tbgrpmsinfo GPS�������ն˶�Ӧ��ϵ��Ϣ����
	 */
	void delete(Session sess,TbGpsMsListInfo tbgpsmsinfo); 
	
	
	/**
	 * ����GPS�����ն˶�Ӧ��ϵ
	 * @param sess �־û���������Ҫ��Hiberate Session
	 * @param m ��Ҫ�����GPS�����ն˶�Ӧ��ϵ��Ϣ
	 * @retun GPS������Ϣ������
	 */
    String save(Session sess , TbGpsMsListInfo gpsms);
    
    
    
    /**����gps_idɾ����¼
	 * @param gps_id GPS����
	 */
	void deleteGpsMsInfoByGps_id(Session sess,String gps_id);
	
	
	/**����ms_idɾ����¼
	 * @param ms_id Ⱥ�����
	 */
	void deleteGpsMsInfoByMs_id(Session sess,String ms_id);
	
	
	 /**
	 * ���ݲ�ѯ����ȡ�ü�¼����
	 * @param gpsid
	 * @param msid
	 * @return  ��ѯ����ȡ�ü�¼����
	 */
    Integer findGpsMs_SearchCount(Session sess,String gpsid,String msid,String pagentid,String childagentid,String ep);
   
    
     /**
	    * ����ָ��ҳ����ѯ����ȡ�ü�¼��Ϣ
	    * @param pageNo
	    * @param pageSize
	    * @param gpsid
	    * @param msid
	    * @return  ָ��ҳ����ѯ����ȡ�ü�¼��Ϣ
	    */
    List findGpsMs_SearchByPage(Session sess,int pageNo,int pageSize,String gpsid,String msid,String pagentid,String childagentid,String ep);
    
    
    /**
     * ����GPS�Ų�ѯ�Ѿ����ڵ��ն���Ϣ
     * @param gpsid
     * @return
     */
    List findAllms_bygpsid(Session sess,String gpsid);
   
    
}