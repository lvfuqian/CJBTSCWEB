package com.elegps.tscweb.model;

import java.util.Date;

/**
 * 语音数据（adb数据库月表）TbVoiceData entity
 * @author wanglei
 *
 */
public class TabVoiceInfo {

	private String vdId;	//主键id
	private String msId;	//终端id
	private String grpId;	//群组id
	private Date beginDT;	//语音开始时间
	private Date endDT;		//语音结束时间
	
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
