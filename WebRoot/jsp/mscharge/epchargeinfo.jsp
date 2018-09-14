<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@ page import="com.elegps.tscweb.model.TbMsInfo"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.lang.String"%>
<%@ page import="java.net.URLEncoder"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="com.elegps.tscweb.model.TbAgentInfo"%>
<%@ page import="com.elegps.tscweb.model.TbEnterpriseInfo"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
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

	font-size: 12px;
}

.STYLE17 {
	color: #993300;
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
		<link href="css/manage.css" rel="stylesheet" type="text/css" />
		<link href="css/list.css" rel="stylesheet" type="text/css" />
		<style type="text/css">
<!--
.STYLE9 {
	color: #000000;
	font-weight: bold;
	text-indent: 10px;
}
-->
</style>
		<base href="<%=basePath%>">
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

	</head>
	<body onload="goload()">
		<form id="epchargeinfo" name="epchargeinfo" method="post">
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
										<span class="STYLE4">您现在的位置：<span class="STYLE7">企业充值历史记录信息</span>
										</span>
									</td>
									<td width="52%">
										<img src="images/3_03.jpg" width="518" height="35">
									</td>
								</tr>
							</table>
				<table width="100%" height="73" border="0">
								<tr>
									<td width="77" align="right">
										<div align="right">
											代理商名称:										</div>									</td>
									<td width="133" align="left">
										<div align="left">
											<select name="agent" id="agent_id"
												style="width: 200; margin: 0; FONT-SIZE: 9pt; FONT-WEIGHT: normal;"
												onchange="agentchang(document.forms[0])">
												<option value="">
													全部代理商												</option>
												<%
													List listagent = (List) request.getAttribute("agentList");
													if (listagent != null) {
														for (int i = 0; i < listagent.size(); i++) {
															TbAgentInfo agentinfo = new TbAgentInfo();
															agentinfo = (TbAgentInfo) listagent.get(i);
												%>
												<option value="<%=agentinfo.getAgent_Id()%>"><%=agentinfo.getAgent_Name()%></option>
												<%
													}
													}
												%>
											</select>
										</div>									</td>
									<td width="60" align="right">
										<div align="right">
											企业名称:
											</div>	</td>
									<td width="139" align="left">
										<div align="left">
											<select name="ep" id="ep_id"
												style="width: 200; margin: 0; FONT-SIZE: 9pt; FONT-WEIGHT: normal;">
												<option value="">
													全部企业												</option>
												<%
													List listep = (List) request.getAttribute("epList");
													if (listep != null) {
														for (int i = 0; i < listep.size(); i++) {
															TbEnterpriseInfo epinfo = new TbEnterpriseInfo();
															epinfo = (TbEnterpriseInfo) listep.get(i);
												%>
												<option value="<%=epinfo.getEp_Id()%>"><%=epinfo.getEp_Name()%></option>
												<%
													}
													}
												%>
											</select>
										</div></td>
										<td>
									     <div align="right">
											<span style="cursor: hand"><img src="images/bt_cx.gif" alt="查询"
													width="65" height="22"
													onclick="epchargesearch(document.forms[0])" /></span>
										    </div>
										    </td>
									</tr>
							</table>
							<br>

							<table width="100%" height="21" border="1" bordercolor="#C1DAD7">
								<tr height="12">
									<td width="5%" class="list_header" height="12">
										<span class="STYLE77">扣费机构类型</span>
									</td>
									<td width="15%" class="list_header" height="12">
										<span class="STYLE77">扣费机构名</span>
									</td>
									<td width="15%" class="list_header" height="12">
										<span class="STYLE77">充值企业</span>
									</td>
									<td width="7%" class="list_header" height="12" align="center">
										<span class="STYLE77">充值金额</span>
									</td>
									<td width="7%" class="list_header" height="12" align="center">
										<span class="STYLE77">充值日期</span>
									</td>
								</tr>

								<logic:present name="epchargeList" scope="request">								
									<%
										String orgtype = "代理商";
										SimpleDateFormat dateFormat = new SimpleDateFormat(
												"yyyy-MM-dd HH:mm:ss");
										List list = (List) request.getAttribute("epchargeList");
										if (list != null) {
											for (int i = 0; i < list.size(); i++) {
												Map map = new HashMap();
   			                                    map = (Map) list.get(i);
   			                                    if(map.get("orgtype").toString().equals("1")){
   			                                      orgtype ="代理商";
   			                                    }else{
   			                                      orgtype="企业";
   			                                    }
									%>
									<tr>
										
										<td class="STYLE8" align="center">
											<font><span class="STYLE77"> 
											 <%=orgtype%> </span> </font>
										</td>
										<td class="STYLE8" align="center">
										<font><span class="STYLE77"> 
 										   <%=map.get("orgname")%> </span> </font>
										</td>
										<td class="STYLE8" align="center">
										<font><span class="STYLE77"> 
 										   <%=map.get("epname")%> </span> </font>
										</td>
										<td class="STYLE8" align="center">
										<font><span class="STYLE77">
 									     <%=map.get("epcash")%> </span> </font>
										</td>
										<td class="STYLE8" align="center">
										<font><span class="STYLE77">
 									     <% 
 									     if (map.get("chargedate")== null) {
											out.print("");
					 					 } else {
						  				    out.print(dateFormat.format(map.get("chargedate")));
					 					 }
										 %>  </span> </font>
										</td>
										
									</tr>
									<%
										}
										}
									%>
									<tr>
										<td align="right" colspan="10" class="list_footer">
											共有<%=request.getAttribute("epchargeCount")%>条&nbsp;&nbsp; 第<%=request.getAttribute("currentPage")%>页&nbsp;
											共<%=request.getAttribute("pageCount")%>页&nbsp;
											<a
												href="mscharge.do?CMD=<%=request.getAttribute("CMD")%>&agent=<%=request.getAttribute("agent")%>&ep=<%=request.getAttribute("ep")%>&pageNo=1"><img
													src="images/bt_first.gif" width="42" height="16" border="0"
													align="absmiddle">
											</a>
											<a
												href="mscharge.do?CMD=<%=request.getAttribute("CMD")%>&agent=<%=request.getAttribute("agent")%>&ep=<%=request.getAttribute("ep")%>&pageNo=<%=((Integer) request.getAttribute("currentPage") - 1)%>"><img
													src="images/bt_pre.gif" width="38" height="16" border="0"
													align="absmiddle">
											</a>
											<a
												href="mscharge.do?CMD=<%=request.getAttribute("CMD")%>&agent=<%=request.getAttribute("agent")%>&ep=<%=request.getAttribute("ep")%>&pageNo=<%=((Integer) request.getAttribute("currentPage") + 1)%>"><img
													src="images/bt_next.gif" width="41" height="16" border="0"
													align="absmiddle">
											</a>
											<a
												href="mscharge.do?CMD=<%=request.getAttribute("CMD")%>&agent=<%=request.getAttribute("agent")%>&ep=<%=request.getAttribute("ep")%>&pageNo=<%=request.getAttribute("pageCount")%>"><img
													src="images/bt_last.gif" width="42" height="16" border="0"
													align="absmiddle">
											</a> 转到第
											<input type="text" size="2" name="gotopage"
												value='${requestScope.currentPage}' />
											页
											<span style="cursor: hand"><img src="images/bt_go.gif"
													width="20" height="12" border="0"
													onClick="gotoye(document.forms[0])" />
											</span>
										</td>
									</tr>
								</logic:present>
							</table>
						</td>
					</tr>
				</table>
			</td>
		</form>
	</body>
</html>

<script language="javascript">


<!--模糊查询按键  -->
function epchargesearch(form){  
    form.action="mscharge.do?CMD=epcharge_search";
	form.submit();
}

<!--改变代理商时-->
function agentchang(form){
  form.action="mscharge.do?CMD=epagentchange";
  form.submit();
}


	
function gotoye(form){
 	 var page=this.epchargeinfo.gotopage.value;
	 if(!isNumber(page)){
     alert("输入跳转页数非法,请重新输入.(只能由数字组成)");
     return false;
     }
    form.action="mscharge.do?CMD=<%=request.getAttribute("CMD")%>&agent=<%=request.getAttribute("agent")%>&ep=<%=request.getAttribute("ep")%>&pageNo="+page;
	form.submit();   
}



<!-- 装入上次查询用户类型查询、在线状态查询、用户有效状态查询的装态 ->
function goload() 
{ 
  var agent="<%=request.getAttribute("agent")%>";
  var ep="<%=request.getAttribute("ep")%>";
   document.getElementById("agent_id").value=agent;
   document.getElementById("ep_id").value=ep; 
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
