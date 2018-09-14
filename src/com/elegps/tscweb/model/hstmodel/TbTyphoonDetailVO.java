package com.elegps.tscweb.model.hstmodel;

import java.util.List;

public class TbTyphoonDetailVO {
	private String no;
	private List<TbTyphoonDetail> livePaths;
	private List<TbTyphoonDetail> forecastPaths;

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public List<TbTyphoonDetail> getLivePaths() {
		return livePaths;
	}

	public void setLivePaths(List<TbTyphoonDetail> livePaths) {
		this.livePaths = livePaths;
	}

	public List<TbTyphoonDetail> getForecastPaths() {
		return forecastPaths;
	}

	public void setForecastPaths(List<TbTyphoonDetail> forecastPaths) {
		this.forecastPaths = forecastPaths;
	}

}
