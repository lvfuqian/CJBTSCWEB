<%@ page language="java" pageEncoding="UTF-8"%>
<%@page import="com.elegps.tscweb.model.TbAgentInfo"%>
<%@page import="com.elegps.tscweb.model.TbEnterpriseInfo"%>

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
		<title>企业余额充值</title>
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
									<span class="STYLE4">您现在的位置：<span class="STYLE7">企业余额充值</span>
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
									TbEnterpriseInfo epInfo =(TbEnterpriseInfo)request.getAttribute("epInfo");
									
									String m =epInfo.getEp_Money();
									  if(m==null || m==""){
									  	m="0.00";
									  	epInfo.setEp_Money(m);
									  }
								 %>
						<form id="addmoneyform" name="addmoneyform"
							method="post" >
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
											企业名称:
										</label>
									</td>
									<td>
										<label><%=epInfo.getEp_Name() %></label>
									</td>
								</tr>
								<tr>
									<td align="right">
										<label>
											联系人:
										</label>
									</td>
									<td>
										<label><%=epInfo.getEp_Man() %></label>
									</td>
								</tr>
								<tr>
									<td align="right">
										<label>
											电话:
										</label>
									</td>
									<td>
										<label><%=epInfo.getEp_Tel() %></label>
									</td>
								</tr>
								<tr>
									<td align="right">
										<label>
											企业余额:
										</label>
									</td>
									<td>
										<label><%=epInfo.getEp_Money() %></label>
									</td>
								</tr>
								<tr>
									<td align="right">
										<label>
											充值金额:
										</label>
									</td>
									<td>
										<input name="moneyhow" type="text" class="checkbox"
										onkeyup="return mykey()" 
										onpaste="return false" 
										oncopy="return false"
										oncut="return false" 
											size="30" value=''/>
										<font color="red">&nbsp;<B>*</B>
										</font>
									</td>

								</tr>
								
								<%
								if(rid == 1 || rid == 35 || rid ==36){
								 %>
								<tr>
									<td align="right">
										充值申请描述:
									</td>
									<td>
										<textarea name="moneydiscription" rows="8" cols="50" class="checkbox" style="overflow-y:hidden"></textarea>
									</td>

								</tr>
								<tr>
									<td colspan="2" align="center" class="list_footer">
										<input class=btn type="button" name="btn1" value="提交审核"   onclick="return formcheck(<%=epInfo.getEp_Id() %>,<%=rid %>)"/>
										&nbsp;
										&nbsp;
										<input class=btn type="reset" name="reset" value="重置" />
									</td>
								</tr>
								<%}else if(rid == 2){ %>
								
								<tr>
									<td colspan="2" align="center" class="list_footer">
										<input class=btn type="button" name="btn2" value="充值"  onclick="return formcheck(<%=epInfo.getEp_Id() %>,<%=rid %>)"/>
										&nbsp;
										&nbsp;
										<input class=btn type="reset" name="reset" value="重置" />
									</td>
								</tr>
								<%} %>
							</table>
						</form>
	</body>
</html>

<script language="javascript">

function mykey()   {     //如果输入不是0-9就无效     
	if (isNaN(addmoneyform.moneyhow.value)) {
		alert("请输入数字！"); 
		addmoneyform.moneyhow.value="";
	}  
}

function formcheck(epid,rid){
 	if(addmoneyform.moneyhow.value==""){ 
      alert("充值金额为空");
      addmoneyform.moneyhow.focus();
      return false;
   }
   if(rid==1  || rid==35 || rid==36){ 
       document.addmoneyform.action="money.do?CMD=money_infoczep&epid="+epid;
		document.addmoneyform.submit();	
		//return true;
   }else if(rid==2){
   		document.addmoneyform.action="money.do?CMD=money_infocz&epid="+epid;
		document.addmoneyform.submit();
		//return true;
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
