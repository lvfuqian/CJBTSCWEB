package com.elegps.tscweb.model;

import java.util.Date;

/**
 * TbRoleMenuInfo entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class TbRoleMenuInfo implements java.io.Serializable {

	// Fields

	private Integer roleMenuSid;
	private Integer roleId;
	private Integer menuId;
	private Date create_time;
	private String c02;
	private String c03;
	private String c04;
	private String c05;
	private String c06;

	// Constructors

	/** default constructor */
	public TbRoleMenuInfo() {
	}

	/** minimal constructor */
	public TbRoleMenuInfo(Integer roleMenuSid, Integer roleId, Integer menuId) {
		this.roleMenuSid = roleMenuSid;
		this.roleId = roleId;
		this.menuId = menuId;
		
	}


	// Property accessors

	public Integer getRoleMenuSid() {
		return this.roleMenuSid;
	}

	public void setRoleMenuSid(Integer roleMenuSid) {
		this.roleMenuSid = roleMenuSid;
	}

	public Integer getRoleId() {
		return this.roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Integer getMenuId() {
		return this.menuId;
	}

	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}

	public String getC02() {
		return this.c02;
	}

	public void setC02(String c02) {
		this.c02 = c02;
	}

	public String getC03() {
		return this.c03;
	}

	public void setC03(String c03) {
		this.c03 = c03;
	}

	public String getC04() {
		return this.c04;
	}

	public void setC04(String c04) {
		this.c04 = c04;
	}

	public String getC05() {
		return this.c05;
	}

	public void setC05(String c05) {
		this.c05 = c05;
	}

	public String getC06() {
		return this.c06;
	}

	public void setC06(String c06) {
		this.c06 = c06;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

}