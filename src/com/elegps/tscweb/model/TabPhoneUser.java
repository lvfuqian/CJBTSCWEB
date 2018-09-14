package com.elegps.tscweb.model;

import java.io.Serializable;

public class TabPhoneUser implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// 工号
	private String userId;
	// 姓名
	private String userName;
	// 性别 0 男 1 女
	private int userSex;
	// 登录密码
	private String userPwd;
	// 身份证
	private String userIdCard;
	// 联系电话
	private String userMobile;
	// 住址
	private String userAddress;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getUserSex() {
		return userSex;
	}

	public void setUserSex(int userSex) {
		this.userSex = userSex;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public String getUserIdCard() {
		return userIdCard;
	}

	public void setUserIdCard(String userIdCard) {
		this.userIdCard = userIdCard;
	}

	public String getUserMobile() {
		return userMobile;
	}

	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}

	public String getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}

}
