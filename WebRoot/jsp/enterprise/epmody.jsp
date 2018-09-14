<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ page import="com.elegps.tscweb.model.TbEnterpriseInfo" %>
<%@ page import="com.elegps.tscweb.model.TbAgentInfo"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
 <% 
       TbEnterpriseInfo epinfo=(TbEnterpriseInfo)request.getAttribute("epinfo");
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
    <title>企业信息修改</title>
  </head>
  
  <body onload="goload()">
 <td background="images/gw_06.gif">&nbsp;</td>
    <td width="84%" valign="top"><table width="100%" height="363" border="0" cellpadding="0" cellspacing="0"> <tr>
        <td height="5">&nbsp;</td>
        <td valign="top"><table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
          <tr>
            <td width="3%" height="30" align="center" background="images/3_04.gif"><img src="images/arrow3.gif" width="20" height="19"></td>
            <td width="45%" background="images/3_04.gif"><span class="STYLE4">您现在的位置：<span class="STYLE7">企业信息修改 </span></span></td>
            <td width="52%"><img src="images/3_03.jpg" width="518" height="35"></td>
          </tr>
        </table>   
        <br>
        <br>       
  <form name="epmodify"  method="post"  action="">
  <table align="center" width="450"  border="1" bordercolor="#C1DAD7">
    <tr>
       <td colspan="2" align="center"  class="list_header"><span class="STYLE77">企业信息修改</span></td>
    </tr>
     <tr>
     <td align="right">总部/一级代理商名称:</td>
     <td>
        <select name="pagentid" id="pagentid"  style="{width:180;margin:0;FONT-SIZE:9pt;FONT-WEIGHT:normal;}" onchange="parentagentchang()">
		<% 
       List parentagentlist =(List)request.getAttribute("Pagentlist");
       if(parentagentlist!=null){
       		int role_id = Integer.parseInt(request.getSession().getAttribute("roleId").toString());
       		int aid=((TbEnterpriseInfo)request.getAttribute("epinfo")).getAgent_Id();
  			if(role_id == 2 || role_id == 3 || role_id == 4){
  			//角色为代理商,企业执行（id=2，3）
  				
  				for (int i= 0; i < parentagentlist.size(); i++) {
				 	TbAgentInfo agentinfo=new TbAgentInfo();
				  	agentinfo = (TbAgentInfo)parentagentlist.get(i);
				  	//if(aid == agentinfo.getAgent_Id()){
				%>
	      		<option selected="selected" value="<%=agentinfo.getAgent_Id()%>"><%=agentinfo.getAgent_Name()%></option>
		  		<%
		  			//break;
		  			//}
		  		}
  			}else{
  				for (int i= 0; i < parentagentlist.size(); i++) {
				 TbAgentInfo agentinfo=new TbAgentInfo();
				  agentinfo = (TbAgentInfo)parentagentlist.get(i);
				%>
	      	<option value="<%=agentinfo.getAgent_Id()%>"><%=agentinfo.getAgent_Name()%></option>
		  	<%}
  			}
      		 }%>
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
														<option value="-2">
															所有企业
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
              <td  align="right"><label>名称:</label></td>
              <td ><input style="width:180px;" name="ep_name" type="text" class="checkbox" size="30"  value="<%=epinfo.getEp_Name()%>"/><font color="red">&nbsp;<B>*</B></font></td>
    </tr>
   <tr>
              <td align="right">
                地址:</td>
              <td><input style="width:180px;" name="ep_address" type="text" class="checkbox" size="30" value="<%=epinfo.getEp_Address()%>"/><font color="red">&nbsp;<B>*</B></font></td>
      
    </tr>
    <tr>
	          <td align="right">
                联系人:</td>
              <td><input style="width:180px;" name="ep_man" type="text" class="checkbox" size="30" value="<%=epinfo.getEp_Man()%>"/><font color="red">&nbsp;<B>*</B></font></td>
	</tr>
	
	<tr>
	          <td align="right">
                电话:</td>
              <td><input style="width:180px;" name="ep_tel" type="text" class="checkbox" size="30" value="<%=epinfo.getEp_Tel()%>"/><font color="red">&nbsp;<B>*</B></font></td>
	</tr>
	 <tr>
	          <td align="right">
                联系人:</td>
              <td><input style="width:180px;" name="ep_man1" type="text" class="checkbox" size="30" value="<%=epinfo.getEp_Man1()!=null?epinfo.getEp_Man1():""%>"/></td>
	</tr>
	<tr>
	          <td align="right">
                电话:</td>
              <td><input style="width:180px;" name="ep_tel1" type="text" class="checkbox" size="30" value="<%=epinfo.getEp_Tel1()!=null?epinfo.getEp_Tel1():""%>"/></td>
	</tr>
	<tr>
	          <td align="right">
                电子邮箱:</td>
              <td><input style="width:180px;" name="ep_email" type="text" class="checkbox" size="30" value="<%=epinfo.getEp_Email()%>"/></td>
	</tr>
	<tr>
	          <td align="right">
                网址:</td>
              <td><input style="width:180px;" name="ep_url" type="text" class="checkbox" size="30" value="<%=epinfo.getEp_URL()%>"/></td>
	</tr>
	
	<tr>
	          <td align="right">
               QQ:</td>
              <td><input style="width:180px;" name="ep_qq" type="text" class="checkbox" size="30" value="<%=epinfo.getEp_QQ()%>"/></td>
	</tr>
	<tr>
              <td align="right"><label>备注:</label></td>
              <td><input style="width:180px;" name="ep_remark" type="text" class="checkbox" size="30" value="<%=epinfo.getEp_Remark()%>"></td>
    </tr>
	<tr>
      <td colspan="2" align="center" class="list_footer">
          <input class=btn  type="button" name="button1" value="提交" onclick="goto(document.forms[0])"/>&nbsp;
     
        <input class=btn  type="button" name="button" value="返回" onclick="tomslist()" />
      </td>
    </tr>
    <input name="ep_id" type="hidden" value="<%=epinfo.getEp_Id()%>">
  </table></form></td></tr></table>
</body>
</html>

<script language="javascript">
 function tomslist(){  
  document.location.href="javascript: history.back(-1)";  
 <!-- document.location.href="ep.do?CMD=ep_search";  -->
 }
 
function goto(form){
var flag = window.confirm("确定要修改此记录吗？");
 if(flag){
 if(trim(epmodify.ep_name.value)=="")
   { 
      alert("名称为空，请输入名称!");
      epmodify.ep_name.focus();
      return false;
   }
 if(trim(epmodify.ep_address.value)==""){
      alert("地址为空，请输入地址!");
      epmodify.ep_address.focus();
      return false;
   }
   
 if(trim(epmodify.ep_man.value)==""){
      alert("联系人为空，请输入联系人!");
      epmodify.ep_man.focus();
      return false;
   }
 if(epmodify.ep_tel.value!=""){ 
      if (epmodify.ep_tel.value.isMobile()||epmodify.ep_tel.value.isTel())  {  
            epmodify.ep_tel.value = epmodify.ep_tel.value.Trim();   
        } 
        else {  
            alert("请输入正确的手机号码或电话号码\n\n例如:13800138000或010-3614072"); 
            epmodify.ep_tel.focus();
            return false;        
        }          
  }else{
     alert("联系电话为空，请输入联系电话!");
     epmodify.ep_tel.focus();
     return false;
  }
  
 if(epmodify.ep_email.value!=""){
    if(epmodify.ep_email.value.indexOf("@")==-1){
    alert('请输入你的正确E-mail，必须包括“@”!');
    epmodify.ep_email.focus();
    return false;
    }
   tempmsg=epmodify.ep_email.value.substring(0,epmodify.ep_email.value.indexOf("@"));
   if(tempmsg.length<3){
    alert('请输入你的完整的E-mail!\"@\"前面的字符长度不能小于3位！');
    epmodify.ep_email.focus();
    return false;
   }
   if(!((epmodify.ep_email.value.indexOf(".com")!=-1)||(epmodify.ep_email.value.indexOf(".net")!=-1)||(epmodify.ep_email.value.indexOf(".edu")!=-1))){
    alert('你邮箱的后缀名不正确或后缀名是大写！例如:china@sohu.com');
    epmodify.ep_email.focus();
    return false;
  } 
  tempmsg=epmodify.ep_email.value.substring((epmodify.ep_email.value.indexOf("@")+1),epmodify.ep_email.value.indexOf("."));
  if(tempmsg.length<2){
    alert('请输入你邮箱的完整形式！\"@\"和\".\"之间的字符长度不小于2');
    epmodify.ep_email.focus();
    return false;
  }
 } 
  
  if(epmodify.ep_qq.value!=""){
     var ep_qq=epmodify.ep_qq.value;
 	 for (var i=0; i<ep_qq.length; i++)	
		{
		charcode = ep_qq.charCodeAt(i);
		if (charcode < 48 || charcode > 57){
		    alert("QQ号码只能是由数字组成!请重新输入");
		    epmodify.ep_qq.focus();
			return false;
			}
		}
   }    
   form.action="ep.do?CMD=modify&type=<%=request.getAttribute("type")%>";
   form.submit();  
 }
}


<!-- 装入上次查询代理商类型查询-->
function goload() 
{  
  var pagentid=parseInt(<%=request.getAttribute("pagentid")%>);
  var childagentid=parseInt(<%=request.getAttribute("childagentid")%>);
  document.getElementById("pagentid").value=pagentid;
  document.getElementById("childagentid").value=childagentid;

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


function parentagentchang(){
  var childagentselect= document.getElementById("childagentid");
  epmodify.childagentid.options.length=1; 
  var parentagent=epmodify.pagentid.value;
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