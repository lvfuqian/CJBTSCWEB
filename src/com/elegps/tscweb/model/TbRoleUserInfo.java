package com.elegps.tscweb.model;

import java.util.Date;

/**
 * TbRoleUserInfo entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class TbRoleUserInfo implements java.io.Serializable {

	// Fields

	private Integer userRoleSid;
	private Integer roleSid;
	private Integer userId;
	private Date create_time;
	private String c02;

	// Constructors

	/** default constructor */
	public TbRoleUserInfo() {
	}

	/** minimal constructor */
	public TbRoleUserInfo(Integer userRoleSid, Integer roleSid, Integer userId) {
		this.userRoleSid = userRoleSid;
		this.roleSid = roleSid;
		this.userId = userId;
	}


	// Property accessors

	public Integer getUserRoleSid() {
		return this.userRoleSid;
	}

	public void setUserRoleSid(Integer userRoleSid) {
		this.userRoleSid = userRoleSid;
	}

	public Integer getRoleSid() {
		return this.roleSid;
	}

	public void setRoleSid(Integer roleSid) {
		this.roleSid = roleSid;
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getC02() {
		return this.c02;
	}

	public void setC02(String c02) {
		this.c02 = c02;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

}