package com.elegps.tscweb.serivce.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.elegps.tscweb.comm.HibernateUtil;
import com.elegps.tscweb.dao.ParamsDaoFactory;
import com.elegps.tscweb.dao.impl.ParamsDaoHibernate;
import com.elegps.tscweb.exception.MessageException;
import com.elegps.tscweb.model.TbParamsInfo;
import com.elegps.tscweb.serivce.ParamsManager;


public class ParamsManagerImpl implements ParamsManager{

	private ParamsDaoFactory paramsDao;
	public ParamsManagerImpl() throws MessageException {
		try {
			paramsDao = new ParamsDaoHibernate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new MessageException("初始化业务逻辑组件出现异常...");
		}
	}
	@Override
	public String add(TbParamsInfo paramsInfo) {
		// TODO Auto-generated method stub
		Transaction tx=null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			if(paramsInfo!=null){
				paramsDao.add(sess, paramsInfo);
				tx.commit();
				return paramsInfo.getId()+"";
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
	public Boolean delete(Integer id) {
		// TODO Auto-generated method stub
		Transaction tx = null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();

			TbParamsInfo mss=paramsDao.findById(sess, id);
			if (mss != null) {
				paramsDao.delete(sess, mss);
				tx.commit();
				return true;
			}else{
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
	public TbParamsInfo findById(int id) {
		// TODO Auto-generated method stub
		Transaction tx = null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			TbParamsInfo mss = paramsDao.findById(sess, id);
			if (mss != null) {
				tx.commit();
				return mss;
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
	public TbParamsInfo findByMsId(String msid) {
		// TODO Auto-generated method stub
		Transaction tx = null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			TbParamsInfo mss = paramsDao.findByMsId(sess, msid);
			if (mss != null) {
				tx.commit();
				return mss;
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
	public List<TbParamsInfo> get() {
		// TODO Auto-generated method stub
		Transaction tx = null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			List<TbParamsInfo> mss = paramsDao.get(sess);
			if (mss != null && mss.size() > 0) {
				tx.commit();
				return mss;
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
	public List<TbParamsInfo> get(int pageNo,int pageSize,String msid, Integer type, Integer flag,
			String operator) {
		// TODO Auto-generated method stub
		Transaction tx = null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			List<TbParamsInfo> mss = paramsDao.get(sess, pageNo, pageSize, msid, type, flag, operator);
			if (mss != null && mss.size() > 0) {
				tx.commit();
				return mss;
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
	public Integer getCount(int pageNo,int pageSize,String msid, Integer type, Integer flag,
			String operator) {
		// TODO Auto-generated method stub
		Transaction tx = null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			int count = paramsDao.getCount(sess, pageNo, pageSize, msid, type, flag, operator);
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
		return null;
	}
	@Override
	public Boolean update(TbParamsInfo paramsInfo) {
		// TODO Auto-generated method stub
		Transaction tx = null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			paramsDao.update(sess, paramsInfo);
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
	public Boolean delete(String[] id) {
		// TODO Auto-generated method stub
		Transaction tx = null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			
			for (int i = 0; i < id.length; i++) {
				int emp =Integer.parseInt( id[i]);
				TbParamsInfo mss=paramsDao.findById(sess,emp);
				if (mss != null) {
					paramsDao.delete(sess, mss);
					tx.commit();
					return true;
				}else{
					tx.commit();
					return false;
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
