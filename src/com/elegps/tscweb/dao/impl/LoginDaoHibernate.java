package com.elegps.tscweb.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;


import com.elegps.tscweb.comm.HibernateUtil;
import com.elegps.tscweb.dao.LoginDaoFactory;
import com.elegps.tscweb.dao.MenuDaoFactory;
import com.elegps.tscweb.dao.RoleDaoFactory;
import com.elegps.tscweb.model.TbGpsInfo;
import com.elegps.tscweb.model.TbMenuInfo;
import com.elegps.tscweb.model.TbRoleInfo;
import com.elegps.tscweb.model.TbRoleMenuInfo;
import com.elegps.tscweb.model.TbRoleUserInfo;
import com.elegps.tscweb.model.TbUserInfo;


public class LoginDaoHibernate implements LoginDaoFactory {

	public TbUserInfo getUserInfo(Session sess, String uname, String password) {
		try {
			List user = sess.createQuery(
					"from TbUserInfo tb where tb.userName='"+uname+"' and tb.userPassword='"+password+"'").list();
			if (user != null&&user.size()>=1) {
				return (TbUserInfo)user.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sess != null) {
				// sess.close();
			}
		}
		return null;
	}

	public TbRoleUserInfo getUserRoleInfo(Session sess,TbUserInfo userinfo) {
		try {
			List userrole = sess.createQuery(
					"from TbRoleUserInfo tb where tb.userId='"+userinfo.getUserId()+"'").list();
			if (userrole != null&&userrole.size()>0) {
				return (TbRoleUserInfo)userrole.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sess != null) {
				// sess.close();
			}
		}
		return null;
	}

	public List getRoleMenuInfo(Session sess, TbRoleUserInfo roleuserinfo) {
		try {
			List rolemenu = sess.createQuery(
					"from TbRoleMenuInfo tb where tb.roleId='"+roleuserinfo.getRoleSid()+"'").list();
			if (rolemenu != null) {
				return rolemenu;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sess != null) {
				// sess.close();
			}
		}
		return null;
	}

	public List getMenuInfo(Session sess, String menuid) {
		try {
			ScrollableResults rsmenu = sess.createQuery("select m.menuId as menuid,m.menuName as menuName,m.url as url, m.pmenuId from TbMenuInfo m where m.menuId in("+menuid+")" ).scroll();
			List rsmenucount=sess.createQuery("select m.menuId as menuid,m.menuName as menuName,m.url as url, m.pmenuId from TbMenuInfo m where m.menuId in("+menuid+")" ).list();
			for(int i=0;i<rsmenucount.size();i++){
				while(rsmenu.next()){
					menuid=menuid+","+rsmenu.getInteger(3).toString();
			  }
//				rsmenu=sess.createQuery("select m.menuId as menuid,m.menuName as menuName,m.url as url, m.pmenuId from TbMenuInfo m where m.menuId  in("+menuid+")").scroll();
//				rsmenucount=sess.createQuery("select m.menuId as menuid,m.menuName as menuName,m.url as url, m.pmenuId from TbMenuInfo m where m.menuId  in("+menuid+")").list();
			}
			rsmenu.close();
			List menuinfo=sess.createQuery("from TbMenuInfo m where m.menuId  in("+menuid+") and m.menuId!=0 order by c02").list();
			if (menuinfo != null) {
				return menuinfo;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sess != null) {
				// sess.close();
			}
		}
		return null;
	}

	
		
}
