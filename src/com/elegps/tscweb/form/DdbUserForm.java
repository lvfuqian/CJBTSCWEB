package com.elegps.tscweb.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.elegps.tscweb.model.TabSysconfig;
import com.elegps.tscweb.model.TabSysserverdbinfo;
import com.elegps.tscweb.model.TabSysuserroleId;
import com.elegps.tscweb.model.TabSysusersinfo;

public class DdbUserForm extends ActionForm {

	private static final long serialVersionUID = 1L;

	private TabSysconfig tbConfig;
	private TabSysuserroleId tbUser;
	private TabSysusersinfo tbInfo;
	private TabSysserverdbinfo tabDBInfo;

	public TabSysconfig getTbConfig() {
		return tbConfig;
	}

	public void setTbConfig(TabSysconfig tbConfig) {
		this.tbConfig = tbConfig;
	}

	public TabSysuserroleId getTbUser() {
		return tbUser;
	}

	public void setTbUser(TabSysuserroleId tbUser) {
		this.tbUser = tbUser;
	}

	public TabSysusersinfo getTbInfo() {
		return tbInfo;
	}

	public TabSysserverdbinfo getTabDBInfo() {
		return tabDBInfo;
	}

	public void setTabDBInfo(TabSysserverdbinfo tabDBInfo) {
		this.tabDBInfo = tabDBInfo;
	}

	public void setTbInfo(TabSysusersinfo tbInfo) {
		this.tbInfo = tbInfo;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	/**
	 * Method validate
	 * 
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
	 * 
	 * @param mapping
	 * @param request
	 */
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		// TODO Auto-generated method stub
	}

}
