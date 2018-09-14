<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Date"%>

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
<link href="css/manage.css" rel="stylesheet" type="text/css">
<link href="css/list.css" rel="stylesheet" type="text/css">  
<script type="text/javascript"
	src="js/calendar.js">
	</script>
    <title>增加客户问题信息</title>
  </head>
  
  <body>
  <%
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		%>
 <td background="images/gw_06.gif">&nbsp;</td>
    <td width="84%" valign="top"><table width="100%" height="363" border="0" cellpadding="0" cellspacing="0"> <tr>
        <td height="5">&nbsp;</td>
        <td valign="top"><table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
          <tr>
            <td width="3%" height="30" align="center" background="images/3_04.gif"><img src="images/arrow3.gif" width="20" height="19"></td>
            <td width="45%" background="images/3_04.gif"><span class="STYLE4">您现在的位置：<span class="STYLE7">增加客户问题信息</span></span></td>
            <td width="52%"><img src="images/3_03.jpg" width="518" height="35"></td>
          </tr>
        </table>
        <br>
        <br>
  <form name="addquestion" onSubmit="return formcheck();" method="post"  action="question.do?CMD=add">
  <table align="center" width="535"  border="1" bordercolor="#C1DAD7">
    <tr>
      <td colspan="2" align="center"  class="list_header"><span class="STYLE77">增加客户问题信息</span></td>
    </tr>
     <tr>
            <td width="15%" align="right"><label>客户名称:</label></td>
              <td width="85%"><input style="width:180px;" name="cep" type="text" class="checkbox" size="15"><font color="red">&nbsp;<B>&nbsp;*</B></font></td>
    </tr>
    <tr>
            <td width="15%" align="right"><label>客户联系人:</label></td>
              <td width="85%"><input style="width:180px;" name="cname" type="text" class="checkbox" size="15"><font color="red">&nbsp;<B>&nbsp;*</B></font></td>
    </tr>
   <tr>
            <td width="15%" align="right"><label>客户电话:</label></td>
              <td width="85%"><input style="width:180px;" name="telephone" type="text" class="checkbox" size="15"><font color="red">&nbsp;<B>&nbsp;*</B></font></td>
    </tr>
    <tr>
            <td width="15%" align="right"><label>记录时间:</label></td>
              <td width="85%"><input style="width:180px;" name="recorder_time" type="text" class="checkbox" size="60" onclick="SelectDate(this,'yyyy-MM-dd hh:mm:ss')"  value="<%=dateFormat.format(date)%>"><font color="red">&nbsp;<B>&nbsp;*</B></font></td>
    </tr>
    <tr>
       <td width="15%" align="right"><div align="right">类型:</div></td>  
    <td  width="85%" align="left" >
      <div align="left">
        <select name="type" id="type" style="{width:180;margin:0;FONT-SIZE:9pt;FONT-WEIGHT:normal;}" onchange="agentchang(document.forms[0])">
	      <option value="1">硬件</option>
	      <option value="2">GPS监控端</option>
	      <option value="3">一呼通语音平台</option>
	      <option value="4">GPS数据</option>
	      <option value="5">语音</option>
	      <option value="6">未知</option>
        </select>
        <font color="red">&nbsp;<B>*</B></font>      </div></td>
     </tr>
     <tr>
    <td width="15%" align="right"><div align="right">问题内容:</div></td>
    <td width="85%" align="left" >
      <div align="left">
        <textarea name="question" cols="50" rows="5"></textarea>
        <font color="red">&nbsp;<B>*</B></font>      </div></td>
	</tr> 
     
	<tr>
       <td colspan="2" align="center" class="list_footer">
          <input class=btn type="Submit" name="Submit" value="提交" />&nbsp;
        <input class=btn type="button" name="button" value="返回" onclick="toquestionlist()" />&nbsp;
        <input class=btn type="reset" name="reset" value="重置" />      </td>
    </tr>
  </table>
  </form></body>
</html>

<script language="javascript">
function toquestionlist(){
 document.location.href="question.do?CMD=question_search";
}

function formcheck(){
var tel=addquestion.telephone.value;
  if(trim(addquestion.cep.value)==""){
     alert("请输入客户名称!");
     addquestion.cep.focus();
     return false;
  }
  
  if(trim(addquestion.cname.value)==""){
     alert("请输入客户联系人!");
     addquestion.cname.focus();
     return false;
  }
 
  if(tel!=""){ 
      if (tel.isMobile()||tel.isTel())  {  
            tel=tel.Trim();          
        }
        else {  
            alert("请输入正确的手机号码(11位数字)如:13800138000或电话号码如:020-5487514"); 
            addquestion.telephone.focus();
            return false;        
        }          
   }else {
      alert("客户电话不能为空!");
	  addquestion.telephone.focus();
	  return false;
   }
   
  if(trim(addquestion.type.value)==""){
     alert("请选择问题类型!");
     addquestion.type.focus();
     return false;
  }
  
  if(trim(addquestion.question.value)==""){
     alert("请输入问题描述!");
     addquestion.question.focus();
     return false;
  }
   return true;
}

String.prototype.Trim = function() {  
  var m = this.match(/^\s*(\S+(\s+\S+)*)\s*$/);  
  return (m == null) ? "" : m[1];  
}

String.prototype.isMobile = function() {  
 <!-- return (/^(?:13\d|15[89])-?\d{5}(\d{3}|\*{3})$/.test(this.Trim())); --> 
  return (/^(?:13\d|15\d)-?\d{5}(\d{3}|\*{3})$/.test(this.Trim())); 
} 

String.prototype.isTel = function()
{
    return (/^(([0\+]\d{2,3}-)?(0\d{2,3})-)(\d{7,8})(-(\d{3,}))?$/.test(this.Trim()));
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

</script>
