package com.elegps.tscweb.serivce.impl;


import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;




import com.elegps.tscweb.comm.HibernateUtil;
import com.elegps.tscweb.dao.LoginDaoFactory;
import com.elegps.tscweb.dao.impl.LoginDaoHibernate;
import com.elegps.tscweb.exception.MessageException;
import com.elegps.tscweb.model.TbMenuInfo;
import com.elegps.tscweb.model.TbMsInfo;
import com.elegps.tscweb.model.TbRoleMenuInfo;
import com.elegps.tscweb.model.TbRoleUserInfo;
import com.elegps.tscweb.model.TbUserInfo;
import com.elegps.tscweb.serivce.LoginManager;


public class LoginManagerImpl implements LoginManager{

	private LoginDaoFactory logindao;
	public LoginManagerImpl() throws MessageException {
		try {
			logindao = new LoginDaoHibernate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new MessageException("初始化业务逻辑组件出现异常...");
		}
	}
	public TbUserInfo getUser(String uname, String password) {
		Transaction tx=null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			TbUserInfo ml = logindao.getUserInfo(sess, uname, password);
			if (ml != null) {
				tx.commit();
				return ml;
			}		
		} catch (Exception e) {
			if (null != tx)
				tx.rollback();
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
		}
		return null;
	}
	
	
	public TbRoleUserInfo getUserRole(TbUserInfo userinfo) {
		Transaction tx=null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			TbRoleUserInfo ml = logindao.getUserRoleInfo(sess,userinfo);
			if (ml != null) {
				tx.commit();
				return ml;
			}		
		} catch (Exception e) {
			if (null != tx)
				tx.rollback();
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
		}
		return null;
	}
	
	
	public List<TbRoleMenuInfo> getRoleMenu(TbRoleUserInfo roleUserInfo) {
		Transaction tx=null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			List ml = logindao.getRoleMenuInfo(sess,roleUserInfo);
			if (ml != null&& ml.size() > 0) {
				List<TbRoleMenuInfo> result = new ArrayList<TbRoleMenuInfo>();
				for (Object obj : ml) {
					TbRoleMenuInfo me = (TbRoleMenuInfo) obj;
					result.add(me);
				}
				tx.commit();
				return result;
			}		
		} catch (Exception e) {
			if (null != tx)
				tx.rollback();
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
		}
		return null;
	}
	public List<TbMenuInfo> getMenu(List<TbRoleMenuInfo> roleMenuInfos) {
		Transaction tx=null;
		String menuid=null;
		if(roleMenuInfos.size()>=1){
			menuid=roleMenuInfos.get(0).getMenuId().toString();
		}
		for(int i=1;i<roleMenuInfos.size();i++){
			menuid=menuid+","+roleMenuInfos.get(i).getMenuId().toString();
		}
		
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			List ml = logindao.getMenuInfo(sess,menuid);
			if (ml != null&& ml.size() > 0) {
				List<TbMenuInfo> result = new ArrayList<TbMenuInfo>();
				for (int i=0;i<ml.size();i++) {
					TbMenuInfo me = (TbMenuInfo) ml.get(i);
					result.add(me);
				}
				tx.commit();
				return result;
			}		
		} catch (Exception e) {
			if (null != tx)
				tx.rollback();
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
		}
		return null;
	}

	
	
}
