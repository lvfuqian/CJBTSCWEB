<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
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
	BORDER-LEFT: #7b9ebd 1px solid;
	CURSOR: hand;
	COLOR: black;
	PADDING-TOP: 2px;
	BORDER-BOTTOM: #7b9ebd 1px solid
}

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

.STYLE9 {
	color: #000000;
	font-weight: bold;
	text-indent: 10px;
}
-->
</style>
		<link href="css/list.css" rel="stylesheet" type="text/css" />
		<link href="styles/menu.css" rel="stylesheet" type="text/css" />
		<base href="<%=basePath%>">
		<title>TSC配置参数管理</title>
	</head>
	<body>
		<form name="ddbsearch" method="post">
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
									<span class="STYLE4">您现在的位置： <span class="STYLE7">TSC配置参数管理</span> </span>
								</td>
								<td width="52%">
									<img src="images/3_03.jpg" width="518" height="35">
								</td>
							</tr>
						</table>
						<div class="tdiv">
							<table width="18%" align="left" bordercolor="#C1DAD7"
								class="cladatas">
								<tr class="list_header" >
									<th >
										参数类型
									</th>
								</tr>
								<c:forEach var="logtype"  items="${requestScope.logList}">
								<tr>
									<td>
											<a href="xml.do?method=listAction&&type=${logtype.id }">${logtype.typeText }</a>										
									</td>
								</tr>
								</c:forEach>
							</table>
				<table width="80%" class="cladatas" align="right"
					bordercolor="#C1DAD7">
					<tr>
						<th class="list_header" >
						<input class=btn type="button" name="selectall" value="全选"
								onClick="this.value=check(document.forms[0].list)">
								</th>
								<th colspan="4" class="list_header" >&nbsp;</th>
					</tr>
					<tr >
						<th width="3%" >
							&nbsp;
						</th>
						<th width="8%" bgcolor="#F5FAFA" >
							<span class="STYLE77">元素名称</span>
						</th>
						<th width="7%" >
							<span class="STYLE77">元素内容</span>
						</th>
						<th width="10%" bgcolor="#F5FAFA" >
							<span class="STYLE77">元素说明</span>
						</th>
						<th width="5%" >
							<span class="STYLE77">操作</span>
						</th>
					</tr>
					<c:forEach var="xml" items="${requestScope.list}">
						<tr>
							<td align="center">
								<input id="list1" name="list" type="checkbox"
									value=" ${xml.eName}" />
							</td>
							<td bgcolor="#F5FAFA" align="center">
								<span class="STYLE77">${xml.eName} </span>
							</td>
							<td align="center">
								<span class="STYLE77">${xml.eValue} </span>
							</td>
							<td align="center" bgcolor="#F5FAFA">
								<span class="STYLE77">${xml.eComment} </span>
							</td>
							<td align="center">
								<a href="xml.do?method=findXmlBean&name=${xml.eName }"><span class="STYLE17">修改</span>
								</a>
							</td>
						</tr>
					</c:forEach>
				</table>
				</div>
				</td>
				</tr>
			</table>
		</form>
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
		form.action = "ddb.do?CMD=ddb_delete";
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
    form.action="ddb.do?CMD=ddb_search";
	form.submit();
}
//<!--单个增加 -->
function msidadd(form){
 form.action="ddb.do?CMD=to_add";
 form.submit();
}



//<!--批量冻结-->
function mspldj(form){
if (!dataIsdj(form))   
		return;
		if (confirm("确定冻结所选的内容吗?"))
		{  	
		form.action="ddb.do?CMD=msdj";
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
