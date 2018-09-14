<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
<title>公网集群通话系统登录</title>

<link href="${ctx}/css/css.css" type="text/css" rel="stylesheet"/>
<script src="${ctx}/scripts/jquery.min.js"></script>
<script language="javascript">
  
history.go(1); 
var mes="<%=request.getAttribute("message")%>";
  if(mes!="null"){
    alert(mes);
    parent.window.location.href="login.jsp";
    event.returnValue=false;
  }
</script>
<script type="text/javascript">
	/* $(document).ready(function() {
		if(parent.document.getElementById("testIframe") != null){
			$("#loginBody").css("display","none");
			alert("帐号被登录或已退出,请重新登录!");
			parent.location = "${ctx}/login";
		}
	});
	
	function loadimage(){ 
		document.getElementById("randImage").src = "${ctx}/static/images/image.jsp?"+Math.random(); 
	} */
</script>
</head>
<body id="loginBody" class="logbould">
<form name="login"  method="post">
<input type="hidden" id="mac" name="mac" />
<div class="log_bould">
<div class="logging">
<table width="100%" cellspacing="0" cellpadding="0">
	<!-- <tr>
		<td class="log_fong"></td>
		<td height="10px">&nbsp;
			
		</td>
	</tr> -->
	<tr>
      <td height="30" colspan="3">&nbsp;<div id=loader_container style="visibility:hidden;"><div id=loader><div align=center><font color="red">请稍候，系统正在加载中……</font></div></div></div></td>
    </tr>
	<tr>
	    <td width="16%" class="log_fong">用户名：</td>
	    <td width="84%">
       		<input type="text" id="name" name="user_name"  autocomplete="Off" EnableViewState="false" onkeydown="if(event.keyCode==13) event.keyCode=9" class="inping" style="width:285px">   
       	</td>
 	 </tr>
  	<tr>
	    <td class="log_fong pt15">密  码：</td>
	    <td><input type="password" id="pass" name="password" autocomplete="Off" EnableViewState="false"  class="inping mt17" style="width:285px"></td>
  	</tr>
  	<tr>
	    <td class="log_fong pt15"><!-- 验证码： --></td>
	    <td style="line-height: 30px;">
	    	<table cellspacing="0" cellpadding="0">
	    		<tr>
	    			<td>
	    				<!-- <input type="text" id="rand" name="rand" class="inping mt17" style="width:120px"> -->
	    			</td>
	    			<td style="padding-top: 20px;">
	    				<%-- <img name="randImage" id="randImage" src="${ctx}/static/images/image.jsp" title="点击刷新" onclick="loadimage();" style="cursor: pointer;"/> --%>
	    			</td>
	    		</tr>
	    	</table>
	    </td>
 	</tr>
  	<tr>
	    <td></td>
	    <td><input type="submit" name="Submit" value=""  onclick="goto(document.forms[0]);" class="logang mt25"></td>
  	</tr>
</table>
</div>
</div>
</form>
<div class="c"></div>
<div style="text-align:center; color:#4a4a4a; font-size:12px; height:30px; line-height:30px; margin-top:100px;">
	广州德亨信息技术有限公司   版权所有 Copyright@2015
</div>
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
</body>
</html>
