package com.elegps.tscweb.serivce.impl;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.elegps.tscweb.comm.HibernateUtil;
import com.elegps.tscweb.dao.TyphoonListDaoFactory;
import com.elegps.tscweb.dao.impl.TyphoonListDaoHibernate;
import com.elegps.tscweb.exception.MessageException;
import com.elegps.tscweb.model.hstmodel.TbTyphoonList;
import com.elegps.tscweb.serivce.TyphoonListManager;

public class TyphoonListManagerImpl implements TyphoonListManager{
	private TyphoonListDaoFactory dao;
	public TyphoonListManagerImpl() throws MessageException {
		try {
			dao =new TyphoonListDaoHibernate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new MessageException("初始化业务逻辑组件出现异常...");
		}
	}
	@Override
	public void save(TbTyphoonList tl) {
		Transaction tx=null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			dao.add(sess, tl);
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
