package com.elegps.tscweb.dao;

import org.hibernate.Session;

import com.elegps.tscweb.model.TbGpsInfo;
import com.elegps.tscweb.model.TbMenuInfo;
import com.elegps.tscweb.model.TbRoleInfo;


import java.util.List;

public interface RoleDaoFactory
{
	
	 /**
	 * 根据查询条件取得记录总数
	 * @param role_name
	 * @return  查询条件取得记录总数
	 */
    Integer findRole_SearchCount(Session sess,String role_name);
    
    
    /**
	    * 根据指定页及查询条件取得记录信息
	    * @param pageNo
	    * @param pageSize
	    * @param role_name
	    * @return  指定页及查询条件取得记录信息
	    */
  List findRoleInfo_SearchByPage(Session sess,int pageNo,int pageSize,String role_name);
 
  
    /**
	 * 根据主键加载角色信息
	 * @param sess 持久化操作所需要的Hiberate Session
	 * @param role_id 需要加载角色id
	 */
  TbRoleInfo get(Session sess , String role_id);  
  
 
  /**
	 * 根据角色名加载信息
	 * @param sess 持久化操作所需要的Hiberate Session
	 * @param role_name 需要加载角色名
	 * @param role_id 需要加载角色ID
	 */
  TbRoleInfo get_byname(Session sess ,String role_id, String role_name);
  
  
  /**
	 * 根据角色名加载信息
	 * @param sess 持久化操作所需要的Hiberate Session
	 * @param role_name 需要加载角色名
	 */
  TbRoleInfo get_byname(Session sess ,String role_name);
  
  /**
   * 删除对象
   * @param sess
   * @param m
   */
  void delete(Session sess,TbRoleInfo m);
  
  
  /**
   * 
   * @return 所有角色信息
   */
  List findAllRoleInfo(Session sess);
  
	
}