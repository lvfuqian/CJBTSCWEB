package com.elegps.tscweb.model.hstmodel;

import java.util.Date;
import java.util.List;

public class forecastsVO {
	private Date date;
	private List<TbFisheryForecast> data;

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public List<TbFisheryForecast> getData() {
		return data;
	}

	public void setData(List<TbFisheryForecast> data) {
		this.data = data;
	}

}
