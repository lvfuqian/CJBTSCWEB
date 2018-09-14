<%@ page language="java" pageEncoding="UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@ page import="com.elegps.tscweb.model.TbAdvInfo"%>
<%@ page import="java.util.List"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="css/ComboBox.css" rel="stylesheet" type="text/css">
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
	font-size: 15px;
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
		<link href="css/manage.css" rel="stylesheet" type="text/css">
		<link href="css/list.css" rel="stylesheet" type="text/css">
		<script language="javascript" type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
		<script language="javascript" type="text/javascript" src="js/My97DatePicker/WdatePicker.js"></script>
		<style type="text/css">
<!--
.STYLE9 {
	color: #000000;
	font-weight: bold;
	text-indent: 10px;
}
-->
</style>
		<title>修改广告信息</title>
	</head>

	<body onload="goload()">
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
									<span class="STYLE4">您现在的位置：<span class="STYLE7">广告修改</span>
									</span>
								</td>
								<td width="52%">
									<img src="images/3_03.jpg" width="518" height="35">
								</td>
							</tr>
						</table>
						<br>
						<br>
						<%
								TbAdvInfo advinfo = (TbAdvInfo)request.getAttribute("advinfo");
							 %>
						<form name="advaddform" onSubmit="return formcheck(<%=advinfo.getAdvId() %>);"
							method="post" action="" enctype="multipart/form-data">
							<table align="center" width="470" border="1"
								bordercolor="#C1DAD7">
								<tr>
									<td colspan="2" align="center" class="list_header">
										<span class="STYLE77">广告信息修改</span>
									</td>
								</tr>
								<tr>
									<td align="right">
										广告标题:
									</td>
									<td>
										<input name="advTitle" type="text" class="checkbox" size="30"
											value='<%=advinfo.getAdvTitle() %>'>
										<font color="red">&nbsp;<B>*</B>
										</font>
									</td>

								</tr>
								<tr>
									<td align="right">
										<label>
											广告内容:
										</label>
									</td>
									<td>
										<textarea name="advContent" rows="8" cols="30" 
										class="checkbox" style="overflow-y:hidden"><%=advinfo.getAdvContent() %></textarea>
										<font color="red">&nbsp;<B>*</B>
										</font>
									</td>

								</tr>
								<tr>
									<td align="right">
										<label>
											广告图片:
										</label>
									</td>
									<td>
									<%
										if(!advinfo.getPicUrl().equals("")&&!advinfo.getPicUrl().equals(null)){
											String[] pic = advinfo.getPicUrl().split("\\|");
											for(int i=0;i<pic.length;i++){
											System.out.println(pic[i]);
												%>
												<img alt="" width="150px" height="120px" src='<%=pic[i] %>'/>
												<%
											}
											%>
											<br/>
											<% 
										}
									 %>
										<input type="file"  multiple="true" name="picUrl" class="checkbox" 
											style="overflow-y:hidden" size="30"/>
										<font color="red">&nbsp;<B>*</B>
										</font>
									</td>
								</tr>
								<tr>
									<td align="right">
										<label>
											广告链接:
										</label>
									</td>
									<td>
										<input name="advUrl" class="checkbox" style="overflow-y:hidden" size="30"
											value='<%=advinfo.getAdvUrl() %>'/>
										<font color="red">&nbsp;<B>*</B>
										</font>
									</td>
								</tr>
								<tr>
									<td width="110" align="right">
										推送时间:
									</td>
									<td align="left">
									<input name="sendSTime" type="text" value='<%=advinfo.getSendSTime() %>'
											class="Wdate" onFocus="WdatePicker({lang:'zh-cn',dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:'true',minDate:'%y-%M-{%d}',maxDate:'{%y+1}-%M-%d'})" size="17" value=''/>
									&nbsp;&nbsp;<input name="sendETime" type="text" value='<%=advinfo.getSendETime() %>'
											class="Wdate" onFocus="WdatePicker({lang:'zh-cn',dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:'true',minDate:'%y-%M-{%d}',maxDate:'{%y+1}-%M-%d'})" size="17" value=''/>
										<font color="red">&nbsp;<B>*</B>
										</font>
									</td>
								</tr>
								<tr>
									<td colspan="2" align="center" class="list_footer">
										<input class=btn type="Submit" name="Submit" value="修改" />
										&nbsp;
										<input class=btn type="button" name="button" value="返回"
											onclick="touserlist()" />
										&nbsp;
										<input class=btn type="reset" name="reset" value="重置" />
									</td>
								</tr>
							</table>
						</form>
	</body>
</html>

<script language="javascript">
function touserlist(){
 document.location.href="javascript: history.back(-1)";
}


function formcheck(advid){
 	if(advaddform.advTitle.value==""){ 
      alert("请输入标题！");
      advaddform.advTitle.focus();
      return false;
   	}
   	if(advaddform.advContent.value==""){ 
      alert("请输入广告内容！");
      advaddform.advContent.focus();
      return false;
   	}
	if(advaddform.sendSTime.value=="" || advaddform.sendETime.value=="" ){ 
      alert("请选择推送时间！");
      advaddform.sendSTime.focus();
      return false;
   	}

  	var flag = window.confirm("确定修改广告？");
	if(flag){
		advaddform.action="adv.do?CMD=advupdate&advid="+advid;
	    advaddform.submit();
		return true;
	}

   return false;
}

function goload() 
{ 
 var mes="<%=request.getAttribute("message")%>";
  if(mes!="null"){
    alert(mes);
  } 
} 


//删除字符串左边的空格
function ltrim(str)	
{ 
	if(str.length==0)
		return(str);
	else
	{
		var idx=0;
		while(str.charAt(idx).search(/\s/)==0)
			idx++;
		return(str.substr(idx));
	}
}

//删除字符串右边的空格
function rtrim(str)	
{ 
	if(str.length==0)
		return(str);
	else
	{
		var idx=str.length-1;
		while(str.charAt(idx).search(/\s/)==0)
			idx--;
		return(str.substring(0,idx+1));
	}
}

//删除字符串左右两边的空格
function trim(str)	
{ 
	return(rtrim(ltrim(str)));
}


</script>