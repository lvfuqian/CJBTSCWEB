package com.elegps.tscweb.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.elegps.tscweb.comm.SQLHibernateUtil;
import com.elegps.tscweb.dao.TrafficDAO;

public class TrafficDAOImpl<T> implements TrafficDAO<T> {
	private static Session session = null;

	public void create(Object object) {
		session = SQLHibernateUtil.currentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(object);
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		}
	}

	public int executeQuery(String hql, Object... object) {
		session = SQLHibernateUtil.currentSession();
		Transaction tx = null;
		Number n = null;
		try {
			tx = session.beginTransaction();
			Query q = session.createQuery(hql);
			for (int i = 0; i < object.length; i++) {
				q.setParameter(i, object[i]);
			}
			n = (Number) q.executeUpdate();
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		}
		return (n != null) ? n.intValue() : 0;
	}

	public <T> T getBean(String hql, Object... objects) {
		session = SQLHibernateUtil.currentSession();
		Query q = session.createQuery(hql);
		for (int i = 0; i < objects.length; i++) {
			q.setParameter(i, objects[i]);
		}
		q.setMaxResults(1);
		return (T) q.uniqueResult();
	}

	public List<T> listAll(String hql, int pageNo, int pageSize,
			Object... objects) {
		session = SQLHibernateUtil.currentSession();
		session.clear();
		Query q = session.createQuery(hql);
		if (objects.length > 0) {
			for (int i = 0; i < objects.length; i++) {
				q.setParameter(i, objects[i]);
			}
		}
		if (pageNo > 0)
			q.setFirstResult(pageNo);
		if (pageSize > 0)
			q.setMaxResults(pageSize);
		return q.list();
	}

	public List<T> listAll(String hql, int pageNo, int pageSize) {
		session = SQLHibernateUtil.currentSession();
		session.clear();
		Query q = session.createQuery(hql);
		if (pageNo > 0)
			q.setFirstResult(pageNo);
		if (pageSize > 0)
			q.setMaxResults(pageSize);
		return q.list();
	}

	public int totalCount(String hql, Object... objects) {
		session = SQLHibernateUtil.currentSession();
		Query q = session.createQuery(hql);
		for (int i = 0; i < objects.length; i++) {
			q.setParameter(i, objects[i]);
		}
		Number n = (Number) q.uniqueResult();
		return (n != null) ? n.intValue() : 0;
	}

	public int totalCount(String hql) {
		session = SQLHibernateUtil.currentSession();
		Query q = session.createQuery(hql);
		Number n = (Number) q.uniqueResult();
		return (n != null) ? n.intValue() : 0;
	}
}
