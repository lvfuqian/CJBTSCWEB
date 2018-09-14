<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ page import="com.elegps.tscweb.model.TbAgentInfo"%>
<%@ page import="com.elegps.tscweb.model.TbEnterpriseInfo"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.lang.String" %>
<%@ page import="java.net.URLEncoder" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
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
  <script type='text/javascript' src='dwr/interface/ChildAgent.js'></script>
  <script type='text/javascript' src='dwr/engine.js'></script>
  <script type='text/javascript' src='dwr/util.js'></script>
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

  </head>
  
  <body  onload="goload()">
   <form id="epListForm" name="epListForm" method="post">
 <td background="images/gw_06.gif">&nbsp;</td>
    <td width="84%" valign="top"><table width="100%" height="363" border="0" cellpadding="0" cellspacing="0"> <tr>
        <td height="5">&nbsp;</td>
        <td valign="top"><table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
          <tr>
            <td width="3%" height="30" align="center" background="images/3_04.gif"><img src="images/arrow3.gif" width="20" height="19"></td>
            <td width="45%" background="images/3_04.gif"><span class="STYLE4">您现在的位置：<span class="STYLE7">企业信息管理</span></span></td>
            <td width="52%"><img src="images/3_03.jpg" width="518" height="35"></td>
          </tr>
        </table> 
<table width="100%" border="0">
  <tr>
  <td width="8%" align="right">总部/一级代理商:</td>
    <td width="10%" align="left">
        <select name="pagentid" id="pagentid"  style="{width:100;margin:0;FONT-SIZE:9pt;FONT-WEIGHT:normal;}" onchange="parentagentchang()">
		<% 
       List parentagentlist =(List)request.getAttribute("Pagentlist");
       if(parentagentlist!=null){
      	for (int i= 0; i < parentagentlist.size(); i++) {
				 TbAgentInfo agentinfo=new TbAgentInfo();
				  agentinfo = (TbAgentInfo)parentagentlist.get(i);
				%>
	      <option value="<%=agentinfo.getAgent_Id()%>"><%=agentinfo.getAgent_Name()%></option>
		  <%} }%>
        </select>
    </td>
    <td width="8%" align="right">企业/二级代理商:</td>
    <td width="10%" align="left">
         <select name="childagentid" id="childagentid"  style="{width:128;margin:0;FONT-SIZE:9pt;FONT-WEIGHT:normal;}">
          <option value="-2">所有企业</option>
          <option value="-1">直属企业</option>
          	<% 
       List childagentlistid =(List)request.getAttribute("Cagentlist");
       if(childagentlistid!=null){
      	for (int i= 0; i < childagentlistid.size(); i++) {
				 TbAgentInfo agentinfo=new TbAgentInfo();
				  agentinfo = (TbAgentInfo)childagentlistid.get(i);
				%>
	      <option value="<%=agentinfo.getAgent_Id()%>"><%=agentinfo.getAgent_Name()%></option>
		  <%} }%>
         </select>
     </td>

	<td width="5%" align="right">名称:</td>
    <td width="10%" align="left">
      <input style="width:128px;" type="text" name="epname" value='${requestScope.epname}'/>
    </td> 
	<td width="2%" align="center"><span style="cursor:hand"><img src="images/bt_cx.gif" width="65" height="22"Onclick="ep_nameserach(document.forms[0])"/></span></td>
  </tr>
</table>
<br>



<table width="100%" height="21" border="1" bordercolor="#C1DAD7">
  <tr>
    <td width="5%" align="center" class="list_header">
     	<input class=btn type="button" name="selectall" value="全选"
							onClick="this.value=check(document.forms[0].list)">
    </td>
    <td width="20%" align="center" class="list_header"><span class="STYLE77">名称</span></td>
    <td width="10%" align="center"  class="list_header"><span class="STYLE77">联系人</span></td>
    <td width="25%" align="center" class="list_header"><span class="STYLE77">地址</span></td>
    <td width="10%" align="center" class="list_header"><span class="STYLE77">电话</span></td>
     <td width="10%" align="center" class="list_header"><span class="STYLE77">操作</span></td>
  </tr>
  
  <logic:present name="epList" scope="request" >
   <% 
   List list =(List)request.getAttribute("epList");
   if(list!=null){
   	for (int i= 0; i < list.size(); i++) {
				 TbEnterpriseInfo epinfo=new TbEnterpriseInfo();
				  epinfo = (TbEnterpriseInfo)list.get(i);
				%>
  					<tr>
					<td align="center">
						<input name="list" type="checkbox" value=<%=epinfo.getEp_Id()%>>
					</td>
					<td align="center" bgcolor="#F5FAFA">
					  <font><a href="ep.do?CMD=xiangxi&epid=<%=epinfo.getEp_Id()%>"><span class="STYLE77"><%=epinfo.getEp_Name()%></span></a>
						</font>
					</td>
					<td align="center">
						<font><span class="STYLE77"><%=epinfo.getEp_Man()%></span>
						</font>
					</td>
					<td bgcolor="#F5FAFA" align="center">
						<font><span class="STYLE77">
                                 <%=epinfo.getEp_Address()%>
                                 </span>
						</font>
					</td>
					<td align="center">
						<font><span class="STYLE77">
                                 <%=epinfo.getEp_Tel()%>
                          </span>
                          </font>       
                     </td>
                   <td align="center" bgcolor="#F5FAFA">
					  <font><a href="ep.do?CMD=modifyjsp&epid=<%=epinfo.getEp_Id()%>&type=1"><span class="STYLE77">修改</span></a>
						</font>
					</td>
                     </tr>
      <% } }%>
      
  
 <tr>
   <td align="right" colspan="7" class="list_footer">
         共有<%=request.getAttribute("epCount")%>条&nbsp;&nbsp;
		第<%=request.getAttribute("currentPage")%>页&nbsp;
		共<%=request.getAttribute("pageCount")%>页&nbsp;                                                                                                                                                                                    
		<a href="ep.do?CMD=<%=request.getAttribute("CMD")%>&pagentid=<%=request.getAttribute("pagentid")%>&childagentid=<%=request.getAttribute("childagentid")%>&epname=<%=URLEncoder.encode(request.getAttribute("epname").toString(),"UTF-8")%>&pageNo=1&type=1"><img src="images/bt_first.gif" width="42" height="16" border="0" align="absmiddle"></a>
		<a href="ep.do?CMD=<%=request.getAttribute("CMD")%>&pagentid=<%=request.getAttribute("pagentid")%>&childagentid=<%=request.getAttribute("childagentid")%>&epname=<%=URLEncoder.encode(request.getAttribute("epname").toString(),"UTF-8")%>&pageNo=<%=((Integer)request.getAttribute("currentPage")-1)%>&type=1"><img src="images/bt_pre.gif" width="38" height="16" border="0" align="absmiddle"></a>
		<a href="ep.do?CMD=<%=request.getAttribute("CMD")%>&pagentid=<%=request.getAttribute("pagentid")%>&childagentid=<%=request.getAttribute("childagentid")%>&epname=<%=URLEncoder.encode(request.getAttribute("epname").toString(),"UTF-8")%>&pageNo=<%=((Integer)request.getAttribute("currentPage")+1)%>&type=1"><img src="images/bt_next.gif" width="41" height="16" border="0" align="absmiddle"></a>
		<a href="ep.do?CMD=<%=request.getAttribute("CMD")%>&pagentid=<%=request.getAttribute("pagentid")%>&childagentid=<%=request.getAttribute("childagentid")%>&epname=<%=URLEncoder.encode(request.getAttribute("epname").toString(),"UTF-8")%>&pageNo=<%=request.getAttribute("pageCount")%>&type=1"><img src="images/bt_last.gif" width="42" height="16" border="0" align="absmiddle"></a>
		转到第<input type="text" size="2" name="gotopage" value='${requestScope.currentPage}'/>页<span style="cursor:hand"><img src="images/bt_go.gif" width="20" height="12" border="0" onClick="gotoye(document.forms[0])"/></span>			
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
function ep_nameserach(form){
    form.action="ep.do?CMD=ep_search&type=1";
	form.submit();
}


function gotoye(form){
    var page=this.epListForm.gotopage.value;
    if(!isNumber(page)){
     alert("输入跳转页数非法,请重新输入.(只能由数字组成)");
     return false;
     }
    form.action="ep.do?CMD=<%=request.getAttribute("CMD")%>&pagentid=<%=request.getAttribute("pagentid")%>&childagentid=<%=request.getAttribute("childagentid")%>&epname=<%=URLEncoder.encode(request.getAttribute("epname").toString(),"UTF-8")%>&type=1&pageNo="+page;
	form.submit();  
}


<!-- 装入上次查询代理商类型查询-->
function goload() 
{  
  var pagentid=parseInt(<%=request.getAttribute("pagentid")%>);
  var childagentid=parseInt(<%=request.getAttribute("childagentid")%>);
  document.getElementById("pagentid").value=pagentid;
  document.getElementById("childagentid").value=childagentid;
  var mes="<%=request.getAttribute("message")%>";
  if(mes!="null"){
    alert(mes);
    document.location.href="ep.do?CMD=ep_search&pagentid=<%=request.getAttribute("pagentid")%>&childagentid=<%=request.getAttribute("childagentid")%>&ep=<%=request.getAttribute("ep")%>&type=1";
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
  epListForm.childagentid.options.length=2; 
  var parentagent=epListForm.pagentid.value;
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
}


</script>
