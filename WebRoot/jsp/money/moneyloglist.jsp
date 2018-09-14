<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.elegps.tscweb.model.TbMoneyLog"%>
<%@page import="com.elegps.tscweb.serivce.EnterPriseManager"%>
<%@page import="com.elegps.tscweb.serivce.impl.EnterPriseManagerImpl"%>
<%@page import="com.elegps.tscweb.serivce.MsManager"%>
<%@page import="com.elegps.tscweb.serivce.impl.MsManagerImpl"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ page import="com.elegps.tscweb.model.TbAgentInfo"%>
<%@ page import="com.elegps.tscweb.model.TbEnterpriseInfo"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.lang.String" %>
<%@ page import="java.net.URLEncoder" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<style type="text/css">
.btn {
BORDER-RIGHT: #7b9ebd 1px solid; 
PADDING-RIGHT: 2px; 
BORDER-TOP: #7b9ebd 1px solid; 
PADDING-LEFT: 2px; 
FONT-SIZE: 12px; 
FILTER: progid:DXImageTransform.Microsoft.Gradient(GradientType=0, StartColorStr=#ffffff, EndColorStr=#cecfde); 
BORDER-LEFT: #7b9ebd 1px solid; CURSOR: hand; COLOR: black; PADDING-TOP: 2px; BORDER-BOTTOM: #7b9ebd 1px solid
}
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
-->
</style>
<style type="text/css">
<!--
@import url("css/top.css");
.STYLE4 {
	font-size: 12px;
	color: #333333;
}
.STYLE7 {
	color: #FF3300;
	font-weight: bold;
}
.STYLE77 {
	color: #000066;
	font-size: 12px;
}
.STYLE17 {
	color: #993300;
	
}
a:active {
	font-size: 12px;
	color: #0099FF;
	font-weight: normal;
}
.STYLE8 {font-size: 12px}
a:hover {
	font-size: 12px;
	color: #CC00FF;
	text-decoration: underline;
}
-->
</style> 
  <script type='text/javascript' src='dwr/interface/ChildAgent.js'></script>
  <script type='text/javascript' src='dwr/engine.js'></script>
  <script type='text/javascript' src='dwr/util.js'></script>
<link href="css/manage.css" rel="stylesheet" type="text/css"/>
<link href="css/list.css" rel="stylesheet" type="text/css"/>
<style type="text/css">
<!--
.STYLE9 {
	color: #000000;
	font-weight: bold;
	text-indent: 10px;
}
-->
</style> 
    <base href="<%=basePath%>">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script language="javascript" type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
		<script language="javascript" type="text/javascript" src="js/My97DatePicker/WdatePicker.js"></script>
  </head>
  
  <body  onload="goload()">
   <form id="mlListForm" name="mlListForm" method="post">
 <td background="images/gw_06.gif">&nbsp;</td>
    <td width="84%" valign="top"><table width="100%" height="363" border="0" cellpadding="0" cellspacing="0"> <tr>
        <td height="5">&nbsp;</td>
        <td valign="top"><table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
          <tr>
            <td width="3%" height="30" align="center" background="images/3_04.gif"><img src="images/arrow3.gif" width="20" height="19"></td>
            <td width="45%" background="images/3_04.gif"><span class="STYLE4">您现在的位置：<span class="STYLE7">充值记录查询</span></span></td>
            <td width="52%"><img src="images/3_03.jpg" width="518" height="35"></td>
          </tr>
        </table> 
<table width="99%" border="0" >
  <tr>
  	<td width="20%" align="left"  style="padding-top: 30px; padding-bottom:20px; ">
  	<%
		int rid = Integer.parseInt(request.getSession().getAttribute("roleId").toString());
		if(rid == 2 || rid ==4){
		 %>
		<span><strong>您当前余额：<%=request.getAttribute("money") %>&nbsp;元</strong></span>
		<br><br>   
		<%} %> 
  	</td>
  	<%
  	if(rid != 2 && rid !=4){
  	 %>
	<td width="5%" align="right">充值人:</td>
    <td width="10%" align="left">
      <input style="width:128px;" type="text" name="uname" value='<%=request.getAttribute("uname") %>'/>
    </td> 
    <%} %>
    <td width="6%" align="right">充值方式:</td>
    <td width="10%" align="left">
    <select name="ptype" id="ptype" style="width: 100; margin: 0; FONT-SIZE: 9pt; FONT-WEIGHT: normal;">
			<option value="0">余额充值</option>
			<option value="3">回收企业余额</option>
			<option value="4">终端套餐转移</option>
			<option value="1">支付宝</option>
			<option value="2">微信</option>
	</select>
    </td>
    <td width="6%" align="right">充值时间:</td>
    <td width="32%" align="left">
    <input id="date1"  name="date1" type="text" class="Wdate" onFocus="WdatePicker({lang:'zh-cn',dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:'true'})" size="19" value='<%=request.getAttribute("date1") %>'/>
    --<input id="date2"  name="date2" type="text" class="Wdate" onFocus="WdatePicker({lang:'zh-cn',dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:'true'})" size="19" value='<%=request.getAttribute("date2") %>'/><!--
      <input style="width:128px;" type="text" name="date1" value=''/> &nbsp;--&nbsp;
      <input style="width:128px;" type="text" name="date2" value=''/>--> 
    </td> 
	<td width="2%" align="center"><span style="cursor:hand"><img src="images/bt_cx.gif" width="65" height="22"Onclick="u_nameserach(document.forms[0])"/></span></td>
  </tr>
  <tr>
  <td width="20%" align="left"></td>
  	<% 
    	int ptype = Integer.parseInt(request.getAttribute("ptype").toString());
    	String searchname = "";
    	if(ptype != 0){
    		if(ptype == 1){
    			searchname ="支付宝帐号";
    		}else if(ptype == 2){
    			searchname ="微信帐号";
    		}else if(ptype == 3){
    			searchname ="回收余额企业名";
    		}else if(ptype == 4){
    			searchname ="转移终端ID";
    		}
    %>
    
    
  	<td width="10%" align="right"><%=searchname %>:</td>
    <td width="10%" align="left">
      <input style="width:128px;" type="text" name="pay_num" value='<%=request.getAttribute("pay_num") %>'/>
    </td>
    <%
    	if(ptype == 1 || ptype ==2){
     %>	
    <td width="10%" align="right">第三方交易号:</td>
    <td width="10%" align="left">
      <input style="width:128px;" type="text" name="teadeNo" value='<%=request.getAttribute("teadeNo") %>'/>
    </td>
    <td width="10%" align="right">订单号:</td>
    <td width="10%" align="left">
      <input style="width:128px;" type="text" name="orderNo" value='<%=request.getAttribute("orderNo") %>'/>
    </td>
    <%}} %>
  </tr>
</table>
<br>



<table width="99%" height="21" border="1" bordercolor="#C1DAD7">
  <tr>
  	<td width="10%" align="center" class="list_header"><span class="STYLE77">充值人</span></td>
    <td width="15%" align="center" class="list_header"><span class="STYLE77">充值帐号</span></td>
    <td width="5%" align="center"  class="list_header"><span class="STYLE77">充值金额</span></td>
    <td width="10%" align="center" class="list_header"><span class="STYLE77">充值时间</span></td>
    <td width="10%" align="center" class="list_header"><span class="STYLE77">充值方式</span></td>
    <% 
    	//int ptype = Integer.parseInt(request.getAttribute("ptype").toString());
    	String showname = "";
    	if(ptype != 0){
    		if(ptype == 1){
    			showname ="支付宝帐号";
    		}else if(ptype == 2){
    			showname ="微信帐号";
    		}else if(ptype == 3){
    			showname ="回收余额企业";
    		}else if(ptype == 4){
    			showname ="转移终端";
    		}
    %>
    <td width="15%" align="center" class="list_header"><span class="STYLE77"><%=showname %></span></td>
    
    <%
    	if(ptype == 1 || ptype == 2){
    %>
    <td width="10%" align="center" class="list_header"><span class="STYLE77">第三方交易号</span></td>
    <td width="10%" align="center" class="list_header"><span class="STYLE77">订单号</span></td>
    <%}} %>
  </tr>
  
  <logic:present name="mlList" scope="request" >
   <% 
   List list =(List)request.getAttribute("mlList");
   if(list!=null){
   	for (int i= 0; i < list.size(); i++) {
				 TbMoneyLog mlinfo=new TbMoneyLog();
				  mlinfo = (TbMoneyLog)list.get(i);
				  String m =mlinfo.getHow();
				  if(m==null || m==""){
				  	m="0.00";
				  	mlinfo.setHow(m);
				  }
				  String pt = "余额充值";
				  String dsfname ="";
				  if(mlinfo.getPayType() == 1){
				  	pt="支付宝";
				  	dsfname = mlinfo.getPayNum();
				  }else if(mlinfo.getPayType() == 2){
				 	pt="微信";
				 	dsfname = mlinfo.getPayNum();
				  }else if(mlinfo.getPayType() == 3){
				 	pt="回收";
				 	EnterPriseManager epm = new EnterPriseManagerImpl();
				 	dsfname = epm.getEpinfo_byepid(mlinfo.getPayNum()).getEp_Name();
				  }else if(mlinfo.getPayType() == 4){
				 	pt="套餐转移";
				 	MsManager msm = new MsManagerImpl();
				 	dsfname = "【" + msm.getTBMsinfoby_msid(mlinfo.getPayNum()).getMsName() + "】" + mlinfo.getPayNum() ;
				  }
				%>
  					<tr>
  					<td align="center" bgcolor="#F5FAFA">
						<font><span class="STYLE77"><%=mlinfo.getUser().getUserName()%></span>
						</font>
					</td>
					<td align="center">
					  <font><span class="STYLE77"><%=mlinfo.getMoneyToUser()%></span>
						</font>
					</td>
					<td align="center" bgcolor="#F5FAFA">
						<font><span class="STYLE77"><%=mlinfo.getHow()%></span>
						</font>
					</td>
					<td align="center">
					  <font><span class="STYLE77"><%=mlinfo.getMlogTime()%></span>
						</font>
					</td>
					<!-- ==== -->
					<td align="center" bgcolor="#F5FAFA">
						<font><span class="STYLE77"><%=pt%></span>
						</font>
					</td>
					<%
						if(ptype != 0){
					 %>
					<td align="center">
					  <font><span class="STYLE77"><%=dsfname %></span>
						</font>
					</td>
					<%
						if(ptype == 1 || ptype == 2){
					 %>
					<td align="center" bgcolor="#F5FAFA">
						<font><span class="STYLE77"><%=mlinfo.getTeadeNo()%></span>
						</font>
					</td>
					<td align="center">
					  <font><span class="STYLE77"><%=mlinfo.getMoney_Trade_No()%></span>
						</font>
					</td>
					<%
					}}
					 %>
					</tr>
      <% } }%>
      
  
 <tr>
   <td align="right" colspan="8" class="list_footer">
         共有<%=request.getAttribute("mlCount")%>条&nbsp;&nbsp;
		第<%=request.getAttribute("currentPage")%>页&nbsp;
		共<%=request.getAttribute("pageCount")%>页&nbsp;                                                                                                                                                                            
		<img src="images/bt_first.gif" width="42" height="16" border="0" align="absmiddle"  onClick="return pageclick('1');">
		<img src="images/bt_pre.gif" width="38" height="16" border="0" align="absmiddle"  onClick="return pageclick('<%=((Integer)request.getAttribute("currentPage")-1)%>');">
		<img src="images/bt_next.gif" width="41" height="16" border="0" align="absmiddle"  onClick="return pageclick('<%=((Integer)request.getAttribute("currentPage")+1)%>');">
		<img src="images/bt_last.gif" width="42" height="16" border="0" align="absmiddle"   onClick="return pageclick('<%=request.getAttribute("pageCount")%>');">
		转到第<input type="text" size="2" name="gotopage" value='${requestScope.currentPage}'/>页<span style="cursor:hand"><img src="images/bt_go.gif" width="20" height="12" border="0" onClick="gotoye(document.forms[0])"  align="absmiddle"/></span>			
  </td>                                                                                      
  </tr>
  </logic:present>
</table>
  
  </body>
</html>

<script language="javascript">

<!-- 装入上次查询用户类型查询、在线状态查询、用户有效状态查询的装态 ->
function goload() 
{ 
	var ptselect = "<%=request.getAttribute("ptype")%>";
	document.getElementById("ptype").value=ptselect;
	
  var mes="<%=request.getAttribute("message")%>";
  if(mes!="null"){
    alert(mes);
  }   
} 

function pageclick(page){
    mlListForm.action="money.do?CMD=moneylog&pageNo="+page;
	mlListForm.submit();
}

<!--模糊查询按键  -->
function u_nameserach(form){
    form.action="money.do?CMD=moneylog";
	form.submit();
}


function gotoye(form){
    var page=this.mlListForm.gotopage.value;
    if(!isNumber(page)){
     alert("输入跳转页数非法,请重新输入.(只能由数字组成)");
     return false;
     }
    form.action="money.do?CMD=<%=request.getAttribute("CMD")%>&pageNo="+page;
	form.submit();  
}

function isNumber(yeid)
{
	var charcode;
	for (var i=0; i<yeid.length; i++)	
	{
		charcode = yeid.charCodeAt(i);
		if (charcode < 48 || charcode > 57)	
			return false;
	}
	return true;
}

</script>
