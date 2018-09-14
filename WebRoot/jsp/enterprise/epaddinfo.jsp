<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="com.elegps.tscweb.model.TbAgentInfo"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>


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
    <title>增加企业信息</title>
  </head>
  
  <body onload="goload()">
 <td background="images/gw_06.gif">&nbsp;</td>
    <td width="84%" valign="top"><table width="100%" height="363" border="0" cellpadding="0" cellspacing="0"> <tr>
        <td height="5">&nbsp;</td>
        <td valign="top"><table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
          <tr>
            <td width="3%" height="30" align="center" background="images/3_04.gif"><img src="images/arrow3.gif" width="20" height="19"></td>
            <td width="45%" background="images/3_04.gif"><span class="STYLE4">您现在的位置：<span class="STYLE7">企业信息添加</span></span></td>
            <td width="52%"><img src="images/3_03.jpg" width="518" height="35"></td>
          </tr>
        </table>
        <br>
        <br>
  <form name="epaddform" onSubmit="return formcheck();" method="post"  action="ep.do?CMD=add">
  <table align="center" width="450"  border="1" bordercolor="#C1DAD7">
   
     
    <tr>
      <td colspan="2" align="center"  class="list_header"><span class="STYLE77">企业信息添加</span></td>
    </tr>
     <tr>
     <td align="right">总部/一级代理商名称:</td>
     <td width="10%" align="left">
        <select name="pagentid" id="pagentid"  style="{width:180;margin:0;FONT-SIZE:9pt;FONT-WEIGHT:normal;}" onchange="parentagentchang()">
		<% 
       List parentagentlist =(List)request.getAttribute("Pagentlist");
       if(parentagentlist!=null){
      	for (int i= 0; i < parentagentlist.size(); i++) {
				 TbAgentInfo agentinfo=new TbAgentInfo();
				  agentinfo = (TbAgentInfo)parentagentlist.get(i);
				%>
	      <option value="<%=agentinfo.getAgent_Id()%>"><%=agentinfo.getAgent_Name()%></option>
		  <%} }%>
        </select><font color="red">&nbsp;<B>*</B></font>
    </td>
	</tr> 
	  <tr>
     <td align="right">企业/二级代理商名称:</td>
      <td>
        <select name="childagentid" id="childagentid"  style="{width:180;margin:0;FONT-SIZE:9pt;FONT-WEIGHT:normal;}">
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
														<option value="">
															-----请选择-----
														</option>
          												<option value="-1">直属企业</option>													
														<%
															}
														 %>	
          	<% 
            List childagentlistid =(List)request.getAttribute("Cagentlist");
            if(childagentlistid!=null){
        	for (int i= 0; i < childagentlistid.size(); i++) {
				 TbAgentInfo agentinfo=new TbAgentInfo();
				  agentinfo = (TbAgentInfo)childagentlistid.get(i);
				%>
	      <option value="<%=agentinfo.getAgent_Id()%>"><%=agentinfo.getAgent_Name()%></option>
		  <%} }%>
         </select><font color="red">&nbsp;<B>*</B></font>
       </td>
	</tr> 
   <tr>
              <td width="15%" align="right"><label>名称:</label></td>
              <td width="35%"><input style="width:180px;" name="ep_name" type="text" class="checkbox" size="30"><font color="red">&nbsp;<B>*</B></font></td>
    </tr>
    <tr>
              <td  align="right">
                地址:</td>
              <td><input style="width:180px;" name="ep_address" type="text" class="checkbox" size="30"><font color="red">&nbsp;<B>*</B></font></td>
      
    </tr>
    <tr>
              <td align="right"><label>联系人:</label></td>
              <td><input style="width:180px;" name="ep_man" type="text" class="checkbox" size="30"><font color="red">&nbsp;<B>*</B></font></td>
    </tr>
    <tr>
              <td  align="right"><label>电话:</label></td>
              <td><input style="width:180px;" name="ep_tel" type="text" class="checkbox" size="30"><font color="red">&nbsp;<B>*</B></font></td>
     
    </tr>
     <tr>
              <td align="right"><label>联系人:</label></td>
              <td><input style="width:180px;" name="ep_man1" type="text" class="checkbox" size="30"></td>
    </tr>
    <tr>
              <td  align="right"><label>电话:</label></td>
              <td><input style="width:180px;" name="ep_tel1" type="text" class="checkbox" size="30"></td>
     
    </tr>
     <tr>
              <td align="right"><label>电子邮箱:</label></td>
              <td><input style="width:180px;" name="ep_email" type="text" class="checkbox" size="30"></td>
    </tr>
    <tr>
              <td align="right"><label>网址:</label></td>
              <td><input style="width:180px;" name="ep_url" type="text" class="checkbox" size="30"></td>
    </tr>
   
    <tr>
              <td align="right"><label>QQ:</label></td>
              <td><input style="width:180px;" name="ep_qq" type="text" class="checkbox" size="30"></td>
    </tr>
    <tr>
              <td align="right"><label>备注:</label></td>
              <td><input style="width:180px;" name="ep_remark" type="text" class="checkbox" size="30"></td>
    </tr>
	
	<tr>
       <td colspan="2" align="center" class="list_footer">
          <input class=btn type="Submit" name="Submit" value="提交" />&nbsp;
        <input class=btn type="button" name="button" value="返回" onclick="toeplist()" />&nbsp;
        <input class=btn type="reset" name="reset" value="重置" />
      </td>
    </tr>
  </table></body>
</html>

<script language="javascript">
function toeplist(){
document.location.href="javascript: history.back(-1)";  
}

function formcheck(){
 if(trim(epaddform.pagentid.value)=="")
   { 
      alert("一级代理商名称不能为空，请选择一级代理商名称!");
      epaddform.pagentid.focus();
      return false;
   }
   
 if(trim(epaddform.childagentid.value)=="")
   { 
      alert("请选择二级代理商!");
      epaddform.childagentid.focus();
      return false;
   }
   
 if(trim(epaddform.ep_name.value)=="")
	 {
 	 alert("请输入名称!");
 	 epaddform.ep_name.focus();
 	 return false;
	 }
 if(trim(epaddform.ep_address.value)==""){
     alert("请输入地址!");
 	 epaddform.ep_address.focus();
 	 return false;
   }
 if(trim(epaddform.ep_man.value)==""){
     alert("请输入联系人!");
 	 epaddform.ep_man.focus();
 	 return false;
   }
   
 if(epaddform.ep_tel.value!=""){ 
      if (epaddform.ep_tel.value.isMobile()||epaddform.ep_tel.value.isTel())  {  
            epaddform.ep_tel.value = epaddform.ep_tel.value.Trim();  
        } 
        else {  
            alert("请输入正确的手机号码或电话号码\n\n例如:13800138000或010-3614072"); 
            epaddform.ep_tel.focus();
            return false;        
        }          
  }else{
     alert("请输入联系电话!");
 	 epaddform.ep_tel.focus();
 	 return false;
  } 
  
 if(epaddform.ep_email.value!=""){
    if(epaddform.ep_email.value.indexOf("@")==-1){
    alert('请输入你的正确E-mail，必须包括“@”!');
    epaddform.ep_email.focus();
    return false;
    }
   tempmsg=epaddform.ep_email.value.substring(0,epaddform.ep_email.value.indexOf("@"));
   if(tempmsg.length<3){
    alert('请输入你的完整的E-mail!\"@\"前面的字符长度不能小于3位！');
    epaddform.ep_email.focus();
    return false;
   }
   if(!((epaddform.ep_email.value.indexOf(".com")!=-1)||(epaddform.ep_email.value.indexOf(".net")!=-1)||(epaddform.ep_email.value.indexOf(".edu")!=-1))){
    alert('你邮箱的后缀名不正确或后缀名是大写！例如:china@sohu.com');
    epaddform.ep_email.focus();
    return false;
  } 
  tempmsg=epaddform.ep_email.value.substring((epaddform.ep_email.value.indexOf("@")+1),epaddform.ep_email.value.indexOf("."));
  if(tempmsg.length<2){
    alert('请输入你邮箱的完整形式！\"@\"和\".\"之间的字符长度不小于2');
    epaddform.ep_email.focus();
    return false;
  }
 } 
  
  if(epaddform.ep_qq.value!=""){
     var ep_qq=epaddform.ep_qq.value;
 	 for (var i=0; i<ep_qq.length; i++)	
		{
		charcode = ep_qq.charCodeAt(i);
		if (charcode < 48 || charcode > 57){
		    alert("QQ号码只能是由数字组成!请重新输入");
		    epaddform.ep_qq.focus();
			return false;
			}
		}
   } 
   
   
  return true;
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
    //"兼容格式: 国家代码(2到3位)-区号(2到3位)-电话号码(7到8位)-分机号(3位)"
    //return (/^(([0\+]\d{2,3}-)?(0\d{2,3})-)?(\d{7,8})(-(\d{3,}))?$/.test(this.Trim()));
    return (/^(([0\+]\d{2,3}-)?(0\d{2,3})-)(\d{7,8})(-(\d{3,}))?$/.test(this.Trim()));
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


<!-- 装入上次查询用户类型查询、在线状态查询、用户有效状态查询的装态 ->
function goload() 
{ 
  var pagentid=parseInt(<%=request.getAttribute("pagentid")%>);
  var childagentid=parseInt(<%=request.getAttribute("childagentid")%>);
  document.getElementById("pagentid").value=pagentid;
  //document.getElementById("childagentid").value=childagentid;
  var r_id=<%=Integer.parseInt(request.getSession().getAttribute("roleId").toString())%>;
  if(r_id == 2 || r_id == 3 || r_id == 4 ){
	 document.getElementById("pagentid").length = 1;
	}
} 

function parentagentchang(){
  var childagentselect= document.getElementById("childagentid");
  epaddform.childagentid.options.length=2; 
  var parentagent=epaddform.pagentid.value;
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
}
</script>
