package com.elegps.tscweb.serivce.impl;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.elegps.tscweb.comm.HibernateUtil;
import com.elegps.tscweb.dao.FisheryForecastDaoFactory;
import com.elegps.tscweb.dao.impl.FisheryForecastDaoHibernate;
import com.elegps.tscweb.exception.MessageException;
import com.elegps.tscweb.model.hstmodel.TbFisheryForecast;
import com.elegps.tscweb.serivce.FisheryForecastManager;

public class FisheryForecastManagerImpl implements FisheryForecastManager{
	private FisheryForecastDaoFactory dao;
	public FisheryForecastManagerImpl() throws MessageException {
		try {
			dao =new FisheryForecastDaoHibernate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new MessageException("初始化业务逻辑组件出现异常...");
		}
	}
	@Override
	public void save(TbFisheryForecast ff) {
		Transaction tx=null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			dao.add(sess, ff);
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
