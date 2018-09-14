<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="com.elegps.tscweb.model.TbORGAdvanceInfo" %>

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
   
    
    <title>预充值详细信息</title>

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
   <form id="form1" name="form1" method="post" action="">
  <td background="images/gw_06.gif">&nbsp;</td>
    <td width="84%" valign="top"><table width="100%" height="363" border="0" cellpadding="0" cellspacing="0"> <tr>
        <td height="5">&nbsp;</td>
        <td valign="top"><table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
          <tr>
            <td width="3%" height="30" align="center" background="images/3_04.gif"><img src="images/arrow3.gif" width="20" height="19"></td>
            <td width="45%" background="images/3_04.gif"><span class="STYLE4">您现在的位置：<span class="STYLE7">预充值详细信息</span></span></td>
            <td width="52%"><img src="images/3_03.jpg" width="518" height="35"></td>
          </tr>
        </table>
        <br>
        <br>
   <table align="center" width="550"  border="1" bordercolor="#C1DAD7">
  	
    <tr>
      <td colspan="2" align="center"  class="list_header"><span class="STYLE77">预充值详细信息</span></td>
    </tr>
    <% 
       TbORGAdvanceInfo tbadvanceinfo=(TbORGAdvanceInfo)request.getAttribute("advaneceinfo");
       String valireuslt="";
       String validate="";
       String valiperson="";
       String remark="";
       if(tbadvanceinfo.getValidateResult()==1){
         valireuslt="通过";
       }else{
         valireuslt="没有验证";
       }
       if(!(tbadvanceinfo.getValidateDate()==null)){
         validate=tbadvanceinfo.getValidateDate().toString();
       }
       
        if(!(tbadvanceinfo.getValidatePerson()==null)){
         valiperson=tbadvanceinfo.getValidatePerson();
       }
       
        if(!(tbadvanceinfo.getRemark()==null)){
          remark=tbadvanceinfo.getRemark();
       }
    %>
     <tr>
      <td align="right"><span style="font-size: 10.5pt; font-family: 宋体;">预充值机构类型</span>: </td>
      <td >${requestScope.advancetype}</td>
    </tr>
   <tr>
      <td align="right"><span style="font-size: 10.5pt; font-family: 宋体;">预充值机构名</span>: </td>
      <td >${requestScope.advanename}</td>
    </tr>
    <tr>
      <td align="right"><span style="font-size: 10.5pt; font-family: 宋体;">预充值金额</span>: </td>
      <td ><%=tbadvanceinfo.getAdvanceCash()%></td>
    </tr>
    <tr>
      <td align="right"><span style="font-size: 10.5pt; font-family: 宋体;">预充值日期</span>:</td>
      <td><%=tbadvanceinfo.getAdvanceDate()%></td>
    </tr>
	<tr>
      <td align="right"><span style="font-size: 10.5pt; font-family: 宋体;">预充值填写人</span>:</td>
      <td>
      	<%=tbadvanceinfo.getAdvancePerson()%>
      </td>
	</tr>
	<tr>
      <td align="right"><span style="font-size: 10.5pt; font-family: 宋体;">验证日期</span>:</td>
      <td><p><%=validate%></p></td>
    </tr>
	<tr>
      <td align="right"><span style="font-size: 10.5pt; font-family: 宋体;">验证人</span>:</td>
      <td><%=valiperson%></td>
	</tr>
	<tr>
      <td align="right"><span style="font-size: 10.5pt; font-family: 宋体;">验证结果</span>:</td>
      <td><%=valireuslt%></td>
	</tr>
	<tr>
      <td align="right"><span style="font-size: 10.5pt; font-family: 宋体;">备注</span>:</td>
      <td><%=remark%></td>
	</tr>
	 <td colspan="2" align="center" class="list_footer">
      <input class=btn type="button" name="button" value="返回" onclick="goadvancelist()" />
      </td>
  </table>
  
  </body>
</html>

<script language="javascript">
 function goadvancelist(){
  document.location.href="javascript: history.back(-1)";  
  <!-- document.location.href="advance.do?CMD=advance_search";  -->
 }
</script>