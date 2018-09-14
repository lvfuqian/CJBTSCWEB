package com.elegps.tscweb.serivce.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.elegps.tscweb.comm.HibernateUtil;
import com.elegps.tscweb.dao.PhoneTypeDaoFactory;
import com.elegps.tscweb.dao.impl.PhoneTypeDaoHibernate;
import com.elegps.tscweb.exception.MessageException;
import com.elegps.tscweb.model.TbPFInfo;
import com.elegps.tscweb.model.TbPhoneTypeInfo;
import com.elegps.tscweb.serivce.PhoneTypeManager;

public class PhoneTypeManagerImpl implements PhoneTypeManager{

	private PhoneTypeDaoFactory ptDao;
	public PhoneTypeManagerImpl() throws MessageException {
		try {
			ptDao = new PhoneTypeDaoHibernate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new MessageException("初始化业务逻辑组件出现异常...");
		}
	}
	@Override
	public void addPTInfo(TbPhoneTypeInfo ptInfo) {
		Transaction tx=null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();

			ptDao.addPTInfo(sess, ptInfo);
			tx.commit();

		} catch (Exception e) {
			if (null != tx)
				tx.rollback();
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
		}
		
	}
	@Override
	public TbPhoneTypeInfo findOneByTypeAndFlag(String type,int flag) {
		Transaction tx = null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			TbPhoneTypeInfo pt = ptDao.findOneByTypeAndFlag(sess, type,flag);
			if (pt != null) {
				tx.commit();
				return pt;
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
	public String findAllPTInfo() {
		Transaction tx = null;
		try {
			
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			String typeList = ptDao.findAllPTInfo(sess);
			tx.commit();
			return typeList;
			
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
	public void deletePTInfo(TbPhoneTypeInfo ptInfo) {
		Transaction tx=null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			
			ptDao.deletePTInfo(sess, ptInfo);
			tx.commit();

		} catch (Exception e) {
			if (null != tx)
				tx.rollback();
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
		}
	}
	@Override
	public int getPtCount(String type) {
		Transaction tx = null;
		try {
			
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			int typeCount = ptDao.getPtCount(sess, type);
			tx.commit();
			return typeCount;
			
		} catch (Exception e) {
			if (null != tx)
				tx.rollback();
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
		}
		return 0;
	}
	@Override
	public List<TbPhoneTypeInfo> getPtList(int pageNo, int pageSize, String type) {
		Transaction tx = null;
		try {
			
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			List<TbPhoneTypeInfo> list = ptDao.getPtList(sess, pageNo, pageSize, type);
			tx.commit();
			return list;
			
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
	public Boolean updatePt(TbPhoneTypeInfo ptInfo) {
		Transaction tx = null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
				if(ptInfo!=null){
					ptDao.updatePt(sess, ptInfo);
				}else{
					tx.commit();
					return false;
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
	public TbPhoneTypeInfo findOneById(int id) {
		Transaction tx = null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			TbPhoneTypeInfo pt = ptDao.findOneById(sess, id);
			if (pt != null) {
				tx.commit();
				return pt;
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
	public Boolean deletePtInfo(String[] ptId) {
		Transaction tx = null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			
			for (int i = 0; i < ptId.length; i++) {
				int emp =Integer.parseInt( ptId[i]);
				TbPhoneTypeInfo pt=ptDao.findOneById(sess, emp);
				if (pt != null) {
					ptDao.deletePTInfo(sess, pt);
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

}
