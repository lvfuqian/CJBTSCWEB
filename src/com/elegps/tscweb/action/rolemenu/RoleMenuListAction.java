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
			// 如果没有就接收到参数默认显示全部角色与菜单对应关系信息
			if (request.getParameter("CMD") == null
					|| request.getParameter("CMD").equals("")) {
				cmd = "rolemenu_search";
			} else {
				cmd = request.getParameter("CMD");
			}

			if (cmd.equals("add")) {// 增加角色与菜单对应信息
				actionforward = rolemenu_add(mapping, form, request, response);
			} else if (cmd.equals("delete")) { // 删除选中记录
				actionforward = rolemenu_delete(mapping, form, request,
						response);
			} else if (cmd.equals("addtojsp")) {// 把信息加载到rolemenu对应关系的增加页面
				actionforward = rolemenu_addtojsp(mapping, form, request,
						response);
			} else if (cmd.equals("rolemenu_search")) { // 所有条件同时查询
				actionforward = rolemenu_Sarch(mapping, form, request, response);
			} else if (cmd.equals("addtoplmenu_byrolejsp")) { // 加载根据角色名批量增加菜单的窗体
				actionforward = rolemenu_addtoplmenu_byrolejsp(mapping, form,
						request, response);
			} else if (cmd.equals("role_search")) {// 当根据角色批量增加菜单信息页面，显示已有的菜单信息(根据角色批量添加菜单)
				actionforward = rolemenu_addtoplmenu_byrole(mapping, form,
						request, response);
			} else if (cmd.equals("plroleby_menuadd")) { // 根据roleid批量增加菜单（提交）
				actionforward = rolemenu_add_bymenuid(mapping, form, request,
						response);
			}
		} else {
			request.setAttribute("message", "Session过期，请重新登录");
			actionforward = mapping.findForward("logging");
		}
		if (actionforward == null) {
			return mapping.findForward("success");
		}
		return actionforward;
	}

	/**
	 * 批量增加角色菜单对应关系(根据roleid批量增加菜单)
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
	 * 把信息加载到rolemenu对应关系的批量增加页面(根据roleid批量菜单)
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
		// 根据roleid查询在角色菜单对应关系中已经存在的菜单
		List listrolemenu = rolemenumanager.getMenu_info(roleid);
		// 所有角色信息
		List<TbRoleInfo> listrole = rolemanager.getAllRole_Info();
		// 用于角色菜单关系批量添加时(取得该roleid没有添加的菜单信息)
		List<TbMenuInfo> listmenu = menumanager
				.getAllMenu_Info_not_Byroleid(roleid);
		// if (listrole != null && listmenu != null) {
		// 设置返回角色查询的select中所有的option值
		request.setAttribute("roleList", listrole);
		// 设置返回菜单查询的select中不存在所有的option值
		request.setAttribute("menuList", listmenu);
		// 设置返回根据roleid查询已经存在的菜单信息
		request.setAttribute("listrolemenu", listrolemenu);
		// 设置返回的角色ID
		request.setAttribute("roleid", roleid);
		return mapping.findForward("rolemenuaddplmenujsp");
		// } else {
		// return null;
		// }

	}

	/**
	 * 把信息加载到rolemenu对应关系的批量增加页面(根据角色名批量增加菜单)
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
			// 设置返回角色查询的select中所有的option值
			request.setAttribute("roleList", listrole);
			System.out.println("角色与菜单有记录可以添加对应关系");
			return mapping.findForward("rolemenuaddplmenujsp");
		} else {
			System.out.println("角色与菜单没有记录不可以添加对应关系");
			return null;
		}

	}

	/**
	 * 把信息加载到rolemenu对应关系的增加页面
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
			// 设置返回角色查询的select中所有的option值
			request.setAttribute("listrole", listrole);
			// 设置返回菜单查询的select中所有的option值
			request.setAttribute("listmenu", listmenu);
			System.out.println("角色或菜单有记录可以添加对应关系");
			return mapping.findForward("rolemenuaddjsp");
		} else {
			System.out.println("角色或菜单没有记录不可以添加对应关系");
			resultmessage = "角色或菜单没有记录不可以添加对应关系!";
			request.setAttribute("message", resultmessage);
			return mapping.findForward("showrolemenulist");
		}

	}

	/**
	 * 增加角色与菜单对应关系
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
			System.out.println("GPS厂商终端对应关系添加成功");
			resultmessage = "添加成功!";
			request.setAttribute("message", resultmessage);
			return mapping.findForward("showrolemenulist");
		} else {
			System.out.println("GPS厂商终端对应关系添加失败");
			resultmessage = "GPS厂商终端对应关系添已经存在,添加失败!";
			request.setAttribute("message", resultmessage);
			return mapping.findForward("showrolemenulist");
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
	public ActionForward rolemenu_delete(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		String[] list = request.getParameterValues("list");
		Boolean deletesuce;
		deletesuce = rolemenumanager.deleteRoleMenu(list);
		String resultmessage = null;
		if (deletesuce) {
			System.out.println("删除成功");
			resultmessage = "删除成功!";
			request.setAttribute("message", resultmessage);
			return mapping.findForward("showrolemenulist");
		} else {
			System.out.println("删除失败");
			resultmessage = "删除失败!";
			request.setAttribute("message", resultmessage);
			return mapping.findForward("showrolemenulist");
		}
	}

	/**
	 * 根据条件查询
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
		if (role_id == null || role_id.equals("")) { // 为全部角色
			role_id = "";
		}
		String menu_id = request.getParameter("menu_id");
		if (menu_id == null || menu_id.equals("")) { // 为全部菜单
			menu_id = "";
		}
		// 获取总条数
		int rolemenuCount = 0;
		rolemenuCount = rolemenumanager.getRoleMenu_SearchCount(role_id,
				menu_id);
		// 获取每页的条数
		int pageSize = 12;
		// 获取总页数
		int pageCount = rolemenumanager.getPageCount(rolemenuCount, pageSize);
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
		request.setAttribute("rolemenuCount", rolemenuCount);
		// 返回每页面显示条数
		request.setAttribute("pageSize", pageSize);
		// 返回当前页
		request.setAttribute("currentPage", pageNo);

		// 返回的总页数
		request.setAttribute("pageCount", pageCount);

		// 设置返回的命令字
		request.setAttribute("CMD", cmd);
		// 设置返回角色菜单查询的select中option值
		request.setAttribute("role_id", role_id);
		request.setAttribute("menu_id", menu_id);

		// 设置返回角色查询的select中所有的option值
		request.setAttribute("roleList", rolemanager.getAllRole_Info());
		// 设置返回菜单查询的select中所有的option值
		request.setAttribute("menuList", menumanager.getAllMenu_Info());

		request.setAttribute("rolemenuList", rolemenumanager
				.getRoleMenu_SearchByPage(pageNo, pageSize, role_id, menu_id));
		return mapping.findForward("success");

	}
}