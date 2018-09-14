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
import com.elegps.tscweb.dao.RoleMenuDaoFactory;
import com.elegps.tscweb.dao.impl.DdmsMsDaoHibernate;
import com.elegps.tscweb.dao.impl.GpsDaoHibernate;
import com.elegps.tscweb.dao.impl.GpsMsDaoHibernate;
import com.elegps.tscweb.dao.impl.GrpDaoHibernate;
import com.elegps.tscweb.dao.impl.GrpMsDaoHibernate;
import com.elegps.tscweb.dao.impl.MenuDaoHibernate;
import com.elegps.tscweb.dao.impl.MsDaoHibernate;
import com.elegps.tscweb.dao.impl.RoleMenuDaoHibernate;
import com.elegps.tscweb.exception.MessageException;
import com.elegps.tscweb.model.TbDdmsMsListInfo;
import com.elegps.tscweb.model.TbGpsMsListInfo;
import com.elegps.tscweb.model.TbGrpInfo;
import com.elegps.tscweb.model.TbGrpMsListInfo;
import com.elegps.tscweb.model.TbMenuInfo;
import com.elegps.tscweb.model.TbMsInfo;
import com.elegps.tscweb.model.TbRoleInfo;
import com.elegps.tscweb.model.TbRoleMenuInfo;
import com.elegps.tscweb.serivce.DdmsMsManager;
import com.elegps.tscweb.serivce.GpsMsManager;
import com.elegps.tscweb.serivce.GrpMsManager;
import com.elegps.tscweb.serivce.MenuManager;

public class MenuManagerImpl implements MenuManager {

	
	private MenuDaoFactory menudao;
	private RoleMenuDaoFactory rolemenudao;

	public MenuManagerImpl() throws MessageException {
		try {
			menudao = new MenuDaoHibernate();
			rolemenudao =new RoleMenuDaoHibernate(); 
		} catch (Exception e) {
			e.printStackTrace();
			throw new MessageException("初始化业务逻辑组件出现异常...");
		}
	}

	// 跟据菜单名查询总条数
	public int getMenu_SearchCount(String menu_name) {
		Transaction tx = null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			Integer count = menudao.findMenu_SearchCount(sess, menu_name);
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

	public List<TbMenuInfo> getTbMenuInfoby_name(int pageNo, int pageSize,
			String menu_name) {
		Transaction tx = null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			List ml = menudao.findMenuInfo_SearchByPage(sess, pageNo, pageSize,
					menu_name);
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

	/**
	 * 根据menu_id删除记录
	 */
	public Boolean deleteMenu(String[] menu_id) {
		Transaction tx = null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			for (int i = 0; i < menu_id.length; i++) {
				String emp = menu_id[i];
//				TbMenuInfo menu = menudao.get(sess, emp);
//				if (menu != null) {
				    rolemenudao.deleteMenu(sess, emp);
					menudao.delete(sess, emp);
//				}
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

	public String createMenu(String menu_name,String pmenu_id, String url) {
		Transaction tx=null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			Date dateime=new Date();
			TbMenuInfo menuinfo=menudao.get_byname(sess, menu_name);
			if(menuinfo==null){
				TbMenuInfo menu = new TbMenuInfo();
				menu.setMenuName(menu_name);
				menu.setUrl(url);
				menu.setPmenuId(Integer.parseInt(pmenu_id));
				menu.setCreate_time(dateime);
				sess.save(menu);
				//默认要把权限付给系统管理员(roleid为0)
				String roleid="0";
				TbRoleMenuInfo rolemenuinfo=rolemenudao.get_ByRoMeId(sess, roleid, menu.getMenuId().toString());
				if(rolemenuinfo==null)
				{
					TbRoleMenuInfo rolemenu=new TbRoleMenuInfo();
					rolemenu.setRoleId(Integer.parseInt(roleid));
					rolemenu.setMenuId(menu.getMenuId());
					rolemenu.setCreate_time(dateime);
					sess.save(rolemenu);
				}
				tx.commit();
				return menu.getMenuId().toString();
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

	public TbMenuInfo getMenuInfoby_menuid(String menu_id) {
		Transaction tx=null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			TbMenuInfo menuinfo=menudao.get(sess, menu_id);
			if(menuinfo!=null){
				tx.commit();
				return menuinfo;
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

	public String modifyMenu(String menu_id, String menu_name, String url,String pmenu_id) {
		Transaction tx=null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			TbMenuInfo menuinfo=menudao.get_byname(sess,menu_id, menu_name);
			if(menuinfo!=null){
				tx.commit();
				return null;
			}
			TbMenuInfo menu = menudao.get(sess, menu_id);
			Date datetime=new Date();
			if (menu != null) {
				menu.setMenuName(menu_name);
				menu.setUrl(url);
				menu.setCreate_time(datetime);
				menu.setPmenuId(Integer.parseInt(pmenu_id));
				sess.update(menu);
				tx.commit();
				return menu.getMenuId().toString();
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

	public List<TbMenuInfo> getAllMenu_Info() {
		Transaction tx = null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			List ml = menudao.findAllMenuInfo(sess);
			if (ml != null && ml.size() > 0) {
				List<TbMenuInfo> result = new ArrayList<TbMenuInfo>();
				for (Object obj : ml) {
					TbMenuInfo me = (TbMenuInfo) obj;
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

	public List getAllMenu_Info_not_Byroleid(String roleid) {
		Transaction tx=null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			List ml = menudao.findMenu_Info_byroleid(sess,roleid);
			if (ml != null && ml.size() > 0) {
				List<TbMenuInfo> result = new ArrayList<TbMenuInfo>();
				for (Object obj : ml) {
					TbMenuInfo me = (TbMenuInfo) obj;
					result.add(me);
				}
				tx.commit();
				return result;
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
}
