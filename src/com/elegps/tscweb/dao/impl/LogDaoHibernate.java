package com.elegps.tscweb.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.elegps.tscweb.dao.LogDaoFactory;
import com.elegps.tscweb.model.TbUserLog;

public class LogDaoHibernate implements LogDaoFactory {

	public Integer searchUserLogCount(Session session, String hql,
			Object... objects) {
		Query q = session.createQuery(hql);
		for (int i = 0; i < objects.length; i++) {
			q.setParameter(i, objects[i]);
		}
		Number n = (Number) q.uniqueResult();
		return (n != null && n.intValue() > 0) ? n.intValue() : 0;
	}

	public List searchUserLogList(Session session, String hql, int pageNo,
			int pageSize, Object... objects) {
		Query q = session.createQuery(hql);
		for (int i = 0; i < objects.length; i++) {
			q.setParameter(i, objects[i]);
		}
		if (pageNo > 0) {
			q.setFirstResult(pageNo);
		}
		if (pageSize > 0) {
			q.setMaxResults(pageSize);
		}
		return q.list();
	}

	public void save(Session session, TbUserLog userLog) {
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(userLog);
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		}
	}

}
