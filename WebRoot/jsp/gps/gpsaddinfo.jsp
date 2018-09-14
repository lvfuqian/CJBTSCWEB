<%@ page language="java" pageEncoding="UTF-8"%> 

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ page import="com.elegps.tscweb.model.TbMsInfo"%>
<%@ page import="com.elegps.tscweb.model.TbGrpInfo"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.lang.String" %>

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
    <title>增加GPS服务商登录账号信息</title>
  </head>
  
  <body>
 <td background="images/gw_06.gif">&nbsp;</td>
    <td width="84%" valign="top"><table width="100%" height="363" border="0" cellpadding="0" cellspacing="0"> <tr>
        <td height="5">&nbsp;</td>
        <td valign="top"><table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
          <tr>
            <td width="3%" height="30" align="center" background="images/3_04.gif"><img src="images/arrow3.gif" width="20" height="19"></td>
            <td width="45%" background="images/3_04.gif"><span class="STYLE4">您现在的位置：<span class="STYLE7">GPS接入管理添加</span></span></td>
            <td width="52%"><img src="images/3_03.jpg" width="518" height="35"></td>
          </tr>
        </table>
        <br>
        <br>
  <form name="gpsaddform"  onSubmit="return formcheck();"  method="post"  action="gps.do?CMD=add">
  <table align="center" width="450"  border="1" bordercolor="#C1DAD7">
  <tr>
      <td colspan="2" align="center"  class="list_header"><span class="STYLE77">GPS接入管理添加</span></td>
    </tr>
    <tr>
    <td width="15%" align="right"><label>运营商名称：</label></td>
    <td width="35%" align="left">
       <input style="width:180px;" class="checkbox" type="text" name="gps_name" /><font color="red">&nbsp;<B>*</B></font> 
	</td>
	</tr>
    <tr>
    <td width="15%" align="right"><label>账号：</label></td>
    <td width="35%" align="left">
       <input style="width:180px;" class="checkbox" type="text" name="gps_id" /><font color="red">&nbsp;<B>*</B></font> 
	</td>
	</tr>
	<tr>
	<td width="15%" align="right"><label>密码：</label></td>
     <td width="35%" align="left">
      <input style="width:180px;" class="checkbox" type="text" name="pasword" /> <font color="red"><B>*</B></font>
    </td> 
    </tr>
    <tr>
      <td colspan="2" align="center" class="list_footer">
          <input class=btn type="Submit" name="Submit" value="提交" />&nbsp;
        <input class=btn type="button" name="button" value="返回" onclick="togpslist()" />&nbsp;
      </td>
    </tr>
  </table>
</body>
</html>

<script type="text/javascript">
 function formcheck(){
 if(trim(gpsaddform.gps_name.value)=="")
   { 
      alert("运营商名称为空，请输入运营商名称");
      gpsaddform.gps_name.focus();
      return false;
   }
  if(trim(gpsaddform.gps_id.value)=="")
   { 
      alert("GPS服务商登录账号为空，请输入GPS服务商登录账号");
      gpsaddform.gps_id.focus();
      return false;
   }
   if(!isNumberChar(trim(gpsaddform.gps_id.value))){
     alert("GPS服务商登录账号只能由字母和数字组成,请重新输入.");
   	 gpsaddform.gps_id.focus();
     return false;
   }
   if(gpsaddform.pasword.value.length<6){
    alert("GPS服务商登录密码长度必须大于等于6位");
    gpsaddform.pasword.focus();
    return false;
   }
   
   
   return true;
 }
 
 
 function togpslist(){
  url="gps.do?CMD=gps_id_list";
  document.location.href=url;
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

//字符串是不是由数字跟字母组成
function isNumberChar(yeid)
{
	var reg=/^[a-z0-9.]*$/gi  
    if(reg.test(yeid))
    {
      return true; 
    } 
    else {
      return false;
    } 
}

</script>
