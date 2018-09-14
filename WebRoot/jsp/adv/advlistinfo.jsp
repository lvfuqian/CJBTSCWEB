<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ page import="com.elegps.tscweb.model.TbAdvInfo"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.lang.String" %>
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
<script language="javascript" type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
		<script language="javascript" type="text/javascript" src="js/My97DatePicker/WdatePicker.js"></script>
  </head>
  
  <body  onload="goload()">
   <form id="form1" name="form1" method="post">
 <td background="images/gw_06.gif">&nbsp;</td>
    <td width="84%" valign="top"><table width="100%" height="363" border="0" cellpadding="0" cellspacing="0"> <tr>
        <td height="5">&nbsp;</td>
        <td valign="top"><table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
          <tr>
            <td width="3%" height="30" align="center" background="images/3_04.gif"><img src="images/arrow3.gif" width="20" height="19"></td>
            <td width="45%" background="images/3_04.gif"><span class="STYLE4">您现在的位置：<span class="STYLE7">广告管理</span></span></td>
            <td width="52%"><img src="images/3_03.jpg" width="518" height="35"></td>
          </tr>
        </table><br/>
        <table width="100%" border="0" >
  <tr>
	<td width="5%" align="right">标题:</td>
    <td width="10%" align="left">
      <input style="width:128px;" type="text" id="advTitle" name="advTitle" value=''/>
    </td> 
    <td width="5%" align="right">广告链接:</td>
    <td width="10%" align="left">
    <input style="width:128px;" type="text" id="advUrl" name="advUrl" value=''/>
    </td>
    <td width="5%" align="right">推送时间:</td>
    <td width="30%" align="left">
      <input id="sendSTime" name="sendSTime" type="text" class="Wdate" onFocus="WdatePicker({lang:'zh-cn',dateFmt:'yyyy-MM-dd',readOnly:'true'})" size="17" value=''/>
	--<input id="sendETime"  name="sendETime" type="text" class="Wdate" onFocus="WdatePicker({lang:'zh-cn',dateFmt:'yyyy-MM-dd',readOnly:'true'})" size="17" value=''/> 
    </td> 
    <td width="5%" align="right">创建时间:</td>
    <td width="30%" align="left" colspan="3">
      <input id="creatSTime"  name="creatSTime" type="text" class="Wdate" onFocus="WdatePicker({lang:'zh-cn',dateFmt:'yyyy-MM-dd',readOnly:'true'})" size="17" value=''/>
	--<input id="creatETime"  name="creatETime" type="text" class="Wdate" onFocus="WdatePicker({lang:'zh-cn',dateFmt:'yyyy-MM-dd',readOnly:'true'})" size="17" value=''/> 
    </td> 
  </tr>
  <tr><td><br/></td></tr>
<tr>
	<td colspan="7"></td>
  <td align="center"><span style="cursor:hand"><img src="images/bt_cx.gif" width="65" height="22"Onclick="advsearch(document.forms[0])"/></span></td>
 	<td align="center"><span style="cursor:hand">
    <img src="images/bt_add.gif" width="65" height="22" Onclick="advadd()"></span></td>
	<td align="center"><span style="cursor:hand">
	<img src="images/bt_del.gif" width="65" height="22"  Onclick="onSelect(document.forms[0])">
	</span></td>
</tr>
</table>
<br>
  <%
  		int rid =Integer.parseInt( request.getSession().getAttribute("roleId").toString());
   %>

<table width="100%" height="21" border="1" bordercolor="#C1DAD7">
  <tr>
  <%
		if (rid == 1 || rid == 35){//管理员级别以上的功能
   %>
    <td width="2%" align="center" class="list_header">
     	<input class=btn type="button" name="selectall" value="全选"
							onClick="this.value=check(document.forms[0].list)">
    </td>
    <%} %>
    <td width="20%" align="center" class="list_header"><span class="STYLE77">标题</span></td>
    <td width="18%" align="center" class="list_header"><span class="STYLE77">广告链接</span></td>
    <td width="25%" align="center" class="list_header"><span class="STYLE77">推送时间</span></td>
    <td width="25%" align="center" class="list_header"><span class="STYLE77">创建时间</span></td>
	<td width="15%" align="center" class="list_header"><span class="STYLE77">操作</span></td>
  </tr>
  
  <logic:present name="advList" scope="request" >
  
   <% 
   List<TbAdvInfo> list =(List<TbAdvInfo>)request.getAttribute("advList");
   if(list!=null){
  	 for (int i= 0; i < list.size(); i++) {
  	 			TbAdvInfo adv = new TbAdvInfo();
  	 			adv=list.get(i);
				
				%>
  					<tr>
  					 <%
						if (rid == 1 || rid == 35){//管理员级别以上的功能
   					%>
					<td align="center">
						<input id="list1" name="list" type="checkbox" value=<%=adv.getAdvId()%>>
					</td>
					<%} %>
					<td bgcolor="#F5FAFA">
						<font><span class="STYLE77"><%=adv.getAdvTitle()%></span>
						</font>
					</td>
					<td align="center">
						<font><span class="STYLE77"><%=adv.getAdvUrl()%></span>
						</font>
					</td>
					<td bgcolor="#F5FAFA" align="center">
						<font><span class="STYLE77" ><%=adv.getSendSTime()%>--<%=adv.getSendETime()%></span>
						</font>
					</td>
					<td align="center">
						<font><span class="STYLE77" ><%=adv.getCreatTime()%></span>
						</font>
					</td>
					 <%
						if (rid == 1 || rid == 35){//管理员级别以上的功能
   					%>
                     <td align="center" bgcolor="#F5FAFA">
                     	 <a href="adv.do?CMD=advshowjsp&advId=<%=adv.getAdvId()%>&type=<%=adv.getAdvType()%>"><span class="STYLE17">查看</span></a>&nbsp;&nbsp;|&nbsp;
                         <a href="adv.do?CMD=advupdatejsp&advId=<%=adv.getAdvId()%>"><span class="STYLE17">修改</span></a>
                     </td>
                     <%} %>
                     </tr>
      <% } }%>
      
  
 <tr>
    <td align="right" colspan="6" class="list_footer">
    每页显示
										 <select name="pageSize" id="pageSize"  style="{width:30;margin:0;FONT-SIZE:9pt;FONT-WEIGHT:normal;}">
										 <option value="10">10</option>
										 <option value="15">15</option>
										 <option value="20">20</option>
										 <option value="25">25</option>
										 <option value="30">30</option>
										 </select>
										 条&nbsp;&nbsp;
											共有<%=request.getAttribute("advCount")%>条&nbsp;&nbsp; &nbsp;&nbsp; 
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
 	
  </td>                                                                                      
  </tr>
  </logic:present>
</table>
 </form>
  </body>
</html>

<script language="javascript">

function pageclick(page){
    form1.action="adv.do?CMD=advlist&pageNo="+page;
	form1.submit();
}
function gotoye(form){
    var page=this.form1.gotopage.value;
    if(!isNumber(page)){
     alert("输入跳转页数非法,请重新输入.(只能由数字组成)");
     return false;
     }
    form.action="adv.do?CMD=<%=request.getAttribute("CMD")%>&pageNo="+page;
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
		form.action="adv.do?CMD=advdelete";
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


function advadd(){
  document.location.href="adv.do?CMD=advaddjsp";
}
function advsearch(from){
   from.action="adv.do?CMD=advlist";
   from.submit();
}

<!-- 装入上次查询用户类型查询、在线状态查询、用户有效状态查询的装态 ->
function goload() 
{ 

	var title = "<%=request.getAttribute("advTitle")%>";
	document.getElementById("advTitle").value=title;
	var advUrl = "<%=request.getAttribute("advUrl")%>";
	document.getElementById("advUrl").value=advUrl;
	var sendSTime = "<%=request.getAttribute("sendSTime")%>";
	document.getElementById("sendSTime").value=sendSTime;
	var sendETime = "<%=request.getAttribute("sendETime")%>";
	document.getElementById("sendETime").value=sendETime;
	var creatSTime = "<%=request.getAttribute("creatSTime")%>";
	document.getElementById("creatSTime").value=creatSTime;
	var creatETime = "<%=request.getAttribute("creatETime")%>";
	document.getElementById("creatETime").value=creatETime;
	var psize = <%=request.getAttribute("pageSize")%>;
  document.getElementById("pageSize").value=psize;
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
