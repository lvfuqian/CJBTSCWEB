package com.elegps.tscweb.dao.impl;

import org.hibernate.Session;

import com.elegps.tscweb.dao.FisheryForecastDaoFactory;
import com.elegps.tscweb.model.hstmodel.TbFisheryForecast;

public class FisheryForecastDaoHibernate implements FisheryForecastDaoFactory{

	@Override
	public void add(Session session, TbFisheryForecast ff) {
		try {
			session.save(ff);
			session.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session!=null){
				//session.close();
			}
		}
		
	}

}
