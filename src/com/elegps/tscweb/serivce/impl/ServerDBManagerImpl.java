package com.elegps.tscweb.serivce.impl;

import java.util.List;

import org.hibernate.Session;

import com.elegps.tscweb.comm.DDBHibernateUtil;
import com.elegps.tscweb.dao.DdbDaoFactory;
import com.elegps.tscweb.dao.impl.DdbDaoHibernate;
import com.elegps.tscweb.model.TabSysconfig;
import com.elegps.tscweb.model.TabSysserverdbinfo;
import com.elegps.tscweb.model.TabSysuserroleId;
import com.elegps.tscweb.serivce.DdbManager;

public class ServerDBManagerImpl implements DdbManager<TabSysserverdbinfo> {
	private DdbDaoFactory<TabSysserverdbinfo> daoFactory;

	public ServerDBManagerImpl() {
		daoFactory = new DdbDaoHibernate();
	}

	public List<TabSysserverdbinfo> getListBean(int pageNo, int pageSize) {
		Session session = DDBHibernateUtil.currentSession();
		String hql = " FROM  TabSysserverdbinfo";
		return daoFactory.listObject(session, hql, -1, -1);
	}

	public List<TabSysserverdbinfo> listAll(int pageNo, int pageSize,
			String str, String msid,String loginid) {
		Session session = DDBHibernateUtil.currentSession();
		int fromIdx=(pageNo-1)*pageSize;
		String hql = " FROM  TabSysserverdbinfo";
		return daoFactory.listObject(session, hql, fromIdx, pageSize);
	}

	public void create(Object objects) {
		Session session=DDBHibernateUtil.currentSession();
		daoFactory.create(session, objects);
	}

	public void delete(int id) {

	}

	public TabSysserverdbinfo getBean(String hql, Object... objects) {
		Session session = DDBHibernateUtil.currentSession();
		return (TabSysserverdbinfo) daoFactory.getBean(session, hql, objects);
	}
	public TabSysuserroleId getRoleBean(String hql, Object... objects) {
		Session session = DDBHibernateUtil.currentSession();
		return (TabSysuserroleId) daoFactory.getBean(session, hql, objects);
	}

	public List<TabSysserverdbinfo> getBeanTotal(Object... objects) {
		return null;
	}

	public TabSysserverdbinfo getBean(int id) {
		Session session = DDBHibernateUtil.currentSession();
		String hql = " FROM  TabSysserverdbinfo WHERE  sId=?";
		return (TabSysserverdbinfo) daoFactory.getBean(session, hql, id);
	}

	public List<TabSysserverdbinfo> getListBean(int pageNo, int pageSize,
			String userName, String msId) {
		Session session = DDBHibernateUtil.currentSession();
		StringBuilder hql = new StringBuilder(" FROM  TabSysserverdbinfo ");
		if (userName != null && !"".equals(userName)) {
			hql.append("");
		}
		if (userName != null && !"".equals(userName)) {
			return daoFactory.listObject(session, hql.toString(), pageNo,
					pageSize, userName, msId);
		} else {
			return daoFactory.listObject(session, hql.toString(), -1, -1);
		}
	}

	public int getMaxId(Object obj) {
		return 0;
	}

	public int totalCount(TabSysconfig objects) {
		return 0;
	}

	public int update(String hql, Object... objects) {
		Session session=DDBHibernateUtil.currentSession();
		return daoFactory.executeQuery(session, hql, objects);
	}

	public int totalCount(String userName, String msid,String loginid) {
		Session session=DDBHibernateUtil.currentSession();
		String hql = "SELECT COUNT(*) FROM  TabSysserverdbinfo WHERE 1=? ";
		return daoFactory.toaltCount(session, hql, 1);
	}

	public List<TabSysserverdbinfo> listRole(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TabSysserverdbinfo> getListBean(int pageNo, int pageSize,
			StringBuffer msId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TabSysserverdbinfo> listAll(int pageNo, int pageSize,
			String userName, String msid, String loginid, StringBuffer msId2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int totalCount(String userName, String msid, String loginid,
			StringBuffer msId2) {
		// TODO Auto-generated method stub
		return 0;
	}

}
