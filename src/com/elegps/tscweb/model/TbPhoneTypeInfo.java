package com.elegps.tscweb.model;

import java.util.Date;

/**
 * TbPhoneTypeInfo entity
 * @author wanglei
 *
 */
public class TbPhoneTypeInfo implements java.io.Serializable {
	
	
	private Integer id; 	//����id
	private String type;	//�ͺ�
	private Date time;		//����ʱ��
	private String product;	//������Ʒ������
	private String cpuAbi;	//cpuָ�
	private String cpuAbiTwo;//cpuָ�2
	private String tags;	//����build�ı�ǩ
	private String model;	//�汾�������û��ɼ�������
	private String versionSdk;//SDK�汾
	private String versionIncremental;//�汾����
	private String versionRelease;//Ŀǰ��֪�İ汾����
	private String versionCodename;//�汾������
	private String device;//�豸����
	private String display;//��ʾ������
	private String brand;//ϵͳ������
	private String board;//����
	private String fingerprint;//Ψһʶ����
	private String sdkId;//�޶��汾�б�
	private String manufacturer;//Ӳ��������
	private String sdkUser;
	private int falg;	//ƥ��״̬��0���ر�ƥ�䡢1������ƥ�䣩
	
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
