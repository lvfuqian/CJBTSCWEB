package com.elegps.tscweb.dao;

import org.hibernate.Session;


public interface PhoneDaoHibernate<T> extends DdbDaoFactory<T> {
	public String getMaxPK(Session session, Object obj);

}
