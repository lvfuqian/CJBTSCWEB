package com.elegps.tscweb.serivce;

import java.util.List;
import com.elegps.tscweb.model.TbCheckMInfo;
import com.elegps.tscweb.model.TbMoneyLog;

/**
 * ��ֵ
 * @author wanglei
 * 2015-4-2
 */
public interface MoneyManager {
	
	/**
	 * ��ӳ�ֵ��¼log
	 * @param mLog
	 * @return Boolean
	 */
	Boolean addMoneyLog(TbMoneyLog  mLog);
	
	/**
	 * ������ѯ�������
	 * @param userName
	 * @param resName
	 * @param proStatus
	 * @param roleId
	 * @return
	 */
	Integer findCMCount(String userName,String resName,int proStatus, int roleId,int uId,int cRole);
	
	/**
	 * ������ѯ�����Ϣ
	 * @param pageNo
	 * @param pageSize
	 * @param userName
	 * @param resName
	 * @param proStatus
	 * @param roleId
	 * @return
	 */
	List<TbCheckMInfo> findAllCMInfo(int pageNo,int pageSize,String userName,String resName,int proStatus, int roleId,int uId,int cRole); 
	
	/**
	 * �ύ��ֵ����
	 * @param check
	 * @return
	 */
	Boolean addCheck(TbCheckMInfo check);
	/**
	 *�޸ĳ�ֵ���루���ͨ����
	 * @param check
	 * @return
	 */
	Boolean updateCheck(TbCheckMInfo check);
	
	/**
	 * id��ѯ�����Ϣ
	 * @param finId
	 * @return
	 */
	TbCheckMInfo getCMInfoById(int finId);

	/**
	 * ��ѯ��ֵ��¼����
	 * @param sess
	 * @param roleId
	 * @param uId
	 * @return
	 */
	Integer getMoneyLogCount(int roleId,int uId,String userName,String dateStart,String dateEnd,int ptype,String pay_num,String teadeNo,String orderNo);
	/**
	 * ��ҳ��ѯ��ֵ��¼
	 * @param sess
	 * @param pageNo
	 * @param pageSize
	 * @param roleId
	 * @param uId
	 * @return
	 */
	List getMoneyLogListByPage(int pageNo, int pageSize,int roleId,int uId,String userName,String dateStart,String dateEnd,int ptype,String pay_num,String teadeNo,String orderNo);
}
