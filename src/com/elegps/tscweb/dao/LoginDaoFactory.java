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
	 * ����������ѯ�û���Ϣ
	 * @param sess
	 * @param uname
	 * @param password
	 * @return
	 */
	TbUserInfo getUserInfo(Session sess,String uname, String password);
	
	
	/**
	 * ȡ�ø��û���ɫ
	 * @param sess
	 * @param userinfo
	 * @return
	 */	
	TbRoleUserInfo getUserRoleInfo(Session sess,TbUserInfo userinfo);
	

	/**
	 * ȡ�ø��û��Ľ�ɫ��˵���Ӧ��ϵ����Ϣ
	 * @param sess
	 * @param roleuserinfo
	 * @return
	 */	
	List getRoleMenuInfo(Session sess,TbRoleUserInfo roleuserinfo);

	
	/**
	 * ȡ�ø��û��˵���Ϣ
	 * @param sess
	 * @param mneuid
	 * @return
	 */
	List getMenuInfo(Session sess,String mneuid);   
	
}