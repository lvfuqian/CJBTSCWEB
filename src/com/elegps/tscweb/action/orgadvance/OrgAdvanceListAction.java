package com.elegps.tscweb.action.orgadvance;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.elegps.tscweb.action.base.BaseAction;
import com.elegps.tscweb.comm.UserInfo;
import com.elegps.tscweb.exception.MessageException;
import com.elegps.tscweb.model.TbAgentInfo;
import com.elegps.tscweb.model.TbEnterpriseInfo;
import com.elegps.tscweb.model.TbORGAdvanceInfo;
import com.elegps.tscweb.model.TbUserInfo;

public class OrgAdvanceListAction extends BaseAction {
	/*
	 * Generated Methods
	 */
	
	//wanglei ��ҵ�û�ִ��
	public static int a_id = 0;//������id
	public static int r_id = 0;//��ɫid
	public static int ep_id = 0;//��ҵid
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
			// ���û�оͽ��յ�����Ĭ����ʾȫ������
			if (request.getParameter("CMD") == null
					|| request.getParameter("CMD").equals("")) {
				cmd = "advance_search";
			} else {
				cmd = request.getParameter("CMD");
			}
			if (cmd.equals("advance_search")) { // Ԥ��ֵ��������ͬʱ��ѯ
				actionforward = advance_Sarch(mapping, form, request, response);
			} else if (cmd.equals("advance_addjsp")) {// �����ҳ��
				actionforward = advance_addjsp(mapping, form, request, response);
			} else if (cmd.equals("advanceadd")) {// ���Ԥ��ֵ
				actionforward = advance_add(mapping, form, request, response);
			} else if (cmd.equals("modifyjsp")) {// �޸�ҳ��
				actionforward = advance_modifyjsp(mapping, form, request,
						response);
			} else if (cmd.equals("modify")) {// �޸�Ԥ��ֵ��Ϣ
				actionforward = advance_modify(mapping, form, request, response);
			} else if (cmd.equals("delete")) {// ɾ��Ԥ��ֵ
				actionforward = advance_delet(mapping, form, request, response);
			} else if (cmd.equals("xinaxi")) // �鿴Ԥ��ֵ��ϸ��Ϣ
			{
				actionforward = advance_xianxi(mapping, form, request, response);
			} else if (cmd.equals("validate_search")) {
				actionforward = validate_search(mapping, form, request,
						response);
			} else if (cmd.equals("validatejsp")) {
				actionforward = validate_jsp(mapping, form, request, response);
			} else if (cmd.equals("validate")) {
				actionforward = validate(mapping, form, request, response);
			} else if (cmd.equals("charge_search")) { // ���ݴ�����,��ҵ������ѯ��ֵ��ʷ��¼��Ϣ
				actionforward = charge_search(mapping, form, request, response);
			}
		} else {
			request.setAttribute("message", "Session���ڣ������µ�¼");
			actionforward = mapping.findForward("logging");
		}
		return actionforward;
	}

	/**
	 * ���ݴ�����,��ҵ������ѯԤ��ֵ��Ϣ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward advance_Sarch(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String pagentid = ""; // һ��������id
		String childagentid = ""; // ����������id
		String ep = ""; // ��ҵid
		String cmd = request.getParameter("CMD");
		String advanceresult = request.getParameter("aresult"); // ��֤���
		HttpSession session = request.getSession(true);
		TbUserInfo userInfo = (TbUserInfo) session.getAttribute("user");
		if (request.getParameter("pagentid") != null) {
			pagentid = request.getParameter("pagentid");
		} else {
			pagentid = "-1"; // ���������ܲ�
		}

		if (request.getParameter("childagentid") != null
				&& !request.getParameter("childagentid").equals("")) {
			childagentid = request.getParameter("childagentid");
		} else {
			childagentid = "-2"; // ������ҵ
		}

		if (request.getParameter("ep") != null
				&& !request.getParameter("ep").equals("")) {
			ep = request.getParameter("ep");
		} else {
			ep = "-1"; // ȫ����ҵ
		}
		if (advanceresult == null) {
			advanceresult = "";
		}
		int advanceCount = 0;
		advanceCount = advancemanger.getAdvance_SearchCount(pagentid,
				childagentid, ep, advanceresult);
		// ��ȡÿҳ������
		int pageSize = 12;
		// ��ȡ��ҳ��
		int pageCount = advancemanger.getPageCount(advanceCount, pageSize);
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
		request.setAttribute("advanceCount", advanceCount);
		// ����ÿҳ����ʾ����
		request.setAttribute("pageSize", pageSize);
		// ���ص�ǰҳ
		request.setAttribute("currentPage", pageNo);
		// ���÷��ص�������
		request.setAttribute("CMD", cmd);

		request.setAttribute("pagentid", pagentid);
		request.setAttribute("childagentid", childagentid);
		request.setAttribute("ep", ep);
		request.setAttribute("aresult", advanceresult);
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

		// ���ص���ҳ��
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("advanceList", advancemanger
				.getAdvance_SearchByPage(pageNo, pageSize, pagentid,
						childagentid, ep, advanceresult));
		return mapping.findForward("succes");
	}

	public ActionForward advance_xianxi(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		// ȡ��advanceidԤ��ֵid
		String advanceid = request.getParameter("advanceid");
		String advancetype = "";
		String advanename = "";
		TbORGAdvanceInfo orgadvance = null;
		orgadvance = advancemanger.getTbORGAdvanceInfo_byadvancetid(advanceid);
		if (orgadvance != null) {
			if (orgadvance.getOrgType() == 1)// ����Ϊ������
			{
				advancetype = "������";
				advanename = agentmanger.getAgent_ByAgentID(
						orgadvance.getOrgId().toString()).getAgent_Name();
			} else {
				advancetype = "��ҵ";
				advanename = epmanger.getEpinfo_byepid(
						orgadvance.getOrgId().toString()).getEp_Name();
			}
			request.setAttribute("advaneceinfo", orgadvance);
			request.setAttribute("advancetype", advancetype);
			request.setAttribute("advanename", advanename);
			return mapping.findForward("xiangxi");
		} else {
			String resultmessage = null;
			resultmessage = "�ü�¼������!";
			request.setAttribute("message", resultmessage);
			return mapping.findForward("showadvancelist");
		}

	}

	/**
	 * ��������Ϣɾ��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws MessageException
	 */
	public ActionForward advance_delet(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MessageException {
		String[] aidlist = request.getParameterValues("list");
		Boolean advamcedelete = advancemanger.deleteAdvance(aidlist);
		String resultmessage = null;
		if (advamcedelete) {
			resultmessage = "ɾ���ɹ�";
			request.setAttribute("message", resultmessage);
			return mapping.findForward("showadvancelist");
		} else {
			resultmessage = "ɾ��ʧ��";
			request.setAttribute("message", resultmessage);
			return mapping.findForward("showadvancelist");
		}
	}

	/**
	 * ���ǰ��׼��ҳ��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward advance_addjsp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MessageException {
		// һ������������
		request.setAttribute("Pagentlist", agentmanger.getParentAgent(a_id,r_id));
		return mapping.findForward("addadvancejsp");
	}

	/**
	 * ���Ԥ��ֵ��Ϣ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws MessageException
	 */
	public ActionForward advance_add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MessageException {
		TbUserInfo user = UserInfo.getUserInfo(request);
		String orgtype = ""; // 1Ϊ������,2Ϊ��ҵ
		String orgid = "";
		if (!request.getParameter("pagentid").equals("")) {
			orgtype = "1";
			orgid = request.getParameter("pagentid");
		}
		if (!request.getParameter("childagentid").equals("")
				&& !request.getParameter("childagentid").equals("-1")) {
			orgtype = "1";
			orgid = request.getParameter("childagentid");
		}
		if (!request.getParameter("ep").equals("")
				&& request.getParameter("ep") != null) {
			orgtype = "2";
			orgid = request.getParameter("ep");
		}
		String advance = request.getParameter("advance");
		String resultmessage = null;
		resultmessage = advancemanger.createAdvance(orgtype, orgid, advance,
				user.getUserName());
		request.setAttribute("message", resultmessage);
		return mapping.findForward("showadvancelist");

	}

	/**
	 * ׼���޸�ʱ��ҳ����Ϣ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws MessageException
	 */
	public ActionForward advance_modifyjsp(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws MessageException {
		String advanceid = request.getParameter("advanceid");
		String advancetype = "";
		String advanename = "";
		TbORGAdvanceInfo orgadvance = null;
		orgadvance = advancemanger.getTbORGAdvanceInfo_byadvancetid(advanceid);
		if (orgadvance != null) {
			if (orgadvance.getOrgType() == 1)// ����Ϊ������
			{
				advancetype = "������";
				advanename = agentmanger.getAgent_ByAgentID(
						orgadvance.getOrgId().toString()).getAgent_Name();
			} else {
				advancetype = "��ҵ";
				advanename = epmanger.getEpinfo_byepid(
						orgadvance.getOrgId().toString()).getEp_Name();
			}
			request.setAttribute("advaneceinfo", orgadvance);
			request.setAttribute("advancetype", advancetype);
			request.setAttribute("advanename", advanename);
			return mapping.findForward("modiadvancejsp");
		} else {
			String resultmessage = null;
			resultmessage = "�ü�¼������!";
			request.setAttribute("message", resultmessage);
			return mapping.findForward("showadvancelist");
		}
	}

	/**
	 * �޸�Ԥ��ֵ��Ϣ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws MessageException
	 */
	public ActionForward advance_modify(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MessageException {
		String advanceid = request.getParameter("advanceid");
		String advance = request.getParameter("advance");
		String modifymess = advancemanger.modifyAdvanece(advanceid, advance);
		request.setAttribute("message", modifymess);
		return mapping.findForward("showadvancelist");

	}

	/**
	 * ���ݴ�����,��ҵ������ѯԤ��ֵ��Ϣ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward validate_search(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		String pagentid = ""; // һ��������id
		String childagentid = ""; // ����������id
		String ep = ""; // ��ҵid
		String cmd = request.getParameter("CMD");
		String advanceresult = request.getParameter("aresult"); // ��֤���
		HttpSession session = request.getSession(true);
		TbUserInfo userInfo = (TbUserInfo) session.getAttribute("user");
		if (request.getParameter("pagentid") != null) {
			pagentid = request.getParameter("pagentid");
		} else {
			pagentid = "-1"; // ���������ܲ�
		}

		if (request.getParameter("childagentid") != null
				&& !request.getParameter("childagentid").equals("")) {
			childagentid = request.getParameter("childagentid");
		} else {
			childagentid = "-2"; // ������ҵ
		}

		if (request.getParameter("ep") != null
				&& !request.getParameter("ep").equals("")) {
			ep = request.getParameter("ep");
		} else {
			ep = "-1"; // ȫ����ҵ
		}
		if (advanceresult == null) {
			advanceresult = "";
		}
		int advanceCount = 0;
		advanceCount = advancemanger.getAdvance_SearchCount(pagentid,
				childagentid, ep, advanceresult);
		// ��ȡÿҳ������
		int pageSize = 12;
		// ��ȡ��ҳ��
		int pageCount = advancemanger.getPageCount(advanceCount, pageSize);
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
		request.setAttribute("advanceCount", advanceCount);
		// ����ÿҳ����ʾ����
		request.setAttribute("pageSize", pageSize);
		// ���ص�ǰҳ
		request.setAttribute("currentPage", pageNo);
		// ���÷��ص�������
		request.setAttribute("CMD", cmd);

		request.setAttribute("pagentid", pagentid);
		request.setAttribute("childagentid", childagentid);
		request.setAttribute("ep", ep);
		request.setAttribute("aresult", advanceresult);
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

		// ���ص���ҳ��
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("advanceList", advancemanger
				.getAdvance_SearchByPage(pageNo, pageSize, pagentid,
						childagentid, ep, advanceresult));
		return mapping.findForward("valisucces");
	}

	/**
	 * ��֤ʱ��ҳ����Ϣ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws MessageException
	 */
	public ActionForward validate_jsp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MessageException {
		String advanceid = request.getParameter("advanceid");
		String advancetype = "";
		String advanename = "";
		TbORGAdvanceInfo orgadvance = null;
		orgadvance = advancemanger.getTbORGAdvanceInfo_byadvancetid(advanceid);
		if (orgadvance != null) {
			if (orgadvance.getOrgType() == 1)// ����Ϊ������
			{
				advancetype = "������";
				advanename = agentmanger.getAgent_ByAgentID(
						orgadvance.getOrgId().toString()).getAgent_Name();
			} else {
				advancetype = "��ҵ";
				advanename = epmanger.getEpinfo_byepid(
						orgadvance.getOrgId().toString()).getEp_Name();
			}
			request.setAttribute("advaneceinfo", orgadvance);
			request.setAttribute("advancetype", advancetype);
			request.setAttribute("advanename", advanename);
			return mapping.findForward("validatejsp");
		} else {
			String resultmessage = null;
			resultmessage = "�ü�¼������!";
			request.setAttribute("message", resultmessage);
			return mapping.findForward("valisucces");
		}
	}

	/**
	 * ��֤
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws MessageException
	 */
	public ActionForward validate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MessageException {
		TbUserInfo user = UserInfo.getUserInfo(request);
		String advanceid = request.getParameter("advanceid");
		String aresult = request.getParameter("aresult");
		String remark = request.getParameter("remark");
		String validatemess = advancemanger.validate(advanceid, aresult,
				remark, user.getUserName());
		request.setAttribute("message", validatemess);
		return mapping.findForward("valisucces");

	}

	/**
	 * ���ݴ�����,��ҵ������ѯ��ֵ��ʷ��¼��Ϣ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward charge_search(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String pagentid = ""; // һ��������id
		String childagentid = ""; // ����������id
		String ep = ""; // ��ҵid
		String cmd = request.getParameter("CMD");
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
		int chargeCount = 0;
		chargeCount = advancemanger.getCharge_SearchCount(pagentid,
				childagentid, ep);
		// ��ȡÿҳ������
		int pageSize = 12;
		// ��ȡ��ҳ��
		int pageCount = advancemanger.getPageCount(chargeCount, pageSize);
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
		request.setAttribute("chargeCount", chargeCount);
		// ����ÿҳ����ʾ����
		request.setAttribute("pageSize", pageSize);
		// ���ص�ǰҳ
		request.setAttribute("currentPage", pageNo);
		// ���÷��ص�������
		request.setAttribute("CMD", cmd);

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
		// ���ص���ҳ��
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("chargeList", advancemanger
				.getCharge_SearchByPage(pageNo, pageSize, pagentid,
						childagentid, ep));
		return mapping.findForward("chargesucces");
	}

}