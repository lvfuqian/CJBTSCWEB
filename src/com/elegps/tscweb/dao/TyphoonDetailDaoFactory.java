package com.elegps.tscweb.dao;

import org.hibernate.Session;

import com.elegps.tscweb.model.hstmodel.TbTyphoonDetail;


public interface TyphoonDetailDaoFactory {
	/**
	 * Ìí¼Ó
	 * @param session
	 * @param td
	 */
	void add(Session session,TbTyphoonDetail td);
}
