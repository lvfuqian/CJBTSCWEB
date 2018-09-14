<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="com.elegps.tscweb.model.TbEnterpriseInfo"%>
<%@ page import="java.util.List"%>
<%@ page import="com.elegps.tscweb.model.TbAgentInfo"%>
<%@ page import="com.elegps.tscweb.model.TbEnterpriseInfo"%>
<%@ page import="com.elegps.tscweb.model.TbPFInfo"%>

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
    <title>增加终端用户信息</title>
  </head>
  
  <body onload="goload()">
 <td background="images/gw_06.gif">&nbsp;</td>
    <td width="84%" valign="top"><table width="100%" height="363" border="0" cellpadding="0" cellspacing="0"> <tr>
        <td height="5">&nbsp;</td>
        <td valign="top"><table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
          <tr>
            <td width="3%" height="30" align="center" background="images/3_04.gif"><img src="images/arrow3.gif" width="20" height="19"></td>
            <td width="45%" background="images/3_04.gif"><span class="STYLE4">您现在的位置：<span class="STYLE7">终端用户添加</span></span></td>
            <td width="52%"><img src="images/3_03.jpg" width="518" height="35"></td>
          </tr>
        </table>
        <br>
        <br>
  <form name="msaddfor" onSubmit="return formcheck();" method="post"  action="ms.do?CMD=add">
  <table align="center" width="450"  border="1" bordercolor="#C1DAD7">
    <tr>
      <td colspan="2" align="center"  class="list_header"><span class="STYLE77">终端用户添加</span></td>
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
              <td width="15%" align="right"><label>终端号码:</label></td>
              <td width="35%"><input style="width:180px;" name="ms_id" type="text" value='${requestScope.ms_id}' class="checkbox" maxlength="21" onblur="checkms(this.value)">
              <B>&nbsp;<font color="red" id="mscheck">*</font></B></td>
    </tr>
    <tr>
              <td  align="right">
                终端别名:</td>
              <td><input style="width:180px;" name="ms_name" type="text" value='${requestScope.ms_name}' class="checkbox" maxlength="30"><font color="red">&nbsp;<B>&nbsp;*</B></font></td>
      
    </tr>
    <tr>
              <td  align="right"><label>登陆密码:</label></td>
              <td><input style="width:180px;" name="password" type="text" value='${requestScope.password}' class="checkbox" size="30"><font color="red">&nbsp;<B>&nbsp;*</B></font></td>
     
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
        </select><font color="red">&nbsp;<B>&nbsp;*</B></font>
       </td>
	</tr>
	<tr>
      <td align="right">终端型号:</td>
      <td>
        <select name="c05" id="c05"  style="{width:180;margin:0;FONT-SIZE:9pt;FONT-WEIGHT:normal;}" >
         <option  value="1">BK_PTT</option>   
		 <option  value="2">BK_GPS</option>
		 <option  value="4">BK_803</option>
		 <option  value="8">T808</option>
		 <option  value="16">HR3000</option>
		 <option  value="32">BK_K6</option>
        </select><font color="red">&nbsp;<B>&nbsp;*</B></font>
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
         <option  value="C">电信（C）</option>
		 <option  value="W">联通（W）</option>
		 <option  value="M">移动（M）</option>
		</select><font color="red">&nbsp;<B>*</B></font>
	</td>
	</tr>
	<tr>
              <td  align="right">
               终端卡号:</td>
              <td><input style="width:180px;" name="ms_modid" type="text" value='${requestScope.ms_modid}' class="checkbox" size="30"></td>
      
    </tr>
     <tr>
              <td  align="right">
                初始里程:</td>
              <td><input style="width:180px;" name="mileageas" type="text" value="0" class="checkbox" readonly="true" maxlength="11"></td>
      
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
    <input name="mssl" type="hidden" value="1">
  </table></form></body>
</html>

<script language="javascript">
function checkms(msid){
	ChildAgent.checkIsMs(msid,function(data){
		if(!data){
			document.getElementById('mscheck').innerHTML = "终端号码已存在！";
		}else{
			document.getElementById('mscheck').innerHTML = "*";	
		}
	});
	
}

function tomslist(){
  document.location.href="javascript: history.back(-1)";  
}
var reg='^[a-zA-Z0-9]{21}+$';
function formcheck(){

 if(trim(msaddfor.pagentid.value)=="")
   { 
      alert("一级代理商名称不能为空，请选择一级代理商名称!");
      msaddfor.pagentid.focus();
      return false;
   }
   
 if(trim(msaddfor.childagentid.value)=="")
   { 
      alert("请选择二级代理商!");
      msaddfor.childagentid.focus();
      return false;
   }
  //if(trim(msaddfor.ep.value)==""){
   //  alert("请选择企业!");
   //  msaddfor.ep.focus();
  //   return false;
 // }
 if(trim(msaddfor.ms_id.value)=="")
   { 
      alert("终端号码为空，请输入终端号码");
      msaddfor.ms_id.focus();
      return false;
   }
 if(trim(msaddfor.ms_id.value).length!=21)
	 {
 	 alert("终端号长度必须为21位,请输入");
 	 msaddfor.ms_id.focus();
 	 return false;
	 }
	 
 if(trim(msaddfor.ms_id.value).length==21){
     var ms_id=msaddfor.ms_id.value;
 	for (var i=0; i<ms_id.length; i++)	
		{
		charcode = ms_id.charCodeAt(i);
		if (charcode < 48 || charcode > 121){
		    alert("终端号码只能是由[a-zA-Z0-9]组成!,请重新输入");
		    msaddfor.ms_id.focus();
			return false;
			}
		}
 }
 
  if(trim(msaddfor.mileageas.value).length>0){
     var mileage=msaddfor.mileageas.value;
 	 for (var i=0; i<mileage.length; i++)	
		{
		charcode = mileage.charCodeAt(i);
		if (charcode < 48 || charcode > 57){
		    alert("总里程只能是由数字组成!,请重新输入");
		    msaddfor.mileageas.focus();
			return false;
			}
		}
 }else{
   return false;
 }
 

 if(msaddfor.ms_name.value=="")
   { 
      alert("终端名称为空，请输入终端名称");
      msaddfor.ms_name.focus();
      return false;
   }
  

 if(msaddfor.password.value=="")
   { 
      alert("密码不能为空，请输入密码");
      msaddfor.password.focus();
      return false;
   }
 
 if(trim(msaddfor.password.value).length<1)
   { 
      alert("密码长度必须等于或大于1位，请输入密码");
      msaddfor.password.focus();
      return false;
   }
   
   if(msaddfor.ms_type.value=="")
   {
      alert("请选择终端类型！");
      msaddfor.ms_type.focus();
      return false;
   }
   
   if(msaddfor.ms_level.value=="")
   {
      alert("请选择终端级别！");
      msaddfor.ms_level.focus();
      return false;
   }
   
   if(msaddfor.ms_modid.value!=""){ 
      if (trim(msaddfor.ms_modid.value).length!=11)  {   
            alert("卡号长度必须是11位,请输入正确的终端卡号\n\n例如:13800138000"); 
            msaddfor.ms_modid.focus();
            return false;        
        }else{
         var id=msaddfor.ms_modid.value;
 	     for (var i=0; i<id.length; i++)	
		  {
		    charcode = id.charCodeAt(i);
		    if (charcode < 48 || charcode > 57){
		    alert("卡号只能是由数字组成!,请重新输入");
		    msaddfor.ms_modid.focus();
			return false;
			}
		 }
        }          
   }   
   return true;
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
 if(msaddfor.ms_type.value==2){
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


function parentagentchang(){
  var childagentselect= document.getElementById("childagentid");
  msaddfor.childagentid.options.length=1; 
  var parentagent=msaddfor.pagentid.value;
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
        var pagent=msaddfor.pagentid.value;
        var cagent=msaddfor.childagentid.value;
        msaddfor.ep.options.length=0;
		getEpinfo();
}

 		var a_id=<%=Integer.parseInt(request.getSession().getAttribute("agentId").toString())%>;
  		var r_id=<%=Integer.parseInt(request.getSession().getAttribute("roleId").toString())%>;
  		var ep_id=<%=Integer.parseInt(request.getSession().getAttribute("epId").toString())%>;
function getEpinfo(){
   var epselect= document.getElementById("ep_id");
        var pagent=msaddfor.pagentid.value;
        var cagent=msaddfor.childagentid.value;
        msaddfor.ep.options.length=0;
        /**ChildAgent.getEpinfo(pagent,cagent,function(data){
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
        }); */
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
