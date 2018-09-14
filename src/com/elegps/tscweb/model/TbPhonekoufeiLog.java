package com.elegps.tscweb.model;

import java.io.Serializable;
import java.util.Date;

public class TbPhonekoufeiLog implements Serializable{
	private Integer id;
	private String msid;
	private String chuanzPhoneNum; //船长电话号码
	private String desPhoneNum;	   //对方电话号码
	private Date startTime;		   //开始时间
	private Integer callTime;	   //通话时长，单位秒
	private Integer money;		   //扣费钱,单位分
	private String imsi;		   //船家宝帐号ID
	private Integer type;		   //类型：0：电话费，1：短信费

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getMsid() {
		return msid;
	}
	public void setMsid(String msid) {
		this.msid = msid;
	}
	public String getChuanzPhoneNum() {
		return chuanzPhoneNum;
	}
	public void setChuanzPhoneNum(String chuanzPhoneNum) {
		this.chuanzPhoneNum = chuanzPhoneNum;
	}
	public String getDesPhoneNum() {
		return desPhoneNum;
	}
	public void setDesPhoneNum(String desPhoneNum) {
		this.desPhoneNum = desPhoneNum;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Integer getCallTime() {
		return callTime;
	}
	public void setCallTime(Integer callTime) {
		this.callTime = callTime;
	}
	public Integer getMoney() {
		return money;
	}
	public void setMoney(Integer money) {
		this.money = money;
	}

	public String getImsi() {
		return imsi;
	}
	public void setImsi(String imsi) {
		this.imsi = imsi;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

}
