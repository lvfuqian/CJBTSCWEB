package com.elegps.tscweb.action.ddb;

import com.elegps.tscweb.action.base.BaseAction;
import com.elegps.tscweb.model.TbGrpInfo;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class AjaxAction extends BaseAction {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ActionForward af = null;
		String doaction = "";
		if (request.getParameter("DA") != null
				&& !"".equals(request.getParameter("DA"))) {
			if (request.getParameter("DA") == "grp"||"grp".equals(request.getParameter("DA"))) {
				af = listGrpInfo(mapping, form, request, response);
			}
			if (request.getParameter("DA") == "ms"||"ms".equals(request.getParameter("DA"))) {
				af = listMsInfo(mapping, form, request, response);
			}
		}
		return af;
	}

	public ActionForward listGrpInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String pagentid = request.getParameter("pagentid");
		String childagentid = request.getParameter("childagentid");
		String ep = request.getParameter("ep");
		List<TbGrpInfo> listgrp = grpmanager.getAllGrp_Info(pagentid,
				childagentid, ep);
		try{
		request.setAttribute("listgrp", listgrp);
		if (listgrp != null) {
			System.out.println(">>>>>>>jquery json is mothod >>>>>>>>>>>>>>");
			TbGrpInfo grpinfo;
			for (int i = 0; i < listgrp.size(); i++) {
				grpinfo = (TbGrpInfo) listgrp.get(i);
				response.getWriter().write("{id:'" + grpinfo.getGrpid()+"',"+"name:'" + grpinfo.getGrpname()+"-"+grpinfo.getGrpid()+"'}<br/>");
//				System.out.println("{id:'" + grpinfo.getGrpid()+"',"+"name:'" + grpinfo.getGrpname()+"-"+grpinfo.getGrpid()+"'},");
			}
		}
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

	public ActionForward listMsInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return null;
	}
}
