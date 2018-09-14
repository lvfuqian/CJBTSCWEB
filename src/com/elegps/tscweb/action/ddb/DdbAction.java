package com.elegps.tscweb.action.ddb;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.elegps.tscweb.action.base.BaseAction;
import com.elegps.tscweb.comm.HRAddress;
import com.elegps.tscweb.comm.UserInfo;
import com.elegps.tscweb.exception.MessageException;
import com.elegps.tscweb.model.TabSysconfig;
import com.elegps.tscweb.model.TabSysserverdbinfo;
import com.elegps.tscweb.model.TabSysuserroleId;
import com.elegps.tscweb.model.TabSysusersinfo;
import com.elegps.tscweb.model.TbEnterpriseInfo;
import com.elegps.tscweb.model.TbMsInfo;
import com.elegps.tscweb.model.TbUserInfo;
import com.elegps.tscweb.model.TbUserLog;

public class DdbAction extends BaseAction {
	
	
	//wanglei ��ҵ�û�ִ��
	public static int agent_id = 0;//������id
	public static int r_id = 0;//��ɫid
	public static int ep_id = 0;//��ɫid
	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MessageException {
		ActionForward af = null;
		String cmd = null;
		TbUserInfo userInfo = UserInfo.getUserInfo(request);
		if (userInfo != null) {
			
			//wanglei agent_id r_id ep_id
			agent_id =Integer.parseInt(request.getSession().getAttribute("agentId")+"");//������id
			r_id =Integer.parseInt(request.getSession().getAttribute("roleId")+"");//��ɫid
			ep_id =Integer.parseInt(request.getSession().getAttribute("epId")+"");//��ɫid
			
			// ���û�оͽ��յ�����Ĭ����ʾȫ������
			if (request.getParameter("CMD") == null
					|| request.getParameter("CMD").equals("")) {
				cmd = "ddb_search";
			} else {
				cmd = request.getParameter("CMD");
			}

			// ���
			if (cmd.equals("ddb_add")) {
				af = addAction(mapping, form, request, response);
			}
			// ɾ��
			if (cmd.equals("ddb_delete")) {
				af = deleteAction(mapping, form, request, response);
			}
			// �޸�
			if (cmd.equals("ddb_update")) {
				af = updateAction(mapping, form, request, response);
			}
			// �޸�
			if (cmd.equals("to_add")) {
				af = toAddAction(mapping, form, request, response);
			}
			// ת�����ݵ�ҳ��
			if (cmd.equals("ddb_to")) {
				af = toAction(mapping, form, request, response);
			}
			// ת�����ݵ�ҳ��
			if (cmd.equals("ddb_search")) {
				af = searchAction(mapping, form, request, response);
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
	 * ��ӵ����û���Ϣ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward addAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MessageException {
		TabSysconfig tbc = new TabSysconfig();
		TabSysusersinfo tbu = new TabSysusersinfo();
		TabSysuserroleId tbi = new TabSysuserroleId();
		String ms_id = request.getParameter("msid");
		String loginId = request.getParameter("loginId");
		String password = request.getParameter("pass");
		Integer dbid = new Integer(request.getParameter("sid"));
		String roleid = request.getParameter("roleid");
		try {
			if (ms_id != null && !"".equals(ms_id) && dbid != null
					&& !"".equals(dbid)) {
				TbMsInfo msInfo = msmanager.getTBMsinfoby_msid(ms_id);
				TabSysserverdbinfo tabSysDBInfo = (TabSysserverdbinfo) serverDBManager
						.getBean(dbid);
				if (msInfo != null && tabSysDBInfo != null) {
					List<TabSysconfig> tbs = ddbManager.getBeanTotal(ms_id);
					if (tbs == null || tbs.size() <= 0) {
						String hql = "UPDATE TbMsInfo  SET c01=? WHERE msId=?";
						tbu.setUserName(msInfo.getMsName());
						tbu.setLoginId(loginId);
						tbu.setPassword(password);
						// ���TabSysusersinfo ��Ϣ
						ddbManager.create(tbu);
						//��¼������־
						TbUserLog userLog=new TbUserLog();
						userLog.setUserId(Integer.parseInt(request.getSession().getAttribute("uId").toString()));
						userLog.setlDate(new Date());
						userLog.setlAddress(HRAddress.getIpAddr(request));
						userLog.setlType(14);
						userLog.setlContent("��ӵ���ID{ "+msInfo.getMsId()+" }�ĵ�¼�˺�Ϊ{"+loginId+"}");
						logManager.save(userLog);
						// ��ӹ�ϵ����Ϣ
						int uid = ddbManager.getMaxId(TabSysusersinfo.class);
						// if(uid>0)
						tbi.setUserId(uid);
						if (roleid != null && !"".equals(roleid)) {
							tbi.setRoleId(new Integer(roleid));
						} else {
							tbi.setRoleId(1);
						}
						ddbManager.create(tbi);
						// ���·����Ϣ
						tbc.setUserId(uid);
						tbc.setMsid(msInfo.getMsId());
						tbc.setMspwd(msInfo.getPasswd());
						tbc.setApmIp(tabSysDBInfo.getsIp());
						tbc.setApmPort(new Integer(tabSysDBInfo.getsApmPort()));

						tbc.setNowDataDbip(tabSysDBInfo.getsDbIp());
						tbc.setNowDataDbport(new Integer(tabSysDBInfo
								.getsNowPort()));
						tbc.setNowDataDbname(tabSysDBInfo.getsNowdbName());
						tbc.setNowDataDbuser(tabSysDBInfo.getsNowUser());
						tbc.setNowDataDbpwd(tabSysDBInfo.getsNowPass());

						tbc.setHisDataDbip(tabSysDBInfo.getsDbIp());
						tbc.setHisDataDbport(new Integer(tabSysDBInfo
								.getsHisPort()));
						tbc.setHisDataDbname(tabSysDBInfo.getsHisdbName());
						tbc.setHisDataDbuser(tabSysDBInfo.getsHisUser());
						tbc.setHisDataDbpwd(tabSysDBInfo.getsHisPass());
						msmanager.update(hql, 1, msInfo.getMsId());
						tbc.setTabSysusersinfo(tbu);
						ddbManager.create(tbc);
						request.setAttribute("message", "��ӳɹ���");
						return mapping.findForward("ddb_add_sessuce");
					} else {
						request.setAttribute("message", "���ʧ��!���û��Ѿ�ע��");
						return mapping.findForward("ddb_add_sessuce");
					}
				}
			}
		} catch (Exception e) {
			request.setAttribute("message", "���ʧ��");
			e.printStackTrace();
		}
		return mapping.findForward("ddb_add_sessuce");
	}

	/**
	 * ɾ�������û���Ϣ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward deleteAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws MessageException {
//		DdbUserForm ddbUserForm = (DdbUserForm) form;
		String[] userId = request.getParameterValues("list");
		String hql_udpate = "UPDATE  TbMsInfo SET c01=?  WHERE  msId=?";
		try {
			if (userId != null && userId.length > 0) {
				for (int i = 0; i < userId.length; i++) {
					TabSysconfig sc = ddbManager.getBean(new Integer(userId[i]
							.trim()).intValue());
					//��¼������־
					TbUserLog userLog=new TbUserLog();
					userLog.setUserId(Integer.parseInt(request.getSession().getAttribute("uId").toString()));
					userLog.setlDate(new Date());
					userLog.setlAddress(HRAddress.getIpAddr(request));
					userLog.setlType(16);
					userLog.setlContent("ɾ������ID{ "+sc.getMsid()+" }�ĵ�¼�˺�");
					logManager.save(userLog);
					if (sc != null) {
						msmanager.update(hql_udpate, "0", sc.getMsid());
					}
					ddbManager.delete(new Integer(userId[i].trim()).intValue());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward("ddb_delete_ok");
	}

	/**
	 * �޸ĵ����û���Ϣ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward updateAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws MessageException {
		String userId = request.getParameter("userId");
		String ms_id = request.getParameter("msid");
		String loginId = request.getParameter("loginId");
		String password = request.getParameter("pass");
		String roleid = request.getParameter("roleid");
		Integer dbid = new Integer(request.getParameter("sid"));
		try {
			if (ms_id != null && !"".equals(ms_id) && dbid != null
					&& !"".equals(dbid)) {
				TbMsInfo msInfo = msmanager.getTBMsinfoby_msid(ms_id);
				TabSysserverdbinfo ddbInfo = (TabSysserverdbinfo) serverDBManager
						.getBean(dbid);
				// List<TabSysconfig> tbs = ddbManager.getBeanTotal(ms_id);
				String hql_role = "UPDATE TabSysuserroleId  SET roleId=?  WHERE userId=?";
				String hql_user = "UPDATE TabSysusersinfo SET userName=?,loginId=?,password=?  WHERE userId=?";
				String hql_config = "UPDATE TabSysconfig  SET msid=?,mspwd=?,apmIp=?,apmPort=?,nowDataDbip=?,nowDataDbname=?,nowDataDbport=?,"
						+ "nowDataDbuser=?,nowDataDbpwd=?,hisDataDbip=?,hisDataDbname=?,hisDataDbport=?,hisDataDbuser=?,hisDataDbpwd=?  WHERE  userId=?";
				String hql_c01 = "UPDATE  TbMsInfo SET c01=?  WHERE  msId=?";
				TabSysconfig tbc = ddbManager.getBean(new Integer(userId));
				TbMsInfo tbms = msmanager.getTBMsinfoby_msid(tbc.getMsid());
				if (msInfo != null && ddbInfo != null) {
					msmanager.update(hql_c01, 0, tbms.getMsId());
					int bool_user = ddbManager.update(hql_user, msInfo
							.getMsName(), loginId, password,
							new Integer(userId));
					if (bool_user > 0) {
						ddbManager.update(hql_role, new Integer(roleid),
								new Integer(userId));
						ddbManager.update(hql_config, msInfo.getMsId(), msInfo
								.getPasswd(), ddbInfo.getsIp(), ddbInfo
								.getsApmPort(), ddbInfo.getsDbIp(), ddbInfo
								.getsNowdbName(), ddbInfo.getsNowPort(),
								ddbInfo.getsNowUser(), ddbInfo.getsNowPass(),
								ddbInfo.getsDbIp(), ddbInfo.getsHisdbName(),
								ddbInfo.getsHisPort(), ddbInfo.getsHisUser(),
								ddbInfo.getsHisPass(), new Integer(userId));
						msmanager.update(hql_c01, 1, msInfo.getMsId());
						//��¼������־
						TbUserLog userLog=new TbUserLog();
						userLog.setUserId(Integer.parseInt(request.getSession().getAttribute("uId").toString()));
						userLog.setlDate(new Date());
						userLog.setlAddress(HRAddress.getIpAddr(request));
						userLog.setlType(15);
						userLog.setlContent("�޸ĵ���ID{ "+msInfo.getMsId()+" }�ĵ�¼�˺�Ϊ{"+loginId+"}");
						logManager.save(userLog);
						request.setAttribute("message", "�޸ĳɹ���");
						return mapping.findForward("ddb_update_sessuce");
					} else {
						request.setAttribute("message", "�޸�ʧ�ܣ�");
						return mapping.findForward("ddb_update_sessuce");
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward("ddb_update_sessuce");
	}

	/**
	 * ת�������û�������Ϣ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward toAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String userId = request.getParameter("userId");
		String hql = " FROM TabSysserverdbinfo WHERE sIp=?";
		String hql_role = " FROM TabSysuserroleId WHERE userId=?";
		try {
			List<TabSysserverdbinfo> listdb = serverDBManager.getListBean(-1,
					-1);
			TabSysconfig config = ddbManager.getBean(new Integer(userId));
			if (config != null) {
				TbMsInfo tbmsin = null;
				TbEnterpriseInfo epinfo = null;
				int agenttype = 0;
				tbmsin = msmanager.getTBMsinfoby_msid(config.getMsid());
				if (tbmsin != null) {
					epinfo = epmanger.getEpinfo_byepid(tbmsin.getEpid()
							.toString());
				}
				if (epinfo != null) {
					agenttype = agentmanger.getAgenttype(String.valueOf(epinfo
							.getAgent_Id()));
					request.setAttribute("ep_id", epinfo.getEp_Id());
				}
				TabSysserverdbinfo tdb = serverDBManager.getBean(hql, config
						.getApmIp());
				TabSysuserroleId tbrole = serverDBManager.getRoleBean(hql_role,
						new Integer(userId));
				request.setAttribute("dbid", tdb.getsId());
				request.setAttribute("loginId", config.getTabSysusersinfo()
						.getLoginId());
				request.setAttribute("pass", config.getTabSysusersinfo()
						.getPassword());
				request.setAttribute("ms_id", config.getMsid());
				request.setAttribute("agent", agenttype);
				request.setAttribute("config", config);
				request.setAttribute("listdb", listdb);
				request.setAttribute("rolelist", ddbManager.listRole(-1, -1));
				request.setAttribute("roleid", tbrole.getRoleId());
				// һ������������
//				request.setAttribute("agentList", agentmanger.getParentAgent(agenttype,r_id));
				// һ��ָ�����������̽����
//				request.setAttribute("Cagentlist", 
//						agentmanger.getAll_Agent().getChildAgentByParamentid(String.valueOf(epinfo.getAgent_Id())));
				if (agenttype != 0) { // �����������µ���ҵ
					// һ������������
					request
							.setAttribute("agentList", agentmanger
									.getParentAgent(agenttype,r_id));
					request.setAttribute("Cagentlist", agentmanger
							.getChildAgentByParamentid(String.valueOf(agenttype)));
					request.setAttribute("pagentid", agenttype);
					request.setAttribute("childagentid", epinfo.getAgent_Id());
					request.setAttribute("epList", epmanger
							.getEpinfo_byeid(String.valueOf(agenttype),
									String.valueOf(epinfo.getAgent_Id()), ep_id, r_id));
				} else { // һ��������ֱ����ҵ
					// һ������������
					request
							.setAttribute("agentList", agentmanger
									.getParentAgent(agent_id,r_id));
					// һ��ָ�����������̽����
					request.setAttribute("Cagentlist", agentmanger
							.getChildAgentByParamentid(epinfo.getAgent_Id()+""));
					request.setAttribute("pagentid", epinfo.getAgent_Id());
					request.setAttribute("childagentid", "-1");
					request.setAttribute("epList", epmanger
							.getEpinfo_byeid(String.valueOf(epinfo
									.getAgent_Id()), String.valueOf(-1), ep_id, r_id));
				}
				// ����������ҵĬ��
//				List list = null;
//				if (r_id == 3 || r_id == 4){
//					list = epmanger.getEpinfo_byeid(
//							String.valueOf(agenttype), String.valueOf(epinfo.getAgent_Id()), ep_id, r_id);
//				}else{
//					list = epmanger.getEpinfo_byagentid(String.valueOf(agenttype),
//							String.valueOf(epinfo.getAgent_Id()), agent_id, r_id);
//				}
//				request.setAttribute("epList", list);
				
//				request.setAttribute("epList", epmanger.getEpinfo_byeid(
//						String.valueOf(agenttype), String.valueOf(epinfo.getAgent_Id()), ep_id, r_id));
				
				
				request.setAttribute("epid", epinfo.getEp_Id());
				request.setAttribute("ddmsList", msmanager.listMsInfoByEpId(
						new Integer(epinfo.getEp_Id()), 2, "1", config
								.getMsid()));
				return mapping.findForward("to_update_ok");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward("ddb_add_sessuce");

	}

	/**
	 * ת�������û�������Ϣ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward toAddAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
//		DdbUserForm ddbUserForm = (DdbUserForm) form;
		String pagentid = ""; // һ��������id
		String childagentid = ""; // ����������id

		if (request.getParameter("pagentid") != null) {
			pagentid = request.getParameter("pagentid");
		} else {
			pagentid = "-1"; // ���������ܲ�
		}

		if (request.getParameter("childagentid") != null) {
			childagentid = request.getParameter("childagentid");
		} else {
			childagentid = "-1"; // ֱ����ҵ
		}

		String ep = request.getParameter("ep");
		if (ep == null || ep.equals("null")) {
			ep = "-1";
		}
		// һ������������
		
		request.setAttribute("agentList", agentmanger.getParentAgent(agent_id,r_id));
		// һ��ָ�����������̽����
		request.setAttribute("Cagentlist", agentmanger
				.getChildAgentByParamentid(pagentid));
		// ����������ҵĬ��
		request.setAttribute("epList", epmanger.getEpinfo_byeid(pagentid,
				childagentid, ep_id, r_id));
		request.setAttribute("pagentid", pagentid);
		request.setAttribute("childagentid", childagentid);
		request.setAttribute("epid", ep);
		request.setAttribute("ddmsList", msmanager.listMsInfoByEpId(
				new Integer(ep), 2, "1"));
		request.setAttribute("rolelist", ddbManager.listRole(-1, -1));
		List<TabSysserverdbinfo> listdb = serverDBManager.getListBean(-1, -1);
		if (listdb != null) {
			request.setAttribute("listdb", listdb);
			return mapping.findForward("to_add_ok");
		}

		return mapping.findForward("ddb_add_sessuce");
	}

	/**
	 * ��ѯ�����û���Ϣ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 * @throws ServletException
	 */
	public ActionForward searchAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
//		DdbUserForm ddbUserForm = (DdbUserForm) form;
		//��ѯ����
		String userName = request.getParameter("userNames");
		String msid = request.getParameter("msids");
		String loginid=request.getParameter("loginids");
		
		request.setAttribute("userNames", userName);
		request.setAttribute("msids", msid);
		request.setAttribute("loginids", loginid);
		//wanglei agent_id r_id ep_id
		StringBuffer epid = new StringBuffer();
		List<TbMsInfo> msList = null;
		StringBuffer ms_id = new StringBuffer();
		List<TabSysconfig> ddblist = null;
		if(r_id ==2){//������
			List<TbEnterpriseInfo> epList = epmanger.getEpinfo_byagentid(agent_id+"");//�õ������̵���ҵ
			if(epList!= null){
				for(int i = 0 ; i< epList.size(); i++){
					epid.append("'").append(epList.get(i).getEp_Id()).append("'"); 
					if(i!= epList.size()-1){
						epid.append(",");
					}
				}
			
				msList = msmanager.getMsInfo_byEpid(epid);//�õ���ҵ���ն�
				
				for(int i = 0 ; i< msList.size(); i++){
					ms_id.append("'").append(msList.get(i).getMsId()).append("'"); //msID String
					if(i!= msList.size()-1){
						ms_id.append(",");
					}
				}
			}else{
				// ��ȡ������
				request.setAttribute("msCount", 0);
				// ����ÿҳ����ʾ����
				request.setAttribute("pageSize", 12);
				// ���ص�ǰҳ
				request.setAttribute("currentPage", 0);
				request.setAttribute("pageCount", 0);
				// ���÷��ص�������
				request.setAttribute("CMD", "ddb_search");
				request.setAttribute("dbinfo",null);
				return mapping.findForward("ddb_search_sessuce");
			}
		}else if(r_id ==3 || r_id ==4){//��ҵ
			epid.append("'").append(ep_id).append("'");
			 msList = msmanager.getMsInfo_byEpid(epid);//�õ���ҵ���ն�
			 if(msList!=null){
			 for(int i = 0 ; i< msList.size(); i++){
				ms_id.append("'").append(msList.get(i).getMsId()).append("'"); //msID String
				if(i!= msList.size()-1){
					ms_id.append(",");
				}
			 }
			 }else{
				// ��ȡ������
					request.setAttribute("msCount", 0);
					// ����ÿҳ����ʾ����
					request.setAttribute("pageSize", 12);
					// ���ص�ǰҳ
					request.setAttribute("currentPage", 0);
					request.setAttribute("pageCount", 0);
					// ���÷��ص�������
					request.setAttribute("CMD", "ddb_search");
					request.setAttribute("dbinfo",null);
					return mapping.findForward("ddb_search_sessuce");
			 }
		}else{//����Ա
			//��ѯ����
		}
		
		
		int msCount = 0;
		msCount = ddbManager.totalCount(userName, msid,loginid,ms_id);
		// ��ȡÿҳ������
		int pageSize = 12;
		// ��ȡ��ҳ��
		int pageCount = (msCount + pageSize - 1) / pageSize;
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
		request.setAttribute("msCount", msCount);
		// ����ÿҳ����ʾ����
		request.setAttribute("pageSize", pageSize);
		// ���ص�ǰҳ
		request.setAttribute("currentPage", pageNo);
		request.setAttribute("pageCount", pageCount);
		// ���÷��ص�������
		request.setAttribute("CMD", "ddb_search");
		
		try {
			if ((userName != null && !"".equals(userName))
					|| (msid != null && !"".equals(msid))|| (loginid != null && !"".equals(loginid))) {
				ddblist = ddbManager.listAll(pageNo, pageSize, userName.trim(), msid.trim(),loginid.trim(),ms_id);
			} else {
				ddblist = ddbManager.getListBean(pageNo, pageSize,ms_id);
			}
			if (ddblist != null) {
				request.setAttribute("ddblist", ddblist);
					List<TabSysserverdbinfo> dbinfo=serverDBManager.getListBean(-1,-1);
					request.setAttribute("dbinfo",dbinfo);
				return mapping.findForward("ddb_search_sessuce");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward("ddb_search_sessuce");
	}

}