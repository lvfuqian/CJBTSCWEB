package com.elegps.tscweb.action.Agent;

import com.elegps.tscweb.action.base.BaseAction;
import com.elegps.tscweb.comm.HRAddress;
import com.elegps.tscweb.comm.UserInfo;
import com.elegps.tscweb.exception.MessageException;
import com.elegps.tscweb.form.AddAgentForm;
import com.elegps.tscweb.model.TbAgentInfo;
import com.elegps.tscweb.model.TbUserInfo;
import com.elegps.tscweb.model.TbUserLog;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

/**
 * MyEclipse Struts Creation date: 10-20-2008
 * 
 * XDoclet definition:
 * 
 * @struts.action validate="true"
 */
public class AgentListAction extends BaseAction {
	/*
	 * Generated Methods
	 */
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
			
			//
			agent_id =Integer.parseInt(request.getSession().getAttribute("agentId")+"");//代理商id
			r_id =Integer.parseInt(request.getSession().getAttribute("roleId")+"");//角色id

			
			if (request.getParameter("CMD") == null
					|| request.getParameter("CMD").equals("")) {
				cmd = "agent_search";
			} else {
				cmd = request.getParameter("CMD");
			}
			if (cmd.equals("argment_search")) { // 所有条件同时查询
				actionforward = agent_Sarch(mapping, form, request, response);
			}
			if (cmd.equals("agent_addjsp")) {// 到添加页面 zr
				actionforward = toadd(mapping, form, request, response);
			}
			if (cmd.equals("agentadd")) {// 添加代理商 zr
				actionforward = addagent(mapping, form, request, response);
			}
			if (cmd.equals("beforeupdate")) {// 修改前的准备页面 zr
				actionforward = to_updatejsp(mapping, form, request, response);
			}
			if (cmd.equals("update")) {// 修改代理商信息 zr
				actionforward = updateagent(mapping, form, request, response);
			}
			if (cmd.equals("getagentbyagentid")) {// 根据agentid查询代理商信息 zr
				actionforward = getAgentById(mapping, form, request, response);
			}
			if (cmd.equals("delete")) {// 删除代理商
				actionforward = deleteagent(mapping, form, request, response);
			} else if (cmd.equals("xinaxi")) // 查看用户详细信息 zr
			{
				actionforward = agent_xianxi(mapping, form, request, response);
			}
		} else {
			request.setAttribute("message", "Session过期，请重新登录");
			actionforward = mapping.findForward("logging");
		}

		return actionforward;
	}

	/**
	 * 根据代理商条件查询
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward agent_Sarch(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String cmd = request.getParameter("CMD");
		String agent_name = request.getParameter("agent_name");// 代理商名字
		String agent_type = request.getParameter("agent_type");// 代理商类型
		String type=request.getParameter("type");
		if (agent_name == null || (agent_name.trim()).equals("")) {
			agent_name = "";
		}
		if (agent_type == null || (agent_type.trim().equals(""))) {
			agent_type = "-1";
		}
		int agentCount = 0;
		if (r_id == 2){
			agentCount = agentmanger.getAgent_SearchCount(agent_name, agent_type,agent_id);
		}else{
			agentCount = agentmanger.getAgent_SearchCount(agent_name, agent_type);
		}
		// 获取每页的条数
		int pageSize = 12;
		// 获取总页数
		int pageCount = agentmanger.getPageCount(agentCount, pageSize);
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
		request.setAttribute("agentCount", agentCount);
		// 返回每页面显示条数
		request.setAttribute("pageSize", pageSize);
		// 返回当前页
		request.setAttribute("currentPage", pageNo);
		// 设置返回的命令字
		request.setAttribute("CMD", cmd);
		// 设置返回用户类型
		request.setAttribute("agent_type", agent_type);
		// 设置返回用户查询的intput中值
		request.setAttribute("agent_name", agent_name);

		// 返回的总页数
		request.setAttribute("pageCount", pageCount);
		// 返回所有的一级代理商
		List agentlist = null;
		if (r_id == 2){
			agentlist = agentmanger.getTbAgentInfoby_name(pageNo, pageSize, agent_name, agent_type,agent_id);
		}else{
			agentlist = agentmanger.getTbAgentInfoby_name(pageNo, pageSize, agent_name, agent_type);
		}
		
		request.setAttribute("pagentlist", agentmanger.getParentAgent(agent_id,r_id));
		request.setAttribute("agentList", agentlist);
		if(type!=null&&!type.equals("null")){
			return mapping.findForward("kfsucces");
		}else{
			return mapping.findForward("succes");
		}
		
	}

	public ActionForward agent_xianxi(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		// 取得agentid
		String agentid = request.getParameter("agentid").toString();
		TbAgentInfo tbagentinfo = null;
		String resultmessage = null;
		tbagentinfo = agentmanger.getTbAgentinfo_byagentid(agentid);
		if (tbagentinfo != null) {
			request.setAttribute("tbagentinfo", tbagentinfo);
			request.setAttribute("agent_name", agentmanger
					.getAgentName(agentid));// 显示代理商名称 zr
			return mapping.findForward("xiangxi");
		} else { // 说明没有找到.
			resultmessage = "该记录不存在！";
			request.setAttribute("message", resultmessage);
			return mapping.findForward("showagentlist");
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
	public ActionForward deleteagent(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MessageException {
		String[] aidlist = request.getParameterValues("list");
		Boolean deleteagent = agentmanger.deleteagentById(aidlist);
		for (int i = 0; i < aidlist.length; i++) {
			TbAgentInfo agentInfo= agentmanger.getTbAgentinfo_byagentid(aidlist[i]);
			if(agentInfo!=null){
				//记录操作日志
				TbUserLog userLog=new TbUserLog();
				userLog.setUserId(Integer.parseInt(request.getSession().getAttribute("uId").toString()));
				userLog.setlDate(new Date());
				userLog.setlAddress(HRAddress.getIpAddr(request));
				userLog.setlType(17);
				userLog.setlContent("删除代理商{"+agentInfo.getAgent_Name()+"}");
				logManager.save(userLog);
			}
		}
		String resultmessage = null;
		if (deleteagent) {
			resultmessage = "删除成功";
			request.setAttribute("message", resultmessage);
			return mapping.findForward("showagentlist");
		} else {
			resultmessage = "删除失败";
			request.setAttribute("message", resultmessage);
			return mapping.findForward("showagentlist");
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
	public ActionForward toadd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MessageException {
		List pagentlist = agentmanger.getParentAgent(agent_id,r_id);
		if (pagentlist != null && pagentlist.size() > 0) {
			request.setAttribute("pagentlist", pagentlist);
		}
		return mapping.findForward("addagentjsp");
	}

	/**
	 * 添加代理商 zr
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws MessageException
	 */
	public ActionForward addagent(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MessageException {
		AddAgentForm addagentform = (AddAgentForm) form;
		String agentpid = "0"; // 默认父id为0
		// String agentid=addagentform.getAgentid().toString();
		String agentname = addagentform.getAgentname();
		String agentaddress = addagentform.getAgentaddress();
		String agenttel = addagentform.getAgenttel();
		String agenttel1 = addagentform.getAgenttel1();
		String agentemail = addagentform.getAgentemail();
		String agenturl = addagentform.getAgenturl();
		String agentman = addagentform.getAgentman();
		String agentman1 = addagentform.getAgentman1();
		String agentqq = addagentform.getAgentqq();
		if (addagentform.getAgentpid() != null) {
			agentpid = addagentform.getAgentpid().toString();
		}
		String agentremark = addagentform.getAgentremark();
		String agenttype = request.getParameter("rad");
		
		if (agenttype.equals("一级代理商")) {
			agentpid = "0";
		}
		String results = null;
		String names = request.getParameter("agentname");
		TbAgentInfo info = agentmanger.getAgentByName(names);
		if (info == null) {
			results = "添加成功";
			agentmanger.saveagent(agentname, agentaddress, agenttel, agenttel1,
					agentemail, agenturl, agentman,agentman1, agentqq, agentpid,
					agentremark);
			//记录操作日志
			TbUserLog userLog=new TbUserLog();
			userLog.setUserId(Integer.parseInt(request.getSession().getAttribute("uId").toString()));
			userLog.setlDate(new Date());
			userLog.setlAddress(HRAddress.getIpAddr(request));
			userLog.setlType(17);
			userLog.setlContent("添加代理商{"+agentname+"}");
			logManager.save(userLog);
			request.setAttribute("message", results);
			return mapping.findForward("showagentlist");
		} else {
			results = "代理商<" + agentname + ">已经存在，添加失败!";
			request.setAttribute("message", results);
			return mapping.findForward("showagentlist");
		}
	}

	/**
	 * 根据代理商ID查询代理商信息 zr
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws MessageException
	 */
	public ActionForward getAgentById(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MessageException {
		String agentid = request.getParameter("agentid");
		TbAgentInfo pagentinfo = agentmanger.getAgent_ByAgentID(agentid);
		if (pagentinfo != null) {
			request.setAttribute("pagentinfo", pagentinfo);
		}
		return mapping.findForward("");
	}

	/**
	 * 准备修改时的页面信息 zr
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws MessageException
	 */
	public ActionForward to_updatejsp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MessageException {
		List allagent = agentmanger.getAll_Agent();// 所有代理商
		String type=request.getParameter("type");
		if (allagent != null) {
			request.setAttribute("allagent", allagent);
		}

		List pagentlist = agentmanger.getParentAgent(agent_id,r_id);// 所有一级代理商
		if (pagentlist != null && pagentlist.size() > 0) {
			request.setAttribute("pagentlist", pagentlist);
		}

		String agentid = request.getParameter("Aid");
		TbAgentInfo agentinfo = agentmanger.getAgent_ByAgentID(agentid);// 根据代理商ID查询

		if(type!=null){
	    	request.setAttribute("type",type );
	    }
		if (agentinfo != null) {
			request.setAttribute("agentinfo", agentinfo);
			request.setAttribute("agent_name", agentmanger
					.getAgentName(agentid));
		    
		    return mapping.findForward("updateagentjsp");
		} else {
			return mapping.findForward("updateagentjsp");
		}
	}

	/**
	 * 修改代理商 zr
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws MessageException
	 */
	public ActionForward updateagent(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MessageException {
		String agentid = request.getParameter("agentid");
		String agentname = request.getParameter("agentname");
		String agentaddress = request.getParameter("agentaddress");
		String agenttel = request.getParameter("agenttel");
		String agenttel1 = request.getParameter("agenttel1");
		String agentemail = request.getParameter("agentemail");
		String agenturl = request.getParameter("agenturl");
		String agentman = request.getParameter("agentman");
		String agentman1 = request.getParameter("agentman1");
		String agentqq = request.getParameter("agentqq");
		String agentremark = request.getParameter("agentremark");
		String upda = agentmanger.updateagent(agentid, agentname, agentaddress,
				agenttel, agenttel1, agentemail, agenturl, agentman, agentman1,agentqq,
				agentremark);
		//记录操作日志
		TbUserLog userLog=new TbUserLog();
		userLog.setUserId(Integer.parseInt(request.getSession().getAttribute("uId").toString()));
		userLog.setlDate(new Date());
		userLog.setlAddress(HRAddress.getIpAddr(request));
		userLog.setlType(17);
		userLog.setlContent("修改代理商{"+agentname+"}");
		logManager.save(userLog);
		String results = null;
		if (upda != null) {
			results = "修改成功！";
			request.setAttribute("message", results);			
		} else {
			results = "修改失败！";
			request.setAttribute("message", results);
			
		}
		String type=request.getParameter("type");
		if(type!=null&&!type.equals("null")){
			return mapping.findForward("kfshowagentlist");
		}else{
			return mapping.findForward("showagentlist");
		}
	}
}