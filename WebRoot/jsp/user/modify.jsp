<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
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
FILTER: progid:DXImageTransform.Microsoft.Gradient(GradientType=0, StartColorStr=#ffffff, EndColorStr=#cecfde); 
BORDER-LEFT: #7b9ebd 1px solid; CURSOR: hand; COLOR: black; PADDING-TOP: 2px; BORDER-BOTTOM: #7b9ebd 1px solid
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
.STYLE8 {font-size: 12px}
a:hover {
	font-size: 12px;
	color: #CC00FF;
	text-decoration: underline;
}
-->
</style>
  <script type='text/javascript' src='dwr/interface/UserChanger.js'></script>
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
    <title>修改密码</title>
  </head>
  
  <body>
 <td background="images/gw_06.gif">&nbsp;</td>
    <td width="84%" valign="top"><table width="100%" height="363" border="0" cellpadding="0" cellspacing="0"> <tr>
        <td height="5">&nbsp;</td>
        <td valign="top"><table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
          <tr>
            <td width="3%" height="30" align="center" background="images/3_04.gif"><img src="images/arrow3.gif" width="20" height="19"></td>
            <td width="45%" background="images/3_04.gif"><span class="STYLE4">您现在的位置：<span class="STYLE7">修改密码 </span></span></td>
            <td width="52%"><img src="images/3_03.jpg" width="518" height="35"></td>
          </tr>
        </table>   
        <br>
        <br>           
  <form name="usermodify"  method="post"  action="">
  <table align="center" width="450"  border="1" bordercolor="#C1DAD7">
    <tr>
      <td colspan="2" align="center"  class="list_header"><span class="STYLE77">修改密码</span></td>
    </tr> 
    
    <tr>
              <td width="30%" align="right">  
                原密码:</td>
              <td><input id="ypsw" name="ypsw" type="password" class="checkbox" size="30" /><font color="red">&nbsp;<B>*</B></font></td>
      
    </tr>
    <tr>
              <td width="30%" align="right"> 
                新密码:</td>
              <td><input id="password" name="password" type="password" class="checkbox" size="30" /></td>
      
    </tr>
    <tr>
              <td width="30%" align="right"> 
                确认密码:</td>
              <td><input id="password1" name="password1" type="password" class="checkbox" size="30"/></td>
      
    </tr>
	<tr>
      <td colspan="2" align="center" class="list_footer">
          <input class=btn type="button" name="button1" value="提交" onclick="goto(document.forms[0])"/>&nbsp;
        <input class=btn type="button" name="button" value="返回" onclick="tolist()" />
      </td>
    </tr>
  </table>
  	<input type="hidden" id="uname" name="uname" value="<%=request.getSession().getAttribute("users")%>" />
  </table>
</body>
</html>

<script language="javascript">
 function tolist(){  
   document.location.href="javascript: history.back(-1)"; 
 }
 
function goto(form){
if(usermodify.ypsw.value=="")
   { 
      alert("密码不能为空，请输入密码!");
      usermodify.ypsw.focus();
      return false;
   }
 
 if(usermodify.password.value=="")
   { 
      alert("新密码不能为空，请输入新密码!");
      usermodify.password.focus();
      return false;
   }
   
    if(usermodify.password1.value=="")
   { 
      alert("确认密码不能为空，请输入确认密码!");
      usermodify.password1.focus();
      return false;
   }
 
 if(usermodify.password.value!=usermodify.password1.value)
   { 
      alert("密码不一致，请重新入新密码！");
      usermodify.password.focus();
      return false;
   }
   

 var flag = window.confirm("确定要修改密码吗？");
 if(flag){
        var sname=usermodify.uname.value;
        var ypass= usermodify.ypsw.value;
        UserChanger.getUser(sname,ypass,function(data){
        if(data==false){
            alert("原密码错误，请重新输入密码！");
            usermodify.ypsw.focus();
          }else{
             SetUserName();
          } 
        }); 
	}
}

function SetUserName() 
{    
   var sname=usermodify.uname.value;
   var pass= usermodify.password.value;
   UserChanger.ChangerPassword(sname,pass,function(data1){
       if(data1==false){
            alert("密码修改失败!");
          }else{
            alert("密码修改成功!");
          } 
        });
}

</script>
