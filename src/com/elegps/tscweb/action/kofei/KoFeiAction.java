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

	//wanglei 企业用户执行
	public static int agent_id = 0;//代理商id
	public static int r_id = 0;//角色id
	public static int ep_id = 0;//企业id
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
			agent_id =Integer.parseInt(request.getSession().getAttribute("agentId")+"");//代理商id
			r_id =Integer.parseInt(request.getSession().getAttribute("roleId")+"");//角色id
			ep_id =Integer.parseInt(request.getSession().getAttribute("epId")+"");//角色id
			
			// 如果没有就接收到参数默认显示全部类型
			if (request.getParameter("CMD") == null
					|| request.getParameter("CMD").equals("")) {
				cmd = "kf_search";
			} else {
				cmd = request.getParameter("CMD");
			}

			if (cmd.equals("kf_search")) {// 增加群组信息
				actionforward = kfList(mapping, form, request, response);
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
	
	private ActionForward kfList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String pagentid = ""; // 一级代理商id
		String childagentid = ""; // 二级代理商id
		String ep = ""; // 企业id
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
			endTime=DateFormat.format(new Date(), null);//默认当前时间
		}
		
		String agent = request.getSession().getAttribute("agentId")+"";
		
		
		if (request.getParameter("pagentid") != null && (agent.equals("-1") || agent.equals("0"))) {
			pagentid = request.getParameter("pagentid");
		} else {
			//pagentid = "-1"; // 代理商是总部
			pagentid = agent;
        	if(pagentid.equals("0"))
        		pagentid = "-1";
		}

		if (request.getParameter("childagentid") != null) {
			childagentid = request.getParameter("childagentid");
		} else {
			childagentid = "-2"; // 所有企业
		}

		if (request.getParameter("ep") != null) {
			ep = request.getParameter("ep");
		} else {
			if(r_id == 4 || r_id == 3){
				ep =ep_id+"";
			}else{
				ep = "-1"; // 全部企业
			}
			
		}
		
		int kfCount = 0;
		kfCount = apManager.findKFCountByPage(pagentid, childagentid, ep, msid, Integer.valueOf(type), startTime, endTime,imsi);
		// 获取每页的条数
		String ps =request.getParameter("pageSize");
		int pageSize = 10;
		if(ps!= null){
			pageSize =Integer.parseInt(ps);
		}
		
		// 获取总页数
		int pageCount = (kfCount + pageSize - 1) / pageSize;
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
		request.setAttribute("kfCount", kfCount);
		// 返回每页面显示条数
		request.setAttribute("pageSize", pageSize);
		// 返回当前页
		request.setAttribute("currentPage", pageNo);
		// 设置返回的命令字
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
		// 一级指定二级代理商结果集
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
//        // 一级指定二级代理商结果集
//        request.setAttribute("Cagentlist", agentmanger
//                .getChildAgentByParamentid(pagentid));
        // 返回所有企业默认
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
		// 返回的总页数
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
