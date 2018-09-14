<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.elegps.tscweb.model.TbMsInfo"%>
<%@page import="com.elegps.tscweb.model.TbPFInfo"%>
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

  </head>
  
  <body  onload="goload()">
   <form id="msListForm" name="msListForm" method="post">
 <td background="images/gw_06.gif">&nbsp;</td>
    <td width="84%" valign="top"><table width="100%" height="363" border="0" cellpadding="0" cellspacing="0"> <tr>
        <td height="5">&nbsp;</td>
        <td valign="top"><table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
          <tr>
            <td width="3%" height="30" align="center" background="images/3_04.gif"><img src="images/arrow3.gif" width="20" height="19"></td>
            <td width="45%" background="images/3_04.gif"><span class="STYLE4">您现在的位置：<span class="STYLE7">终端套餐充值</span></span></td>
            <td width="52%"><img src="images/3_03.jpg" width="518" height="35"></td>
          </tr>
        </table> 
<table width="100%" border="0">
  <tr>
  	<td width="50%" align="left">
  	<%
		int rid = Integer.parseInt(request.getSession().getAttribute("roleId").toString());
		if(rid == 2 || rid ==4){
		 %>
		<span><strong>您当前余额：<%=request.getAttribute("money") %>&nbsp;元</strong></span>
		<br><br>   
		<%} %> 
	</td>
	<td width="5%" align="right">终端ID:</td>
    <td width="10%" align="left">
      <input style="width:128px;" type="text" name="ms_id" value=''/>
    </td> 
	<td width="5%" align="right">终端名称:</td>
    <td width="10%" align="left">
      <input style="width:128px;" type="text" name="ms_name" value=''/>
    </td> 
	<td width="2%" align="center"><span style="cursor:hand"><img src="images/bt_cx.gif" width="65" height="22"Onclick="ms_serach(document.forms[0])"/></span></td>
	<td width="2%" align="center">
      <span style="cursor:hand"></span></td>
  </tr>
</table>

<table width="100%" height="21" border="1" bordercolor="#C1DAD7">
  <tr>
    <td width="20%" align="center" class="list_header"><span class="STYLE77">终端ID</span></td>
    <td width="10%" align="center"  class="list_header"><span class="STYLE77">终端名称</span></td>
    <td width="25%" align="center" class="list_header"><span class="STYLE77">当前套餐</span></td>
    <td width="10%" align="center" class="list_header"><span class="STYLE77">到期日期</span></td>
     <td width="10%" align="center" class="list_header"><span class="STYLE77">操作</span></td>
  </tr>
  
  <logic:present name="msList" scope="request" >
   <% 
   List list =(List)request.getAttribute("msList");
   if(list!=null){
   	for (int i= 0; i < list.size(); i++) {
   		TbMsInfo msinfo =new TbMsInfo();
   		msinfo = (TbMsInfo)list.get(i);
				// Map map = new HashMap();
			   // map = (Map) list.get(i);
				%>
  					<tr>
  					
					<td align="center">
					  <font><span class="STYLE77"><%=msinfo.getMsId()%></span>
						</font>
					</td>
					<td align="center">
					  <font><span class="STYLE77"><%=msinfo.getMsName()%></span>
						</font>
					</td>
					<td align="center" bgcolor="#F5FAFA">
						<font><span class="STYLE77">
						<%=msinfo.getPf().getPfType() %>
						</span>
						</font>
					</td>
					<td align="center">
						<font><span class="STYLE77">
                                 <%=msinfo.getMsMoneyTime()%>
                                 </span>
						</font>
					</td>
                   <td align="center" bgcolor="#F5FAFA">
					  <font><a href="money.do?CMD=ms_money_infojsp&msid=<%=msinfo.getMsId()%>"><span class="STYLE77">充值</span></a>
						</font>
					</td>
                     </tr>
      <% } }%>
      
  
 <tr>
   <td align="right" colspan="7" class="list_footer">
         共有<%=request.getAttribute("msCount")%>条&nbsp;&nbsp;
		第<%=request.getAttribute("currentPage")%>页&nbsp;
		共<%=request.getAttribute("pageCount")%>页&nbsp;                                                                                                                                                                                    
		<a href="money.do?CMD=<%=request.getAttribute("CMD")%>&pageNo=1"><img src="images/bt_first.gif" width="42" height="16" border="0" align="absmiddle"></a>
		<a href="money.do?CMD=<%=request.getAttribute("CMD")%>&pageNo=<%=((Integer)request.getAttribute("currentPage")-1)%>"><img src="images/bt_pre.gif" width="38" height="16" border="0" align="absmiddle"></a>
		<a href="money.do?CMD=<%=request.getAttribute("CMD")%>&pageNo=<%=((Integer)request.getAttribute("currentPage")+1)%>"><img src="images/bt_next.gif" width="41" height="16" border="0" align="absmiddle"></a>
		<a href="money.do?CMD=<%=request.getAttribute("CMD")%>&pageNo=<%=request.getAttribute("pageCount")%>"><img src="images/bt_last.gif" width="42" height="16" border="0" align="absmiddle"></a>
		转到第<input type="text" size="2" name="gotopage" value='${requestScope.currentPage}'/>页<span style="cursor:hand"><img src="images/bt_go.gif" width="20" height="12" border="0" onClick="gotoye(document.forms[0])"/></span>			
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
  var mes="<%=request.getAttribute("message")%>";
  if(mes!="null"){
    alert(mes);
  }   
} 

<!--模糊查询按键  -->
function ms_serach(form){
    form.action="money.do?CMD=ms_money_listinfojsp";
	form.submit();
}


function gotoye(form){
    var page=this.msListForm.gotopage.value;
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
