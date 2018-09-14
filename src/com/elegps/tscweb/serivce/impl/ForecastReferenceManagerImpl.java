package com.elegps.tscweb.serivce.impl;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.elegps.tscweb.comm.HibernateUtil;
import com.elegps.tscweb.dao.ForecastReferenceDaoFactory;
import com.elegps.tscweb.dao.impl.ForecastReferenceDaoHibernate;
import com.elegps.tscweb.exception.MessageException;
import com.elegps.tscweb.model.hstmodel.TbForecastReference;
import com.elegps.tscweb.serivce.ForecastReferenceManager;

public class ForecastReferenceManagerImpl implements ForecastReferenceManager{
	private ForecastReferenceDaoFactory dao;
	public ForecastReferenceManagerImpl() throws MessageException {
		try {
			dao =new ForecastReferenceDaoHibernate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new MessageException("初始化业务逻辑组件出现异常...");
		}
	}
	@Override
	public void save(TbForecastReference fr) {
		Transaction tx=null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			dao.add(sess, fr);
			tx.commit();
		} catch (Exception e) {
			if(tx!=null)
				tx.rollback();
			e.printStackTrace();
		}finally {
			HibernateUtil.closeSession();
		}
	}

}
