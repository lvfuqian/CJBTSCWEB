package com.elegps.tscweb.dao;

import java.util.List;
import org.hibernate.Session;
import com.elegps.tscweb.model.TbMoneyLog;

/**
 * ��ֵ��־log
 * @author wanglei
 * 2015-4-2
 */
public interface MoneyLogDaoFactory {
	
	/**
	 * ��ӳ�ֵ��¼
	 * @param sess
	 * @param mLog
	 */
	void addMoneyLog(Session sess,TbMoneyLog mLog);
	
	/**
	 * ��ҳ��ѯ��ֵ��¼
	 * @param sess
	 * @param roleId
	 * @return
	 */
	List getMoneyLogListByPage(Session sess,int pageNo, int pageSize,int roleId,int uId,String userName,String dateStart,String dateEnd,int ptype,String pay_num,String teadeNo,String orderNo);
	
	/**
	 * ��ֵ��¼����
	 * @param sess
	 * @param roleId
	 * @return
	 */
	Integer getMoneyLogCount(Session sess,int roleId,int uId,String userName,String dateStart,String dateEnd,int ptype,String pay_num,String teadeNo,String orderNo);
}
