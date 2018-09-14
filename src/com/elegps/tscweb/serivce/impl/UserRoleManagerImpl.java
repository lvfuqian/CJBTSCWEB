package com.elegps.tscweb.serivce.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.elegps.tscweb.comm.HibernateUtil;
import com.elegps.tscweb.dao.RoleMenuDaoFactory;
import com.elegps.tscweb.dao.UserRoleDaoFactory;
import com.elegps.tscweb.dao.impl.RoleMenuDaoHibernate;
import com.elegps.tscweb.dao.impl.UserRoleDaoHibernate;
import com.elegps.tscweb.exception.MessageException;
import com.elegps.tscweb.model.TbGpsMsListInfo;
import com.elegps.tscweb.model.TbRoleInfo;
import com.elegps.tscweb.model.TbRoleMenuInfo;
import com.elegps.tscweb.model.TbRoleUserInfo;
import com.elegps.tscweb.serivce.RoleMenuManager;
import com.elegps.tscweb.serivce.UserRoleManager;
public class UserRoleManagerImpl implements UserRoleManager {

	private UserRoleDaoFactory userroledao;

	public UserRoleManagerImpl() throws MessageException {
		try {
			userroledao = new UserRoleDaoHibernate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new MessageException("初始化业务逻辑组件出现异常...");
		}
	}

	public int getUserRole_SearchCount(String user_id, String role_id) {
		Transaction tx=null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			Integer count=userroledao.findUserRole_SearchCount(sess,user_id,role_id);
			if (count!= null) {
				tx.commit();
				return count;
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

	public List getUserRole_SearchByPage(int pageNo, int pageSize,
			String user_id, String role_id) {
		Transaction tx=null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			List ml = userroledao.findUserRole_SearchByPage(sess, pageNo, pageSize,
					user_id,role_id);
			if (ml != null && ml.size() > 0) {
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

	public String createUserRoleInfo(String user_id, String role_id) {
		Transaction tx=null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			Date dateime=new Date();
			TbRoleUserInfo userroleinfo = userroledao.get_ByUsRoId(sess,user_id,role_id);
			if(userroleinfo==null){
				TbRoleUserInfo userrole=new TbRoleUserInfo();
				userrole.setUserId(Integer.parseInt(user_id));
				userrole.setRoleSid(Integer.parseInt(role_id));
				userrole.setCreate_time(dateime);
				sess.save(userrole);
				tx.commit();
				return userrole.getUserRoleSid().toString();
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

	public Boolean deleteUserRole(String[] userrole_id) {
		Transaction tx = null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			for (int i = 0; i < userrole_id.length; i++) {
				String emp = userrole_id[i];
				TbRoleUserInfo userrole = userroledao.get(sess, emp);
				if (userrole != null) {
					userroledao.delete(sess, userrole);
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

	public String getRoidinfo_ByUserid(String user_id) {
		Transaction tx=null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			TbRoleUserInfo userroleinfo = userroledao.getRoleid_byUserid(sess,user_id);
			if (userroleinfo!=null) {
				tx.commit();
				return userroleinfo.getRoleSid().toString();
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
