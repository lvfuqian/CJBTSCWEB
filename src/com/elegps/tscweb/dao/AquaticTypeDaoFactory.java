package com.elegps.tscweb.dao;

import org.hibernate.Session;

import com.elegps.tscweb.model.hstmodel.TbAquaticType;

public interface AquaticTypeDaoFactory {
	/**
	 * Ìí¼Ó
	 * @param session
	 * @param at
	 */
	void add(Session session,TbAquaticType at);
}
