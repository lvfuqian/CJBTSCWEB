package com.elegps.tscweb.action.money;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.alipay.config.AlipayConfig;
import com.alipay.util.AlipaySubmit;
import com.alipay.util.UtilDate;
import com.elegps.tscweb.action.base.BaseAction;
import com.elegps.tscweb.comm.UserInfo;
import com.elegps.tscweb.exception.MessageException;
import com.elegps.tscweb.model.TbAgentInfo;
import com.elegps.tscweb.model.TbCheckMInfo;
import com.elegps.tscweb.model.TbEnterpriseInfo;
import com.elegps.tscweb.model.TbMoneyLog;
import com.elegps.tscweb.model.TbMsInfo;
import com.elegps.tscweb.model.TbPFInfo;
import com.elegps.tscweb.model.TbRoleInfo;
import com.elegps.tscweb.model.TbUserInfo;
import com.elegps.tscweb.tscconfig.FilePathConfig;
import com.wechatpay.demo.Demo;
import com.wechatpay.demo.WxPayDto;


/**
 * level 2015-4-2
 * @author wanglei
 *
 */
public class MoneyAction extends BaseAction{

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
			
			if (cmd.equals("money_czjsp")) { // ��ҵ,�����̵�¼��ֵҳ��
				actionforward = ep_or_angent_money_czjsp(mapping, form, request, response,
						role_id,user.getAgent_Id().toString(),user.getEp_Id().toString());
			}
			if (cmd.equals("epangent_money_cz")) { // ������ ��ҵ��¼��ֵҳ���ֵ����ִ��
				actionforward = epangent_money_cz(mapping, form, request, response,
						role_id,user.getAgent_Id().toString(),user.getEp_Id().toString(),
						user);
			}
			
			
			if (cmd.equals("money_listinfojsp")) { // ��ҵ���ù���ҳ��
				actionforward = money_listinfojsp(mapping, form, request, response,
						role_id,user.getAgent_Id().toString(),user.getEp_Id().toString());
			}
			if (cmd.equals("money_infoczjsp")) { // ��ҵ���ù���ҳ���ֵ����ִ�У���ת����ҵ��ֵ��Ϣҳ�棩
				actionforward = money_infoczjsp(mapping, form, request, response,
						role_id,user.getAgent_Id().toString(),user.getEp_Id().toString());
			}
			if (cmd.equals("money_infocz")) { // ��ҵ���ù����ֵҳ���ֵ����ִ��
				actionforward = money_infocz(mapping, form, request, response,
						role_id,user.getAgent_Id().toString(),user.getEp_Id().toString(),user);
			}
			if (cmd.equals("money_infoczep")) { // ��ҵ���ù����ֵҳ���ֵ����ִ��
				actionforward = money_infoczep(mapping, form, request, response,role_id,user);
			}
			
			if (cmd.equals("money_listinfo_ajsp")) { // �����̷��ù����ֵҳ���ֵ����ִ��
				actionforward = money_listinfo_ajsp(mapping, form, request, response,
						role_id,user.getAgent_Id().toString(),user.getEp_Id().toString());
			}
			if (cmd.equals("money_info_cza_jsp")) { // �����̷��ù���ҳ���ֵ����ִ�У���ת�������̳�ֵ��Ϣҳ�棩
				actionforward = money_info_cza_jsp(mapping, form, request, response,
						role_id,user.getAgent_Id().toString(),user.getEp_Id().toString());
			}
			if (cmd.equals("money_infocza")) { // �����̷��ù����ֵҳ���ֵ����ִ��
				actionforward = money_infocza(mapping, form, request, response,user);
			}
			
			if (cmd.equals("ms_money_listinfojsp")) { // �ն��ײͷ���listҳ��
				actionforward = ms_money_listinfojsp(mapping, form, request, response,
						role_id,user.getAgent_Id().toString(),user.getEp_Id().toString());
			}
			if (cmd.equals("ms_money_plczjsp")) { // �ն��ײ�����ҳ��
				actionforward = ms_money_plczjsp(mapping, form, request, response,
						role_id,user.getAgent_Id().toString(),user.getEp_Id().toString());
			}
			if (cmd.equals("ms_money_infojsp")) { // �ն��ײͷ���listҳ���ֵ����ִ�У���ת���ն˳�ֵ��Ϣҳ�棩
				actionforward = ms_money_infojsp(mapping, form, request, response,
						role_id,user.getAgent_Id().toString(),user.getEp_Id().toString());
			}
			if (cmd.equals("ms_money_cz")) { // �ն˳�ֵҳ���ֵ����ִ��
				actionforward = ms_money_cz(mapping, form, request, response,
						role_id,user.getAgent_Id().toString(),user.getEp_Id().toString(),user);
			}
			if (cmd.equals("ms_money_plcz")) { // �ն�������ֵҳ��������ֵ����ִ��
				actionforward = ms_money_plcz(mapping, form, request, response,
						role_id,user.getAgent_Id().toString(),user.getEp_Id().toString(),user);
			}
			if (cmd.equals("money_infoczmslist")) { // �ն˷��ù����ֵҳ���ֵ����ִ��
				actionforward = money_infoczmslist(mapping, form, request, response,user);
			}
			
			if (cmd.equals("moneylog")) { // �ն˷��ù����ֵҳ���ֵ����ִ��
				actionforward = moneylog(mapping, form, request, response,role_id,user.getAgent_Id().toString(),user.getEp_Id().toString(),user);
			}
			if (cmd.equals("apppaylog")) { // ��ѯapp�ն˳�ֵ��Ϣ��¼list
				actionforward = apppaylog(mapping, form, request, response,role_id,user.getAgent_Id().toString(),user.getEp_Id().toString(),user);
			}
		}
		return actionforward;
	}
	
	private ActionForward apppaylog(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			int roleId, String uAid, String uEid, TbUserInfo user) {
		
		return null;
	}

	/**
	 * ��ֵ��¼��ѯ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @param user
	 * @return
	 */
	private ActionForward moneylog(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			int role_id,String agentid,String epId,TbUserInfo user) {
		// TODO Auto-generated method stub
		String mjsp = Money(role_id, agentid, epId);//��� 
		request.setAttribute("money",mjsp );//����jspҳ��
		
		String cmd = request.getParameter("CMD");
		String userName =request.getParameter("uname");//��ֵ����
		if (userName == null || (userName.trim()).equals("")) {
			userName = "";
		}
		String dateStart = request.getParameter("date1");//��ѯ��ʼʱ��
		if (dateStart == null) {
			dateStart = "";
		}
		String dateEnd = request.getParameter("date2");//��ѯ����ʱ��
		if (dateEnd == null) {
			dateEnd = "";
		}
		
		int ptype = 0 ;//��ֵ��ʽ
		if(request.getParameter("ptype") != null){
			ptype = Integer.parseInt(request.getParameter("ptype"));
		}
		
		String pay_num = request.getParameter("pay_num");//
		String teadeNo = request.getParameter("teadeNo");//���׺�
		String orderNo = request.getParameter("orderNo");//������
		if (pay_num == null || (pay_num.trim()).equals("")) {
			pay_num = "";
		}
		if (teadeNo == null || (teadeNo.trim()).equals("")) {
			teadeNo = "";
		}
		if (orderNo == null || (orderNo.trim()).equals("")) {
			orderNo = "";
		}
		
		request.setAttribute("ptype",ptype );
		request.setAttribute("pay_num",pay_num );
		request.setAttribute("teadeNo",teadeNo );
		request.setAttribute("orderNo",orderNo );
		
		int mlCount = 0;
		mlCount = moneyManager.getMoneyLogCount(role_id, user.getUserId(), userName, dateStart, dateEnd, ptype, pay_num, teadeNo, orderNo);
		// ��ȡÿҳ������
		int pageSize = 12;
		// ��ȡ��ҳ��
		int pageCount = (mlCount + pageSize - 1) / pageSize;
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
		request.setAttribute("mlCount", mlCount);
		// ����ÿҳ����ʾ����
		request.setAttribute("pageSize", pageSize);
		// ���ص�ǰҳ
		request.setAttribute("currentPage", pageNo);
		// ���÷��ص�������
		request.setAttribute("CMD", cmd);
//
		// ���ص���ҳ��
		request.setAttribute("pageCount", pageCount);
		
		request.setAttribute("uname",userName );
		request.setAttribute("date1",dateStart );
		request.setAttribute("date2",dateEnd );
		
		// �����û���λ��Ϣ
		List<TbEnterpriseInfo> list = null;
		
		list = moneyManager.getMoneyLogListByPage(pageNo, pageSize, role_id, user.getUserId(), userName, dateStart, dateEnd, ptype, pay_num, teadeNo, orderNo);
		
		request.setAttribute("mlList",list );
		
		return mapping.findForward("moneylog_listjsp");
	}

	/**
	 * ���ն˳�ֵ(����Աִ��)
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @param userId
	 * @return
	 */
	private ActionForward money_infoczmslist(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response
			,TbUserInfo user
			) {
		
		String pfId = request.getParameter("pfType");//��ֵ���ײ�id
		String ms_id[] = request.getParameterValues("selectmsid");//������ֵ��id
		String moneydiscription = request.getParameter("moneydiscription");//��ֵ��������
		String epName = request.getParameter("ep");//��ҵid
		
		String msidsb = new String();
		msidsb += epmanger.getEpinfo_byepid(epName).getEp_Name()+"��"+ms_id.length+"���ն�"+",";
		for(String s:ms_id){
			msidsb += s+",";
		}
		String str =new String(msidsb);
		
		
		TbPFInfo pfInfo = pfManager.findPFInfoById(pfId); //�ײ�
//		List<TbMsInfo> msListInfo = new ArrayList<TbMsInfo>();//�ն�list
//		for(int i = 0 ; i< ms_id.length;i++){
//			TbMsInfo msInfo =new TbMsInfo();
//			msInfo = msmanager.getTBMsinfoby_msid(ms_id[i]);//�ն�
//			msListInfo.add(msInfo);
//		}
		
		Double deMoney = Double.parseDouble(pfInfo.getPfHow()) * ms_id.length;
		//�ύ����
		
		TbCheckMInfo checkInfo = new TbCheckMInfo();
		checkInfo.setProposer(user);//������(��¼�û�)
		checkInfo.setProDescription(moneydiscription);//��������
		checkInfo.setProStatus(0);//δ���״̬
		checkInfo.setResMoney(deMoney.toString());//������
		checkInfo.setProTime(new Date());//����ʱ��
		checkInfo.setResId(str);//��ֵ�ʺ�id
		//checkInfo.setResRole(new TbRoleInfo());//��ֵ�ʺŽ�ɫ���ն˽�ɫ��
		Boolean check = moneyManager.addCheck(checkInfo);
		if(check){
			String print ="��ֵ����ִ�гɹ���";
			String resultmessage="��ֵ����ִ�гɹ�";
			showMessage(request, print, resultmessage);
		}
		
		return mapping.findForward("cmlistinfoms");//��ת�������б�ҳ
	}
	
	/**
	 * ����ҵ��ֵ(����Աִ��)
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @param userId
	 * @return
	 */
	private ActionForward money_infoczep(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response
			,int role_id,TbUserInfo user
			) {
		String epid = request.getParameter("epid");//��ֵ��	��ҵid	
		String newMoney = request.getParameter("moneyhow");//ҳ��������
		String moneydiscription = request.getParameter("moneydiscription");//��ֵ��������
		//�ύ����
		
		TbCheckMInfo checkInfo = new TbCheckMInfo();
		checkInfo.setProposer(user);//������(��¼�û�)
		checkInfo.setProDescription(moneydiscription);//��������
		checkInfo.setProStatus(0);//δ���״̬
		checkInfo.setResMoney(newMoney);//������
		checkInfo.setProTime(new Date());//����ʱ��
		checkInfo.setResId(epid);//��ֵ�ʺ�id
		//���ִ�����ҵ��ֱ����ҵ
		String r_id = "3";
		if(epmanger.getEpinfo_byepid(epid).getAgent_Id() == -1){
			r_id = "4";
		}else{
			r_id = "3";
		}
		checkInfo.setResRole(rolemanager.getRoleInfoby_roleid(r_id));//��ֵ�ʺŽ�ɫ����ҵ��ɫ��
		Boolean check = moneyManager.addCheck(checkInfo);
		if(check){
			String print ="��ֵ����ִ�гɹ���";
			String resultmessage="��ֵ����ִ�гɹ�";
			showMessage(request, print, resultmessage);
		}
		return mapping.findForward("cmlistinfoep");//��ת�������б�ҳ
	}
	
	/**
	 * �������̳�ֵ(����Աִ��)
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @param userId
	 * @return
	 */
	private ActionForward money_infocza(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response
//			int roleId, String agentid,
//			String epId
			,TbUserInfo user
			) {
		// TODO Auto-generated method stub
		//�ж�����Ƿ��㹻
//		Double checkMoney=0.00;
//		if(roleId == 3){//��ҵ��ɫ
//			TbEnterpriseInfo ep = epmanger.getEpinfo_byepid(epId);
//			checkMoney = Double.valueOf(ep.getEp_Money());
//		}else{//�����̽�ɫ
//			if(roleId == 1 || roleId == 35){//����Ա����������Ա
//				agentid = "-1";//�ܲ�
//			}
//			TbAgentInfo a = agentmanger.getAgent_ByAgentID(agentid);
//			checkMoney = Double.valueOf(a.getAgent_Money());
//		}
//		if(checkMoney<0 || checkMoney == 0){
//			String print ="���㣡";
//			String resultmessage="���㣬�޷�������";
//			showMessage(request, print, resultmessage);
//			return  ep_or_angent_money_czjsp(mapping, form, request, response,
//					roleId,agentid,epId);
//		}
	
	
		String aid = request.getParameter("aid");//��ֵ�Ĵ�����id	
		String newMoney = request.getParameter("moneyhow");//ҳ��������
		String moneydiscription = request.getParameter("moneydiscription");//��ֵ��������
		//�ύ����
		
		TbCheckMInfo checkInfo = new TbCheckMInfo();
		checkInfo.setProposer(user);//������(��¼�û�)
		checkInfo.setProDescription(moneydiscription);//��������
		checkInfo.setProStatus(0);//δ���״̬
		checkInfo.setResMoney(newMoney);//������
		checkInfo.setProTime(new Date());//����ʱ��
		checkInfo.setResId(aid);//��ֵ�ʺ�id
		checkInfo.setResRole(rolemanager.getRoleInfoby_roleid("2"));//��ֵ�ʺŽ�ɫ�������̽�ɫ��
		Boolean check = moneyManager.addCheck(checkInfo);
		if(check){
			String print ="��ֵ����ִ�гɹ���";
			String resultmessage="��ֵ����ִ�гɹ�";
			showMessage(request, print, resultmessage);
		}
//		//��Ǯ
//		Double deMoney =Double.parseDouble("-" + newMoney);
//		Boolean deduct = AddAndDeductMoney(roleId,agentid,epId,deMoney);//�۳���ֵ�˵����
//		if(deduct){
//			String print ="�۳�ִ�гɹ���";
//			String resultmessage="";
//			showMessage(request, print, resultmessage);
//			String nn="";
//			nn = agentmanger.getAgent_ByAgentID(aid).getAgent_Man().toString();
//
//			Boolean a =  addLog(deMoney,nn,uId);//���log���ݵ����ݿ�
//
//			if(a){
//				System.out.println("�۳���¼����ɹ�");
//			}else{
//				System.out.println("�۳���¼����ʧ��");
//			}
			
			//��ֵ
//			Double money =Double.parseDouble(agentmanger.getAgentName(aid).getAgent_Money());//���������
//			Boolean update =AddMoneyA(aid, newMoney);//���³�ֵ�Ľ������
//			if(update){//��ӳ�ֵ��¼log
//				print ="��ִֵ�гɹ���";
//				resultmessage="�ɹ�Ϊ"+nn+"��ֵ" + newMoney +"Ԫ";
//				showMessage(request, print, resultmessage);
//				
//				String name = null;
//	
//				name = agentmanger.getAgent_ByAgentID(aid).getAgent_Name();
//	
//				Boolean b =  addLog(newMoney,name,uId);//���log���ݵ����ݿ�
//	
//				if(b){
//					System.out.println("��ֵ��¼����ɹ�");
//				}else{
//					System.out.println("��ֵ��¼����ʧ��");
//				}
//			}
//		}
		return mapping.findForward("cmlistinfo");//��ת�������б�ҳ
	}

	/**
	 * Ϊ�����̳�ֵҳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @param roleId
	 * @param string
	 * @param string2
	 * @return
	 */
	private ActionForward money_info_cza_jsp(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response,int roleId, String agentid,
			String epId) {
		// TODO Auto-generated method stub
		String mjsp = Money(roleId, agentid, epId);//���
		request.setAttribute("money",mjsp );//����jspҳ��
		String aId =request.getParameter("aid");//��ȡҳ�洫��Ҫ��ֵ�Ĵ���id
		request.setAttribute("aInfo",agentmanger.getAgent_ByAgentID(aId));//���ش�����Ϣ��ҳ��
		return mapping.findForward("cqinfoajsp");//����������ֵҳ��;
	}

	/**
	 * ������list jsp
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @param roleId
	 * @param string
	 * @param string2
	 * @return
	 */
	private ActionForward money_listinfo_ajsp(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response,int roleId, String agentid,
			String epId) {
		// TODO Auto-generated method stub
		String mjsp = Money(roleId, agentid, epId);//���
		request.setAttribute("money",mjsp );//����jspҳ��
		
		String cmd = request.getParameter("CMD");
		String agent_name = request.getParameter("agent_name");// ����������
		String agent_type = request.getParameter("agent_type");// ����������
		String type=request.getParameter("type");
		if (agent_name == null || (agent_name.trim()).equals("")) {
			agent_name = "";
		}
		if (agent_type == null || (agent_type.trim().equals(""))) {
			agent_type = "-1";
		}
		int agentCount = 0;
		agentCount = agentmanger.getAgent_SearchCount(agent_name, agent_type);
		// ��ȡÿҳ������
		int pageSize = 12;
		// ��ȡ��ҳ��
		int pageCount = agentmanger.getPageCount(agentCount, pageSize);
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
		request.setAttribute("agentCount", agentCount);
		// ����ÿҳ����ʾ����
		request.setAttribute("pageSize", pageSize);
		// ���ص�ǰҳ
		request.setAttribute("currentPage", pageNo);
		// ���÷��ص�������
		request.setAttribute("CMD", cmd);
		// ���÷����û�����
		request.setAttribute("agent_type", agent_type);
		// ���÷����û���ѯ��intput��ֵ
		request.setAttribute("agent_name", agent_name);

		// ���ص���ҳ��
		request.setAttribute("pageCount", pageCount);
		// �������е�һ��������

		request.setAttribute("pagentlist", agentmanger.getParentAgent(Integer.parseInt(agentid),roleId));
		request.setAttribute("agentList", agentmanger.getTbAgentInfoby_name(
				pageNo, pageSize, agent_name, agent_type));
		return mapping.findForward("cqlistinfo_ajsp");
	}

	/**
	 * �ն�������ֵ����
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
private ActionForward ms_money_plcz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			int roleId, String agentid,
			String epId, TbUserInfo user) {
		// TODO Auto-generated method stub
		//�ж�����Ƿ��㹻
		Double checkMoney=0.00;
		if(roleId == 3|| roleId == 4){//��ҵ��ɫ
			TbEnterpriseInfo ep = epmanger.getEpinfo_byepid(epId);
			checkMoney = Double.valueOf(ep.getEp_Money());
		}else{//�����̽�ɫ
			if(roleId == 1 || roleId == 35){//����Ա����������Ա
				agentid = "-1";//�ܲ�
			}
			TbAgentInfo a = agentmanger.getAgent_ByAgentID(agentid);
			checkMoney = Double.valueOf(a.getAgent_Money());
		}
		if(checkMoney<0 || checkMoney == 0){
			String print ="���㣡";
			String resultmessage="���㣬�޷�������";
			showMessage(request, print, resultmessage);
			return  ms_money_plczjsp(mapping, form, request, response,
					roleId,agentid,epId);
		}
		
	
		String pfId = request.getParameter("pfType");//��ֵ���ײ�id
		String ms_id[] = request.getParameterValues("selectmsid");//������ֵ��id
		
		TbPFInfo pfInfo = pfManager.findPFInfoById(pfId); //�ײ�
		List<TbMsInfo> msListInfo = new ArrayList<TbMsInfo>();//�ն�list
		for(int i = 0 ; i< ms_id.length;i++){
			TbMsInfo msInfo =new TbMsInfo();
			msInfo = msmanager.getTBMsinfoby_msid(ms_id[i]);//�ն�
			msListInfo.add(msInfo);
		}
		
		Double deMoney = Double.parseDouble(pfInfo.getPfHow()) * (-1*(ms_id.length));
		Boolean deduct = AddAndDeductMoney(roleId,agentid,epId,deMoney);//�۳���ֵ�˵����
		if(deduct){
			String print ="�۳�ִ�гɹ���";
			String resultmessage="";
			showMessage(request, print, resultmessage);
			
//			String msidsb = new String();
//			for(String s:ms_id){
//				msidsb += ","+s;
//			}
//			String str =new String(msidsb);
//			Boolean a = false;
//			for(int i = 0;i < msListInfo.size() ; i++ ){
//				String ss ="��"+msListInfo.get(i).getMsName()+"��"+ msListInfo.get(i).getMsId();
//				Boolean c = addLog(Double.parseDouble(pfInfo.getPfHow()), ss, user);//���log���ݵ����ݿ�
//				if(c == true  && i == msListInfo.size()){
//					a = true;
//				}
//			}
//			
//			if(a){
//				System.out.println("�۳���¼����ɹ�");
//			}else{
//				System.out.println("�۳���¼����ʧ��");
//			}
			//��ֵ�ն�
			Calendar c = Calendar.getInstance();//���һ��������ʵ��     
			SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			for(int i=0;i<msListInfo.size();i++){
				TbMsInfo ms=new TbMsInfo();
				ms =msListInfo.get(i);
				ms.setPf(pfInfo);//�޸��ն��ײ���Ϣ
				
				//���ڱȽϣ�����ʱ��<��ֵʱ�䣬�����ڳ�ֵ��ʱ������ϼ����ײ�ʱ����������ʱ������ϼ����ײ�ʱ��
				if(ms.getMsMoneyTime()!=null){
					if(ms.getMsMoneyTime().before(new Date())){//����ʱ���ڳ�ֵʱ��֮ǰ
						c.setTime(new Date());//��������ʱ��   
						c.add(Calendar.MONTH,pfInfo.getPfTime());//���������·���������
					}else{
						c.setTime(ms.getMsMoneyTime());//��������ʱ��   
						c.add(Calendar.MONTH,pfInfo.getPfTime());//���������·���������
					}
				}else{
					c.setTime(new Date());//��������ʱ��   
					c.add(Calendar.MONTH,pfInfo.getPfTime());//���������·���������
				}
				ms.setMsMoneyTime(c.getTime());//�޸��ײ͵���ʱ��
				ms.setIs_Arrearage(1);//�ʷ�״̬��Ϊ�з�
				ms.setFlag(1);//��Ϊ����״̬
				msListInfo.set(i, ms);
			}
			
			Boolean upms = msmanager.updateMs(msListInfo);
			if(upms){
				String print1 ="�ն��ײͳ�ִֵ�гɹ���";
				String resultmessage1="�ɹ���ֵ"+msListInfo.size()+"���ն˵��ײͣ�"+pfInfo.getPfType() +"������"+deMoney+"Ԫ";
				showMessage(request, print1, resultmessage1);
				
				Boolean b = false;
				for(int i = 0;i < msListInfo.size() ; i++ ){
					String s ="��"+msListInfo.get(i).getMsName()+"��"+ msListInfo.get(i).getMsId();
					Boolean d = addLog(-1*Double.parseDouble(pfInfo.getPfHow()), s, user,0, null,null,null);//���log���ݵ����ݿ�
					if(d == true  && i == msListInfo.size()){
						b = true;
					}
				}
				//Boolean b = addLog(-1*deMoney, str, uId);//���log���ݵ����ݿ�
				if(b){
					System.out.println("�ն��ײ�������ֵ��¼����ɹ�");
				}else{
					System.out.println("�ն��ײ�������ֵ��¼����ʧ��");
				}
			}else{
				System.out.println("�ն��ײͳ�ִֵ��ʧ�ܣ�");
			}
		}
		
		return  ms_money_plczjsp(mapping, form, request, response,
				roleId,agentid,epId);
	}


	/**
	 * ������ֵjsp
	 */
	private ActionForward ms_money_plczjsp(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response,int roleId, String agentid,
			String epId) {
		// TODO Auto-generated method stub
		String mjsp = Money(roleId, agentid, epId);//���
		request.setAttribute("money",mjsp );//����jspҳ��
		
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
		request.setAttribute("Pagentlist", agentmanger.getParentAgent(Integer.parseInt(agentid),roleId));
//		if(!pagentid.equals("-1")){
//			// һ������������
//			request.setAttribute("Pagentlist", agentmanger.getParentAgentNotIncludeZB());
//		}else{
//			// һ������������
//			request.setAttribute("Pagentlist", agentmanger.getParentAgent(Integer.parseInt(agentid),roleId));
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
		
		return mapping.findForward("ms_money_plczjsp");
	}


	
	/**
	 * �ն˳�ֵ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @param roleId
	 * @param agentid
	 * @param epId
	 * @return
	 */
private ActionForward ms_money_cz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			int roleId, String agentid,
			String epId, TbUserInfo user) {
		// TODO Auto-generated method stub
	//�ж�����Ƿ��㹻
//	Double checkMoney=0.00;
//	if(roleId == 3 || roleId == 4){//��ҵ��ɫ
//		TbEnterpriseInfo ep = epmanger.getEpinfo_byepid(epId);
//		checkMoney = Double.valueOf(ep.getEp_Money());
//	}else{//�����̽�ɫ
//		if(roleId == 1 || roleId == 35){//����Ա����������Ա
//			agentid = "-1";//�ܲ�
//		}
//		TbAgentInfo a = agentmanger.getAgent_ByAgentID(agentid);
//		checkMoney = Double.valueOf(a.getAgent_Money());
//	}
//	if(checkMoney<0 || checkMoney == 0){
//		String print ="���㣡";
//		String resultmessage="���㣬�޷�������";
//		showMessage(request, print, resultmessage);
//		return  ms_money_listinfojsp(mapping, form, request, response,
//				roleId,agentid,epId);
//	}
//		
//		String pfId = request.getParameter("pfType");//��ֵ���ײ�id
//		String msId = request.getParameter("msid");//��ֵ���ն�id
//		
//		TbPFInfo pfInfo = pfManager.findPFInfoById(pfId); //�ײ�
//		TbMsInfo msInfo = msmanager.getTBMsinfoby_msid(msId);//�ն�
//		Double deMoney = Double.parseDouble("-" + pfInfo.getPfHow());
//		Boolean deduct = AddAndDeductMoney(roleId,agentid,epId,deMoney);//�۳���ֵ�˵����
//		if(deduct){
//			String print ="�۳�ִ�гɹ���";
//			String resultmessage="";
//			showMessage(request, print, resultmessage);
//			
////			Boolean a =  addLog(deMoney,msInfo.getMsId(),user);//���log���ݵ����ݿ�
////			if(a){
////				System.out.println("�۳���¼����ɹ�");
////			}else{
////				System.out.println("�۳���¼����ʧ��");
////			}
//			
//			//��ֵ�ն�
//			msInfo.setPf(pfInfo);//�޸��ն��ײ���Ϣ
//			
//			Calendar c = Calendar.getInstance();//���һ��������ʵ��     
//			SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//			//���ڱȽϣ�����ʱ��<��ֵʱ�䣬�����ڳ�ֵ��ʱ������ϼ����ײ�ʱ����������ʱ������ϼ����ײ�ʱ��
//			if(msInfo.getMsMoneyTime().before(new Date())){//����ʱ���ڳ�ֵʱ��֮ǰ
//				c.setTime(new Date());//��������ʱ��   
//				c.add(Calendar.MONTH,pfInfo.getPfTime());//���������·���������
//			}else{
//				c.setTime(msInfo.getMsMoneyTime());//��������ʱ��   
//				c.add(Calendar.MONTH,pfInfo.getPfTime());//���������·���������
//			}
//		
//			msInfo.setMsMoneyTime(c.getTime());//�޸��ײ͵���ʱ��
//			msInfo.setIs_Arrearage(1);//�ʷ�״̬��Ϊ�з�
//			msInfo.setFlag(1);//����ʹ��״̬
//			List<TbMsInfo> msList = new ArrayList<TbMsInfo>();
//			msList.add(msInfo);
//			
//			Boolean upms = msmanager.updateMs(msList);
//			if(upms){
//				String print1 ="�ն��ײͳ�ִֵ�гɹ���";
//				String resultmessage1="�ɹ���ֵ�ն��ײͣ�"+pfInfo.getPfType() +"������"+deMoney+"Ԫ";
//				showMessage(request, print1, resultmessage1);
//				
//				Boolean b =  addLog(Double.parseDouble(pfInfo.getPfHow()),msInfo.getMsId(),user);//���log���ݵ����ݿ�
//				if(b){
//					System.out.println("�ն��ײͳ�ֵ��¼����ɹ�");
//				}else{
//					System.out.println("�ն��ײͳ�ֵ��¼����ʧ��");
//				}
//			}
//		}
//		
//		return ms_money_listinfojsp(mapping, form, request, response,
//				roleId,agentid,epId);
		return null;
	}
	
	/**
	 * �ն˳�ֵҳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @param roleId
	 * @param string
	 * @param string2
	 * @return
	 */
	private ActionForward ms_money_infojsp(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response,int roleId, String agentid,
			String epId) {
		// TODO Auto-generated method stub
		      
		String mjsp = Money(roleId, agentid, epId);//���
		request.setAttribute("money",mjsp );//����jspҳ��
		
		String msid = request.getParameter("msid");//��ֵ���ն�id
//		String []msidlist = request.getParameterValues("list");//������ֵ�ն�id
		
		TbMsInfo msinfo =null;
//		List<TbMsInfo> msList = null;
//		if(msid != null){
			msinfo = msmanager.getTBMsinfoby_msid(msid);//�����ն���Ϣ
//		}else if(msidlist != null){
//			msList = msmanager.msInfoList(msidlist);
//		}
		List<TbPFInfo> pfList = pfManager.findAllPFInfo();
		request.setAttribute("pfList", pfList);
		request.setAttribute("msInfo", msinfo);
//		request.setAttribute("msListInfo", msList);
		
		return mapping.findForward("mscqjsp");
	}

	
	/**
	 * �ն�list jsp
	 */
	private ActionForward ms_money_listinfojsp(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response,int roleId, String agentid,
			String epId) {
		// TODO Auto-generated method stub
		String mjsp = Money(roleId, agentid, epId);//���
		request.setAttribute("money",mjsp );//����jspҳ��
		
		//��ȡms list
		
		
		String pagentid = ""; // һ��������id
        String childagentid = ""; // ����������id
        String cmd = request.getParameter("CMD");
        HttpSession session = request.getSession(true);
        String type = request.getParameter("type");
        
        String agent = request.getSession().getAttribute("agentId")+"";
        String ep = request.getParameter("ep");
        if (ep == null || ep.equals("null")) {
            ep = "-1";
        }
        
//        if(roleId == 2){//������
//			childagentid="-2";
//			pagentid="0";
////			List<TbEnterpriseInfo> eplist = epmanger.getEpinfo_byagentid(agentid);
//		}else if(roleId == 1 || roleId == 35){//����Ա�ͳ���������
//			pagentid = "-1";
//			childagentid="0";
//		}
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
            childagentid = "-2"; // ������ҵ
        }

        
        String user_type = request.getParameter("user_type");
        if (user_type == null || user_type.equals("null")) {
            user_type = "3";
        }
        String statue = request.getParameter("statue");
        if (statue == null || statue.equals("null")) {
            statue = "3";
        }
        String flag = request.getParameter("flag");
        if (flag == null || flag.equals("null")) {
            flag = "1";
        }

        String ms_id = request.getParameter("ms_id");
        if (ms_id == null || ms_id.equals("")) {
            ms_id = "";
        }
        String ms_name = request.getParameter("ms_name");
        if (ms_name == null || ms_name.equals("")) {
            ms_name = "";
        }
        String ismobile = request.getParameter("c03");
        if (ismobile == null || ismobile.equals("")) { // 2ȫ��,0,��ֹ�л�,1,�����л�
            ismobile = "2";
        }
        String arrearage = request.getParameter("c04");
        if (arrearage == null || arrearage.equals("")) { // 2ȫ��,0,����,1,�ֳֻ�
            arrearage = "2";
        }
        int msCount = 0;
        msCount = msmanager.getMs_sertch(user_type, statue, flag, ms_id.trim(),
                ms_name.trim(), pagentid, childagentid, ep, ismobile, arrearage);
        // ��ȡÿҳ������
        int pageSize = 12;
        // ��ȡ��ҳ��
        int pageCount = msmanager.getPageCount(msCount, pageSize);
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
        request.setAttribute("msCount", msCount);
        // ����ÿҳ����ʾ����
        request.setAttribute("pageSize", pageSize);
        // ���ص�ǰҳ
        request.setAttribute("currentPage", pageNo);
        // ���÷��ص�������
        request.setAttribute("CMD", cmd);
        // ���÷����û�����

        // �����÷����û���ѯ��select��optionֵ
        request.setAttribute("mstype", user_type);
        request.setAttribute("msstatue", statue);
        request.setAttribute("msflag", flag);
        request.setAttribute("c03", ismobile);
        request.setAttribute("c04", arrearage);
        request.setAttribute("ms_id", ms_id);
        request.setAttribute("ms_name", ms_name);
        request.setAttribute("pagentid", pagentid);
        request.setAttribute("childagentid", childagentid);
        request.setAttribute("ep", ep);
//        if(!pagentid.equals("-1")){
//			// һ������������
//			request.setAttribute("Pagentlist", agentmanger.getParentAgentNotIncludeZB());
//		}else{
//			// һ������������
//			request.setAttribute("Pagentlist", agentmanger.getParentAgent(Integer.parseInt(agentid),roleId));
//		}
//        // һ��ָ�����������̽����
//        request.setAttribute("Cagentlist", agentmanger
//                .getChildAgentByParamentid(pagentid));
        // ����������ҵĬ��
//        List<TbEnterpriseInfo> list = null;
//		if (roleId == 3){
//			list = epmanger.getEpinfo_byeid(pagentid,
//					childagentid, Integer.parseInt(epId), roleId);
//		}else{
//			list = epmanger.getEpinfo_byagentid(pagentid,
//					childagentid,Integer.parseInt(agentid), roleId);
//		}
//		request.setAttribute("epList", list);
//        List<TbMsInfo> msList = msmanager.getTBMsinfoby_mspage(pageNo,
//                pageSize, user_type, statue, flag, ms_id.trim(),
//                ms_name.trim(), pagentid, childagentid, ep, ismobile, arrearage,roleId, agentid, epId);
        List<TbMsInfo> msList = msmanager.getTBMsinfoby_mspage(pageNo,
              pageSize, user_type, statue, flag, ms_id.trim(),
              ms_name.trim(), pagentid, childagentid, ep, ismobile, arrearage);

        // ���ص���ҳ��
        request.setAttribute("pageCount", pageCount);
        request.setAttribute("msList", msList);
       
        return mapping.findForward("mscqlistinfojsp");

	}



/**
 * ����ҵ��ֵ
 */
private ActionForward money_infocz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			int roleId, String agentid,
			String epId,TbUserInfo user) {
		// TODO Auto-generated method stub
		//�ж�����Ƿ��㹻
		Double checkMoney=0.00;
		if(roleId == 3 || roleId == 4){//��ҵ��ɫ
			TbEnterpriseInfo ep = epmanger.getEpinfo_byepid(epId);
			checkMoney = Double.valueOf(ep.getEp_Money());
		}else{//�����̽�ɫ
			if(roleId == 1 || roleId == 35){//����Ա����������Ա
				agentid = "-1";//�ܲ�
			}
			TbAgentInfo a = agentmanger.getAgent_ByAgentID(agentid);
			checkMoney = Double.valueOf(a.getAgent_Money());
		}
		if(checkMoney<0 || checkMoney == 0){
			String print ="���㣡";
			String resultmessage="���㣬�޷�������";
			showMessage(request, print, resultmessage);
			return  ep_or_angent_money_czjsp(mapping, form, request, response,
					roleId,agentid,epId);
		}
	
	
		String eid = request.getParameter("epid");//��ֵ����ҵid	
		Double newMoney = Double.parseDouble( request.getParameter("moneyhow"));//ҳ��������
		//��Ǯ
		Double deMoney =Double.parseDouble("-" + newMoney);
		Boolean deduct = AddAndDeductMoney(roleId,agentid,epId,deMoney);//�۳���ֵ�˵����
		if(deduct){
			String print ="�۳�ִ�гɹ���";
			String resultmessage="";
			showMessage(request, print, resultmessage);
			String nn="";
			nn = ((TbEnterpriseInfo)epmanger.getEpinfo_byepid(eid)).getEp_Name();

//			Boolean a =  addLog(deMoney,nn,user);//���log���ݵ����ݿ�
//
//			if(a){
//				System.out.println("�۳���¼����ɹ�");
//			}else{
//				System.out.println("�۳���¼����ʧ��");
//			}
			
			//��ֵ
			Double money =Double.parseDouble(Money(eid));//��ҵ���
			Boolean update =AddMoney(eid, newMoney);//���³�ֵ�Ľ������
			if(update){//��ӳ�ֵ��¼log
				print ="��ִֵ�гɹ���";
				resultmessage="�ɹ�Ϊ"+nn+"��ֵ" + newMoney +"Ԫ";
				showMessage(request, print, resultmessage);
				
				String name = null;
	
				name = ((TbEnterpriseInfo)epmanger.getEpinfo_byepid(eid)).getEp_Name();
	
				Boolean b =  addLog(newMoney,name,user,0, null,null,null);//���log���ݵ����ݿ�
	
				if(b){
					System.out.println("��ֵ��¼����ɹ�");
				}else{
					System.out.println("��ֵ��¼����ʧ��");
				}
			}
		}
		return money_listinfojsp(mapping, form, request, response,
				roleId,agentid,epId);
	}
	
/**
 * ��ת������ҵ��ֵҳ��
 */
private ActionForward money_infoczjsp(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response, int roleId, String agentid,
			String epId) {
		String mjsp = Money(roleId, agentid, epId);//���
		request.setAttribute("money",mjsp );//����jspҳ��
		String eId =request.getParameter("mepid");//��ȡҳ�洫��Ҫ��ֵ����ҵid
		request.setAttribute("epInfo",epmanger.getEpinfo_byepid(eId));//������ҵ��Ϣ��ҳ��
		
		return mapping.findForward("cqinfojsp");//�������ҵ��ֵҳ��;
	}

/**
 * ��ҵ�����б���Ϣ�鿴
 */
private ActionForward money_listinfojsp(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response, int roleId, String agentid,
			String epId) {
			String mjsp = Money(roleId, agentid, epId);//���
			request.setAttribute("money",mjsp );//����jspҳ��
			
			//��ȡ��¼�û����µ���ҵ

				String pagentid = ""; // һ��������id
				String childagentid = ""; // ����������id
//				String type=request.getParameter("type");

				String cmd = request.getParameter("CMD");
				String epname = request.getParameter("epname");
				if (epname == null || epname.equals("")) {
					epname = "";
				}
				
				if(roleId == 2){//������
					childagentid="-2";
					pagentid="0";
//					List<TbEnterpriseInfo> eplist = epmanger.getEpinfo_byagentid(agentid);
				}else if(roleId == 1 || roleId == 35){//����Ա�ͳ���������
					pagentid = "-1";
					childagentid="0";
				}else{
					pagentid = "0";
					childagentid="0";
				}
				int epCount = 0;
				epCount = epmanger.getEp_sertch(pagentid, childagentid, epname.trim(), Integer.parseInt(epId), roleId);
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
//
				// ���ص���ҳ��
				request.setAttribute("pageCount", pageCount);

				request.setAttribute("epname", epname);
				// �����û���λ��Ϣ
				List<TbEnterpriseInfo> list = null;
				if (roleId == 3){
					list = epmanger.getEpinfoby_mspage(pageNo,
							pageSize, pagentid, childagentid, epname.trim(), Integer.parseInt(epId), roleId);
				}else{
					list = epmanger.getEpinfoby_mspage(pageNo,
							pageSize, pagentid, childagentid, epname.trim(), Integer.parseInt(agentid), roleId);
				}
				request.setAttribute("epList",list );

			return mapping.findForward("cqlistinfojsp");//����listҳ��
	}
/**
 * ��¼���û����Լ���ֵ����
 * @param mapping
 * @param form
 * @param request
 * @param response
 * @param roleId
 * @param agentid
 * @param epId
 * @param uId
 * @param uName
 * @return
 */
private ActionForward epangent_money_cz(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response, int roleId,String agentid,String epId,TbUserInfo user) {
		Double money =Double.parseDouble(Money( roleId, agentid, epId));//�û����
		Double newMoney = Double.parseDouble( request.getParameter("moneyhow"));//ҳ��������
		
		String name =user.getUserName()+"/�ʺų�ֵ";//������Ʒ����
		UtilDate date = new UtilDate();
		String get_order =Demo.getNonceStr();//������
		String payMethod = request.getParameter("moneytype");//֧����ʽ��1��֧����    2��΢�ţ�
		if(payMethod.equals("1")){//֧����֧��
	////////////////////////////////////�������//////////////////////////////////////
	
			//֧������
			String payment_type = "1";
			//��������޸�
			//�������첽֪ͨҳ��·�� http://203.88.213.243/TSCWEB/alipayjsp/notify_url.jsp
			String notify_url = FilePathConfig.getAliNotifyUrl();
			//��http://��ʽ������·�������ܼ�?id=123�����Զ������
	
			//ҳ����תͬ��֪ͨҳ��·��http://203.88.213.243/TSCWEB/alipayjsp/return_url.jsp
			String return_url = FilePathConfig.getServer_Ip()+"alipayjsp/return_url.jsp";
			//��http://��ʽ������·�������ܼ�?id=123�����Զ������������д��http://localhost/
			System.out.println(notify_url);

			String out_trade_no = null,subject = null,total_fee = null,body = null,show_url = null,anti_phishing_key = null,exter_invoke_ip = null;
			try {
				
				out_trade_no = get_order;
			
				//�̻���վ����ϵͳ��Ψһ�����ţ�����
		
				//��������
				subject = name;//new String(name.getBytes("ISO-8859-1"),"UTF-8");
				//����
		
				//������
				total_fee = new String(newMoney.toString().getBytes("ISO-8859-1"),"UTF-8");
				//����
		
				//��������
		
				 body = null;//new String(request.getParameter("WIDbody").getBytes("ISO-8859-1"),"UTF-8");
				//��Ʒչʾ��ַ
				 show_url = null;//new String(request.getParameter("WIDshow_url").getBytes("ISO-8859-1"),"UTF-8");
				//����http://��ͷ������·�������磺http://www.�̻���ַ.com/myorder.html
		
				//������ʱ���
				 anti_phishing_key = "";
				//��Ҫʹ����������ļ�submit�е�query_timestamp����
		
				//�ͻ��˵�IP��ַ
				 exter_invoke_ip = "";
				//�Ǿ�����������IP��ַ���磺221.0.0.1
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//////////////////////////////////////////////////////////////////////////////////
			
			//������������������
			Map<String, String> sParaTemp = new HashMap<String, String>();
			sParaTemp.put("service", "create_direct_pay_by_user");
	        sParaTemp.put("partner", AlipayConfig.partner);
	        sParaTemp.put("seller_email", AlipayConfig.seller_email);
	        sParaTemp.put("_input_charset", AlipayConfig.input_charset);
			sParaTemp.put("payment_type", payment_type);
			sParaTemp.put("notify_url", notify_url);
			sParaTemp.put("return_url", return_url);
			sParaTemp.put("out_trade_no", out_trade_no);
			sParaTemp.put("subject", subject);
			sParaTemp.put("total_fee", total_fee);
			sParaTemp.put("body", body);
			sParaTemp.put("show_url", show_url);
			sParaTemp.put("anti_phishing_key", anti_phishing_key);
			sParaTemp.put("exter_invoke_ip", exter_invoke_ip);
			
			//��������
			String sHtmlText = AlipaySubmit.buildRequest(sParaTemp,"get","ȷ��");
			System.out.println(sHtmlText);
			request.setAttribute("sHtmlText", sHtmlText);
		}else if(payMethod.equals("2")){//΢��֧��
			//ɨ��֧��
			//notifyurl����Ϊ80�˿ڲ��ҵ�ַ���ܴ�����
			String notifyurl = FilePathConfig.getServer_Ip()+"wechat/notify_url.jsp";
		    WxPayDto tpWxPay1 = new WxPayDto();
		    tpWxPay1.setBody(name);
		    tpWxPay1.setOrderId(get_order);
		    tpWxPay1.setSpbillCreateIp(request.getRemoteAddr());//�ͻ���IP
		    tpWxPay1.setTotalFee(newMoney+"");
		    String codeurl = Demo.getCodeurl(tpWxPay1,notifyurl);
		    request.setAttribute("codeurl", codeurl);
		    request.setAttribute("paymoney", newMoney);

		    return mapping.findForward("wechatjsp");
		}
//		Boolean update =AddAndDeductMoney( roleId, agentid, epId, newMoney);//��������
//		if(update){//��ӳ�ֵ��¼log
//			
//			String print ="��ִֵ�гɹ���";
//			String resultmessage="�ɹ���ֵ" + newMoney +"Ԫ";
//			showMessage(request, print, resultmessage);
//			
//			String name = null;
//
//			if(roleId == 3 || roleId == 4 ){
//				name = ((TbEnterpriseInfo)epmanger.getEpinfo_byepid(epId)).getEp_Name();
//			}else{
//				name = user.getUserName();
//			}
//			Boolean b =  addLog(newMoney,name,user);//���log���ݵ����ݿ�
//
//			if(b){
//				System.out.println("��ֵ��¼����ɹ�");
//			}else{
//				System.out.println("��ֵ��¼����ʧ��");
//			}
//		}
		return money_listinfojsp(mapping,
				form, request,
				response, roleId, agentid,
				epId);
	}

	/**
	 * ������,��ҵ�û���¼��ֵҳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @param roleId
	 * @return
	 */
	private ActionForward ep_or_angent_money_czjsp(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response, int roleId,String agentid,String epId) {
		// TODO Auto-generated method stub
		String money =Money( roleId, agentid, epId);//�û����
		
		request.setAttribute("money",money );//����jspҳ��
		return mapping.findForward("cqjsp");
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
	 * ��ҵ���
	 * @param epId
	 * @return
	 */
	private String Money(String epId){
		String m =null;
		m = ((TbEnterpriseInfo)epmanger.getEpinfo_byepid(epId)).getEp_Money();
		if(m == null || m == ""){
			m="0.00";
		}
		return m;
	}
	
	/**
	 * ��ֵ��۳������ݿ�
	 * @param roleId
	 * @param agentid
	 * @param epId
	 * @param money
	 * @return
	 */
	private  Boolean AddAndDeductMoney(int roleId,String agentid,String epId ,Double money){
//		TbEnterpriseInfo epInfo =new TbEnterpriseInfo();
//		TbAgentInfo aInfo = new TbAgentInfo();
		double m = 0;
		Boolean updateMoney = false;

		if(roleId == 4 ){//ֱ����ҵ��ɫ
			TbEnterpriseInfo ep = epmanger.getEpinfo_byepid(epId);
			if(ep.getEp_Money()==null || ep.getEp_Money() ==""){
				ep.setEp_Money("0");
			}
			double newm = Double.parseDouble(ep.getEp_Money());
			m= money + newm;
			ep.setEp_Money(Double.toString(m));
			updateMoney = epmanger.updateEp(ep);
		}else{//�����̽�ɫ
			if(roleId == 1 || roleId == 35){//����Ա����������Ա
				agentid = "-1";//�ܲ�
			}
			TbAgentInfo a = agentmanger.getAgent_ByAgentID(agentid);
			double newm = Double.parseDouble(a.getAgent_Money());
			 m= money + newm ;
			 a.setAgent_Money(Double.toString(m));
			 updateMoney= agentmanger.updateAgent(a);//���½��
		}
		return updateMoney;
	}
	/**
	 * ����ҵ��ֵ
	 * @param epId
	 * @param money
	 * @return
	 */
	private Boolean AddMoney(String epId ,Double money){
//		TbEnterpriseInfo epInfo =new TbEnterpriseInfo();
//		TbAgentInfo aInfo = new TbAgentInfo();
		double m = 0;
		Boolean updateMoney = false;

			TbEnterpriseInfo ep = epmanger.getEpinfo_byepid(epId);
			String mm = ep.getEp_Money();
			if(mm == null || mm == ""){
				mm="0.00";
			}
			double newm = Double.parseDouble(mm);
			m= newm + money;
			ep.setEp_Money(Double.toString(m));
			updateMoney = epmanger.updateEp(ep);
		return updateMoney;
	}
	/**
	 * �������̳�ֵ
	 * @param epId
	 * @param money
	 * @return
	 */
	private Boolean AddMoneyA(String aId ,Double money){
//		TbEnterpriseInfo epInfo =new TbEnterpriseInfo();
//		TbAgentInfo aInfo = new TbAgentInfo();
		double m = 0;
		Boolean updateMoney = false;

			TbAgentInfo a = agentmanger.getAgent_ByAgentID(aId);
			String mm = a.getAgent_Money();
			if(mm == null || mm == ""){
				mm="0.00";
			}
			double newm = Double.parseDouble(mm);
			m= newm + money;
			a.setAgent_Money(Double.toString(m));
			updateMoney = agentmanger.updateAgent(a);
		return updateMoney;
	}
	
	/**
	 * ��ֵ��Ӽ�¼
	 * @param m
	 * @param name
	 * @param uId
	 * @return
	 */
	private  Boolean addLog(Double m,String name,TbUserInfo user,int payType,String payNum,String tNo,String mtNo){
		TbMoneyLog ml=new TbMoneyLog();	
		ml.setHow(Double.toString(m));
		ml.setMlogTime(new Date());
		ml.setMoneyToUser(name);
		ml.setUser(user);
		
		ml.setPayType(payType);//��ֵ����(֧������1��΢�ţ�2��������ҵ��3���ն��ײ�ת�ƣ�4������ֵ��0)
		ml.setPayNum(payNum);//��ֵ���루֧������΢�źţ��ն�id����ҵid��
		ml.setTeadeNo(tNo);//֧�����׺ţ�֧������΢�ţ�
		ml.setMoney_Trade_No(mtNo);//������
		
		return moneyManager.addMoneyLog(ml);
	}
	
	/**
	 * ���ز�����Ϣ
	 * log
	 */
	private  void showMessage(HttpServletRequest request,String print,String resultmessage){
		System.out.println(print);
		request.setAttribute("message", resultmessage);
	}
	
	/**
	 * ֧����,΢�ų�ֵ�ɹ���ִ�����ݿ��¼
	 */
	public Boolean  zhiFuBaoMoney(int roleId,TbUserInfo user,Double newMoney,int payType,String payNum,String tNo,String mtNo){

		Boolean update =AddAndDeductMoney( roleId, user.getAgent_Id().toString(), 
				user.getEp_Id().toString(), newMoney);//��������
		if(update){//��ӳ�ֵ��¼log
			
			String print ="��ִֵ�гɹ���";
			
			String name = null;

			if(roleId == 3 || roleId == 4 ){
				name = ((TbEnterpriseInfo)epmanger.getEpinfo_byepid(user.getEp_Id().toString())).getEp_Name();
			}else{
				name = user.getUserName();
			}
			Boolean b =  addLog(newMoney,name,user, payType, payNum, tNo, mtNo);//���log���ݵ����ݿ�

			if(b){
				System.out.println("��ֵ��¼����ɹ�");
			}else{
				System.out.println("��ֵ��¼����ʧ��");
				return false;
			}
			return true;
		}
		return false;
	}
}
