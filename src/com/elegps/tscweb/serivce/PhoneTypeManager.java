package com.elegps.tscweb.serivce;

import java.util.List;

import com.elegps.tscweb.model.TbPhoneTypeInfo;

public interface PhoneTypeManager {

	/**
	 * ����ͺ���Ϣ
	 * @param sess
	 * @param pfInfo
	 */
	void addPTInfo(TbPhoneTypeInfo ptInfo);
	
	
	/**
	 * �޸��ͺ���Ϣ
	 * @param ptInfo
	 * @return
	 */
	Boolean updatePt(TbPhoneTypeInfo ptInfo);
	
	/**
	 * ����������ѯ
	 * @param id
	 * @return
	 */
	TbPhoneTypeInfo findOneById(int id);
	
	/**
	 * �����ͺŲ�ѯ
	 * @param sess
	 * @param type
	 * @return
	 */
	TbPhoneTypeInfo findOneByTypeAndFlag(String type,int flag);
	
	/**
	 * 
	 * @return �����ͺ���Ϣ
	 */
	String findAllPTInfo(); 
	
	/**
	 * ɾ���ͺ���Ϣ
	 * @param sess
	 * @param pfInfo
	 */
	void deletePTInfo(TbPhoneTypeInfo ptInfo);
	
	/**
	 * ����ɾ���ͺ���Ϣ
	 * @param ptId
	 * @return Boolean
	 */
	Boolean deletePtInfo(String[] ptId);
	
	/**
	 * ��ȡ�ͺ���������
	 * @return
	 */
	int getPtCount(String type);
	
	/**
	 * ��ҳ��ȡ�ͺ���Ϣ�б�
	 * @param type
	 * @return
	 */
	List<TbPhoneTypeInfo> getPtList(int pageNo,int pageSize,String type);
}
