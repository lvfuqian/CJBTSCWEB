package com.elegps.tscweb.dao;

import org.hibernate.Session;

import com.elegps.tscweb.model.hstmodel.TbGaleForecast;

public interface GaleForecastDaoFactory {
	/**
	 * Ìí¼Ó
	 * @param session
	 * @param gf
	 */
	void add(Session session,TbGaleForecast gf);
}
