package com.elegps.tscweb.action.grpms;

import com.elegps.tscweb.action.base.BaseAction;
import com.elegps.tscweb.comm.HRAddress;
import com.elegps.tscweb.comm.UserInfo;
import com.elegps.tscweb.exception.MessageException;
import com.elegps.tscweb.model.TbEnterpriseInfo;
import com.elegps.tscweb.model.TbGrpMsListInfo;
import com.elegps.tscweb.model.TbUserInfo;
import com.elegps.tscweb.model.TbUserLog;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * MyEclipse Struts Creation date: 10-20-2008
 * 
 * XDoclet definition:
 * 
 * @struts.action validate="true"
 */
public class GrpMsListAction extends BaseAction {
	/*
	 * Generated Methods
	 */
	
	//wanglei 企业用户执行
	public static int a_id = 0;//代理商id
	public static int r_id = 0;//角色id
	public static int ep_id = 0;//角色id
	
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
			ep_id = Integer.parseInt(request.getSession().getAttribute("epId")+"");//企业id
            // 如果没有就接收到参数默认显示全部群组信息
            if (request.getParameter("CMD") == null
                    || request.getParameter("CMD").equals("")) {
                cmd = "grpms_search";
            } else {
                cmd = request.getParameter("CMD");
            }

            if (cmd.equals("add")) {// 增加群组终端对应信息
                actionforward = grpms_add(mapping, form, request, response);
            } else if (cmd.equals("grpms_search")) { // 所有条件同时查询
                actionforward = grpms_Sarch(mapping, form, request, response);
            } else if (cmd.equals("grp_id")) { // 根据群组类型查询群组信息
                actionforward = grp_idsearch(mapping, form, request, response);
            } else if (cmd.equals("ms_id")) { // 根据群组通话状态查询群组信息(1通话中、0未在通话中）
                actionforward = ms_idsearch(mapping, form, request, response);
            } else if (cmd.equals("delete")) { // 删除选中群组信息(逻辑删除flag改为0)
                actionforward = grpms_delete(mapping, form, request, response);
            } else if (cmd.equals("addtojsp")) {// 把信息加载到grpms对应关系的增加页面
                actionforward = grpms_addtojsp(mapping, form, request, response);
            } else if (cmd.equals("addtoplms_bygrpjsp")) { // 把信息加载到grpms对应关系的批量增加页面(根据群组号批量添加终端)
                actionforward = grpms_addtoplms_bygrpjsp(mapping, form, request, response);
            } else if (cmd.equals("plgrpby_msidadd")) { // 根据群组号批量增添加终端用户信息(根据群组号批量添加终端)
                actionforward = grpms_add_bygrpid(mapping, form, request, response);
            } else if (cmd.equals("addtoplgrp_bymsjsp")) { // 把信息加载到grpms对应关系的批量增加页面(根据终端号批量添加群组)
                actionforward = grpms_addtoplgrp_bymsjsp(mapping, form, request, response);
            } else if (cmd.equals("plgrpby_grpidadd")) { //根据终端号批量增添加群组信息(根据终端号批量添加群组)
                actionforward = grpms_add_bymsid(mapping, form, request, response);
            } else if (cmd.equals("kyaddtoplgrp_bymsjsp")) { // 把信息加载到grpms对应关系的跨企业批量增加页面(根据终端号批量添加群组)
                actionforward = kygrpms_addtoplgrp_bymsjsp(mapping, form, request, response);
            } else if (cmd.equals("kyplgrpby_grpidadd")) { //根据终端号跨企业批量增添加群组信息(根据终端号批量添加群组)
                actionforward = kygrpms_add_bymsid(mapping, form, request, response);
            } else if (cmd.equals("togrpmsmodijsp")) {  //修改群组置
                actionforward = grpms_modytojsp(mapping, form, request, response);
            } else if (cmd.equals("grpmsmodify")) {
                actionforward = grpmsmodify(mapping, form, request, response);
            }
        } else {
            request.setAttribute("message", "Session过期，请重新登录");
            actionforward = mapping.findForward("logging");
        }

        if (actionforward == null) {
            return mapping.findForward("success");
        }

        return actionforward;


    }

    /**
	 * 修改群组置
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws MessageException
	 */
	public ActionForward grpmsmodify(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MessageException {
		String grpid=request.getParameter("grp_id");
		String rgrpid=request.getParameter("rgrp_id");
		String msid=request.getParameter("ms_id");
		String config=request.getParameter("config");
		String modifyok=grpmsmanager.modifyGrpMs(grpid,rgrpid,msid,config);
		String resultmessage=null;
			if(modifyok!=null){
				System.out.println("修改成功");
				resultmessage="修改成功!";
				request.setAttribute("message",resultmessage);
				return mapping.findForward("showgrpmslist");
			}
			else{
				System.out.println("运营商名称已经存在,修改失败");
				resultmessage="修改失败!";
				request.setAttribute("message",resultmessage);
				return mapping.findForward("showgrpmslist");
			}
	}
	
	/**
	 * 到修改群组端对应关系jsp页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws MessageException
	 */
	public ActionForward grpms_modytojsp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MessageException {
		String grpid = request.getParameter("grpid");
		String msid = request.getParameter("msid");
		TbGrpMsListInfo tbgrpms = null;
		tbgrpms = grpmsmanager.getGrpMs_ByGrpMsid(grpid,msid);
		if (tbgrpms != null) {
			request.setAttribute("tbgrpmsinfo",tbgrpms);
			return mapping.findForward("grpmsmdifyjsp");
		} else { // 说明没有找到要修改的记录.
			return null;
		}
	}
	
	/**
	 * 批量增加群组终端对应关系(根据msid批量增加群组)
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward grpms_add_bymsid(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		String ms_id = request.getParameter("ms_id");
		String grp_id[] = request.getParameterValues("selectgrpid");
		String insert = null; 
		insert = grpmsmanager.createGrpMsInfo_ByMsid(ms_id, grp_id);
		if (insert != null) {
			request.setAttribute("message", insert);
			return mapping.findForward("showgrpmslist");
		} else {
			request.setAttribute("message", insert);
			return mapping.findForward("showgrpmslist");
		}

	}
	

	/**
	 * 把信息加载到grpms对应关系的批量增加页面(根据终端号批量添加群组)
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward grpms_addtoplgrp_bymsjsp(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws MessageException {
		
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

		String ep = request.getParameter("ep");
		if (ep == null || ep.equals("null")) {
			if(r_id == 4 || r_id == 3){
				ep = ep_id+"";
			}else{
				ep = "-1";   //全部企业}
			}
		}
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
//			request.setAttribute("Pagentlist", agentmanger.getParentAgent(a_id,r_id));
////		}
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
					childagentid, a_id, r_id);
		}
		request.setAttribute("epList",list );
        if(ep!="-1"){
//        	request.setAttribute("grpList", grpmanager.getAllGrp_Info(pagentid,childagentid,ep));
        }
		request.setAttribute("pagentid", pagentid);
		request.setAttribute("childagentid", childagentid);
//		request.setAttribute("epid", ep);
		
		return mapping.findForward("grpmsaddplgrpjsp");

	}


	/**
	 * 批量增加群组终端对应关系(根据grpid批量增加终端)
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward grpms_add_bygrpid(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
        String grp_id = request.getParameter("grp_id");
        String ms_id[] = request.getParameterValues("selectmsid");
        String changeMs = request.getParameter("changeMs");
        String[] msArray = changeMs.split(",");

        String insert = null;
        insert = grpmsmanager.createGrpMsInfo_ByGrpid(grp_id, ms_id);
        if (insert != null && (changeMs != null && !"".equals(changeMs))) {
            for (int i = 0; i < msArray.length; i++) {
                String msa = msArray[i];
                String[] msb = msa.split("\\$");
                if (changeMs.indexOf(msb[0]) == changeMs.lastIndexOf(msb[0])) {
                    //记录操作日志
                    TbUserLog userLog = new TbUserLog();
                    userLog.setUserId(Integer.parseInt(request.getSession().getAttribute("uId").toString()));
                    userLog.setlDate(new Date());
                    userLog.setlAddress(HRAddress.getIpAddr(request));
                    userLog.setlType((msb[1].equals("1")) ? 7 : 8);
                    userLog.setlContent("群组:{ " + grp_id + " }终端:{ " + msb[0] + " }" + ((msb[1].equals("1")) ? "添加" : "取消") + "对应关系");
                    logManager.save(userLog);
                }
            }
            request.setAttribute("message", insert);
            return mapping.findForward("showgrpmslist");
        } else {
            request.setAttribute("message", insert);
            return mapping.findForward("showgrpmslist");
        }

    }

	/**
	 * 把信息加载到grpms对应关系的批量增加页面(根据群组号批量添加终端)
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward grpms_addtoplms_bygrpjsp(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws MessageException {
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

		String ep = request.getParameter("ep");
		if (ep == null || ep.equals("null")) {
			if(r_id == 4){
				ep = ep_id+"";
			}else{
				ep = "-1";   //全部企业}
			}
		}
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
//		if(!pagentid.equals("-1")){
//			// 一级代理商名称
//			request.setAttribute("Pagentlist", agentmanger.getParentAgentNotIncludeZB());
//		}else{
			// 一级代理商名称
//			request.setAttribute("Pagentlist", agentmanger.getParentAgent(a_id,r_id));
//		}
		
		// 一级指定二级代理商结果集
//		request.setAttribute("Cagentlist", agentmanger
//				.getChildAgentByParamentid(pagentid));
		// 返回所有企业默认
		List<TbEnterpriseInfo> list = null;
		if (r_id == 3 || r_id == 4){
			list = epmanger.getEpinfo_byeid(pagentid,
					childagentid, ep_id, r_id);
		}else{
			list = epmanger.getEpinfo_byagentid(pagentid,
					childagentid, a_id, r_id);
		}
		request.setAttribute("epList", list);
        if(ep!="-1"){
//        	request.setAttribute("grpList", grpmanager.getAllGrp_Info(pagentid,childagentid,ep));
        }
		request.setAttribute("pagentid", pagentid);
		request.setAttribute("childagentid", childagentid);
//		request.setAttribute("epid", ep);

	  return mapping.findForward("grpmsaddplmsjsp");


	}

	/**
	 * 把信息加载到grpms对应关系的增加页面
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward grpms_addtojsp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MessageException {
		String pagentid = ""; // 一级代理商id
		String childagentid = ""; // 二级代理商id

		String agent = request.getSession().getAttribute("agentId")+"";
		int apid = Integer.parseInt(request.getSession().getAttribute("aPId")+"");
		
		if (request.getParameter("pagentid") != null && (agent.equals("-1") || agent.equals("0"))) {
			pagentid = request.getParameter("pagentid");
		} else {
			 //pagentid = "-1"; // 代理商是总部
			if(apid==0){
				pagentid = agent;
			}else{
				pagentid = apid+"";
			}
        	
        	if(pagentid.equals("0"))
        		pagentid = "-1";
		}

		if (request.getParameter("childagentid") != null) {
			childagentid = request.getParameter("childagentid");
		} else {
			childagentid = "-1"; // 直属企业
		}
		if(apid!=0){
			childagentid = agent;
		}

		String ep = request.getParameter("ep");
		if (ep == null || ep.equals("null")) {
			if(r_id ==4){
				ep = ep_id+"";
			}else{
				ep = "-1";
			}
		}
		
		
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
//		if(!pagentid.equals("-1")){
//			// 一级代理商名称
//			request.setAttribute("Pagentlist", agentmanger.getParentAgentNotIncludeZB());
//		}else{
			// 一级代理商名称
//			request.setAttribute("Pagentlist", agentmanger.getParentAgent(a_id,r_id));
//		}
		
		
		// 一级指定二级代理商结果集
//		request.setAttribute("Cagentlist", agentmanger
//				.getChildAgentByParamentid(pagentid));
		// 返回所有企业默认
		List<TbEnterpriseInfo> list = null;
		if (r_id == 3 || r_id == 4 ){
			list = epmanger.getEpinfo_byeid(pagentid,
					childagentid, ep_id, r_id);
		}else{
			list = epmanger.getEpinfo_byagentid(pagentid,
					childagentid, a_id, r_id);
		}
		request.setAttribute("epList", list);
        if(ep!="-1"){
        	request.setAttribute("grpList", grpmanager.getAllGrp_Info(pagentid,childagentid,ep));
        	request.setAttribute("msList", msmanager.getAllMs_Info(pagentid,childagentid,ep));
        }
		request.setAttribute("pagentid", pagentid);
		request.setAttribute("childagentid", childagentid);
		request.setAttribute("epid", ep);
		return mapping.findForward("grpmsaddjsp");
	

	}

	/**
	 * 根据grp_id查询
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward grp_idsearch(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MessageException {
		String cmd = request.getParameter("CMD");
		String type = "0"; // 2全部群组

		// 如果type取值为空或为null,刚显示全部终端信息
		if (request.getParameter("type") == null
				|| request.getParameter("type").equals("")) {
			type = "2";
		} else {
			type = request.getParameter("type");
		}

		if (type.equals("2")) { // 全部类型
			// 获取总条数
			int grpmsCount = 0;
			grpmsCount = grpmsmanager.getAllGrp_idCount();
			// 获取每页的条数
			int pageSize = 12;
			// 获取总页数
			int pageCount = grpmsmanager.getPageCount(grpmsCount, pageSize);
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
			request.setAttribute("grpmsCount", grpmsCount);
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

			// 设置返回群组查询的select中option值
			request.setAttribute("grp_id", type);


			request.setAttribute("grpmsList", grpmsmanager
					.getAllGrpid_InfoByPage(pageNo, pageSize));
			return mapping.findForward("success");

		} else {
			// 获取总条数
			int grpmsCount = 0;
			grpmsCount = grpmsmanager.getGrp_idCount(type);
			// 获取每页的条数
			int pageSize = 12;
			// 获取总页数
			int pageCount = grpmsmanager.getPageCount(grpmsCount, pageSize);
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
			request.setAttribute("grpmsCount", grpmsCount);
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
			// 设置返回群组查询的select中option值
			request.setAttribute("grp_id", type);
			// 设置返回群组查询的select中所有的option值
//			request.setAttribute("grpList", grpmanager.getAllGrp_Info());
			// 设置返回终端查询的select中所有的option值
//			request.setAttribute("msList", msmanager.getAllMs_Info());
			request.setAttribute("grpmsList", grpmsmanager.getGrpid_InfoByPage(
					pageNo, pageSize, type));
			return mapping.findForward("success");
		}

	}

	/**
	 * 根据ms_id查询
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward ms_idsearch(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String cmd = request.getParameter("CMD");
		String type = "0"; // 2全部群组

		// 如果type取值为空或为null,刚显示全部群组信息
		if (request.getParameter("type") == null
				|| request.getParameter("type").equals("")) {
			type = "2";
		} else {
			type = request.getParameter("type");
		}

		if (type.equals("2")) { // 全部类型
			// 获取总条数
			int grpmsCount = 0;
			grpmsCount = grpmsmanager.getAllms_idCount();
			// 获取每页的条数
			int pageSize = 12;
			// 获取总页数
			int pageCount = grpmsmanager.getPageCount(grpmsCount, pageSize);
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
			request.setAttribute("grpmsCount", grpmsCount);
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

			// 设置返回群组查询的select中option值
			request.setAttribute("ms_id", type);

			// 设置返回群组查询的select中所有的option值
//			request.setAttribute("grpList", grpmanager.getAllGrp_Info());
			// 设置返回终端查询的select中所有的option值
//			request.setAttribute("msList", msmanager.getAllMs_Info());

			request.setAttribute("grpmsList", grpmsmanager
					.getAllMsid_InfoByPage(pageNo, pageSize));
			return mapping.findForward("success");

		} else {
			// 获取总条数
			int grpmsCount = 0;
			grpmsCount = grpmsmanager.getMs_idCount(type);
			// 获取每页的条数
			int pageSize = 12;
			// 获取总页数
			int pageCount = grpmsmanager.getPageCount(grpmsCount, pageSize);
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
			request.setAttribute("grpmsCount", grpmsCount);
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
			// 设置返回群组查询的select中option值
			request.setAttribute("ms_id", type);
			// 设置返回群组查询的select中所有的option值
//			request.setAttribute("grpList", grpmanager.getAllGrp_Info());
			// 设置返回终端查询的select中所有的option值
//			request.setAttribute("msList", msmanager.getAllMs_Info());
			request.setAttribute("grpmsList", grpmsmanager.getMsid_InfoByPage(
					pageNo, pageSize, type));
			return mapping.findForward("success");
		}

	}

	/**
	 * 增加群组终端对应关系
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward grpms_add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String grp_id = request.getParameter("grp_id");
		String ms_id = request.getParameter("ms_id");
		String insert = null;
		insert = grpmsmanager.createGrpMsInfo(grp_id, ms_id);
		String resultmessage = null;
		if (insert != null) {
			System.out.println("群组终端对应关系添加成功");
			resultmessage = "群组终端对应关系添加成功!";
			//记录操作日志
			TbUserLog userLog=new TbUserLog();
			userLog.setUserId(Integer.parseInt(request.getSession().getAttribute("uId").toString()));
			userLog.setlDate(new Date());
			userLog.setlAddress(HRAddress.getIpAddr(request));
			userLog.setlType(7);
			userLog.setlContent("群组:{ "+grp_id+" }终端:{ "+ms_id+" }添加对应关系");
			logManager.save(userLog);
			request.setAttribute("message", resultmessage);
			return mapping.findForward("showgrpmslist");
		} else {
			System.out.println("群组终端对应关系添加失败");
			resultmessage = "该群组终端对应关系已经存在,添加失败!";
			request.setAttribute("message", resultmessage);
			return mapping.findForward("showgrpmslist");
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
	public ActionForward grpms_delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String[] list = request.getParameterValues("list");
		Boolean deletesuce;
		deletesuce = grpmsmanager.deleteGrpMs(list);
		String resultmessage = null;
		if (deletesuce) {
			for (int i = 0; i < list.length; i++) {
				String mg=list[i];
				String [] mgs=mg.split(",");
				//记录操作日志
				TbUserLog userLog=new TbUserLog();
				userLog.setUserId(Integer.parseInt(request.getSession().getAttribute("uId").toString()));
				userLog.setlDate(new Date());
				userLog.setlAddress(HRAddress.getIpAddr(request));
				userLog.setlType(8);
				userLog.setlContent("群组:{ "+mgs[0]+" }终端:{ "+mgs[1]+" }取消对应关系");
				logManager.save(userLog);
			}
			System.out.println("删除成功");
			resultmessage = "删除成功!";
			request.setAttribute("message", resultmessage);
			return mapping.findForward("showgrpmslist");
		} else {
			System.out.println("删除失败");
			resultmessage = "删除失败!";
			request.setAttribute("message", resultmessage);
			return mapping.findForward("showgrpmslist");
		}

	}
	
	
	/**
	 * 修改群组终端配置
	 */
	public ActionForward updateGMPZ(ActionMapping mapping,HttpServletRequest request, HttpServletResponse response,
			String grpid,String msid,String config){
		
		String modifyok=grpmsmanager.modifyGrpMs(grpid,grpid,msid,config);
		String resultmessage=null;
			if(modifyok!=null){
				System.out.println("修改成功");
				resultmessage="修改成功!";
				request.setAttribute("message",resultmessage);
				return mapping.findForward("success");
			}
			else{
				System.out.println("运营商名称已经存在,修改失败");
				resultmessage="修改失败!";
				request.setAttribute("message",resultmessage);
				return mapping.findForward("success");
			}
	}

	/**
	 * 根据条件查询
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward grpms_Sarch(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		if(request.getParameter("pzgrpid")!=null && !request.getParameter("pzgrpid").equals("")
				&& request.getParameter("pzmsid")!=null && !request.getParameter("pzmsid").equals("")
				&& request.getParameter("config")!=null && !request.getParameter("config").equals("")){
			String pzgrpid=request.getParameter("pzgrpid");
			String pzmsid=request.getParameter("pzmsid");
			String config=request.getParameter("config");
			
			updateGMPZ(mapping,request,response,pzgrpid,pzmsid,config);
		}
		
		String pagentid = ""; // 一级代理商id
		String childagentid = ""; // 二级代理商id
		String ep="";  //企业id
		String cmd = request.getParameter("CMD");
		String grpid = request.getParameter("grpid");//群组号码
		String msid = request.getParameter("msid");//终端号码
		HttpSession session = request.getSession(true);
		TbUserInfo userInfo = (TbUserInfo) session.getAttribute("user");
		
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
			childagentid="-2";   //所有企业
		}

		if(request.getParameter("ep")!=null){
			ep = request.getParameter("ep");
		}else{
			if(r_id == 4 || r_id ==3){
				ep = ep_id+"";
			}else{
				ep = "-1";   //全部企业}
			}
		}
		if (grpid == null) { // 为全部群组编号
			grpid = "";
		}
		
		if (msid == null) {//全部终端号
			msid = "";
		}
		// 获取总条数
		int grpmsCount = 0;
		grpmsCount = grpmsmanager.getGrpMs_SearchCount(grpid.trim(), msid.trim(),pagentid,childagentid,ep);
		// 获取每页的条数
		String ps =request.getParameter("pageSize");
		int pageSize = 10;
		if(ps!= null){
			pageSize =Integer.parseInt(ps);
		}
		// 获取总页数
		int pageCount = grpmsmanager.getPageCount(grpmsCount, pageSize);
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
		request.setAttribute("grpmsCount", grpmsCount);
		// 返回每页面显示条数
		request.setAttribute("pageSize", pageSize);
		// 返回当前页
		request.setAttribute("currentPage", pageNo);

		// 返回的总页数
		request.setAttribute("pageCount", pageCount);

		// 设置返回的命令字
		request.setAttribute("CMD", cmd);

		// 设置返回群组查询的select中option值
		request.setAttribute("msid", msid);
		request.setAttribute("grpid", grpid);
		request.setAttribute("pagentid", pagentid);
		request.setAttribute("childagentid", childagentid);
		request.setAttribute("ep", ep);
		
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
//		// 一级代理商名称
//		request.setAttribute("Pagentlist", agentmanger.getParentAgent(a_id,r_id));
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
					childagentid, a_id, r_id);
		}
		request.setAttribute("epList", list);
		//设置返回群组信息
		request.setAttribute("grpList", grpmanager.getAllGrp_Info(pagentid,childagentid,ep));
		// 设置返回终端信息
		request.setAttribute("msList", msmanager.getAllMs_Info(pagentid,childagentid,ep));
				
	
	
		request.setAttribute("grpmsList", grpmsmanager.getGrpMs_SearchByPage(
				pageNo, pageSize, grpid, msid,pagentid,childagentid,ep));
		return mapping.findForward("success");

	}
	
	
	
	

	
	
	/**
	 * 把信息加载到grpms对应关系的跨企业批量增加页面(根据终端号批量添加群组)
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward kygrpms_addtoplgrp_bymsjsp(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws MessageException {
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
			if(r_id == 4){
				ep = ep_id+"";
			}else{
				ep = "-1";   //全部企业}
			}
		}
		// 一级代理商名称
		request.setAttribute("Pagentlist", agentmanger.getParentAgent(a_id,r_id));
		// 一级指定二级代理商结果集
		request.setAttribute("Cagentlist", agentmanger
				.getChildAgentByParamentid(pagentid));
		// 返回所有企业默认
		List<TbEnterpriseInfo> list = null;
		if (r_id == 3 || r_id ==4){
			list = epmanger.getEpinfo_byeid(pagentid,
					childagentid, ep_id, r_id);
		}else{
			list = epmanger.getEpinfo_byagentid(pagentid,
					childagentid, a_id, r_id);
		}
		request.setAttribute("epList", list);
        if(ep!="-1"){
//        	request.setAttribute("grpList", grpmanager.getAllGrp_Info(pagentid,childagentid,ep));
        }
		request.setAttribute("pagentid", pagentid);
		request.setAttribute("childagentid", childagentid);
//		request.setAttribute("epid", ep);
		return mapping.findForward("kygrpmsaddplgrpjsp");

	}
	
	

	
	
	
	
	
	
	
	
	
	/**
	 * 批量跨企业增加群组终端对应关系(根据msid批量增加群组)
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward kygrpms_add_bymsid(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		String ms_id = request.getParameter("ms_id");
		String grp_id[] = request.getParameterValues("selectgrpid");
		String insert = null; 
		insert = grpmsmanager.createGrpMsInfo_ByMsid(ms_id, grp_id);
		if (insert != null) {
			request.setAttribute("message", insert);
			return mapping.findForward("showgrpmslist");
		} else {
			request.setAttribute("message", insert);
			return mapping.findForward("showgrpmslist");
		}

	}
	
}