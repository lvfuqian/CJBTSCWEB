<%@ page language="java" pageEncoding="UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@ page import="com.elegps.tscweb.model.TbRoleInfo"%>
<%@ page import="com.elegps.tscweb.model.TbAgentInfo"%>
<%@ page import="com.elegps.tscweb.model.TbEnterpriseInfo"%>
<%@ page import="java.util.List"%>


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
  <script type='text/javascript' src='dwr/interface/ChildAgent.js'></script>
  <script type='text/javascript' src='dwr/engine.js'></script>
  <script type='text/javascript' src='dwr/util.js'></script>
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
		<title>增加用户信息</title>
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
									<span class="STYLE4">您现在的位置：<span class="STYLE7">用户添加</span>
									</span>
								</td>
								<td width="52%">
									<img src="images/3_03.jpg" width="518" height="35">
								</td>
							</tr>
						</table>
						<br>
						<br>
						<form name="useraddform" onSubmit="return formcheck();"
							method="post" action="user.do?CMD=add">
							<table align="center" width="450" border="1"
								bordercolor="#C1DAD7">
								<tr>
									<td colspan="2" align="center" class="list_header">
										<span class="STYLE77">用户添加</span>
									</td>
								</tr>
								<tr>
									<td align="right">
										用户名称:
									</td>
									<td>
										<input name="user_name" type="text" class="checkbox" size="30"
											value='${requestScope.username}'>
										<font color="red">&nbsp;<B>*</B>
										</font>
									</td>

								</tr>
								<tr>

									<td align="right">
										<label>
											登陆密码:
										</label>
									</td>
									<td>
										<input name="password" type="password" class="checkbox"
											size="30" value='${requestScope.password}'>
										<font color="red">&nbsp;<B>*</B>
										</font>
									</td>

								</tr>
								<tr>
									<td align="right">
										<label>
											确认登陆密码:
										</label>
									</td>
									<td>
										<input name="password1" type="password" class="checkbox"
											size="30" value='${requestScope.password1}'>
										<font color="red">&nbsp;<B>*</B>
										</font>
									</td>

								</tr>
								<tr>
									<td align="right">
										角色名:
									</td>
									<td align="left">
										<select name="roleid" id="roleid"
											style="width: 180; margin: 0; FONT-SIZE: 9pt; FONT-WEIGHT: normal;" onchange="rolechange(this)">
										 	<%
										 	List listrole = (List) request.getAttribute("listrole");
										 	//登录角色不是代理商（2：rid）执行
										 	if (listrole != null) {
										 		if(Integer.parseInt(request.getSession().getAttribute("roleId").toString()) != 2){
										 		%>
											<option value="0">
												----请选择----
											</option>
											<%
													for (int i = 0; i < listrole.size(); i++) {
														TbRoleInfo tbroleinfo = new TbRoleInfo();
														tbroleinfo = (TbRoleInfo) listrole.get(i);
														
											%>
											<option value="<%=tbroleinfo.getRoleId()%>"><%=tbroleinfo.getRoleName()%></option>
											<%	
													}
												}else{
													for(int i = 0; i < listrole.size(); i++){
														TbRoleInfo tbroleinfo = new TbRoleInfo();
														tbroleinfo = (TbRoleInfo) listrole.get(i);
														//登录角色是代理商，下拉只显示企业用户（3）
														if(tbroleinfo.getRoleId() == 3){
											%>
											<option selected="selected" value="<%=tbroleinfo.getRoleId()%>"><%=tbroleinfo.getRoleName()%></option>
											<%
														}												
													}
												}
											}
											%>
										</select>
										<font color="red">&nbsp;<B>*</B>
										</font>
									</td>
								</tr>
								<tr>
									<td align="right">
										代理商:
									</td>
									<td align="left">
										<select name="agentid" id="agentid"
											style="width: 180; margin: 0; FONT-SIZE: 9pt; FONT-WEIGHT: normal;"
											onchange="agentchang(document.forms[0])">
											<%
												List listagent = (List) request.getAttribute("listagent");
												
										 		if (listagent != null) {
										 			//登录角色不是代理商（2：rid）执行
										 			if(Integer.parseInt(request.getSession().getAttribute("roleId").toString())!= 2){
											%>
											<option value="0">
												----请选择----
											</option>
											<%
														for (int i = 0; i < listagent.size(); i++) {
															TbAgentInfo agentinfo = new TbAgentInfo();
															agentinfo = (TbAgentInfo) listagent.get(i);
											%>
											<option value="<%=agentinfo.getAgent_Id()%>"><%=agentinfo.getAgent_Name()%></option>
											<%
														}
													}else{
														for (int i = 0; i < listagent.size(); i++) {
															TbAgentInfo agentinfo = new TbAgentInfo();
															agentinfo = (TbAgentInfo) listagent.get(i);
															//登录角色是代理商，下拉只显示代理商
															//if(agentinfo.getAgent_Id()== Integer.parseInt(request.getSession().getAttribute("agentId").toString())){
											%>
											<option selected="selected" value="<%=agentinfo.getAgent_Id()%>"><%=agentinfo.getAgent_Name()%></option>
											<%
															}
														//}
													}
												}
											%>
										</select>
										<font color="red">&nbsp;<B>*</B>
										</font>
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
									<td align="right">
										用户单位:
									</td>
									<td align="left">
										<select name="epid" id="epid"
											style="width: 180; margin: 0; FONT-SIZE: 9pt; FONT-WEIGHT: normal;">
											<option value="0">
												所有用户单位
											</option>
											<%
												List listep = (List) request.getAttribute("listep");
												if (listep != null) {
													for (int i = 0; i < listep.size(); i++) {
														TbEnterpriseInfo epinfo = new TbEnterpriseInfo();
														epinfo = (TbEnterpriseInfo) listep.get(i);
											%>
											<option value="<%=epinfo.getEp_Id()%>"><%=epinfo.getEp_Name()%></option>
											<%
												}
												}
											%>
										</select>
										<font color="red">&nbsp;<B>*</B>
										</font>
									</td>
								</tr>
								<tr>
									<td colspan="2" align="center" class="list_footer">
										<input class=btn type="Submit" name="Submit" value="提交" />
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

<script language="javascript"><!--
function touserlist(){
 document.location.href="user.do?CMD=user_search";
}


function formcheck(){
 if(useraddform.user_name.value=="")
   { 
      alert("用户名为空，请输入用户名");
      useraddform.user_name.focus();
      return false;
   }
 
 if(useraddform.password.value=="")
   { 
      alert("密码不能为空，请输入密码");
      useraddform.password.focus();
      return false;
   }
 
 if(trim(useraddform.password.value).length<6)
   { 
      alert("密码长度必须大于6位，请输入密码");
      useraddform.password.focus();
      return false;
   }
   
  if(useraddform.password1.value=="")
   { 
      alert("确认密码不能为空，请输入确认密码");
      useraddform.password1.focus();
      return false;
   }
 
 if(trim(useraddform.password1.value).length<6)
   { 
      alert("确认密码长度必须大于6位，请输入确认密码");
      useraddform.password1.focus();
      return false;
   }
   
   
   if(useraddform.password.value!=useraddform.password1.value)
   {
      alert("密码输入不一至，请输入密码");
      useraddform.password.value="";
      useraddform.password1.value="";
      useraddform.password.focus();
      return false;
   }
   
   
  if(useraddform.roleid.value=="" ||useraddform.roleid.value=="0" ){
     alert("请选择角色!");
     useraddform.roleid.focus();
     return false;
   }
   
   if(trim(useraddform.agentid.value)=="" || trim(useraddform.agentid.value)=="0"){
     alert("请选择代理商!");
     useraddform.agentid.focus();
     return false;
   }
   if(useraddform.childagentid.value==""){
     alert("请选择二级代理!");
     useraddform.childagentid.focus();
     return false;
   }
   
   if(useraddform.epid.value==""){
     alert("请选择用户单位!");
     useraddform.epid.focus();
     return false;
   }
 
   return true;
}

function rolechange(selectrole){
	var selroleval = selectrole.value;
	$("#agentid").find('option').eq(0).attr("selected",true);
	$("#childagentid").find('option').eq(0).attr("selected",true);
	$("#epid").find('option').eq(0).attr("selected",true);
		for(var i = 0; i < $("#agentid").find('option').length; i++){
			$("#agentid").find('option').eq(i).attr("disabled",false);
			if(selroleval == "1" || selroleval == "4" || selroleval == "35" || selroleval == "36" || selroleval == "40" ){
				if($("#agentid").find('option').eq(i).val() != "-1"){
					$("#agentid").find('option').eq(i).attr("disabled","disabled");
				}
			}else{
				if($("#agentid").find('option').eq(i).val() == "-1"){
					$("#agentid").find('option').eq(i).attr("disabled","disabled");
				}
			}
		}
	}


function agentchang(form){
	if(form.agentid.value==""){
		form.epid.options.length=1;
		return false;
	}
	
 var childagentselect= document.getElementById("childagentid");
  form.childagentid.options.length=2; 
  var parentagent=form.agentid.value;
  if(parentagent!=null){
        ChildAgent.getchiledAgent(parentagent,function(data){
         if(data!=null){
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
	
	//getEpinfo();
}

function goload() 
{ 
//getEpinfo();
  var role="<%=request.getAttribute("roleid")%>";
  var agent="<%=request.getAttribute("agentid")%>";
  var ep="<%=request.getAttribute("epid")%>";
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
  
} 
function childagentchang(){
		var epselect= document.getElementById("epid");
        var pagent=useraddform.agentid.value;
        var cagent=useraddform.childagentid.value;
        useraddform.epid.options.length=1;
        if(cagent == ""){
        	useraddform.epid.options.length=1;
        }else {
        	getEpinfo();
        }
        
}
var a_id=<%=Integer.parseInt(request.getSession().getAttribute("agentId").toString())%>;
  		var r_id=<%=Integer.parseInt(request.getSession().getAttribute("roleId").toString())%>;
  		var ep_id=<%=Integer.parseInt(request.getSession().getAttribute("epId").toString())%>;
function getEpinfo(){
   var epselect= document.getElementById("epid");
        var agentid=useraddform.agentid.value;
        var cagent=useraddform.childagentid.value;
        useraddform.epid.options.length=1;
        if(r_id != 3 && r_id != 4){
        ChildAgent.getEpinfoByAId(agentid,cagent,a_id,r_id,function(data){
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
        	ChildAgent.getEpinfoByEId(agentid,cagent,ep_id,r_id,function(data){
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


--></script>
