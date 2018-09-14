package com.elegps.tscweb.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.elegps.tscweb.dao.DdbDaoFactory;

public class DdbDaoHibernate implements DdbDaoFactory {

	public void create(Session session, Object objects) {
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(objects);
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		}
	}

	/**
	 * 执行指定hql
	 */
	public int executeQuery(Session session, String hql, Object... objects) {
		Transaction tx = null;
		Number n = null;
		try {
			tx = session.beginTransaction();
			Query q = session.createQuery(hql);
			for (int i = 0; i < objects.length; i++) {
				q.setParameter(i, objects[i]);
			}
			n = (Number) q.executeUpdate();
			tx.commit();
			return n.intValue();
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * 查询对象
	 */
	public List<Object> listObject(Session session, String hql, int pageNO,
			int pageSize, Object... objects) {
		Query q = session.createQuery(hql);
		for (int i = 0; i < objects.length; i++) {
			q.setParameter(i, objects[i]);
		}
		if (pageNO > 0) {
			q.setFirstResult(pageNO);
		}
		if (pageSize > 0) {
			q.setMaxResults(pageSize);
		}
		return q.list();
	}

	/**
	 * 查询对象
	 */
	public Object getBean(Session session, String hql, Object... objects) {
		Query q = session.createQuery(hql);
		for (int i = 0; i < objects.length; i++) {
			q.setParameter(i, objects[i]);
		}
		q.setMaxResults(1);
		return q.uniqueResult();
	}

	/**
	 * 统计条数
	 */
	public int toaltCount(Session session, String hql, Object... objects) {
		Query q = session.createQuery(hql);
		if (objects != null && objects.length > 0) {
			for (int i = 0; i < objects.length; i++) {
				q.setParameter(i, objects[i]);
			}
		}
		Number n = (Number) q.uniqueResult();
		return (n != null) ? n.intValue() : 0;
	}

	/**
	 * 修改对象
	 */
	public int update(Session session, String hql, Object... objects) {
		Transaction tx = null;
		Number n = null;
		try {
			tx = session.beginTransaction();
			Query q = session.createQuery(hql);
			for (int i = 0; i < objects.length; i++) {
				q.setParameter(i, objects[i]);
			}
			n = (Number) q.executeUpdate();
			tx.commit();
			return n.intValue();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		}
		return 0;
	}

	public List listObjectInfo(Session session, String hql, int pageNO,
			int pageSize) {
		Query q=session.createQuery(hql);
		if (pageNO > 0) {
			q.setFirstResult(pageNO);
		}
		if (pageSize > 0) {
			q.setMaxResults(pageSize);
		}
		return q.list();
	}

	public int getMaxId(Session session, Object obj) {
		String hql = "SELECT MAX(userId) FROM  TabSysusersinfo";
		Query q = session.createQuery(hql);
		q.setMaxResults(1);
		Number n = (Number) q.uniqueResult();
		return (n != null) ? n.intValue() : 0;
	}

}
