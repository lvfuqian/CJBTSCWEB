package com.elegps.tscweb.comm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import com.elegps.tscweb.model.TbUserInfo;

public class UserInfo{
	 public static TbUserInfo getUserInfo(HttpServletRequest request){
		    HttpSession session = request.getSession(true);
			TbUserInfo userInfo=(TbUserInfo)session.getAttribute("user");
			return userInfo;
	 }
	

}
