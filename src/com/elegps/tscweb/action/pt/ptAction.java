package com.elegps.tscweb.action.pt;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.elegps.tscweb.action.base.BaseAction;
import com.elegps.tscweb.comm.UserInfo;
import com.elegps.tscweb.exception.MessageException;
import com.elegps.tscweb.model.TbPhoneTypeInfo;
import com.elegps.tscweb.model.TbUserInfo;

public class ptAction extends BaseAction{
	
	TbUserInfo user = new TbUserInfo();

	public ActionForward execute(ActionMapping mapping, ActionForm form,
	            HttpServletRequest request, HttpServletResponse response)throws MessageException {
		ActionForward actionforward = null;
		String cmd = null;
		user = UserInfo.getUserInfo(request);
		if (user != null) {
			// 如果没有就接收到参数默认显示全部类型
			if (request.getParameter("CMD") == null || request.getParameter("CMD").equals("")) {
				cmd = "pt_search";
			} else {
				cmd = request.getParameter("CMD");
			}
			
			if (cmd.equals("pt_search")) { // 查询手机型号页面
				actionforward = ptlistjsp(mapping,request, response);
			}
			if (cmd.equals("pt_config")) { // 型号适配（开启/关闭）
				actionforward = ptconfig(mapping,request, response);
			}
			if (cmd.equals("xianxi")) { // 详细信息
				actionforward = xianxi(mapping,request, response);
			}
			
			if (cmd.equals("delete")) { // 详细信息
				actionforward = ptDelete(mapping,request, response);
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

	private ActionForward ptDelete(ActionMapping mapping,
			HttpServletRequest request, HttpServletResponse response) {
		String[] list = request.getParameterValues("list");
		Boolean delpf = ptManager.deletePtInfo(list);
		// 获取总条数
		request.setAttribute("ptCount", 0);
		// 返回每页面显示条数
		request.setAttribute("pageSize", Integer.valueOf(request.getParameter("pageSize")));
		// 返回的总页数
		request.setAttribute("pageCount", 0);
		// 返回当前页
		request.setAttribute("currentPage", Integer.valueOf(request.getParameter("pageNo")));
		request.setAttribute("type", request.getParameter("type"));
		request.setAttribute("CMD", "");
		request.setAttribute("ptList", null);
		
		String resultmessage = null;
		if (delpf) {
			System.out.println("型号信息删除成功");
			resultmessage = "删除成功";
			request.setAttribute("message", resultmessage);
			return ptlistjsp(mapping, request, response);
		} else {
			System.out.println("型号信息删除失败");
			resultmessage = "删除失败";
			request.setAttribute("message", resultmessage);
			return ptlistjsp(mapping,request, response);
		}
	}

	private ActionForward xianxi(ActionMapping mapping,
			HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		TbPhoneTypeInfo pt = ptManager.findOneById(Integer.valueOf(id));
		request.setAttribute("ptInfo", pt);
		return mapping.findForward("ptinfo");
	}

	private ActionForward ptconfig(ActionMapping mapping,
			HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		
		TbPhoneTypeInfo pt = ptManager.findOneById(Integer.valueOf(id));
		int flag = Integer.valueOf(request.getParameter("flag"));
		if(pt==null){
			request.setAttribute("message", "数据异常！");
		}else{
			if(flag==pt.getFalg()){
				if(flag==1){
					pt.setFalg(0);
				}else if(flag==0){
					pt.setFalg(1);
				}
				ptManager.updatePt(pt);
			}
			request.setAttribute("message", "修改型号配置完成！");
		}
		
		// 获取总条数
		request.setAttribute("ptCount", 0);
		// 返回每页面显示条数
		request.setAttribute("pageSize", Integer.valueOf(request.getParameter("pageSize")));
		// 返回的总页数
		request.setAttribute("pageCount", 0);
		// 返回当前页
		request.setAttribute("currentPage", Integer.valueOf(request.getParameter("pageNo")));
		request.setAttribute("type", request.getParameter("type"));
		request.setAttribute("CMD", "");
		request.setAttribute("ptList", null);
		return mapping.findForward("ptlist");
	}

	private ActionForward ptlistjsp(ActionMapping mapping,
			HttpServletRequest request, HttpServletResponse response) {
		String type = "";
		if(request.getParameter("type")!=null && !request.getParameter("type").equals("")){
			type = request.getParameter("type");
		}
		String cmd = request.getParameter("CMD");
		
		int ptCount = 0;
		ptCount =ptManager.getPtCount(type);
		// 获取每页的条数
		String ps =request.getParameter("pageSize");
		int pageSize = 10;
		if(ps!= null){
			pageSize =Integer.parseInt(ps);
		}
		
		// 获取总页数
		int pageCount = (ptCount + pageSize - 1) / pageSize;
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
		request.setAttribute("ptCount", ptCount);
		// 返回每页面显示条数
		request.setAttribute("pageSize", pageSize);
		// 返回当前页
		request.setAttribute("currentPage", pageNo);

		// 返回的总页数
		request.setAttribute("pageCount", pageCount);

		// 设置返回的命令字
		request.setAttribute("CMD", cmd);
		request.setAttribute("type", type);
		
		List<TbPhoneTypeInfo> ptList = null;
		if(ptCount>0){
			ptList = ptManager.getPtList(pageNo, pageSize, type);
		}
		request.setAttribute("ptList", ptList);
		return mapping.findForward("ptlist");
	}
	
	
	
}
