package com.elegps.tscweb.model;

/**
 * TabSysuserroleId entity. @author MyEclipse Persistence Tools
 */

public class TabSysuserroleId implements java.io.Serializable {

	// Fields

	 private Integer userId;
	private Integer roleId;
	private TabSysusersinfo tabSysusersinfo;

	// Constructors

	/** default constructor */
	public TabSysuserroleId() {
	}

	// /** full constructor */
	// public TabSysuserroleId(Integer userId, Integer roleId) {
	// this.userId = userId;
	// this.roleId = roleId;
	// }
	//
	// // Property accessors
	//
	 public Integer getUserId() {
	 return this.userId;
	 }
	
	 public void setUserId(Integer userId) {
	 this.userId = userId;
	 }

	public Integer getRoleId() {
		return this.roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public TabSysusersinfo getTabSysusersinfo() {
		return tabSysusersinfo;
	}

	public void setTabSysusersinfo(TabSysusersinfo tabSysusersinfo) {
		this.tabSysusersinfo = tabSysusersinfo;
	}

}