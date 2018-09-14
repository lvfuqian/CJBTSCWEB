package com.elegps.tscweb.serivce;

import java.util.List;
import com.elegps.tscweb.model.TbPFInfo;

public interface PFManager {

	/**
	 * ����ײ���Ϣ
	 *
	 * @param pfInfo
	 */
	String addPFInfo(TbPFInfo pfInfo);
	
	/**
	 * 
	 * @return �����ײ���Ϣ
	 */
	List<TbPFInfo> findAllPFInfo();
	
	/**
	 * id�����ײ���Ϣ
	 * @param pfId
	 * @return
	 */
	TbPFInfo findPFInfoById(String pfId);
	
	/**
	 * id�����ײ���Ϣ
	 * @param pfId
	 * @return Boolean
	 */
	Boolean findPFById(String pfId);
	
	/**
	 *  �޸��ײ���Ϣ
	 * 
	 * @param pfInfo
	 * @return Boolean
	 */
	Boolean updatePFInfo(TbPFInfo pfInfo);
	
	/**
	 * ɾ��
	 * @param pfId
	 * @return Boolean
	 */
	Boolean deletePFInfo(String[] pfId);
	
	/**
	 * 
	 * @param how
	 * @return
	 */
	TbPFInfo getPFinfoByHow(double how); 
}
