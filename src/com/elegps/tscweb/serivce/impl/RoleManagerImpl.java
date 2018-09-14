package com.elegps.tscweb.serivce.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.elegps.tscweb.comm.HibernateUtil;
import com.elegps.tscweb.dao.DdmsMsDaoFactory;
import com.elegps.tscweb.dao.GpsDaoFactory;
import com.elegps.tscweb.dao.GpsMsDaoFactory;
import com.elegps.tscweb.dao.GrpDaoFactory;
import com.elegps.tscweb.dao.GrpMsDaoFactory;
import com.elegps.tscweb.dao.MenuDaoFactory;
import com.elegps.tscweb.dao.MsDaoFactory;
import com.elegps.tscweb.dao.RoleDaoFactory;
import com.elegps.tscweb.dao.RoleMenuDaoFactory;
import com.elegps.tscweb.dao.UserRoleDaoFactory;
import com.elegps.tscweb.dao.impl.DdmsMsDaoHibernate;
import com.elegps.tscweb.dao.impl.GpsDaoHibernate;
import com.elegps.tscweb.dao.impl.GpsMsDaoHibernate;
import com.elegps.tscweb.dao.impl.GrpDaoHibernate;
import com.elegps.tscweb.dao.impl.GrpMsDaoHibernate;
import com.elegps.tscweb.dao.impl.MenuDaoHibernate;
import com.elegps.tscweb.dao.impl.MsDaoHibernate;
import com.elegps.tscweb.dao.impl.RoleDaoHibernate;
import com.elegps.tscweb.dao.impl.RoleMenuDaoHibernate;
import com.elegps.tscweb.dao.impl.UserRoleDaoHibernate;
import com.elegps.tscweb.exception.MessageException;
import com.elegps.tscweb.model.TbDdmsMsListInfo;
import com.elegps.tscweb.model.TbGpsMsListInfo;
import com.elegps.tscweb.model.TbGrpInfo;
import com.elegps.tscweb.model.TbGrpMsListInfo;
import com.elegps.tscweb.model.TbMenuInfo;
import com.elegps.tscweb.model.TbMsInfo;
import com.elegps.tscweb.model.TbRoleInfo;
import com.elegps.tscweb.serivce.DdmsMsManager;
import com.elegps.tscweb.serivce.GpsMsManager;
import com.elegps.tscweb.serivce.GrpMsManager;
import com.elegps.tscweb.serivce.MenuManager;
import com.elegps.tscweb.serivce.RoleManager;

public class RoleManagerImpl implements RoleManager {

	private RoleDaoFactory roledao;
	private RoleMenuDaoFactory rolemenudao;
	private UserRoleDaoFactory userroledao;

	public RoleManagerImpl() throws MessageException {
		try {
			roledao = new RoleDaoHibernate();
			rolemenudao =new RoleMenuDaoHibernate(); 
			userroledao =new UserRoleDaoHibernate(); 
		} catch (Exception e) {
			e.printStackTrace();
			throw new MessageException("初始化业务逻辑组件出现异常...");
		}
	}

	// 跟据角色名查询总条数
	public int getRole_SearchCount(String role_name) {
		Transaction tx = null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			Integer count = roledao.findRole_SearchCount(sess, role_name);
			if (count != null) {
				tx.commit();
				return count;
			} else {
				tx.commit();
				return 0;
			}
		} catch (Exception e) {
			if (null != tx)
				tx.rollback();
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
		}
		return 0;
	}

	/**
	 * 根据每页记录数，总记录数获取总页数
	 * 
	 * @param count
	 *            总记录数
	 * @param pageSize
	 *            每页显示的记录数
	 * @return 计算得到的总页数
	 */
	public int getPageCount(int count, int pageSize) {
		return (count + pageSize - 1) / pageSize;
	}

	public List<TbRoleInfo> getTbRoleInfoby_name(int pageNo, int pageSize,
			String role_name) {
		Transaction tx = null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			List ml = roledao.findRoleInfo_SearchByPage(sess, pageNo, pageSize,
					role_name);
			if (ml != null && ml.size() > 0) {
				List<TbRoleInfo> result = new ArrayList<TbRoleInfo>();
				for (Object obj : ml) {
					TbRoleInfo me = (TbRoleInfo) obj;
					result.add(me);
				}
				tx.commit();
				return result;
			} else {
				tx.commit();
				return null;
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

	/**
	 * 根据role_id删除记录
	 */
	public Boolean deleteRole(String[] role_id) {
		Transaction tx = null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			for (int i = 0; i < role_id.length; i++) {
				String emp = role_id[i];
				TbRoleInfo role = roledao.get(sess, emp);
				if (role != null) {
					roledao.delete(sess, role);
					rolemenudao.deleteRole(sess, emp);
					userroledao.deleteRole(sess, emp);
				}
			}
			tx.commit();
			return true;
		} catch (Exception e) {
			if (null != tx)
				tx.rollback();
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
		}
		return false;
	}

	public String createRole(String role_name) {
		Transaction tx=null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			Date dateime=new Date();
			TbRoleInfo roleinfo = roledao.get_byname(sess, role_name);
			if(roleinfo==null){
				TbRoleInfo role=new TbRoleInfo();
				role.setRoleName(role_name);
				role.setCreate_time(dateime);
				sess.save(role);
				tx.commit();
				return role.getRoleId().toString();
			}
			else{
				tx.commit();
				return null;
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

	/**
	 * 返回特定指定条件角色信息
	 * @param role_id 角色ID
	 */
	public TbRoleInfo getRoleInfoby_roleid(String role_id) {
		Transaction tx=null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			TbRoleInfo roleinfo=roledao.get(sess, role_id);
			if(roleinfo!=null){
				tx.commit();
				return roleinfo;
			}else{
				tx.commit();
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			HibernateUtil.closeSession();
		}
		return null;
	}

	public String modifyRole(String role_id, String role_name) {
		Transaction tx=null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			TbRoleInfo roleinfo = roledao.get_byname(sess,role_id,role_name);
			if(roleinfo!=null){
				tx.commit();
				return null;
			}
			TbRoleInfo role = roledao.get(sess, role_id);
			Date datetime=new Date();
			if (role != null) {
				role.setRoleName(role_name);
				role.setCreate_time(datetime);
				sess.update(role);
				tx.commit();
				return role.getRoleId().toString();
			}else{
				tx.commit();
				return null;
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

	public List<TbRoleInfo> getAllRole_Info() {
		Transaction tx = null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			List ml = roledao.findAllRoleInfo(sess);
			if (ml != null && ml.size() > 0) {
				List<TbRoleInfo> result = new ArrayList<TbRoleInfo>();
				for (Object obj : ml) {
					TbRoleInfo me = (TbRoleInfo) obj;
					result.add(me);
				}
				tx.commit();
				return result;
			} else {
				tx.commit();
				return null;
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
