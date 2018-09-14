<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ page import="com.elegps.tscweb.model.TbQuestionInfo" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
 <%    
       TbQuestionInfo tbquestioninfo=(TbQuestionInfo)request.getAttribute("tbquestion");
       String resolvent="";
       if(tbquestioninfo.getResolyent()!=null){
         resolvent=tbquestioninfo.getResolyent();
       }
       String solve_man="";
       if(tbquestioninfo.getSolve_Man()!=null){
         solve_man=tbquestioninfo.getSolve_Man();
       }
 
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
    <title>修改客户问题信息</title>
  </head>
  
  <body onload="goload()">
 <td background="images/gw_06.gif">&nbsp;</td>
    <td width="84%" valign="top"><table width="100%" height="363" border="0" cellpadding="0" cellspacing="0"> <tr>
        <td height="5">&nbsp;</td>
        <td valign="top"><table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
          <tr>
            <td width="3%" height="30" align="center" background="images/3_04.gif"><img src="images/arrow3.gif" width="20" height="19"></td>
            <td width="45%" background="images/3_04.gif"><span class="STYLE4">您现在的位置：<span class="STYLE7">修改客户问题信息</span></span></td>
            <td width="52%"><img src="images/3_03.jpg" width="518" height="35"></td>
          </tr>
        </table>   
        <br>
        <br>       
  <form name="questionmodify"  method="post"  action="">
  <table align="center" width="450"  border="1" bordercolor="#C1DAD7">
    <tr>
       <td colspan="2" align="center"  class="list_header"><span class="STYLE77">修改客户问题信息</span></td>
    </tr>
     <tr>
            <td width="15%" align="right"><label>客户名称:</label></td>
              <td width="85%"><input style="width:180px;" name="cep" type="text" class="checkbox" size="15" value="<%=tbquestioninfo.getCep()%>"><font color="red">&nbsp;<B>&nbsp;*</B></font></td>
    </tr>
    <tr>
            <td width="15%" align="right"><label>客户联系人:</label></td>
              <td width="85%"><input style="width:180px;" name="cname" type="text" class="checkbox" size="15" value="<%=tbquestioninfo.getCname()%>"><font color="red">&nbsp;<B>&nbsp;*</B></font></td>
    </tr>
	 <tr>
              <td width="110" align="right"><label>客户电话:</label></td>
              <td ><input style="width:180px;" name="telephone" type="text" class="checkbox" size="15" value="<%=tbquestioninfo.getTelephone()%>"><font color="red">&nbsp;<B>&nbsp;*</B></font></td>
    </tr>
    <tr>
       <td width="15%" align="right"><div align="right">类型:</div></td>  
     <td  width="85%" align="left" >
      <div align="left">
        <select name="type" id="type" style="{width:180;margin:0;FONT-SIZE:9pt;FONT-WEIGHT:normal;}" onchange="agentchang(document.forms[0])">
	      <option value="1">硬件</option>
	      <option value="2">GPS监控端</option>
	      <option value="3">一呼通语音平台</option>
	      <option value="4">GPS数据</option>
	      <option value="5">语音</option>
	      <option value="6">未知</option>
        </select>
        <font color="red">&nbsp;<B>*</B></font>      </div></td>
     </tr>
     <tr>
       <td width="15%" align="right"><div align="right">问题内容:</div></td>  
    <td width="85%" align="left" >
      <div align="left">
        <textarea name="question" cols="50" rows="5"><%=tbquestioninfo.getContent()%></textarea>
        <font color="red">&nbsp;<B>*</B></font>      </div></td>
     </tr>
     <tr>
    <td width="15%" align="right"><div align="right">跟进情况:</div></td>
    <td width="85%" align="left" >
      <div align="left">
        <textarea name="resolvent" cols="50" rows="5"><%=resolvent%></textarea>
      </div></td>
     </tr>
     <tr>
              <td width="110" align="right"><label>协助人员:</label></td>
              <td ><input style="width:180px;" name="solve_man" type="text" class="checkbox" size="30" value="<%=solve_man%>"/></td>
    </tr>
    <tr>
              <td align="right">问题状态:</td>
              <td width="139" align="left">
				<div align="left">
				  <select name="state" id="state"
				    style="width: 180; margin: 0; FONT-SIZE: 9pt; FONT-WEIGHT: normal;">
					<option value="0">未解决</option>
					<option value="1">已解决且回访</option>
				 </select>
				</div></td>
      
    </tr>
   
	<tr>
      <td colspan="2" align="center" class="list_footer">
          <input class=btn  type="button" name="button1" value="提交" onclick="goto(document.forms[0])"/>&nbsp;
     
        <input class=btn  type="button" name="button" value="返回" onclick="toquestionlist()" />
      </td>
    </tr>
  </table>
     <input type="hidden" name="id"	value="<%=tbquestioninfo.getId()%>" />
  </form></td></tr></table>
</body>
</html>

<script language="javascript">
 function toquestionlist(){  
  document.location.href="question.do?CMD=question_search";
 }


function goto(form){
    var tel=questionmodify.telephone.value;
     if(trim(questionmodify.cep.value)==""){
	     alert("请输入客户名称!");
	     questionmodify.cep.focus();
	     return false;
	    }
	  
	   if(trim(questionmodify.cname.value)==""){
	     alert("请输入客户联系人!");
	     questionmodify.cname.focus();
	     return false;
	   }
 	 
 	 if(tel!=""){ 
      if (tel.isMobile()||tel.isTel())  {  
            tel=tel.Trim();            
        }
        else {  
            alert("请输入正确的手机号码(11位数字)如:13584754875或电话号码如:020-5487514"); 
            questionmodify.telephone.focus();
            return false;        
        }          
   }else {
      alert("客户电话不能为空!");
	  questionmodify.telephone.focus();
	  return false;
   }
   
	if(trim(questionmodify.question.value)==""){
     alert("请输入问题描述!");
     questionmodify.question.focus();
     return false;
 	 }
 	 
 	 if(questionmodify.state.value=="1"){
 	   if(trim(questionmodify.resolvent.value)==""){
         alert("请输入解决方法!");
         questionmodify.resolvent.focus();
         return false;
 	    }
 	    if(trim(questionmodify.solve_man.value)==""){
         alert("请输入技术人员!");
         questionmodify.solve_man.focus();
         return false;
 	    }
 	 }
 	 
 var flag = window.confirm("确定要修改此记录吗？");
 if(flag){
   form.action="question.do?CMD=modify";
   form.submit(); 
  }
}



function goload() 
{
 var state="<%=tbquestioninfo.getState()%>";
 var type="<%=tbquestioninfo.getType()%>";
 document.getElementById("state").value=state; 
 document.getElementById("type").value=type;
}


String.prototype.Trim = function() {  
  var m = this.match(/^\s*(\S+(\s+\S+)*)\s*$/);  
  return (m == null) ? "" : m[1];  
}

String.prototype.isMobile = function() {  
 <!-- return (/^(?:13\d|15[89])-?\d{5}(\d{3}|\*{3})$/.test(this.Trim())); --> 
  return (/^(?:13\d|15\d)-?\d{5}(\d{3}|\*{3})$/.test(this.Trim())); 
} 

String.prototype.isTel = function()
{
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
</script>