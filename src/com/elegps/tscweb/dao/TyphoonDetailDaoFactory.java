package com.elegps.tscweb.dao;

import org.hibernate.Session;

import com.elegps.tscweb.model.hstmodel.TbTyphoonDetail;


public interface TyphoonDetailDaoFactory {
	/**
	 * ���
	 * @param session
	 * @param td
	 */
	void add(Session session,TbTyphoonDetail td);
}
