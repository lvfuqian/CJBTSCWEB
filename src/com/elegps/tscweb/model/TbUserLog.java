package com.elegps.tscweb.model;

import java.io.Serializable;
import java.util.Date;

public class TbUserLog implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int lId;
	private int userId; // ������ID
	private String lAddress;// ����IP��ַ
	/**
	 * 0,����{��¼,��ӵ�¼�˺�,��Ӳ˵�,�޸Ĳ˵�...} 1,����ն�ID 2,�޸��ն�ID 3,ɾ���ն�ID 4,���Ⱥ��ID 5,�޸�Ⱥ��ID
	 * 6,ɾ��Ⱥ��ID 7,���Ⱥ���ն˹�ϵ 8,ȡ��Ⱥ���ն˹�ϵ 9,���GPS�˺� 10,�޸�GPS�˺� 11,ɾ��GPS�˺�
	 * 12,���GPS�ն˹�ϵ 13,ȡ��GPS�ն˹�ϵ 14,��ӵ����˺� 15,�޸ĵ����˺� 16,ɾ�������˺� 17,�����̹��� 18,��ҵ����
	 * 19,�ͻ��������
	 */
	private int lType;// ��������
	// private String lPrimaryvalue;//��������
	private String lContent;// ��������˵��
	private Date lDate;// ����ʱ��
	private String lRemark;// ��ע
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
	 * 0,����{��¼,��ӵ�¼�˺�,��Ӳ˵�,�޸Ĳ˵�} 1,����ն�ID 2,�޸��ն�ID 3,ɾ���ն�ID 4,���Ⱥ��ID 5,�޸�Ⱥ��ID
	 * 6,ɾ��Ⱥ��ID 7,���Ⱥ���ն˹�ϵ 8,ȡ��Ⱥ���ն˹�ϵ 9,���GPS�˺� 10,�޸�GPS�˺� 11,ɾ��GPS�˺�
	 * 12,���GPS�ն˹�ϵ 13,ȡ��GPS�ն˹�ϵ 14,��ӵ����˺� 15,�޸ĵ����˺� 16,ɾ�������˺� 17,�����̹��� 18,��ҵ����
	 * 19,�ͻ��������
	 */
	public String getTypeName() {
		String typeName = "";
		switch (lType) {
		case 1:
			typeName = "����ն�ID";
			break;
		case 2:
			typeName = "�޸��ն�ID";
			break;
		case 3:
			typeName = "ɾ���ն�ID";
			break;
		case 4:
			typeName = "���Ⱥ��ID";
			break;
		case 5:
			typeName = "�޸�Ⱥ��ID";
			break;
		case 6:
			typeName = "ɾ��Ⱥ��ID";
			break;
		case 7:
			typeName = "���Ⱥ���ն˹�ϵ";
			break;
		case 8:
			typeName = "ȡ��Ⱥ���ն˹�ϵ";
			break;
		case 9:
			typeName = "���GPS�˺�";
			break;
		case 10:
			typeName = "�޸�GPS�˺�";
			break;
		case 11:
			typeName = "ɾ��GPS�˺�";
			break;
		case 12:
			typeName = "���GPS�ն˹�ϵ";
			break;
		case 13:
			typeName = "ȡ��GPSȺ���ϵ";
			break;
		case 14:
			typeName = "��ӵ����˺�";
			break;
		case 15:
			typeName = "�޸ĵ����˺�";
			break;
		case 16:
			typeName = "ɾ�������˺�";
			break;
		case 17:
			typeName = "�����̹���";
			break;
		case 18:
			typeName = "��ҵ����";
			break;
		case 19:
			typeName = "�ͻ��������";
			break;
		case 0:
			typeName = "����";
			break;

		default:
			typeName = "����";
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
