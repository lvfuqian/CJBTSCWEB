package com.elegps.tscweb.dao.impl;

import org.hibernate.Session;

import com.elegps.tscweb.dao.GaleHierrrarchyDaoFactory;
import com.elegps.tscweb.model.hstmodel.TbGaleHierrarchy;

public class GaleHierrarchyDaoHibernate implements GaleHierrrarchyDaoFactory{

	@Override
	public void add(Session session, TbGaleHierrarchy gh) {
		try {
			session.save(gh);
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
