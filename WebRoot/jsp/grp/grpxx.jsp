<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="com.elegps.tscweb.model.TbGrpInfo" %>
<%@ page import="com.elegps.tscweb.model.TbEnterpriseInfo" %>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%
   String epname="";
   TbEnterpriseInfo tbepname=(TbEnterpriseInfo)request.getAttribute("epname");
   if(tbepname!=null){
   epname=tbepname.getEp_Name();
   }
 %>

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
   
    
    <title>群组详细信息</title>

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
            <td width="45%" background="images/3_04.gif"><span class="STYLE4">您现在的位置：<span class="STYLE7">群组详细信息</span></span></td>
            <td width="52%"><img src="images/3_03.jpg" width="518" height="35"></td>
          </tr>
        </table>
        <br>
        <br>
   <table align="center" width="550"  border="1" bordercolor="#C1DAD7">
  	
    <tr>
      <td colspan="2" align="center"  class="list_header"><span class="STYLE77">群组详细信息</span></td>
    </tr>
    <% 
       TbGrpInfo tbgrpinfo=(TbGrpInfo)request.getAttribute("tbgrpinfo");
       String qzlx=null;  //群组类型    
       if(tbgrpinfo.getGrptype()==1){
         qzlx="电召群组";
       }else{
         qzlx="物流群组";
       }
        
       String qzthzt=null;  //群组通话状态









       if(tbgrpinfo.getStatus()==1){
       	qzthzt="通话中";
       }
       else{
       	qzthzt="未在通话中";
       }  
       String flag=null;     //群组有效状态 
       if(tbgrpinfo.getFlag()==1){
         flag="正常";
       }
       else{
        flag="失效";
       }
       
       String lf=null;  //漏呼
       if(tbgrpinfo.getGrplf()==0){
         lf="是";
       }else{
         lf="否";
       }
       String zngx = "否";
       if(tbgrpinfo.getC03() != null && tbgrpinfo.getC03() != ""){
       if(tbgrpinfo.getC03().equals("1")){
       		zngx = "是";
       }
       }
    %>
   <tr>
      <td align="right"><span style="font-size: 10.5pt; font-family: 宋体;">所属企业</span>: </td>
      <td ><%=epname%></td>
    </tr>
    <tr>
      <td align="right"><span style="font-size: 10.5pt; font-family: 宋体;">群组号码</span>: </td>
      <td ><%=tbgrpinfo.getGrpid()%></td>
    </tr>
    <tr>
      <td align="right"><span style="font-size: 10.5pt; font-family: 宋体;">群组名字</span>:</td>
      <td><%=tbgrpinfo.getGrpname()%></td>
    </tr>
	<tr>
      <td align="right"><span style="font-size: 10.5pt; font-family: 宋体;">创建者终端号码</span>:</td>
      <td>
      	<%=tbgrpinfo.getMsid()%>
      </td>
	</tr>
	<tr>
      <td align="right"><span style="font-size: 10.5pt; font-family: 宋体;">注册日期</span>:</td>
      <td><p><%=tbgrpinfo.getRegtime()%></p></td>
    </tr>
	<tr>
      <td align="right"><span style="font-size: 10.5pt; font-family: 宋体;">群组通话状态</span>:</td>
      <td><%=qzthzt%></td>
	</tr>
	<tr>
      <td align="right"><span style="font-size: 10.5pt; font-family: 宋体;">发起者MS_Id</span>:</td>
      <td><%=tbgrpinfo.getPptmsid()%></td>
	</tr>
	<tr>
      <td align="right"><span style="font-size: 10.5pt; font-family: 宋体;">群组类别</span>:</td>
      <td><%=qzlx%></td>
	</tr>
	<tr>
      <td align="right"><span style="font-size: 10.5pt; font-family: 宋体;">群组有效状态</span>:</td>
      <td><%=flag%></td>
	</tr>
	<tr>
      <td align="right"><span style="font-size: 10.5pt; font-family: 宋体;">组内信息共享</span>:</td>
      <td><%=zngx%></td>
	</tr>
	<!--<tr>
      <td align="right"><span style="font-size: 10.5pt; font-family: 宋体;">是否漏呼</span>:</td>
      <td></td>
	</tr>
	--><tr>
      <td align="right"><span style="font-size: 10.5pt; font-family: 宋体;">单次通话时长</span>:</td>
      <td><%=tbgrpinfo.getTalkinglast()/60000%>分钟</td>
	</tr>
	 <td colspan="2" align="center" class="list_footer">
      <input class=btn type="button" name="button" value="返回" onclick="gomslist()" />
      </td>
  </table>
  
  </body>
</html>

<script language="javascript">
 function gomslist(){
  document.location.href="javascript: history.back(-1)";  
  <!-- document.location.href="grp.do?CMD=grp_search";  -->
 }
</script>