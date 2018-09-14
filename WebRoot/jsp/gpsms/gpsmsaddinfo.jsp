<%@ page language="java" pageEncoding="UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ page import="com.elegps.tscweb.model.TbMsInfo"%>
<%@ page import="com.elegps.tscweb.model.TbGpsInfo"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.lang.String" %>
<%@ page import="com.elegps.tscweb.model.TbAgentInfo"%>
<%@ page import="com.elegps.tscweb.model.TbEnterpriseInfo"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
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
    <script type='text/javascript' src='dwr/interface/ChildAgent.js'></script>
    <script type='text/javascript' src='dwr/engine.js'></script>
    <script type='text/javascript' src='dwr/util.js'></script>
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
    <title>增加终端用户信息</title>
  </head>
  
  <body onload="goload()">
  <td background="images/gw_06.gif">&nbsp;</td>
    <td width="84%" valign="top"><table width="100%" height="363" border="0" cellpadding="0" cellspacing="0"> <tr>
        <td height="5">&nbsp;</td>
        <td valign="top"><table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
          <tr>
            <td width="3%" height="30" align="center" background="images/3_04.gif"><img src="images/arrow3.gif" width="20" height="19"></td>
            <td width="45%" background="images/3_04.gif"><span class="STYLE4">您现在的位置：<span class="STYLE7">GPS接入、终端关系添加</span></span></td>
            <td width="52%"><img src="images/3_03.jpg" width="518" height="35"></td>
          </tr>
        </table>
        <br>
        <br>
  <form name="gpsmsaddform"  onSubmit="return formcheck();"  method="post"  action="gpsms.do?CMD=add">
   <table align="center" width="500"  border="1" bordercolor="#C1DAD7">
  <tr>
      <td colspan="2" align="center"  class="list_header"><span class="STYLE77">GPS接入、终端关系添加</span></td>
    </tr>
     <tr>
     <td align="right">总部/一级代理商名称:</td>
     <td width="10%" align="left">
        <select name="pagentid" id="pagentid"  style="{width:300;margin:0;FONT-SIZE:9pt;FONT-WEIGHT:normal;}" onchange="parentagentchang()">
		<% 
       List parentagentlist =(List)request.getAttribute("Pagentlist");
       if(parentagentlist!=null){
      	for (int i= 0; i < parentagentlist.size(); i++) {
				 TbAgentInfo agentinfo=new TbAgentInfo();
				  agentinfo = (TbAgentInfo)parentagentlist.get(i);
				%>
	      <option value="<%=agentinfo.getAgent_Id()%>"><%=agentinfo.getAgent_Name()%></option>
		  <%} }%>
        </select><font color="red">&nbsp;<B>*</B></font>
    </td>
	</tr> 
	  <tr>
     <td align="right">企业/二级代理商名称:</td>
      <td>
        <select name="childagentid" id="childagentid"  style="{width:300;margin:0;FONT-SIZE:9pt;FONT-WEIGHT:normal;}"  onchange="childagentchang()"> 
          <option value="-1">直属企业</option>
          	<% 
            List childagentlistid =(List)request.getAttribute("Cagentlist");
            if(childagentlistid!=null){
        	for (int i= 0; i < childagentlistid.size(); i++) {
				 TbAgentInfo agentinfo=new TbAgentInfo();
				  agentinfo = (TbAgentInfo)childagentlistid.get(i);
				%>
	      <option value="<%=agentinfo.getAgent_Id()%>"><%=agentinfo.getAgent_Name()%></option>
		  <%} }%>
         </select><font color="red">&nbsp;<B>*</B></font>
       </td>
	</tr> 
     <tr>
    <td width="15%" align="right"><div align="right">企业名称:</div></td>
    <td width="40%" align="left" >
      <div align="left">
        <select name="ep" id="ep_id" style="{width:300;margin:0;FONT-SIZE:9pt;FONT-WEIGHT:normal;}" onchange="epchang()">
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
     <td width="15%" align="right"><div align="right">GPS帐号:</div></td>
     <td align="left">
    <select name="gpsid" id="gps_id" style="{width:300;margin:0;FONT-SIZE:9pt;FONT-WEIGHT:normal;}">  
   <% 
   List listgps =(List)request.getAttribute("gpsList");
   if(listgps!=null){
   	for (int i= 0; i < listgps.size(); i++) {
				 TbGpsInfo tbgpsinfo=new TbGpsInfo();
				  tbgpsinfo = (TbGpsInfo)listgps.get(i);
				%>
	    <option value="<%=tbgpsinfo.getGpsop_id()%>"><%=tbgpsinfo.getGpsop_id()%>-<%=tbgpsinfo.getGps_name()%></option>
		<%} }%>
		</select><font color="red">&nbsp;&nbsp;<B>*</B></font>
    </td> 
    </tr>
   <tr>
	<td align="right"><label>终端号码:</label></td>
    <td align="left">
    <select name="ms_id" id="ms_id" style="{width:300;margin:0;FONT-SIZE:9pt;FONT-WEIGHT:normal;}">
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
		</select><font color="red">&nbsp;&nbsp;<B>*</B></font>
	</td>
	</tr>
	
    <tr>
      <td colspan="2" align="center" class="list_footer">
          <input class=btn type="Submit" name="Submit" value="提交" />
        <input class=btn type="button" name="button" value="返回" onclick="togpsmslist()" />&nbsp;
      </td>
    </tr>
  </table>
</form></body>
</html>
<script type="text/javascript">

<!--提交时表单验证-->
function formcheck(){
    if(gpsmsaddform.pagentid.value=="")
	   { 
	      alert("一级代理商名称不能为空，请选择一级代理商名称!");
	      gpsmsaddform.pagentid.focus();
	      return false;
	   }
	 if(gpsmsaddform.childagentid.value=="")
	   { 
	      alert("请选择二级代理商!");
	      gpsmsaddform.childagentid.focus();
	      return false;
	   }
	  if(gpsmsaddform.ep.value==""){
	     alert("请选择企业!");
	     gpsmsaddform.ep.focus();
	     return false;
	  }
   if(gpsmsaddform.gpsid.value==""){
     alert("请选择GPS厂商!");
     gpsmsaddform.gpsid.focus();
     return false;
  }
  if(gpsmsaddform.ms_id.value==""){
     alert("请选择终端!");
     gpsmsaddform.msid.focus();
     return false;
  }
}

function togpsmslist(){
 document.location.href="javascript: history.back(-1)";  
 <!-- url="gpsms.do?CMD=gpsms_search";
  document.location.href=url;  -->
}


<!-- 装入上次查询用户类型查询、在线状态查询、用户有效状态查询的装态 ->
function goload() 
{ 
  var pagentid=parseInt(<%=request.getAttribute("pagentid")%>);
  var childagentid=parseInt(<%=request.getAttribute("childagentid")%>);
  var ep="<%=request.getAttribute("epid")%>";
  document.getElementById("pagentid").value=pagentid;
  document.getElementById("childagentid").value=childagentid;
  document.getElementById("ep_id").value=ep;  
} 

function parentagentchang(){
  var childagentselect= document.getElementById("childagentid");
  gpsmsaddform.childagentid.options.length=1; 
  var parentagent=gpsmsaddform.pagentid.value;
  if(parentagent!=null){
        ChildAgent.getchiledAgent(parentagent,function(data){
         if(data!=null){
          for(var i=0;i<data.length;i++){
           for(var j=0;j<data[i].length;j++){
              var option = document.createElement("OPTION");
               option.value=data[i][j];
 	           option.text=data[i][++j];
               childagentselect.add(option);
           }
          } 
         } 
        }); 
	}
	getEpinfo();
	 
}

function childagentchang(){
       getEpinfo();
}

		var a_id=<%=Integer.parseInt(request.getSession().getAttribute("agentId").toString())%>;
        var r_id=<%=Integer.parseInt(request.getSession().getAttribute("roleId").toString())%>;
        var ep_id=<%=Integer.parseInt(request.getSession().getAttribute("epId").toString())%>;
function getEpinfo(){
        var epselect= document.getElementById("ep_id");
        var pagent=gpsmsaddform.pagentid.value;
        var cagent=gpsmsaddform.childagentid.value;
        gpsmsaddform.ep.options.length=0;
        gpsmsaddform.ms_id.options.length=0;
        if(r_id != 3 && r_id != 4){
        ChildAgent.getEpinfoByAId(pagent,cagent,a_id,r_id,function(data){
           if(data!=null){
            for(var i=0;i<data.length;i++){
             for(var j=0;j<data[i].length;j++){
              var option = document.createElement("OPTION");
               option.value=data[i][j];
 	           option.text=data[i][++j];
               epselect.add(option);
              }
             }
          }
        }); 
        }else{
        	ChildAgent.getEpinfoByEId(pagent,cagent,ep_id,r_id,function(data){
           if(data!=null){
            for(var i=0;i<data.length;i++){
             for(var j=0;j<data[i].length;j++){
              var option = document.createElement("OPTION");
               option.value=data[i][j];
 	           option.text=data[i][++j];
               epselect.add(option);
              }
             }
          }
        });
        } 
        setTimeout("getMsinfo()",200);	 
}

function epchang(){
 getMsinfo();
}



function getMsinfo(){
  var msselect =document.getElementById("ms_id");
  var pagentid=gpsmsaddform.pagentid.value;
  var childagentid=gpsmsaddform.childagentid.value;
  var ep=gpsmsaddform.ep.value;
  msselect.options.length=0;
  ChildAgent.getMsinfo(pagentid,childagentid,ep,function(data){
   if(data!=null){
      for(var i=0;i<data.length;i++){
             for(var j=0;j<data[i].length;j++){
              var option = document.createElement("OPTION");
               option.value=data[i][j];
 	           option.text=data[i][++j];
               msselect.add(option);
              }
           }
   }
  });
}
</script>


