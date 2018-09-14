package com.elegps.tscweb.model.hstmodel;

public class TbForecastReference {
	private int id;
	private String forecastSource; // 气象台
	private String pubtime; // 发布时间
	private String type; // 0-海区预报 1-渔场预报
	private String content; //
	private String windLive;
	private String visibility;
	private String windDirectionStr;
	private String forecastHour;
	private String weather;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getForecastSource() {
		return forecastSource;
	}

	public void setForecastSource(String forecastSource) {
		this.forecastSource = forecastSource;
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getWindLive() {
		return windLive;
	}

	public void setWindLive(String windLive) {
		this.windLive = windLive;
	}

	public String getVisibility() {
		return visibility;
	}

	public void setVisibility(String visibility) {
		this.visibility = visibility;
	}

	public String getWindDirectionStr() {
		return windDirectionStr;
	}

	public void setWindDirectionStr(String windDirectionStr) {
		this.windDirectionStr = windDirectionStr;
	}

	public String getForecastHour() {
		return forecastHour;
	}

	public void setForecastHour(String forecastHour) {
		this.forecastHour = forecastHour;
	}

	public String getWeather() {
		return weather;
	}

	public void setWeather(String weather) {
		this.weather = weather;
	}

}
