package com.elegps.tscweb.dao;

import java.util.List;
import org.hibernate.Session;
import com.elegps.tscweb.model.TbMoneyLog;

/**
 * 充值日志log
 * @author wanglei
 * 2015-4-2
 */
public interface MoneyLogDaoFactory {
	
	/**
	 * 添加充值记录
	 * @param sess
	 * @param mLog
	 */
	void addMoneyLog(Session sess,TbMoneyLog mLog);
	
	/**
	 * 分页查询充值记录
	 * @param sess
	 * @param roleId
	 * @return
	 */
	List getMoneyLogListByPage(Session sess,int pageNo, int pageSize,int roleId,int uId,String userName,String dateStart,String dateEnd,int ptype,String pay_num,String teadeNo,String orderNo);
	
	/**
	 * 充值记录数量
	 * @param sess
	 * @param roleId
	 * @return
	 */
	Integer getMoneyLogCount(Session sess,int roleId,int uId,String userName,String dateStart,String dateEnd,int ptype,String pay_num,String teadeNo,String orderNo);
}
