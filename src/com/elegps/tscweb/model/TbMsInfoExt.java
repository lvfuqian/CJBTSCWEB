package com.elegps.tscweb.model;

import java.io.Serializable;

/**
 * ��Ӧ��ϵ��
 * 
 * @author ACER
 * @date 2011-03-14
 * 
 */
public class TbMsInfoExt implements Serializable {
	public TbMsInfoExt() {
		// TODO Auto-generated constructor stub
	}

	private String msId;// �ն�ID
	private String simNum;// SIM ����
	private String deviceNum;// �豸���
	private int enterpriseId;// ��ҵID
	private int carPlateColor;// ������ɫ 0:��ɫ,1����,2����, 3����

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
