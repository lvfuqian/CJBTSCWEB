package com.elegps.tscweb.action.gps;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.elegps.tscweb.action.base.BaseAction;
import com.elegps.tscweb.comm.HRAddress;
import com.elegps.tscweb.comm.UserInfo;
import com.elegps.tscweb.exception.MessageException;
import com.elegps.tscweb.form.AddgpsForm;
import com.elegps.tscweb.model.TbGpsInfo;
import com.elegps.tscweb.model.TbUserInfo;
import com.elegps.tscweb.model.TbUserLog;

/**
 * MyEclipse Struts Creation date: 10-20-2008
 * 
 * XDoclet definition:
 * 
 * @struts.action validate="true"
 */
public class GpsListAction extends BaseAction {
	/*
	 * Generated Methods
	 */

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
		String type=null;
		if (user != null) {
			// 如果没有就接收到参数默认显示全部类型
			if (request.getParameter("CMD") == null
					|| request.getParameter("CMD").equals("")) {
				cmd = "gps_id_list";
			} else {
				cmd = request.getParameter("CMD");
			}

			if (cmd.equals("add")) {// 增加GPS信息
				actionforward = gps_add(mapping, form, request, response);
			} else if (cmd.equals("gps_id_list")) { // 根据GPS帐号和名称查询GPS信息
				type=request.getParameter("type");
				actionforward = gps_idnameSarch(mapping, form, request,
						response,type);

			} else if (cmd.equals("delete")) { // 删除选中GPS信息
				actionforward = gps_delete(mapping, form, request, response);
			} else if (cmd.equals("gpsmodify")) { // 修改终端用户信息
				actionforward = grp_modify(mapping, form, request, response);
			} else if (cmd.equals("togpsmdoyijsp")) { // 把要修改的信息传到修改的jsp页面
				actionforward = gps_modytojsp(mapping, form, request, response);
			}else if(cmd.equals("addjsp")){
				actionforward = gps_addjsp(mapping, form, request, response);
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
	 * 转到添加页面
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws MessageException
	 */
	public ActionForward gps_addjsp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MessageException {
			return mapping.findForward("addjsp");
	}
	
	
	/**
	 * 修改GPS信息
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
		String gps_id = request.getParameter("gps_id");
		String password = request.getParameter("password");
		String gps_name = request.getParameter("gps_name");
		String modifyok = gpsmanager.modifyGps(gps_id, password.trim(),
				gps_name.trim());
		//记录操作日志
		TbUserLog userLog=new TbUserLog();
		userLog.setUserId(Integer.parseInt(request.getSession().getAttribute("uId").toString()));
		userLog.setlDate(new Date());
		userLog.setlAddress(HRAddress.getIpAddr(request));
		userLog.setlType(10);
		userLog.setlContent("修改GPS账号{"+gps_id+"}");
		logManager.save(userLog);
		String resultmessage = null;
		if (modifyok != null) {
			System.out.println("修改成功");
			resultmessage = "修改成功!";
			request.setAttribute("message", resultmessage);
			return mapping.findForward("showgpslist");
		} else {
			System.out.println("运营商名称已经存在,修改失败");
			resultmessage = "修改失败!";
			request.setAttribute("message", resultmessage);
			return mapping.findForward("showgpslist");
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
	public ActionForward gps_modytojsp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MessageException {
		// 取得grpid
		String gpsid = request.getParameter("gpsid");
		// request.setAttribute("gpsid", gpsid);
		// return mapping.findForward("gpsmdify");
		TbGpsInfo tbgps = null;
		tbgps = gpsmanager.getGps_bygrpid(gpsid);
		if (tbgps != null) {
			request.setAttribute("tbgpsinfo", tbgps);
			return mapping.findForward("gpsmdify");
		} else { // 说明没有找到要修改的记录.
			return null;
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
	public ActionForward gps_idnameSarch(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response,String type) {
		String cmd = request.getParameter("CMD");
		String gpsid = "";
		String gpsname = "";

		// 如果gps_id取值为空或为null,刚显示全部gps_id的信息
		if (request.getParameter("gpsid") == null) {
			gpsid = "";
		} else {
			gpsid = request.getParameter("gpsid").trim();
		}
		if (request.getParameter("gpsname") == null) {
			gpsname = "";
		} else {
			gpsname = request.getParameter("gpsname").trim();
		}

		// 获取总条数
		int gpsCount = 0;
		gpsCount = gpsmanager.getGps_idCount(gpsid, gpsname);
		// 获取每页的条数
		int pageSize = 12;
		// 获取总页数
		int pageCount = gpsmanager.getPageCount(gpsCount, pageSize);
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
		request.setAttribute("gpsCount", gpsCount);
		// 返回每页面显示条数
		request.setAttribute("pageSize", pageSize);
		// 返回当前页
		request.setAttribute("currentPage", pageNo);

		// 返回的总页数
		request.setAttribute("pageCount", pageCount);

		// 返回页面查询关键字gpsid
		request.setAttribute("gpsid", gpsid);

		// 返回页面查询关键字gpsname
		request.setAttribute("gpsname", gpsname);

		// 设置返回的命令字
		request.setAttribute("CMD", cmd);

		request.setAttribute("gpsList", gpsmanager.getGps_idyPage(pageNo,
				pageSize, gpsid, gpsname));
		if(type!=null){
			return mapping.findForward("kfsucces");
		}else{
			return mapping.findForward("succes");
		}

	}

	public ActionForward gps_add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		AddgpsForm addgpsform = (AddgpsForm) form;
		String gps_id = addgpsform.getGps_id();
		String password = addgpsform.getPasword();
		String gps_name = addgpsform.getGps_name();
		String insert = null;
		insert = gpsmanager.createGps(gps_id, password, gps_name);
		//记录操作日志
		TbUserLog userLog=new TbUserLog();
		userLog.setUserId(Integer.parseInt(request.getSession().getAttribute("uId").toString()));
		userLog.setlDate(new Date());
		userLog.setlAddress(HRAddress.getIpAddr(request));
		userLog.setlType(9);
		userLog.setlContent("添加GPS账号{"+gps_id+"}");
		logManager.save(userLog);
		String resultmessage = null;
		if (insert != null) {
			System.out.println("GPS运营商添加成功");
			resultmessage = "GPS运营商添加成功!";
			request.setAttribute("message", resultmessage);
			return mapping.findForward("showgpslist");
		} else {
			System.out.println("GPS运营商添加失败");
			resultmessage = "GPS运营商,添加失败!";
			request.setAttribute("message", resultmessage);
			return mapping.findForward("showgpslist");
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
	public ActionForward gps_delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String[] list = request.getParameterValues("list");
		String resultmessage = null;
		for (int i = 0; i < list.length; i++) {
			//记录操作日志
			TbUserLog userLog=new TbUserLog();
			userLog.setUserId(Integer.parseInt(request.getSession().getAttribute("uId").toString()));
			userLog.setlDate(new Date());
			userLog.setlAddress(HRAddress.getIpAddr(request));
			userLog.setlType(11);
			userLog.setlContent("删除GPS账号{"+list[i]+"}");
			logManager.save(userLog);
		}
		if (gpsmanager.deleteGps(list)) {
			System.out.println("服务商登录账号信息删除成功");
			resultmessage = "删除成功!";
			request.setAttribute("message", resultmessage);
			return mapping.findForward("showgpslist");
		} else {
			System.out.println("服务商登录账号信息删除失败");
			resultmessage = "删除失败!";
			request.setAttribute("message", resultmessage);
			return mapping.findForward("showgpslist");
		}

	}
}