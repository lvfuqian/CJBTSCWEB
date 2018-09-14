package com.elegps.tscweb.action.userrole;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.elegps.tscweb.action.base.BaseAction;
import com.elegps.tscweb.comm.UserInfo;
import com.elegps.tscweb.exception.MessageException;
import com.elegps.tscweb.model.TbGpsInfo;
import com.elegps.tscweb.model.TbMenuInfo;
import com.elegps.tscweb.model.TbMsInfo;
import com.elegps.tscweb.model.TbRoleInfo;
import com.elegps.tscweb.model.TbUserInfo;

public class UserRoleListAction extends BaseAction {
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
		if (user != null) {
			// ���û�оͽ��յ�����Ĭ����ʾȫ���û����ɫ��Ӧ��ϵ��Ϣ
			if (request.getParameter("CMD") == null
					|| request.getParameter("CMD").equals("")) {
				cmd = "userrole_search";
			} else {
				cmd = request.getParameter("CMD");
			}
			if (cmd.equals("add")) {// �����û����ɫ��Ӧ��Ϣ
				actionforward = userrole_add(mapping, form, request, response);
			} else if (cmd.equals("delete")) { // ɾ��ѡ�м�¼
				actionforward = userrole_delete(mapping, form, request,
						response);
			} else if (cmd.equals("addtojsp")) {// ����Ϣ���ص�rolemenu��Ӧ��ϵ������ҳ��
				actionforward = userrole_addtojsp(mapping, form, request,
						response);
			} else if (cmd.equals("userrole_search")) { // ��������ͬʱ��ѯ
				actionforward = userrole_Sarch(mapping, form, request, response);
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
	 * ����Ϣ���ص�userrole��Ӧ��ϵ������ҳ��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward userrole_addtojsp(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws MessageException {
		// usermanager.getNotUser_Info()��ȡû�з���
		List<TbUserInfo> listuser = usermanager.getNotUser_Info();
		List<TbRoleInfo> listrole = rolemanager.getAllRole_Info();
		String resultmessage = null;
		if (listrole != null && listrole.size() > 0 && listuser != null
				&& listuser.size() > 0) {
			// ���÷����û���ѯ��select�����е�optionֵ
			request.setAttribute("listuser", listuser);
			// ���÷��ؽ�ɫ��ѯ��select�����е�optionֵ
			request.setAttribute("listrole", listrole);
			System.out.println("�û����ɫ�м�¼������Ӷ�Ӧ��ϵ");
			return mapping.findForward("userroleaddjsp");
		} else {
			System.out.println("�û����ɫû�м�¼��������Ӷ�Ӧ��ϵ");
			resultmessage = "�û����ɫû�м�¼��������Ӷ�Ӧ��ϵ!";
			request.setAttribute("message", resultmessage);
			return mapping.findForward("showuserrolelist");
		}

	}

	/**
	 * �����û����ɫ��Ӧ��ϵ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward userrole_add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String user_id = request.getParameter("userid");
		String role_id = request.getParameter("roleid");
		String insert = null;
		String resultmessage = null;
		insert = userrolemanager.createUserRoleInfo(user_id, role_id);
		if (insert != null) {
			System.out.println("�û����ɫ��Ӧ��ϵ��ӳɹ�");
			resultmessage = "��ӳɹ�!";
			request.setAttribute("message", resultmessage);
			return mapping.findForward("showuserrolelist");
		} else {
			System.out.println("�û����ɫ��Ӧ��ϵ���ʧ��");
			resultmessage = "�û����ɫ��Ӧ��ϵ���Ѿ�����,���ʧ��!";
			request.setAttribute("message", resultmessage);
			return mapping.findForward("showuserrolelist");
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
	public ActionForward userrole_delete(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		String[] list = request.getParameterValues("list");
		Boolean deletesuce;
		deletesuce = userrolemanager.deleteUserRole(list);
		String resultmessage = null;
		if (deletesuce) {
			System.out.println("ɾ���ɹ�");
			resultmessage = "ɾ���ɹ�!";
			request.setAttribute("message", resultmessage);
			return mapping.findForward("showuserrolelist");
		} else {
			System.out.println("ɾ��ʧ��");
			resultmessage = "ɾ��ʧ��!";
			request.setAttribute("message", resultmessage);
			return mapping.findForward("showuserrolelist");
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
	public ActionForward userrole_Sarch(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String cmd = request.getParameter("CMD");
		String user_id = request.getParameter("user_id");
		if (user_id == null) { // Ϊȫ���û�
			user_id = "";
		}
		String role_id = request.getParameter("role_id");
		if (role_id == null) { // Ϊȫ����ɫ
			role_id = "";
		}
		// ��ȡ������
		int userroleCount = 0;
		userroleCount = userrolemanager.getUserRole_SearchCount(user_id,
				role_id);
		// ��ȡÿҳ������
		int pageSize = 12;
		// ��ȡ��ҳ��
		int pageCount = userrolemanager.getPageCount(userroleCount, pageSize);
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
		request.setAttribute("userroleCount", userroleCount);
		// ����ÿҳ����ʾ����
		request.setAttribute("pageSize", pageSize);
		// ���ص�ǰҳ
		request.setAttribute("currentPage", pageNo);

		// ���ص���ҳ��
		request.setAttribute("pageCount", pageCount);

		// ���÷��ص�������
		request.setAttribute("CMD", cmd);
		// ���÷��ؽ�ɫ�˵���ѯ��select��optionֵ
		request.setAttribute("user_id", user_id);
		request.setAttribute("role_id", role_id);

		// ���÷����û���ѯ��select�����е�optionֵ
		request.setAttribute("userList", usermanager.getAllUser_Info());
		// ���÷��ؽ�ɫ��ѯ��select�����е�optionֵ
		request.setAttribute("roleList", rolemanager.getAllRole_Info());

		request.setAttribute("userroleList", userrolemanager
				.getUserRole_SearchByPage(pageNo, pageSize, user_id, role_id));
		return mapping.findForward("success");

	}
}