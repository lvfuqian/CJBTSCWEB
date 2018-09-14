package com.elegps.tscweb.model.hstmodel;

import java.util.List;

public class forecastsDataVO {
	private String pubTime;
	private String name;
	private List<forecastsVO> forecasts;

	public String getPubTime() {
		return pubTime;
	}

	public void setPubTime(String pubTime) {
		this.pubTime = pubTime;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<forecastsVO> getForecasts() {
		return forecasts;
	}

	public void setForecasts(List<forecastsVO> forecasts) {
		this.forecasts = forecasts;
	}

}