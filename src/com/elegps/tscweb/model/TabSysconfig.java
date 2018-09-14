package com.elegps.tscweb.model;

/**
 * TabSysconfig entity. @author MyEclipse Persistence Tools
 */

public class TabSysconfig implements java.io.Serializable {

	// Fields

	private Integer userId;
	private String msid;
	private String mspwd;
	private String apmIp;
	private Integer apmPort;
	private String nowDataDbip;
	private String nowDataDbname;
	private Integer nowDataDbport;
	private String nowDataDbuser;
	private String nowDataDbpwd;
	private String hisDataDbip;
	private String hisDataDbname;
	private Integer hisDataDbport;
	private String hisDataDbuser;
	private String hisDataDbpwd;
	private TabSysusersinfo tabSysusersinfo;
	private TabSysuserroleId tbur;
	private TabSysserverdbinfo dbInfo;

	public TabSysserverdbinfo getDbInfo() {
		return dbInfo;
	}

	public void setDbInfo(TabSysserverdbinfo dbInfo) {
		this.dbInfo = dbInfo;
	}

	public TabSysuserroleId getTbur() {
		return tbur;
	}

	public void setTbur(TabSysuserroleId tbur) {
		this.tbur = tbur;
	}

	public TabSysusersinfo getTabSysusersinfo() {
		return tabSysusersinfo;
	}

	public void setTabSysusersinfo(TabSysusersinfo tabSysusersinfo) {
		this.tabSysusersinfo = tabSysusersinfo;
	}

	/** default constructor */
	public TabSysconfig() {
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getMsid() {
		return this.msid;
	}

	public void setMsid(String msid) {
		this.msid = msid;
	}

	public String getMspwd() {
		return this.mspwd;
	}

	public void setMspwd(String mspwd) {
		this.mspwd = mspwd;
	}

	public String getApmIp() {
		return this.apmIp;
	}

	public void setApmIp(String apmIp) {
		this.apmIp = apmIp;
	}

	public Integer getApmPort() {
		return this.apmPort;
	}

	public void setApmPort(Integer apmPort) {
		this.apmPort = apmPort;
	}

	public String getNowDataDbip() {
		return this.nowDataDbip;
	}

	public void setNowDataDbip(String nowDataDbip) {
		this.nowDataDbip = nowDataDbip;
	}

	public String getNowDataDbname() {
		return this.nowDataDbname;
	}

	public void setNowDataDbname(String nowDataDbname) {
		this.nowDataDbname = nowDataDbname;
	}

	public Integer getNowDataDbport() {
		return this.nowDataDbport;
	}

	public void setNowDataDbport(Integer nowDataDbport) {
		this.nowDataDbport = nowDataDbport;
	}

	public String getNowDataDbuser() {
		return this.nowDataDbuser;
	}

	public void setNowDataDbuser(String nowDataDbuser) {
		this.nowDataDbuser = nowDataDbuser;
	}

	public String getNowDataDbpwd() {
		return this.nowDataDbpwd;
	}

	public void setNowDataDbpwd(String nowDataDbpwd) {
		this.nowDataDbpwd = nowDataDbpwd;
	}

	public String getHisDataDbip() {
		return this.hisDataDbip;
	}

	public void setHisDataDbip(String hisDataDbip) {
		this.hisDataDbip = hisDataDbip;
	}

	public String getHisDataDbname() {
		return this.hisDataDbname;
	}

	public void setHisDataDbname(String hisDataDbname) {
		this.hisDataDbname = hisDataDbname;
	}

	public Integer getHisDataDbport() {
		return this.hisDataDbport;
	}

	public void setHisDataDbport(Integer hisDataDbport) {
		this.hisDataDbport = hisDataDbport;
	}

	public String getHisDataDbuser() {
		return this.hisDataDbuser;
	}

	public void setHisDataDbuser(String hisDataDbuser) {
		this.hisDataDbuser = hisDataDbuser;
	}

	public String getHisDataDbpwd() {
		return this.hisDataDbpwd;
	}

	public void setHisDataDbpwd(String hisDataDbpwd) {
		this.hisDataDbpwd = hisDataDbpwd;
	}

}