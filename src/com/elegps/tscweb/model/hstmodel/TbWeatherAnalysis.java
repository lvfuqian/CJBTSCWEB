package com.elegps.tscweb.model.hstmodel;

public class TbWeatherAnalysis {
	private int id;
	private String msid;
	private String result;	//��ϸ��Ϣ
	private String lon;		//��ǰ���γ��
	private String lat;		//��ǰ��ľ���

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

	
	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
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
