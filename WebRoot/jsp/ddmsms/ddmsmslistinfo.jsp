<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ page import="com.elegps.tscweb.model.TbDdmsMsListInfo"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.lang.String" %> 
<%@ page import="com.elegps.tscweb.model.TbAgentInfo"%>
<%@ page import="com.elegps.tscweb.model.TbEnterpriseInfo"%>
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
  
  <body  onload="goload()">
   <form id="form1" name="form1" method="post">
 <td background="images/gw_06.gif">&nbsp;</td>
    <td width="84%" valign="top"><table width="100%" height="363" border="0" cellpadding="0" cellspacing="0"> <tr>
        <td height="5">&nbsp;</td>
        <td valign="top"><table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
          <tr>
            <td width="3%" height="30" align="center" background="images/3_04.gif"><img src="images/arrow3.gif" width="20" height="19"></td>
            <td width="45%" background="images/3_04.gif"><span class="STYLE4">您现在的位置：<span class="STYLE7">调度设置</span></span></td>
            <td width="52%"><img src="images/3_03.jpg" width="518" height="35"></td>
          </tr>
        </table>
        <table width="100%">
  <tr>
    <td width="80" align="right"><div align="right">代理商名称:</div></td>  
    <td  width="133" align="left" >
      <div align="left">
        <select name="agent" id="agent_id" style="{width:130;margin:0;FONT-SIZE:9pt;FONT-WEIGHT:normal;}" onchange=  "agentchang(document.forms[0])">
          <option value="">全部代理商</option>
            <% 
          List listagent =(List)request.getAttribute("agentList");
          if(listagent!=null){
           for (int i= 0; i < listagent.size(); i++) {
				 TbAgentInfo agentinfo=new TbAgentInfo();
				  agentinfo = (TbAgentInfo)listagent.get(i);
				%>
	      <option value="<%=agentinfo.getAgent_Id()%>"><%=agentinfo.getAgent_Name()%></option>
		  <%} }%>
        </select>
      </div></td>
    <td width="110"><div align="right">调度用户ID:</dib></td>
      <td>
       <div>
      <select name="ddms_id" id="ddms_id" style="{width:200;margin:0;FONT-SIZE:9pt;FONT-WEIGHT:normal;}">
	     <option value="">全部</option>
   <% 
   List listddms =(List)request.getAttribute("ddmsList");
   if(listddms!=null){
   	for (int i= 0; i < listddms.size(); i++) {
				  String tbddmsinfo = (String)listddms.get(i);
				%>
	    <option value="<%=tbddmsinfo%>"><%=tbddmsinfo%></option>
		<%} }%>
		</select></div></td>
    <td width="190" align="center"><span style="cursor:hand"><img src="images/bt_cx.gif" width="65" height="22" Onclick="ddmsmsearch(document.forms[0])"/></span></td>
  </tr>
  <tr>
   <td width="80" align="right"><div align="right">企业名称:</div></td>
    <td width="139" align="left" >
      <div align="left">
        <select name="ep" id="ep_id" style="{width:130;margin:0;FONT-SIZE:9pt;FONT-WEIGHT:normal;}">
          <option value="">全部企业</option>
          <% 
           List listep =(List)request.getAttribute("epList");
          if(listep!=null){
           for (int i= 0; i < listep.size(); i++) {
				 TbEnterpriseInfo epinfo=new TbEnterpriseInfo();
				  epinfo = (TbEnterpriseInfo)listep.get(i);
				%>
	      <option value="<%=epinfo.getEp_Id()%>"><%=epinfo.getEp_Name()%></option>
		  <%} }%>
        </select>
      </div></td>
    <td  width="110"><div align="right">非调度用户ID:</div></td>
      <td>
       <div>
      <select name="ms_id" id="ms_id" style="{width:200;margin:0;FONT-SIZE:9pt;FONT-WEIGHT:normal;}">
	     <option value="">全部</option>
     <% 
   List listms =(List)request.getAttribute("msList");
   if(listms!=null){
   		for (int i= 0; i < listms.size(); i++) {
				 TbDdmsMsListInfo tbmsinfo=new TbDdmsMsListInfo();
				  tbmsinfo = (TbDdmsMsListInfo)listms.get(i);
				%>
	    <option value="<%=tbmsinfo.getMs_id()%>"><%=tbmsinfo.getMs_id()%></option>
		<%} 
		}%>
		</select></div></td>
    <td align="center"><span style="cursor:hand"><img src="images/bt_add.gif" width="65" height="22" Onclick="ddmsmsadd()"/></span>
      <span style="cursor:hand"><img src="images/bt_del.gif" width="65" height="22" Onclick="onSelect(document.forms[0])"></span></td>
  </tr>
</table>
     
   
      

<table width="100%" height="21" border="1" bordercolor="#C1DAD7">
  <tr>
    <td width="3%" class="list_header" align="center">
     	<input class=btn type="button" name="selectall" value="全选"
							onClick="this.value=check(document.forms[0].list)">
    </td>
    <td width="22%" align="center" class="list_header"><span class="STYLE77">调度用户ID</span></td>
    <td width="22%" align="center" class="list_header"><span class="STYLE77">非调度用户ID</span></td>
  </tr>
  
  <logic:present name="ddmsmsList" scope="request" >
   <% 
   List list =(List)request.getAttribute("ddmsmsList");
   if(list!=null){
  	 for (int i= 0; i < list.size(); i++) {
				 TbDdmsMsListInfo tbddmsmsinfo=new TbDdmsMsListInfo();
				  tbddmsmsinfo = (TbDdmsMsListInfo)list.get(i);
				%>
  					<tr>
					<td align="center">
						<input name="list" type="checkbox" value="<%=tbddmsmsinfo.getDdms_id()%>,<%=tbddmsmsinfo.getMs_id()%>">
					</td>
					<td align="center" >
					  <font><a href="ms.do?CMD=xinaxi&msid=<%=tbddmsmsinfo.getDdms_id()%>"><span class="STYLE77"><span class="STYLE77"><%=tbddmsmsinfo.getDdms_id()%></span></a>
						</font>
					</td>
					<td align="center" >
						<font><a href="ms.do?CMD=xinaxi&msid=<%=tbddmsmsinfo.getMs_id()%>"><span class="STYLE77"><%=tbddmsmsinfo.getMs_id()%></span></a>
						</font>
					</td>
                    </tr>
      <% } 
      }%>
      
  
 <tr>
     <td align="right" colspan="6" class="list_footer"> 
         共有<%=request.getAttribute("ddmsmsCount")%>条&nbsp;&nbsp;
		第<%=request.getAttribute("currentPage")%>页&nbsp;
		共<%=request.getAttribute("pageCount")%>页&nbsp;   
		<a href="ddmsms.do?CMD=<%=request.getAttribute("CMD")%>&ddms_id=<%=request.getAttribute("ddms_id")%>&ms_id=<%=request.getAttribute("ms_id")%>&pageNo=1"><img src="images/bt_first.gif" width="42" height="16" border="0" align="absmiddle"></a>                         
		<a href="ddmsms.do?CMD=<%=request.getAttribute("CMD")%>&ddms_id=<%=request.getAttribute("ddms_id")%>&ms_id=<%=request.getAttribute("ms_id")%>&pageNo=<%=((Integer)request.getAttribute("currentPage")-1)%>"><img src="images/bt_pre.gif" width="38" height="16" border="0" align="absmiddle"></a>
		<a href="ddmsms.do?CMD=<%=request.getAttribute("CMD")%>&ddms_id=<%=request.getAttribute("ddms_id")%>&ms_id=<%=request.getAttribute("ms_id")%>&pageNo=<%=((Integer)request.getAttribute("currentPage")+1)%>"><img src="images/bt_next.gif" width="41" height="16" border="0" align="absmiddle"></a>
		<a href="ddmsms.do?CMD=<%=request.getAttribute("CMD")%>&ddms_id=<%=request.getAttribute("ddms_id")%>&ms_id=<%=request.getAttribute("ms_id")%>&pageNo=<%=request.getAttribute("pageCount")%>"><img src="images/bt_last.gif" width="42" height="16" border="0" align="absmiddle"></a>
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
		form.action="ddmsms.do?CMD=delete";
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


function ddmsmsadd(){
document.location.href="ddmsms.do?CMD=addtojsp"
}

function gotoye(){
    var page=this.form1.gotopage.value;
     if(!isNumber(page)){
      alert("输入跳转页数非法,请重新输入.(只能由数字组成)");
      return false;
     }
	document.location.href="ddmsms.do?CMD=<%=request.getAttribute("CMD")%>&ddms_id=<%=request.getAttribute("ddms_id")%>&ms_id=<%=request.getAttribute("ms_id")%>&pageNo="+page;
}

function ddmsmsearch(form){
    form.action="ddmsms.do?CMD=ddmsms_search";
	form.submit();
}

<!--改变代理商时-->
function agentchang(form){
  form.action="ddmsms.do?CMD=agentchange";
  form.submit();
}

<!-- 装入上次查询的装态 ->
function goload() 
{ 
  var ddmssid="<%=request.getAttribute("ddms_id")%>";
  var msid="<%=request.getAttribute("ms_id")%>";
  var agent="<%=request.getAttribute("agent")%>";
  var ep="<%=request.getAttribute("ep")%>";
  document.getElementById("ddms_id").value=ddmssid;
  document.getElementById("ms_id").value=msid;  
  document.getElementById("agent_id").value=agent;
  document.getElementById("ep_id").value=ep;
  var mes="<%=request.getAttribute("message")%>";
  if(mes!="null"){
    alert(mes);
    document.location.href="ddmsms.do?CMD=ddmsms_search";
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
