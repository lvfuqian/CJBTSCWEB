package com.elegps.tscweb.action.orgadvance;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.elegps.tscweb.action.base.BaseAction;
import com.elegps.tscweb.comm.UserInfo;
import com.elegps.tscweb.exception.MessageException;
import com.elegps.tscweb.model.TbAgentInfo;
import com.elegps.tscweb.model.TbEnterpriseInfo;
import com.elegps.tscweb.model.TbORGAdvanceInfo;
import com.elegps.tscweb.model.TbUserInfo;

public class OrgAdvanceListAction extends BaseAction {
	/*
	 * Generated Methods
	 */
	
	//wanglei 企业用户执行
	public static int a_id = 0;//代理商id
	public static int r_id = 0;//角色id
	public static int ep_id = 0;//企业id
	/**
	 * Method execute
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws MessageException
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MessageException {
		ActionForward actionforward = null;
		String cmd = null;
		TbUserInfo user = UserInfo.getUserInfo(request);
		if (user != null) {
			
			//wanglei 
			a_id = Integer.parseInt(request.getSession().getAttribute("agentId")+"");//代理商id
			r_id = Integer.parseInt(request.getSession().getAttribute("roleId")+"");//角色id
			ep_id =Integer.parseInt(request.getSession().getAttribute("epId")+"");//角色id
			// 如果没有就接收到参数默认显示全部类型
			if (request.getParameter("CMD") == null
					|| request.getParameter("CMD").equals("")) {
				cmd = "advance_search";
			} else {
				cmd = request.getParameter("CMD");
			}
			if (cmd.equals("advance_search")) { // 预充值所有条件同时查询
				actionforward = advance_Sarch(mapping, form, request, response);
			} else if (cmd.equals("advance_addjsp")) {// 到添加页面
				actionforward = advance_addjsp(mapping, form, request, response);
			} else if (cmd.equals("advanceadd")) {// 添加预充值
				actionforward = advance_add(mapping, form, request, response);
			} else if (cmd.equals("modifyjsp")) {// 修改页面
				actionforward = advance_modifyjsp(mapping, form, request,
						response);
			} else if (cmd.equals("modify")) {// 修改预充值信息
				actionforward = advance_modify(mapping, form, request, response);
			} else if (cmd.equals("delete")) {// 删除预充值
				actionforward = advance_delet(mapping, form, request, response);
			} else if (cmd.equals("xinaxi")) // 查看预充值详细信息
			{
				actionforward = advance_xianxi(mapping, form, request, response);
			} else if (cmd.equals("validate_search")) {
				actionforward = validate_search(mapping, form, request,
						response);
			} else if (cmd.equals("validatejsp")) {
				actionforward = validate_jsp(mapping, form, request, response);
			} else if (cmd.equals("validate")) {
				actionforward = validate(mapping, form, request, response);
			} else if (cmd.equals("charge_search")) { // 根据代理商,企业条件查询充值历史记录信息
				actionforward = charge_search(mapping, form, request, response);
			}
		} else {
			request.setAttribute("message", "Session过期，请重新登录");
			actionforward = mapping.findForward("logging");
		}
		return actionforward;
	}

	/**
	 * 根据代理商,企业条件查询预充值信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward advance_Sarch(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String pagentid = ""; // 一级代理商id
		String childagentid = ""; // 二级代理商id
		String ep = ""; // 企业id
		String cmd = request.getParameter("CMD");
		String advanceresult = request.getParameter("aresult"); // 验证结果
		HttpSession session = request.getSession(true);
		TbUserInfo userInfo = (TbUserInfo) session.getAttribute("user");
		if (request.getParameter("pagentid") != null) {
			pagentid = request.getParameter("pagentid");
		} else {
			pagentid = "-1"; // 代理商是总部
		}

		if (request.getParameter("childagentid") != null
				&& !request.getParameter("childagentid").equals("")) {
			childagentid = request.getParameter("childagentid");
		} else {
			childagentid = "-2"; // 所有企业
		}

		if (request.getParameter("ep") != null
				&& !request.getParameter("ep").equals("")) {
			ep = request.getParameter("ep");
		} else {
			ep = "-1"; // 全部企业
		}
		if (advanceresult == null) {
			advanceresult = "";
		}
		int advanceCount = 0;
		advanceCount = advancemanger.getAdvance_SearchCount(pagentid,
				childagentid, ep, advanceresult);
		// 获取每页的条数
		int pageSize = 12;
		// 获取总页数
		int pageCount = advancemanger.getPageCount(advanceCount, pageSize);
		// 从页面取得当前页
		int pageNo;
		String pageNoStr = request.getParameter("pageNo");
		if (pageNoStr == null || pageNoStr.trim().equals("")) {
			pageNo = 1;
		} else {
			pageNo = Integer.parseInt(request.getParameter("pageNo"));
		}
		// 如果请求页已经超出了最大页
		if (pageNo > pageCount) {
			pageNo = pageCount;
		}
		// 如果请求页小于一页
		if (pageNo < 1) {
			pageNo = 1;
		}
		// 获取总条数
		request.setAttribute("advanceCount", advanceCount);
		// 返回每页面显示条数
		request.setAttribute("pageSize", pageSize);
		// 返回当前页
		request.setAttribute("currentPage", pageNo);
		// 设置返回的命令字
		request.setAttribute("CMD", cmd);

		request.setAttribute("pagentid", pagentid);
		request.setAttribute("childagentid", childagentid);
		request.setAttribute("ep", ep);
		request.setAttribute("aresult", advanceresult);
		// 一级代理商名称
		request.setAttribute("Pagentlist", agentmanger.getParentAgent(a_id,r_id));
		// 一级指定二级代理商结果集
		request.setAttribute("Cagentlist", agentmanger
				.getChildAgentByParamentid(pagentid));
		// 返回所有企业默认
		List<TbEnterpriseInfo> list = null;
		if (r_id == 3){
			list = epmanger.getEpinfo_byeid(pagentid,
					childagentid, ep_id, r_id);
		}else{
			list = epmanger.getEpinfo_byagentid(pagentid,
					childagentid, a_id, r_id);
		}
		request.setAttribute("epList", list);

		// 返回的总页数
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("advanceList", advancemanger
				.getAdvance_SearchByPage(pageNo, pageSize, pagentid,
						childagentid, ep, advanceresult));
		return mapping.findForward("succes");
	}

	public ActionForward advance_xianxi(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		// 取得advanceid预充值id
		String advanceid = request.getParameter("advanceid");
		String advancetype = "";
		String advanename = "";
		TbORGAdvanceInfo orgadvance = null;
		orgadvance = advancemanger.getTbORGAdvanceInfo_byadvancetid(advanceid);
		if (orgadvance != null) {
			if (orgadvance.getOrgType() == 1)// 类型为代理商
			{
				advancetype = "代理商";
				advanename = agentmanger.getAgent_ByAgentID(
						orgadvance.getOrgId().toString()).getAgent_Name();
			} else {
				advancetype = "企业";
				advanename = epmanger.getEpinfo_byepid(
						orgadvance.getOrgId().toString()).getEp_Name();
			}
			request.setAttribute("advaneceinfo", orgadvance);
			request.setAttribute("advancetype", advancetype);
			request.setAttribute("advanename", advanename);
			return mapping.findForward("xiangxi");
		} else {
			String resultmessage = null;
			resultmessage = "该记录不存在!";
			request.setAttribute("message", resultmessage);
			return mapping.findForward("showadvancelist");
		}

	}

	/**
	 * 代理商信息删除
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws MessageException
	 */
	public ActionForward advance_delet(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MessageException {
		String[] aidlist = request.getParameterValues("list");
		Boolean advamcedelete = advancemanger.deleteAdvance(aidlist);
		String resultmessage = null;
		if (advamcedelete) {
			resultmessage = "删除成功";
			request.setAttribute("message", resultmessage);
			return mapping.findForward("showadvancelist");
		} else {
			resultmessage = "删除失败";
			request.setAttribute("message", resultmessage);
			return mapping.findForward("showadvancelist");
		}
	}

	/**
	 * 添加前的准备页面
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward advance_addjsp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MessageException {
		// 一级代理商名称
		request.setAttribute("Pagentlist", agentmanger.getParentAgent(a_id,r_id));
		return mapping.findForward("addadvancejsp");
	}

	/**
	 * 添加预充值信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws MessageException
	 */
	public ActionForward advance_add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MessageException {
		TbUserInfo user = UserInfo.getUserInfo(request);
		String orgtype = ""; // 1为代理商,2为企业
		String orgid = "";
		if (!request.getParameter("pagentid").equals("")) {
			orgtype = "1";
			orgid = request.getParameter("pagentid");
		}
		if (!request.getParameter("childagentid").equals("")
				&& !request.getParameter("childagentid").equals("-1")) {
			orgtype = "1";
			orgid = request.getParameter("childagentid");
		}
		if (!request.getParameter("ep").equals("")
				&& request.getParameter("ep") != null) {
			orgtype = "2";
			orgid = request.getParameter("ep");
		}
		String advance = request.getParameter("advance");
		String resultmessage = null;
		resultmessage = advancemanger.createAdvance(orgtype, orgid, advance,
				user.getUserName());
		request.setAttribute("message", resultmessage);
		return mapping.findForward("showadvancelist");

	}

	/**
	 * 准备修改时的页面信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws MessageException
	 */
	public ActionForward advance_modifyjsp(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws MessageException {
		String advanceid = request.getParameter("advanceid");
		String advancetype = "";
		String advanename = "";
		TbORGAdvanceInfo orgadvance = null;
		orgadvance = advancemanger.getTbORGAdvanceInfo_byadvancetid(advanceid);
		if (orgadvance != null) {
			if (orgadvance.getOrgType() == 1)// 类型为代理商
			{
				advancetype = "代理商";
				advanename = agentmanger.getAgent_ByAgentID(
						orgadvance.getOrgId().toString()).getAgent_Name();
			} else {
				advancetype = "企业";
				advanename = epmanger.getEpinfo_byepid(
						orgadvance.getOrgId().toString()).getEp_Name();
			}
			request.setAttribute("advaneceinfo", orgadvance);
			request.setAttribute("advancetype", advancetype);
			request.setAttribute("advanename", advanename);
			return mapping.findForward("modiadvancejsp");
		} else {
			String resultmessage = null;
			resultmessage = "该记录不存在!";
			request.setAttribute("message", resultmessage);
			return mapping.findForward("showadvancelist");
		}
	}

	/**
	 * 修改预充值信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws MessageException
	 */
	public ActionForward advance_modify(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MessageException {
		String advanceid = request.getParameter("advanceid");
		String advance = request.getParameter("advance");
		String modifymess = advancemanger.modifyAdvanece(advanceid, advance);
		request.setAttribute("message", modifymess);
		return mapping.findForward("showadvancelist");

	}

	/**
	 * 根据代理商,企业条件查询预充值信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward validate_search(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		String pagentid = ""; // 一级代理商id
		String childagentid = ""; // 二级代理商id
		String ep = ""; // 企业id
		String cmd = request.getParameter("CMD");
		String advanceresult = request.getParameter("aresult"); // 验证结果
		HttpSession session = request.getSession(true);
		TbUserInfo userInfo = (TbUserInfo) session.getAttribute("user");
		if (request.getParameter("pagentid") != null) {
			pagentid = request.getParameter("pagentid");
		} else {
			pagentid = "-1"; // 代理商是总部
		}

		if (request.getParameter("childagentid") != null
				&& !request.getParameter("childagentid").equals("")) {
			childagentid = request.getParameter("childagentid");
		} else {
			childagentid = "-2"; // 所有企业
		}

		if (request.getParameter("ep") != null
				&& !request.getParameter("ep").equals("")) {
			ep = request.getParameter("ep");
		} else {
			ep = "-1"; // 全部企业
		}
		if (advanceresult == null) {
			advanceresult = "";
		}
		int advanceCount = 0;
		advanceCount = advancemanger.getAdvance_SearchCount(pagentid,
				childagentid, ep, advanceresult);
		// 获取每页的条数
		int pageSize = 12;
		// 获取总页数
		int pageCount = advancemanger.getPageCount(advanceCount, pageSize);
		// 从页面取得当前页
		int pageNo;
		String pageNoStr = request.getParameter("pageNo");
		if (pageNoStr == null || pageNoStr.trim().equals("")) {
			pageNo = 1;
		} else {
			pageNo = Integer.parseInt(request.getParameter("pageNo"));
		}
		// 如果请求页已经超出了最大页
		if (pageNo > pageCount) {
			pageNo = pageCount;
		}
		// 如果请求页小于一页
		if (pageNo < 1) {
			pageNo = 1;
		}
		// 获取总条数
		request.setAttribute("advanceCount", advanceCount);
		// 返回每页面显示条数
		request.setAttribute("pageSize", pageSize);
		// 返回当前页
		request.setAttribute("currentPage", pageNo);
		// 设置返回的命令字
		request.setAttribute("CMD", cmd);

		request.setAttribute("pagentid", pagentid);
		request.setAttribute("childagentid", childagentid);
		request.setAttribute("ep", ep);
		request.setAttribute("aresult", advanceresult);
		// 一级代理商名称
		request.setAttribute("Pagentlist", agentmanger.getParentAgent(a_id,r_id));
		// 一级指定二级代理商结果集
		request.setAttribute("Cagentlist", agentmanger
				.getChildAgentByParamentid(pagentid));
		// 返回所有企业默认
		List<TbEnterpriseInfo> list = null;
		if (r_id == 3){
			list = epmanger.getEpinfo_byeid(pagentid,
					childagentid, ep_id, r_id);
		}else{
			list = epmanger.getEpinfo_byagentid(pagentid,
					childagentid, a_id, r_id);
		}
		request.setAttribute("epList", list);

		// 返回的总页数
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("advanceList", advancemanger
				.getAdvance_SearchByPage(pageNo, pageSize, pagentid,
						childagentid, ep, advanceresult));
		return mapping.findForward("valisucces");
	}

	/**
	 * 验证时的页面信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws MessageException
	 */
	public ActionForward validate_jsp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MessageException {
		String advanceid = request.getParameter("advanceid");
		String advancetype = "";
		String advanename = "";
		TbORGAdvanceInfo orgadvance = null;
		orgadvance = advancemanger.getTbORGAdvanceInfo_byadvancetid(advanceid);
		if (orgadvance != null) {
			if (orgadvance.getOrgType() == 1)// 类型为代理商
			{
				advancetype = "代理商";
				advanename = agentmanger.getAgent_ByAgentID(
						orgadvance.getOrgId().toString()).getAgent_Name();
			} else {
				advancetype = "企业";
				advanename = epmanger.getEpinfo_byepid(
						orgadvance.getOrgId().toString()).getEp_Name();
			}
			request.setAttribute("advaneceinfo", orgadvance);
			request.setAttribute("advancetype", advancetype);
			request.setAttribute("advanename", advanename);
			return mapping.findForward("validatejsp");
		} else {
			String resultmessage = null;
			resultmessage = "该记录不存在!";
			request.setAttribute("message", resultmessage);
			return mapping.findForward("valisucces");
		}
	}

	/**
	 * 验证
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws MessageException
	 */
	public ActionForward validate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MessageException {
		TbUserInfo user = UserInfo.getUserInfo(request);
		String advanceid = request.getParameter("advanceid");
		String aresult = request.getParameter("aresult");
		String remark = request.getParameter("remark");
		String validatemess = advancemanger.validate(advanceid, aresult,
				remark, user.getUserName());
		request.setAttribute("message", validatemess);
		return mapping.findForward("valisucces");

	}

	/**
	 * 根据代理商,企业条件查询充值历史记录信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward charge_search(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String pagentid = ""; // 一级代理商id
		String childagentid = ""; // 二级代理商id
		String ep = ""; // 企业id
		String cmd = request.getParameter("CMD");
		HttpSession session = request.getSession(true);
		TbUserInfo userInfo = (TbUserInfo) session.getAttribute("user");
		if (request.getParameter("pagentid") != null) {
			pagentid = request.getParameter("pagentid");
		} else {
			pagentid = "-1"; // 代理商是总部
		}

		if (request.getParameter("childagentid") != null) {
			childagentid = request.getParameter("childagentid");
		} else {
			childagentid = "-2"; // 所有企业
		}

		if (request.getParameter("ep") != null) {
			ep = request.getParameter("ep");
		} else {
			ep = "-1"; // 全部企业
		}
		int chargeCount = 0;
		chargeCount = advancemanger.getCharge_SearchCount(pagentid,
				childagentid, ep);
		// 获取每页的条数
		int pageSize = 12;
		// 获取总页数
		int pageCount = advancemanger.getPageCount(chargeCount, pageSize);
		// 从页面取得当前页
		int pageNo;
		String pageNoStr = request.getParameter("pageNo");
		if (pageNoStr == null || pageNoStr.trim().equals("")) {
			pageNo = 1;
		} else {
			pageNo = Integer.parseInt(request.getParameter("pageNo"));
		}
		// 如果请求页已经超出了最大页
		if (pageNo > pageCount) {
			pageNo = pageCount;
		}
		// 如果请求页小于一页
		if (pageNo < 1) {
			pageNo = 1;
		}
		// 获取总条数
		request.setAttribute("chargeCount", chargeCount);
		// 返回每页面显示条数
		request.setAttribute("pageSize", pageSize);
		// 返回当前页
		request.setAttribute("currentPage", pageNo);
		// 设置返回的命令字
		request.setAttribute("CMD", cmd);

		request.setAttribute("pagentid", pagentid);
		request.setAttribute("childagentid", childagentid);
		request.setAttribute("ep", ep);
		// 一级代理商名称
		request.setAttribute("Pagentlist", agentmanger.getParentAgent(a_id,r_id));
		// 一级指定二级代理商结果集
		request.setAttribute("Cagentlist", agentmanger
				.getChildAgentByParamentid(pagentid));
		// 返回所有企业默认
		List<TbEnterpriseInfo> list = null;
		if (r_id == 3){
			list = epmanger.getEpinfo_byeid(pagentid,
					childagentid, ep_id, r_id);
		}else{
			list = epmanger.getEpinfo_byagentid(pagentid,
					childagentid, a_id, r_id);
		}
		request.setAttribute("epList", list);
		// 返回的总页数
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("chargeList", advancemanger
				.getCharge_SearchByPage(pageNo, pageSize, pagentid,
						childagentid, ep));
		return mapping.findForward("chargesucces");
	}

}