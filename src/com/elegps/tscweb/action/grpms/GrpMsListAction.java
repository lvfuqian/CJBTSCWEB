package com.elegps.tscweb.action.grpms;

import com.elegps.tscweb.action.base.BaseAction;
import com.elegps.tscweb.comm.HRAddress;
import com.elegps.tscweb.comm.UserInfo;
import com.elegps.tscweb.exception.MessageException;
import com.elegps.tscweb.model.TbEnterpriseInfo;
import com.elegps.tscweb.model.TbGrpMsListInfo;
import com.elegps.tscweb.model.TbUserInfo;
import com.elegps.tscweb.model.TbUserLog;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * MyEclipse Struts Creation date: 10-20-2008
 * 
 * XDoclet definition:
 * 
 * @struts.action validate="true"
 */
public class GrpMsListAction extends BaseAction {
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
			ep_id = Integer.parseInt(request.getSession().getAttribute("epId")+"");//��ҵid
            // ���û�оͽ��յ�����Ĭ����ʾȫ��Ⱥ����Ϣ
            if (request.getParameter("CMD") == null
                    || request.getParameter("CMD").equals("")) {
                cmd = "grpms_search";
            } else {
                cmd = request.getParameter("CMD");
            }

            if (cmd.equals("add")) {// ����Ⱥ���ն˶�Ӧ��Ϣ
                actionforward = grpms_add(mapping, form, request, response);
            } else if (cmd.equals("grpms_search")) { // ��������ͬʱ��ѯ
                actionforward = grpms_Sarch(mapping, form, request, response);
            } else if (cmd.equals("grp_id")) { // ����Ⱥ�����Ͳ�ѯȺ����Ϣ
                actionforward = grp_idsearch(mapping, form, request, response);
            } else if (cmd.equals("ms_id")) { // ����Ⱥ��ͨ��״̬��ѯȺ����Ϣ(1ͨ���С�0δ��ͨ���У�
                actionforward = ms_idsearch(mapping, form, request, response);
            } else if (cmd.equals("delete")) { // ɾ��ѡ��Ⱥ����Ϣ(�߼�ɾ��flag��Ϊ0)
                actionforward = grpms_delete(mapping, form, request, response);
            } else if (cmd.equals("addtojsp")) {// ����Ϣ���ص�grpms��Ӧ��ϵ������ҳ��
                actionforward = grpms_addtojsp(mapping, form, request, response);
            } else if (cmd.equals("addtoplms_bygrpjsp")) { // ����Ϣ���ص�grpms��Ӧ��ϵ����������ҳ��(����Ⱥ�����������ն�)
                actionforward = grpms_addtoplms_bygrpjsp(mapping, form, request, response);
            } else if (cmd.equals("plgrpby_msidadd")) { // ����Ⱥ�������������ն��û���Ϣ(����Ⱥ�����������ն�)
                actionforward = grpms_add_bygrpid(mapping, form, request, response);
            } else if (cmd.equals("addtoplgrp_bymsjsp")) { // ����Ϣ���ص�grpms��Ӧ��ϵ����������ҳ��(�����ն˺��������Ⱥ��)
                actionforward = grpms_addtoplgrp_bymsjsp(mapping, form, request, response);
            } else if (cmd.equals("plgrpby_grpidadd")) { //�����ն˺����������Ⱥ����Ϣ(�����ն˺��������Ⱥ��)
                actionforward = grpms_add_bymsid(mapping, form, request, response);
            } else if (cmd.equals("kyaddtoplgrp_bymsjsp")) { // ����Ϣ���ص�grpms��Ӧ��ϵ�Ŀ���ҵ��������ҳ��(�����ն˺��������Ⱥ��)
                actionforward = kygrpms_addtoplgrp_bymsjsp(mapping, form, request, response);
            } else if (cmd.equals("kyplgrpby_grpidadd")) { //�����ն˺ſ���ҵ���������Ⱥ����Ϣ(�����ն˺��������Ⱥ��)
                actionforward = kygrpms_add_bymsid(mapping, form, request, response);
            } else if (cmd.equals("togrpmsmodijsp")) {  //�޸�Ⱥ����
                actionforward = grpms_modytojsp(mapping, form, request, response);
            } else if (cmd.equals("grpmsmodify")) {
                actionforward = grpmsmodify(mapping, form, request, response);
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
	 * �޸�Ⱥ����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws MessageException
	 */
	public ActionForward grpmsmodify(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MessageException {
		String grpid=request.getParameter("grp_id");
		String rgrpid=request.getParameter("rgrp_id");
		String msid=request.getParameter("ms_id");
		String config=request.getParameter("config");
		String modifyok=grpmsmanager.modifyGrpMs(grpid,rgrpid,msid,config);
		String resultmessage=null;
			if(modifyok!=null){
				System.out.println("�޸ĳɹ�");
				resultmessage="�޸ĳɹ�!";
				request.setAttribute("message",resultmessage);
				return mapping.findForward("showgrpmslist");
			}
			else{
				System.out.println("��Ӫ�������Ѿ�����,�޸�ʧ��");
				resultmessage="�޸�ʧ��!";
				request.setAttribute("message",resultmessage);
				return mapping.findForward("showgrpmslist");
			}
	}
	
	/**
	 * ���޸�Ⱥ��˶�Ӧ��ϵjspҳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws MessageException
	 */
	public ActionForward grpms_modytojsp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MessageException {
		String grpid = request.getParameter("grpid");
		String msid = request.getParameter("msid");
		TbGrpMsListInfo tbgrpms = null;
		tbgrpms = grpmsmanager.getGrpMs_ByGrpMsid(grpid,msid);
		if (tbgrpms != null) {
			request.setAttribute("tbgrpmsinfo",tbgrpms);
			return mapping.findForward("grpmsmdifyjsp");
		} else { // ˵��û���ҵ�Ҫ�޸ĵļ�¼.
			return null;
		}
	}
	
	/**
	 * ��������Ⱥ���ն˶�Ӧ��ϵ(����msid��������Ⱥ��)
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward grpms_add_bymsid(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		String ms_id = request.getParameter("ms_id");
		String grp_id[] = request.getParameterValues("selectgrpid");
		String insert = null; 
		insert = grpmsmanager.createGrpMsInfo_ByMsid(ms_id, grp_id);
		if (insert != null) {
			request.setAttribute("message", insert);
			return mapping.findForward("showgrpmslist");
		} else {
			request.setAttribute("message", insert);
			return mapping.findForward("showgrpmslist");
		}

	}
	

	/**
	 * ����Ϣ���ص�grpms��Ӧ��ϵ����������ҳ��(�����ն˺��������Ⱥ��)
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward grpms_addtoplgrp_bymsjsp(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws MessageException {
		
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

		String ep = request.getParameter("ep");
		if (ep == null || ep.equals("null")) {
			if(r_id == 4 || r_id == 3){
				ep = ep_id+"";
			}else{
				ep = "-1";   //ȫ����ҵ}
			}
		}
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
//			request.setAttribute("Pagentlist", agentmanger.getParentAgent(a_id,r_id));
////		}
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
					childagentid, a_id, r_id);
		}
		request.setAttribute("epList",list );
        if(ep!="-1"){
//        	request.setAttribute("grpList", grpmanager.getAllGrp_Info(pagentid,childagentid,ep));
        }
		request.setAttribute("pagentid", pagentid);
		request.setAttribute("childagentid", childagentid);
//		request.setAttribute("epid", ep);
		
		return mapping.findForward("grpmsaddplgrpjsp");

	}


	/**
	 * ��������Ⱥ���ն˶�Ӧ��ϵ(����grpid���������ն�)
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward grpms_add_bygrpid(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
        String grp_id = request.getParameter("grp_id");
        String ms_id[] = request.getParameterValues("selectmsid");
        String changeMs = request.getParameter("changeMs");
        String[] msArray = changeMs.split(",");

        String insert = null;
        insert = grpmsmanager.createGrpMsInfo_ByGrpid(grp_id, ms_id);
        if (insert != null && (changeMs != null && !"".equals(changeMs))) {
            for (int i = 0; i < msArray.length; i++) {
                String msa = msArray[i];
                String[] msb = msa.split("\\$");
                if (changeMs.indexOf(msb[0]) == changeMs.lastIndexOf(msb[0])) {
                    //��¼������־
                    TbUserLog userLog = new TbUserLog();
                    userLog.setUserId(Integer.parseInt(request.getSession().getAttribute("uId").toString()));
                    userLog.setlDate(new Date());
                    userLog.setlAddress(HRAddress.getIpAddr(request));
                    userLog.setlType((msb[1].equals("1")) ? 7 : 8);
                    userLog.setlContent("Ⱥ��:{ " + grp_id + " }�ն�:{ " + msb[0] + " }" + ((msb[1].equals("1")) ? "���" : "ȡ��") + "��Ӧ��ϵ");
                    logManager.save(userLog);
                }
            }
            request.setAttribute("message", insert);
            return mapping.findForward("showgrpmslist");
        } else {
            request.setAttribute("message", insert);
            return mapping.findForward("showgrpmslist");
        }

    }

	/**
	 * ����Ϣ���ص�grpms��Ӧ��ϵ����������ҳ��(����Ⱥ�����������ն�)
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward grpms_addtoplms_bygrpjsp(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws MessageException {
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

		String ep = request.getParameter("ep");
		if (ep == null || ep.equals("null")) {
			if(r_id == 4){
				ep = ep_id+"";
			}else{
				ep = "-1";   //ȫ����ҵ}
			}
		}
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
//		if(!pagentid.equals("-1")){
//			// һ������������
//			request.setAttribute("Pagentlist", agentmanger.getParentAgentNotIncludeZB());
//		}else{
			// һ������������
//			request.setAttribute("Pagentlist", agentmanger.getParentAgent(a_id,r_id));
//		}
		
		// һ��ָ�����������̽����
//		request.setAttribute("Cagentlist", agentmanger
//				.getChildAgentByParamentid(pagentid));
		// ����������ҵĬ��
		List<TbEnterpriseInfo> list = null;
		if (r_id == 3 || r_id == 4){
			list = epmanger.getEpinfo_byeid(pagentid,
					childagentid, ep_id, r_id);
		}else{
			list = epmanger.getEpinfo_byagentid(pagentid,
					childagentid, a_id, r_id);
		}
		request.setAttribute("epList", list);
        if(ep!="-1"){
//        	request.setAttribute("grpList", grpmanager.getAllGrp_Info(pagentid,childagentid,ep));
        }
		request.setAttribute("pagentid", pagentid);
		request.setAttribute("childagentid", childagentid);
//		request.setAttribute("epid", ep);

	  return mapping.findForward("grpmsaddplmsjsp");


	}

	/**
	 * ����Ϣ���ص�grpms��Ӧ��ϵ������ҳ��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward grpms_addtojsp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MessageException {
		String pagentid = ""; // һ��������id
		String childagentid = ""; // ����������id

		String agent = request.getSession().getAttribute("agentId")+"";
		int apid = Integer.parseInt(request.getSession().getAttribute("aPId")+"");
		
		if (request.getParameter("pagentid") != null && (agent.equals("-1") || agent.equals("0"))) {
			pagentid = request.getParameter("pagentid");
		} else {
			 //pagentid = "-1"; // ���������ܲ�
			if(apid==0){
				pagentid = agent;
			}else{
				pagentid = apid+"";
			}
        	
        	if(pagentid.equals("0"))
        		pagentid = "-1";
		}

		if (request.getParameter("childagentid") != null) {
			childagentid = request.getParameter("childagentid");
		} else {
			childagentid = "-1"; // ֱ����ҵ
		}
		if(apid!=0){
			childagentid = agent;
		}

		String ep = request.getParameter("ep");
		if (ep == null || ep.equals("null")) {
			if(r_id ==4){
				ep = ep_id+"";
			}else{
				ep = "-1";
			}
		}
		
		
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
//		if(!pagentid.equals("-1")){
//			// һ������������
//			request.setAttribute("Pagentlist", agentmanger.getParentAgentNotIncludeZB());
//		}else{
			// һ������������
//			request.setAttribute("Pagentlist", agentmanger.getParentAgent(a_id,r_id));
//		}
		
		
		// һ��ָ�����������̽����
//		request.setAttribute("Cagentlist", agentmanger
//				.getChildAgentByParamentid(pagentid));
		// ����������ҵĬ��
		List<TbEnterpriseInfo> list = null;
		if (r_id == 3 || r_id == 4 ){
			list = epmanger.getEpinfo_byeid(pagentid,
					childagentid, ep_id, r_id);
		}else{
			list = epmanger.getEpinfo_byagentid(pagentid,
					childagentid, a_id, r_id);
		}
		request.setAttribute("epList", list);
        if(ep!="-1"){
        	request.setAttribute("grpList", grpmanager.getAllGrp_Info(pagentid,childagentid,ep));
        	request.setAttribute("msList", msmanager.getAllMs_Info(pagentid,childagentid,ep));
        }
		request.setAttribute("pagentid", pagentid);
		request.setAttribute("childagentid", childagentid);
		request.setAttribute("epid", ep);
		return mapping.findForward("grpmsaddjsp");
	

	}

	/**
	 * ����grp_id��ѯ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward grp_idsearch(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MessageException {
		String cmd = request.getParameter("CMD");
		String type = "0"; // 2ȫ��Ⱥ��

		// ���typeȡֵΪ�ջ�Ϊnull,����ʾȫ���ն���Ϣ
		if (request.getParameter("type") == null
				|| request.getParameter("type").equals("")) {
			type = "2";
		} else {
			type = request.getParameter("type");
		}

		if (type.equals("2")) { // ȫ������
			// ��ȡ������
			int grpmsCount = 0;
			grpmsCount = grpmsmanager.getAllGrp_idCount();
			// ��ȡÿҳ������
			int pageSize = 12;
			// ��ȡ��ҳ��
			int pageCount = grpmsmanager.getPageCount(grpmsCount, pageSize);
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
			request.setAttribute("grpmsCount", grpmsCount);
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
			request.setAttribute("grp_id", type);


			request.setAttribute("grpmsList", grpmsmanager
					.getAllGrpid_InfoByPage(pageNo, pageSize));
			return mapping.findForward("success");

		} else {
			// ��ȡ������
			int grpmsCount = 0;
			grpmsCount = grpmsmanager.getGrp_idCount(type);
			// ��ȡÿҳ������
			int pageSize = 12;
			// ��ȡ��ҳ��
			int pageCount = grpmsmanager.getPageCount(grpmsCount, pageSize);
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
			request.setAttribute("grpmsCount", grpmsCount);
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
			request.setAttribute("grp_id", type);
			// ���÷���Ⱥ���ѯ��select�����е�optionֵ
//			request.setAttribute("grpList", grpmanager.getAllGrp_Info());
			// ���÷����ն˲�ѯ��select�����е�optionֵ
//			request.setAttribute("msList", msmanager.getAllMs_Info());
			request.setAttribute("grpmsList", grpmsmanager.getGrpid_InfoByPage(
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
		String type = "0"; // 2ȫ��Ⱥ��

		// ���typeȡֵΪ�ջ�Ϊnull,����ʾȫ��Ⱥ����Ϣ
		if (request.getParameter("type") == null
				|| request.getParameter("type").equals("")) {
			type = "2";
		} else {
			type = request.getParameter("type");
		}

		if (type.equals("2")) { // ȫ������
			// ��ȡ������
			int grpmsCount = 0;
			grpmsCount = grpmsmanager.getAllms_idCount();
			// ��ȡÿҳ������
			int pageSize = 12;
			// ��ȡ��ҳ��
			int pageCount = grpmsmanager.getPageCount(grpmsCount, pageSize);
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
			request.setAttribute("grpmsCount", grpmsCount);
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
//			request.setAttribute("grpList", grpmanager.getAllGrp_Info());
			// ���÷����ն˲�ѯ��select�����е�optionֵ
//			request.setAttribute("msList", msmanager.getAllMs_Info());

			request.setAttribute("grpmsList", grpmsmanager
					.getAllMsid_InfoByPage(pageNo, pageSize));
			return mapping.findForward("success");

		} else {
			// ��ȡ������
			int grpmsCount = 0;
			grpmsCount = grpmsmanager.getMs_idCount(type);
			// ��ȡÿҳ������
			int pageSize = 12;
			// ��ȡ��ҳ��
			int pageCount = grpmsmanager.getPageCount(grpmsCount, pageSize);
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
			request.setAttribute("grpmsCount", grpmsCount);
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
//			request.setAttribute("grpList", grpmanager.getAllGrp_Info());
			// ���÷����ն˲�ѯ��select�����е�optionֵ
//			request.setAttribute("msList", msmanager.getAllMs_Info());
			request.setAttribute("grpmsList", grpmsmanager.getMsid_InfoByPage(
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
	public ActionForward grpms_add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String grp_id = request.getParameter("grp_id");
		String ms_id = request.getParameter("ms_id");
		String insert = null;
		insert = grpmsmanager.createGrpMsInfo(grp_id, ms_id);
		String resultmessage = null;
		if (insert != null) {
			System.out.println("Ⱥ���ն˶�Ӧ��ϵ��ӳɹ�");
			resultmessage = "Ⱥ���ն˶�Ӧ��ϵ��ӳɹ�!";
			//��¼������־
			TbUserLog userLog=new TbUserLog();
			userLog.setUserId(Integer.parseInt(request.getSession().getAttribute("uId").toString()));
			userLog.setlDate(new Date());
			userLog.setlAddress(HRAddress.getIpAddr(request));
			userLog.setlType(7);
			userLog.setlContent("Ⱥ��:{ "+grp_id+" }�ն�:{ "+ms_id+" }��Ӷ�Ӧ��ϵ");
			logManager.save(userLog);
			request.setAttribute("message", resultmessage);
			return mapping.findForward("showgrpmslist");
		} else {
			System.out.println("Ⱥ���ն˶�Ӧ��ϵ���ʧ��");
			resultmessage = "��Ⱥ���ն˶�Ӧ��ϵ�Ѿ�����,���ʧ��!";
			request.setAttribute("message", resultmessage);
			return mapping.findForward("showgrpmslist");
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
	public ActionForward grpms_delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String[] list = request.getParameterValues("list");
		Boolean deletesuce;
		deletesuce = grpmsmanager.deleteGrpMs(list);
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
				userLog.setlType(8);
				userLog.setlContent("Ⱥ��:{ "+mgs[0]+" }�ն�:{ "+mgs[1]+" }ȡ����Ӧ��ϵ");
				logManager.save(userLog);
			}
			System.out.println("ɾ���ɹ�");
			resultmessage = "ɾ���ɹ�!";
			request.setAttribute("message", resultmessage);
			return mapping.findForward("showgrpmslist");
		} else {
			System.out.println("ɾ��ʧ��");
			resultmessage = "ɾ��ʧ��!";
			request.setAttribute("message", resultmessage);
			return mapping.findForward("showgrpmslist");
		}

	}
	
	
	/**
	 * �޸�Ⱥ���ն�����
	 */
	public ActionForward updateGMPZ(ActionMapping mapping,HttpServletRequest request, HttpServletResponse response,
			String grpid,String msid,String config){
		
		String modifyok=grpmsmanager.modifyGrpMs(grpid,grpid,msid,config);
		String resultmessage=null;
			if(modifyok!=null){
				System.out.println("�޸ĳɹ�");
				resultmessage="�޸ĳɹ�!";
				request.setAttribute("message",resultmessage);
				return mapping.findForward("success");
			}
			else{
				System.out.println("��Ӫ�������Ѿ�����,�޸�ʧ��");
				resultmessage="�޸�ʧ��!";
				request.setAttribute("message",resultmessage);
				return mapping.findForward("success");
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
	public ActionForward grpms_Sarch(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		if(request.getParameter("pzgrpid")!=null && !request.getParameter("pzgrpid").equals("")
				&& request.getParameter("pzmsid")!=null && !request.getParameter("pzmsid").equals("")
				&& request.getParameter("config")!=null && !request.getParameter("config").equals("")){
			String pzgrpid=request.getParameter("pzgrpid");
			String pzmsid=request.getParameter("pzmsid");
			String config=request.getParameter("config");
			
			updateGMPZ(mapping,request,response,pzgrpid,pzmsid,config);
		}
		
		String pagentid = ""; // һ��������id
		String childagentid = ""; // ����������id
		String ep="";  //��ҵid
		String cmd = request.getParameter("CMD");
		String grpid = request.getParameter("grpid");//Ⱥ�����
		String msid = request.getParameter("msid");//�ն˺���
		HttpSession session = request.getSession(true);
		TbUserInfo userInfo = (TbUserInfo) session.getAttribute("user");
		
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
			childagentid="-2";   //������ҵ
		}

		if(request.getParameter("ep")!=null){
			ep = request.getParameter("ep");
		}else{
			if(r_id == 4 || r_id ==3){
				ep = ep_id+"";
			}else{
				ep = "-1";   //ȫ����ҵ}
			}
		}
		if (grpid == null) { // Ϊȫ��Ⱥ����
			grpid = "";
		}
		
		if (msid == null) {//ȫ���ն˺�
			msid = "";
		}
		// ��ȡ������
		int grpmsCount = 0;
		grpmsCount = grpmsmanager.getGrpMs_SearchCount(grpid.trim(), msid.trim(),pagentid,childagentid,ep);
		// ��ȡÿҳ������
		String ps =request.getParameter("pageSize");
		int pageSize = 10;
		if(ps!= null){
			pageSize =Integer.parseInt(ps);
		}
		// ��ȡ��ҳ��
		int pageCount = grpmsmanager.getPageCount(grpmsCount, pageSize);
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
		request.setAttribute("grpmsCount", grpmsCount);
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
		request.setAttribute("grpid", grpid);
		request.setAttribute("pagentid", pagentid);
		request.setAttribute("childagentid", childagentid);
		request.setAttribute("ep", ep);
		
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
//		// һ������������
//		request.setAttribute("Pagentlist", agentmanger.getParentAgent(a_id,r_id));
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
					childagentid, a_id, r_id);
		}
		request.setAttribute("epList", list);
		//���÷���Ⱥ����Ϣ
		request.setAttribute("grpList", grpmanager.getAllGrp_Info(pagentid,childagentid,ep));
		// ���÷����ն���Ϣ
		request.setAttribute("msList", msmanager.getAllMs_Info(pagentid,childagentid,ep));
				
	
	
		request.setAttribute("grpmsList", grpmsmanager.getGrpMs_SearchByPage(
				pageNo, pageSize, grpid, msid,pagentid,childagentid,ep));
		return mapping.findForward("success");

	}
	
	
	
	

	
	
	/**
	 * ����Ϣ���ص�grpms��Ӧ��ϵ�Ŀ���ҵ��������ҳ��(�����ն˺��������Ⱥ��)
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward kygrpms_addtoplgrp_bymsjsp(ActionMapping mapping,
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
			if(r_id == 4){
				ep = ep_id+"";
			}else{
				ep = "-1";   //ȫ����ҵ}
			}
		}
		// һ������������
		request.setAttribute("Pagentlist", agentmanger.getParentAgent(a_id,r_id));
		// һ��ָ�����������̽����
		request.setAttribute("Cagentlist", agentmanger
				.getChildAgentByParamentid(pagentid));
		// ����������ҵĬ��
		List<TbEnterpriseInfo> list = null;
		if (r_id == 3 || r_id ==4){
			list = epmanger.getEpinfo_byeid(pagentid,
					childagentid, ep_id, r_id);
		}else{
			list = epmanger.getEpinfo_byagentid(pagentid,
					childagentid, a_id, r_id);
		}
		request.setAttribute("epList", list);
        if(ep!="-1"){
//        	request.setAttribute("grpList", grpmanager.getAllGrp_Info(pagentid,childagentid,ep));
        }
		request.setAttribute("pagentid", pagentid);
		request.setAttribute("childagentid", childagentid);
//		request.setAttribute("epid", ep);
		return mapping.findForward("kygrpmsaddplgrpjsp");

	}
	
	

	
	
	
	
	
	
	
	
	
	/**
	 * ��������ҵ����Ⱥ���ն˶�Ӧ��ϵ(����msid��������Ⱥ��)
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward kygrpms_add_bymsid(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		String ms_id = request.getParameter("ms_id");
		String grp_id[] = request.getParameterValues("selectgrpid");
		String insert = null; 
		insert = grpmsmanager.createGrpMsInfo_ByMsid(ms_id, grp_id);
		if (insert != null) {
			request.setAttribute("message", insert);
			return mapping.findForward("showgrpmslist");
		} else {
			request.setAttribute("message", insert);
			return mapping.findForward("showgrpmslist");
		}

	}
	
}