<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@ page import="com.elegps.tscweb.model.TbMenuInfo"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%
			TbMenuInfo menuinfo = (TbMenuInfo) request
			.getAttribute("tbmenuinfo");
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
		<title>权限菜单管理信息</title>
	</head>

	<body onload="goload()">
<td background="images/gw_06.gif">&nbsp;</td>
    <td width="84%" valign="top"><table width="100%" height="363" border="0" cellpadding="0" cellspacing="0"> <tr>
        <td height="5">&nbsp;</td>
        <td valign="top"><table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
          <tr>
            <td width="3%" height="30" align="center" background="images/3_04.gif"><img src="images/arrow3.gif" width="20" height="19"></td>
            <td width="45%" background="images/3_04.gif"><span class="STYLE4">您现在的位置：<span class="STYLE7">修改权限菜单 </span></span></td>
            <td width="52%"><img src="images/3_03.jpg" width="518" height="35"></td>
          </tr>
        </table>   
        <br>
        <br> 
					<form name="menumodify" method="post" action="">
						<table align="center" width="450"  border="1" bordercolor="#C1DAD7">
							<tr>
								<td colspan="2" align="center"  class="list_header"><span class="STYLE77">
									修改权限菜单
								</span></td>
							</tr>
							<tr>
								<td width="100" align="right">
									菜单名称:
								</td>
								<td>
									<input style="width:210px;" name="meun_name" type="text" class="checkbox" size="30"
										value="<%=menuinfo.getMenuName()%>" /><font color="red">&nbsp;<B>*</B></font>
								</td>

							</tr>
							<tr>
            			  <td   align="right"> 
              			  父菜单名:</td>
             			 <td>
             				  <select name="pmenu_id" id="pmneuid" style="{width:210;margin:0;FONT-SIZE:9pt;FONT-WEIGHT:normal;}">
	    						<option value="0">顶级菜单</option>
								<% 
   								List listmenu =(List)request.getAttribute("menubypmenuidList");
   								if(listmenu!=null){
   								for (int i= 0; i < listmenu.size(); i++) {
								TbMenuInfo tbmenuinfo=new TbMenuInfo();
								tbmenuinfo = (TbMenuInfo)listmenu.get(i);
								if(menuinfo.getMenuId().equals(tbmenuinfo.getMenuId())){
									 continue;
								}
								%>
	   			 				<option value="<%=tbmenuinfo.getMenuId()%>"><%=tbmenuinfo.getMenuName()%></option>
								<%} }%>
							</select><font color="red">&nbsp;<B>*</B></font>
            			  </td>
      
   					 </tr>  
							<tr>
								<td  align="right">
									URL地址:
								</td>
								<td>
									<input style="width:210px;" name="url" type="text" class="checkbox" size="30"
										value="<%=menuinfo.getUrl()%>" /><font color="red">&nbsp;<B>*&nbsp;(#目录&nbsp;&nbsp;.菜单失效)</B></font>
								</td>

							</tr>

							<tr>
								<td colspan="2" align="center" class="list_footer">
									<input class=btn  type="button" name="button1" value="提交" onclick="goto(document.forms[0])" />&nbsp;
									<input class=btn  type="button" name="button" value="返回"
										onclick="tomeunlist()" />
								</td>
							</tr>
						</table>
						
						<input type="hidden" name="menu_id"
							value="<%=menuinfo.getMenuId()%>" />
			</form></td></tr>
		
			
			</table>
	</body>
</html>

<script language="javascript">
 function tomeunlist(){  
   document.location.href="menu.do?CMD=menu_search";
 }
 
function goto(form){
 if(menumodify.meun_name.value=="")
   { 
      alert("菜单名为空，请输入菜单名");
      menumodify.meun_name.focus();
      return false;
   }
   if(menumodify.url.value==""){
      alert("菜单地址为空，请输入菜单地址");
      menumodify.url.focus();
      return false;
   } 
	var flag = window.confirm("确定要修改此记录吗？");
	if(flag){
		form.action="menu.do?CMD=menumodify";
		form.submit(); 
	}
}

function goload() 
{ 
  var pmneu_id="<%=menuinfo.getPmenuId()%>";
  if(pmneu_id=="null"){
  document.getElementById("pmneuid").value=0;
  }else{
  document.getElementById("pmneuid").value=pmneu_id;   
  } 
}
</script>