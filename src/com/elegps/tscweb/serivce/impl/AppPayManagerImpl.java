package com.elegps.tscweb.serivce.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.elegps.tscweb.comm.HibernateUtil;
import com.elegps.tscweb.dao.AppPayDaoFactory;
import com.elegps.tscweb.dao.MsDaoFactory;
import com.elegps.tscweb.dao.impl.AppPayDaoHibernate;
import com.elegps.tscweb.dao.impl.MsDaoHibernate;
import com.elegps.tscweb.exception.MessageException;
import com.elegps.tscweb.model.TbAppPayInfo;
import com.elegps.tscweb.model.TbKoFeiLogInfo;
import com.elegps.tscweb.model.TbMsInfo;
import com.elegps.tscweb.serivce.AppPayManager;




public class AppPayManagerImpl implements AppPayManager 
{
	
	private AppPayDaoFactory apdao;
	private MsDaoFactory msdao;
	public AppPayManagerImpl() throws MessageException {
		try {
			apdao = new AppPayDaoHibernate();
			msdao = new MsDaoHibernate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new MessageException("初始化业务逻辑组件出现异常...");
		}

	}
	@Override
	public String addAPInfo(TbAppPayInfo apInfo) {
		Transaction tx=null;
		try{
			Session session=HibernateUtil.currentSession();
			tx=session.beginTransaction();
			apdao.addAPInfo(session, apInfo);
			tx.commit();
			return "true";
		}catch(Exception e){
			if(tx!=null){
				tx.rollback();
				e.printStackTrace();
			}
		}finally{
			HibernateUtil.closeSession();
		}
		return null;
	}
	
	@Override
	public List<TbAppPayInfo> findAllApInfo() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<TbAppPayInfo> findAllApInfo(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Integer findApCount() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public TbAppPayInfo findApInfoById(String id) {
		Transaction tx=null;
		try{
			Session session=HibernateUtil.currentSession();
			tx=session.beginTransaction();
			TbAppPayInfo info=(TbAppPayInfo)apdao.findApInfoById(session, id);
		    if(info!=null){
		    	tx.commit();
		    	return info;
		    }else{
		    	tx.commit();
		    	return null;
		    }
		}catch(Exception e){
			tx.rollback();
			e.printStackTrace();
		}finally{
			HibernateUtil.closeSession();
		}
		return null;
	}
	@Override
	public List<TbAppPayInfo> findApInfoByPage(int pageNo,int pageSize,String phonems,int payType) {
		Transaction tx=null;
		try{
			Session sess=HibernateUtil.currentSession();
			tx=sess.beginTransaction();
			List<TbAppPayInfo> list=apdao.findApInfoByPage(sess,pageNo,pageSize, phonems,payType);
			if(list!=null){
				tx.commit();
				return list;
			}
		}catch(Exception e){
			tx.rollback();
			e.printStackTrace();
		}finally{
			HibernateUtil.closeSession();
		}
		return null;
	}
	@Override
	public Boolean updateApInfo(TbAppPayInfo apInfo) {
		Transaction tx = null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			apdao.updateApInfo(sess, apInfo);
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
	public Boolean deleteApInfo(String[] id) {
		return null;
	}
	@Override
	public Boolean deleteApInfo(String id) {
		Transaction tx = null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			TbAppPayInfo ap = apdao.findApInfoById(sess, id);
			if(ap != null){
				apdao.deleteApInfo(sess, ap);
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
	public boolean appPay(TbAppPayInfo apInfo, TbMsInfo msinfo) {
		Transaction tx = null;
		Session sess =null;
		try {
			sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			if(apInfo == null || msinfo == null){
				tx.commit();
				return false;
			}
			msdao.update(sess, msinfo);
			apInfo.setSuccessTime(new Date());
			apdao.addAPInfo(sess, apInfo);
			tx.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			HibernateUtil.closeSession();
		}
		return false;
	}
	@Override
	public Integer findApCountByPage(String phonems, int payType) {
		Transaction tx = null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			Integer count = apdao.findApCountByPage(sess,phonems,payType);
			if (count != null) {
				tx.commit();
				return count;
			} else {
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
	public Map<String,String> findApCountByTime(String msid,String startTime,String endTime) {
		Transaction tx = null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			Map<String,String> map = apdao.findApCountByTime(sess,msid,startTime,endTime);
			if (map != null) {
				tx.commit();
				return map;
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
	public Map<String, String> findKFByTime(String msid, String startTime,
			String endTime) {
		Transaction tx = null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			Map<String,String> map = apdao.findKFByTime(sess,msid,startTime,endTime);
			if (map != null) {
				tx.commit();
				return map;
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
	public Integer findKFCountByPage(String pagentid, String childagentid,
			String ep, String msid, int type, String startTime, String endTime,String imsi) {
		Transaction tx = null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			Integer count = apdao.findKFCountByPage(sess, pagentid, childagentid, ep, msid, type, startTime, endTime,imsi);
			if (count != null) {
				tx.commit();
				return count;
			} else {
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
	public List<TbKoFeiLogInfo> findKFListInfo_sertchByPage(int pageNo,
			int pageSize, String pagentid, String childagentid, String ep,
			String msid, int type, String startTime, String endTime,String imsi) {
		Transaction tx = null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			List<TbKoFeiLogInfo> kfList= apdao.findKFListInfo_sertchByPage(sess, pageNo, pageSize, pagentid, childagentid, ep,
					msid, type, startTime, endTime,imsi);
			if(kfList==null){
				return null;
			}
			if (kfList.size()>0) {
				tx.commit();
				return kfList;
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