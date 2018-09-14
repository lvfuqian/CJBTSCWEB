<%@ page language="java" pageEncoding="UTF-8"%>
<%@page import="com.elegps.tscweb.model.TbAgentInfo"%>
<%@page import="com.elegps.tscweb.model.TbEnterpriseInfo"%>
<%@page import="com.elegps.tscweb.model.TbMsInfo"%>
<%@page import="com.elegps.tscweb.model.TbPFInfo"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@ page import="com.elegps.tscweb.model.TbUserInfo"%>
<%@ page import="java.util.List"%>
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
		<title>终端套餐充值</title>
	</head>

	<body onload="goload()">
		<td background="images/gw_06.gif">&nbsp;&nbsp; 
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
									<span class="STYLE4">您现在的位置：<span class="STYLE7">终端套餐充值</span>
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
		int rid = Integer.parseInt(request.getSession().getAttribute("roleId").toString());
		if(rid == 2 || rid ==4){
		 %>
		<span><strong>您当前余额：<%=request.getAttribute("money") %>&nbsp;元</strong></span>
		<br><br>   
		<%} %> 
						<%
									TbMsInfo msInfo = new TbMsInfo();
									msInfo = (TbMsInfo)request.getAttribute("msInfo");
									
							//		List<TbMsInfo> msListInfo =//(List<TbMsInfo>)request.getAttribute("msListInfo");
							//		String[] msidlist=null;
							//		if(msListInfo!=null){
							//			for(int i; i< msListInfo.size();i++){
							//				msidlist[i] = msListInfo.get(i).getMsId();
							//			}
							//		}
								 %>
						<form name="addmoneyform" onSubmit="return formcheck();"
							method="post" action="money.do?CMD=ms_money_cz&msid=<%=msInfo.getMsId() %>">
							<%
								if(msInfo != null){
							 %>
							<table align="center" width="450" border="1"
								bordercolor="#C1DAD7">
								<tr>
									<td colspan="2" align="center" class="list_header">
										<span class="STYLE77">充值</span>
									</td>
								</tr>
								
								<tr>
									<td align="right">
										<label>
											终端ID:
										</label>
									</td>
									<td>
										<label><%=msInfo.getMsId()%></label>
									</td>
								</tr>
								<tr>
									<td align="right">
										<label>
											终端名称:
										</label>
									</td>
									<td>
										<label><%=msInfo.getMsName() %></label>
									</td>
								</tr>
								<tr>
									<td align="right">
										<label>
											当前套餐:
										</label>
									</td>
									<td>
										<label><%=msInfo.getPf().getPfType() %></label>
									</td>
								</tr>
								<tr>
									<td align="right">
										<label>
											到期日期:
										</label>
									</td>
									<td>
										<label><%=msInfo.getMsMoneyTime() %></label>
									</td>
								</tr>
								<tr>
									<td align="right">
										<label>
											充值套餐:
										</label>
									</td>
									<td>
										<select name="pfType" id="pfType"
											style="width: 180; margin: 0; FONT-SIZE: 9pt; FONT-WEIGHT: normal;">
											<option value="-1" selected="selected">请选择套餐</option>
											<%
												List<TbPFInfo> pfList = (List<TbPFInfo>)request.getAttribute("pfList");
												if(pfList!= null){
													for (int i= 0; i < pfList.size(); i++) {
														TbPFInfo pf = new TbPFInfo();
														pf = pfList.get(i);
														
											%>
													<option value='<%=pf.getPfId() %>'><%=pf.getPfType() %></option>	
											<%
													}
												}
											 %>
											

										</select>
									</td>
								</tr>
								<tr>
									<td colspan="2" align="center" class="list_footer">
										<input class=btn type="Submit" name="Submit" value="充值" />
										&nbsp;
										&nbsp;
										<input class=btn type="reset" name="reset" value="重置" />
									</td>
								</tr>
							</table>

								<%
								}
							 %>
						</form>
	</body>
</html>

<script language="javascript">

function formcheck(){
	var pftype =document.getElementById("pfType").value
	if(pftype == "-1"){
		alert("请选择套餐进行充值！");
		return false;
	}
	
	return true;
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
