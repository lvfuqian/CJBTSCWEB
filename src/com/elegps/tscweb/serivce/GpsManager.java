package com.elegps.tscweb.serivce;

import java.util.List;

import com.elegps.tscweb.exception.MessageException;
import com.elegps.tscweb.model.TbGpsInfo;
import com.elegps.tscweb.model.TbGrpInfo;




public interface GpsManager 
{ 
	/**
	 * ��ȡ����GPS�����̵�¼�˺�����
	 * @return ����GPS�����̵�¼�˺ŵ����� 
	 */
	int getGps_idAllCount();
	
	
	/**
	 * ����ÿҳ��¼�����ܼ�¼����ȡ��ҳ��
	 * @param count �ܼ�¼��
	 * @param pageSize ÿҳ��ʾ�ļ�¼��
	 * @return ����õ�����ҳ��
	 */
	int getPageCount(int count , int pageSize);
	
	
	/**
	 * �����ض�ҳ������GPS�����̵�¼�˺�
	 * @param pageNo ָ��ҳ��
	 * @param  pagesize ÿҳ��ʾ������
	 * @return ָ��ҳ��ȫ��GPS�����̵�¼�˺���Ϣ
	 */
	List <TbGpsInfo> getAllGps_idyPage(int pageNo,int pagesize);
	
	
	
	/**
	 * ��ȡָ��GPS�����̵�¼�˺�����
	 * @return ָ��GPS�����̵�¼�˺ŵ����� 
	 */
	int getGps_idCount(String gps_id,String gps_name);
	
	
	
	/**
	 * �����ض�ҳ��ָ��GPS�����̵�¼�˺�
	 * @param pageNo ָ��ҳ��
	 * @param  pagesize ÿҳ��ʾ������
	 * @return ָ��ҳ��GPS�����̵�¼�˺���Ϣ
	 */
	List <TbGpsInfo> getGps_idyPage(int pageNo,int pagesize,String gps_id,String gps_name);
	

	
	/**
	 * ����һ��GPS�����̵�¼�˺���Ϣ
	 * @param gps_id ��¼�˺�
	 * @param password ����
	 * @return �´���¼�˺ŵ�����,�������ʧ�ܣ�����null��
	 */  
	String createGps(String gps_id , String password,String gps_name);
	
	
	
	/**����gps_id�б�
	 * ɾ����¼
	 * @param grpid_list
	 */
	Boolean deleteGps(String[] gpsid_list);
	
	
	/**
	 * ���ݷ����̵�¼�˺ż�����Ϣ
	 * @param gps_id
	 * @return
	 */
	TbGpsInfo getGps_bygrpid(String gps_id);
	
	
	/**����gps_id���¼�¼�б�
	 * ���¼�¼�б�
	 * @param gps_id
	 * @param password
	 * @param gps_name
	 */
	String modifyGps(String gps_id,String password,String gps_name);
	
	
	/**
	 * ����GPS������Ϣ
	 * @return ָ��ҳ��ȫ��GPS������Ϣ
	 */
	List <TbGpsInfo> getAllGps_Info();
	
	
}