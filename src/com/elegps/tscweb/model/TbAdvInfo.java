package com.elegps.tscweb.model;

import java.sql.Time;
import java.util.Date;

/**
 * TbAdvInfo entity
 * @author wanglei
 *
 */
public class TbAdvInfo implements java.io.Serializable {
	
	//�����Ϣtb
	private Integer advId; 	//����id
	private Integer userId;	//�û�ID
	private String advTitle;//����
	private String advContent;//�������
	private String picUrl;//���ͼƬ·��
	private String advUrl;//����ַ
	//private	Integer isSend;	//����״̬��0��δ���� �� 1���ѷ��ͣ�
	private Date sendSTime;	//���Ϳ�ʼ����
	private Date sendETime;	//���ͽ�������
	private	Date creatTime;		//����ʱ��
	private Integer advType;	//�������
	private Time showSTime;		//���Ϳ�ʼʱ��
	private Time showETime;		//���ͽ���ʱ��
	
	/**
	 * @return the showSTime
	 */
	public Time getShowSTime() {
		return showSTime;
	}

	/**
	 * @param showSTime the showSTime to set
	 */
	public void setShowSTime(Time showSTime) {
		this.showSTime = showSTime;
	}

	/**
	 * @return the showETime
	 */
	public Time getShowETime() {
		return showETime;
	}

	/**
	 * @param showETime the showETime to set
	 */
	public void setShowETime(Time showETime) {
		this.showETime = showETime;
	}

	public TbAdvInfo(){};
	
	public TbAdvInfo(Integer advId,Integer userId,String advTitle,String advContent,
			String picUrl,String advUrl,Date sendETime,
			Date sendSTime,Date creatTime,Integer advType,Time showSTime,Time showETime){
		this.advId =advId;
		this.userId=userId;
		this.advTitle=advTitle;
		this.advContent=advContent;
		this.picUrl=picUrl;
		this.advUrl=advUrl;
		this.sendSTime=sendSTime;
		this.sendETime=sendETime;
		this.creatTime=creatTime;
		this.advType=advType;
		this.showETime=showETime;
		this.showSTime=showSTime;
	};
	/**
	 * @return the advId
	 */
	public Integer getAdvId() {
		return advId;
	}
	/**
	 * @param advId the advId to set
	 */
	public void setAdvId(Integer advId) {
		this.advId = advId;
	}
	/**
	 * @return the userId
	 */
	public Integer getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	/**
	 * @return the advConetent
	 */
	public String getAdvContent() {
		return advContent;
	}
	/**
	 * @param advConetent the advConetent to set
	 */
	public void setAdvContent(String advContent) {
		this.advContent = advContent;
	}
	/**
	 * @return the creatTime
	 */
	public Date getCreatTime() {
		return creatTime;
	}
	/**
	 * @param creatTime the creatTime to set
	 */
	public void setCreatTime(Date creatTime) {
		this.creatTime = creatTime;
	}

	/**
	 * @param advTitle the advTitle to set
	 */
	public void setAdvTitle(String advTitle) {
		this.advTitle = advTitle;
	}

	/**
	 * @return the advTitle
	 */
	public String getAdvTitle() {
		return advTitle;
	}

	/**
	 * @return the picUrl
	 */
	public String getPicUrl() {
		return picUrl;
	}

	/**
	 * @param picUrl the picUrl to set
	 */
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	/**
	 * @return the advUrl
	 */
	public String getAdvUrl() {
		return advUrl;
	}

	/**
	 * @param advUrl the advUrl to set
	 */
	public void setAdvUrl(String advUrl) {
		this.advUrl = advUrl;
	}

	/**
	 * @return the sendSTime
	 */
	public Date getSendSTime() {
		return sendSTime;
	}

	/**
	 * @param sendSTime the sendSTime to set
	 */
	public void setSendSTime(Date sendSTime) {
		this.sendSTime = sendSTime;
	}

	/**
	 * @return the sendETime
	 */
	public Date getSendETime() {
		return sendETime;
	}

	/**
	 * @param sendETime the sendETime to set
	 */
	public void setSendETime(Date sendETime) {
		this.sendETime = sendETime;
	}

	/**
	 * @param advType the advType to set
	 */
	public void setAdvType(Integer advType) {
		this.advType = advType;
	}

	/**
	 * @return the advType
	 */
	public Integer getAdvType() {
		return advType;
	}
}
