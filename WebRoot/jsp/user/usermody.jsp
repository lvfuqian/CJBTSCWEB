<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ page import="com.elegps.tscweb.model.TbUserInfo" %>
<%@ page import="com.elegps.tscweb.model.TbRoleInfo"%>
<%@ page import="com.elegps.tscweb.model.TbAgentInfo"%>
<%@ page import="com.elegps.tscweb.model.TbEnterpriseInfo"%>
<%@ page import="java.util.List"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
 <% 
       TbUserInfo tbuserinfo=(TbUserInfo)request.getAttribute("tbuserinfo");
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
<script type="text/javascript" src="scripts/jquery.min.js"></script>
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
    <title>修改用户信息</title>
  </head>
  
  <body onload="goload()">
 <td background="images/gw_06.gif">&nbsp;</td>
    <td width="84%" valign="top"><table width="100%" height="363" border="0" cellpadding="0" cellspacing="0"> <tr>
        <td height="5">&nbsp;</td>
        <td valign="top"><table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
          <tr>
            <td width="3%" height="30" align="center" background="images/3_04.gif"><img src="images/arrow3.gif" width="20" height="19"></td>
            <td width="45%" background="images/3_04.gif"><span class="STYLE4">您现在的位置：<span class="STYLE7">修改用户 </span></span></td>
            <td width="52%"><img src="images/3_03.jpg" width="518" height="35"></td>
          </tr>
        </table>   
        <br>
        <br>           
  <form name="usermodify"  method="post"  action="">
  <table align="center" width="450"  border="1" bordercolor="#C1DAD7">
    <tr>
      <td colspan="2" align="center"  class="list_header"><span class="STYLE77">修改用户</span></td>
    </tr> 
    <tr>
              <td align="right"> 
                用户名称:</td>
              <td><input name="user_name" type="text" class="checkbox" size="30" value="<%=tbuserinfo.getUserName()%>"/><font color="red">&nbsp;<B>*</B></font></td>
      
    </tr>
    <tr>
              <td align="right">  
                用户密码:</td>
              <td><input name="user_psw" type="password" class="checkbox" size="30" value="<%=tbuserinfo.getUserPassword()%>"/><font color="red">&nbsp;<B>*</B></font></td>
      
    </tr>
     <tr>
             <td align="right">角色名:</td>
                <td>
              <select name="roleid" id="roleid" style="{width:180;margin:0;FONT-SIZE:9pt;FONT-WEIGHT:normal;}">
  			 <%                                        
  				 List listrole =(List)request.getAttribute("listrole");
  				 if(listrole!=null){
  				 	int role_id = Integer.parseInt(request.getSession().getAttribute("roleId").toString());
  				 	if(role_id == 2){
  				 	//角色为代理商执行（id=2）
  				 		int rid=Integer.parseInt(request.getAttribute("roleid").toString());
  				 		for (int i= 0; i < listrole.size(); i++) {
						 	TbRoleInfo tbroleinfo=new TbRoleInfo();
						 	tbroleinfo = (TbRoleInfo)listrole.get(i);
						 	if(rid == tbroleinfo.getRoleId()){
						 %>
	    				<option selected="selected" value="<%=tbroleinfo.getRoleId()%>"><%=tbroleinfo.getRoleName()%></option>
						<%
								break;
							}
						}
					}else{
   							for (int i= 0; i < listrole.size(); i++) {
								TbRoleInfo tbroleinfo=new TbRoleInfo();
						 		tbroleinfo = (TbRoleInfo)listrole.get(i);
			%>
	    			<option value="<%=tbroleinfo.getRoleId()%>"><%=tbroleinfo.getRoleName()%></option>
			<%} }}%>
		</select><font color="red">&nbsp;<B>*</B></font>
        </td>
    </tr>
    <tr>
             <td align="right">代理商:</td>
                <td align="left">
              <select name="agentid" id="agentid" style="{width:180;margin:0;FONT-SIZE:9pt;FONT-WEIGHT:normal;}" onchange="agentchang(document.forms[0])">
  			 <%                                        
  				 List listagent =(List)request.getAttribute("listagent");
  				 if(listagent!=null){
  				 //角色为代理商执行（id=2）
  				 	int role_id = Integer.parseInt(request.getSession().getAttribute("roleId").toString());
  				 	if(role_id == 2){
  				 		int aid=Integer.parseInt(request.getAttribute("agentid").toString());
  				 		for (int i= 0; i < listagent.size(); i++) {
						 TbAgentInfo agentinfo=new TbAgentInfo();
						 agentinfo = (TbAgentInfo)listagent.get(i);
						 //if(aid == agentinfo.getAgent_Id()){
			%>
	    			<option  selected="selected" value="<%=agentinfo.getAgent_Id()%>"><%=agentinfo.getAgent_Name()%></option>
			<%			
							break;
						//}
						}
  				 	}else{
  				 		for (int i= 0; i < listagent.size(); i++) {
						 TbAgentInfo agentinfo=new TbAgentInfo();
						 agentinfo = (TbAgentInfo)listagent.get(i);
			%>
	    			<option value="<%=agentinfo.getAgent_Id()%>"><%=agentinfo.getAgent_Name()%></option>
			<%}} }%>
		</select><font color="red">&nbsp;<B>*</B></font>
        </td>
    </tr>
    <tr>
								<td align="right">直属企业/二级代理商:</td>
        <td align="left">
         <select name="childagentid" id="childagentid"  style="{width:180;margin:0;FONT-SIZE:9pt;FONT-WEIGHT:normal;}" onchange="childagentchang()">
			<option value="">
			----请选择----
			</option>
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
          												<option value="-1">直属企业</option>													
														<%
															}
														 %>	
          	<% 
              List childagentlistid =(List)request.getAttribute("childagentlistid");
              if(childagentlistid!=null){
      	        for (int i= 0; i < childagentlistid.size(); i++) {
				 TbAgentInfo agentinfo=new TbAgentInfo();
				  agentinfo = (TbAgentInfo)childagentlistid.get(i);
				%>
	       <option value="<%=agentinfo.getAgent_Id()%>"><%=agentinfo.getAgent_Name()%></option>
		    <%} }%>
        </select>
        <font color="red">&nbsp;<B>*</B>
										</font>
     </td></tr>
    <tr>
             <td align="right">用户单位:</td>
                <td align="left">
              <select name="epid" id="epid" style="{width:180;margin:0;FONT-SIZE:9pt;FONT-WEIGHT:normal;}">
              <option value="0">所有用户单位</option>
  			 <%                                        
  				 List listep =(List)request.getAttribute("listep");
  				 if(listep!=null){
   					for (int i= 0; i < listep.size(); i++) {
						 TbEnterpriseInfo epinfo=new TbEnterpriseInfo();
						 epinfo = (TbEnterpriseInfo)listep.get(i);
			%>
	    			<option value="<%=epinfo.getEp_Id()%>"><%=epinfo.getEp_Name()%></option>
			<%} }%>
		</select><font color="red">&nbsp;<B>*</B></font>
        </td>
    </tr>
	<tr>
      <td colspan="2" align="center" class="list_footer">
          <input class="btn" type="button" name="button1" value="提交" onclick="goto(document.forms[0])"/>&nbsp;
        <input class="btn" type="button"  name="button" value="返回" onclick="touserlist()" />
      </td>
    </tr>
  </table>
  	<input type="hidden" name="user_id"	value="<%=tbuserinfo.getUserId()%>" />
  </table>
</body>
</html>

<script language="javascript">
 function touserlist(){  
  document.location.href="user.do?CMD=user_search";
 }
 
function goto(form){
if(usermodify.user_name.value=="")
   { 
      alert("用户名为空，请输入用户名");
      usermodify.user_name.focus();
      return false;
   }
 if(usermodify.user_psw.value=="")
   { 
      alert("密码不能为空，请输入密码");
      usermodify.user_psw.focus();
      return false;
   }
 if(usermodify.user_psw.value.length<6)
   { 
      alert("密码长度必须大于6位，请输入密码");
      usermodify.user_psw.focus();
      return false;
   }
 if(usermodify.roleid.value==""){
      alert("没有选择角色，请选择角色!");
      usermodify.roleid.focus();
      return false;
 }

  if(usermodify.agentid.value==""){
   alert("请选择代理商!");
   usermodify.agentid.focus();
   return false;
 }
    if(usermodify.childagentid.value==""){
     alert("请选择二级代理!");
     useraddform.childagentid.focus();
     return false;
   }
 if(usermodify.epid.value==""){
   alert("请选择用户单位!");
   usermodify.epid.focus();
   return false;
 }
 
var flag = window.confirm("确定要修改此记录吗？");
if(flag){
	form.action="user.do?CMD=usermodify";
	form.submit(); 
}
}


function agentchang(form){
    form.action="user.do?CMD=agentchangeusermdoy&type=<%=tbuserinfo.getUserId()%>";
	form.submit(); 
}


function goload() 
{ 
  var role="<%=request.getAttribute("roleid")%>";
  var agent="<%=request.getAttribute("agentid")%>";
  var ep="<%=tbuserinfo.getEp_Id()%>";
  var ua="<%=request.getAttribute("child")%>";

  document.getElementById("childagentid").value=ua;
  if (role!="null"){
     document.getElementById("roleid").value=role;
  }else{
     document.getElementById("roleid").value="";
  }
  if (agent!="null"){
     document.getElementById("agentid").value=agent; 
  }else{
     document.getElementById("agentid").value="0";
  }
  if (ep!="null"){
     document.getElementById("epid").value=ep; 
  }else{
     document.getElementById("epid").value="0";
  }
  
  		for(var i = 0; i < $("#roleid").find('option').length; i++){
			//$("#roleid").find('option').eq(i).attr("disabled",false);
			if($("#roleid").find('option').eq(i).val() == role){
					$("#roleid").find('option').eq(i).attr("disabled",false);
			}else{
					$("#roleid").find('option').eq(i).attr("disabled","disabled");
			}
		}
} 
</script>