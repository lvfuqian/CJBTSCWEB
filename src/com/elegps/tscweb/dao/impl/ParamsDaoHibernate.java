package com.elegps.tscweb.dao.impl;

import java.util.List;

import org.hibernate.Session;

import com.elegps.tscweb.dao.ParamsDaoFactory;
import com.elegps.tscweb.model.TbParamsInfo;

public class ParamsDaoHibernate implements ParamsDaoFactory {

	@Override
	public void add(Session sess, TbParamsInfo paramsInfo) {
		// TODO Auto-generated method stub
		try {
			sess.save(paramsInfo);
			sess.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(Session sess, TbParamsInfo paramsInfo) {
		// TODO Auto-generated method stub
		try {
			sess.delete(paramsInfo);
			sess.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public TbParamsInfo findById(Session sess, int id) {
		// TODO Auto-generated method stub
		String hql =" from TbParamsInfo where id = "+id;
		try {
			List mss = sess.createQuery(hql).list();
			if (mss != null && mss.size() > 0) {
				
				return (TbParamsInfo)mss.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sess != null) {
//						sess.close();
			}
			
		}
		return null;
	}

	@Override
	public TbParamsInfo findByMsId(Session sess, String msid) {
		// TODO Auto-generated method stub
		String hql =" from TbParamsInfo where msid = "+msid;
		try {
			List mss = sess.createQuery(hql).list();
			if (mss != null && mss.size() > 0) {
				
				return (TbParamsInfo)mss.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sess != null) {
//						sess.close();
			}
			
		}
		return null;
	}

	@Override
	public List<TbParamsInfo> get(Session sess) {
		// TODO Auto-generated method stub
		try {
			List<TbParamsInfo> mss =(List<TbParamsInfo>)sess.createQuery(" from TbParamsInfo ").list();
			if (mss != null && mss.size() > 0) {
				return mss;
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
	public List<TbParamsInfo> get(Session sess,int pageNo,int pageSize, String msid, Integer type,
			Integer flag, String operator) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getCount(Session sess,int pageNo,int pageSize, String msid, Integer type,
			Integer flag, String operator) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Session sess, TbParamsInfo paramsInfo) {
		// TODO Auto-generated method stub
		try {
			sess.saveOrUpdate(paramsInfo);
			sess.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
