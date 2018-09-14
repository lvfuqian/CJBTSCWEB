package com.elegps.tscweb.model;

import java.util.Date;

public class TbYxCrmLog {

	private Integer id;
	private String ms_id;
	private Integer operation;	//1:É¾³ý¡¢2¡¢¶³½á
	private Integer enterprise_id;
	private String enterprise_name;
	private Date time;
	private String ms_name;
	private String phone;
	private String member_id;
	private String oper_id;
	private String imsi;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getMs_id() {
		return ms_id;
	}
	public void setMs_id(String msId) {
		ms_id = msId;
	}
	public Integer getOperation() {
		return operation;
	}
	public void setOperation(Integer operation) {
		this.operation = operation;
	}
	public Integer getEnterprise_id() {
		return enterprise_id;
	}
	public void setEnterprise_id(Integer enterpriseId) {
		enterprise_id = enterpriseId;
	}
	public String getEnterprise_name() {
		return enterprise_name;
	}
	public void setEnterprise_name(String enterpriseName) {
		enterprise_name = enterpriseName;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public String getMs_name() {
		return ms_name;
	}
	public void setMs_name(String msName) {
		ms_name = msName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String memberId) {
		member_id = memberId;
	}
	public String getOper_id() {
		return oper_id;
	}
	public void setOper_id(String operId) {
		oper_id = operId;
	}
	public String getImsi() {
		return imsi;
	}
	public void setImsi(String imsi) {
		this.imsi = imsi;
	}
	
}
