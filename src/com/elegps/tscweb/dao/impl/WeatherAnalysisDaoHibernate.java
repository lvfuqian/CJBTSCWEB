package com.elegps.tscweb.dao.impl;

import org.hibernate.Session;

import com.elegps.tscweb.dao.WeatherAnalysisDaoFactory;
import com.elegps.tscweb.model.hstmodel.TbWeatherAnalysis;

public class WeatherAnalysisDaoHibernate implements WeatherAnalysisDaoFactory{

	@Override
	public void add(Session session, TbWeatherAnalysis wa) {
		try {
			session.save(wa);
			session.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				// session.close();
			}
		}
		
	}

}
