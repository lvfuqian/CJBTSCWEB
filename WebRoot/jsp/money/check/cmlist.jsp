<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.elegps.tscweb.model.TbCheckMInfo"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

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
   <form id="cmListForm" name="cmListForm" method="post">
 <td background="images/gw_06.gif">&nbsp;</td>
    <td width="84%" valign="top"><table width="100%" height="363" border="0" cellpadding="0" cellspacing="0"> <tr>
        <td height="5">&nbsp;</td>
        <td valign="top"><table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
          <tr>
            <td width="3%" height="30" align="center" background="images/3_04.gif"><img src="images/arrow3.gif" width="20" height="19"></td>
            <td width="45%" background="images/3_04.gif"><span class="STYLE4">您现在的位置：<span class="STYLE7">费用申请信息</span></span></td>
            <td width="52%"><img src="images/3_03.jpg" width="518" height="35"></td>
          </tr>
        </table> 
<table width="60%" border="0" align="right">
  <tr>
  <%
	int rid =Integer.parseInt( request.getSession().getAttribute("roleId").toString());
	if (rid == 40){
   %>
	<td width="5%" align="right">申请人:</td>
    <td width="10%" align="left">
      <input style="width:128px;" type="text" name="username" id="username" value=''/>
    </td>
    <%} %> 
    <td width="5%" align="right">充值帐号:</td>
    <td width="10%" align="left">
      <input style="width:128px;" type="text" name="resname" id="resname" value=''/>
    </td>
    <td width="5%" align="right">审核状态:</td>
    <td width="10%" align="left">
      <select name="prostatus" id="prostatus"  style="{width:100;margin:0;FONT-SIZE:9pt;FONT-WEIGHT:normal;}">
      	<option value="-1">全部</option>
      	<option value="0">未审核</option>
      	<option value="1">通过</option>
      	<option value="2">不通过</option>
      </select>
    </td>
	<td width="2%" align="center"><span style="cursor:hand"><img src="images/bt_cx.gif" width="65" height="22"Onclick="user_nameserach(document.forms[0])"/></span></td>
  </tr>
</table>
<br>

<table width="100%" height="21" border="1" bordercolor="#C1DAD7">
  <tr>
  <%
    if (rid == 40){
   	%>
    <td width="10%" align="center" class="list_header"><span class="STYLE77">申请人</span></td>
    <%} %>
    <td width="24%" align="center" class="list_header"><span class="STYLE77">充值帐号</span></td>
    <td width="10%" align="center" class="list_header"><span class="STYLE77">充值金额（元）</span></td>
    <td width="10%" align="center" class="list_header"><span class="STYLE77">申请时间</span></td>
     <td width="8%" align="center" class="list_header"><span class="STYLE77">审核状态</span></td>
     <td width="10%" align="center" class="list_header"><span class="STYLE77">审核时间</span></td>
     <td width="8%" align="center" class="list_header"><span class="STYLE77">申请详细</span></td>
     <%
     if (rid == 40){
   	%> 
     <td width="10%" align="center" class="list_header"><span class="STYLE77">审核</span></td>
     <%} %>
  </tr>
  
  <logic:present name="cmList" scope="request" >
   <% 
   //List list =(List)request.getAttribute("cmList");
   Map<Map, TbCheckMInfo> map =(LinkedHashMap<Map, TbCheckMInfo>)request.getAttribute("cmList");
   for(Map.Entry<Map, TbCheckMInfo> entry:map.entrySet()){  
   		TbCheckMInfo cminfo=new TbCheckMInfo();
				cminfo = entry.getValue();
				
				String sm =cminfo.getResMoney();
				if(sm==null || sm==""){
					sm="0.00";
				}
				cminfo.setResMoney(sm);
				Map<Integer,String> m = entry.getKey();
				String resId ="";
				for(Object o:m.keySet()){ 
					resId=(String)m.get(o);
				}
				
				if(cminfo.getResRole() == null){
					if(cminfo.getResId().contains(",") && cminfo.getResId().length()>45){
						resId = cminfo.getResId().substring(0,25) + " ...... ";
					}else{
						resId = cminfo.getResId().substring(0,21);
					}
				}
				String bgcolor ="";
				if(cminfo.getProStatus()==0){
					bgcolor ="#7B7B7B";
				}
				if(cminfo.getProStatus()==1){
					bgcolor ="green";
				}
				if(cminfo.getProStatus()==2){
					bgcolor ="red";
				}
	%>
  					<tr>
  					<%
     					if (rid == 40){
   					%>
					<td align="center" bgcolor="#F5FAFA">
					  <font color='<%=bgcolor %>'><a href="#"><span><%=cminfo.getProposer().getUserName() %></span></a>
						</font>
					</td>
					<%} %>
					<td align="center">
						<font color='<%=bgcolor %>'><span><%=resId %></span>
						</font>
					</td>
					<td align="center" bgcolor="#F5FAFA">
					  <font  color='<%=bgcolor %>'><span><%=cminfo.getResMoney() %></span>
						</font>
					</td>
					<td align="center">
						<font color='<%=bgcolor %>'><span><%=cminfo.getProTime()%></span>
						</font>
					</td>
					<td align="center" bgcolor="#F5FAFA">
					  <font color='<%=bgcolor %>'><span>
					  		<%
					  			if(cminfo.getProStatus()==0){
					  		 %>
					  		 	待审核
					  		 <%}else if(cminfo.getProStatus()==1){ %>
					  			通过
					  		<%}else if(cminfo.getProStatus()==2){ %>
					  			不通过
					  		<%} %>
					  	</span>
						</font>
					</td>
					<td align="center">
					  <font color='<%=bgcolor %>'><span>
					  		<%
					  			if( cminfo.getCheckTime() == null){
					  		 %>
					  		 	--
					  		 <%}else{ %>
					  			<%=cminfo.getCheckTime() %>
					  		<%} %>
					  	</span>
						</font>
					</td>
					<td align="center" bgcolor="#F5FAFA">
					  <font color='<%=bgcolor %>'><span>
							<a href="check.do?CMD=cminfojsp&cmid=<%=cminfo.getFinId() %>"><span class="STYLE77">查看</span></a>
						</span>
						</font>
					</td>
				<%
     			if (rid == 40){
   				%> 
                   <td align="center">
					  <font color='<%=bgcolor %>'>
					  
					  <a href="check.do?CMD=money_check_ok&cmid=<%=cminfo.getFinId() %>"><span class="STYLE77">通过</span></a>&nbsp;&nbsp;|&nbsp;&nbsp;
					  <a href="check.do?CMD=money_check_no&cmid=<%=cminfo.getFinId() %>"><span class="STYLE77">不通过</span></a>
						</font>
					</td>
				<%} %>
              	</tr>
      <% }%>
      
  
 <tr>
   <td align="right" colspan="8" class="list_footer">
         共有<%=request.getAttribute("cmCount")%>条&nbsp;&nbsp;
		第<%=request.getAttribute("currentPage")%>页&nbsp;
		共<%=request.getAttribute("pageCount")%>页&nbsp;                                                                                                                                                                                    
		<a href="check.do?CMD=<%=request.getAttribute("CMD")%>&username=<%=URLEncoder.encode(request.getAttribute("username").toString(),"UTF-8")%>&resname=<%=URLEncoder.encode(request.getAttribute("resname").toString(),"UTF-8")%>&prostatus=<%=request.getAttribute("prostatus")%>&pageNo=1"><img src="images/bt_first.gif" width="42" height="16" border="0" align="absmiddle"></a>
		<a href="check.do?CMD=<%=request.getAttribute("CMD")%>&username=<%=URLEncoder.encode(request.getAttribute("username").toString(),"UTF-8")%>&resname=<%=URLEncoder.encode(request.getAttribute("resname").toString(),"UTF-8")%>&prostatus=<%=request.getAttribute("prostatus")%>&pageNo=<%=((Integer)request.getAttribute("currentPage")-1)%>"><img src="images/bt_pre.gif" width="38" height="16" border="0" align="absmiddle"></a>
		<a href="check.do?CMD=<%=request.getAttribute("CMD")%>&username=<%=URLEncoder.encode(request.getAttribute("username").toString(),"UTF-8")%>&resname=<%=URLEncoder.encode(request.getAttribute("resname").toString(),"UTF-8")%>&prostatus=<%=request.getAttribute("prostatus")%>&pageNo=<%=((Integer)request.getAttribute("currentPage")+1)%>"><img src="images/bt_next.gif" width="41" height="16" border="0" align="absmiddle"></a>
		<a href="check.do?CMD=<%=request.getAttribute("CMD")%>&username=<%=URLEncoder.encode(request.getAttribute("username").toString(),"UTF-8")%>&resname=<%=URLEncoder.encode(request.getAttribute("resname").toString(),"UTF-8")%>&prostatus=<%=request.getAttribute("prostatus")%>&pageNo=<%=request.getAttribute("pageCount")%>"><img src="images/bt_last.gif" width="42" height="16" border="0" align="absmiddle"></a>
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
  var resname = "<%=request.getAttribute("resname") %>";
  if(<%=rid %> == 40){
 	  var username = "<%=request.getAttribute("username") %>";
	  if(username != "null"){
	  	document.getElementById("username").value=username;
	  }
  }
  if(resname!="null"){
  	document.getElementById("resname").value=resname;
  }
  
  var proStatus = "<%=request.getAttribute("prostatus") %>";
  jsSelectItemByValue(cmListForm.prostatus,proStatus);
  
  var mes="<%=request.getAttribute("message")%>";
  if(mes!="null"){
    alert(mes);
  }
  
} 

function jsSelectItemByValue(objSelect,objItemText) {  
   for(var i=0;i<objSelect.options.length;i++) {  
            if(objSelect.options[i].value == objItemText) {  
               objSelect.options[i].selected = true;  
               break;  
            }  
        }  
} 


<!--模糊查询按键  -->
function user_nameserach(form){
    form.action="check.do?CMD=<%=request.getAttribute("CMD") %>";
	form.submit();
}


function gotoye(form){
    var page=this.cmListForm.gotopage.value;
    if(!isNumber(page)){
     alert("输入跳转页数非法,请重新输入.(只能由数字组成)");
     return false;
     }
    form.action= "check.do?CMD=<%=request.getAttribute("CMD")%>&pageNo="+page;
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
