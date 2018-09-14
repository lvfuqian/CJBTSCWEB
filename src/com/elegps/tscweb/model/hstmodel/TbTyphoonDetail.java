package com.elegps.tscweb.model.hstmodel;

public class TbTyphoonDetail {
	private int id;
	private String no; // 台风编号
	private String title; // 台风标题
	private String Fenglifor10; // 十级风圈半径(单位km)
	private String Fenglifor7; // 七级风圈半径(单位km)
	private String Wind; // 风速（单位：m/s）
	private String WindLevel; // 风力（单位：级）
	private String Press; // 中心气压（单位：hPa）
	private String Latitude; // 中心位置纬度
	private String Longitude;
	private String NowString; // 实况时次
	private String Publisher; // 发布者BABZ-中央台预报BEHZ-浙江省气象台预报RJTD东京预报PGTW-关岛预报RCTP-台北预报VHHH-香港预报RKSL-韩国预报
	private String Ttype; // 1-热带低压2-热带风暴3-强热带风暴4-台风5-强台风6-超强台风
	private String Distance; //
	private String Interval;
	private int flag; // 0-台风预报路径 1-台风实况路径

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
