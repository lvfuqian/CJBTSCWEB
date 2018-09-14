<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@ page import="com.elegps.tscweb.model.TbMsInfo"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.lang.String"%>
<%@ page import="java.net.URLEncoder"%>
<%@ page import="java.text.SimpleDateFormat"%>
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

	</head>
	<body onload="goload()">
		<form id="MSSerachForm" name="MSSerachForm" method="post">
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
										<span class="STYLE4">您现在的位置：<span class="STYLE7">终端用户</span>
										</span>
									</td>
									<td width="52%">
										<img src="images/3_03.jpg" width="518" height="35">
									</td>
								</tr>
							</table>
<table width="100%" height="20" border="0" align="right">
  <tr>
    <td  colspan="8"><table width="100%" border="0">
      <tr>
        <td width="12%" align="right">总部/一级代理商:</td>
        <td td width="12%" align="left"> <select name="pagentid" id="pagentid"  style="width:100;margin:0;FONT-SIZE:9pt;FONT-WEIGHT:normal;" onchange="parentagentchang()">
		<% 
         List parentagentlist =(List)request.getAttribute("Pagentlist");
         if(parentagentlist!=null){
        	for (int i= 0; i < parentagentlist.size(); i++) {
				 TbAgentInfo agentinfo=new TbAgentInfo();
				  agentinfo = (TbAgentInfo)parentagentlist.get(i);
				%>
	      <option value="<%=agentinfo.getAgent_Id()%>"><%=agentinfo.getAgent_Name()%></option>
		  <%}
		   }%>
        </select>
        <td  width="12%" align="right">直属企业/二级代理商:</td>
        <td  width="10%" align="left">
         <select name="childagentid" id="childagentid"  style="{width:128;margin:0;FONT-SIZE:9pt;FONT-WEIGHT:normal;}" onchange="childagentchang()">
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
        <td width="7%" align="right">
			<div align="right">企业名称:</div>
		</td>
        <td width="12%" align="left">
										<div align="left">
											<select name="ep" id="ep_id" style="width: 128; margin: 0; FONT-SIZE: 9pt; FONT-WEIGHT: normal;">
												<option value="-1">全部企业</option>
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
		<div align="right">用户类型:</div>	
	</td>
    <td width="12%" align="left">
			<div align="left">
				<select name="user_type" id="mstype"
					style="width: 80; margin: 0; FONT-SIZE: 9pt; FONT-WEIGHT: normal;">
					<option value="3">全部类型</option>
					<option value="0">终端用户</option>
					<option value="1">群组管理</option>
					<option value="2">调度用户</option>
					<option  value="5">下呼调度</option>
					<option value="4">免费用户</option>
				</select>
			</div>
	</td>
  </tr>
    </table>
    </td>
  </tr>
  <tr>
    <td width="10%" align="right">
			<div align="right">在线状态:</div>	
	</td>
    <td width="12%" align="left">
				<div align="left">
					<select name="statue" id="msstatue"
						style="width: 70; margin: 0; FONT-SIZE: 9pt; FONT-WEIGHT: normal;">
						<option value="3">全部</option>
						<option value="0">离线</option>
						<option value="1">在线</option>
						<option value="2">关机</option>
					</select>
				</div>
	</td>
    <td width="10%" align="right">
				<div align="right">用户状态:</div>									
	</td>
    <td width="12%" align="left">
				<div align="left">
					<select name="flag" id="msflag"
						style="width: 70; margin: 0; FONT-SIZE: 9pt; FONT-WEIGHT: normal;">
						<option value="2">全部</option>
						<option value="0">冻结</option>
						<option value="1">正常</option>
					</select>
				</div>
		</td>
    <td width="10%" align="right">
				<div align="right">终端类型:</div>
	</td>
    <td width="12%" align="left">
	       <select name="c04" id="c04" style="width: 70;">
                   <option value="0">全部</option>
                   <option  value="1">A18</option>   
		 <option  value="2">A380</option>
		 <option  value="3">A777</option>
		 <option  value="4">H1000</option>
		 <option  value="5">S40</option>
		 <option  value="6">S60</option>
		 <option  value="7">S50</option>
		 <option  value="8">T20</option>
		 <option  value="9">T25</option>
		 <option  value="10">G10</option>
		 <option  value="11">K880</option>
		 <option  value="12">其他</option>
            </select>
	</td>
	 <td width="10%" align="right">
				<div align="right">电话模式:</div>
	</td>
    <td width="12%" align="left">
	       <select name="c03" id="c03" style="width: 70;">
                   <option value="2">全部</option>
                   <option value="0">禁止</option>
                  <option value="1"> 允许</option>
            </select>
	</td>
  </tr>
  <tr>
    <td width="10%" align="right">
			<div align="right">终端号码:</div>	
   </td>
    <td  align="left"  width="12%">
			<div align="left">
				<input name="msid" type="text" style="width: 128px;"
				value='${requestScope.ms_id}' size="21" maxlength="21" />
		    </div>									
	</td>
    <td align="right" width="10%">
				<div align="right">终端别名:</div>	
	</td>
    <td align="left">
			<div align="left">
				<input style="width: 128px;" type="text" name="msname"
					value='${requestScope.ms_name}' size="21" maxlength="60" />
			</div>									
	</td>
    <td colspan="4" align="center" width="40%">
                                        <div>
											<span style="cursor: hand"><img src="images/bt_cx.gif" alt="查询"
													width="65" height="22"
													onclick="msidserach(document.forms[0])" /></span>
										    </div>
	    </td>
  </tr>
</table>
<br>
<br>
<br>
<br>
<br>
<br>

							<table width="100%" height="21" border="1" bordercolor="#C1DAD7">
								<tr height="12">
									<td width="3%" height="12" align="center" class="list_header">
										<input class=btn type="button" name="selectall" value="全选"
											onClick="this.value=check(document.forms[0].list)">
									</td>
									<td width="14%" class="list_header" height="12">
										<span class="STYLE77">终端号码</span>
									</td>
									<td width="12%" class="list_header" height="12">
										<span class="STYLE77">终端别名</span>
									</td>
									<td width="7%" class="list_header" height="12">
										<span class="STYLE77">终端类型</span>
									</td>
									<td width="6%" class="list_header" height="12" align="center">
										<span class="STYLE77">强插级别</span>
									</td>
									<td width="6%" class="list_header" height="12" align="center">
										<span class="STYLE77">在线状态</span>
									</td>
									<td width="6%" class="list_header" height="12" align="center">
										<span class="STYLE77">用户状态</span>
									</td>
									<td width="7%" class="list_header" height="12" align="center">
										<span class="STYLE77">终端类型</span>
									</td>
									<td width="7%" class="list_header" height="12" align="center">
										<span class="STYLE77">切换电话模式</span>
									</td>
									<td width="8%" class="list_header" height="12" align="center">
										<span class="STYLE77">GPS提交时间</span>
									</td>
									<td width="24%" class="list_header" height="12" align="center">
										<span class="STYLE77">操作</span>
									</td>
								</tr>

								<logic:present name="msList" scope="request">
								
									<%
										String mstype = "";
										String OnlineStatus = "";
										String flagstatus = "";
										String fontcolor="black";
										String is_Arrearage="";
										String is_mobile="";
										SimpleDateFormat dateFormat = new SimpleDateFormat(
												"yyyy-MM-dd HH:mm:ss");
										List list = (List) request.getAttribute("msList");
										long time=0;
										if (list != null) {
											for (int i = 0; i < list.size(); i++) {
												TbMsInfo tbmsinfo = new TbMsInfo();
												tbmsinfo = (TbMsInfo) list.get(i);
												if (tbmsinfo.getLastPostGps_time() != null){
												 time=(new Date().getTime()-tbmsinfo.getLastPostGps_time().getTime())/(1000*24);
												}
												if(tbmsinfo.getOnlineStatus()==1&&time>15){
												  fontcolor="red";
												}else{
												  fontcolor="#000066";
												}
									%>
									<tr>
										<td align="center">
											<input id="list1" name="list" type="checkbox"
												value=<%=tbmsinfo.getMsId()%>>
										</td>
										<td bgcolor="#F5FAFA" align="center">
											<font><a
												href="ms.do?CMD=xinaxi&msid=<%=tbmsinfo.getMsId()%>"><span
													class="STYLE77"><font color="<%=fontcolor%>"><%=tbmsinfo.getMsId()%></font></span>
											</a> </font>
										</td>
										<td align="center">
											<font color="<%=fontcolor%>"><span class="STYLE77"><%=tbmsinfo.getMsName()%></span>
											</font>
										</td>
										<td bgcolor="#F5FAFA" align="center">
											<font color="<%=fontcolor%>"><span class="STYLE77"> <%
 											if (tbmsinfo.getMsType() == 0) {
 												mstype = "一般终端用户";
 											} else if (tbmsinfo.getMsType() == 1) {
 												mstype = "群组管理用户";
 											} else if(tbmsinfo.getMsType() == 2){
 												mstype = "调度用户";
 											}else if(tbmsinfo.getMsType() == 5){
 												mstype = "下呼调度";
 											}else{
 											   mstype = "免费用户";
 											}
 											%> <%=mstype%> </span> </font>
										</td>
										<td  align="center">
											<font color="<%=fontcolor%>"><span class="STYLE77"> <%
 										String zdjb = null;
 										if (tbmsinfo.getMsLevel() == 0) {
 											zdjb = "无强插";
 										} else if (tbmsinfo.getMsLevel() == 1) {
 										    zdjb = "低强插";
 										} else if (tbmsinfo.getMsLevel() == 2) {
 										    zdjb = "中强插";
 										} else {
 										    zdjb = "高强插";
 										}
 		
 										%> <%=zdjb%> </span> </font>
										</td>
										<td bgcolor="#F5FAFA"  align="center">
											<font color="<%=fontcolor%>"><span class="STYLE77"> <%
 										if (tbmsinfo.getOnlineStatus() == 0) {
 											OnlineStatus = "离线";
 										} else if (tbmsinfo.getOnlineStatus() == 1) {
 											OnlineStatus = "在线";
 										} else {
										 	OnlineStatus = "关机";
										}
										%> <%=OnlineStatus%> </span> </font>
										</td>
										<td  align="center">
											<font color="<%=fontcolor%>"><span class="STYLE77"> <%
										 		if (tbmsinfo.getFlag() == 0) {
										 		flagstatus = "冻结";
										 			} else {
										 		flagstatus = "正常";
										 			}
										       %> <%=flagstatus%> </span> </font>
										</td>
										<td bgcolor="#F5FAFA"  align="center">
										<font color="<%=fontcolor%>"><span class="STYLE77"> 
									 <%
	     String ms_category="";
	   if(tbmsinfo.getMsCategory()!=null){
	     if(tbmsinfo.getNetWorkType()==null){
	     	tbmsinfo.setNetWorkType("");
	     }
	     if(tbmsinfo.getMsCategory().equals("0")){
	     	ms_category = "V8"+tbmsinfo.getNetWorkType();
	     }else if(tbmsinfo.getMsCategory().equals("1")){
	     	ms_category = "A18"+tbmsinfo.getNetWorkType();
	     }
	    else if(tbmsinfo.getMsCategory().equals("2")){
	     	ms_category = "A380"+tbmsinfo.getNetWorkType();
	     }
	     else if(tbmsinfo.getMsCategory().equals("3")){
	     	ms_category = "A777"+tbmsinfo.getNetWorkType();
	     }
	     else if(tbmsinfo.getMsCategory().equals("4")){
	     	ms_category = "H1000"+tbmsinfo.getNetWorkType();
	     }
	     else if(tbmsinfo.getMsCategory().equals("5")){
	     	ms_category = "S40"+tbmsinfo.getNetWorkType();
	     }
	     else if(tbmsinfo.getMsCategory().equals("6")){
	     	ms_category = "S60"+tbmsinfo.getNetWorkType();
	     }
	     else if(tbmsinfo.getMsCategory().equals("7")){
	     	ms_category = "S50"+tbmsinfo.getNetWorkType();
	     }
	     else if(tbmsinfo.getMsCategory().equals("8")){
	     	ms_category = "T20"+tbmsinfo.getNetWorkType();
	     }
	     else if(tbmsinfo.getMsCategory().equals("9")){
	     	ms_category = "T25"+tbmsinfo.getNetWorkType();
	     }
	     else if(tbmsinfo.getMsCategory().equals("10")){
	     	ms_category = "G10"+tbmsinfo.getNetWorkType();
	     }
	     else if(tbmsinfo.getMsCategory().equals("11")){
	     	ms_category = "K880"+tbmsinfo.getNetWorkType();
	     }else if(tbmsinfo.getMsCategory().equals("12")){
	     	ms_category = "其他"+tbmsinfo.getNetWorkType();
	     }else{
	     	//tbmsinfo.setMsCategory("-1");
	     	ms_category ="";
	     }
	     }
	      %> <%=ms_category%></span> </font>
										</td>
										<td bgcolor="#F5FAFA"  align="center">
										<font color="<%=fontcolor%>"><span class="STYLE77"> 
									 <%
 										if (tbmsinfo.getC03()=="0"||"0".equals(tbmsinfo.getC03())) {
 											is_mobile="禁止";
 										}else if(tbmsinfo.getC03()=="1"||"1".equals(tbmsinfo.getC03())){ 
										 	is_mobile="允许";
										} 
										%> <%=is_mobile%></span> </font>
										</td>
										<td  align="center">
											<font color="<%=fontcolor%>"><span class="STYLE77"> <%
											 		if (tbmsinfo.getLastPostGps_time() == null) {
											 		out.print("");
											 			} else {
											 		out.print(dateFormat.format(tbmsinfo
											 				.getLastPostGps_time()));
											 			}
											        %> </span> </font>
										</td>
										<td bgcolor="#F5FAFA" align="center">
											<a href="ms.do?CMD=tomsmdoyijsp&msid=<%=tbmsinfo.getMsId()%>"><span
												class="STYLE17">修改</span>
											</a>&nbsp;|&nbsp;
											<a
												href="grpms.do?CMD=grpms_search&msid=<%=tbmsinfo.getMsId()%>"><span
												class="STYLE17">所属群组</span>
											</a>
											&nbsp;|&nbsp;
											<a
												href="ms.do?CMD=pbjsp&msid=<%=tbmsinfo.getMsId()%>"><span
												class="STYLE17">手机绑定</span>
											</a>
										</td>
									</tr>
									<%
										}
										}
									%>

<tr>
										<td align="right" colspan="11" class="list_footer">
										  每页显示
										 <select name="pageSize" id="pageSize"  style="{width:30;margin:0;FONT-SIZE:9pt;FONT-WEIGHT:normal;}">
										 <option value="10">10</option>
										 <option value="15">15</option>
										 <option value="20">20</option>
										 <option value="25">25</option>
										 <option value="30">30</option>
										 </select>
										 条&nbsp;&nbsp;
											共有<%=request.getAttribute("msCount")%>条&nbsp;&nbsp; &nbsp;&nbsp; 
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
						</td>
					</tr>
				</table>
			</td>
		</form>
	</body>
</html>

<script language="javascript">
function pageclick(page){
    MSSerachForm.action='ms.do?CMD=<%=request.getAttribute("CMD")%>&type=1&pageNo='+page;
	MSSerachForm.submit();
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

//<!--模糊查询按键  -->
function msidserach(form){
    form.action="ms.do?CMD=ms_search&type=1";
	form.submit();
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
 	 var page=this.MSSerachForm.gotopage.value;
	 if(!isNumber(page)){
     alert("输入跳转页数非法,请重新输入.(只能由数字组成)");
     return false;
     }
    form.action="ms.do?CMD=<%=request.getAttribute("CMD")%>&pagentid=<%=request.getAttribute("pagentid")%>&childagentid=<%=request.getAttribute("childagentid")%>&ep=<%=request.getAttribute("ep")%>&user_type=<%=request.getAttribute("mstype")%>&statue=<%=request.getAttribute("msstatue")%>&flag=<%=request.getAttribute("msflag")%>&msid=<%=request.getAttribute("ms_id")%>&msname=<%=URLEncoder.encode(request.getAttribute("ms_name")
							.toString(), "UTF-8")%>&type=1&pageNo="+page;
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
   //document.getElementById("arrearage").value=arrearage; alert("123");
   var psize = <%=request.getAttribute("pageSize")%>;
  document.getElementById("pageSize").value=psize;
  var mes="<%=request.getAttribute("message")%>";
  if(mes!="null"){
    alert(mes);
    document.location.href="ms.do?CMD=ms_search&pagentid=<%=request.getAttribute("pagentid")%>&childagentid=<%=request.getAttribute("childagentid")%>&ep=<%=request.getAttribute("ep")%>&type=1";
  } 
  var r_id=<%=Integer.parseInt(request.getSession().getAttribute("roleId").toString())%>;
    if(r_id == 2 || r_id == 3 || r_id == 4 ){
	 document.getElementById("pagentid").length = 1;
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
  MSSerachForm.childagentid.options.length=2; 
  var parentagent=MSSerachForm.pagentid.value;
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
        var pagent=MSSerachForm.pagentid.value;
        var cagent=MSSerachForm.childagentid.value;
        MSSerachForm.ep.options.length=1;
        getEpinfo();
}

		var a_id=<%=Integer.parseInt(request.getSession().getAttribute("agentId").toString())%>;
        var r_id=<%=Integer.parseInt(request.getSession().getAttribute("roleId").toString())%>;
        var ep_id=<%=Integer.parseInt(request.getSession().getAttribute("epId").toString())%>;
function getEpinfo(){
        var epselect= document.getElementById("ep_id");
        var pagent=MSSerachForm.pagentid.value;
        var cagent=MSSerachForm.childagentid.value;
        MSSerachForm.ep.options.length=1;
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
</script>
