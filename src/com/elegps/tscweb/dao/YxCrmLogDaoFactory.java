package com.elegps.tscweb.dao;

import org.hibernate.Session;

import com.elegps.tscweb.model.TbYxCrmLog;

public interface YxCrmLogDaoFactory {

	void save(Session sess, TbYxCrmLog m);

}