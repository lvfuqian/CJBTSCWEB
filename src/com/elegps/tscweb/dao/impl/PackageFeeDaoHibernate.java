package com.elegps.tscweb.dao.impl;

import java.util.List;

import org.hibernate.Session;

import com.elegps.tscweb.dao.PFDaoFactory;
import com.elegps.tscweb.model.TbPFInfo;

public class PackageFeeDaoHibernate implements PFDaoFactory {

	@Override
	public void addPFInfo(Session sess, TbPFInfo pfInfo) {
		// TODO Auto-generated method stub
		try {
			sess.save(pfInfo);
			sess.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

	@Override
	public List<TbPFInfo> findAllPFInfo(Session sess) {
		// TODO Auto-generated method stub	
		try {
			List<TbPFInfo> pf =(List<TbPFInfo>)sess.createQuery(" from TbPFInfo ").list();
			if (pf != null && pf.size() > 0) {
				return pf;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sess != null) {
//				sess.close();
			}
			
		}
		
		return null;
	}

	@Override
	public void updatePFInfo(Session sess, TbPFInfo pfInfo) {
		// TODO Auto-generated method stub
		try {
			sess.saveOrUpdate(pfInfo);
			sess.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

	@Override
	public TbPFInfo findPFInfo(Session sess, int pfId) {
		// TODO Auto-generated method stub
		String hql =" from TbPFInfo pf where pfId = "+pfId;
		try {
			List pf = sess.createQuery(hql).list();
			if (pf != null && pf.size() > 0) {
				
				return (TbPFInfo)pf.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sess != null) {
//				sess.close();
			}
			
		}
		
		return null;
	}

	@Override
	public void deletePFInfo(Session sess, TbPFInfo pfInfo) {
		// TODO Auto-generated method stub
		try {
			sess.delete(pfInfo);
			sess.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

	@Override
	public TbPFInfo findPFinfoByHow(Session sess, Double how) {
		// TODO Auto-generated method stub
		String hql =" from TbPFInfo pf where pfHow = "+how;
		try {
			List pf = sess.createQuery(hql).list();
			if (pf != null && pf.size() > 0) {
				
				return (TbPFInfo)pf.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sess != null) {
//				sess.close();
			}
			
		}
		return null;
	}
	
	

}
