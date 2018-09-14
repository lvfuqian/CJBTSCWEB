package com.elegps.tscweb.action.menu;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.elegps.tscweb.action.base.BaseAction;
import com.elegps.tscweb.comm.UserInfo;
import com.elegps.tscweb.exception.MessageException;
import com.elegps.tscweb.form.AddmenuForm;
import com.elegps.tscweb.model.TbMenuInfo;
import com.elegps.tscweb.model.TbUserInfo;

/**
 * level 2008-11-18
 * 
 * @struts.action validate="true"
 */
public class MenuListAction extends BaseAction {
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
				cmd = "menu_search";
			} else {
				cmd = request.getParameter("CMD");
			}

			if (cmd.equals("add")) {// ���Ӳ˵���Ϣ
				actionforward = menu_add(mapping, form, request, response);
			} else if (cmd.equals("delete")) { // ɾ��ѡ�в˵���Ϣ
				actionforward = menu_delete(mapping, form, request, response);
			} else if (cmd.equals("tomenumdoyijsp")) { // ��Ҫ�޸ĵ���Ϣ�����޸ĵ�jspҳ��
				actionforward = menu_modytojsp(mapping, form, request, response);
			} else if (cmd.equals("menumodify")) { // �޸Ĳ˵���Ϣ
				actionforward = menu_modify(mapping, form, request, response);
			} else if (cmd.equals("menu_search")) { // ��������ͬʱ��ѯ
				actionforward = menu_Sarch(mapping, form, request, response);
			} else if (cmd.equals("menu_addjsp")) {// ת������ҳ��
				actionforward = menu_Addjsp(mapping, form, request, response);
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
	 * ���ظ��˵�id��Ϣ
	 * 
	 */
	public ActionForward menu_Addjsp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MessageException {
		request
				.setAttribute("menubypmenuidList", menumanager
						.getAllMenu_Info());
		return mapping.findForward("menuaddjsp");

	}

	/**
	 * �޸Ĳ˵���Ϣ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws MessageException
	 */
	public ActionForward menu_modify(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MessageException {
		String menu_id = request.getParameter("menu_id");
		String meun_name = request.getParameter("meun_name");
		String url = request.getParameter("url");
		String pmenu_id = request.getParameter("pmenu_id");
		String modifyok = menumanager.modifyMenu(menu_id, meun_name.trim(),
				url, pmenu_id);
		String resultmessage = null;
		if (modifyok != null) {
			System.out.println("�޸ĳɹ�");
			resultmessage = "�޸ĳɹ�!";
			request.setAttribute("message", resultmessage);
			return mapping.findForward("showmenulist");
		} else {
			System.out.println("�޸�ʧ��");
			resultmessage = "�˵���<" + meun_name + ">�Ѿ����ڣ��޸�ʧ��!";
			request.setAttribute("message", resultmessage);
			return mapping.findForward("showmenulist");
		}
	}

	// OK
	/**
	 * ���޸Ĳ˵���Ϣ��jspҳ��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws MessageException
	 */
	public ActionForward menu_modytojsp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MessageException {
		// ȡ��grpid
		String menu_id = request.getParameter("type");
		TbMenuInfo menuinfo = null;
		menuinfo = menumanager.getMenuInfoby_menuid(menu_id);
		request
				.setAttribute("menubypmenuidList", menumanager
						.getAllMenu_Info());
		String resultmessage = null;
		if (menuinfo != null) {
			request.setAttribute("tbmenuinfo", menuinfo);
			return mapping.findForward("menumdify");
		} else { // ˵��û���ҵ�Ҫ�޸ĵļ�¼.
			System.out.println("�޸ļ�¼û���ҵ�");
			resultmessage = "��¼������!";
			request.setAttribute("message", resultmessage);
			return mapping.findForward("showmenulist");
		}
	}

	public ActionForward menu_add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		AddmenuForm menuform = (AddmenuForm) form;
		String menu_name = menuform.getMenuname();
		String url = menuform.getUrl();
		String pmenuid = menuform.getPmenu_id();
		String insert = null;
		String resultmessage = null;
		insert = menumanager.createMenu(menu_name.trim(), pmenuid, url);
		if (insert != null) {
			System.out.println("�˵���Ϣ��ӳɹ�");
			resultmessage = "��ӳɹ�!";
			request.setAttribute("message", resultmessage);
			return mapping.findForward("showmenulist");
		} else {
			System.out.println("�˵���Ϣ���ʧ��");
			resultmessage = "�˵���<" + menu_name + ">�Ѿ����ڣ����ʧ��!";
			request.setAttribute("message", resultmessage);
			return mapping.findForward("showmenulist");
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
	public ActionForward menu_delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String[] list = request.getParameterValues("list");
		Boolean delgrp = menumanager.deleteMenu(list);
		String resultmessage = null;
		if (delgrp) {
			System.out.println("�˵���Ϣɾ���ɹ�");
			resultmessage = "ɾ���ɹ�";
			request.setAttribute("message", resultmessage);
			return mapping.findForward("showmenulist");
		} else {
			System.out.println("�˵���Ϣɾ��ʧ��");
			resultmessage = "ɾ��ʧ��";
			request.setAttribute("message", resultmessage);
			return mapping.findForward("showmenulist");
		}
	}

	/**
	 * ���ݲ˵����б���Ϣ�Ĳ�ѯ������ѯ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward menu_Sarch(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String cmd = request.getParameter("CMD");
		String menu_name = request.getParameter("menu_name");// �˵���
		if (menu_name == null || (menu_name.trim()).equals("")) {
			menu_name = "";
		}

		int menuCount = 0;
		menuCount = menumanager.getMenu_SearchCount(menu_name);
		// ��ȡÿҳ������
		int pageSize = 12;
		// ��ȡ��ҳ��
		int pageCount = menumanager.getPageCount(menuCount, pageSize);
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
		request.setAttribute("menuCount", menuCount);
		// ����ÿҳ����ʾ����
		request.setAttribute("pageSize", pageSize);
		// ���ص�ǰҳ
		request.setAttribute("currentPage", pageNo);
		// ���÷��ص�������
		request.setAttribute("CMD", cmd);
		// ���÷����û�����

		// �����÷����û���ѯ��select��optionֵ
		request.setAttribute("menu_name", menu_name);

		// ���ص���ҳ��
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("menuList", menumanager.getTbMenuInfoby_name(
				pageNo, pageSize, menu_name));
		return mapping.findForward("succes");
	}
}