package com.elegps.tscweb.dao;

import org.hibernate.Session;

import com.elegps.tscweb.model.hstmodel.TbAquaticPrice;

public interface AquaticPriceDaoFactory {
	/**
	 * ���
	 * @param session
	 * @param ap
	 */
	void add(Session session,TbAquaticPrice ap);
}
