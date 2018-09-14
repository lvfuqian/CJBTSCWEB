package com.elegps.tscweb.serivce.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.elegps.tscweb.comm.HibernateUtil;
import com.elegps.tscweb.dao.PFDaoFactory;
import com.elegps.tscweb.dao.impl.PackageFeeDaoHibernate;
import com.elegps.tscweb.exception.MessageException;
import com.elegps.tscweb.model.TbPFInfo;
import com.elegps.tscweb.model.TbUserInfo;
import com.elegps.tscweb.serivce.PFManager;

public class PFManagerImpl implements PFManager{

	private PFDaoFactory pfDao;
	public PFManagerImpl() throws MessageException {
		try {
			pfDao = new PackageFeeDaoHibernate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new MessageException("初始化业务逻辑组件出现异常...");
		}
	}

	@Override
	public String addPFInfo(TbPFInfo pfInfo) {
		Transaction tx=null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			Date dateime=new Date();
			if(pfInfo!=null){
				pfDao.addPFInfo(sess, pfInfo);
				tx.commit();
				return pfInfo.getPfId().toString();
			}
			else{
				tx.commit();
				return null;
			}
		} catch (Exception e) {
			if (null != tx)
				tx.rollback();
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
		}
		return null;
	}

	@Override
	public Boolean deletePFInfo(String[] pfId) {
		Transaction tx = null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			
			for (int i = 0; i < pfId.length; i++) {
				int emp =Integer.parseInt( pfId[i]);
				TbPFInfo pf=pfDao.findPFInfo(sess,emp);
				if (pf != null) {
					pfDao.deletePFInfo(sess, pf);
				}
			}
			tx.commit();
			return true;
		} catch (Exception e) {
			if (null != tx)
				tx.rollback();
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
		}
		return false;
	}

	@Override
	public List<TbPFInfo> findAllPFInfo() {
		// TODO Auto-generated method stub
		Transaction tx = null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			List<TbPFInfo> pf = pfDao.findAllPFInfo(sess);
			if (pf != null && pf.size() > 0) {
				tx.commit();
				return pf;
			} else {
				tx.commit();
				return null;
			}
		} catch (Exception e) {
			if (null != tx)
				tx.rollback();
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
		}
		return null;
	}

	@Override
	public Boolean updatePFInfo(TbPFInfo pfInfo) {
		// TODO Auto-generated method stub
		Transaction tx = null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
				pfDao.updatePFInfo(sess, pfInfo);
			tx.commit();
			return true;
		} catch (Exception e) {
			if (null != tx)
				tx.rollback();
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
		}
		return false;
	}

	@Override
	public TbPFInfo findPFInfoById(String pfId) {
		Transaction tx = null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			TbPFInfo pf = pfDao.findPFInfo(sess, Integer.parseInt(pfId));
			if (pf != null) {
				tx.commit();
				return pf;
			} else {
				tx.commit();
				return null;
			}
		} catch (Exception e) {
			if (null != tx)
				tx.rollback();
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
		}
		return null;
	}

	@Override
	public Boolean findPFById(String pfId) {
		// TODO Auto-generated method stub
		Transaction tx = null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			TbPFInfo pf = pfDao.findPFInfo(sess, Integer.parseInt(pfId));
			if (pf != null) {
				tx.commit();
				return true;
			} else {
				tx.commit();
				return false;
			}
		} catch (Exception e) {
			if (null != tx)
				tx.rollback();
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
		}
		return false;
	}

	@Override
	public TbPFInfo getPFinfoByHow(double how) {
		// TODO Auto-generated method stub
		Transaction tx = null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			TbPFInfo pf = pfDao.findPFinfoByHow(sess, how);
			if (pf != null) {
				tx.commit();
				return pf;
			} else {
				tx.commit();
				return null;
			}
		} catch (Exception e) {
			if (null != tx)
				tx.rollback();
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
		}
		return null;
	}


}
