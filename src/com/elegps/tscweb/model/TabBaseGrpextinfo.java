package com.elegps.tscweb.model;

/**
 * TabBaseGrpextinfo entity. @author MyEclipse Persistence Tools
 */

public class TabBaseGrpextinfo implements java.io.Serializable {

	// Fields

	private String grpId;
	private Integer type;
	private String companyName;
	private Double lg;
	private Double lt;
	private Double weidu;
	public Double getWeidu() {
		return lt;
	}

	private Integer radius;
	private Integer overSpdSmsTip;
	private Integer enterpriseId;

	// Constructors

	/** default constructor */
	public TabBaseGrpextinfo() {
	}

	/** full constructor */
	public TabBaseGrpextinfo(String grpId, Integer type, String companyName,
			Double lg, Double lt, Integer radius, Integer overSpdSmsTip,
			Integer enterpriseId) {
		this.grpId = grpId;
		this.type = type;
		this.companyName = companyName;
		this.lg = lg;
		this.lt = lt;
		this.radius = radius;
		this.overSpdSmsTip = overSpdSmsTip;
		this.enterpriseId = enterpriseId;
	}

	// Property accessors

	public String getGrpId() {
		return this.grpId;
	}

	public void setGrpId(String grpId) {
		this.grpId = grpId;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getCompanyName() {
		return this.companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public Double getLg() {
		return this.lg;
	}

	public void setLg(Double lg) {
		this.lg = lg;
	}

	public Double getLt() {
		return this.lt;
	}

	public void setLt(Double lt) {
		this.lt = lt;
	}

	public Integer getRadius() {
		return this.radius;
	}

	public void setRadius(Integer radius) {
		this.radius = radius;
	}

	public Integer getOverSpdSmsTip() {
		return this.overSpdSmsTip;
	}

	public void setOverSpdSmsTip(Integer overSpdSmsTip) {
		this.overSpdSmsTip = overSpdSmsTip;
	}

	public Integer getEnterpriseId() {
		return this.enterpriseId;
	}

	public void setEnterpriseId(Integer enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

}