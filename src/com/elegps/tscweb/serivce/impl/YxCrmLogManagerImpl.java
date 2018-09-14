package com.elegps.tscweb.serivce.impl;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.elegps.tscweb.comm.HibernateUtil;
import com.elegps.tscweb.dao.YxCrmLogDaoFactory;
import com.elegps.tscweb.model.TbYxCrmLog;
import com.elegps.tscweb.serivce.YxCrmLogManager;

public class YxCrmLogManagerImpl implements YxCrmLogManager {

	private YxCrmLogDaoFactory dao;
	
	@Override
	public void save(TbYxCrmLog m) {
		Transaction tx = null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			
			dao.save(sess, m);
			tx.commit();
			
		} catch (Exception e) {
			if (null != tx)
				tx.rollback();
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
		}
	}


}
