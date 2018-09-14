<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.LinkedHashMap"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.lang.String"%>
<%@ page import="java.net.URLEncoder"%>
<%@ page import="com.elegps.tscweb.model.TbAgentInfo"%>
<%@ page import="com.elegps.tscweb.model.TbEnterpriseInfo"%>
<%@ page import="com.elegps.tscweb.model.TbKoFeiLogInfo"%>
<%@ page import="com.elegps.tscweb.model.TbPhonekoufeiLog"%>
<%@ page import="java.text.SimpleDateFormat"%>
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
  <script language="javascript" type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
<script language="javascript" type="text/javascript" src="js/My97DatePicker/WdatePicker.js"></script>
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
		<form id="kfForm" name="kfForm" method="post">
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
										<span class="STYLE4">您现在的位置：<span class="STYLE7">卫星通话扣费账单</span>
										</span>
									</td>
									<td width="52%">
										<img src="images/3_03.jpg" width="518" height="35">
									</td>
								</tr>
							</table>
							<table width="100%" height="20" border="0" align="right">
								
								<tr>
									<td width="10%" align="right">
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
									<td width="10%" align="right">
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
															style="width: 128; margin: 0; FONT-SIZE: 9pt; FONT-WEIGHT: normal;">
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
																	
																	if(rid==3 && eid != 0){
																		//企业用户登录执行
															%>
															<option selected="selected" value="<%=((TbEnterpriseInfo)listep.get(0)).getEp_Id()%>"><%=((TbEnterpriseInfo)listep.get(0)).getEp_Name()%></option>
															<%
																	}else{
																		for (int i = 0; i < listep.size(); i++) {
																			TbEnterpriseInfo epinfo = new TbEnterpriseInfo();
																			epinfo = (TbEnterpriseInfo) listep.get(i);
															%>
															<option value="<%=epinfo.getEp_Id()%>"><%=epinfo.getEp_Name()%></option>
															<%
																		}
																	}
																}
															%>
														</select>
													</div>
									</td>
									<td width="10%" align="right">
										<div align="right">
											扣费类型:
										</div>
									</td>
									<td width="12%" align="left">
										<div align="left">
											<select name="type" id="type"
												style="width: 70; margin: 0; FONT-SIZE: 9pt; FONT-WEIGHT: normal;">
												<option value="-1">
													全部
												</option>
												<option value="0">
													电话
												</option>
												<option value="1">
													短信
												</option>
											</select>
										</div>
									</td>
								</tr>
								<tr>
									
									<td width="10%" align="right">
										<div align="right">
											船家宝ID:
										</div>
									</td>
									<td align="left" width="12%">
										<div align="left">
											<input  type="text" name="imsi"
												value="" size="21" maxlength="21" />
										</div>
									</td>
									
									<td width="10%" align="right">
										<div align="right">
											号码:
										</div>
									</td>
									<td align="left" width="12%">
										<div align="left">
											<input  type="text" name="msid"
												value="" size="21" maxlength="21" />
										</div>
									</td>
									<td width="10%" align="right">
										<div align="right">
											扣费时间:
										</div>
									</td>
									<td align="left" width="10%" colspan="3">
										<div align="left">
											<input style="width: 128px;" id="startTime" name="startTime" type="text"
											class="Wdate" onFocus="WdatePicker({lang:'zh-cn',dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:'true',minDate:'{%y-1}-%M-{%d}',maxDate:'%y-%M-%d'})" size="17" value="<%=request.getAttribute("startTime")%>"/>
											  --  <input style="width: 128px;" id="endTime" name="endTime" type="text"
											class="Wdate" onFocus="WdatePicker({lang:'zh-cn',dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:'true',minDate:'{%y-1}-%M-{%d}',maxDate:'%y-%M-%d'})" size="17" value=""/>
										</div>
									</td>
									<td colspan="4" align="center" width="35%">
										<div>
											<span style="cursor: hand"><img src="images/bt_cx.gif" alt="查询"
													width="65" height="22"
													onclick="kfserach()" /></span>
										</div>
									</td>
								</tr>
							
							</table>
							<br>
							<br>
							<br>
							<br>
							<br>

							<table width="100%" height="21" border="1" bordercolor="#C1DAD7">
								<tr>
									<!-- <td width="3%" align="center" class="list_header" height="25">
									    <span class="STYLE77">单选</span>
									</td> -->
									<td width="12%" align="center" class="list_header">
										<span class="STYLE77">船家宝ID</span>
									</td>
									<td width="13%" align="center" class="list_header">
										<span class="STYLE77">号码</span>
									</td>
									<td width="12%" align="center" class="list_header">
										<span class="STYLE77">扣费类型</span>
									</td>
									<td width="13%" align="center" class="list_header">
										<span class="STYLE77">扣费金额</span>
									</td>
									<%--<td width="12%" align="center" class="list_header">
										<span class="STYLE77">扣费类别</span>
									</td>--%>
									<td width="8%" align="center" class="list_header">
										<span class="STYLE77">扣费时间</span>
									</td>
									<!-- <td width="23%" align="center" class="list_header">
										<span class="STYLE77">操作</span>
									</td> -->
								</tr>

								<logic:present name="kfList" scope="request">
									<%
											String type = null;
											String grpOrNot = null;
											Double money = null;
											SimpleDateFormat dateFormat = new SimpleDateFormat(
												"yyyy-MM-dd HH:mm:ss");
												
											List<Map> mList =(List<Map>)request.getAttribute("kfList");
											if(mList!=null){
											for(int i = 0 ; i < mList.size(); i++){
												TbPhonekoufeiLog kfinfo = new TbPhonekoufeiLog();
												kfinfo = (TbPhonekoufeiLog) mList.get(i);
												if(kfinfo.getType()!=null){
													if(kfinfo.getType()==0){
														type ="电话";
													}else if(kfinfo.getType()==1){
														type ="短信";
													}
												}else{
												    type="";
												}

												if(kfinfo.getMoney()!=null){
													money = Double.valueOf(kfinfo.getMoney()/100);
												}

												/*if(kfinfo.getGrpOrNot()==0){
													grpOrNot ="单聊";
												}else{
													grpOrNot ="群聊";
												}*/
											//}
											
											//List list = (List) request.getAttribute("grpList");
											//if (list != null) {
											//	for (int i = 0; i < list.size(); i++) {
											//		TbGrpInfo tbgrpinfo = new TbGrpInfo();
											//		tbgrpinfo = (TbGrpInfo) list.get(i);
									%>
									<tr>
										<%-- <td align="center">
											<input name="list" type="radio"
												value=<%=kfinfo.getId()%>>
										</td> --%>
										<td align="center">
											<font><span class="STYLE77"><%=kfinfo.getImsi()==null?"":kfinfo.getImsi()%></span>
											</font>
										</td>
										<td align="center" bgcolor="#F5FAFA">
											<font><a
												href="ms.do?CMD=xinaxi&msid=<%=kfinfo.getMsid()%>"><span
													class="STYLE77"><%=kfinfo.getMsid()%></span>
											</a> </font>
										</td>
										
										<td align="center">
											<font><span class="STYLE77"><%=type%></span>
											</font>
										</td>
										<td align="center" bgcolor="#F5FAFA">
											<font><span class="STYLE77"><%=money%></span>
											</font>
										</td>
										<%--<td align="center">
											<font><span class="STYLE77"><%=grpOrNot%></span>
											</font>
										</td>--%>
										<td align="center">
											<font><span class="STYLE77"><%=dateFormat.format(kfinfo.getStartTime())%></span>
											</font>
										</td>
										<%-- <td align="center" >
											<a
												href="grpms.do?CMD=grpms_search&grpid=<%=kfinfo.getId()%>"><span
												class="STYLE17">终端列表</span>
											</a>
										</td> --%>
									</tr>
									<%
										}
											}
									%>

</logic:present>
									<tr>
										<td align="right" colspan="11" class="list_footer">
										 每页显示
										 <select name="pageSize" id="pageSize"  style="{width:40;margin:0;FONT-SIZE:9pt;FONT-WEIGHT:normal;}">
										 <option value="10">10</option>
										 <option value="15">15</option>
										 <option value="20">20</option>
										 <option value="25">25</option>
										 <option value="30">30</option>
										 </select>
										 条&nbsp;&nbsp;
											共有<%=request.getAttribute("kfCount")%>条&nbsp;&nbsp; &nbsp;&nbsp;
											第<%=request.getAttribute("currentPage")%>页&nbsp;
											共<%=request.getAttribute("pageCount")%>页&nbsp;
													<img src="images/bt_first.gif" width="42" height="16" border="0" align="absmiddle"  onClick="return pageclick('1');">
		<img src="images/bt_pre.gif" width="38" height="16" border="0" align="absmiddle"  onClick="return pageclick('<%=((Integer)request.getAttribute("currentPage")-1)%>');">
		<img src="images/bt_next.gif" width="41" height="16" border="0" align="absmiddle"  onClick="return pageclick('<%=((Integer)request.getAttribute("currentPage")+1)%>');">
		<img src="images/bt_last.gif" width="42" height="16" border="0" align="absmiddle"   onClick="return pageclick('<%=request.getAttribute("pageCount")%>');">
											 转到第
											<input type="text" size="2" name="gotopage"
												value="${requestScope.currentPage}" />
											页
											<span style="cursor: hand"><img src="images/bt_go.gif"
													width="20" height="12" border="0"
													onClick="gotoye(document.forms[0])" />
											</span>
										</td>
									</tr>
								
							</table>
	</body>
</html>

<script language="javascript">
function pageclick(page){
    kfForm.action="pkf.do?CMD=<%=request.getAttribute("CMD")%>&pageNo="+page;
	kfForm.submit();
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
/* if (!dataIsAvail(form))   
		return;
		if (confirm("确定删除所选的内容吗?"))
		{  	
		form.action="grp.do?CMD=delete";
		form.submit();
	  }   */
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

//<!--模糊查询按键  -->
function kfserach(){
    kfForm.action="pkf.do?CMD=pkf_search";
	kfForm.submit();
}


//<!--改变代理商时-->
function agentchang(form){
  form.action="pkf.do?CMD=agentchange";
  form.submit();
}

function gotoye(form){
    var page=this.kfForm.gotopage.value;
    if(!isNumber(page)){
     alert("输入跳转页数非法,请重新输入.(只能由数字组成)");
     return false;
     }
    form.action="pkf.do?CMD=<%=request.getAttribute("CMD")%>&pagentid=<%=request.getAttribute("pagentid")%>&"+
    	"childagentid=<%=request.getAttribute("childagentid")%>&ep=<%=request.getAttribute("ep")%>&"+
    	"type=<%=request.getAttribute("type")%>&msid=<%=request.getAttribute("msid")%>&pageNo="+page;
	form.submit();  
}


//<!-- 装入上次查询用户类型查询、在线状态查询、用户有效状态查询的装态 -->
function goload() 
{ 
  var type=<%=request.getAttribute("type")%>;
  var msid="<%=request.getAttribute("msid")%>";
  var imsi="<%=request.getAttribute("imsi")%>";
  var ep=<%=request.getAttribute("ep")%>;
  var pagentid=parseInt(<%=request.getAttribute("pagentid")%>);
  var childagentid=parseInt(<%=request.getAttribute("childagentid")%>);
  var startTime="<%=request.getAttribute("startTime")%>";
  var endTime="<%=request.getAttribute("endTime")%>";
  var psize = <%=request.getAttribute("pageSize")%>;
  
  
   document.getElementById("pagentid").value=pagentid;
   document.getElementById("childagentid").value=childagentid;
   document.getElementById("type").value=type;
   document.getElementById("msid").value=msid; 
   document.getElementById("imsi").value=imsi; 
   document.getElementById("ep_id").value=ep;
   document.getElementById("startTime").value=startTime;
   document.getElementById("endTime").value=endTime;
   document.getElementById("pageSize").value=psize;
   var mes="<%=request.getAttribute("message")%>";
  if(mes!="null"){
    alert(mes);
  }  

 if(<%=rid %> == 4){
  	document.getElementById("pagentid").length = 1;
  	document.getElementById("childagentid").length = 1;
  }
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
  var parentagent=kfForm.pagentid.value;
  if(parentagent!=null){
        ChildAgent.getchiledAgent(parentagent,function(data){
         if(data!=null){
           	kfForm.childagentid.options.length=2;
         	
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
        var a_id=<%=Integer.parseInt(request.getSession().getAttribute("agentId").toString())%>;
  		var r_id=<%=Integer.parseInt(request.getSession().getAttribute("roleId").toString())%>;
  		var ep_id=<%=Integer.parseInt(request.getSession().getAttribute("epId").toString())%>;
function childagentchang(){
        getEpinfo();
}

function getEpinfo(){
        var epselect= document.getElementById("ep_id");
        var pagent=kfForm.pagentid.value;
        var cagent=kfForm.childagentid.value;
        kfForm.ep.options.length=1;
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
           kfForm.ep.options.length=0;
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
