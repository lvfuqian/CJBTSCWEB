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
			// ���û�оͽ��յ�����Ĭ����ʾȫ������
			if (request.getParameter("CMD") == null
					|| request.getParameter("CMD").equals("")) {
				cmd = "gps_id_list";
			} else {
				cmd = request.getParameter("CMD");
			}

			if (cmd.equals("add")) {// ����GPS��Ϣ
				actionforward = gps_add(mapping, form, request, response);
			} else if (cmd.equals("gps_id_list")) { // ����GPS�ʺź����Ʋ�ѯGPS��Ϣ
				type=request.getParameter("type");
				actionforward = gps_idnameSarch(mapping, form, request,
						response,type);

			} else if (cmd.equals("delete")) { // ɾ��ѡ��GPS��Ϣ
				actionforward = gps_delete(mapping, form, request, response);
			} else if (cmd.equals("gpsmodify")) { // �޸��ն��û���Ϣ
				actionforward = grp_modify(mapping, form, request, response);
			} else if (cmd.equals("togpsmdoyijsp")) { // ��Ҫ�޸ĵ���Ϣ�����޸ĵ�jspҳ��
				actionforward = gps_modytojsp(mapping, form, request, response);
			}else if(cmd.equals("addjsp")){
				actionforward = gps_addjsp(mapping, form, request, response);
			}
		} else {
			request.setAttribute("message", "Session���ڣ������µ�¼");
			actionforward = mapping.findForward("logging");
		}
		if (actionforward == null) {
			return mapping.findForward("succes");
		} 
		return actionforward;

	}

	
	/**
	 * ת�����ҳ��
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
	 * �޸�GPS��Ϣ
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
		//��¼������־
		TbUserLog userLog=new TbUserLog();
		userLog.setUserId(Integer.parseInt(request.getSession().getAttribute("uId").toString()));
		userLog.setlDate(new Date());
		userLog.setlAddress(HRAddress.getIpAddr(request));
		userLog.setlType(10);
		userLog.setlContent("�޸�GPS�˺�{"+gps_id+"}");
		logManager.save(userLog);
		String resultmessage = null;
		if (modifyok != null) {
			System.out.println("�޸ĳɹ�");
			resultmessage = "�޸ĳɹ�!";
			request.setAttribute("message", resultmessage);
			return mapping.findForward("showgpslist");
		} else {
			System.out.println("��Ӫ�������Ѿ�����,�޸�ʧ��");
			resultmessage = "�޸�ʧ��!";
			request.setAttribute("message", resultmessage);
			return mapping.findForward("showgpslist");
		}
	}

	/**
	 * ���޸��û���Ϣ��jspҳ��
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
		// ȡ��grpid
		String gpsid = request.getParameter("gpsid");
		// request.setAttribute("gpsid", gpsid);
		// return mapping.findForward("gpsmdify");
		TbGpsInfo tbgps = null;
		tbgps = gpsmanager.getGps_bygrpid(gpsid);
		if (tbgps != null) {
			request.setAttribute("tbgpsinfo", tbgps);
			return mapping.findForward("gpsmdify");
		} else { // ˵��û���ҵ�Ҫ�޸ĵļ�¼.
			return null;
		}
	}

	/**
	 * ����ms_id��ģ����ѯ
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

		// ���gps_idȡֵΪ�ջ�Ϊnull,����ʾȫ��gps_id����Ϣ
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

		// ��ȡ������
		int gpsCount = 0;
		gpsCount = gpsmanager.getGps_idCount(gpsid, gpsname);
		// ��ȡÿҳ������
		int pageSize = 12;
		// ��ȡ��ҳ��
		int pageCount = gpsmanager.getPageCount(gpsCount, pageSize);
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
		request.setAttribute("gpsCount", gpsCount);
		// ����ÿҳ����ʾ����
		request.setAttribute("pageSize", pageSize);
		// ���ص�ǰҳ
		request.setAttribute("currentPage", pageNo);

		// ���ص���ҳ��
		request.setAttribute("pageCount", pageCount);

		// ����ҳ���ѯ�ؼ���gpsid
		request.setAttribute("gpsid", gpsid);

		// ����ҳ���ѯ�ؼ���gpsname
		request.setAttribute("gpsname", gpsname);

		// ���÷��ص�������
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
		//��¼������־
		TbUserLog userLog=new TbUserLog();
		userLog.setUserId(Integer.parseInt(request.getSession().getAttribute("uId").toString()));
		userLog.setlDate(new Date());
		userLog.setlAddress(HRAddress.getIpAddr(request));
		userLog.setlType(9);
		userLog.setlContent("���GPS�˺�{"+gps_id+"}");
		logManager.save(userLog);
		String resultmessage = null;
		if (insert != null) {
			System.out.println("GPS��Ӫ����ӳɹ�");
			resultmessage = "GPS��Ӫ����ӳɹ�!";
			request.setAttribute("message", resultmessage);
			return mapping.findForward("showgpslist");
		} else {
			System.out.println("GPS��Ӫ�����ʧ��");
			resultmessage = "GPS��Ӫ��,���ʧ��!";
			request.setAttribute("message", resultmessage);
			return mapping.findForward("showgpslist");
		}

	}

	/**
	 * ����ɾ��
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
			//��¼������־
			TbUserLog userLog=new TbUserLog();
			userLog.setUserId(Integer.parseInt(request.getSession().getAttribute("uId").toString()));
			userLog.setlDate(new Date());
			userLog.setlAddress(HRAddress.getIpAddr(request));
			userLog.setlType(11);
			userLog.setlContent("ɾ��GPS�˺�{"+list[i]+"}");
			logManager.save(userLog);
		}
		if (gpsmanager.deleteGps(list)) {
			System.out.println("�����̵�¼�˺���Ϣɾ���ɹ�");
			resultmessage = "ɾ���ɹ�!";
			request.setAttribute("message", resultmessage);
			return mapping.findForward("showgpslist");
		} else {
			System.out.println("�����̵�¼�˺���Ϣɾ��ʧ��");
			resultmessage = "ɾ��ʧ��!";
			request.setAttribute("message", resultmessage);
			return mapping.findForward("showgpslist");
		}

	}
}