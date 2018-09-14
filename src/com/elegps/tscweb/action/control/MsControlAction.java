package com.elegps.tscweb.action.control;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.elegps.tscweb.action.base.BaseAction;
import com.elegps.tscweb.comm.UserInfo;
import com.elegps.tscweb.model.ControlInfo;
import com.elegps.tscweb.model.TbMsControlInfo;
import com.elegps.tscweb.model.TbUserInfo;

public class MsControlAction extends BaseAction {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ActionForward af = null;
		int cmd = 0;
		TbUserInfo userInfo = UserInfo.getUserInfo(request);
		if (userInfo != null) {
			// 如果没有就接收到参数默认显示全部类型
			if (request.getParameter("CMD") == null
					|| request.getParameter("CMD").equals("")) {
				cmd = 0;

			} else {
				cmd = Integer.parseInt(request.getParameter("CMD"));
			}
			switch (cmd) {
			case 0:
				af = listControl(mapping, form, request, response);
				break;
			case 1:
				af=toupdate(mapping, form, request, response);
				break;
			case 2:
				af = updateControl(mapping, form, request, response);
				break;
			default:
				af = listControl(mapping, form, request, response);
				break;
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
	 * 跳转到修改页面
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward toupdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String msId = request.getParameter("msIds");
		String msName=request.getParameter("msNames");
		try{
		TbMsControlInfo controlInfo = msControlBiz.getControl(msId);
		if (controlInfo != null) {
			request.setAttribute("controlInfo", controlInfo);
			request.setAttribute("msNames", msName);
			return mapping.findForward("took");
		} else {
			request.setAttribute("msg", "该记录不存在");
			return mapping.findForward("tonull");
		}
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 修改该记录
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 * @throws NumberFormatException 
	 */
	public ActionForward updateControl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws NumberFormatException, Exception {
			String msId=request.getParameter("msIds");
			String r01=request.getParameter("r01");
			String r02=request.getParameter("r02");
			String r03=request.getParameter("r03");
			String r04=request.getParameter("r04");
			String r05=request.getParameter("r05");
			String hql="UPDATE TbMsControlInfo SET r01=?,r02=?,r03=?,r04=?,r05=? WHERE msId=?";
			int bool=msControlBiz.update(hql, Integer.valueOf(r01),Float.valueOf(r02),Float.valueOf(r03),Integer.valueOf(r04),r05,msId);
			if(bool>0){
				request.setAttribute("msg", "修改成功!");
				request.setAttribute("CMD", 0);
				return mapping.findForward("updateok");
			}else{
				request.setAttribute("msg", "修改失败!");
				request.setAttribute("CMD", 0);
				return mapping.findForward("tonull");
			}
	}

	/**
	 * 获取列表
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward listControl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String pagentid = ""; // 一级代理商id
		String childagentid = ""; // 二级代理商id
		String grpid = request.getParameter("grpid");// 群组号码
		String msId = request.getParameter("msId");
		String msName = request.getParameter("msName");
		String setr01 = request.getParameter("setr01");

		//wanglei 
		int agent_id =Integer.parseInt(request.getSession().getAttribute("agentId")+"");//代理商id
		int r_id =Integer.parseInt(request.getSession().getAttribute("roleId")+"");//角色id
		int ep_id =Integer.parseInt(request.getSession().getAttribute("epId")+"");//角色id
		
		if (request.getParameter("pagentid") != null) {
			pagentid = request.getParameter("pagentid");
		} else {
			pagentid = "-1"; // 代理商是总部
		}

		if (request.getParameter("childagentid") != null) {
			childagentid = request.getParameter("childagentid");
		} else {
			childagentid = "-2"; // 所有企业
		}

		String ep = request.getParameter("ep");
		if (ep == null || ep.equals("null")) {
			ep = "-1";
		}
		int msCount = 0;
		try {
			msCount = msControlBiz.executeControlCount(msId, msName, ep, grpid,setr01);
			// 获取每页的条数
			int pageSize = 12;
			// 获取总页数
			int pageCount = (msCount + pageSize - 1) / pageSize;
			// 从页面取得当前页
			int pageNo = 1;
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

			List<ControlInfo> list = null;

			list = msControlBiz.findList(pageNo, pageSize, msId, msName, ep,	grpid, setr01);

			// 获取总条数
			request.setAttribute("msCount", msCount);
			// 返回每页面显示条数
			request.setAttribute("pageSize", pageSize);
			// 返回当前页
			request.setAttribute("currentPage", pageNo);

			// 返回的总页数
			request.setAttribute("pageCount", pageCount);

			request.setAttribute("list", list);
			request.setAttribute("pagentid", pagentid);
			request.setAttribute("childagentid", childagentid);
			request.setAttribute("ep", ep);
			if (grpid== null || grpid.equals("null")) {
				grpid = "-1";
			}
			request.setAttribute("grpid", grpid);
			// 一级代理商名称
			request.setAttribute("Pagentlist", agentmanger.getParentAgent(agent_id,r_id));
			// 一级指定二级代理商结果集
			request.setAttribute("Cagentlist",agentmanger.getChildAgentByParamentid(pagentid));
			// 返回所有企业默认
			request.setAttribute("epList",epmanger.getEpinfo_byeid(pagentid, childagentid, ep_id, r_id));
			// 设置返回群组信息
			request.setAttribute("grpList",grpmanager.getAllGrp_Info(pagentid, childagentid, ep));
			request.setAttribute("msId", msId);
			request.setAttribute("msName", msName);
			request.setAttribute("setr01", (setr01==null||"".equals(setr01))?"-1":setr01);
			request.setAttribute("CMD", 0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward("listok");
	}
}
