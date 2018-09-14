<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="com.elegps.tscweb.model.TbEnterpriseInfo"%>
<%@ page import="java.util.List"%>
<%@ page import="com.elegps.tscweb.model.TbAgentInfo"%>
<%@ page import="com.elegps.tscweb.model.TbEnterpriseInfo"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>


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
    <title>批量增加终端用户信息</title>
  </head>
  
  <body  onload="goload()">
 <td background="images/gw_06.gif"><br>&nbsp;</td>
    <td width="84%" valign="top"><table width="100%" height="363" border="0" cellpadding="0" cellspacing="0"> <tr>
        <td height="5">&nbsp;</td>
        <td valign="top"><table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
          <tr>
            <td width="3%" height="30" align="center" background="images/3_04.gif"><img src="images/arrow3.gif" width="20" height="19"></td>
            <td width="45%" background="images/3_04.gif"><span class="STYLE4">您现在的位置：<span class="STYLE7">批量增加终端用户信息</span></span></td>
            <td width="52%"><img src="images/3_03.jpg" width="518" height="35"></td>
          </tr>
        </table>
        <br>
        <br>
  <form name="mspladdfor" onSubmit="return formcheck();" method="post"  action="ms.do?CMD=add">
  <table align="center" width="450"  border="1" bordercolor="#C1DAD7">
    <tr>
      <td colspan="2" align="center"  class="list_header"><span class="STYLE77">批量增加终端用户信息</span></td>
    </tr>
   <tr>
     <td align="right">总部/一级代理商名称:</td>
     <td width="10%" align="left">
        <select name="pagentid" id="pagentid"  style="{width:180;margin:0;FONT-SIZE:9pt;FONT-WEIGHT:normal;}" onchange="parentagentchang()">
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
        <select name="childagentid" id="childagentid"  style="{width:180;margin:0;FONT-SIZE:9pt;FONT-WEIGHT:normal;}"  onchange="childagentchang()">
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
        <select name="ep" id="ep_id" style="{width:180;margin:0;FONT-SIZE:9pt;FONT-WEIGHT:normal;}">
        <option value=""></option>
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
	</tr> 
   <tr>
              <td width="15%" align="right"><label>开始终端号码:</label></td>
              <td width="35%"><input style="width:180px;" name="ms_id" type="text" value='${requestScope.ms_id}' class="checkbox" maxlength="21"><font color="red">&nbsp;&nbsp;<B>*</B></font></td>      
    </tr>
    <tr>
            <td width="15%" align="right"><label>终端个数:</label></td>
            <td width="15%"><input style="width:40px;" name="mssl" type="text" value='${requestScope.mssl}' class="checkbox" maxlength="3"><font color="red" >&nbsp;*&nbsp;&nbsp;注：最多只能输入三位数</font></td>
    </tr>
    <tr>
              <td  align="right">
                终端别名:</td>
              <td><input style="width:180px;" name="ms_name" type="text" value='${requestScope.ms_name}' class="checkbox" maxlength="30"><font color="red">&nbsp;&nbsp;<B>*</B></font></td>
      
    </tr>
    <tr>
              <td  align="right"><label>登陆密码:</label></td>
              <td><input style="width:180px;" name="password" type="text" value='${requestScope.password}' class="checkbox" size="30"><font color="red">&nbsp;&nbsp;<B>*</B></font></td>
     
    </tr>
	<tr>
      <td align="right">用户类型:</td>
      <td>
       <select name="ms_type" id="mstype"  style="{width:180;margin:0;FONT-SIZE:9pt;FONT-WEIGHT:normal;}" onChange="mstypechang()">
	    <option  value="0">终端用户</option>   
		<option  value="1">群组管理</option>
		<option  value="2">调度用户</option>
		<option  value="5">下呼调度</option>
		<option  value="4">免费用户</option>
        </select><font color="red">&nbsp;&nbsp;<B>*</B></font>
       </td>
	</tr>
	<tr>
      <td align="right">强插级别:</td>
      <td>
        <select name="ms_level" id="mslevel"  style="{width:180;margin:0;FONT-SIZE:9pt;FONT-WEIGHT:normal;}">
          <option  value="0">无强插</option>   
		  <option  value="1">低强插</option>
		  <option  value="2">中强插</option>
		  <option  value="3">高强插</option>
        </select><font color="red">&nbsp;<B>&nbsp;*</B></font>
       </td>
	</tr>
	<tr>
      <td align="right">是否单呼:</td>
      <td>
        <select name="ms_df" id="msdf"  style="{width:180;margin:0;FONT-SIZE:9pt;FONT-WEIGHT:normal;}">
            <option  value="0">否</option>   
			<option  value="1">是</option>
        </select><font color="red">&nbsp;<B>&nbsp;*</B></font>
       </td>
	</tr>
	<tr>
      <td align="right">通话权限:</td>
      <td>
        <select name="call" id="call"  style="{width:180;margin:0;FONT-SIZE:9pt;FONT-WEIGHT:normal;}">
            <option  value="1">是</option>   
			<option  value="0">否</option>
        </select><font color="red">&nbsp;<B>&nbsp;*</B></font>
       </td>
	</tr>
	<tr>
      <td align="right">费用状态:</td>
      <td>
        <select name="package_fee" id="package_fee"  style="{width:180;margin:0;FONT-SIZE:9pt;FONT-WEIGHT:normal;}">
            <option  value="-1">欠费</option>  
        </select><font color="red">&nbsp;<B>&nbsp;*</B></font>
       </td>
	</tr>
		<tr>
	<td align="right">电话权限:</td>
	<td>
	  <select name="c03" id="c03"  style="{width:180;margin:0;FONT-SIZE:9pt;FONT-WEIGHT:normal;}">
	     <option value="0">禁止切换</option>
		 <option value="1">允许切换</option>
		</select><font color="red">&nbsp;<B>*</B></font>
	</td>
	</tr>
	<tr>
	<td align="right">终端类别:</td>
	<td>
	  <select name="c04" id="c04"  style="{width:180;margin:0;FONT-SIZE:9pt;FONT-WEIGHT:normal;}">
	  	<option  value="0">车机</option>
         <option  value="1">手持机</option>   
		</select><font color="red">&nbsp;<B>*</B></font>
	</td>
	</tr>
	<tr>
	<td align="right">终端类型:</td>
	<td>
	  <select name="ms_category" id="ms_category"  style="{width:180;margin:0;FONT-SIZE:9pt;FONT-WEIGHT:normal;}">
	  	<option  value="0">V8</option>
         <option  value="1">A18</option>   
		 <option  value="2">A380</option>
		 <option  value="3">A777</option>
		 <option  value="4">H1000</option>
		 <option  value="5">S40</option>
		 <option  value="6">S60</option>
		 <option  value="7">S50</option>
		 <option  value="8">T20</option>
		 <option  value="9">T25</option>
		 <option  value="10">G10</option>
		 <option  value="11">K880</option>
		 <option  value="12">其他</option>
		</select><font color="red">&nbsp;<B>*</B></font>
	</td>
	</tr>
	
	<tr>
	<td align="right">网络类型:</td>
	<td>
	  <select name="nwType" id="nwType"  style="{width:180;margin:0;FONT-SIZE:9pt;FONT-WEIGHT:normal;}">
	  	<option  value="">无网络</option>
         <option  value="c">电信（C）</option>
		 <option  value="w">联通（W）</option>
		 <option  value="m">移动（M）</option>
		</select><font color="red">&nbsp;<B>*</B></font>
	</td>
	</tr>
	<tr>
              <td align="right"><label>备注信息:</label></td>
              <td><input style="width:180px;" name="memo" type="text" value='${requestScope.memo}' class="checkbox" size="30"></td>
    </tr>
	<tr>
       <td colspan="2" align="center" class="list_footer">
          <input class=btn type="Submit" name="Submit" value="提交" />&nbsp;
        <input class=btn type="button" name="button" value="返回" onclick="tomslist()" />&nbsp;
        <input class=btn type="reset" name="reset" value="重置" />
      </td>
    </tr>
    <input name="ms_modid" type="hidden" value="">
    <input name="mileageas" type="hidden" value="0">
  </table></body>
</html>

<script language="javascript">
function tomslist(){
  document.location.href="javascript: history.back(-1)";  
}

function formcheck(){
  if(trim(mspladdfor.pagentid.value)=="")
   { 
      alert("一级代理商名称不能为空，请选择一级代理商名称!");
      mspladdfor.pagentid.focus();
      return false;
   }
   
 if(trim(mspladdfor.childagentid.value)=="")
   { 
      alert("请选择二级代理商!");
      mspladdfor.childagentid.focus();
      return false;
   }
 // if(trim(mspladdfor.ep.value)==""){
 //    alert("请选择企业!");
 //    mspladdfor.ep.focus();
 //    return false;
 // }
 if(trim(mspladdfor.ms_id.value)=="")
   { 
      alert("终端号码为空，请输入终端号码");
      mspladdfor.ms_id.focus();
      return false;
   }
 if((mspladdfor.ms_id.value).length!=21)
	 {
 	 alert("终端号长度必须为21位,请输入");
 	 mspladdfor.ms_id.focus();
 	 return false;
	 }
	 
 if(trim(mspladdfor.ms_id.value).length==21){
     var msid=trim(mspladdfor.ms_id.value);
 	 for (var i=0; i<msid.length; i++)	
		{
		charcode = msid.charCodeAt(i);
		if (charcode < 48 || charcode > 57){
		  alert("批量添加的终端号码只能是由[0-9]数字组成!,请重新输入");
		    mspladdfor.ms_id.focus();
			return false;
			}
		}
 }
 
  if(trim(mspladdfor.mssl.value)=="")
   { 
      alert("终端号码个数为空，请输入添加批量终端个数");
      mspladdfor.mssl.focus();
      return false;
   }

 
  if(trim(mspladdfor.mssl.value).length>3){
      alert("添加批量终端个数大于3位数，请重新输入");
      mspladdfor.mssl.focus();
      return false;
  }
  
  if(trim(mspladdfor.mssl.value).length<4){
     var msleng=trim(mspladdfor.mssl.value);
    if(msleng=="000"){
    	 alert("终端号码个数无效!,请重新输入");
		 mspladdfor.mssl.focus();
		 return false;
    }else{
     	for (var i=0; i<msleng.length; i++)	
		{
			charcode = msleng.charCodeAt(i);
			if (charcode < 48 || charcode > 57){
		 	   alert("终端号码只能是由数字组成!,请重新输入");
		 	   mspladdfor.mssl.focus();
			   return false;
			}
		}
     }
  }
   
    var lms=parseInt((trim(mspladdfor.ms_id.value)).substring(18,21),10);
    var tms=parseInt(trim(mspladdfor.mssl.value),10);
    var ts=lms+tms;
    var maxmsid=1000-lms;
    if(ts>1000){
      alert("终端号个数超出范围!最大终端个数是:"+maxmsid);
      mspladdfor.mssl.value=maxmsid
      mspladdfor.mssl.focus();
	  return false;
    }

 if(trim(mspladdfor.ms_name.value)=="")
   { 
      alert("终端名称为空，请输入终端名称");
      mspladdfor.ms_name.focus();
      return false;
   }
 
 if(mspladdfor.password.value=="")
   { 
      alert("密码不能为空，请输入密码");
      mspladdfor.password.focus();
      return false;
   }
 
 if(trim(mspladdfor.password.value).length<1)
   { 
      alert("密码长度必须等于或大于1位，请输入密码");
      mspladdfor.password.focus();
      return false;
   }
   if(mspladdfor.ms_type.value=="")
   {
      alert("请选择终端类型！");
      mspladdfor.ms_type.focus();
      return false;
   }
   if(mspladdfor.ms_level.value=="")
   {
      alert("请选择终端级别！");
      mspladdfor.ms_level.focus();
      return false;
   }
   return true;
}

//<!--改变代理商时-->
function agentchang(form){
  form.action="ms.do?CMD=pladdagentchange";
  form.submit();
}

//<!-- 装入上次查询用户类型查询、在线状态查询、用户有效状态查询的装态 ->
function goload() 
{ 

  var pagentid=parseInt(<%=request.getAttribute("pagentid")%>);
  var childagentid=parseInt(<%=request.getAttribute("childagentid")%>);
  var ep="<%=request.getAttribute("epid")%>";
  document.getElementById("pagentid").value=pagentid;
  document.getElementById("childagentid").value=childagentid;
  document.getElementById("ep_id").value=ep; 
    var r_id=<%=Integer.parseInt(request.getSession().getAttribute("roleId").toString())%>;
    if(r_id == 2 || r_id == 3 || r_id == 4 ){
	 document.getElementById("pagentid").length = 1;
	} 
} 


function mstypechang(){
 if(mspladdfor.ms_type.value==2){
  document.getElementById("mslevel").value=1;
 }else{
  document.getElementById("mslevel").value=0;
 }
}

//删除字符串左边的空格
function ltrim(str)	
{ 
	if(str.length==0)
		return(str);
	else
	{
		var idx=0;
		while(str.charAt(idx).search(/\s/)==0)
			idx++;
		return(str.substr(idx));
	}
}

//删除字符串右边的空格
function rtrim(str)	
{ 
	if(str.length==0)
		return(str);
	else
	{
		var idx=str.length-1;
		while(str.charAt(idx).search(/\s/)==0)
			idx--;
		return(str.substring(0,idx+1));
	}
}

//删除字符串左右两边的空格
function trim(str)	
{ 
	return(rtrim(ltrim(str)));
}
//检查一级代理名称
function parentagentchang(){
  var childagentselect= document.getElementById("childagentid");
  mspladdfor.childagentid.options.length=1; 
  var parentagent=mspladdfor.pagentid.value;
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
        var epselect= document.getElementById("ep_id");
        var pagent=mspladdfor.pagentid.value;
        var cagent=mspladdfor.childagentid.value;
        mspladdfor.ep.options.length=1;
        getEpinfo();
}

var a_id=<%=Integer.parseInt(request.getSession().getAttribute("agentId").toString())%>;
  		var r_id=<%=Integer.parseInt(request.getSession().getAttribute("roleId").toString())%>;
  		var ep_id=<%=Integer.parseInt(request.getSession().getAttribute("epId").toString())%>;
function getEpinfo(){
   var epselect= document.getElementById("ep_id");
        var pagent=mspladdfor.pagentid.value;
        var cagent=mspladdfor.childagentid.value;
        mspladdfor.ep.options.length=1;
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
}
</script>
