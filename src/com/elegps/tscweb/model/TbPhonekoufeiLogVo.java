package com.elegps.tscweb.model;

public class TbPhonekoufeiLogVo {
	private String dialingNum;// 拨出次数
	private String dialingMoney; // 拨出总费用
	private String calledNum;// 被叫次数
	private String calledMoney; // 被叫总费用
	private String dialingTime; //拨出时长
	private String calledTime; //被叫时长

	public String getDialingNum() {
		return dialingNum;
	}

	public void setDialingNum(String dialingNum) {
		this.dialingNum = dialingNum;
	}

	public String getDialingMoney() {
		return dialingMoney;
	}

	public void setDialingMoney(String dialingMoney) {
		this.dialingMoney = dialingMoney;
	}

	public String getCalledNum() {
		return calledNum;
	}

	public void setCalledNum(String calledNum) {
		this.calledNum = calledNum;
	}

	public String getCalledMoney() {
		return calledMoney;
	}

	public void setCalledMoney(String calledMoney) {
		this.calledMoney = calledMoney;
	}

	public String getDialingTime() {
		return dialingTime;
	}

	public void setDialingTime(String dialingTime) {
		this.dialingTime = dialingTime;
	}

	public String getCalledTime() {
		return calledTime;
	}

	public void setCalledTime(String calledTime) {
		this.calledTime = calledTime;
	}

	
}
