package com.elegps.tscweb.dao.impl;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;

import com.elegps.tscweb.comm.HibernateUtil;
import com.elegps.tscweb.dao.UserRoleDaoFactory;
import com.elegps.tscweb.model.TbRoleMenuInfo;
import com.elegps.tscweb.model.TbRoleUserInfo;
import com.elegps.tscweb.model.TbUserInfo;


public class UserRoleDaoHibernate implements UserRoleDaoFactory {

	public Integer findUserRole_SearchCount(Session sess, String user_id,
			String role_id) {
		String hql = "select count(userRoleSid) from TbRoleUserInfo where userId!=0 ";
		if (user_id.equals("")) {
			if (role_id.equals("")) {

			} else {
				hql = hql + " and roleSid=" + role_id;
			}
		} else {
			hql = hql + " and userId=" + user_id;
			if (role_id.equals("")) {

			} else {
				hql = hql + " and roleSid=" + role_id;
			}
		}

		try {
			Object obj = sess.createQuery(hql).uniqueResult();
			if (obj != null) {
				int mscount = (Integer) obj;
				return mscount;
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

	public List findUserRole_SearchByPage(Session sess, int pageNo,
			int pageSize, String user_id, String role_id) {
		String hql = "select ur.userRoleSid as urid,ur.create_time as create_time,u.userName as uname,r.roleName as rname from TbRoleUserInfo ur,TbUserInfo u,TbRoleInfo r where ur.userId!=0";
		if (user_id.equals("")) {
			if (role_id.equals("")) {

			} else {
				hql = hql + " and ur.roleSid=" + role_id;
			}
		} else {
			hql = hql + " and ur.userId=" + user_id;
			if (role_id.equals("")) {

			} else {
				hql = hql + " and ur.roleSid=" + role_id;
			}
		}

		hql = hql + " and ur.roleSid=r.roleId and ur.userId=u.userId order by ur.create_time desc";
		try {
			int offset = (pageNo - 1) * pageSize;
			ScrollableResults srs = sess.createQuery(hql).setFirstResult(offset)
					.setMaxResults(pageSize).scroll();
			List res=new ArrayList();
			while(srs.next()){
				Map map = new HashMap();
				map.put("userroleid",srs.getInteger(0));
				map.put("username", srs.getString(2));
				map.put("rolename", srs.getString(3));
                res.add(map);
            }
			if (res != null && res.size()>0) {
				return res;
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

	public TbRoleUserInfo get_ByUsRoId(Session sess, String user_id,
			String role_id) {
		try {
			List userrole = sess.createQuery(
					"from TbRoleUserInfo m where m.userId=" + user_id
							+ " and m.roleSid=" + role_id).list();
			if (userrole != null && userrole.size() > 0) {
				return (TbRoleUserInfo) userrole.get(0);
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

	public TbRoleUserInfo get(Session sess, String userrole_id) {
		try {
			List ms = sess.createQuery("from TbRoleUserInfo m where m.userRoleSid="+userrole_id).list();
			if (ms != null && ms.size() > 0) {
				return (TbRoleUserInfo) ms.get(0);
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

	public void delete(Session sess, TbRoleUserInfo m) {
		try {
			sess.delete(m);
			sess.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} 	
	}

	public void deleteUser(Session sess, String user_id) {
		try {
			String hql = "delete TbRoleUserInfo where userId=?";
			Query q = sess.createQuery(hql);
			q.setString(0, user_id);
			q.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sess != null) {
//				sess.close();
			}
		}
	}
	
	public void deleteRole(Session sess, String role_id) {
		try {
			String hql = "delete TbRoleUserInfo where roleSid=?";
			Query q = sess.createQuery(hql);
			q.setString(0, role_id);
			q.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sess != null) {
//				sess.close();
			}
		}
	}

	public TbRoleUserInfo getRoleid_byUserid(Session sess, String user_id) {
		try {
			List ms = sess.createQuery("from TbRoleUserInfo m where m.userId="+user_id).list();
			if (ms != null && ms.size() > 0) {
				 return (TbRoleUserInfo) ms.get(0);
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
