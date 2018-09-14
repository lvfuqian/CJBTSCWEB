<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@ page import="com.elegps.tscweb.model.TbORGAdvanceInfo"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<% 
      TbORGAdvanceInfo tbadvanceinfo=(TbORGAdvanceInfo)request.getAttribute("advaneceinfo");
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
	FILTER: progid : DXImageTransform . Microsoft .
		Gradient(GradientType = 0, StartColorStr = #ffffff, EndColorStr =
		#cecfde);
	BORDER-LEFT: #7b9ebd 1px solid;
	CURSOR: hand;
	COLOR: black;
	PADDING-TOP: 2px;
	BORDER-BOTTOM: #7b9ebd 1px solid
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

.STYLE8 {
	font-size: 12px
}

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
		<title>修改预充值信息</title>
	</head>

	<body>
		<td background="images/gw_06.gif">
			&nbsp;
		</td>
		<td width="84%" valign="top">
			<table width="100%" height="363" border="0" cellpadding="0"
				cellspacing="0">
				<tr>
					<td height="5">
						&nbsp;
					</td>
					<td valign="top">
						<table width="100%" border="0" align="center" cellpadding="0"
							cellspacing="0">
							<tr>
								<td width="3%" height="30" align="center"
									background="images/3_04.gif">
									<img src="images/arrow3.gif" width="20" height="19">
								</td>
								<td width="45%" background="images/3_04.gif">
									<span class="STYLE4">您现在的位置：<span class="STYLE7">修改预充值信息</span>
									</span>
								</td>
								<td width="52%">
									<img src="images/3_03.jpg" width="518" height="35">
								</td>
							</tr>
						</table>
						<br>
						<br>
						<form name="advancemodify" method="post" action="">
							<table align="center" width="450" border="1"
								bordercolor="#C1DAD7">
								<tr>
									<td colspan="2" align="center" class="list_header">
										<span class="STYLE77"> 修改预充值信息 </span>
									</td>
								</tr>
						
								<tr>
									<td width="20%" align="right">
										<div align="right">
											预充值类型:
										</div>
									 </td>
										<td>
										<input class="checkbox" style="width: 230px;" type="text"
											name="advancetype" value="${requestScope.advancetype}" readonly />
									   </td>
									
								</tr>
								<tr>
									<td width="20%" align="right">
										<div align="right">
											预充值机构名:
										</div>
									</td>
									<td>
										<input class="checkbox" style="width: 230px;" type="text"
											name="advanename" value="${requestScope.advanename}" readonly />
									</td>
								</tr>
								<tr>
									<td width="20%" align="right">
										<label>
											预充值金额:
										</label>
									</td>
									<td>
										<input style="width: 230px;" class="checkbox" type="text"
											name="advance" maxlength="6" value="<%=tbadvanceinfo.getAdvanceCash()%>" />元（RMB）
										<font color="red"><B>*</B>
										</font>
									</td>
								</tr>
								<tr>
									<td colspan="2" align="center" class="list_footer">
										<input class=btn type="button" name="button1" value="提交"
											onclick="goto(document.forms[0])" />
										&nbsp;
										<input class=btn type="button" name="button" value="返回"
											onclick="toadvancelist()" />
									</td>
								</tr>
							</table>
							<input type="hidden" name="advanceid"	value="<%=tbadvanceinfo.getAdvanceId()%>" />
						</form>
					</td>
				</tr>
			</table>
	</body>
</html>

<script language="javascript">
 function toadvancelist(){  
  document.location.href="javascript: history.back(-1)";  
  <!-- document.location.href="advance.do?CMD=advance_search"; -->
 }
 
function goto(form){
  if(parseFloat(advancemodify.advance.value)<1){
     alert("预充值必须大于1或等于1，请重新输入预充值!");
     advancemodify.advance.focus();
     return false; 
    }
    
	if(advancemodify.advance.value==""){
	 alert("预充值不能为空，请输入预充值!");
     advancemodify.advance.focus();
     return false; 
    }else{
      var je=advancemodify.advance.value;
      if(!isNumber(je)){
        alert("请输入正确的整数金额,只能由数字组成");
        return false;
       }
    }
 
     form.action="advance.do?CMD=modify";
     form.submit();
}


function isNumber(yeid)
{
	var charcode;
	for (var i=0; i<yeid.length; i++)	
	{
		charcode = yeid.charCodeAt(i);
		if (charcode < 48 || charcode > 57)	
			return false;
	}
	return true;
}

</script>