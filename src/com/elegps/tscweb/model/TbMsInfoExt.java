package com.elegps.tscweb.model;

import java.io.Serializable;

/**
 * 对应关系表
 * 
 * @author ACER
 * @date 2011-03-14
 * 
 */
public class TbMsInfoExt implements Serializable {
	public TbMsInfoExt() {
		// TODO Auto-generated constructor stub
	}

	private String msId;// 终端ID
	private String simNum;// SIM 卡号
	private String deviceNum;// 设备编号
	private int enterpriseId;// 企业ID
	private int carPlateColor;// 车牌颜色 0:蓝色,1：黄,2：白, 3：黑

	public String getMsId() {
		return msId;
	}

	public void setMsId(String msId) {
		this.msId = msId;
	}

	public String getSimNum() {
		return simNum;
	}

	public void setSimNum(String simNum) {
		this.simNum = simNum;
	}

	public String getDeviceNum() {
		return deviceNum;
	}

	public void setDeviceNum(String deviceNum) {
		this.deviceNum = deviceNum;
	}

	public int getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(int enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public int getCarPlateColor() {
		return carPlateColor;
	}

	public void setCarPlateColor(int carPlateColor) {
		this.carPlateColor = carPlateColor;
	}

}
