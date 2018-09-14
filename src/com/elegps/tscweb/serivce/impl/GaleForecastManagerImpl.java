package com.elegps.tscweb.serivce.impl;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.elegps.tscweb.comm.HibernateUtil;
import com.elegps.tscweb.dao.GaleForecastDaoFactory;
import com.elegps.tscweb.dao.impl.GaleForecastDaoHibernate;
import com.elegps.tscweb.exception.MessageException;
import com.elegps.tscweb.model.hstmodel.TbGaleForecast;
import com.elegps.tscweb.serivce.GaleForecastManager;

public class GaleForecastManagerImpl implements GaleForecastManager{
	private GaleForecastDaoFactory dao;
	public GaleForecastManagerImpl() throws MessageException {
		try {
			dao =new GaleForecastDaoHibernate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new MessageException("初始化业务逻辑组件出现异常...");
		}
	}
	@Override
	public void save(TbGaleForecast gf) {
		Transaction tx=null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			dao.add(sess, gf);
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
