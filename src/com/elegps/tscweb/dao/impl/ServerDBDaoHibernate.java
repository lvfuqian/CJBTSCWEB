package com.elegps.tscweb.dao.impl;

import java.util.List;

import org.hibernate.Session;

import com.elegps.tscweb.dao.DdbDaoFactory;
import com.elegps.tscweb.model.TabSysserverdbinfo;

public class ServerDBDaoHibernate implements DdbDaoFactory<TabSysserverdbinfo> {

	
	public void create(Session session, Object  objects) {

	}

	
	public int executeQuery(Session session, String hql, Object... objects) {
		return 0;
	}

	
	public <T> T getBean(Session session, String hql, Object[] objcts) {
		return null;
	}

	
	public List<TabSysserverdbinfo> listObject(Session session, String hql,
			int pageNO, int pageSize, Object... objects) {
		return null;
	}

	
	public List<TabSysserverdbinfo> listObjectInfo(Session session, String hql,
			int pageNO, int pageSize) {
		return null;
	}

	
	public int toaltCount(Session session, String hql, Object... objects) {
		return 0;
	}

	
	public int update(Session session, String hql, Object... objects) {
		return 0;
	}

	
	public int getMaxId(Session session, Object obj) {
		// TODO Auto-generated method stub
		return 0;
	}

}
