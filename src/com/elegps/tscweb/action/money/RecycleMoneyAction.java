package com.elegps.tscweb.action.money;

import java.sql.Timestamp;
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
 *�����ʽ�
 * @author wanglei
 *	2015-5-18
 */
public class RecycleMoneyAction extends BaseAction{
	
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
			String mjsp = Money(role_id, user.getAgent_Id().toString(),user.getEp_Id().toString());//���
			request.setAttribute("money",mjsp );//����jspҳ��
			
			if (cmd.equals("hs_ep_money_jsp")) { // ��ת��������ҵ�ʽ�ҳ��
				actionforward = hs_ep_money_jsp(mapping, form, request, response,
						role_id,user.getAgent_Id(),user.getEp_Id());
			}
			if (cmd.equals("hs_ms_money_jsp")) { // ��ת���ն��ʽ�ת��ҳ��
				actionforward = hc_ms_money_jsp(mapping, form, request, response,
						role_id,user.getAgent_Id().toString(),user.getEp_Id().toString());
			}
			if (cmd.equals("hs_ep_money")) { // ִ����ҵ�ʽ�ת��
				actionforward = hc_ep_money(mapping, form, request, response,role_id,user);
			}
			if (cmd.equals("hs_ms_money")) { // ִ���ն��ʽ�ת��
				actionforward = hs_ms_money(mapping, form, request, response,user);
			}
			
		}
		
		return actionforward;
	}

	/**
	 * �ն��ײ�ת��ִ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @param roleId
	 * @return
	 */
	private ActionForward hs_ms_money(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,TbUserInfo user) {
		// TODO Auto-generated method stub
		String zyms = request.getParameter("zyms");//ת���ײ͵��ն�id
		String zdms = request.getParameter("zdms");//�ײ�ת�����ն�id
		
		Calendar c = Calendar.getInstance();//���һ��������ʵ��  
		//SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		c.setTime(new Date());//��������ʱ��   
		c.add(Calendar.MONTH,1);//���������·���������
		Date date = c.getTime();//ϵͳʱ��ǰ1���µ�ʱ��
		
		//ת���ײ͵��ն�id����������ʹ�õģ�����ʱ��������1����
		TbMsInfo zymsinfo = msmanager.getTBMsinfoby_msid(zyms);
		if(zymsinfo == null ){
			showMessage(request, "�ն�"+zyms+"�����ڣ�", "�ն�"+zyms+"�����ڣ�");
			return mapping.findForward("hsms");
		}else if (zymsinfo.getFlag() == 0){
			showMessage(request, "�ն�"+zyms+"�Ѿ�ͣ����flag=0��", "�ն�"+zyms+"�Ѿ�ͣ���ˣ�");
			return mapping.findForward("hsms");
		}else if (zymsinfo.getMsMoneyTime().before(date)){
			showMessage(request, "�ն�"+zyms+"���ò���һ���£�", "�ն�"+zyms+"���ò���һ���£�");
			return mapping.findForward("hsms");
		}
		//�ײ�ת�����ն�id ����Ƿ��״̬���ն�
		TbMsInfo zdmsinfo = msmanager.getTBMsinfoby_msid(zdms);
		if(zdmsinfo == null ){
			String print ="�ն�"+zdms+"�����ڣ�";
			String resultmessage="�ն�"+zdms+"�����ڣ�";
			showMessage(request, print, resultmessage);
			return mapping.findForward("hsms");
		}else if(zdmsinfo.getMsMoneyTime()!=null){
			if(zdmsinfo.getMsMoneyTime().after(new Date())){
				showMessage(request, "�ն�"+zdms+"����ʹ�ã��ײͲ���ת�����з�״̬��", "�ն�"+zdms+"����ʹ�ã��ײͲ���ת����");
				return mapping.findForward("hsms");
			}
		}
		int zyep = zymsinfo.getEpid();
		int zdep = zdmsinfo.getEpid();
		if(zyep!=zdep){//����ͬһ��ҵ
			showMessage(request, "�ն˲���ͬһ��ҵ��", "�ն˲���ͬһ��ҵ��");
			return mapping.findForward("hsms");
		}
		
		Timestamp zyDate = new Timestamp(zymsinfo.getMsMoneyTime().getTime());//ת���ײ͵ĵ���ʱ��
		
		//�����ײ�ת   ��  ���ն���Ϣ
		zymsinfo.setFlag(0);//����
		zymsinfo.setIs_Arrearage(0);//Ƿ��
		//zymsinfo.setPf(pf);
		zymsinfo.setMsMoneyTime(new Date());//����ʱ���Ϊת��ʱ��
		List<TbMsInfo> zymsList = new ArrayList<TbMsInfo>();
		zymsList.add(zymsinfo);
		Boolean zy = msmanager.updateMs(zymsList);//������Ϣ
		if(!zy){
			showMessage(request, "����ת�Ƶ��ն�"+zyms+"ʧ�ܣ�", "�ײ�ת��ʧ�ܣ�");
			return mapping.findForward("hsms");
		}
		//�����ײ�ת   ��   ���ն���Ϣ
		zdmsinfo.setFlag(1);//����ʹ��
		zdmsinfo.setIs_Arrearage(1);//�з�
		zdmsinfo.setMsMoneyTime(zyDate);//ת���ն��ײ͵ĵ���ʱ��
		zdmsinfo.setPf(zymsinfo.getPf());//ת���ײ�����
		List<TbMsInfo> zdmsList = new ArrayList<TbMsInfo>();
		zdmsList.add(zdmsinfo);
		Boolean zd = msmanager.updateMs(zdmsList);//������Ϣ
		if(!zd){
			showMessage(request, "����ת�����ն�"+zyms+"ʧ�ܣ�", "�ײ�ת��ʧ�ܣ�");
			return mapping.findForward("hsms");
		}
		
		TbMoneyLog ml=new TbMoneyLog();	
		ml.setHow("0.00");
		ml.setMlogTime(new Date());
		ml.setMoneyToUser("��"+zdmsinfo.getMsName()+"��" + zdmsinfo.getMsId());//�ײ�ת�����ն�ID
		ml.setUser(user);
		ml.setPayType(4);//��ֵ����  �ն��ײ�ת�ƣ�4
		ml.setPayNum(zymsinfo.getMsId()); //ת���ײ͵��ն�id
		Boolean aml = moneyManager.addMoneyLog(ml);
		if(!aml){
			showMessage(request, "�ն��ײ�ת�Ƽ�¼����ʧ�ܣ�", "��¼ʧ�ܣ�");
			return mapping.findForward("hsms");
		}
		showMessage(request, "�ն��ײ�ת��ִ�гɹ���", "�ն��ײ�ת�Ƴɹ���");
		return mapping.findForward("hsms");
	}

	/**
	 * �ն��ײ�ת��ҳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @param roleId
	 * @param string
	 * @param string2
	 * @return
	 */
	private ActionForward hc_ms_money_jsp(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response, int roleId, String agentid,
			String epId) {
		// TODO Auto-generated method stub
		List<TbPFInfo> pfList = pfManager.findAllPFInfo();
		request.setAttribute("pfList", pfList);
		
		String pagentid = ""; // һ��������id
		String childagentid = ""; // ����������id
		String agent = request.getSession().getAttribute("agentId")+"";
		if (request.getParameter("pagentid") != null && (agent.equals("-1") || agent.equals("0"))) {
			pagentid = request.getParameter("pagentid");
		} else {
			 //pagentid = "-1"; // ���������ܲ�
        	pagentid = agent;
        	if(pagentid.equals("0"))
        		pagentid = "-1";
		}

		if (request.getParameter("childagentid") != null) {
			childagentid = request.getParameter("childagentid");
		} else {
			childagentid = "-1"; // ֱ����ҵ
		}

		String ep = request.getParameter("ep");
		if (ep == null || ep.equals("null")) {
			ep = "-1";
		}
		
//		if(!pagentid.equals("-1")){
//			// һ������������
//			request.setAttribute("Pagentlist", agentmanger.getParentAgentNotIncludeZB());
//		}else{
			// һ������������ request.setAttribute("Pagentlist", agentmanger.getParentAgent(agenttype,r_id));
			request.setAttribute("Pagentlist", agentmanger.getParentAgent(Integer.parseInt(agentid),roleId));
//		}
		
		// һ��ָ�����������̽����
		request.setAttribute("Cagentlist", agentmanger
				.getChildAgentByParamentid(pagentid));
		// ����������ҵĬ��
		List<TbEnterpriseInfo> list = null;
		if (roleId == 3 || roleId == 4){
			list = epmanger.getEpinfo_byeid(pagentid,
					childagentid, Integer.parseInt(epId), roleId);
		}else{
			list = epmanger.getEpinfo_byagentid(pagentid,
					childagentid, Integer.parseInt(agentid), roleId);
		}
		request.setAttribute("epList", list);
        if(ep!="-1"){
//        	request.setAttribute("grpList", grpmanager.getAllGrp_Info(pagentid,childagentid,ep));
        }
		request.setAttribute("pagentid", pagentid);
		request.setAttribute("childagentid", childagentid);
		
		return mapping.findForward("hsmsjsp");
	}

	/**
	 * ִ����ҵ�ʽ�ת��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @param roleId
	 * @param user
	 * @return
	 */
	private ActionForward hc_ep_money(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			int roleId, TbUserInfo user) {
		// TODO Auto-generated method stub
		//��ȡ���յ���ҵid
		String epid = request.getParameter("epid");
		if(epid != null && !epid.equals("")){
			TbEnterpriseInfo epInfo = epmanger.getEpinfo_byepid(epid);
			String epMoney = "0";
			if(epInfo.getEp_Money() != null && epInfo.getEp_Money()!="")
				epMoney = epInfo.getEp_Money();
			
			TbAgentInfo aInfo = agentmanger.getAgent_ByAgentID(epInfo.getAgent_Id()+"");
			if(aInfo.getAgent_Id() == -1){//ֱ����ҵ���ܻ���
				String print ="ֱ����ҵ���ܻ��գ�";
				String resultmessage="ֱ����ҵ���ܻ��գ�";
				showMessage(request, print, resultmessage);
				return mapping.findForward("hsep");
			}
			if(roleId == 2){//������
				if(Double.parseDouble(epMoney) >= 0){
					Double newMoney = Double.parseDouble(aInfo.getAgent_Money()) + Double.parseDouble(epMoney);
					aInfo.setAgent_Money(newMoney+"");
					Boolean a = agentmanger.updateAgent(aInfo);
					if(a){
						
						epInfo.setEp_Money("0.00");
						Boolean ep =epmanger.updateEp(epInfo);
						if(ep){
							//���ݿ�log��¼
							String print ="������ҵ���ɹ���";
							String resultmessage="�ɹ�����" + epMoney +"Ԫ";
							showMessage(request, print, resultmessage);
							
								TbMoneyLog ml=new TbMoneyLog();	
								ml.setHow(epMoney);
								ml.setMlogTime(new Date());
								ml.setMoneyToUser(user.getUserName());
								ml.setUser(user);
								ml.setPayType(3);//������ҵ���
								ml.setPayNum(epInfo.getEp_Id()+"");
							Boolean b = moneyManager.addMoneyLog(ml) ;//addLog(Double.parseDouble(epInfo.getEp_Money()),name,user);//���log���ݵ����ݿ�
				
							if(b){
								System.out.println("������ҵ����¼����ɹ�");
							}else{
								System.out.println("������ҵ����¼����ʧ��");
							}
						}
					}
				}
			}else if(roleId == 35 || roleId == 36 || roleId == 1){
				if(Double.parseDouble(epMoney) >= 0){
					Double newMoney = Double.parseDouble(aInfo.getAgent_Money()) + Double.parseDouble(epMoney);
					aInfo.setAgent_Money(newMoney+"");
					Boolean a = agentmanger.updateAgent(aInfo);
					if(a){
						
						epInfo.setEp_Money("0.00");
						Boolean ep =epmanger.updateEp(epInfo);
						if(ep){
							//���ݿ�log��¼
							String print ="������ҵ���ɹ����������̣�"+aInfo.getAgent_Name()+"";
							String resultmessage="�ɹ�����" + epMoney +"Ԫ�������̣���"+aInfo.getAgent_Name()+"��";
							showMessage(request, print, resultmessage);
							
								TbMoneyLog ml=new TbMoneyLog();	
								ml.setHow(epMoney);
								ml.setMlogTime(new Date());
								ml.setMoneyToUser(aInfo.getAgent_Name());
								ml.setUser(user);
								ml.setPayType(3);//������ҵ���
								ml.setPayNum(epInfo.getEp_Id()+"");
							Boolean b = moneyManager.addMoneyLog(ml) ;//addLog(Double.parseDouble(epInfo.getEp_Money()),name,user);//���log���ݵ����ݿ�
				
							if(b){
								System.out.println("������ҵ����¼����ɹ�");
							}else{
								System.out.println("������ҵ����¼����ʧ��");
							}
						}
					}
				}
			}
		}
		return mapping.findForward("hsep");
	}

	/**
	 * ��ת��������ҵ�ʽ�ҳ�棨list��ҳ��ʾ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @param roleId
	 * @param agentId
	 * @param epId
	 * @return
	 */
	private ActionForward hs_ep_money_jsp(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response, int roleId, Integer agentId,
			Integer epId) {
		// TODO Auto-generated method stub
		String pagentid = ""; // һ��������id
		String childagentid = ""; // ����������id
		
		if (request.getParameter("pagentid") != null && (agentId == -1 || agentId == 0)) {
			pagentid = request.getParameter("pagentid");
		} else {
			//pagentid = "-1"; // ���������ܲ�
			pagentid = agentId+"";
        	if(pagentid.equals("0"))
        		pagentid = "-1";
		}

		if (request.getParameter("childagentid") != null) {
			childagentid = request.getParameter("childagentid");
		} else {
			childagentid = "-2"; // ������ҵ
		}
		String cmd = request.getParameter("CMD");
		String epname = request.getParameter("epname");
		if (epname == null || epname.equals("")) {
			epname = "";
		}
		
		int epCount = 0;
		epCount = epmanger.getEp_sertch(pagentid, childagentid, epname.trim(), epId, roleId);
		// ��ȡÿҳ������
		int pageSize = 12;
		// ��ȡ��ҳ��
		int pageCount = epmanger.getPageCount(epCount, pageSize);
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
		request.setAttribute("epCount", epCount);
		// ����ÿҳ����ʾ����
		request.setAttribute("pageSize", pageSize);
		// ���ص�ǰҳ
		request.setAttribute("currentPage", pageNo);
		// ���÷��ص�������
		request.setAttribute("CMD", cmd);

		// ���ص���ҳ��
		request.setAttribute("pageCount", pageCount);
		//if(!pagentid.equals("-1")){
			// һ������������
		//	request.setAttribute("Pagentlist", agentmanger.getParentAgentNotIncludeZB());
		//}else{
			// һ������������
			request.setAttribute("Pagentlist", agentmanger.getParentAgent(agentId,roleId));
		//}
		// һ��ָ�����������̽����
		request.setAttribute("Cagentlist", agentmanger
				.getChildAgentByParamentid(pagentid));
		// �����÷����û���ѯ��select��optionֵ
		request.setAttribute("pagentid", pagentid);
		request.setAttribute("childagentid", childagentid);
		request.setAttribute("epname", epname);
		// �����û���λ��Ϣ
		List<TbEnterpriseInfo> list = null;
		if (roleId == 3 || roleId == 4){
			list = epmanger.getEpinfoby_mspage(pageNo,
					pageSize, pagentid, childagentid, epname.trim(), epId, roleId);
		}else{
			list = epmanger.getEpinfoby_mspage(pageNo,
					pageSize, pagentid, childagentid, epname.trim(), agentId, roleId);
		}
		request.setAttribute("epList",list );

			return mapping.findForward("hsepjsp");
	}


	/**
	 * ��ȡ��¼�û����
	 * @param roleId
	 * @param agentid
	 * @param epId
	 * @return
	 */
	private String Money(int roleId,String agentid,String epId){
		String m =null;
		if(roleId == 4){//��ҵ��ɫ
			m = ((TbEnterpriseInfo)epmanger.getEpinfo_byepid(epId)).getEp_Money();
		}else{//�����̽�ɫ
			m = ((TbAgentInfo)agentmanger.getAgent_ByAgentID(agentid)).getAgent_Money();
		}
		if(m == null || m == ""){
			m="0.00";
		}
		return m;
	}
	
	/**
	 * ��ֵ��Ӽ�¼
	 * @param m
	 * @param name
	 * @param uId
	 * @return
	 */
	private  Boolean addLog(Double m,String name,TbUserInfo user){
		TbMoneyLog ml=new TbMoneyLog();	
		ml.setHow(Double.toString(m));
		ml.setMlogTime(new Date());
		ml.setMoneyToUser(name);
		ml.setUser(user);
		
		return moneyManager.addMoneyLog(ml);
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
