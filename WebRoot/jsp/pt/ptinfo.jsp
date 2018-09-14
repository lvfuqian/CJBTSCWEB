<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="com.elegps.tscweb.model.TbPhoneTypeInfo" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>


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
    <title>手机型号详细信息</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <body>
  	<c:set value="${requestScope.tbx}" var="tbx"></c:set>
   <form id="form1" name="form1" method="post" action="">
 <td background="images/gw_06.gif">&nbsp;</td>
    <td width="84%" valign="top"><table width="100%" height="363" border="0" cellpadding="0" cellspacing="0"> <tr>
        <td height="5">&nbsp;</td>
        <td valign="top"><table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
          <tr>
            <td width="3%" height="30" align="center" background="images/3_04.gif"><img src="images/arrow3.gif" width="20" height="19"></td>
            <td width="45%" background="images/3_04.gif"><span class="STYLE4">您现在的位置：<span class="STYLE7">手机型号详细信息</span></span></td>
            <td width="52%"><img src="images/3_03.jpg" width="518" height="35"></td>
          </tr>
        </table>
        <br>
        <br>
  <table align="center" width="550"  border="1" bordercolor="#C1DAD7">
  	
    <tr>
      <td colspan="2" align="center"  class="list_header"><span class="STYLE77">手机型号详细信息</span></td>
    </tr>
    <% 
       SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
       TbPhoneTypeInfo pt=(TbPhoneTypeInfo)request.getAttribute("ptInfo");
    %>
     <tr>
              <td align="right"><label>型号:</label></td>
              <td width="5%"><%=pt.getType()==null?"":pt.getType() %></td>
    </tr>
     <tr>
              <td align="right"><label>产品名称:</label></td>
              <td width="5%"><%=pt.getProduct()==null?"":pt.getProduct() %></td>
    </tr>
    <tr>
              <td align="right">cpu指令集:</td>
              <td><%=pt.getCpuAbi()==null?"":pt.getCpuAbi() %></td>
      
    </tr>
	<tr>
      <td width="15%" align="right">cpu指令集2:</td>
      <td width="35%"><%=pt.getCpuAbiTwo()==null?"":pt.getCpuAbiTwo() %></td>
	</tr>
	<tr>
      <td width="15%" align="right">描述标签:</td>
      <td width="35%">
      <%=pt.getTags()==null?"":pt.getTags() %></td>
	</tr>
	<tr>
      <td width="15%" align="right">用户可见名称:</td>
      <td width="35%">
      <%=pt.getModel()==null?"":pt.getModel() %>
      </td>
	</tr>
	<tr>
      <td width="15%" align="right">SDK版本:</td>
      <td width="35%">
       <%=pt.getVersionSdk()==null?"":pt.getVersionSdk() %>
      </td>
	</tr>
	<tr>
      <td width="15%" align="right">版本增量:</td>
      <td width="35%">
       <%=pt.getVersionIncremental()==null?"":pt.getVersionIncremental() %>
      </td>
	</tr>
	<tr>
      <td width="15%" align="right">版本代码:</td>
      <td width="35%"><p><%=pt.getVersionRelease()==null?"":pt.getVersionRelease() %></p></td>
    </tr>
    <tr>
      <td width="15%" align="right">版本号名称:</td>
      <td width="35%">
       <%=pt.getVersionCodename()==null?"":pt.getVersionCodename() %>
      </td>
	</tr>
	
	<tr>
      <td align="right">设备参数:</td>
      <td><%=pt.getDevice()==null?"":pt.getDevice() %></td>
	</tr>
	<tr>
      <td align="right">显示屏参数:</td>
      <td><%=pt.getDisplay()==null?"":pt.getDisplay() %></td>
    </tr>
    <tr>
      <td align="right">系统定制商:</td>
      <td>
         <%=pt.getBrand()==null?"":pt.getProduct() %>
      </td>
    <tr>
     <tr>
      <td align="right">主板:</td>
      <td><%=pt.getBoard()==null?"":pt.getBoard() %></td>
    <tr>
     <tr>
      <td align="right">唯一识别码:</td>
      <td><%=pt.getFingerprint()==null?"":pt.getFingerprint() %></td>
    <tr>
    <tr>
      <td align="right">修订版本列表:</td>
      <td><%=pt.getSdkId()==null?"":pt.getSdkId() %></td>
    <tr>
     <tr>
      <td align="right">硬件制造商:</td>
      <td>
        <%=pt.getManufacturer()==null?"":pt.getManufacturer() %>
      </td>
    <tr>
    <td align="right">USER:</td>
       <td><%=pt.getSdkUser()==null?"":pt.getSdkUser() %></td>
    </tr>
    <tr>
       <td align="right">记录时间:</td>
       <td><%=pt.getTime()==null?"":pt.getTime() %></td>
    </tr>
    <tr>
    	  <td colspan="2" align="center" class="list_footer">
          <input class=btn type="button" name="button" value="返回" onclick="gomslist()" />
      </td>
    <tr>
  </table>
  </body>
</html>
<script language="javascript">
 function gomslist(){
   document.location.href="javascript: history.back(-1)";  
 //<!-- document.location.href="ms.do?CMD=ms_search";  -->
 }
</script>

