package com.elegps.tscweb.model;

/**
 * TabSysroleinfo entity. @author MyEclipse Persistence Tools
 */

public class TabSysroleinfo implements java.io.Serializable {

	// Fields

	private Integer roleId;
	private String roleName;
	private String description;

	// Constructors

	/** default constructor */
	public TabSysroleinfo() {
	}

	/** full constructor */
	public TabSysroleinfo(String roleName, String description) {
		this.roleName = roleName;
		this.description = description;
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

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}