package com.elegps.tscweb.dao.impl;

import org.hibernate.Session;

import com.elegps.tscweb.dao.AquaticIndexDaoFactory;
import com.elegps.tscweb.model.hstmodel.TbAquaticIndex;

public class AquaticIndexDaoHibernate implements AquaticIndexDaoFactory{

	@Override
	public void add(Session session, TbAquaticIndex ai) {
		try {
			session.save(ai);
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
