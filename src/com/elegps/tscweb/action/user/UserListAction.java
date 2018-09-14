package com.elegps.tscweb.action.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.elegps.tscweb.action.base.BaseAction;
import com.elegps.tscweb.comm.HRAddress;
import com.elegps.tscweb.comm.MD5;
import com.elegps.tscweb.comm.UserInfo;
import com.elegps.tscweb.exception.MessageException;
import com.elegps.tscweb.form.AdduserForm;
import com.elegps.tscweb.model.TbAgentInfo;
import com.elegps.tscweb.model.TbEnterpriseInfo;
import com.elegps.tscweb.model.TbRoleInfo;
import com.elegps.tscweb.model.TbUserInfo;
import com.elegps.tscweb.model.TbUserLog;

/**
 * level 2008-11-22
 * 
 * @struts.action validate="true"
 */
public class UserListAction extends BaseAction {
	/*
	 * Generated Methods
	 */
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
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MessageException {
		ActionForward actionforward = null;
		String cmd = null;
		TbUserInfo user = UserInfo.getUserInfo(request);
		if (user != null) {
			a_id = Integer.parseInt(request.getSession().getAttribute("agentId")+"");//代理商id
			r_id = Integer.parseInt(request.getSession().getAttribute("roleId")+"");//角色id
			ep_id=Integer.parseInt(request.getSession().getAttribute("epId")+"");//角色企业id
			// 如果没有就接收到参数默认显示全部类型
			if (request.getParameter("CMD") == null
					|| request.getParameter("CMD").equals("")) {
				cmd = "user_search";
			} else {
				cmd = request.getParameter("CMD");
			}

			if (cmd.equals("add")) {// 增加用户信息
				actionforward = user_add(mapping, form, request, response);
			} else if (cmd.equals("delete")) { // 删除选中用户信息
				actionforward = user_delete(mapping, form, request, response);
			} else if (cmd.equals("usermdoyijsp")) { // 把要修改的信息传到修改的jsp页面
				actionforward = user_modytojsp(mapping, form, request, response);
			} else if (cmd.equals("agentchangeusermdoy")) { // 修改页面agentchange时传到修改的jsp页面
				actionforward = agentuser_modytojsp(mapping, form, request,
						response);
			} else if (cmd.equals("usermodify")) { // 修改用户信息
				actionforward = user_modify(mapping, form, request, response);
			} else if (cmd.equals("user_search")) { // 所有条件同时查询
				actionforward = user_Sarch(mapping, form, request, response);
			} else if (cmd.equals("user_addjsp")) {// 转到增加页面
				actionforward = user_Addjsp(mapping, form, request, response);
			}else if (cmd.equals("changepassword")) {// 转到修改密码页面
				actionforward = ChangePassword(mapping, form, request, response);
			}else if(cmd.equals("userLog")){/*操作日志列表*/
				actionforward=userLogList(mapping, form, request, response);
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

	public ActionForward user_Addjsp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MessageException {
		String username = request.getParameter("user_name");
		String password = request.getParameter("password");
		String password1 = request.getParameter("password1");
		String agentid = request.getParameter("agentid");
		if (agentid == null) {
			agentid = "0";
		}
		String epid = request.getParameter("epid");
		if (epid == null) {
			epid = "0";
		}
		String roleid = request.getParameter("roleid");
		List<TbRoleInfo> listrole = rolemanager.getAllRole_Info();
		// usermanager.getAllAgent_Info()要移到Agent模块中
		List<TbAgentInfo> listagent = null;
		List<TbAgentInfo> childagentlistid = null;
		int apid = Integer.parseInt(request.getSession().getAttribute("aPId")+"");
		if(apid == 0){
			listagent = agentmanger.getParentAgent(a_id,r_id);
		//}
		// 一级指定二级代理商结果集
			childagentlistid = agentmanger.getChildAgentByParamentid(a_id+"");
		}else{
			int a = agentmanger.getAgent_ByAgentID(apid+"").getAgent_Id();
			listagent = agentmanger.getParentAgent(a,r_id);
			childagentlistid = agentmanger.getChildAgentByAId(a_id+"");
		}
		
//		List<TbAgentInfo> listagent = agentmanger.getParentAgent(a_id, r_id);//usermanager.getAllAgent_Info();
//		List<TbAgentInfo> childagentlistid = agentmanger.getChildAgentByAId(a_id+"");
		
		List<TbEnterpriseInfo> listep = null;//epmanger.getEpinfo_byagentid(agentid);

		String resultmessage = null;
		if (listrole.size() > 0 && listrole != null) {
			// 设置返回角色查询的select中所有的option值
			request.setAttribute("username", username);
			request.setAttribute("password", password);
			request.setAttribute("password1", password1);
			request.setAttribute("agentid", agentid);
			request.setAttribute("epid", epid);
			request.setAttribute("roleid", roleid);
			request.setAttribute("listagent", listagent);
			request.setAttribute("childagentlistid", childagentlistid);
			request.setAttribute("listep", listep);
			request.setAttribute("listrole", listrole);
			return mapping.findForward("useraddjsp");
		} else {
			resultmessage = "角色不存在，请先添加角色!";
			request.setAttribute("message", resultmessage);
			return mapping.findForward("showuserlist");
		}

	}

	/**
	 * 修改角色信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws MessageException
	 */
	public ActionForward user_modify(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MessageException {
		String user_id = request.getParameter("user_id");
		String user_name = request.getParameter("user_name");
		String psw = request.getParameter("user_psw");
		String roleid = request.getParameter("roleid");
		String agentid = request.getParameter("agentid");
		String epid = request.getParameter("epid");
		//没有修改密码就不需要再加密，修改密码了才加密
		String sqlPsw = usermanager.getUserInfoby_userid(user_id).getUserPassword();
		String newPsw = "";
		if(psw.equals(sqlPsw)){
			newPsw = sqlPsw;
		}else{
			newPsw = md5.str2MD5(psw);
		}
		
		String a = "-1";
		String cagentid = request.getParameter("childagentid"); 
		if(cagentid.equals("")||cagentid.equals("-1")||cagentid.equals(null)){
			a = agentid;
		}else{
			a = cagentid;
		}
		
		String modifyok = usermanager.modifyUser(user_id, user_name.trim(),
				newPsw, roleid, a, epid);
		String resultmessage = null;
		if (modifyok != null) {
			System.out.println("修改成功");
			resultmessage = "修改成功!";
			request.setAttribute("message", resultmessage);
			return mapping.findForward("showuserlist");
		} else {
			System.out.println("修改失败");
			resultmessage = "用户名<" + user_name + ">已经存在，修改失败!";
			request.setAttribute("message", resultmessage);
			return mapping.findForward("showuserlist");
		}
	}

	/**
	 * 把修改角色信息传jsp页面
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws MessageException
	 */
	public ActionForward user_modytojsp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MessageException {

		// 取得user_id
		String user_id = request.getParameter("type");
		String roleid = null;
		if (userrolemanager.getRoidinfo_ByUserid(user_id) != null) {
			roleid = userrolemanager.getRoidinfo_ByUserid(user_id);
		} else {
			roleid = "-1";
		}
		List<TbRoleInfo> listrole = rolemanager.getAllRole_Info();
		TbUserInfo userinfo = usermanager.getUserInfoby_userid(user_id);
		String resultmessage = null;
		// usermanager.getAllAgent_Info()要移到Agent模块中
		
		//List<TbAgentInfo> listagent = usermanager.getAllAgent_Info();
		List<TbAgentInfo> listagent = null;
		List<TbAgentInfo> childagentlistid = null;
		int child = -1;
		TbAgentInfo ainfo = agentmanger.getAgent_ByAgentID(userinfo.getAgent_Id()+"");//Integer.parseInt(request.getSession().getAttribute("aPId")+"");
		if(ainfo.getAgent_PId() == 0){
			listagent = agentmanger.getParentAgent(ainfo.getAgent_Id(),Integer.parseInt(roleid));
		//}
		// 一级指定二级代理商结果集
			childagentlistid = agentmanger.getChildAgentByParamentid(ainfo.getAgent_Id()+"");
		}else{
			//int a = agentmanger.getAgent_ByAgentID(ainfo.getAgent_Id()+"").getAgent_Id();
			listagent = agentmanger.getParentAgent(ainfo.getAgent_PId(),Integer.parseInt(roleid));
			childagentlistid = agentmanger.getChildAgentByAId(ainfo.getAgent_Id()+"");
			child = ainfo.getAgent_Id();
		}
		
		if (listagent != null) {
			List<TbEnterpriseInfo> listep = epmanger
					.getEpinfo_byagentid(userinfo.getAgent_Id().toString());

			if (userinfo != null) {
				if (listrole.size() > 0 && listrole != null) {
					request.setAttribute("listrole", listrole);
					request.setAttribute("roleid", roleid);
					request.setAttribute("agentid", userinfo.getAgent_Id());
					request.setAttribute("child", child);
					request.setAttribute("tbuserinfo", userinfo);
					request.setAttribute("listagent", listagent);
					request.setAttribute("childagentlistid", childagentlistid);
					request.setAttribute("listep", listep);
					return mapping.findForward("usermdify");
				} else {
					resultmessage = "角色不存在，请先添加角色!";
					request.setAttribute("message", resultmessage);
					return mapping.findForward("showuserlist");
				}
			}
		} else { // 说明没有找到要修改的记录.
			System.out.println("修改记录没有找到");
			resultmessage = "代理商或企业不存在!";
			request.setAttribute("message", resultmessage);
			return mapping.findForward("showuserlist");
		}
		return mapping.findForward("showuserlist");
	}

	/**
	 * 把修改角色信息传jsp页面
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws MessageException
	 */
	public ActionForward agentuser_modytojsp(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws MessageException {

		// 取得user_id
		String user_id = request.getParameter("type");
		String roleid = null;
		if (userrolemanager.getRoidinfo_ByUserid(user_id) != null) {
			roleid = userrolemanager.getRoidinfo_ByUserid(user_id);
		} else {
			roleid = "-1";
		}
		List<TbRoleInfo> listrole = rolemanager.getAllRole_Info();
		TbUserInfo userinfo = usermanager.getUserInfoby_userid(user_id);
		// usermanager.getAllAgent_Info()要移到Agent模块中
		List<TbAgentInfo> listagent = usermanager.getAllAgent_Info();
		List<TbEnterpriseInfo> listep = null;
		if (request.getParameter("agentid") != null) {
			listep = epmanger.getEpinfo_byagentid(request
					.getParameter("agentid"));
		} else {
			listep = epmanger.getEpinfo_byagentid(userinfo.getAgent_Id()
					.toString());
		}
		String resultmessage = null;
		if (userinfo != null) {
			if (listrole.size() > 0 && listrole != null) {
				request.setAttribute("listrole", listrole);
				request.setAttribute("roleid", roleid);
				request.setAttribute("tbuserinfo", userinfo);
				request.setAttribute("listagent", listagent);
				request
						.setAttribute("agentid", request
								.getParameter("agentid"));
				request.setAttribute("listep", listep);
				return mapping.findForward("usermdify");
			} else {
				resultmessage = "角色不存在，请先添加角色!";
				request.setAttribute("message", resultmessage);
				return mapping.findForward("showuserlist");
			}

		} else { // 说明没有找到要修改的记录.
			System.out.println("修改记录没有找到");
			resultmessage = "记录不存在!";
			request.setAttribute("message", resultmessage);
			return mapping.findForward("showuserlist");
		}
	}

	public ActionForward user_add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		AdduserForm userform = (AdduserForm) form;
		String user_name = userform.getUser_name();
		String psw = userform.getPassword();
		String roleid = userform.getRoleid();
		String agentid = userform.getAgentid();
		String epid = userform.getEpid();
		String insert = null;
		String resultmessage = null;
		
		String a = "-1";
		String cagentid = request.getParameter("childagentid"); 
		if(cagentid.equals("")||cagentid.equals("-1")||cagentid.equals(null)){
			a = agentid;
		}else{
			a = cagentid;
		}
		insert = usermanager.createUser(user_name.trim(), md5.str2MD5(psw.trim()), roleid,
				a, epid);
		//记录操作日志
		TbUserLog userLog=new TbUserLog();
		userLog.setUserId(Integer.parseInt(request.getSession().getAttribute("uId").toString()));
		userLog.setlDate(new Date());
		userLog.setlAddress(HRAddress.getIpAddr(request));
		userLog.setlType(0);
		userLog.setlContent("添加用户{"+user_name+"}");
		logManager.save(userLog);
		if (insert != null) {
			System.out.println("用户信息添加成功");
			resultmessage = "添加成功!";
			request.setAttribute("message", resultmessage);
			return mapping.findForward("showuserlist");
		} else {
			System.out.println("用户信息添加失败");
			resultmessage = "用户名<" + user_name + ">已经存在，添加失败!";
			request.setAttribute("message", resultmessage);
			return mapping.findForward("showuserlist");
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
	public ActionForward user_delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String[] list = request.getParameterValues("list");
		Boolean deluser = usermanager.deleteUser(list);
		String resultmessage = null;
		if (deluser) {
			System.out.println("用户信息删除成功");
			resultmessage = "删除成功";
			request.setAttribute("message", resultmessage);
			return mapping.findForward("showuserlist");
		} else {
			System.out.println("用户信息删除失败");
			resultmessage = "删除失败";
			request.setAttribute("message", resultmessage);
			return mapping.findForward("showuserlist");
		}
	}

	/**
	 * 根据角色名列表信息的查询条件查询
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward user_Sarch(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String cmd = request.getParameter("CMD");
		String user_name = request.getParameter("username");// 用户名

		if (user_name == null || (user_name.trim()).equals("")) {
			user_name = "";
		}
		
		//wanglei 
		int agent_id =Integer.parseInt(request.getSession().getAttribute("agentId")+"");//代理商id
		String user_id =request.getSession().getAttribute("uId")+"";//登录用户id
		int userCount = 0;
		userCount = usermanager.getUser_SearchCount(user_name,agent_id);
		// 获取每页的条数
		int pageSize = 12;
		// 获取总页数
		int pageCount = usermanager.getPageCount(userCount, pageSize);
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
		request.setAttribute("userCount", userCount);
		// 返回每页面显示条数
		request.setAttribute("pageSize", pageSize);
		// 返回当前页
		request.setAttribute("currentPage", pageNo);
		// 设置返回的命令字
		request.setAttribute("CMD", cmd);
		// 设置返回用户类型

		// 设置返回用户查询的intput中值
		request.setAttribute("user_name", user_name);

		// 返回的总页数
		request.setAttribute("pageCount", pageCount);
		
		//登录用户排第一位
		List uList = usermanager.getTbUserInfoby_name(
				pageNo, pageSize, user_name, agent_id);
		List<Map> userList = new ArrayList<Map>();
		if(uList!=null){
			for(int i = 0; i<uList.size(); i++){
				Map map = new HashMap();
			    map = (Map) uList.get(i);
				if(map.get("userid").toString().equals(user_id)){
					userList.add(0, map);
				}else{
					userList.add(map);
				}
			}
		}
		request.setAttribute("userList", userList);
		
		return mapping.findForward("succes");
	}

	//转到修改用户密码页面
	public ActionForward ChangePassword(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MessageException {
		return mapping.findForward("mdify");
	}
	/**
	 * 获取日志记录列表
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws MessageException
	 */
	public ActionForward userLogList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws MessageException{
		String cmd = request.getParameter("CMD");
		String userId=request.getParameter("userId");
		String lType=request.getParameter("lType");
		String startDate=request.getParameter("startDate");
		String endDate=request.getParameter("endDate");
		int uId=-1;
		int lt=-1;
		if(userId!=null&&!"".equals(userId)){
			uId=Integer.parseInt(userId);
		}
		if(lType!=null&&!"".equals(lType)){
			lt=Integer.parseInt(lType);
		}
		int logCount = 0;
		logCount =logManager.searchUserLogCount(uId, lt, startDate, endDate);
		// 获取每页的条数
		int pageSize = 12;
		// 获取总页数
		int pageCount = (logCount + pageSize - 1) / pageSize;
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
		List<TbUserLog> logList=logManager.searchUserLogList(pageNo, pageSize, uId, lt, startDate, endDate);
		request.setAttribute("logList", logList);
		// 获取总条数
		request.setAttribute("userCount", logCount);
		// 返回每页面显示条数
		request.setAttribute("pageSize", pageSize);
		// 返回当前页
		request.setAttribute("currentPage", pageNo);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("CMD", cmd);
		request.setAttribute("userId", userId);
		request.setAttribute("lType", lType);
		request.setAttribute("startDate", startDate);
		request.setAttribute("endDate", endDate);
		request.setAttribute("userList", usermanager.getAllUser_Info());
		return mapping.findForward("tologlist");
	}
}