package com.elegps.tscweb.dao.impl;

import org.hibernate.Session;

import com.elegps.tscweb.dao.ForecastReferenceDaoFactory;
import com.elegps.tscweb.model.hstmodel.TbForecastReference;

public class ForecastReferenceDaoHibernate implements ForecastReferenceDaoFactory{

	@Override
	public void add(Session session, TbForecastReference fr) {
		try {
			session.save(fr);
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
