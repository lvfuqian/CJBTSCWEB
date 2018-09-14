package com.elegps.tscweb.model;

import java.util.Date;

/**
 * 语音数据（adb数据库月表）TbVoiceData entity
 * @author wanglei
 *
 */
public class TabVoiceData{
	
	private Integer id;	//主键id
	private String msId;	//终端id
	private String vdId;	//TbVoiceInfo外键
	private String data;	//语音数据
	
	
	public Integer getId() {
		return id;
	}
	public String getMsId() {
		return msId;
	}
	public String getVdId() {
		return vdId;
	}
	public String getData() {
		return data;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setMsId(String msId) {
		this.msId = msId;
	}
	public void setVdId(String vdId) {
		this.vdId = vdId;
	}
	public void setData(String data) {
		this.data = data;
	}
}
