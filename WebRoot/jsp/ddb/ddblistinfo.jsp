<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.elegps.tscweb.model.TabSysserverdbinfo"%>
<%@page import="com.elegps.tscweb.serivce.DdbManager"%>
<%@page import="com.elegps.tscweb.serivce.impl.DdbManagerImpl"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
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
	FILTER: progid :         DXImageTransform.Microsoft.Gradient (      
		  GradientType =         0, StartColorStr =         #ffffff,
		EndColorStr = 
		    
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
		<title>调度用户管理</title>
		<!--
	-->
		<link rel="stylesheet" type="text/css" href="styles.css">
	</head>
	<body>
		<form name="ddbUserForm" method="post">
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
									<span class="STYLE4">您现在的位置：<span class="STYLE7">调度用户管理</span>
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
									登录账号
									<input type="text" name="loginids" value="" />
								</td>
								<td align="center">
									调度名称
									<input type="text" name="userNames" value="" />
								</td>
								<td align="center">
									调度ID
									<input type="text" name="msids" value="" />
								</td>
								<td width="8%" align="center">
									<span style="cursor: hand"> <img src="images/bt_cx.gif"
											alt="查询" width="65" height="22"
											onclick="msidserach(document.forms[0])" /> </span>
								</td>
								<%
													int r_id=Integer.parseInt(request.getSession().getAttribute("roleId").toString());
													if(r_id != 2 && r_id != 3 && r_id != 4 ){//代理商、企业、直属企业
													 %>
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
								<%} %>
							</tr>
						</table>
						<br />
						<table width="93%" height="21" border="1" bordercolor="#C1DAD7">
							<tr height="12">
							<%
							if(r_id != 2 && r_id != 3 && r_id != 4 ){//代理商、企业、直属企业
							 %>
								<td width="5%" height="12" align="center" class="list_header">
									<input class=btn type="button" name="selectall" value="全选"
										onClick="this.value=check(document.forms[0].list)">
								</td>
								<%} %>
								<td width="8%" class="list_header" height="12">
									<span class="STYLE77">登录帐号</span>
								</td>
								<td width="7%" class="list_header" height="12" align="center">
									<span class="STYLE77">登录密码</span>
								</td>
								<td width="10%" class="list_header" height="12">
									<span class="STYLE77">调度名称</span>
								</td>
								<td width="15%" class="list_header" height="12">
									<span class="STYLE77">调度ID</span>
								</td>
								<td width="9%" class="list_header" height="12">
									<span class="STYLE77">终端服务器</span>
								</td>
								<td width="5%" class="list_header" height="12" >
									<span class="STYLE77">操作</span>
								</td>
							</tr>
							<!-- 
								<logic:present name="ddblist" scope="request">
							 -->
							<c:forEach var="ddb" items="${requestScope.ddblist}">
								<tr>
								<%
							if(r_id != 2 && r_id != 3 && r_id != 4 ){//代理商、企业、直属企业
							 %>
									<td align="center">
										<input id="list1" name="list" type="checkbox"
											value=" ${ddb.userId}" />
									</td>
										<%} %>
									<td bgcolor="#F5FAFA" align="center">
										<font> <!--a href="ddb.do?CMD=xinaxi&userId=${ddb.userId}"-->
											<span class="STYLE77"><font color="">${ddb.tabSysusersinfo.loginId}</font>
										</span> </font>
									</td>
									<td align="center">
										<font color=""><span class="STYLE77">${ddb.tabSysusersinfo.password
												} </span> </font>
									</td>
									<td align="center">
										<font color=""><span class="STYLE77">${ddb.tabSysusersinfo.userName
												} </span> </font>
									</td>
									<td align="center">
										<font color=""><span class="STYLE77">${ddb.msid}</span>
										</font>
									</td>
									<td bgcolor="#F5FAFA" align="center">
										<font color="">
										<span class="STYLE77"> 
										<c:forEach	var="sname" items="${requestScope.dbinfo}">
													<c:if test="${sname.sIp==ddb.apmIp}">
													${sname.sName}
												</c:if>
											</c:forEach> 
											</span> </font>
									</td>
									<td bgcolor="#F5FAFA" align="center">
										<a href="ddb.do?CMD=ddb_to&userId=${ddb.userId}"><span
											class="STYLE17">修改</span> </a>
									</td>
								</tr>
							</c:forEach>
							<tr>
								<td align="right" colspan="10" class="list_footer">
								每页显示<%=request.getAttribute("pageSize")%>条&nbsp;&nbsp;
									共有${requestScope.msCount}条&nbsp;&nbsp;&nbsp;&nbsp;
									第${requestScope.currentPage}页&nbsp;
									共${requestScope.pageCount}页&nbsp;
									<a href="ddb.do?CMD=${requestScope.CMD}&pageNo=1"><img
											src="images/bt_first.gif" width="42" height="16" border="0" />
									</a>
									<a
										href="ddb.do?CMD=${requestScope.CMD}&pageNo=${requestScope.currentPage -1}"><img
											src="images/bt_pre.gif" width="38" height="16" border="0" />
									</a>
									<a
										href="ddb.do?CMD=${requestScope.CMD}&pageNo=${requestScope.currentPage +1}"><img
											src="images/bt_next.gif" width="41" height="16" border="0" />
									</a>
									<a
										href="ddb.do?CMD=${requestScope.CMD}&pageNo=${requestScope.pageCount}"><img
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
	
function gotoye(form){
 	 var page=form.gotopage.value;
	 if(!isNumber(page)){
     alert("输入跳转页数非法,请重新输入.(只能由数字组成)");
     return false;
     }
    form.action="ddb.do?CMD=<%=request.getParameter("CMD")%>&pageNo="+page;
	form.submit();   
}



//<!-- 装入上次查询用户类型查询、在线状态查询、用户有效状态查询的装态 ->
function goload() 
{ 
  var mstype=<%=request.getAttribute("mstype")%>;
  var msstatue=<%=request.getAttribute("msstatue")%>;
  var msflag=<%=request.getAttribute("msflag")%>;
  var ep="<%=request.getAttribute("ep")%>"; 
  var arrearage="<%=request.getAttribute("arrearage")%>";
  var pagentid=parseInt(<%=request.getAttribute("pagentid")%>);
  var childagentid=parseInt(<%=request.getAttribute("childagentid")%>);
   document.getElementById("pagentid").value=pagentid;
   document.getElementById("childagentid").value=childagentid;
   document.getElementById("mstype").value=mstype; 
   document.getElementById("msstatue").value=msstatue; 
   document.getElementById("msflag").value=msflag;
   document.getElementById("ep_id").value=ep; 
   document.getElementById("arrearage").value=arrearage;
  var mes="<%=request.getAttribute("message")%>";
  if(mes!="null"){
    alert(mes);
    document.location.href="ddb.do?CMD=ms_search&pagentid=<%=request.getAttribute("pagentid")%>&childagentid=<%=request.getAttribute("childagentid")%>&ep=<%=request.getAttribute("ep")%>";
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
  ddbUserForm.childagentid.options.length=2; 
  var parentagent=ddbUserForm.pagentid.value;
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
        var pagent=ddbUserForm.pagentid.value;
        var cagent=ddbUserForm.childagentid.value;
        ddbUserForm.ep.options.length=1;
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
        var pagent=ddbUserForm.pagentid.value;
        var cagent=ddbUserForm.childagentid.value;
        ddbUserForm.ep.options.length=1;
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