package com.elegps.tscweb.serivce.impl;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.elegps.tscweb.comm.HibernateUtil;
import com.elegps.tscweb.dao.AquaticPriceDaoFactory;
import com.elegps.tscweb.dao.impl.AquaticPriceDaoHibernate;
import com.elegps.tscweb.exception.MessageException;
import com.elegps.tscweb.model.hstmodel.TbAquaticPrice;
import com.elegps.tscweb.serivce.AquaticPriceManager;

public class AquaticPriceManagerImpl implements AquaticPriceManager{
	private AquaticPriceDaoFactory dao;
	public AquaticPriceManagerImpl() throws MessageException {
		try {
			dao =new AquaticPriceDaoHibernate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new MessageException("初始化业务逻辑组件出现异常...");
		}
	}
	@Override
	public void save(TbAquaticPrice ap) {
		Transaction tx=null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			dao.add(sess, ap);
			tx.commit();
		} catch (Exception e) {
			if (null != tx)
				tx.rollback();
			e.printStackTrace();
		}finally {
			HibernateUtil.closeSession();
		}
	}

}
