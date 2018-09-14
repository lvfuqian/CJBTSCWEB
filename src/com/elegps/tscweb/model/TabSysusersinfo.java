package com.elegps.tscweb.model;

import java.util.HashSet;
import java.util.Set;

/**
 * TabSysusersinfo entity. @author MyEclipse Persistence Tools
 */

public class TabSysusersinfo implements java.io.Serializable {

	// Fields

	private Integer userId;
	private String userName;
	private String loginId;
	private String password;
	private TabSysconfig tabSysconfig;
	private TabSysuserroleId tabSysUser;

	// Constructors

	/** default constructor */
	public TabSysusersinfo() {
	}

	/** minimal constructor */
	public TabSysusersinfo(Integer userId) {
		this.userId = userId;
	}

	/** full constructor */
	public TabSysusersinfo(Integer userId, String userName, String loginId,
			String password) {
		this.userId = userId;
		this.userName = userName;
		this.loginId = loginId;
		this.password = password;
	}

	// Property accessors

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getLoginId() {
		return this.loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public TabSysconfig getTabSysconfig() {
		return tabSysconfig;
	}

	public void setTabSysconfig(TabSysconfig tabSysconfig) {
		this.tabSysconfig = tabSysconfig;
	}

	public TabSysuserroleId getTabSysUser() {
		return tabSysUser;
	}

	public void setTabSysUser(TabSysuserroleId tabSysUser) {
		this.tabSysUser = tabSysUser;
	}

}