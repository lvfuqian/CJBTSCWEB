package com.elegps.tscweb.dao;

import org.hibernate.Session;

import com.elegps.tscweb.model.hstmodel.TbWeatherAnalysis;


public interface WeatherAnalysisDaoFactory {
	/**
	 * ���
	 * @param session
	 * @param wa
	 */
	void add(Session session,TbWeatherAnalysis wa);
}
