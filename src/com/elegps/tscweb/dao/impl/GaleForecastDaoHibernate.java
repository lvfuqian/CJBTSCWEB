package com.elegps.tscweb.dao.impl;

import org.hibernate.Session;

import com.elegps.tscweb.dao.GaleForecastDaoFactory;
import com.elegps.tscweb.model.hstmodel.TbGaleForecast;

public class GaleForecastDaoHibernate implements GaleForecastDaoFactory{

	@Override
	public void add(Session session, TbGaleForecast gf) {
		try {
			session.save(gf);
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
