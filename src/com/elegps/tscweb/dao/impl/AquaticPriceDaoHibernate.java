package com.elegps.tscweb.dao.impl;

import org.hibernate.Session;

import com.elegps.tscweb.dao.AquaticPriceDaoFactory;
import com.elegps.tscweb.model.hstmodel.TbAquaticPrice;

public class AquaticPriceDaoHibernate implements AquaticPriceDaoFactory{

	@Override
	public void add(Session session, TbAquaticPrice ap) {
		try {
			session.save(ap);
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
