package com.elegps.tscweb.dao;

import java.util.List;

import org.hibernate.Session;
import com.elegps.tscweb.model.TbPhoneTypeInfo;

public interface PhoneTypeDaoFactory {
	
	/**
	 * ����ͺ���Ϣ
	 * @param sess
	 * @param pfInfo
	 */
	void addPTInfo(Session sess,TbPhoneTypeInfo ptInfo);
	
	/**
	 * �޸��ͺ���Ϣ
	 * @param ptInfo
	 * @return
	 */
	void updatePt(Session sess,TbPhoneTypeInfo ptInfo);
	
	/**
	 * ����������ѯ
	 * @param id
	 * @return
	 */
	TbPhoneTypeInfo findOneById(Session sess,int id);
	
	
	/**
	 * �����ͺŲ�ѯ
	 * @param sess
	 * @param type
	 * @return
	 */
	TbPhoneTypeInfo findOneByTypeAndFlag(Session sess,String type,int flag);
	
	/**
	 * 
	 * @return �����ͺ���Ϣ
	 */
	String findAllPTInfo(Session sess); 
	
	/**
	 * ɾ���ͺ���Ϣ
	 * @param sess
	 * @param pfInfo
	 */
	void deletePTInfo(Session sess,TbPhoneTypeInfo ptInfo);
	
	/**
	 * ��ȡ�ͺ���������
	 * @return
	 */
	int getPtCount(Session sess,String type);
	
	/**
	 * ��ҳ��ȡ�ͺ���Ϣ�б�
	 * @param type
	 * @return
	 */
	List<TbPhoneTypeInfo> getPtList(Session sess,int pageNo,int pageSize,String type);
}
