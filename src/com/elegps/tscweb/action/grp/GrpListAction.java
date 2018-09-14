package com.elegps.tscweb.action.grp;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.elegps.tscweb.action.base.BaseAction;
import com.elegps.tscweb.comm.GrpIdRule;
import com.elegps.tscweb.comm.HRAddress;
import com.elegps.tscweb.comm.UserInfo;
import com.elegps.tscweb.exception.MessageException;
import com.elegps.tscweb.form.AddgrpForm;

import com.elegps.tscweb.model.TabBaseGrpextinfo;
import com.elegps.tscweb.model.TbAgentInfo;
import com.elegps.tscweb.model.TbEnterpriseInfo;
import com.elegps.tscweb.model.TbGrpInfo;
import com.elegps.tscweb.model.TbMsInfo;
import com.elegps.tscweb.model.TbUserInfo;
import com.elegps.tscweb.model.TbUserLog;
import com.elegps.tscweb.tscconfig.FilePathConfig;

/**
 * MyEclipse Struts Creation date: 10-20-2008
 * 
 * XDoclet definition:
 * 
 * @struts.action validate="true"
 */
public class GrpListAction extends BaseAction {
	/*
	 * Generated Methods
	 */
	//wanglei ��ҵ�û�ִ��
	public static int agent_id = 0;//������id
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
			agent_id =Integer.parseInt(request.getSession().getAttribute("agentId")+"");//������id
			r_id =Integer.parseInt(request.getSession().getAttribute("roleId")+"");//��ɫid
			ep_id =Integer.parseInt(request.getSession().getAttribute("epId")+"");//��ɫid
			
			// ���û�оͽ��յ�����Ĭ����ʾȫ������
			if (request.getParameter("CMD") == null
					|| request.getParameter("CMD").equals("")) {
				cmd = "grp_search";
			} else {
				cmd = request.getParameter("CMD");
			}

			if (cmd.equals("add")) {// ����Ⱥ����Ϣ
				actionforward = grp_add(mapping, form, request, response);
			} else if (cmd.equals("xinaxi")) // �鿴Ⱥ����ϸ��Ϣ
			{
				actionforward = grp_xianxi(mapping, form, request, response);
			} else if (cmd.equals("grp_type_list")) { // ����Ⱥ�����Ͳ�ѯȺ����Ϣ
				actionforward = grp_typesearch(mapping, form, request, response);
			} else if (cmd.equals("grp_Status")) { // ����Ⱥ��ͨ��״̬��ѯȺ����Ϣ(1ͨ���С�0δ��ͨ���У�
				actionforward = grp_Statussearch(mapping, form, request,
						response);
			} else if (cmd.equals("delete")) { // ɾ��ѡ��Ⱥ����Ϣ(�߼�ɾ��flag��Ϊ0)
				actionforward = grp_delete(mapping, form, request, response);
			} else if (cmd.equals("grp_idsearch")) { // Ⱥ���ģ����ѯ
				actionforward = grp_idSarch(mapping, form, request, response);
			} else if (cmd.equals("grp_flag")) { // Ⱥ��״̬��ѯ��0ʧЧ��1������
				actionforward = grp_flagSarch(mapping, form, request, response);
			} else if (cmd.equals("togrpmdoyijsp")) { // ��Ҫ�޸ĵ���Ϣ�����޸ĵ�jspҳ��
				actionforward = grp_modytojsp(mapping, form, request, response);
			} else if (cmd.equals("grpmodify")) { // �޸��ն��û���Ϣ
				actionforward = grp_modify(mapping, form, request, response);
			} else if (cmd.equals("grp_search")) { // ��������ͬʱ��ѯ			
				actionforward = grp_Sarch(mapping, form, request, response);
			} else if (cmd.equals("grp_addjsp")) {// ת������ҳ��
				actionforward = grp_Addjsp(mapping, form, request, response);
			} else if (cmd.equals("basegrp_search")) {// ת������ҳ��
				actionforward = baseGrp_Search(mapping, form, request, response);
			} else if (cmd.equals("basegrp_delete")) {// ת������ҳ��
				actionforward = baseGrp_delete(mapping, form, request, response);
			} else if (cmd.equals("base_grp_jsp")) {// ת������ҳ��
				actionforward = base_Grp_jsp(mapping, form, request, response);
			} else if (cmd.equals("base_grp_add")) {// ת������ҳ��
				actionforward = base_Grp_add(mapping, form, request, response);
			} else if (cmd.equals("base_grp_updatejsp")) {// ת������ҳ��
				actionforward = base_Grp_modytojsp(mapping, form, request, response);
			} else if (cmd.equals("base_grp_udpate")) {// ת������ҳ��
				actionforward = base_Grp_udpate(mapping, form, request, response);
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
	 * ��ʾȺ����ϸ��Ϣ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward grp_xianxi(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		// ȡ�õ�ǰ��¼��grpid
		String grpid = request.getParameter("grpid");
		TbGrpInfo tbgrpin = null;
		tbgrpin = grpmanager.getGrpinfoby_grpid(grpid);
		TbEnterpriseInfo epname = grpmanager.getEpBygrpid(grpid);

		String resultmessage = null;
		if (tbgrpin != null) {
			request.setAttribute("epname", grpmanager.getEpBygrpid(grpid));// Ⱥ�������û���λ����
			request.setAttribute("tbgrpinfo", tbgrpin);
			return mapping.findForward("xiangxi");
		} else { // ˵��û���ҵ�.
			System.out.println("Ⱥ����ϸ��Ϣû���ҵ�");
			resultmessage = "�ü�¼������!";
			request.setAttribute("message", resultmessage);
			return mapping.findForward("showgrplist");
		}
	}

	/**
	 * ����Ⱥ�����jsp��Ϣ
	 * 
	 */
	public ActionForward grp_Addjsp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MessageException {		
		String pagentid = ""; // һ��������id
		String childagentid = ""; // ����������id

		if (request.getParameter("pagentid") != null) {
			pagentid = request.getParameter("pagentid");
		} else {
			if(r_id == 2 || r_id ==3 || r_id ==4){
				pagentid = agent_id+"";
			}else{
				pagentid = "-1"; // ���������ܲ�
			}
		}

		if (request.getParameter("childagentid") != null) {
			childagentid = request.getParameter("childagentid");
		} else {
			childagentid = "-1"; // ֱ����ҵ
		}

		String ep = request.getParameter("ep");
		if (ep == null || ep.equals("null")) {
			if(r_id == 4 || r_id == 3){
				ep = ep_id+"";
			}else{
				ep = "-1";
			}
		}
		// һ������������
		int apid = Integer.parseInt(request.getSession().getAttribute("aPId")+"");
		if(apid == 0){
			request.setAttribute("Pagentlist", agentmanger.getParentAgent(agent_id,r_id));
		//}
		// һ��ָ�����������̽����
			if(r_id ==3 || r_id ==4){
				request.setAttribute("Cagentlist",null);
			}else{
				request.setAttribute("Cagentlist", agentmanger.getChildAgentByParamentid(pagentid));
			}
		}else{
			int a = agentmanger.getAgent_ByAgentID(apid+"").getAgent_Id();
			request.setAttribute("Pagentlist", agentmanger.getParentAgent(a,r_id));
			request.setAttribute("Cagentlist", agentmanger.getChildAgentByAId(agent_id+""));
		}
//		request.setAttribute("Pagentlist", agentmanger.getParentAgent(agent_id,r_id));
//		// һ��ָ�����������̽����
//		request.setAttribute("Cagentlist", agentmanger
//				.getChildAgentByParamentid(pagentid));
		// ����������ҵĬ��
		List<TbEnterpriseInfo> list = null;
		if (r_id == 3 || r_id == 4){
			list = epmanger.getEpinfo_byeid(pagentid,
					childagentid, ep_id, r_id);
		}else{
			list = epmanger.getEpinfo_byagentid(pagentid,
					childagentid, agent_id, r_id);
		}
		request.setAttribute("epList", list);
//		request.setAttribute("epList", epmanger.getEpinfo_byagentid(pagentid,
//				childagentid, ep_id, r_id));
		if (ep != "-1") {
			request.setAttribute("msList", msmanager.getAllMs_Info(pagentid,
					childagentid, ep));
		}
		request.setAttribute("pagentid", pagentid);
		request.setAttribute("childagentid", childagentid);
		request.setAttribute("epid", ep);
		//request.setAttribute("grpid", FilePathConfig.getGRPId());
		return mapping.findForward("toaddjsp");

	}

	/**
	 * Ⱥ����Ϣ���
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */

	public ActionForward grp_add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		AddgrpForm addgrpform = (AddgrpForm) form;
		
		String grp_name = addgrpform.getGrp_name();
		String ms_id = addgrpform.getMs_id();
		String ep = addgrpform.getEp();
		String C03 = request.getParameter("c03");

		int talksc;
		try {
			talksc = Integer.parseInt(addgrpform.getTalkinglast().trim());
		} catch (Exception e) {
			talksc = 3;
		}

		String pagentid = ""; // һ��������id
		String childagentid = ""; // ����������id
		String grp_pid="-1";   //�ϼ�Ⱥ�飬-1����һ��Ⱥ��
		if (request.getParameter("pagentid") != null) {
			pagentid = request.getParameter("pagentid");
		} else {
			pagentid = "-1"; // ���������ܲ�
		}
		
		String grp_id = GrpIdRule.grpIdRule(pagentid, ep);//�Զ�����21λȺ��Id
		
		if (request.getParameter("childagentid") != null) {
			childagentid = request.getParameter("childagentid");
		} else {
			childagentid = "-1"; // ֱ����ҵ
		}
		
		if (request.getParameter("grp_pid") != null) {
			grp_pid = request.getParameter("grp_pid");
		} 
		int grp_type = Integer.parseInt(addgrpform.getGrp_type());
		// Ⱥ���״̬flag 0 ʧЧ 1 ���� Ĭ��1
		int flag = 1;
		int grp_lf = 1;
		String resultmessage = null;
		String grpcreate = grpmanager.createGrp(grp_id, grp_name, ms_id,
				grp_type, flag, talksc, ep, grp_lf,grp_pid,C03);
		//��¼������־
		TbUserLog userLog=new TbUserLog();
		userLog.setUserId(Integer.parseInt(request.getSession().getAttribute("uId").toString()));
		userLog.setlDate(new Date());
		userLog.setlAddress(HRAddress.getIpAddr(request));
		userLog.setlType(4);
		userLog.setlContent("���Ⱥ��{"+grp_id+"}");
		logManager.save(userLog);
		if (grpcreate.equals("succeed")) {
			resultmessage = "��ӳɹ�!";
		} else if (grpcreate.equals("exist")) {
			resultmessage = "��Ⱥ������Ѿ�����!���ʧ��!";
		} else {
			resultmessage = "���ʧ��!";
		}
		request.setAttribute("message", resultmessage);
		// request.setAttribute("pagentid", pagentid);
		// request.setAttribute("childagentid", childagentid);
		// request.setAttribute("ep", ep);
		return mapping.findForward("showgrplist");

	}

	/**
	 * �޸�Ⱥ����Ϣ
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
		String ep_id = request.getParameter("ep");
		String grp_id = request.getParameter("grp_id");
		String grp_name = request.getParameter("grp_name");
		int grp_type = Integer.parseInt(request.getParameter("grp_type"));
		int grp_flag = Integer.parseInt(request.getParameter("grp_flag"));
		int grp_lf = 1;//Integer.parseInt(request.getParameter("grp_lf"));
		String C03 = request.getParameter("c03");
		int grp_talksc;
		try {
			grp_talksc = Integer
					.parseInt(request.getParameter("talksc").trim());
		} catch (Exception e) {
			grp_talksc = 3;
		}
		String modifyok = grpmanager.modifyGrp(grp_id, ep_id, grp_name,
				grp_type, grp_flag, grp_talksc, grp_lf,C03);
		String resultmessage = null;
		//��¼������־
		TbUserLog userLog=new TbUserLog();
		userLog.setUserId(Integer.parseInt(request.getSession().getAttribute("uId").toString()));
		userLog.setlDate(new Date());
		userLog.setlAddress(HRAddress.getIpAddr(request));
		userLog.setlType(5);
		userLog.setlContent("�޸�Ⱥ��{"+grp_id+"}");
		logManager.save(userLog);
		if (modifyok != null) {
			resultmessage = "�޸ĳɹ�!";
			request.setAttribute("message", resultmessage);
		} else {
			resultmessage = "�޸�ʧ��!";
			request.setAttribute("message", resultmessage);
		}
		String type=request.getParameter("type");
		if(type!=null&&!type.equals("null")){
			return mapping.findForward("kfshowgrplist");
		}else{
			return mapping.findForward("showgrplist");
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
	public ActionForward grp_modytojsp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MessageException {
		// ȡ��grpid
		String grpid = request.getParameter("grpid");
		TbGrpInfo grpinfo = null;
		TbEnterpriseInfo epinfo = null;
		String type=request.getParameter("type");
		if(type!=null){
	    	request.setAttribute("type",type );
	    }
		grpinfo = grpmanager.getGrpinfoby_grpid(grpid);
		if (grpinfo != null) {
			epinfo = epmanger.getEpinfo_byepid(grpinfo.getEp_Id().toString());
		}

		String resultmessage = null;
		
		if (grpinfo != null) {
			request.setAttribute("tbgrpinfo", grpinfo);
			if (epinfo != null) {
				int agenttype = agentmanger.getAgenttype(String.valueOf(epinfo.getAgent_Id()));
				
				
				
				
				if (agenttype != 0) { // �����������µ���ҵ
					// һ������������
					request.setAttribute("Pagentlist", agentmanger.getParentAgent(agenttype,r_id));
					request.setAttribute("Cagentlist", agentmanger.getChildAgentByAId(epinfo.getAgent_Id()+""));
					request.setAttribute("pagentid", agenttype);
					request.setAttribute("childagentid", epinfo.getAgent_Id());
					request.setAttribute("ep", epinfo.getEp_Id());
					request.setAttribute("epList", epmanger
							.getEpinfo_byeid(String.valueOf(agenttype),
									String.valueOf(epinfo.getAgent_Id()), ep_id, r_id));
				} else { // һ��������ֱ����ҵ
					// һ��ָ�����������̽����
					// һ������������
					request.setAttribute("Pagentlist", agentmanger.getParentAgent(agent_id,r_id));
					if(r_id ==3 || r_id ==4){
						request.setAttribute("Cagentlist",null);
					}else{
						request.setAttribute("Cagentlist", agentmanger
								.getChildAgentByParamentid(String.valueOf(epinfo
										.getAgent_Id())));
					}
					request.setAttribute("pagentid", epinfo.getAgent_Id());
					request.setAttribute("childagentid", "-1");
					request.setAttribute("ep", epinfo.getEp_Id());
					request.setAttribute("epList", epmanger
							.getEpinfo_byeid(String.valueOf(epinfo
									.getAgent_Id()), String.valueOf(-1), ep_id, r_id));
				}

			}
			return mapping.findForward("grpmdify");
		} else { // ˵��û���ҵ�.

			resultmessage = "Ҫ�޸ĵļ�¼�����ڣ�";
			request.setAttribute("message", resultmessage);
			return mapping.findForward("showgrplist");
		}

	}

	public ActionForward grp_flagSarch(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MessageException {

		String cmd = request.getParameter("CMD");
		int type = 0; // 2ȫ������ 1���� 0ʧЧ

		// ���ms_typeȡֵΪ�ջ�Ϊnull,����ʾȫ�����͵���Ϣ
		if (request.getParameter("type") == null
				|| request.getParameter("type").equals("")) {
			type = 2;
		} else {
			type = Integer.parseInt(request.getParameter("type"));
		}

		if (type == 2) { // ȫ������
			// ��ȡ������
			int grpCount = 0;
			try {
				grpCount = grpmanager.getAllGrp_flagCount();
			} catch (MessageException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			// ��ȡÿҳ������
			int pageSize = 12;
			// ��ȡ��ҳ��
			int pageCount = grpmanager.getPageCount(grpCount, pageSize);
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
			request.setAttribute("grpCount", grpCount);
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

			// �����÷����û���ѯ��select��optionֵ
			request.setAttribute("grpflag", type);

			request.setAttribute("grpList", grpmanager.getAllGrp_flagByPage(
					pageNo, pageSize));
			return mapping.findForward("succes");

		} else {
			// ��ȡ������
			int grpCount = 0;
			grpCount = grpmanager.getGrp_flagCount(type);
			// ��ȡÿҳ������
			int pageSize = 12;
			// ��ȡ��ҳ��
			int pageCount = grpmanager.getPageCount(grpCount, pageSize);
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
			request.setAttribute("grpCount", grpCount);
			// ����ÿҳ����ʾ����
			request.setAttribute("pageSize", pageSize);
			// ���ص�ǰҳ
			request.setAttribute("currentPage", pageNo);
			// ���÷��ص�������
			request.setAttribute("CMD", cmd);
			// ���÷����û�����
			request.setAttribute("type", type);
			// �����÷����û���ѯ��select��optionֵ
			request.setAttribute("grpflag", type);
			// ���ص���ҳ��
			request.setAttribute("pageCount", pageCount);
			request.setAttribute("grpList", grpmanager.getGrp_flagByPage(
					pageNo, pageSize, type));
			return mapping.findForward("succes");
		}

	}

	public ActionForward grp_typesearch(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MessageException {
		String cmd = request.getParameter("CMD");
		int type = 00; // 2ȫ������ 00��ͨȺ�� 01����Ⱥ�� 10�µ���Ⱥ�� 11δ����

		// ���ms_typeȡֵΪ�ջ�Ϊnull,����ʾȫ�����͵���Ϣ
		if (request.getParameter("type") == null
				|| request.getParameter("type").equals("")) {
			type = 2;
		} else {
			type = Integer.parseInt(request.getParameter("type"));
		}

		if (type == 2) { // ȫ������
			// ��ȡ������
			int grpCount = 0;
			grpCount = grpmanager.getAllgrp_typeCount();
			// ��ȡÿҳ������
			int pageSize = 12;
			// ��ȡ��ҳ��
			int pageCount = grpmanager.getPageCount(grpCount, pageSize);
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
			request.setAttribute("grpCount", grpCount);
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

			// �����÷����û���ѯ��select��optionֵ
			request.setAttribute("grptype", type);
			request.setAttribute("grpList", grpmanager.getAllGrp_typeByPage(
					pageNo, pageSize));
			return mapping.findForward("succes");

		} else {
			// ��ȡ������
			int grpCount = 0;
			grpCount = grpmanager.getGrp_typeCount(type);
			// ��ȡÿҳ������
			int pageSize = 12;
			// ��ȡ��ҳ��
			int pageCount = grpmanager.getPageCount(grpCount, pageSize);
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
			request.setAttribute("grpCount", grpCount);
			// ����ÿҳ����ʾ����
			request.setAttribute("pageSize", pageSize);
			// ���ص�ǰҳ
			request.setAttribute("currentPage", pageNo);
			// ���÷��ص�������
			request.setAttribute("CMD", cmd);
			// ���÷����û�����
			request.setAttribute("type", type);

			// �����÷����û���ѯ��select��optionֵ
			request.setAttribute("grptype", type);

			// ���ص���ҳ��
			request.setAttribute("pageCount", pageCount);
			request.setAttribute("grpList", grpmanager.getGrp_typeByPage(
					pageNo, pageSize, type));
			return mapping.findForward("succes");
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
	public ActionForward grp_idSarch(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String cmd = request.getParameter("CMD");
		String type = null; // 2ȫ��״̬ 0���� 1����

		// ���ms_typeȡֵΪ�ջ�Ϊnull,����ʾȫ�����͵���Ϣ
		if (request.getParameter("type") == null
				|| request.getParameter("type").equals("")) {
			return mapping.findForward("succes");
		} else {
			type = (String) request.getParameter("type");
			// ��ȡ������
			int grpCount = 0;
			grpCount = grpmanager.getGrp_idCount(type);
			// ��ȡÿҳ������
			int pageSize = 12;
			// ��ȡ��ҳ��
			int pageCount = grpmanager.getPageCount(grpCount, pageSize);
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
			request.setAttribute("grpCount", grpCount);
			// ����ÿҳ����ʾ����
			request.setAttribute("pageSize", pageSize);
			// ���ص�ǰҳ
			request.setAttribute("currentPage", pageNo);

			// ���ص���ҳ��
			request.setAttribute("pageCount", pageCount);

			// ����ҳ���ѯ�ؼ���
			request.setAttribute("grpidSear", type);

			// ���÷��ص�������
			request.setAttribute("CMD", cmd);
			// ���÷����û�����
			request.setAttribute("type", type);

			request.setAttribute("grpList", grpmanager.getTbGrpinfoby_grpid(
					pageNo, pageSize, type));
			return mapping.findForward("succes");
		}

	}

	// ����
	public ActionForward grp_Statussearch(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws MessageException {
		String cmd = request.getParameter("CMD");
		int type = 0; // 2ȫ��״̬ 0δ��ͨ���� 1ͨ����
		if (request.getParameter("type") == null
				|| request.getParameter("type").equals("")) {
			type = 2;
		} else {
			type = Integer.parseInt(request.getParameter("type"));
		}

		if (type == 2) { // ȫ��״̬
			// ��ȡ������
			int grpCount = 0;
			grpCount = grpmanager.getAllGrpStatus_allCount();
			// ��ȡÿҳ������
			int pageSize = 12;
			// ��ȡ��ҳ��
			int pageCount = grpmanager.getPageCount(grpCount, pageSize);
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
			request.setAttribute("grpCount", grpCount);
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

			// �����÷����û���ѯ��select��optionֵ
			request.setAttribute("grpstatue", type);

			request.setAttribute("grpList", grpmanager
					.getAllGrpStatus_allByPage(pageNo, pageSize));
			return mapping.findForward("succes");

		} else {
			// ��ȡ������
			int grpCount = 0;
			grpCount = grpmanager.getGrp_StauteCount(type);
			// ��ȡÿҳ������
			int pageSize = 12;
			// ��ȡ��ҳ��
			int pageCount = grpmanager.getPageCount(grpCount, pageSize);
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
			request.setAttribute("grpCount", grpCount);
			// ����ÿҳ����ʾ����
			request.setAttribute("pageSize", pageSize);
			// ���ص�ǰҳ
			request.setAttribute("currentPage", pageNo);
			// ���÷��ص�������
			request.setAttribute("CMD", cmd);
			// ���÷����û�����
			request.setAttribute("type", type);
			// �����÷����û���ѯ��select��optionֵ
			request.setAttribute("grpstatue", type);
			// ���ص���ҳ��
			request.setAttribute("pageCount", pageCount);
			request.setAttribute("grpList", grpmanager.getGrp_StatusByPage(
					pageNo, pageSize, type));
			return mapping.findForward("succes");
		}

	}

	/**
	 * ��������ɾ��,�а�falg�ֶδ�1���0
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward grp_delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String grpid = request.getParameter("list");
		Boolean delgrp =grpmanager.deleteGrp(grpid);
		//��¼������־
		TbUserLog userLog=new TbUserLog();
		userLog.setUserId(Integer.parseInt(request.getSession().getAttribute("uId").toString()));
		userLog.setlDate(new Date());
		userLog.setlAddress(HRAddress.getIpAddr(request));
		userLog.setlType(6);
		userLog.setlContent("ɾ��Ⱥ��{"+grpid+"}");
		logManager.save(userLog);
		String resultmessage = null;
		if (delgrp) {
			resultmessage = "ɾ���ɹ�";
			request.setAttribute("message", resultmessage);
			return mapping.findForward("showgrplist");
		} else {
			resultmessage = "ɾ��ʧ��";
			request.setAttribute("message", resultmessage);
			return mapping.findForward("showgrplist");
		}
	}

	/**
	 * ����grp�б���Ϣ�Ĳ�ѯ������ѯ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward grp_Sarch(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String pagentid = ""; // һ��������id
		String childagentid = ""; // ����������id
		String ep = ""; // ��ҵid
		String cmd = request.getParameter("CMD");
//		HttpSession session = request.getSession(true);
		String type=request.getParameter("type");
		
		String agent = request.getSession().getAttribute("agentId")+"";
		
		if (request.getParameter("pagentid") != null && (agent.equals("-1") || agent.equals("0"))) {
			pagentid = request.getParameter("pagentid");
		} else {
			//pagentid = "-1"; // ���������ܲ�
			pagentid = agent;
        	if(pagentid.equals("0"))
        		pagentid = "-1";
		}

		if (request.getParameter("childagentid") != null) {
			childagentid = request.getParameter("childagentid");
		} else {
			childagentid = "-2"; // ������ҵ
		}

		if (request.getParameter("ep") != null) {
			ep = request.getParameter("ep");
		} else {
			if(r_id == 4 || r_id == 3){
				ep =ep_id+"";
			}else{
				ep = "-1"; // ȫ����ҵ
			}
			
		}

		String grp_type = request.getParameter("grptype");// Ⱥ������ȫ��Ϊ2
		if (grp_type == null) {
			grp_type = "99";
		}
		String statue = request.getParameter("statue");
		if (statue == null) { // Ⱥ��ͨ��״̬ȫ��Ϊ2
			statue = "2";
		}
		String flag = request.getParameter("flag");
		if (flag == null) { // Ⱥ����Ч״̬ȫ��Ϊ2
			flag = "1";
		}
		String grp_id = request.getParameter("grpid"); // Ⱥ��id
		if (grp_id == null) {
			grp_id = "";
		}
		String grp_name = request.getParameter("grpname"); // Ⱥ����
		if (grp_name == null) {
			grp_name = "";
		}

		int grpCount = 0;
		grpCount = grpmanager.getGrp_sertch(grp_type, statue, flag, grp_id
				.trim(), grp_name.trim(), pagentid, childagentid, ep);
		// ��ȡÿҳ������
		String ps =request.getParameter("pageSize");
		int pageSize = 10;
		if(ps!= null){
			pageSize =Integer.parseInt(ps);
		}
		
		// ��ȡ��ҳ��
		int pageCount = grpmanager.getPageCount(grpCount, pageSize);
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
		request.setAttribute("grpCount", grpCount);
		// ����ÿҳ����ʾ����
		request.setAttribute("pageSize", pageSize);
		// ���ص�ǰҳ
		request.setAttribute("currentPage", pageNo);
		// ���÷��ص�������
		request.setAttribute("CMD", cmd);
		// ���÷����û�����

		// �����÷����û���ѯ��select��optionֵ
		request.setAttribute("grp_type", grp_type);
		request.setAttribute("grpstatue", statue);
		request.setAttribute("grpflag", flag);
		request.setAttribute("grp_id", grp_id);
		request.setAttribute("grp_name", grp_name);
		request.setAttribute("pagentid", pagentid);
		request.setAttribute("childagentid", childagentid);
		request.setAttribute("ep", ep);
//		if(!pagentid.equals("-1")){
//			// һ������������
//			request.setAttribute("Pagentlist", agentmanger.getParentAgentNotIncludeZB());
//		}else{
			// һ������������
		int apid = Integer.parseInt(request.getSession().getAttribute("aPId")+"");
		if(apid == 0){
			request.setAttribute("Pagentlist", agentmanger.getParentAgent(agent_id,r_id));
		//}
		// һ��ָ�����������̽����
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
//			request.setAttribute("Pagentlist", agentmanger.getParentAgent(agent_id,r_id));
////		}
//		// һ��ָ�����������̽����
//		request.setAttribute("Cagentlist", agentmanger
//				.getChildAgentByParamentid(pagentid));
		// ����������ҵĬ��
		request.setAttribute("epList", epmanger.getEpinfo_byeid(pagentid,
				childagentid, ep_id, r_id));
		// ���ص���ҳ��
		
		List<TbGrpInfo> grpList = grpmanager.getTBGrpinfoby_grppage(
				pageNo, pageSize, grp_type, statue, flag, grp_id.trim(),
				grp_name.trim(), pagentid, childagentid, ep);

		List<Map> lm = null;
		if(grpList != null){
			lm = new ArrayList<Map>();
			for (int i = 0; i < grpList.size(); i++) {
				int lineCount = 0;
				int grpMsCount = grpmsmanager.grpMsCount(grpList.get(i).getGrpid());
				if(grpMsCount !=0 ){
					lineCount = grpmsmanager.grpOnLineMsCount(grpList.get(i).getGrpid());
				}
				String pid = grpList.get(i).getGrppid();
				String pname = "";
				if(pid.equals("-1") || pid.equals("") || pid.equals(null)){
					pname = "";
				}else{
					pname = grpmanager.getGrpinfoby_grpid(pid).getGrpname();
				}
				Map m = new HashMap();
				m.put("grp", grpList.get(i));
				m.put("lineCount", lineCount);
				m.put("grpMsCount", grpMsCount);
				m.put("grppname", pname);//����Ⱥ��
				lm.add(m);
			}
		}
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("grpList", lm);
		if(type!=null&&!type.equals("null")){
			return mapping.findForward("kfsucces");
		}else{
			return mapping.findForward("succes");
		}
	}
	
	/**
	 * ɾ��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward baseGrp_delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String [] grpid = request.getParameterValues("list");
		try{
			if(grpid!=null&&grpid.length>0){
				for (int i = 0; i < grpid.length; i++) {
					grpmanager.delete(String.valueOf(grpid[i]).trim());
				}
			}
			return mapping.findForward("basesucces");
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * ����grp�б���Ϣ�Ĳ�ѯ������ѯ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward baseGrp_Search(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String cmd = request.getParameter("CMD");
		String type=request.getParameter("type");
		String grpname=request.getParameter("grpname");
		String grpId=request.getParameter("grpid");
		TabBaseGrpextinfo baseGrp=new TabBaseGrpextinfo();
		if(type!=null&&!"".equals(type)){
			baseGrp.setType(Integer.valueOf(type.trim()));
		}
		if(grpname!=null&&!"".equals(grpname)){
			baseGrp.setCompanyName(grpname.trim());
		}
		if(grpId!=null&&!"".equals(grpId)){
			baseGrp.setGrpId(grpId.trim());
		}
		int grpCount = 0;
		grpCount = grpmanager.totalCount(baseGrp);
		// ��ȡÿҳ������
		int pageSize = 12;
		// ��ȡ��ҳ��
		int pageCount = (grpCount + pageSize - 1) / pageSize;
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
		List<TabBaseGrpextinfo> baseGrpList=grpmanager.listAll(pageNo, pageSize, baseGrp);
		// ��ȡ������
		request.setAttribute("grpCount", grpCount);
		// ����ÿҳ����ʾ����
		request.setAttribute("pageSize", pageSize);
		// ���ص�ǰҳ
		request.setAttribute("currentPage", pageNo);
		// ���÷��ص�������
		request.setAttribute("CMD", cmd);
		// ���÷����û�����

		// һ������������
		request.setAttribute("type",type);
		request.setAttribute("grpname",grpname);
		request.setAttribute("grpid",grpId);

		// ���ص���ҳ��
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("baseGrp", baseGrpList);
		
			return mapping.findForward("basesearche");
	}
	
	/**
	 * ����Ⱥ�����jsp��Ϣ
	 * 
	 */
	public ActionForward base_Grp_jsp(ActionMapping mapping, ActionForm form,
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
		request.setAttribute("Pagentlist", agentmanger.getParentAgent(agent_id,r_id));
		// һ��ָ�����������̽����
		request.setAttribute("Cagentlist", agentmanger.getChildAgentByParamentid(pagentid));
		// ����������ҵĬ��
		request.setAttribute("epList", epmanger.getEpinfo_byeid(pagentid,childagentid, ep_id, r_id));
		request.setAttribute("pagentid", pagentid);
		request.setAttribute("childagentid", childagentid);
		request.setAttribute("epid", ep);
		return mapping.findForward("baseaddjsp");

	}
	
	/**
	 * Ⱥ����Ϣ���
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */

	public ActionForward base_Grp_add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		TabBaseGrpextinfo baseGrp=new TabBaseGrpextinfo();
		String grp_id = request.getParameter("grp_id");
		String ep = request.getParameter("ep");
		String lg=request.getParameter("lg");
		String lt=request.getParameter("lt");
		String grptype=request.getParameter("grptype");
		String radius=request.getParameter("radius");
		String overSpdSmsTip=request.getParameter("overSpdSmsTip");
		if(grp_id!=null&&!"".equals(grp_id)){
			String [] str=grp_id.split("-");
			baseGrp.setGrpId(str[0]);
			baseGrp.setCompanyName(str[1]);
		}
		if(ep!=null&&!"".equals(ep))
			baseGrp.setEnterpriseId(new Integer(ep));
		if(lg!=null&&!"".equals(lg))
			baseGrp.setLg(Double.valueOf(lg));
		if(lt!=null&&!"".equals(lt))
			baseGrp.setLt(Double.valueOf(lt));
		if(grptype!=null&&!"".equals(grptype))
			baseGrp.setType(Integer.valueOf(grptype));
		if(radius!=null&&!"".equals(radius))
			baseGrp.setRadius(Integer.valueOf(radius));
		if(overSpdSmsTip!=null&&!"".equals(overSpdSmsTip))
			baseGrp.setOverSpdSmsTip(Integer.valueOf(overSpdSmsTip));

		String resultmessage = null;
		 grpmanager.create(baseGrp);
		 resultmessage = "���ʧ��!";
		
		request.setAttribute("message", resultmessage);
		return mapping.findForward("basesucces");

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
	public ActionForward base_Grp_modytojsp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MessageException {
		// ȡ��grpid
		String grpid = request.getParameter("grpid");
		TbGrpInfo grpinfo = null;
		TbEnterpriseInfo epinfo = null;
		String type=request.getParameter("type");
		if(type!=null){
	    	request.setAttribute("type",type );
	    }
		grpinfo = grpmanager.getGrpinfoby_grpid(grpid);
		if (grpinfo != null) {
			epinfo = epmanger.getEpinfo_byepid(grpinfo.getEp_Id().toString());
		}

		String resultmessage = null;
		if (grpinfo != null) {
			request.setAttribute("tbgrpinfo", grpinfo);
			if (epinfo != null) {
				int agenttype = agentmanger.getAgenttype(String.valueOf(epinfo
						.getAgent_Id()));
				if (agenttype != 0) { // �����������µ���ҵ
					// һ������������
					request.setAttribute("Pagentlist", agentmanger
							.getParentAgent(agent_id,r_id));
					request.setAttribute("Cagentlist", agentmanger
							.getChildAgentByParamentid(String
									.valueOf(agenttype)));
					request.setAttribute("pagentid", agenttype);
					request.setAttribute("basegrp", grpmanager.getBean(grpid));
					request.setAttribute("childagentid", epinfo.getAgent_Id());
					request.setAttribute("ep", epinfo.getEp_Id());
					request.setAttribute("epList", epmanger
							.getEpinfo_byeid(String.valueOf(agenttype),
									String.valueOf(epinfo.getAgent_Id()), ep_id, r_id));
				} else { // һ��������ֱ����ҵ
					// һ������������
					request.setAttribute("Pagentlist", agentmanger
							.getParentAgent(agent_id,r_id));
					// һ��ָ�����������̽����
					request.setAttribute("Cagentlist", agentmanger
							.getChildAgentByParamentid(String.valueOf(epinfo
									.getAgent_Id())));
					request.setAttribute("basegrp", grpmanager.getBean(grpid));
					request.setAttribute("pagentid", epinfo.getAgent_Id());
					request.setAttribute("childagentid", "-1");
					request.setAttribute("ep", epinfo.getEp_Id());
					request.setAttribute("epList", epmanger
							.getEpinfo_byeid(String.valueOf(epinfo
									.getAgent_Id()), String.valueOf(-1), ep_id, r_id));
				}

			}
			return mapping.findForward("baseupdatejsp");
		} else { // ˵��û���ҵ�.

			resultmessage = "Ҫ�޸ĵļ�¼�����ڣ�";
			request.setAttribute("message", resultmessage);
			return mapping.findForward("basesucces");
		}
	}
	/**
	 * Ⱥ����Ϣ�޸�
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */

	public ActionForward base_Grp_udpate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		TabBaseGrpextinfo baseGrp=new TabBaseGrpextinfo();
		String grp_id = request.getParameter("grp_id");
		String ep = request.getParameter("ep");
		String lg=request.getParameter("lg");
		String lt=request.getParameter("lt");
		String grptype=request.getParameter("grptype");
		String radius=request.getParameter("radius");
		String overSpdSmsTip=request.getParameter("overSpdSmsTip");
		if(grp_id!=null&&!"".equals(grp_id)){
			String [] str=grp_id.split("-");
			baseGrp.setGrpId(str[0].trim());
			baseGrp.setCompanyName(str[1].trim());
		}
		if(ep!=null&&!"".equals(ep))
			baseGrp.setEnterpriseId(new Integer(ep));
		if(lg!=null&&!"".equals(lg))
			baseGrp.setLg(Double.valueOf(lg));
		if(lt!=null&&!"".equals(lt))
			baseGrp.setLt(Double.valueOf(lt));
		if(grptype!=null&&!"".equals(grptype))
			baseGrp.setType(Integer.valueOf(grptype));
		if(radius!=null&&!"".equals(radius))
			baseGrp.setRadius(Integer.valueOf(radius));
		if(overSpdSmsTip!=null&&!"".equals(overSpdSmsTip))
			baseGrp.setOverSpdSmsTip(Integer.valueOf(overSpdSmsTip));

		String resultmessage=null;
		String hql ="UPDATE TabBaseGrpextinfo SET lg=?,lt=?,type=?,radius=?,overSpdSmsTip=? WHERE  grpId=?";
		 int bool=grpmanager.update(hql,baseGrp.getLg(),baseGrp.getLt(),baseGrp.getType(),baseGrp.getRadius(),baseGrp.getOverSpdSmsTip(), baseGrp.getGrpId());
		 if(bool>0){
			 resultmessage = "�޸ĳɹ�!";
		 }else{
			 resultmessage = "�޸�ʧ��!";
		 }
		request.setAttribute("message", resultmessage);
		return mapping.findForward("basesucces");

	}

}