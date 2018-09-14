package com.elegps.tscweb.action.grp;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.elegps.tscweb.action.base.BaseAction;
import com.elegps.tscweb.comm.GrpIdRule;
import com.elegps.tscweb.comm.HRAddress;
import com.elegps.tscweb.comm.UserInfo;
import com.elegps.tscweb.exception.MessageException;
import com.elegps.tscweb.form.AddgrpForm;

import com.elegps.tscweb.model.TabBaseGrpextinfo;
import com.elegps.tscweb.model.TbAgentInfo;
import com.elegps.tscweb.model.TbEnterpriseInfo;
import com.elegps.tscweb.model.TbGrpInfo;
import com.elegps.tscweb.model.TbMsInfo;
import com.elegps.tscweb.model.TbUserInfo;
import com.elegps.tscweb.model.TbUserLog;
import com.elegps.tscweb.tscconfig.FilePathConfig;

/**
 * MyEclipse Struts Creation date: 10-20-2008
 * 
 * XDoclet definition:
 * 
 * @struts.action validate="true"
 */
public class GrpListAction extends BaseAction {
	/*
	 * Generated Methods
	 */
	//wanglei 企业用户执行
	public static int agent_id = 0;//代理商id
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
			agent_id =Integer.parseInt(request.getSession().getAttribute("agentId")+"");//代理商id
			r_id =Integer.parseInt(request.getSession().getAttribute("roleId")+"");//角色id
			ep_id =Integer.parseInt(request.getSession().getAttribute("epId")+"");//角色id
			
			// 如果没有就接收到参数默认显示全部类型
			if (request.getParameter("CMD") == null
					|| request.getParameter("CMD").equals("")) {
				cmd = "grp_search";
			} else {
				cmd = request.getParameter("CMD");
			}

			if (cmd.equals("add")) {// 增加群组信息
				actionforward = grp_add(mapping, form, request, response);
			} else if (cmd.equals("xinaxi")) // 查看群组详细信息
			{
				actionforward = grp_xianxi(mapping, form, request, response);
			} else if (cmd.equals("grp_type_list")) { // 根据群组类型查询群组信息
				actionforward = grp_typesearch(mapping, form, request, response);
			} else if (cmd.equals("grp_Status")) { // 根据群组通话状态查询群组信息(1通话中、0未在通话中）
				actionforward = grp_Statussearch(mapping, form, request,
						response);
			} else if (cmd.equals("delete")) { // 删除选中群组信息(逻辑删除flag改为0)
				actionforward = grp_delete(mapping, form, request, response);
			} else if (cmd.equals("grp_idsearch")) { // 群组号模糊查询
				actionforward = grp_idSarch(mapping, form, request, response);
			} else if (cmd.equals("grp_flag")) { // 群组状态查询（0失效、1正常）
				actionforward = grp_flagSarch(mapping, form, request, response);
			} else if (cmd.equals("togrpmdoyijsp")) { // 把要修改的信息传到修改的jsp页面
				actionforward = grp_modytojsp(mapping, form, request, response);
			} else if (cmd.equals("grpmodify")) { // 修改终端用户信息
				actionforward = grp_modify(mapping, form, request, response);
			} else if (cmd.equals("grp_search")) { // 所有条件同时查询			
				actionforward = grp_Sarch(mapping, form, request, response);
			} else if (cmd.equals("grp_addjsp")) {// 转到增加页面
				actionforward = grp_Addjsp(mapping, form, request, response);
			} else if (cmd.equals("basegrp_search")) {// 转到增加页面
				actionforward = baseGrp_Search(mapping, form, request, response);
			} else if (cmd.equals("basegrp_delete")) {// 转到增加页面
				actionforward = baseGrp_delete(mapping, form, request, response);
			} else if (cmd.equals("base_grp_jsp")) {// 转到增加页面
				actionforward = base_Grp_jsp(mapping, form, request, response);
			} else if (cmd.equals("base_grp_add")) {// 转到增加页面
				actionforward = base_Grp_add(mapping, form, request, response);
			} else if (cmd.equals("base_grp_updatejsp")) {// 转到增加页面
				actionforward = base_Grp_modytojsp(mapping, form, request, response);
			} else if (cmd.equals("base_grp_udpate")) {// 转到增加页面
				actionforward = base_Grp_udpate(mapping, form, request, response);
			}
		} else {
			request.setAttribute("message", "Session过期，请重新登录");
			actionforward = mapping.findForward("logging");
		}

		if (actionforward == null) {
			return mapping.findForward("succes");
		}
		
		return actionforward;
	}

	/**
	 * 显示群组详细信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward grp_xianxi(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		// 取得当前记录的grpid
		String grpid = request.getParameter("grpid");
		TbGrpInfo tbgrpin = null;
		tbgrpin = grpmanager.getGrpinfoby_grpid(grpid);
		TbEnterpriseInfo epname = grpmanager.getEpBygrpid(grpid);

		String resultmessage = null;
		if (tbgrpin != null) {
			request.setAttribute("epname", grpmanager.getEpBygrpid(grpid));// 群组所属用户单位名称
			request.setAttribute("tbgrpinfo", tbgrpin);
			return mapping.findForward("xiangxi");
		} else { // 说明没有找到.
			System.out.println("群组明细信息没有找到");
			resultmessage = "该记录不存在!";
			request.setAttribute("message", resultmessage);
			return mapping.findForward("showgrplist");
		}
	}

	/**
	 * 加载群组添加jsp信息
	 * 
	 */
	public ActionForward grp_Addjsp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MessageException {		
		String pagentid = ""; // 一级代理商id
		String childagentid = ""; // 二级代理商id

		if (request.getParameter("pagentid") != null) {
			pagentid = request.getParameter("pagentid");
		} else {
			if(r_id == 2 || r_id ==3 || r_id ==4){
				pagentid = agent_id+"";
			}else{
				pagentid = "-1"; // 代理商是总部
			}
		}

		if (request.getParameter("childagentid") != null) {
			childagentid = request.getParameter("childagentid");
		} else {
			childagentid = "-1"; // 直属企业
		}

		String ep = request.getParameter("ep");
		if (ep == null || ep.equals("null")) {
			if(r_id == 4 || r_id == 3){
				ep = ep_id+"";
			}else{
				ep = "-1";
			}
		}
		// 一级代理商名称
		int apid = Integer.parseInt(request.getSession().getAttribute("aPId")+"");
		if(apid == 0){
			request.setAttribute("Pagentlist", agentmanger.getParentAgent(agent_id,r_id));
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
			request.setAttribute("Cagentlist", agentmanger.getChildAgentByAId(agent_id+""));
		}
//		request.setAttribute("Pagentlist", agentmanger.getParentAgent(agent_id,r_id));
//		// 一级指定二级代理商结果集
//		request.setAttribute("Cagentlist", agentmanger
//				.getChildAgentByParamentid(pagentid));
		// 返回所有企业默认
		List<TbEnterpriseInfo> list = null;
		if (r_id == 3 || r_id == 4){
			list = epmanger.getEpinfo_byeid(pagentid,
					childagentid, ep_id, r_id);
		}else{
			list = epmanger.getEpinfo_byagentid(pagentid,
					childagentid, agent_id, r_id);
		}
		request.setAttribute("epList", list);
//		request.setAttribute("epList", epmanger.getEpinfo_byagentid(pagentid,
//				childagentid, ep_id, r_id));
		if (ep != "-1") {
			request.setAttribute("msList", msmanager.getAllMs_Info(pagentid,
					childagentid, ep));
		}
		request.setAttribute("pagentid", pagentid);
		request.setAttribute("childagentid", childagentid);
		request.setAttribute("epid", ep);
		//request.setAttribute("grpid", FilePathConfig.getGRPId());
		return mapping.findForward("toaddjsp");

	}

	/**
	 * 群组信息添加
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */

	public ActionForward grp_add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		AddgrpForm addgrpform = (AddgrpForm) form;
		
		String grp_name = addgrpform.getGrp_name();
		String ms_id = addgrpform.getMs_id();
		String ep = addgrpform.getEp();
		String C03 = request.getParameter("c03");

		int talksc;
		try {
			talksc = Integer.parseInt(addgrpform.getTalkinglast().trim());
		} catch (Exception e) {
			talksc = 3;
		}

		String pagentid = ""; // 一级代理商id
		String childagentid = ""; // 二级代理商id
		String grp_pid="-1";   //上级群组，-1代表一级群组
		if (request.getParameter("pagentid") != null) {
			pagentid = request.getParameter("pagentid");
		} else {
			pagentid = "-1"; // 代理商是总部
		}
		
		String grp_id = GrpIdRule.grpIdRule(pagentid, ep);//自动生成21位群组Id
		
		if (request.getParameter("childagentid") != null) {
			childagentid = request.getParameter("childagentid");
		} else {
			childagentid = "-1"; // 直属企业
		}
		
		if (request.getParameter("grp_pid") != null) {
			grp_pid = request.getParameter("grp_pid");
		} 
		int grp_type = Integer.parseInt(addgrpform.getGrp_type());
		// 群组的状态flag 0 失效 1 正常 默认1
		int flag = 1;
		int grp_lf = 1;
		String resultmessage = null;
		String grpcreate = grpmanager.createGrp(grp_id, grp_name, ms_id,
				grp_type, flag, talksc, ep, grp_lf,grp_pid,C03);
		//记录操作日志
		TbUserLog userLog=new TbUserLog();
		userLog.setUserId(Integer.parseInt(request.getSession().getAttribute("uId").toString()));
		userLog.setlDate(new Date());
		userLog.setlAddress(HRAddress.getIpAddr(request));
		userLog.setlType(4);
		userLog.setlContent("添加群组{"+grp_id+"}");
		logManager.save(userLog);
		if (grpcreate.equals("succeed")) {
			resultmessage = "添加成功!";
		} else if (grpcreate.equals("exist")) {
			resultmessage = "该群组号码已经存在!添加失败!";
		} else {
			resultmessage = "添加失败!";
		}
		request.setAttribute("message", resultmessage);
		// request.setAttribute("pagentid", pagentid);
		// request.setAttribute("childagentid", childagentid);
		// request.setAttribute("ep", ep);
		return mapping.findForward("showgrplist");

	}

	/**
	 * 修改群组信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws MessageException
	 */
	public ActionForward grp_modify(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MessageException {
		String ep_id = request.getParameter("ep");
		String grp_id = request.getParameter("grp_id");
		String grp_name = request.getParameter("grp_name");
		int grp_type = Integer.parseInt(request.getParameter("grp_type"));
		int grp_flag = Integer.parseInt(request.getParameter("grp_flag"));
		int grp_lf = 1;//Integer.parseInt(request.getParameter("grp_lf"));
		String C03 = request.getParameter("c03");
		int grp_talksc;
		try {
			grp_talksc = Integer
					.parseInt(request.getParameter("talksc").trim());
		} catch (Exception e) {
			grp_talksc = 3;
		}
		String modifyok = grpmanager.modifyGrp(grp_id, ep_id, grp_name,
				grp_type, grp_flag, grp_talksc, grp_lf,C03);
		String resultmessage = null;
		//记录操作日志
		TbUserLog userLog=new TbUserLog();
		userLog.setUserId(Integer.parseInt(request.getSession().getAttribute("uId").toString()));
		userLog.setlDate(new Date());
		userLog.setlAddress(HRAddress.getIpAddr(request));
		userLog.setlType(5);
		userLog.setlContent("修改群组{"+grp_id+"}");
		logManager.save(userLog);
		if (modifyok != null) {
			resultmessage = "修改成功!";
			request.setAttribute("message", resultmessage);
		} else {
			resultmessage = "修改失败!";
			request.setAttribute("message", resultmessage);
		}
		String type=request.getParameter("type");
		if(type!=null&&!type.equals("null")){
			return mapping.findForward("kfshowgrplist");
		}else{
			return mapping.findForward("showgrplist");
		}
	}

	/**
	 * 把修改用户信息传jsp页面
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws MessageException
	 */
	public ActionForward grp_modytojsp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MessageException {
		// 取得grpid
		String grpid = request.getParameter("grpid");
		TbGrpInfo grpinfo = null;
		TbEnterpriseInfo epinfo = null;
		String type=request.getParameter("type");
		if(type!=null){
	    	request.setAttribute("type",type );
	    }
		grpinfo = grpmanager.getGrpinfoby_grpid(grpid);
		if (grpinfo != null) {
			epinfo = epmanger.getEpinfo_byepid(grpinfo.getEp_Id().toString());
		}

		String resultmessage = null;
		
		if (grpinfo != null) {
			request.setAttribute("tbgrpinfo", grpinfo);
			if (epinfo != null) {
				int agenttype = agentmanger.getAgenttype(String.valueOf(epinfo.getAgent_Id()));
				
				
				
				
				if (agenttype != 0) { // 二级代理商下的企业
					// 一级代理商名称
					request.setAttribute("Pagentlist", agentmanger.getParentAgent(agenttype,r_id));
					request.setAttribute("Cagentlist", agentmanger.getChildAgentByAId(epinfo.getAgent_Id()+""));
					request.setAttribute("pagentid", agenttype);
					request.setAttribute("childagentid", epinfo.getAgent_Id());
					request.setAttribute("ep", epinfo.getEp_Id());
					request.setAttribute("epList", epmanger
							.getEpinfo_byeid(String.valueOf(agenttype),
									String.valueOf(epinfo.getAgent_Id()), ep_id, r_id));
				} else { // 一级代理商直属企业
					// 一级指定二级代理商结果集
					// 一级代理商名称
					request.setAttribute("Pagentlist", agentmanger.getParentAgent(agent_id,r_id));
					if(r_id ==3 || r_id ==4){
						request.setAttribute("Cagentlist",null);
					}else{
						request.setAttribute("Cagentlist", agentmanger
								.getChildAgentByParamentid(String.valueOf(epinfo
										.getAgent_Id())));
					}
					request.setAttribute("pagentid", epinfo.getAgent_Id());
					request.setAttribute("childagentid", "-1");
					request.setAttribute("ep", epinfo.getEp_Id());
					request.setAttribute("epList", epmanger
							.getEpinfo_byeid(String.valueOf(epinfo
									.getAgent_Id()), String.valueOf(-1), ep_id, r_id));
				}

			}
			return mapping.findForward("grpmdify");
		} else { // 说明没有找到.

			resultmessage = "要修改的记录不存在！";
			request.setAttribute("message", resultmessage);
			return mapping.findForward("showgrplist");
		}

	}

	public ActionForward grp_flagSarch(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MessageException {

		String cmd = request.getParameter("CMD");
		int type = 0; // 2全部类型 1正常 0失效

		// 如果ms_type取值为空或为null,刚显示全部类型的信息
		if (request.getParameter("type") == null
				|| request.getParameter("type").equals("")) {
			type = 2;
		} else {
			type = Integer.parseInt(request.getParameter("type"));
		}

		if (type == 2) { // 全部类型
			// 获取总条数
			int grpCount = 0;
			try {
				grpCount = grpmanager.getAllGrp_flagCount();
			} catch (MessageException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			// 获取每页的条数
			int pageSize = 12;
			// 获取总页数
			int pageCount = grpmanager.getPageCount(grpCount, pageSize);
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
			request.setAttribute("grpCount", grpCount);
			// 返回每页面显示条数
			request.setAttribute("pageSize", pageSize);
			// 返回当前页
			request.setAttribute("currentPage", pageNo);

			// 返回的总页数
			request.setAttribute("pageCount", pageCount);

			// 设置返回的命令字
			request.setAttribute("CMD", cmd);
			// 设置返回用户类型
			request.setAttribute("type", type);

			// 设设置返回用户查询的select中option值
			request.setAttribute("grpflag", type);

			request.setAttribute("grpList", grpmanager.getAllGrp_flagByPage(
					pageNo, pageSize));
			return mapping.findForward("succes");

		} else {
			// 获取总条数
			int grpCount = 0;
			grpCount = grpmanager.getGrp_flagCount(type);
			// 获取每页的条数
			int pageSize = 12;
			// 获取总页数
			int pageCount = grpmanager.getPageCount(grpCount, pageSize);
			// 从页面取得当前页
			int pageNo;
			String pageNoStr = request.getParameter("pageNo");
			if (pageNoStr == null || pageNoStr.trim().equals("")) {
				pageNo = 1;
			} else {
				pageNo = Integer.parseInt(pageNoStr.trim());
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
			request.setAttribute("grpCount", grpCount);
			// 返回每页面显示条数
			request.setAttribute("pageSize", pageSize);
			// 返回当前页
			request.setAttribute("currentPage", pageNo);
			// 设置返回的命令字
			request.setAttribute("CMD", cmd);
			// 设置返回用户类型
			request.setAttribute("type", type);
			// 设设置返回用户查询的select中option值
			request.setAttribute("grpflag", type);
			// 返回的总页数
			request.setAttribute("pageCount", pageCount);
			request.setAttribute("grpList", grpmanager.getGrp_flagByPage(
					pageNo, pageSize, type));
			return mapping.findForward("succes");
		}

	}

	public ActionForward grp_typesearch(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MessageException {
		String cmd = request.getParameter("CMD");
		int type = 00; // 2全部类型 00普通群组 01电召群组 10新电召群组 11未定义

		// 如果ms_type取值为空或为null,刚显示全部类型的信息
		if (request.getParameter("type") == null
				|| request.getParameter("type").equals("")) {
			type = 2;
		} else {
			type = Integer.parseInt(request.getParameter("type"));
		}

		if (type == 2) { // 全部类型
			// 获取总条数
			int grpCount = 0;
			grpCount = grpmanager.getAllgrp_typeCount();
			// 获取每页的条数
			int pageSize = 12;
			// 获取总页数
			int pageCount = grpmanager.getPageCount(grpCount, pageSize);
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
			request.setAttribute("grpCount", grpCount);
			// 返回每页面显示条数
			request.setAttribute("pageSize", pageSize);
			// 返回当前页
			request.setAttribute("currentPage", pageNo);

			// 返回的总页数
			request.setAttribute("pageCount", pageCount);

			// 设置返回的命令字
			request.setAttribute("CMD", cmd);
			// 设置返回用户类型
			request.setAttribute("type", type);

			// 设设置返回用户查询的select中option值
			request.setAttribute("grptype", type);
			request.setAttribute("grpList", grpmanager.getAllGrp_typeByPage(
					pageNo, pageSize));
			return mapping.findForward("succes");

		} else {
			// 获取总条数
			int grpCount = 0;
			grpCount = grpmanager.getGrp_typeCount(type);
			// 获取每页的条数
			int pageSize = 12;
			// 获取总页数
			int pageCount = grpmanager.getPageCount(grpCount, pageSize);
			// 从页面取得当前页
			int pageNo;
			String pageNoStr = request.getParameter("pageNo");
			if (pageNoStr == null || pageNoStr.trim().equals("")) {
				pageNo = 1;
			} else {
				pageNo = Integer.parseInt(pageNoStr.trim());
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
			request.setAttribute("grpCount", grpCount);
			// 返回每页面显示条数
			request.setAttribute("pageSize", pageSize);
			// 返回当前页
			request.setAttribute("currentPage", pageNo);
			// 设置返回的命令字
			request.setAttribute("CMD", cmd);
			// 设置返回用户类型
			request.setAttribute("type", type);

			// 设设置返回用户查询的select中option值
			request.setAttribute("grptype", type);

			// 返回的总页数
			request.setAttribute("pageCount", pageCount);
			request.setAttribute("grpList", grpmanager.getGrp_typeByPage(
					pageNo, pageSize, type));
			return mapping.findForward("succes");
		}

	}

	/**
	 * 根据ms_id做模糊查询
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward grp_idSarch(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String cmd = request.getParameter("CMD");
		String type = null; // 2全部状态 0离线 1在线

		// 如果ms_type取值为空或为null,刚显示全部类型的信息
		if (request.getParameter("type") == null
				|| request.getParameter("type").equals("")) {
			return mapping.findForward("succes");
		} else {
			type = (String) request.getParameter("type");
			// 获取总条数
			int grpCount = 0;
			grpCount = grpmanager.getGrp_idCount(type);
			// 获取每页的条数
			int pageSize = 12;
			// 获取总页数
			int pageCount = grpmanager.getPageCount(grpCount, pageSize);
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
			request.setAttribute("grpCount", grpCount);
			// 返回每页面显示条数
			request.setAttribute("pageSize", pageSize);
			// 返回当前页
			request.setAttribute("currentPage", pageNo);

			// 返回的总页数
			request.setAttribute("pageCount", pageCount);

			// 返回页面查询关键字
			request.setAttribute("grpidSear", type);

			// 设置返回的命令字
			request.setAttribute("CMD", cmd);
			// 设置返回用户类型
			request.setAttribute("type", type);

			request.setAttribute("grpList", grpmanager.getTbGrpinfoby_grpid(
					pageNo, pageSize, type));
			return mapping.findForward("succes");
		}

	}

	// 根据
	public ActionForward grp_Statussearch(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws MessageException {
		String cmd = request.getParameter("CMD");
		int type = 0; // 2全部状态 0未在通话中 1通话中
		if (request.getParameter("type") == null
				|| request.getParameter("type").equals("")) {
			type = 2;
		} else {
			type = Integer.parseInt(request.getParameter("type"));
		}

		if (type == 2) { // 全部状态
			// 获取总条数
			int grpCount = 0;
			grpCount = grpmanager.getAllGrpStatus_allCount();
			// 获取每页的条数
			int pageSize = 12;
			// 获取总页数
			int pageCount = grpmanager.getPageCount(grpCount, pageSize);
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
			request.setAttribute("grpCount", grpCount);
			// 返回每页面显示条数
			request.setAttribute("pageSize", pageSize);
			// 返回当前页
			request.setAttribute("currentPage", pageNo);

			// 返回的总页数
			request.setAttribute("pageCount", pageCount);

			// 设置返回的命令字
			request.setAttribute("CMD", cmd);
			// 设置返回用户类型
			request.setAttribute("type", type);

			// 设设置返回用户查询的select中option值
			request.setAttribute("grpstatue", type);

			request.setAttribute("grpList", grpmanager
					.getAllGrpStatus_allByPage(pageNo, pageSize));
			return mapping.findForward("succes");

		} else {
			// 获取总条数
			int grpCount = 0;
			grpCount = grpmanager.getGrp_StauteCount(type);
			// 获取每页的条数
			int pageSize = 12;
			// 获取总页数
			int pageCount = grpmanager.getPageCount(grpCount, pageSize);
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
			request.setAttribute("grpCount", grpCount);
			// 返回每页面显示条数
			request.setAttribute("pageSize", pageSize);
			// 返回当前页
			request.setAttribute("currentPage", pageNo);
			// 设置返回的命令字
			request.setAttribute("CMD", cmd);
			// 设置返回用户类型
			request.setAttribute("type", type);
			// 设设置返回用户查询的select中option值
			request.setAttribute("grpstatue", type);
			// 返回的总页数
			request.setAttribute("pageCount", pageCount);
			request.setAttribute("grpList", grpmanager.getGrp_StatusByPage(
					pageNo, pageSize, type));
			return mapping.findForward("succes");
		}

	}

	/**
	 * 不作物理删除,中把falg字段从1变成0
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward grp_delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String grpid = request.getParameter("list");
		Boolean delgrp =grpmanager.deleteGrp(grpid);
		//记录操作日志
		TbUserLog userLog=new TbUserLog();
		userLog.setUserId(Integer.parseInt(request.getSession().getAttribute("uId").toString()));
		userLog.setlDate(new Date());
		userLog.setlAddress(HRAddress.getIpAddr(request));
		userLog.setlType(6);
		userLog.setlContent("删除群组{"+grpid+"}");
		logManager.save(userLog);
		String resultmessage = null;
		if (delgrp) {
			resultmessage = "删除成功";
			request.setAttribute("message", resultmessage);
			return mapping.findForward("showgrplist");
		} else {
			resultmessage = "删除失败";
			request.setAttribute("message", resultmessage);
			return mapping.findForward("showgrplist");
		}
	}

	/**
	 * 根据grp列表信息的查询条件查询
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward grp_Sarch(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String pagentid = ""; // 一级代理商id
		String childagentid = ""; // 二级代理商id
		String ep = ""; // 企业id
		String cmd = request.getParameter("CMD");
//		HttpSession session = request.getSession(true);
		String type=request.getParameter("type");
		
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

		if (request.getParameter("ep") != null) {
			ep = request.getParameter("ep");
		} else {
			if(r_id == 4 || r_id == 3){
				ep =ep_id+"";
			}else{
				ep = "-1"; // 全部企业
			}
			
		}

		String grp_type = request.getParameter("grptype");// 群组类型全部为2
		if (grp_type == null) {
			grp_type = "99";
		}
		String statue = request.getParameter("statue");
		if (statue == null) { // 群组通话状态全部为2
			statue = "2";
		}
		String flag = request.getParameter("flag");
		if (flag == null) { // 群组有效状态全部为2
			flag = "1";
		}
		String grp_id = request.getParameter("grpid"); // 群组id
		if (grp_id == null) {
			grp_id = "";
		}
		String grp_name = request.getParameter("grpname"); // 群组名
		if (grp_name == null) {
			grp_name = "";
		}

		int grpCount = 0;
		grpCount = grpmanager.getGrp_sertch(grp_type, statue, flag, grp_id
				.trim(), grp_name.trim(), pagentid, childagentid, ep);
		// 获取每页的条数
		String ps =request.getParameter("pageSize");
		int pageSize = 10;
		if(ps!= null){
			pageSize =Integer.parseInt(ps);
		}
		
		// 获取总页数
		int pageCount = grpmanager.getPageCount(grpCount, pageSize);
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
		request.setAttribute("grpCount", grpCount);
		// 返回每页面显示条数
		request.setAttribute("pageSize", pageSize);
		// 返回当前页
		request.setAttribute("currentPage", pageNo);
		// 设置返回的命令字
		request.setAttribute("CMD", cmd);
		// 设置返回用户类型

		// 设设置返回用户查询的select中option值
		request.setAttribute("grp_type", grp_type);
		request.setAttribute("grpstatue", statue);
		request.setAttribute("grpflag", flag);
		request.setAttribute("grp_id", grp_id);
		request.setAttribute("grp_name", grp_name);
		request.setAttribute("pagentid", pagentid);
		request.setAttribute("childagentid", childagentid);
		request.setAttribute("ep", ep);
//		if(!pagentid.equals("-1")){
//			// 一级代理商名称
//			request.setAttribute("Pagentlist", agentmanger.getParentAgentNotIncludeZB());
//		}else{
			// 一级代理商名称
		int apid = Integer.parseInt(request.getSession().getAttribute("aPId")+"");
		if(apid == 0){
			request.setAttribute("Pagentlist", agentmanger.getParentAgent(agent_id,r_id));
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
//			request.setAttribute("Pagentlist", agentmanger.getParentAgent(agent_id,r_id));
////		}
//		// 一级指定二级代理商结果集
//		request.setAttribute("Cagentlist", agentmanger
//				.getChildAgentByParamentid(pagentid));
		// 返回所有企业默认
		request.setAttribute("epList", epmanger.getEpinfo_byeid(pagentid,
				childagentid, ep_id, r_id));
		// 返回的总页数
		
		List<TbGrpInfo> grpList = grpmanager.getTBGrpinfoby_grppage(
				pageNo, pageSize, grp_type, statue, flag, grp_id.trim(),
				grp_name.trim(), pagentid, childagentid, ep);

		List<Map> lm = null;
		if(grpList != null){
			lm = new ArrayList<Map>();
			for (int i = 0; i < grpList.size(); i++) {
				int lineCount = 0;
				int grpMsCount = grpmsmanager.grpMsCount(grpList.get(i).getGrpid());
				if(grpMsCount !=0 ){
					lineCount = grpmsmanager.grpOnLineMsCount(grpList.get(i).getGrpid());
				}
				String pid = grpList.get(i).getGrppid();
				String pname = "";
				if(pid.equals("-1") || pid.equals("") || pid.equals(null)){
					pname = "";
				}else{
					pname = grpmanager.getGrpinfoby_grpid(pid).getGrpname();
				}
				Map m = new HashMap();
				m.put("grp", grpList.get(i));
				m.put("lineCount", lineCount);
				m.put("grpMsCount", grpMsCount);
				m.put("grppname", pname);//父级群名
				lm.add(m);
			}
		}
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("grpList", lm);
		if(type!=null&&!type.equals("null")){
			return mapping.findForward("kfsucces");
		}else{
			return mapping.findForward("succes");
		}
	}
	
	/**
	 * 删除
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward baseGrp_delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String [] grpid = request.getParameterValues("list");
		try{
			if(grpid!=null&&grpid.length>0){
				for (int i = 0; i < grpid.length; i++) {
					grpmanager.delete(String.valueOf(grpid[i]).trim());
				}
			}
			return mapping.findForward("basesucces");
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 根据grp列表信息的查询条件查询
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward baseGrp_Search(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String cmd = request.getParameter("CMD");
		String type=request.getParameter("type");
		String grpname=request.getParameter("grpname");
		String grpId=request.getParameter("grpid");
		TabBaseGrpextinfo baseGrp=new TabBaseGrpextinfo();
		if(type!=null&&!"".equals(type)){
			baseGrp.setType(Integer.valueOf(type.trim()));
		}
		if(grpname!=null&&!"".equals(grpname)){
			baseGrp.setCompanyName(grpname.trim());
		}
		if(grpId!=null&&!"".equals(grpId)){
			baseGrp.setGrpId(grpId.trim());
		}
		int grpCount = 0;
		grpCount = grpmanager.totalCount(baseGrp);
		// 获取每页的条数
		int pageSize = 12;
		// 获取总页数
		int pageCount = (grpCount + pageSize - 1) / pageSize;
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
		List<TabBaseGrpextinfo> baseGrpList=grpmanager.listAll(pageNo, pageSize, baseGrp);
		// 获取总条数
		request.setAttribute("grpCount", grpCount);
		// 返回每页面显示条数
		request.setAttribute("pageSize", pageSize);
		// 返回当前页
		request.setAttribute("currentPage", pageNo);
		// 设置返回的命令字
		request.setAttribute("CMD", cmd);
		// 设置返回用户类型

		// 一级代理商名称
		request.setAttribute("type",type);
		request.setAttribute("grpname",grpname);
		request.setAttribute("grpid",grpId);

		// 返回的总页数
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("baseGrp", baseGrpList);
		
			return mapping.findForward("basesearche");
	}
	
	/**
	 * 加载群组添加jsp信息
	 * 
	 */
	public ActionForward base_Grp_jsp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MessageException {
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

		String ep = request.getParameter("ep");
		if (ep == null || ep.equals("null")) {
			ep = "-1";
		}
		// 一级代理商名称
		request.setAttribute("Pagentlist", agentmanger.getParentAgent(agent_id,r_id));
		// 一级指定二级代理商结果集
		request.setAttribute("Cagentlist", agentmanger.getChildAgentByParamentid(pagentid));
		// 返回所有企业默认
		request.setAttribute("epList", epmanger.getEpinfo_byeid(pagentid,childagentid, ep_id, r_id));
		request.setAttribute("pagentid", pagentid);
		request.setAttribute("childagentid", childagentid);
		request.setAttribute("epid", ep);
		return mapping.findForward("baseaddjsp");

	}
	
	/**
	 * 群组信息添加
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */

	public ActionForward base_Grp_add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		TabBaseGrpextinfo baseGrp=new TabBaseGrpextinfo();
		String grp_id = request.getParameter("grp_id");
		String ep = request.getParameter("ep");
		String lg=request.getParameter("lg");
		String lt=request.getParameter("lt");
		String grptype=request.getParameter("grptype");
		String radius=request.getParameter("radius");
		String overSpdSmsTip=request.getParameter("overSpdSmsTip");
		if(grp_id!=null&&!"".equals(grp_id)){
			String [] str=grp_id.split("-");
			baseGrp.setGrpId(str[0]);
			baseGrp.setCompanyName(str[1]);
		}
		if(ep!=null&&!"".equals(ep))
			baseGrp.setEnterpriseId(new Integer(ep));
		if(lg!=null&&!"".equals(lg))
			baseGrp.setLg(Double.valueOf(lg));
		if(lt!=null&&!"".equals(lt))
			baseGrp.setLt(Double.valueOf(lt));
		if(grptype!=null&&!"".equals(grptype))
			baseGrp.setType(Integer.valueOf(grptype));
		if(radius!=null&&!"".equals(radius))
			baseGrp.setRadius(Integer.valueOf(radius));
		if(overSpdSmsTip!=null&&!"".equals(overSpdSmsTip))
			baseGrp.setOverSpdSmsTip(Integer.valueOf(overSpdSmsTip));

		String resultmessage = null;
		 grpmanager.create(baseGrp);
		 resultmessage = "添加失败!";
		
		request.setAttribute("message", resultmessage);
		return mapping.findForward("basesucces");

	}
	/**
	 * 把修改用户信息传jsp页面
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws MessageException
	 */
	public ActionForward base_Grp_modytojsp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MessageException {
		// 取得grpid
		String grpid = request.getParameter("grpid");
		TbGrpInfo grpinfo = null;
		TbEnterpriseInfo epinfo = null;
		String type=request.getParameter("type");
		if(type!=null){
	    	request.setAttribute("type",type );
	    }
		grpinfo = grpmanager.getGrpinfoby_grpid(grpid);
		if (grpinfo != null) {
			epinfo = epmanger.getEpinfo_byepid(grpinfo.getEp_Id().toString());
		}

		String resultmessage = null;
		if (grpinfo != null) {
			request.setAttribute("tbgrpinfo", grpinfo);
			if (epinfo != null) {
				int agenttype = agentmanger.getAgenttype(String.valueOf(epinfo
						.getAgent_Id()));
				if (agenttype != 0) { // 二级代理商下的企业
					// 一级代理商名称
					request.setAttribute("Pagentlist", agentmanger
							.getParentAgent(agent_id,r_id));
					request.setAttribute("Cagentlist", agentmanger
							.getChildAgentByParamentid(String
									.valueOf(agenttype)));
					request.setAttribute("pagentid", agenttype);
					request.setAttribute("basegrp", grpmanager.getBean(grpid));
					request.setAttribute("childagentid", epinfo.getAgent_Id());
					request.setAttribute("ep", epinfo.getEp_Id());
					request.setAttribute("epList", epmanger
							.getEpinfo_byeid(String.valueOf(agenttype),
									String.valueOf(epinfo.getAgent_Id()), ep_id, r_id));
				} else { // 一级代理商直属企业
					// 一级代理商名称
					request.setAttribute("Pagentlist", agentmanger
							.getParentAgent(agent_id,r_id));
					// 一级指定二级代理商结果集
					request.setAttribute("Cagentlist", agentmanger
							.getChildAgentByParamentid(String.valueOf(epinfo
									.getAgent_Id())));
					request.setAttribute("basegrp", grpmanager.getBean(grpid));
					request.setAttribute("pagentid", epinfo.getAgent_Id());
					request.setAttribute("childagentid", "-1");
					request.setAttribute("ep", epinfo.getEp_Id());
					request.setAttribute("epList", epmanger
							.getEpinfo_byeid(String.valueOf(epinfo
									.getAgent_Id()), String.valueOf(-1), ep_id, r_id));
				}

			}
			return mapping.findForward("baseupdatejsp");
		} else { // 说明没有找到.

			resultmessage = "要修改的记录不存在！";
			request.setAttribute("message", resultmessage);
			return mapping.findForward("basesucces");
		}
	}
	/**
	 * 群组信息修改
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */

	public ActionForward base_Grp_udpate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		TabBaseGrpextinfo baseGrp=new TabBaseGrpextinfo();
		String grp_id = request.getParameter("grp_id");
		String ep = request.getParameter("ep");
		String lg=request.getParameter("lg");
		String lt=request.getParameter("lt");
		String grptype=request.getParameter("grptype");
		String radius=request.getParameter("radius");
		String overSpdSmsTip=request.getParameter("overSpdSmsTip");
		if(grp_id!=null&&!"".equals(grp_id)){
			String [] str=grp_id.split("-");
			baseGrp.setGrpId(str[0].trim());
			baseGrp.setCompanyName(str[1].trim());
		}
		if(ep!=null&&!"".equals(ep))
			baseGrp.setEnterpriseId(new Integer(ep));
		if(lg!=null&&!"".equals(lg))
			baseGrp.setLg(Double.valueOf(lg));
		if(lt!=null&&!"".equals(lt))
			baseGrp.setLt(Double.valueOf(lt));
		if(grptype!=null&&!"".equals(grptype))
			baseGrp.setType(Integer.valueOf(grptype));
		if(radius!=null&&!"".equals(radius))
			baseGrp.setRadius(Integer.valueOf(radius));
		if(overSpdSmsTip!=null&&!"".equals(overSpdSmsTip))
			baseGrp.setOverSpdSmsTip(Integer.valueOf(overSpdSmsTip));

		String resultmessage=null;
		String hql ="UPDATE TabBaseGrpextinfo SET lg=?,lt=?,type=?,radius=?,overSpdSmsTip=? WHERE  grpId=?";
		 int bool=grpmanager.update(hql,baseGrp.getLg(),baseGrp.getLt(),baseGrp.getType(),baseGrp.getRadius(),baseGrp.getOverSpdSmsTip(), baseGrp.getGrpId());
		 if(bool>0){
			 resultmessage = "修改成功!";
		 }else{
			 resultmessage = "修改失败!";
		 }
		request.setAttribute("message", resultmessage);
		return mapping.findForward("basesucces");

	}

}