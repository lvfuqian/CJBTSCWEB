<%@ page language="java" pageEncoding="UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ page import="com.elegps.tscweb.model.TbMsInfo"%>
<%@ page import="com.elegps.tscweb.model.TbGpsInfo"%>
<%@ page import="com.elegps.tscweb.model.TbDdmsMsListInfo"%>
<%@ page import="com.elegps.tscweb.model.TbGrpMsListInfo"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.lang.String" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
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
    <title>调度管理批量添加</title>
    
  </head>
  
  <body onload="goload()">
 <td background="images/gw_06.gif">&nbsp;</td>
    <td width="84%" valign="top"><table width="100%" height="363" border="0" cellpadding="0" cellspacing="0"> <tr>
        <td height="5">&nbsp;</td>
        <td valign="top"><table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
          <tr>
            <td width="3%" height="30" align="center" background="images/3_04.gif"><img src="images/arrow3.gif" width="20" height="19"></td>
            <td width="51%" background="images/3_04.gif"><span class="STYLE4">您现在的位置：<span class="STYLE7">调度管理批量添加终端用户</span></span></td>
            <td width="47%"><img src="images/3_03.jpg" width="505" height="35"></td>
          </tr>
        </table>
        <br>
        <br>                                                      
  <form name="ddmspladdform"   method="post"  action="">
  <table align="center" width="500"  border="1" bordercolor="#C1DAD7">
  <tr>
    <td colspan="4"  align="center"  class="list_header"><span class="STYLE77">调度管理批量添加终端用户</span></td>
  </tr>
  <tr>
    <td colspan="4">调度用户ID:
	    <select name="ddmsid" id="ddms_id" style="{width:350;margin:0;FONT-SIZE:9pt;FONT-WEIGHT:normal;}" onchange="ddmschange()">  
   <% 
   List listddms =(List)request.getAttribute("ddmslist");
   if(listddms!=null){
   	for (int i= 0; i < listddms.size(); i++) {
				 TbMsInfo msinfo=new TbMsInfo();
				  msinfo=(TbMsInfo)listddms.get(i);
				%>
	    <option value="<%=msinfo.getMsId()%>"><%=msinfo.getMsId()%>-<%=msinfo.getMsName()%></option>
		<%} }%> 
		</select><font color="red">&nbsp;<B>*</B></font>  
	</td>
  </tr>
  <tr>
    <td width="45%">已经存在的非调度用户列表:<font color="red">&nbsp;<B>*</B></font></td>
    <td width="30" rowspan="2">
       <input class=btn name="button" type="button"  title="全部右移"  onclick="leftallToRight()" value=">>" />  
       <br>
       <input class=btn name="button2" type="button" title="右移" onclick="leftToRight()" value="=>" />
       <br>      
       <input class=btn name="button3" type="button" title="左移" onclick="rightToLeft()" value="<=" />
       <br>    
      <input class=btn name="button4" type="button" title="全部左移" onclick="rightallToLeft()" value="&lt;&lt;" />
      </td>
    <td width="200">所有待选非调度用户列表:<font color="red">&nbsp;<B>*</B></font></td>
  </tr>
  <tr>
    <td height="106">
	<select name="selectmsid"  style="{width:320;margin:0;FONT-SIZE:9pt;FONT-WEIGHT:normal;}" size="13" multiple>
    <% 
     List listms =(List)request.getAttribute("listms");
     if(listms!=null){
   			for (int i= 0; i < listms.size(); i++) {
				 TbMsInfo tbmsinfo=new TbMsInfo();
				  tbmsinfo = (TbMsInfo)listms.get(i);
				  %>
	    <option value="<%=tbmsinfo.getMsId()%>"><%=tbmsinfo.getMsId()%>—<%=tbmsinfo.getMsName()%></option>
		<%} 
		}%>
    </select>	
	</td>
   <td width="200">
	 <select name="selectms"  style="{width:320;margin:0;FONT-SIZE:9pt;FONT-WEIGHT:normal;}" size="13" multiple>
      <% 
     List listmss =(List)request.getAttribute("listnotddms");
     if(listmss!=null){
   		for (int i= 0; i < listmss.size(); i++) {
				 TbMsInfo tbmsinfo=new TbMsInfo();
				  tbmsinfo = (TbMsInfo)listmss.get(i);
				%>
	    <option value="<%=tbmsinfo.getMsId()%>"><%=tbmsinfo.getMsId()%>—<%=tbmsinfo.getMsName()%></option>
		<%} 
		}%>
    </select>
	</td>
  </tr>
   <tr>
    <td colspan="4"  align="center" class="list_footer"> 
	    <input class=btn type="button" name="buttontj" value="提交" onclick="subdoClick(document.forms[0])"/>&nbsp;
        <input class=btn type="button" name="button" value="返回" onclick="togrplist()" /></td>
  </tr>
</table>
  </form>
</body>
</html>

<script type="text/javascript">
 function subdoClick(form){
 var msidlist=document.getElementById("selectmsid");
 if(msidlist.options.length<1){
  	if(confirm("确定移动吗?"))
		{  	
		 form.action="ddmsms.do?CMD=ddmspladd";
         form.submit();
	  }  
	  else{
	   return;
	  }  
 }else{
   for(var i=0,j=msidlist.options.length;i<j;i++){
     msidlist[i].setAttribute('selected',true);
   }
   form.action="ddmsms.do?CMD=ddmspladd";
   form.submit();
 }
}

 function togrplist(){
  url="ddmsms.do?CMD=ddmsms_search";
  document.location.href=url;
}
function ddmschange(){
   var ddmsid=ddmspladdform.ddmsid.value;

   url="ddmsms.do?CMD=ddmschange_search&ddmsid="+ddmsid;
   document.location.href=url;
}
function goload() 
{ 
  var ddmsid="<%=request.getAttribute("ddmsid")%>";
  if (ddmsid!=null){
     document.getElementById("ddms_id").value=ddmsid 
  }else{
    document.getElementById("ddms_id").value="";
  }
}


var leftObj = document.getElementById("selectmsid");
var rightObj = document.getElementById("selectms");

function leftToRight(){
  var i =0;
  var count=0;
  for(i=leftObj.options.length-1;i>=0;i--){
   if(leftObj.options[i].selected){
   var option = document.createElement("OPTION");
 	 option.value=leftObj.options[i].value;
 	 option.text=leftObj.options[i].text;
   rightObj.options.add(option) ;   
   leftObj.options.remove(i) ; 
   }  
  }
}



function leftallToRight(){
  var i =0;
  var count=0;
  for(i=leftObj.options.length-1;i>=0;i--){
   var option = document.createElement("OPTION");
 	 option.value=leftObj.options[i].value;
 	 option.text=leftObj.options[i].text;
   rightObj.options.add(option) ;   
   leftObj.options.remove(i) ;  
  }
}

function rightToLeft(){
  var i =0;
  var count=0;
  for(i=rightObj.options.length-1;i>=0;i--){
   if(rightObj.options[i].selected){
   var option = document.createElement("OPTION");
   option.value=rightObj.options[i].value;
   option.text=rightObj.options[i].text;
   leftObj.options.add(option) ;   
   rightObj.options.remove(i) ; 
   }
  }
}

function rightallToLeft(){
  var i =0;
  var count=0;
  for(i=rightObj.options.length-1;i>=0;i--){
   var option = document.createElement("OPTION");
   option.value=rightObj.options[i].value;
   option.text=rightObj.options[i].text;
   leftObj.options.add(option) ;   
   rightObj.options.remove(i) ; 
   }
}

</script>




