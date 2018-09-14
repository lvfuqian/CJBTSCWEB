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
		<title>电召员用户管理</title>
		<link rel="stylesheet" type="text/css" href="styles.css">
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
									<span class="STYLE4">您现在的位置：<span class="STYLE7">电召用户管理</span>
									</span>
								</td>
								<td width="52%">
									<img src="images/3_03.jpg" width="518" height="35">
								</td>
							</tr>
						</table>
						<table width="90%" border="0">
							<tr>
								<td align="center">
									姓名:
									<input type="text" name="userName" value="${requestScope.userName }" />
								</td>
								<td align="center">
									性别:
									<select name="userSex" id="userSex">
										<option value=""  selected="selected">全部</option>
										<option value="0">男</option>
										<option value="1">女</option>
									</select>
									<script type="text/javascript">
										document.all.userSex.value=${requestScope.userSex};
									</script>
								</td>
								<td align="center">
									联系电话:
									<input type="text" name="userMobile" value="${requestScope.userMobile }" />
								</td>
								<td width="8%" align="center">
									<span style="cursor: hand"> <img src="images/bt_cx.gif"
											alt="查询" width="65" height="22"
											onclick="msidserach(document.forms[0])" /> </span>
								</td>
								<td width="8%" align="center">
									<span style="cursor: hand"> <img src="images/bt_add.gif"
											alt="增加" width="65" height="22"
											onclick="msidadd(document.forms[0])" /> </span>
								</td>
								<td width="8%" align="center">
									<span style="cursor: hand"> <img src="images/bt_del.gif"
											alt="删除" width="65" height="22"
											onclick="onSelect(document.forms[0])" /> </span>
								</td>
							</tr>
						</table>
						<br />
						<table width="93%" height="21" border="1" bordercolor="#C1DAD7"
							class="listtable">
							<tr height="12">
								<th width="5%" height="12" align="center" class="list_header">
									<input class=btn type="button" name="selectall" value="全选"
										onClick="this.value=check(document.forms[0].list)">

								</th>
								<th width="8%" class="list_header" height="12">
									<span class="STYLE77">编号</span>
								</th>
								<th width="7%" class="list_header" height="12" align="center">
									<span class="STYLE77">姓名</span>
								</th>
								<th width="7%" class="list_header" height="12">
									<span class="STYLE77">性别</span>
								</th>
								<th width="15%" class="list_header" height="12">
									<span class="STYLE77">身份证</span>
								</th>
								<th width="9%" class="list_header" height="12">
									<span class="STYLE77">联系电话</span>
								</th>
								<th width="20%" class="list_header" height="12">
									<span class="STYLE77">联系地址</span>
								</th>
								<th width="5%" class="list_header" height="12">
									<span class="STYLE77">操作</span>
								</th>
							</tr>
							<!-- 
								<logic:present name="ddblist" scope="request">
							 -->
							<c:forEach var="phoneuser" items="${requestScope.phoneUser}">
								<tr>
									<td align="center">
										<input id="list1" name="list" type="checkbox"
											value=" ${phoneuser.userId}" />
									</td>
									<td bgcolor="#F5FAFA" align="center">
										<font> <span class="STYLE77">${phoneuser.userId}
										</span> </font>
									</td>
									<td align="center">
										<font> <span class="STYLE77">${phoneuser.userName}
										</span> </font>
									</td>
									<td bgcolor="#F5FAFA" align="center">
										<font color="">
											 <span class="STYLE77">
												<c:if test="${phoneuser.userSex==0}">男</c:if>
												<c:if test="${phoneuser.userSex==1}">女</c:if>
											</span>
										</font>
									</td>
									<td align="center">
										<font color=""> <span class="STYLE77">${phoneuser.userIdCard}
										</span> </font>
									</td>
									<td bgcolor="#F5FAFA" align="center">
										<font color=""><span class="STYLE77">${phoneuser.userMobile}</span>
										</font>
									</td>
									<td  align="center">
										<font color=""> <span class="STYLE77">${phoneuser.userAddress} </span> </font>
									</td>
									<td bgcolor="#F5FAFA" align="center">
										<a href="puser.do?CMD=5&userId=${phoneuser.userId}"><span
											class="STYLE17">修改</span> </a>
									</td>
								</tr>
							</c:forEach>
							<tr>
								<td align="right" colspan="10" class="list_footer">
									共有${requestScope.msCount}条&nbsp;&nbsp;
									第${requestScope.currentPage}页&nbsp;
									共${requestScope.pageCount}页&nbsp;
									<a href="puser.do?CMD=${requestScope.CMD}&pageNo=1"><img
											src="images/bt_first.gif" width="42" height="16" border="0" />
									</a>
									<a href="puser.do?CMD=${requestScope.CMD}&pageNo=${requestScope.currentPage -1}"><img
											src="images/bt_pre.gif" width="38" height="16" border="0" />
									</a>
									<a href="puser.do?CMD=${requestScope.CMD}&pageNo=${requestScope.currentPage +1}"><img
											src="images/bt_next.gif" width="41" height="16" border="0" />
									</a>
									<a href="puser.do?CMD=${requestScope.CMD}&pageNo=${requestScope.pageCount}"><img
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
							<!-- 
								</logic:present>
								 -->
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
		form.action = "puser.do?CMD=3";
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
    form.action="puser.do?CMD=0";
	form.submit();
}
//<!--单个增加 -->
function msidadd(form){
 form.action="puser.do?CMD=4";
 form.submit();
}




function gotoye(form){
 	 var page=this.ddbsearch.gotopage.value;
	 if(!isNumber(page)){
     alert("输入跳转页数非法,请重新输入.(只能由数字组成)");
     return false;
     }
    form.action="puser.do?CMD=<%=request.getParameter("CMD")%>&pageNo="+page;
	form.submit();   
}



//<!-- 装入上次查询用户类型查询、在线状态查询、用户有效状态查询的装态 ->
/**function goload() 
{ 
   document.getElementById("userName").value=userName;
   document.getElementById("userSex").value=userSex;
   document.getElementById("userMobile").value=userMobile; 

    document.location.href="puser.do?CMD=0&userName="+userName+"&userSex="+userSex+"&userMobile="+userMobile;

} **/


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
