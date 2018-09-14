package com.elegps.tscweb.action.EnterPrise;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.elegps.tscweb.action.base.BaseAction;
import com.elegps.tscweb.comm.HRAddress;
import com.elegps.tscweb.comm.UserInfo;
import com.elegps.tscweb.exception.MessageException;
import com.elegps.tscweb.form.AddEpForm;
import com.elegps.tscweb.form.AddmsForm;
import com.elegps.tscweb.model.TbEnterpriseInfo;
import com.elegps.tscweb.model.TbUserInfo;
import com.elegps.tscweb.model.TbUserLog;

/**
 * MyEclipse Struts Creation date: 10-20-2008
 * 
 * XDoclet definition:
 * 
 * @struts.action validate="true"
 */
public class EnterPriseListAction extends BaseAction {
	/*
	 * Generated Methods
	 */
	
	//wanglei ��ҵ�û�ִ��
	public static int a_id = 0;//������id
	public static int r_id = 0;//��ɫid
	public static int ep_id= 0;
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
			ep_id=Integer.parseInt(request.getSession().getAttribute("epId")+"");//��ɫ��ҵid
			// ���û�оͽ��յ�����Ĭ����ʾȫ������
			if (request.getParameter("CMD") == null
					|| request.getParameter("CMD").equals("")) {
				cmd = "ep_search";
			} else {
				cmd = request.getParameter("CMD");
			}

			if (cmd.equals("ep_search")) { // ��������ͬʱ��ѯ
				actionforward = ep_Sarch(mapping, form, request, response);
			} else if (cmd.equals("xiangxi")) // �鿴�û���ϸ��Ϣ
			{
				actionforward = ep_xianxi(mapping, form, request, response);
			} else if (cmd.equals("epadd")) { // ��ת��jsp����ҳ��
				actionforward = ep_addjsp(mapping, form, request, response);
			} else if (cmd.equals("add")) { // ����
				actionforward = ep_add(mapping, form, request, response);
			} else if (cmd.equals("modifyjsp")) { // ��ת��jsp�޸�ҳ��
				actionforward = ep_modifyjsp(mapping, form, request, response);
			} else if (cmd.equals("modify")) { // �޸�
				actionforward = ep_modify(mapping, form, request, response);
			} else if (cmd.equals("delete")) { // ɾ��
				actionforward = ep_delete(mapping, form, request, response);
			}
		} else {
			request.setAttribute("message", "Session���ڣ������µ�¼");
			actionforward = mapping.findForward("logging");
		}

		return actionforward;
	}

	/**
	 * ����ep�б���Ϣ�Ĳ�ѯ������ѯ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward ep_Sarch(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String pagentid = ""; // һ��������id
		String childagentid = ""; // ����������id
		String type=request.getParameter("type");
		// if(request.getSession().getAttribute("agent_id").equals(null)){
		// //session��ʧ��û�е�¼,���ص�¼ҳ��
		// }else{
		// agent_id=request.getSession().getAttribute("agent_id").toString();
		// }

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
		String cmd = request.getParameter("CMD");
		String epname = request.getParameter("epname");
		if (epname == null || epname.equals("")) {
			epname = "";
		}
		
		int epCount = 0;
		epCount = epmanger.getEp_sertch(pagentid, childagentid, epname.trim(), ep_id, r_id);
		// ��ȡÿҳ������
		int pageSize = 12;
		// ��ȡ��ҳ��
		int pageCount = epmanger.getPageCount(epCount, pageSize);
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
		request.setAttribute("epCount", epCount);
		// ����ÿҳ����ʾ����
		request.setAttribute("pageSize", pageSize);
		// ���ص�ǰҳ
		request.setAttribute("currentPage", pageNo);
		// ���÷��ص�������
		request.setAttribute("CMD", cmd);

		// ���ص���ҳ��
		request.setAttribute("pageCount", pageCount);
//		if(!pagentid.equals("-1")){
//			// һ������������
//			request.setAttribute("Pagentlist", agentmanger.getParentAgentNotIncludeZB());
//		}else{
			// һ������������
		int apid = Integer.parseInt(request.getSession().getAttribute("aPId")+"");
		if(apid == 0){
			request.setAttribute("Pagentlist", agentmanger.getParentAgent(a_id,r_id));
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
		// �����÷����û���ѯ��select��optionֵ
		request.setAttribute("pagentid", pagentid);
		request.setAttribute("childagentid", childagentid);
		request.setAttribute("epname", epname);
		// �����û���λ��Ϣ
		List<TbEnterpriseInfo> list = null;
		if (r_id == 3 || r_id == 4){
			list = epmanger.getEpinfoby_mspage(pageNo,
					pageSize, pagentid, childagentid, epname.trim(), ep_id, r_id);
		}else{
			list = epmanger.getEpinfoby_mspage(pageNo,
					pageSize, pagentid, childagentid, epname.trim(), a_id, r_id);
		}
		request.setAttribute("epList",list );
//		request.setAttribute("epList", epmanger.getEpinfoby_mspage(pageNo,
//				pageSize, pagentid, childagentid, epname.trim(), a_id, r_id));
		if(type!=null&&!type.equals("null")){
			return mapping.findForward("kfsucces");
		}else{
			return mapping.findForward("succes");
		}
		
	}

	/**
	 * �û���λ��ϸ��Ϣ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward ep_xianxi(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		// ȡ��epid
		String epid = request.getParameter("epid").toString();
		TbEnterpriseInfo epinfo = null;
		String resultmessage = null;
		epinfo = epmanger.getEpinfo_byepid(epid);
		if (epinfo != null) {
			System.out.println("�ҵ�������¼����ϸ��Ϣ");
			request
					.setAttribute("agentnames", epmanger
							.getAgent_ByEpName(epid));
			request.setAttribute("epinfo", epinfo);
			return mapping.findForward("xiangxi");
		} else { // ˵��û���ҵ�.
			System.out.println("û���ҵ�������¼����ϸ��Ϣ");
			resultmessage = "�ü�¼�����ڣ�";
			request.setAttribute("message", resultmessage);
			return mapping.findForward("showagentlist");
		}
	}

	/**
	 * ת���û���λ����ҳ��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward ep_addjsp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String pagentid = ""; // һ��������id
		String childagentid = ""; // ����������id
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
			childagentid = "-1"; // ֱ����ҵ
		}
		request.setAttribute("pagentid", pagentid);
		request.setAttribute("childagentid", childagentid);
//		if(!pagentid.equals("-1")){
//			// һ������������
//			request.setAttribute("Pagentlist", agentmanger.getParentAgentNotIncludeZB());
//		}else{
			// һ������������
		int apid = Integer.parseInt(request.getSession().getAttribute("aPId")+"");
		if(apid == 0){
			request.setAttribute("Pagentlist", agentmanger.getParentAgent(a_id,r_id));
		//}
		// һ��ָ�����������̽����
			request.setAttribute("Cagentlist", agentmanger.getChildAgentByParamentid(pagentid));
		}else{
			int a = agentmanger.getAgent_ByAgentID(apid+"").getAgent_Id();
			request.setAttribute("Pagentlist", agentmanger.getParentAgent(a,r_id));
			request.setAttribute("Cagentlist", agentmanger.getChildAgentByAId(agent));
		}
//			request.setAttribute("Pagentlist", agentmanger.getParentAgent(a_id,r_id));
////		}
//		// һ��ָ�����������̽����
//		request.setAttribute("Cagentlist", agentmanger
//				.getChildAgentByParamentid(pagentid));
		return mapping.findForward("epaddjsp");

	}

	/**
	 * �û���λ����ҳ��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward ep_add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String agent_id = "";
		AddEpForm epform = (AddEpForm) form;
		String ep_name = epform.getEp_name().trim();
		String ep_address = epform.getEp_address().trim();
		String ep_tel = epform.getEp_tel().trim();
		String ep_tel1 = epform.getEp_tel1().trim();
		String ep_email = epform.getEp_email().trim();
		String ep_url = epform.getEp_url().trim();
		String ep_man = epform.getEp_man().trim();
		String ep_man1 = epform.getEp_man1().trim();
		String ep_qq = epform.getEp_qq().trim();
		String ep_remark = epform.getEp_remark().trim();
		if (epform.getChildagentid().equals("-1")) { // һ��������ֱ����ҵ
			agent_id = epform.getPagentid();
		} else { // �����������µ���ҵ
			agent_id = epform.getChildagentid();
		}
		TbEnterpriseInfo info = epmanger.getEpByName(ep_name);
		String resultmessage = null;

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

		if (info == null) {
			epmanger.createEp(ep_name, ep_address, ep_tel, ep_tel1, ep_email,
					ep_url, ep_man, ep_man1,ep_qq, agent_id, ep_remark);
			//��¼������־
			TbUserLog userLog=new TbUserLog();
			userLog.setUserId(Integer.parseInt(request.getSession().getAttribute("uId").toString()));
			userLog.setlDate(new Date());
			userLog.setlAddress(HRAddress.getIpAddr(request));
			userLog.setlType(18);
			userLog.setlContent("�����ҵ:{"+ep_name+"}");
			logManager.save(userLog);
			resultmessage = "��ҵ��λ��Ϣ��ӳɹ���";
		} else {
			resultmessage = "��ҵ��λ" + ep_name + "�Ѿ�����,���ʧ��!";
		}
		request.setAttribute("pagentid", pagentid);
		request.setAttribute("childagentid", childagentid);
		request.setAttribute("message", resultmessage);
		return mapping.findForward("showeplist");
	}

	/**
	 * ת���޸�ҳ��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward ep_modifyjsp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String agent_id = "";
		// ȡ��epid
		String epid = request.getParameter("epid").toString();
		TbEnterpriseInfo epinfo = null;
		String resultmessage = null;
		epinfo = epmanger.getEpinfo_byepid(epid);
		String type=request.getParameter("type");
		if(type!=null){
	    	request.setAttribute("type",type );
	    }
		if (epinfo != null) {
			request.setAttribute("epinfo", epinfo);
			// ȡ�ô��������� �����idΪ0�ձ�ʾһ���������µ���ҵ�������Ƕ����������µ���ҵ
			int agenttype = agentmanger.getAgenttype(String.valueOf(epinfo
					.getAgent_Id()));

			if (agenttype != 0) { // �����������µ���ҵ
				// һ������������
				request
						.setAttribute("Pagentlist", agentmanger
								.getParentAgent(agenttype,r_id));
				request.setAttribute("Cagentlist", agentmanger.getChildAgentByAId(epinfo.getAgent_Id()+""));
//				request.setAttribute("Cagentlist", agentmanger
//						.getChildAgentByParamentid(String.valueOf(agenttype)));
				request.setAttribute("pagentid", agenttype);
				request.setAttribute("childagentid", epinfo.getAgent_Id());
			} else { // һ��������ֱ����ҵ
				// һ������������
				request
						.setAttribute("Pagentlist", agentmanger
								.getParentAgent(a_id,r_id));
				// һ��ָ�����������̽����
				if(r_id ==3 || r_id ==4){
					request.setAttribute("Cagentlist",null);
				}else{
					request.setAttribute("Cagentlist", agentmanger
							.getChildAgentByParamentid(String.valueOf(epinfo
									.getAgent_Id())));
				}
				
				request.setAttribute("pagentid", epinfo.getAgent_Id());
				request.setAttribute("childagentid", "-1");
			}			
			return mapping.findForward("epmodifyjsp");
		} else { // ˵��û���ҵ�.
			System.out.println("û���ҵ�������¼����ϸ��Ϣ");
			resultmessage = "�ü�¼�����ڣ�";
			request.setAttribute("message", resultmessage);
			return mapping.findForward("showeplist");
		}
	}

	/**
	 * �޸�
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward ep_modify(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String ep_id = request.getParameter("ep_id");
		String ep_name = request.getParameter("ep_name").trim();
		String ep_address = request.getParameter("ep_address").trim();
		String ep_tel = request.getParameter("ep_tel").trim();
		String ep_tel1 = request.getParameter("ep_tel1").trim();
		String ep_email = request.getParameter("ep_email").trim();
		String ep_url = request.getParameter("ep_url").trim();
		String ep_man = request.getParameter("ep_man").trim();
		String ep_man1 = request.getParameter("ep_man1").trim();
		String ep_qq = request.getParameter("ep_qq").trim();
		String ep_remark = request.getParameter("ep_remark").trim();
		String agent_id = "";
		if (request.getParameter("childagentid").equals("-1")) { // һ��������ֱ����ҵ
			agent_id = request.getParameter("pagentid");
		} else { // �����������µ���ҵ
			agent_id = request.getParameter("childagentid");
		}

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

		String modifyok = epmanger.modifyEp(agent_id, ep_id, ep_name,
				ep_address, ep_tel, ep_tel1, ep_email, ep_url, ep_man, ep_man1,ep_qq,
				ep_remark);
		//��¼������־
		TbUserLog userLog=new TbUserLog();
		userLog.setUserId(Integer.parseInt(request.getSession().getAttribute("uId").toString()));
		userLog.setlDate(new Date());
		userLog.setlAddress(HRAddress.getIpAddr(request));
		userLog.setlType(18);
		userLog.setlContent("�޸���ҵ:{"+ep_name+"}");
		logManager.save(userLog);
		String resultmessage = null;
		if (modifyok != null) {
			resultmessage = "�ն���Ϣ�޸ĳɹ���";
			request.setAttribute("message", resultmessage);
		} else {
			resultmessage = "�ն���Ϣ�޸�ʧ�ܣ�";
			request.setAttribute("message", resultmessage);
		}

		request.setAttribute("pagentid", pagentid);
		request.setAttribute("childagentid", childagentid);
		String type=request.getParameter("type");
		if(type!=null&&!type.equals("null")){
			return mapping.findForward("kfshoweplist");
		}else{
			return mapping.findForward("showeplist");
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
	public ActionForward ep_delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String[] list = request.getParameterValues("list");
		Boolean delsucc = epmanger.deleteEp(list);
		for (int i = 0; i < list.length; i++) {
			TbEnterpriseInfo enterpriseInfo=epmanger.getEpinfo_byepid(list[i]);
			if(enterpriseInfo!=null){
				//��¼������־
				TbUserLog userLog=new TbUserLog();
				userLog.setUserId(Integer.parseInt(request.getSession().getAttribute("uId").toString()));
				userLog.setlDate(new Date());
				userLog.setlAddress(HRAddress.getIpAddr(request));
				userLog.setlType(18);
				userLog.setlContent("ɾ����ҵ:{"+enterpriseInfo.getEp_Name()+"}");
				logManager.save(userLog);
			}
		}
		String resultmessage = null;
		if (delsucc) {
			resultmessage = "�û���λɾ���ɹ���";
			request.setAttribute("message", resultmessage);
			return mapping.findForward("showeplist");
		} else {
			resultmessage = "�û���λɾ��ʧ�ܣ�";
			request.setAttribute("message", resultmessage);
			return mapping.findForward("showeplist");
		}

	}
}