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
			
			//
			agent_id =Integer.parseInt(request.getSession().getAttribute("agentId")+"");//������id
			r_id =Integer.parseInt(request.getSession().getAttribute("roleId")+"");//��ɫid

			
			if (request.getParameter("CMD") == null
					|| request.getParameter("CMD").equals("")) {
				cmd = "agent_search";
			} else {
				cmd = request.getParameter("CMD");
			}
			if (cmd.equals("argment_search")) { // ��������ͬʱ��ѯ
				actionforward = agent_Sarch(mapping, form, request, response);
			}
			if (cmd.equals("agent_addjsp")) {// �����ҳ�� zr
				actionforward = toadd(mapping, form, request, response);
			}
			if (cmd.equals("agentadd")) {// ��Ӵ����� zr
				actionforward = addagent(mapping, form, request, response);
			}
			if (cmd.equals("beforeupdate")) {// �޸�ǰ��׼��ҳ�� zr
				actionforward = to_updatejsp(mapping, form, request, response);
			}
			if (cmd.equals("update")) {// �޸Ĵ�������Ϣ zr
				actionforward = updateagent(mapping, form, request, response);
			}
			if (cmd.equals("getagentbyagentid")) {// ����agentid��ѯ��������Ϣ zr
				actionforward = getAgentById(mapping, form, request, response);
			}
			if (cmd.equals("delete")) {// ɾ��������
				actionforward = deleteagent(mapping, form, request, response);
			} else if (cmd.equals("xinaxi")) // �鿴�û���ϸ��Ϣ zr
			{
				actionforward = agent_xianxi(mapping, form, request, response);
			}
		} else {
			request.setAttribute("message", "Session���ڣ������µ�¼");
			actionforward = mapping.findForward("logging");
		}

		return actionforward;
	}

	/**
	 * ���ݴ�����������ѯ
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
		String agent_name = request.getParameter("agent_name");// ����������
		String agent_type = request.getParameter("agent_type");// ����������
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
		// ��ȡÿҳ������
		int pageSize = 12;
		// ��ȡ��ҳ��
		int pageCount = agentmanger.getPageCount(agentCount, pageSize);
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
		request.setAttribute("agentCount", agentCount);
		// ����ÿҳ����ʾ����
		request.setAttribute("pageSize", pageSize);
		// ���ص�ǰҳ
		request.setAttribute("currentPage", pageNo);
		// ���÷��ص�������
		request.setAttribute("CMD", cmd);
		// ���÷����û�����
		request.setAttribute("agent_type", agent_type);
		// ���÷����û���ѯ��intput��ֵ
		request.setAttribute("agent_name", agent_name);

		// ���ص���ҳ��
		request.setAttribute("pageCount", pageCount);
		// �������е�һ��������
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
		// ȡ��agentid
		String agentid = request.getParameter("agentid").toString();
		TbAgentInfo tbagentinfo = null;
		String resultmessage = null;
		tbagentinfo = agentmanger.getTbAgentinfo_byagentid(agentid);
		if (tbagentinfo != null) {
			request.setAttribute("tbagentinfo", tbagentinfo);
			request.setAttribute("agent_name", agentmanger
					.getAgentName(agentid));// ��ʾ���������� zr
			return mapping.findForward("xiangxi");
		} else { // ˵��û���ҵ�.
			resultmessage = "�ü�¼�����ڣ�";
			request.setAttribute("message", resultmessage);
			return mapping.findForward("showagentlist");
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
	public ActionForward deleteagent(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MessageException {
		String[] aidlist = request.getParameterValues("list");
		Boolean deleteagent = agentmanger.deleteagentById(aidlist);
		for (int i = 0; i < aidlist.length; i++) {
			TbAgentInfo agentInfo= agentmanger.getTbAgentinfo_byagentid(aidlist[i]);
			if(agentInfo!=null){
				//��¼������־
				TbUserLog userLog=new TbUserLog();
				userLog.setUserId(Integer.parseInt(request.getSession().getAttribute("uId").toString()));
				userLog.setlDate(new Date());
				userLog.setlAddress(HRAddress.getIpAddr(request));
				userLog.setlType(17);
				userLog.setlContent("ɾ��������{"+agentInfo.getAgent_Name()+"}");
				logManager.save(userLog);
			}
		}
		String resultmessage = null;
		if (deleteagent) {
			resultmessage = "ɾ���ɹ�";
			request.setAttribute("message", resultmessage);
			return mapping.findForward("showagentlist");
		} else {
			resultmessage = "ɾ��ʧ��";
			request.setAttribute("message", resultmessage);
			return mapping.findForward("showagentlist");
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
	 * ��Ӵ����� zr
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
		String agentpid = "0"; // Ĭ�ϸ�idΪ0
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
		
		if (agenttype.equals("һ��������")) {
			agentpid = "0";
		}
		String results = null;
		String names = request.getParameter("agentname");
		TbAgentInfo info = agentmanger.getAgentByName(names);
		if (info == null) {
			results = "��ӳɹ�";
			agentmanger.saveagent(agentname, agentaddress, agenttel, agenttel1,
					agentemail, agenturl, agentman,agentman1, agentqq, agentpid,
					agentremark);
			//��¼������־
			TbUserLog userLog=new TbUserLog();
			userLog.setUserId(Integer.parseInt(request.getSession().getAttribute("uId").toString()));
			userLog.setlDate(new Date());
			userLog.setlAddress(HRAddress.getIpAddr(request));
			userLog.setlType(17);
			userLog.setlContent("��Ӵ�����{"+agentname+"}");
			logManager.save(userLog);
			request.setAttribute("message", results);
			return mapping.findForward("showagentlist");
		} else {
			results = "������<" + agentname + ">�Ѿ����ڣ����ʧ��!";
			request.setAttribute("message", results);
			return mapping.findForward("showagentlist");
		}
	}

	/**
	 * ���ݴ�����ID��ѯ��������Ϣ zr
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
	 * ׼���޸�ʱ��ҳ����Ϣ zr
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
		List allagent = agentmanger.getAll_Agent();// ���д�����
		String type=request.getParameter("type");
		if (allagent != null) {
			request.setAttribute("allagent", allagent);
		}

		List pagentlist = agentmanger.getParentAgent(agent_id,r_id);// ����һ��������
		if (pagentlist != null && pagentlist.size() > 0) {
			request.setAttribute("pagentlist", pagentlist);
		}

		String agentid = request.getParameter("Aid");
		TbAgentInfo agentinfo = agentmanger.getAgent_ByAgentID(agentid);// ���ݴ�����ID��ѯ

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
	 * �޸Ĵ����� zr
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
		//��¼������־
		TbUserLog userLog=new TbUserLog();
		userLog.setUserId(Integer.parseInt(request.getSession().getAttribute("uId").toString()));
		userLog.setlDate(new Date());
		userLog.setlAddress(HRAddress.getIpAddr(request));
		userLog.setlType(17);
		userLog.setlContent("�޸Ĵ�����{"+agentname+"}");
		logManager.save(userLog);
		String results = null;
		if (upda != null) {
			results = "�޸ĳɹ���";
			request.setAttribute("message", results);			
		} else {
			results = "�޸�ʧ�ܣ�";
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