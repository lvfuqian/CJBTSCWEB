package com.elegps.tscweb.tscconfig;

import java.io.Serializable;

/**
 * 对应xml文件的Bean
 * 
 * @author LuYun
 * 
 */
public class XmlBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// 元素名称
	private String eName;
	// 元素内容
	private String eValue;
	// 元素类型
	private String eType;
	// 元素说明
	private String eComment;

	public String geteName() {
		return eName;
	}

	public void seteName(String eName) {
		this.eName = eName;
	}

	public String geteValue() {
		return eValue;
	}

	public void seteValue(String eValue) {
		this.eValue = eValue;
	}

	public String geteType() {
		return eType;
	}

	public void seteType(String eType) {
		this.eType = eType;
	}

	public String geteComment() {
		return eComment;
	}

	public void seteComment(String eComment) {
		this.eComment = eComment;
	}

}
