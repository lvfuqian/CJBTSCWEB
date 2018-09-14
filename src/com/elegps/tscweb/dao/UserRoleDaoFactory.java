package com.elegps.tscweb.dao;

import org.hibernate.Session;

import com.elegps.tscweb.model.TbGpsInfo;
import com.elegps.tscweb.model.TbMenuInfo;
import com.elegps.tscweb.model.TbRoleInfo;
import com.elegps.tscweb.model.TbRoleMenuInfo;
import com.elegps.tscweb.model.TbRoleUserInfo;


import java.util.List;

public interface UserRoleDaoFactory
{
	
	 /**
	 * 根据查询条件取得记录总数
	 * @param user_id
	 * @param role_id
	 * @return  查询条件取得记录总数
	 */
    Integer findUserRole_SearchCount(Session sess,String user_id,String role_id);
    
    
    /**
	    * 根据指定页及查询条件取得记录信息
	    * @param pageNo
	    * @param pageSize
	    * @param user_id
	    * @param role_id
	    * @return  指定页及查询条件取得记录信息
	    */
    List findUserRole_SearchByPage(Session sess,int pageNo,int pageSize,String user_id,String role_id);
    
    
    
    /**
     * 根据用户ID和角色ID
     * @param sess
     * @param role_id
     * @param menu_id
     * @return
     */
    TbRoleUserInfo get_ByUsRoId(Session sess,String user_id,String role_id);
    
    
    /**
     * 根据主键加载信息
     * @param sess
     * @param userrole_id
     */
    TbRoleUserInfo get(Session sess , String userrole_id); 
    
    
    /**
     * 删除对象
     * @param sess
     * @param m
     */
    void delete(Session sess,TbRoleUserInfo m);
    
    
    /**
     * 根据用户ID(user_id)删除用户角色对应关系
     * @param user_id
     * @return
     */
    void deleteUser(Session sess,String user_id);
    
    /**
     * 根据角色ID(role_id)删除用户角色对应关系
     * @param role_id
     * @return
     */
    void deleteRole(Session sess,String role_id);
    
    
    /**
     * 根据用户ID查询角色ID
     * @param sess
     * @param user_id
     * @return
     */
    TbRoleUserInfo getRoleid_byUserid(Session sess,String user_id); 
	
}