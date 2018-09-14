package com.elegps.tscweb.action.Adv;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.elegps.tscweb.action.base.BaseAction;
import com.elegps.tscweb.comm.HRAddress;
import com.elegps.tscweb.comm.ReadNotFileUtil;
import com.elegps.tscweb.comm.UpLoadFileUtil;
import com.elegps.tscweb.comm.UserInfo;
import com.elegps.tscweb.exception.MessageException;
import com.elegps.tscweb.model.TbAdvInfo;
import com.elegps.tscweb.model.TbEnterpriseInfo;
import com.elegps.tscweb.model.TbMsInfo;
import com.elegps.tscweb.model.TbUserInfo;
import com.elegps.tscweb.model.TbUserLog;
import com.elegps.tscweb.tscconfig.FilePathConfig;

/**
 * level 2015-8-18
 * @author wanglei
 *
 */
public class AdvAction extends BaseAction{
	TbUserInfo user = new TbUserInfo();
	
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MessageException {
		ActionForward actionforward = null;
		String cmd = null;
		user = UserInfo.getUserInfo(request);
		if (user != null) {
			// ���û�оͽ��յ�����Ĭ����ʾȫ������
			if (request.getParameter("CMD") == null || request.getParameter("CMD").equals("")) {
				cmd = "adv_search";
			} else {
				cmd = request.getParameter("CMD");
			}
			
			if (cmd.equals("advaddjsp")) { // ��ӹ��jspҳ��
				actionforward = advaddjsp(mapping, form, request, response);
			}
			if (cmd.equals("advadd")) { //��ӹ��ִ��
				try {
					actionforward = advadd(mapping, form, request, response);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (cmd.equals("advupdatejsp")) { // �޸Ĺ��jspҳ��
				actionforward = advupdatejsp(mapping, form, request, response);
			}
			if (cmd.equals("advupdate")) { //�޸Ĺ��ִ��
				actionforward = advupdate(mapping, form, request, response);
			}
			if (cmd.equals("advlist")) { //����б�ҳ��
				actionforward = advlist(mapping, form, request, response);
			}
			if (cmd.equals("advdelete")) { //���ɾ������
				actionforward = advdelete(mapping, form, request, response);
			}
			if (cmd.equals("advyulan")) { //���Ԥ������
				actionforward = advyulan(mapping, form, request, response);
			}
			if (cmd.equals("yulanadd")) { //���Ԥ��ҳ����Ӳ���
				actionforward = yulanadd(mapping, form, request, response);
			}
			if (cmd.equals("advshowjsp")) { //�ֻ�app��ʾ���ҳ��
				actionforward = advshowjsp(mapping, form, request, response);
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

	private ActionForward advshowjsp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		//app���ն�id��������ͣ�pc-TSCWEB�����id
		
		String msid = request.getParameter("phone");
		if( msid == "" || msid == null){
			msid = "";
		}else{
			msid = FilePathConfig.getMSId()+msid;
		}
		
		String advId = request.getParameter("advId");
		int advType = 0;
		String type = request.getParameter("type");
		if(!type.equals("") && !type.equals(null)){
			advType = Integer.parseInt(type);
		}
		
		List list = new ArrayList();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if(advId == "" || advId == null ){//app����
			list = advManager.findAdvInfoByPage(1, 5, "", "", sdf.format(new Date()), "", "", "",advType);
		}else{//TSCWEB����
			TbAdvInfo adv = advManager.findAdvInfoById(advId);
			list.add(adv);
		}
		request.setAttribute("advlist", list);
		if(advType == 0){
			return mapping.findForward("advshoww");//���ֹ��ҳ��
		}else if (advType == 1){
			return mapping.findForward("advshowp");//ͼƬ���ҳ��
		}
		return null;
	}

	private ActionForward yulanadd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String advType = request.getParameter("advType");
		String advTitle = request.getParameter("advTitle");
		String advContent = request.getParameter("advContent");
		String sendSTime = request.getParameter("sendSTime");
		String sendETime = request.getParameter("sendETime");
		String advUrl = request.getParameter("advUrl");
		String fileurl = request.getParameter("fileurl");
		
		if(advType.equals("")||advType.equals(null)){
			System.out.println("��ѡ�������ͣ�");
			return null;
		}
		if(advTitle.equals("")||advTitle.equals(null)){
			System.out.println("����������⣡");
			return null;
		}
		if(sendSTime.equals(null) || sendSTime.equals("")){
			System.out.println("��������ʾ��ʼ���ڣ�");
			return null;
		}
		if(sendETime.equals(null) || sendETime.equals("")){
			System.out.println("��������ʾ�������ڣ�");
			return null;
		}
		Date ss = null;
		Date se = null;
		SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd Z yyyy", Locale.UK);
		try {
			ss = sdf.parse(sendSTime);
			se = sdf.parse(sendETime);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		TbAdvInfo adv =new TbAdvInfo();
		adv.setAdvContent(advContent);
		adv.setAdvTitle(advTitle);
		adv.setAdvType(Integer.parseInt(advType));
		adv.setAdvUrl(advUrl);
		adv.setPicUrl(fileurl);
		adv.setSendSTime(ss);
		adv.setSendETime(se);
		adv.setCreatTime(new Date()); 
		adv.setUserId(user.getUserId());
		System.out.print("ok");
		String advId = advManager.addAdvInfo(adv);
		if(advId.equals(null)){
			System.out.println("��ӹ��ʧ��");
			request.setAttribute("message", "����ʧ�ܣ�");
		}else{
			System.out.println("��ӹ��ɹ�Id��"+advId);
			request.setAttribute("message", "�����ɹ���");
		}
		return mapping.findForward("toadvlist");
	}

	private ActionForward advyulan(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String advType = request.getParameter("advType");
		String advTitle = request.getParameter("advTitle");
		String advContent = request.getParameter("advContent");
		String sendSTime = request.getParameter("sendSTime");
		String sendETime = request.getParameter("sendETime");
		String advUrl = request.getParameter("advUrl");
		String fileurl = request.getParameter("fileurl");
		
		if(advType.equals("")||advType.equals(null)){
			System.out.println("��ѡ�������ͣ�");
			return null;
		}
		if(advTitle.equals("")||advTitle.equals(null)){
			System.out.println("����������⣡");
			return null;
		}
		if(sendSTime.equals(null) || sendSTime.equals("")){
			System.out.println("��������ʾ��ʼ���ڣ�");
			return null;
		}
		if(sendETime.equals(null) || sendETime.equals("")){
			System.out.println("��������ʾ�������ڣ�");
			return null;
		}
		Date ss = null;
		Date se = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			ss = sdf.parse(sendSTime);
			se = sdf.parse(sendETime);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		TbAdvInfo adv =new TbAdvInfo();
		adv.setAdvContent(advContent);
		adv.setAdvTitle(advTitle);
		adv.setAdvType(Integer.parseInt(advType));
		adv.setAdvUrl(advUrl);
		adv.setPicUrl(fileurl);
		adv.setSendSTime(ss);
		adv.setSendETime(se);
		//adv.setCreatTime(new Date()); 
		//adv.setUserId(user.getUserId());
		
		request.setAttribute("adv", adv);
		return mapping.findForward("toadvyulan");
	}

	private ActionForward advdelete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String[] list = request.getParameterValues("list"); 
		Boolean delsucc = advManager.deleteAdvInfo(list);
        for (int i = 0; i < list.length; i++) {
            //��¼������־
            TbUserLog userLog = new TbUserLog();
            userLog.setUserId(Integer.parseInt(request.getSession().getAttribute("uId").toString()));
            userLog.setlDate(new Date());
            userLog.setlAddress(HRAddress.getIpAddr(request));
            userLog.setlType(3);
            userLog.setlContent("ɾ�����{" + list[i] + "}");
            logManager.save(userLog);
        }
        String resultmessage = null;
        if (delsucc) {
            resultmessage = "�����Ϣɾ���ɹ���";
            request.setAttribute("message", resultmessage);
            return mapping.findForward("toadvlist");
        } else {
            resultmessage = "�����Ϣɾ��ʧ�ܣ�";
            request.setAttribute("message", resultmessage);
            return mapping.findForward("toadvlist");
        }
	}

	/**
	 * �������б�ҳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	private ActionForward advlist(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		
		String cmd = request.getParameter("CMD");
		String title = request.getParameter("advTitle");
		String url = request.getParameter("advUrl");
		String sendSTime = request.getParameter("sendSTime");
		String sendETime = request.getParameter("sendETime");
		String creatSTime = request.getParameter("creatSTime");
		String creatETime = request.getParameter("creatETime");
		if(title == null)
			title="";
		if(url==null)
			url="";
		if(sendSTime == null)
			sendSTime="";
		if(sendETime == null)
			sendETime="";
		if(creatSTime == null)
			creatSTime="";
		if(creatETime == null)
			creatETime="";

		Integer advType = -1;
		 Integer advCount = 0;
		 //��ȡ��ѯ����
	 	advCount = advManager.findAdvCount(title, url, sendSTime, sendETime, 
	 			creatSTime, creatETime,advType);
        // ��ȡÿҳ������
	 	String ps =request.getParameter("pageSize");
		int pageSize = 10;
		if(ps!= null){
			pageSize =Integer.parseInt(ps);
		}
        // ��ȡ��ҳ��
	 	Integer pageCount = (advCount + pageSize - 1) / pageSize;
        // ��ҳ��ȡ�õ�ǰҳ
	 	Integer pageNo;
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
        request.setAttribute("advCount", advCount);
        // ����ÿҳ����ʾ����
        request.setAttribute("pageSize", pageSize);
        // ���ص�ǰҳ
        request.setAttribute("currentPage", pageNo);
        // ���÷��ص�������
        request.setAttribute("CMD", cmd);
        // ���ص���ҳ��
        request.setAttribute("pageCount", pageCount);
        //��ȡ���list����
        List<TbAdvInfo> advList = advManager.findAdvInfoByPage(pageNo, pageSize, title, url,
        		sendSTime, sendETime, creatSTime, creatETime,advType);
        request.setAttribute("advList", advList);
		
		request.setAttribute("advTitle", title);
		request.setAttribute("sendSTime", sendSTime);
		request.setAttribute("sendETime", sendETime);
		request.setAttribute("creatSTime", creatSTime);
		request.setAttribute("creatETime", creatETime);
		request.setAttribute("advUrl", url);
        
		return mapping.findForward("advlist");
	}

	private ActionForward advupdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		String advId = request.getParameter("advid");
		if(advId.equals(null) || advId.equals("")){
			System.out.println("advId��");
			request.setAttribute("message", "�����쳣���޸�ʧ��!");
			return mapping.findForward("toadvlist");
		}
		TbAdvInfo adv = advManager.findAdvInfoById(advId);
		if(adv == null){
			System.out.println("������");
			request.setAttribute("message", "�����쳣���޸�ʧ��!");
			return mapping.findForward("toadvlist");
		}
		try {
			TbAdvInfo advInfo = UpLoadFileUtil.uploadHeadShow(request, response,"advFile");
			if(advInfo.getPicUrl() == null){
				advInfo.setPicUrl(adv.getPicUrl());
			}
			advInfo.setAdvId(Integer.parseInt(advId));
			advInfo.setUserId(adv.getUserId());
			
			Boolean b = advManager.updateAdvInfo(advInfo);
			if(b){
				System.out.println("�޸ĳɹ�");
				request.setAttribute("message", "�޸ĳɹ�!");
				return mapping.findForward("toadvlist");
			}else{
				System.out.println("�޸�ʧ��");
				request.setAttribute("message", "�޸�ʧ��!");
				return mapping.findForward("toadvlist");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	private ActionForward advupdatejsp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String advId = request.getParameter("advId");
		if(advId.equals(null)||advId.equals("")){
			return null;
		}
		TbAdvInfo advInfo = advManager.findAdvInfoById(advId);
		if(advInfo == null){
			System.out.println("��Ϣ������");
			request.setAttribute("message", "��Ϣ������!");
			return mapping.findForward("toadvlist");
		}
		
		request.setAttribute("advinfo", advInfo);
		return mapping.findForward("advmodyjsp");
	}

	/**
	 * ��ӹ�����ִ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException 
	 */
	private ActionForward advadd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws IOException {
		String advType = request.getParameter("advType");
		String advTitle = request.getParameter("advTitle");
		String advContent = request.getParameter("advContent");
		String sendSTime = request.getParameter("sendSTime");
		String sendETime = request.getParameter("sendETime");
		String advUrl = request.getParameter("advUrl");
		String fileurl = request.getParameter("fileurl");
		
		if(advType.equals("")||advType.equals(null)){
			System.out.println("��ѡ�������ͣ�");
			return null;
		}
		if(advTitle.equals("")||advTitle.equals(null)){
			System.out.println("����������⣡");
			return null;
		}
		if(sendSTime.equals(null) || sendSTime.equals("")){
			System.out.println("��������ʾ��ʼ���ڣ�");
			return null;
		}
		if(sendETime.equals(null) || sendETime.equals("")){
			System.out.println("��������ʾ�������ڣ�");
			return null;
		}
		Date ss = null;
		Date se = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			ss = sdf.parse(sendSTime);
			se = sdf.parse(sendETime);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		TbAdvInfo adv =new TbAdvInfo();
		adv.setAdvContent(advContent);
		adv.setAdvTitle(advTitle);
		adv.setAdvType(Integer.parseInt(advType));
		adv.setAdvUrl(advUrl);
		adv.setPicUrl(fileurl);
		adv.setSendSTime(ss);
		adv.setSendETime(se);
		adv.setCreatTime(new Date()); 
		adv.setUserId(user.getUserId());
		
		String advId = advManager.addAdvInfo(adv);
		if(advId.equals(null)){
			System.out.println("��ӹ��ʧ��");
			request.setAttribute("message", "����ʧ�ܣ�");
		}else{
			System.out.println("��ӹ��ɹ�Id��"+advId);
			request.setAttribute("message", "�����ɹ���");
		}
		return mapping.findForward("toadvlist");
	}

	/**
	 * ��ӹ��jsp
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	private ActionForward advaddjsp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return mapping.findForward("advaddjsp");
	}
}
