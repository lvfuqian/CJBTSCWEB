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
import com.elegps.tscweb.dao.UserDaoFactory;
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
import com.elegps.tscweb.dao.impl.UserDaoHibernate;
import com.elegps.tscweb.dao.impl.UserRoleDaoHibernate;
import com.elegps.tscweb.exception.MessageException;
import com.elegps.tscweb.model.TbAgentInfo;
import com.elegps.tscweb.model.TbDdmsMsListInfo;
import com.elegps.tscweb.model.TbGpsMsListInfo;
import com.elegps.tscweb.model.TbGrpInfo;
import com.elegps.tscweb.model.TbGrpMsListInfo;
import com.elegps.tscweb.model.TbMenuInfo;
import com.elegps.tscweb.model.TbMsInfo;
import com.elegps.tscweb.model.TbRoleInfo;
import com.elegps.tscweb.model.TbRoleUserInfo;
import com.elegps.tscweb.model.TbUserInfo;
import com.elegps.tscweb.serivce.DdmsMsManager;
import com.elegps.tscweb.serivce.GpsMsManager;
import com.elegps.tscweb.serivce.GrpMsManager;
import com.elegps.tscweb.serivce.MenuManager;
import com.elegps.tscweb.serivce.RoleManager;
import com.elegps.tscweb.serivce.UserManager;

public class UserManagerImpl implements UserManager{

	private UserDaoFactory userdao;
	private UserRoleDaoFactory userroledao;
	public UserManagerImpl() throws MessageException {
		try {
			userdao = new UserDaoHibernate();
			userroledao =new UserRoleDaoHibernate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new MessageException("初始化业务逻辑组件出现异常...");
		}
	}

	public int getPageCount(int count, int pageSize) {
		return (count + pageSize - 1) / pageSize;
	}

	public int getUser_SearchCount(String user_name,int agent_id) {
		Transaction tx = null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			Integer count = userdao.findUser_SearchCount(sess, user_name,agent_id);
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

	public List getTbUserInfoby_name(int pageNo, int pageSize,
			String user_name ,int agent_id) {
		Transaction tx = null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			List ml = userdao.findUserInfo_SearchByPage(sess, pageNo, pageSize,
					user_name,agent_id);
			if (ml != null && ml.size() > 0) {
				tx.commit();
				return ml;
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

	public String createUser(String user_name, String psw,String roleid,String agentid,String epid) {
		Transaction tx=null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			Date dateime=new Date();
			TbUserInfo userinfo = userdao.get_byname(sess, user_name);
			if(userinfo==null){
				TbUserInfo user=new TbUserInfo();
				user.setUserName(user_name);
				user.setUserPassword(psw);
				user.setAgent_Id(Integer.parseInt(agentid));
				user.setEp_Id(Integer.parseInt(epid));
				user.setCreate_time(dateime);
				sess.save(user);
				TbRoleUserInfo userrole=new TbRoleUserInfo();
				userrole.setUserId(user.getUserId());
				userrole.setRoleSid(Integer.parseInt(roleid));
				userrole.setCreate_time(dateime);
				sess.save(userrole);
				tx.commit();
				return user.getUserId().toString();
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

	public Boolean deleteUser(String[] user_id) {
		Transaction tx = null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			for (int i = 0; i < user_id.length; i++) {
				String emp = user_id[i];
				TbUserInfo user = userdao.get(sess, emp);
				if (user != null) {
					userdao.delete(sess, user);
					userroledao.deleteUser(sess, emp);
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

	public TbUserInfo getUserInfoby_userid(String user_id) {
		Transaction tx=null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			TbUserInfo userinfo=userdao.get(sess, user_id);
			if(userinfo!=null){
				tx.commit();
				return userinfo;
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

	public String modifyUser(String user_id, String user_name, String psw,String roleid,String agentid,String epid) {
		Transaction tx=null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			TbUserInfo userinfo = userdao.get_byname(sess,user_id,user_name);
			if(userinfo!=null){
				tx.commit();
				return null;
			}
			TbUserInfo user = userdao.get(sess, user_id);
			Date datetime=new Date();
			if (user != null) {
				user.setUserName(user_name);
				user.setUserPassword(psw);
				user.setCreate_time(datetime);
				user.setAgent_Id(Integer.parseInt(agentid));
				user.setEp_Id(Integer.parseInt(epid));
				sess.update(user);
				TbRoleUserInfo userroleinfo = userroledao.getRoleid_byUserid(sess,user_id);
				if(userroleinfo!=null){
					if(roleid.equals("-1")){
						//删除
						sess.delete(userroleinfo);
						tx.commit();
						return user.getUserId().toString();
					}else{
						//更新
						userroleinfo.setRoleSid(Integer.parseInt(roleid));
						sess.update(userroleinfo);
						tx.commit();
						return user.getUserId().toString();
					}
					
				}else{
					if(roleid.equals("-1")){
						tx.commit();
						return user.getUserId().toString();
					}else{
						//增加
						TbRoleUserInfo userrole=new TbRoleUserInfo();
						userrole.setUserId(Integer.parseInt(user_id));
						userrole.setRoleSid(Integer.parseInt(roleid));
						sess.save(userrole);
						tx.commit();
						return user.getUserId().toString();
					}
				}
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

	public List<TbUserInfo> getAllUser_Info() {
		Transaction tx = null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			List ml = userdao.findAllUserInfo(sess);
			if (ml != null && ml.size() > 0) {
				List<TbUserInfo> result = new ArrayList<TbUserInfo>();
				for (Object obj : ml) {
					TbUserInfo me = (TbUserInfo) obj;
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

	public List<TbUserInfo> getNotUser_Info() {
		Transaction tx = null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			List ml = userdao.findNotUserInfo(sess);
			if (ml != null && ml.size() > 0) {
				List<TbUserInfo> result = new ArrayList<TbUserInfo>();
				for (Object obj : ml) {
					TbUserInfo me = (TbUserInfo) obj;
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

	
	
	public List<TbAgentInfo> getAllAgent_Info() {
		Transaction tx = null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			List ml = userdao.getAllAgentInfo(sess);
			if (ml != null && ml.size() > 0) {
				List<TbAgentInfo> result = new ArrayList<TbAgentInfo>();
				for (Object obj : ml) {
					TbAgentInfo me = (TbAgentInfo) obj;
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

	
	//2009-11-20  修改当前用户密码
	public Boolean ChangePassword(String name, String psasword) {
		Transaction tx=null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			TbUserInfo user = userdao.get_User(sess, name);
			if (user != null) {
				user.setUserName(name);
				user.setUserPassword(psasword);
				sess.update(user);
				tx.commit();
				return true;
			}
		} catch (Exception e) {
			if (null != tx)
				tx.rollback();
			e.printStackTrace();
			return false;
		} finally {
			HibernateUtil.closeSession();
		}
		return false;
	}

	@Override
	public TbUserInfo get_User(String name) {
		Transaction tx=null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			TbUserInfo user = userdao.get_User(sess, name);
			return user;
		} catch (Exception e) {
			if (null != tx)
				tx.rollback();
			e.printStackTrace();
			return null;
		} finally {
			HibernateUtil.closeSession();
		}
	}
	
	@Override
	public Boolean updateUser(TbUserInfo user) {
		Transaction tx=null;
		try{
			Session session=HibernateUtil.currentSession();
			tx=session.beginTransaction();
			if(user!=null){
				
				userdao.updateInfo(session, user);
				tx.commit();
				
				return true;
			}else{
				tx.commit();
				return false;
			}
		}catch(Exception e){
			if(tx!=null){
				tx.rollback();
				e.printStackTrace();
			}
		}
		finally{
			HibernateUtil.closeSession();
		}
		return false;
	}


}
