package com.elegps.tscweb.dao.impl;

import java.util.List;

import org.hibernate.Session;

import com.elegps.tscweb.dao.BalanceDaoFactory;
import com.elegps.tscweb.model.TbORGBalanceInfo;


public class BalanceDaoHibernate implements BalanceDaoFactory {



	public TbORGBalanceInfo get(Session sess, String orgtype, String orgid) {
		try {
			List balance = sess.createQuery("from TbORGBalanceInfo b where b.orgId="+orgid+" and b.orgType="+orgtype).list();
			if (balance != null && balance.size() > 0) {
				return (TbORGBalanceInfo) balance.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sess != null) {
//				sess.close();
			}
		}
		return null;
	}
	
}
