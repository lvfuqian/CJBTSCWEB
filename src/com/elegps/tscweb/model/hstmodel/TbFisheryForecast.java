package com.elegps.tscweb.model.hstmodel;

import java.util.Date;

public class TbFisheryForecast {
	private int id;
	private String msid;
	private String name; // 渔场名称
	private String pubtime; // 发布时间
	private String type; // 渔场类型 1-当前渔场 2-精细化位置
	private String scale; // 3-返回未来3天预报数据（默认） 5-返回5天 7-返回7天
	private Date date; // 日期
	private String windLevel; // 风力等级
	private String windDirection; // 风向
	private String wave; // 浪高
	private String waveStr;
	private Date forecastDate; // 预报时次
	private String lon; // 经度
	private String lat; // 纬度

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMsid() {
		return msid;
	}

	public void setMsid(String msid) {
		this.msid = msid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPubtime() {
		return pubtime;
	}

	public void setPubtime(String pubtime) {
		this.pubtime = pubtime;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getScale() {
		return scale;
	}

	public void setScale(String scale) {
		this.scale = scale;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getWindLevel() {
		return windLevel;
	}

	public void setWindLevel(String windLevel) {
		this.windLevel = windLevel;
	}

	public String getWindDirection() {
		return windDirection;
	}

	public void setWindDirection(String windDirection) {
		this.windDirection = windDirection;
	}

	public String getWave() {
		return wave;
	}

	public void setWave(String wave) {
		this.wave = wave;
	}

	public Date getForecastDate() {
		return forecastDate;
	}

	public void setForecastDate(Date forecastDate) {
		this.forecastDate = forecastDate;
	}

	public String getLon() {
		return lon;
	}

	public void setLon(String lon) {
		this.lon = lon;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getWaveStr() {
		return waveStr;
	}

	public void setWaveStr(String waveStr) {
		this.waveStr = waveStr;
	}

}
