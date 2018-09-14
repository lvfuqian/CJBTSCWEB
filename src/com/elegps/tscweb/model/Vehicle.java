package com.elegps.tscweb.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Vehicle implements Serializable {
	private int id;
	private int mobileId;
	private int driverId;
	private int vehicleTypeId;
	private int colorId;
	private String chepai;
	private String cheji;
	private String  yuyin;
	private String data;
	private String GPRS;
	private String chejipass;
	private Date createtime;
	private int creater ;
	private String loginpass;
	private String loginenable;
	private int isenable;
	private Date overduetime;
	private int months;
	private int pawstate;
	private int oilbox;
	private String remark;
	private Set<PositionLast> positionLast=new HashSet<PositionLast>();
	
	
	public Set<PositionLast> getPositionLast() {
		return positionLast;
	}
	public void setPositionLast(Set<PositionLast> positionLast) {
		this.positionLast = positionLast;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getMobileId() {
		return mobileId;
	}
	public void setMobileId(int mobileId) {
		this.mobileId = mobileId;
	}
	public int getDriverId() {
		return driverId;
	}
	public void setDriverId(int driverId) {
		this.driverId = driverId;
	}
	public int getVehicleTypeId() {
		return vehicleTypeId;
	}
	public void setVehicleTypeId(int vehicleTypeId) {
		this.vehicleTypeId = vehicleTypeId;
	}
	public int getColorId() {
		return colorId;
	}
	public void setColorId(int colorId) {
		this.colorId = colorId;
	}
	public String getChepai() {
		return chepai;
	}
	public void setChepai(String chepai) {
		this.chepai = chepai;
	}
	public String getCheji() {
		return cheji;
	}
	public void setCheji(String cheji) {
		this.cheji = cheji;
	}
	public String getYuyin() {
		return yuyin;
	}
	public void setYuyin(String yuyin) {
		this.yuyin = yuyin;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getGPRS() {
		return GPRS;
	}
	public void setGPRS(String gPRS) {
		GPRS = gPRS;
	}
	public String getChejipass() {
		return chejipass;
	}
	public void setChejipass(String chejipass) {
		this.chejipass = chejipass;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public int getCreater() {
		return creater;
	}
	public void setCreater(int creater) {
		this.creater = creater;
	}
	public String getLoginpass() {
		return loginpass;
	}
	public void setLoginpass(String loginpass) {
		this.loginpass = loginpass;
	}
	public String getLoginenable() {
		return loginenable;
	}
	public void setLoginenable(String loginenable) {
		this.loginenable = loginenable;
	}
	public int getIsenable() {
		return isenable;
	}
	public void setIsenable(int isenable) {
		this.isenable = isenable;
	}
	public Date getOverduetime() {
		return overduetime;
	}
	public void setOverduetime(Date overduetime) {
		this.overduetime = overduetime;
	}
	public int getMonths() {
		return months;
	}
	public void setMonths(int months) {
		this.months = months;
	}
	public int getPawstate() {
		return pawstate;
	}
	public void setPawstate(int pawstate) {
		this.pawstate = pawstate;
	}
	public int getOilbox() {
		return oilbox;
	}
	public void setOilbox(int oilbox) {
		this.oilbox = oilbox;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
