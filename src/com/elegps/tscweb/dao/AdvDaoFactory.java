package com.elegps.tscweb.dao;

import java.util.List;

import org.hibernate.Session;

import com.elegps.tscweb.model.TbAdvInfo;


/**
 * �����Ϣ
 * @author wanglei
 *
 */
public interface AdvDaoFactory {
	
	/**
	 * ��ӹ����Ϣ
	 * @param sess
	 * @param advInfo
	 */
	void addAdvInfo(Session sess,TbAdvInfo advInfo);
	
	/**
	 * 
	 * @return ��ѯ���й����Ϣ
	 */
	List<TbAdvInfo> findAllAdvInfo(Session sess); 
	
	/**
	 * ������ѯ���й����Ϣ(��ҳ��ʾ)
	 * @param sess
	 * @param pageNo
	 * @param pageSize
	 * @param userId
	 * @param isSend
	 * @param sendSTime
	 * @param sendETime
	 * @param creatSTime
	 * @return creatETime
	 */
	List<TbAdvInfo> findAllAdvInfo(Session sess,Integer pageNo,Integer pageSize,String title,String url,String sendSTime,String sendETime,String creatSTime,String creatETime,int advType); 
	
	/**
	 * ������ѯ ������
	 * @param sess
	 * @param userId
	 * @param isSend
	 * @param sendSTime
	 * @param sendETime
	 * @param creatSTime
	 * @return creatETime
	 */
	Integer findAdvCount(Session sess,String title,String url,String sendSTime,String sendETime,String creatSTime,String creatETime,int advType);
	
	/**
	 *  ���¹����Ϣ
	 * @param sess
	 * @param advInfo
	 */
	void updateAdvInfo(Session sess,TbAdvInfo advInfo);
	
	/**
	 * id���ҹ����Ϣ
	 * @param sess,AdvId
	 * @return
	 */
	TbAdvInfo findAdvInfoById(Session sess,int AdvId);
	
	/**
	 * ɾ�������Ϣ
	 * @param sess
	 * @param advInfo
	 */
	void deleteAdvInfo(Session sess,TbAdvInfo advInfo);
}
