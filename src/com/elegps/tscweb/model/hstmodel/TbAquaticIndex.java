package com.elegps.tscweb.model.hstmodel;

import java.util.Date;

public class TbAquaticIndex {
	private int id;
	private String type; // 1-�ܼ۸�ָ��2-�¼۸�ָ��3-����ָ��4-�ɽ���
	private Date pubtime; // ����ʱ��
	private String time; // ʱ�䰮��
	private String amout; // ��ֵ
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getPubtime() {
		return pubtime;
	}

	public void setPubtime(Date pubtime) {
		this.pubtime = pubtime;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getAmout() {
		return amout;
	}

	public void setAmout(String amout) {
		this.amout = amout;
	}

}
