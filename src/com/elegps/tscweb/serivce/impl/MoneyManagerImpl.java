package com.elegps.tscweb.serivce.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.elegps.tscweb.comm.HibernateUtil;
import com.elegps.tscweb.dao.CheckMDaoFactory;
import com.elegps.tscweb.dao.MoneyLogDaoFactory;
import com.elegps.tscweb.dao.impl.CheckMDaoHibernate;
import com.elegps.tscweb.dao.impl.MoneyLogDaoHibernate;
import com.elegps.tscweb.exception.MessageException;
import com.elegps.tscweb.model.TbCheckMInfo;
import com.elegps.tscweb.model.TbEnterpriseInfo;
import com.elegps.tscweb.model.TbMoneyLog;
import com.elegps.tscweb.serivce.MoneyManager;

public class MoneyManagerImpl implements MoneyManager {

	private MoneyLogDaoFactory mlogDao;
	private CheckMDaoFactory checkMDao;
	public MoneyManagerImpl() throws MessageException {
		try {
			mlogDao = new MoneyLogDaoHibernate();
			checkMDao = new CheckMDaoHibernate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new MessageException("初始化业务逻辑组件出现异常...");
		}
	}
	
	@Override
	public Boolean addMoneyLog(TbMoneyLog mLog) {
		Transaction tx = null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			if(mLog != null){
				mlogDao.addMoneyLog(sess, mLog);
				tx.commit();
				return true;
			}
			else{
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
	public List<TbCheckMInfo> findAllCMInfo(int pageNo, int pageSize,
			String userName, String resName, int proStatus, int roleId,int uId,int cRole) {
		// TODO Auto-generated method stub
		Transaction tx=null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			List ml = checkMDao.findAllCMInfo(sess, pageNo, pageSize, userName, resName, proStatus, roleId, uId, cRole);
			if (ml != null && ml.size() > 0) {
				List<TbCheckMInfo> result = new ArrayList<TbCheckMInfo>();
				for (Object obj : ml) {
					TbCheckMInfo me = (TbCheckMInfo) obj;
					result.add(me);
				}
				tx.commit();
				return result;
			}else{
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
	public Integer findCMCount(String userName, String resName, int proStatus,
			int roleId,int uId,int cRole) {
		// TODO Auto-generated method stub
		Transaction tx = null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			int count = checkMDao.findCMCount(sess, userName, resName, proStatus, roleId, uId, cRole);
			if(count!= 0){
				tx.commit();
				return count;
			}else{
				tx.commit();
				return 0;
			}
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
	public Boolean addCheck(TbCheckMInfo check) {
		// TODO Auto-generated method stub
		Transaction tx = null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			if(check != null){
				checkMDao.addCMInfo(sess, check);
				tx.commit();
				return true;
			}
			else{
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
	public Boolean updateCheck(TbCheckMInfo check) {
		// TODO Auto-generated method stub
		Transaction tx = null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			if(check != null){
				checkMDao.updateCMInfo(sess, check);
				tx.commit();
				return true;
			}
			else{
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
	public TbCheckMInfo getCMInfoById(int finId) {
		// TODO Auto-generated method stub
		Transaction tx=null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			
			TbCheckMInfo cminfo = checkMDao.findCMInfoById(sess, finId);
			if (cminfo != null) {
				tx.commit();
				return cminfo;
			}else{
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
	public Integer getMoneyLogCount(int roleId, int uId,String userName,String dateStart,String dateEnd,int ptype,String pay_num,String teadeNo,String orderNo) {
		// TODO Auto-generated method stub
		Transaction tx = null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			int count = mlogDao.getMoneyLogCount(sess, roleId, uId, userName, dateStart, dateEnd, ptype, pay_num, teadeNo, orderNo);
			if(count!= 0){
				tx.commit();
				return count;
			}else{
				tx.commit();
				return 0;
			}
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
	public List getMoneyLogListByPage(int pageNo, int pageSize,
			int roleId, int uId,String userName,String dateStart,String dateEnd,int ptype,String pay_num,String teadeNo,String orderNo) {
		// TODO Auto-generated method stub
		Transaction tx=null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			List ml = mlogDao.getMoneyLogListByPage(sess, pageNo, pageSize, roleId, uId, userName, dateStart, dateEnd, ptype, pay_num, teadeNo, orderNo);
			if (ml != null && ml.size() > 0) {
				List<TbMoneyLog> result = new ArrayList<TbMoneyLog>();
				for (Object obj : ml) {
					TbMoneyLog me = (TbMoneyLog) obj;
					result.add(me);
				}
				tx.commit();
				return result;
			}else{
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
