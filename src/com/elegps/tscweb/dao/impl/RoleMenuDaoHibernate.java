package com.elegps.tscweb.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;

import com.elegps.tscweb.comm.HibernateUtil;
import com.elegps.tscweb.dao.MenuDaoFactory;
import com.elegps.tscweb.dao.RoleDaoFactory;
import com.elegps.tscweb.dao.RoleMenuDaoFactory;
import com.elegps.tscweb.model.TbGpsInfo;
import com.elegps.tscweb.model.TbGrpMsListInfo;
import com.elegps.tscweb.model.TbMenuInfo;
import com.elegps.tscweb.model.TbRoleInfo;
import com.elegps.tscweb.model.TbRoleMenuInfo;

public class RoleMenuDaoHibernate implements RoleMenuDaoFactory {

	public Integer findRoleMenu_SearchCount(Session sess, String role_id,
			String menu_id) {
		String hql = "select count(roleMenuSid) from TbRoleMenuInfo where roleId!=0 ";
		if (role_id.equals("")) {
			if (menu_id.equals("")) {

			} else {
				hql = hql + " and  menuId=" + menu_id;
			}
		} else {
			hql = hql + " and roleId=" + role_id;
			if (menu_id.equals("")) {

			} else {
				hql = hql + " and menuId=" + menu_id;
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

	public List findRoleMenu_SearchByPage(Session sess, int pageNo,
			int pageSize, String role_id, String menu_id) {
		String hql = "select rm.roleMenuSid as rmid,rm.create_time as create_time, r.roleName as rname,m.menuName as mname from TbRoleMenuInfo rm,TbRoleInfo r,TbMenuInfo m where rm.roleId!=0";
		if (role_id.equals("")) {
			if (menu_id.equals("")) {

			} else {
				hql = hql + " and rm.menuId=" + menu_id;
			}
		} else {
			hql = hql + " and rm.roleId=" + role_id;
			if (menu_id.equals("")) {

			} else {
				hql = hql + " and rm.menuId=" + menu_id;
			}
		}
		int i = hql.indexOf("where");
		if (i > 0) {
			hql = hql + " and rm.roleId=r.roleId and rm.menuId=m.menuId";
		} else {
			hql = hql + " where rm.roleId=r.roleId and rm.menuId=m.menuId";
		}
		hql = hql + " order by rm.create_time desc";
		try {
			int offset = (pageNo - 1) * pageSize;
			ScrollableResults srs = sess.createQuery(hql).setFirstResult(offset)
					.setMaxResults(pageSize).scroll();
			List res=new ArrayList();
			while(srs.next()){
				Map map = new HashMap();
				map.put("rolemenuid",srs.getInteger(0));
				map.put("rolename", srs.getString(2));
				map.put("menuname", srs.getString(3));
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

	public TbRoleMenuInfo get_ByRoMeId(Session sess, String role_id,
			String menu_id) {
		try {
			List rolemenu = sess.createQuery(
					"from TbRoleMenuInfo m where m.roleId=" + role_id
							+ " and m.menuId=" + menu_id).list();
			if (rolemenu != null && rolemenu.size() > 0) {
				return (TbRoleMenuInfo) rolemenu.get(0);
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

	public void delete(Session sess, TbRoleMenuInfo m) {
		try {
			sess.delete(m);
			sess.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} 	
	}

	public TbRoleMenuInfo get(Session sess, String rolemenu_id) {
		try {
			List ms = sess.createQuery("from TbRoleMenuInfo m where m.roleMenuSid="+rolemenu_id).list();
			if (ms != null && ms.size() > 0) {
				return (TbRoleMenuInfo) ms.get(0);
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

	public void deleteMenu(Session sess, String menu_id) {
		try {
			String hql = "delete TbRoleMenuInfo where menuId in(select m.menuId  from TbMenuInfo m where m.menuId="+menu_id+" or m.pmenuId="+menu_id+")";
			Query q = sess.createQuery(hql);
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
			String hql = "delete TbRoleMenuInfo where roleId=?";
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

	public List findAllmenu_byroleid(Session sess, String roleid) {
		try {
			ScrollableResults srs = sess.createQuery(
					"select rm.menuId as menuid,m.menuName as menuname  from TbRoleMenuInfo rm,TbMenuInfo m where  rm.menuId=m.menuId and rm.roleId=" + roleid + " and m.menuId<>0 order by m.menuName").scroll();
			List res=new ArrayList();
			while(srs.next()){
				Map map = new HashMap();
				map.put("menuid",srs.getInteger(0));
				map.put("menuname", srs.getString(1));
                res.add(map);
            }
			if (res != null && res.size()>0) {
				return res;
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
	
	
	public void save(Session sess, TbRoleMenuInfo rolemenu) {
		try {
			sess.save(rolemenu);
			sess.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sess != null) {
//				sess.close();
			}
		}
		
	}
}
