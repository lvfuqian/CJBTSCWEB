package com.elegps.tscweb.serivce.impl;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.elegps.tscweb.comm.HibernateUtil;
import com.elegps.tscweb.dao.GaleHierrrarchyDaoFactory;
import com.elegps.tscweb.dao.impl.GaleHierrarchyDaoHibernate;
import com.elegps.tscweb.exception.MessageException;
import com.elegps.tscweb.model.hstmodel.TbGaleHierrarchy;
import com.elegps.tscweb.serivce.GaleHierrarchyManager;

public class GaleHierrarchyManagerImpl implements GaleHierrarchyManager{
	private GaleHierrrarchyDaoFactory dao;
	public GaleHierrarchyManagerImpl() throws MessageException {
		try {
			dao =new GaleHierrarchyDaoHibernate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new MessageException("初始化业务逻辑组件出现异常...");
		}
	}
	@Override
	public void save(TbGaleHierrarchy gh) {
		Transaction tx=null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			dao.add(sess, gh);
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
