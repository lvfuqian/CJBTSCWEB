package com.elegps.tscweb.model;

import java.util.Date;

/**
 * TbAppPayInfo generated by MyEclipse Persistence Tools
 */

public class TbAppPayInfo implements java.io.Serializable {

	/**
	 * 
	 */
	private String id;		//系统订单号
	private String payer;	//付款号码
	private String phoneOrMs; //21位终端号，被充号码
	private String payNum;	//充值支付宝帐号
	private String sellerId; //充值的支付宝用户号（唯一）
	private String tradeNo;	//支付宝交易号
	private String payMoney; //充值金额
	private Date time;	//充值时间
	private Date successTime;	//充值成功时间
	private Integer type;//c充值方式
	
	public TbAppPayInfo(){
		
	}
	
	public TbAppPayInfo(String id,String phoneOrMs,String payNum,String sellerId,String tradeNo,
			String payMoney,Date time,Date successTime,String payer,Integer type){
		this.id = id;
		this.payMoney = payMoney;
		this.payNum = payNum;
		this.phoneOrMs = phoneOrMs;
		this.sellerId = sellerId;
		this.successTime =successTime;
		this.time = time;
		this.tradeNo =tradeNo;
		this.payer = payer;
		this.type = type;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the phoneOrMs
	 */
	public String getPhoneOrMs() {
		return phoneOrMs;
	}

	/**
	 * @param phoneOrMs the phoneOrMs to set
	 */
	public void setPhoneOrMs(String phoneOrMs) {
		this.phoneOrMs = phoneOrMs;
	}

	/**
	 * @return the payNum
	 */
	public String getPayNum() {
		return payNum;
	}

	/**
	 * @param payNum the payNum to set
	 */
	public void setPayNum(String payNum) {
		this.payNum = payNum;
	}

	/**
	 * @return the sellerId
	 */
	public String getSellerId() {
		return sellerId;
	}

	/**
	 * @param sellerId the sellerId to set
	 */
	public void setSellerId(String sellerId) {
		this.sellerId = sellerId;
	}

	/**
	 * @return the tradeNo
	 */
	public String getTradeNo() {
		return tradeNo;
	}

	/**
	 * @param tradeNo the tradeNo to set
	 */
	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	/**
	 * @return the payMonney
	 */
	public String getPayMoney() {
		return payMoney;
	}

	/**
	 * @param payMonney the payMonney to set
	 */
	public void setPayMoney(String payMoney) {
		this.payMoney = payMoney;
	}

	/**
	 * @return the time
	 */
	public Date getTime() {
		return time;
	}

	/**
	 * @param time the time to set
	 */
	public void setTime(Date time) {
		this.time = time;
	}

	/**
	 * @return the successTime
	 */
	public Date getSuccessTime() {
		return successTime;
	}

	/**
	 * @param successTime the successTime to set
	 */
	public void setSuccessTime(Date successTime) {
		this.successTime = successTime;
	}
	/**
	 * @param payer the payer to set
	 */
	public void setPayer(String payer) {
		this.payer = payer;
	}

	/**
	 * @return the payer
	 */
	public String getPayer() {
		return payer;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

}