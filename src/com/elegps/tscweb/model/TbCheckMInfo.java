package com.elegps.tscweb.model;

import java.util.Date;

/**
 * TbPackageFeeInfo entity
 * @author wanglei
 *
 */
public class TbCheckMInfo implements java.io.Serializable {
	
	//�������tb
	private Integer finId; 	//����id
	private TbUserInfo proposer; //������
	private String resId;	//��ֵ�ʺ�id
	private TbRoleInfo resRole;//��ֵ�ʺŽ�ɫ
	private String resMoney;//��ֵ���
	private	String proDescription;	//����������Ϣ
	private Integer proStatus;	//����״̬��0��δ��� ��1�����ͨ�� �� 2����˲�ͨ����
	private	Integer readState;	//������Ϣ�Ѷ�״̬��0��δ�� �� 1���Ѷ���
	private Date proTime;	//����ʱ��
	private TbUserInfo checkPerson;	//�����
	private	Date checkTime;		//���ʱ��
	
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
