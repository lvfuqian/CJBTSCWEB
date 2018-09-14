package com.elegps.tscweb.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;


import com.elegps.tscweb.comm.HibernateUtil;
import com.elegps.tscweb.dao.MenuDaoFactory;
import com.elegps.tscweb.dao.RoleDaoFactory;
import com.elegps.tscweb.model.TbGpsInfo;
import com.elegps.tscweb.model.TbMenuInfo;
import com.elegps.tscweb.model.TbRoleInfo;
import com.elegps.tscweb.model.TbRoleUserInfo;


public class RoleDaoHibernate implements RoleDaoFactory {

	/**
	 * 查询指定角色名的记录数
	 */
	public Integer findRole_SearchCount(Session sess, String role_name) {
		String hql = "select count(m.roleName) from TbRoleInfo m where m.roleId<>0";
		if (role_name.equals("")) { // 表示为全部菜单
		}else{
			hql=hql+" and m.roleName like '%"+role_name+ "%'";
		}

		try {
			Object obj = sess.createQuery(hql).uniqueResult();
			if (obj != null) {
				int menucount = (Integer) obj;
				return menucount;
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

	public List findRoleInfo_SearchByPage(Session sess, int pageNo,
			int pageSize, String role_name) {
		String hql="from TbRoleInfo m where m.roleId<>0";
		if (role_name.equals("")) { // 表示为全部菜单
		}else{
			hql=hql+" and m.roleName like '%"+role_name+ "%'";
		}
		hql=hql+" order by m.create_time desc";
		try {
			int offset = (pageNo - 1) * pageSize;
			List list=sess.createQuery(hql).setFirstResult(offset).setMaxResults(pageSize).list();
			if(list!=null){
				return list;
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

	public void delete(Session sess, TbRoleInfo m) {
		try {
			sess.delete(m);
			sess.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} 	
	}

	public TbRoleInfo get(Session sess, String role_id) {
		try {
			List ms = sess.createQuery("from TbRoleInfo m where m.roleId="+role_id).list();
			if (ms != null && ms.size() > 0) {
				return (TbRoleInfo) ms.get(0);
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

	public TbRoleInfo get_byname(Session sess,String role_id, String role_name) {
		try {
			List ms = sess.createQuery("from TbRoleInfo m where m.roleName='"+role_name+"' and roleId!="+role_id).list();
			if (ms != null && ms.size() > 0) {
				return (TbRoleInfo) ms.get(0);
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

	public TbRoleInfo get_byname(Session sess, String role_name) {
		try {
			List ms = sess.createQuery("from TbRoleInfo m where m.roleName='"+role_name+"'").list();
			if (ms != null && ms.size() > 0) {
				return (TbRoleInfo) ms.get(0);
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

	public List findAllRoleInfo(Session sess) {
		String hql="from TbRoleInfo m  where m.roleId!=0 order by m.create_time desc";
		try {
			List list=sess.createQuery(hql).list();
			if(list!=null){
				return list;
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
