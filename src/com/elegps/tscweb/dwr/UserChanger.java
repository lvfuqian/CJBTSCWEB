package com.elegps.tscweb.dwr;


import com.elegps.tscweb.comm.MD5;
import com.elegps.tscweb.model.TbUserInfo;
import com.elegps.tscweb.serivce.LoginManager;
import com.elegps.tscweb.serivce.UserManager;
import com.elegps.tscweb.serivce.impl.LoginManagerImpl;
import com.elegps.tscweb.serivce.impl.UserManagerImpl;


public class UserChanger {
	protected static LoginManager loginmanager;
	protected static MD5 md5;
	protected static UserManager usermanager;
	static {
		try {
			loginmanager=new LoginManagerImpl();
			md5=new MD5();
			usermanager=new UserManagerImpl();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//判断修改密码时，原密码输入是否正确
	public Boolean getUser(String name, String password) {
		TbUserInfo userInfo=loginmanager.getUser(name,md5.str2MD5(password));
		if(userInfo!=null){
			return true;
		}else{
			return false;
		}	
	}
	
	public Boolean ChangerPassword(String name,String password){
		return usermanager.ChangePassword(name,md5.str2MD5(password));	
	}
}
