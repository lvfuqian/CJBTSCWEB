package com.elegps.tscweb.action.role;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.elegps.tscweb.action.base.BaseAction;
import com.elegps.tscweb.comm.UserInfo;
import com.elegps.tscweb.exception.MessageException;
import com.elegps.tscweb.form.AddroleForm;
import com.elegps.tscweb.model.TbRoleInfo;
import com.elegps.tscweb.model.TbUserInfo;

/**
 * level 2008-11-18
 * 
 * @struts.action validate="true"
 */
public class RoleListAction extends BaseAction {
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
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MessageException {
		ActionForward actionforward = null;
		String cmd = null;
		TbUserInfo user = UserInfo.getUserInfo(request);
		if (user != null) {
			// ���û�оͽ��յ�����Ĭ����ʾȫ������
			if (request.getParameter("CMD") == null
					|| request.getParameter("CMD").equals("")) {
				cmd = "role_search";
			} else {
				cmd = request.getParameter("CMD");
			}

			if (cmd.equals("add")) {// ���ӽ�ɫ��Ϣ
				actionforward = role_add(mapping, form, request, response);
			} else if (cmd.equals("delete")) { // ɾ��ѡ�н�ɫ��Ϣ
				actionforward = role_delete(mapping, form, request, response);
			} else if (cmd.equals("torolemdoyijsp")) { // ��Ҫ�޸ĵ���Ϣ�����޸ĵ�jspҳ��
				actionforward = role_modytojsp(mapping, form, request, response);
			} else if (cmd.equals("rolemodify")) { // �޸Ľ�ɫ��Ϣ
				actionforward = role_modify(mapping, form, request, response);
			} else if (cmd.equals("role_search")) { // ��������ͬʱ��ѯ
				actionforward = role_Sarch(mapping, form, request, response);
			} else if (cmd.equals("role_addjsp")) {// ת������ҳ��
				actionforward = role_Addjsp(mapping, form, request, response);
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
	 * ���ؽ�ɫid��Ϣ
	 * 
	 */
	public ActionForward role_Addjsp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MessageException {
		return mapping.findForward("roleaddjsp");

	}

	/**
	 * �޸Ľ�ɫ��Ϣ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws MessageException
	 */
	public ActionForward role_modify(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MessageException {
		String role_id = request.getParameter("roleid");
		String role_name = request.getParameter("rolename");
		String modifyok = rolemanager.modifyRole(role_id, role_name.trim());
		String resultmessage = null;
		if (modifyok != null) {
			System.out.println("�޸ĳɹ�");
			resultmessage = "�޸ĳɹ�!";
			request.setAttribute("message", resultmessage);
			return mapping.findForward("showrolelist");
		} else {
			System.out.println("�޸�ʧ��");
			resultmessage = "��ɫ��<" + role_name + ">�Ѿ����ڣ��޸�ʧ��!";
			request.setAttribute("message", resultmessage);
			return mapping.findForward("showrolelist");
		}
	}

	/**
	 * ���޸Ľ�ɫ��Ϣ��jspҳ��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws MessageException
	 */
	public ActionForward role_modytojsp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MessageException {
		// ȡ��role_id
		String role_id = request.getParameter("type");
		TbRoleInfo roleinfo = null;
		roleinfo = rolemanager.getRoleInfoby_roleid(role_id);
		String resultmessage = null;
		if (roleinfo != null) {
			request.setAttribute("tbroleinfo", roleinfo);
			return mapping.findForward("rolemdify");
		} else { // ˵��û���ҵ�Ҫ�޸ĵļ�¼.
			System.out.println("�޸ļ�¼û���ҵ�");
			resultmessage = "��¼������!";
			request.setAttribute("message", resultmessage);
			return mapping.findForward("showrolelist");
		}
	}

	public ActionForward role_add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		AddroleForm roleform = (AddroleForm) form;
		String role_name = roleform.getRolename();
		String insert = null;
		String resultmessage = null;
		insert = rolemanager.createRole(role_name.trim());
		if (insert != null) {
			System.out.println("��ɫ��Ϣ��ӳɹ�");
			resultmessage = "��ӳɹ�!";
			request.setAttribute("message", resultmessage);
			return mapping.findForward("showrolelist");
		} else {
			System.out.println("��ɫ��Ϣ���ʧ��");
			resultmessage = "��ɫ��<" + role_name + ">�Ѿ����ڣ����ʧ��!";
			request.setAttribute("message", resultmessage);
			return mapping.findForward("showrolelist");
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
	public ActionForward role_delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String[] list = request.getParameterValues("list");
		Boolean delrole = rolemanager.deleteRole(list);
		String resultmessage = null;
		if (delrole) {
			System.out.println("��ɫ��Ϣɾ���ɹ�");
			resultmessage = "ɾ���ɹ�";
			request.setAttribute("message", resultmessage);
			return mapping.findForward("showrolelist");
		} else {
			System.out.println("��ɫ��Ϣɾ��ʧ��");
			resultmessage = "ɾ��ʧ��";
			request.setAttribute("message", resultmessage);
			return mapping.findForward("showrolelist");
		}
	}

	/**
	 * ���ݽ�ɫ���б���Ϣ�Ĳ�ѯ������ѯ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward role_Sarch(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String cmd = request.getParameter("CMD");
		String role_name = request.getParameter("role_name");// �˵���
		if (role_name == null || (role_name.trim()).equals("")) {
			role_name = "";
		}

		int roleCount = 0;
		roleCount = rolemanager.getRole_SearchCount(role_name);
		// ��ȡÿҳ������
		int pageSize = 12;
		// ��ȡ��ҳ��
		int pageCount = rolemanager.getPageCount(roleCount, pageSize);
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
		request.setAttribute("roleCount", roleCount);
		// ����ÿҳ����ʾ����
		request.setAttribute("pageSize", pageSize);
		// ���ص�ǰҳ
		request.setAttribute("currentPage", pageNo);
		// ���÷��ص�������
		request.setAttribute("CMD", cmd);
		// ���÷����û�����

		// ���÷����û���ѯ��intput��ֵ
		request.setAttribute("role_name", role_name);

		// ���ص���ҳ��
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("roleList", rolemanager.getTbRoleInfoby_name(
				pageNo, pageSize, role_name));
		return mapping.findForward("succes");
	}
}