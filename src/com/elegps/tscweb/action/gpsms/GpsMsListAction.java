package com.elegps.tscweb.action.gpsms;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.elegps.tscweb.action.base.BaseAction;
import com.elegps.tscweb.comm.HRAddress;
import com.elegps.tscweb.comm.UserInfo;
import com.elegps.tscweb.exception.MessageException;
import com.elegps.tscweb.model.TbAgentInfo;
import com.elegps.tscweb.model.TbEnterpriseInfo;
import com.elegps.tscweb.model.TbGpsInfo;
import com.elegps.tscweb.model.TbGrpInfo;
import com.elegps.tscweb.model.TbMsInfo;
import com.elegps.tscweb.model.TbUserInfo;
import com.elegps.tscweb.model.TbUserLog;

/**
 * MyEclipse Struts Creation date: 10-25-2008
 * 
 * XDoclet definition:
 * 
 * @struts.action validate="true"
 */
public class GpsMsListAction extends BaseAction {
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
			ep_id =Integer.parseInt(request.getSession().getAttribute("epId")+"");//角色id
			// 如果没有就接收到参数默认显示全部GPS厂商跟终端对应关系信息
			if (request.getParameter("CMD") == null
					|| request.getParameter("CMD").equals("")) {
				cmd = "gpsms_search";
			} else {
				cmd = request.getParameter("CMD");
			}

			if (cmd.equals("add")) {// 增加GPS厂商终端对应信息
				actionforward = gpsms_add(mapping, form, request, response);
			} else if (cmd.equals("gps_id")) { // 根据GPS厂商查询群组信息
				actionforward = gps_idsearch(mapping, form, request, response);
			} else if (cmd.equals("ms_id")) { // 根据群组通话状态查询群组信息(1通话中、0未在通话中）
				actionforward = ms_idsearch(mapping, form, request, response);
			} else if (cmd.equals("delete")) { // 删除选中群组信息(逻辑删除flag改为0)
				actionforward = gpsms_delete(mapping, form, request, response);
			} else if (cmd.equals("addtojsp")) {// 把信息加载到gpsms对应关系的增加页面
				actionforward = gpsms_addtojsp(mapping, form, request, response);
			} else if (cmd.equals("gpsms_search")) { // 所有条件同时查询
				actionforward = gpsms_Sarch(mapping, form, request, response);
			} else if (cmd.equals("pladdtom_tojsp")) { // 把信息加载到gpsms对应关系的批量增加页面(根据GPS号批量添加终端)
				actionforward = gpsms_addmstojsp(mapping, form, request,
						response);
			} else if (cmd.equals("gpsmsplmsadd")) { // 根据GPS批量增添加终端用户信息(根据GPS号批量添加终端)
				actionforward = gpsms_add_bygpsid(mapping, form, request,
						response);
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
	 * 批量增加GPS终端对应关系(根据gpsid批量增加终端)
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward gpsms_add_bygpsid(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		String gps_id = request.getParameter("gpsid");
		String ms_id[] = request.getParameterValues("selectmsid");
		String changeMs=request.getParameter("changeMs");
		String [] msArray=changeMs.split(",");
		String insert = null;
		insert = gpsmsmanager.createGpsMsInfo_ByGpsid(gps_id, ms_id);
		if (insert != null) {
			for (int i = 0; i < msArray.length; i++) {
				String msa=msArray[i];
				String [] msb=msa.split("\\$");
				if(changeMs.indexOf(msb[0])==changeMs.lastIndexOf(msb[0])){
					//记录操作日志
					TbUserLog userLog=new TbUserLog();
					userLog.setUserId(Integer.parseInt(request.getSession().getAttribute("uId").toString()));
					userLog.setlDate(new Date());
					userLog.setlAddress(HRAddress.getIpAddr(request));
					userLog.setlType((msb[1].equals("1"))?12:13);
					userLog.setlContent("GPS:{ "+gps_id+" }终端:{ "+msb[0]+" }"+((msb[1].equals("1"))?"添加":"取消")+"对应关系");
					logManager.save(userLog);
				}
			}
			request.setAttribute("message", insert);
			return mapping.findForward("showgpsmslist");
		} else {
			request.setAttribute("message", insert);
			return mapping.findForward("showgpsmslist");
		}

	}

	/**
	 * 把信息加载到gpsms对应关系的批量增加页面(根据GPS号批量添加终端)
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward gpsms_addmstojsp(ActionMapping mapping,
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
			ep = "-1";
		}
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
//		request.setAttribute("epList", epmanger.getEpinfo_byeid(pagentid,
//				childagentid, ep_id, r_id));
		request.setAttribute("gpsList", gpsmanager.getAllGps_Info());
		request.setAttribute("pagentid", pagentid);
		request.setAttribute("childagentid", childagentid);
		request.setAttribute("epid", ep);
		return mapping.findForward("gpsmsaddplmsjsp");
	}

	/**
	 * 把信息加载到gpsms对应关系的增加页面
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward gpsms_addtojsp(ActionMapping mapping, ActionForm form,
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
//		request.setAttribute("epList", epmanger.getEpinfo_byeid(pagentid,
//				childagentid, ep_id, r_id));
		request.setAttribute("gpsList", gpsmanager.getAllGps_Info());

		if (ep != "-1") {
			request.setAttribute("msList", msmanager.getAllMs_Info(pagentid,
					childagentid, ep));
		}
		request.setAttribute("pagentid", pagentid);
		request.setAttribute("childagentid", childagentid);
		request.setAttribute("epid", ep);
		return mapping.findForward("gpsmsaddjsp");
	}

	/**
	 * 根据gps_id查询
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward gps_idsearch(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MessageException {
		String cmd = request.getParameter("CMD");
		String type = "0"; // 2全部GPS厂商

		// 如果type取值为空或为null,刚显示全部GPS厂商信息
		if (request.getParameter("type") == null
				|| request.getParameter("type").equals("")) {
			type = "2";
		} else {
			type = request.getParameter("type");
		}

		if (type.equals("2")) { // 全部类型
			// 获取总条数
			int gpsmsCount = 0;
			gpsmsCount = gpsmsmanager.getAllGps_idCount();
			// 获取每页的条数
			int pageSize = 12;
			// 获取总页数
			int pageCount = gpsmsmanager.getPageCount(gpsmsCount, pageSize);
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
			request.setAttribute("gpsmsCount", gpsmsCount);
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
			request.setAttribute("gps_id", type);

			// 设置返回群组查询的select中所有的option值
			request.setAttribute("gpsList", gpsmanager.getAllGps_Info());
			// 设置返回终端查询的select中所有的option值
			// request.setAttribute("msList", msmanager.getAllMs_Info());

			request.setAttribute("gpsmsList", gpsmsmanager
					.getAllGrpid_InfoByPage(pageNo, pageSize));
			return mapping.findForward("success");

		} else {
			// 获取总条数
			int gpsmsCount = 0;
			gpsmsCount = gpsmsmanager.getGps_idCount(type);
			// 获取每页的条数
			int pageSize = 12;
			// 获取总页数
			int pageCount = gpsmsmanager.getPageCount(gpsmsCount, pageSize);
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
			request.setAttribute("gpsmsCount", gpsmsCount);
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
			request.setAttribute("gps_id", type);
			// 设置返回群组查询的select中所有的option值
			request.setAttribute("gpsList", gpsmanager.getAllGps_Info());
			// 设置返回终端查询的select中所有的option值
			// request.setAttribute("msList", msmanager.getAllMs_Info());
			request.setAttribute("gpsmsList", gpsmsmanager.getGpsid_InfoByPage(
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
		String type = "0"; // 全部GPS厂商

		// 如果type取值为空或为null,刚显示全部GPS厂商信息
		if (request.getParameter("type") == null
				|| request.getParameter("type").equals("")) {
			type = "2";
		} else {
			type = request.getParameter("type");
		}

		if (type.equals("2")) { // 全部终端号
			// 获取总条数
			int gpsmsCount = 0;
			gpsmsCount = gpsmsmanager.getAllGps_idCount();
			// 获取每页的条数
			int pageSize = 12;
			// 获取总页数
			int pageCount = gpsmsmanager.getPageCount(gpsmsCount, pageSize);
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
			request.setAttribute("gpsmsCount", gpsmsCount);
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
			request.setAttribute("grpList", gpsmanager.getAllGps_Info());
			// 设置返回终端查询的select中所有的option值
			// request.setAttribute("msList", msmanager.getAllMs_Info());

			request.setAttribute("gpsmsList", gpsmsmanager
					.getAllMsid_InfoByPage(pageNo, pageSize));
			return mapping.findForward("success");

		} else {
			// 获取总条数
			int gpsmsCount = 0;
			gpsmsCount = gpsmsmanager.getMs_idCount(type);
			// 获取每页的条数
			int pageSize = 12;
			// 获取总页数
			int pageCount = gpsmanager.getPageCount(gpsmsCount, pageSize);
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
			request.setAttribute("gpsmsCount", gpsmsCount);
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
			request.setAttribute("gpsList", gpsmanager.getAllGps_Info());
			// 设置返回终端查询的select中所有的option值
			// request.setAttribute("msList", msmanager.getAllMs_Info());
			request.setAttribute("gpsmsList", gpsmsmanager.getMsid_InfoByPage(
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
	public ActionForward gpsms_add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String gps_id = request.getParameter("gpsid");
		String ms_id = request.getParameter("ms_id");
		String insert = null;
		String resultmessage = null;
		insert = gpsmsmanager.createGpsMsInfo(gps_id, ms_id);
		if (insert != null) {
				//记录操作日志
				TbUserLog userLog=new TbUserLog();
				userLog.setUserId(Integer.parseInt(request.getSession().getAttribute("uId").toString()));
				userLog.setlDate(new Date());
				userLog.setlAddress(HRAddress.getIpAddr(request));
				userLog.setlType(12);
				userLog.setlContent("GPS:{ "+gps_id+" }终端:{ "+ms_id+" }添加对应关系");
				logManager.save(userLog);
			System.out.println("GPS厂商终端对应关系添加成功");
			resultmessage = "添加成功!";
			request.setAttribute("message", resultmessage);
			return mapping.findForward("showgpsmslist");
		} else {
			System.out.println("GPS厂商终端对应关系添加失败");
			resultmessage = "GPS厂商终端对应关系添已经存在,添加失败!";
			request.setAttribute("message", resultmessage);
			return mapping.findForward("showgpsmslist");
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
	public ActionForward gpsms_delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String[] list = request.getParameterValues("list");
		for (int i = 0; i < list.length; i++) {
			System.out.println(list[i]);
		}
		Boolean deletesuce;
		deletesuce = gpsmsmanager.deleteGpsMs(list);
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
				userLog.setlType(13);
				userLog.setlContent("GPS:{ "+mgs[0]+" }终端:{ "+mgs[1]+" }取消对应关系");
				logManager.save(userLog);
			}
			System.out.println("删除成功");
			resultmessage = "删除成功!";
			request.setAttribute("message", resultmessage);
			return mapping.findForward("showgpsmslist");
		} else {
			System.out.println("删除失败");
			resultmessage = "删除失败!";
			request.setAttribute("message", resultmessage);
			return mapping.findForward("showgpsmslist");
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
	public ActionForward gpsms_Sarch(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String pagentid = ""; // 一级代理商id
		String childagentid = ""; // 二级代理商id
		String ep = ""; // 企业id
		String cmd = request.getParameter("CMD");
		String gpsid = request.getParameter("gpsid");
		String msid = request.getParameter("msid");// 终端号码
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
		if (gpsid == null) { // 为全部GPS编号
			gpsid = "";
		}

		if (msid == null) {// 全部终端号
			msid = "";
		}
		// 获取总条数
		int gpsmsCount = 0;
		gpsmsCount = gpsmsmanager.getGpsMs_SearchCount(gpsid, msid, pagentid,
				childagentid, ep);
		// 获取每页的条数
		int pageSize = 12;
		// 获取总页数
		int pageCount = gpsmsmanager.getPageCount(gpsmsCount, pageSize);
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
		request.setAttribute("gpsmsCount", gpsmsCount);
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
		request.setAttribute("gpsid", gpsid);
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
//		request.setAttribute("epList", epmanger.getEpinfo_byeid(pagentid,
//				childagentid, ep_id, r_id));
		// 设置返回群组信息
		request.setAttribute("gpsList", gpsmanager.getAllGps_Info());
		// 设置返回终端信息
		request.setAttribute("msList", msmanager.getAllMs_Info(pagentid,
				childagentid, ep));

		request.setAttribute("gpsmsList", gpsmsmanager.getGpsMs_SearchByPage(
				pageNo, pageSize, gpsid, msid, pagentid, childagentid, ep));
		return mapping.findForward("success");

	}

}