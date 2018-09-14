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
	 * ȡ�ø��û���Ϣ
	 * @param uname
	 * @param password
	 * @return
	 */
	TbUserInfo getUser(String uname,String password);
	
	
	/**
	 * ȡ�ø��û���ɫ��Ӧ��ϵ��Ϣ
	 * @param uname
	 * @param password
	 * @return
	 */
	TbRoleUserInfo getUserRole(TbUserInfo userinfo);
	
	
	/**
	 *  ȡ�ø��û��Ľ�ɫ��˵���Ӧ��ϵ��Ϣ
	 * @param roleUserInfo
	 * @return
	 */
	List<TbRoleMenuInfo> getRoleMenu(TbRoleUserInfo roleUserInfo);
	
	
	/**
	 * ȡ�ò˵���Ϣ
	 * @param roleMenuInfos
	 * @return
	 */
	List<TbMenuInfo> getMenu(List<TbRoleMenuInfo> roleMenuInfos);	

}