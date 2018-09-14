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
			// 如果没有就接收到参数默认显示全部类型
			if (request.getParameter("CMD") == null || request.getParameter("CMD").equals("")) {
				cmd = "success";
			} else {
				cmd = request.getParameter("CMD");
			}
			
			//获取用户角色
			int role_id =Integer.parseInt(request.getSession().getAttribute("roleId")+"");
			
			if (cmd.equals("money_czjsp")) { // 企业,代理商登录充值页面
				actionforward = ep_or_angent_money_czjsp(mapping, form, request, response,
						role_id,user.getAgent_Id().toString(),user.getEp_Id().toString());
			}
			if (cmd.equals("epangent_money_cz")) { // 代理商 企业登录充值页面充值操作执行
				actionforward = epangent_money_cz(mapping, form, request, response,
						role_id,user.getAgent_Id().toString(),user.getEp_Id().toString(),
						user);
			}
			
			
			if (cmd.equals("money_listinfojsp")) { // 企业费用管理页面
				actionforward = money_listinfojsp(mapping, form, request, response,
						role_id,user.getAgent_Id().toString(),user.getEp_Id().toString());
			}
			if (cmd.equals("money_infoczjsp")) { // 企业费用管理页面充值操作执行（跳转到企业充值信息页面）
				actionforward = money_infoczjsp(mapping, form, request, response,
						role_id,user.getAgent_Id().toString(),user.getEp_Id().toString());
			}
			if (cmd.equals("money_infocz")) { // 企业费用管理充值页面充值操作执行
				actionforward = money_infocz(mapping, form, request, response,
						role_id,user.getAgent_Id().toString(),user.getEp_Id().toString(),user);
			}
			if (cmd.equals("money_infoczep")) { // 企业费用管理充值页面充值操作执行
				actionforward = money_infoczep(mapping, form, request, response,role_id,user);
			}
			
			if (cmd.equals("money_listinfo_ajsp")) { // 代理商费用管理充值页面充值操作执行
				actionforward = money_listinfo_ajsp(mapping, form, request, response,
						role_id,user.getAgent_Id().toString(),user.getEp_Id().toString());
			}
			if (cmd.equals("money_info_cza_jsp")) { // 代理商费用管理页面充值操作执行（跳转到代理商充值信息页面）
				actionforward = money_info_cza_jsp(mapping, form, request, response,
						role_id,user.getAgent_Id().toString(),user.getEp_Id().toString());
			}
			if (cmd.equals("money_infocza")) { // 代理商费用管理充值页面充值操作执行
				actionforward = money_infocza(mapping, form, request, response,user);
			}
			
			if (cmd.equals("ms_money_listinfojsp")) { // 终端套餐费用list页面
				actionforward = ms_money_listinfojsp(mapping, form, request, response,
						role_id,user.getAgent_Id().toString(),user.getEp_Id().toString());
			}
			if (cmd.equals("ms_money_plczjsp")) { // 终端套餐批量页面
				actionforward = ms_money_plczjsp(mapping, form, request, response,
						role_id,user.getAgent_Id().toString(),user.getEp_Id().toString());
			}
			if (cmd.equals("ms_money_infojsp")) { // 终端套餐费用list页面充值操作执行（跳转到终端充值信息页面）
				actionforward = ms_money_infojsp(mapping, form, request, response,
						role_id,user.getAgent_Id().toString(),user.getEp_Id().toString());
			}
			if (cmd.equals("ms_money_cz")) { // 终端充值页面充值操作执行
				actionforward = ms_money_cz(mapping, form, request, response,
						role_id,user.getAgent_Id().toString(),user.getEp_Id().toString(),user);
			}
			if (cmd.equals("ms_money_plcz")) { // 终端批量充值页面批量充值操作执行
				actionforward = ms_money_plcz(mapping, form, request, response,
						role_id,user.getAgent_Id().toString(),user.getEp_Id().toString(),user);
			}
			if (cmd.equals("money_infoczmslist")) { // 终端费用管理充值页面充值操作执行
				actionforward = money_infoczmslist(mapping, form, request, response,user);
			}
			
			if (cmd.equals("moneylog")) { // 终端费用管理充值页面充值操作执行
				actionforward = moneylog(mapping, form, request, response,role_id,user.getAgent_Id().toString(),user.getEp_Id().toString(),user);
			}
			if (cmd.equals("apppaylog")) { // 查询app终端充值信息记录list
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
	 * 充值记录查询
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
		String mjsp = Money(role_id, agentid, epId);//余额 
		request.setAttribute("money",mjsp );//传回jsp页面
		
		String cmd = request.getParameter("CMD");
		String userName =request.getParameter("uname");//充值人名
		if (userName == null || (userName.trim()).equals("")) {
			userName = "";
		}
		String dateStart = request.getParameter("date1");//查询开始时间
		if (dateStart == null) {
			dateStart = "";
		}
		String dateEnd = request.getParameter("date2");//查询结束时间
		if (dateEnd == null) {
			dateEnd = "";
		}
		
		int ptype = 0 ;//充值方式
		if(request.getParameter("ptype") != null){
			ptype = Integer.parseInt(request.getParameter("ptype"));
		}
		
		String pay_num = request.getParameter("pay_num");//
		String teadeNo = request.getParameter("teadeNo");//交易号
		String orderNo = request.getParameter("orderNo");//订单号
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
		// 获取每页的条数
		int pageSize = 12;
		// 获取总页数
		int pageCount = (mlCount + pageSize - 1) / pageSize;
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
		request.setAttribute("mlCount", mlCount);
		// 返回每页面显示条数
		request.setAttribute("pageSize", pageSize);
		// 返回当前页
		request.setAttribute("currentPage", pageNo);
		// 设置返回的命令字
		request.setAttribute("CMD", cmd);
//
		// 返回的总页数
		request.setAttribute("pageCount", pageCount);
		
		request.setAttribute("uname",userName );
		request.setAttribute("date1",dateStart );
		request.setAttribute("date2",dateEnd );
		
		// 返回用户单位信息
		List<TbEnterpriseInfo> list = null;
		
		list = moneyManager.getMoneyLogListByPage(pageNo, pageSize, role_id, user.getUserId(), userName, dateStart, dateEnd, ptype, pay_num, teadeNo, orderNo);
		
		request.setAttribute("mlList",list );
		
		return mapping.findForward("moneylog_listjsp");
	}

	/**
	 * 给终端充值(管理员执行)
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
		
		String pfId = request.getParameter("pfType");//充值的套餐id
		String ms_id[] = request.getParameterValues("selectmsid");//批量充值的id
		String moneydiscription = request.getParameter("moneydiscription");//充值申请描述
		String epName = request.getParameter("ep");//企业id
		
		String msidsb = new String();
		msidsb += epmanger.getEpinfo_byepid(epName).getEp_Name()+"："+ms_id.length+"个终端"+",";
		for(String s:ms_id){
			msidsb += s+",";
		}
		String str =new String(msidsb);
		
		
		TbPFInfo pfInfo = pfManager.findPFInfoById(pfId); //套餐
//		List<TbMsInfo> msListInfo = new ArrayList<TbMsInfo>();//终端list
//		for(int i = 0 ; i< ms_id.length;i++){
//			TbMsInfo msInfo =new TbMsInfo();
//			msInfo = msmanager.getTBMsinfoby_msid(ms_id[i]);//终端
//			msListInfo.add(msInfo);
//		}
		
		Double deMoney = Double.parseDouble(pfInfo.getPfHow()) * ms_id.length;
		//提交申请
		
		TbCheckMInfo checkInfo = new TbCheckMInfo();
		checkInfo.setProposer(user);//申请人(登录用户)
		checkInfo.setProDescription(moneydiscription);//申请描述
		checkInfo.setProStatus(0);//未审核状态
		checkInfo.setResMoney(deMoney.toString());//申请金额
		checkInfo.setProTime(new Date());//申请时间
		checkInfo.setResId(str);//充值帐号id
		//checkInfo.setResRole(new TbRoleInfo());//充值帐号角色（终端角色）
		Boolean check = moneyManager.addCheck(checkInfo);
		if(check){
			String print ="充值申请执行成功！";
			String resultmessage="充值申请执行成功";
			showMessage(request, print, resultmessage);
		}
		
		return mapping.findForward("cmlistinfoms");//跳转到申请列表页
	}
	
	/**
	 * 给企业充值(管理员执行)
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
		String epid = request.getParameter("epid");//充值的	企业id	
		String newMoney = request.getParameter("moneyhow");//页面输入金额
		String moneydiscription = request.getParameter("moneydiscription");//充值申请描述
		//提交申请
		
		TbCheckMInfo checkInfo = new TbCheckMInfo();
		checkInfo.setProposer(user);//申请人(登录用户)
		checkInfo.setProDescription(moneydiscription);//申请描述
		checkInfo.setProStatus(0);//未审核状态
		checkInfo.setResMoney(newMoney);//申请金额
		checkInfo.setProTime(new Date());//申请时间
		checkInfo.setResId(epid);//充值帐号id
		//区分代理企业和直属企业
		String r_id = "3";
		if(epmanger.getEpinfo_byepid(epid).getAgent_Id() == -1){
			r_id = "4";
		}else{
			r_id = "3";
		}
		checkInfo.setResRole(rolemanager.getRoleInfoby_roleid(r_id));//充值帐号角色（企业角色）
		Boolean check = moneyManager.addCheck(checkInfo);
		if(check){
			String print ="充值申请执行成功！";
			String resultmessage="充值申请执行成功";
			showMessage(request, print, resultmessage);
		}
		return mapping.findForward("cmlistinfoep");//跳转到申请列表页
	}
	
	/**
	 * 给代理商充值(管理员执行)
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
		//判断余额是否足够
//		Double checkMoney=0.00;
//		if(roleId == 3){//企业角色
//			TbEnterpriseInfo ep = epmanger.getEpinfo_byepid(epId);
//			checkMoney = Double.valueOf(ep.getEp_Money());
//		}else{//代理商角色
//			if(roleId == 1 || roleId == 35){//管理员，超级管理员
//				agentid = "-1";//总部
//			}
//			TbAgentInfo a = agentmanger.getAgent_ByAgentID(agentid);
//			checkMoney = Double.valueOf(a.getAgent_Money());
//		}
//		if(checkMoney<0 || checkMoney == 0){
//			String print ="余额不足！";
//			String resultmessage="余额不足，无法操作！";
//			showMessage(request, print, resultmessage);
//			return  ep_or_angent_money_czjsp(mapping, form, request, response,
//					roleId,agentid,epId);
//		}
	
	
		String aid = request.getParameter("aid");//充值的代理商id	
		String newMoney = request.getParameter("moneyhow");//页面输入金额
		String moneydiscription = request.getParameter("moneydiscription");//充值申请描述
		//提交申请
		
		TbCheckMInfo checkInfo = new TbCheckMInfo();
		checkInfo.setProposer(user);//申请人(登录用户)
		checkInfo.setProDescription(moneydiscription);//申请描述
		checkInfo.setProStatus(0);//未审核状态
		checkInfo.setResMoney(newMoney);//申请金额
		checkInfo.setProTime(new Date());//申请时间
		checkInfo.setResId(aid);//充值帐号id
		checkInfo.setResRole(rolemanager.getRoleInfoby_roleid("2"));//充值帐号角色（代理商角色）
		Boolean check = moneyManager.addCheck(checkInfo);
		if(check){
			String print ="充值申请执行成功！";
			String resultmessage="充值申请执行成功";
			showMessage(request, print, resultmessage);
		}
//		//扣钱
//		Double deMoney =Double.parseDouble("-" + newMoney);
//		Boolean deduct = AddAndDeductMoney(roleId,agentid,epId,deMoney);//扣除充值人的余额
//		if(deduct){
//			String print ="扣除执行成功！";
//			String resultmessage="";
//			showMessage(request, print, resultmessage);
//			String nn="";
//			nn = agentmanger.getAgent_ByAgentID(aid).getAgent_Man().toString();
//
//			Boolean a =  addLog(deMoney,nn,uId);//添加log数据到数据库
//
//			if(a){
//				System.out.println("扣除记录插入成功");
//			}else{
//				System.out.println("扣除记录插入失败");
//			}
			
			//充值
//			Double money =Double.parseDouble(agentmanger.getAgentName(aid).getAgent_Money());//代理商余额
//			Boolean update =AddMoneyA(aid, newMoney);//更新充值的金额数据
//			if(update){//添加充值记录log
//				print ="充值执行成功！";
//				resultmessage="成功为"+nn+"充值" + newMoney +"元";
//				showMessage(request, print, resultmessage);
//				
//				String name = null;
//	
//				name = agentmanger.getAgent_ByAgentID(aid).getAgent_Name();
//	
//				Boolean b =  addLog(newMoney,name,uId);//添加log数据到数据库
//	
//				if(b){
//					System.out.println("充值记录插入成功");
//				}else{
//					System.out.println("充值记录插入失败");
//				}
//			}
//		}
		return mapping.findForward("cmlistinfo");//跳转到申请列表页
	}

	/**
	 * 为代理商充值页面
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
		String mjsp = Money(roleId, agentid, epId);//余额
		request.setAttribute("money",mjsp );//传回jsp页面
		String aId =request.getParameter("aid");//获取页面传来要充值的代理id
		request.setAttribute("aInfo",agentmanger.getAgent_ByAgentID(aId));//传回代理信息到页面
		return mapping.findForward("cqinfoajsp");//进入给代理充值页面;
	}

	/**
	 * 代理商list jsp
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
		String mjsp = Money(roleId, agentid, epId);//余额
		request.setAttribute("money",mjsp );//传回jsp页面
		
		String cmd = request.getParameter("CMD");
		String agent_name = request.getParameter("agent_name");// 代理商名字
		String agent_type = request.getParameter("agent_type");// 代理商类型
		String type=request.getParameter("type");
		if (agent_name == null || (agent_name.trim()).equals("")) {
			agent_name = "";
		}
		if (agent_type == null || (agent_type.trim().equals(""))) {
			agent_type = "-1";
		}
		int agentCount = 0;
		agentCount = agentmanger.getAgent_SearchCount(agent_name, agent_type);
		// 获取每页的条数
		int pageSize = 12;
		// 获取总页数
		int pageCount = agentmanger.getPageCount(agentCount, pageSize);
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
		request.setAttribute("agentCount", agentCount);
		// 返回每页面显示条数
		request.setAttribute("pageSize", pageSize);
		// 返回当前页
		request.setAttribute("currentPage", pageNo);
		// 设置返回的命令字
		request.setAttribute("CMD", cmd);
		// 设置返回用户类型
		request.setAttribute("agent_type", agent_type);
		// 设置返回用户查询的intput中值
		request.setAttribute("agent_name", agent_name);

		// 返回的总页数
		request.setAttribute("pageCount", pageCount);
		// 返回所有的一级代理商

		request.setAttribute("pagentlist", agentmanger.getParentAgent(Integer.parseInt(agentid),roleId));
		request.setAttribute("agentList", agentmanger.getTbAgentInfoby_name(
				pageNo, pageSize, agent_name, agent_type));
		return mapping.findForward("cqlistinfo_ajsp");
	}

	/**
	 * 终端批量充值操作
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
		//判断余额是否足够
		Double checkMoney=0.00;
		if(roleId == 3|| roleId == 4){//企业角色
			TbEnterpriseInfo ep = epmanger.getEpinfo_byepid(epId);
			checkMoney = Double.valueOf(ep.getEp_Money());
		}else{//代理商角色
			if(roleId == 1 || roleId == 35){//管理员，超级管理员
				agentid = "-1";//总部
			}
			TbAgentInfo a = agentmanger.getAgent_ByAgentID(agentid);
			checkMoney = Double.valueOf(a.getAgent_Money());
		}
		if(checkMoney<0 || checkMoney == 0){
			String print ="余额不足！";
			String resultmessage="余额不足，无法操作！";
			showMessage(request, print, resultmessage);
			return  ms_money_plczjsp(mapping, form, request, response,
					roleId,agentid,epId);
		}
		
	
		String pfId = request.getParameter("pfType");//充值的套餐id
		String ms_id[] = request.getParameterValues("selectmsid");//批量充值的id
		
		TbPFInfo pfInfo = pfManager.findPFInfoById(pfId); //套餐
		List<TbMsInfo> msListInfo = new ArrayList<TbMsInfo>();//终端list
		for(int i = 0 ; i< ms_id.length;i++){
			TbMsInfo msInfo =new TbMsInfo();
			msInfo = msmanager.getTBMsinfoby_msid(ms_id[i]);//终端
			msListInfo.add(msInfo);
		}
		
		Double deMoney = Double.parseDouble(pfInfo.getPfHow()) * (-1*(ms_id.length));
		Boolean deduct = AddAndDeductMoney(roleId,agentid,epId,deMoney);//扣除充值人的余额
		if(deduct){
			String print ="扣除执行成功！";
			String resultmessage="";
			showMessage(request, print, resultmessage);
			
//			String msidsb = new String();
//			for(String s:ms_id){
//				msidsb += ","+s;
//			}
//			String str =new String(msidsb);
//			Boolean a = false;
//			for(int i = 0;i < msListInfo.size() ; i++ ){
//				String ss ="【"+msListInfo.get(i).getMsName()+"】"+ msListInfo.get(i).getMsId();
//				Boolean c = addLog(Double.parseDouble(pfInfo.getPfHow()), ss, user);//添加log数据到数据库
//				if(c == true  && i == msListInfo.size()){
//					a = true;
//				}
//			}
//			
//			if(a){
//				System.out.println("扣除记录插入成功");
//			}else{
//				System.out.println("扣除记录插入失败");
//			}
			//充值终端
			Calendar c = Calendar.getInstance();//获得一个日历的实例     
			SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			for(int i=0;i<msListInfo.size();i++){
				TbMsInfo ms=new TbMsInfo();
				ms =msListInfo.get(i);
				ms.setPf(pfInfo);//修改终端套餐信息
				
				//日期比较，到期时间<充值时间，在现在充值的时间基础上加上套餐时长，否则到期时间基础上加上套餐时长
				if(ms.getMsMoneyTime()!=null){
					if(ms.getMsMoneyTime().before(new Date())){//到期时间在充值时间之前
						c.setTime(new Date());//设置日历时间   
						c.add(Calendar.MONTH,pfInfo.getPfTime());//在日历的月份上增加月
					}else{
						c.setTime(ms.getMsMoneyTime());//设置日历时间   
						c.add(Calendar.MONTH,pfInfo.getPfTime());//在日历的月份上增加月
					}
				}else{
					c.setTime(new Date());//设置日历时间   
					c.add(Calendar.MONTH,pfInfo.getPfTime());//在日历的月份上增加月
				}
				ms.setMsMoneyTime(c.getTime());//修改套餐到期时间
				ms.setIs_Arrearage(1);//资费状态改为有费
				ms.setFlag(1);//改为正常状态
				msListInfo.set(i, ms);
			}
			
			Boolean upms = msmanager.updateMs(msListInfo);
			if(upms){
				String print1 ="终端套餐充值执行成功！";
				String resultmessage1="成功充值"+msListInfo.size()+"个终端的套餐（"+pfInfo.getPfType() +"），共"+deMoney+"元";
				showMessage(request, print1, resultmessage1);
				
				Boolean b = false;
				for(int i = 0;i < msListInfo.size() ; i++ ){
					String s ="【"+msListInfo.get(i).getMsName()+"】"+ msListInfo.get(i).getMsId();
					Boolean d = addLog(-1*Double.parseDouble(pfInfo.getPfHow()), s, user,0, null,null,null);//添加log数据到数据库
					if(d == true  && i == msListInfo.size()){
						b = true;
					}
				}
				//Boolean b = addLog(-1*deMoney, str, uId);//添加log数据到数据库
				if(b){
					System.out.println("终端套餐批量充值记录插入成功");
				}else{
					System.out.println("终端套餐批量充值记录插入失败");
				}
			}else{
				System.out.println("终端套餐充值执行失败！");
			}
		}
		
		return  ms_money_plczjsp(mapping, form, request, response,
				roleId,agentid,epId);
	}


	/**
	 * 批量充值jsp
	 */
	private ActionForward ms_money_plczjsp(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response,int roleId, String agentid,
			String epId) {
		// TODO Auto-generated method stub
		String mjsp = Money(roleId, agentid, epId);//余额
		request.setAttribute("money",mjsp );//传回jsp页面
		
		List<TbPFInfo> pfList = pfManager.findAllPFInfo();
		request.setAttribute("pfList", pfList);
		
		String pagentid = ""; // 一级代理商id
		String childagentid = ""; // 二级代理商id
		String agent = request.getSession().getAttribute("agentId")+"";
		if (request.getParameter("pagentid") != null && (agent.equals("-1") || agent.equals("0"))) {
			pagentid = request.getParameter("pagentid");
		} else {
			 //pagentid = "-1"; // 代理商是总部
        	pagentid = agent;
        	if(pagentid.equals("0"))
        		pagentid = "-1";
		}

		if (request.getParameter("childagentid") != null) {
			childagentid = request.getParameter("childagentid");
		} else {
			childagentid = "-1"; // 直属企业
		}

		String ep = request.getParameter("ep");
		if (ep == null || ep.equals("null")) {
			ep = "-1";
		}
		request.setAttribute("Pagentlist", agentmanger.getParentAgent(Integer.parseInt(agentid),roleId));
//		if(!pagentid.equals("-1")){
//			// 一级代理商名称
//			request.setAttribute("Pagentlist", agentmanger.getParentAgentNotIncludeZB());
//		}else{
//			// 一级代理商名称
//			request.setAttribute("Pagentlist", agentmanger.getParentAgent(Integer.parseInt(agentid),roleId));
//		}
		
		// 一级指定二级代理商结果集
		request.setAttribute("Cagentlist", agentmanger
				.getChildAgentByParamentid(pagentid));
		// 返回所有企业默认
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
	 * 终端充值
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
	//判断余额是否足够
//	Double checkMoney=0.00;
//	if(roleId == 3 || roleId == 4){//企业角色
//		TbEnterpriseInfo ep = epmanger.getEpinfo_byepid(epId);
//		checkMoney = Double.valueOf(ep.getEp_Money());
//	}else{//代理商角色
//		if(roleId == 1 || roleId == 35){//管理员，超级管理员
//			agentid = "-1";//总部
//		}
//		TbAgentInfo a = agentmanger.getAgent_ByAgentID(agentid);
//		checkMoney = Double.valueOf(a.getAgent_Money());
//	}
//	if(checkMoney<0 || checkMoney == 0){
//		String print ="余额不足！";
//		String resultmessage="余额不足，无法操作！";
//		showMessage(request, print, resultmessage);
//		return  ms_money_listinfojsp(mapping, form, request, response,
//				roleId,agentid,epId);
//	}
//		
//		String pfId = request.getParameter("pfType");//充值的套餐id
//		String msId = request.getParameter("msid");//充值的终端id
//		
//		TbPFInfo pfInfo = pfManager.findPFInfoById(pfId); //套餐
//		TbMsInfo msInfo = msmanager.getTBMsinfoby_msid(msId);//终端
//		Double deMoney = Double.parseDouble("-" + pfInfo.getPfHow());
//		Boolean deduct = AddAndDeductMoney(roleId,agentid,epId,deMoney);//扣除充值人的余额
//		if(deduct){
//			String print ="扣除执行成功！";
//			String resultmessage="";
//			showMessage(request, print, resultmessage);
//			
////			Boolean a =  addLog(deMoney,msInfo.getMsId(),user);//添加log数据到数据库
////			if(a){
////				System.out.println("扣除记录插入成功");
////			}else{
////				System.out.println("扣除记录插入失败");
////			}
//			
//			//充值终端
//			msInfo.setPf(pfInfo);//修改终端套餐信息
//			
//			Calendar c = Calendar.getInstance();//获得一个日历的实例     
//			SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//			//日期比较，到期时间<充值时间，在现在充值的时间基础上加上套餐时长，否则到期时间基础上加上套餐时长
//			if(msInfo.getMsMoneyTime().before(new Date())){//到期时间在充值时间之前
//				c.setTime(new Date());//设置日历时间   
//				c.add(Calendar.MONTH,pfInfo.getPfTime());//在日历的月份上增加月
//			}else{
//				c.setTime(msInfo.getMsMoneyTime());//设置日历时间   
//				c.add(Calendar.MONTH,pfInfo.getPfTime());//在日历的月份上增加月
//			}
//		
//			msInfo.setMsMoneyTime(c.getTime());//修改套餐到期时间
//			msInfo.setIs_Arrearage(1);//资费状态改为有费
//			msInfo.setFlag(1);//正常使用状态
//			List<TbMsInfo> msList = new ArrayList<TbMsInfo>();
//			msList.add(msInfo);
//			
//			Boolean upms = msmanager.updateMs(msList);
//			if(upms){
//				String print1 ="终端套餐充值执行成功！";
//				String resultmessage1="成功充值终端套餐（"+pfInfo.getPfType() +"），共"+deMoney+"元";
//				showMessage(request, print1, resultmessage1);
//				
//				Boolean b =  addLog(Double.parseDouble(pfInfo.getPfHow()),msInfo.getMsId(),user);//添加log数据到数据库
//				if(b){
//					System.out.println("终端套餐充值记录插入成功");
//				}else{
//					System.out.println("终端套餐充值记录插入失败");
//				}
//			}
//		}
//		
//		return ms_money_listinfojsp(mapping, form, request, response,
//				roleId,agentid,epId);
		return null;
	}
	
	/**
	 * 终端充值页面
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
		      
		String mjsp = Money(roleId, agentid, epId);//余额
		request.setAttribute("money",mjsp );//传回jsp页面
		
		String msid = request.getParameter("msid");//充值的终端id
//		String []msidlist = request.getParameterValues("list");//批量充值终端id
		
		TbMsInfo msinfo =null;
//		List<TbMsInfo> msList = null;
//		if(msid != null){
			msinfo = msmanager.getTBMsinfoby_msid(msid);//单个终端信息
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
	 * 终端list jsp
	 */
	private ActionForward ms_money_listinfojsp(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response,int roleId, String agentid,
			String epId) {
		// TODO Auto-generated method stub
		String mjsp = Money(roleId, agentid, epId);//余额
		request.setAttribute("money",mjsp );//传回jsp页面
		
		//获取ms list
		
		
		String pagentid = ""; // 一级代理商id
        String childagentid = ""; // 二级代理商id
        String cmd = request.getParameter("CMD");
        HttpSession session = request.getSession(true);
        String type = request.getParameter("type");
        
        String agent = request.getSession().getAttribute("agentId")+"";
        String ep = request.getParameter("ep");
        if (ep == null || ep.equals("null")) {
            ep = "-1";
        }
        
//        if(roleId == 2){//代理商
//			childagentid="-2";
//			pagentid="0";
////			List<TbEnterpriseInfo> eplist = epmanger.getEpinfo_byagentid(agentid);
//		}else if(roleId == 1 || roleId == 35){//管理员和超级管理人
//			pagentid = "-1";
//			childagentid="0";
//		}
        if (request.getParameter("pagentid") != null && (agent.equals("-1") || agent.equals("0"))) {
            pagentid = request.getParameter("pagentid");
        } else {
            //pagentid = "-1"; // 代理商是总部
        	pagentid = agent;
        	if(pagentid.equals("0"))
        		pagentid = "-1";
        }

        if (request.getParameter("childagentid") != null) {
            childagentid = request.getParameter("childagentid");
        } else {
            childagentid = "-2"; // 所有企业
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
        if (ismobile == null || ismobile.equals("")) { // 2全部,0,禁止切换,1,允许切换
            ismobile = "2";
        }
        String arrearage = request.getParameter("c04");
        if (arrearage == null || arrearage.equals("")) { // 2全部,0,车机,1,手持机
            arrearage = "2";
        }
        int msCount = 0;
        msCount = msmanager.getMs_sertch(user_type, statue, flag, ms_id.trim(),
                ms_name.trim(), pagentid, childagentid, ep, ismobile, arrearage);
        // 获取每页的条数
        int pageSize = 12;
        // 获取总页数
        int pageCount = msmanager.getPageCount(msCount, pageSize);
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
        request.setAttribute("msCount", msCount);
        // 返回每页面显示条数
        request.setAttribute("pageSize", pageSize);
        // 返回当前页
        request.setAttribute("currentPage", pageNo);
        // 设置返回的命令字
        request.setAttribute("CMD", cmd);
        // 设置返回用户类型

        // 设设置返回用户查询的select中option值
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
//			// 一级代理商名称
//			request.setAttribute("Pagentlist", agentmanger.getParentAgentNotIncludeZB());
//		}else{
//			// 一级代理商名称
//			request.setAttribute("Pagentlist", agentmanger.getParentAgent(Integer.parseInt(agentid),roleId));
//		}
//        // 一级指定二级代理商结果集
//        request.setAttribute("Cagentlist", agentmanger
//                .getChildAgentByParamentid(pagentid));
        // 返回所有企业默认
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

        // 返回的总页数
        request.setAttribute("pageCount", pageCount);
        request.setAttribute("msList", msList);
       
        return mapping.findForward("mscqlistinfojsp");

	}



/**
 * 给企业充值
 */
private ActionForward money_infocz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			int roleId, String agentid,
			String epId,TbUserInfo user) {
		// TODO Auto-generated method stub
		//判断余额是否足够
		Double checkMoney=0.00;
		if(roleId == 3 || roleId == 4){//企业角色
			TbEnterpriseInfo ep = epmanger.getEpinfo_byepid(epId);
			checkMoney = Double.valueOf(ep.getEp_Money());
		}else{//代理商角色
			if(roleId == 1 || roleId == 35){//管理员，超级管理员
				agentid = "-1";//总部
			}
			TbAgentInfo a = agentmanger.getAgent_ByAgentID(agentid);
			checkMoney = Double.valueOf(a.getAgent_Money());
		}
		if(checkMoney<0 || checkMoney == 0){
			String print ="余额不足！";
			String resultmessage="余额不足，无法操作！";
			showMessage(request, print, resultmessage);
			return  ep_or_angent_money_czjsp(mapping, form, request, response,
					roleId,agentid,epId);
		}
	
	
		String eid = request.getParameter("epid");//充值的企业id	
		Double newMoney = Double.parseDouble( request.getParameter("moneyhow"));//页面输入金额
		//扣钱
		Double deMoney =Double.parseDouble("-" + newMoney);
		Boolean deduct = AddAndDeductMoney(roleId,agentid,epId,deMoney);//扣除充值人的余额
		if(deduct){
			String print ="扣除执行成功！";
			String resultmessage="";
			showMessage(request, print, resultmessage);
			String nn="";
			nn = ((TbEnterpriseInfo)epmanger.getEpinfo_byepid(eid)).getEp_Name();

//			Boolean a =  addLog(deMoney,nn,user);//添加log数据到数据库
//
//			if(a){
//				System.out.println("扣除记录插入成功");
//			}else{
//				System.out.println("扣除记录插入失败");
//			}
			
			//充值
			Double money =Double.parseDouble(Money(eid));//企业余额
			Boolean update =AddMoney(eid, newMoney);//更新充值的金额数据
			if(update){//添加充值记录log
				print ="充值执行成功！";
				resultmessage="成功为"+nn+"充值" + newMoney +"元";
				showMessage(request, print, resultmessage);
				
				String name = null;
	
				name = ((TbEnterpriseInfo)epmanger.getEpinfo_byepid(eid)).getEp_Name();
	
				Boolean b =  addLog(newMoney,name,user,0, null,null,null);//添加log数据到数据库
	
				if(b){
					System.out.println("充值记录插入成功");
				}else{
					System.out.println("充值记录插入失败");
				}
			}
		}
		return money_listinfojsp(mapping, form, request, response,
				roleId,agentid,epId);
	}
	
/**
 * 跳转到给企业充值页面
 */
private ActionForward money_infoczjsp(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response, int roleId, String agentid,
			String epId) {
		String mjsp = Money(roleId, agentid, epId);//余额
		request.setAttribute("money",mjsp );//传回jsp页面
		String eId =request.getParameter("mepid");//获取页面传来要充值的企业id
		request.setAttribute("epInfo",epmanger.getEpinfo_byepid(eId));//传回企业信息到页面
		
		return mapping.findForward("cqinfojsp");//进入给企业充值页面;
	}

/**
 * 企业费用列表信息查看
 */
private ActionForward money_listinfojsp(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response, int roleId, String agentid,
			String epId) {
			String mjsp = Money(roleId, agentid, epId);//余额
			request.setAttribute("money",mjsp );//传回jsp页面
			
			//获取登录用户旗下的企业

				String pagentid = ""; // 一级代理商id
				String childagentid = ""; // 二级代理商id
//				String type=request.getParameter("type");

				String cmd = request.getParameter("CMD");
				String epname = request.getParameter("epname");
				if (epname == null || epname.equals("")) {
					epname = "";
				}
				
				if(roleId == 2){//代理商
					childagentid="-2";
					pagentid="0";
//					List<TbEnterpriseInfo> eplist = epmanger.getEpinfo_byagentid(agentid);
				}else if(roleId == 1 || roleId == 35){//管理员和超级管理人
					pagentid = "-1";
					childagentid="0";
				}else{
					pagentid = "0";
					childagentid="0";
				}
				int epCount = 0;
				epCount = epmanger.getEp_sertch(pagentid, childagentid, epname.trim(), Integer.parseInt(epId), roleId);
				// 获取每页的条数
				int pageSize = 12;
				// 获取总页数
				int pageCount = epmanger.getPageCount(epCount, pageSize);
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
				request.setAttribute("epCount", epCount);
				// 返回每页面显示条数
				request.setAttribute("pageSize", pageSize);
				// 返回当前页
				request.setAttribute("currentPage", pageNo);
				// 设置返回的命令字
				request.setAttribute("CMD", cmd);
//
				// 返回的总页数
				request.setAttribute("pageCount", pageCount);

				request.setAttribute("epname", epname);
				// 返回用户单位信息
				List<TbEnterpriseInfo> list = null;
				if (roleId == 3){
					list = epmanger.getEpinfoby_mspage(pageNo,
							pageSize, pagentid, childagentid, epname.trim(), Integer.parseInt(epId), roleId);
				}else{
					list = epmanger.getEpinfoby_mspage(pageNo,
							pageSize, pagentid, childagentid, epname.trim(), Integer.parseInt(agentid), roleId);
				}
				request.setAttribute("epList",list );

			return mapping.findForward("cqlistinfojsp");//进入list页面
	}
/**
 * 登录的用户给自己充值操作
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
		Double money =Double.parseDouble(Money( roleId, agentid, epId));//用户余额
		Double newMoney = Double.parseDouble( request.getParameter("moneyhow"));//页面输入金额
		
		String name =user.getUserName()+"/帐号充值";//购买商品名称
		UtilDate date = new UtilDate();
		String get_order =Demo.getNonceStr();//订单号
		String payMethod = request.getParameter("moneytype");//支付方式（1、支付宝    2、微信）
		if(payMethod.equals("1")){//支付宝支付
	////////////////////////////////////请求参数//////////////////////////////////////
	
			//支付类型
			String payment_type = "1";
			//必填，不能修改
			//服务器异步通知页面路径 http://203.88.213.243/TSCWEB/alipayjsp/notify_url.jsp
			String notify_url = FilePathConfig.getAliNotifyUrl();
			//需http://格式的完整路径，不能加?id=123这类自定义参数
	
			//页面跳转同步通知页面路径http://203.88.213.243/TSCWEB/alipayjsp/return_url.jsp
			String return_url = FilePathConfig.getServer_Ip()+"alipayjsp/return_url.jsp";
			//需http://格式的完整路径，不能加?id=123这类自定义参数，不能写成http://localhost/
			System.out.println(notify_url);

			String out_trade_no = null,subject = null,total_fee = null,body = null,show_url = null,anti_phishing_key = null,exter_invoke_ip = null;
			try {
				
				out_trade_no = get_order;
			
				//商户网站订单系统中唯一订单号，必填
		
				//订单名称
				subject = name;//new String(name.getBytes("ISO-8859-1"),"UTF-8");
				//必填
		
				//付款金额
				total_fee = new String(newMoney.toString().getBytes("ISO-8859-1"),"UTF-8");
				//必填
		
				//订单描述
		
				 body = null;//new String(request.getParameter("WIDbody").getBytes("ISO-8859-1"),"UTF-8");
				//商品展示地址
				 show_url = null;//new String(request.getParameter("WIDshow_url").getBytes("ISO-8859-1"),"UTF-8");
				//需以http://开头的完整路径，例如：http://www.商户网址.com/myorder.html
		
				//防钓鱼时间戳
				 anti_phishing_key = "";
				//若要使用请调用类文件submit中的query_timestamp函数
		
				//客户端的IP地址
				 exter_invoke_ip = "";
				//非局域网的外网IP地址，如：221.0.0.1
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//////////////////////////////////////////////////////////////////////////////////
			
			//把请求参数打包成数组
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
			
			//建立请求
			String sHtmlText = AlipaySubmit.buildRequest(sParaTemp,"get","确认");
			System.out.println(sHtmlText);
			request.setAttribute("sHtmlText", sHtmlText);
		}else if(payMethod.equals("2")){//微信支付
			//扫码支付
			//notifyurl必须为80端口并且地址不能带参数
			String notifyurl = FilePathConfig.getServer_Ip()+"wechat/notify_url.jsp";
		    WxPayDto tpWxPay1 = new WxPayDto();
		    tpWxPay1.setBody(name);
		    tpWxPay1.setOrderId(get_order);
		    tpWxPay1.setSpbillCreateIp(request.getRemoteAddr());//客户端IP
		    tpWxPay1.setTotalFee(newMoney+"");
		    String codeurl = Demo.getCodeurl(tpWxPay1,notifyurl);
		    request.setAttribute("codeurl", codeurl);
		    request.setAttribute("paymoney", newMoney);

		    return mapping.findForward("wechatjsp");
		}
//		Boolean update =AddAndDeductMoney( roleId, agentid, epId, newMoney);//更新数据
//		if(update){//添加充值记录log
//			
//			String print ="充值执行成功！";
//			String resultmessage="成功充值" + newMoney +"元";
//			showMessage(request, print, resultmessage);
//			
//			String name = null;
//
//			if(roleId == 3 || roleId == 4 ){
//				name = ((TbEnterpriseInfo)epmanger.getEpinfo_byepid(epId)).getEp_Name();
//			}else{
//				name = user.getUserName();
//			}
//			Boolean b =  addLog(newMoney,name,user);//添加log数据到数据库
//
//			if(b){
//				System.out.println("充值记录插入成功");
//			}else{
//				System.out.println("充值记录插入失败");
//			}
//		}
		return money_listinfojsp(mapping,
				form, request,
				response, roleId, agentid,
				epId);
	}

	/**
	 * 代理商,企业用户登录充值页面
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
		String money =Money( roleId, agentid, epId);//用户余额
		
		request.setAttribute("money",money );//传回jsp页面
		return mapping.findForward("cqjsp");
	}
	
	
	/**
	 * 获取登录用户余额
	 * @param roleId
	 * @param agentid
	 * @param epId
	 * @return
	 */
	private String Money(int roleId,String agentid,String epId){
		String m =null;
		if(roleId == 4){//企业角色
			m = ((TbEnterpriseInfo)epmanger.getEpinfo_byepid(epId)).getEp_Money();
		}else{//代理商角色
			m = ((TbAgentInfo)agentmanger.getAgent_ByAgentID(agentid)).getAgent_Money();
		}
		if(m == null || m == ""){
			m="0.00";
		}
		return m;
	}
	
	
	
	/**
	 * 企业余额
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
	 * 充值或扣除金额到数据库
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

		if(roleId == 4 ){//直属企业角色
			TbEnterpriseInfo ep = epmanger.getEpinfo_byepid(epId);
			if(ep.getEp_Money()==null || ep.getEp_Money() ==""){
				ep.setEp_Money("0");
			}
			double newm = Double.parseDouble(ep.getEp_Money());
			m= money + newm;
			ep.setEp_Money(Double.toString(m));
			updateMoney = epmanger.updateEp(ep);
		}else{//代理商角色
			if(roleId == 1 || roleId == 35){//管理员，超级管理员
				agentid = "-1";//总部
			}
			TbAgentInfo a = agentmanger.getAgent_ByAgentID(agentid);
			double newm = Double.parseDouble(a.getAgent_Money());
			 m= money + newm ;
			 a.setAgent_Money(Double.toString(m));
			 updateMoney= agentmanger.updateAgent(a);//更新金额
		}
		return updateMoney;
	}
	/**
	 * 给企业充值
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
	 * 给代理商充值
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
	 * 充值添加记录
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
		
		ml.setPayType(payType);//充值类型(支付宝：1，微信：2，回收企业余额：3，终端套餐转移：4，余额充值：0)
		ml.setPayNum(payNum);//充值号码（支付宝，微信号，终端id，企业id）
		ml.setTeadeNo(tNo);//支付交易号（支付宝，微信）
		ml.setMoney_Trade_No(mtNo);//订单号
		
		return moneyManager.addMoneyLog(ml);
	}
	
	/**
	 * 返回操作信息
	 * log
	 */
	private  void showMessage(HttpServletRequest request,String print,String resultmessage){
		System.out.println(print);
		request.setAttribute("message", resultmessage);
	}
	
	/**
	 * 支付宝,微信充值成功后执行数据库记录
	 */
	public Boolean  zhiFuBaoMoney(int roleId,TbUserInfo user,Double newMoney,int payType,String payNum,String tNo,String mtNo){

		Boolean update =AddAndDeductMoney( roleId, user.getAgent_Id().toString(), 
				user.getEp_Id().toString(), newMoney);//更新数据
		if(update){//添加充值记录log
			
			String print ="充值执行成功！";
			
			String name = null;

			if(roleId == 3 || roleId == 4 ){
				name = ((TbEnterpriseInfo)epmanger.getEpinfo_byepid(user.getEp_Id().toString())).getEp_Name();
			}else{
				name = user.getUserName();
			}
			Boolean b =  addLog(newMoney,name,user, payType, payNum, tNo, mtNo);//添加log数据到数据库

			if(b){
				System.out.println("充值记录插入成功");
			}else{
				System.out.println("充值记录插入失败");
				return false;
			}
			return true;
		}
		return false;
	}
}
