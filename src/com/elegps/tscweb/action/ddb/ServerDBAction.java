package com.elegps.tscweb.action.ddb;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.elegps.tscweb.action.base.BaseAction;
import com.elegps.tscweb.comm.UserInfo;
import com.elegps.tscweb.model.TbUserInfo;

public class ServerDBAction extends BaseAction {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ActionForward af = null;
		String cmd = null;
		TbUserInfo userInfo = UserInfo.getUserInfo(request);
		if (userInfo != null) {

			// 如果没有就接收到参数默认显示全部类型
			if (request.getParameter("CMD") == null
					|| request.getParameter("CMD").equals("")) {
				cmd = "db_search";
			} else {
				cmd = request.getParameter("CMD");
			}
			if (cmd.equals("db_add")) {

			}
			if (cmd.equals("db_udpate")) {
			}
			if (cmd.equals("db_delete")) {
			}
			if (cmd.equals("db_search")) {
			}
			if(cmd.equals("db_to")){
				
			}

		} else {
			request.setAttribute("message", "Session过期，请重新登录");
			af = mapping.findForward("logging");
		}
		if (af == null) {
			af = mapping.findForward("succes");
		}
		return af;
	}
	public ActionForward addAction(){
		return null;
	}

}
