package com.elegps.tscweb.action.control;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.elegps.tscweb.action.base.BaseAction;
import com.elegps.tscweb.comm.UserInfo;
import com.elegps.tscweb.model.ControlInfo;
import com.elegps.tscweb.model.TbMsControlInfo;
import com.elegps.tscweb.model.TbUserInfo;

public class MsControlAction extends BaseAction {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ActionForward af = null;
		int cmd = 0;
		TbUserInfo userInfo = UserInfo.getUserInfo(request);
		if (userInfo != null) {
			// ���û�оͽ��յ�����Ĭ����ʾȫ������
			if (request.getParameter("CMD") == null
					|| request.getParameter("CMD").equals("")) {
				cmd = 0;

			} else {
				cmd = Integer.parseInt(request.getParameter("CMD"));
			}
			switch (cmd) {
			case 0:
				af = listControl(mapping, form, request, response);
				break;
			case 1:
				af=toupdate(mapping, form, request, response);
				break;
			case 2:
				af = updateControl(mapping, form, request, response);
				break;
			default:
				af = listControl(mapping, form, request, response);
				break;
			}

		} else {
			request.setAttribute("message", "Session���ڣ������µ�¼");
			af = mapping.findForward("logging");
		}
		if (af == null) {
			af = mapping.findForward("succes");
		}
		return af;
	}

	/**
	 * ��ת���޸�ҳ��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward toupdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String msId = request.getParameter("msIds");
		String msName=request.getParameter("msNames");
		try{
		TbMsControlInfo controlInfo = msControlBiz.getControl(msId);
		if (controlInfo != null) {
			request.setAttribute("controlInfo", controlInfo);
			request.setAttribute("msNames", msName);
			return mapping.findForward("took");
		} else {
			request.setAttribute("msg", "�ü�¼������");
			return mapping.findForward("tonull");
		}
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * �޸ĸü�¼
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 * @throws NumberFormatException 
	 */
	public ActionForward updateControl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws NumberFormatException, Exception {
			String msId=request.getParameter("msIds");
			String r01=request.getParameter("r01");
			String r02=request.getParameter("r02");
			String r03=request.getParameter("r03");
			String r04=request.getParameter("r04");
			String r05=request.getParameter("r05");
			String hql="UPDATE TbMsControlInfo SET r01=?,r02=?,r03=?,r04=?,r05=? WHERE msId=?";
			int bool=msControlBiz.update(hql, Integer.valueOf(r01),Float.valueOf(r02),Float.valueOf(r03),Integer.valueOf(r04),r05,msId);
			if(bool>0){
				request.setAttribute("msg", "�޸ĳɹ�!");
				request.setAttribute("CMD", 0);
				return mapping.findForward("updateok");
			}else{
				request.setAttribute("msg", "�޸�ʧ��!");
				request.setAttribute("CMD", 0);
				return mapping.findForward("tonull");
			}
	}

	/**
	 * ��ȡ�б�
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward listControl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String pagentid = ""; // һ��������id
		String childagentid = ""; // ����������id
		String grpid = request.getParameter("grpid");// Ⱥ�����
		String msId = request.getParameter("msId");
		String msName = request.getParameter("msName");
		String setr01 = request.getParameter("setr01");

		//wanglei 
		int agent_id =Integer.parseInt(request.getSession().getAttribute("agentId")+"");//������id
		int r_id =Integer.parseInt(request.getSession().getAttribute("roleId")+"");//��ɫid
		int ep_id =Integer.parseInt(request.getSession().getAttribute("epId")+"");//��ɫid
		
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

		String ep = request.getParameter("ep");
		if (ep == null || ep.equals("null")) {
			ep = "-1";
		}
		int msCount = 0;
		try {
			msCount = msControlBiz.executeControlCount(msId, msName, ep, grpid,setr01);
			// ��ȡÿҳ������
			int pageSize = 12;
			// ��ȡ��ҳ��
			int pageCount = (msCount + pageSize - 1) / pageSize;
			// ��ҳ��ȡ�õ�ǰҳ
			int pageNo = 1;
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

			List<ControlInfo> list = null;

			list = msControlBiz.findList(pageNo, pageSize, msId, msName, ep,	grpid, setr01);

			// ��ȡ������
			request.setAttribute("msCount", msCount);
			// ����ÿҳ����ʾ����
			request.setAttribute("pageSize", pageSize);
			// ���ص�ǰҳ
			request.setAttribute("currentPage", pageNo);

			// ���ص���ҳ��
			request.setAttribute("pageCount", pageCount);

			request.setAttribute("list", list);
			request.setAttribute("pagentid", pagentid);
			request.setAttribute("childagentid", childagentid);
			request.setAttribute("ep", ep);
			if (grpid== null || grpid.equals("null")) {
				grpid = "-1";
			}
			request.setAttribute("grpid", grpid);
			// һ������������
			request.setAttribute("Pagentlist", agentmanger.getParentAgent(agent_id,r_id));
			// һ��ָ�����������̽����
			request.setAttribute("Cagentlist",agentmanger.getChildAgentByParamentid(pagentid));
			// ����������ҵĬ��
			request.setAttribute("epList",epmanger.getEpinfo_byeid(pagentid, childagentid, ep_id, r_id));
			// ���÷���Ⱥ����Ϣ
			request.setAttribute("grpList",grpmanager.getAllGrp_Info(pagentid, childagentid, ep));
			request.setAttribute("msId", msId);
			request.setAttribute("msName", msName);
			request.setAttribute("setr01", (setr01==null||"".equals(setr01))?"-1":setr01);
			request.setAttribute("CMD", 0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward("listok");
	}
}
