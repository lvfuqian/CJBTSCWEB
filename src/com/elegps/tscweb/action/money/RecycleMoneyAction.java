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
 *回收资金
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
			// 如果没有就接收到参数默认显示全部类型
			if (request.getParameter("CMD") == null || request.getParameter("CMD").equals("")) {
				cmd = "success";
			} else {
				cmd = request.getParameter("CMD");
			}
			
			//获取用户角色 
			int role_id =Integer.parseInt(request.getSession().getAttribute("roleId")+"");
			String mjsp = Money(role_id, user.getAgent_Id().toString(),user.getEp_Id().toString());//余额
			request.setAttribute("money",mjsp );//传回jsp页面
			
			if (cmd.equals("hs_ep_money_jsp")) { // 跳转到回收企业资金页面
				actionforward = hs_ep_money_jsp(mapping, form, request, response,
						role_id,user.getAgent_Id(),user.getEp_Id());
			}
			if (cmd.equals("hs_ms_money_jsp")) { // 跳转到终端资金转移页面
				actionforward = hc_ms_money_jsp(mapping, form, request, response,
						role_id,user.getAgent_Id().toString(),user.getEp_Id().toString());
			}
			if (cmd.equals("hs_ep_money")) { // 执行企业资金转移
				actionforward = hc_ep_money(mapping, form, request, response,role_id,user);
			}
			if (cmd.equals("hs_ms_money")) { // 执行终端资金转移
				actionforward = hs_ms_money(mapping, form, request, response,user);
			}
			
		}
		
		return actionforward;
	}

	/**
	 * 终端套餐转移执行
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
		String zyms = request.getParameter("zyms");//转移套餐的终端id
		String zdms = request.getParameter("zdms");//套餐转到的终端id
		
		Calendar c = Calendar.getInstance();//获得一个日历的实例  
		//SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		c.setTime(new Date());//设置日历时间   
		c.add(Calendar.MONTH,1);//在日历的月份上增加月
		Date date = c.getTime();//系统时间前1个月的时间
		
		//转移套餐的终端id必须是正常使用的，到期时间必须大于1个月
		TbMsInfo zymsinfo = msmanager.getTBMsinfoby_msid(zyms);
		if(zymsinfo == null ){
			showMessage(request, "终端"+zyms+"不存在！", "终端"+zyms+"不存在！");
			return mapping.findForward("hsms");
		}else if (zymsinfo.getFlag() == 0){
			showMessage(request, "终端"+zyms+"已经停用了flag=0！", "终端"+zyms+"已经停用了！");
			return mapping.findForward("hsms");
		}else if (zymsinfo.getMsMoneyTime().before(date)){
			showMessage(request, "终端"+zyms+"费用不足一个月！", "终端"+zyms+"费用不足一个月！");
			return mapping.findForward("hsms");
		}
		//套餐转到的终端id 必须欠费状态的终端
		TbMsInfo zdmsinfo = msmanager.getTBMsinfoby_msid(zdms);
		if(zdmsinfo == null ){
			String print ="终端"+zdms+"不存在！";
			String resultmessage="终端"+zdms+"不存在！";
			showMessage(request, print, resultmessage);
			return mapping.findForward("hsms");
		}else if(zdmsinfo.getMsMoneyTime()!=null){
			if(zdmsinfo.getMsMoneyTime().after(new Date())){
				showMessage(request, "终端"+zdms+"正常使用，套餐不能转到，有费状态！", "终端"+zdms+"正常使用，套餐不能转到！");
				return mapping.findForward("hsms");
			}
		}
		int zyep = zymsinfo.getEpid();
		int zdep = zdmsinfo.getEpid();
		if(zyep!=zdep){//不在同一企业
			showMessage(request, "终端不是同一企业！", "终端不是同一企业！");
			return mapping.findForward("hsms");
		}
		
		Timestamp zyDate = new Timestamp(zymsinfo.getMsMoneyTime().getTime());//转移套餐的到期时间
		
		//更新套餐转   移  的终端信息
		zymsinfo.setFlag(0);//冻结
		zymsinfo.setIs_Arrearage(0);//欠费
		//zymsinfo.setPf(pf);
		zymsinfo.setMsMoneyTime(new Date());//到期时间改为转移时间
		List<TbMsInfo> zymsList = new ArrayList<TbMsInfo>();
		zymsList.add(zymsinfo);
		Boolean zy = msmanager.updateMs(zymsList);//更新信息
		if(!zy){
			showMessage(request, "更新转移的终端"+zyms+"失败！", "套餐转移失败！");
			return mapping.findForward("hsms");
		}
		//更新套餐转   到   的终端信息
		zdmsinfo.setFlag(1);//正常使用
		zdmsinfo.setIs_Arrearage(1);//有费
		zdmsinfo.setMsMoneyTime(zyDate);//转移终端套餐的到期时间
		zdmsinfo.setPf(zymsinfo.getPf());//转移套餐类型
		List<TbMsInfo> zdmsList = new ArrayList<TbMsInfo>();
		zdmsList.add(zdmsinfo);
		Boolean zd = msmanager.updateMs(zdmsList);//更新信息
		if(!zd){
			showMessage(request, "更新转到的终端"+zyms+"失败！", "套餐转移失败！");
			return mapping.findForward("hsms");
		}
		
		TbMoneyLog ml=new TbMoneyLog();	
		ml.setHow("0.00");
		ml.setMlogTime(new Date());
		ml.setMoneyToUser("【"+zdmsinfo.getMsName()+"】" + zdmsinfo.getMsId());//套餐转到的终端ID
		ml.setUser(user);
		ml.setPayType(4);//充值类型  终端套餐转移：4
		ml.setPayNum(zymsinfo.getMsId()); //转移套餐的终端id
		Boolean aml = moneyManager.addMoneyLog(ml);
		if(!aml){
			showMessage(request, "终端套餐转移记录插入失败！", "记录失败！");
			return mapping.findForward("hsms");
		}
		showMessage(request, "终端套餐转移执行成功！", "终端套餐转移成功！");
		return mapping.findForward("hsms");
	}

	/**
	 * 终端套餐转移页面
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
		
//		if(!pagentid.equals("-1")){
//			// 一级代理商名称
//			request.setAttribute("Pagentlist", agentmanger.getParentAgentNotIncludeZB());
//		}else{
			// 一级代理商名称 request.setAttribute("Pagentlist", agentmanger.getParentAgent(agenttype,r_id));
			request.setAttribute("Pagentlist", agentmanger.getParentAgent(Integer.parseInt(agentid),roleId));
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
		
		return mapping.findForward("hsmsjsp");
	}

	/**
	 * 执行企业资金转移
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
		//获取回收的企业id
		String epid = request.getParameter("epid");
		if(epid != null && !epid.equals("")){
			TbEnterpriseInfo epInfo = epmanger.getEpinfo_byepid(epid);
			String epMoney = "0";
			if(epInfo.getEp_Money() != null && epInfo.getEp_Money()!="")
				epMoney = epInfo.getEp_Money();
			
			TbAgentInfo aInfo = agentmanger.getAgent_ByAgentID(epInfo.getAgent_Id()+"");
			if(aInfo.getAgent_Id() == -1){//直属企业不能回收
				String print ="直属企业不能回收！";
				String resultmessage="直属企业不能回收！";
				showMessage(request, print, resultmessage);
				return mapping.findForward("hsep");
			}
			if(roleId == 2){//代理商
				if(Double.parseDouble(epMoney) >= 0){
					Double newMoney = Double.parseDouble(aInfo.getAgent_Money()) + Double.parseDouble(epMoney);
					aInfo.setAgent_Money(newMoney+"");
					Boolean a = agentmanger.updateAgent(aInfo);
					if(a){
						
						epInfo.setEp_Money("0.00");
						Boolean ep =epmanger.updateEp(epInfo);
						if(ep){
							//数据库log记录
							String print ="回收企业余额成功！";
							String resultmessage="成功回收" + epMoney +"元";
							showMessage(request, print, resultmessage);
							
								TbMoneyLog ml=new TbMoneyLog();	
								ml.setHow(epMoney);
								ml.setMlogTime(new Date());
								ml.setMoneyToUser(user.getUserName());
								ml.setUser(user);
								ml.setPayType(3);//回收企业余额
								ml.setPayNum(epInfo.getEp_Id()+"");
							Boolean b = moneyManager.addMoneyLog(ml) ;//addLog(Double.parseDouble(epInfo.getEp_Money()),name,user);//添加log数据到数据库
				
							if(b){
								System.out.println("回收企业余额记录插入成功");
							}else{
								System.out.println("回收企业余额记录插入失败");
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
							//数据库log记录
							String print ="回收企业余额成功！给代理商："+aInfo.getAgent_Name()+"";
							String resultmessage="成功回收" + epMoney +"元到代理商：‘"+aInfo.getAgent_Name()+"’";
							showMessage(request, print, resultmessage);
							
								TbMoneyLog ml=new TbMoneyLog();	
								ml.setHow(epMoney);
								ml.setMlogTime(new Date());
								ml.setMoneyToUser(aInfo.getAgent_Name());
								ml.setUser(user);
								ml.setPayType(3);//回收企业余额
								ml.setPayNum(epInfo.getEp_Id()+"");
							Boolean b = moneyManager.addMoneyLog(ml) ;//addLog(Double.parseDouble(epInfo.getEp_Money()),name,user);//添加log数据到数据库
				
							if(b){
								System.out.println("回收企业余额记录插入成功");
							}else{
								System.out.println("回收企业余额记录插入失败");
							}
						}
					}
				}
			}
		}
		return mapping.findForward("hsep");
	}

	/**
	 * 跳转到回收企业资金页面（list分页显示）
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
		String pagentid = ""; // 一级代理商id
		String childagentid = ""; // 二级代理商id
		
		if (request.getParameter("pagentid") != null && (agentId == -1 || agentId == 0)) {
			pagentid = request.getParameter("pagentid");
		} else {
			//pagentid = "-1"; // 代理商是总部
			pagentid = agentId+"";
        	if(pagentid.equals("0"))
        		pagentid = "-1";
		}

		if (request.getParameter("childagentid") != null) {
			childagentid = request.getParameter("childagentid");
		} else {
			childagentid = "-2"; // 所有企业
		}
		String cmd = request.getParameter("CMD");
		String epname = request.getParameter("epname");
		if (epname == null || epname.equals("")) {
			epname = "";
		}
		
		int epCount = 0;
		epCount = epmanger.getEp_sertch(pagentid, childagentid, epname.trim(), epId, roleId);
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

		// 返回的总页数
		request.setAttribute("pageCount", pageCount);
		//if(!pagentid.equals("-1")){
			// 一级代理商名称
		//	request.setAttribute("Pagentlist", agentmanger.getParentAgentNotIncludeZB());
		//}else{
			// 一级代理商名称
			request.setAttribute("Pagentlist", agentmanger.getParentAgent(agentId,roleId));
		//}
		// 一级指定二级代理商结果集
		request.setAttribute("Cagentlist", agentmanger
				.getChildAgentByParamentid(pagentid));
		// 设设置返回用户查询的select中option值
		request.setAttribute("pagentid", pagentid);
		request.setAttribute("childagentid", childagentid);
		request.setAttribute("epname", epname);
		// 返回用户单位信息
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
	 * 充值添加记录
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
	 * 返回操作信息
	 * log
	 */
	private void showMessage(HttpServletRequest request,String print,String resultmessage){
		System.out.println(print);
		request.setAttribute("message", resultmessage);
	}
}
