<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ page import="com.elegps.tscweb.model.TbRoleInfo"%>
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
  
  <body onload="goload()">
   <form id="form1" name="form1" method="post">
 <td background="images/gw_06.gif">&nbsp;</td>
    <td width="84%" valign="top"><table width="100%" height="363" border="0" cellpadding="0" cellspacing="0"> <tr>
        <td height="5">&nbsp;</td>
        <td valign="top"><table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
          <tr>
            <td width="3%" height="30" align="center" background="images/3_04.gif"><img src="images/arrow3.gif" width="20" height="19"></td>
            <td width="45%" background="images/3_04.gif"><span class="STYLE4">您现在的位置：<span class="STYLE7">角色管理</span></span></td>
            <td width="52%"><img src="images/3_03.jpg" width="518" height="35"></td>
          </tr>
        </table>
<table width="100%" border="0" >
 
	<tr>
	  <td width="6%" align="right">角色名称：</td>
    <td width="10%" align="left">
      <input style="width:160px;" type="text" name="role_name" value='${requestScope.role_name}'/>
    </td> 
   
	<td width="2%" align="center"><span style="cursor:hand"><img src="images/bt_cx.gif" width="65" height="22" Onclick="roleserach(document.forms[0])"></span></td>
    <td width="2%" align="center"><span style="cursor:hand"><img src="images/bt_add.gif" width="65" height="22" Onclick="roleadd()"></span></td>
	<td width="2%" align="center">
      <span style="cursor:hand"><img src="images/bt_del.gif" width="65" height="22"  Onclick="onSelect(document.forms[0])"></span>
    </td>
  </tr>
</table>
<br>

<table width="100%" height="21" border="1" bordercolor="#C1DAD7">
  <tr height="12">
    <td width="5%" class="list_header" align="center">
     	<input class=btn type="button" name="selectall" value="全选"
							onClick="this.value=check(document.forms[0].list)">
    </td>
 <!-- <td width="22%" align="center" class="list_header"><span class="STYLE77">角色ID</span></td>  -->
    <td width="22%" align="center" class="list_header"><span class="STYLE77">角色名称</span></td>
	<td width="15%" align="center" class="list_header"><span class="STYLE77">修改</span></td>
  </tr>
  
  <logic:present name="roleList" scope="request" >
  
   <% 
   List list =(List)request.getAttribute("roleList");
   if(list!=null){
  	 for (int i= 0; i < list.size(); i++) {
				 TbRoleInfo tbroleinfo=new TbRoleInfo();
				  tbroleinfo = (TbRoleInfo)list.get(i);
				%>
  					<tr>
					<td align="center">
						<input id="list1" name="list" type="checkbox" value=<%=tbroleinfo.getRoleId()%>>
					</td>
				<!--	<td align="center" bgcolor="#F5FAFA">
					  <font><span class="STYLE77"><%=tbroleinfo.getRoleId()%></span>
						</font>
					</td>    -->
					<td bgcolor="#F5FAFA">
						<font><span class="STYLE77"><%=tbroleinfo.getRoleName()%></span>
						</font>
					</td>
                     <td align="center">
                         <a href="role.do?CMD=torolemdoyijsp&type=<%=tbroleinfo.getRoleId()%>"><span class="STYLE17">修改</span></a>
                     </td>
                     </tr>
      <% } }%>
      
  
 <tr>
    <td align="right" colspan="6" class="list_footer">
 
         共有<%=request.getAttribute("roleCount")%>条&nbsp;&nbsp;
		第<%=request.getAttribute("currentPage")%>页&nbsp;
		共<%=request.getAttribute("pageCount")%>页&nbsp;   
		<a href="role.do?CMD=<%=request.getAttribute("CMD")%>&role_name=<%=URLEncoder.encode(request.getAttribute("role_name").toString(),"UTF-8")%>&pageNo=1"><img src="images/bt_first.gif" width="42" height="16" border="0" align="absmiddle"></a>
		<a href="role.do?CMD=<%=request.getAttribute("CMD")%>&role_name=<%=URLEncoder.encode(request.getAttribute("role_name").toString(),"UTF-8")%>&pageNo=<%=((Integer)request.getAttribute("currentPage")-1)%>"><img src="images/bt_pre.gif" width="38" height="16" border="0" align="absmiddle"></a>
		<a href="role.do?CMD=<%=request.getAttribute("CMD")%>&role_name=<%=URLEncoder.encode(request.getAttribute("role_name").toString(),"UTF-8")%>&pageNo=<%=((Integer)request.getAttribute("currentPage")+1)%>"><img src="images/bt_next.gif" width="41" height="16" border="0" align="absmiddle"></a>
		<a href="role.do?CMD=<%=request.getAttribute("CMD")%>&role_name=<%=URLEncoder.encode(request.getAttribute("role_name").toString(),"UTF-8")%>&pageNo=<%=request.getAttribute("pageCount")%>"><img src="images/bt_last.gif" width="42" height="16" border="0" align="absmiddle"></a>
		转到第<input type="text" size="2" name="gotopage" value='${requestScope.currentPage}'/>页<span style="cursor:hand"><img src="images/bt_go.gif" width="20" height="12" border="0" onClick="gotoye(document.forms[0])"/></span>		
  </td>                                                                                      
  </tr>
  </logic:present>
</table>
  
  </body>
</html>

<script language="javascript">

var checkflag = "false";
function check(field) {
     if(field==null){
         return "全选";
        }
	if (checkflag == "false")
	 {  
		for (i = 0; i < field.length; i++)
		{
			field[i].checked = true;
		}
			checkflag = "true";
			return "取消"; 
	}else {
		for (i = 0; i < field.length; i++) 
		{
			field[i].checked = false; 
		}
		checkflag = "false";
		return "全选"; 
	}
}

function onSelect(form){
if (!dataIsAvail(form))   
		return;
		if (confirm("确定删除所选的内容吗?"))
		{  	
		form.action="role.do?CMD=delete";
		form.submit();
	  }  
	  return;
}

function dataIsAvail(form)
	{
	  var chkbox = form.list;
	  var isChecked=false;
	  if (chkbox == null)
	  {
		alert("未选中内容，不能删除！");
		return false;
	  }
	  else
	  {
		var chkLength = chkbox.length;
	
		if(chkLength == null)
		{
		  if (chkbox.checked)
			isChecked=true;
		}
		else
		{
		  for(i=0;i<chkLength;i++)
		  {
			if(chkbox[i].checked)
			{
			  isChecked = true;
			  break;
			}
		  }
		}
		if(isChecked==false)
		{
		  alert("未选中内容，不能删除！");
		  return false;
		}
		else
		  return true;
	  }
	}

<!--模糊查询按键  -->
function roleserach(form){
    form.action="role.do?CMD=role_search";
	form.submit();
}

function roleadd(){
  document.location.href="role.do?CMD=role_addjsp";
}

function gotoye(form){
   var page=this.form1.gotopage.value;
   if(!isNumber(page)){
     alert("输入跳转页数非法,请重新输入.(只能由数字组成)");
     return false;
     }
    form.action="role.do?CMD=<%=request.getAttribute("CMD")%>&role_name=<%=URLEncoder.encode(request.getAttribute("role_name").toString(),"UTF-8")%>&pageNo="+page;
	form.submit();   
}



<!-- 装入上次查询用户类型查询、在线状态查询、用户有效状态查询的装态 ->
function goload() 
{ 
  var mes="<%=request.getAttribute("message")%>";
  if(mes!="null"){
    alert(mes);
  }   
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
