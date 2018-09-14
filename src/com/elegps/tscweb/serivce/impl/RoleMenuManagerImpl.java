package com.elegps.tscweb.serivce.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.elegps.tscweb.comm.HibernateUtil;
import com.elegps.tscweb.dao.RoleMenuDaoFactory;
import com.elegps.tscweb.dao.impl.RoleMenuDaoHibernate;
import com.elegps.tscweb.exception.MessageException;
import com.elegps.tscweb.model.TbGpsMsListInfo;
import com.elegps.tscweb.model.TbGrpMsListInfo;
import com.elegps.tscweb.model.TbRoleInfo;
import com.elegps.tscweb.model.TbRoleMenuInfo;
import com.elegps.tscweb.serivce.RoleMenuManager;
public class RoleMenuManagerImpl implements RoleMenuManager {

	private RoleMenuDaoFactory rolemenudao;

	public RoleMenuManagerImpl() throws MessageException {
		try {
			rolemenudao = new RoleMenuDaoHibernate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new MessageException("初始化业务逻辑组件出现异常...");
		}
	}

	public int getRoleMenu_SearchCount(String role_id, String menu_id) {
		Transaction tx=null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			Integer count=rolemenudao.findRoleMenu_SearchCount(sess,role_id,menu_id);
			if (count!= null) {
				tx.commit();
				return count;
			}
			else{
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

		/**
		    * 根据指定页及查询条件取得记录信息
		    * @param pageNo
		    * @param pageSize
		    * @param role_id
		    * @param menu_id
		    * @return  指定页及查询条件取得记录信息
		    */
		public List getRoleMenu_SearchByPage(int pageNo,
				int pageSize, String role_id, String menu_id) {
			Transaction tx=null;
			try {
				Session sess = HibernateUtil.currentSession();
				tx = sess.beginTransaction();
				List ml = rolemenudao.findRoleMenu_SearchByPage(sess, pageNo, pageSize,
						role_id,menu_id);
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

		public String createRoleMenuInfo(String role_id, String menu_id) {
			Transaction tx=null;
			try {
				Session sess = HibernateUtil.currentSession();
				tx = sess.beginTransaction();
				Date dateime=new Date();
				TbRoleMenuInfo rolemenuinfo = rolemenudao.get_ByRoMeId(sess,role_id,menu_id);
				if(rolemenuinfo==null){
					TbRoleMenuInfo rolemenu=new TbRoleMenuInfo();
					rolemenu.setRoleId(Integer.parseInt(role_id));
					rolemenu.setMenuId(Integer.parseInt(menu_id));
					rolemenu.setCreate_time(dateime);
					sess.save(rolemenu);
					tx.commit();
					return rolemenu.getRoleMenuSid().toString();
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

		public Boolean deleteRoleMenu(String[] rolemenu_id) {
			Transaction tx = null;
			try {
				Session sess = HibernateUtil.currentSession();
				tx = sess.beginTransaction();
				for (int i = 0; i < rolemenu_id.length; i++) {
					String emp = rolemenu_id[i];
					TbRoleMenuInfo rolemenu = rolemenudao.get(sess, emp);
					if (rolemenu != null) {
						rolemenudao.delete(sess, rolemenu);
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

		public List getMenu_info(String roleid) {
			Transaction tx=null;
			try {
				Session sess = HibernateUtil.currentSession();
				tx = sess.beginTransaction();
				List ml = rolemenudao.findAllmenu_byroleid(sess,roleid);
				if (ml != null && ml.size() > 0) {
					tx.commit();
					return ml;
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

		public String createRoleMenuInfo_ByRoleid(String roleid, String[] menuid) {
			Transaction tx=null;
			try {
				Session sess = HibernateUtil.currentSession();
				tx = sess.beginTransaction();
				rolemenudao.deleteRole(sess, roleid);
				if(menuid==null||menuid.length<1){
					tx.commit();
					return new String("删除成功！");
				}
				for(int i=0;i<menuid.length;i++) {
					TbRoleMenuInfo rolemenu = new TbRoleMenuInfo();
					Date date = new Date();
					rolemenu.setRoleId(Integer.parseInt(roleid));
					rolemenu.setMenuId(Integer.parseInt(menuid[i]));
					rolemenu.setCreate_time(date);
					rolemenudao.save(sess, rolemenu);
				}
				tx.commit();
				return new String("批量添加成功!");
			} catch (Exception e) {
				if (null != tx)
					tx.rollback();
				e.printStackTrace();
			} finally {
				HibernateUtil.closeSession();
			}
			return new String("批量添加失败!");
		}
	
}
