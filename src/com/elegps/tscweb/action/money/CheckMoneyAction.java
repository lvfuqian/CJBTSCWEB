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
 *审核
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
			// 如果没有就接收到参数默认显示全部类型
			if (request.getParameter("CMD") == null || request.getParameter("CMD").equals("")) {
				cmd = "success";
			} else {
				cmd = request.getParameter("CMD");
			}
			
			//获取用户角色 
			int role_id =Integer.parseInt(request.getSession().getAttribute("roleId")+"");
			//cmlistinfo，cminfo   money_check_ok
			if (cmd.equals("checklistinfojsp")) { // 费用申请代理商列表页面（管理员查看，财务查看和审核）
				actionforward = checklistinfojsp(mapping, form, request, response,
						role_id,user.getUserId());
			}
			if (cmd.equals("checklistinfoepjsp")) { // 费用申请企业列表页面（管理员查看，财务查看和审核）
				actionforward = checklistinfojsp(mapping, form, request, response,
						role_id,user.getUserId());
			}
			if (cmd.equals("checklistinfomsjsp")) { // 费用申请终端列表页面（管理员查看，财务查看和审核）
				actionforward = checklistinfojsp(mapping, form, request, response,
						role_id,user.getUserId());
			}
			if (cmd.equals("money_check_ok")) { // 费用申请列表页面（财务执行通过）
				actionforward = money_check(mapping, form, request, response,role_id,user);
			}
			if (cmd.equals("money_check_no")) { // 费用申请列表页面（财务执行不通过）
				actionforward = money_check(mapping, form, request, response,role_id,user);
			}
			
			if (cmd.equals("cminfojsp")) { // 费用申请列表页面（管理员查看，财务查看和审核）
				actionforward = cminfojsp(mapping, form, request, response);
			}
		}
		
		return actionforward;
	}

	/**
	 * 跳转到审核详细信息页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	private ActionForward cminfojsp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		int finId = Integer.parseInt(request.getParameter("cmid")+"");//审核id
		TbCheckMInfo cmInfo = moneyManager.getCMInfoById(finId);
		TbPFInfo pf = null;
		TbEnterpriseInfo ep =null;
		TbAgentInfo a=null;
		if(cmInfo.getResRole()==null){
				//cmInfo.setResId(cmInfo.getResId().replace(",", "<br/>"));
				String[] s=cmInfo.getResId().split(",");
				//页面显示终端格式：终端名：终端id
				String res =s[0]+"<br/>";
				for(int i=1;i< s.length;i++){
					TbMsInfo msinfo = msmanager.getTBMsinfoby_msid(s[i]);
					if(msinfo == null){
						res += "《终端："+s[i]+"已不存在！》<br/>";
					}else{
						res += msinfo.getMsName()+"："+s[i]+"<br/>";
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
	 * 审核执行（通过，不通过）
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
			String print1 ="无权限执行此操作";
			String resultmessage1="无权限执行此操作";
			showMessage(request, print1, resultmessage1);
			return checklistinfojsp(mapping, form, request, response,
					roleId,user.getUserId());
		}
		String cmd = request.getParameter("CMD");
		String finId = request.getParameter("cmid");
		TbCheckMInfo cminfo = moneyManager.getCMInfoById(Integer.parseInt(finId));
		if(cmd.equals("money_check_ok")){//通过
			cminfo.setReadState(1);
			cminfo.setProStatus(1);
			cminfo.setCheckTime(new Date());
			cminfo.setCheckPerson(user);
			
			String mm = "";
			if(cminfo.getResRole()!=null){
				if(cminfo.getResRole().getRoleId() == 2){//增加代理商金额
					TbAgentInfo a = agentmanger.getAgent_ByAgentID(cminfo.getResId());
					mm = a.getAgent_Money();
					if(mm == null || mm == ""){
						mm="0.00";
					}
					double newm = Double.parseDouble(mm) + Double.parseDouble(cminfo.getResMoney());
					a.setAgent_Money(Double.toString(newm));
					Boolean updateMoney = agentmanger.updateAgent(a);
					if(updateMoney){
						String print ="充值执行成功！";
						String resultmessage="成功充值" + cminfo.getResMoney() +"元";
						showMessage(request, print, resultmessage);
						
						TbMoneyLog ml=new TbMoneyLog();	
						ml.setHow(cminfo.getResMoney());
						ml.setMlogTime(new Date());
						ml.setMoneyToUser(a.getAgent_Name());
						ml.setUser(user);
						ml.setPayType(0);
						Boolean addML = moneyManager.addMoneyLog(ml);
						if(addML){
							System.out.println("审核充值记录插入成功");
						}else{
							System.out.println("审核充值记录插入失败");
						}
					}else{
						String print ="充值执行失败！";
						String resultmessage="充值" + cminfo.getResMoney() +"元 失败！";
						showMessage(request, print, resultmessage);
					}
				}else if(cminfo.getResRole().getRoleId() == 3){//增加企业金额
					TbEnterpriseInfo ep = epmanger.getEpinfo_byepid(cminfo.getResId());
					mm = ep.getEp_Money();
					if(mm == null || mm == ""){
						mm="0.00";
					}
					double newm = Double.parseDouble(mm) + Double.parseDouble(cminfo.getResMoney());
					ep.setEp_Money(Double.toString(newm));
					Boolean updateMoney = epmanger.updateEp(ep);
					if(updateMoney){
						String print ="充值执行成功！";
						String resultmessage="成功充值" + cminfo.getResMoney() +"元";
						showMessage(request, print, resultmessage);
						
						TbMoneyLog ml=new TbMoneyLog();	
						ml.setHow(cminfo.getResMoney());
						ml.setMlogTime(new Date());
						ml.setMoneyToUser(ep.getEp_Name());
						ml.setUser(user);
						ml.setPayType(0);
						Boolean addML = moneyManager.addMoneyLog(ml);
						if(addML){
							System.out.println("审核充值记录插入成功");
						}else{
							System.out.println("审核充值记录插入失败");
						}
					}else{
						String print ="充值执行失败！";
						String resultmessage="充值" + cminfo.getResMoney() +"元 失败！";
						showMessage(request, print, resultmessage);
					}
				}
			}else {//增加终端套餐
				String [] msid = cminfo.getResId().split(",");//拿出终端id
				Double money = Double.parseDouble(cminfo.getResMoney())/(msid.length-1);//计算每个终端的充值费用
				TbPFInfo pf = pfManager.getPFinfoByHow(money);
				List<TbMsInfo> msList = new ArrayList<TbMsInfo>(); 
				Calendar c = Calendar.getInstance();//获得一个日历的实例     
				//SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				for(int i = 1 ;i < msid.length ; i++){
					TbMsInfo msinfo = msmanager.getTBMsinfoby_msid(msid[i]);
					if(msinfo == null){
						continue;
					}
					msinfo.setPf(pf);//修改终端套餐信息
					
					//日期比较，到期时间<充值时间，在现在充值的时间基础上加上套餐时长，否则到期时间基础上加上套餐时长
					if(msinfo.getMsMoneyTime()!=null){
						if(msinfo.getMsMoneyTime().before(new Date())){//到期时间在充值时间之前
							c.setTime(new Date());//设置日历时间   
							c.add(Calendar.MONTH,pf.getPfTime());//在日历的月份上增加月
						}else{
							c.setTime(msinfo.getMsMoneyTime());//设置日历时间   
							c.add(Calendar.MONTH,pf.getPfTime());//在日历的月份上增加月
						}
					}else{
						c.setTime(new Date());//设置日历时间   
						c.add(Calendar.MONTH,pf.getPfTime());//在日历的月份上增加月
					}
					msinfo.setMsMoneyTime(c.getTime());//修改套餐到期时间
					msinfo.setIs_Arrearage(1);//资费状态改为有费
					msinfo.setFlag(1);//改为正常状态
					msList.add(msinfo);
				}
				Boolean upms = msmanager.updateMs(msList);
				String allm = msList.size() * Double.parseDouble(pf.getPfHow()) +"" ;
				cminfo.setResMoney(allm) ;
				if(upms){
					String print1 ="终端套餐充值执行成功！";
					String resultmessage1="成功充值"+msList.size()+"个终端的套餐（"+pf.getPfType() +"），共"+allm+"元";
					showMessage(request, print1, resultmessage1);
					Boolean addML = false;
					for(int i = 0; i<msList.size(); i ++){
						TbMoneyLog ml=new TbMoneyLog();	
						ml.setHow(pf.getPfHow());
						ml.setMlogTime(new Date());
						String s ="【"+msList.get(i).getMsName()+"】"+ msList.get(i).getMsId();
						ml.setMoneyToUser(s);
						ml.setUser(user);
						ml.setPayType(0);
						addML = moneyManager.addMoneyLog(ml);
						if(addML==true && i == msList.size()){
							addML = true;
						}
					}
					
					if(addML){
						System.out.println("审核充值记录插入成功");
					}else{
						System.out.println("审核充值记录插入失败");
					}
				}else{
					String print1 ="终端套餐充值执行失败！";
					String resultmessage1="充值"+msList.size()+"个终端的套餐（"+pf.getPfType() +"），共"+cminfo.getResMoney()+"元，失败";
					showMessage(request, print1, resultmessage1);
				}
			}
		}else if(cmd.equals("money_check_no")){//不通过
			cminfo.setReadState(1);
			cminfo.setProStatus(2);
			cminfo.setCheckTime(new Date());
			cminfo.setCheckPerson(user);
		}
		
//		if(cmd.equals("money_check_ok")){//插入记录log
//			TbMoneyLog ml=new TbMoneyLog();	
//			ml.setHow(cminfo.getResMoney());
//			ml.setMlogTime(new Date());
//			ml.setMoneyToUser(cminfo.getResId());
//			ml.setUserId(user.getUserId());
//			Boolean addML = moneyManager.addMoneyLog(ml);
//			if(addML){
//				System.out.println("审核充值记录插入成功");
//			}else{
//				System.out.println("审核充值记录插入失败");
//			}
//		}
		
		Boolean cm = moneyManager.updateCheck(cminfo);//更新数据
		if(cm){
			String print ="审核执行成功！";
			String resultmessage="审核执行成功！";
			showMessage(request, print, resultmessage);
		}else{
			String print ="审核执行失败！";
			String resultmessage="审核执行失败！";
			showMessage(request, print, resultmessage);
		}
		
		//跳转页面
		if(cminfo.getResRole() == null){
			return mapping.findForward("cmlistinfoms");
		}else if(cminfo.getResRole().getRoleId() == 3 || cminfo.getResRole().getRoleId() == 4){//企业
			return mapping.findForward("cmlistinfoep");
		}else if(cminfo.getResRole().getRoleId() == 2){//代理商
			return mapping.findForward("cmlistinfoa");
		}

		return null;
	}

	/**
	 * 查询费用申请列表jsp页面
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
		
		String userName="";//申请人
		String cmd = request.getParameter("CMD");
		int checkRole = 1;//全部
		if(cmd.equals("checklistinfojsp")){
			checkRole = 2;//代理商
		}else if(cmd.equals("checklistinfoepjsp")){
			checkRole = 10;//企业
		}else if(cmd.equals("checklistinfomsjsp")){
			checkRole = 0;//终端
		}
		//查询条件
		if(roleId == 40){//财务角色id:40
			userName = request.getParameter("username");//申请人
		}
		if (userName == null || userName.equals("")) {
			userName = "";
		}
		String resName = request.getParameter("resname");//充值帐户
		if (resName == null || resName.equals("")) {
			resName = "";
		}
		//审核状态（0：未审核 ，1：审核通过 ， 2：审核不通过）
		int proStatus =-1;
		if(request.getParameter("prostatus")!=null){
			proStatus=Integer.parseInt(request.getParameter("prostatus"));
		}
		
		
		int cmCount = moneyManager.findCMCount(userName, resName, proStatus, roleId, uId, checkRole);//总条数
		// 获取每页的条数
		int pageSize = 12;
		// 获取总页数
		int pageCount = (cmCount + pageSize - 1) / pageSize;
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
		request.setAttribute("cmCount", cmCount);
		// 返回每页面显示条数
		request.setAttribute("pageSize", pageSize);
		// 返回当前页
		request.setAttribute("currentPage", pageNo);
		// 设置返回的命令字
		request.setAttribute("CMD", cmd);
//
		// 返回的总页数
		request.setAttribute("pageCount", pageCount);

		request.setAttribute("username", userName);
		request.setAttribute("resname", resName);
		request.setAttribute("prostatus", proStatus);
		// 返回用户单位信息
		List<TbCheckMInfo> list = moneyManager.findAllCMInfo(pageNo, pageSize, userName, resName, proStatus, roleId,uId,checkRole);
//		List cmlist =new ArrayList();
		
		Map<Map, TbCheckMInfo> map =null;
		if(list !=null){
			map = new LinkedHashMap<Map, TbCheckMInfo>();
			for(int i =0 ; i<list.size();i++){
				Map m= new LinkedHashMap();
				TbCheckMInfo cminfo =list.get(i);
				if(cminfo.getResRole()==null){//终端
					
					m.put(i,cminfo.getResId());
					map.put(m, cminfo);
				}else{
					if(cminfo.getResRole().getRoleId() == 2){//代理商
						m.put(i, agentmanger.getAgent_ByAgentID(cminfo.getResId()).getAgent_Name());
						map.put(m, cminfo);
					}else if(cminfo.getResRole().getRoleId() == 3){//企业
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
	 * 返回操作信息
	 * log
	 */
	private void showMessage(HttpServletRequest request,String print,String resultmessage){
		System.out.println(print);
		request.setAttribute("message", resultmessage);
	}
}
