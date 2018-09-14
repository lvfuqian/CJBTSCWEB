<%@ page language="java" pageEncoding="UTF-8"%>
<%@page import="com.elegps.tscweb.model.TbAgentInfo"%>
<%@page import="com.elegps.tscweb.model.TbEnterpriseInfo"%>
<%@page import="com.elegps.tscweb.model.TbCheckMInfo"%>
<%@page import="com.elegps.tscweb.model.TbPFInfo"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@ page import="com.elegps.tscweb.model.TbUserInfo"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Calendar"%>
<%@ page import="java.text.SimpleDateFormat"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
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
		<title>审核详细</title>
	</head>

	<body onload="goload()">
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
									<span class="STYLE4">您现在的位置：<span class="STYLE7">审核详细信息</span>
									</span>
								</td>
								<td width="52%">
									<img src="images/3_03.jpg" width="518" height="35">
								</td>
							</tr>
						</table>
						<br>
						<br>
						<%
									int rid =Integer.parseInt( request.getSession().getAttribute("roleId").toString());
									TbCheckMInfo cmInfo =(TbCheckMInfo)request.getAttribute("cmInfo");
									String m =cmInfo.getResMoney();
									  if(m==null || m==""){
									  	m="0.00";
									  	cmInfo.setResMoney(m);
									  }
									Calendar c = Calendar.getInstance();//获得一个日历的实例     
									SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
									String dt =format2.format(cmInfo.getProTime());
								 %>
						<form name="addmoneyform" onSubmit="return formcheck();"
							method="post">
							<table align="center" width="450" border="1"
								bordercolor="#C1DAD7">
								<tr>
									<td colspan="2" align="center" class="list_header">
										<span class="STYLE77">审核信息</span>
									</td>
								</tr>
								
								<tr>
									<td align="right">
										<label>
											申请人:
										</label>
									</td>
									<td>
										<label><%=cmInfo.getProposer().getUserName() %></label>
									</td>
								</tr>
								<tr>
									<td align="right">
										<label>
											申请金额:
										</label>
									</td>
									<td>
										<label><%=cmInfo.getResMoney() %></label>
									</td>
								</tr>
								<tr>
									<td align="right">
										<label>
											申请时间:
										</label>
									</td>
									<td>
										<label>
										<%=dt %>
										 
										</label>
									</td>
								</tr>
								<tr>
									<td align="right">
										<label>
											申请描述:
										</label>
									</td>
									<td>
										<label><%=cmInfo.getProDescription() %></label>
									</td>
								</tr>
								<tr>
									<td align="right">
										<label>
											充值对象:
										</label>
									</td>
									<td>
										<label>
										<%
											if(cmInfo.getResRole()==null){
										%>
										终端
										<%}else{ %>
										<%=cmInfo.getResRole().getRoleName() %>
										<%} %>
										</label>
									</td>
								</tr>
								<%
									if(cmInfo.getResRole()==null){
										if(request.getAttribute("pf")!=null){
											TbPFInfo pf =(TbPFInfo)request.getAttribute("pf");
								%>
								<tr>
									<td align="right">
										<label>
											充值套餐:
										</label>
									</td>
									<td>
										<label><%=pf.getPfType() %> ： <%=pf.getPfHow() %>元</label>
									</td>
								</tr>
								<%}} %>
								<%
								if(cmInfo.getResRole()!=null){
									TbAgentInfo a=null;
									TbEnterpriseInfo ep =null;
								%>
									<tr>
									<td align="right">
										<label>
											充值账户:
										</label>
									</td>
									<td>
										<label>
										<%
											if(cmInfo.getResRole().getRoleId()==2){
												a=(TbAgentInfo)request.getAttribute("a");
										 %>
										 <%=a.getAgent_Name() %>
										 <%}else if(cmInfo.getResRole().getRoleId()==3){
										 		ep =(TbEnterpriseInfo)request.getAttribute("ep");
										  %>
											<%=ep.getEp_Name() %>
										<%} %>
										</label>
									</td>
									</tr>
									<tr>
									<td align="right">
										<label>
											联系人:
										</label>
									</td>
									<td>
										<label>
										<%
											if(cmInfo.getResRole().getRoleId()==2){
										 %>
										 <%=a.getAgent_Man() %>
										 <%}else if(cmInfo.getResRole().getRoleId()==3){
										  %>
											<%=ep.getEp_Man() %>
										<%} %>
										</label>
									</td>
									</tr>
									<tr>
									<td align="right">
										<label>
											电话:
										</label>
									</td>
									<td>
										<label>
										<%
											if(cmInfo.getResRole().getRoleId()==2){
										 %>
										 <%=a.getAgent_Tel() %>
										 <%}else if(cmInfo.getResRole().getRoleId()==3){%>
											<%=ep.getEp_Tel() %>
										<%} %>
										</label>
									</td>
									</tr>
								<%}else{ %>
								<tr>
									<td align="right">
										<label>
											充值账户:
										</label>
									</td>
									<td>
										<label>
										<%=cmInfo.getResId() %>
										</label>
									</td>
								</tr>
								<%} %>
								<tr>
									<td align="right">
										<label>
											审核状态:
										</label>
									</td>
									<td>
										<label><%if(cmInfo.getProStatus()==0){ %>未审核 <%} %>
												<%if(cmInfo.getProStatus()==1){ %>通过 <%} %>
												<%if(cmInfo.getProStatus()==2){ %>不通过 <%} %>
										</label>
									</td>
								</tr>
								<%
									if(rid == 40 && cmInfo.getProStatus()==0){
								 %>
								<tr>
									<td colspan="2" align="center" class="list_footer">
										<input class=btn type="button" name="btnyes" value="通过" onclick="btncheck(<%=cmInfo.getFinId() %>,'money_check_ok')"/>
										&nbsp;
										&nbsp;
										<input class=btn type="button" name="btnno" value="不通过" onclick="btncheck(<%=cmInfo.getFinId() %>,'money_check_no')"/>
									</td>
								</tr>
								<%}else{ %>
									<tr>
									<td colspan="2" align="center" class="list_footer">
										<input class=btn type="button" name="button" value="返回"
											onclick="toback()" />
									</td>
								</tr>
								<%} %>
							</table>
						</form>
	</body>
</html>

<script language="javascript">
function btncheck(id,check){
	document.location.href="check.do?CMD="+check +"&cmid="+id;
}
function toback(){
 	history.go(-1);
 //document.location.href="check.do?CMD=";
}

function goload() 
{ 
  var role="<%=request.getAttribute("roleid")%>";
  var agent="<%=request.getAttribute("agentid")%>";
  var ep="<%=request.getAttribute("epid")%>";
  if (role!="null"){
     document.getElementById("roleid").value=role;
  }else{
     document.getElementById("roleid").value="";
  }
  if (agent!="null"){
     document.getElementById("agentid").value=agent; 
  }else{
     document.getElementById("agentid").value="0";
  }
  if (ep!="null"){
     document.getElementById("epid").value=ep; 
  }else{
     document.getElementById("epid").value="0";
  }
} 


//删除字符串左边的空格
function ltrim(str)	
{ 
	if(str.length==0)
		return(str);
	else
	{
		var idx=0;
		while(str.charAt(idx).search(/\s/)==0)
			idx++;
		return(str.substr(idx));
	}
}

//删除字符串右边的空格
function rtrim(str)	
{ 
	if(str.length==0)
		return(str);
	else
	{
		var idx=str.length-1;
		while(str.charAt(idx).search(/\s/)==0)
			idx--;
		return(str.substring(0,idx+1));
	}
}

//删除字符串左右两边的空格
function trim(str)	
{ 
	return(rtrim(ltrim(str)));
}


</script>
