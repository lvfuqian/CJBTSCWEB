package com.elegps.tscweb.action.EnterPrise;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.elegps.tscweb.action.base.BaseAction;
import com.elegps.tscweb.comm.HRAddress;
import com.elegps.tscweb.comm.UserInfo;
import com.elegps.tscweb.exception.MessageException;
import com.elegps.tscweb.form.AddEpForm;
import com.elegps.tscweb.form.AddmsForm;
import com.elegps.tscweb.model.TbEnterpriseInfo;
import com.elegps.tscweb.model.TbUserInfo;
import com.elegps.tscweb.model.TbUserLog;

/**
 * MyEclipse Struts Creation date: 10-20-2008
 * 
 * XDoclet definition:
 * 
 * @struts.action validate="true"
 */
public class EnterPriseListAction extends BaseAction {
	/*
	 * Generated Methods
	 */
	
	//wanglei 企业用户执行
	public static int a_id = 0;//代理商id
	public static int r_id = 0;//角色id
	public static int ep_id= 0;
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
			ep_id=Integer.parseInt(request.getSession().getAttribute("epId")+"");//角色企业id
			// 如果没有就接收到参数默认显示全部类型
			if (request.getParameter("CMD") == null
					|| request.getParameter("CMD").equals("")) {
				cmd = "ep_search";
			} else {
				cmd = request.getParameter("CMD");
			}

			if (cmd.equals("ep_search")) { // 所有条件同时查询
				actionforward = ep_Sarch(mapping, form, request, response);
			} else if (cmd.equals("xiangxi")) // 查看用户详细信息
			{
				actionforward = ep_xianxi(mapping, form, request, response);
			} else if (cmd.equals("epadd")) { // 跳转到jsp增加页面
				actionforward = ep_addjsp(mapping, form, request, response);
			} else if (cmd.equals("add")) { // 增加
				actionforward = ep_add(mapping, form, request, response);
			} else if (cmd.equals("modifyjsp")) { // 跳转到jsp修改页面
				actionforward = ep_modifyjsp(mapping, form, request, response);
			} else if (cmd.equals("modify")) { // 修改
				actionforward = ep_modify(mapping, form, request, response);
			} else if (cmd.equals("delete")) { // 删除
				actionforward = ep_delete(mapping, form, request, response);
			}
		} else {
			request.setAttribute("message", "Session过期，请重新登录");
			actionforward = mapping.findForward("logging");
		}

		return actionforward;
	}

	/**
	 * 根据ep列表信息的查询条件查询
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward ep_Sarch(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String pagentid = ""; // 一级代理商id
		String childagentid = ""; // 二级代理商id
		String type=request.getParameter("type");
		// if(request.getSession().getAttribute("agent_id").equals(null)){
		// //session丢失或没有登录,返回登录页面
		// }else{
		// agent_id=request.getSession().getAttribute("agent_id").toString();
		// }

		String agent = request.getSession().getAttribute("agentId")+"";
		
		if (request.getParameter("pagentid") != null && (agent.equals("-1") || agent.equals("0"))) {
			pagentid = request.getParameter("pagentid");
		} else {
			//pagentid = "-1"; // 代理商是总部
			pagentid = agent;
        	if(pagentid.equals("0"))
        		pagentid = "-1";
		}

		if (request.getParameter("childagentid") != null) {
			childagentid = request.getParameter("childagentid");
		} else {
			childagentid = "-2"; // 所有企业
		}
		String cmd = request.getParameter("CMD");
		String epname = request.getParameter("epname");
		if (epname == null || epname.equals("")) {
			epname = "";
		}
		
		int epCount = 0;
		epCount = epmanger.getEp_sertch(pagentid, childagentid, epname.trim(), ep_id, r_id);
		// 获取每页的条数
		int pageSize = 12;
		// 获取总页数
		int pageCount = epmanger.getPageCount(epCount, pageSize);
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
		request.setAttribute("epCount", epCount);
		// 返回每页面显示条数
		request.setAttribute("pageSize", pageSize);
		// 返回当前页
		request.setAttribute("currentPage", pageNo);
		// 设置返回的命令字
		request.setAttribute("CMD", cmd);

		// 返回的总页数
		request.setAttribute("pageCount", pageCount);
//		if(!pagentid.equals("-1")){
//			// 一级代理商名称
//			request.setAttribute("Pagentlist", agentmanger.getParentAgentNotIncludeZB());
//		}else{
			// 一级代理商名称
		int apid = Integer.parseInt(request.getSession().getAttribute("aPId")+"");
		if(apid == 0){
			request.setAttribute("Pagentlist", agentmanger.getParentAgent(a_id,r_id));
		//}
		// 一级指定二级代理商结果集
			if(r_id ==3 || r_id ==4){
				request.setAttribute("Cagentlist",null);
			}else{
				request.setAttribute("Cagentlist", agentmanger.getChildAgentByParamentid(pagentid));
			}
		}else{
			int a = agentmanger.getAgent_ByAgentID(apid+"").getAgent_Id();
			request.setAttribute("Pagentlist", agentmanger.getParentAgent(a,r_id));
			request.setAttribute("Cagentlist", agentmanger.getChildAgentByAId(agent));
		}
		// 设设置返回用户查询的select中option值
		request.setAttribute("pagentid", pagentid);
		request.setAttribute("childagentid", childagentid);
		request.setAttribute("epname", epname);
		// 返回用户单位信息
		List<TbEnterpriseInfo> list = null;
		if (r_id == 3 || r_id == 4){
			list = epmanger.getEpinfoby_mspage(pageNo,
					pageSize, pagentid, childagentid, epname.trim(), ep_id, r_id);
		}else{
			list = epmanger.getEpinfoby_mspage(pageNo,
					pageSize, pagentid, childagentid, epname.trim(), a_id, r_id);
		}
		request.setAttribute("epList",list );
//		request.setAttribute("epList", epmanger.getEpinfoby_mspage(pageNo,
//				pageSize, pagentid, childagentid, epname.trim(), a_id, r_id));
		if(type!=null&&!type.equals("null")){
			return mapping.findForward("kfsucces");
		}else{
			return mapping.findForward("succes");
		}
		
	}

	/**
	 * 用户单位详细信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward ep_xianxi(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		// 取得epid
		String epid = request.getParameter("epid").toString();
		TbEnterpriseInfo epinfo = null;
		String resultmessage = null;
		epinfo = epmanger.getEpinfo_byepid(epid);
		if (epinfo != null) {
			System.out.println("找到这条记录的详细信息");
			request
					.setAttribute("agentnames", epmanger
							.getAgent_ByEpName(epid));
			request.setAttribute("epinfo", epinfo);
			return mapping.findForward("xiangxi");
		} else { // 说明没有找到.
			System.out.println("没有找到这条记录的详细信息");
			resultmessage = "该记录不存在！";
			request.setAttribute("message", resultmessage);
			return mapping.findForward("showagentlist");
		}
	}

	/**
	 * 转到用户单位增加页面
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward ep_addjsp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String pagentid = ""; // 一级代理商id
		String childagentid = ""; // 二级代理商id
		String agent = request.getSession().getAttribute("agentId")+"";
		if (request.getParameter("pagentid") != null && (agent.equals("-1") || agent.equals("0"))) {
			pagentid = request.getParameter("pagentid");
		} else {
			//pagentid = "-1"; // 代理商是总部
        	pagentid = agent;
        	if(pagentid.equals("0"))
        		pagentid = "-1";
		}

		if (request.getParameter("childagentid") != null) {
			childagentid = request.getParameter("childagentid");
		} else {
			childagentid = "-1"; // 直属企业
		}
		request.setAttribute("pagentid", pagentid);
		request.setAttribute("childagentid", childagentid);
//		if(!pagentid.equals("-1")){
//			// 一级代理商名称
//			request.setAttribute("Pagentlist", agentmanger.getParentAgentNotIncludeZB());
//		}else{
			// 一级代理商名称
		int apid = Integer.parseInt(request.getSession().getAttribute("aPId")+"");
		if(apid == 0){
			request.setAttribute("Pagentlist", agentmanger.getParentAgent(a_id,r_id));
		//}
		// 一级指定二级代理商结果集
			request.setAttribute("Cagentlist", agentmanger.getChildAgentByParamentid(pagentid));
		}else{
			int a = agentmanger.getAgent_ByAgentID(apid+"").getAgent_Id();
			request.setAttribute("Pagentlist", agentmanger.getParentAgent(a,r_id));
			request.setAttribute("Cagentlist", agentmanger.getChildAgentByAId(agent));
		}
//			request.setAttribute("Pagentlist", agentmanger.getParentAgent(a_id,r_id));
////		}
//		// 一级指定二级代理商结果集
//		request.setAttribute("Cagentlist", agentmanger
//				.getChildAgentByParamentid(pagentid));
		return mapping.findForward("epaddjsp");

	}

	/**
	 * 用户单位增加页面
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward ep_add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String agent_id = "";
		AddEpForm epform = (AddEpForm) form;
		String ep_name = epform.getEp_name().trim();
		String ep_address = epform.getEp_address().trim();
		String ep_tel = epform.getEp_tel().trim();
		String ep_tel1 = epform.getEp_tel1().trim();
		String ep_email = epform.getEp_email().trim();
		String ep_url = epform.getEp_url().trim();
		String ep_man = epform.getEp_man().trim();
		String ep_man1 = epform.getEp_man1().trim();
		String ep_qq = epform.getEp_qq().trim();
		String ep_remark = epform.getEp_remark().trim();
		if (epform.getChildagentid().equals("-1")) { // 一级代理商直属企业
			agent_id = epform.getPagentid();
		} else { // 二级代理商下的企业
			agent_id = epform.getChildagentid();
		}
		TbEnterpriseInfo info = epmanger.getEpByName(ep_name);
		String resultmessage = null;

		String pagentid = ""; // 一级代理商id
		String childagentid = ""; // 二级代理商id
		if (request.getParameter("pagentid") != null) {
			pagentid = request.getParameter("pagentid");
		} else {
			pagentid = "-1"; // 代理商是总部
		}

		if (request.getParameter("childagentid") != null) {
			childagentid = request.getParameter("childagentid");
		} else {
			childagentid = "-1"; // 直属企业
		}

		if (info == null) {
			epmanger.createEp(ep_name, ep_address, ep_tel, ep_tel1, ep_email,
					ep_url, ep_man, ep_man1,ep_qq, agent_id, ep_remark);
			//记录操作日志
			TbUserLog userLog=new TbUserLog();
			userLog.setUserId(Integer.parseInt(request.getSession().getAttribute("uId").toString()));
			userLog.setlDate(new Date());
			userLog.setlAddress(HRAddress.getIpAddr(request));
			userLog.setlType(18);
			userLog.setlContent("添加企业:{"+ep_name+"}");
			logManager.save(userLog);
			resultmessage = "企业单位信息添加成功！";
		} else {
			resultmessage = "企业单位" + ep_name + "已经存在,添加失败!";
		}
		request.setAttribute("pagentid", pagentid);
		request.setAttribute("childagentid", childagentid);
		request.setAttribute("message", resultmessage);
		return mapping.findForward("showeplist");
	}

	/**
	 * 转到修改页面
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward ep_modifyjsp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String agent_id = "";
		// 取得epid
		String epid = request.getParameter("epid").toString();
		TbEnterpriseInfo epinfo = null;
		String resultmessage = null;
		epinfo = epmanger.getEpinfo_byepid(epid);
		String type=request.getParameter("type");
		if(type!=null){
	    	request.setAttribute("type",type );
	    }
		if (epinfo != null) {
			request.setAttribute("epinfo", epinfo);
			// 取得代理商类型 如果父id为0刚表示一级代理商下的企业，否则是二级代理商下的企业
			int agenttype = agentmanger.getAgenttype(String.valueOf(epinfo
					.getAgent_Id()));

			if (agenttype != 0) { // 二级代理商下的企业
				// 一级代理商名称
				request
						.setAttribute("Pagentlist", agentmanger
								.getParentAgent(agenttype,r_id));
				request.setAttribute("Cagentlist", agentmanger.getChildAgentByAId(epinfo.getAgent_Id()+""));
//				request.setAttribute("Cagentlist", agentmanger
//						.getChildAgentByParamentid(String.valueOf(agenttype)));
				request.setAttribute("pagentid", agenttype);
				request.setAttribute("childagentid", epinfo.getAgent_Id());
			} else { // 一级代理商直属企业
				// 一级代理商名称
				request
						.setAttribute("Pagentlist", agentmanger
								.getParentAgent(a_id,r_id));
				// 一级指定二级代理商结果集
				if(r_id ==3 || r_id ==4){
					request.setAttribute("Cagentlist",null);
				}else{
					request.setAttribute("Cagentlist", agentmanger
							.getChildAgentByParamentid(String.valueOf(epinfo
									.getAgent_Id())));
				}
				
				request.setAttribute("pagentid", epinfo.getAgent_Id());
				request.setAttribute("childagentid", "-1");
			}			
			return mapping.findForward("epmodifyjsp");
		} else { // 说明没有找到.
			System.out.println("没有找到这条记录的详细信息");
			resultmessage = "该记录不存在！";
			request.setAttribute("message", resultmessage);
			return mapping.findForward("showeplist");
		}
	}

	/**
	 * 修改
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward ep_modify(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String ep_id = request.getParameter("ep_id");
		String ep_name = request.getParameter("ep_name").trim();
		String ep_address = request.getParameter("ep_address").trim();
		String ep_tel = request.getParameter("ep_tel").trim();
		String ep_tel1 = request.getParameter("ep_tel1").trim();
		String ep_email = request.getParameter("ep_email").trim();
		String ep_url = request.getParameter("ep_url").trim();
		String ep_man = request.getParameter("ep_man").trim();
		String ep_man1 = request.getParameter("ep_man1").trim();
		String ep_qq = request.getParameter("ep_qq").trim();
		String ep_remark = request.getParameter("ep_remark").trim();
		String agent_id = "";
		if (request.getParameter("childagentid").equals("-1")) { // 一级代理商直属企业
			agent_id = request.getParameter("pagentid");
		} else { // 二级代理商下的企业
			agent_id = request.getParameter("childagentid");
		}

		String pagentid = ""; // 一级代理商id
		String childagentid = ""; // 二级代理商id
		if (request.getParameter("pagentid") != null) {
			pagentid = request.getParameter("pagentid");
		} else {
			pagentid = "-1"; // 代理商是总部
		}

		if (request.getParameter("childagentid") != null) {
			childagentid = request.getParameter("childagentid");
		} else {
			childagentid = "-1"; // 直属企业
		}

		String modifyok = epmanger.modifyEp(agent_id, ep_id, ep_name,
				ep_address, ep_tel, ep_tel1, ep_email, ep_url, ep_man, ep_man1,ep_qq,
				ep_remark);
		//记录操作日志
		TbUserLog userLog=new TbUserLog();
		userLog.setUserId(Integer.parseInt(request.getSession().getAttribute("uId").toString()));
		userLog.setlDate(new Date());
		userLog.setlAddress(HRAddress.getIpAddr(request));
		userLog.setlType(18);
		userLog.setlContent("修改企业:{"+ep_name+"}");
		logManager.save(userLog);
		String resultmessage = null;
		if (modifyok != null) {
			resultmessage = "终端信息修改成功！";
			request.setAttribute("message", resultmessage);
		} else {
			resultmessage = "终端信息修改失败！";
			request.setAttribute("message", resultmessage);
		}

		request.setAttribute("pagentid", pagentid);
		request.setAttribute("childagentid", childagentid);
		String type=request.getParameter("type");
		if(type!=null&&!type.equals("null")){
			return mapping.findForward("kfshoweplist");
		}else{
			return mapping.findForward("showeplist");
		}
		
	}

	/**
	 * 物理删除
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward ep_delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String[] list = request.getParameterValues("list");
		Boolean delsucc = epmanger.deleteEp(list);
		for (int i = 0; i < list.length; i++) {
			TbEnterpriseInfo enterpriseInfo=epmanger.getEpinfo_byepid(list[i]);
			if(enterpriseInfo!=null){
				//记录操作日志
				TbUserLog userLog=new TbUserLog();
				userLog.setUserId(Integer.parseInt(request.getSession().getAttribute("uId").toString()));
				userLog.setlDate(new Date());
				userLog.setlAddress(HRAddress.getIpAddr(request));
				userLog.setlType(18);
				userLog.setlContent("删除企业:{"+enterpriseInfo.getEp_Name()+"}");
				logManager.save(userLog);
			}
		}
		String resultmessage = null;
		if (delsucc) {
			resultmessage = "用户单位删除成功！";
			request.setAttribute("message", resultmessage);
			return mapping.findForward("showeplist");
		} else {
			resultmessage = "用户单位删除失败！";
			request.setAttribute("message", resultmessage);
			return mapping.findForward("showeplist");
		}

	}
}