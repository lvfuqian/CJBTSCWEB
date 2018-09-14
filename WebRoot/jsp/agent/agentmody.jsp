<%@ page language="java" pageEncoding="UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>



<%@ page import="com.elegps.tscweb.model.TbAgentInfo"%>

	<%
			TbAgentInfo tbagentinfo = (TbAgentInfo) request
			.getAttribute("agentinfo");
    %>
    <%
    			TbAgentInfo agentname = (TbAgentInfo) request
    			.getAttribute("agentinfo");
    %>
    <%
                TbAgentInfo name = (TbAgentInfo)request
    			.getAttribute("agent_name");
    
     %>
<html>
  <head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
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
		<title>修改代理商信息</title>
	</head>

	<body>
	
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
									<span class="STYLE4">您现在的位置：<span class="STYLE7">代理商修改</span>
									</span>
								</td>
								<td width="52%">
									<img src="images/3_03.jpg" width="518" height="35">
								</td>
							</tr>
						</table>
						<br>
						<br>
						<form name="agentupdaform" onSubmit="return checkagent();"   method="post" action="agent.do?CMD=update&type=<%=request.getAttribute("type")%>">

							<table align="center" width="450" border="1"
								bordercolor="#C1DAD7">
							   <tr>
							     <td colspan="2" align="center"  class="list_header"><span class="STYLE77">代理商修改</span></td>
							   </tr>
								<tr>
									<td align="right">
										<label>
											
										</label>
									</td>
									<td>
										<input name="agentid" readonly=true type="hidden"
											value="<%=tbagentinfo.getAgent_Id()%>"
											style="width: 230; margin: 0; FONT-SIZE: 9pt; FONT-WEIGHT: normal;">
									</td>
								</tr>
								<tr>
								<%
									if(tbagentinfo.getAgent_PId()==0){%>
									<td align="right">
										<label>
									    代理商类型



										</label>
									</td>
									<td>
										<input name="agentpid" readonly=true
											value="一级代理商"
											style="width: 230; margin: 0; FONT-SIZE: 9pt; FONT-WEIGHT: normal;">
									</td>
									<%}
									%>
							   <%
									if(tbagentinfo.getAgent_PId()!=0){%>
									<td align="right">
										<label>
											父级代理商:
										</label>
									</td>
									<td>
										<input name="agentpid" readonly=true
											value="<%=name.getAgent_Name() %>"
											style="width: 230; margin: 0; FONT-SIZE: 9pt; FONT-WEIGHT: normal;">
									</td>
									<%}
									 %>
								</tr>
								<tr>
									<td align="right">
										<label>
											名称:
										</label>
									</td>
									<td>
										<input style="width: 230px;" class="checkbox" type="text"
											name="agentname" value="<%=tbagentinfo.getAgent_Name()%>" />
										<font color="red">&nbsp;<B>*</B> </font>
									</td>
								</tr>
								<tr>
									<td align="right">
										<label>
											地址:
										</label>
									</td>
									<td>
										<input style="width: 230px;" class="checkbox" type="text"
											name="agentaddress" value="<%=tbagentinfo.getAgent_Address()%>" />
										<font color="red">&nbsp;<B>*</B> </font>
									</td>
								</tr>
								<tr>
									<td align="right">
										<label>
											联系人:
										</label>
									</td>
									<td>
										<input style="width: 230px;" class="checkbox" type="text"
											name="agentman" value="<%=tbagentinfo.getAgent_Man()%>" />
											<font color="red">&nbsp;<B>*</B> </font>
									</td>
								</tr>
								<tr>
									<td align="right">
										<label>
											电话:
										</label>
									</td>
									<td>
										<input style="width: 230px;" class="checkbox" type="text"
											name="agenttel" value="<%=tbagentinfo.getAgent_Tel()%>" />
											<font color="red">&nbsp;<B>*</B> </font>
									</td>
								</tr>
								<tr>
									<td align="right">
										<label>
											联系人:
										</label>
									</td>
									<td>
										<input style="width: 230px;" class="checkbox" type="text"
											name="agentman1" value="<%=tbagentinfo.getAgent_Man1()!=null?tbagentinfo.getAgent_Man1():""%>" />
									</td>
								</tr>
								<tr>
									<td align="right">
										<label>
											电话:
										</label>
									</td>
									<td>
										<input style="width: 230px;" class="checkbox" type="text"
											name="agenttel1" value="<%=tbagentinfo.getAgent_Tel1()!=null?tbagentinfo.getAgent_Tel1():""%>" />
									</td>
								</tr>
								<tr>
									<td align="right">
										<label>
											邮箱:
										</label>
									</td>
									<td>
										<input style="width: 230px;" class="checkbox" type="text"
											name="agentemail" value="<%=tbagentinfo.getAgent_Email()%>" />
									</td>
								</tr>
								<tr>
									<td align="right">
										<label>
											网址:
										</label>
									</td>
									<td>
										<input style="width: 230px;" class="checkbox" type="text"
											name="agenturl" value="<%=tbagentinfo.getAgent_URL()%>" />
									</td>
								</tr>
							
								<tr>
									<td align="right">
										<label>
											QQ号码:
										</label>
									</td>
									<td>
										<input style="width: 230px;" class="checkbox" type="text"
											name="agentqq" value="<%=tbagentinfo.getAgent_QQ()%>" />
									</td>
								</tr>
								<tr>
									<td align="right">
										<label>
											备注:
										</label>
									</td>
									<td>
										<input style="width: 230px;" class="checkbox" type="text"
											name="agentremark" value="<%=tbagentinfo.getAgent_Remark()%>" />
									</td>
								</tr>
								<tr>
									<td colspan="2" align="center" class="list_footer">
										<input class=btn type="Submit" name="Submit" value="提交"
											/>
										<input class=btn type="button" name="button" value="返回"
											onclick="toagentlist()" />
									</td>
								</tr>
							</table>
						</form>
	</body>
</html>

<script language="javascript">

function checkagent(){
  var flag = window.confirm("确定要修改此记录吗？");
  if(flag){
	var aname=agentupdaform.agentname.value;
	var address=agentupdaform.agentaddress.value;
	var man=agentupdaform.agentman.value;
	var email=agentupdaform.agentemail.value;
	var tel=agentupdaform.agenttel.value;
	var qq=agentupdaform.agentqq.value;
	if(aname==""){
	alert("名称不能为空!");
	agentupdaform.agentname.focus();
	return false;
	}
	if(address==""){
	alert("地址不能为空!");
	agentupdaform.agentaddress.focus();
	return false;
	}
	if(man==""){
	 alert("联系人不能为空!");
	 agentupdaform.agentman.focus();
	 return false;
	}
	
	if(tel!=""){ 
      if (tel.isMobile()||tel.isTel())  {  
            tel=tel.Trim();  
            
        }
        else {  
            alert("请输入正确的手机号码(11位数字)如:13584754875或电话号码如:020-5487514"); 
            agentupdaform.agenttel.focus();
            return false;        
        }          
   }else{
     alert("联系电话不能为空!");
	 agentupdaform.agenttel.focus();
	 return false;
   }
	
	
	if(email!=""){
    if(email.indexOf("@")==-1){
    alert('请输入你的正确E-mail，必须包括“@”!');
    agentupdaform.agentemail.focus();
    return false;
    }
    tempmsg=email.substring(0,email.indexOf("@"));
    if(tempmsg.length<3){
    alert('请输入你的完整的E-mail!\"@\"前面的字符长度不能小于3位！');
    agentupdaform.agentemail.focus();
    return false;
   }
   if(!((email.indexOf(".com")!=-1)||(email.indexOf(".net")!=-1)||(email.indexOf(".edu")!=-1))){
    alert('你邮箱的后缀名不正确或后缀名是大写！,例如:china@sohu.com');
    agentupdaform.agentemail.focus();
    return false;
  } 
  tempmsg=email.substring((email.indexOf("@")+1),email.indexOf("."));
  if(tempmsg.length<2){
    alert('请输入你邮箱的完整形式！\"@\"和\".\"之间的字符长度不小于2');
    agentupdaform.agentemail.focus();
    return false;
  }
 } 
 
 if(isNaN(qq)){
	alert("qq号码必须是数字");
	return false;
	}
	return true;
 }else{
  return false;
 }
}
	 
	function toagentlist(){
	   document.location.href="javascript: history.back(-1)"; 
	}
	
	String.prototype.Trim = function() {  
  var m = this.match(/^\s*(\S+(\s+\S+)*)\s*$/);  
  return (m == null) ? "" : m[1];  
}

String.prototype.isMobile = function() {  
  <!-- return (/^(?:13\d|15[89])-?\d{5}(\d{3}|\*{3})$/.test(this.Trim())); --> 
    return (/^(?:13\d|14\d|15\d|18\d)-?\d{5}(\d{3}|\*{3})$/.test(this.Trim())); 
} 

String.prototype.isTel = function()
{
    return (/^(([0\+]\d{2,3}-)?(0\d{2,3})-)(\d{7,8})(-(\d{3,}))?$/.test(this.Trim()));
}

//删除字符串左边的空格
function ltrim(str)	
{ 
	if(str.length==0){
		return(str);
		}else
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
	if(str.length==0){
		return(str);
		}
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
