package com.elegps.tscweb.model.hstmodel;

import java.util.Date;

public class TbFisheryForecast {
	private int id;
	private String msid;
	private String name; // �泡����
	private String pubtime; // ����ʱ��
	private String type; // �泡���� 1-��ǰ�泡 2-��ϸ��λ��
	private String scale; // 3-����δ��3��Ԥ�����ݣ�Ĭ�ϣ� 5-����5�� 7-����7��
	private Date date; // ����
	private String windLevel; // �����ȼ�
	private String windDirection; // ����
	private String wave; // �˸�
	private String waveStr;
	private Date forecastDate; // Ԥ��ʱ��
	private String lon; // ����
	private String lat; // γ��

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
