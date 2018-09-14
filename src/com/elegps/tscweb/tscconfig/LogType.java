package com.elegps.tscweb.tscconfig;

import java.io.Serializable;

/**
 * 日志类型
 * 
 * @author LuYun
 * 
 */
public class LogType implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String typeText;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTypeText() {
		return typeText;
	}

	public void setTypeText(String typeText) {
		this.typeText = typeText;
	}



}
