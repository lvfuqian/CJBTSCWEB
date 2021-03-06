package com.elegps.tscweb.model;

import java.util.Date;

/**
 * TbMsInfo generated by MyEclipse Persistence Tools
 */

public class TbORGChargeInfo implements java.io.Serializable {

	// Fields
	private Integer chargeId;
	private Integer orgType;
	private Integer orgId;
	private Integer chargeCash;
	private String advancePerson;
	private String validatePerson;
	private Date chargeDate;
	private String remark;
	private String c01;
	private String c02;
	private String c03;
	public Integer getChargeId() {
		return chargeId;
	}
	public void setChargeId(Integer chargeId) {
		this.chargeId = chargeId;
	}

	public Integer getChargeCash() {
		return chargeCash;
	}
	public void setChargeCash(Integer chargeCash) {
		this.chargeCash = chargeCash;
	}
	public String getAdvancePerson() {
		return advancePerson;
	}
	public void setAdvancePerson(String advancePerson) {
		this.advancePerson = advancePerson;
	}
	public String getValidatePerson() {
		return validatePerson;
	}
	public void setValidatePerson(String validatePerson) {
		this.validatePerson = validatePerson;
	}
	public Date getChargeDate() {
		return chargeDate;
	}
	public void setChargeDate(Date chargeDate) {
		this.chargeDate = chargeDate;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getC01() {
		return c01;
	}
	public void setC01(String c01) {
		this.c01 = c01;
	}
	public String getC02() {
		return c02;
	}
	public void setC02(String c02) {
		this.c02 = c02;
	}
	public String getC03() {
		return c03;
	}
	public void setC03(String c03) {
		this.c03 = c03;
	}
	public Integer getOrgType() {
		return orgType;
	}
	public void setOrgType(Integer orgType) {
		this.orgType = orgType;
	}
	public Integer getOrgId() {
		return orgId;
	}
	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}
	
	
}