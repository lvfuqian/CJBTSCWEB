package com.elegps.tscweb.model;


import java.util.Date;

/**
 * TbMsInfo generated by MyEclipse Persistence Tools
 */

public class TbGpsInfo implements java.io.Serializable {

	/**
	 * 
	 */
	private String gpsop_id;
	private String password;
	private Date login_time;
	private Date create_time;
	private String gps_name;
	private Integer onlineStatus;
	private String c02;
	private String c03;


	// Constructors

	/** default constructor */
	public TbGpsInfo() {
	}


	public String getGpsop_id() {
		return gpsop_id;
	}


	public void setGpsop_id(String gpsop_id) {
		this.gpsop_id = gpsop_id;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public Date getLogin_time() {
		return login_time;
	}


	public void setLogin_time(Date login_time) {
		this.login_time = login_time;
	}



	public String getC02() {
		return c02;
	}


	public void setC02(String c02) {
		this.c02 = c02;
	}


	public String getC03() {
		return c03;
	}


	public void setC03(String c03) {
		this.c03 = c03;
	}


	public Date getCreate_time() {
		return create_time;
	}


	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}


	public String getGps_name() {
		return gps_name;
	}


	public void setGps_name(String gps_name) {
		this.gps_name = gps_name;
	}


	public Integer getOnlineStatus() {
		return onlineStatus;
	}


	public void setOnlineStatus(Integer onlineStatus) {
		this.onlineStatus = onlineStatus;
	}


	
	

}