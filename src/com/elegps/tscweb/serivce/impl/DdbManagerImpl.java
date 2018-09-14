package com.elegps.tscweb.serivce.impl;

import java.util.List;

import org.hibernate.Session;

import com.elegps.tscweb.comm.DDBHibernateUtil;
import com.elegps.tscweb.comm.HibernateUtil;
import com.elegps.tscweb.dao.DdbDaoFactory;
import com.elegps.tscweb.dao.impl.DdbDaoHibernate;
import com.elegps.tscweb.model.DdbSearchBean;
import com.elegps.tscweb.model.TabSysconfig;
import com.elegps.tscweb.model.TabSysroleinfo;
import com.elegps.tscweb.model.TabSysusersinfo;
import com.elegps.tscweb.serivce.DdbManager;

public class DdbManagerImpl implements DdbManager {
	private DdbDaoFactory ddbDao;

	public DdbManagerImpl() {
		ddbDao = new DdbDaoHibernate();
	}

	public void create(Object objects) {
		Session session = DDBHibernateUtil.currentSession();
		ddbDao.create(session, objects);
	}

	public void delete(int userid) {
		Session session = DDBHibernateUtil.currentSession();
		String hql_user = "DELETE  FROM  TabSysusersinfo WHERE userId=?";
		String hql_config = "DELETE FROM  TabSysconfig WHERE userId=?";
		String hql_role = "DELETE FROM TabSysuserroleId WHERE userId=?";
		ddbDao.executeQuery(session, hql_user, userid);
		ddbDao.executeQuery(session, hql_config, userid);
		ddbDao.executeQuery(session, hql_role, userid);
	}

	@SuppressWarnings("unchecked")
	public TabSysconfig getBean(String hql, Object... objects) {
		Session session = DDBHibernateUtil.currentSession();
		hql = " FROM  TabSysserverdbinfo WHERE  sId=?";
		return (TabSysconfig) ddbDao.getBean(session, hql, objects);
	}

	@SuppressWarnings("unchecked")
	public List<TabSysconfig> getListBean(int pageNo, int pageSize) {
		Session session = DDBHibernateUtil.currentSession();
		session.clear();
		int fromIdx = (pageNo - 1) * pageSize;
		String hql = "  FROM " +
				"TabSysconfig as tbc  WHERE " +
				"tbc.userId=tbc.tabSysusersinfo.userId  ORDER BY  tbc.userId DESC ";
		return (List<TabSysconfig>) ddbDao.listObject(session, hql, fromIdx,
				pageSize);
	}

	@SuppressWarnings("unchecked")
	public List<TabSysconfig> getListBean(int pageNo, int pageSize,StringBuffer ms_id) {
		Session session = DDBHibernateUtil.currentSession();
		session.clear();
		int fromIdx = (pageNo - 1) * pageSize;
		String hql = "  FROM " +
				"TabSysconfig as tbc  WHERE " +
				"tbc.userId=tbc.tabSysusersinfo.userId  ";
		
				if(ms_id.length() == 0){
					//管理员查询
				}else{
					hql+=" AND tbc.msid in ("+ ms_id +")";
				}
				
				hql += "ORDER BY  tbc.userId DESC ";
		return (List<TabSysconfig>) ddbDao.listObject(session, hql, fromIdx,
				pageSize);
	}
	
	@SuppressWarnings("unchecked")
	public List<TabSysconfig> listAll(int pageNo, int pageSize,
			String userName, String msid,String loginid) {
		Session session = DDBHibernateUtil.currentSession();
		session.clear();
		int fromIdx = (pageNo - 1) * pageSize;
		StringBuilder hql = new StringBuilder(
				"  FROM TabSysconfig as tbc WHERE 1=1 ");	
		if (userName != null && !"".equals(userName)) {
			hql.append(" AND  tbc.tabSysusersinfo.userName  like ? ");
		}
		if (msid != null && !"".equals(msid)) {
			hql.append("  AND tbc.msid like ?");
		}
		if (loginid != null && !"".equals(loginid)) {
			hql.append("  AND tbc.tabSysusersinfo.loginId like ?");
		}
		hql.append("  ORDER BY tbc.userId DESC");
		if (userName != null && !"".equals(userName)) {
			return ddbDao.listObject(session, hql.toString(), fromIdx,
					pageSize, "%"+userName+"%");
		} else if (msid != null && !"".equals(msid)) {
			return ddbDao.listObject(session, hql.toString(), fromIdx,
					pageSize, "%"+msid+"%");
		} else if (loginid != null && !"".equals(loginid)) {
			return ddbDao.listObject(session, hql.toString(), fromIdx,
					pageSize, "%"+loginid+"%");
		} else {
			return ddbDao.listObject(session, hql.toString(), fromIdx,
					pageSize, "%"+userName+"%", msid,"%"+loginid+"%");
		}
	}
	@SuppressWarnings("unchecked")
	public List<TabSysconfig> listAll(int pageNo, int pageSize,
			String userName, String msid,String loginid,StringBuffer ms_id ) {
		Session session = DDBHibernateUtil.currentSession();
		session.clear();
		int fromIdx = (pageNo - 1) * pageSize;
		StringBuilder hql = new StringBuilder(
				"  FROM TabSysconfig as tbc WHERE 1=1 ");
		if(ms_id.length() == 0){
			//管理员查询
		}else{
			hql.append(" AND tbc.msid in ("+ ms_id +")");
		}
		if (userName != null && !"".equals(userName)) {
			hql.append(" AND  tbc.tabSysusersinfo.userName  like ? ");
		}
		if (msid != null && !"".equals(msid)) {
			hql.append("  AND tbc.msid like ?");
		}
		if (loginid != null && !"".equals(loginid)) {
			hql.append("  AND tbc.tabSysusersinfo.loginId like ?");
		}
		hql.append("  ORDER BY tbc.userId DESC");
		if (userName != null && !"".equals(userName)) {
			return ddbDao.listObject(session, hql.toString(), fromIdx,
					pageSize, "%"+userName+"%");
		} else if (msid != null && !"".equals(msid)) {
			return ddbDao.listObject(session, hql.toString(), fromIdx,
					pageSize, "%"+msid+"%");
		} else if (loginid != null && !"".equals(loginid)) {
			return ddbDao.listObject(session, hql.toString(), fromIdx,
					pageSize, "%"+loginid+"%");
		} else {
			return ddbDao.listObject(session, hql.toString(), fromIdx,
					pageSize, "%"+userName+"%", msid,"%"+loginid+"%");
		}
	}
	
	public int totalCount(String userName, String msid,String loginid,StringBuffer ms_id) {
		Session session = DDBHibernateUtil.currentSession();
		session.clear();
		StringBuilder hql = new StringBuilder("SELECT COUNT(*) FROM TabSysconfig as tsc WHERE 1=1");
		try {
			if(ms_id.length() == 0){
				//管理员查询
			}else{
				hql.append(" AND tsc.msid in ("+ ms_id +")");
			}
			if (userName != null && !"".equals(userName)) {
				hql.append(" AND tsc.tabSysusersinfo.userName  like ?  ");
			}
			if (msid != null && !"".equals(msid)) {
				hql.append(" AND tsc.msid like ?");
			}
			if (loginid != null && !"".equals(loginid)) {
				hql.append("  AND tsc.tabSysusersinfo.loginId like ?");
			}
			if (userName != null && !"".equals(userName)) {
				return ddbDao.toaltCount(session, hql.toString(),  "%"+userName+"%");
			} else if (msid != null && !"".equals(msid)) {
				return ddbDao.toaltCount(session, hql.toString(),  "%"+msid+"%");
			} else if (loginid != null && !"".equals(loginid)) {
				return ddbDao.toaltCount(session, hql.toString(),  "%"+loginid+"%");
			} else if (userName != null && !"".equals(userName) && msid != null
					&& !"".equals(msid)&&loginid!=null&&!"".equals(loginid)) {
				return ddbDao.toaltCount(session, hql.toString(),  "%"+userName+"%",
						msid,"%"+loginid+"%");
			} else {
				return ddbDao.toaltCount(session, hql.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ddbDao.toaltCount(session, hql.toString());
	}
	public int totalCount(String userName, String msid,String loginid) {
		Session session = DDBHibernateUtil.currentSession();
		session.clear();
		StringBuilder hql = new StringBuilder("SELECT COUNT(*) FROM TabSysconfig as tsc WHERE 1=1");
		try {
			
			if (userName != null && !"".equals(userName)) {
				hql.append(" AND tsc.tabSysusersinfo.userName  like ?  ");
			}
			if (msid != null && !"".equals(msid)) {
				hql.append(" AND tsc.msid like ?");
			}
			if (loginid != null && !"".equals(loginid)) {
				hql.append("  AND tsc.tabSysusersinfo.loginId like ?");
			}
			if (userName != null && !"".equals(userName)) {
				return ddbDao.toaltCount(session, hql.toString(),  "%"+userName+"%");
			} else if (msid != null && !"".equals(msid)) {
				return ddbDao.toaltCount(session, hql.toString(),  "%"+msid+"%");
			} else if (loginid != null && !"".equals(loginid)) {
				return ddbDao.toaltCount(session, hql.toString(),  "%"+loginid+"%");
			} else if (userName != null && !"".equals(userName) && msid != null
					&& !"".equals(msid)&&loginid!=null&&!"".equals(loginid)) {
				return ddbDao.toaltCount(session, hql.toString(),  "%"+userName+"%",
						msid,"%"+loginid+"%");
			} else {
				return ddbDao.toaltCount(session, hql.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ddbDao.toaltCount(session, hql.toString());
	}
	public int update(String hql, Object... objects) {
		Session session = DDBHibernateUtil.currentSession();
		return ddbDao.update(session, hql, objects);
	}

	@SuppressWarnings("unchecked")
	public List<TabSysconfig> getBeanTotal(Object... objects) {
		Session session = DDBHibernateUtil.currentSession();
		String hql = " FROM TabSysconfig WHERE  msid=?";
		int fromIdx = (-1 - 1) * -1;
		return (List<TabSysconfig>) ddbDao.listObject(session, hql,
				fromIdx, -1,objects);
	}

	public int getMaxId(Object obj) {
		Session session = DDBHibernateUtil.currentSession();
		return ddbDao.getMaxId(session, obj);
	}

	@SuppressWarnings("unchecked")
	public TabSysconfig getBean(int id) {
		Session session = DDBHibernateUtil.currentSession();
		String hql = " FROM  TabSysconfig tbc WHERE tbc.userId=? ";
		return (TabSysconfig) ddbDao.getBean(session, hql, id);
	}

	public int totalCount(TabSysconfig tsc) {
		// TODO Auto-generated method stub
		return 0;
	}

	public List<TabSysroleinfo> listRole(int pageNo, int pageSize) {
		Session session=DDBHibernateUtil.currentSession();
//		int fromIdx = (pageNo - 1) * pageSize;
		String hql=" FROM TabSysroleinfo as tbr";
		return ddbDao.listObjectInfo(session, hql, -1, -1);
	}
	
	public Object getRoleBean(String hql, Object... objects) {
		// TODO Auto-generated method stub
		return null;
	}
}
