package com.elegps.tscweb.action.ws;

import com.elegps.tscweb.action.base.BaseAction;
import com.elegps.tscweb.comm.Comm;
import com.elegps.tscweb.comm.DateFormat;
import com.elegps.tscweb.comm.HRAddress;
import com.elegps.tscweb.comm.ToJsonString;
import com.elegps.tscweb.exception.MessageException;
import com.elegps.tscweb.model.TbAdvInfo;
import com.elegps.tscweb.model.TbAppPayInfo;
import com.elegps.tscweb.model.TbGrpInfo;
import com.elegps.tscweb.model.TbGrpMsListInfo;
import com.elegps.tscweb.model.TbMsInfo;
import com.elegps.tscweb.model.TbParamsInfo;
import com.elegps.tscweb.model.TbPhoneTypeInfo;
import com.elegps.tscweb.model.TbPhonekoufeiLogVo;
import com.elegps.tscweb.model.TbUserLog;
import com.elegps.tscweb.tscconfig.FilePathConfig;
import com.wechatpay.demo.Demo;
import com.wechatpay.demo.WxPayDto;

import emdx.example.SendManager;
import flexjson.JSONSerializer;

import org.apache.axis.i18n.RB;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * MyEclipse Struts Creation date: 10-20-2008
 * 
 * XDoclet definition:
 * 
 * @struts.action validate="true"
 */
public class WsAction extends BaseAction {

	public static Map<String, MsInfoBean> checkCodeMap = new ConcurrentHashMap<String, MsInfoBean>();
	private String prifix_id = FilePathConfig.getMSId();
	private String prifix_grpid = FilePathConfig.getGRPId10();
	private String yht_enterpriseId = FilePathConfig.getYhtEnterpriseId();
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
		ResourceBundle rb = null;
		String locale = request.getParameter("locale");
		if(locale==null) locale = "zh";
		if("zh".equals(locale)){	//����
			Locale currentLocale=new Locale("zh", "CH");
			rb=ResourceBundle.getBundle("i18n.MessgesBundle", currentLocale);
		}else if("en".equals(locale)){	//Ӣ��
			Locale currentLocale=new Locale("en", "US");
			rb=ResourceBundle.getBundle("i18n.MessgesBundle", currentLocale);
		}
		
		String cmd = request.getParameter("CMD");
		
		if(cmd == null || cmd.equals("")){
			request.setAttribute("flag", rb.getString("msg1"));
			actionforward = mapping.findForward("default");
		}else if(cmd.equals("register")){
			actionforward = registerMs(mapping, request,rb);
			
		}else if(cmd.equals("registergetcode")){
			actionforward = registergetcode(mapping, request,rb);
			
		}else if(cmd.equals("resetpwd")){
			actionforward = resetPwd(mapping, request,rb);
			
		}else if(cmd.equals("resetpwdgetcode")){
			actionforward = resetpwdgetcode(mapping, request,rb);
			
		}else if(cmd.equals("updatemsinfo")){
			actionforward = updateMsinfo(mapping, request,rb);
			
		}else if(cmd.equals("updatefamilynumber")){
		actionforward = updateFamilyNumber(mapping, request,rb);

		}else if(cmd.equals("updatefamilynumbers")){
		actionforward = updateFamilyNumbers(mapping, request,rb);

		}else if(cmd.equals("getfamilynumbers")){
		actionforward = getFamilyNumbers(mapping, request,rb);

		} else if(cmd.equals("yqfriend")){
			actionforward = yqFriend(mapping, request,rb);
			
		}else if(cmd.equals("xjgrp")){	//����Ⱥ��
			actionforward = xjGrp(mapping, request,rb);
			
		}else if(cmd.equals("cztjgrp")){	//Ⱥ��ͬ���ն˼���
			actionforward = cztjGrp(mapping, request,rb);
			if("OK".equals(request.getSession().getAttribute("succeed"))){
				cztjgprMessage(request);  //no
			}
		}else if(cmd.equals("sqjrgrp")){	//�ն˲������Ⱥ������
			actionforward = sqjrGrp(mapping, request,rb);
			
		}else if(cmd.equals("scgrpms")){	//ɾ��Ⱥ���Ա
			actionforward = scGrpMs(mapping, request,rb);
			
		}else if(cmd.equals("tcgrp")){	//Ⱥ���Ա�˳�Ⱥ��
			actionforward = tcGrp(mapping, request,rb);
			
		}else if(cmd.equals("jsgrp")){	//Ⱥ���Ա�˳�Ⱥ��
			actionforward = jsGrp(mapping, request,rb);
			
		}else if(cmd.equals("tbphone")){	//ͬ������
			actionforward = tbPhone(mapping, request,rb);
			
		}else if(cmd.equals("updategrpinfo")){	//�޸�Ⱥ�����
			actionforward = updateGrpinfo(mapping, request,rb);
		}else if(cmd.equals("grpinfo")){	//��ȡȺ����Ϣ
			actionforward = grpinfo(mapping, request,rb);	
		}else if(cmd.equals("getserveraddress")){	//��ȡ��¼TSC�ķ�����IP���˿�
			actionforward = getServerAddress(mapping, request,rb);
			
		}else if (cmd.equals("addfreeswitchuser")) {
			actionforward = addFreeswitchUser(mapping, request,rb);
			
		}else if (cmd.equals("getmsidofapp")) {//APP��ȡ�󶨵��ն˵�¼�ʺ�phone_login
			actionforward = getmsidofapp(mapping, request,rb);
		}else if (cmd.equals("getpayinfo")) {//APP��ȡ֧����Ϣ
			actionforward = getpayinfo(mapping, request,rb);
		}else if (cmd.equals("msmoney")) {//APP��ȡ�ʺ����
			actionforward = msmoney(mapping, request,rb);
		}else if (cmd.equals("movemss")) {//�����ƶ����ѵ�ָ��Ⱥ��
			actionforward = movemss(mapping, request,rb);
		}else if (cmd.equals("advshowjsp")) {//app��ȡ���
			actionforward = advshowjsp(mapping, request,rb);
		}else if (cmd.equals("searchphone")) {//�ֻ�����ģ����ѯ
			actionforward = searchphone(mapping, request,rb);
		}else if (cmd.equals("addpt")) {//����ֻ��ͺ�
			actionforward = addpt(mapping, request,rb);
		}else if (cmd.equals("deletept")) {//ɾ���ֻ��ͺ�
			actionforward = deletept(mapping, request,rb);
		}else if (cmd.equals("searchpt")) {//��ѯ�ֻ��ͺ�
			actionforward = searchpt(mapping, request,rb);
		}else if (cmd.equals("userbillinfo")) {//��ѯ�û��۷��˵���Ϣ
			actionforward = userbillinfo(mapping, request,rb);
			
		}else if (cmd.equals("ali_order")){//��ȡ֧����֧������
			actionforward = aliOrder(mapping, request,response,rb);
		}else if (cmd.equals("wechat_order")) {//����΢��Ԥ֧������
			actionforward = wechatOrder(mapping, request,response,rb);
		}else if (cmd.equals("mh_grp")) {//Ⱥ��š���ģ����������Ⱥ��
			actionforward = mhGrp(mapping, request,response,rb);
		}else if (cmd.equals("mstyjq")){
			actionforward = mstyjq(mapping, request,rb);
			if("OK".equals(request.getSession().getAttribute("succeed"))){
				mstyjqMessage(request); //no
			}
		}
		else{
			request.setAttribute("flag", rb.getString("msg2"));
			actionforward = mapping.findForward("default");
		}
		
		return actionforward;
	}

	private ActionForward sqjrGrp(ActionMapping mapping,
			HttpServletRequest request,ResourceBundle rb) {
		String phone = request.getParameter("phone");
		if(isEmpty(phone)){
			request.setAttribute("flag", rb.getString("msg3"));
			return mapping.findForward("default");
		}
		
		String grpid = request.getParameter("grpid");
		if(isEmpty(grpid)){
			request.setAttribute("flag", rb.getString("msg4"));
			return mapping.findForward("default");
		}
		TbMsInfo msinfo = msmanager.getTBMsinfoby_msid(prifix_id + phone);
		if(msinfo==null){
			request.setAttribute("flag", rb.getString("msg5"));
			return mapping.findForward("default");
		}
		
		String grpID = prifix_grpid.substring(0, 8) + grpid;
		TbGrpInfo grpInfo = grpmanager.getGrpinfoby_grpid(grpID);
		if(grpInfo == null){
			request.setAttribute("flag", rb.getString("msg6"));
			return mapping.findForward("default");
		}
//		if(!isExistGrp(grpID)){
//			request.setAttribute("flag", "Ⱥ�鲻����!");
//			return mapping.findForward("default");
//		}
		
		int mse =msinfo.getEpid();
		int grpe = grpInfo.getEp_Id();
		if(mse != grpe){
			request.setAttribute("flag", rb.getString("msg7"));
			return mapping.findForward("default");
		}
		String content = "NOTIFY|MSREQINGRP|"+msinfo.getMsId()+"|"+msinfo.getMsName()+"|"+grpInfo.getGrpid()+"|"+grpInfo.getGrpname();
		creatParams(grpInfo.getMsid().substring(10, 21), content);
		
		String grpname =grpmanager.getGrpinfoby_grpid(grpID).getGrpname();
		request.setAttribute("flag", "true,"+grpID+","+grpname);
		return mapping.findForward("default");
	}

	private ActionForward mhGrp(ActionMapping mapping,
			HttpServletRequest request, HttpServletResponse response,ResourceBundle rb) {
		String nameOrId = request.getParameter("nameOrId");
		if( Comm.isEmpty(nameOrId)){
			request.setAttribute("flag", ToJsonString.packagePara("false", rb.getString("msg1")));
			return mapping.findForward("default");
		}
		String phone = request.getParameter("phone");
		if( Comm.isEmpty(phone)){
			request.setAttribute("flag", ToJsonString.packagePara("false", rb.getString("msg1")));
			return mapping.findForward("default");
		}
		TbMsInfo msinfo = msmanager.getTBMsinfoby_msid(prifix_id+phone);
		if(msinfo == null){
			request.setAttribute("flag", ToJsonString.packagePara("false", rb.getString("msg8")));
			return mapping.findForward("default");
		}
		
		request.setAttribute("flag", ToJsonString.packagePara("true", rb.getString("msg9"),"grpList",grpmanager.getGrpByNameOrId(nameOrId, msinfo.getEpid())));
		return mapping.findForward("default");
	}

	/**
	 * ��ȡ֧����֧����Ϣ
	 * @param mapping
	 * @param request
	 * @param response
	 * @return
	 */
	private ActionForward aliOrder(ActionMapping mapping,
			HttpServletRequest request, HttpServletResponse response,ResourceBundle rb) {
		String phone = request.getParameter("phone");
		if( Comm.isEmpty(phone)){
			request.setAttribute("flag", ToJsonString.packagePara("false", rb.getString("msg1")));
			return mapping.findForward("default");
		}
		TbMsInfo msinfo = msmanager.getTBMsinfoby_msid(prifix_id+phone);
		System.out.println(rb.getString("msg10")+prifix_id+phone);
		if(msinfo == null){
			request.setAttribute("flag", ToJsonString.packagePara("false", rb.getString("msg11")));
			return mapping.findForward("default");
		}
		
		String order = DateFormat.format(new Date(), "yyyyMMddHHmmssSSS")+phone.substring(7, 11);
		
		request.setAttribute("flag", ToJsonString.packagePara("true", rb.getString("msg12"),"name","���ұ��˺ų�ֵ","order",order,"aliInfo",FilePathConfig.getAliInfo()));
		return mapping.findForward("default");
	}



	/**
	 * ����΢��Ԥ֧������
	 * @param mapping
	 * @param request
	 * @param response
	 * @return
	 */
	private ActionForward wechatOrder(ActionMapping mapping,
			HttpServletRequest request, HttpServletResponse response,ResourceBundle rb) {
		String money = request.getParameter("money");
		if( Comm.isEmpty(money)){
			request.setAttribute("flag", ToJsonString.packagePara("false", rb.getString("msg1")));
			return mapping.findForward("default");
		}
		String attach = request.getParameter("attach");
		if(Comm.isEmpty(attach)){
			request.setAttribute("flag", ToJsonString.packagePara("false", rb.getString("msg1")));
			return mapping.findForward("default");
		}
		
		String[] b = attach.split("\\|");
		TbMsInfo msinfo = msmanager.getTBMsinfoby_msid(prifix_id+b[1].replace(" ", ""));
		if(msinfo == null){
			request.setAttribute("flag", ToJsonString.packagePara("false", rb.getString("msg11")));
			return mapping.findForward("default");
		}
		
		WxPayDto tpWxPayDto = new WxPayDto();
		tpWxPayDto.setAttach(attach);
		tpWxPayDto.setBody("���ұ��˺ų�ֵ");
		tpWxPayDto.setOrderId(DateFormat.format(new Date(), "yyyyMMddHHmmssSSS")+b[1].substring(7, 11));
		tpWxPayDto.setTotalFee(money);
		tpWxPayDto.setSpbillCreateIp(HRAddress.getIpAddr(request));
		String clientPar = Demo.getPackage(tpWxPayDto,request,response);
		if(!clientPar.equals("false")){
			request.setAttribute("flag", clientPar);
			return mapping.findForward("default");
		}
		request.setAttribute("flag", ToJsonString.packagePara("false", rb.getString("msg14")));
		return mapping.findForward("default");
	}
	
	
	/**
	 * ��ѯ�û��۷��˵���Ϣ
	 * @param mapping
	 * @param request
	 * @return
	 */
	private ActionForward userbillinfo(ActionMapping mapping,
			HttpServletRequest request,ResourceBundle rb) {
		String msId = request.getParameter("msId");
		if(Comm.isEmpty(msId)){
			request.setAttribute("flag", ToJsonString.packagePara("false", rb.getString("msg1")));
			return mapping.findForward("default");
		}
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		if(Comm.isEmpty(startTime)){
			startTime=DateFormat.format(new Date(), "yyyy-MM-dd")+" 00:00:00";
		}
		if(Comm.isEmpty(endTime)){
			endTime=DateFormat.format(new Date(), null);//Ĭ�ϵ�ǰʱ��
		}
		
		TbMsInfo msInfo = msmanager.getTBMsinfoby_msid(msId);
		if(msInfo == null){
			request.setAttribute("flag", ToJsonString.packagePara("false", rb.getString("msg15")));
			return mapping.findForward("default");
		}
	
		Map<String,String> map =apManager.findApCountByTime(msId,startTime,endTime);
		Map<String,String> map2 =apManager.findKFByTime(msId,startTime,endTime);
		TbPhonekoufeiLogVo pkVo =pkManager.getPhonekoufeiLog(msId, startTime, endTime);
		request.setAttribute("flag", ToJsonString.packagePara("true", rb.getString("msg9"),"money",msInfo.getMsMoney(),
				"appPayCount",map.get("apcount"),"appPayMoney",map.get("apsum"),
				//���͵��˵���Ϣ
				"totalCount",map2.get("totalCount"),"totalMoney",map2.get("totalMoney"),
				"msgCount",map2.get("msgCount"),"msgOneCount",map2.get("msgOneCount"),
				"msgManyCount",map2.get("msgManyCount"),"msgTotalMoney",map2.get("msgTotalMoney"),
				"picCount",map2.get("picCount"),"picOneCount",map2.get("picOneCount"),
				"picManyCount",map2.get("picManyCount"),"picTotalMoney",map2.get("picTotalMoney"),
				"voiCount",map2.get("voiCount"),"voiOneCount",map2.get("voiOneCount"),
				"voiManyCount",map2.get("voiManyCount"),"voiTotalMoney",map2.get("voiTotalMoney"),
				
				//���յ��˵���Ϣ
				"rtotalCount",map2.get("rtotalCount"),"rtotalMoney",map2.get("rtotalMoney"),
				"rmsgCount",map2.get("rmsgCount"),"rmsgOneCount",map2.get("rmsgOneCount"),
				"rmsgManyCount",map2.get("rmsgManyCount"),"rmsgTotalMoney",map2.get("rmsgTotalMoney"),
				"rpicCount",map2.get("rpicCount"),"rpicOneCount",map2.get("rpicOneCount"),
				"rpicManyCount",map2.get("rpicManyCount"),"rpicTotalMoney",map2.get("rpicTotalMoney"),
				"rvoiCount",map2.get("rvoiCount"),"rvoiOneCount",map2.get("rvoiOneCount"),
				"rvoiManyCount",map2.get("rvoiManyCount"),"rvoiTotalMoney",map2.get("rvoiTotalMoney"),
				
				//�绰���˵���Ϣ
				"dialingNum",pkVo.getDialingNum(),
				"dialingMoney",pkVo.getDialingMoney(),  //�ֵ�λת��Ԫ
				"calledNum",pkVo.getCalledNum(),
				"calledMoney",pkVo.getCalledMoney(),  //�ֵ�λת��Ԫ
				"dialingTime",pkVo.getDialingTime(),
				"calledTime",pkVo.getCalledTime()
				));
		return mapping.findForward("default");
	}

	private ActionForward grpinfo(ActionMapping mapping,
			HttpServletRequest request,ResourceBundle rb) {
		String grpId = request.getParameter("grpId");
		if( grpId == "" || grpId == null){
			request.setAttribute("flag", ToJsonString.packagePara("false", rb.getString("msg16")));
			return mapping.findForward("default");
		}
		TbGrpInfo grpInfo = grpmanager.getGrpinfoby_grpid(grpId);
		if(grpInfo == null){
			request.setAttribute("flag", ToJsonString.packagePara("false", rb.getString("msg6")));
			return mapping.findForward("default");
		}
		int lineCount = 0;
		int grpMsCount = grpmsmanager.grpMsCount(grpId);
		if(grpMsCount !=0 ){
			lineCount = grpmsmanager.grpOnLineMsCount(grpId);
		}
		TbMsInfo msInfo = msmanager.getTBMsinfoby_msid(grpInfo.getMsid());
		if(msInfo==null){
			msInfo = new TbMsInfo();
		}
		request.setAttribute("flag", ToJsonString.packagePara("true", rb.getString("msg17"),
				"grpName",grpInfo.getGrpname(),"grpId",grpInfo.getGrpid(),"cjrId",grpInfo.getMsid(),"cjrName",msInfo.getMsName()
				,"msCount",grpMsCount,"lineMsCount",lineCount));
		return mapping.findForward("default");
	}

	private ActionForward searchpt(ActionMapping mapping,
			HttpServletRequest request,ResourceBundle rb) {
		String type = request.getParameter("type");
		if( type == "" || type == null){
			request.setAttribute("flag", rb.getString("msg69"));
			return mapping.findForward("default");
		}
		TbPhoneTypeInfo ptInfo =  ptManager.findOneByTypeAndFlag(type,1);
		if(ptInfo==null){
			request.setAttribute("flag", rb.getString("msg19"));
			return mapping.findForward("default");
		}
		
		//String typeList = ptManager.findAllPTInfo();
		request.setAttribute("flag", "true");
		return mapping.findForward("default");
	}

	private ActionForward deletept(ActionMapping mapping,
			HttpServletRequest request,ResourceBundle rb) {
		String type = request.getParameter("type");
		if( type == "" || type == null){
			request.setAttribute("flag", rb.getString("msg69"));
			return mapping.findForward("default");
		}
		TbPhoneTypeInfo ptInfo =  ptManager.findOneByTypeAndFlag(type,-1);
		if(ptInfo==null){
			request.setAttribute("flag", rb.getString("msg20"));
			return mapping.findForward("default");
		}
		ptManager.deletePTInfo(ptInfo);
		request.setAttribute("flag", rb.getString("msg21"));
		return mapping.findForward("default");
	}

	private ActionForward addpt(ActionMapping mapping,
			HttpServletRequest request,ResourceBundle rb) {
		String type = request.getParameter("type");
		String model = request.getParameter("MODEL");
		if( type == "" || type == null || model == "" || model == null){
			request.setAttribute("flag", rb.getString("msg1"));
			return mapping.findForward("default");
		}
		
		if(ptManager.findOneByTypeAndFlag(type,-1)!=null){
			request.setAttribute("flag", rb.getString("msg22"));
			return mapping.findForward("default");
		}
		
		String Product = request.getParameter("Product");
		String CPU_ABI = request.getParameter("CPU_ABI");
		String CPU_ABI_TWO = request.getParameter("CPU_ABI_TWO");
		String TAGS = request.getParameter("TAGS");
		String VERSION_SDK = request.getParameter("VERSION_SDK");
		String VERSION_INCREMENTAL = request.getParameter("VERSION_INCREMENTAL");
		String VERSION_RELEASE = request.getParameter("VERSION_RELEASE");
		String VERSION_CODENAME = request.getParameter("VERSION_CODENAME");
		String DEVICE = request.getParameter("DEVICE");
		String DISPLAY = request.getParameter("DISPLAY");
		String BRAND = request.getParameter("BRAND");
		String BOARD = request.getParameter("BOARD");
		String FINGERPRINT = request.getParameter("FINGERPRINT");
		String ID = request.getParameter("ID");
		String MANUFACTURER = request.getParameter("MANUFACTURER");
		String USER = request.getParameter("USER");
		TbPhoneTypeInfo ptInfo = new TbPhoneTypeInfo();
		ptInfo.setType(type);
		ptInfo.setModel(model);
		ptInfo.setBoard(BOARD);
		ptInfo.setBrand(BRAND);
		ptInfo.setCpuAbi(CPU_ABI);
		ptInfo.setCpuAbiTwo(CPU_ABI_TWO);
		ptInfo.setDevice(DEVICE);
		ptInfo.setDisplay(DISPLAY);
		ptInfo.setFalg(0);
		ptInfo.setFingerprint(FINGERPRINT);
		ptInfo.setManufacturer(MANUFACTURER);
		ptInfo.setProduct(Product);
		ptInfo.setSdkId(ID);
		ptInfo.setSdkUser(USER);
		ptInfo.setTags(TAGS);
		ptInfo.setTime(new Date());
		ptInfo.setVersionCodename(VERSION_CODENAME);
		ptInfo.setVersionIncremental(VERSION_INCREMENTAL);
		ptInfo.setVersionRelease(VERSION_RELEASE);
		ptInfo.setVersionSdk(VERSION_SDK);
		ptManager.addPTInfo(ptInfo);
		
		request.setAttribute("flag", rb.getString("msg23"));
		return mapping.findForward("default");
	}

	/**
	 * �ֻ�����ģ����ѯ�ն�
	 * @param mapping
	 * @param request
	 * @return
	 */
	private ActionForward searchphone(ActionMapping mapping,
			HttpServletRequest request,ResourceBundle rb) {
		String phone = request.getParameter("phone");
		System.out.println("searchphone:"+phone);
		if( phone == "" || phone == null){
			request.setAttribute("flag", rb.getString("msg24"));
			return mapping.findForward("default");
		}
		if(phone.length()!=11){
			request.setAttribute("flag", rb.getString("msg25"));
			return mapping.findForward("default");
		}
		System.out.println("searchphone2:"+phone);
		String msid = msmanager.findMsIdByPhone(phone);
		request.setAttribute("flag", msid);
		return mapping.findForward("default");
	}
	
	private ActionForward advshowjsp(ActionMapping mapping,
			HttpServletRequest request,ResourceBundle rb) {
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

	private ActionForward movemss(ActionMapping mapping,
			HttpServletRequest request,ResourceBundle rb) {
		String phone = request.getParameter("phone");
		if (isEmpty(phone)) {
			request.setAttribute("flag", rb.getString("msg26"));
			return mapping.findForward("default");
		}
		
		String grpid = request.getParameter("grpid");
		if (isEmpty(phone)) {
			request.setAttribute("flag", rb.getString("msg27"));
			return mapping.findForward("default");
		}
		
		TbMsInfo msInfo = msmanager.getTBMsinfoby_msid(prifix_id+phone);
		if(msInfo == null){
			request.setAttribute("flag", rb.getString("msg5"));
			return mapping.findForward("default");
		}
		
		TbGrpInfo grpInfo = grpmanager.getGrpinfoby_grpid(grpid);
		if(grpInfo == null || !grpInfo.getMsid().equals(prifix_id+phone)){
			request.setAttribute("flag", rb.getString("msg28"));
			return mapping.findForward("default");
		}
		
		String msids = request.getParameter("msids");
		if (isEmpty(msids)) {
			request.setAttribute("flag", rb.getString("msg29"));
			return mapping.findForward("default");
		}
		
		String[] msIdArr = msids.split("\\|");
		for (int i = 0; i < msIdArr.length; i++) {
			try {
				//addMsGrp(msIdArr[i], grpid);
				String content = "NOTIFY|MSADDSGRP|"+msString(grpInfo.getGrpid().substring(10, 21))+"|"+grpid+"|"+grpInfo.getGrpname();
				creatParams(msIdArr[i], content);
			} catch (Exception e) {
				System.out.println("�ն����Ⱥ�����msID= "+prifix_id+phone+",grpID= "+grpid);
				e.printStackTrace();
			}
		}
		
		request.setAttribute("flag",true);
		return mapping.findForward("default");
	}
	
	private ActionForward msmoney(ActionMapping mapping,
			HttpServletRequest request,ResourceBundle rb) {
		String phone = request.getParameter("phone");
		if (isEmpty(phone)) {
			request.setAttribute("flag", rb.getString("msg26"));
			return mapping.findForward("default");
		}
		TbMsInfo msInfo = msmanager.getTBMsinfoby_msid(prifix_id+phone);
		if(msInfo == null){
			request.setAttribute("flag", rb.getString("msg5"));
			return mapping.findForward("default");
		}
		request.setAttribute("flag", msInfo.getMsMoney());
		return mapping.findForward("default");
	}

	private ActionForward getpayinfo(ActionMapping mapping,
			HttpServletRequest request,ResourceBundle rb) {
		String payType = request.getParameter("type");
		if(!payType.equals("0") && !payType.equals("1") && !payType.equals("2") && !payType.equals("3")){
			request.setAttribute("flag", rb.getString("msg31"));
			return mapping.findForward("default");
		}
		String phone = request.getParameter("phone");
		if (isEmpty(phone)) {
			request.setAttribute("flag", rb.getString("msg26"));
			return mapping.findForward("default");
		}
		TbMsInfo msInfo = msmanager.getTBMsinfoby_msid(prifix_id+phone);
		if(msInfo == null){
			request.setAttribute("flag", rb.getString("msg5"));
			return mapping.findForward("default");
		}
		//��ȡ�ʺų�ֵ��¼
		int apcount = 0;	//��ѯ��¼������
		apcount = apManager.findApCountByPage(msInfo.getMsId(),Integer.parseInt(payType));
		 // ��ȡÿҳ������
        int pageSize = 0;
        String pageSizeStr = request.getParameter("pageSize");
        if (pageSizeStr == null || pageSizeStr.trim().equals("")||pageSizeStr.trim().equals("0")) {
        	pageSize = 10;
        } else {
        	pageSize = Integer.parseInt(request.getParameter("pageSize"));
        }
     // ��ȡ��ҳ��
        int pageCount = (apcount + pageSize - 1) / pageSize;
        // ��ҳ��ȡ�õ�ǰҳ
        int pageNo;
        String pageNoStr = request.getParameter("pageNo");
        if (pageNoStr == null || pageNoStr.trim().equals("")||pageNoStr.trim().equals("0")) {
            pageNo = 1;
        } else {
            pageNo = Integer.parseInt(request.getParameter("pageNo"));
        }
        // �������ҳ�Ѿ����������ҳ
//        if (pageNo > pageCount) {
//            pageNo = pageCount;
//        }
//        // �������ҳС��һҳ
//        if (pageNo < 1) {
//            pageNo = 1;
//        }
//        request.setAttribute("pageSize",pageSize);
//        request.setAttribute("pageNo",pageNo);
//        request.setAttribute("pageCount",pageCount);
		List<TbAppPayInfo> apList = apManager.findApInfoByPage(pageNo,pageSize
				,msInfo.getMsId(),Integer.parseInt(payType));
//		if(apList == null){
//			request.setAttribute("flag", false);
//			return mapping.findForward("default");
//		}
		JSONSerializer jstring = new JSONSerializer();
		request.setAttribute("flag", jstring.serialize(apList));
		return mapping.findForward("default");
	}

	private ActionForward getmsidofapp(ActionMapping mapping,
			HttpServletRequest request,ResourceBundle rb) {
		String phone = request.getParameter("phone");
		if (isEmpty(phone)) {
			request.setAttribute("flag", rb.getString("msg26"));
			return mapping.findForward("default");
		}
		TbMsInfo msInfo = msmanager.getTBMsinfoby_msid(prifix_id+phone);
		if(msInfo == null){
			request.setAttribute("flag", rb.getString("msg5"));
			return mapping.findForward("default");
		}
		if(msInfo.getPhoneLogin().equals("") || msInfo.getPhoneLogin().equals(null)){
			request.setAttribute("flag", "false");
		}else{
			request.setAttribute("flag", msInfo.getPhoneLogin());
		}
		return mapping.findForward("default");
	}
	
	private ActionForward addFreeswitchUser(ActionMapping mapping,
			HttpServletRequest request,ResourceBundle rb) {
		String phone = request.getParameter("phone");
		if (isEmpty(phone)) {
			request.setAttribute("flag", rb.getString("msg26"));
			return mapping.findForward("default");
		}

		String name=request.getParameter("name");
		

		if (isEmpty(name)) {
			request.setAttribute("flag", rb.getString("msg32"));
			return mapping.findForward("default");
		}

		String directoryPath = FilePathConfig.getFreeswitch_Path()
				+ "conf/directory/default/";

		String autoLoadxml = FilePathConfig.getFreeswitch_Path()+"/bin/fs_cli.sh";
		
		File file = new File(directoryPath + phone + ".xml");
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
				request.setAttribute("flag", rb.getString("msg33"));
				return mapping.findForward("default");
			}
		} else {
			request.setAttribute("flag", rb.getString("msg5"));
			return mapping.findForward("default");
		}

		try {
//			FileWriter fileWritter = new FileWriter(file.getName(), true);
//			BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
//			bufferWritter.write("<include>");
//			bufferWritter.write("<user id=\""+phone+"\">");
//			bufferWritter.write("<params>");
//			bufferWritter.write("<param name=\"password\" value=\"$${default_password}\"/>");
//			bufferWritter.write("<param name=\"vm-password\" value=\""+phone+"\"/>");
//			bufferWritter.write("</params>");
//			bufferWritter.write("<variables>");
//			bufferWritter.write("<variable name=\"toll_allow\" value=\"domestic,international,local\"/>");
//			bufferWritter.write("<variable name=\"accountcode\" value=\""+phone+"\"/>");
//			bufferWritter.write("<variable name=\"user_context\" value=\"default\"/>");
//			bufferWritter.write("<variable name=\"effective_caller_id_name\" value=\""+name+"\"/>");
//			bufferWritter.write("<variable name=\"effective_caller_id_number\" value=\"penghb\"/>");
//			bufferWritter.write("<variable name=\"outbound_caller_id_name\" value=\"$${outbound_caller_name}\"/>");
//			bufferWritter.write("<variable name=\"outbound_caller_id_number\" value=\"$${outbound_caller_id}\"/>");
//			bufferWritter.write("<variable name=\"callgroup\" value=\"techsupport\"/>");
//			bufferWritter.write("</variables>");
//			bufferWritter.write("</user>");
//			bufferWritter.write("</include>");
//			
//			bufferWritter.close();

			
			StringBuffer sb = new StringBuffer();
			sb.append("<include>\n");
			sb.append("<user id=\""+phone+"\">\n");
			sb.append("<params>\n");
			sb.append("<param name=\"password\" value=\"$${default_password}\"/>\n");
			sb.append("<param name=\"vm-password\" value=\""+phone+"\"/>\n");
			sb.append("</params>\n");
			sb.append("<variables>\n");
			sb.append("<variable name=\"toll_allow\" value=\"domestic,international,local\"/>\n");
			sb.append("<variable name=\"accountcode\" value=\""+phone+"\"/>\n");
			sb.append("<variable name=\"user_context\" value=\"default\"/>\n");
			sb.append("<variable name=\"effective_caller_id_name\" value=\""+name+"\"/>\n");
			sb.append("<variable name=\"effective_caller_id_number\" value=\""+phone+"\"/>\n");
			sb.append("<variable name=\"outbound_caller_id_name\" value=\"$${outbound_caller_name}\"/>\n");
			sb.append("<variable name=\"outbound_caller_id_number\" value=\"$${outbound_caller_id}\"/>\n");
			sb.append("<variable name=\"callgroup\" value=\"techsupport\"/>\n");
			sb.append("</variables>\n");
			sb.append("</user>\n");
			sb.append("</include>\n");
			
			byte[] date = sb.toString().getBytes();
			
			OutputStream bos = new FileOutputStream(directoryPath + phone + ".xml");
			bos.write(date);
			bos.flush();
			bos.close();
			
			Runtime runtime = Runtime.getRuntime();
			runtime.exec(autoLoadxml);
		} catch (IOException e) {
			e.printStackTrace();
			file.delete();
			request.setAttribute("flag", rb.getString("msg34"));
			return mapping.findForward("default");
		}
		request.setAttribute("flag", "true");
		return mapping.findForward("default");
	}
	
	private ActionForward getServerAddress(ActionMapping mapping,
			HttpServletRequest request,ResourceBundle rb){
		String phone = request.getParameter("phone");
		if(isEmpty(phone)){
			request.setAttribute("flag", rb.getString("msg35"));
			return mapping.findForward("default");
		}
		
		String pwd = request.getParameter("pwd");
		
		String msid = prifix_id + phone;
		TbMsInfo msInfo = msmanager.getTBMsinfoby_msid(msid);
		
		if(msInfo==null){
			request.setAttribute("flag", rb.getString("msg36"));
			return mapping.findForward("default");
		}
		
		if(!msInfo.getPasswd().equals(pwd)){
			request.setAttribute("flag", rb.getString("msg37"));
			return mapping.findForward("default");
		}
		
		TbGrpMsListInfo grpMs = grpmsmanager.getGrp(msid);
		if(grpMs==null){
			request.setAttribute("flag", rb.getString("msg38"));
			return mapping.findForward("default");
		}
		
		TbGrpInfo grpInfo = grpmanager.getGrpinfoby_grpid(grpMs.getGrp_id());
		request.setAttribute("flag", "true,"+grpInfo.getServerIp()+",12030");
		return mapping.findForward("default");
		
	}
	
	private ActionForward updateGrpinfo(ActionMapping mapping,
			HttpServletRequest request,ResourceBundle rb){
		
		String phone = request.getParameter("phone");
		if(isEmpty(phone)){
			request.setAttribute("flag", rb.getString("msg35"));
			return mapping.findForward("default");
		}
		
		String grpid = request.getParameter("grpid");
		if(isEmpty(grpid)){
			request.setAttribute("flag", rb.getString("msg39"));
			return mapping.findForward("default");
		}
		
		String ms_id = prifix_id+phone;
		
		TbGrpInfo grpInfo = grpmanager.getGrpinfoby_grpid(grpid);
		if(grpInfo == null){
			request.setAttribute("flag", rb.getString("msg6"));
			return mapping.findForward("default");
		}
		
		if(!grpInfo.getMsid().equals(ms_id)){
			request.setAttribute("flag", rb.getString("msg40"));
			return mapping.findForward("default");
		}
		
		String name = request.getParameter("name");
			if(isEmpty(name)){
				request.setAttribute("flag", rb.getString("msg41"));
				return mapping.findForward("default");
			}
			
			grpInfo.setGrpname(name);
			grpmanager.update(grpInfo);
			
			request.setAttribute("flag", "true");
			return mapping.findForward("default");

	}
	
	private ActionForward tbPhone(ActionMapping mapping,
			HttpServletRequest request,ResourceBundle rb){
		String phones = request.getParameter("phones");
		if(isEmpty(phones)){
			request.setAttribute("flag", "");
			return mapping.findForward("default");
		}
		
		String[] phone = phones.split(",");
		TbMsInfo info = null;
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < phone.length; i++) {
			String msID = prifix_id+phone[i];
			
			info = msmanager.getTBMsinfoby_msid(msID);
			if(info != null){
				sb.append(phone[i]+",");
			}
		}
		
		request.setAttribute("flag", sb.toString());
		return mapping.findForward("default");
	}
	
	private ActionForward jsGrp(ActionMapping mapping,
			HttpServletRequest request,ResourceBundle rb){
		
		String phone = request.getParameter("phone");
		if(isEmpty(phone)){
			request.setAttribute("flag", rb.getString("msg35"));
			return mapping.findForward("default");
		}
		
		if(!isExist(phone)){
			request.setAttribute("flag", rb.getString("msg5"));
			return mapping.findForward("default");
		}
		
		String grpid = request.getParameter("grpid");
		if(isEmpty(grpid)){
			request.setAttribute("flag", rb.getString("msg42"));
			return mapping.findForward("default");
		}
		
		String msID21 = prifix_id+phone;
		
		TbGrpInfo grpInfo = grpmanager.getGrpinfoby_grpid(grpid);
		if(grpInfo == null){
			request.setAttribute("flag", rb.getString("msg6"));
			return mapping.findForward("default");
		}
		
		if(!grpInfo.getMsid().equals(msID21)){
			request.setAttribute("flag", rb.getString("msg43"));
			return mapping.findForward("default");
		}
		
		String defaultGrpId = prifix_grpid+phone;
		if(grpid.equals(defaultGrpId)){
			request.setAttribute("flag", rb.getString("msg44"));
			return mapping.findForward("default");
		}
		
		if(grpmanager.deleteGrp(grpid)){
			TbUserLog userLog=new TbUserLog();
			userLog.setUserId(0);
			userLog.setlDate(new Date());
			userLog.setlAddress(HRAddress.getIpAddr(request));
			userLog.setlType(6);
			userLog.setlContent("ɾ��Ⱥ��{"+grpid+"}");
			logManager.save(userLog);
			
			request.setAttribute("flag", "true");
			return mapping.findForward("default");
		}else{
			request.setAttribute("flag", rb.getString("msg45"));
			return mapping.findForward("default");
		}
		
	}
	
	private ActionForward tcGrp(ActionMapping mapping,
			HttpServletRequest request,ResourceBundle rb){
		
		String phone = request.getParameter("phone");
		if(isEmpty(phone)){
			request.setAttribute("flag", rb.getString("msg35"));
			return mapping.findForward("default");
		}
		
		if(!isExist(phone)){
			request.setAttribute("flag", rb.getString("msg5"));
			return mapping.findForward("default");
		}
		
		String grpid = request.getParameter("grpid");
		if(isEmpty(grpid)){
			request.setAttribute("flag", rb.getString("msg42"));
			return mapping.findForward("default");
		}
		
		String msID21 = prifix_id+phone;
		
		TbGrpInfo grpInfo = grpmanager.getGrpinfoby_grpid(grpid);
		if(grpInfo == null){
			request.setAttribute("flag", rb.getString("msg6"));
			return mapping.findForward("default");
		}
		
		if(grpInfo.getMsid().equals(msID21)){
			request.setAttribute("flag", rb.getString("msg46"));
			return mapping.findForward("default");
		}
		
		String[] str = new String[]{msID21+","+grpid};
		
		if(grpmsmanager.deleteGrpMs(str)){
			for (int i = 0; i < str.length; i++) {
				String mg=str[i];
				String [] mgs=mg.split(",");
				//��¼������־
				TbUserLog userLog=new TbUserLog();
				userLog.setUserId(0);
				userLog.setlDate(new Date());
				userLog.setlAddress(HRAddress.getIpAddr(request));
				userLog.setlType(8);
				userLog.setlContent("Ⱥ��:{ "+mgs[0]+" }�ն�:{ "+mgs[1]+" }ȡ����Ӧ��ϵ");
				logManager.save(userLog);
			}
			
			request.setAttribute("flag", "true");
			return mapping.findForward("default");
		}else{
			request.setAttribute("flag", rb.getString("msg47"));
			return mapping.findForward("default");
		}
	}
	
	private ActionForward scGrpMs(ActionMapping mapping,
			HttpServletRequest request,ResourceBundle rb){
		String phone = request.getParameter("phone");
		if(isEmpty(phone)){
			request.setAttribute("flag", rb.getString("msg35"));
			return mapping.findForward("default");
		}
		
		if(!isExist(phone)){
			request.setAttribute("flag", rb.getString("msg5"));
			return mapping.findForward("default");
		}
		
		String grpid = request.getParameter("grpid");
		if(isEmpty(grpid)){
			request.setAttribute("flag", rb.getString("msg42"));
			return mapping.findForward("default");
		}
		
		String targetms = request.getParameter("targetms");
		if(isEmpty(targetms) || !isExist(targetms)){
			request.setAttribute("flag", rb.getString("msg48"));
			return mapping.findForward("default");
		}
		
		//String msID21 = prifix_id+phone;
		String targetms21 = prifix_id+targetms;
		
		TbGrpInfo grpInfo = grpmanager.getGrpinfoby_grpid(grpid);
		if(grpInfo == null){
			request.setAttribute("flag", rb.getString("msg6"));
			return mapping.findForward("default");
		}
		
		if(grpInfo.getMsid().equals(targetms21)){
			request.setAttribute("flag", rb.getString("msg49"));
			return mapping.findForward("default");
		}
		
		String[] str = new String[]{targetms21+","+grpid};
		if(grpmsmanager.deleteGrpMs(str)){
			for (int i = 0; i < str.length; i++) {
				String mg=str[i];
				String [] mgs=mg.split(",");
				//��¼������־
				TbUserLog userLog=new TbUserLog();
				userLog.setUserId(0);
				userLog.setlDate(new Date());
				userLog.setlAddress(HRAddress.getIpAddr(request));
				userLog.setlType(8);
				userLog.setlContent("Ⱥ��:{ "+mgs[0]+" }�ն�:{ "+mgs[1]+" }ȡ����Ӧ��ϵ");
				logManager.save(userLog);
			}
			
			request.setAttribute("flag", "true");
			return mapping.findForward("default");
		}else{
			request.setAttribute("flag", rb.getString("msg47"));
			return mapping.findForward("default");
		}
		
	}
	
	private ActionForward cztjGrp(ActionMapping mapping,
			HttpServletRequest request,ResourceBundle rb){
		
		String msid = request.getParameter("msid");
		if(isEmpty(msid)){
			request.setAttribute("flag", rb.getString("msg50"));
			return mapping.findForward("default");
		}
		
		String grpid = request.getParameter("grpid");
		if(isEmpty(grpid)){
			request.setAttribute("flag", rb.getString("msg42"));
			return mapping.findForward("default");
		}
	
		if(!isExist(msid.substring(10, 21))){
			request.setAttribute("flag", rb.getString("msg5"));
			return mapping.findForward("default");
		}
		
		String grpID = prifix_grpid.substring(0, 8) + grpid;
		
		String grpPhone = prifix_id +request.getParameter("phone");//Ⱥ��
		TbGrpInfo grpInfo = grpmanager.getGrpinfoby_grpid(grpID);
		if(grpInfo == null){
			request.setAttribute("flag", rb.getString("msg6"));
			return mapping.findForward("default");
		}
		
		if(!grpInfo.getMsid().equals(grpPhone)){
			request.setAttribute("flag", rb.getString("msg51"));
			return mapping.findForward("default");
		}
//		if(!isExistGrp(grpID)){
//			request.setAttribute("flag", "Ⱥ�鲻����!");
//			return mapping.findForward("default");
//		}
		
		int mse =msmanager.getTBMsinfoby_msid(msid).getEpid();
		int grpe = grpmanager.getGrpinfoby_grpid(grpID).getEp_Id();
		if(mse != grpe){
			request.setAttribute("flag", rb.getString("msg52"));
			return mapping.findForward("default");
		}
		addMsGrp(msid.substring(10, 21), grpID);
		
		String grpname =grpmanager.getGrpinfoby_grpid(grpID).getGrpname();
		request.setAttribute("flag", "true,"+grpID+","+grpname);
		request.getSession().setAttribute("succeed", "OK");
		return mapping.findForward("default");
	}
	
	private ActionForward xjGrp(ActionMapping mapping,
			HttpServletRequest request,ResourceBundle rb){
		String phone = request.getParameter("phone");
		if(isEmpty(phone)){
			request.setAttribute("flag", rb.getString("msg35"));
			return mapping.findForward("default");
		}
		
		String grpname;
		try {
			grpname = request.getParameter("grpname");
		
			
			if(isEmpty(grpname)){
				request.setAttribute("flag", rb.getString("msg70"));
				return mapping.findForward("default");
			}
			String msid = prifix_id+phone;
			TbMsInfo info = msmanager.getTBMsinfoby_msid(msid);
			if(info==null){
				request.setAttribute("flag", rb.getString("msg5"));
				return mapping.findForward("default");
			}
			
			int count = 0;
			List grpList = grpmanager.findGrp_Info_byMsId(prifix_id+phone);
			if(grpList!=null){
				count = grpList.size();
			}
			
			if(count >= 3){
				request.setAttribute("flag", rb.getString("msg53"));
				return mapping.findForward("default");
			}else{
				String grpID = addGrp(phone, grpname,String.valueOf(info.getEpid()));
				addMsGrp(phone, grpID);
				request.setAttribute("flag", "true,"+grpID+","+grpname);
				return mapping.findForward("default");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("flag", "false");
		return mapping.findForward("default");
	}
	
	private ActionForward yqFriend(ActionMapping mapping,
			HttpServletRequest request,ResourceBundle rb){
		String phone = request.getParameter("phone");
		if(isEmpty(phone)){
			request.setAttribute("flag", rb.getString("msg35"));
			return mapping.findForward("default");
		}
		
		String msId = prifix_id+phone;
		String yqm = request.getParameter("yqm");
		if(isEmpty(phone)){
			request.setAttribute("flag", rb.getString("msg54"));
			return mapping.findForward("default");
		}
		
		String grpID = prifix_grpid.substring(0, 8) + yqm;
		System.out.println(grpID);
		TbGrpInfo grpInfo = grpmanager.getGrpinfoby_grpid(grpID);
		if(grpInfo==null){
			request.setAttribute("flag", rb.getString("msg54"));
			return mapping.findForward("default");
		}
		if(!grpInfo.getMsid().equals(msId)){
			request.setAttribute("flag", rb.getString("msg55"));
			return mapping.findForward("default");
		}
		
		String friend = request.getParameter("friend");
		if(isEmpty(friend)){
			request.setAttribute("flag", rb.getString("msg56"));
			return mapping.findForward("default");
		}
		TbMsInfo msInfo = msmanager.getTBMsinfoby_msid(prifix_id+phone);
		TbMsInfo msInfoFriend = msmanager.getTBMsinfoby_msid(prifix_id+friend);
		String appPath = FilePathConfig.getAPPPath();
		if(msInfoFriend == null){
			if(addMsId(friend, "123456", friend,String.valueOf(msInfo.getEpid()))){
				addMsGrp(friend, grpID);
			}else{
				request.setAttribute("flag", rb.getString("msg57"));
				return mapping.findForward("default");
			}
			//����Ĭ��Ⱥ��
			String friendGrp = addGrp(friend, friend,String.valueOf(msInfo.getEpid()));
			addMsGrp(friend, friendGrp);
			
			//String result = SendManager.sendSmsYqFriend(yqm, msInfo.getMsName(), appPath, friend);
			int s = SendManager.sendSmsYqFriend(yqm, msInfo.getMsName(), appPath, friend);
			
			if(s == 0){//0:���ͳɹ�
				System.out.println(friend);
				request.setAttribute("flag", "true");
				return mapping.findForward("default");
			}else{//1:����ʧ�� -1:�������쳣
				request.setAttribute("flag", rb.getString("msg58"));
				return mapping.findForward("default");
			}
			
		}else{
			//String result = SendManager.sendSmsYqFriend2(yqm, msInfo.getMsName(), appPath, friend);
			int s = SendManager.sendSmsYqFriend2(yqm, msInfo.getMsName(), appPath, friend);
			
			if(s == 0){//0:���ͳɹ�
				System.out.println(friend);
				request.setAttribute("flag", "true");
				return mapping.findForward("default");
			}else{//1:����ʧ�� -1:�������쳣
				request.setAttribute("flag", rb.getString("msg59"));
				return mapping.findForward("default");
			}
		}
		
//		String appPath = FilePathConfig.getAPPPath();
//		TbMsInfo msInfo = msmanager.getTBMsinfoby_msid(prifix_id+phone);
//		String result = sendSmsYqFriend(yqm, msInfo.getMsName(), appPath, friend);
//		int s = Integer.parseInt(result);
//		
//		if(s == 1){//1:���ͳɹ�
//			System.out.println(friend);
//			request.setAttribute("flag", "true");
//			return mapping.findForward("default");
//		}else{//0:����ʧ�� -1:�������쳣
//			request.setAttribute("flag", "��֤���ȡʧ�ܣ����Ժ�����!");
//			return mapping.findForward("default");
//		}
	}

	private ActionForward resetpwdgetcode(ActionMapping mapping,
			HttpServletRequest request,ResourceBundle rb){
		String phone = request.getParameter("phone");
		if(isEmpty(phone)){
			request.setAttribute("flag", rb.getString("msg35"));
			return mapping.findForward("default");
		}
		
		if(!isExist(phone)){
			request.setAttribute("flag", rb.getString("msg5"));
			return mapping.findForward("default");
		}
		
		int code = (int)(Math.random()*9000+1000);
		//String result = SendManager.sendSms(phone, code);
		
		int s = SendManager.sendSms(phone, code);
		if(s == 0){//1:���ͳɹ�
			MsInfoBean bean = new MsInfoBean(phone, "123456", null, code);
			checkCodeMap.put(phone, bean);
			
			request.setAttribute("flag", "true");
			return mapping.findForward("default");
		}else{//1:����ʧ�� -1:�������쳣
			request.setAttribute("flag", rb.getString("msg60"));
			return mapping.findForward("default");
		}
	}
	
	private ActionForward registergetcode(ActionMapping mapping,
			HttpServletRequest request,ResourceBundle rb){
		String phone = request.getParameter("phone");
		if(isEmpty(phone)){
			request.setAttribute("flag", rb.getString("msg35"));
			return mapping.findForward("default");
		}
		
		if(isExist(phone)){
			request.setAttribute("flag", rb.getString("msg61"));
			return mapping.findForward("default");
		}
		
		int code = (int)(Math.random()*9000+1000);
		//String result = sendSms(phone, code);
		
//		int s = SendManager.sendSms2(phone, code);
		int s = SendManager.sendSms(phone, code);
		if(s == 0){//1:���ͳɹ�
			MsInfoBean bean = new MsInfoBean(phone, null, null, code);
			checkCodeMap.put(phone, bean);
			
			System.out.println(phone+"��֤�룺"+code);
			
			request.setAttribute("flag", "true");
			return mapping.findForward("default");
		}else{//0:����ʧ�� -1:�������쳣
			request.setAttribute("flag", rb.getString("msg60"));
			return mapping.findForward("default");
		}
	}
	
	private ActionForward updateMsinfo(ActionMapping mapping,
			HttpServletRequest request,ResourceBundle rb){
		
		String phone = request.getParameter("phone");
		if(isEmpty(phone)){
			request.setAttribute("flag", rb.getString("msg35"));
			return mapping.findForward("default");
		}
		String name= request.getParameter("name");
			if(isEmpty(name)){
				request.setAttribute("flag", rb.getString("msg62"));
				return mapping.findForward("default");
			}
			
			String ms_id = prifix_id+phone;
			
			TbMsInfo info = msmanager.getTBMsinfoby_msid(ms_id);
			if(info==null){
				request.setAttribute("flag", rb.getString("msg5"));
				return mapping.findForward("default");
			}else{
				info.setMsName(name);
				List<TbMsInfo> msList = new ArrayList<TbMsInfo>();
				msList.add(info);
				msmanager.updateMs(msList);
				request.setAttribute("flag", "true");
			}
			
		return mapping.findForward("default");
	}

	private ActionForward getFamilyNumbers(ActionMapping mapping,
									   HttpServletRequest request,ResourceBundle rb){

		String phone = request.getParameter("phone");
		if(isEmpty(phone)){
			request.setAttribute("flag", rb.getString("msg35"));
			return mapping.findForward("default");
		}

		String ms_id = prifix_id+phone;

		TbMsInfo info = msmanager.getTBMsinfoby_msid(ms_id);
		if(info==null){
			request.setAttribute("flag", rb.getString("msg5"));
			return mapping.findForward("default");
		}else{
			String familyNumbers = info.getFamilyNumbers();
			request.setAttribute("flag", familyNumbers);
			/*if(isEmpty(familyNumbers)){
				request.setAttribute("flag", Collections.EMPTY_LIST);
			}else {
				request.setAttribute("flag", Arrays.asList(familyNumbers.split(",")));
			}*/

		}

		return mapping.findForward("default");
	}

	private ActionForward updateFamilyNumber(ActionMapping mapping,
											  HttpServletRequest request,ResourceBundle rb){

		String phone = request.getParameter("phone");
		if(isEmpty(phone)){
			request.setAttribute("flag", rb.getString("msg35"));
			return mapping.findForward("default");
		}

		String oldNumber = request.getParameter("oldNumber");
		if(isEmpty(oldNumber)){
			request.setAttribute("flag", rb.getString("msg76"));
			return mapping.findForward("default");
		}

		String newNumber = request.getParameter("newNumber");
		if(isEmpty(newNumber)){
			request.setAttribute("flag", rb.getString("msg77"));
			return mapping.findForward("default");
		}

		String ms_id = prifix_id+phone;

		TbMsInfo info = msmanager.getTBMsinfoby_msid(ms_id);
		if(info==null){
			request.setAttribute("flag", rb.getString("msg5"));
			return mapping.findForward("default");
		}

		String familyNumbers = info.getFamilyNumbers();
		if(familyNumbers.contains(oldNumber)){
			familyNumbers = familyNumbers.replace(oldNumber,newNumber);
		}else{
			request.setAttribute("flag", rb.getString("msg78"));
			return mapping.findForward("default");
		}

		info.setFamilyNumbers(familyNumbers);
		Boolean flag = msmanager.updateFamilyNumbers(info);
		request.setAttribute("flag", flag);

		return mapping.findForward("default");
	}

	private ActionForward updateFamilyNumbers(ActionMapping mapping,
									   HttpServletRequest request,ResourceBundle rb){

		String phone = request.getParameter("phone");
		if(isEmpty(phone)){
			request.setAttribute("flag", rb.getString("msg35"));
			return mapping.findForward("default");
		}

		String familyNumbers = request.getParameter("familyNumbers");
		if(isEmpty(familyNumbers)){
			request.setAttribute("flag", rb.getString("msg73"));
			return mapping.findForward("default");
		}

		String ms_id = prifix_id+phone;

		TbMsInfo info = msmanager.getTBMsinfoby_msid(ms_id);
		if(info==null){
			request.setAttribute("flag", rb.getString("msg5"));
			return mapping.findForward("default");
		}

		Integer roleType = info.getRoleType();
		if(roleType==null){
			roleType=0;
			info.setRoleType(roleType);
		}

		int familyNumSize = familyNumbers.split(",").length;
		if(roleType.toString().equals("0") && familyNumSize > 10){
			request.setAttribute("flag", rb.getString("msg74"));
			return mapping.findForward("default");
		}else if(roleType.toString().equals("1") && familyNumSize > 5){
			request.setAttribute("flag", rb.getString("msg75"));
			return mapping.findForward("default");
		}

		info.setFamilyNumbers(familyNumbers);
		msmanager.updateFamilyNumbers(info);
		request.setAttribute("flag", "true");

		return mapping.findForward("default");
	}


//	private ActionForward resetpwdCheckcode(ActionMapping mapping,
//			HttpServletRequest request) {
//		String phone = request.getParameter("phone");
//		if(isEmpty(phone)){
//			request.setAttribute("flag", "�ֻ��������!");
//			return mapping.findForward("default");
//		}
//		String code = request.getParameter("code");
//		if(isEmpty(code)){
//			request.setAttribute("flag", "У�������!");
//			return mapping.findForward("default");
//		}
//		
//		MsInfoBean bean = checkCodeMap.get(phone);
//		int checkCode = Integer.parseInt(code);
//		
//		if(bean==null){
//			request.setAttribute("flag", "У������ʧЧ!");
//			return mapping.findForward("default");
//		}else{
//			if(bean.getCheckcode() == checkCode){
//				if(updatePwd(phone, bean.getPwd())){
//					request.setAttribute("flag", "true");
//				} else {	//ʧ��
//					request.setAttribute("flag", "��������ʧ�ܣ����Ժ�����!");
//				}
//				
//				checkCodeMap.remove(phone);
//				return mapping.findForward("default");
//			}else{
//				request.setAttribute("flag", "У�������!");
//				return mapping.findForward("default");
//			}
//		}
//	}

	private ActionForward resetPwd(ActionMapping mapping,
			HttpServletRequest request,ResourceBundle rb) {
		String phone = request.getParameter("phone");
		if(isEmpty(phone)){
			request.setAttribute("flag", rb.getString("msg35"));
			return mapping.findForward("default");
		}
		String pwd = request.getParameter("pwd");
		if(isEmpty(pwd)){
			request.setAttribute("flag", rb.getString("msg63"));
			return mapping.findForward("default");
		}
		String code = request.getParameter("code");
		if(isEmpty(code)){
			request.setAttribute("flag", rb.getString("msg64"));
			return mapping.findForward("default");
		}
		
		if(isExist(phone)){
			
			MsInfoBean bean = checkCodeMap.get(phone);
			
			if(bean == null){
				request.setAttribute("flag", rb.getString("msg65"));
				return mapping.findForward("default");
			}else{
				int codetemp = Integer.parseInt(code);
				if(bean.getCheckcode() == codetemp){
					if(updatePwd(phone, pwd)){
						request.setAttribute("flag", "true");
					} else {	//ʧ��
						request.setAttribute("flag", rb.getString("msg66"));
					}
					
					checkCodeMap.remove(phone);
					return mapping.findForward("default");
				}else{
					request.setAttribute("flag", rb.getString("msg64"));
					return mapping.findForward("default");
				}
			}
		}else{
			request.setAttribute("flag", rb.getString("msg5"));
			return mapping.findForward("default");
		}
	}

//	private ActionForward registerCheckcode(ActionMapping mapping,
//			HttpServletRequest request) {
//		String phone = request.getParameter("phone");
//		if(isEmpty(phone)){
//			request.setAttribute("flag", "�ֻ��������!");
//			return mapping.findForward("default");
//		}
//		String code = request.getParameter("code");
//		if(isEmpty(code)){
//			request.setAttribute("flag", "У�������!");
//			return mapping.findForward("default");
//		}
//		
//		MsInfoBean bean = checkCodeMap.get(phone);
//		int checkCode = Integer.parseInt(code);
//		
//		if(bean==null){
//			request.setAttribute("flag", "У������ʧЧ��������ע��!");
//			return mapping.findForward("default");
//		}else{
//			if(bean.getCheckcode() == checkCode){
//				if(addMsId(phone, bean.getPwd(), bean.getName())){	//���ID�³ɹ�
//					if(bean.getYqm().equals("0")){
//						if(!isExistGrp(phone)){
//							addGrp(phone);
//							addMsGrp(phone, phone);
//						}
//						
//					}else{
//						if(!isExist(bean.getYqm())){
//							request.setAttribute("flag", "�����˲�����,��ȷ��!");
//							return mapping.findForward("default");
//						}else{
//							addMsGrp(phone, bean.getYqm());
//						}
//					}
//					request.setAttribute("flag", "true");
//				} else {	//���ʧ��
//					request.setAttribute("flag", "�ʺ��Ѵ���!");
//				}
//				
//				checkCodeMap.remove(phone);
//				return mapping.findForward("default");
//			}else{
//				request.setAttribute("flag", "У�������!");
//				return mapping.findForward("default");
//			}
//		}
//	}

	
	private void addMsGrp(String phone,String grpID){
		String ms_id = prifix_id+phone;
		
		grpmsmanager.createGrpMsInfo(grpID, ms_id);
	}
	
	private boolean isExistGrp(String grpId){
		TbGrpInfo grpInfo = grpmanager.getGrpinfoby_grpid(grpId);
		if(grpInfo == null){
			return false;
		}else{
			return true;
		}
	}
	
	private String addGrp(String phone, String grpName,String epId){
		String grpId8 = prifix_grpid.substring(0, 8);
		String ms_id = prifix_id+phone;
		
		String grpID = "";
		for(int i=0;i<99;i++){
			String temp = "";
			if(i<10){
				temp = "0"+i;
			}else{
				temp = i+"";
			}
			
			grpID = grpId8 + temp + phone;
			if(!isExistGrp(grpID)){
				break;
			}
		}
		grpmanager.createGrp(grpID, grpName, ms_id, 2, 1, 
				3, epId, 1, "-1","1");
//		grpmanager.createGrp(grpID, grpName, ms_id, 2, 1, 
//				3, yht_enterpriseId, 1, "-1","1");
		
		return grpID;
	}
	
	private ActionForward registerMs(ActionMapping mapping, HttpServletRequest request,ResourceBundle rb) {
		String phone = request.getParameter("phone");
		if(isEmpty(phone)){
			request.setAttribute("flag", rb.getString("msg35"));
			return mapping.findForward("default");
		}
		
		String pwd = request.getParameter("pwd");
		if(isEmpty(pwd)){
			request.setAttribute("flag", rb.getString("msg37"));
			return mapping.findForward("default");
		}
		System.out.println(request.getCharacterEncoding());
		String name=request.getParameter("name");
		System.out.println(name);
//		try {
//			name = new String(request.getParameter("name").getBytes(
//					"ISO-8859-1"), "gbk");
//		} catch (UnsupportedEncodingException e1) {
//			e1.printStackTrace();
//			request.setAttribute("flag", "��������!");
//			return mapping.findForward("default");
//		}
			
			if(isEmpty(name)){
				request.setAttribute("flag", rb.getString("msg62"));
				return mapping.findForward("default");
			}
			
			String yqm = request.getParameter("yqm");
			if(isEmpty(yqm)){
//				request.setAttribute("flag", "���������!");
//				return mapping.findForward("default");
				yqm = "0";
			}
			
			if(!yqm.equals("0") && !isExist(yqm.substring(2))){
				request.setAttribute("flag", rb.getString("msg67"));
				return mapping.findForward("default");
			}
			
			if(!yqm.equals("0") && !isExistGrp(prifix_grpid.substring(0, 8) + yqm)){
				request.setAttribute("flag", rb.getString("msg55"));
				return mapping.findForward("default");
			}
			
			String code = request.getParameter("code");
			if(isEmpty(code)){
				request.setAttribute("flag", rb.getString("msg64"));
				return mapping.findForward("default");
			}

			String roleType = request.getParameter("roleType");
			if(isEmpty(roleType)){
				request.setAttribute("flag", rb.getString("msg72"));
				return mapping.findForward("default");
			}

			String familyNumbers = request.getParameter("familyNumbers");
			if(isEmpty(familyNumbers)){
				request.setAttribute("flag", rb.getString("msg73"));
				return mapping.findForward("default");
			}

			int familyNumSize = familyNumbers.split(",").length;
			if(roleType.equals("0") && familyNumSize > 10){
				request.setAttribute("flag", rb.getString("msg74"));
				return mapping.findForward("default");
			}else if(roleType.equals("1") && familyNumSize > 5){
				request.setAttribute("flag", rb.getString("msg75"));
				return mapping.findForward("default");
			}

			if(isExist(phone)){
				request.setAttribute("flag", rb.getString("msg71"));
				return mapping.findForward("default");
			}else{
				MsInfoBean bean = checkCodeMap.get(phone);
				if(bean == null){
					request.setAttribute("flag", rb.getString("msg65"));
					return mapping.findForward("default");
				}else{
					int codetemp = Integer.parseInt(code);
					if(bean.getCheckcode() == codetemp){
						String epId = yht_enterpriseId;
						if(!yqm.equals("0")){
							String msid = prifix_id+yqm;
							TbMsInfo info = msmanager.getTBMsinfoby_msid(msid);
							epId =String.valueOf(info.getEpid());
						}
						if(addMsId(phone, pwd, name,epId,roleType,familyNumbers)){	//���ID�³ɹ�
							if(yqm.equals("0")){
								if(!isExistGrp(prifix_grpid + phone)){
									String grpName = request.getParameter("grpName");
									if(isEmpty(grpName)){
										grpName = phone;
									}
									
									String grpID = addGrp(phone, grpName,epId);
									addMsGrp(phone, grpID);
								}
							}else{
								
								addMsGrp(phone, prifix_grpid.substring(0, 8) + yqm);
							}
							request.setAttribute("flag", "true");
						} else {	//���ʧ��
							request.setAttribute("flag", rb.getString("msg71"));
						}
						
						checkCodeMap.remove(phone);
						return mapping.findForward("default");
					}else{
						request.setAttribute("flag", rb.getString("msg68"));
						return mapping.findForward("default");
					}
				}
			}
	}
	
	private boolean updatePwd(String phone,String pwd){
		String msid = prifix_id+phone;
		return msmanager.updatePwd(msid, pwd);
	}
	
//	private String sendSms(String phone, int code){
//		SendServiceSoapProxy proxy = new SendServiceSoapProxy();
//		String result;
//		try {
////			result = proxy.sendSmsByString("������ͨ", "BDHT", "+FJMm4ybswQRfIOFklQ+Xw==", 
////					"������ͨ��վע��", phone, "�𾴵��û�������ע����֤���ǣ�"+code+"������֤��10������ʧЧ��");
//			result = proxy.sendSmsByString("�º���Ϣ", "DHXX", "+FJMm4ybswQRfIOFklQ+Xw==", "�º���վע��", phone, "�𾴵��û�������ע����֤���ǣ�"+code+"������֤��10������ʧЧ��");
//			return result;
//		} catch (RemoteException e) {
//			e.printStackTrace();
//			return "0";
//		}
//	}
//	
//	private String sendSmsYqFriend(String yqm,String name, String address,String friend){
//		SendServiceSoapProxy proxy = new SendServiceSoapProxy();
//		String result;
//		try {
////			result = proxy.sendSmsByString("������ͨ", "BDHT", "+FJMm4ybswQRfIOFklQ+Xw==", 
////					"������ͨ��վע��", friend, 
//			result = proxy.sendSmsByString("�º���Ϣ", "DHXX", "+FJMm4ybswQRfIOFklQ+Xw==", 
//					"�º���վע��", friend, 
//					"���ã����ĺ��ѡ�"+name+"��������������ѵ绰���Խ��飬���ص�ַ��"+address+" ,��¼�ʺţ�"+friend
//					+" ,��¼���룺123465");
//			return result;
//		} catch (RemoteException e) {
//			e.printStackTrace();
//			return "0";
//		}
//		
//	}
//	
//	private String sendSmsYqFriend2(String yqm,String name, String address,String friend){
//		SendServiceSoapProxy proxy = new SendServiceSoapProxy();
//		String result;
//		try {
////			result = proxy.sendSmsByString("������ͨ", "BDHT", "+FJMm4ybswQRfIOFklQ+Xw==", 
////					"������ͨ��վע��", friend, 
//			result = proxy.sendSmsByString("�º���Ϣ", "DHXX", "+FJMm4ybswQRfIOFklQ+Xw==", 
//					"�º���վע��", friend, 
//					"���ã����ĺ��ѡ�"+name+"��������������ѵ绰���Խ��飬���ص�ַ��"+address+" ,���Ϊ��"+yqm);
//			return result;
//		} catch (RemoteException e) {
//			e.printStackTrace();
//			return "0";
//		}
//		
//	}
	
	private boolean isExist(String phone){
		String msid = prifix_id+phone;
		TbMsInfo info = msmanager.getTBMsinfoby_msid(msid);

        if (info == null) {
        	return false;
        }else{
        	return true;
        }
	}

	private boolean addMsId(String phone,String pwd,String name,String epId){
		String msid = prifix_id+phone;
		msmanager.createMs(msid, 1, name, pwd, 0, 1,
				epId/**��ҵID��Ĭ���и��º���ҵ��**/, "3", phone, null, 1, 0,
				0, "0", "0", "1", null,"12",-1);
//        msmanager.createMs(msid, 1, name, pwd, 0, 1,
//        		yht_enterpriseId/**��ҵID��Ĭ���и��º���ҵ��**/, "3", phone, null, 1, 0,
//                0, "0", "0", "1", null,"12",-1);
		String content = "";

		content = "����ն�[" + msid + "]";

		//��¼������־
		TbUserLog userLog = new TbUserLog();
		userLog.setUserId(Integer.parseInt("0"));
		userLog.setlDate(new Date());
		userLog.setlAddress("127.0.0.1");
		userLog.setlType(1);
		userLog.setlContent(content);
		logManager.save(userLog);
		return true;
	}

	private boolean addMsId(String phone,String pwd,String name,String epId,String roleType,String familyNumbers){
		String msid = prifix_id+phone;
		msmanager.createMs(msid, 1, name, pwd, 0, 1,
        		epId/**��ҵID��Ĭ���и��º���ҵ��**/, "3", phone, null, 1, 0,
                0, "0", "0", "1", null,"12",-1,roleType,familyNumbers);
//        msmanager.createMs(msid, 1, name, pwd, 0, 1,
//        		yht_enterpriseId/**��ҵID��Ĭ���и��º���ҵ��**/, "3", phone, null, 1, 0,
//                0, "0", "0", "1", null,"12",-1);
        String content = "";
       
        content = "����ն�[" + msid + "]";
        
        //��¼������־
        TbUserLog userLog = new TbUserLog();
        userLog.setUserId(Integer.parseInt("0"));
        userLog.setlDate(new Date());
        userLog.setlAddress("127.0.0.1");
        userLog.setlType(1);
        userLog.setlContent(content);
        logManager.save(userLog);
        return true;
	}
	
	private boolean isEmpty(String par){
		if(par == null || par.equals("")){
			return true;
		}else{
			return false;
		}
	}
	/**
	 * ��װ�ն��ַ������ն�ID|�ն˱�����
	 * @param phone��11λ�ֻ��ţ�
	 * @return
	 */
	private String msString(String phone){
		String msid = prifix_id+phone;
		TbMsInfo ms = msmanager.getTBMsinfoby_msid(msid);
		if(ms==null){
			return null;
		}else{
			return ms.getMsId()+"|"+ms.getMsName();
		}
	}
	/**
	 * ������Ϣ֪ͨ��¼
	 * @return
	 */
	private boolean creatParams(String phone,String content){
		String msid = prifix_id+phone;
		String c = null;
		if(content!=null & !content.equals("")){
			String[] cs = content.split("\\|");
			if(cs.length>1){
				c = cs[0]+"|"+cs[1];
			}else{
				c=cs[0];
			}
		}
		
		TbParamsInfo par = new TbParamsInfo();
		par.setContent(content);
		par.setCount(0);
		par.setCrateTime(new Date());
		par.setOperator("-1");
		if("NOTIFY|MSREQINGRP".equals(c) || "NOTIFY|MSAGRREJGRP".equals(c) || "NOTIFY|MSTERMGRP".equals(c) ||
		   "NOTIFY|MSADDGRPERR".equals(c) || "NOTIFY|MSREMGRP".equals(c) || "NOTIFY|MSTERMTGRP".equals(c) ||
		   "NOTIFY|MSREMTGRP".equals(c) || "NOTIFY|PAYMSG".equals(c) || "NOTIFY|MSADDSGRP".equals(c) ||
		   "NOTIFY|MSMENMBERGRP".equals(c)){
			par.setType(111);
		}else{
			par.setType(3);
		}
		
		par.setFlag(0);
		par.setMsid(msid);
		
		String id = paramsManager.add(par);
		if(id == null || id.equals("")){
			return false;
		}else{
			return true;
		}
	}
	/**
	 * ֧�����첽�������ݴ���
	 * @param apInfo
	 * @return
	 */
	public boolean appPayNotify(TbAppPayInfo apInfo){
		TbMsInfo msinfo = msmanager.getTBMsinfoby_msid(apInfo.getPhoneOrMs());
		if(msinfo==null){
			System.out.println("��ֵ���׺ţ�"+apInfo.getTradeNo()+"  TSCWEBϵͳ�����ڳ�ֵ���ն˺��룡");
			return false;
		}
		Double oldmoney = Double.parseDouble(msinfo.getMsMoney());
		Double newmoney = oldmoney + Double.parseDouble(apInfo.getPayMoney());
		msinfo.setMsMoney(newmoney+"");
		boolean apb = apManager.appPay(apInfo, msinfo);
		if(!apb){
			System.out.println("��ֵ���׺ţ�"+apInfo.getTradeNo()+"  TSCWEBϵͳ��ֵʧ�ܣ�");
			return false;
		}else{
			System.out.println("��ֵ���׺ţ�"+apInfo.getTradeNo()+"  TSCWEBϵͳ��ֵ�ɹ���");
			payMessage(apInfo);
			return true;
		}
	}

	/**
	 * ��ӳ�ֵ������Ϣ����TSC��ȡ������Ϣ���͵�app��
	 */
	public void payMessage(TbAppPayInfo apInfo){
		String phone = apInfo.getPhoneOrMs().substring(10);
		String payPhone = apInfo.getPayer().substring(10);
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String d = sdf.format(apInfo.getSuccessTime());
		String content ="NOTIFY|PAYMSG|"+msString(payPhone)+"|"+msString(phone)+"|"+apInfo.getPayNum()+"|"+d;
		if(creatParams(phone,content)){
			System.out.println("��ֵ��Ϣ���ͳɹ���");
		}else{
			System.out.println("��ֵ��Ϣ����ʧ�ܣ�");
		}
	}
	
	/**
	 * Ⱥ��ͬ���Ⱥ��Ϣ֪ͨ�նˣ���TSC��ȡ������Ϣ���͵�app��
	 */
	public void cztjgprMessage(HttpServletRequest request){
		String msid = request.getParameter("msid");
		String phone =msid.substring(10, 21);    //�����˵绰����
		String grpid = request.getParameter("grpid");
		String grpID = prifix_grpid.substring(0, 8) + grpid;
		String grpname =grpmanager.getGrpinfoby_grpid(grpID).getGrpname();
		String content = "NOTIFY|MSAGRREJGRP|"+msString(phone)+"|"+grpID+"|"+grpname+"|OK";
		if(creatParams(phone,content)){
			System.out.println("Ⱥ��ͬ����Ϣ���ͳɹ�");
			System.out.println("content:"+content);
		}else{
			System.out.println("Ⱥ��ͬ����Ϣ����ʧ��");
		}
	}
	/**
	 * �ն�ͬ��Ⱥ�������Ⱥ����TSC��ȡ������Ϣ���͵�app��
	 */
	private ActionForward mstyjq(ActionMapping mapping,
			HttpServletRequest request,ResourceBundle rb){
		
		String msid = request.getParameter("msid");
		String grpid = request.getParameter("grpid");
		if(!isExist(msid.substring(10, 21))){
			request.setAttribute("flag", rb.getString("msg5"));
			return mapping.findForward("default");
		}
		
		String grpID = prifix_grpid.substring(0, 8) + grpid;

		TbGrpInfo grpInfo = grpmanager.getGrpinfoby_grpid(grpID);
		if(grpInfo == null){
			request.setAttribute("flag", rb.getString("msg6"));
			return mapping.findForward("default");
		}
		addMsGrp(msid.substring(10, 21), grpID);
		
		String grpname =grpmanager.getGrpinfoby_grpid(grpID).getGrpname();
		request.setAttribute("flag", "true,"+grpID+","+grpname);
		
		request.getSession().setAttribute("succeed", "OK");
		return mapping.findForward("default");
	}
	/**
	 * �ն�ͬ���Ⱥ֪ͨȺ������TSC��ȡ������Ϣ���͵�app��
	 */
	public void mstyjqMessage(HttpServletRequest request){ 
		String msid = request.getParameter("msid");
		String grpid = request.getParameter("grpid");
		String grpID = prifix_grpid.substring(0, 8) + grpid;
		TbGrpInfo grpInfo = grpmanager.getGrpinfoby_grpid(grpID);
		String s = grpInfo.getMsid();
		String phone =s.substring(10, 21);    //Ⱥ��
		String grpname =grpInfo.getGrpname();
		
		String content = "NOTIFY|MSMENMBERGRP|"+msString(msid.substring(10, 21))+"|"+grpID+"|"+grpname+"|OK";
		if(creatParams(phone,content)){
			System.out.println("�ն�ͬ����Ϣ���ͳɹ�");
			System.out.println("content:"+content);
		}else{
			System.out.println("�ն�ͬ����Ϣ����ʧ��");
		}
	}

}