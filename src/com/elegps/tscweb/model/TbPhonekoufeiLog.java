package com.elegps.tscweb.model;

import java.io.Serializable;
import java.util.Date;

public class TbPhonekoufeiLog implements Serializable{
	private Integer id;
	private String msid;
	private String chuanzPhoneNum; //�����绰����
	private String desPhoneNum;	   //�Է��绰����
	private Date startTime;		   //��ʼʱ��
	private Integer callTime;	   //ͨ��ʱ������λ��
	private Integer money;		   //�۷�Ǯ,��λ��
	private String imsi;		   //���ұ��ʺ�ID
	private Integer type;		   //���ͣ�0���绰�ѣ�1�����ŷ�

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
