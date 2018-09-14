package com.elegps.tscweb.serivce;

import java.util.List;

import com.elegps.tscweb.model.TabPhoneUser;

public interface PhoneUserManage extends BaseManage<TabPhoneUser> {

	public int update(String hql, Object... objects);

	public int total(String userName, String userSex, String userMobile);

	public List<TabPhoneUser> listAll(int pageNo, int pageSize,
			String userName, String userSex, String userMobile);
	
	public String getPrimaryKey();
}
