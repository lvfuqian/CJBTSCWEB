package com.elegps.tscweb.dao;

import org.hibernate.Session;

import com.elegps.tscweb.model.TbGpsInfo;
import com.elegps.tscweb.model.TbMenuInfo;
import com.elegps.tscweb.model.TbRoleInfo;
import com.elegps.tscweb.model.TbRoleMenuInfo;


import java.util.List;

public interface RoleMenuDaoFactory
{
	 /**
	 * 根据查询条件取得记录总数
	 * @param role_id
	 * @param menu_id
	 * @return  查询条件取得记录总数
	 */
    Integer findRoleMenu_SearchCount(Session sess,String role_id,String menu_id);
    
    
    /**
	    * 根据指定页及查询条件取得记录信息
	    * @param pageNo
	    * @param pageSize
	    * @param role_id
	    * @param menu_id
	    * @return  指定页及查询条件取得记录信息
	    */
  List findRoleMenu_SearchByPage(Session sess,int pageNo,int pageSize,String role_id,String menu_id);
  
  /**
   * 根据角色ID和菜单ID
   * @param sess
   * @param role_id
   * @param menu_id
   * @return
   */
  TbRoleMenuInfo get_ByRoMeId(Session sess,String role_id,String menu_id);
  
  /**
   * 根据主键加载信息
   * @param sess
   * @param rolemenu_id
   */
  TbRoleMenuInfo get(Session sess , String rolemenu_id); 
  
  
  /**
   * 删除对象
   * @param sess
   * @param m
   */
  void delete(Session sess,TbRoleMenuInfo m);
  
  
  /**
   * 根据角色ID(role_id)删除角色菜单对应关系
   * @param role_id
   * @return
   */
  void deleteRole(Session sess,String role_id);
  
  
  /**
   * 根据菜单ID(menu_id)删除角色菜单对应关系
   * @param menu_id
   * @return
   */
  void deleteMenu(Session sess,String menu_id);
  
  /**
   * 跟据角色ID查询已经存在的菜单信息
   * @param roleid
   * @return
   */
  List findAllmenu_byroleid(Session sess,String roleid);
  
  /**
   * 保存
   * @param sess
   * @param rolemenu
   */
  void save(Session sess, TbRoleMenuInfo rolemenu);
	
}