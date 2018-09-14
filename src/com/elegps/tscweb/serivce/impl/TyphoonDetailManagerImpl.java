package com.elegps.tscweb.serivce.impl;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.elegps.tscweb.comm.HibernateUtil;
import com.elegps.tscweb.dao.TyphoonDetailDaoFactory;
import com.elegps.tscweb.dao.impl.TyphoonDetailDaoHibernate;
import com.elegps.tscweb.exception.MessageException;
import com.elegps.tscweb.model.hstmodel.TbTyphoonDetail;
import com.elegps.tscweb.serivce.TyphoonDetailManager;

public class TyphoonDetailManagerImpl implements TyphoonDetailManager{
	private TyphoonDetailDaoFactory dao;
	public TyphoonDetailManagerImpl() throws MessageException {
		try {
			dao =new TyphoonDetailDaoHibernate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new MessageException("初始化业务逻辑组件出现异常...");
		}
	}
	@Override
	public void save(TbTyphoonDetail td) {
		Transaction tx=null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			dao.add(sess, td);
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
