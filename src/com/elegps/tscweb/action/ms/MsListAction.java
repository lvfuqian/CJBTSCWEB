package com.elegps.tscweb.action.ms;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.elegps.tscweb.action.base.BaseAction;
import com.elegps.tscweb.comm.HRAddress;
import com.elegps.tscweb.comm.UpLoadFileUtil;
import com.elegps.tscweb.comm.UserInfo;
import com.elegps.tscweb.exception.MessageException;
import com.elegps.tscweb.form.AddmsForm;
import com.elegps.tscweb.model.TbEnterpriseInfo;
import com.elegps.tscweb.model.TbMsInfo;
import com.elegps.tscweb.model.TbMsInfoExt;
import com.elegps.tscweb.model.TbPFInfo;
import com.elegps.tscweb.model.TbUserInfo;
import com.elegps.tscweb.model.TbUserLog;
/**
 * Struts Creation date: 10-11-2008
 * <p/>
 * XDoclet definition:
 *
 * @struts.action validate="true"
 */
public class MsListAction extends BaseAction {
    /*
	 * Generated Methods
	 */
	
	//wanglei ��ҵ�û�ִ��
	public static int a_id = 0;//������id
	public static int r_id = 0;//��ɫid
	public static int ep_id = 0;//��ҵid
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
        TbUserInfo user = UserInfo.getUserInfo(request);
        if (user != null) {
			
			//wanglei 
			a_id = Integer.parseInt(request.getSession().getAttribute("agentId")+"");//������id
			r_id = Integer.parseInt(request.getSession().getAttribute("roleId")+"");//��ɫid
			ep_id =Integer.parseInt(request.getSession().getAttribute("epId")+"");//��ɫid
            // ���û�оͽ��յ�����Ĭ����ʾȫ������
            if (request.getParameter("CMD") == null
                    || request.getParameter("CMD").equals("")) {
                cmd = "ms_search";
            } else {
                cmd = request.getParameter("CMD");
            }
            if (cmd.equals("add")) {// �����û���Ϣ
                actionforward = ms_add(mapping, form, request, response);
            } else if (cmd.equals("xinaxi")) // �鿴�û���ϸ��Ϣ
            {
                actionforward = ms_xianxi(mapping, form, request, response);
            } else if (cmd.equals("ms_type_list")) { // �����ն��û����Ͳ�ѯ�ն��û���Ϣ
                actionforward = ms_typesearch(mapping, form, request, response);
            } else if (cmd.equals("ms_Online_Status")) { // �����ն��û�����״̬��ѯ�ն��û���Ϣ(1���ߡ�0���ߣ�
                actionforward = ms_Online_Statussearch(mapping, form, request,response);
            } else if (cmd.equals("delete")) { // ɾ��ѡ���ն��û���Ϣ(�߼�ɾ��flag��Ϊ0)
                actionforward = ms_delete(mapping, form, request, response);
            } else if (cmd.equals("ms_idsearch")) { // �û��ն˺�ģ����ѯ
                actionforward = ms_idSarch(mapping, form, request, response);
            } else if (cmd.equals("ms_flag")) { // �û�״̬��ѯ��0ʧЧ��1������
                actionforward = ms_flagSarch(mapping, form, request, response);
            } else if (cmd.equals("tomsmdoyijsp")) { // ��Ҫ�޸ĵ���Ϣ�����޸ĵ�jspҳ��
                actionforward = ms_modytojsp(mapping, form, request, response);
            } else if (cmd.equals("msmodify")) { // �޸��ն��û���Ϣ
                actionforward = ms_modify(mapping, form, request, response);
            } else if (cmd.equals("ms_search")) { // ��������ͬʱ��ѯ
                actionforward = ms_Sarch(mapping, form, request, response);
            } else if (cmd.equals("msdj")) { // ����
                actionforward = ms_dj(mapping, form, request, response);
            } else if (cmd.equals("msaddjsp")) { // ת��jsp���ӵ����ն�ҳ��
                actionforward = ms_addjsp(mapping, form, request, response);
            } else if (cmd.equals("mspladdjsp")) { // ת��jsp���Ӷ���ն�ҳ��
                actionforward = ms_pladdjsp(mapping, form, request, response);
            } else if (cmd.equals("mstoext")) {// ��ת���޸Ĺ�ϵ��jspҳ��
                actionforward = getMsExtAction(mapping, form, request, response);
            } else if (cmd.equals("msextudpate")) {// ��ת���޸Ĺ�ϵ��jspҳ��
                actionforward = msExtUdpate(mapping, form, request, response);
            }else if (cmd.equals("fpmsjsp")) {// ��ת�������ն˵�jspҳ��
                actionforward = fpmsjsp(mapping, form, request, response);
            }else if (cmd.equals("fpms")) {// ִ�з��书��
                actionforward = fpms(mapping, form, request, response);
            }else if (cmd.equals("pbjsp")) {// ���ֻ���jspҳ��
                actionforward = pbjsp(mapping, form, request, response);
            }else if (cmd.equals("phonebind")) {// ���ֻ����ύ
                actionforward = phonebind(mapping, form, request, response);
            }else if (cmd.equals("excelms")) {// ����excelms�����ն���Ϣҳ��
                actionforward = excelmsjsp(mapping, form, request, response);
            }
            
        } else {
            request.setAttribute("message", "Session���ڣ������µ�¼");
            actionforward = mapping.findForward("logging");
        }
        if (actionforward == null) {
            actionforward = mapping.findForward("succes");
        }
        return actionforward;
    }

	private ActionForward excelmsjsp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		 String pagentid = ""; // һ��������id
	        String childagentid = ""; // ����������id

	        if (request.getParameter("pagentid") != null) {
	            pagentid = request.getParameter("pagentid");
	        } else {
	            pagentid = "-1"; // ���������ܲ�
	        }

	        if (request.getParameter("childagentid") != null) {
	            childagentid = request.getParameter("childagentid");
	        } else {
	            childagentid = "-1"; // ֱ����ҵ
	        }

	        String ep = request.getParameter("ep");
	        if (ep == null || ep.equals("null")) {
	        	if(r_id == 4){
	        		ep = ep_id+"";
	        	}else{
	        		ep = "-1";
	        	}
	        }
	        // һ������������
	        request.setAttribute("Pagentlist", agentmanger.getParentAgent(a_id,r_id));
	        // һ��ָ�����������̽����
	        request.setAttribute("Cagentlist", agentmanger
	                .getChildAgentByParamentid(pagentid));
	        // ����������ҵĬ��
	        List<TbEnterpriseInfo> list = null;
			if (r_id == 3 || r_id == 4){
				list = epmanger.getEpinfo_byeid(pagentid,
						childagentid, ep_id, r_id);
			}else{
				list = epmanger.getEpinfo_byagentid(pagentid,
						childagentid, a_id, r_id);
			}
			request.setAttribute("epList", list);
	        request.setAttribute("pagentid", pagentid);
	        request.setAttribute("childagentid", childagentid);
	        request.setAttribute("epid", ep);


	        return mapping.findForward("excelms");
	}

	private ActionForward phonebind(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String msid = request.getParameter("msid");
		String phone = request.getParameter("phone");
		if(msid.equals("")||msid.equals(null)){
    		return null;
    	}
		if(phone.equals("")||phone.equals(null)){
    		return null;
    	}
		TbMsInfo msinfo = msmanager.getTBMsinfoby_msid(msid);
    	if(msinfo == null){
    		return null;
    	}
    	msinfo.setPhoneLogin(phone);
    	if(msmanager.update(msinfo)){
    		request.setAttribute("message", "�ֻ�����󶨳ɹ���");
    		return mapping.findForward("showmslist");
    	}
    	request.setAttribute("msid", msinfo.getMsId());
    	request.setAttribute("msname", msinfo.getMsName());
    	request.setAttribute("message", "ʧ�ܣ�");
    	return mapping.findForward("pbjsp");
	}

	private ActionForward pbjsp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
    	String msid = request.getParameter("msid");
    	if(msid.equals("")||msid.equals(null)){
    		return null;
    	}
    	TbMsInfo msinfo = msmanager.getTBMsinfoby_msid(msid);
    	if(msinfo == null){
    		return null;
    	}
    	request.setAttribute("msid", msinfo.getMsId());
    	request.setAttribute("msname", msinfo.getMsName());
		return mapping.findForward("pbjsp");
	}

	/**
     * ִ�з����ն�
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    private ActionForward fpms(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
    	String fpms[] = request.getParameterValues("selectmsid");//�õ�Ҫ������ն�id
    	if(fpms.length < 1){
    		System.out.println("û��ѡ��Ҫ������ն�");
    		request.setAttribute("message", "û��ѡ��Ҫ������նˣ�");
    		mapping.findForward("succes");
    	}
    	String aId = request.getParameter("pagentid");
    	String cId = request.getParameter("childagentid");
    	String epId = request.getParameter("ep");//������ҵ��id
    	String grpId = request.getParameter("grp_id");//����Ⱥ��ID
    	if(cId.equals("-1")){
    		if(epId.equals("")||epId.equals(null)){
    			epId = "0";
//        		System.out.println("û��ѡ��Ҫ���䵽����ҵ");
//        		request.setAttribute("message", "û��ѡ��Ҫ���䵽����ҵ��");
//        		mapping.findForward("succes");
        	}
    	}else{
    		aId = cId;
    		if(epId.equals("")||epId.equals(null)){
    			epId = "0";
    		}
    	}
    	
    	List<TbMsInfo> list = new ArrayList<TbMsInfo>();
    	for(int i = 0;i<fpms.length; i++){
    		TbMsInfo msInfo = new TbMsInfo();
    		msInfo = msmanager.getTBMsinfoby_msid(fpms[i]);
    		if(msInfo != null){
    			msInfo.setAgentId(Integer.parseInt(aId));
    			msInfo.setEpid(Integer.parseInt(epId));
    			list.add(msInfo);
    		}
    	}
    	Boolean b = msmanager.updateMs(list);//����
    	if(!b){
    		System.out.println("����ʧ�ܣ�");
    		request.setAttribute("message", "����ʧ�ܣ�");
    		mapping.findForward("succes");
    	}
		String resultmessage = "";
    	if(!grpId.equals("")&&!grpId.equals(null)){//��ӵ�Ⱥ��
    		for(int i = 0;i<fpms.length; i++){
	    		String insert = null;
	    		insert = grpmsmanager.createGrpMsInfo(grpId, fpms[i]);
	    		if (insert != null) {
	    			System.out.println("Ⱥ���ն˶�Ӧ��ϵ��ӳɹ�");
	    			resultmessage = "���䵽Ⱥ��ɹ�!";
	    			//��¼������־
	    			TbUserLog userLog=new TbUserLog();
	    			userLog.setUserId(Integer.parseInt(request.getSession().getAttribute("uId").toString()));
	    			userLog.setlDate(new Date());
	    			userLog.setlAddress(HRAddress.getIpAddr(request));
	    			userLog.setlType(7);
	    			userLog.setlContent("Ⱥ��:{ "+grpId+" }�ն�:{ "+fpms[i]+" }��Ӷ�Ӧ��ϵ");
	    			logManager.save(userLog);
	    		} else {
	    			System.out.println("Ⱥ���ն˶�Ӧ��ϵ���ʧ��");
	    			resultmessage = "���䵽Ⱥ��ʧ��!";
	    		}
    		}
    	}
    	if(b){
    		System.out.println("�ն˷���ɹ�");
            request.setAttribute("message", "�ն˷���ɹ���"+resultmessage);
    	}else{
    		System.out.println("�ն˷���ʧ��");
            request.setAttribute("message", "�ն˷���ʧ�ܣ�"+resultmessage);
    	}
		return mapping.findForward("showmslist");
	}
    /**
     * ��ת���ն˷���ҳ��
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
	private ActionForward fpmsjsp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
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
			if(r_id == 4){
				ep = ep_id+"";
			}else{
				ep = "-1";   //ȫ����ҵ}
			}
		}
		
//		if(!pagentid.equals("-1")){
//			// һ������������
//			request.setAttribute("Pagentlist", agentmanger.getParentAgentNotIncludeZB());
//		}else{
			// һ������������
		int apid = Integer.parseInt(request.getSession().getAttribute("aPId")+"");
		if(apid == 0){
			request.setAttribute("Pagentlist", agentmanger.getParentAgent(a_id,r_id));
		//}
		// һ��ָ�����������̽����
			request.setAttribute("Cagentlist", agentmanger.getChildAgentByParamentid(pagentid));
		}else{
			int a = agentmanger.getAgent_ByAgentID(apid+"").getAgent_Id();
			request.setAttribute("Pagentlist", agentmanger.getParentAgent(a,r_id));
			request.setAttribute("Cagentlist", agentmanger.getChildAgentByAId(agent));
		}
//			request.setAttribute("Pagentlist", agentmanger.getParentAgent(a_id,r_id));
////		}
//		
//		// һ��ָ�����������̽����
//		request.setAttribute("Cagentlist", agentmanger
//				.getChildAgentByParamentid(pagentid));
		// ����������ҵĬ��
		List<TbEnterpriseInfo> list = null;
		if (r_id == 3 || r_id == 4){
			list = epmanger.getEpinfo_byeid(pagentid,
					childagentid, ep_id, r_id);
		}else{
			list = epmanger.getEpinfo_byagentid(pagentid,
					childagentid, a_id, r_id);
		}
		request.setAttribute("epList", list);
        if(ep!="-1"){
//        	request.setAttribute("grpList", grpmanager.getAllGrp_Info(pagentid,childagentid,ep));
        }
		request.setAttribute("pagentid", pagentid);
		request.setAttribute("childagentid", childagentid);
		return mapping.findForward("fpmsjsp");
	}

	/**
     * jsp���ӵ����ն�ҳ����Ϣ
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws MessageException
     */
    public ActionForward ms_addjsp(ActionMapping mapping, ActionForm form,
                                   HttpServletRequest request, HttpServletResponse response)
            throws MessageException {
        String pagentid = ""; // һ��������id
        String childagentid = ""; // ����������id

        if (request.getParameter("pagentid") != null) {
            pagentid = request.getParameter("pagentid");
        } else {
            pagentid = "-1"; // ���������ܲ�
        }

        if (request.getParameter("childagentid") != null) {
            childagentid = request.getParameter("childagentid");
        } else {
            childagentid = "-1"; // ֱ����ҵ
        }

        String ep = request.getParameter("ep");
        if (ep == null || ep.equals("null")) {
        	if(r_id == 4){
        		ep = ep_id+"";
        	}else{
        		ep = "-1";
        	}
        }
        // һ������������
        request.setAttribute("Pagentlist", agentmanger.getParentAgent(a_id,r_id));
        // һ��ָ�����������̽����
        request.setAttribute("Cagentlist", agentmanger
                .getChildAgentByParamentid(pagentid));
        // ����������ҵĬ��
        List<TbEnterpriseInfo> list = null;
		if (r_id == 3 || r_id == 4){
			list = epmanger.getEpinfo_byeid(pagentid,
					childagentid, ep_id, r_id);
		}else{
			list = epmanger.getEpinfo_byagentid(pagentid,
					childagentid, a_id, r_id);
		}
		request.setAttribute("epList", list);
        request.setAttribute("pagentid", pagentid);
        request.setAttribute("childagentid", childagentid);
        request.setAttribute("epid", ep);
//        List eplist = epmanger.getEpinfo_byagentid(pagentid, childagentid, ep_id, r_id);
        // if(eplist!=null && eplist.size()>0){
        // TbEnterpriseInfo epinfo=(TbEnterpriseInfo)eplist.get(0);
        // request.setAttribute("epid",epinfo.getEp_Id());
        // }else{
        // request.setAttribute("epid","-1");
        // }

        return mapping.findForward("msaddjsp");

    }

    /**
     * jsp���Ӷ���ն�ҳ����Ϣ
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws MessageException
     */
    public ActionForward ms_pladdjsp(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
            throws MessageException {
        String pagentid = ""; // һ��������id
        String childagentid = ""; // ����������id

        if (request.getParameter("pagentid") != null) {
            pagentid = request.getParameter("pagentid");
        } else {
            pagentid = "-1"; // ���������ܲ�
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

        // һ������������
        request.setAttribute("Pagentlist", agentmanger.getParentAgent(a_id,r_id));
        // һ��ָ�����������̽����
        request.setAttribute("Cagentlist", agentmanger
                .getChildAgentByParamentid(pagentid));
        // ����������ҵĬ��
//        request.setAttribute("epList", epmanger.getEpinfo_byagentid(pagentid,
//                childagentid, ep_id, r_id));

        request.setAttribute("pagentid", pagentid);
        request.setAttribute("childagentid", childagentid);
        request.setAttribute("ep", ep);
//        List eplist = epmanger.getEpinfo_byagentid(pagentid, childagentid, ep_id, r_id);
        List<TbEnterpriseInfo> list = null;
		if (r_id == 3 ||r_id == 4){
			list = epmanger.getEpinfo_byeid(pagentid,
					childagentid, ep_id, r_id);
		}else{
			list = epmanger.getEpinfo_byagentid(pagentid,
					childagentid, a_id, r_id);
		}
		request.setAttribute("epList", list);
        // if(eplist!=null && eplist.size()>0){
        // TbEnterpriseInfo epinfo=(TbEnterpriseInfo)eplist.get(0);
        // request.setAttribute("epid",epinfo.getEp_Id());
        // }else{
        // request.setAttribute("epid","-1");
        // }
        return mapping.findForward("mspladdjsp");
    }

    /**
     * ���޸��û���Ϣ
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws MessageException
     */
    public ActionForward ms_modify(ActionMapping mapping, ActionForm form,
                                   HttpServletRequest request, HttpServletResponse response)
            throws MessageException {
        String ep_id = request.getParameter("ep");
        String ms_id = request.getParameter("ms_id");//ԭmsId
        String newmsId = request.getParameter("msId");//��msId
        String ms_name = request.getParameter("ms_name").trim();
        int ms_type = Integer.parseInt(request.getParameter("ms_type"));
        int ms_flag = Integer.parseInt(request.getParameter("ms_flag"));
        int delddms = Integer.parseInt(request.getParameter("delddms"));
        int ms_level = Integer.parseInt(request.getParameter("ms_level"));
        int packagefee = Integer.parseInt(request.getParameter("package_fee"));
        int ms_df = Integer.parseInt(request.getParameter("ms_df"));
        int ms_call = Integer.parseInt(request.getParameter("call"));
        int mileageas = 0;
        String ismobile = request.getParameter("c03");
        String c04 = request.getParameter("c04");
        String ms_modid = request.getParameter("ms_modid").trim();
        String ms_pas = request.getParameter("ms_password").trim();
        String memo = request.getParameter("memo");
        String msCategory = request.getParameter("ms_category");
        String netWorkType = request.getParameter("nwType");
//		String simNum=request.getParameter("simNum");
//		String deviceNum=request.getParameter("deviceNum"); 
//		int carPlateColor=Integer.parseInt(request.getParameter("carPlateColor"));
        String pagentid = ""; // һ��������id
        String childagentid = ""; // ����������id
        if (request.getParameter("pagentid") != null) {
            pagentid = request.getParameter("pagentid");
        } else {
            pagentid = "-1"; // ���������ܲ�
        }

        if (request.getParameter("childagentid") != null) {
            childagentid = request.getParameter("childagentid");
        } else {
            childagentid = "-1"; // ֱ����ҵ
        }
        //ִ�е�hql���
//		String hql_ms="UPDATE TbMsInfo SET msId=? WHERE msId=?";
//		String hql_grp="UPDATE TbGrpInfo SET msid=? WHERE msid=?";
//		String hql_dbms="UPDATE TbDdmsMsListInfo SET ms_id=? WHERE ms_id=?";
//		String hql_gpsms="UPDATE TbGpsMsListInfo SET l_ms_id=?,ms_id=? WHERE l_ms_id=?";
//		String hql_grpms="UPDATE TbGrpMsListInfo SET ms_id=? WHERE ms_id=?";
//		if(ms_id!=newmsId){
//			if(	msmanager.updateMsExt(ms_id, simNum, deviceNum,Integer.parseInt(ep_id), carPlateColor, newmsId)>0){
//				if(msmanager.getMsById("SELECT COUNT(*) FROM TbMsInfo WHERE msId=?", ms_id)>0){
//					msmanager.update(hql_ms, newmsId,ms_id);
//				}
//				if(msmanager.getMsById("SELECT COUNT(*) FROM TbGrpInfo WHERE msid=?", ms_id)>0){
//					msmanager.update(hql_grp, newmsId,ms_id);
//				}
//				if(msmanager.getMsById("SELECT COUNT(*) FROM TbDdmsMsListInfo WHERE ms_id=?", ms_id)>0){
//					msmanager.update(hql_dbms, newmsId,ms_id);
//				}
//				if(msmanager.getMsById("SELECT COUNT(*) FROM TbGpsMsListInfo WHERE l_ms_id=?", ms_id)>0){
//					msmanager.update(hql_gpsms, newmsId,newmsId.substring(9, 21),ms_id);
//				}
//				if(msmanager.getMsById("SELECT COUNT(*) FROM TbGrpMsListInfo WHERE ms_id=?", ms_id)>0){
//					msmanager.update(hql_grpms, newmsId,ms_id);
//				}
//			}
//			ms_id=newmsId;
//		}
        TbPFInfo pfinfo = pfManager.findPFInfoById(packagefee+"");
        String modifyok = msmanager.modifyMs(ms_id, ep_id, ms_name, ms_type,
                ms_flag, delddms, ms_modid, ms_pas, ms_level, pfinfo,
                ms_df, ms_call, mileageas, memo, ismobile, c04,msCategory,Integer.parseInt(pagentid),netWorkType);
        //��¼������־
        TbUserLog userLog = new TbUserLog();
        userLog.setUserId(Integer.parseInt(request.getSession().getAttribute("uId").toString()));
        userLog.setlDate(new Date());
        userLog.setlAddress(HRAddress.getIpAddr(request));
        userLog.setlType(2);
        userLog.setlContent("�޸��ն�[" + ms_id + "]");
        logManager.save(userLog);
        String resultmessage = null;
        if (modifyok != null) {
            resultmessage = "�ն���Ϣ�޸ĳɹ���";
            request.setAttribute("message", resultmessage);
        } else {
            resultmessage = "�ն���Ϣ�޸�ʧ�ܣ�";
            request.setAttribute("message", resultmessage);
        }
        request.setAttribute("pagentid", pagentid);
        request.setAttribute("childagentid", childagentid);
        request.setAttribute("ep", ep_id);
        String type = request.getParameter("type");
        if (type != null && !type.equals("null")) {
            return mapping.findForward("kfshowmslist");
        } else {
            return mapping.findForward("showmslist");
        }

    }

    /**
     * ���޸��û���Ϣ��jspҳ��
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws MessageException
     */
    public ActionForward ms_modytojsp(ActionMapping mapping, ActionForm form,
                                      HttpServletRequest request, HttpServletResponse response)
            throws MessageException {
        // ȡ��msid
        String msid = request.getParameter("msid");
        TbEnterpriseInfo epinfo = null;
        String type = request.getParameter("type");
        if (type != null) {
            request.setAttribute("type", type);
        }
        TbMsInfo tbmsin = null;
//		TbMsInfoExt tbx = null;
        tbmsin = msmanager.getTBMsinfoby_msid(msid);
        if (tbmsin != null) {
            epinfo = epmanger.getEpinfo_byepid(tbmsin.getEpid().toString());
        }
        String resultmessage = null;
        if (tbmsin != null) {
//			tbx = msmanager.getExtById(msid);
//			request.setAttribute("tbx", tbx);
            request.setAttribute("tbmsinfo", tbmsin);
            if (epinfo != null) {
                int agenttype = agentmanger.getAgenttype(String.valueOf(epinfo.getAgent_Id()));
                if (agenttype != 0) { // �����������µ���ҵ
                    // һ������������
                    request.setAttribute("Pagentlist", agentmanger.getParentAgent(agenttype,r_id));
                    request.setAttribute("Cagentlist", agentmanger.getChildAgentByAId(epinfo.getAgent_Id()+""));
                    request.setAttribute("pagentid", agenttype);
                    request.setAttribute("childagentid", epinfo.getAgent_Id());
                    request.setAttribute("ep", epinfo.getEp_Id());
//                    request.setAttribute("epList", epmanger.getEpinfo_byagentid(String.valueOf(agenttype),
//                            String.valueOf(epinfo.getAgent_Id()), epinfo.getAgent_Id(), r_id));
                } else { // һ��������ֱ����ҵ
                    // һ������������
                    request.setAttribute("Pagentlist", agentmanger.getParentAgent(a_id,r_id));
                    // һ��ָ�����������̽����
                    if(r_id ==3 || r_id ==4){
    					request.setAttribute("Cagentlist",null);
    				}else{
    					request.setAttribute("Cagentlist", agentmanger.getChildAgentByParamentid(String.valueOf(epinfo.getAgent_Id())));
    				}
                    request.setAttribute("pagentid", epinfo.getAgent_Id());
                    request.setAttribute("childagentid", "-1");
                    request.setAttribute("ep", epinfo.getEp_Id());
                   
                }
                List epList =new ArrayList();
                if(r_id ==3 ||r_id == 4){
                	epList =epmanger.getEpinfo_byeid(String.valueOf(epinfo.getAgent_Id()), String.valueOf(-1), 
                			ep_id, r_id);
                }else{
                	epList =epmanger.getEpinfo_byagentid(String.valueOf(epinfo.getAgent_Id()),
                            String.valueOf(-1), a_id, r_id);
                }
                request.setAttribute("epList",epList );
            }
            return mapping.findForward("msmodify");
        } else { // ˵��û���ҵ�.

            resultmessage = "Ҫ�޸ĵļ�¼�����ڣ�";
            request.setAttribute("message", resultmessage);
            return mapping.findForward("showmslist");
        }
    }

    public ActionForward ms_flagSarch(ActionMapping mapping, ActionForm form,
                                      HttpServletRequest request, HttpServletResponse response)
            throws MessageException {
        String cmd = request.getParameter("CMD");
        int type = 0; // 2ȫ������ 1���� 0ʧЧ

        // ���ms_typeȡֵΪ�ջ�Ϊnull,����ʾȫ�����͵���Ϣ
        if (request.getParameter("type") == null
                || request.getParameter("type").equals("")) {
            type = 2;
        } else {
            type = Integer.parseInt(request.getParameter("type"));
        }

        if (type == 2) { // ȫ������
            // ��ȡ������
            int msCount = 0;
            msCount = msmanager.getAllMs_flagCount();
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

            // ���ص���ҳ��
            request.setAttribute("pageCount", pageCount);

            // ���÷��ص�������
            request.setAttribute("CMD", cmd);
            // ���÷����û�����
            request.setAttribute("type", type);

            // �����÷����û���ѯ��select��optionֵ
            request.setAttribute("msflag", type);

            request.setAttribute("msList", msmanager.getAllMs_flagByPage(
                    pageNo, pageSize));
            return mapping.findForward("succes");

        } else {
            // ��ȡ������
            int msCount = 0;
            msCount = msmanager.getMs_flagCount(type);
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
                pageNo = Integer.parseInt(pageNoStr.trim());
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
            request.setAttribute("type", type);
            // �����÷����û���ѯ��select��optionֵ
            request.setAttribute("msflag", type);
            // ���ص���ҳ��
            request.setAttribute("pageCount", pageCount);
            request.setAttribute("msList", msmanager.getMs_flagByPage(pageNo,
                    pageSize, type));
            return mapping.findForward("succes");
        }

    }

    public ActionForward ms_typesearch(ActionMapping mapping, ActionForm form,
                                       HttpServletRequest request, HttpServletResponse response)
            throws MessageException {
        String cmd = request.getParameter("CMD");
        int type = 0; // 3ȫ������ 0һ���ն��û� 1Ⱥ������� 2�����û�

        // ���ms_typeȡֵΪ�ջ�Ϊnull,����ʾȫ�����͵���Ϣ
        if (request.getParameter("type") == null
                || request.getParameter("type").equals("")) {
            type = 3;
        } else {
            type = Integer.parseInt(request.getParameter("type"));
        }

        if (type == 3) { // ȫ������
            // ��ȡ������
            int msCount = 0;
            msCount = msmanager.getAllMs_typeCount();
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

            // ���ص���ҳ��
            request.setAttribute("pageCount", pageCount);

            // ���÷��ص�������
            request.setAttribute("CMD", cmd);
            // ���÷����û�����
            request.setAttribute("type", type);

            // �����÷����û���ѯ��select��optionֵ
            request.setAttribute("mstype", type);

            request.setAttribute("msList", msmanager.getAllMs_typeByPage(
                    pageNo, pageSize));
            return mapping.findForward("succes");

        } else {
            // ��ȡ������
            int msCount = 0;
            msCount = msmanager.getMs_typeCount(type);
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
                pageNo = Integer.parseInt(pageNoStr.trim());
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
            request.setAttribute("type", type);

            // �����÷����û���ѯ��select��optionֵ
            request.setAttribute("mstype", type);

            // ���ص���ҳ��
            request.setAttribute("pageCount", pageCount);
            request.setAttribute("msList", msmanager.getMs_typeByPage(pageNo,
                    pageSize, type));
            return mapping.findForward("succes");
        }

    }

    /**
     * ����ms_id��ģ����ѯ
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward ms_idSarch(ActionMapping mapping, ActionForm form,
                                    HttpServletRequest request, HttpServletResponse response) {
        String cmd = request.getParameter("CMD");
        String type = null; // 2ȫ��״̬ 0���� 1����
        // ���ms_typeȡֵΪ�ջ�Ϊnull,����ʾȫ�����͵���Ϣ
        if (request.getParameter("type") == null
                || request.getParameter("type").equals("succes")) {
            return mapping.findForward("succes");
        } else {
            type = (String) request.getParameter("type");
            // ��ȡ������
            int msCount = 0;
            msCount = msmanager.getMsms_idCount(type);
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

            // ���ص���ҳ��
            request.setAttribute("pageCount", pageCount);

            // ����ҳ���ѯ�ؼ���
            request.setAttribute("msidSear", type);

            // ���÷��ص�������
            request.setAttribute("CMD", cmd);
            // ���÷����û�����
            request.setAttribute("type", type);

            request.setAttribute("msList", msmanager.getTBMsinfoby_msid(pageNo,
                    pageSize, type));
            return mapping.findForward("succes");
        }

    }

    // ����
    public ActionForward ms_Online_Statussearch(ActionMapping mapping,
                                                ActionForm form, HttpServletRequest request,
                                                HttpServletResponse response) throws MessageException {
        String cmd = request.getParameter("CMD");
        int type = 0; // 2ȫ��״̬ 0���� 1����
        // ���ms_typeȡֵΪ�ջ�Ϊnull,����ʾȫ�����͵���Ϣ
        if (request.getParameter("type") == null
                || request.getParameter("type").equals("")) {
            type = 2;
        } else {
            type = Integer.parseInt(request.getParameter("type"));
        }

        if (type == 2) { // ȫ��״̬
            // ��ȡ������
            int msCount = 0;
            msCount = msmanager.getAllMsOnline_allCount();
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

            // ���ص���ҳ��
            request.setAttribute("pageCount", pageCount);

            // ���÷��ص�������
            request.setAttribute("CMD", cmd);
            // ���÷����û�����
            request.setAttribute("type", type);

            // �����÷����û���ѯ��select��optionֵ
            request.setAttribute("msstatue", type);

            request.setAttribute("msList", msmanager.getAllMsOnline_allByPage(
                    pageNo, pageSize));
            return mapping.findForward("succes");

        } else {
            // ��ȡ������
            int msCount = 0;
            msCount = msmanager.getMs_OnlineCount(type);
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
            request.setAttribute("type", type);
            // �����÷����û���ѯ��select��optionֵ
            request.setAttribute("msstatue", type);
            // ���ص���ҳ��
            request.setAttribute("pageCount", pageCount);
            request.setAttribute("msList", msmanager.getMs_OnlineByPage(pageNo,
                    pageSize, type));
            return mapping.findForward("succes");
        }

    }

    public ActionForward ms_add(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request, HttpServletResponse response) {
        AddmsForm addmsform = (AddmsForm) form;
        String ms_id = addmsform.getMs_id().trim();
        String msslstr = addmsform.getMssl();
        String epid = addmsform.getEp();
        if(epid.equals("") || epid.equals(null)){
        	epid = "0";
        }
        String ms_level = addmsform.getMs_level();
        String ms_modid = addmsform.getMs_modid().trim();
        String netWorkType = addmsform.getNwType();
        String msCategory = request.getParameter("ms_category");//�ն����ͣ�A18��S40.....��
        
        int package_fee = addmsform.getPackage_fee();
        int ms_df = addmsform.getMs_df();
        int call = addmsform.getCall();
        int mileageas = addmsform.getMileageas();
        int mssl;
        if (msslstr == null) {
            mssl = 1;
        } else {
            mssl = Integer.parseInt(msslstr);
        }
        String ms_name = addmsform.getMs_name().trim();
        String password = addmsform.getPassword();
        String c03 = addmsform.getC03();
        String c04 = addmsform.getC04();
        int ms_type = Integer.parseInt(addmsform.getMs_type());
        // �û���״̬flag 0 ʧЧ         1 ����      Ĭ��1
        int flag = 1;
        String memo = addmsform.getMemo();
        String resultmessage = null;
        String pagentid = ""; // һ��������id
        String childagentid = ""; // ����������id
        if (request.getParameter("pagentid") != null) {
            pagentid = request.getParameter("pagentid");
        } else {
            pagentid = "-1"; // ���������ܲ�
        }

        if (request.getParameter("childagentid") != null) {
            childagentid = request.getParameter("childagentid");
        } else {
            childagentid = "-1"; // ֱ����ҵ
        }
        String msid = request.getParameter("ms_id");
        TbMsInfo info = msmanager.getTBMsinfoby_msid(msid);

        if (info == null) {
        	TbPFInfo pfinfo = pfManager.findPFInfoById(package_fee+"");
            msmanager.createMs(ms_id, mssl, ms_name, password, ms_type, flag,
                    epid, ms_level, ms_modid, pfinfo, ms_df, call,
                    mileageas, memo, c03, c04, netWorkType,msCategory,Integer.parseInt(pagentid));
            String content = "";
            if (mssl > 1) {
                String msrp = (String) ms_id.subSequence(ms_id.length() - 5, ms_id.length());
                content = "��������ն�[" + ms_id.subSequence(0, ms_id.length() - 5) + " {" + msrp + "}~" + "{" + (Integer.parseInt(msrp) + mssl) + "} ]��(" + mssl + ")��";
            } else {
                content = "����ն�[" + ms_id + "]";
            }
            //��¼������־
            TbUserLog userLog = new TbUserLog();
            userLog.setUserId(Integer.parseInt(request.getSession().getAttribute("uId").toString()));
            userLog.setlDate(new Date());
            userLog.setlAddress(HRAddress.getIpAddr(request));
            userLog.setlType(1);
            userLog.setlContent(content);
            logManager.save(userLog);
            resultmessage = "�ն���Ϣ��ӳɹ���";
            request.setAttribute("message", resultmessage);
        } else {
            resultmessage = "���ն˺��Ѿ�����,���ʧ��";
            request.setAttribute("message", resultmessage);
        }
        request.setAttribute("pagentid", pagentid);
        request.setAttribute("childagentid", childagentid);
        request.setAttribute("ep", epid);
        return mapping.findForward("showmslist");
    }

    public ActionForward ms_xianxi(ActionMapping mapping, ActionForm form,
                                   HttpServletRequest request, HttpServletResponse response) {
        // ȡ��msid
        String epname = "";
        TbEnterpriseInfo epinfo = null;
        String msid = request.getParameter("msid");
        TbMsInfo tbmsin = null;
        String resultmessage = null;
        tbmsin = msmanager.getTBMsinfoby_msid(msid);
//		TbMsInfoExt tbx=msmanager.getExtById(msid);
        if (tbmsin.getEpid() != null) {
            epinfo = epmanger.getEpinfo_byepid(tbmsin.getEpid().toString());
        }
        if (epinfo == null) {
            epname = "";
        } else {
            epname = epinfo.getEp_Name();
        }

        if (tbmsin != null) {
            System.out.println("�ҵ�������¼����ϸ��Ϣ");
            request
                    .setAttribute("tbmsinfo", msmanager
                            .getTBMsinfoby_msid(msid));
            request.setAttribute("epname", epname);
//			request.setAttribute("tbx", tbx);
            return mapping.findForward("xiangxi");
        } else { // ˵��û���ҵ�.
            System.out.println("û���ҵ�������¼����ϸ��Ϣ");
            resultmessage = "�ü�¼�����ڣ�";
            request.setAttribute("message", resultmessage);
            return mapping.findForward("showmslist");
        }
    }

    /**
     * ����ɾ��
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward ms_delete(ActionMapping mapping, ActionForm form,
                                   HttpServletRequest request, HttpServletResponse response) {
        // String msid_list = request.getParameter("msidlist");
        String[] list = request.getParameterValues("list");
        Boolean delsucc = msmanager.deleteMs(list);
        for (int i = 0; i < list.length; i++) {
            //��¼������־
            TbUserLog userLog = new TbUserLog();
            userLog.setUserId(Integer.parseInt(request.getSession().getAttribute("uId").toString()));
            userLog.setlDate(new Date());
            userLog.setlAddress(HRAddress.getIpAddr(request));
            userLog.setlType(3);
            userLog.setlContent("ɾ���ն�{" + list[i] + "}");
            logManager.save(userLog);
        }
        String resultmessage = null;
        if (delsucc) {
            resultmessage = "�ն���Ϣɾ���ɹ���";
            request.setAttribute("message", resultmessage);
            return mapping.findForward("showmslist");
        } else {
            resultmessage = "�ն���Ϣɾ��ʧ�ܣ�";
            request.setAttribute("message", resultmessage);
            return mapping.findForward("showmslist");
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
    public ActionForward ms_Sarch(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request, HttpServletResponse response) {
        String pagentid = ""; // һ��������id
        String childagentid = ""; // ����������id
        String cmd = request.getParameter("CMD");
        HttpSession session = request.getSession(true);
        String type = request.getParameter("type");
        
        String agent = request.getSession().getAttribute("agentId")+"";
        int apid = Integer.parseInt(request.getSession().getAttribute("aPId")+"");
        if (request.getParameter("pagentid") != null && (agent.equals("-1") || agent.equals("0"))) {
            pagentid = request.getParameter("pagentid");
        } else {
            //pagentid = "-1"; // ���������ܲ�
        	if(apid!=0){
        		pagentid = apid+"";
        	}else{
        		pagentid = agent;
        	}
        	
        	if(pagentid.equals("0"))
        		pagentid = "-1";
        }

        if (request.getParameter("childagentid") != null) {
            childagentid = request.getParameter("childagentid");
        } else {
            childagentid = "-2"; // ������ҵ
        }
        if(apid!=0){
        	childagentid = agent+"";
    	}

        String ep = request.getParameter("ep");
        if (ep == null || ep.equals("null")|| ep.equals("")) {
        	if(r_id == 4 || r_id == 3){
        		ep = ep_id + "";
        	}else{
        		ep = "-1";
        	}
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
        String ms_id = request.getParameter("msid");
        if (ms_id == null || ms_id.equals("")) {
            ms_id = "";
        }
        String ms_name = request.getParameter("msname");
        if (ms_name == null || ms_name.equals("")) {
            ms_name = "";
        }
        String ismobile = request.getParameter("c03");
        if (ismobile == null || ismobile.equals("")) { // 2ȫ��,0,��ֹ�л�,1,�����л�
            ismobile = "2";
        }
        String arrearage = request.getParameter("ms_category");
        if (arrearage == null || arrearage.equals("")) { // -1ȫ����0-12����
            arrearage = "-1";
        }
        int msCount = 0;
        msCount = msmanager.getMs_sertch(user_type, statue, flag, ms_id.trim(),
                ms_name.trim(), pagentid, childagentid, ep, ismobile, arrearage);
        // ��ȡÿҳ������
        String ps =request.getParameter("pageSize");
		int pageSize = 10;
		if(ps!= null){
			pageSize =Integer.parseInt(ps);
		}
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
        request.setAttribute("ms_category", arrearage);
        request.setAttribute("ms_id", ms_id);
        request.setAttribute("ms_name", ms_name);
        request.setAttribute("pagentid", pagentid);
        request.setAttribute("childagentid", childagentid);
        request.setAttribute("ep", ep);
//        if(!pagentid.equals("-1")){
//			// һ������������
//			request.setAttribute("Pagentlist", agentmanger.getParentAgentNotIncludeZB());
//		}else{
			// һ������������
        
		if(apid == 0){
			request.setAttribute("Pagentlist", agentmanger.getParentAgent(a_id,r_id));
		//}
		// һ��ָ�����������̽����
			if(r_id ==3 || r_id ==4){
				request.setAttribute("Cagentlist",null);
			}else{
			request.setAttribute("Cagentlist", agentmanger.getChildAgentByParamentid(pagentid));
			}
		}else{
			int a = agentmanger.getAgent_ByAgentID(apid+"").getAgent_Id();
			request.setAttribute("Pagentlist", agentmanger.getParentAgent(a,r_id));
			request.setAttribute("Cagentlist", agentmanger.getChildAgentByAId(agent));
			pagentid = a+"";
		}
//			request.setAttribute("Pagentlist", agentmanger.getParentAgent(a_id,r_id));
//		//}
//        // һ��ָ�����������̽����
//        request.setAttribute("Cagentlist", agentmanger
//                .getChildAgentByParamentid(pagentid));
        // ����������ҵĬ��
        List<TbEnterpriseInfo> list = null;
		if (r_id == 3 || r_id == 4){
			list = epmanger.getEpinfo_byeid(pagentid,
					childagentid, ep_id, r_id);
		}else{
			list = epmanger.getEpinfo_byagentid(pagentid,
					childagentid, a_id, r_id);
		}
		request.setAttribute("epList", list);
        List<TbMsInfo> msList = msmanager.getTBMsinfoby_mspage(pageNo,
                pageSize, user_type, statue, flag, ms_id.trim(),
                ms_name.trim(), pagentid, childagentid, ep, ismobile, arrearage);
//		String sql="update TbMsInfo set c07=? where msId=?";
//		for (TbMsInfo tbMsInfo : msList) {
//			try {
//				msmanager.update(sql,CnToPYAlpha.getFive(tbMsInfo.getMsName(), 5), tbMsInfo.getMsId());
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
        // ���ص���ҳ��
        request.setAttribute("pageCount", pageCount);
        request.setAttribute("msList", msList);
        if (type != null && !type.equals("null")) {
            return mapping.findForward("kfsucces");
        } else {
            return mapping.findForward("succes");
        }
    }

    /**
     * ��ö�Ӧ��ϵҳ��
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward getMsExtAction(ActionMapping mapping, ActionForm form,
                                        HttpServletRequest request, HttpServletResponse response) {
        String msId = request.getParameter("msId");
        TbMsInfoExt tbx = msmanager.getExtById(msId);
        String message = null;
        if (tbx != null) {
            request.setAttribute("tbx", tbx);
            return mapping.findForward("getmsext");
        } else {
            message = "���صĽ�������ڣ�";
            request.setAttribute("message", message);
            return mapping.findForward("showmslist");
        }
    }

    /**
     * ִ��ָ����hql�������޸�
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward msExtUdpate(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response) {
        String msId = request.getParameter("mId");
        String newmsId = request.getParameter("msId");
        String simNum = request.getParameter("simNum");
        String deviceNum = request.getParameter("deviceNum");
        int carPlateColor = Integer.parseInt(request.getParameter("carPlateColor"));
        System.out.println("ԭID:" + msId + "\n" + "��ID:" + newmsId);
        if (msId == null && "".equals(msId)) {
            request.setAttribute("message", "�޸�ʧ��");
            return mapping.findForward("showmslist");
        }
        if (newmsId == null && "".equals(newmsId)) {
            request.setAttribute("message", "�޸�ʧ��");
            return mapping.findForward("showmslist");
        }
        //ִ�е�hql���
        String hql_ms = "UPDATE TbMsInfo SET msId=? WHERE msId=?";
        String hql_grp = "UPDATE TbGrpInfo SET msid=? WHERE msid=?";
        String hql_dbms = "UPDATE TbDdmsMsListInfo SET ms_id=? WHERE ms_id=?";
        String hql_gpsms = "UPDATE TbGpsMsListInfo SET l_ms_id=? WHERE l_ms_id=?";
        String hql_grpms = "UPDATE TbGrpMsListInfo SET ms_id=? WHERE ms_id=?";

        if (msmanager.updateMsExt(msId, simNum, deviceNum, 54, carPlateColor, newmsId) > 0) {
            if (msmanager.getMsById("SELECT COUNT(*) FROM TbMsInfo WHERE msId=?", msId) > 0) {
                msmanager.update(hql_ms, newmsId, msId);
            }
            if (msmanager.getMsById("SELECT COUNT(*) FROM TbGrpInfo WHERE msid=?", msId) > 0) {
                msmanager.update(hql_grp, newmsId, msId);
            }
            if (msmanager.getMsById("SELECT COUNT(*) FROM TbDdmsMsListInfo WHERE ms_id=?", msId) > 0) {
                msmanager.update(hql_dbms, newmsId, msId);
            }
            if (msmanager.getMsById("SELECT COUNT(*) FROM TbGpsMsListInfo WHERE l_ms_id=?", msId) > 0) {
                msmanager.update(hql_gpsms, newmsId, msId);
            }
            if (msmanager.getMsById("SELECT COUNT(*) FROM TbGrpMsListInfo WHERE ms_id=?", msId) > 0) {
                msmanager.update(hql_grpms, newmsId, msId);
            }
            request.setAttribute("message", "�޸ĳɹ�");
            return mapping.findForward("showmslist");
        } else {
            request.setAttribute("message", "�޸�ʧ��");
            return mapping.findForward("showmslist");
        }

    }

    /**
     * ����
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward ms_dj(ActionMapping mapping, ActionForm form,
                               HttpServletRequest request, HttpServletResponse response) {
        String[] list = request.getParameterValues("list");
        Boolean delsucc = msmanager.msdj(list);
        String resultmessage = null;
        if (delsucc) {
            resultmessage = "�ն���Ϣ����ɹ���";
            request.setAttribute("message", resultmessage);
            return mapping.findForward("showmslist");
        } else {
            resultmessage = "�ն���Ϣ����ʧ�ܣ�";
            request.setAttribute("message", resultmessage);
            return mapping.findForward("showmslist");
        }

    }

}