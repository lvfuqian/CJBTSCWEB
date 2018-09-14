package com.elegps.tscweb.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.elegps.tscweb.dao.PhoneDaoHibernate;
import com.elegps.tscweb.model.TabPhoneUser;

public class PhoneUserDaoHibernate implements PhoneDaoHibernate<TabPhoneUser> {

	public void create(Session session, Object objects) {
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(objects);
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		}

	}

	public int update(Session session, String hql, Object... objects) {

		return 0;
	}

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

	public List<TabPhoneUser> listObjectInfo(Session session, String hql,
			int pageNO, int pageSize) {
		Query q=session.createQuery(hql);
		if(pageNO>0)
			q.setFirstResult(pageNO);
		if(pageSize>0)
			q.setMaxResults(pageSize);
		
		return q.list();
	}

	public List<TabPhoneUser> listObject(Session session, String hql,
			int pageNO, int pageSize, Object... objects) {
		Query q=session.createQuery(hql);
		for (int i = 0; i < objects.length; i++) {
			q.setParameter(i, objects[i]);
		}
		if(pageNO>0)
			q.setFirstResult(pageNO);
		if(pageSize>0)
			q.setMaxResults(pageSize);
		
		return q.list();
	}

	public int toaltCount(Session session, String hql, Object... objects) {
		Query q=session.createQuery(hql);
		if(objects!=null&&objects.length>0){
			for (int i = 0; i < objects.length; i++) {
				q.setParameter(i, objects[i]);
			}
		}
		Number n=(Number)q.uniqueResult();
		return (n!=null&&n.intValue()>0)?n.intValue():0;
	}

	public TabPhoneUser getBean(Session session, String hql, Object... objects) {
		Query q=session.createQuery(hql);
		if(objects!=null&&objects.length>0){
			for (int i = 0; i < objects.length; i++) {
				q.setParameter(i, objects[i]);
			}
		}
		q.setMaxResults(1);
		return (TabPhoneUser) q.uniqueResult();
	}

	public int getMaxId(Session session, Object obj) {
		try {
			Query q=session.createQuery(obj.toString());
			return (Integer)q.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public String getMaxPK(Session session ,Object obj){
		try {
			Query q=session.createQuery(obj.toString());
			return (String) q.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
