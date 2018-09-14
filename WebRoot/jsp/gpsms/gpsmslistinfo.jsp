<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@ page import="com.elegps.tscweb.model.TbGpsMsListInfo"%>
<%@ page import="com.elegps.tscweb.model.TbMsInfo"%>
<%@ page import="com.elegps.tscweb.model.TbGpsInfo"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.lang.String"%>
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


		<!-- 以下是加进来的css样式表 -->
		<style type="text/css">
.btn {
	BORDER-RIGHT: #7b9ebd 1px solid;
	PADDING-RIGHT: 2px;
	BORDER-TOP: #7b9ebd 1px solid;
	PADDING-LEFT: 2px;
	FONT-SIZE: 12px;
	FILTER: progid :           DXImageTransform .           Microsoft .     
		Gradient(GradientType =           0, StartColorStr =           #ffffff,
		EndColorStr =     
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
	color: #000066;
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
		<script type='text/javascript' src='dwr/interface/ChildAgent.js'>
</script>
		<script type='text/javascript' src='dwr/engine.js'>
</script>
		<script type='text/javascript' src='dwr/util.js'>
</script>
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
		<form id="gpsmsform" name="gpsmsform" method="post">
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
										<span class="STYLE4">您现在的位置：<span class="STYLE7">GPS接入、终端关系设置</span>
										</span>
									</td>
									<td width="52%">
										<img src="images/3_03.jpg" width="518" height="35">
									</td>
								</tr>
							</table>

							<table width="100%">
								<tr>
									<td width="18%" align="left">
										<div align="right">
											总部/一级代理商:
										</div>
									</td>
									<td width="15%" align="left">
										<div align="right">
											<select name="pagentid" id="pagentid"
												style="width: 180; margin: 0; FONT-SIZE: 9pt; FONT-WEIGHT: normal;"
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
									<td width="21%" align="right">
										<div align="right">
											直属企业/二级代理商:
										</div>
									</td>
									<td width="12%" align="left">
										<div align="left">
											<select name="childagentid" id="childagentid"
												style="width: 180; margin: 0; FONT-SIZE: 9pt; FONT-WEIGHT: normal;"
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
									<td width="15%" align="right">
										<div align="right">
											企业名称:
										</div>
									</td>
									<td width="12%" align="left">
										<div align="left">
											<select name="ep" id="ep_id"
												style="width: 180; margin: 0; FONT-SIZE: 9pt; FONT-WEIGHT: normal;"
												onchange="epchang()">
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
									<td>
									</td>
								</tr>
								<tr>
									<td width="10%" align="right">
										GPS帐号:
									</td>
									<td width="150" align="left">
										<select name="gpsid" id="gps_id"
											style="width: 180; margin: 0; FONT-SIZE: 9pt; FONT-WEIGHT: normal;">
											<option value="">
												全部
											</option>
											<%
												List listgps = (List) request.getAttribute("gpsList");
												if (listgps != null) {
													for (int i = 0; i < listgps.size(); i++) {
														TbGpsInfo tbgpsinfo = new TbGpsInfo();
														tbgpsinfo = (TbGpsInfo) listgps.get(i);
											%>
											<option value="<%=tbgpsinfo.getGpsop_id()%>"><%=tbgpsinfo.getGpsop_id()%>-<%=tbgpsinfo.getGps_name()%></option>
											<%
												}
												}
											%>
										</select>
									</td>
									<td width="10%" align="right">
										终端号码:
									</td>
									<td>
										<select name="msid" id="ms_id"
											style="width: 180; margin: 0; FONT-SIZE: 9pt; FONT-WEIGHT: normal;">
											<option value="">
												全部
											</option>
											<%
												List listms = (List) request.getAttribute("msList");
												if (listms != null) {
													for (int i = 0; i < listms.size(); i++) {
														TbMsInfo tbmsinfo = new TbMsInfo();
														tbmsinfo = (TbMsInfo) listms.get(i);
											%>
											<option value="<%=tbmsinfo.getMsId()%>"><%=tbmsinfo.getMsId()%>-<%=tbmsinfo.getMsName()%></option>
											<%
												}
												}
											%>
										</select>
									</td>
									<td align="center" colspan="3">
										<p>
											<span style="cursor: hand"><img src="images/bt_cx.gif"
													width="65" height="22"
													Onclick="gpsmsearch(document.forms[0])" /> </span>
											<span style="cursor: hand"><img
													src="images/bt_add.gif" width="65" height="22"
													Onclick="gpsmsadd(document.forms[0])" /> </span>
											<span style="cursor: hand"><img
													src="images/bt_del.gif" width="65" height="22"
													Onclick="onSelect(document.forms[0])"> </span>
											<span style="cursor: hand"><img
													src="images/biao_plxj.gif" width="100" height="22"
													Onclick="gpsmspladd(document.forms[0])" /> </span>
										</p>
									</td>
								</tr>
							</table>


							<br>



							<table width="100%" height="21" border="1" bordercolor="#C1DAD7">
								<tr>
									<td width="3%" class="list_header" align="center">
										<input class=btn type="button" name="selectall" value="全选"
											onClick="this.value=check(document.forms[0].list)">
									</td>
									<td width="22%" align="center" class="list_header">
										<span class="STYLE77">GPS账号</span>
									</td>
									<td width="22%" align="center" class="list_header">
										<span class="STYLE77">GPS运营商名称</span>
									</td>
									<td width="22%" align="center" class="list_header">
										<span class="STYLE77">终端号码</span>
									</td>
									<td width="22%" align="center" class="list_header">
										<span class="STYLE77">终端名称</span>
									</td>
								</tr>

								<logic:present name="gpsmsList" scope="request">
									<%
										List list = (List) request.getAttribute("gpsmsList");
											if (list != null) {
												for (int i = 0; i < list.size(); i++) {
													Map map = new HashMap();
													map = (Map) list.get(i);
									%>
									<tr>
										<td align="center">
											<input name="list" type="checkbox"
												value="<%=map.get("gpsid")%>,<%=map.get("msid")%>">
										</td>
										<td align="center" bgcolor="#F5FAFA">
											<font><span class="STYLE77"><%=map.get("gpsid")%></span>
											</font>
										</td>
										<td align="center">
											<font><span class="STYLE77"><%=map.get("gpsname")%></span>
											</font>
										</td>
										<td align="center" bgcolor="#F5FAFA">
											<font><span class="STYLE77"><%=map.get("msid")%></span>
											</font>
										</td>
										<td align="center">
											<font><span class="STYLE77"><%=map.get("msname")%></span>
											</font>
										</td>
									</tr>
									<%
										}
											}
									%>


									<tr>
										<td align="right" colspan="6" class="list_footer">
											共有<%=request.getAttribute("gpsmsCount")%>条&nbsp;&nbsp; 第<%=request.getAttribute("currentPage")%>页&nbsp;
											共<%=request.getAttribute("pageCount")%>页&nbsp;
											<a
												href="gpsms.do?CMD=<%=request.getAttribute("CMD")%>&pagentid=<%=request.getAttribute("pagentid")%>&childagentid=<%=request.getAttribute("childagentid")%>&ep=<%=request.getAttribute("ep")%>&gpsid=<%=request.getAttribute("gpsid")%>&msid=<%=request.getAttribute("msid")%>&pageNo=1"><img
													src="images/bt_first.gif" width="42" height="16" border="0"
													align="absmiddle"> </a>
											<a
												href="gpsms.do?CMD=<%=request.getAttribute("CMD")%>&pagentid=<%=request.getAttribute("pagentid")%>&childagentid=<%=request.getAttribute("childagentid")%>&ep=<%=request.getAttribute("ep")%>&gpsid=<%=request.getAttribute("gpsid")%>&msid=<%=request.getAttribute("msid")%>&pageNo=<%=((Integer) request.getAttribute("currentPage") - 1)%>"><img
													src="images/bt_pre.gif" width="38" height="16" border="0"
													align="absmiddle"> </a>
											<a
												href="gpsms.do?CMD=<%=request.getAttribute("CMD")%>&pagentid=<%=request.getAttribute("pagentid")%>&childagentid=<%=request.getAttribute("childagentid")%>&ep=<%=request.getAttribute("ep")%>&gpsid=<%=request.getAttribute("gpsid")%>&msid=<%=request.getAttribute("msid")%>&pageNo=<%=((Integer) request.getAttribute("currentPage") + 1)%>"><img
													src="images/bt_next.gif" width="41" height="16" border="0"
													align="absmiddle"> </a>
											<a
												href="gpsms.do?CMD=<%=request.getAttribute("CMD")%>&pagentid=<%=request.getAttribute("pagentid")%>&childagentid=<%=request.getAttribute("childagentid")%>&ep=<%=request.getAttribute("ep")%>&gpsid=<%=request.getAttribute("gpsid")%>&msid=<%=request.getAttribute("msid")%>&pageNo=<%=request.getAttribute("pageCount")%>"><img
													src="images/bt_last.gif" width="42" height="16" border="0"
													align="absmiddle"> </a> 转到第
											<input type="text" size="2" name="gotopage"
												value='${requestScope.currentPage}' />
											页
											<span style="cursor: hand"><img src="images/bt_go.gif"
													width="20" height="12" border="0"
													onClick="gotoye(document.forms[0])" /> </span>
										</td>
									</tr>
								</logic:present>
							</table>
						</td>
					</tr>
				</table>
	</body>
</html>

<script language="javascript">

var checkflag = "false";
function check(field) {
	if (field == null) {
		return "全选";
	}
	if (checkflag == "false") {
		for (i = 0; i < field.length; i++) {
			field[i].checked = true;
		}
		checkflag = "true";
		return "取消";
	} else {
		for (i = 0; i < field.length; i++) {
			field[i].checked = false;
		}
		checkflag = "false";
		return "全选";
	}
}

function onSelect(form) {
	if (!dataIsAvail(form))
		return;
	if (confirm("确定删除所选的内容吗?")) {
		form.action = "gpsms.do?CMD=delete";
		form.submit();
	}
	return;
}

function dataIsAvail(form) {
	var chkbox = form.list;
	var isChecked = false;
	if (chkbox == null) {
		alert("未选中内容，不能删除！");
		return false;
	} else {
		var chkLength = chkbox.length;

		if (chkLength == null) {
			if (chkbox.checked)
				isChecked = true;
		} else {
			for (i = 0; i < chkLength; i++) {
				if (chkbox[i].checked) {
					isChecked = true;
					break;
				}
			}
		}
		if (isChecked == false) {
			alert("未选中内容，不能删除！");
			return false;
		} else
			return true;
	}
}

function gpsmsadd(form) {
	form.action = "gpsms.do?CMD=addtojsp";
	form.submit();
	//document.location.href="gpsms.do?CMD=addtojsp"
}

//根据GPS号批量添加终端
function gpsmspladd(form) {
	form.action = "gpsms.do?CMD=pladdtom_tojsp";
	form.submit();
	//document.location.href="gpsms.do?CMD=pladdtom_tojsp"
}
function gotoye(form) {

	var page = this.gpsmsform.gotopage.value;

	if (!isNumber(page)) {
		alert("输入跳转页数非法,请重新输入.(只能由数字组成)");
		return false;
	}
document.location.href="gpsms.do?CMD=<%=request.getAttribute("CMD")%>&pagentid=<%=request.getAttribute("pagentid")%>&childagentid=<%=request.getAttribute("childagentid")%>&ep=<%=request.getAttribute("ep")%>&gpsid=<%=request.getAttribute("gpsid")%>&msid=<%=request.getAttribute("msid")%>&pageNo="+page;
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

function gpsmsearch(form){
    form.action="gpsms.do?CMD=gpsms_search";
	form.submit();
}

<!-- 装入上次查询的装态 ->
function goload() 
{ 

  var ep="<%=request.getAttribute("ep")%>";
  var pagentid=parseInt(<%=request.getAttribute("pagentid")%>);
  var childagentid=parseInt(<%=request.getAttribute("childagentid")%>);
  var grpid="<%=request.getAttribute("grpid")%>";
  var msid="<%=request.getAttribute("msid")%>";
  var gpsid="<%=request.getAttribute("gpsid")%>";
   document.getElementById("pagentid").value=pagentid;
   document.getElementById("childagentid").value=childagentid;
   document.getElementById("ep_id").value=ep;
   document.getElementById("ms_id").value=msid;
   document.getElementById("gps_id").value=gpsid;
  var mes="<%=request.getAttribute("message")%>";
  if(mes!="null"){
    alert(mes);
    document.location.href="gpsms.do?CMD=gpsms_search&pagentid=<%=request.getAttribute("pagentid")%>&childagentid=<%=request.getAttribute("childagentid")%>&ep=<%=request.getAttribute("ep")%>&gpsid=<%=request.getAttribute("gpsid")%>&msid=<%=request.getAttribute("msid")%>";
  }  
} 


function parentagentchang(){
  var childagentselect= document.getElementById("childagentid");
  gpsmsform.childagentid.options.length=2; 
  var parentagent=gpsmsform.pagentid.value;
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
       getEpinfo();
}

		var a_id=<%=Integer.parseInt(request.getSession().getAttribute("agentId").toString())%>;
        var r_id=<%=Integer.parseInt(request.getSession().getAttribute("roleId").toString())%>;
        var ep_id=<%=Integer.parseInt(request.getSession().getAttribute("epId").toString())%>;
function getEpinfo(){
        var epselect= document.getElementById("ep_id");
        var pagent=gpsmsform.pagentid.value;
        var cagent=gpsmsform.childagentid.value;
        gpsmsform.ep.options.length=1;
        gpsmsform.ms_id.options.length=1;
        if(r_id != 3 && r_id != 4){
        ChildAgent.getEpinfoByAId(pagent,cagent,a_id,r_id,function(data){
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
        }else{
        	ChildAgent.getEpinfoByEId(pagent,cagent,ep_id,r_id,function(data){
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
}

function epchang(){
 getMsinfo();
}

function getMsinfo(){
  var msselect =document.getElementById("ms_id");
  var pagentid=gpsmsform.pagentid.value;
  var childagentid=gpsmsform.childagentid.value;
  var ep=gpsmsform.ep.value;
  msselect.options.length=1;
  ChildAgent.getMsinfo(pagentid,childagentid,ep,function(data){
   if(data!=null){
      for(var i=0;i<data.length;i++){
             for(var j=0;j<data[i].length;j++){
              var option = document.createElement("OPTION");
               option.value=data[i][j];
 	           option.text=data[i][++j];
               msselect.add(option);
              }
           }
   }
  });
}
</script>
