package com.elegps.tscweb.tscconfig;

import java.io.Serializable;

/**
 * ��Ӧxml�ļ���Bean
 * 
 * @author LuYun
 * 
 */
public class XmlBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Ԫ������
	private String eName;
	// Ԫ������
	private String eValue;
	// Ԫ������
	private String eType;
	// Ԫ��˵��
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
