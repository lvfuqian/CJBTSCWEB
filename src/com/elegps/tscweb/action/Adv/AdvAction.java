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
			// 如果没有就接收到参数默认显示全部类型
			if (request.getParameter("CMD") == null || request.getParameter("CMD").equals("")) {
				cmd = "adv_search";
			} else {
				cmd = request.getParameter("CMD");
			}
			
			if (cmd.equals("advaddjsp")) { // 添加广告jsp页面
				actionforward = advaddjsp(mapping, form, request, response);
			}
			if (cmd.equals("advadd")) { //添加广告执行
				try {
					actionforward = advadd(mapping, form, request, response);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (cmd.equals("advupdatejsp")) { // 修改广告jsp页面
				actionforward = advupdatejsp(mapping, form, request, response);
			}
			if (cmd.equals("advupdate")) { //修改广告执行
				actionforward = advupdate(mapping, form, request, response);
			}
			if (cmd.equals("advlist")) { //广告列表页面
				actionforward = advlist(mapping, form, request, response);
			}
			if (cmd.equals("advdelete")) { //广告删除操作
				actionforward = advdelete(mapping, form, request, response);
			}
			if (cmd.equals("advyulan")) { //广告预览操作
				actionforward = advyulan(mapping, form, request, response);
			}
			if (cmd.equals("yulanadd")) { //广告预览页面添加操作
				actionforward = yulanadd(mapping, form, request, response);
			}
			if (cmd.equals("advshowjsp")) { //手机app显示广告页面
				actionforward = advshowjsp(mapping, form, request, response);
			}
			
			
		} else {
			request.setAttribute("message", "Session过期，请重新登录");
			actionforward = mapping.findForward("logging");
		}
		if (actionforward == null) {
			return mapping.findForward("success");
		}
		return actionforward;
	}

	private ActionForward advshowjsp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		//app传终端id、广告类型，pc-TSCWEB传广告id
		
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
		if(advId == "" || advId == null ){//app请求
			list = advManager.findAdvInfoByPage(1, 5, "", "", sdf.format(new Date()), "", "", "",advType);
		}else{//TSCWEB请求
			TbAdvInfo adv = advManager.findAdvInfoById(advId);
			list.add(adv);
		}
		request.setAttribute("advlist", list);
		if(advType == 0){
			return mapping.findForward("advshoww");//文字广告页面
		}else if (advType == 1){
			return mapping.findForward("advshowp");//图片广告页面
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
			System.out.println("请选择广告类型！");
			return null;
		}
		if(advTitle.equals("")||advTitle.equals(null)){
			System.out.println("请输入广告标题！");
			return null;
		}
		if(sendSTime.equals(null) || sendSTime.equals("")){
			System.out.println("请输入显示开始日期！");
			return null;
		}
		if(sendETime.equals(null) || sendETime.equals("")){
			System.out.println("请输入显示结束日期！");
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
			System.out.println("添加广告失败");
			request.setAttribute("message", "发布失败！");
		}else{
			System.out.println("添加广告成功Id："+advId);
			request.setAttribute("message", "发布成功！");
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
			System.out.println("请选择广告类型！");
			return null;
		}
		if(advTitle.equals("")||advTitle.equals(null)){
			System.out.println("请输入广告标题！");
			return null;
		}
		if(sendSTime.equals(null) || sendSTime.equals("")){
			System.out.println("请输入显示开始日期！");
			return null;
		}
		if(sendETime.equals(null) || sendETime.equals("")){
			System.out.println("请输入显示结束日期！");
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
            //记录操作日志
            TbUserLog userLog = new TbUserLog();
            userLog.setUserId(Integer.parseInt(request.getSession().getAttribute("uId").toString()));
            userLog.setlDate(new Date());
            userLog.setlAddress(HRAddress.getIpAddr(request));
            userLog.setlType(3);
            userLog.setlContent("删除广告{" + list[i] + "}");
            logManager.save(userLog);
        }
        String resultmessage = null;
        if (delsucc) {
            resultmessage = "广告信息删除成功！";
            request.setAttribute("message", resultmessage);
            return mapping.findForward("toadvlist");
        } else {
            resultmessage = "广告信息删除失败！";
            request.setAttribute("message", resultmessage);
            return mapping.findForward("toadvlist");
        }
	}

	/**
	 * 进入广告列表页面
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
		 //获取查询数量
	 	advCount = advManager.findAdvCount(title, url, sendSTime, sendETime, 
	 			creatSTime, creatETime,advType);
        // 获取每页的条数
	 	String ps =request.getParameter("pageSize");
		int pageSize = 10;
		if(ps!= null){
			pageSize =Integer.parseInt(ps);
		}
        // 获取总页数
	 	Integer pageCount = (advCount + pageSize - 1) / pageSize;
        // 从页面取得当前页
	 	Integer pageNo;
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
        request.setAttribute("advCount", advCount);
        // 返回每页面显示条数
        request.setAttribute("pageSize", pageSize);
        // 返回当前页
        request.setAttribute("currentPage", pageNo);
        // 设置返回的命令字
        request.setAttribute("CMD", cmd);
        // 返回的总页数
        request.setAttribute("pageCount", pageCount);
        //获取广告list数据
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
			System.out.println("advId空");
			request.setAttribute("message", "数据异常，修改失败!");
			return mapping.findForward("toadvlist");
		}
		TbAdvInfo adv = advManager.findAdvInfoById(advId);
		if(adv == null){
			System.out.println("不存在");
			request.setAttribute("message", "数据异常，修改失败!");
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
				System.out.println("修改成功");
				request.setAttribute("message", "修改成功!");
				return mapping.findForward("toadvlist");
			}else{
				System.out.println("修改失败");
				request.setAttribute("message", "修改失败!");
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
			System.out.println("信息不存在");
			request.setAttribute("message", "信息不存在!");
			return mapping.findForward("toadvlist");
		}
		
		request.setAttribute("advinfo", advInfo);
		return mapping.findForward("advmodyjsp");
	}

	/**
	 * 添加广告操作执行
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
			System.out.println("请选择广告类型！");
			return null;
		}
		if(advTitle.equals("")||advTitle.equals(null)){
			System.out.println("请输入广告标题！");
			return null;
		}
		if(sendSTime.equals(null) || sendSTime.equals("")){
			System.out.println("请输入显示开始日期！");
			return null;
		}
		if(sendETime.equals(null) || sendETime.equals("")){
			System.out.println("请输入显示结束日期！");
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
			System.out.println("添加广告失败");
			request.setAttribute("message", "发布失败！");
		}else{
			System.out.println("添加广告成功Id："+advId);
			request.setAttribute("message", "发布成功！");
		}
		return mapping.findForward("toadvlist");
	}

	/**
	 * 添加广告jsp
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
