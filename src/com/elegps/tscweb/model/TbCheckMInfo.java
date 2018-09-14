package com.elegps.tscweb.model;

import java.util.Date;

/**
 * TbPackageFeeInfo entity
 * @author wanglei
 *
 */
public class TbCheckMInfo implements java.io.Serializable {
	
	//…Í«Î…Û∫Àtb
	private Integer finId; 	//÷˜º¸id
	private TbUserInfo proposer; //…Í«Î»À
	private String resId;	//≥‰÷µ’ ∫≈id
	private TbRoleInfo resRole;//≥‰÷µ’ ∫≈Ω«…´
	private String resMoney;//≥‰÷µΩ∂Ó
	private	String proDescription;	//…Í«Î√Ë ˆ–≈œ¢
	private Integer proStatus;	//…Í«Î◊¥Ã¨£®0£∫Œ¥…Û∫À £¨1£∫…Û∫ÀÕ®π˝ £¨ 2£∫…Û∫À≤ªÕ®π˝£©
	private	Integer readState;	//…Í«Î–≈œ¢“—∂¡◊¥Ã¨£®0£∫Œ¥∂¡ £¨ 1£∫“—∂¡£©
	private Date proTime;	//…Í«Î ±º‰
	private TbUserInfo checkPerson;	//…Û∫À»À
	private	Date checkTime;		//…Û∫À ±º‰
	
	public Integer getFinId() {
		return finId;
	}
	public void setFinId(Integer finId) {
		this.finId = finId;
	}
	public TbUserInfo getProposer() {
		return proposer;
	}
	public void setProposer(TbUserInfo proposer) {
		this.proposer = proposer;
	}
	public String getProDescription() {
		return proDescription;
	}
	public void setProDescription(String proDescription) {
		this.proDescription = proDescription;
	}
	public Integer getProStatus() {
		return proStatus;
	}
	public void setProStatus(Integer proStatus) {
		this.proStatus = proStatus;
	}
	public Integer getReadState() {
		return readState;
	}
	public void setReadState(Integer readState) {
		this.readState = readState;
	}
	public Date getProTime() {
		return proTime;
	}
	public void setProTime(Date proTime) {
		this.proTime = proTime;
	}
	public TbUserInfo getCheckPerson() {
		return checkPerson;
	}
	public void setCheckPerson(TbUserInfo checkPerson) {
		this.checkPerson = checkPerson;
	}
	public Date getCheckTime() {
		return checkTime;
	}
	public void setCheckTime(Date checkTime) {
		this.checkTime = checkTime;
	}
	/**
	 * @param resMonet the resMonet to set
	 */
	public void setResMoney(String resMoney) {
		this.resMoney = resMoney;
	}
	/**
	 * @return the resMonet
	 */
	public String getResMoney() {
		return resMoney;
	}
	/**
	 * @param resId the resId to set
	 */
	public void setResId(String resId) {
		this.resId = resId;
	}
	/**
	 * @return the resId
	 */
	public String getResId() {
		return resId;
	}
	/**
	 * @param resRole the resRole to set
	 */
	public void setResRole(TbRoleInfo resRole) {
		this.resRole = resRole;
	}
	/**
	 * @return the resRole
	 */
	public TbRoleInfo getResRole() {
		return resRole;
	}
}
