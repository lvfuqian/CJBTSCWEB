package com.elegps.tscweb.serivce.impl;

import com.elegps.tscweb.model.TbKoFeiLogInfo;
import com.elegps.tscweb.model.TbPhonekoufeiLog;
import org.hibernate.Session;

import com.elegps.tscweb.comm.HibernateUtil;
import com.elegps.tscweb.dao.PhonekoufeiDaoFactory;
import com.elegps.tscweb.dao.impl.PhonekoufeiDaoHibernate;
import com.elegps.tscweb.exception.MessageException;
import com.elegps.tscweb.model.TbPhonekoufeiLogVo;
import com.elegps.tscweb.serivce.PhonekoufeiManager;
import org.hibernate.Transaction;

import java.util.List;

public class PhonekoufeiManagerImpl implements PhonekoufeiManager{

	private PhonekoufeiDaoFactory pdao;
	public PhonekoufeiManagerImpl() throws MessageException {
		try {
			pdao = new PhonekoufeiDaoHibernate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new MessageException("初始化业务逻辑组件出现异常...");
		}
	}
	@Override
	public TbPhonekoufeiLogVo getPhonekoufeiLog(String msid,String startTime,String endTime) {
		try {
			Session sess = HibernateUtil.currentSession();
			TbPhonekoufeiLogVo pkVo = pdao.findPhonekoufeiLog(sess, msid,startTime,endTime);
			return pkVo;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Integer findPKFCountByPage(String pagentid, String childagentid,
									 String ep, String msid, int type, String startTime, String endTime,String imsi) {
		Transaction tx = null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			Integer count = pdao.findPKFCountByPage(sess, pagentid, childagentid, ep, msid, type, startTime, endTime,imsi);
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
	public List<TbPhonekoufeiLog> findPKFListInfo_sertchByPage(int pageNo,
															int pageSize, String pagentid, String childagentid, String ep,
															String msid, int type, String startTime, String endTime, String imsi) {
		Transaction tx = null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			List<TbPhonekoufeiLog> kfList= pdao.findPKFListInfo_sertchByPage(sess, pageNo, pageSize, pagentid, childagentid, ep,
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
