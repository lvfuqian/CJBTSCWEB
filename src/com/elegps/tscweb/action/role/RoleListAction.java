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
			// 如果没有就接收到参数默认显示全部类型
			if (request.getParameter("CMD") == null
					|| request.getParameter("CMD").equals("")) {
				cmd = "role_search";
			} else {
				cmd = request.getParameter("CMD");
			}

			if (cmd.equals("add")) {// 增加角色信息
				actionforward = role_add(mapping, form, request, response);
			} else if (cmd.equals("delete")) { // 删除选中角色信息
				actionforward = role_delete(mapping, form, request, response);
			} else if (cmd.equals("torolemdoyijsp")) { // 把要修改的信息传到修改的jsp页面
				actionforward = role_modytojsp(mapping, form, request, response);
			} else if (cmd.equals("rolemodify")) { // 修改角色信息
				actionforward = role_modify(mapping, form, request, response);
			} else if (cmd.equals("role_search")) { // 所有条件同时查询
				actionforward = role_Sarch(mapping, form, request, response);
			} else if (cmd.equals("role_addjsp")) {// 转到增加页面
				actionforward = role_Addjsp(mapping, form, request, response);
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
	 * 加载角色id信息
	 * 
	 */
	public ActionForward role_Addjsp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MessageException {
		return mapping.findForward("roleaddjsp");

	}

	/**
	 * 修改角色信息
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
			System.out.println("修改成功");
			resultmessage = "修改成功!";
			request.setAttribute("message", resultmessage);
			return mapping.findForward("showrolelist");
		} else {
			System.out.println("修改失败");
			resultmessage = "角色名<" + role_name + ">已经存在，修改失败!";
			request.setAttribute("message", resultmessage);
			return mapping.findForward("showrolelist");
		}
	}

	/**
	 * 把修改角色信息传jsp页面
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
		// 取得role_id
		String role_id = request.getParameter("type");
		TbRoleInfo roleinfo = null;
		roleinfo = rolemanager.getRoleInfoby_roleid(role_id);
		String resultmessage = null;
		if (roleinfo != null) {
			request.setAttribute("tbroleinfo", roleinfo);
			return mapping.findForward("rolemdify");
		} else { // 说明没有找到要修改的记录.
			System.out.println("修改记录没有找到");
			resultmessage = "记录不存在!";
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
			System.out.println("角色信息添加成功");
			resultmessage = "添加成功!";
			request.setAttribute("message", resultmessage);
			return mapping.findForward("showrolelist");
		} else {
			System.out.println("角色信息添加失败");
			resultmessage = "角色名<" + role_name + ">已经存在，添加失败!";
			request.setAttribute("message", resultmessage);
			return mapping.findForward("showrolelist");
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
	public ActionForward role_delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String[] list = request.getParameterValues("list");
		Boolean delrole = rolemanager.deleteRole(list);
		String resultmessage = null;
		if (delrole) {
			System.out.println("角色信息删除成功");
			resultmessage = "删除成功";
			request.setAttribute("message", resultmessage);
			return mapping.findForward("showrolelist");
		} else {
			System.out.println("角色信息删除失败");
			resultmessage = "删除失败";
			request.setAttribute("message", resultmessage);
			return mapping.findForward("showrolelist");
		}
	}

	/**
	 * 根据角色名列表信息的查询条件查询
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
		String role_name = request.getParameter("role_name");// 菜单名
		if (role_name == null || (role_name.trim()).equals("")) {
			role_name = "";
		}

		int roleCount = 0;
		roleCount = rolemanager.getRole_SearchCount(role_name);
		// 获取每页的条数
		int pageSize = 12;
		// 获取总页数
		int pageCount = rolemanager.getPageCount(roleCount, pageSize);
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
		request.setAttribute("roleCount", roleCount);
		// 返回每页面显示条数
		request.setAttribute("pageSize", pageSize);
		// 返回当前页
		request.setAttribute("currentPage", pageNo);
		// 设置返回的命令字
		request.setAttribute("CMD", cmd);
		// 设置返回用户类型

		// 设置返回用户查询的intput中值
		request.setAttribute("role_name", role_name);

		// 返回的总页数
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("roleList", rolemanager.getTbRoleInfoby_name(
				pageNo, pageSize, role_name));
		return mapping.findForward("succes");
	}
}