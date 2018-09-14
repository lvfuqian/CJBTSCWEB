package com.elegps.tscweb.dao;

import org.hibernate.Session;

import com.elegps.tscweb.model.TbGpsInfo;
import com.elegps.tscweb.model.TbMenuInfo;
import com.elegps.tscweb.model.TbRoleInfo;
import com.elegps.tscweb.model.TbRoleMenuInfo;
import com.elegps.tscweb.model.TbRoleUserInfo;
import com.elegps.tscweb.model.TbUserInfo;


import java.util.List;

public interface LoginDaoFactory
{
	/**
	 * 跟根条件查询用户信息
	 * @param sess
	 * @param uname
	 * @param password
	 * @return
	 */
	TbUserInfo getUserInfo(Session sess,String uname, String password);
	
	
	/**
	 * 取得该用户角色
	 * @param sess
	 * @param userinfo
	 * @return
	 */	
	TbRoleUserInfo getUserRoleInfo(Session sess,TbUserInfo userinfo);
	

	/**
	 * 取得该用户的角色与菜单对应关系的信息
	 * @param sess
	 * @param roleuserinfo
	 * @return
	 */	
	List getRoleMenuInfo(Session sess,TbRoleUserInfo roleuserinfo);

	
	/**
	 * 取得该用户菜单信息
	 * @param sess
	 * @param mneuid
	 * @return
	 */
	List getMenuInfo(Session sess,String mneuid);   
	
}