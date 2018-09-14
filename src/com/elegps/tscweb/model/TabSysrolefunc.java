package com.elegps.tscweb.model;

/**
 * TabSysrolefunc entity. @author MyEclipse Persistence Tools
 */

public class TabSysrolefunc implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer roleId;
	private Integer funcId;
	private Integer right;

	// Constructors

	/** default constructor */
	public TabSysrolefunc() {
	}

	/** full constructor */
	public TabSysrolefunc(Integer roleId, Integer funcId, Integer right) {
		this.roleId = roleId;
		this.funcId = funcId;
		this.right = right;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getRoleId() {
		return this.roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Integer getFuncId() {
		return this.funcId;
	}

	public void setFuncId(Integer funcId) {
		this.funcId = funcId;
	}

	public Integer getRight() {
		return this.right;
	}

	public void setRight(Integer right) {
		this.right = right;
	}

}