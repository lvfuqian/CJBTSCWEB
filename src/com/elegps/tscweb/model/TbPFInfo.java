package com.elegps.tscweb.model;

import java.util.Date;

/**
 * TbPackageFeeInfo entity
 * @author wanglei
 *
 */
public class TbPFInfo implements java.io.Serializable {
	
	//�ײ�tb
	private Integer pfId; 	//����id
	private String pfType;	//�ײ�����
	private String pfHow;	//�ײͷ���
	private Integer pfTime;	//�ײ�ʱ��(��)
	/**
	 * @param pfId the pfId to set
	 */
	public void setPfId(Integer pfId) {
		this.pfId = pfId;
	}
	/**
	 * @return the pfId
	 */
	public Integer getPfId() {
		return pfId;
	}
	/**
	 * @param pfType the pfType to set
	 */
	public void setPfType(String pfType) {
		this.pfType = pfType;
	}
	/**
	 * @return the pfType
	 */
	public String getPfType() {
		return pfType;
	}
	/**
	 * @param pfHow the pfHow to set
	 */
	public void setPfHow(String pfHow) {
		this.pfHow = pfHow;
	}
	/**
	 * @return the pfHow
	 */
	public String getPfHow() {
		return pfHow;
	}
	/**
	 * @param pfTime the pfTime to set
	 */
	public void setPfTime(Integer pfTime) {
		this.pfTime = pfTime;
	}
	/**
	 * @return the pfTime
	 */
	public Integer getPfTime() {
		return pfTime;
	}
}
