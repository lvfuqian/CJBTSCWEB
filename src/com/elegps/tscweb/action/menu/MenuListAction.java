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
			// 如果没有就接收到参数默认显示全部类型
			if (request.getParameter("CMD") == null
					|| request.getParameter("CMD").equals("")) {
				cmd = "menu_search";
			} else {
				cmd = request.getParameter("CMD");
			}

			if (cmd.equals("add")) {// 增加菜单信息
				actionforward = menu_add(mapping, form, request, response);
			} else if (cmd.equals("delete")) { // 删除选中菜单信息
				actionforward = menu_delete(mapping, form, request, response);
			} else if (cmd.equals("tomenumdoyijsp")) { // 把要修改的信息传到修改的jsp页面
				actionforward = menu_modytojsp(mapping, form, request, response);
			} else if (cmd.equals("menumodify")) { // 修改菜单信息
				actionforward = menu_modify(mapping, form, request, response);
			} else if (cmd.equals("menu_search")) { // 所有条件同时查询
				actionforward = menu_Sarch(mapping, form, request, response);
			} else if (cmd.equals("menu_addjsp")) {// 转到增加页面
				actionforward = menu_Addjsp(mapping, form, request, response);
			}

		} else {
			request.setAttribute("message", "Session过期，请重新登录");
			actionforward = mapping.findForward("logging");
		}
		if (actionforward == null) {
			return mapping.findForward("succes");
		}
		return actionforward;
	}

	/**
	 * 加载父菜单id信息
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
	 * 修改菜单信息
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
			System.out.println("修改成功");
			resultmessage = "修改成功!";
			request.setAttribute("message", resultmessage);
			return mapping.findForward("showmenulist");
		} else {
			System.out.println("修改失败");
			resultmessage = "菜单名<" + meun_name + ">已经存在，修改失败!";
			request.setAttribute("message", resultmessage);
			return mapping.findForward("showmenulist");
		}
	}

	// OK
	/**
	 * 把修改菜单信息传jsp页面
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
		// 取得grpid
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
		} else { // 说明没有找到要修改的记录.
			System.out.println("修改记录没有找到");
			resultmessage = "记录不存在!";
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
			System.out.println("菜单信息添加成功");
			resultmessage = "添加成功!";
			request.setAttribute("message", resultmessage);
			return mapping.findForward("showmenulist");
		} else {
			System.out.println("菜单信息添加失败");
			resultmessage = "菜单名<" + menu_name + ">已经存在，添加失败!";
			request.setAttribute("message", resultmessage);
			return mapping.findForward("showmenulist");
		}

	}

	/**
	 * 物理删除
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
			System.out.println("菜单信息删除成功");
			resultmessage = "删除成功";
			request.setAttribute("message", resultmessage);
			return mapping.findForward("showmenulist");
		} else {
			System.out.println("菜单信息删除失败");
			resultmessage = "删除失败";
			request.setAttribute("message", resultmessage);
			return mapping.findForward("showmenulist");
		}
	}

	/**
	 * 根据菜单名列表信息的查询条件查询
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
		String menu_name = request.getParameter("menu_name");// 菜单名
		if (menu_name == null || (menu_name.trim()).equals("")) {
			menu_name = "";
		}

		int menuCount = 0;
		menuCount = menumanager.getMenu_SearchCount(menu_name);
		// 获取每页的条数
		int pageSize = 12;
		// 获取总页数
		int pageCount = menumanager.getPageCount(menuCount, pageSize);
		// 从页面取得当前页
		int pageNo;
		String pageNoStr = request.getParameter("pageNo");
		if (pageNoStr == null || pageNoStr.trim().equals("")) {
			pageNo = 1;
		} else {
			pageNo = Integer.parseInt(request.getParameter("pageNo"));
		}
		// 如果请求页已经超出了最大页
		if (pageNo > pageCount) {
			pageNo = pageCount;
		}
		// 如果请求页小于一页
		if (pageNo < 1) {
			pageNo = 1;
		}
		// 获取总条数
		request.setAttribute("menuCount", menuCount);
		// 返回每页面显示条数
		request.setAttribute("pageSize", pageSize);
		// 返回当前页
		request.setAttribute("currentPage", pageNo);
		// 设置返回的命令字
		request.setAttribute("CMD", cmd);
		// 设置返回用户类型

		// 设设置返回用户查询的select中option值
		request.setAttribute("menu_name", menu_name);

		// 返回的总页数
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("menuList", menumanager.getTbMenuInfoby_name(
				pageNo, pageSize, menu_name));
		return mapping.findForward("succes");
	}
}