<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@ page import="com.elegps.tscweb.model.TbGrpMsListInfo"%>
<%@ page import="com.elegps.tscweb.model.TbGrpInfo"%>
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
	FILTER: progid :                 DXImageTransform.Microsoft.Gradient (  
		       
		      GradientType =                 0, StartColorStr =               
		 #ffffff, EndColorStr =     
		    
		      #cecfde );
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
		<title>油量参数设置管理</title>
		<link rel="stylesheet" type="text/css" href="styles.css">
	</head>
	<body onload="goload()">
		<form name="controlsearch" method="post">
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
								<td width="50%" background="images/3_04.gif">
									<span class="STYLE4">您现在的位置：<span class="STYLE7">终端油量参数设置</span>
									</span>
								</td>
								<td width="50%">
									<img src="images/3_03.jpg" width="518" height="35">
								</td>
							</tr>
						</table>
						<table width="100%">
							<tr>
								<td width="12%" align="right">
									<div align="right">
										总部/一级代理商:
									</div>
								</td>
								<td width="12%" align="left">
									<div align="left">
										<select name="pagentid" id="pagentid"
											style="width: 100; margin: 0; FONT-SIZE: 9pt; FONT-WEIGHT: normal;"
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
								<td width="14%" align="right">
									<div align="right">
										直属企业/二级代理商:
									</div>
								</td>
								<td width="12%" align="left">
									<div align="left">
										<select name="childagentid" id="childagentid"
											style="width: 128; margin: 0; FONT-SIZE: 9pt; FONT-WEIGHT: normal;"
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
											style="width: 128; margin: 0; FONT-SIZE: 9pt; FONT-WEIGHT: normal;"
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
								<td width="10%" align="right">
									群组号码:
								</td>
								<td width="150" align="left">
									<select name="grpid" id="grp_id"
										style="width: 90; margin: 0; FONT-SIZE: 9pt; FONT-WEIGHT: normal;">
										<option value="-1">
											全部
										</option>
										<%
											List listgrp = (List) request.getAttribute("grpList");
											if (listgrp != null) {
												for (int i = 0; i < listgrp.size(); i++) {
													TbGrpInfo tbgrpinfo = new TbGrpInfo();
													tbgrpinfo = (TbGrpInfo) listgrp.get(i);
										%>
										<option value="<%=tbgrpinfo.getGrpid()%>"><%=tbgrpinfo.getGrpname()%></option>
										<%
											}
											}
										%>
									</select>
								</td>
							</tr>
							<tr>
								<td width="10%" align="right">
									终端号码:
								</td>
								<td>
									<input name="msId" id="msId" value="${requestScope.msId }" />
								</td>
								<td width="10%" align="right">
									终端名称:
								</td>
								<td>
									<input name="msName" id="msName" value="${requestScope.msName }" />
								</td>
								<td width="10%" align="right">
									油量设置:
								</td>
								<td width="150" align="left">
									<select name="setr01" id="setr01"
										style="width: 90; margin: 0; FONT-SIZE: 9pt; FONT-WEIGHT: normal;">
										<option value="-1" >
											全部
										</option>
										<option value="0">
											否
										</option>
										<option value="1">
											是
										</option>
									</select>
								</td>
								<td align="center" colspan="3">
									<span style="cursor: hand"><img
											src="images/bt_cx.gif" width="65" height="22"
											Onclick="msidserach(document.forms[0])" /> </span>
								</td>
							</tr>
						</table>
						<br />
						<table width="100%" height="21" border="1" bordercolor="#C1DAD7">
							<tr height="12">
								<td width="3%" height="12" align="center" class="list_header">
								&nbsp;
								</td>
								<td width="10%" class="list_header" height="12" align="center">
									<span class="STYLE77">终端ID</span>
								</td>
								<td width="10%" class="list_header" height="12" align="center">
									<span class="STYLE77">终端名称</span>
								</td>
								<td width="10%" class="list_header" height="12" align="center">
									<span class="STYLE77">是否设置油量</span>
								</td>
								<td width="9%" class="list_header" height="12" align="center">
									<span class="STYLE77">油量参数一</span>
								</td>
								<td width="9%" class="list_header" height="12" align="center">
									<span class="STYLE77">油量参数二</span>
								</td>
								<td width="9%" class="list_header" height="12" align="center">
									<span class="STYLE77">油量初始值</span>
								</td>
								<td width="5%" class="list_header" height="12" align="center">
									<span class="STYLE77">操作</span>
								</td>
							</tr>
							<c:forEach var="controllist" items="${requestScope.list}" varStatus="status">
								<tr>
									<td align="center">
											${(requestScope.currentPage-1)*requestScope.pageSize+status.count}
									</td>
									<td bgcolor="#F5FAFA" align="center">
										<span class="STYLE77">${controllist.msId} </span>
									</td>
									<td align="center">
										<span class="STYLE77">${controllist.msName} </span>
									</td>
									<td align="center">
										<span class="STYLE77">
											<c:if test="${controllist.r01==0}">否</c:if>
											<c:if test="${controllist.r01==1}">是</c:if>
										 </span>
									</td>
									<td align="center">
										<span class="STYLE77"><c:if test="${controllist.r01==1}">${controllist.r02}</c:if></span>
									</td>
									<td align="center">
										<span class="STYLE77"><c:if test="${controllist.r01==1}">${controllist.r03}</c:if></span>
									</td>
									<td bgcolor="#F5FAFA" align="center">
										<c:if test="${controllist.r01==1}">${controllist.r04}</c:if>
									</td>
									<td bgcolor="#F5FAFA" align="center">
										<a href="control.do?CMD=1&&msIds=${controllist.msId}&msNames=${controllist.msName}"><span
											class="STYLE17">修改</span> </a>
									</td>
								</tr>
							</c:forEach>
							<tr>
								<td align="right" colspan="10" class="list_footer">
									共有${requestScope.msCount}条&nbsp;&nbsp;
									第${requestScope.currentPage}页&nbsp;
									共${requestScope.pageCount}页&nbsp;
									<a href="control.do?CMD=${requestScope.CMD}&pageNo=1&pagentid=${requestScope.pagentid}&ep=${requestScope.ep}
									&childagentid=${requestScope.childagentid}&msId=${requestScope.msId}
									&msName=${requestScope.msName}&grpid=${requestScope.grpid}&setr01=${requestScope.setr01}"><img
											src="images/bt_first.gif" width="42" height="16" border="0" />
									</a>
									<a href="control.do?CMD=${requestScope.CMD}&pageNo=${requestScope.currentPage -1}&pagentid=${requestScope.pagentid}&ep=${requestScope.ep}
									&childagentid=${requestScope.childagentid}&msId=${requestScope.msId}
									&msName=${requestScope.msName}&grpid=${requestScope.grpid}&setr01=${requestScope.setr01}"><img
											src="images/bt_pre.gif" width="38" height="16" border="0" />
									</a>
									<a href="control.do?CMD=${requestScope.CMD}&pageNo=${requestScope.currentPage +1}&pagentid=${requestScope.pagentid}&ep=${requestScope.ep}
									&childagentid=${requestScope.childagentid}&msId=${requestScope.msId}
									&msName=${requestScope.msName}&grpid=${requestScope.grpid}&setr01=${requestScope.setr01}"><img
											src="images/bt_next.gif" width="41" height="16" border="0" />
									</a>
									<a href="control.do?CMD=${requestScope.CMD}&pageNo=${requestScope.pageCount}&pagentid=${requestScope.pagentid}&ep=${requestScope.ep}
									&childagentid=${requestScope.childagentid}&msId=${requestScope.msId}
									&msName=${requestScope.msName}&grpid=${requestScope.grpid}&setr01=${requestScope.setr01}"><img
											src="images/bt_last.gif" width="42" height="16" border="0" />
									</a> 转到第
									<input type="text" size="2" name="gotopage"
										value="${requestScope.currentPage}" />
									页
									<span style="cursor: hand"><img src="images/bt_go.gif"
											width="20" height="12" border="0"
											onClick="gotoye(document.forms[0])" /> </span>
								</td>
							</tr>
						</table>
					</td>
				</tr>
				</form>
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
		form.action = "control.do?CMD=ddb_delete";
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
}//<!--模糊查询按键  -->
function msidserach(form){
    form.action="control.do?CMD=0";
	form.submit();
}
//<!--单个增加 -->
function msidadd(form){
 form.action="control.do?CMD=to_add";
 form.submit();
}



//<!--批量冻结-->
function mspldj(form){
if (!dataIsdj(form))   
		return;
		if (confirm("确定冻结所选的内容吗?"))
		{  	
		form.action="control.do?CMD=msdj";
		form.submit();
	  }  
	  return;
}

function dataIsdj(form)
	{
	  var chkbox = form.list;
	  var isChecked=false;
	  if (chkbox == null)
	  {
		alert("未选中内容，不能冻结！");
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
		  alert("未选中内容，不能冻结！");
		  return false;
		}
		else
		  return true;
	  }
	}
	
function gotoye(form){
 	 var page=this.ddbUserForm.gotopage.value;
	 if(!isNumber(page)){
     alert("输入跳转页数非法,请重新输入.(只能由数字组成)");
     return false;
     }
    form.action="control.do?CMD=<%=request.getParameter("CMD")%>&pageNo="+page;
	form.submit();   
}




//<!-- 装入上次查询的装态 ->
function goload() 
{ 
  var ep="<%=request.getAttribute("ep")%>";
  var pagentid=parseInt(<%=request.getAttribute("pagentid")%>);
  var childagentid=parseInt(<%=request.getAttribute("childagentid")%>);
  var grpid="<%=request.getAttribute("grpid")%>";
  var msid="<%=request.getAttribute("msId")%>";
  var setr01="<%=request.getAttribute("setr01")%>";
   document.getElementById("pagentid").value=pagentid;
   document.getElementById("childagentid").value=childagentid;
   document.getElementById("ep_id").value=ep;
   document.getElementById("grp_id").value=grpid;
   document.getElementById("grp_id").value=grpid;
   document.getElementById("setr01").value=setr01;
  var mes="<%=request.getAttribute("message")%>";
  if(mes!="null"){
    alert(mes);
    document.location.href="control.do?CMD=listControl&pagentid=<%=request.getAttribute("pagentid")%>&childagentid=<%=request.getAttribute("childagentid")%>&ep=<%=request.getAttribute("ep")%>&grpid=<%=request.getAttribute("grpid")%>&msid=<%=request.getAttribute("msid")%>";
  }   
} 

function parentagentchang(){
  var childagentselect= document.getElementById("childagentid");
  controlsearch.childagentid.options.length=2; 
  var parentagent=controlsearch.pagentid.value;
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


function getEpinfo(){
        var epselect= document.getElementById("ep_id");
        var pagent=controlsearch.pagentid.value;
        var cagent=controlsearch.childagentid.value;
        controlsearch.ep.options.length=1;
        controlsearch.grp_id.options.length=1;
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

function epchang(){
 getGrpinfo();
}


function getGrpinfo(){
  var grpselect =document.getElementById("grp_id");
  var pagent=controlsearch.pagentid.value;
  var childagent=controlsearch.childagentid.value;
  var ep=controlsearch.ep.value;
  grpselect.options.length=1;
  ChildAgent.getGrpinfo(pagent,childagent,ep,function(data){
   if(data!=null){
      for(var i=0;i<data.length;i++){
             for(var j=0;j<data[i].length;j++){
              var option = document.createElement("OPTION");
               option.value=data[i][j];
 	           option.text=data[i][++j];
               grpselect.add(option);
              }
           }
   }
  });
}

</script>
