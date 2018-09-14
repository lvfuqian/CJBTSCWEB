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
        <script type='text/javascript' src='dwr/interface/ChildAgent.js'></script>
        <script type='text/javascript' src='dwr/engine.js'></script>
        <script type='text/javascript' src='dwr/util.js'></script>
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
		<form id="mschargeform" name="mschargeform" method="post">
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
										<span class="STYLE4">您现在的位置：<span class="STYLE7">终端充值信息</span>
										</span>
									</td>
									<td width="52%">
										<img src="images/3_03.jpg" width="518" height="35">
									</td>
								</tr>
							</table>
				<table width="100%" height="73" border="0">
								<tr>
									<td width="10%" align="right">
										<div align="right">
											总部/一级代理商:
										</div>
									</td>
									<td width="12%" align="left">
										<div align="left">
											<select name="pagentid" id="pagentid"
												style="width: 150; margin: 0; FONT-SIZE: 9pt; FONT-WEIGHT: normal;"
												onchange="parentagentchang()">
												<%
													List parentagentlist = (List) request.getAttribute("Pagentlist");
													if (parentagentlist != null) {
														for (int i = 0; i < parentagentlist.size(); i++) {
															TbAgentInfo agentinfo = new TbAgentInfo();
															agentinfo = (TbAgentInfo) parentagentlist.get(i);
												%>
												<option value="<%=agentinfo.getAgent_Id()%>"><%=agentinfo.getAgent_Name()%></option>
												<%
													}
													}
												%>
											</select>
										</div>
									</td>
									<td width="10%" align="right">
										<div align="right">
											直属企业/二级代理商:
										</div>
									</td>
									<td width="12%" align="left">
										<div align="left">
											<select name="childagentid" id="childagentid"
												style="width: 150; margin: 0; FONT-SIZE: 9pt; FONT-WEIGHT: normal;"
												onchange="childagentchang()">
												<option value="-2">
													所有企业
												</option>
												<option value="-1">
													直属企业
												</option>

												<%
													List childagentlistid = (List) request.getAttribute("Cagentlist");
													if (childagentlistid != null) {
														for (int i = 0; i < childagentlistid.size(); i++) {
															TbAgentInfo agentinfo = new TbAgentInfo();
															agentinfo = (TbAgentInfo) childagentlistid.get(i);
												%>
												<option value="<%=agentinfo.getAgent_Id()%>"><%=agentinfo.getAgent_Name()%></option>
												<%
													}
													}
												%>
											</select>
										</div>
									</td>
									<td width="10%" align="right">
										<div align="right">
											企业名称:
										</div>
									</td>
									<td width="12%" align="left">
										<div align="left">
											<select name="ep" id="ep_id"
												style="width: 150; margin: 0; FONT-SIZE: 9pt; FONT-WEIGHT: normal;">
												<option value="-1">
													全部企业
												</option>
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
										</div>
									</td>
								</tr>
									<tr>
									<td align="right">
										<div align="right">
											号码:</div></td>
									<td align="left">
										<div align="left">
											<input name="msid" type="text" style="width: 200px;"
												value='${requestScope.ms_id}' size="21" maxlength="21" />
										</div></td>
									<td align="right">
										<div align="right">
											终端别名:	</div></td>
									<td align="left">
										<div align="left">
											<input style="width: 200px;" type="text" name="msname"
												value='${requestScope.ms_name}' size="21" maxlength="60" />
										</div>									</td>
									    <td colspan="2">
									     <div align="right">
											<span style="cursor: hand"><img src="images/bt_cx.gif" alt="查询"
													width="65" height="22"
													onclick="mschargesearch(document.forms[0])" /></span>
											&nbsp;&nbsp;
											<span style="cursor: hand"><img src="images/biao_back.gif" alt="充值"
													width="65" height="22"
													onclick="mscharge(document.forms[0])" /></span>
										    </div>
							        </td>
								</tr>
							</table>
							<br>

							<table width="100%" height="21" border="1" bordercolor="#C1DAD7">
								<tr height="12">
									<td width="15%" class="list_header" height="12">
										<span class="STYLE77">终端号码</span>
									</td>
									<td width="12%" class="list_header" height="12">
										<span class="STYLE77">终端别名</span>
									</td>
									<td width="9%" class="list_header" height="12">
										<span class="STYLE77">终端类型</span>
									</td>
									<td width="7%" class="list_header" height="12" align="center">
										<span class="STYLE77">在线状态</span>
									</td>
									<td width="7%" class="list_header" height="12" align="center">
										<span class="STYLE77">用户状态</span>
									</td>
									<td width="7%" class="list_header" height="12" align="center">
										<span class="STYLE77">资费状态</span>
									</td>
									<td width="7%" class="list_header" height="12" align="center">
										<span class="STYLE77">月套餐费(元)</span>
									</td>
									<td width="7%" class="list_header" height="12" align="center">
										<span class="STYLE77">终端余额(元)</span>
									</td>
									<td width="7%" class="list_header" height="12" align="center">
										<span class="STYLE77">扣费日期</span>
									</td>
								</tr>

								<logic:present name="msList" scope="request">
								
									<%
										String mstype = null;
										String OnlineStatus = null;
										String flagstatus = null;
										String is_Arrearage=null;
										
										List list = (List) request.getAttribute("msList");
										if (list != null) {
											for (int i = 0; i < list.size(); i++) {
												TbMsInfo tbmsinfo = new TbMsInfo();
												tbmsinfo = (TbMsInfo) list.get(i);
									%>
									<tr>
										<td bgcolor="#F5FAFA" class="STYLE8" align="center">
											<font><span
													class="STYLE77"><%=tbmsinfo.getMsId()%></span>
											</font>
										</td>
										<td class="STYLE8" align="center">
											<font><span class="STYLE77"><%=tbmsinfo.getMsName()%></span>
											</font>
										</td>
										<td bgcolor="#F5FAFA" class="STYLE8" align="center">
											<font><span class="STYLE77"> <%
 											if (tbmsinfo.getMsType() == 0) {
 												mstype = "终端用户";
 											} else if (tbmsinfo.getMsType() == 1) {
 												mstype = "群组管理";
 											} else {
 												mstype = "调度用户";
 											}
 											%> <%=mstype%> </span> </font>
										</td>
										<td class="STYLE8" align="center">
											<font><span class="STYLE77"> <%
 										if (tbmsinfo.getOnlineStatus() == 0) {
 											OnlineStatus = "离线";
 										} else if (tbmsinfo.getOnlineStatus() == 1) {
 											OnlineStatus = "在线";
 										} else {
										 	OnlineStatus = "关机";
										}
										%> <%=OnlineStatus%> </span> </font>
										</td>
										
										<td class="STYLE8" align="center">
											<font><span class="STYLE77"> <%
										 		if (tbmsinfo.getFlag() == 0) {
										 		flagstatus = "冻结";
										 			} else {
										 		flagstatus = "正常";
										 			}
										       %> <%=flagstatus%> </span> </font>
										</td>
										<td class="STYLE8" align="center">
										<font><span class="STYLE77"> <%
 										if (tbmsinfo.getIs_Arrearage()== 0) {
 											is_Arrearage ="欠费";
 										}else{ 
										 	is_Arrearage ="不欠费";
										}
										%> <%=is_Arrearage%> </span> </font>
										</td>
										<td class="STYLE8" align="center">
										<font><span class="STYLE77">
 									     <%=tbmsinfo.getPackage_fee()%> </span> </font>
										</td>
										<td class="STYLE8" align="center">
										<font><span class="STYLE77">
 									     <%=tbmsinfo.getBalance_cash()%> </span> </font>
										</td>
										<td class="STYLE8" align="center">
										<font><span class="STYLE77">
 									     <%if (tbmsinfo.getCharge_date()==null) {
											 		out.print("");
											 			} else {
											 		out.print(tbmsinfo.getCharge_date());
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
											共有<%=request.getAttribute("msCount")%>条&nbsp;&nbsp; 第<%=request.getAttribute("currentPage")%>页&nbsp;
											共<%=request.getAttribute("pageCount")%>页&nbsp;
											<a
												href="mscharge.do?CMD=<%=request.getAttribute("CMD")%>&msid=<%=request.getAttribute("ms_id")%>&msname=<%=URLEncoder.encode(request.getAttribute("ms_name")
							.toString(), "UTF-8")%>&pagentid=<%=request.getAttribute("pagentid")%>&childagentid=<%=request.getAttribute("childagentid")%>&ep=<%=request.getAttribute("ep")%>&pageNo=1"><img
													src="images/bt_first.gif" width="42" height="16" border="0"
													align="absmiddle">
											</a>
											<a
												href="mscharge.do?CMD=<%=request.getAttribute("CMD")%>&msid=<%=request.getAttribute("ms_id")%>&msname=<%=URLEncoder.encode(request.getAttribute("ms_name")
							.toString(), "UTF-8")%>&pagentid=<%=request.getAttribute("pagentid")%>&childagentid=<%=request.getAttribute("childagentid")%>&ep=<%=request.getAttribute("ep")%>&pageNo=<%=((Integer) request.getAttribute("currentPage") - 1)%>"><img
													src="images/bt_pre.gif" width="38" height="16" border="0"
													align="absmiddle">
											</a>
											<a
												href="mscharge.do?CMD=<%=request.getAttribute("CMD")%>&msid=<%=request.getAttribute("ms_id")%>&msname=<%=URLEncoder.encode(request.getAttribute("ms_name")
							.toString(), "UTF-8")%>&pagentid=<%=request.getAttribute("pagentid")%>&childagentid=<%=request.getAttribute("childagentid")%>&ep=<%=request.getAttribute("ep")%>&pageNo=<%=((Integer) request.getAttribute("currentPage") + 1)%>"><img
													src="images/bt_next.gif" width="41" height="16" border="0"
													align="absmiddle">
											</a>
											<a
												href="mscharge.do?CMD=<%=request.getAttribute("CMD")%>&msid=<%=request.getAttribute("ms_id")%>&msname=<%=URLEncoder.encode(request.getAttribute("ms_name")
							.toString(), "UTF-8")%>&pagentid=<%=request.getAttribute("pagentid")%>&childagentid=<%=request.getAttribute("childagentid")%>&ep=<%=request.getAttribute("ep")%>&pageNo=<%=request.getAttribute("pageCount")%>"><img
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
function mschargesearch(form){  
    form.action="mscharge.do?CMD=mscharge_search";
	form.submit();
}
<!--充值 -->
function mscharge(){
document.location.href="mscharge.do?CMD=mschargeaddjsp";
}

	
function gotoye(form){
 	 var page=this.mschargeform.gotopage.value;
	 if(!isNumber(page)){
     alert("输入跳转页数非法,请重新输入.(只能由数字组成)");
     return false;
     }
    form.action="mscharge.do?CMD=<%=request.getAttribute("CMD")%>&msid=<%=request.getAttribute("ms_id")%>&msname=<%=URLEncoder.encode(request.getAttribute("ms_name").toString(), "UTF-8")%>&pagentid=<%=request.getAttribute("pagentid")%>&childagentid=<%=request.getAttribute("childagentid")%>&ep=<%=request.getAttribute("ep")%>&pageNo="+page;
	form.submit();   
}



<!-- 装入上次查询用户类型查询、在线状态查询、用户有效状态查询的装态 ->
function goload() 
{ 
  var ep="<%=request.getAttribute("ep")%>";
  var pagentid=parseInt(<%=request.getAttribute("pagentid")%>);
  var childagentid=parseInt(<%=request.getAttribute("childagentid")%>);
   document.getElementById("pagentid").value=pagentid;
   document.getElementById("childagentid").value=childagentid;
   document.getElementById("ep_id").value=ep;
  var mes="<%=request.getAttribute("message")%>";
  if(mes!="null"){
    alert(mes);
    document.location.href="mscharge.do?CMD=mscharge_search&msid=<%=request.getAttribute("ms_id")%>&msname=<%=URLEncoder.encode(request.getAttribute("ms_name").toString(), "UTF-8")%>&pagentid=<%=request.getAttribute("pagentid")%>&childagentid=<%=request.getAttribute("childagentid")%>&ep=<%=request.getAttribute("ep")%>";
  }   
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

function parentagentchang(){
  var childagentselect= document.getElementById("childagentid");
  mschargeform.childagentid.options.length=2; 
  var parentagent=mschargeform.pagentid.value;
  if(parentagent!=null){
        ChildAgent.getchiledAgent(parentagent,function(data){
         if(data!=null){
          for(var i=0;i<data.length;i++){
           for(var j=0;j<data[i].length;j++){
              var option = document.createElement("OPTION");
               option.value=data[i][j];
 	           option.text=data[i][++j];
               childagentselect.add(option);
           }
          } 
         } 
        }); 
	}	
	getEpinfo();
	 
}

function childagentchang(){
        var epselect= document.getElementById("ep_id");
        var pagent=mschargeform.pagentid.value;
        var cagent=mschargeform.childagentid.value;
        mschargeform.ep.options.length=1;
        ChildAgent.getEpinfo(pagent,cagent,function(data){
           if(data!=null){
            for(var i=0;i<data.length;i++){
             for(var j=0;j<data[i].length;j++){
              var option = document.createElement("OPTION");
               option.value=data[i][j];
 	           option.text=data[i][++j];
               epselect.add(option);
              }
             }
          }  
        });
}


function getEpinfo(){
        var epselect= document.getElementById("ep_id");
        var pagent=mschargeform.pagentid.value;
        var cagent=mschargeform.childagentid.value;
        mschargeform.ep.options.length=1;
        ChildAgent.getEpinfo(pagent,cagent,function(data){
           if(data!=null){
            for(var i=0;i<data.length;i++){
             for(var j=0;j<data[i].length;j++){
              var option = document.createElement("OPTION");
               option.value=data[i][j];
 	           option.text=data[i][++j];
               epselect.add(option);
              }
             }
          }  
        });  	 
}
</script>
