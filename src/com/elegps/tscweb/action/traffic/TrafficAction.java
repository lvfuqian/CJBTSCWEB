package com.elegps.tscweb.action.traffic;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.elegps.tscweb.action.base.BaseAction;
import com.elegps.tscweb.comm.UserInfo;
import com.elegps.tscweb.exception.MessageException;
import com.elegps.tscweb.model.TbUserInfo;
import com.elegps.tscweb.model.Vehicle;

public class TrafficAction extends BaseAction {

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ActionForward actionforward = null;
		String cmd = "";
		TbUserInfo user = UserInfo.getUserInfo(request);
		if (user != null) {
			// 如果没有就接收到参数默认显示全部类型
			if (request.getParameter("CMD") == null
					|| request.getParameter("CMD").equals("")) {
				cmd = "traffic_search";
			} else {
				cmd = request.getParameter("CMD");
			}
			if (cmd.equals("traffic_search")) {
				actionforward = trafficSearch(mapping, form, request, response);
			}
			if (cmd.equals("traffic_update")) {
				actionforward = trafficUpdate(mapping, form, request, response);
			}
			if (cmd.equals("traffic_delete")) {
				actionforward = delete(mapping, form, request, response);
			}
			if (cmd.equals("to_update")) {
				actionforward = toUpdate(mapping, form, request, response);
			}
			if (cmd.equals("to_add")) {
				actionforward = toAdd(mapping, form, request, response);
			}
			if (cmd.equals("traffic_add")) {
				actionforward = trafficAdd(mapping, form, request, response);
			}
		} else {
			request.setAttribute("message", "Session过期，请重新登录");
			actionforward = mapping.findForward("logging");
		}
		return actionforward;
	}

	public ActionForward trafficAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MessageException {
		Vehicle vehicle = new Vehicle();
		String chepai = request.getParameter("chepai");
		String cheji = request.getParameter("cheji");
		String gprs = request.getParameter("gprs");
		vehicle.setChepai(chepai);
		vehicle.setCheji(cheji);
		vehicle.setGPRS(gprs);
		vbiz.create(vehicle);
		if (chepai != null && !"".equals(chepai) && cheji != null
				&& !"".equals(cheji) && gprs != null && !"".equals(gprs)) {
			request.setAttribute("message", "添加成功!");
		} else {
			request.setAttribute("message", "添加失败!");
		}
		return mapping.findForward("search");
	}

	public ActionForward trafficSearch(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MessageException {
		int msCount = 0;
		String chepai = null;
		String cheji=request.getParameter("chejis");
		String gps=request.getParameter("gpss");
		if (request.getParameter("chepai") != null
				&& !"".equals(request.getParameter("chepai"))) {
			chepai = request.getParameter("chepai");
		}
		try {
			msCount = vbiz.totalCount(chepai,cheji,gps);
			// 获取每页的条数
			int pageSize = 12;
			// 获取总页数
			int pageCount = (msCount + pageSize - 1) / pageSize;
			// msmanager.getPageCount(msCount, pageSize);
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
			request.setAttribute("trafficCount", msCount);
			// 返回每页面显示条数
			request.setAttribute("pageSize", pageSize);
			// 返回当前页
			request.setAttribute("currentPage", pageNo);
			request.setAttribute("pageCount", pageCount);
			// 设置返回的命令字
			request.setAttribute("CMD", "traffic_search");

			List<Vehicle> vehicle = vbiz.listAll(pageNo, pageSize, chepai,cheji,gps);
			if (vehicle != null) {
				request.setAttribute("vehicle", vehicle);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward("traffic_search");
	}

	public ActionForward trafficUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MessageException {
		String vid = request.getParameter("vid");
		String chepai = request.getParameter("chepais");
		String cheji = request.getParameter("cheji");
		String GPRS = request.getParameter("gprs");
		int bool = vbiz.update(chepai, cheji, GPRS, new Integer(vid));
		if (bool > 0) {
			request.setAttribute("message", "修改成功!");
		} else {
			request.setAttribute("message", "修改失败!");
		}
		return mapping.findForward("search");
	}

	public ActionForward toUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String vid = request.getParameter("vid");
		Vehicle vehicle = (Vehicle) vbiz.getBean(Integer.parseInt(vid));
		if (vehicle != null) {
			request.setAttribute("vehicle", vehicle);
			return mapping.findForward("to_jsp");
		}
		return mapping.findForward("traffic_search");
	}

	public ActionForward toAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		return mapping.findForward("to_jsp2");
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MessageException {
		String[] vid = request.getParameterValues("list");
		if (vid != null && vid.length > 0) {
			for (int i = 0; i < vid.length; i++) {
				vbiz.delete(new Integer(vid[i]));
			}
		}
		return mapping.findForward("search");
	}

}
