package com.elegps.tscweb.model.hstmodel;

public class TbTyphoonDetail {
	private int id;
	private String no; // ̨����
	private String title; // ̨�����
	private String Fenglifor10; // ʮ����Ȧ�뾶(��λkm)
	private String Fenglifor7; // �߼���Ȧ�뾶(��λkm)
	private String Wind; // ���٣���λ��m/s��
	private String WindLevel; // ��������λ������
	private String Press; // ������ѹ����λ��hPa��
	private String Latitude; // ����λ��γ��
	private String Longitude;
	private String NowString; // ʵ��ʱ��
	private String Publisher; // ������BABZ-����̨Ԥ��BEHZ-�㽭ʡ����̨Ԥ��RJTD����Ԥ��PGTW-�ص�Ԥ��RCTP-̨��Ԥ��VHHH-���Ԥ��RKSL-����Ԥ��
	private String Ttype; // 1-�ȴ���ѹ2-�ȴ��籩3-ǿ�ȴ��籩4-̨��5-ǿ̨��6-��ǿ̨��
	private String Distance; //
	private String Interval;
	private int flag; // 0-̨��Ԥ��·�� 1-̨��ʵ��·��

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getFenglifor10() {
		return Fenglifor10;
	}

	public void setFenglifor10(String fenglifor10) {
		Fenglifor10 = fenglifor10;
	}

	public String getFenglifor7() {
		return Fenglifor7;
	}

	public void setFenglifor7(String fenglifor7) {
		Fenglifor7 = fenglifor7;
	}

	public String getWind() {
		return Wind;
	}

	public void setWind(String wind) {
		Wind = wind;
	}

	public String getWindLevel() {
		return WindLevel;
	}

	public void setWindLevel(String windLevel) {
		WindLevel = windLevel;
	}

	public String getPress() {
		return Press;
	}

	public void setPress(String press) {
		Press = press;
	}

	public String getLatitude() {
		return Latitude;
	}

	public void setLatitude(String latitude) {
		Latitude = latitude;
	}

	public String getLongitude() {
		return Longitude;
	}

	public void setLongitude(String longitude) {
		Longitude = longitude;
	}

	public String getNowString() {
		return NowString;
	}

	public void setNowString(String nowString) {
		NowString = nowString;
	}

	public String getPublisher() {
		return Publisher;
	}

	public void setPublisher(String publisher) {
		Publisher = publisher;
	}

	public String getTtype() {
		return Ttype;
	}

	public void setTtype(String ttype) {
		Ttype = ttype;
	}

	public String getDistance() {
		return Distance;
	}

	public void setDistance(String distance) {
		Distance = distance;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getInterval() {
		return Interval;
	}

	public void setInterval(String interval) {
		Interval = interval;
	}

}
