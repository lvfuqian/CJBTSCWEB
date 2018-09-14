<%@ page language="java" pageEncoding="UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ page import="com.elegps.tscweb.model.TbAgentInfo"%>
<%@ page import="com.elegps.tscweb.model.TbEnterpriseInfo"%>
<%@ page import="java.util.List"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="css/ComboBox.css" rel="stylesheet" type="text/css">
<style type="text/css">
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
    <title>增加预充值信息</title>
  </head>
  
  <body>
<td background="images/gw_06.gif"><br>&nbsp;</td>
    <td width="84%" valign="top"><table width="100%" height="363" border="0" cellpadding="0" cellspacing="0"> <tr>
        <td height="5">&nbsp;</td>
        <td valign="top"><table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
          <tr>
            <td width="3%" height="30" align="center" background="images/3_04.gif"><img src="images/arrow3.gif" width="20" height="19"></td>
            <td width="45%" background="images/3_04.gif"><span class="STYLE4">您现在的位置：<span class="STYLE7">预充值添加</span></span></td>
            <td width="52%"><img src="images/3_03.jpg" width="518" height="35"></td>
          </tr>
        </table>
        <br>
        <br>
  <form name="advanceaddform" onSubmit="return formcheck();"  method="post" action="advance.do?CMD=advanceadd">
   <table align="center" width="450"  border="1" bordercolor="#C1DAD7">
    <tr>
      <td colspan="2" align="center"  class="list_header"><span class="STYLE77">预充值添加</span></td>
    </tr>
    <tr>
     <td align="right">总部/一级代理商名称:</td>
     <td width="10%" align="left">
        <select name="pagentid" id="pagentid"  style="{width:230;margin:0;FONT-SIZE:9pt;FONT-WEIGHT:normal;}" onchange="parentagentchang()">
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
        <select name="childagentid" id="childagentid"  style="{width:230;margin:0;FONT-SIZE:9pt;FONT-WEIGHT:normal;}"  onchange="childagentchang()"> 
          <option value=""> </option>
          <option value="-1">直属企业</option>
         </select>
       </td>
	</tr> 
     <tr>
    <td width="15%" align="right"><div align="right">企业名称:</div></td>
    <td width="40%" align="left" >
      <div align="left">
        <select name="ep" id="ep_id" style="{width:230;margin:0;FONT-SIZE:9pt;FONT-WEIGHT:normal;}">
         <option value=""> </option>
        </select>
      </div></td>
	</tr>  
    <tr>
      <td align="right"><label>预充值金额:</label></td>
      <td>
        <input style="width:230px;" class="checkbox" type="text" name="advance" maxlength="6"/>元（RMB）<font color="red"><B>*</B></font></td>
    </tr>
    <tr>
       <td colspan="2" align="center" class="list_footer">
        <input class=btn type="Submit" name="Submit" value="提交" />
        <input class=btn type="button" name="button" value="返回" onclick="toadvancelist()" />
        <input class=btn type="reset" name="reset" value="重置" />
      </td>
    </tr>
  </table>
</form></body>
</html>

<script language="javascript">

 function formcheck(){
 
 if(parseFloat(advanceaddform.advance.value)<1){
   alert("预充值必须大于1或等于1，请重新输入预充值!");
   advanceaddform.advance.focus();
   return false; 
 }
 
 if(advanceaddform.advance.value==""){
     alert("预充值不能为空，请输入预充值!");
     advanceaddform.advance.focus();
     return false; 
  }else{
    var je=advanceaddform.advance.value;
    if(!isNumber(je)){
      alert("请输入正确的整数金额,只能由数字组成");
      return false;
     }
  }
  return true;
 }
 
<!-- 装入上次查询用户类型查询、在线状态查询、用户有效状态查询的装态 ->
function goload() 
{ 
  var pagentid=parseInt(<%=request.getAttribute("pagentid")%>);
  var childagentid=parseInt(<%=request.getAttribute("childagentid")%>);
  var ep="<%=request.getAttribute("ep")%>";
  document.getElementById("pagentid").value=pagentid;
  document.getElementById("childagentid").value=childagentid;
  document.getElementById("ep_id").value=ep;  
} 
 
function toadvancelist(){
 document.location.href="javascript: history.back(-1)";
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


function parentagentchang(){
  var childagentselect= document.getElementById("childagentid");
  advanceaddform.childagentid.options.length=2; 
  advanceaddform.ep.options.length=0;
  var parentagent=advanceaddform.pagentid.value;
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
 
}

function childagentchang(){
        getEpinfo();	 
}


function getEpinfo(){
        var epselect= document.getElementById("ep_id");
        var pagent=advanceaddform.pagentid.value;
        var cagent=advanceaddform.childagentid.value;
        advanceaddform.ep.options.length=1;
        ChildAgent.getEpinfo(pagent,cagent,function(data){
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




</script>
