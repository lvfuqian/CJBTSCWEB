package com.elegps.tscweb.action.kofei;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.elegps.tscweb.action.base.BaseAction;
import com.elegps.tscweb.comm.Comm;
import com.elegps.tscweb.comm.DateFormat;
import com.elegps.tscweb.comm.UserInfo;
import com.elegps.tscweb.exception.MessageException;
import com.elegps.tscweb.model.TbEnterpriseInfo;
import com.elegps.tscweb.model.TbKoFeiLogInfo;
import com.elegps.tscweb.model.TbUserInfo;

public class KoFeiAction extends BaseAction{

	//wanglei ��ҵ�û�ִ��
	public static int agent_id = 0;//������id
	public static int r_id = 0;//��ɫid
	public static int ep_id = 0;//��ҵid
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
			//wanglei 
			agent_id =Integer.parseInt(request.getSession().getAttribute("agentId")+"");//������id
			r_id =Integer.parseInt(request.getSession().getAttribute("roleId")+"");//��ɫid
			ep_id =Integer.parseInt(request.getSession().getAttribute("epId")+"");//��ɫid
			
			// ���û�оͽ��յ�����Ĭ����ʾȫ������
			if (request.getParameter("CMD") == null
					|| request.getParameter("CMD").equals("")) {
				cmd = "kf_search";
			} else {
				cmd = request.getParameter("CMD");
			}

			if (cmd.equals("kf_search")) {// ����Ⱥ����Ϣ
				actionforward = kfList(mapping, form, request, response);
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
	
	private ActionForward kfList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String pagentid = ""; // һ��������id
		String childagentid = ""; // ����������id
		String ep = ""; // ��ҵid
		String cmd = request.getParameter("CMD");
//		HttpSession session = request.getSession(true);
		String type=request.getParameter("type");
		if(Comm.isEmpty(type)){
			type ="-1";
		}
		String msid=request.getParameter("msid");
		String imsi=request.getParameter("imsi");
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		if(Comm.isEmpty(startTime)){
			startTime=DateFormat.format(new Date(), "yyyy-MM-dd")+" 00:00:00";
		}
		if(Comm.isEmpty(endTime)){
			endTime=DateFormat.format(new Date(), null);//Ĭ�ϵ�ǰʱ��
		}
		
		String agent = request.getSession().getAttribute("agentId")+"";
		
		
		if (request.getParameter("pagentid") != null && (agent.equals("-1") || agent.equals("0"))) {
			pagentid = request.getParameter("pagentid");
		} else {
			//pagentid = "-1"; // ���������ܲ�
			pagentid = agent;
        	if(pagentid.equals("0"))
        		pagentid = "-1";
		}

		if (request.getParameter("childagentid") != null) {
			childagentid = request.getParameter("childagentid");
		} else {
			childagentid = "-2"; // ������ҵ
		}

		if (request.getParameter("ep") != null) {
			ep = request.getParameter("ep");
		} else {
			if(r_id == 4 || r_id == 3){
				ep =ep_id+"";
			}else{
				ep = "-1"; // ȫ����ҵ
			}
			
		}
		
		int kfCount = 0;
		kfCount = apManager.findKFCountByPage(pagentid, childagentid, ep, msid, Integer.valueOf(type), startTime, endTime,imsi);
		// ��ȡÿҳ������
		String ps =request.getParameter("pageSize");
		int pageSize = 10;
		if(ps!= null){
			pageSize =Integer.parseInt(ps);
		}
		
		// ��ȡ��ҳ��
		int pageCount = (kfCount + pageSize - 1) / pageSize;
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
		request.setAttribute("kfCount", kfCount);
		// ����ÿҳ����ʾ����
		request.setAttribute("pageSize", pageSize);
		// ���ص�ǰҳ
		request.setAttribute("currentPage", pageNo);
		// ���÷��ص�������
		request.setAttribute("CMD", cmd);
		
		request.setAttribute("type", type);
		
		request.setAttribute("startTime", startTime);
		request.setAttribute("endTime", endTime);
		request.setAttribute("pagentid", pagentid);
		request.setAttribute("childagentid", childagentid);
		request.setAttribute("ep", ep);
		
		if(agent_id == 0){
			request.setAttribute("Pagentlist", agentmanger.getParentAgent(agent_id,r_id));
		//}
		// һ��ָ�����������̽����
			if(r_id ==3 || r_id ==4){
				request.setAttribute("Cagentlist",null);
			}else{
			request.setAttribute("Cagentlist", agentmanger.getChildAgentByParamentid(pagentid));
			}
		}else{
			int a = agentmanger.getAgent_ByAgentID(agent_id+"").getAgent_Id();
			request.setAttribute("Pagentlist", agentmanger.getParentAgent(a,r_id));
			request.setAttribute("Cagentlist", agentmanger.getChildAgentByAId(agent));
			pagentid = a+"";
		}
//			request.setAttribute("Pagentlist", agentmanger.getParentAgent(a_id,r_id));
//		//}
//        // һ��ָ�����������̽����
//        request.setAttribute("Cagentlist", agentmanger
//                .getChildAgentByParamentid(pagentid));
        // ����������ҵĬ��
        List<TbEnterpriseInfo> list = null;
		if (r_id == 3 || r_id == 4){
			list = epmanger.getEpinfo_byeid(pagentid,
					childagentid, ep_id, r_id);
		}else{
			list = epmanger.getEpinfo_byagentid(pagentid,
					childagentid, agent_id, r_id);
		}
		request.setAttribute("epList", list);
		List<TbKoFeiLogInfo> kfList = null;
		if(kfCount>0){
			kfList = apManager.findKFListInfo_sertchByPage(pageNo, pageSize, pagentid, childagentid, ep,
					msid, Integer.valueOf(type), startTime, endTime,imsi);
		}
		// ���ص���ҳ��
        request.setAttribute("pageCount", pageCount);
        request.setAttribute("kfList", kfList);
        if(msid==null){
        	msid ="";
        }
        request.setAttribute("msid", msid);
        if(imsi==null){
        	imsi ="";
        }
        request.setAttribute("imsi", imsi);
        return mapping.findForward("kflist");
	}
	
	
}
