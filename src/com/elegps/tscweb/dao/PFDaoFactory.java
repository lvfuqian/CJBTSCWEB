package com.elegps.tscweb.dao;

import java.util.List;

import org.hibernate.Session;
import com.elegps.tscweb.model.TbPFInfo;

public interface PFDaoFactory {
	
	/**
	 * ����ײ���Ϣ
	 * @param sess
	 * @param pfInfo
	 */
	void addPFInfo(Session sess,TbPFInfo pfInfo);
	
	/**
	 * 
	 * @return �����ײ���Ϣ
	 */
	List<TbPFInfo> findAllPFInfo(Session sess); 
	
	/**
	 *  �޸��ײ���Ϣ
	 * @param sess
	 * @param pfInfo
	 */
	void updatePFInfo(Session sess,TbPFInfo pfInfo);
	
	/**
	 * id�����ײ���Ϣ
	 * @param sess,pfId
	 * @return
	 */
	TbPFInfo findPFInfo(Session sess,int pfId);
	
	/**
	 * ɾ��������Ϣ
	 * @param sess
	 * @param pfInfo
	 */
	void deletePFInfo(Session sess,TbPFInfo pfInfo);
	
	
	/**
	 * ͨ������ѯ
	 * @param sess
	 * @param how
	 * @return
	 */
	TbPFInfo findPFinfoByHow(Session sess,Double how);
}
