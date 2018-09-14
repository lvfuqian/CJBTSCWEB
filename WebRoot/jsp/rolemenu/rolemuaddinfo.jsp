<%@ page language="java" pageEncoding="UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ page import="com.elegps.tscweb.model.TbRoleInfo"%>
<%@ page import="com.elegps.tscweb.model.TbMenuInfo"%>
<%@ page import="java.util.List"%>


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
<style type="text/css">
<!--
.STYLE9 {
	color: #000000;
	font-weight: bold;
	text-indent: 10px;
}
-->
</style>    
    <title>增加角色菜单关系信息</title>
  </head>
  
  <body>
 
 <td background="images/gw_06.gif">&nbsp;</td>
    <td width="84%" valign="top"><table width="100%" height="363" border="0" cellpadding="0" cellspacing="0"> <tr>
        <td height="5">&nbsp;</td>
        <td valign="top"><table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
          <tr>
            <td width="3%" height="30" align="center" background="images/3_04.gif"><img src="images/arrow3.gif" width="20" height="19"></td>
            <td width="45%" background="images/3_04.gif"><span class="STYLE4">您现在的位置：<span class="STYLE7">角色菜单关系添加</span></span></td>
            <td width="52%"><img src="images/3_03.jpg" width="518" height="35"></td>
          </tr>
        </table>
        <br>
        <br>
  <form name="msaddfor"  method="post"  action="rolemenu.do?CMD=add">
  <table align="center" width="450"  border="1" bordercolor="#C1DAD7">
    <tr>
       <td colspan="2" align="center" class="list_header"><span class="STYLE77">角色菜单关系添加</span></td>
    </tr>
    <tr>
             <td width="110" align="right">角色名:</td>
                <td align="left">
              <select name="roleid" style="{width:230;margin:0;FONT-SIZE:9pt;FONT-WEIGHT:normal;}">
  			 <%                                        
  				 List listrole =(List)request.getAttribute("listrole");
  				 if(listrole!=null){
   					for (int i= 0; i < listrole.size(); i++) {
						 TbRoleInfo tbroleinfo=new TbRoleInfo();
						 tbroleinfo = (TbRoleInfo)listrole.get(i);
			%>
	    			<option value="<%=tbroleinfo.getRoleId()%>"><%=tbroleinfo.getRoleName()%></option>
			<%} }%>
		</select><font color="red">&nbsp;<B>*</B></font>
        </td>
    </tr>
    <tr>
              <td align="right"><label>菜单名:</label></td>
        <td>
        <select name="menuid" style="{width:230;margin:0;FONT-SIZE:9pt;FONT-WEIGHT:normal;}">
		<% 
   		List listmenu =(List)request.getAttribute("listmenu");
  		if(listmenu!=null){
   			for (int i= 0; i < listmenu.size(); i++) {
				 TbMenuInfo tbmenuinfo=new TbMenuInfo();
				 tbmenuinfo = (TbMenuInfo)listmenu.get(i);
				%>
	   		 <option value="<%=tbmenuinfo.getMenuId()%>"><%=tbmenuinfo.getMenuName()%></option>
		<%} }%>
		</select><font color="red">&nbsp;<B>*</B></font>
      </td>
    </tr>
	<tr>
        <td colspan="2" align="center" class="list_footer">
          <input class=btn type="Submit" name="Submit" value="提交" />
        <input class=btn type="button" name="button" value="返回" onclick="tomslist()" />&nbsp;&nbsp;&nbsp;
     
      </td>
    </tr>
  </table></body>
</html>

<script language="javascript">
function tomslist(){
 document.location.href="rolemenu.do?CMD=rolemenu_search";
}


</script>
