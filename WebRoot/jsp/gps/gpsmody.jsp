<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@ page import="com.elegps.tscweb.model.TbGpsInfo"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
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
		<title>修改服务商登录信息</title>
	</head>

	<body>
<td background="images/gw_06.gif">&nbsp;</td>
    <td width="84%" valign="top"><table width="100%" height="363" border="0" cellpadding="0" cellspacing="0"> <tr>
        <td height="5">&nbsp;</td>
        <td valign="top"><table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
          <tr>
            <td width="3%" height="30" align="center" background="images/3_04.gif"><img src="images/arrow3.gif" width="20" height="19"></td>
            <td width="45%" background="images/3_04.gif"><span class="STYLE4">您现在的位置：<span class="STYLE7">GPS接入管理修改 </span></span></td>
            <td width="52%"><img src="images/3_03.jpg" width="518" height="35"></td>
          </tr>
        </table>   
        <br>
        <br> 
        <% 
			TbGpsInfo gpsinfo = (TbGpsInfo) request.getAttribute("tbgpsinfo");
        %>
		<form name="gpsmdi" id="gpsmdi"  method="post">
			<table align="center" width="450"  border="1" bordercolor="#C1DAD7">
				<tr>
    		  <td colspan="2" align="center"  class="list_header"><span class="STYLE77">GPS接入管理修改</span></td>
    			</tr>
    			<tr>
   					<td width="15%" align="right"><label>运营商名称：</label></td>
   					<td width="35%" align="left">
      					<input style="width:180px;" class="checkbox" type="text" name="gps_name" value="<%=gpsinfo.getGps_name()%>"/><font color="red">&nbsp;<B>*</B></font> 
					</td>
			    </tr>
				<tr>
					<td width="30%" align="right"><label>登录账号:</label></td>
					<td width="731">
						<input class="checkbox"  style="width:180px;" type="text" name="gps_id" value="<%=gpsinfo.getGpsop_id()%>" readonly/>
					</td>
				</tr>
				<tr>
					<td width="30%" align="right"><label>登录密码:</label></td>
					<td width="731">
						<input class="checkbox"  style="width:180px;" type="text" name="password" value="<%=gpsinfo.getPassword()%>"/><font color="red">&nbsp;<B>*</B></font>
					</td>
				</tr>	
				<tr>
					<td colspan="2" align="center" class="list_footer">
						<input class=btn  type="button" name="button1" value="提交" onclick="goto(document.forms[0])"/>&nbsp;
						<input class=btn  type="button" name="button" value="返回" onclick="togpslist()" />
					</td>
				</tr>
			</table></form></td></tr></table>
		
	</body>
</html>

<script language="javascript">

 function togpslist(){ 
  document.location.href="javascript: history.back(-1)";  
  <!-- document.location.href="gps.do?CMD=gps_id_list";  -->
 }

function goto(form){
if(gpsmdi.password.value=="")
   { 
      alert("GPS服务商登录密码为空，请输入GPS服务商登录密码");
      gpsmdi.password.focus();
      return false;
   }
   if(gpsmdi.password.value.length<6){
    alert("GPS服务商登录密码长度必须大于等于6位");
    gpsmdi.password.focus();
    return false;
   }
   
	var flag = window.confirm("确定要修改此记录吗？");
	if(flag){
	    form.action="gps.do?CMD=gpsmodify";
		form.submit(); 
	}
}

</script>