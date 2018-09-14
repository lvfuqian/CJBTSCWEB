package com.elegps.tscweb.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.elegps.tscweb.dao.MsDaoControlFactory;
import com.elegps.tscweb.model.ControlInfo;
import com.elegps.tscweb.model.TbMsControlInfo;

public class MsDaoControlHibernate implements MsDaoControlFactory {

	public void add(Session session, TbMsControlInfo tmc) {
		try {
			session.save(tmc);
			session.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public int executeHql(Session session, String hql, Object... obj) {
		Query q = session.createQuery(hql);
		if (obj != null) {
			for (int i = 0; i < obj.length; i++) {
				q.setParameter(i, obj[i]);
			}
		}
		Number n = (Number) q.executeUpdate();
		return (n != null && n.intValue() > 0) ? n.intValue() : 0;
	}

	public int executeControlCount(Session session, String hql, Object... obj) {
		Query q = session.createSQLQuery(hql);
		Number n = (Number) q.uniqueResult();
		return (n != null && n.intValue() > 0) ? n.intValue() : 0;
	}

	public List findList(Session session, String hql, int pageNo, int pageSize,
			Object... obj) {
		try {
			int offset = (pageNo - 1) * pageSize;
			Query q = session.createSQLQuery(hql);
			if (offset > 0)
				q.setFirstResult(offset);
			if (pageSize > 0)
				q.setMaxResults(pageSize);
			session.flush();
			List list = q.list();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public TbMsControlInfo getControl(Session session, String hql,	Object... obj) {
		try {
			Query q = session.createQuery(hql);
			if (obj != null) {
				for (int i = 0; i < obj.length; i++) {
					q.setParameter(i, obj[i]);
				}
			}
			return (TbMsControlInfo) q.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
