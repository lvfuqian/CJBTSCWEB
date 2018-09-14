<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
	FILTER: progid :               DXImageTransform .              
		Microsoft . 
		     
		
		
		  
		 
		Gradient(GradientType =               0, StartColorStr =             
		 #ffffff, EndColorStr =       
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
		<script type='text/javascript' src='dwr/interface/ChildAgent.js'></script>
		<script type='text/javascript' src='dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>
		<link href="css/manage.css" rel="stylesheet" type="text/css">
		<link href="css/list.css" rel="stylesheet" type="text/css">
		<style type="text/css">
<!--
.STYLE9 {
	color: #000000;
	font-weight: bold;
	text-indent: 10px;
}
-->
</style>
		<title>修改终端其他设置</title>
	</head>

	<body>
		<c:set var="controlInfo" value="${requestScope.controlInfo }"></c:set>
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
									<span class="STYLE4">您现在的位置：<span class="STYLE7">修改终端其他设置</span>
									</span>
								</td>
								<td width="52%">
									<img src="images/3_03.jpg" width="518" height="35">
								</td>
							</tr>
						</table>
						<br>
						<br>
						<form name="control" id="control" method="post" action="">
							<table align="center" width="450" border="1"
								bordercolor="#C1DAD7">
								<tr>
									<td colspan="2" align="center" class="list_header">
										<span class="STYLE77"> 修改终端其他设置</span>
									</td>
								</tr>
								<tr>
									<td width="110" align="right">
										<label>
											终端编号:
										</label>
									</td>
									<td>
										<input class="checkbox" style="width: 230px;" type="text"
											name="msIds" value="${controlInfo.msId }" readonly />
									</td>
								</tr>
								<tr>
									<td align="right">
										<label>
											终端名称:
										</label>
									</td>
									<td>
										<input class="checkbox" style="width: 230px;" type="text"
											name="msNames" value="${requestScope.msNames }"
											readonly="readonly" />
										<font color="red">&nbsp;<B>*</B> </font>
									</td>
								</tr>
								<tr>
									<td align="right">
										<label>
											是否设置油量参数:
										</label>
									</td>
									<td>
										<select name="r01" id="r01"
											style="width: 80; margin: 0; FONT-SIZE: 9pt; FONT-WEIGHT: normal;">
											<c:if test="${controlInfo.r01==0}">
												<option value="0" selected="selected">
													否
												</option>
												<option value="1">
													是
												</option>
											</c:if>
											<c:if test="${controlInfo.r01==1}">
											<option value="0">
												否
											</option>
											<option value="1" selected="selected">
												是
											</option>
											</c:if>
										</select>
										<font color="red">&nbsp;<B>*</B> </font>
								</tr>
								<tr>
									<td align="right">
										<label>
											油量参数一:
										</label>
									</td>
									<td>
										<input class="checkbox" style="width: 230px;" type="text"
											name="r02" value="${controlInfo.r02 }" />
										<font color="red">&nbsp;<B>*</B> </font>
									</td>
								</tr>
								<tr>
									<td align="right">
										<label>
											油量参数二:
										</label>
									</td>
									<td>
										<input class="checkbox" style="width: 230px;" type="text"
											name="r03" value="${controlInfo.r03 }" />
										<font color="red">&nbsp;<B>*</B> </font>
									</td>
								</tr>
								<tr>
									<td align="right">
										<label>
											油量初始参数:
										</label>
									</td>
									<td>
										<input class="checkbox" style="width: 230px;" type="text"
											name="r04" value="${controlInfo.r04 }" />
										<font color="red">&nbsp;<B>*</B> </font>
									</td>
								</tr>
								<tr>
								<td align="right">
									<label>
										其他:
									</label>
								</td>
								<td>
									<input class="checkbox" style="width: 230px;" type="text"
										name="r05" value="${controlInfo.r05 }" />
									<font color="red">&nbsp;<B>*</B> </font>
								</td>
								</tr>
								<tr>
									<td colspan="2" align="center" class="list_footer">
										<input class=btn type="button" name="button1" value="提交"
											onclick="gotook(document.forms[0])" />
										&nbsp;
										<input class=btn type="button" name="button" value="返回"
											onclick="togrplist()" />
									</td>
								</tr>
							</table>
						</form>
					</td>
				</tr>
			</table>
	</body>
</html>

<script language="javascript">
 function togrplist(){  
   document.location.href="javascript: history.back(-1)";  
 }
 
function gotook(form){
	var flag = window.confirm("确定要修改此记录吗？");
	if(flag){
   	   if(trim(control.r04.value)=="")
         { 
          alert("油量初始参数为空，请输入油量初始参数！");
          control.r04.focus();
          return false;
         }
      if(trim(control.r04.value).length<2){
        alert("油量初始参数长度必须大于3位数,请重新输入油量初始参数！");
        control.r04.focus();
        return false;
       }
   
     if(trim(control.r04.value).length<2){
     	var talking=trim(control.r04.value);
 		for (var i=0; i<talking.length; i++)	
		{
			charcode = talking.charCodeAt(i);
			if (charcode < 48 || charcode > 57){
		    	alert("通话时长只能是由数字组成!,请重新输入！");
		    	control.r04.focus();
				return false;
			}
		}
 	 }
		 form.action="control.do?CMD=2";
	     form.submit(); 
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