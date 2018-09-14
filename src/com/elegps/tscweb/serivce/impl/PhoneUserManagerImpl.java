package com.elegps.tscweb.serivce.impl;

import java.util.List;

import org.hibernate.Session;

import com.elegps.tscweb.comm.DDBHibernateUtil;
import com.elegps.tscweb.dao.PhoneDaoHibernate;
import com.elegps.tscweb.dao.impl.PhoneUserDaoHibernate;
import com.elegps.tscweb.model.TabPhoneUser;
import com.elegps.tscweb.serivce.PhoneUserManage;

public class PhoneUserManagerImpl implements PhoneUserManage {
	private PhoneDaoHibernate<TabPhoneUser> daoFactory = null;

	public PhoneUserManagerImpl() {
		try {
			daoFactory = new PhoneUserDaoHibernate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void create(TabPhoneUser t) {
		try {
			Session session = DDBHibernateUtil.currentSession();
			daoFactory.create(session, t);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public int update(TabPhoneUser t) {
		Session session = DDBHibernateUtil.currentSession();
		StringBuilder update = new StringBuilder("UPDATE TabPhoneUser t  SET ");
		update.append(" t.userName=?,t.userSex=?,t.userPwd=?,t.userIdCard=?,t.userMobile=?,t.userAddress=?  WHERE t.userId=?");
		return daoFactory.executeQuery(session, update.toString(),
				t.getUserName(), t.getUserSex(), t.getUserPwd(),
				t.getUserIdCard(), t.getUserMobile(), t.getUserAddress(),
				t.getUserId());
	}

	public void delete(TabPhoneUser t) {
		Session session = DDBHibernateUtil.currentSession();
		String hql = " DELETE  TabPhoneUser t WHERE t.userId=? ";
		daoFactory.executeQuery(session, hql, t.getUserId());
	}

	public int update(String hql, Object... objects) {
		Session session = DDBHibernateUtil.currentSession();
		return daoFactory.executeQuery(session, hql, objects);
	}

	public void delete(Object... objects) {

	}

	public int total(String userName, String userSex, String userMobile) {
		Session session = DDBHibernateUtil.currentSession();
		StringBuilder hql = new StringBuilder(
				"SELECT COUNT(*) FROM TabPhoneUser t WHERE  1=1 ");
		if (userName != null && !"".equals(userName)) {
			hql.append(" AND t.userName  like '%" + userName + "%'");
		}
		if (userSex != null && !"".equals(userSex)) {
			hql.append(" AND 	t.userSex=" + Integer.parseInt(userSex));
		}
		if (userMobile != null && !"".equals(userMobile)) {
			hql.append(" AND t.userMobile like '%" + userMobile + "%'");
		}
		session.clear();
		return daoFactory.toaltCount(session, hql.toString(),null);
	}

	public List<TabPhoneUser> listAll(int pageNo, int pageSize,
			String userName, String userSex, String userMobile) {
		int formIndex = (pageNo - 1) * pageSize;
		Session session = DDBHibernateUtil.currentSession();
		StringBuilder hql = new StringBuilder(
				" FROM TabPhoneUser t WHERE  1=1 ");
		if (userName != null && !"".equals(userName)) {
			hql.append(" AND t.userName  like '%" + userName + "%'");
		}
		if (userSex != null && !"".equals(userSex)) {
			hql.append(" AND 	t.userSex=" + Integer.parseInt(userSex));
		}
		if (userMobile != null && !"".equals(userMobile)) {
			hql.append(" AND t.userMobile like '%" + userMobile + "%'");
		}
		session.clear();
		return daoFactory.listObject(session, hql.toString(), formIndex,
				pageSize);
	}

	public int total(Object... objects) {
		return 0;
	}

	public List<TabPhoneUser> listAll(int pageNo, int pageSize, Object... obj) {
		return null;
	}

	public TabPhoneUser getBeanInfo(String PK) {
		Session session = DDBHibernateUtil.currentSession();
		String hql = " FROM  TabPhoneUser  t  WHERE t.userId=?";
		return (TabPhoneUser) daoFactory.getBean(session, hql, PK);
	}

	public String getPrimaryKey() {
		Session session=DDBHibernateUtil.currentSession();
		String hql="SELECT MAX(userId) FROM TabPhoneUser";
		return daoFactory.getMaxPK(session, hql);
	}

}
