<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@ page import="com.elegps.tscweb.model.TbMsInfo"%>
<%@ page import="com.elegps.tscweb.model.Vehicle"%>
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
	FILTER: progid : DXImageTransform.Microsoft.Gradient ( GradientType = 0,
		StartColorStr = #ffffff, EndColorStr = #cecfde );
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
		<script type='text/javascript' src='dwr/interface/ChildAgent.js'>
</script>
		<script type='text/javascript' src='dwr/engine.js'>
</script>
		<script type='text/javascript' src='dwr/util.js'>
</script>
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
		<title>修改终端用户信息</title>
	</head>

	<body>
		<c:set value="${requestScope.vehicle}" var="vh"></c:set>
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
									<span class="STYLE4">您现在的位置：<span class="STYLE7">修改车牌信息
									</span>
									</span>
								</td>
								<td width="52%">
									<img src="images/3_03.jpg" width="518" height="35">
								</td>
							</tr>
						</table>
						<br>
						<br>
						<form name="traffic" method="post" action="">
							<table align="center" width="450" border="1"
								bordercolor="#C1DAD7">
								<tr>
									<td colspan="2" align="center" class="list_header">
										<span class="STYLE77">修改车牌信息</span>
									</td>
								</tr>
								<tr>
										<input type="hidden" name="vid" value="${vh.id}"/> 
								<tr>
									<td align="right">
										<label>
											车牌号码:
										</label>
									</td>
									<td>
										<input style="width: 180px;"  type="text"	class="checkbox" size="15" name="chepais" value="${vh.chepai}" />
										<font color="red">&nbsp;<B>*</B>
										</font>
									</td>
								</tr>
								<tr>
									<td width="15%" align="right">
										<label>
											车机卡号:
										</label>
									</td>
									<td width="40%" align="left">
										<input style="width: 180px;"  type="text"	class="checkbox" size="15" name="cheji" value="${vh.cheji}" />
										<font color="red">&nbsp;<B>*</B>
										</font>
									</td>
								</tr>
								<tr>
									<td width="15%" align="right">
										<label>
											GPRS帐号:
											</label>
									</td>
									<td>
										<input style="width: 180px;" name="gprs" type="text"
											class="checkbox" size="15" value="${vh.GPRS}" />
										<font color="red">&nbsp;<B>*</B>
										</font>
									</td>
								</tr>
								<tr>
									<td colspan="2" align="center" class="list_footer">
										<input class=btn type="button" name="button1" value="提交"
											onclick="goto(document.forms[0])" />
										&nbsp;

										<input class=btn type="button" name="button" value="返回"
											onclick="tomslist()" />
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
function tomslist() {
	document.location.href = "javascript: history.back(-1)";
}function goto(form){
var flag = window.confirm("确定要修改此记录吗？");
if(flag){

   if(trim(traffic.chepais.value)==""){
      alert("车牌名称不能为空!");
      traffic.chepais.focus();
      return false; 
   } 
   
  if(trim(traffic.cheji.value)!=""){ 
     		if(trim(traffic.cheji.value).length!=11){
            alert("车机卡号码长度必须是11位,请输入正确的车机卡号！"); 
            return false;        
           } else   if (!isMobil(trim(traffic.cheji.value)))  {   
            alert("车机号码长度必须是11位,请输入正确的车机卡号\n\n例如:13800138000！"); 
            traffic.cheji.focus();
            return false;        
        }
    }else{
      alert("车机卡号为空，请输入车机卡号");
      traffic.cheji.focus();
      return false;
    }
    if(trim(traffic.gprs.value)!="")
   { 
	   	if(!isDigit(trim(traffic.gprs.value))){
	      alert("GPRS号码必须为10~11位数字，请输入GPRS号码");
	      traffic.gprs.focus();
	      return false;
	      }
   } else { 
      alert("GPRS号码为空，请输入GPRS号码");
      traffic.gprs.focus();
      return false;
   }  
       form.action="traffic.do?CMD=traffic_update";
	   form.submit(); 
  }
function isDigit(s)    
{    
var patrn=/^[0-9]{10,11}$/;    
if (!patrn.exec(s)) 
return false   
return true   
} 
function isMobil(s)    
{    
var patrn=/^((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8}$/;      
if (!patrn.exec(s))
 return false   
return true   
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