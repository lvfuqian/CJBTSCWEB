<%
/* *
 功能：微信支付回调数据通知
**/
%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="java.util.Map"%>
<%@ page import="com.elegps.tscweb.tscconfig.FilePathConfig"%>
<%@ page import="com.wechatpay.demo.*"%>
<%@ page import="com.wechatpay.demo.WxPayResult"%>
<%@ page import="com.elegps.tscweb.model.TbUserInfo"%>
<%@ page import="com.elegps.tscweb.action.money.MoneyAction"%>
<%
	System.out.print("收到");
	WxPayResult wr = Notify.notify(request,response);//验证回调数据

	System.out.print(wr.getResultCode());
	//计算得出通知验证结果
	if("SUCCESS".equals(wr.getResultCode())){//验证成功
		//////////////////////////////////////////////////////////////////////////////////////////
		//请在这里加上商户的业务逻辑程序代码

		//——请根据您的业务逻辑来编写程序（以下代码仅作参考）——
			out.println("<br />微信扫码充值成功");
			//判断该笔订单是否在商户网站中已经做过处理
				//如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
				//如果有做过处理，不执行商户的业务程序
  				int r_id=Integer.parseInt(request.getSession().getAttribute("roleId").toString());
  				TbUserInfo user =(TbUserInfo)request.getSession().getAttribute("user");
  				MoneyAction  m = new MoneyAction();
  				Boolean b=m.zhiFuBaoMoney( r_id, user,Double.parseDouble(wr.getTotalFee()),2,wr.getOpenid(),wr.getTransactionId(),wr.getOutTradeNo());//插入记录log
  				if(b){
					out.println("商户订单号:"+wr.getOutTradeNo() +"<br />微信交易号:"
					+wr.getTransactionId() +"<br />交易状态" +wr.getReturnCode()+
					"<br />买家微信ID" +wr.getOpenid()+"/n交易金额" +wr.getTotalFee()
					+"<br />角色Id" +r_id+"<br />代理Id" +user.getAgent_Id()+"<br />企业Id" +user.getEp_Id() +"<br />商家系统充值成功！");
					out.println("<br />商家系统充值成功！");
					out.println("<a href=\"http://"+FilePathConfig.getServer_Ip()+"money.do?CMD=money_listinfojsp\">点击跳转页面 </a>");
					
				}else{
					out.println("<br />商家系统充值失败！");
				}
		
		//该页面可做页面美工编辑
		out.println("<br />");
		//——请根据您的业务逻辑来编写程序（以上代码仅作参考）——

		//////////////////////////////////////////////////////////////////////////////////////////
	}else{
		//该页面可做页面美工编辑
		out.println("微信扫码充值失败");
	}
%>