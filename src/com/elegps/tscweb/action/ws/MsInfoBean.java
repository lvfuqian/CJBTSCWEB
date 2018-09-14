package com.elegps.tscweb.action.ws;

public class MsInfoBean {
	public MsInfoBean(String phone,String pwd,String name,int checkcode,String yqm){
		this.phone = phone;
		this.pwd = pwd;
		this.name = name;
		this.checkcode = checkcode;
		this.yqm = yqm;
	}
	
	public MsInfoBean(String phone,String pwd,String name,int checkcode){
		this.phone = phone;
		this.pwd = pwd;
		this.name = name;
		this.checkcode = checkcode;
	}

	private String phone;
	private String pwd;
	private String name;
	private int checkcode;
	private String yqm;
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCheckcode() {
		return checkcode;
	}
	public void setCheckcode(int checkcode) {
		this.checkcode = checkcode;
	}
	public String getYqm() {
		return yqm;
	}
	public void setYqm(String yqm) {
		this.yqm = yqm;
	}
}
