package com.elegps.tscweb.dao;

import org.hibernate.Session;

import com.elegps.tscweb.model.TbGpsInfo;
import com.elegps.tscweb.model.TbGrpInfo;
import com.elegps.tscweb.model.TbMsInfo;

import java.util.List;

public interface GpsDaoFactory
{
	/**
	 * ��ȡ����GPS�����̵�¼�˺�����
	 * @return ����GPS�����̵�¼�˺ŵ����� 
	 */
	Integer getGps_idAllCount(Session sess);
	
	
	/**
	 * �����ض�ҳ������GPS�����̵�¼�˺�
	 * @param pageNo ָ��ҳ��
	 * @param  pagesize ÿҳ��ʾ������
	 * @return ָ��ҳ��ȫ��GPS�����̵�¼�˺���Ϣ
	 */
    List findAllGps_typeByPage(Session sess , int pageNo , int pageSize);
    
    
    /**
	 * ��ȡ����GPS�����̵�¼�˺�����
	 * @return ����GPS�����̵�¼�˺ŵ����� 
	 */
    Integer getGps_idCount(Session sess,String gps_id,String gps_name);
    
    
    /**
	 * �����ض�ҳ��ָ��GPS�����̵�¼�˺�
	 * @param pageNo ָ��ҳ��
	 * @param  pagesize ÿҳ��ʾ������
	 * @return ָ��ҳ��GPS�����̵�¼�˺���Ϣ
	 */
    List findGps_typeByPage(Session sess , int pageNo , int pageSize,String gps_id,String gps_name);
    
    
    /**
	 * �����������ط����̵�¼�˺���Ϣ
	 * @param sess �־û���������Ҫ��Hiberate Session
	 * @param gps_id ��Ҫ���ص�¼�˺���Ϣ
	 * @return ���ط����̵�¼�˺���Ϣ
	 */
    TbGpsInfo get(Session sess , String gps_id);  //���ﲻ������ֻ��Ϊint,long����Ϊ�ַ���
    
    
    /**
     * �鿴��û����ͬ��gsp_id,gsp_name
     * @param sess
     * @param gps_id
     * @param gps_name
     * @return
     */
    TbGpsInfo getidorname(Session sess,String gps_id,String gps_name); 
    
    
    /**
     * ���ҳ�gps_id��������Ϊgps_name�ļ�¼
     * @param sess
     * @param gps_id
     * @param gps_name
     * @return
     */
    TbGpsInfo get_byname(Session sess,String gps_id,String gps_name); 
    
    
    /**
	 * ��������̵�¼�˺���Ϣ
	 * @param sess �־û���������Ҫ��Hiberate Session
	 * @param m ��Ҫ����ķ����̵�¼�˺���Ϣ
	 * @retun �����̵�¼�˺���Ϣ������
	 */
    String save(Session sess , TbGpsInfo m);
    
    /**
     * ɾ������
     * @param sess
     * @param m
     */
    void delete(Session sess,TbGpsInfo m);
    
    
    /**
	 * ����GPS������Ϣ
	 * @return ָ��ҳ��ȫ��GPS������Ϣ
	 */
    List findAllGps_Info(Session sess);
}