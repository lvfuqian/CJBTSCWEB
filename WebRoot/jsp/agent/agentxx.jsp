<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="com.elegps.tscweb.model.TbAgentInfo" %>

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
   
    
    <title>代理商详细信息</title>

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
            <td width="45%" background="images/3_04.gif"><span class="STYLE4">您现在的位置：<span class="STYLE7">代理商详细信息</span></span></td>
            <td width="52%"><img src="images/3_03.jpg" width="518" height="35"></td>
          </tr>
        </table>
        <br>
        <br>
   <table align="center" width="550"  border="1" bordercolor="#C1DAD7">
  	
    <tr>
      <td colspan="2" align="center"  class="list_header"><span class="STYLE77">代理商详细信息</span></td>
    </tr>
    <% 
       TbAgentInfo tbagentinfo=(TbAgentInfo)request.getAttribute("tbagentinfo");
           
    %>
    <%
       TbAgentInfo name = (TbAgentInfo)request
    			.getAttribute("agent_name");
    
     %>
     <tr>
      <%
									if(tbagentinfo.getAgent_PId()!=0){%>
									<td align="right">
										<label>
											父级代理商:
										</label>
									</td>
									<td>
											<%=name.getAgent_Name() %>
									</td>
									<%}
									 %>
     </tr>
    <tr>
      <td width=20% align="right">名称: </td>
      <td>
         <%=tbagentinfo.getAgent_Name()%>
      </td>
    </tr>
    <tr>
      <td align="right">地址:</td>
      <td>
         <%=tbagentinfo.getAgent_Address()%>
      </td>
    </tr>
    <tr>
      <td align="right">联系人:</td>
       <td>
         <%=tbagentinfo.getAgent_Man()%>
        </td>
	</tr>
	<tr>
      <td align="right">电话:</td>
        <td>
         <%=tbagentinfo.getAgent_Tel()%>
        </td>
	</tr>
	<tr>
      <td align="right">联系人:</td>
       <td>
         <%=tbagentinfo.getAgent_Man1()!=null?tbagentinfo.getAgent_Man1():""%>
        </td>
	</tr>
	<tr>
      <td align="right">电话:</td>
        <td>
         <%=tbagentinfo.getAgent_Tel1()!=null?tbagentinfo.getAgent_Tel1():""%>
        </td>
	</tr>
	<tr>
      <td align="right">邮箱:</td>
       <td>
         <%=tbagentinfo.getAgent_Email()%>
        </td>
    </tr>
	<tr>
      <td align="right">网址:</td>
      <td>
         <%=tbagentinfo.getAgent_URL()%>
        </td>
	</tr>
	
	<tr>
      <td align="right">QQ号码:</td>
        <td>
         <%=tbagentinfo.getAgent_QQ()%>
        </td>
	</tr>
	<tr>
      <td align="right">备注:</td>
        <td>
         <%=tbagentinfo.getAgent_Remark()%>
        </td>
	</tr>
	
	 <td colspan="2" align="center" class="list_footer">
      <input class=btn type="button" name="button" value="返回" onclick="goagentlist()" />
      </td>
  </table>
  
  </body>
</html>

<script language="javascript">
 function goagentlist(){
     document.location.href="javascript: history.back(-1)";  
	 <!-- document.location.href="agent.do?CMD=argment_search";  -->
	}
</script>