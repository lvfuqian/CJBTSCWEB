package com.elegps.tscweb.model.hstmodel;

import java.util.Date;

public class TbAquaticIndex {
	private int id;
	private String type; // 1-周价格指数2-月价格指数3-景气指数4-成交量
	private Date pubtime; // 发布时间
	private String time; // 时间爱你
	private String amout; // 数值
	

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
