package com.elegps.tscweb.model;

import java.io.Serializable;
import java.util.Date;

public class PositionLast implements Serializable {
	private int vehicleId;
	private int userId;
	private Date gpsDate;//GPS时间
	private Date ReceiveDate;//接收时间
	private float longitude;//经度
	private float Latitude;//纬度
	private float height;//高度
	private float speed;//速度
	private int   direction;//方向
	private String Feasibility;//有效性
	private int  status;//状态
	private String  mome;
	private String status2;
	private float temperature;
	private Date differencetime ;
	private Date lastruntime;
	private Vehicle vehicle;
	
	public Vehicle getVehicle() {
		return vehicle;
	}
	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}
	public int getVehicleId() {
		return vehicleId;
	}
	public void setVehicleId(int vehicleId) {
		this.vehicleId = vehicleId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public Date getGpsDate() {
		return gpsDate;
	}
	public void setGpsDate(Date gpsDate) {
		this.gpsDate = gpsDate;
	}
	public Date getReceiveDate() {
		return ReceiveDate;
	}
	public void setReceiveDate(Date receiveDate) {
		ReceiveDate = receiveDate;
	}
	public float getLongitude() {
		return longitude;
	}
	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}
	public float getLatitude() {
		return Latitude;
	}
	public void setLatitude(float latitude) {
		Latitude = latitude;
	}
	public float getHeight() {
		return height;
	}
	public void setHeight(float height) {
		this.height = height;
	}
	public float getSpeed() {
		return speed;
	}
	public void setSpeed(float speed) {
		this.speed = speed;
	}
	public int getDirection() {
		return direction;
	}
	public void setDirection(int direction) {
		this.direction = direction;
	}
	public String getFeasibility() {
		return Feasibility;
	}
	public void setFeasibility(String feasibility) {
		Feasibility = feasibility;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getMome() {
		return mome;
	}
	public void setMome(String mome) {
		this.mome = mome;
	}
	public String getStatus2() {
		return status2;
	}
	public void setStatus2(String status2) {
		this.status2 = status2;
	}
	public float getTemperature() {
		return temperature;
	}
	public void setTemperature(float temperature) {
		this.temperature = temperature;
	}
	public Date getDifferencetime() {
		return differencetime;
	}
	public void setDifferencetime(Date differencetime) {
		this.differencetime = differencetime;
	}
	public Date getLastruntime() {
		return lastruntime;
	}
	public void setLastruntime(Date lastruntime) {
		this.lastruntime = lastruntime;
	}
	
}
