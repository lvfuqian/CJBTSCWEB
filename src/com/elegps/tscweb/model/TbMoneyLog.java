package com.elegps.tscweb.model;

import java.util.Date;

/**
 * 2015-4-2
 * @author wanglei
 *
 */
public class TbMoneyLog implements java.io.Serializable{

	
	private Integer mlogId;
	private TbUserInfo user;//��ֵ��
	private String moneyToUser;//����ֵ��name
	private String how;//��ֵ���
	private Date mlogTime;//logʱ��
	private Integer payType;//��ֵ����(֧������1��΢�ţ�2��������ҵ��3���ն�ת�ƣ�4������ֵ��0)
	private String teadeNo;//֧����΢�Ž��׺�
	private String payNum;//��ֵ���루֧������΢�źţ��ն�id����ҵid��
	private String money_Trade_No;//�����ţ�ϵͳ�ֶ����ɣ�
	
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
