<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@ page import="com.elegps.tscweb.model.TbORGAdvanceInfo"%>

<%
			TbORGAdvanceInfo tbadvanceinfo = (TbORGAdvanceInfo) request
			.getAttribute("advaneceinfo");
			String remark="";
			if(tbadvanceinfo.getRemark()!=null){
			 remark=tbadvanceinfo.getRemark();
			}
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
	FILTER: progid :   DXImageTransform .   Microsoft . 
		
		Gradient(GradientType =   0, StartColorStr =   #ffffff, EndColorStr = 
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
		<title>验证预充值信息</title>
	</head>

	<body onload="onload()">
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
									<span class="STYLE4">您现在的位置：<span class="STYLE7">验证预充值信息</span>
									</span>
								</td>
								<td width="52%">
									<img src="images/3_03.jpg" width="518" height="35">
								</td>
							</tr>
						</table>
						<br>
						<br>
						<form name="vadildate" method="post" action="">
							<table align="center" width="450" border="1"
								bordercolor="#C1DAD7">
								<tr>
									<td colspan="2" align="center" class="list_header">
										<span class="STYLE77"> 验证预充值信息 </span>
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
											name="advancetype" value="${requestScope.advancetype}"
											readonly />
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
											name="advance" value="<%=tbadvanceinfo.getAdvanceCash()%>"
											readonly />元（RMB）
										<font color="red"><B>*</B></font>
									</td>
								</tr>
                                <tr>
								<td width="55" align="right">
									<div align="right">
										验证:
									</div>
								</td>
								<td width="100" align="left">
									<div align="left">
										<select name="aresult" id="vresult"
											style="width: 230; margin: 0; FONT-SIZE: 9pt; FONT-WEIGHT: normal;">
											<option value="0">
												没有验证
											</option>
											<option value="1">
												通过
											</option>
											<option value="2">
												没通过
											</option>
										</select>
									</div>
								</td>
								</tr>
								 <tr>
								<td width="55" align="right">
									<div align="right">
										备注:
									</div>
								</td>
								<td>
								<input style="width: 230px;" class="checkbox" type="text"
											name="remark" value="<%=remark%>"
											 />
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
							<input type="hidden" name="advanceid"
								value="<%=tbadvanceinfo.getAdvanceId()%>" />
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
  form.action="advance.do?CMD=validate";
  form.submit(); 
}


<!-- 装入上次查询用户类型查询、在线状态查询、用户有效状态查询的装态 -->
function onload() 
{ 
  var aresult=<%=tbadvanceinfo.getValidateResult()%>;
  document.getElementById("vresult").value=aresult;
} 
</script>