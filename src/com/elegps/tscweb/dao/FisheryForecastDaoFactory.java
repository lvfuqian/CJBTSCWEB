package com.elegps.tscweb.dao;

import org.hibernate.Session;

import com.elegps.tscweb.model.hstmodel.TbFisheryForecast;


public interface FisheryForecastDaoFactory {
	/**
	 * Ìí¼Ó
	 * @param session
	 * @param ff
	 */
	void add(Session session,TbFisheryForecast ff);
}
