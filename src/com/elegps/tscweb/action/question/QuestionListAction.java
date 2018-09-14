package com.elegps.tscweb.action.question;



import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.elegps.tscweb.action.base.BaseAction;
import com.elegps.tscweb.comm.UserInfo;
import com.elegps.tscweb.exception.MessageException;
import com.elegps.tscweb.form.AddQuestionForm;
import com.elegps.tscweb.form.AddmsForm;
import com.elegps.tscweb.model.TbAgentInfo;
import com.elegps.tscweb.model.TbEnterpriseInfo;
import com.elegps.tscweb.model.TbMsInfo;
import com.elegps.tscweb.model.TbQuestionInfo;
import com.elegps.tscweb.model.TbUserInfo;
import com.elegps.tscweb.serivce.QuestionManager;
import com.elegps.tscweb.serivce.impl.QuestionManagerImpl;


/**
 * MyEclipse Struts Creation date: 10-11-2008
 * 
 * XDoclet definition:
 * 
 * @struts.action validate="true"
 */
public class QuestionListAction extends BaseAction {
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
		TbUserInfo user=UserInfo.getUserInfo(request);
		if(user!=null){
			// 如果没有就接收到参数默认显示全部类型
			if (request.getParameter("CMD") == null
					|| request.getParameter("CMD").equals("")) {
				cmd = "question_search";
			} else {
				cmd = request.getParameter("CMD");
			}

			if (cmd.equals("add")) {// 增加用户信息
				actionforward = question_add(mapping, form, request, response);
			} else if (cmd.equals("xingxi")) // 查看用户详细信息
			{
				actionforward = question_xingxi(mapping, form, request, response);
			}else if (cmd.equals("mdoyijsp")) { //把要修改的信息传到修改的jsp页面
				actionforward = question_modytojsp(mapping, form, request, response);
			} else if(cmd.equals("modify")){  //修改终端用户信息
			    actionforward=question_modify(mapping, form, request, response);
			}else if(cmd.equals("question_search")){  //所有条件同时查询
				actionforward = question_Sarch(mapping, form, request, response);
			}else if(cmd.equals("addjsp")){
				actionforward = addquestionjsp(mapping, form, request, response);
			}
			if(actionforward==null){
				actionforward=mapping.findForward("succes");
			}
		}else{
			request.setAttribute("message","Session过期，请重新登录");
			actionforward=mapping.findForward("logging");
		}
		
		return actionforward;
	}

	
	
	 /**
     * 增加jsp页面
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
	public ActionForward addquestionjsp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		return mapping.findForward("addquestionjsp");
	}
	
	
	/**
	 * 把修改信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws MessageException
	 */
	public ActionForward question_modify(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MessageException {
		    TbUserInfo user=UserInfo.getUserInfo(request);
		    String id=request.getParameter("id");
		    String type=request.getParameter("type");
			String question=request.getParameter("question");			
			String resolvent=request.getParameter("resolvent");
		    String solve_man=request.getParameter("solve_man").trim();
		    String state=request.getParameter("state");
		    String cep=request.getParameter("cep");
		    String cname=request.getParameter("cname");
		    String telephone=request.getParameter("telephone");
			String modifyok=questionmanger.modifyQuestion(user.getUserName(),id,type,question,resolvent,solve_man,state,cep,cname,telephone);
			String resultmessage=null;
				if(modifyok!=null){
					resultmessage="客服信息修改成功！";
					request.setAttribute("message",resultmessage);
					return mapping.findForward("showquestionlist");
				}
				else{
					resultmessage="客服信息修改失败！";
					request.setAttribute("message",resultmessage);
					return mapping.findForward("showquestionlist");
				}
	}
	
	
	
	/**
	 * 把修改用户信息传jsp页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws MessageException
	 */
	public ActionForward question_modytojsp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MessageException {
		String id = request.getParameter("id");
		TbQuestionInfo tbquestion = null;
		tbquestion=questionmanger.getQuestionby_id(id);
        String resultmessage=null;
		if (tbquestion != null) {
			request.setAttribute("tbquestion",tbquestion);
			return mapping.findForward("msmodify");
		} else { // 说明没有找到.
			resultmessage="该记录不存在！";
			request.setAttribute("message",resultmessage);
			return mapping.findForward("showquestionlist");
		}
	}

	
	
    /**
     * 增加
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
	public ActionForward question_add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		TbUserInfo user=UserInfo.getUserInfo(request);
		AddQuestionForm addquestionform = (AddQuestionForm) form;
		String type = addquestionform.getType();
		String question=addquestionform.getQuestion();
		String cep=addquestionform.getCep();
		String cname=addquestionform.getCname();
		String telphone=addquestionform.getTelephone();
		String recordertime=addquestionform.getRecorder_time();
		String insert=null;
		insert=questionmanger.createQuestion(user.getUserName(),type,question,cep,cname,telphone,recordertime);
		String resultmessage=null;
		if(insert!=null){
			resultmessage="客服信息添加成功！";
		}else{
			resultmessage="客服添加失败";
		}
		request.setAttribute("message",resultmessage);
		return mapping.findForward("showquestionlist");
	}
		



	public ActionForward question_xingxi(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		TbQuestionInfo tbquestion = null;
		tbquestion=questionmanger.getQuestionby_id(id);
        String resultmessage=null;
		if (tbquestion != null) {
			request.setAttribute("tbquestion",tbquestion);
			return mapping.findForward("xingxi");
		} else { // 说明没有找到.
			resultmessage="该记录不存在！";
			request.setAttribute("message",resultmessage);
			return mapping.findForward("showquestionlist");
		}
	}

	
	
	
	/**
	 * 根据ms列表信息的查询条件查询
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward question_Sarch(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
			String agentid="";
			String cmd = request.getParameter("CMD");
			HttpSession session = request.getSession(true);
			TbUserInfo userInfo=(TbUserInfo)session.getAttribute("user");
			String type=request.getParameter("type");
			if(type==null||type.equals("null")){   //类型
				type="";
			}
			String state=request.getParameter("state");  //状态 是否已经解决 
			if(state==null||state.equals("null")){
				state="";
			}
			String recorder=request.getParameter("recorder");   //记录人
			if(recorder==null||recorder.equals("null")){
				recorder="";
			}
			String henchman=request.getParameter("henchman");   //跟踪人
			if(henchman==null||henchman.equals("null")){
				henchman="";
			}
			String solve_man=request.getParameter("solve_man");  //解决人
			if(solve_man==null||solve_man.equals("")){
				solve_man="";
			}
			int questionCount = 0;
			questionCount = questionmanger.getQuestion_sertch(type,state,recorder.trim(),henchman.trim(),solve_man.trim());
			// 获取每页的条数
			int pageSize = 15;
			// 获取总页数
			int pageCount = questionmanger.getPageCount(questionCount, pageSize);
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
			request.setAttribute("questionCount", questionCount);
			// 返回的总页数
			request.setAttribute("pageCount", pageCount);
			// 返回每页面显示条数
			request.setAttribute("pageSize", pageSize);
			// 返回当前页
			request.setAttribute("currentPage", pageNo);
			// 设置返回的命令字
			request.setAttribute("CMD", cmd);
			// 设置返回用户类型

			// 设设置返回用户查询的select中option值
			request.setAttribute("type", type);
			request.setAttribute("state", state);
			request.setAttribute("recorder", recorder);
			request.setAttribute("henchman", henchman);
			request.setAttribute("solve_man", solve_man);
			request.setAttribute("questionList", questionmanger.getQuestioinfoby_page(pageNo,
					pageSize, type,state,recorder.trim(),henchman.trim(),solve_man.trim()));
			return mapping.findForward("succes");
		
	
	}
	
	
	
	
}