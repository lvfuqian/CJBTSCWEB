package com.elegps.tscweb.model;

import java.util.Date;

/**
 * 2015-4-2
 * @author wanglei
 *
 */
public class TbMoneyLog implements java.io.Serializable{

	
	private Integer mlogId;
	private TbUserInfo user;//充值人
	private String moneyToUser;//被充值人name
	private String how;//充值金额
	private Date mlogTime;//log时间
	private Integer payType;//充值类型(支付宝：1，微信：2，回收企业余额：3，终端转移：4，余额充值：0)
	private String teadeNo;//支付宝微信交易号
	private String payNum;//充值号码（支付宝，微信号，终端id，企业id）
	private String money_Trade_No;//订单号（系统字段生成）
	
	public Integer getPayType() {
		return payType;
	}
	public void setPayType(Integer payType) {
		this.payType = payType;
	}
	public String getTeadeNo() {
		return teadeNo;
	}
	public void setTeadeNo(String teadeNo) {
		this.teadeNo = teadeNo;
	}
	public String getPayNum() {
		return payNum;
	}
	public void setPayNum(String payNum) {
		this.payNum = payNum;
	}
	public String getMoney_Trade_No() {
		return money_Trade_No;
	}
	public void setMoney_Trade_No(String moneyTradeNo) {
		money_Trade_No = moneyTradeNo;
	}
	public Integer getMlogId() {
		return mlogId;
	}
	public void setMlogId(Integer mlogId) {
		this.mlogId = mlogId;
	}
	public String getMoneyToUser() {
		return moneyToUser;
	}
	public void setMoneyToUser(String moneyToUser) {
		this.moneyToUser = moneyToUser;
	}
	public String getHow() {
		return how;
	}
	public void setHow(String how) {
		this.how = how;
	}
	public Date getMlogTime() {
		return mlogTime;
	}
	public void setMlogTime(Date mlogTime) {
		this.mlogTime = mlogTime;
	}
	/**
	 * @param user the user to set
	 */
	public void setUser(TbUserInfo user) {
		this.user = user;
	}
	/**
	 * @return the user
	 */
	public TbUserInfo getUser() {
		return user;
	}
	
	
	
}
