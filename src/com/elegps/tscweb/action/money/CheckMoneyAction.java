package com.elegps.tscweb.action.money;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.elegps.tscweb.action.base.BaseAction;
import com.elegps.tscweb.comm.UserInfo;
import com.elegps.tscweb.exception.MessageException;
import com.elegps.tscweb.model.TbAgentInfo;
import com.elegps.tscweb.model.TbCheckMInfo;
import com.elegps.tscweb.model.TbEnterpriseInfo;
import com.elegps.tscweb.model.TbMoneyLog;
import com.elegps.tscweb.model.TbMsInfo;
import com.elegps.tscweb.model.TbPFInfo;
import com.elegps.tscweb.model.TbUserInfo;
import com.elegps.tscweb.serivce.EnterPriseManager;

/**
 *���
 * @author wanglei
 *	2015-4-14
 */
public class CheckMoneyAction extends BaseAction{
	
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MessageException {
		ActionForward actionforward = null;
		String cmd = null;
		TbUserInfo user = UserInfo.getUserInfo(request);
		if (user != null) {
			// ���û�оͽ��յ�����Ĭ����ʾȫ������
			if (request.getParameter("CMD") == null || request.getParameter("CMD").equals("")) {
				cmd = "success";
			} else {
				cmd = request.getParameter("CMD");
			}
			
			//��ȡ�û���ɫ 
			int role_id =Integer.parseInt(request.getSession().getAttribute("roleId")+"");
			//cmlistinfo��cminfo   money_check_ok
			if (cmd.equals("checklistinfojsp")) { // ��������������б�ҳ�棨����Ա�鿴������鿴����ˣ�
				actionforward = checklistinfojsp(mapping, form, request, response,
						role_id,user.getUserId());
			}
			if (cmd.equals("checklistinfoepjsp")) { // ����������ҵ�б�ҳ�棨����Ա�鿴������鿴����ˣ�
				actionforward = checklistinfojsp(mapping, form, request, response,
						role_id,user.getUserId());
			}
			if (cmd.equals("checklistinfomsjsp")) { // ���������ն��б�ҳ�棨����Ա�鿴������鿴����ˣ�
				actionforward = checklistinfojsp(mapping, form, request, response,
						role_id,user.getUserId());
			}
			if (cmd.equals("money_check_ok")) { // ���������б�ҳ�棨����ִ��ͨ����
				actionforward = money_check(mapping, form, request, response,role_id,user);
			}
			if (cmd.equals("money_check_no")) { // ���������б�ҳ�棨����ִ�в�ͨ����
				actionforward = money_check(mapping, form, request, response,role_id,user);
			}
			
			if (cmd.equals("cminfojsp")) { // ���������б�ҳ�棨����Ա�鿴������鿴����ˣ�
				actionforward = cminfojsp(mapping, form, request, response);
			}
		}
		
		return actionforward;
	}

	/**
	 * ��ת�������ϸ��Ϣҳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	private ActionForward cminfojsp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		int finId = Integer.parseInt(request.getParameter("cmid")+"");//���id
		TbCheckMInfo cmInfo = moneyManager.getCMInfoById(finId);
		TbPFInfo pf = null;
		TbEnterpriseInfo ep =null;
		TbAgentInfo a=null;
		if(cmInfo.getResRole()==null){
				//cmInfo.setResId(cmInfo.getResId().replace(",", "<br/>"));
				String[] s=cmInfo.getResId().split(",");
				//ҳ����ʾ�ն˸�ʽ���ն������ն�id
				String res =s[0]+"<br/>";
				for(int i=1;i< s.length;i++){
					TbMsInfo msinfo = msmanager.getTBMsinfoby_msid(s[i]);
					if(msinfo == null){
						res += "���նˣ�"+s[i]+"�Ѳ����ڣ���<br/>";
					}else{
						res += msinfo.getMsName()+"��"+s[i]+"<br/>";
					}
				}
				cmInfo.setResId(res);
				
				pf=pfManager.getPFinfoByHow(Double.parseDouble(cmInfo.getResMoney())/s.length);
				request.setAttribute("pf", pf);
		}else if(cmInfo.getResRole().getRoleId()==3){
			request.setAttribute("ep", epmanger.getEpinfo_byepid(cmInfo.getResId()));
		}else if(cmInfo.getResRole().getRoleId()==2){
			request.setAttribute("a", agentmanger.getAgent_ByAgentID(cmInfo.getResId()));
		}
		request.setAttribute("cmInfo", cmInfo);
		return mapping.findForward("cminfo");
	}

	/**
	 * ���ִ�У�ͨ������ͨ����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @param roleId
	 * @param string
	 * @param string2
	 * @param userId
	 * @return
	 */
	private ActionForward money_check(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			int roleId, TbUserInfo user) {
		// TODO Auto-generated method stub
		if(roleId!=40){
			String print1 ="��Ȩ��ִ�д˲���";
			String resultmessage1="��Ȩ��ִ�д˲���";
			showMessage(request, print1, resultmessage1);
			return checklistinfojsp(mapping, form, request, response,
					roleId,user.getUserId());
		}
		String cmd = request.getParameter("CMD");
		String finId = request.getParameter("cmid");
		TbCheckMInfo cminfo = moneyManager.getCMInfoById(Integer.parseInt(finId));
		if(cmd.equals("money_check_ok")){//ͨ��
			cminfo.setReadState(1);
			cminfo.setProStatus(1);
			cminfo.setCheckTime(new Date());
			cminfo.setCheckPerson(user);
			
			String mm = "";
			if(cminfo.getResRole()!=null){
				if(cminfo.getResRole().getRoleId() == 2){//���Ӵ����̽��
					TbAgentInfo a = agentmanger.getAgent_ByAgentID(cminfo.getResId());
					mm = a.getAgent_Money();
					if(mm == null || mm == ""){
						mm="0.00";
					}
					double newm = Double.parseDouble(mm) + Double.parseDouble(cminfo.getResMoney());
					a.setAgent_Money(Double.toString(newm));
					Boolean updateMoney = agentmanger.updateAgent(a);
					if(updateMoney){
						String print ="��ִֵ�гɹ���";
						String resultmessage="�ɹ���ֵ" + cminfo.getResMoney() +"Ԫ";
						showMessage(request, print, resultmessage);
						
						TbMoneyLog ml=new TbMoneyLog();	
						ml.setHow(cminfo.getResMoney());
						ml.setMlogTime(new Date());
						ml.setMoneyToUser(a.getAgent_Name());
						ml.setUser(user);
						ml.setPayType(0);
						Boolean addML = moneyManager.addMoneyLog(ml);
						if(addML){
							System.out.println("��˳�ֵ��¼����ɹ�");
						}else{
							System.out.println("��˳�ֵ��¼����ʧ��");
						}
					}else{
						String print ="��ִֵ��ʧ�ܣ�";
						String resultmessage="��ֵ" + cminfo.getResMoney() +"Ԫ ʧ�ܣ�";
						showMessage(request, print, resultmessage);
					}
				}else if(cminfo.getResRole().getRoleId() == 3){//������ҵ���
					TbEnterpriseInfo ep = epmanger.getEpinfo_byepid(cminfo.getResId());
					mm = ep.getEp_Money();
					if(mm == null || mm == ""){
						mm="0.00";
					}
					double newm = Double.parseDouble(mm) + Double.parseDouble(cminfo.getResMoney());
					ep.setEp_Money(Double.toString(newm));
					Boolean updateMoney = epmanger.updateEp(ep);
					if(updateMoney){
						String print ="��ִֵ�гɹ���";
						String resultmessage="�ɹ���ֵ" + cminfo.getResMoney() +"Ԫ";
						showMessage(request, print, resultmessage);
						
						TbMoneyLog ml=new TbMoneyLog();	
						ml.setHow(cminfo.getResMoney());
						ml.setMlogTime(new Date());
						ml.setMoneyToUser(ep.getEp_Name());
						ml.setUser(user);
						ml.setPayType(0);
						Boolean addML = moneyManager.addMoneyLog(ml);
						if(addML){
							System.out.println("��˳�ֵ��¼����ɹ�");
						}else{
							System.out.println("��˳�ֵ��¼����ʧ��");
						}
					}else{
						String print ="��ִֵ��ʧ�ܣ�";
						String resultmessage="��ֵ" + cminfo.getResMoney() +"Ԫ ʧ�ܣ�";
						showMessage(request, print, resultmessage);
					}
				}
			}else {//�����ն��ײ�
				String [] msid = cminfo.getResId().split(",");//�ó��ն�id
				Double money = Double.parseDouble(cminfo.getResMoney())/(msid.length-1);//����ÿ���ն˵ĳ�ֵ����
				TbPFInfo pf = pfManager.getPFinfoByHow(money);
				List<TbMsInfo> msList = new ArrayList<TbMsInfo>(); 
				Calendar c = Calendar.getInstance();//���һ��������ʵ��     
				//SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				for(int i = 1 ;i < msid.length ; i++){
					TbMsInfo msinfo = msmanager.getTBMsinfoby_msid(msid[i]);
					if(msinfo == null){
						continue;
					}
					msinfo.setPf(pf);//�޸��ն��ײ���Ϣ
					
					//���ڱȽϣ�����ʱ��<��ֵʱ�䣬�����ڳ�ֵ��ʱ������ϼ����ײ�ʱ����������ʱ������ϼ����ײ�ʱ��
					if(msinfo.getMsMoneyTime()!=null){
						if(msinfo.getMsMoneyTime().before(new Date())){//����ʱ���ڳ�ֵʱ��֮ǰ
							c.setTime(new Date());//��������ʱ��   
							c.add(Calendar.MONTH,pf.getPfTime());//���������·���������
						}else{
							c.setTime(msinfo.getMsMoneyTime());//��������ʱ��   
							c.add(Calendar.MONTH,pf.getPfTime());//���������·���������
						}
					}else{
						c.setTime(new Date());//��������ʱ��   
						c.add(Calendar.MONTH,pf.getPfTime());//���������·���������
					}
					msinfo.setMsMoneyTime(c.getTime());//�޸��ײ͵���ʱ��
					msinfo.setIs_Arrearage(1);//�ʷ�״̬��Ϊ�з�
					msinfo.setFlag(1);//��Ϊ����״̬
					msList.add(msinfo);
				}
				Boolean upms = msmanager.updateMs(msList);
				String allm = msList.size() * Double.parseDouble(pf.getPfHow()) +"" ;
				cminfo.setResMoney(allm) ;
				if(upms){
					String print1 ="�ն��ײͳ�ִֵ�гɹ���";
					String resultmessage1="�ɹ���ֵ"+msList.size()+"���ն˵��ײͣ�"+pf.getPfType() +"������"+allm+"Ԫ";
					showMessage(request, print1, resultmessage1);
					Boolean addML = false;
					for(int i = 0; i<msList.size(); i ++){
						TbMoneyLog ml=new TbMoneyLog();	
						ml.setHow(pf.getPfHow());
						ml.setMlogTime(new Date());
						String s ="��"+msList.get(i).getMsName()+"��"+ msList.get(i).getMsId();
						ml.setMoneyToUser(s);
						ml.setUser(user);
						ml.setPayType(0);
						addML = moneyManager.addMoneyLog(ml);
						if(addML==true && i == msList.size()){
							addML = true;
						}
					}
					
					if(addML){
						System.out.println("��˳�ֵ��¼����ɹ�");
					}else{
						System.out.println("��˳�ֵ��¼����ʧ��");
					}
				}else{
					String print1 ="�ն��ײͳ�ִֵ��ʧ�ܣ�";
					String resultmessage1="��ֵ"+msList.size()+"���ն˵��ײͣ�"+pf.getPfType() +"������"+cminfo.getResMoney()+"Ԫ��ʧ��";
					showMessage(request, print1, resultmessage1);
				}
			}
		}else if(cmd.equals("money_check_no")){//��ͨ��
			cminfo.setReadState(1);
			cminfo.setProStatus(2);
			cminfo.setCheckTime(new Date());
			cminfo.setCheckPerson(user);
		}
		
//		if(cmd.equals("money_check_ok")){//�����¼log
//			TbMoneyLog ml=new TbMoneyLog();	
//			ml.setHow(cminfo.getResMoney());
//			ml.setMlogTime(new Date());
//			ml.setMoneyToUser(cminfo.getResId());
//			ml.setUserId(user.getUserId());
//			Boolean addML = moneyManager.addMoneyLog(ml);
//			if(addML){
//				System.out.println("��˳�ֵ��¼����ɹ�");
//			}else{
//				System.out.println("��˳�ֵ��¼����ʧ��");
//			}
//		}
		
		Boolean cm = moneyManager.updateCheck(cminfo);//��������
		if(cm){
			String print ="���ִ�гɹ���";
			String resultmessage="���ִ�гɹ���";
			showMessage(request, print, resultmessage);
		}else{
			String print ="���ִ��ʧ�ܣ�";
			String resultmessage="���ִ��ʧ�ܣ�";
			showMessage(request, print, resultmessage);
		}
		
		//��תҳ��
		if(cminfo.getResRole() == null){
			return mapping.findForward("cmlistinfoms");
		}else if(cminfo.getResRole().getRoleId() == 3 || cminfo.getResRole().getRoleId() == 4){//��ҵ
			return mapping.findForward("cmlistinfoep");
		}else if(cminfo.getResRole().getRoleId() == 2){//������
			return mapping.findForward("cmlistinfoa");
		}

		return null;
	}

	/**
	 * ��ѯ���������б�jspҳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @param roleId 
	 * @return
	 */
	private ActionForward checklistinfojsp(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response, int roleId,int uId) {
		// TODO Auto-generated method stub
		
		String userName="";//������
		String cmd = request.getParameter("CMD");
		int checkRole = 1;//ȫ��
		if(cmd.equals("checklistinfojsp")){
			checkRole = 2;//������
		}else if(cmd.equals("checklistinfoepjsp")){
			checkRole = 10;//��ҵ
		}else if(cmd.equals("checklistinfomsjsp")){
			checkRole = 0;//�ն�
		}
		//��ѯ����
		if(roleId == 40){//�����ɫid:40
			userName = request.getParameter("username");//������
		}
		if (userName == null || userName.equals("")) {
			userName = "";
		}
		String resName = request.getParameter("resname");//��ֵ�ʻ�
		if (resName == null || resName.equals("")) {
			resName = "";
		}
		//���״̬��0��δ��� ��1�����ͨ�� �� 2����˲�ͨ����
		int proStatus =-1;
		if(request.getParameter("prostatus")!=null){
			proStatus=Integer.parseInt(request.getParameter("prostatus"));
		}
		
		
		int cmCount = moneyManager.findCMCount(userName, resName, proStatus, roleId, uId, checkRole);//������
		// ��ȡÿҳ������
		int pageSize = 12;
		// ��ȡ��ҳ��
		int pageCount = (cmCount + pageSize - 1) / pageSize;
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
		request.setAttribute("cmCount", cmCount);
		// ����ÿҳ����ʾ����
		request.setAttribute("pageSize", pageSize);
		// ���ص�ǰҳ
		request.setAttribute("currentPage", pageNo);
		// ���÷��ص�������
		request.setAttribute("CMD", cmd);
//
		// ���ص���ҳ��
		request.setAttribute("pageCount", pageCount);

		request.setAttribute("username", userName);
		request.setAttribute("resname", resName);
		request.setAttribute("prostatus", proStatus);
		// �����û���λ��Ϣ
		List<TbCheckMInfo> list = moneyManager.findAllCMInfo(pageNo, pageSize, userName, resName, proStatus, roleId,uId,checkRole);
//		List cmlist =new ArrayList();
		
		Map<Map, TbCheckMInfo> map =null;
		if(list !=null){
			map = new LinkedHashMap<Map, TbCheckMInfo>();
			for(int i =0 ; i<list.size();i++){
				Map m= new LinkedHashMap();
				TbCheckMInfo cminfo =list.get(i);
				if(cminfo.getResRole()==null){//�ն�
					
					m.put(i,cminfo.getResId());
					map.put(m, cminfo);
				}else{
					if(cminfo.getResRole().getRoleId() == 2){//������
						m.put(i, agentmanger.getAgent_ByAgentID(cminfo.getResId()).getAgent_Name());
						map.put(m, cminfo);
					}else if(cminfo.getResRole().getRoleId() == 3){//��ҵ
						m.put(i, epmanger.getEpinfo_byepid(cminfo.getResId()).getEp_Name());
						map.put(m, cminfo);
					}
				}
			}
		}
		request.setAttribute("cmList",map );
		
		System.out.print(cmCount);
		return mapping.findForward("cmlistinfo");
	}
	
	/**
	 * ���ز�����Ϣ
	 * log
	 */
	private void showMessage(HttpServletRequest request,String print,String resultmessage){
		System.out.println(print);
		request.setAttribute("message", resultmessage);
	}
}
