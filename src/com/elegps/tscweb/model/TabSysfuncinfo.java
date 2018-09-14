package com.elegps.tscweb.model;

/**
 * TabSysfuncinfo entity. @author MyEclipse Persistence Tools
 */

public class TabSysfuncinfo implements java.io.Serializable {

	// Fields

	private Integer funcId;
	private String funcName;
	private String description;

	// Constructors

	/** default constructor */
	public TabSysfuncinfo() {
	}

	/** full constructor */
	public TabSysfuncinfo(String funcName, String description) {
		this.funcName = funcName;
		this.description = description;
	}

	// Property accessors

	public Integer getFuncId() {
		return this.funcId;
	}

	public void setFuncId(Integer funcId) {
		this.funcId = funcId;
	}

	public String getFuncName() {
		return this.funcName;
	}

	public void setFuncName(String funcName) {
		this.funcName = funcName;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}