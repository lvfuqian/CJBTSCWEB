package com.elegps.tscweb.model;

import java.util.Date;

/**
 * �������ݣ�adb���ݿ��±�TbVoiceData entity
 * @author wanglei
 *
 */
public class TabVoiceData{
	
	private Integer id;	//����id
	private String msId;	//�ն�id
	private String vdId;	//TbVoiceInfo���
	private String data;	//��������
	
	
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
