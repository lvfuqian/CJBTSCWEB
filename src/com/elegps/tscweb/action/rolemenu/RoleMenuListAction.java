package com.elegps.tscweb.action.rolemenu;

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
import com.elegps.tscweb.model.TbGrpInfo;
import com.elegps.tscweb.model.TbMenuInfo;
import com.elegps.tscweb.model.TbMsInfo;
import com.elegps.tscweb.model.TbRoleInfo;
import com.elegps.tscweb.model.TbUserInfo;

public class RoleMenuListAction extends BaseAction {
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
			// ���û�оͽ��յ�����Ĭ����ʾȫ����ɫ��˵���Ӧ��ϵ��Ϣ
			if (request.getParameter("CMD") == null
					|| request.getParameter("CMD").equals("")) {
				cmd = "rolemenu_search";
			} else {
				cmd = request.getParameter("CMD");
			}

			if (cmd.equals("add")) {// ���ӽ�ɫ��˵���Ӧ��Ϣ
				actionforward = rolemenu_add(mapping, form, request, response);
			} else if (cmd.equals("delete")) { // ɾ��ѡ�м�¼
				actionforward = rolemenu_delete(mapping, form, request,
						response);
			} else if (cmd.equals("addtojsp")) {// ����Ϣ���ص�rolemenu��Ӧ��ϵ������ҳ��
				actionforward = rolemenu_addtojsp(mapping, form, request,
						response);
			} else if (cmd.equals("rolemenu_search")) { // ��������ͬʱ��ѯ
				actionforward = rolemenu_Sarch(mapping, form, request, response);
			} else if (cmd.equals("addtoplmenu_byrolejsp")) { // ���ظ��ݽ�ɫ���������Ӳ˵��Ĵ���
				actionforward = rolemenu_addtoplmenu_byrolejsp(mapping, form,
						request, response);
			} else if (cmd.equals("role_search")) {// �����ݽ�ɫ�������Ӳ˵���Ϣҳ�棬��ʾ���еĲ˵���Ϣ(���ݽ�ɫ������Ӳ˵�)
				actionforward = rolemenu_addtoplmenu_byrole(mapping, form,
						request, response);
			} else if (cmd.equals("plroleby_menuadd")) { // ����roleid�������Ӳ˵����ύ��
				actionforward = rolemenu_add_bymenuid(mapping, form, request,
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
	 * �������ӽ�ɫ�˵���Ӧ��ϵ(����roleid�������Ӳ˵�)
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward rolemenu_add_bymenuid(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		String role_id = request.getParameter("roleid");
		String menu_id[] = request.getParameterValues("selectmenuid");
		String insert = null;
		insert = rolemenumanager.createRoleMenuInfo_ByRoleid(role_id, menu_id);
		if (insert != null) {
			request.setAttribute("message", insert);
			return mapping.findForward("showrolemenulist");
		} else {
			request.setAttribute("message", insert);
			return mapping.findForward("showrolemenulist");
		}

	}

	/**
	 * ����Ϣ���ص�rolemenu��Ӧ��ϵ����������ҳ��(����roleid�����˵�)
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward rolemenu_addtoplmenu_byrole(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws MessageException {
		String roleid = request.getParameter("roleid");
		// ����roleid��ѯ�ڽ�ɫ�˵���Ӧ��ϵ���Ѿ����ڵĲ˵�
		List listrolemenu = rolemenumanager.getMenu_info(roleid);
		// ���н�ɫ��Ϣ
		List<TbRoleInfo> listrole = rolemanager.getAllRole_Info();
		// ���ڽ�ɫ�˵���ϵ�������ʱ(ȡ�ø�roleidû����ӵĲ˵���Ϣ)
		List<TbMenuInfo> listmenu = menumanager
				.getAllMenu_Info_not_Byroleid(roleid);
		// if (listrole != null && listmenu != null) {
		// ���÷��ؽ�ɫ��ѯ��select�����е�optionֵ
		request.setAttribute("roleList", listrole);
		// ���÷��ز˵���ѯ��select�в��������е�optionֵ
		request.setAttribute("menuList", listmenu);
		// ���÷��ظ���roleid��ѯ�Ѿ����ڵĲ˵���Ϣ
		request.setAttribute("listrolemenu", listrolemenu);
		// ���÷��صĽ�ɫID
		request.setAttribute("roleid", roleid);
		return mapping.findForward("rolemenuaddplmenujsp");
		// } else {
		// return null;
		// }

	}

	/**
	 * ����Ϣ���ص�rolemenu��Ӧ��ϵ����������ҳ��(���ݽ�ɫ���������Ӳ˵�)
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward rolemenu_addtoplmenu_byrolejsp(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws MessageException {
		List<TbRoleInfo> listrole = rolemanager.getAllRole_Info();
		if (listrole != null && listrole.size() > 0) {
			// ���÷��ؽ�ɫ��ѯ��select�����е�optionֵ
			request.setAttribute("roleList", listrole);
			System.out.println("��ɫ��˵��м�¼������Ӷ�Ӧ��ϵ");
			return mapping.findForward("rolemenuaddplmenujsp");
		} else {
			System.out.println("��ɫ��˵�û�м�¼��������Ӷ�Ӧ��ϵ");
			return null;
		}

	}

	/**
	 * ����Ϣ���ص�rolemenu��Ӧ��ϵ������ҳ��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward rolemenu_addtojsp(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws MessageException {
		List<TbRoleInfo> listrole = rolemanager.getAllRole_Info();
		List<TbMenuInfo> listmenu = menumanager.getAllMenu_Info();
		String resultmessage = null;
		if (listrole != null && listrole.size() > 0 && listmenu != null
				&& listmenu.size() > 0) {
			// ���÷��ؽ�ɫ��ѯ��select�����е�optionֵ
			request.setAttribute("listrole", listrole);
			// ���÷��ز˵���ѯ��select�����е�optionֵ
			request.setAttribute("listmenu", listmenu);
			System.out.println("��ɫ��˵��м�¼������Ӷ�Ӧ��ϵ");
			return mapping.findForward("rolemenuaddjsp");
		} else {
			System.out.println("��ɫ��˵�û�м�¼��������Ӷ�Ӧ��ϵ");
			resultmessage = "��ɫ��˵�û�м�¼��������Ӷ�Ӧ��ϵ!";
			request.setAttribute("message", resultmessage);
			return mapping.findForward("showrolemenulist");
		}

	}

	/**
	 * ���ӽ�ɫ��˵���Ӧ��ϵ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward rolemenu_add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String role_id = request.getParameter("roleid");
		String menu_id = request.getParameter("menuid");
		String insert = null;
		String resultmessage = null;
		insert = rolemenumanager.createRoleMenuInfo(role_id, menu_id);
		if (insert != null) {
			System.out.println("GPS�����ն˶�Ӧ��ϵ��ӳɹ�");
			resultmessage = "��ӳɹ�!";
			request.setAttribute("message", resultmessage);
			return mapping.findForward("showrolemenulist");
		} else {
			System.out.println("GPS�����ն˶�Ӧ��ϵ���ʧ��");
			resultmessage = "GPS�����ն˶�Ӧ��ϵ���Ѿ�����,���ʧ��!";
			request.setAttribute("message", resultmessage);
			return mapping.findForward("showrolemenulist");
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
	public ActionForward rolemenu_delete(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		String[] list = request.getParameterValues("list");
		Boolean deletesuce;
		deletesuce = rolemenumanager.deleteRoleMenu(list);
		String resultmessage = null;
		if (deletesuce) {
			System.out.println("ɾ���ɹ�");
			resultmessage = "ɾ���ɹ�!";
			request.setAttribute("message", resultmessage);
			return mapping.findForward("showrolemenulist");
		} else {
			System.out.println("ɾ��ʧ��");
			resultmessage = "ɾ��ʧ��!";
			request.setAttribute("message", resultmessage);
			return mapping.findForward("showrolemenulist");
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
	public ActionForward rolemenu_Sarch(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String cmd = request.getParameter("CMD");
		String role_id = request.getParameter("role_id");
		if (role_id == null || role_id.equals("")) { // Ϊȫ����ɫ
			role_id = "";
		}
		String menu_id = request.getParameter("menu_id");
		if (menu_id == null || menu_id.equals("")) { // Ϊȫ���˵�
			menu_id = "";
		}
		// ��ȡ������
		int rolemenuCount = 0;
		rolemenuCount = rolemenumanager.getRoleMenu_SearchCount(role_id,
				menu_id);
		// ��ȡÿҳ������
		int pageSize = 12;
		// ��ȡ��ҳ��
		int pageCount = rolemenumanager.getPageCount(rolemenuCount, pageSize);
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
		request.setAttribute("rolemenuCount", rolemenuCount);
		// ����ÿҳ����ʾ����
		request.setAttribute("pageSize", pageSize);
		// ���ص�ǰҳ
		request.setAttribute("currentPage", pageNo);

		// ���ص���ҳ��
		request.setAttribute("pageCount", pageCount);

		// ���÷��ص�������
		request.setAttribute("CMD", cmd);
		// ���÷��ؽ�ɫ�˵���ѯ��select��optionֵ
		request.setAttribute("role_id", role_id);
		request.setAttribute("menu_id", menu_id);

		// ���÷��ؽ�ɫ��ѯ��select�����е�optionֵ
		request.setAttribute("roleList", rolemanager.getAllRole_Info());
		// ���÷��ز˵���ѯ��select�����е�optionֵ
		request.setAttribute("menuList", menumanager.getAllMenu_Info());

		request.setAttribute("rolemenuList", rolemenumanager
				.getRoleMenu_SearchByPage(pageNo, pageSize, role_id, menu_id));
		return mapping.findForward("success");

	}
}