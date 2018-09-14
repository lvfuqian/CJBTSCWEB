package com.elegps.tscweb.model;

import java.io.Serializable;

public class TabPhoneUser implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// ����
	private String userId;
	// ����
	private String userName;
	// �Ա� 0 �� 1 Ů
	private int userSex;
	// ��¼����
	private String userPwd;
	// ���֤
	private String userIdCard;
	// ��ϵ�绰
	private String userMobile;
	// סַ
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
