/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.elegps.tscweb.form;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/** 
 * MyEclipse Struts
 * Creation date: 10-15-2008
 * 
 * XDoclet definition:
 * @struts.form name="addmsform"
 */
public class AddQuestionForm extends ActionForm {
	/*
	 * Generated fields
	 */
	private String type;
	
	private String question;

	private String telephone;
	
	private String cep;
	
	private String cname;
	
	private String recorder_time;

	public String getRecorder_time() {
		return recorder_time;
	}

	public void setRecorder_time(String recorder_time) {
		this.recorder_time = recorder_time;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}



}