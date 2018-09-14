package com.elegps.tscweb.model;

import java.io.Serializable;

public class ControlInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String msId;
	private String msName;
	private int enterpriseId;
	private String enterpriseName;
	private int r01;
	private float r02;
	private float r03;
	private int r04;
	private String r05;

	public String getMsId() {
		return msId;
	}

	public void setMsId(String msId) {
		this.msId = msId;
	}

	public String getMsName() {
		return msName;
	}

	public void setMsName(String msName) {
		this.msName = msName;
	}

	public int getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(int enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public String getEnterpriseName() {
		return enterpriseName;
	}

	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
	}

	public int getR01() {
		return r01;
	}

	public void setR01(int r01) {
		this.r01 = r01;
	}

	public float getR02() {
		return r02;
	}

	public void setR02(float r02) {
		this.r02 = r02;
	}

	public float getR03() {
		return r03;
	}

	public void setR03(float r03) {
		this.r03 = r03;
	}

	public int getR04() {
		return r04;
	}

	public void setR04(int r04) {
		this.r04 = r04;
	}

	public String getR05() {
		return r05;
	}

	public void setR05(String r05) {
		this.r05 = r05;
	}

}
