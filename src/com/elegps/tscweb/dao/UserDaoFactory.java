package com.elegps.tscweb.dao;

import org.hibernate.Session;

import com.elegps.tscweb.model.TbGpsInfo;
import com.elegps.tscweb.model.TbMenuInfo;
import com.elegps.tscweb.model.TbRoleInfo;
import com.elegps.tscweb.model.TbUserInfo;

import java.util.List;

public interface UserDaoFactory {

	/**
	 * 根据查询条件取得记录总数
	 * 
	 * @param user_name
	 * @return 查询条件取得记录总数
	 */
	Integer findUser_SearchCount(Session sess, String user_name,int agent_id);

	/**
	 * 根据指定页及查询条件取得记录信息
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @param user_name
	 * @return 指定页及查询条件取得记录信息
	 */
	List findUserInfo_SearchByPage(Session sess, int pageNo, int pageSize,
			String user_name,int agent_id);

	/**
	 * 根据用户名加载信息 
	 * @param sess    持久化操作所需要的Hiberate Session
	 * @param user_name   需要加载用户名
	 */
	TbUserInfo get_byname(Session sess, String user_name);
	
	
	/**
	 * 根据用户名加载信息
	 * @param sess
	 * @param user_id
	 * @param user_name
	 * @return
	 */
	TbUserInfo get_byname(Session sess, String user_id, String user_name);
	
	
	 /**
	 * 根据主键加载用户信息
	 * @param sess 持久化操作所需要的Hiberate Session
	 * @param user_id 需要加载用户信息
	 */
	TbUserInfo get(Session sess , String user_id);  
	
	/**
	   * 删除对象
	   * @param sess
	   * @param m
	   */
    void delete(Session sess,TbUserInfo m);
    
    /**
     * 
     * @return 所有用户信息
     */
    List findAllUserInfo(Session sess);
    
    /**
     * 
     * @return 所有没有分配角色用户信息
     */
    List findNotUserInfo(Session sess);
    
    /**
     * 
     * @return  返回所有代理商
     */
     
    List getAllAgentInfo(Session sess);
    
    
	/**
	 * 2009-11-20
	 * 根据用户名,密码加载用户信息
	 * @param sess
	 * @param name
	 * @param password
	 * @return
	 */
	TbUserInfo get_User(Session sess, String name);
	
	/**
     * 修改信息
     * @param sess
     * @param user
     */
    void updateInfo(Session sess,TbUserInfo user);
}