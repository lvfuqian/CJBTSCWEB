<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ page import="com.elegps.tscweb.model.TbGpsInfo"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Map"%> 
<%@ page import="java.net.URLEncoder" %>
<%@ page import="java.lang.String" %>
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
            <td width="45%" background="images/3_04.gif"><span class="STYLE4">您现在的位置：<span class="STYLE7">GPS接入管理</span></span></td>
            <td width="52%"><img src="images/3_03.jpg" width="518" height="35"></td>
          </tr>
        </table>
         <% String gpsid=(String)request.getAttribute("gpsid");
          if(gpsid==null){
            gpsid="";
           }
           String gpsname=(String)request.getAttribute("gpsname");
           if(gpsname==null){
            gpsname="";
           }
         %>
<table width="100%" border="0">
  <tr>
    <td>
        &nbsp;&nbsp;&nbsp;GPS账号:<input style="width:160px;" type="text" name="gpsid" value="<%=gpsid%>"/>
    </td> 
    <td>
        GPS运营商名称:<input style="width:160px;" type="text" name="gpsname" value="<%=gpsname%>"/>
    </td>  
	<td width="8%" align="center"><span style="cursor:hand"><img src="images/bt_cx.gif" width="65" height="22" Onclick="gpsidserach(document.forms[0])"/></span></td>
    <td width="8%" align="center"><span style="cursor:hand"><img src="images/bt_add.gif" width="65" height="22" Onclick="gpsidadd()"/></span></td>
	<td width="8%" align="center">
      <span style="cursor:hand"><img src="images/bt_del.gif" width="65" height="22" Onclick="onSelect(document.forms[0])"></span>
    </td>
  </tr>
</table>
<br>



<table width="100%" height="21" border="1" bordercolor="#C1DAD7">
  <tr>
     <td width="3%" class="list_header" align="center">
     	<input class=btn type="button" name="selectall" value="全选"
							onClick="this.value=check(document.forms[0].list)">
    </td>
    <td width="22%" align="center" class="list_header"><span class="STYLE77">登录账号</span></td>
    <td width="25%" align="center" class="list_header"><span class="STYLE77">运营商名称</span></td>
    <td width="10%" align="center" class="list_header"><span class="STYLE77">登录时间</span></td>
	<td width="20%" align="center" class="list_header"><span class="STYLE77">操作</span></td>
  </tr>
  
  <logic:present name="gpsList" scope="request" >
   <% 
   List list =(List)request.getAttribute("gpsList");
   if(list!=null){
   	for (int i= 0; i < list.size(); i++) {
				 TbGpsInfo tbgpsinfo=new TbGpsInfo();
				  tbgpsinfo = (TbGpsInfo)list.get(i);
				%>
  					<tr>
					<td align="center">
						<input name="list" type="checkbox" value=<%=tbgpsinfo.getGpsop_id()%>>
					</td>
					<td align="center"  bgcolor="#F5FAFA">
					  <font><span class="STYLE77"><%=tbgpsinfo.getGpsop_id()%></span>
						</font>
					</td>
					<td align="center" >
					  <font><span class="STYLE77"><%=tbgpsinfo.getGps_name()%></span>
						</font>
					</td>
					<td align="center" bgcolor="#F5FAFA">
						<font><span class="STYLE77"><%=tbgpsinfo.getLogin_time()%></span>
						</font>
					</td>
                     <td align="center" >
                         <a href="gps.do?CMD=togpsmdoyijsp&gpsid=<%=tbgpsinfo.getGpsop_id()%>"><span class="STYLE17">修改</span></a>&nbsp;&nbsp;|&nbsp;&nbsp;<a href="gpsms.do?CMD=gpsms_search&gpsid=<%=tbgpsinfo.getGpsop_id()%>"><span class="STYLE17">终端列表</span></a>
                     </td>
                     </tr>
      <% } }%>
 <tr>
    <td align="right" colspan="6" class="list_footer">
         共有<%=request.getAttribute("gpsCount")%>条&nbsp;&nbsp;
		第<%=request.getAttribute("currentPage")%>页&nbsp;
		共<%=request.getAttribute("pageCount")%>页&nbsp;   
		<a href="gps.do?CMD=<%=request.getAttribute("CMD")%>&gpsid=<%=request.getAttribute("gpsid")%>&gpsname=<%=URLEncoder.encode(request.getAttribute("gpsname").toString(),"UTF-8")%>&pageNo=1"><img src="images/bt_first.gif" width="42" height="16" border="0" align="absmiddle"></a>
		<a href="gps.do?CMD=<%=request.getAttribute("CMD")%>&gpsid=<%=request.getAttribute("gpsid")%>&gpsname=<%=URLEncoder.encode(request.getAttribute("gpsname").toString(),"UTF-8")%>&pageNo=<%=((Integer)request.getAttribute("currentPage")-1)%>"><img src="images/bt_pre.gif" width="38" height="16" border="0" align="absmiddle"></a>
		<a href="gps.do?CMD=<%=request.getAttribute("CMD")%>&gpsid=<%=request.getAttribute("gpsid")%>&gpsname=<%=URLEncoder.encode(request.getAttribute("gpsname").toString(),"UTF-8")%>&pageNo=<%=((Integer)request.getAttribute("currentPage")+1)%>"><img src="images/bt_next.gif" width="41" height="16" border="0" align="absmiddle"></a>
		<a href="gps.do?CMD=<%=request.getAttribute("CMD")%>&gpsid=<%=request.getAttribute("gpsid")%>&gpsname=<%=URLEncoder.encode(request.getAttribute("gpsname").toString(),"UTF-8")%>&pageNo=<%=request.getAttribute("pageCount")%>"><img src="images/bt_last.gif" width="42" height="16" border="0" align="absmiddle"></a>
		转到第<input type="text" size="2" name="gotopage" value='${requestScope.currentPage}'/>页	<span style="cursor:hand"><img src="images/bt_go.gif" width="20" height="12" border="0" onClick="gotoye(document.forms[0])"/></span>		
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
		form.action="gps.do?CMD=delete";
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


function gpsidadd(){
	document.location.href="gps.do?CMD=addjsp";	
}

function gotoye(){
    var page=this.form1.gotopage.value;
 	if(!isNumber(page)){
     alert("输入跳转页数非法,请重新输入.(只能由数字组成)");
     return false;
     }
	document.location.href="gps.do?CMD=<%=request.getAttribute("CMD")%>&gpsid=<%=request.getAttribute("gpsid")%>&gpsname=<%=URLEncoder.encode(request.getAttribute("gpsname").toString(),"UTF-8")%>&pageNo="+page;
}

<!--模糊查询按键  -->
function gpsidserach(form){ 
   form.action="gps.do?CMD=gps_id_list";
   form.submit();
}

function goload() 
{ 
var mes="<%=request.getAttribute("message")%>";
  if(mes!="null"){
    alert(mes);
    document.location.href="gps.do?CMD=gps_id_list";
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


 
