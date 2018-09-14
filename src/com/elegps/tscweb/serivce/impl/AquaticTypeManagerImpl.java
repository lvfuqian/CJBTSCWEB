package com.elegps.tscweb.serivce.impl;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.elegps.tscweb.comm.HibernateUtil;
import com.elegps.tscweb.dao.AquaticTypeDaoFactory;
import com.elegps.tscweb.dao.impl.AquaticTypeDaoHibernate;
import com.elegps.tscweb.exception.MessageException;
import com.elegps.tscweb.model.hstmodel.TbAquaticType;
import com.elegps.tscweb.serivce.AquaticTypeManager;

public class AquaticTypeManagerImpl implements  AquaticTypeManager{
	private AquaticTypeDaoFactory dao;
	public AquaticTypeManagerImpl() throws MessageException {
		try {
			dao =new AquaticTypeDaoHibernate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new MessageException("初始化业务逻辑组件出现异常...");
		}
	}
	@Override
	public void save(TbAquaticType at) {
		Transaction tx=null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			dao.add(sess, at);
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
