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
<%@ page import="com.elegps.tscweb.model.TbPhoneTypeInfo"%>
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
		<form id="ptform" name="ptform" method="post" action="pt.do?CMD=<%=request.getAttribute("CMD")%>">
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
										<span class="STYLE4">您现在的位置：<span class="STYLE7">型号匹配设置</span>
										</span>
									</td>
									<td width="52%">
										<img src="images/3_03.jpg" width="518" height="35">
									</td>
								</tr>
							</table>
							<table width="100%">
								<tr>
									<td width="60%" align="right">
										<div align="right">
											型号:<input type="text" name = "type" id="type" value="${type }"/>
										</div>
									</td>
									<td width="12%" align="left">
										<div align="left">
											
										</div>
									</td>
									<td width="350" align="center" >
										<p>
											<span style="cursor: hand"><span style="cursor: hand"><img
														src="images/bt_cx.gif" width="65" height="22"
														Onclick="ptearch(document.forms[0])" />
											</span> <span style="cursor: hand"> <img
														src="images/bt_del.gif" width="65" height="22"
														Onclick="onSelect(document.forms[0])">
											</span>
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
									<td width="13%" align="center" class="list_header">
										<span class="STYLE77">型号</span>
									</td>
									<td width="12%" align="center" class="list_header">
										<span class="STYLE77">产品名称</span>
									</td>
									<td width="13%" align="center" class="list_header">
										<span class="STYLE77">MODEL名称</span>
									</td>
									<td width="10%" align="center" class="list_header">
										<span class="STYLE77">SDK版本</span>
									</td>
									<td width="13%" align="center" class="list_header">
										<span class="STYLE77">系统商</span>
									</td>
									<td width="13%" align="center" class="list_header">
										<span class="STYLE77">硬件商</span>
									</td>
									<td align="center" class="list_header"
										align="center">
										<span class="STYLE77">适配状态（点击修改配置）</span>
									</td>
								</tr>

								<logic:present name="ptList" scope="request">
									<%
										String Status = null;
											List<TbPhoneTypeInfo> list = (List<TbPhoneTypeInfo>) request.getAttribute("ptList");
											if (list != null) {
												for (int i = 0; i < list.size(); i++) {
													TbPhoneTypeInfo pt =  list.get(i);
									%>
									<tr>
										<td align="center">
											<input name="list" type="checkbox"
												value="<%=pt.getId() %>">
										</td>
										<td bgcolor="#F5FAFA" align="center">
											<font><a
												href="pt.do?CMD=xianxi&id=<%=pt.getId() %>"><span
													class="STYLE77"> <%=pt.getType() %></span>
											</a> </font>
										</td>
										<td align="center">
											<span class="STYLE77"> <%=pt.getProduct()==null?"":pt.getProduct() %></span>
										</td>
										<td align="center" bgcolor="#F5FAFA">
											<span class="STYLE77"> <%=pt.getModel() %></span>
										</td>
										<td align="center">
											<span class="STYLE77"> <%=pt.getVersionSdk()==null?"":pt.getVersionSdk() %></span>
										</td>
										<td align="center" bgcolor="#F5FAFA">
											<span class="STYLE77"> <%=pt.getBrand()==null?"":pt.getBrand() %></span>
										</td>
										<td align="center">
											<span class="STYLE77"> <%=pt.getManufacturer()==null?"":pt.getManufacturer() %></span>
										</td>
										<td align="center" bgcolor="#F5FAFA">
											<font><span class="STYLE77"> 
										<%
											String pzconfig ="";
										 	if (pt.getFalg()==0) {
							 					Status = "关闭";
							 					pzconfig = "0";
							 				} else {
							 					Status = "开启";
							 					pzconfig = "1";
							 				}
										 %>
 <a href="" onclick="return pzConfig('<%=pt.getId() %>','<%=pt.getFalg() %>');">
 <span class="STYLE17"><%=Status%></span> </a> 
	 </span> </font>
										</td>
										
										</tr>
									<%
										}
											}
									%>
</logic:present>

									<tr>
										<td align="right" colspan="8" class="list_footer">
											 每页显示
										 <select name="pageSize" id="pageSize"  style="{width:30;margin:0;FONT-SIZE:9pt;FONT-WEIGHT:normal;}">
										 <option value="10">10</option>
										 <option value="15">15</option>
										 <option value="20">20</option>
										 <option value="25">25</option>
										 <option value="30">30</option>
										 </select>
										 条&nbsp;&nbsp;
											共有<%=request.getAttribute("ptCount")%>条&nbsp;&nbsp;
											&nbsp;&nbsp;
											 第<%=request.getAttribute("currentPage")%>页&nbsp;
											共<%=request.getAttribute("pageCount")%>页&nbsp;
													<img src="images/bt_first.gif" width="42" height="16" border="0" align="absmiddle"  onClick="return pageclick('1');">
		<img src="images/bt_pre.gif" width="38" height="16" border="0" align="absmiddle"  onClick="return pageclick('<%=(((Integer)request.getAttribute("currentPage"))-1)%>');">
		<img src="images/bt_next.gif" width="41" height="16" border="0" align="absmiddle"  onClick="return pageclick('<%=(((Integer)request.getAttribute("currentPage"))+1)%>');">
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
								
							</table>
							</from>
	</body>
</html>

<script language="javascript">
function pageclick(page){
    ptform.action="pt.do?CMD=<%=request.getAttribute("CMD")%>&pageNo="+page;
	ptform.submit();
}

function pzConfig(id,config){
	var page=this.ptform.gotopage.value;
	var flag = window.confirm("确定要修改配置吗？");
	if(flag){
	     ptform.action="pt.do?CMD=pt_config&id="+id+"&flag="+config+"&pageNo="+page;
		 ptform.submit();
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
		var page=this.ptform.gotopage.value;  	
		form.action="pt.do?CMD=delete&pageNo="+page;
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

function gotoye(form){
    var page=this.ptform.gotopage.value;
     if(!isNumber(page)){
     alert("输入跳转页数非法,请重新输入.(只能由数字组成)");
     return false;
     }
	form.action="pt.do?CMD=<%=request.getAttribute("CMD")%>&pageNo="+page;
	form.submit();
}

function ptearch(form){
    form.action="pt.do?CMD=pt_search";
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
  var psize = <%=request.getAttribute("pageSize")%>;
  document.getElementById("pageSize").value=psize;
  
  var mes='<%=request.getAttribute("message")%>';
  if(mes!="null"){
    alert(mes);
    document.location.href="pt.do?CMD=pt_search&type=<%=request.getAttribute("type")%>&pageNo=<%=request.getAttribute("currentPage")%>&psize="+psize;
  }
} 



</script>


