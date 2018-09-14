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
			// ���û�оͽ��յ�����Ĭ����ʾȫ������
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
			request.setAttribute("message", "Session���ڣ������µ�¼");
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
			request.setAttribute("message", "��ӳɹ�!");
		} else {
			request.setAttribute("message", "���ʧ��!");
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
			// ��ȡÿҳ������
			int pageSize = 12;
			// ��ȡ��ҳ��
			int pageCount = (msCount + pageSize - 1) / pageSize;
			// msmanager.getPageCount(msCount, pageSize);
			// ��ҳ��ȡ�õ�ǰҳ
			int pageNo;
			String pageNoStr = request.getParameter("pageNo");
			if (pageNoStr == null || pageNoStr.trim().equals("")) {
				pageNo = 1;
			} else {
				pageNo = Integer.parseInt(request.getParameter("pageNo"));
			}
			// �������ҳ�Ѿ����������ҳ
			if (pageNo > pageCount) {
				pageNo = pageCount;
			}
			// �������ҳС��һҳ
			if (pageNo < 1) {
				pageNo = 1;
			}
			// ��ȡ������
			request.setAttribute("trafficCount", msCount);
			// ����ÿҳ����ʾ����
			request.setAttribute("pageSize", pageSize);
			// ���ص�ǰҳ
			request.setAttribute("currentPage", pageNo);
			request.setAttribute("pageCount", pageCount);
			// ���÷��ص�������
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
			request.setAttribute("message", "�޸ĳɹ�!");
		} else {
			request.setAttribute("message", "�޸�ʧ��!");
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
