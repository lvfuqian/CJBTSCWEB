package com.elegps.tscweb.model;

import java.io.Serializable;
import java.util.Date;

public class TbUserLog implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int lId;
	private int userId; // 操作人ID
	private String lAddress;// 操作IP地址
	/**
	 * 0,其他{登录,添加登录账号,添加菜单,修改菜单...} 1,添加终端ID 2,修改终端ID 3,删除终端ID 4,添加群组ID 5,修改群组ID
	 * 6,删除群组ID 7,添加群组终端关系 8,取消群组终端关系 9,添加GPS账号 10,修改GPS账号 11,删除GPS账号
	 * 12,添加GPS终端关系 13,取消GPS终端关系 14,添加调度账号 15,修改调度账号 16,删除调度账号 17,代理商管理 18,企业管理
	 * 19,客户问题管理
	 */
	private int lType;// 操作类型
	// private String lPrimaryvalue;//操作主键
	private String lContent;// 操作内容说明
	private Date lDate;// 操作时间
	private String lRemark;// 备注
	private String userName;
	private String typeName;
	
	public int getlId() {
		return lId;
	}

	public void setlId(int lId) {
		this.lId = lId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getlAddress() {
		return lAddress;
	}

	public void setlAddress(String lAddress) {
		this.lAddress = lAddress;
	}

	public int getlType() {
		return lType;
	}

	public void setlType(int lType) {
		this.lType = lType;
	}

	public String getlContent() {
		return lContent;
	}

	public void setlContent(String lContent) {
		this.lContent = lContent;
	}

	public Date getlDate() {
		return lDate;
	}

	public void setlDate(Date lDate) {
		this.lDate = lDate;
	}

	public String getlRemark() {
		return lRemark;
	}

	public void setlRemark(String lRemark) {
		this.lRemark = lRemark;
	}

	/*
	 * 0,其他{登录,添加登录账号,添加菜单,修改菜单} 1,添加终端ID 2,修改终端ID 3,删除终端ID 4,添加群组ID 5,修改群组ID
	 * 6,删除群组ID 7,添加群组终端关系 8,取消群组终端关系 9,添加GPS账号 10,修改GPS账号 11,删除GPS账号
	 * 12,添加GPS终端关系 13,取消GPS终端关系 14,添加调度账号 15,修改调度账号 16,删除调度账号 17,代理商管理 18,企业管理
	 * 19,客户问题管理
	 */
	public String getTypeName() {
		String typeName = "";
		switch (lType) {
		case 1:
			typeName = "添加终端ID";
			break;
		case 2:
			typeName = "修改终端ID";
			break;
		case 3:
			typeName = "删除终端ID";
			break;
		case 4:
			typeName = "添加群组ID";
			break;
		case 5:
			typeName = "修改群组ID";
			break;
		case 6:
			typeName = "删除群组ID";
			break;
		case 7:
			typeName = "添加群组终端关系";
			break;
		case 8:
			typeName = "取消群组终端关系";
			break;
		case 9:
			typeName = "添加GPS账号";
			break;
		case 10:
			typeName = "修改GPS账号";
			break;
		case 11:
			typeName = "删除GPS账号";
			break;
		case 12:
			typeName = "添加GPS终端关系";
			break;
		case 13:
			typeName = "取消GPS群组关系";
			break;
		case 14:
			typeName = "添加调度账号";
			break;
		case 15:
			typeName = "修改调度账号";
			break;
		case 16:
			typeName = "删除调度账号";
			break;
		case 17:
			typeName = "代理商管理";
			break;
		case 18:
			typeName = "企业管理";
			break;
		case 19:
			typeName = "客户问题管理";
			break;
		case 0:
			typeName = "其他";
			break;

		default:
			typeName = "其他";
			break;
		}
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
