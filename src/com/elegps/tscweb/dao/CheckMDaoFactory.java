package com.elegps.tscweb.dao;

import java.util.List;

import org.hibernate.Session;

import com.elegps.tscweb.model.TbCheckMInfo;
import com.elegps.tscweb.model.TbPFInfo;

/**
 * ��ֵ�ύ�������
 * @author wanglei
 *
 */
public interface CheckMDaoFactory {
	
	/**
	 * ��ӳ�ֵ������Ϣ
	 * @param sess
	 * @param checkInfo
	 */
	void addCMInfo(Session sess,TbCheckMInfo checkInfo);
	
	/**
	 * 
	 * @return ��ѯ���г�ֵ������Ϣ
	 */
	List<TbCheckMInfo> findAllCMInfo(Session sess); 
	
	/**
	 * ������ѯ���г�ֵ������Ϣ(��ҳ��ʾ)
	 * @param sess
	 * @param pageNo
	 * @param pageSize
	 * @param userName
	 * @param resName
	 * @param proStatus
	 * @param roleId
	 * @return list
	 */
	List<TbCheckMInfo> findAllCMInfo(Session sess,int pageNo,int pageSize,String userName,String resName,int proStatus, int roleId,int uId,int cRole); 
	
	/**
	 * ������ѯ ������
	 * @param sess
	 * @param userName
	 * @param resName
	 * @param proStatus
	 * @param roleId
	 * @return count
	 */
	Integer findCMCount(Session sess,String userName,String resName,int proStatus, int roleId,int uId,int cRole);
	
	/**
	 *  ���³�ֵ������Ϣ
	 * @param sess
	 * @param checkInfo
	 */
	void updateCMInfo(Session sess,TbCheckMInfo checkInfo);
	
	/**
	 * id���ҳ�ֵ������Ϣ
	 * @param sess,finId
	 * @return
	 */
	TbCheckMInfo findCMInfoById(Session sess,int finId);
	
	/**
	 * ɾ����ֵ������Ϣ
	 * @param sess
	 * @param checkInfo
	 */
	void deleteCMInfo(Session sess,TbCheckMInfo checkInfo);
}
