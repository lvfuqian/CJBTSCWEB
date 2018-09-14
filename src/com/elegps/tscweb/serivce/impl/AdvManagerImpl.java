package com.elegps.tscweb.serivce.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.elegps.tscweb.comm.HibernateUtil;
import com.elegps.tscweb.dao.AdvDaoFactory;
import com.elegps.tscweb.dao.impl.AdvDaoHibernate;
import com.elegps.tscweb.exception.MessageException;
import com.elegps.tscweb.serivce.AdvManager;
import com.elegps.tscweb.model.TbAdvInfo;;

public class AdvManagerImpl implements AdvManager{

	private AdvDaoFactory advDao;
	public AdvManagerImpl() throws MessageException {
		try {
			advDao = new AdvDaoHibernate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new MessageException("初始化业务逻辑组件出现异常...");
		}
	}

	@Override
	public String addAdvInfo(TbAdvInfo advInfo) {
		Transaction tx=null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			if(advInfo!=null){
				advDao.addAdvInfo(sess, advInfo);
				tx.commit();
				return advInfo.getAdvId().toString();
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
	public Boolean deleteAdvInfo(String[] advId) {
		Transaction tx = null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			
			for (int i = 0; i < advId.length; i++) {
				int emp =Integer.parseInt( advId[i]);
				TbAdvInfo advInfo=advDao.findAdvInfoById(sess, emp);
				if (advInfo != null) {
					advDao.deleteAdvInfo(sess, advInfo);
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
	public List<TbAdvInfo> findAllAdvInfo() {
		// TODO Auto-generated method stub
		Transaction tx = null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			List<TbAdvInfo> adv = advDao.findAllAdvInfo(sess);
			if (adv != null && adv.size() > 0) {
				tx.commit();
				return adv;
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
	public Boolean updateAdvInfo(TbAdvInfo advInfo) {
		// TODO Auto-generated method stub
		Transaction tx = null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
				advDao.updateAdvInfo(sess, advInfo);
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
	public TbAdvInfo findAdvInfoById(String advId) {
		Transaction tx = null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			TbAdvInfo adv = advDao.findAdvInfoById(sess, Integer.parseInt(advId));
			if (adv != null) {
				tx.commit();
				return adv;
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
	public Boolean findAdvById(String advId) {
		// TODO Auto-generated method stub
		Transaction tx = null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			TbAdvInfo adv = advDao.findAdvInfoById(sess, Integer.parseInt(advId));
			if (adv != null) {
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
	public Integer findAdvCount(String title, String url, String sendSTime,
			String sendETime, String creatSTime, String creatETime,int advType) {
		Transaction tx = null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			Integer advCount = advDao.findAdvCount(sess, title, url, sendSTime, 
					sendETime, creatSTime, creatETime,advType);
			tx.commit();
			return advCount;
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
	public List<TbAdvInfo> findAdvInfoByPage(Integer pageNo, Integer pageSize,
			String title, String url, String sendSTime, String sendETime,
			String creatSTime, String creatETime,int advType) {
		Transaction tx = null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			List<TbAdvInfo> advList = advDao.findAllAdvInfo(sess, pageNo, pageSize, title,
					url, sendSTime, sendETime, creatSTime, creatETime,advType);
			if (advList != null) {
				tx.commit();
				return advList;
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
