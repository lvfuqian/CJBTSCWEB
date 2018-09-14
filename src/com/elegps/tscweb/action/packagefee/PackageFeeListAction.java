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
			// ���û�оͽ��յ�����Ĭ����ʾȫ������
			if (request.getParameter("CMD") == null || request.getParameter("CMD").equals("")) {
				cmd = "pf_search";
			} else {
				cmd = request.getParameter("CMD");
			}
			
			if (cmd.equals("pf_search")) { // ��������ͬʱ��ѯ
				actionforward = pf_Sarch(mapping, form, request, response);
			}
			if (cmd.equals("pf_mody")) { // �޸�
				actionforward = pf_mody(mapping, form, request, response);
			}
			if (cmd.equals("pf_modyjsp")) { // �޸�jspҳ��
				actionforward = pf_modyjsp(mapping, form, request, response);
			}
			if (cmd.equals("pf_add")) { // ���
				actionforward = pf_add(mapping, form, request, response);
			}
			if (cmd.equals("pf_addjsp")) { // ���jspҳ��
				actionforward = pf_addjsp(mapping, form, request, response);
			}
			if (cmd.equals("pf_delete")) { // ɾ��
				actionforward = pf_delete(mapping, form, request, response);
			}
			
			
			
		} else {
			request.setAttribute("message", "Session���ڣ������µ�¼");
			actionforward = mapping.findForward("logging");
		}
		if (actionforward == null) {
			return mapping.findForward("success");
		}
		return actionforward;
	}
	
	/**
	 * �����޸�jspҳ��
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
				System.out.println("���ײͲ�����");
				resultmessage = "���ײͲ�����";
				request.setAttribute("message", "���ײͲ�����");
				return null;
			}
		}else{
			System.out.println("�ײ���Ϣ����ʧ��");
			resultmessage = "����ʧ��";
			request.setAttribute("message", resultmessage);
			return pf_Sarch(mapping, form, request, response);
		}
		
	}

	/**
	 * �������jspҳ��
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
	 * ɾ��
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
			System.out.println("�ײ���Ϣɾ���ɹ�");
			resultmessage = "ɾ���ɹ�";
			request.setAttribute("message", resultmessage);
			return pf_Sarch(mapping, form, request, response);
		} else {
			System.out.println("�ײ���Ϣɾ��ʧ��");
			resultmessage = "ɾ��ʧ��";
			request.setAttribute("message", resultmessage);
			return pf_Sarch(mapping, form, request, response);
		}
	}

	/**
	 * ���
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
			System.out.println("�ײ���Ϣ��ӳɹ�");
			resultmessage = "��ӳɹ�";
			request.setAttribute("message", resultmessage);
			
			return pf_Sarch(mapping, form, request, response);
		} else {
			System.out.println("�ײ���Ϣ���ʧ��");
			resultmessage = "���ʧ��";
			request.setAttribute("message", resultmessage);
			return pf_Sarch(mapping, form, request, response);
		}
	}

	/**
	 * �޸��ײ���Ϣ
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
				System.out.println("�ײ���Ϣ�޸ĳɹ�");
				resultmessage = "�޸ĳɹ�";
				request.setAttribute("message", resultmessage);
				return pf_Sarch(mapping, form, request, response);
			}else{
				System.out.println("�ײ���Ϣ�޸�ʧ��");
				resultmessage = "�޸�ʧ��";
				request.setAttribute("message", resultmessage);
				return null;
			}
		}
		return null;
	}

	/**
	 * �����ײ���Ϣ��ѯ
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
		pfList = pfManager.findAllPFInfo();//��ѯ�����ײ���Ϣ
		
		request.setAttribute("pfList", pfList);//�ײ���Ϣlist����
		return mapping.findForward("pflist");
	}
}
