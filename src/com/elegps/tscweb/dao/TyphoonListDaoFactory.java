package com.elegps.tscweb.dao;

import org.hibernate.Session;

import com.elegps.tscweb.model.hstmodel.TbTyphoonList;


public interface TyphoonListDaoFactory {
	/**
	 * Ìí¼Ó
	 * @param session
	 * @param tl
	 */
	void add(Session session,TbTyphoonList tl);
}
