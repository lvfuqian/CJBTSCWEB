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
			// 如果没有就接收到参数默认显示全部用户与角色对应关系信息
			if (request.getParameter("CMD") == null
					|| request.getParameter("CMD").equals("")) {
				cmd = "userrole_search";
			} else {
				cmd = request.getParameter("CMD");
			}
			if (cmd.equals("add")) {// 增加用户与角色对应信息
				actionforward = userrole_add(mapping, form, request, response);
			} else if (cmd.equals("delete")) { // 删除选中记录
				actionforward = userrole_delete(mapping, form, request,
						response);
			} else if (cmd.equals("addtojsp")) {// 把信息加载到rolemenu对应关系的增加页面
				actionforward = userrole_addtojsp(mapping, form, request,
						response);
			} else if (cmd.equals("userrole_search")) { // 所有条件同时查询
				actionforward = userrole_Sarch(mapping, form, request, response);
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
	 * 把信息加载到userrole对应关系的增加页面
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
		// usermanager.getNotUser_Info()获取没有分配
		List<TbUserInfo> listuser = usermanager.getNotUser_Info();
		List<TbRoleInfo> listrole = rolemanager.getAllRole_Info();
		String resultmessage = null;
		if (listrole != null && listrole.size() > 0 && listuser != null
				&& listuser.size() > 0) {
			// 设置返回用户查询的select中所有的option值
			request.setAttribute("listuser", listuser);
			// 设置返回角色查询的select中所有的option值
			request.setAttribute("listrole", listrole);
			System.out.println("用户与角色有记录可以添加对应关系");
			return mapping.findForward("userroleaddjsp");
		} else {
			System.out.println("用户与角色没有记录不可以添加对应关系");
			resultmessage = "用户与角色没有记录不可以添加对应关系!";
			request.setAttribute("message", resultmessage);
			return mapping.findForward("showuserrolelist");
		}

	}

	/**
	 * 增加用户与角色对应关系
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
			System.out.println("用户与角色对应关系添加成功");
			resultmessage = "添加成功!";
			request.setAttribute("message", resultmessage);
			return mapping.findForward("showuserrolelist");
		} else {
			System.out.println("用户与角色对应关系添加失败");
			resultmessage = "用户与角色对应关系添已经存在,添加失败!";
			request.setAttribute("message", resultmessage);
			return mapping.findForward("showuserrolelist");
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
	public ActionForward userrole_delete(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		String[] list = request.getParameterValues("list");
		Boolean deletesuce;
		deletesuce = userrolemanager.deleteUserRole(list);
		String resultmessage = null;
		if (deletesuce) {
			System.out.println("删除成功");
			resultmessage = "删除成功!";
			request.setAttribute("message", resultmessage);
			return mapping.findForward("showuserrolelist");
		} else {
			System.out.println("删除失败");
			resultmessage = "删除失败!";
			request.setAttribute("message", resultmessage);
			return mapping.findForward("showuserrolelist");
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
	public ActionForward userrole_Sarch(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String cmd = request.getParameter("CMD");
		String user_id = request.getParameter("user_id");
		if (user_id == null) { // 为全部用户
			user_id = "";
		}
		String role_id = request.getParameter("role_id");
		if (role_id == null) { // 为全部角色
			role_id = "";
		}
		// 获取总条数
		int userroleCount = 0;
		userroleCount = userrolemanager.getUserRole_SearchCount(user_id,
				role_id);
		// 获取每页的条数
		int pageSize = 12;
		// 获取总页数
		int pageCount = userrolemanager.getPageCount(userroleCount, pageSize);
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
		request.setAttribute("userroleCount", userroleCount);
		// 返回每页面显示条数
		request.setAttribute("pageSize", pageSize);
		// 返回当前页
		request.setAttribute("currentPage", pageNo);

		// 返回的总页数
		request.setAttribute("pageCount", pageCount);

		// 设置返回的命令字
		request.setAttribute("CMD", cmd);
		// 设置返回角色菜单查询的select中option值
		request.setAttribute("user_id", user_id);
		request.setAttribute("role_id", role_id);

		// 设置返回用户查询的select中所有的option值
		request.setAttribute("userList", usermanager.getAllUser_Info());
		// 设置返回角色查询的select中所有的option值
		request.setAttribute("roleList", rolemanager.getAllRole_Info());

		request.setAttribute("userroleList", userrolemanager
				.getUserRole_SearchByPage(pageNo, pageSize, user_id, role_id));
		return mapping.findForward("success");

	}
}