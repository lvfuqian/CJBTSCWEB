package com.elegps.tscweb.dao.impl;

import org.hibernate.Session;

import com.elegps.tscweb.dao.TyphoonListDaoFactory;
import com.elegps.tscweb.model.hstmodel.TbTyphoonList;

public class TyphoonListDaoHibernate implements TyphoonListDaoFactory{

	@Override
	public void add(Session session, TbTyphoonList tl) {
		try {
			session.save(tl);
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
