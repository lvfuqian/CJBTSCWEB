package com.elegps.tscweb.dao.impl;

import org.hibernate.Session;

import com.elegps.tscweb.dao.AquaticTypeDaoFactory;
import com.elegps.tscweb.model.hstmodel.TbAquaticType;

public class AquaticTypeDaoHibernate implements AquaticTypeDaoFactory{

	@Override
	public void add(Session session, TbAquaticType at) {
		try {
			session.save(at);
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
