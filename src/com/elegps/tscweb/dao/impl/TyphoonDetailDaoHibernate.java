package com.elegps.tscweb.dao.impl;

import org.hibernate.Session;

import com.elegps.tscweb.dao.TyphoonDetailDaoFactory;
import com.elegps.tscweb.model.hstmodel.TbTyphoonDetail;

public class TyphoonDetailDaoHibernate implements TyphoonDetailDaoFactory{

	@Override
	public void add(Session session, TbTyphoonDetail td) {
		try {
			session.save(td);
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
