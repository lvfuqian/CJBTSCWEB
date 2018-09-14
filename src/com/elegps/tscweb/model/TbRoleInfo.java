package com.elegps.tscweb.model;

import java.util.Date;

/**
 * TbRoleInfo entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class TbRoleInfo implements java.io.Serializable {

	// Fields

	private Integer roleId;
	private String roleName;
	private Date create_time;
	private String c02;

	// Constructors

	/** default constructor */
	public TbRoleInfo() {
	}

	/** minimal constructor */
	public TbRoleInfo(Integer roleId, String roleName) {
		this.roleId = roleId;
		this.roleName = roleName;
	}


	// Property accessors

	public Integer getRoleId() {
		return this.roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
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