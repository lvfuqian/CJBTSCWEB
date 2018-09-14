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
	
	//wanglei ��ҵ�û�ִ��
	public static int a_id = 0;//������id
	public static int r_id = 0;//��ɫid
	public static int ep_id = 0;//��ɫid
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
			a_id = Integer.parseInt(request.getSession().getAttribute("agentId")+"");//������id
			r_id = Integer.parseInt(request.getSession().getAttribute("roleId")+"");//��ɫid
			ep_id =Integer.parseInt(request.getSession().getAttribute("epId")+"");//��ɫid
			// ���û�оͽ��յ�����Ĭ����ʾȫ��GPS���̸��ն˶�Ӧ��ϵ��Ϣ
			if (request.getParameter("CMD") == null
					|| request.getParameter("CMD").equals("")) {
				cmd = "gpsms_search";
			} else {
				cmd = request.getParameter("CMD");
			}

			if (cmd.equals("add")) {// ����GPS�����ն˶�Ӧ��Ϣ
				actionforward = gpsms_add(mapping, form, request, response);
			} else if (cmd.equals("gps_id")) { // ����GPS���̲�ѯȺ����Ϣ
				actionforward = gps_idsearch(mapping, form, request, response);
			} else if (cmd.equals("ms_id")) { // ����Ⱥ��ͨ��״̬��ѯȺ����Ϣ(1ͨ���С�0δ��ͨ���У�
				actionforward = ms_idsearch(mapping, form, request, response);
			} else if (cmd.equals("delete")) { // ɾ��ѡ��Ⱥ����Ϣ(�߼�ɾ��flag��Ϊ0)
				actionforward = gpsms_delete(mapping, form, request, response);
			} else if (cmd.equals("addtojsp")) {// ����Ϣ���ص�gpsms��Ӧ��ϵ������ҳ��
				actionforward = gpsms_addtojsp(mapping, form, request, response);
			} else if (cmd.equals("gpsms_search")) { // ��������ͬʱ��ѯ
				actionforward = gpsms_Sarch(mapping, form, request, response);
			} else if (cmd.equals("pladdtom_tojsp")) { // ����Ϣ���ص�gpsms��Ӧ��ϵ����������ҳ��(����GPS����������ն�)
				actionforward = gpsms_addmstojsp(mapping, form, request,
						response);
			} else if (cmd.equals("gpsmsplmsadd")) { // ����GPS����������ն��û���Ϣ(����GPS����������ն�)
				actionforward = gpsms_add_bygpsid(mapping, form, request,
						response);
			}
		} else {
			request.setAttribute("message", "Session���ڣ������µ�¼");
			actionforward = mapping.findForward("logging");
		}

		if (actionforward == null) {
			return mapping.findForward("success");
		} 	
		return actionforward;
		

	}

	/**
	 * ��������GPS�ն˶�Ӧ��ϵ(����gpsid���������ն�)
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
					//��¼������־
					TbUserLog userLog=new TbUserLog();
					userLog.setUserId(Integer.parseInt(request.getSession().getAttribute("uId").toString()));
					userLog.setlDate(new Date());
					userLog.setlAddress(HRAddress.getIpAddr(request));
					userLog.setlType((msb[1].equals("1"))?12:13);
					userLog.setlContent("GPS:{ "+gps_id+" }�ն�:{ "+msb[0]+" }"+((msb[1].equals("1"))?"���":"ȡ��")+"��Ӧ��ϵ");
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
	 * ����Ϣ���ص�gpsms��Ӧ��ϵ����������ҳ��(����GPS����������ն�)
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
		String pagentid = ""; // һ��������id
		String childagentid = ""; // ����������id

		if (request.getParameter("pagentid") != null) {
			pagentid = request.getParameter("pagentid");
		} else {
			pagentid = "-1"; // ���������ܲ�
		}

		if (request.getParameter("childagentid") != null) {
			childagentid = request.getParameter("childagentid");
		} else {
			childagentid = "-1"; // ֱ����ҵ
		}

		String ep = request.getParameter("ep");
		if (ep == null || ep.equals("null")) {
			ep = "-1";
		}
		// һ������������
		request.setAttribute("Pagentlist", agentmanger.getParentAgent(a_id,r_id));
		// һ��ָ�����������̽����
		request.setAttribute("Cagentlist", agentmanger
				.getChildAgentByParamentid(pagentid));
		// ����������ҵĬ��
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
	 * ����Ϣ���ص�gpsms��Ӧ��ϵ������ҳ��
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
		String pagentid = ""; // һ��������id
		String childagentid = ""; // ����������id

		if (request.getParameter("pagentid") != null) {
			pagentid = request.getParameter("pagentid");
		} else {
			pagentid = "-1"; // ���������ܲ�
		}

		if (request.getParameter("childagentid") != null) {
			childagentid = request.getParameter("childagentid");
		} else {
			childagentid = "-1"; // ֱ����ҵ
		}

		String ep = request.getParameter("ep");
		if (ep == null || ep.equals("null")) {
			ep = "-1";
		}
		// һ������������
		request.setAttribute("Pagentlist", agentmanger.getParentAgent(a_id,r_id));
		// һ��ָ�����������̽����
		request.setAttribute("Cagentlist", agentmanger
				.getChildAgentByParamentid(pagentid));
		// ����������ҵĬ��
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
	 * ����gps_id��ѯ
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
		String type = "0"; // 2ȫ��GPS����

		// ���typeȡֵΪ�ջ�Ϊnull,����ʾȫ��GPS������Ϣ
		if (request.getParameter("type") == null
				|| request.getParameter("type").equals("")) {
			type = "2";
		} else {
			type = request.getParameter("type");
		}

		if (type.equals("2")) { // ȫ������
			// ��ȡ������
			int gpsmsCount = 0;
			gpsmsCount = gpsmsmanager.getAllGps_idCount();
			// ��ȡÿҳ������
			int pageSize = 12;
			// ��ȡ��ҳ��
			int pageCount = gpsmsmanager.getPageCount(gpsmsCount, pageSize);
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
			request.setAttribute("gpsmsCount", gpsmsCount);
			// ����ÿҳ����ʾ����
			request.setAttribute("pageSize", pageSize);
			// ���ص�ǰҳ
			request.setAttribute("currentPage", pageNo);

			// ���ص���ҳ��
			request.setAttribute("pageCount", pageCount);

			// ���÷��ص�������
			request.setAttribute("CMD", cmd);
			// ���÷����û�����
			request.setAttribute("type", type);

			// ���÷���Ⱥ���ѯ��select��optionֵ
			request.setAttribute("gps_id", type);

			// ���÷���Ⱥ���ѯ��select�����е�optionֵ
			request.setAttribute("gpsList", gpsmanager.getAllGps_Info());
			// ���÷����ն˲�ѯ��select�����е�optionֵ
			// request.setAttribute("msList", msmanager.getAllMs_Info());

			request.setAttribute("gpsmsList", gpsmsmanager
					.getAllGrpid_InfoByPage(pageNo, pageSize));
			return mapping.findForward("success");

		} else {
			// ��ȡ������
			int gpsmsCount = 0;
			gpsmsCount = gpsmsmanager.getGps_idCount(type);
			// ��ȡÿҳ������
			int pageSize = 12;
			// ��ȡ��ҳ��
			int pageCount = gpsmsmanager.getPageCount(gpsmsCount, pageSize);
			// ��ҳ��ȡ�õ�ǰҳ
			int pageNo;
			String pageNoStr = request.getParameter("pageNo");
			if (pageNoStr == null || pageNoStr.trim().equals("")) {
				pageNo = 1;
			} else {
				pageNo = Integer.parseInt(pageNoStr.trim());
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
			request.setAttribute("gpsmsCount", gpsmsCount);
			// ����ÿҳ����ʾ����
			request.setAttribute("pageSize", pageSize);
			// ���ص�ǰҳ
			request.setAttribute("currentPage", pageNo);
			// ���ص���ҳ��
			request.setAttribute("pageCount", pageCount);
			// ���÷��ص�������
			request.setAttribute("CMD", cmd);
			// ���÷����û�����
			request.setAttribute("type", type);
			// ���÷���Ⱥ���ѯ��select��optionֵ
			request.setAttribute("gps_id", type);
			// ���÷���Ⱥ���ѯ��select�����е�optionֵ
			request.setAttribute("gpsList", gpsmanager.getAllGps_Info());
			// ���÷����ն˲�ѯ��select�����е�optionֵ
			// request.setAttribute("msList", msmanager.getAllMs_Info());
			request.setAttribute("gpsmsList", gpsmsmanager.getGpsid_InfoByPage(
					pageNo, pageSize, type));
			return mapping.findForward("success");
		}

	}

	/**
	 * ����ms_id��ѯ
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
		String type = "0"; // ȫ��GPS����

		// ���typeȡֵΪ�ջ�Ϊnull,����ʾȫ��GPS������Ϣ
		if (request.getParameter("type") == null
				|| request.getParameter("type").equals("")) {
			type = "2";
		} else {
			type = request.getParameter("type");
		}

		if (type.equals("2")) { // ȫ���ն˺�
			// ��ȡ������
			int gpsmsCount = 0;
			gpsmsCount = gpsmsmanager.getAllGps_idCount();
			// ��ȡÿҳ������
			int pageSize = 12;
			// ��ȡ��ҳ��
			int pageCount = gpsmsmanager.getPageCount(gpsmsCount, pageSize);
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
			request.setAttribute("gpsmsCount", gpsmsCount);
			// ����ÿҳ����ʾ����
			request.setAttribute("pageSize", pageSize);
			// ���ص�ǰҳ
			request.setAttribute("currentPage", pageNo);

			// ���ص���ҳ��
			request.setAttribute("pageCount", pageCount);

			// ���÷��ص�������
			request.setAttribute("CMD", cmd);
			// ���÷����û�����
			request.setAttribute("type", type);

			// ���÷���Ⱥ���ѯ��select��optionֵ
			request.setAttribute("ms_id", type);

			// ���÷���Ⱥ���ѯ��select�����е�optionֵ
			request.setAttribute("grpList", gpsmanager.getAllGps_Info());
			// ���÷����ն˲�ѯ��select�����е�optionֵ
			// request.setAttribute("msList", msmanager.getAllMs_Info());

			request.setAttribute("gpsmsList", gpsmsmanager
					.getAllMsid_InfoByPage(pageNo, pageSize));
			return mapping.findForward("success");

		} else {
			// ��ȡ������
			int gpsmsCount = 0;
			gpsmsCount = gpsmsmanager.getMs_idCount(type);
			// ��ȡÿҳ������
			int pageSize = 12;
			// ��ȡ��ҳ��
			int pageCount = gpsmanager.getPageCount(gpsmsCount, pageSize);
			// ��ҳ��ȡ�õ�ǰҳ
			int pageNo;
			String pageNoStr = request.getParameter("pageNo");
			if (pageNoStr == null || pageNoStr.trim().equals("")) {
				pageNo = 1;
			} else {
				pageNo = Integer.parseInt(pageNoStr.trim());
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
			request.setAttribute("gpsmsCount", gpsmsCount);
			// ����ÿҳ����ʾ����
			request.setAttribute("pageSize", pageSize);
			// ���ص�ǰҳ
			request.setAttribute("currentPage", pageNo);
			// ���ص���ҳ��
			request.setAttribute("pageCount", pageCount);
			// ���÷��ص�������
			request.setAttribute("CMD", cmd);
			// ���÷����û�����
			request.setAttribute("type", type);
			// ���÷���Ⱥ���ѯ��select��optionֵ
			request.setAttribute("ms_id", type);
			// ���÷���Ⱥ���ѯ��select�����е�optionֵ
			request.setAttribute("gpsList", gpsmanager.getAllGps_Info());
			// ���÷����ն˲�ѯ��select�����е�optionֵ
			// request.setAttribute("msList", msmanager.getAllMs_Info());
			request.setAttribute("gpsmsList", gpsmsmanager.getMsid_InfoByPage(
					pageNo, pageSize, type));
			return mapping.findForward("success");
		}

	}

	/**
	 * ����Ⱥ���ն˶�Ӧ��ϵ
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
				//��¼������־
				TbUserLog userLog=new TbUserLog();
				userLog.setUserId(Integer.parseInt(request.getSession().getAttribute("uId").toString()));
				userLog.setlDate(new Date());
				userLog.setlAddress(HRAddress.getIpAddr(request));
				userLog.setlType(12);
				userLog.setlContent("GPS:{ "+gps_id+" }�ն�:{ "+ms_id+" }��Ӷ�Ӧ��ϵ");
				logManager.save(userLog);
			System.out.println("GPS�����ն˶�Ӧ��ϵ��ӳɹ�");
			resultmessage = "��ӳɹ�!";
			request.setAttribute("message", resultmessage);
			return mapping.findForward("showgpsmslist");
		} else {
			System.out.println("GPS�����ն˶�Ӧ��ϵ���ʧ��");
			resultmessage = "GPS�����ն˶�Ӧ��ϵ���Ѿ�����,���ʧ��!";
			request.setAttribute("message", resultmessage);
			return mapping.findForward("showgpsmslist");
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
				//��¼������־
				TbUserLog userLog=new TbUserLog();
				userLog.setUserId(Integer.parseInt(request.getSession().getAttribute("uId").toString()));
				userLog.setlDate(new Date());
				userLog.setlAddress(HRAddress.getIpAddr(request));
				userLog.setlType(13);
				userLog.setlContent("GPS:{ "+mgs[0]+" }�ն�:{ "+mgs[1]+" }ȡ����Ӧ��ϵ");
				logManager.save(userLog);
			}
			System.out.println("ɾ���ɹ�");
			resultmessage = "ɾ���ɹ�!";
			request.setAttribute("message", resultmessage);
			return mapping.findForward("showgpsmslist");
		} else {
			System.out.println("ɾ��ʧ��");
			resultmessage = "ɾ��ʧ��!";
			request.setAttribute("message", resultmessage);
			return mapping.findForward("showgpsmslist");
		}
	}

	/**
	 * ����������ѯ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward gpsms_Sarch(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String pagentid = ""; // һ��������id
		String childagentid = ""; // ����������id
		String ep = ""; // ��ҵid
		String cmd = request.getParameter("CMD");
		String gpsid = request.getParameter("gpsid");
		String msid = request.getParameter("msid");// �ն˺���
		HttpSession session = request.getSession(true);
		TbUserInfo userInfo = (TbUserInfo) session.getAttribute("user");

		if (request.getParameter("pagentid") != null) {
			pagentid = request.getParameter("pagentid");
		} else {
			pagentid = "-1"; // ���������ܲ�
		}

		if (request.getParameter("childagentid") != null) {
			childagentid = request.getParameter("childagentid");
		} else {
			childagentid = "-2"; // ������ҵ
		}

		if (request.getParameter("ep") != null) {
			ep = request.getParameter("ep");
		} else {
			ep = "-1"; // ȫ����ҵ
		}
		if (gpsid == null) { // Ϊȫ��GPS���
			gpsid = "";
		}

		if (msid == null) {// ȫ���ն˺�
			msid = "";
		}
		// ��ȡ������
		int gpsmsCount = 0;
		gpsmsCount = gpsmsmanager.getGpsMs_SearchCount(gpsid, msid, pagentid,
				childagentid, ep);
		// ��ȡÿҳ������
		int pageSize = 12;
		// ��ȡ��ҳ��
		int pageCount = gpsmsmanager.getPageCount(gpsmsCount, pageSize);
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
		request.setAttribute("gpsmsCount", gpsmsCount);
		// ����ÿҳ����ʾ����
		request.setAttribute("pageSize", pageSize);
		// ���ص�ǰҳ
		request.setAttribute("currentPage", pageNo);

		// ���ص���ҳ��
		request.setAttribute("pageCount", pageCount);

		// ���÷��ص�������
		request.setAttribute("CMD", cmd);

		// ���÷���Ⱥ���ѯ��select��optionֵ
		request.setAttribute("msid", msid);
		request.setAttribute("gpsid", gpsid);
		request.setAttribute("pagentid", pagentid);
		request.setAttribute("childagentid", childagentid);
		request.setAttribute("ep", ep);

		// һ������������
		request.setAttribute("Pagentlist", agentmanger.getParentAgent(a_id,r_id));
		// һ��ָ�����������̽����
		request.setAttribute("Cagentlist", agentmanger
				.getChildAgentByParamentid(pagentid));
		// ����������ҵĬ��
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
		// ���÷���Ⱥ����Ϣ
		request.setAttribute("gpsList", gpsmanager.getAllGps_Info());
		// ���÷����ն���Ϣ
		request.setAttribute("msList", msmanager.getAllMs_Info(pagentid,
				childagentid, ep));

		request.setAttribute("gpsmsList", gpsmsmanager.getGpsMs_SearchByPage(
				pageNo, pageSize, gpsid, msid, pagentid, childagentid, ep));
		return mapping.findForward("success");

	}

}