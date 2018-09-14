<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@ page import="com.elegps.tscweb.model.TbGrpMsListInfo"%>
<%@ page import="com.elegps.tscweb.model.TbMsInfo"%>
<%@ page import="com.elegps.tscweb.model.TbGrpInfo"%>
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
		<form id="grpmsform" name="grpmsform" method="post">
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
										<span class="STYLE4">您现在的位置：<span class="STYLE7">群组、终端关系设置</span>
										</span>
									</td>
									<td width="52%">
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
												<%
															int eid =Integer.parseInt( request.getSession().getAttribute("epId").toString());
															int rid =Integer.parseInt( request.getSession().getAttribute("roleId").toString());
															int apid =Integer.parseInt( request.getSession().getAttribute("aPId").toString());
															if(apid>0){
															}else if ((rid == 3||rid == 4) && eid != 0){
															%>
															<option value="-1">
															直属企业
															</option>
															<%
															}else{
															%>													
														<option value="-2">
															所有企业
														</option>
          												<option value="-1">直属企业</option>													
														<%
															}
														 %>	
												
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
												style="width: 128; margin: 0; FONT-SIZE: 9pt; FONT-WEIGHT: normal;" onchange="epchang()">
												<%
																if(rid!=3 && rid!=4 && eid == 0){
															%>
																<option value="-1">
																全部企业
																</option>
															<%
																}
															 %>
															
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
									<td width="350" align="center" >
										<p>
											<span style="cursor: hand"><span style="cursor: hand"><img
														src="images/bt_cx.gif" width="65" height="22"
														Onclick="grpmsearch(document.forms[0])" />
											</span> <span style="cursor: hand"><img
														src="images/bt_add.gif" width="65" height="22"
														Onclick="grpmsadd(document.forms[0])" />
											</span> <span style="cursor: hand"> <img
														src="images/bt_del.gif" width="65" height="22"
														Onclick="onSelect(document.forms[0])">
											</span>
										</p>
									</td>
								</tr>
								<tr>
									<td width="10%" align="right">
										群组号码:
									</td>
									<td width="150" align="left">
										<select name="grpid" id="grp_id"
											style="width: 180; margin: 0; FONT-SIZE: 9pt; FONT-WEIGHT: normal;">
											<option value="">
												全部
											</option>
											<%
												List listgrp = (List) request.getAttribute("grpList");
												if (listgrp != null) {
													for (int i = 0; i < listgrp.size(); i++) {
														TbGrpInfo tbgrpinfo = new TbGrpInfo();
														tbgrpinfo = (TbGrpInfo) listgrp.get(i);
											%>
											<option value="<%=tbgrpinfo.getGrpid()%>"><%=tbgrpinfo.getGrpid()%>-<%=tbgrpinfo.getGrpname()%></option>
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
										<span style="cursor: hand"><img
												src="images/biao_zdzj.gif" width="120" height="22"
												Onclick="grpmspladd(document.forms[0])" />
										</span>
										<span style="cursor: hand"><img
												src="images/biao_qzzj.gif" width="120" height="22"
												Onclick="msgrppladd(document.forms[0])" />
										</span>
									</td>
								</tr>
							</table>

							<br>



							<table width="100%" height="21" border="1" bordercolor="#C1DAD7">
								<tr>
									<td width="4%" class="list_header" align="center">
										<input class=btn type="button" name="selectall" value="全选"
											onClick="this.value=check(document.forms[0].list)">
									</td>
									<td width="15%" align="center" class="list_header">
										<span class="STYLE77">群组号码</span>
									</td>
									<td width="15%" align="center" class="list_header">
										<span class="STYLE77">群组名称</span>
									</td>
									<td width="15%" align="center" class="list_header">
										<span class="STYLE77">终端号码</span>
									</td>
									<td width="15%" align="center" class="list_header">
										<span class="STYLE77">终端名称</span>
									</td>
									<td width="15%" align="center" class="list_header"
										align="center">
										<span class="STYLE77">群组配置（点击修改配置）</span>
									</td>
									<!--<td width="12%" align="center" class="list_header"
										align="center">
										<span class="STYLE77">操作</span>
									</td>
								--></tr>

								<logic:present name="grpmsList" scope="request">
									<%
										String Status = null;
											List list = (List) request.getAttribute("grpmsList");
											if (list != null) {
												for (int i = 0; i < list.size(); i++) {
													Map map = new HashMap();
													map = (Map) list.get(i);
									%>
									<tr>
										<td align="center">
											<input name="list" type="checkbox"
												value="<%=map.get("msid")%>,<%=map.get("grpid")%>">
										</td>
										<td bgcolor="#F5FAFA" align="center">
											<font><a
												href="grp.do?CMD=xinaxi&grpid=<%=map.get("grpid")%>"><span
													class="STYLE77"> <%=map.get("grpid")%></span>
											</a> </font>
										</td>
										<td align="center">
											<span class="STYLE77"> <%=map.get("grpname")%></span>
										</td>
										<td align="center" bgcolor="#F5FAFA">
											<font><a
												href="ms.do?CMD=xinaxi&msid=<%=map.get("msid")%>"><span
													class="STYLE77"> <%=map.get("msid")%></span>
											</a> </font>
										</td>
										<td align="center">
											<span class="STYLE77"> <%=map.get("msname")%></span>
										</td>
										<td align="center" bgcolor="#F5FAFA">
											<font><span class="STYLE77"> <%
											String pzconfig ="";
 	if (map.get("config").toString().equals("0")) {
 					Status = "允许";
 					pzconfig = "1";
 				} else {
 					Status = "阻止";
 					pzconfig = "0";
 				}
 %><a href="" 
 	onclick="return grpmspz('<%=map.get("grpid")%>','<%=map.get("msid")%>','<%=pzconfig%>');">
 <span class="STYLE17"><%=Status%></span> </a> 
	 </span> </font>
										</td>
										<!--<td class="STYLE8" align="center">
											<a
												href="grpms.do?CMD=togrpmsmodijsp&grpid=<%=map.get("grpid")%>&msid=<%=map.get("msid")%>"><span
												class="STYLE17">修改</span> </a>

										</td>-->
										</tr>
									<%
										}
											}
									%>


									<tr>
										<td align="right" colspan="7" class="list_footer">
											 每页显示
										 <select name="pageSize" id="pageSize"  style="{width:30;margin:0;FONT-SIZE:9pt;FONT-WEIGHT:normal;}">
										 <option value="10">10</option>
										 <option value="15">15</option>
										 <option value="20">20</option>
										 <option value="25">25</option>
										 <option value="30">30</option>
										 </select>
										 条&nbsp;&nbsp;
											共有<%=request.getAttribute("grpmsCount")%>条&nbsp;&nbsp;
											&nbsp;&nbsp;
											 第<%=request.getAttribute("currentPage")%>页&nbsp;
											共<%=request.getAttribute("pageCount")%>页&nbsp;
													<img src="images/bt_first.gif" width="42" height="16" border="0" align="absmiddle"  onClick="return pageclick('1');">
		<img src="images/bt_pre.gif" width="38" height="16" border="0" align="absmiddle"  onClick="return pageclick('<%=((Integer)request.getAttribute("currentPage")-1)%>');">
		<img src="images/bt_next.gif" width="41" height="16" border="0" align="absmiddle"  onClick="return pageclick('<%=((Integer)request.getAttribute("currentPage")+1)%>');">
		<img src="images/bt_last.gif" width="42" height="16" border="0" align="absmiddle"   onClick="return pageclick('<%=request.getAttribute("pageCount")%>');">
											 转到第
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
							</from>
	</body>
</html>

<script language="javascript">
function pageclick(page){
    grpmsform.action="grpms.do?CMD=<%=request.getAttribute("CMD")%>&pageNo="+page;
	grpmsform.submit();
}

function grpmspz(pzgrpid,pzmsid,config){
	var flag = window.confirm("确定要修改此记录吗？");
	if(flag){
	     grpmsform.action="grpms.do?CMD=grpms_search&pzgrpid="+pzgrpid+"&pzmsid="+pzmsid+"&config="+config;
		 grpmsform.submit();
  	}
  	return false;
}

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
		form.action="grpms.do?CMD=delete";
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


function grpmsadd(form){
 form.action="grpms.do?CMD=addtojsp";
 form.submit();
// document.location.href="grpms.do?CMD=addtojsp"
}

function grpmspladd(form){
 //根据群组号批量添加终端
  form.action="grpms.do?CMD=addtoplms_bygrpjsp";
  form.submit();
 //document.location.href="grpms.do?CMD=addtoplms_bygrpjsp"
}

function msgrppladd(form){
//根据终端号批量添加终端群组
  form.action="grpms.do?CMD=addtoplgrp_bymsjsp";
  form.submit();
 //document.location.href="grpms.do?CMD=addtoplgrp_bymsjsp"
}

function gotoye(form){
    var page=this.grpmsform.gotopage.value;
     if(!isNumber(page)){
     alert("输入跳转页数非法,请重新输入.(只能由数字组成)");
     return false;
     }
	form.action="grpms.do?CMD=<%=request.getAttribute("CMD")%>&pagentid=<%=request.getAttribute("pagentid")%>&childagentid=<%=request.getAttribute("childagentid")%>&ep=<%=request.getAttribute("ep")%>&grpid=<%=request.getAttribute("grpid")%>&msid=<%=request.getAttribute("msid")%>&pageNo="+page;
	form.submit();
}

function grpmsearch(form){
    form.action="grpms.do?CMD=grpms_search";
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




//<!-- 装入上次查询的装态 ->
function goload() 
{ 
  var ep=parseInt(<%=request.getAttribute("ep")%>);
  var pagentid=parseInt(<%=request.getAttribute("pagentid")%>);
  var childagentid=parseInt(<%=request.getAttribute("childagentid")%>);
  var grpid="<%=request.getAttribute("grpid")%>";
  var msid="<%=request.getAttribute("msid")%>";
    var psize = <%=request.getAttribute("pageSize")%>;
  document.getElementById("pageSize").value=psize;
   document.getElementById("pagentid").value=pagentid;
   document.getElementById("childagentid").value=childagentid;
   document.getElementById("ep_id").value=ep;
   document.getElementById("grp_id").value=grpid;
   document.getElementById("ms_id").value=msid;
  var mes="<%=request.getAttribute("message")%>";
  if(mes!="null"){
    alert(mes);
    document.location.href="grpms.do?CMD=grpms_search&pagentid=<%=request.getAttribute("pagentid")%>&childagentid=<%=request.getAttribute("childagentid")%>&ep=<%=request.getAttribute("ep")%>&grpid=<%=request.getAttribute("grpid")%>&msid=<%=request.getAttribute("msid")%>";
  }   
    var r_id=<%=rid %>; 
  if(r_id == 4 ||r_id == 3){
  	document.getElementById("childagentid").length = 1;
	//document.getElementById("ep_id").options.remove(0);
  }
  if(<%=rid %> == 4 || <%=rid %> == 2 || <%=rid %> == 3){
  	document.getElementById("pagentid").length = 1;
  }
} 

function parentagentchang(){
  var childagentselect= document.getElementById("childagentid");
   
  var parentagent=grpmsform.pagentid.value;
  if(parentagent!=null){
        ChildAgent.getchiledAgent(parentagent,function(data){
        grpmsform.childagentid.options.length=2;
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
        var pagent=grpmsform.pagentid.value;
        var cagent=grpmsform.childagentid.value;
        grpmsform.ep.options.length=1;
           grpmsform.grp_id.options.length=1;
        	grpmsform.ms_id.options.length=1;
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
 getGrpinfo();
 getMsinfo();
}


function getGrpinfo(){
  var grpselect =document.getElementById("grp_id");
  var pagent=grpmsform.pagentid.value;
  var childagent=grpmsform.childagentid.value;
  var ep=grpmsform.ep.value;
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

function getMsinfo(){
  var msselect =document.getElementById("ms_id");
  var pagentid=grpmsform.pagentid.value;
  var childagentid=grpmsform.childagentid.value;
  var ep=grpmsform.ep.value;
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


