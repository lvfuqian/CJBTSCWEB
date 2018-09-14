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
			// ���û�оͽ��յ�����Ĭ����ʾȫ������
			if (request.getParameter("CMD") == null
					|| request.getParameter("CMD").equals("")) {
				cmd = "0";
			} else {
				cmd = request.getParameter("CMD");
			}

			// ���
			if (cmd.equals("1")) {
				af = addPhoneUser(mapping, form, request, response);
			}
			// �޸�
			if (cmd.equals("2")) {
				af = updatePhoneUser(mapping, form, request, response);
			}
			// ɾ��
			if (cmd.equals("3")) {
				af = deletePhoneUser(mapping, form, request, response);
			}
			if(cmd.equals("4")){
				af=toaddjsp(mapping, form, request, response);
			}
			if(cmd.equals("5")){
				af=toPhoneUser(mapping, form, request, response);
			}

			// ת�����ݵ�ҳ��
			if (cmd.equals("0")) {
				af = searchPhoneUser(mapping, form, request, response);
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
	 * ��ѯ�����û�
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
			// ��ȡÿҳ������
			int pageSize = 12;
			// ��ȡ��ҳ��
			int pageCount = (count + pageSize - 1) / pageSize;
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
			request.setAttribute("msCount", count);
			// ����ÿҳ����ʾ����
			request.setAttribute("pageSize", pageSize);
			// ���ص�ǰҳ
			request.setAttribute("currentPage", pageNo);
			request.setAttribute("pageCount", pageCount);
			// ���÷��ص�������
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
	 * ��ӵ����û�
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
	 * ��ת���޸�ҳ��
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
	 * �޸ĵ����û�
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
	 * ��ת�����ҳ��
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
	 * ɾ�������û�
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
