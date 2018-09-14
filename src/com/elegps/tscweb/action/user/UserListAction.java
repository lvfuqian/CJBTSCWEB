package com.elegps.tscweb.action.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.elegps.tscweb.action.base.BaseAction;
import com.elegps.tscweb.comm.HRAddress;
import com.elegps.tscweb.comm.MD5;
import com.elegps.tscweb.comm.UserInfo;
import com.elegps.tscweb.exception.MessageException;
import com.elegps.tscweb.form.AdduserForm;
import com.elegps.tscweb.model.TbAgentInfo;
import com.elegps.tscweb.model.TbEnterpriseInfo;
import com.elegps.tscweb.model.TbRoleInfo;
import com.elegps.tscweb.model.TbUserInfo;
import com.elegps.tscweb.model.TbUserLog;

/**
 * level 2008-11-22
 * 
 * @struts.action validate="true"
 */
public class UserListAction extends BaseAction {
	/*
	 * Generated Methods
	 */
	public static int a_id = 0;//������id
	public static int r_id = 0;//��ɫid
	public static int ep_id= 0;
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
			a_id = Integer.parseInt(request.getSession().getAttribute("agentId")+"");//������id
			r_id = Integer.parseInt(request.getSession().getAttribute("roleId")+"");//��ɫid
			ep_id=Integer.parseInt(request.getSession().getAttribute("epId")+"");//��ɫ��ҵid
			// ���û�оͽ��յ�����Ĭ����ʾȫ������
			if (request.getParameter("CMD") == null
					|| request.getParameter("CMD").equals("")) {
				cmd = "user_search";
			} else {
				cmd = request.getParameter("CMD");
			}

			if (cmd.equals("add")) {// �����û���Ϣ
				actionforward = user_add(mapping, form, request, response);
			} else if (cmd.equals("delete")) { // ɾ��ѡ���û���Ϣ
				actionforward = user_delete(mapping, form, request, response);
			} else if (cmd.equals("usermdoyijsp")) { // ��Ҫ�޸ĵ���Ϣ�����޸ĵ�jspҳ��
				actionforward = user_modytojsp(mapping, form, request, response);
			} else if (cmd.equals("agentchangeusermdoy")) { // �޸�ҳ��agentchangeʱ�����޸ĵ�jspҳ��
				actionforward = agentuser_modytojsp(mapping, form, request,
						response);
			} else if (cmd.equals("usermodify")) { // �޸��û���Ϣ
				actionforward = user_modify(mapping, form, request, response);
			} else if (cmd.equals("user_search")) { // ��������ͬʱ��ѯ
				actionforward = user_Sarch(mapping, form, request, response);
			} else if (cmd.equals("user_addjsp")) {// ת������ҳ��
				actionforward = user_Addjsp(mapping, form, request, response);
			}else if (cmd.equals("changepassword")) {// ת���޸�����ҳ��
				actionforward = ChangePassword(mapping, form, request, response);
			}else if(cmd.equals("userLog")){/*������־�б�*/
				actionforward=userLogList(mapping, form, request, response);
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

	public ActionForward user_Addjsp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MessageException {
		String username = request.getParameter("user_name");
		String password = request.getParameter("password");
		String password1 = request.getParameter("password1");
		String agentid = request.getParameter("agentid");
		if (agentid == null) {
			agentid = "0";
		}
		String epid = request.getParameter("epid");
		if (epid == null) {
			epid = "0";
		}
		String roleid = request.getParameter("roleid");
		List<TbRoleInfo> listrole = rolemanager.getAllRole_Info();
		// usermanager.getAllAgent_Info()Ҫ�Ƶ�Agentģ����
		List<TbAgentInfo> listagent = null;
		List<TbAgentInfo> childagentlistid = null;
		int apid = Integer.parseInt(request.getSession().getAttribute("aPId")+"");
		if(apid == 0){
			listagent = agentmanger.getParentAgent(a_id,r_id);
		//}
		// һ��ָ�����������̽����
			childagentlistid = agentmanger.getChildAgentByParamentid(a_id+"");
		}else{
			int a = agentmanger.getAgent_ByAgentID(apid+"").getAgent_Id();
			listagent = agentmanger.getParentAgent(a,r_id);
			childagentlistid = agentmanger.getChildAgentByAId(a_id+"");
		}
		
//		List<TbAgentInfo> listagent = agentmanger.getParentAgent(a_id, r_id);//usermanager.getAllAgent_Info();
//		List<TbAgentInfo> childagentlistid = agentmanger.getChildAgentByAId(a_id+"");
		
		List<TbEnterpriseInfo> listep = null;//epmanger.getEpinfo_byagentid(agentid);

		String resultmessage = null;
		if (listrole.size() > 0 && listrole != null) {
			// ���÷��ؽ�ɫ��ѯ��select�����е�optionֵ
			request.setAttribute("username", username);
			request.setAttribute("password", password);
			request.setAttribute("password1", password1);
			request.setAttribute("agentid", agentid);
			request.setAttribute("epid", epid);
			request.setAttribute("roleid", roleid);
			request.setAttribute("listagent", listagent);
			request.setAttribute("childagentlistid", childagentlistid);
			request.setAttribute("listep", listep);
			request.setAttribute("listrole", listrole);
			return mapping.findForward("useraddjsp");
		} else {
			resultmessage = "��ɫ�����ڣ�������ӽ�ɫ!";
			request.setAttribute("message", resultmessage);
			return mapping.findForward("showuserlist");
		}

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
	public ActionForward user_modify(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MessageException {
		String user_id = request.getParameter("user_id");
		String user_name = request.getParameter("user_name");
		String psw = request.getParameter("user_psw");
		String roleid = request.getParameter("roleid");
		String agentid = request.getParameter("agentid");
		String epid = request.getParameter("epid");
		//û���޸�����Ͳ���Ҫ�ټ��ܣ��޸������˲ż���
		String sqlPsw = usermanager.getUserInfoby_userid(user_id).getUserPassword();
		String newPsw = "";
		if(psw.equals(sqlPsw)){
			newPsw = sqlPsw;
		}else{
			newPsw = md5.str2MD5(psw);
		}
		
		String a = "-1";
		String cagentid = request.getParameter("childagentid"); 
		if(cagentid.equals("")||cagentid.equals("-1")||cagentid.equals(null)){
			a = agentid;
		}else{
			a = cagentid;
		}
		
		String modifyok = usermanager.modifyUser(user_id, user_name.trim(),
				newPsw, roleid, a, epid);
		String resultmessage = null;
		if (modifyok != null) {
			System.out.println("�޸ĳɹ�");
			resultmessage = "�޸ĳɹ�!";
			request.setAttribute("message", resultmessage);
			return mapping.findForward("showuserlist");
		} else {
			System.out.println("�޸�ʧ��");
			resultmessage = "�û���<" + user_name + ">�Ѿ����ڣ��޸�ʧ��!";
			request.setAttribute("message", resultmessage);
			return mapping.findForward("showuserlist");
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
	public ActionForward user_modytojsp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MessageException {

		// ȡ��user_id
		String user_id = request.getParameter("type");
		String roleid = null;
		if (userrolemanager.getRoidinfo_ByUserid(user_id) != null) {
			roleid = userrolemanager.getRoidinfo_ByUserid(user_id);
		} else {
			roleid = "-1";
		}
		List<TbRoleInfo> listrole = rolemanager.getAllRole_Info();
		TbUserInfo userinfo = usermanager.getUserInfoby_userid(user_id);
		String resultmessage = null;
		// usermanager.getAllAgent_Info()Ҫ�Ƶ�Agentģ����
		
		//List<TbAgentInfo> listagent = usermanager.getAllAgent_Info();
		List<TbAgentInfo> listagent = null;
		List<TbAgentInfo> childagentlistid = null;
		int child = -1;
		TbAgentInfo ainfo = agentmanger.getAgent_ByAgentID(userinfo.getAgent_Id()+"");//Integer.parseInt(request.getSession().getAttribute("aPId")+"");
		if(ainfo.getAgent_PId() == 0){
			listagent = agentmanger.getParentAgent(ainfo.getAgent_Id(),Integer.parseInt(roleid));
		//}
		// һ��ָ�����������̽����
			childagentlistid = agentmanger.getChildAgentByParamentid(ainfo.getAgent_Id()+"");
		}else{
			//int a = agentmanger.getAgent_ByAgentID(ainfo.getAgent_Id()+"").getAgent_Id();
			listagent = agentmanger.getParentAgent(ainfo.getAgent_PId(),Integer.parseInt(roleid));
			childagentlistid = agentmanger.getChildAgentByAId(ainfo.getAgent_Id()+"");
			child = ainfo.getAgent_Id();
		}
		
		if (listagent != null) {
			List<TbEnterpriseInfo> listep = epmanger
					.getEpinfo_byagentid(userinfo.getAgent_Id().toString());

			if (userinfo != null) {
				if (listrole.size() > 0 && listrole != null) {
					request.setAttribute("listrole", listrole);
					request.setAttribute("roleid", roleid);
					request.setAttribute("agentid", userinfo.getAgent_Id());
					request.setAttribute("child", child);
					request.setAttribute("tbuserinfo", userinfo);
					request.setAttribute("listagent", listagent);
					request.setAttribute("childagentlistid", childagentlistid);
					request.setAttribute("listep", listep);
					return mapping.findForward("usermdify");
				} else {
					resultmessage = "��ɫ�����ڣ�������ӽ�ɫ!";
					request.setAttribute("message", resultmessage);
					return mapping.findForward("showuserlist");
				}
			}
		} else { // ˵��û���ҵ�Ҫ�޸ĵļ�¼.
			System.out.println("�޸ļ�¼û���ҵ�");
			resultmessage = "�����̻���ҵ������!";
			request.setAttribute("message", resultmessage);
			return mapping.findForward("showuserlist");
		}
		return mapping.findForward("showuserlist");
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
	public ActionForward agentuser_modytojsp(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws MessageException {

		// ȡ��user_id
		String user_id = request.getParameter("type");
		String roleid = null;
		if (userrolemanager.getRoidinfo_ByUserid(user_id) != null) {
			roleid = userrolemanager.getRoidinfo_ByUserid(user_id);
		} else {
			roleid = "-1";
		}
		List<TbRoleInfo> listrole = rolemanager.getAllRole_Info();
		TbUserInfo userinfo = usermanager.getUserInfoby_userid(user_id);
		// usermanager.getAllAgent_Info()Ҫ�Ƶ�Agentģ����
		List<TbAgentInfo> listagent = usermanager.getAllAgent_Info();
		List<TbEnterpriseInfo> listep = null;
		if (request.getParameter("agentid") != null) {
			listep = epmanger.getEpinfo_byagentid(request
					.getParameter("agentid"));
		} else {
			listep = epmanger.getEpinfo_byagentid(userinfo.getAgent_Id()
					.toString());
		}
		String resultmessage = null;
		if (userinfo != null) {
			if (listrole.size() > 0 && listrole != null) {
				request.setAttribute("listrole", listrole);
				request.setAttribute("roleid", roleid);
				request.setAttribute("tbuserinfo", userinfo);
				request.setAttribute("listagent", listagent);
				request
						.setAttribute("agentid", request
								.getParameter("agentid"));
				request.setAttribute("listep", listep);
				return mapping.findForward("usermdify");
			} else {
				resultmessage = "��ɫ�����ڣ�������ӽ�ɫ!";
				request.setAttribute("message", resultmessage);
				return mapping.findForward("showuserlist");
			}

		} else { // ˵��û���ҵ�Ҫ�޸ĵļ�¼.
			System.out.println("�޸ļ�¼û���ҵ�");
			resultmessage = "��¼������!";
			request.setAttribute("message", resultmessage);
			return mapping.findForward("showuserlist");
		}
	}

	public ActionForward user_add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		AdduserForm userform = (AdduserForm) form;
		String user_name = userform.getUser_name();
		String psw = userform.getPassword();
		String roleid = userform.getRoleid();
		String agentid = userform.getAgentid();
		String epid = userform.getEpid();
		String insert = null;
		String resultmessage = null;
		
		String a = "-1";
		String cagentid = request.getParameter("childagentid"); 
		if(cagentid.equals("")||cagentid.equals("-1")||cagentid.equals(null)){
			a = agentid;
		}else{
			a = cagentid;
		}
		insert = usermanager.createUser(user_name.trim(), md5.str2MD5(psw.trim()), roleid,
				a, epid);
		//��¼������־
		TbUserLog userLog=new TbUserLog();
		userLog.setUserId(Integer.parseInt(request.getSession().getAttribute("uId").toString()));
		userLog.setlDate(new Date());
		userLog.setlAddress(HRAddress.getIpAddr(request));
		userLog.setlType(0);
		userLog.setlContent("����û�{"+user_name+"}");
		logManager.save(userLog);
		if (insert != null) {
			System.out.println("�û���Ϣ��ӳɹ�");
			resultmessage = "��ӳɹ�!";
			request.setAttribute("message", resultmessage);
			return mapping.findForward("showuserlist");
		} else {
			System.out.println("�û���Ϣ���ʧ��");
			resultmessage = "�û���<" + user_name + ">�Ѿ����ڣ����ʧ��!";
			request.setAttribute("message", resultmessage);
			return mapping.findForward("showuserlist");
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
	public ActionForward user_delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String[] list = request.getParameterValues("list");
		Boolean deluser = usermanager.deleteUser(list);
		String resultmessage = null;
		if (deluser) {
			System.out.println("�û���Ϣɾ���ɹ�");
			resultmessage = "ɾ���ɹ�";
			request.setAttribute("message", resultmessage);
			return mapping.findForward("showuserlist");
		} else {
			System.out.println("�û���Ϣɾ��ʧ��");
			resultmessage = "ɾ��ʧ��";
			request.setAttribute("message", resultmessage);
			return mapping.findForward("showuserlist");
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
	public ActionForward user_Sarch(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String cmd = request.getParameter("CMD");
		String user_name = request.getParameter("username");// �û���

		if (user_name == null || (user_name.trim()).equals("")) {
			user_name = "";
		}
		
		//wanglei 
		int agent_id =Integer.parseInt(request.getSession().getAttribute("agentId")+"");//������id
		String user_id =request.getSession().getAttribute("uId")+"";//��¼�û�id
		int userCount = 0;
		userCount = usermanager.getUser_SearchCount(user_name,agent_id);
		// ��ȡÿҳ������
		int pageSize = 12;
		// ��ȡ��ҳ��
		int pageCount = usermanager.getPageCount(userCount, pageSize);
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
		request.setAttribute("userCount", userCount);
		// ����ÿҳ����ʾ����
		request.setAttribute("pageSize", pageSize);
		// ���ص�ǰҳ
		request.setAttribute("currentPage", pageNo);
		// ���÷��ص�������
		request.setAttribute("CMD", cmd);
		// ���÷����û�����

		// ���÷����û���ѯ��intput��ֵ
		request.setAttribute("user_name", user_name);

		// ���ص���ҳ��
		request.setAttribute("pageCount", pageCount);
		
		//��¼�û��ŵ�һλ
		List uList = usermanager.getTbUserInfoby_name(
				pageNo, pageSize, user_name, agent_id);
		List<Map> userList = new ArrayList<Map>();
		if(uList!=null){
			for(int i = 0; i<uList.size(); i++){
				Map map = new HashMap();
			    map = (Map) uList.get(i);
				if(map.get("userid").toString().equals(user_id)){
					userList.add(0, map);
				}else{
					userList.add(map);
				}
			}
		}
		request.setAttribute("userList", userList);
		
		return mapping.findForward("succes");
	}

	//ת���޸��û�����ҳ��
	public ActionForward ChangePassword(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MessageException {
		return mapping.findForward("mdify");
	}
	/**
	 * ��ȡ��־��¼�б�
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws MessageException
	 */
	public ActionForward userLogList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws MessageException{
		String cmd = request.getParameter("CMD");
		String userId=request.getParameter("userId");
		String lType=request.getParameter("lType");
		String startDate=request.getParameter("startDate");
		String endDate=request.getParameter("endDate");
		int uId=-1;
		int lt=-1;
		if(userId!=null&&!"".equals(userId)){
			uId=Integer.parseInt(userId);
		}
		if(lType!=null&&!"".equals(lType)){
			lt=Integer.parseInt(lType);
		}
		int logCount = 0;
		logCount =logManager.searchUserLogCount(uId, lt, startDate, endDate);
		// ��ȡÿҳ������
		int pageSize = 12;
		// ��ȡ��ҳ��
		int pageCount = (logCount + pageSize - 1) / pageSize;
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
		List<TbUserLog> logList=logManager.searchUserLogList(pageNo, pageSize, uId, lt, startDate, endDate);
		request.setAttribute("logList", logList);
		// ��ȡ������
		request.setAttribute("userCount", logCount);
		// ����ÿҳ����ʾ����
		request.setAttribute("pageSize", pageSize);
		// ���ص�ǰҳ
		request.setAttribute("currentPage", pageNo);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("CMD", cmd);
		request.setAttribute("userId", userId);
		request.setAttribute("lType", lType);
		request.setAttribute("startDate", startDate);
		request.setAttribute("endDate", endDate);
		request.setAttribute("userList", usermanager.getAllUser_Info());
		return mapping.findForward("tologlist");
	}
}