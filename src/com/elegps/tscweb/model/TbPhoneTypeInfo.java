package com.elegps.tscweb.model;

import java.util.Date;

/**
 * TbPhoneTypeInfo entity
 * @author wanglei
 *
 */
public class TbPhoneTypeInfo implements java.io.Serializable {
	
	
	private Integer id; 	//主键id
	private String type;	//型号
	private Date time;		//创建时间
	private String product;	//整个产品的名称
	private String cpuAbi;	//cpu指令集
	private String cpuAbiTwo;//cpu指令集2
	private String tags;	//描述build的标签
	private String model;	//版本即最终用户可见的名称
	private String versionSdk;//SDK版本
	private String versionIncremental;//版本增量
	private String versionRelease;//目前已知的版本代码
	private String versionCodename;//版本号名称
	private String device;//设备参数
	private String display;//显示屏参数
	private String brand;//系统定制商
	private String board;//主板
	private String fingerprint;//唯一识别码
	private String sdkId;//修订版本列表
	private String manufacturer;//硬件制造商
	private String sdkUser;
	private int falg;	//匹配状态（0：关闭匹配、1：开启匹配）
	
	public Integer getId() {
		return id;
	}
	public String getType() {
		return type;
	}
	public Date getTime() {
		return time;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setType(String type) {
		this.type = type;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public String getProduct() {
		return product;
	}
	public String getCpuAbi() {
		return cpuAbi;
	}
	public String getCpuAbiTwo() {
		return cpuAbiTwo;
	}
	public String getTags() {
		return tags;
	}
	public String getModel() {
		return model;
	}
	public String getVersionSdk() {
		return versionSdk;
	}
	public String getVersionIncremental() {
		return versionIncremental;
	}
	public String getVersionRelease() {
		return versionRelease;
	}
	public String getVersionCodename() {
		return versionCodename;
	}
	public String getDevice() {
		return device;
	}
	public String getDisplay() {
		return display;
	}
	public String getBrand() {
		return brand;
	}
	public String getBoard() {
		return board;
	}
	public String getFingerprint() {
		return fingerprint;
	}
	public String getSdkId() {
		return sdkId;
	}
	public String getManufacturer() {
		return manufacturer;
	}
	public String getSdkUser() {
		return sdkUser;
	}
	public void setProduct(String product) {
		this.product = product;
	}
	public void setCpuAbi(String cpuAbi) {
		this.cpuAbi = cpuAbi;
	}
	public void setCpuAbiTwo(String cpuAbiTwo) {
		this.cpuAbiTwo = cpuAbiTwo;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public void setVersionSdk(String versionSdk) {
		this.versionSdk = versionSdk;
	}
	public void setVersionIncremental(String versionIncremental) {
		this.versionIncremental = versionIncremental;
	}
	public void setVersionRelease(String versionRelease) {
		this.versionRelease = versionRelease;
	}
	public void setVersionCodename(String versionCodename) {
		this.versionCodename = versionCodename;
	}
	public void setDevice(String device) {
		this.device = device;
	}
	public void setDisplay(String display) {
		this.display = display;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public void setBoard(String board) {
		this.board = board;
	}
	public void setFingerprint(String fingerprint) {
		this.fingerprint = fingerprint;
	}
	public void setSdkId(String sdkId) {
		this.sdkId = sdkId;
	}
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	public void setSdkUser(String sdkUser) {
		this.sdkUser = sdkUser;
	}
	public int getFalg() {
		return falg;
	}
	public void setFalg(int falg) {
		this.falg = falg;
	}

}
