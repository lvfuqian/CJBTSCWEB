package com.elegps.tscweb.serivce;

import java.util.List;

import com.elegps.tscweb.exception.MessageException;
import com.elegps.tscweb.model.TbDdmsMsListInfo;
import com.elegps.tscweb.model.TbGpsMsListInfo;
import com.elegps.tscweb.model.TbGrpInfo;
import com.elegps.tscweb.model.TbGrpMsListInfo;
import com.elegps.tscweb.model.TbMenuInfo;
import com.elegps.tscweb.model.TbMsInfo;
import com.elegps.tscweb.model.TbRoleInfo;
import com.elegps.tscweb.model.TbRoleMenuInfo;
import com.elegps.tscweb.model.TbRoleUserInfo;
import com.elegps.tscweb.model.TbUserInfo;

public interface LoginManager {
	/**
	 * 取得该用户信息
	 * @param uname
	 * @param password
	 * @return
	 */
	TbUserInfo getUser(String uname,String password);
	
	
	/**
	 * 取得该用户角色对应关系信息
	 * @param uname
	 * @param password
	 * @return
	 */
	TbRoleUserInfo getUserRole(TbUserInfo userinfo);
	
	
	/**
	 *  取得该用户的角色与菜单对应关系信息
	 * @param roleUserInfo
	 * @return
	 */
	List<TbRoleMenuInfo> getRoleMenu(TbRoleUserInfo roleUserInfo);
	
	
	/**
	 * 取得菜单信息
	 * @param roleMenuInfos
	 * @return
	 */
	List<TbMenuInfo> getMenu(List<TbRoleMenuInfo> roleMenuInfos);	

}