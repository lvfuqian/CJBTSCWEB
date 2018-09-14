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
.black_overlay{  display: none;
    position: absolute;
    top: 0%;  left: 0%;  width: 100%;  height: 100%;
    background-color: black;
    z-index:1001;  
    -moz-opacity: 0.8;  
    opacity:.80;  
    filter: alpha(opacity=80);  }  
.white_content {  display: none;  
	position: absolute;  
	top: 25%;  left: 25%;  width: 50%;  height: 50%;  padding: 16px; 
	border: 16px solid orange;  
	background-color: white;  
	z-index:1002;  
	overflow: auto;  } 
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
  <script type='text/javascript' src='dwr/interface/ChildAgent.js'></script>
  <script type='text/javascript' src='dwr/engine.js'></script>
  <script type='text/javascript' src='dwr/util.js'></script>
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
		<title>增加广告信息</title>
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
									<span class="STYLE4">您现在的位置：<span class="STYLE7">广告添加</span>
									</span>
								</td>
								<td width="52%">
									<img src="images/3_03.jpg" width="518" height="35">
								</td>
							</tr>
						</table>
						<br>
						<br>
						<form name="advaddform" onSubmit="return formcheck();"
							method="post" action=""><!-- enctype="multipart/form-data"-->
							<table align="center" width="470" border="1"
								bordercolor="#C1DAD7">
								<tr>
									<td colspan="2" align="center" class="list_header">
										<span class="STYLE77">广告信息添加</span>
									</td>
								</tr>
								
								<tr id="trfile"  style="visibility:visible;">
									<td align="right" >
										附件:
									</td>
									<td >
										<input name="file2" id="file2" type="file">
										<input name="fileurl" id="fileurl" type="hidden">
										<button name="up" type="button" onclick="upload();">上传</button>
										</font>
									</td>

								</tr>
								<tr>
									<td align="right">
										广告类型:
									</td>
									<td>
										<input name="advType" type="radio" value="0" checked="checked" onclick = "typechange();">文字
										<input name="advType" type="radio" value="1" onclick = "typechange();">图片
										</font>
									</td>

								</tr>
								<tr>
									<td align="right">
										广告标题:
									</td>
									<td>
										<input name="advTitle" type="text" class="checkbox" size="30"
											value=''>
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
										class="checkbox" style="overflow-y:hidden"></textarea>
										<font color="red">&nbsp;<B>*</B>
										</font>
									</td>

								</tr>
								<!--<tr>
									<td align="right">
										<label>
											广告图片:
										</label>
									</td>
									<td>
										<input type="file"  multiple="true" name="picUrl" class="checkbox" style="overflow-y:hidden" size="30"/>
										<font color="red">&nbsp;<B>*</B>
										</font>
									</td>
								</tr>
								--><tr>
									<td align="right">
										<label>
											广告链接:
										</label>
									</td>
									<td>
										<input name="advUrl" class="checkbox" style="overflow-y:hidden" size="30"/>
										<font color="red">&nbsp;<B>*</B>
										</font>
									</td>
								</tr>
								<tr>
									<td width="110" align="right">
										显示日期:
									</td>
									<td align="left">
									<input name="sendSTime" type="text"
											class="Wdate" onFocus="WdatePicker({lang:'zh-cn',dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:'true',minDate:'%y-%M-{%d}',maxDate:'{%y+1}-%M-%d'})" size="17" value=''/>
									&nbsp;&nbsp;<input name="sendETime" type="text"
											class="Wdate" onFocus="WdatePicker({lang:'zh-cn',dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:'true',minDate:'%y-%M-{%d}',maxDate:'{%y+1}-%M-%d'})" size="17" value=''/>
										<font color="red">&nbsp;<B>*</B>
										</font>
									</td>
								</tr>
								<!-- <tr>
									<td width="110" align="right">
										显示时间:
									</td>
									<td align="left">
									<input name="showSTime" type="text"
											class="Wdate" onFocus="WdatePicker({lang:'zh-cn',dateFmt:'HH:mm:ss',readOnly:'true',minDate:'%y-%M-{%d}',maxDate:'{%y+1}-%M-%d'})" size="17" value=''/>
									&nbsp;&nbsp;<input name="showETime" type="text"
											class="Wdate" onFocus="WdatePicker({lang:'zh-cn',dateFmt:'HH:mm:ss',readOnly:'true',minDate:'%y-%M-{%d}',maxDate:'{%y+1}-%M-%d'})" size="17" value=''/>
										<font color="red">&nbsp;<B>*</B>
										</font>
									</td>
								</tr> -->
								<tr>
									<td colspan="2" align="center" class="list_footer">
									<input class=btn type="button" name="yulan" value="预览" onclick="toyulan()"/>
										&nbsp;
										<input class=btn type="Submit" name="Submit" value="发布" />
										&nbsp;
										<input class=btn type="button" name="button" value="返回"
											onclick="touserlist()" />
										&nbsp;
										<input class=btn type="reset" name="reset" value="重置" />
									</td>
								</tr>
							</table>
						</form>
						
				
				
<p>可以根据自己要求修改css样式<a href="javascript:void(0)" 
				onclick="document.getElementById('light').style.display='block';document.getElementById('fade').style.display='block'">
				点击这里打开窗口</a></p> 
<div id="light" class="white_content"> 
    This is the lightbox content. 
    <a href="javascript:void(0)" 
    onclick="document.getElementById('light').style.display='none';document.getElementById('fade').style.display='none'"> 
    Close</a></div> 
<div id="fade" class="black_overlay"> 
</div> 

    
    
						
	</body>
</html>
<script type="text/javascript">

    </script>
<script language="javascript">
function upload(){
        var file=dwr.util.getValue("file2");
        ChildAgent.upload(file,file.value,function(data){
            advaddform.fileurl.value=data;
        });
        return false;
    }

function toyulan(){
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
      alert("请选择推送日期！");
      advaddform.sendSTime.focus();
      return false;
   	}
   	
	var flag = window.confirm("确定预览广告？");
	if(flag){
		advaddform.action="adv.do?CMD=advyulan";
	    advaddform.submit();
		return true;
	}
}

function typechange(){
   var radio = document.getElementsByName("advType");

   for(var i = 0;i < radio.length;i++){
    if(radio[i].checked==true){
	     if(radio[i].value == "0"){
	     	document.getElementById('trfile').style.visibility="hidden";
	     }else if(radio[i].value == "1"){
	     	document.getElementById('trfile').style.visibility="visible";
	     }
    }
   }
}



function touserlist(){
 document.location.href="javascript: history.back(-1)";
}


function formcheck(){
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

  	var flag = window.confirm("确定添加广告？");
	if(flag){
		advaddform.action="adv.do?CMD=advadd";
	    advaddform.submit();
		return true;
	}

   return false;
}

function goload() 
{ 
typechange();
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
