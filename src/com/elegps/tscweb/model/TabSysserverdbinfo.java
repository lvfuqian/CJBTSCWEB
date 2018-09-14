package com.elegps.tscweb.model;

import java.util.HashSet;
import java.util.Set;

/**
 * TabSysserverdbinfo entity. @author MyEclipse Persistence Tools
 */

public class TabSysserverdbinfo implements java.io.Serializable {

	// Fields

	private Integer sId;
	private String sName;
	private String sIp;
	private String sDbIp;
	private String sApmPort;
	private String sNowdbName;
	private String sNowPort;
	private String sNowUser;
	private String sNowPass;
	private String sHisdbName;
	private String sHisPort;
	private String sHisUser;
	private String sHisPass;

	
	/** default constructor */
	public TabSysserverdbinfo() {
	}

	public Integer getsId() {
		return sId;
	}

	public void setsId(Integer sId) {
		this.sId = sId;
	}

	public String getsName() {
		return sName;
	}

	public void setsName(String sName) {
		this.sName = sName;
	}

	public String getsIp() {
		return sIp;
	}

	public void setsIp(String sIp) {
		this.sIp = sIp;
	}

	public String getsApmPort() {
		return sApmPort;
	}

	public void setsApmPort(String sApmPort) {
		this.sApmPort = sApmPort;
	}

	public String getsNowdbName() {
		return sNowdbName;
	}

	public void setsNowdbName(String sNowdbName) {
		this.sNowdbName = sNowdbName;
	}

	public String getsNowPort() {
		return sNowPort;
	}

	public void setsNowPort(String sNowPort) {
		this.sNowPort = sNowPort;
	}

	public String getsNowUser() {
		return sNowUser;
	}

	public void setsNowUser(String sNowUser) {
		this.sNowUser = sNowUser;
	}

	public String getsNowPass() {
		return sNowPass;
	}

	public void setsNowPass(String sNowPass) {
		this.sNowPass = sNowPass;
	}

	public String getsHisdbName() {
		return sHisdbName;
	}

	public void setsHisdbName(String sHisdbName) {
		this.sHisdbName = sHisdbName;
	}

	public String getsHisPort() {
		return sHisPort;
	}

	public void setsHisPort(String sHisPort) {
		this.sHisPort = sHisPort;
	}

	public String getsHisUser() {
		return sHisUser;
	}

	public void setsHisUser(String sHisUser) {
		this.sHisUser = sHisUser;
	}

	public String getsHisPass() {
		return sHisPass;
	}

	public void setsHisPass(String sHisPass) {
		this.sHisPass = sHisPass;
	}

	public String getsDbIp() {
		return sDbIp;
	}

	public void setsDbIp(String sDbIp) {
		this.sDbIp = sDbIp;
	}

}