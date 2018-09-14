package com.elegps.tscweb.dao;

import org.hibernate.Session;


import com.elegps.tscweb.model.TbMenuInfo;


import java.util.List;


public interface MenuDaoFactory
{
	
	 /**
	 * 根据查询条件取得记录总数
	 * @param menu_name
	 * @return  查询条件取得记录总数
	 */
    Integer findMenu_SearchCount(Session sess,String menu_name);
    
    
    /**
	    * 根据指定页及查询条件取得记录信息
	    * @param pageNo
	    * @param pageSize
	    * @param menu_name
	    * @return  指定页及查询条件取得记录信息
	    */
  List findMenuInfo_SearchByPage(Session sess,int pageNo,int pageSize,String menu_name);
 
  
    /**
	 * 根据主键加载菜单信息
	 * @param sess 持久化操作所需要的Hiberate Session
	 * @param menu_id 需要加载菜单id
	 */
  TbMenuInfo get(Session sess , String menu_id);  
  
  /**
	 * 根据菜单名加载菜单信息
	 * @param sess 持久化操作所需要的Hiberate Session
	 * @param menu_name 需要加载菜单名
	 */
  TbMenuInfo get_byname(Session sess , String menu_name);  
  
  
  /**
	 * 根据菜单名加载菜单信息
	 * @param sess 持久化操作所需要的Hiberate Session
	 * @parammenu_id  需要加载菜单id
	 * @param menu_name  需要加载菜单名
	 */
 TbMenuInfo get_byname(Session sess ,String menu_id, String menu_name);  
  
  /**
   * 删除对象
   * @param sess
   * @param m
   */
  void delete(Session sess,String m);
  
  /**
   * 
   * @return 所有菜单信息
   */
  List findAllMenuInfo(Session sess);
  
  
  /**
	 * 用于角色与菜单关系批量添加时(取得该roleid没有添加的菜单信息)
	 * @param sess
	 * @param roleid
	 * @return
	 */
	List findMenu_Info_byroleid(Session sess,String roleid);
}