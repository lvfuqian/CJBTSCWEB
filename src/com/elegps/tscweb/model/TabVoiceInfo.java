package com.elegps.tscweb.model;

import java.util.Date;

/**
 * �������ݣ�adb���ݿ��±�TbVoiceData entity
 * @author wanglei
 *
 */
public class TabVoiceInfo {

	private String vdId;	//����id
	private String msId;	//�ն�id
	private String grpId;	//Ⱥ��id
	private Date beginDT;	//������ʼʱ��
	private Date endDT;		//��������ʱ��
	
	public String getVdId() {
		return vdId;
	}
	public String getMsId() {
		return msId;
	}
	public String getGrpId() {
		return grpId;
	}
	public Date getBeginDT() {
		return beginDT;
	}
	public Date getEndDT() {
		return endDT;
	}
	public void setVdId(String vdId) {
		this.vdId = vdId;
	}
	public void setMsId(String msId) {
		this.msId = msId;
	}
	public void setGrpId(String grpId) {
		this.grpId = grpId;
	}
	public void setBeginDT(Date beginDT) {
		this.beginDT = beginDT;
	}
	public void setEndDT(Date endDT) {
		this.endDT = endDT;
	}
}
