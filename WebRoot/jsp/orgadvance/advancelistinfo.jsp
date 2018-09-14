<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.lang.String"%>
<%@ page import="java.net.URLEncoder"%>
<%@ page import="com.elegps.tscweb.model.TbAgentInfo"%>
<%@ page import="com.elegps.tscweb.model.TbEnterpriseInfo"%>
<%@ page import="java.text.SimpleDateFormat"%>
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
		<form id="advanceform" name="advanceform" method="post">
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
										<span class="STYLE4">您现在的位置：<span class="STYLE7">预充值管理</span>
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
									<td width="10%" align="right">
										<div align="right">
											验证结果:
										</div>
									</td>
									<td width="60" align="left">
										<div align="left">
											<select name="aresult" id="vresult"
												style="width: 60; margin: 0; FONT-SIZE: 9pt; FONT-WEIGHT: normal;">
												<option value="">
													全部
												</option>
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
									<td align="center" colspan="4">
										<div >
											<span style="cursor: hand"><img src="images/bt_cx.gif"
													alt="查询" width="65" height="22"
													onclick="advanceserach(document.forms[0])" />
											</span>&nbsp;&nbsp;
											<span style="cursor: hand"><img
													src="images/bt_add.gif" alt="新建" width="65" height="22"
													onclick="advanceadd(document.forms[0])" />
											</span>&nbsp;&nbsp;&nbsp;
											<span style="cursor: hand"><img
													src="images/bt_del.gif" alt="删除" width="65" height="22"
													onclick="onSelect(document.forms[0])" />
											</span>&nbsp;&nbsp;&nbsp;
										</div>
									</td>
								</tr>
							</table>
							<br>



							<table width="100%" height="21" border="1" bordercolor="#C1DAD7">
								<tr>
									<td width="5%" align="center" class="list_header">
										<input class=btn type="button" name="selectall" value="全选"
											onClick="this.value=check(document.forms[0].list)">
									</td>
									<td width="20%" align="center" class="list_header">
										<span class="STYLE77">预充机构名</span>
									</td>
									<td width="10%" align="center" class="list_header">
										<span class="STYLE77">预充金额</span>
									</td>
									<td width="10%" align="center" class="list_header">
										<span class="STYLE77">预充填写人</span>
									</td>
									<td width="15%" align="center" class="list_header">
										<span class="STYLE77">预充时间</span>
									</td>
									<td width="10%" align="center" class="list_header">
										<span class="STYLE77">验证人</span>
									</td>
									<td width="10%" align="center" class="list_header">
										<span class="STYLE77">验证结果</span>
									</td>
									<td width="10%" align="center" class="list_header">
										<span class="STYLE77">备注</span>
									</td>
									<td width="5%" align="center" class="list_header">
										<span class="STYLE77">操作</span>
									</td>
								</tr>
								<logic:present name="advanceList" scope="request">
									<%
										List list = (List) request.getAttribute("advanceList");
											SimpleDateFormat dateFormat = new SimpleDateFormat(
													"yyyy-MM-dd HH:mm:ss");
											String advancetype = "";
											String validateperson = "";
											String validatedate = "";
											String validateresult = "";
											String remark = "";
											if (list != null) {
												for (int i = 0; i < list.size(); i++) {
													Map map = new HashMap();
													map = (Map) list.get(i);
													if (map.get("advancetype").toString().equals("1")) {
														advancetype = "代理商";
													} else {
														advancetype = "企业";
													}

													if (map.get("validateperson") == null) {
														validateperson = "";
													} else {
														validateperson = map.get("validateperson")
																.toString();
													}

													if ((map.get("validateresult").toString()).equals("1")) {
														validateresult = "通过";
													} else if (map.get("validateresult").toString().equals(
															"2")) {
														validateresult = "没通过";
													} else {
														validateresult = "没有验证";
													}

													if (map.get("remark") == null) {
														remark = "";
													} else {
														remark = map.get("remark").toString();
													}
									%>
									<tr>
										<td align="center">
											<%
												if (map.get("validateresult").toString().equals("1")) {
															} else {
											%>
											<input name="list" type="checkbox"
												value=<%=map.get("advanceid")%>>
											<%
												}
											%>
										</td>
										<td align="center">
											<font><span class="STYLE77"><%=map.get("advancename")%></span>
										</td>
										<td align="center" bgcolor="#F5FAFA">
											<font><a
												href="advance.do?CMD=xinaxi&advanceid=<%=map.get("advanceid")%>"><span
													class="STYLE77"><%=map.get("advance")%></span>
											</a>
										</td>
										<td align="center">
											<font><span class="STYLE77"><%=map.get("advanceperson")%></span>
										</td>
										<td align="center">
											<font><span class="STYLE77">
													<%
														if (map.get("advancedate") == null) {
																		out.print("");
																	} else {
																		out
																				.print(dateFormat.format(map
																						.get("advancedate")));
																	}
													%>
											</span> </font>
										</td>
										<td align="center">
											<font><span class="STYLE77"><%=validateperson%></span>
											</font>
										</td>
										<td bgcolor="#F5FAFA" align="center">
											<font><span class="STYLE77"> <%=validateresult%>
											</span> </font>
										</td>
										<td align="center">
											<font><span class="STYLE77"> <%=remark%> </span> </font>
										</td>
										<td align="center" bgcolor="#F5FAFA">
											<%
												if (map.get("validateresult").toString().equals("1")) {
															} else {
											%>
											<a
												href="advance.do?CMD=modifyjsp&advanceid=<%=map.get("advanceid")%>"><span
												class="STYLE17">修改</span>
											</a>
											<%
												}
											%>
										</td>
									</tr>
									<%
										}
											}
									%>
									<tr>
										<td align="right" colspan="9" class="list_footer">
											共有<%=request.getAttribute("advanceCount")%>条&nbsp;&nbsp; 第<%=request.getAttribute("currentPage")%>页&nbsp;
											共<%=request.getAttribute("pageCount")%>页&nbsp;
											<a
												href="advance.do?CMD=<%=request.getAttribute("CMD")%>&pagentid=<%=request.getAttribute("pagentid")%>&childagentid=<%=request.getAttribute("childagentid")%>&ep=<%=request.getAttribute("ep")%>&aresult=<%=request.getAttribute("aresult")%>&pageNo=1"><img
													src="images/bt_first.gif" width="42" height="16" border="0"
													align="absmiddle">
											</a>
											<a
												href="advance.do?CMD=<%=request.getAttribute("CMD")%>&pagentid=<%=request.getAttribute("pagentid")%>&childagentid=<%=request.getAttribute("childagentid")%>&ep=<%=request.getAttribute("ep")%>&aresult=<%=request.getAttribute("aresult")%>&pageNo=<%=((Integer) request.getAttribute("currentPage") - 1)%>"><img
													src="images/bt_pre.gif" width="38" height="16" border="0"
													align="absmiddle">
											</a>
											<a
												href="advance.do?CMD=<%=request.getAttribute("CMD")%>&pagentid=<%=request.getAttribute("pagentid")%>&childagentid=<%=request.getAttribute("childagentid")%>&ep=<%=request.getAttribute("ep")%>&aresult=<%=request.getAttribute("aresult")%>&pageNo=<%=((Integer) request.getAttribute("currentPage") + 1)%>"><img
													src="images/bt_next.gif" width="41" height="16" border="0"
													align="absmiddle">
											</a>
											<a
												href="advance.do?CMD=<%=request.getAttribute("CMD")%>&pagentid=<%=request.getAttribute("pagentid")%>&childagentid=<%=request.getAttribute("childagentid")%>&ep=<%=request.getAttribute("ep")%>&aresult=<%=request.getAttribute("aresult")%>&pageNo=<%=request.getAttribute("pageCount")%>"><img
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
	</body>
</html>

<script language="javascript">

var checkflag = "false";
function check(field) {
     if(field==null){
         return "全选";
        }
	if (checkflag == "false")
	 {  
		for (i = 0; i < field.length; i++)
		{
			field[i].checked = true;
		}
			checkflag = "true";
			return "取消"; 
	}else {
		for (i = 0; i < field.length; i++) 
		{
			field[i].checked = false; 
		}
		checkflag = "false";
		return "全选"; 
	}
}


function onSelect(form){
if (!dataIsAvail(form))   
		return;
		if (confirm("确定删除所选的内容吗?"))
		{  	
		form.action="advance.do?CMD=delete";
		form.submit();
	  }  
	  return;
}

function dataIsAvail(form)
	{
	  var chkbox = form.list;
	  var isChecked=false;
	  if (chkbox == null)
	  {
		alert("未选中内容，不能删除！");
		return false;
	  }
	  else
	  {
		var chkLength = chkbox.length;
	
		if(chkLength == null)
		{
		  if (chkbox.checked)
			isChecked=true;
		}
		else
		{
		  for(i=0;i<chkLength;i++)
		  {
			if(chkbox[i].checked)
			{
			  isChecked = true;
			  break;
			}
		  }
		}
		if(isChecked==false)
		{
		  alert("未选中内容，不能删除！");
		  return false;
		}
		else
		  return true;
	  }
	}

<!--模糊查询按键  -->
function advanceserach(form){
    form.action="advance.do?CMD=advance_search";
	form.submit();
}

function advanceadd(form){
 form.action="advance.do?CMD=advance_addjsp";
 form.submit();
 //document.location.href="advance.do?CMD=advance_addjsp";
}



function gotoye(form){
    var page=this.advanceform.gotopage.value;
    if(!isNumber(page)){
     alert("输入跳转页数非法,请重新输入.(只能由数字组成)");
     return false;
     }
    form.action="advance.do?CMD=<%=request.getAttribute("CMD")%>&pagentid=<%=request.getAttribute("pagentid")%>&childagentid=<%=request.getAttribute("childagentid")%>&ep=<%=request.getAttribute("ep")%>&aresult=<%=request.getAttribute("aresult")%>&pageNo="+page;
	form.submit();  
}


<!-- 装入上次查询用户类型查询、在线状态查询、用户有效状态查询的装态 -->
function goload() 
{ 
  var ep="<%=request.getAttribute("ep")%>";
  var aresult="<%=request.getAttribute("aresult")%>";
  var pagentid=parseInt(<%=request.getAttribute("pagentid")%>);
  var childagentid=parseInt(<%=request.getAttribute("childagentid")%>);
   document.getElementById("pagentid").value=pagentid;
   document.getElementById("childagentid").value=childagentid;
   document.getElementById("ep_id").value=ep;
   document.getElementById("vresult").value=aresult;
   var mes="<%=request.getAttribute("message")%>";
  if(mes!="null"){
    alert(mes);
    document.location.href="advance.do?CMD=advance_search&pagentid=<%=request.getAttribute("pagentid")%>&childagentid=<%=request.getAttribute("childagentid")%>&ep=<%=request.getAttribute("ep")%>&aresult=<%=request.getAttribute("aresult")%>";
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
  advanceform.childagentid.options.length=2; 
  var parentagent=advanceform.pagentid.value;
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
        var pagent=advanceform.pagentid.value;
        var cagent=advanceform.childagentid.value;
        advanceform.ep.options.length=1;
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
        var pagent=advanceform.pagentid.value;
        var cagent=advanceform.childagentid.value;
        advanceform.ep.options.length=1;
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