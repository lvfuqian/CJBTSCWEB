<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ page import="com.elegps.tscweb.model.TbMsInfo"%>
<%@ page import="com.elegps.tscweb.model.TbGpsInfo"%>
<%@ page import="com.elegps.tscweb.model.TbAgentInfo"%>
<%@ page import="com.elegps.tscweb.model.TbEnterpriseInfo"%>
<%@ page import="java.util.List"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="css/ComboBox.css" rel="stylesheet" type="text/css">
<style type="text/css">
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
.btn {
BORDER-RIGHT: #7b9ebd 1px solid; 
PADDING-RIGHT: 2px; 
BORDER-TOP: #7b9ebd 1px solid; 
PADDING-LEFT: 2px; 
FONT-SIZE: 12px; 
FILTER: progid:DXImageTransform.Microsoft.Gradient(GradientType=0, StartColorStr=#ffffff, EndColorStr=#cecfde); 
BORDER-LEFT: #7b9ebd 1px solid; CURSOR: hand; COLOR: black; PADDING-TOP: 2px; BORDER-BOTTOM: #7b9ebd 1px solid
}
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
	font-size: 15px;
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
<link href="css/manage.css" rel="stylesheet" type="text/css">
<link href="css/list.css" rel="stylesheet" type="text/css">
<style type="text/css">
<!--
.STYLE9 {
	color: #000000;
	font-weight: bold;
	text-indent: 10px;
}
-->
</style>   
    <title>增加调度管理信息</title>
  </head>
  
  <body onload="goload()">
  <td background="images/gw_06.gif">&nbsp;</td>
    <td width="84%" valign="top"><table width="100%" height="363" border="0" cellpadding="0" cellspacing="0"> <tr>
        <td height="5">&nbsp;</td>
        <td valign="top"><table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
          <tr>
            <td width="3%" height="30" align="center" background="images/3_04.gif"><img src="images/arrow3.gif" width="20" height="19"></td>
            <td width="45%" background="images/3_04.gif"><span class="STYLE4">您现在的位置：<span class="STYLE7">调度管理信息添加</span></span></td>
            <td width="52%"><img src="images/3_03.jpg" width="518" height="35"></td>
          </tr>
        </table>
        <br>
        <br>
  <form name="ddmsmsaddform"  onSubmit="return formcheck();"  method="post"  action="ddmsms.do?CMD=add">
  <table align="center" width="450"  border="1" bordercolor="#C1DAD7">
  <tr>
      <td colspan="2" align="center"  class="list_header"><span class="STYLE77">调度管理信息添加</span></td>
    </tr>
    <tr>
       <td width="15%" align="right"><div align="right">代理商名称:</div></td>  
    <td  width="40%" align="left" >
      <div align="left">
        <select name="agentid" id="agent_id" style="{width:300;margin:0;FONT-SIZE:9pt;FONT-WEIGHT:normal;}" onchange="agentchang(document.forms[0])">
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
        <font color="red">&nbsp;<B>*</B></font>
      </div></td>
     </tr>
     <tr>
    <td width="15%" align="right"><div align="right">企业名称:</div></td>
    <td width="40%" align="left" >
      <div align="left">
        <select name="epid" id="ep_id" style="{width:300;margin:0;FONT-SIZE:9pt;FONT-WEIGHT:normal;}" onchange="epchang(document.forms[0])">
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
        <font color="red">&nbsp;<B>*</B></font>
      </div></td>
	</tr> 
    <tr>
    <td width="100" align="right"><label>调度用户ID:</label></td>
    <td  align="left">
    <select name="ddmsid" id="ddms_id"  style="{width:300;margin:0;FONT-SIZE:9pt;FONT-WEIGHT:normal;}">   
   <% 
   List listddms =(List)request.getAttribute("ddmsList");
   if(listddms!=null){
   	for (int i= 0; i < listddms.size(); i++) {
				 TbMsInfo tbddmsinfo=new TbMsInfo();
				  tbddmsinfo = (TbMsInfo)listddms.get(i);
				%>
	    <option value="<%=tbddmsinfo.getMsId()%>"><%=tbddmsinfo.getMsId()%>—<%=tbddmsinfo.getMsName()%></option>
		<%} }%>
		</select><font color="red">&nbsp;<B>*</B></font>
    </td> 
    </tr>
    <tr>
    <td align="right"><label>非调度用户ID:</label></td>
    <td align="left">
    <select name="msid" id="ms_id"  style="{width:300;margin:0;FONT-SIZE:9pt;FONT-WEIGHT:normal;}">   
     <% 
   List listms =(List)request.getAttribute("msList");
   if(listms!=null){
   		for (int i= 0; i < listms.size(); i++) {
				 TbMsInfo tbmsinfo=new TbMsInfo();
				  tbmsinfo = (TbMsInfo)listms.get(i);
				%>
	    <option value="<%=tbmsinfo.getMsId()%>"><%=tbmsinfo.getMsId()%>—<%=tbmsinfo.getMsName()%></option>
		<%} 
		}%>
		</select><font color="red">&nbsp;<B>*</B></font>
	</td>
	</tr>
	
    <tr>
      <td colspan="2" align="center" class="list_footer">
          <input class=btn  type="Submit" name="Submit" value="提交" />&nbsp;
        <input class=btn  type="button" name="button" value="返回" onclick="toddmsmslist()" />&nbsp;
      </td>
    </tr>
  </table></form></td></tr></table></td></body>
</html>
<script type="text/javascript">

<!--提交时表单验证-->
function formcheck(){
   if(ddmsmsaddform.agentid.value==""){
     alert("请选择代理商!");
    ddmsmsaddform.agentid.focus();
     return false;
  }
  if(ddmsmsaddform.epid.value==""){
     alert("请选择企业!");
     ddmsmsaddform.epid.focus();
     return false;
  }
   if(ddmsmsaddform.ddmsid.value==""){
     alert("请选择调度用户!");
     ddmsmsaddform.ddmsid.focus();
     return false;
  }
  if(ddmsmsaddform.msid.value==""){
     alert("请选择终端!");
     ddmsmsaddform.msid.focus();
     return false;
  }
}


function toddmsmslist(){
  url="ddmsms.do?CMD=ddmsms_search";
  document.location.href=url;
}


<!--改变代理商时-->
function agentchang(form){
  form.action="ddmsms.do?CMD=addagentchange";
  form.submit();
}

<!--改变企业时-->
function epchang(form){
  form.action="ddmsms.do?CMD=addepchange";
  form.submit();
}

function goload() 
{ 
  var agent="<%=request.getAttribute("agent")%>";
  var ep="<%=request.getAttribute("ep")%>";
  var ddms_id="<%=request.getAttribute("ddms_id")%>";
  var msid="<%=request.getAttribute("msid")%>";
  document.getElementById("agent_id").value=agent;
  document.getElementById("ep_id").value=ep;
  if (ddms_id!="null"){
     document.getElementById("ddms_id").value=ddms_id 
  }else{
    document.getElementById("ddms_id").value="";
  }
  if (msid!="null"){
     document.getElementById("ms_id").value=msid 
  }else{
     document.getElementById("ms_id").value="";
  }
}
</script>


