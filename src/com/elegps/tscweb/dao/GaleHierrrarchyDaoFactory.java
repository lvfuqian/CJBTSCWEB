package com.elegps.tscweb.dao;

import org.hibernate.Session;

import com.elegps.tscweb.model.hstmodel.TbGaleHierrarchy;

public interface GaleHierrrarchyDaoFactory {
	/**
	 * Ìí¼Ó
	 * @param session
	 * @param gh
	 */
	void add(Session session,TbGaleHierrarchy gh);
}
