package com.elegps.tscweb.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class AddAgentForm extends ActionForm {
	
	private static final long serialVersionUID = 1L;

	/** password property */
	private Integer agentid;

	/** ms_type property */
	private String agentname;

	/** ms_id property */
	private String agentaddress;

	/** ms_name property */
	private String agenttel;
	
	private String agentemail;
	private String agenturl;
	private String agentman;
	private String agentman1;
	private String agentqq;
	private Integer agentpid;
	private String agentremark;
	private String agenttel1;

	/*
	 * Generated Methods
	 */

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	/** 
	 * Method validate
	 * @param mapping
	 * @param request
	 * @return ActionErrors
	 */
	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	/** 
	 * Method reset
	 * @param mapping
	 * @param request
	 */
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		// TODO Auto-generated method stub
	}

	public String getAgentname() {
		return agentname;
	}

	public void setAgentname(String agentname) {
		this.agentname = agentname;
	}

	public String getAgentaddress() {
		return agentaddress;
	}

	public void setAgentaddress(String agentaddress) {
		this.agentaddress = agentaddress;
	}

	public String getAgenttel() {
		return agenttel;
	}

	public void setAgenttel(String agenttel) {
		this.agenttel = agenttel;
	}

	public String getAgentemail() {
		return agentemail;
	}

	public void setAgentemail(String agentemail) {
		this.agentemail = agentemail;
	}

	public String getAgenturl() {
		return agenturl;
	}

	public void setAgenturl(String agenturl) {
		this.agenturl = agenturl;
	}

	public String getAgentman() {
		return agentman;
	}

	public void setAgentman(String agentman) {
		this.agentman = agentman;
	}

	public String getAgentqq() {
		return agentqq;
	}

	public void setAgentqq(String agentqq) {
		this.agentqq = agentqq;
	}

	public Integer getAgentpid() {
		return agentpid;
	}

	public void setAgentpid(Integer agentpid) {
		this.agentpid = agentpid;
	}

	public String getAgentremark() {
		return agentremark;
	}

	public void setAgentremark(String agentremark) {
		this.agentremark = agentremark;
	}

	public Integer getAgentid() {
		return agentid;
	}

	public void setAgentid(Integer agentid) {
		this.agentid = agentid;
	}

	public String getAgenttel1() {
		return agenttel1;
	}

	public void setAgenttel1(String agenttel1) {
		this.agenttel1 = agenttel1;
	}

	public String getAgentman1() {
		return agentman1;
	}

	public void setAgentman1(String agentman1) {
		this.agentman1 = agentman1;
	}

	
}
