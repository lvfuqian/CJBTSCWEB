<%@ page language="java" import="java.util.*,com.elegps.tscweb.model.*" pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>无标题文档</title>
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
.STYLE1 {
	font-size: 15px;
	color: #FF0000;
}.STYLE2 {
	font-size: 15px;
	color: #CC00CC;
}.STYLE3 {
	font-size: 15px;
}

-->
</style></head>

<body>
 <form name="LoginForm" action="login.do" method="post">
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td width="603" background="images/wxxd_02.gif"><img src="images/wxxd_01.gif" width="603" height="100" /></td>
    <td width="260" background="images/wxxd_02.gif">&nbsp;</td>
    <td width="388" valign="bottom" background="images/wxxd_03.gif"><table width="100%" height="78" border="0" align="right" cellpadding="0" cellspacing="0">
      <tr>
        <td width="315">&nbsp;</td>
      </tr>
      <tr>
        <td align="right"><span class="STYLE1">欢迎您</span>:&nbsp;<span class="STYLE2"><%=request.getSession().getAttribute("users")%></span>|<span class="STYLE3"> <a target="_top" href="login.jsp" onclick="javascript:parent.window.location.replace(this.href); event.returnValue=false;">[注销]</a></span> </td>
      </tr>
    </table></td>
  </tr>
</table>
</form>
</body>
</html>
