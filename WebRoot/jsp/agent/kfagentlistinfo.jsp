<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ page import="com.elegps.tscweb.model.TbAgentInfo"%>
<%@ page import="java.util.List"%>
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
   <form id="form1" name="form1" method="post"  >
 <td background="images/gw_06.gif">&nbsp;</td>
    <td width="84%" valign="top"><table width="100%" height="363" border="0" cellpadding="0" cellspacing="0"> <tr>
        <td height="5">&nbsp;</td>
        <td valign="top"><table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
          <tr>
            <td width="3%" height="30" align="center" background="images/3_04.gif"><img src="images/arrow3.gif" width="20" height="19"></td>
            <td width="45%" background="images/3_04.gif"><span class="STYLE4">您现在的位置：<span class="STYLE7">代理商管理</span></span></td>
            <td width="52%"><img src="images/3_03.jpg" width="518" height="35"></td>
          </tr>
        </table> 
<table width="100%" border="0">
  <tr>
  <td width="3%" align="right">一级代理商:</td>
     <td width="13%" align="left">
        <select name="agent_type" id="agent_type"  style="{width:128;margin:0;FONT-SIZE:9pt;FONT-WEIGHT:normal;}">
		 <%
         List parentagent=(List)request.getAttribute("pagentlist");
        if(parentagent!=null){
           for(int i=0;i<parentagent.size();i++){
           TbAgentInfo ainfo=new TbAgentInfo();
           ainfo=(TbAgentInfo)parentagent.get(i);
        %>
        <option value="<%=ainfo.getAgent_Id()%>"><%=ainfo.getAgent_Name() %></option>
        <% }
        }%>
        </select>
    </td>
	<td width="2%" align="left">名称:</td>
    <td width="10%" align="left">
       <input style="width:128px;" type="text" name="agent_name" value='${requestScope.agent_name}'/>
    </td> 
	<td width="2%" align="center"><span style="cursor:hand"><img src="images/bt_cx.gif" width="65" height="22" Onclick="agent_nameserach(document.forms[0])"/></span></td>
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
   <!-- <td width="10%" align="center" class="list_header"><span class="STYLE77">父级代理商</span></td> -->
	<td width="15%" align="center" class="list_header" ><span class="STYLE77">类型</span></td>
	<td width="15%" align="center" class="list_header" ><span class="STYLE77">操作</span></td>
  </tr>
  
  <logic:present name="agentList" scope="request" >
   <% 
   List list =(List)request.getAttribute("agentList");
   if(list!=null){
   	for (int i= 0; i < list.size(); i++) {
				 TbAgentInfo tbagentinfo=new TbAgentInfo();
				  tbagentinfo = (TbAgentInfo)list.get(i);
				%>
  					<tr>
					<td align="center">
						<input name="list" type="checkbox" value="<%=tbagentinfo.getAgent_Id()%>">
					</td>
					<td align="center" bgcolor="#F5FAFA">
					  <font><a href="agent.do?CMD=xinaxi&agentid=<%=tbagentinfo.getAgent_Id()%>"><span class="STYLE77"><%=tbagentinfo.getAgent_Name()%></span></a>
						</font>
					</td>
					<td align="center">
						<font><span class="STYLE77"><%=tbagentinfo.getAgent_Man()%></span>
						</font>
					</td>
					<td bgcolor="#F5FAFA" align="center">
						<font><span class="STYLE77">
                                 <%=tbagentinfo.getAgent_Address()%>
                                 </span>
						</font>
					</td>
					<td align="center">
						<font><span class="STYLE77">
                                 <%=tbagentinfo.getAgent_Tel()%>
                          </span>
                          </font>       
                     </td>
                     <!--  <td>
						<font><span class="STYLE77">
					
                                 
                          </span>
                          </font>       
                     </td>-->
                     	<td bgcolor="#F5FAFA" align="center">
						<font><span class="STYLE77">
                            <%
                              String agenttype;
                              if(tbagentinfo.getAgent_PId()==0)
                              {
                                agenttype="一级代理商";
                              }else{
                                agenttype="二级代理商";
                              }
                            %>
                            <%=agenttype %>
                          </span>
                          </font>       
                     </td>
                     <td align="center"><a href="agent.do?CMD=beforeupdate&Aid=<%=tbagentinfo.getAgent_Id() %>&type=1">修改</a></td>
                     </tr>
      <% } }%>
      
  
 <tr>
   <td align="right" colspan="7" class="list_footer">
         共有<%=request.getAttribute("agentCount")%>条&nbsp;&nbsp;
		第<%=request.getAttribute("currentPage")%>页&nbsp;
		共<%=request.getAttribute("pageCount")%>页&nbsp;                                                                                                                                                                                    
		<a href="agent.do?CMD=<%=request.getAttribute("CMD")%>&agent_name=<%=URLEncoder.encode(request.getAttribute("agent_name").toString(),"UTF-8")%>&agent_type=<%=request.getAttribute("agent_type")%>&pageNo=1&type=1"><img src="images/bt_first.gif" width="42" height="16" border="0" align="absmiddle"></a>
		<a href="agent.do?CMD=<%=request.getAttribute("CMD")%>&agent_name=<%=URLEncoder.encode(request.getAttribute("agent_name").toString(),"UTF-8")%>&agent_type=<%=request.getAttribute("agent_type")%>&pageNo=<%=((Integer)request.getAttribute("currentPage")-1)%>&type=1"><img src="images/bt_pre.gif" width="38" height="16" border="0" align="absmiddle"></a>
		<a href="agent.do?CMD=<%=request.getAttribute("CMD")%>&agent_name=<%=URLEncoder.encode(request.getAttribute("agent_name").toString(),"UTF-8")%>&agent_type=<%=request.getAttribute("agent_type")%>&pageNo=<%=((Integer)request.getAttribute("currentPage")+1)%>&type=1"><img src="images/bt_next.gif" width="41" height="16" border="0" align="absmiddle"></a>
		<a href="agent.do?CMD=<%=request.getAttribute("CMD")%>&agent_name=<%=URLEncoder.encode(request.getAttribute("agent_name").toString(),"UTF-8")%>&agent_type=<%=request.getAttribute("agent_type")%>&pageNo=<%=request.getAttribute("pageCount")%>&type=1"><img src="images/bt_last.gif" width="42" height="16" border="0" align="absmiddle"></a>
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
function agent_nameserach(form){
    form.action="agent.do?CMD=argment_search&type=1"; 
	form.submit();
}



function gotoye(form){
    var page=this.form1.gotopage.value;
    if(!isNumber(page)){
     alert("输入跳转页数非法,请重新输入.(只能由数字组成)");
     return false;
     }
    form.action="agent.do?CMD=<%=request.getAttribute("CMD")%>&agent_name=<%=URLEncoder.encode(request.getAttribute("agent_name").toString(),"UTF-8")%>&agent_type=<%=request.getAttribute("agent_type")%>&type=1&pageNo="+page;
	form.submit();  
}


<!-- 装入上次查询代理商类型查询-->
function goload() 
{ 
  var agenttype=<%=request.getAttribute("agent_type")%>;
   document.getElementById("agent_type").value=agenttype; 
   var mes="<%=request.getAttribute("message")%>";
  if(mes!="null"){
    alert(mes);
    document.location.href="agent.do?CMD=argment_search&type=1";
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
