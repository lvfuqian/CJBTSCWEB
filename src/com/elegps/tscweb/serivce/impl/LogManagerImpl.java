package com.elegps.tscweb.serivce.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;

import com.elegps.tscweb.comm.HibernateUtil;
import com.elegps.tscweb.dao.LogDaoFactory;
import com.elegps.tscweb.dao.impl.LogDaoHibernate;
import com.elegps.tscweb.exception.MessageException;
import com.elegps.tscweb.model.TbUserLog;
import com.elegps.tscweb.serivce.LogManager;

public class LogManagerImpl implements LogManager {
	private LogDaoFactory logDaoFactory;

	public LogManagerImpl() throws MessageException {
		try {
			logDaoFactory = new LogDaoHibernate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new MessageException("初始化业务逻辑组件出现异常...");
		}
	}

	public void save(TbUserLog userLog) {
		try {
			Session session = HibernateUtil.currentSession();
			logDaoFactory.save(session, userLog);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Integer searchUserLogCount(int userId, int lType, String startDate,
			String endDate) {
		Session session = HibernateUtil.currentSession();
		StringBuilder hql = new StringBuilder();
		hql.append("SELECT COUNT(*) FROM  TbUserLog l,TbUserInfo u WHERE  l.userId=u.userId ");
		if (userId > 0) {
			hql.append(" AND l.userId=" + userId);
		}
		if (lType >= 0) {
			hql.append(" AND l.lType=" + lType);
		}
		if (startDate != null && !"".equals(startDate) && endDate != null
				&& !"".equals(endDate)) {
			hql.append(" AND l.lDate   BETWEEN '" + startDate).append("'  AND '" + endDate).append("'");
		}
		if(startDate!=null && !"".equals(startDate) || endDate== null
				&& "".equals(endDate)){
			hql.append(" AND l.lDate>='" + startDate).append("'");
		}
		if(startDate==null && "".equals(startDate) || endDate!= null
				&& !"".equals(endDate)){
			hql.append(" AND l.lDate<='" + endDate).append("'");
		}
		return logDaoFactory.searchUserLogCount(session, hql.toString());
	}

	public List<TbUserLog> searchUserLogList(int pageNo, int pageSize,
			int userId, int lType, String startDate, String endDate) {
		int fromIdx = (pageNo - 1) * pageSize;
		Session session = HibernateUtil.currentSession();
		List<TbUserLog> listLog = new ArrayList<TbUserLog>();
		TbUserLog userLog = null;
		StringBuilder hql = new StringBuilder();
		hql.append("SELECT u.userName,l.lDate,l.lAddress,l.lType,l.lContent,l.lRemark FROM  TbUserLog l,TbUserInfo u WHERE  l.userId=u.userId ");
		if (userId > 0) {
			hql.append(" AND l.userId=" + userId);
		}
		if (lType >= 0) {
			hql.append(" AND l.lType=" + lType);
		}
		if (startDate != null && !"".equals(startDate) && endDate != null
				&& !"".equals(endDate)) {
			hql.append(" AND l.lDate  BETWEEN  '" + startDate).append(	"' AND '" + endDate).append("'");
		}
		if(startDate!=null && !"".equals(startDate) || endDate== null
				&& "".equals(endDate)){
			hql.append(" AND l.lDate>='" + startDate).append("'");
		}
		if(startDate==null && "".equals(startDate) || endDate!= null
				&& !"".equals(endDate)){
			hql.append(" AND l.lDate<='" + endDate).append("'");
		}
		hql.append("  ORDER BY l.lId DESC");
		List list = logDaoFactory.searchUserLogList(session, hql.toString(),
				fromIdx, pageSize);
		for (int i = 0; i < list.size(); i++) {
			Object[] o = (Object[]) list.get(i);
			userLog = new TbUserLog();
			userLog.setUserName((String) o[0]);
			userLog.setlDate((Date) o[1]);
			userLog.setlAddress((String) o[2]);
			userLog.setlType((Integer) o[3]);
			userLog.setlContent((String) o[4]);
			userLog.setlRemark((String) o[5]);
			listLog.add(userLog);
		}
		return listLog;
	}

}
