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
			// ���û�оͽ��յ�����Ĭ����ʾȫ������
			if (request.getParameter("CMD") == null || request.getParameter("CMD").equals("")) {
				cmd = "pt_search";
			} else {
				cmd = request.getParameter("CMD");
			}
			
			if (cmd.equals("pt_search")) { // ��ѯ�ֻ��ͺ�ҳ��
				actionforward = ptlistjsp(mapping,request, response);
			}
			if (cmd.equals("pt_config")) { // �ͺ����䣨����/�رգ�
				actionforward = ptconfig(mapping,request, response);
			}
			if (cmd.equals("xianxi")) { // ��ϸ��Ϣ
				actionforward = xianxi(mapping,request, response);
			}
			
			if (cmd.equals("delete")) { // ��ϸ��Ϣ
				actionforward = ptDelete(mapping,request, response);
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

	private ActionForward ptDelete(ActionMapping mapping,
			HttpServletRequest request, HttpServletResponse response) {
		String[] list = request.getParameterValues("list");
		Boolean delpf = ptManager.deletePtInfo(list);
		// ��ȡ������
		request.setAttribute("ptCount", 0);
		// ����ÿҳ����ʾ����
		request.setAttribute("pageSize", Integer.valueOf(request.getParameter("pageSize")));
		// ���ص���ҳ��
		request.setAttribute("pageCount", 0);
		// ���ص�ǰҳ
		request.setAttribute("currentPage", Integer.valueOf(request.getParameter("pageNo")));
		request.setAttribute("type", request.getParameter("type"));
		request.setAttribute("CMD", "");
		request.setAttribute("ptList", null);
		
		String resultmessage = null;
		if (delpf) {
			System.out.println("�ͺ���Ϣɾ���ɹ�");
			resultmessage = "ɾ���ɹ�";
			request.setAttribute("message", resultmessage);
			return ptlistjsp(mapping, request, response);
		} else {
			System.out.println("�ͺ���Ϣɾ��ʧ��");
			resultmessage = "ɾ��ʧ��";
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
			request.setAttribute("message", "�����쳣��");
		}else{
			if(flag==pt.getFalg()){
				if(flag==1){
					pt.setFalg(0);
				}else if(flag==0){
					pt.setFalg(1);
				}
				ptManager.updatePt(pt);
			}
			request.setAttribute("message", "�޸��ͺ�������ɣ�");
		}
		
		// ��ȡ������
		request.setAttribute("ptCount", 0);
		// ����ÿҳ����ʾ����
		request.setAttribute("pageSize", Integer.valueOf(request.getParameter("pageSize")));
		// ���ص���ҳ��
		request.setAttribute("pageCount", 0);
		// ���ص�ǰҳ
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
		// ��ȡÿҳ������
		String ps =request.getParameter("pageSize");
		int pageSize = 10;
		if(ps!= null){
			pageSize =Integer.parseInt(ps);
		}
		
		// ��ȡ��ҳ��
		int pageCount = (ptCount + pageSize - 1) / pageSize;
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
		request.setAttribute("ptCount", ptCount);
		// ����ÿҳ����ʾ����
		request.setAttribute("pageSize", pageSize);
		// ���ص�ǰҳ
		request.setAttribute("currentPage", pageNo);

		// ���ص���ҳ��
		request.setAttribute("pageCount", pageCount);

		// ���÷��ص�������
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
