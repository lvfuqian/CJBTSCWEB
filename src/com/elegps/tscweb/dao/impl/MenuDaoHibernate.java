package com.elegps.tscweb.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.helpers.CountingQuietWriter;
import org.hibernate.Query;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;


import com.elegps.tscweb.comm.HibernateUtil;
import com.elegps.tscweb.dao.MenuDaoFactory;
import com.elegps.tscweb.model.TbGpsInfo;
import com.elegps.tscweb.model.TbMenuInfo;
import com.elegps.tscweb.model.TbRoleMenuInfo;


public class MenuDaoHibernate implements MenuDaoFactory {

	
	/**
	 * 查询指定菜单的个数
	 */
	public Integer findMenu_SearchCount(Session sess, String menu_name) {
		String hql="select count(m.menuName) from TbMenuInfo m,TbMenuInfo m1";
		if (menu_name.equals("")) { // 表示为全部菜单
		}else{
			hql=hql+" where m.menuName like '%"+menu_name+ "%'";
		}
		int i = hql.indexOf("where");
		if (i > 0) {
			hql = hql + " and m1.menuId=m.pmenuId and m.pmenuId!=-1"; //and m.menuId!=0
		} else {
			hql = hql + " where m1.menuId=m.pmenuId and m.pmenuId!=-1";
		}
		hql=hql+" order by m.create_time desc";
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

	public List findMenuInfo_SearchByPage(Session sess, int pageNo,
			int pageSize, String menu_name) {
		String hql="select m.menuId as menuid,m.create_time as create_time,m.menuName as menuName,m.url as url, m1.menuName as pmenuName from TbMenuInfo m,TbMenuInfo m1";
		if (menu_name.equals("")) { // 表示为全部菜单
		}else{
			hql=hql+" where m.menuName like '%"+menu_name+ "%'";
		}
		int i = hql.indexOf("where");
		if (i > 0) {
			hql = hql + " and m1.menuId=m.pmenuId and m.pmenuId!=-1"; //and m.menuId!=0
		} else {
			hql = hql + " where m1.menuId=m.pmenuId and m.pmenuId!=-1";
		}
		hql=hql+" order by m.create_time desc";
		try {
			int offset = (pageNo - 1) * pageSize;
			ScrollableResults srs = sess.createQuery(hql).setFirstResult(offset)
			.setMaxResults(pageSize).scroll();
			List res=new ArrayList();
			while(srs.next()){
				Map map = new HashMap();
				map.put("menuId",srs.getInteger(0));
				map.put("menuName", srs.getString(2));
				map.put("url", srs.getString(3));
				map.put("pmenuName", srs.getString(4));
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

	public void delete(Session sess, String id) {
		try {
			sess.createQuery("delete from TbMenuInfo m where m.menuId="+id+" or m.pmenuId="+id).executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} 	
	}

	public TbMenuInfo get(Session sess, String menu_id) {
		try {
			List ms = sess.createQuery("from TbMenuInfo m where m.menuId="+menu_id).list();
			if (ms != null && ms.size() > 0) {
				return (TbMenuInfo) ms.get(0);
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

	public TbMenuInfo get_byname(Session sess, String menu_name) {
		try {
			List ms = sess.createQuery("from TbMenuInfo m where m.menuName='"+menu_name+"'").list();
			if (ms != null && ms.size() > 0) {
				return (TbMenuInfo) ms.get(0);
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

	public TbMenuInfo get_byname(Session sess, String menu_id, String menu_name) {
		try {
			List ms = sess.createQuery("from TbMenuInfo m where m.menuName='"+menu_name+"' and menuId!="+menu_id).list();
			if (ms != null && ms.size() > 0) {
				return (TbMenuInfo) ms.get(0);
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

	public List findAllMenuInfo(Session sess) {
		String hql="from TbMenuInfo m where m.menuId<>0 order by m.create_time desc";
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

	public List findMenu_Info_byroleid(Session sess, String roleid) {
		try {
			List list=sess.createQuery("from TbMenuInfo m where  m.menuId not in(select rm.menuId from TbRoleMenuInfo rm  where  rm.roleId=" + roleid + ") and m.menuId<>0 order by m.menuName").list();
			if(list!=null){
				return  list;
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
