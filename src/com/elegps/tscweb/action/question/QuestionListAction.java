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
			// ���û�оͽ��յ�����Ĭ����ʾȫ������
			if (request.getParameter("CMD") == null
					|| request.getParameter("CMD").equals("")) {
				cmd = "question_search";
			} else {
				cmd = request.getParameter("CMD");
			}

			if (cmd.equals("add")) {// �����û���Ϣ
				actionforward = question_add(mapping, form, request, response);
			} else if (cmd.equals("xingxi")) // �鿴�û���ϸ��Ϣ
			{
				actionforward = question_xingxi(mapping, form, request, response);
			}else if (cmd.equals("mdoyijsp")) { //��Ҫ�޸ĵ���Ϣ�����޸ĵ�jspҳ��
				actionforward = question_modytojsp(mapping, form, request, response);
			} else if(cmd.equals("modify")){  //�޸��ն��û���Ϣ
			    actionforward=question_modify(mapping, form, request, response);
			}else if(cmd.equals("question_search")){  //��������ͬʱ��ѯ
				actionforward = question_Sarch(mapping, form, request, response);
			}else if(cmd.equals("addjsp")){
				actionforward = addquestionjsp(mapping, form, request, response);
			}
			if(actionforward==null){
				actionforward=mapping.findForward("succes");
			}
		}else{
			request.setAttribute("message","Session���ڣ������µ�¼");
			actionforward=mapping.findForward("logging");
		}
		
		return actionforward;
	}

	
	
	 /**
     * ����jspҳ��
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
	 * ���޸���Ϣ
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
					resultmessage="�ͷ���Ϣ�޸ĳɹ���";
					request.setAttribute("message",resultmessage);
					return mapping.findForward("showquestionlist");
				}
				else{
					resultmessage="�ͷ���Ϣ�޸�ʧ�ܣ�";
					request.setAttribute("message",resultmessage);
					return mapping.findForward("showquestionlist");
				}
	}
	
	
	
	/**
	 * ���޸��û���Ϣ��jspҳ��
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
		} else { // ˵��û���ҵ�.
			resultmessage="�ü�¼�����ڣ�";
			request.setAttribute("message",resultmessage);
			return mapping.findForward("showquestionlist");
		}
	}

	
	
    /**
     * ����
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
			resultmessage="�ͷ���Ϣ��ӳɹ���";
		}else{
			resultmessage="�ͷ����ʧ��";
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
		} else { // ˵��û���ҵ�.
			resultmessage="�ü�¼�����ڣ�";
			request.setAttribute("message",resultmessage);
			return mapping.findForward("showquestionlist");
		}
	}

	
	
	
	/**
	 * ����ms�б���Ϣ�Ĳ�ѯ������ѯ
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
			if(type==null||type.equals("null")){   //����
				type="";
			}
			String state=request.getParameter("state");  //״̬ �Ƿ��Ѿ���� 
			if(state==null||state.equals("null")){
				state="";
			}
			String recorder=request.getParameter("recorder");   //��¼��
			if(recorder==null||recorder.equals("null")){
				recorder="";
			}
			String henchman=request.getParameter("henchman");   //������
			if(henchman==null||henchman.equals("null")){
				henchman="";
			}
			String solve_man=request.getParameter("solve_man");  //�����
			if(solve_man==null||solve_man.equals("")){
				solve_man="";
			}
			int questionCount = 0;
			questionCount = questionmanger.getQuestion_sertch(type,state,recorder.trim(),henchman.trim(),solve_man.trim());
			// ��ȡÿҳ������
			int pageSize = 15;
			// ��ȡ��ҳ��
			int pageCount = questionmanger.getPageCount(questionCount, pageSize);
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
			request.setAttribute("questionCount", questionCount);
			// ���ص���ҳ��
			request.setAttribute("pageCount", pageCount);
			// ����ÿҳ����ʾ����
			request.setAttribute("pageSize", pageSize);
			// ���ص�ǰҳ
			request.setAttribute("currentPage", pageNo);
			// ���÷��ص�������
			request.setAttribute("CMD", cmd);
			// ���÷����û�����

			// �����÷����û���ѯ��select��optionֵ
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