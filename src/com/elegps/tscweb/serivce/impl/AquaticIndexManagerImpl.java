package com.elegps.tscweb.serivce.impl;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.elegps.tscweb.comm.HibernateUtil;
import com.elegps.tscweb.dao.AquaticIndexDaoFactory;
import com.elegps.tscweb.dao.impl.AquaticIndexDaoHibernate;
import com.elegps.tscweb.exception.MessageException;
import com.elegps.tscweb.model.hstmodel.TbAquaticIndex;
import com.elegps.tscweb.serivce.AquaticIndexManager;

public class AquaticIndexManagerImpl implements  AquaticIndexManager{
	private AquaticIndexDaoFactory dao;
	public AquaticIndexManagerImpl() throws MessageException {
		try {
			dao =new AquaticIndexDaoHibernate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new MessageException("初始化业务逻辑组件出现异常...");
		}
	}
	@Override
	public void save(TbAquaticIndex ai) {
		Transaction tx=null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			dao.add(sess, ai);
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
