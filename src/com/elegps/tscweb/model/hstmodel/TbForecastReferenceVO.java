package com.elegps.tscweb.model.hstmodel;

import java.util.List;

public class TbForecastReferenceVO {
	private String pubtime;
	private String type;
	private String title;
	private List<TbForecastReferenceData> content;

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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<TbForecastReferenceData> getContent() {
		return content;
	}

	public void setContent(List<TbForecastReferenceData> content) {
		this.content = content;
	}

}
