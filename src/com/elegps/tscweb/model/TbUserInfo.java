package com.elegps.tscweb.model;

import java.util.Date;

/**
 * TbUserInfo entity.
 * 
 * @author MyEclipse Persistence Tools
 */
	//”√ªß±Ì
public class TbUserInfo implements java.io.Serializable {

	// Fields

	private Integer userId;
	private String userName;
	private String userPassword;
	private Date create_time;
	private Integer Agent_Id;
	private Integer Ep_Id;
	private String c02;
	// Constructors

	/** default constructor */
	public TbUserInfo() {
	}

	/** minimal constructor */
	public TbUserInfo(Integer userId, String userName, String userPassword) {
		this.userId = userId;
		this.userName = userName;
		this.userPassword = userPassword;
	}


	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return this.userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getC02() {
		return this.c02;
	}

	public void setC02(String c02) {
		this.c02 = c02;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public Integer getAgent_Id() {
		return Agent_Id;
	}

	public void setAgent_Id(Integer agent_Id) {
		Agent_Id = agent_Id;
	}

	public Integer getEp_Id() {
		return Ep_Id;
	}

	public void setEp_Id(Integer ep_Id) {
		Ep_Id = ep_Id;
	}

}