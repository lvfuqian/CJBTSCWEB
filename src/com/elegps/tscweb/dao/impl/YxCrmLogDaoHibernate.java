package com.elegps.tscweb.dao.impl;

import org.hibernate.Session;

import com.elegps.tscweb.dao.YxCrmLogDaoFactory;
import com.elegps.tscweb.model.TbYxCrmLog;

public class YxCrmLogDaoHibernate implements YxCrmLogDaoFactory {

	@Override
	public void save(Session sess, TbYxCrmLog m) {
		try {
			sess.save(m);
			sess.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sess != null) {
				// sess.close();
			}
		}
	}

}
