package com.elegps.tscweb.model.hstmodel;

public class TbGaleForecast {
	private int id;
	private String msid;
	private String windDirection; // ����
	private String color; // ��ɫ
	private String area; // �����ֲ���Χ
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

	public String getWindDirection() {
		return windDirection;
	}

	public void setWindDirection(String windDirection) {
		this.windDirection = windDirection;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
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

}
