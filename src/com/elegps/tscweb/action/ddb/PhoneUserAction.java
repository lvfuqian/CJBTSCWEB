package com.elegps.tscweb.action.ddb;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.elegps.tscweb.action.base.BaseAction;
import com.elegps.tscweb.comm.UserInfo;
import com.elegps.tscweb.model.TabPhoneUser;
import com.elegps.tscweb.model.TbUserInfo;

public class PhoneUserAction extends BaseAction {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ActionForward af = null;
		String cmd = null;
		TbUserInfo userInfo = UserInfo.getUserInfo(request);
		if (userInfo != null) {
			// 如果没有就接收到参数默认显示全部类型
			if (request.getParameter("CMD") == null
					|| request.getParameter("CMD").equals("")) {
				cmd = "0";
			} else {
				cmd = request.getParameter("CMD");
			}

			// 添加
			if (cmd.equals("1")) {
				af = addPhoneUser(mapping, form, request, response);
			}
			// 修改
			if (cmd.equals("2")) {
				af = updatePhoneUser(mapping, form, request, response);
			}
			// 删除
			if (cmd.equals("3")) {
				af = deletePhoneUser(mapping, form, request, response);
			}
			if(cmd.equals("4")){
				af=toaddjsp(mapping, form, request, response);
			}
			if(cmd.equals("5")){
				af=toPhoneUser(mapping, form, request, response);
			}

			// 转发数据到页面
			if (cmd.equals("0")) {
				af = searchPhoneUser(mapping, form, request, response);
			}

		} else {
			request.setAttribute("message", "Session过期，请重新登录");
			af = mapping.findForward("logging");
		}
		if (af == null) {
			af = mapping.findForward("succes");
		}
		return af;
	}

	/**
	 * 查询电召用户
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward searchPhoneUser(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String userName = request.getParameter("userName");
		String userSex = request.getParameter("userSex");
		String userMobile = request.getParameter("userMobile");
		try {
			int count = phoneUserManage.total(userName, userSex, userMobile);
			// 获取每页的条数
			int pageSize = 12;
			// 获取总页数
			int pageCount = (count + pageSize - 1) / pageSize;
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
			request.setAttribute("msCount", count);
			// 返回每页面显示条数
			request.setAttribute("pageSize", pageSize);
			// 返回当前页
			request.setAttribute("currentPage", pageNo);
			request.setAttribute("pageCount", pageCount);
			// 设置返回的命令字
			request.setAttribute("CMD", "0");
			List<TabPhoneUser> phoneUsers = phoneUserManage.listAll(pageNo,
					pageSize, userName, userSex, userMobile);
			request.setAttribute("phoneUser", phoneUsers);
			request.setAttribute("userName", userName);
			request.setAttribute("userSex", userSex);
			request.setAttribute("userMobile", userMobile);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return mapping.findForward("spu_success");
	}

	/**
	 * 添加电召用户
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward addPhoneUser(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String userId=phoneUserManage.getPrimaryKey();
		String userName=request.getParameter("userNames");
		String userSex=request.getParameter("userSexs");
		String userPwd=request.getParameter("userPwd");
		String userIdCard=request.getParameter("userIdCard");
		String userMobile=request.getParameter("userMobiles");
		String userAddress=request.getParameter("userAddress");
		TabPhoneUser phoneUser=new TabPhoneUser();
		phoneUser.setUserId((Integer.parseInt(userId)+1)+"");
		phoneUser.setUserName(userName);
		phoneUser.setUserSex(Integer.parseInt(userSex));
		phoneUser.setUserPwd(userPwd);
		phoneUser.setUserIdCard(userIdCard);
		phoneUser.setUserMobile(userMobile);
		phoneUser.setUserAddress(userAddress);
		phoneUserManage.create(phoneUser);
		return mapping.findForward("spu_add_sessuce");
	}
	/**
	 * 跳转到修改页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward toPhoneUser(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		try {
			String userId=request.getParameter("userId");
			if(userId!=null&&!"".equals(userId)){
				TabPhoneUser phoneUser=phoneUserManage.getBeanInfo(userId);
				if(phoneUser!=null)
					request.setAttribute("phoneUser", phoneUser);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward("to_update_success");
	}

	/**
	 * 修改电召用户
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward updatePhoneUser(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try {
			String userId=request.getParameter("userId");
			String userName=request.getParameter("userNames");
			String userSex=request.getParameter("userSexs");
			String userPwd=request.getParameter("userPwd");
			String userIdCard=request.getParameter("userIdCard");
			String userMobile=request.getParameter("userMobiles");
			String userAddress=request.getParameter("userAddress");
			TabPhoneUser phoneUser=new TabPhoneUser();
			phoneUser.setUserId(userId);
			phoneUser.setUserName(userName);
			phoneUser.setUserSex(Integer.parseInt(userSex));
			phoneUser.setUserPwd(userPwd);
			phoneUser.setUserIdCard(userIdCard);
			phoneUser.setUserMobile(userMobile);
			phoneUser.setUserAddress(userAddress);
			int bool=phoneUserManage.update(phoneUser);
			if(bool>0){
				System.out.println("bool\t"+bool);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward("spu_update_success");
	}
	
	/**
	 * 跳转到添加页面
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward toaddjsp(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		return mapping.findForward("to_add_success");
	}

	/**
	 * 删除电召用户
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward deletePhoneUser(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try {
			String [] userId = request.getParameterValues("list");
			if(userId!=null&&userId.length>0){
				for (int i = 0; i < userId.length; i++) {					
					TabPhoneUser phoneUser = phoneUserManage.getBeanInfo(userId[i].trim());
					if (phoneUser != null) {
						phoneUserManage.delete(phoneUser);
					}
				}
			}
			return mapping.findForward("spu_delete_success");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
