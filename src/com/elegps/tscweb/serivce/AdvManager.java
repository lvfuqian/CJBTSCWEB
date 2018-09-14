package com.elegps.tscweb.serivce;

import java.util.List;
import com.elegps.tscweb.model.TbAdvInfo;

public interface AdvManager {

	/**
	 * ��ӹ����Ϣ
	 *
	 * @param advInfo
	 */
	String addAdvInfo(TbAdvInfo advInfo);
	
	/**
	 * 
	 * @return ���й����Ϣ
	 */
	List<TbAdvInfo> findAllAdvInfo();
	
	/**
	 * id���ҹ����Ϣ
	 * @param advId
	 * @return
	 */
	TbAdvInfo findAdvInfoById(String advId);
	
	/**
	 * id���ҹ����Ϣ
	 * @param advId
	 * @return Boolean
	 */
	Boolean findAdvById(String advId);
	
	/**
	 *  �޸Ĺ����Ϣ
	 * 
	 * @param advInfo
	 * @return Boolean
	 */
	Boolean updateAdvInfo(TbAdvInfo advInfo);
	
	/**
	 * ɾ��
	 * @param advId
	 * @return Boolean
	 */
	Boolean deleteAdvInfo(String[] advId);
	
	/**
	 * ������ѯ������
	 * @param title
	 * @param url
	 * @param sendSTime
	 * @param sendETime
	 * @param creatSTime
	 * @param creatETime
	 * @return Integer
	 */
	Integer findAdvCount(String title,String url,String sendSTime,
			String sendETime,String creatSTime,String creatETime,int advType);
	
	/**
	 * ������ѯ�����Ϣ����ҳ��
	 * @param pageNo
	 * @param pageSize
	 * @param title
	 * @param url
	 * @param sendSTime
	 * @param sendETime
	 * @param creatSTime
	 * @param creatETime
	 * @return List<TbAdvInfo>
	 */
	List<TbAdvInfo> findAdvInfoByPage(Integer pageNo,Integer pageSize,String title,String url,
			String sendSTime,String sendETime,String creatSTime,String creatETime,int advType);
}
