<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
response.setHeader("Pragma","No-cache"); 
response.setHeader("Cache-Control","no-cache"); 
response.setDateHeader("Expires", 0); 
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<script language="javascript">
  
history.go(1); 
var mes="<%=request.getAttribute("message")%>";
  if(mes!="null"){
    alert(mes);
    parent.window.location.href="login.jsp";
    event.returnValue=false;
  }
</script>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>公网集群通话系统登录</title>
    
	<META HTTP-EQUIV="pragma" CONTENT="no-cache"> 
	<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache, must-revalidate"> 
    <META HTTP-EQUIV="expires" CONTENT="Wed, 26 Feb 1997 08:21:57 GMT">
    
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
	<BODY onload="goload()" BGCOLOR=#FFFFFF LEFTMARGIN=0 TOPMARGIN=0 MARGINWIDTH=0 MARGINHEIGHT=0>
	<form  name="login"  method="post" >
	<br>
	<br>
	<br>
	<br>
  <table width="782" height="453" border="0" align="center" cellpadding="0" cellspacing="0" style="background:url(images/bg.jpg) no-repeat;">
    <tr>
      <td height="303" colspan="3">&nbsp;<div id=loader_container style="visibility:hidden;"><div id=loader><div align=center><font color="red">请稍候，系统正在加载中……</font></div></div></div></td>
    </tr>
    <tr>
      <td width="281" rowspan="2">&nbsp;</td>
      <td width="252" style="height:5px;" valign="middle"></td>
      <td width="249" rowspan="2">&nbsp;</td>
    </tr>
    <tr>
      <td valign="top"  style="background:url(images/login.jpg) no-repeat;"><table width="100%"border="0" cellpadding="0" cellspacing="0">
          <tr>
            <td width="44%">&nbsp;</td>
            <td height="55" colspan="2" valign="bottom"><input type="text" id="name" name="user_name" autocomplete="Off" EnableViewState="false" onkeydown="if(event.keyCode==13) event.keyCode=9" style="width:95px;border:1px solid #131869"></td>
          </tr>
          <tr>
            <td>&nbsp;</td>
            <td height="29" colspan="2" valign="bottom"><input type="password" id="pass" name="password" autocomplete="Off" EnableViewState="false" style="width:95px;border:1px solid #131869"></td>
          </tr>
         <tr>
            <td height="30" align="right" valign="bottom"><input type="image" name="Submit" src="images/bt1.jpg" onclick="goto(document.forms[0]);this.disabled=true"></td>
            <td width="14%" >&nbsp;</td>
            <td width="42%" valign="bottom"><input type="image" name="imageField2" src="images/bt2.jpg" onclick="document.forms[0].reset()"></td>
         </tr>
      </table></td>
    </tr>
  </table>
</form>
<!-- ImageReady Slices (log1.jpg) -->
<!-- End ImageReady Slices -->
</BODY>

</HTML>
<script language="javascript">

function goload(){
   login.user_name.vlaue="";
   login.password.vlaue="";
   login.user_name.focus();
   var msg="<%=request.getAttribute("msg")%>";
   if(msg!="null")
     {
      alert(msg);
     }
}

 function goto(form){
 if(login.user_name.value=="")
   { 
      alert("用户不能为空，请输入用户名");
      login.user_name.focus();
      return false;
   }
    if(login.password.value=="")
   { 
      alert("密码不能为空，请输入密码");
      login.password.focus();
      return false;
   }
 loading();
 sub(form);
 }
 
 function sub(form){
 form.action="login.do";
 form.submit();
 }
 
 function loading() 
 { 
   var targelem = document.getElementById('loader_container'); 
   targelem.style.visibility='visible';
 } 
 
 
 
function remove_loading() 
 { 
   var targelems = document.getElementById('loader_container'); 
   targelems.style.visibility='hidden';
 } 
 
</script>