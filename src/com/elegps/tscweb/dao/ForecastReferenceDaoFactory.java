package com.elegps.tscweb.dao;

import org.hibernate.Session;

import com.elegps.tscweb.model.hstmodel.TbForecastReference;

public interface ForecastReferenceDaoFactory {
	/**
	 * ���
	 * @param session
	 * @param fr
	 */
	void add(Session session,TbForecastReference fr);
}
