package com.elegps.tscweb.action.packagefee;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.elegps.tscweb.action.base.BaseAction;
import com.elegps.tscweb.comm.UserInfo;
import com.elegps.tscweb.exception.MessageException;
import com.elegps.tscweb.model.TbPFInfo;
import com.elegps.tscweb.model.TbUserInfo;

/**
 * level 2015-4-1
 * @author wanglei
 *
 */
public class PackageFeeListAction extends BaseAction{

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MessageException {
		ActionForward actionforward = null;
		String cmd = null;
		TbUserInfo user = UserInfo.getUserInfo(request);
		if (user != null) {
			// 如果没有就接收到参数默认显示全部类型
			if (request.getParameter("CMD") == null || request.getParameter("CMD").equals("")) {
				cmd = "pf_search";
			} else {
				cmd = request.getParameter("CMD");
			}
			
			if (cmd.equals("pf_search")) { // 所有条件同时查询
				actionforward = pf_Sarch(mapping, form, request, response);
			}
			if (cmd.equals("pf_mody")) { // 修改
				actionforward = pf_mody(mapping, form, request, response);
			}
			if (cmd.equals("pf_modyjsp")) { // 修改jsp页面
				actionforward = pf_modyjsp(mapping, form, request, response);
			}
			if (cmd.equals("pf_add")) { // 添加
				actionforward = pf_add(mapping, form, request, response);
			}
			if (cmd.equals("pf_addjsp")) { // 添加jsp页面
				actionforward = pf_addjsp(mapping, form, request, response);
			}
			if (cmd.equals("pf_delete")) { // 删除
				actionforward = pf_delete(mapping, form, request, response);
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
	 * 进入修改jsp页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	private ActionForward pf_modyjsp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String pfid = request.getParameter("type");
		String resultmessage = null;
		if(pfid != null && pfid !=""){
			TbPFInfo pfinfo = new TbPFInfo();
			pfinfo = pfManager.findPFInfoById(pfid);
			if(pfinfo !=null){
				request.setAttribute("pfinfo",pfinfo);
				return mapping.findForward("pfmodyjsp");
			}else{
				System.out.println("此套餐不存在");
				resultmessage = "此套餐不存在";
				request.setAttribute("message", "此套餐不存在");
				return null;
			}
		}else{
			System.out.println("套餐信息加载失败");
			resultmessage = "加载失败";
			request.setAttribute("message", resultmessage);
			return pf_Sarch(mapping, form, request, response);
		}
		
	}

	/**
	 * 进入添加jsp页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	private ActionForward pf_addjsp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return mapping.findForward("pfaddjsp");
	}
	/**
	 * 删除
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	private ActionForward pf_delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		String[] list = request.getParameterValues("list");
		Boolean delpf = pfManager.deletePFInfo(list);
		String resultmessage = null;
		if (delpf) {
			System.out.println("套餐信息删除成功");
			resultmessage = "删除成功";
			request.setAttribute("message", resultmessage);
			return pf_Sarch(mapping, form, request, response);
		} else {
			System.out.println("套餐信息删除失败");
			resultmessage = "删除失败";
			request.setAttribute("message", resultmessage);
			return pf_Sarch(mapping, form, request, response);
		}
	}

	/**
	 * 添加
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	private ActionForward pf_add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		TbPFInfo pf = new TbPFInfo();
		pf.setPfType(request.getParameter("pftype"));
		pf.setPfHow(request.getParameter("pfhow"));
		pf.setPfTime(Integer.parseInt(request.getParameter("pftime").toString()));
		String resultmessage = null;
		String pid= pfManager.addPFInfo(pf);
		if (pid != null && pid !="") {
			System.out.println("套餐信息添加成功");
			resultmessage = "添加成功";
			request.setAttribute("message", resultmessage);
			
			return pf_Sarch(mapping, form, request, response);
		} else {
			System.out.println("套餐信息添加失败");
			resultmessage = "添加失败";
			request.setAttribute("message", resultmessage);
			return pf_Sarch(mapping, form, request, response);
		}
	}

	/**
	 * 修改套餐信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	private ActionForward pf_mody(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String pfid = request.getParameter("pfid");
		String resultmessage = null;
		Boolean pftf = pfManager.findPFById(pfid);
		if(pftf){
			TbPFInfo pf = new TbPFInfo();
			pf.setPfId(Integer.parseInt(pfid));
			pf.setPfType(request.getParameter("pftype"));
			pf.setPfHow(request.getParameter("pfhow"));
			pf.setPfTime(Integer.parseInt(request.getParameter("pftime").toString()));
			Boolean pfup=pfManager.updatePFInfo(pf);
			if(pfup){
				System.out.println("套餐信息修改成功");
				resultmessage = "修改成功";
				request.setAttribute("message", resultmessage);
				return pf_Sarch(mapping, form, request, response);
			}else{
				System.out.println("套餐信息修改失败");
				resultmessage = "修改失败";
				request.setAttribute("message", resultmessage);
				return null;
			}
		}
		return null;
	}

	/**
	 * 所有套餐信息查询
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	private ActionForward pf_Sarch(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		List<TbPFInfo> pfList = new ArrayList();
		pfList = pfManager.findAllPFInfo();//查询所有套餐信息
		
		request.setAttribute("pfList", pfList);//套餐信息list返回
		return mapping.findForward("pflist");
	}
}
