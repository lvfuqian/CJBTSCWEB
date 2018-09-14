<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
<style type="text/css">
.btn {
BORDER-RIGHT: #7b9ebd 1px solid; 
PADDING-RIGHT: 2px; 
BORDER-TOP: #7b9ebd 1px solid; 
PADDING-LEFT: 2px; 
FONT-SIZE: 12px; 
FILTER: progid:DXImageTransform.Microsoft.Gradient(GradientType=0, StartColorStr=#ffffff, EndColorStr=#cecfde); 
BORDER-LEFT: #7b9ebd 1px solid; CURSOR: hand; COLOR: black; PADDING-TOP: 2px; BORDER-BOTTOM: #7b9ebd 1px solid
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
.STYLE8 {font-size: 12px}
a:hover {
	font-size: 12px;
	color: #CC00FF;
	text-decoration: underline;
}
-->
</style> 
<link href="css/manage.css" rel="stylesheet" type="text/css"/>
<link href="css/list.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="js/calendar.js"></script>
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
  
  <body  onload="goload()">
   <form id="form1" name="form1" method="post">
 <td background="images/gw_06.gif">&nbsp;</td>
    <td width="84%" valign="top"><table width="100%" height="363" border="0" cellpadding="0" cellspacing="0"> <tr>
        <td height="5">&nbsp;</td>
        <td valign="top"><table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
          <tr>
            <td width="3%" height="30" align="center" background="images/3_04.gif"><img src="images/arrow3.gif" width="20" height="19"></td>
            <td width="45%" background="images/3_04.gif"><span class="STYLE4">您现在的位置：<span class="STYLE7">用户操作记录</span></span></td>
            <td width="52%"><img src="images/3_03.jpg" width="518" height="35"></td>
          </tr>
        </table>
<table width="100%" border="0" >
	<tr>
    <td align="right">用户名称:</td>
    <td>
    <select name="userId" id="userId" style="width: 128px;">
    	<option value="">全部</option>
    	<c:forEach var="tbUserInfo" items="${requestScope.userList }">
    		<option value="${tbUserInfo.userId }">${tbUserInfo.userName }</option>
    	</c:forEach>
    </select>
    <script type="text/javascript">
    	document.all.userId.value=${requestScope.userId};
    </script>
    </td>
    <td align="right">操作类型:</td>
    <td>
    <select name="lType" id="lType" style="width: 128px;">
    	<option value="">全部</option>
    	<option value="1">添加终端ID</option>
    	<option value="2">修改终端ID</option>
    	<option value="3">删除终端ID</option>
    	<option value="4">添加群组ID</option>
    	<option value="5">修改群组ID</option>
    	<option value="6">删除群组ID</option>
    	<option value="7">添加群组终端关系</option>
    	<option value="8">取消群组终端关系</option>
    	<option value="9">添加GPS账号</option>
    	<option value="10">修改GPS账号</option>
    	<option value="11">删除GPS账号</option>
    	<option value="12">添加GPS终端关系</option>
    	<option value="13">取消GPS终端关系</option>
    	<option value="14">添加调度账号</option>
    	<option value="15">修改调度账号</option>
    	<option value="16">删除调度账号</option>
    	<option value="17">代理商管理</option>
    	<option value="18">企业管理</option>
    	<option value="19">客户问题管理</option>
    	<option value="0">其他</option>
    </select>
        <script type="text/javascript">
    	document.all.lType.value=${requestScope.lType};
    </script>
    </td>
    <td align="right">操作时间:</td>
    <td><input type="text" name="startDate" id="startDate" onclick="SelectDate(this)" value="${requestScope.startDate }"/>至<input type="text" name="endDate" id="endDate"  onclick="SelectDate(this)"  value="${requestScope.endDate }" /></td>
	<td width="2%" align="left"><span style="cursor:hand"><img src="images/bt_cx.gif" width="65" height="22" Onclick="user_serach(document.forms[0])"></span></td>
  </tr>
</table>
<br>

<table width="100%" height="21" border="1" bordercolor="#C1DAD7">
  <tr>
    <td width="3%" align="center" class="list_header"><span class="STYLE77">编号</span></td>
    <td width="8%" align="center" class="list_header"><span class="STYLE77">用户名称</span></td>
    <td width="12%" align="center" class="list_header"><span class="STYLE77">操作时间</span></td>
    <td width="10%" align="center" class="list_header"><span class="STYLE77">物理IP</span></td>
    <td width="12%" align="center" class="list_header"><span class="STYLE77">操作类型</span></td>
	<td width="54%" align="center" class="list_header"><span class="STYLE77">具体操作内容</span></td>
  </tr>
			<c:forEach items="${requestScope.logList }" var="logInfo" varStatus="status">
  					<tr  bgcolor="#F5FAFA">
					<td align="left">
						${(requestScope.currentPage-1)*requestScope.pageSize+status.count}
					</td>
					<td align="center">
						<font><span class="STYLE77">${logInfo.userName }</span>
						</font>
					</td>
					<td>
						<font><span class="STYLE77">${logInfo.lDate }</span>
						</font>
					</td>
					<td>
						<font><span class="STYLE77" >${logInfo.lAddress }</span>
						</font>
					</td>
					<td>
						<font><span class="STYLE77">${logInfo.typeName }</span>
						</font>
					</td>
                     <td>
                       <font><span class="STYLE77">${logInfo.lContent }</span></font>
                     </td>
                     </tr>
 		</c:forEach>
      
  
 <tr>
    <td align="right" colspan="6" class="list_footer">
 
         共有<%=request.getAttribute("userCount")%>条&nbsp;&nbsp;
		第<%=request.getAttribute("currentPage")%>页&nbsp;
		共<%=request.getAttribute("pageCount")%>页&nbsp;   
		<a href="user.do?CMD=<%=request.getAttribute("CMD")%>&userId=${requestScope.userId}&lType=${requestScope.lType }&startDate=${requestScope.startDate }&endDate=${requestScope.endDate }&pageNo=1"><img src="images/bt_first.gif" width="42" height="16" border="0" align="absmiddle"></a>
		<a href="user.do?CMD=<%=request.getAttribute("CMD")%>&userId=${requestScope.userId}&lType=${requestScope.lType }&startDate=${requestScope.startDate }&endDate=${requestScope.endDate }&pageNo=<%=((Integer)request.getAttribute("currentPage")-1)%>"><img src="images/bt_pre.gif" width="38" height="16" border="0" align="absmiddle"></a>
		<a href="user.do?CMD=<%=request.getAttribute("CMD")%>&userId=${requestScope.userId}&lType=${requestScope.lType }&startDate=${requestScope.startDate }&endDate=${requestScope.endDate }&pageNo=<%=((Integer)request.getAttribute("currentPage")+1)%>"><img src="images/bt_next.gif" width="41" height="16" border="0" align="absmiddle"></a>
		<a href="user.do?CMD=<%=request.getAttribute("CMD")%>&userId=${requestScope.userId}&lType=${requestScope.lType }&startDate=${requestScope.startDate }&endDate=${requestScope.endDate }&pageNo=<%=request.getAttribute("pageCount")%>"><img src="images/bt_last.gif" width="42" height="16" border="0" align="absmiddle"></a>
		转到第<input type="text" size="2" name="gotopage" value='${requestScope.currentPage}'/>页<span style="cursor:hand"><img src="images/bt_go.gif" width="20" height="12" border="0" onClick="gotoye(document.forms[0])"/></span>		
  </td>                                                                                      
  </tr>
</table>
  
  </body>
</html>

<script language="javascript">


<!--模糊查询按键  -->
function user_serach(form){
    form.action="user.do?CMD=userLog";
	form.submit();
}

function gotoye(form){
 var page=this.form1.gotopage.value;
 if(!isNumber(page)){
     alert("输入跳转页数非法,请重新输入.(只能由数字组成)");
     return false;
     }
  form.action="user.do?CMD=<%=request.getAttribute("CMD")%>&userId=${requestScope.userId}&lType=${requestScope.lType }&startDate=${requestScope.startDate }&endDate=${requestScope.endDate }&pageNo="+page;
  form.submit();   
}



<!-- 装入上次查询用户类型查询、在线状态查询、用户有效状态查询的装态 ->
function goload() 
{ 
  var mes="<%=request.getAttribute("message")%>";
  if(mes!="null"){
    alert(mes);
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
