package com.elegps.tscweb.dao;

import org.hibernate.Session;

import com.elegps.tscweb.model.hstmodel.TbAquaticIndex;

public interface AquaticIndexDaoFactory {
	/**
	 * ���
	 * @param session
	 * @param ai
	 */
	void add(Session session,TbAquaticIndex ai);
}
