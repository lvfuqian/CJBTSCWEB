<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@ page import="com.elegps.tscweb.model.TbQuestionInfo"%>
<%@ page import="java.net.URLEncoder"%>
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
	<body onload="goload()">
		<form id="form1" name="form1" method="post">
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
										<span class="STYLE4">您现在的位置：<span class="STYLE7">客户问题查询</span>
										</span>
									</td>
									<td width="52%">
										<img src="images/3_03.jpg" width="518" height="35">
									</td>
								</tr>
							</table>
							<table width="100%" height="73" border="0">
								<tr>
									<td width="80" align="right">
										<div align="right">
											类型:	</div></td>
									<td width="133" align="left">
										<div align="left">
											<select name="type" id="type"
												style="width: 128; margin: 0; FONT-SIZE: 9pt; FONT-WEIGHT: normal;">
												<option value="">全部类型</option>
												<option value="1">硬件</option>
										        <option value="2">GPS监控端</option>
										        <option value="3">一呼通语音平台</option>
										        <option value="4">GPS数据</option>
										        <option value="5">语音</option>
										        <option value="6">未知</option>
											</select>
										</div></td>
									<td width="80" align="right">
										<div align="right">
											问题状态:	</div></td>
									<td width="139" align="left">
										<div align="left">
											<select name="state" id="state"
												style="width: 128; margin: 0; FONT-SIZE: 9pt; FONT-WEIGHT: normal;">
												<option value="">全部状态	</option>
												<option value="0">未解决</option>
												<option value="1">已解决且回访</option>
											</select>
										</div></td>
									
									<td width="77" align="right"><div align="right">记录人:</div>	</td>
									<td align="left">
										<div align="left"><input name="recorder" type="text" style="width: 128px;"
												value='${requestScope.recorder}' size="10" maxlength="10" />
										</div></td>
								</tr>
								<tr>
									<td width="77" align="right"><div align="right">客服人员:</div>	</td>
									<td align="left">
										<div align="left"><input name="henchman" type="text" style="width: 128px;"
												value='${requestScope.henchman}' size="10" maxlength="10" />
										</div></td>
									<td width="77" align="right"><div align="right">技术人员:</div>	</td>
									<td align="left">
										<div align="left"><input name="solve_man" type="text" style="width: 128px;"
												value='${requestScope.solve_man}' size="10" maxlength="10" />
										</div>
									</td>
									<td colspan="4" align="center">
									      <div>
											<span style="cursor: hand"><img src="images/bt_cx.gif" alt="查询"
													width="65" height="22"
													onclick="questionserach(document.forms[0])" />
											</span>
													&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										   <span style="cursor: hand"><img
													src="images/bt_add.gif" alt="增加" width="65" height="22"
													onclick="questionadd(document.forms[0])" />
											</span>
										    </div>
							        </td>
								</tr>
							</table>
							<br>

							<table width="100%" height="21" border="1" bordercolor="#C1DAD7">
								<tr height="12">
								   <td width="10%" class="list_header" height="12" align="center">
										<span class="STYLE77">记录时间</span>
									</td>
									<td width="15%" class="list_header" height="12" align="center">
										<span class="STYLE77">客户名称</span>
									</td>
									<td width="10%" class="list_header" height="12"  align="center">
										<span class="STYLE77">类型</span>
									</td>
									<td width="22%" class="list_header" height="12"  align="center">
										<span class="STYLE77">问题内容</span>
									</td>
									<td width="10%" class="list_header" height="12" align="center">
										<span class="STYLE77"> 问题状态</span>
									</td>
									<td width="10%" class="list_header" height="12" align="center">
										<span class="STYLE77">操作</span>
									</td>
								</tr>

								<logic:present name="questionList" scope="request">
								
									<%  
									    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
									    String fontcolor;
										String questiontype = "";
										String states="";
										String visit_state="";
										List list = (List) request.getAttribute("questionList");
										long time=0;
										if (list != null) {
											for (int i = 0; i < list.size(); i++) {
												TbQuestionInfo tbquestioninfo = new TbQuestionInfo();
												tbquestioninfo = (TbQuestionInfo) list.get(i);
												if (tbquestioninfo.getState()==0) {
 										        	fontcolor = "red";
 										         } else {
										 	        fontcolor = "black";
										         }
									%>
									<tr>
									<td   class="STYLE8" align="center">
											<span class="STYLE77"> 
											<% if (tbquestioninfo.getRecorder_Time()==null){
										         out.print("");
										       }else{
				                                 out.print(dateFormat.format(tbquestioninfo.getRecorder_Time()));
										       }
										     %>
										    </span>
										</td>
									<td bgcolor="#F5FAFA" class="STYLE8" align="center">
											<span class="STYLE77"> 
										      <%=tbquestioninfo.getCep()%> 
										    </span>
										</td>
																				
										<td class="STYLE8" align="center">
											<span class="STYLE77">
											<%
 											if (tbquestioninfo.getType()== 1) {
 												questiontype = "硬件";
 											} else if (tbquestioninfo.getType()== 2) {
 												questiontype = "GPS监控端";
 											} else if(tbquestioninfo.getType()== 3){
 												questiontype = "一呼通语音平台";
 											}else if(tbquestioninfo.getType()== 4){
 												questiontype = "GPS数据";
 											}else if(tbquestioninfo.getType()== 5){
 												questiontype = "语音";
 											}else {
 											    questiontype = "未知";
 											}
 											%> 
											<%=questiontype%>
											</span>

										</td>
										<td bgcolor="#F5FAFA" class="STYLE8" align="left">
											<span class="STYLE77"> 
											<%
											 String memo="";
											 String test=tbquestioninfo.getContent();
											  if(test.length()>15){
											   memo=test.substring(0,15)+"......";
											  }else{
											   memo=test;
											  }
											%> 
											<a
												href="question.do?CMD=xingxi&id=<%=tbquestioninfo.getId()%>"><span
												class="STYLE17"><%=memo%>
											</a>
											</span> 
										</td>
										<td  class="STYLE8" align="center">
											<span class="STYLE77"> <%
 										if (tbquestioninfo.getState()==0) {
 											states = "未解决";
 										} else {
										 	states = "已解决且回访";
										}
										%> 
										<font color=<%=fontcolor%>>
										<%=states%> 
										</font>
										</span> 
										</td>
										
										<td bgcolor="#F5FAFA" class="STYLE8" align="center">
											<a href="question.do?CMD=mdoyijsp&id=<%=tbquestioninfo.getId()%>"><span
												class="STYLE17">修改</span>
											</a>
										</td>
									</tr>
									<%
										}
										}
									%>


									<tr>
										<td align="right" colspan="10" class="list_footer">
											共有<%=request.getAttribute("questionCount")%>条&nbsp;&nbsp; 第<%=request.getAttribute("currentPage")%>页&nbsp;
											共<%=request.getAttribute("pageCount")%>页&nbsp;
											<a
												href="question.do?CMD=<%=request.getAttribute("CMD")%>&type=<%=request.getAttribute("type")%>&state=<%=request.getAttribute("state")%>&recorder=<%=URLEncoder.encode(request.getAttribute("recorder").toString(), "UTF-8")%>&henchman=<%=URLEncoder.encode(request.getAttribute("henchman").toString(), "UTF-8")%>&solve_man=<%=URLEncoder.encode(request.getAttribute("solve_man").toString(), "UTF-8")%>&pageNo=1"><img
													src="images/bt_first.gif" width="42" height="16" border="0"
													align="absmiddle">
											</a>
											<a
											href="question.do?CMD=<%=request.getAttribute("CMD")%>&type=<%=request.getAttribute("type")%>&state=<%=request.getAttribute("state")%>&recorder=<%=URLEncoder.encode(request.getAttribute("recorder").toString(), "UTF-8")%>&henchman=<%=URLEncoder.encode(request.getAttribute("henchman").toString(), "UTF-8")%>&solve_man=<%=URLEncoder.encode(request.getAttribute("solve_man").toString(), "UTF-8")%>&pageNo=<%=((Integer) request.getAttribute("currentPage") - 1)%>"><img
													src="images/bt_pre.gif" width="38" height="16" border="0"
													align="absmiddle">
											</a>
											<a
												href="question.do?CMD=<%=request.getAttribute("CMD")%>&type=<%=request.getAttribute("type")%>&state=<%=request.getAttribute("state")%>&recorder=<%=URLEncoder.encode(request.getAttribute("recorder").toString(), "UTF-8")%>&henchman=<%=URLEncoder.encode(request.getAttribute("henchman").toString(), "UTF-8")%>&solve_man=<%=URLEncoder.encode(request.getAttribute("solve_man").toString(), "UTF-8")%>&pageNo=<%=((Integer) request.getAttribute("currentPage") + 1)%>"><img
													src="images/bt_next.gif" width="41" height="16" border="0"
													align="absmiddle">
											</a>
											<a
												href="question.do?CMD=<%=request.getAttribute("CMD")%>&type=<%=request.getAttribute("type")%>&state=<%=request.getAttribute("state")%>&recorder=<%=URLEncoder.encode(request.getAttribute("recorder").toString(), "UTF-8")%>&henchman=<%=URLEncoder.encode(request.getAttribute("henchman").toString(), "UTF-8")%>&solve_man=<%=URLEncoder.encode(request.getAttribute("solve_man").toString(), "UTF-8")%>&pageNo=<%=request.getAttribute("pageCount")%>"><img
													src="images/bt_last.gif" width="42" height="16" border="0"
													align="absmiddle">
											</a> 转到第
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
function questionserach(form){
    form.action="question.do?CMD=question_search";
	form.submit();
}


function questionadd(form){
 form.action="question.do?CMD=addjsp";
	form.submit();
}


function gotoye(form){
 	 var page=this.form1.gotopage.value;
	 if(!isNumber(page)){
     alert("输入跳转页数非法,请重新输入.(只能由数字组成)");
     return false;
     }
    form.action="question.do?CMD=<%=request.getAttribute("CMD")%>&type=<%=request.getAttribute("type")%>&state=<%=request.getAttribute("state")%>&recorder=<%=URLEncoder.encode(request.getAttribute("recorder").toString(), "UTF-8")%>&henchman=<%=URLEncoder.encode(request.getAttribute("henchman").toString(), "UTF-8")%>&solve_man=<%=URLEncoder.encode(request.getAttribute("solve_man").toString(), "UTF-8")%>&pageNo="+page;
	form.submit();   
}


function goload() 
{ 
  var type="<%=request.getAttribute("type")%>";
  var state="<%=request.getAttribute("state")%>";
   document.getElementById("type").value=type; 
   document.getElementById("state").value=state; 
  var mes="<%=request.getAttribute("message")%>";
  if(mes!="null"){
    alert(mes);
    document.location.href="question.do?CMD=question_search";
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
