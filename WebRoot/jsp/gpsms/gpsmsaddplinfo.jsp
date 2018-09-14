<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ page import="com.elegps.tscweb.model.TbMsInfo"%>
<%@ page import="com.elegps.tscweb.model.TbGpsInfo"%>
<%@ page import="com.elegps.tscweb.model.TbGrpMsListInfo"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.lang.String" %>
<%@ page import="com.elegps.tscweb.model.TbAgentInfo"%>
<%@ page import="com.elegps.tscweb.model.TbEnterpriseInfo"%>

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
.on{
	background-color:yellow;
 	font-size: 18px;
    font-weight:bold; 
  }
-->
</style>     
<script type="text/javascript">
	$(function(){
		//键盘按键弹起时执行
		$("#searchName").keyup(function(){
				var index = $.trim($("#searchName").val().toString());//去掉两头空格
				if(index == ""){
					$("#selectmsid option").removeClass("on");return false;
				}
				var parent = $("#selectmsid");
				$("#selectmsid option").removeClass("on");
				//选择包含文本框值的所有加上focus类样式，并把它（们）移到select的最前面
				$("#selectmsid option:contains('" + index + "')").prependTo(parent).addClass("on");
		});
	//键盘按键弹起时执行
	$("#searchMs").keyup(function(){
		var index = $.trim($("#searchMs").val().toString());//去掉两头空格
		if(index == ""){
			$("#selectms option").removeClass("on");return false;
		}
		var parent = $("#selectms");
		$("#selectms option").removeClass("on");
		//选择包含文本框值的所有加上focus类样式，并把它（们）移到select的最前面
		$("#selectms option:contains('" + index + "')").prependTo(parent).addClass("on");
	});
}); 
</script>
    <title>根据GPS批量增加终端用户信息</title>
  </head>
  
  <body onload="goload()">
 <td background="images/gw_06.gif">&nbsp;</td>
    <td width="84%" valign="top"><table width="100%" height="363" border="0" cellpadding="0" cellspacing="0"> <tr>
        <td height="5">&nbsp;</td>
        <td valign="top"><table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
          <tr>
            <td width="3%" height="30" align="center" background="images/3_04.gif"><img src="images/arrow3.gif" width="20" height="19"></td>
            <td width="51%" background="images/3_04.gif"><span class="STYLE4">您现在的位置：<span class="STYLE7">根据GPS批量增加终端信息</span></span></td>
            <td width="47%"><img src="images/3_03.jpg" width="505" height="35"></td>
          </tr>
        </table>
        <br>
        <br>                                                      
  <form name="gpsmsaddplform"   method="post"  action="">
  <table align="center" width="500"  border="1" bordercolor="#C1DAD7">
  <tr>
    <td colspan="4"  align="center"  class="list_header"><span class="STYLE77">根据GPS批量增加终端用户信息</span></td>
  </tr>
   <tr>
     <td colspan="4">总部/一级代理商名称:
        <select name="pagentid" id="pagentid"  style="{width:350;margin:0;FONT-SIZE:9pt;FONT-WEIGHT:normal;}" onchange="parentagentchang()">
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
     <td colspan="4">企业/二级代理商名称:
        <select name="childagentid" id="childagentid"  style="{width:350;margin:0;FONT-SIZE:9pt;FONT-WEIGHT:normal;}"  onchange="childagentchang()"> 
          <option value="-1">直属企业</option>
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
    <td colspan="4">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;企业名称:
        <select name="ep" id="ep_id" style="{width:350;margin:0;FONT-SIZE:9pt;FONT-WEIGHT:normal;}" onchange="epchang()">
        <option value=""></option>
          <% 
          List listep =(List)request.getAttribute("epList");
          if(listep!=null){
           for (int i= 0; i < listep.size(); i++) {
				 TbEnterpriseInfo epinfo=new TbEnterpriseInfo();
				  epinfo = (TbEnterpriseInfo)listep.get(i);
				%>
	      <option value="<%=epinfo.getEp_Id()%>"><%=epinfo.getEp_Name()%></option>
		  <%} }%>
        </select>
        <font color="red">&nbsp;<B>*</B></font>
      </td>
	</tr> 
  <tr>
    <td colspan="4">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;GPS号:
	    <select name="gpsid" id="gps_id" style="{width:350;margin:0;FONT-SIZE:9pt;FONT-WEIGHT:normal;}" onchange="gpschang()">
	    <option value=""></option>  
   <% 
   List listgps =(List)request.getAttribute("gpsList");
   if(listgps!=null){
   	for (int i= 0; i < listgps.size(); i++) {
				 TbGpsInfo tbgpsinfo=new TbGpsInfo();
				  tbgpsinfo = (TbGpsInfo)listgps.get(i);
				%>
	    <option value="<%=tbgpsinfo.getGpsop_id()%>"><%=tbgpsinfo.getGpsop_id()%>-<%=tbgpsinfo.getGps_name()%></option>
		<%} }%>
		</select><font color="red">&nbsp;&nbsp;<B>*</B></font>  
	</td>
  </tr>
  <tr>
    <td width="45%">已经存在的终端号列表:<font color="red">&nbsp;<B>*</B></font>&nbsp;<input type="text" name="searchName" id="searchName"  class="topssi"  value="输入关键字" style="width: 120px; height: 24px; line-height: 24px; vertical-align: middle; font-size: 18px; padding-left: 6px; padding-right: 10px;"  onfocus="this.className='topssi';this.focus();if(this.value=='输入关键字'){this.value='';}" onblur="this.className='topssi';if(this.value==''){this.value='输入关键字';} "/></td>
    <td width="30" rowspan="2">
       <input class=btn name="button" type="button" title="全部右移" onclick="leftallToRight()" value=">>" />  
       <br>
       <input class=btn name="button2" type="button" title="右移" onclick="leftToRight()" value="=>" />
       <br>      
       <input class=btn name="button3" type="button" title="左移" onclick="rightToLeft()" value="<=" />
       <br>      
       <input class=btn name="button4" type="button" title="全部左移" onclick="rightallToLeft()" value="&lt;&lt;" /></td>
    <td width="45%">所有待选终端号列表:&nbsp;<input type="text" name="searchMs" id="searchMs"  class="topssi"  value="输入关键字" style="width: 120px; height: 24px; line-height: 24px; vertical-align: middle; font-size: 18px; padding-left: 6px; padding-right: 10px;"  onfocus="this.className='topssi';this.focus();if(this.value=='输入关键字'){this.value='';}" onblur="this.className='topssi';if(this.value==''){this.value='输入关键字';} "/></td>
  </tr>
  <tr>
    <td height="106">
	<select name="selectmsid" id="selectmsid" style="{width:320;margin:0;FONT-SIZE:9pt;FONT-WEIGHT:normal;}" size="13" multiple>
    <% 
     List gpslistms =(List)request.getAttribute("listgpsms");
     if(gpslistms!=null){
   		for (int i= 0; i < gpslistms.size(); i++) {
				 Map map = new HashMap();
			     map = (Map) gpslistms.get(i);
				%>
	    <option value="<%=map.get("msid")%>"><%=map.get("msid")%>—<%=map.get("msname")%></option>
		<%} 
		}%>
    </select>	
	</td>
    <td width="200">
	<select name="selectms" id="selectms"  style="{width:320;margin:0;FONT-SIZE:9pt;FONT-WEIGHT:normal;}" size="13" multiple>
    <% 
     List listms =(List)request.getAttribute("msList");
     if(listms!=null){
   		for (int i= 0; i < listms.size(); i++) {
				 TbMsInfo tbmsinfo=new TbMsInfo();
				  tbmsinfo = (TbMsInfo)listms.get(i);
				%>
			    <option value="<%=tbmsinfo.getMsId()%>"><%=tbmsinfo.getMsId()%>—<%=tbmsinfo.getMsName()%>		</option>
		<%} 
		}%>
    </select>
    <input name="changeMs" id="changeMs" type="hidden" />
	</td>
  </tr>
   <tr>
    <td colspan="4"  align="center" class="list_footer"> 
	    <input class=btn type="button" id="buttontj" name="buttontj" value="提交" onclick="subdoClick(document.forms[0])"/>&nbsp;
        <input class=btn type="button" name="button" value="返回" onclick="togpsmspllist()" /></td>
  </tr>
</table>
  </form>
</body>
</html>

<script type="text/javascript">

<!--提交时表单验证-->
function subdoClick(form){
    if(gpsmsaddplform.pagentid.value=="")
	   { 
	      alert("一级代理商名称不能为空，请选择一级代理商名称!");
	      gpsmsaddplform.pagentid.focus();
	      return false;
	   }
	 if(gpsmsaddplform.childagentid.value=="")
	   { 
	      alert("请选择二级代理商!");
	      gpsmsaddplform.childagentid.focus();
	      return false;
	   }
	  if(gpsmsaddplform.ep.value==""){
	     alert("请选择企业!");
	     gpsmsaddplform.ep.focus();
	     return false;
	  }
   if(gpsmsaddplform.gpsid.value==""){
     alert("请选择GPS厂商!");
     gpsmsaddplform.gpsid.focus();
     return false;
  }

 var msidlist=document.getElementById("selectmsid");
 if(msidlist.options.length<1){
  	if (confirm("确定删除所选GPS对应的终端吗?"))
		{  	
		 form.action="gpsms.do?CMD=gpsmsplmsadd";
         form.submit();
	  }  
	  else{
	   return;
	  }  
 }else{
   for(var i=0,j=msidlist.options.length;i<j;i++){
     msidlist[i].setAttribute('selected',true);
   }
   form.action="gpsms.do?CMD=gpsmsplmsadd";
   form.submit();
 }
}

//<!--返回-->
function togpsmspllist(){
  document.location.href="javascript: history.back(-1)"; 
}




function goload() 
{ 
  invalidation();
  var pagentid=parseInt(<%=request.getAttribute("pagentid")%>);
  var childagentid=parseInt(<%=request.getAttribute("childagentid")%>);
  var ep="<%=request.getAttribute("epid")%>";
  document.getElementById("pagentid").value=pagentid;
  document.getElementById("childagentid").value=childagentid;
  document.getElementById("ep_id").value=ep;  
} 


var leftObj = document.getElementById("selectmsid");
var rightObj = document.getElementById("selectms");
var changeMs = document.getElementById("changeMs");

function leftToRight(){
  var i =0;
  var count=0;
  for(i=leftObj.options.length-1;i>=0;i--){
   if(leftObj.options[i].selected){
   var option = document.createElement("OPTION");
 	 option.value=leftObj.options[i].value;
 	 option.text=leftObj.options[i].text;
 	 changeMs.value+=leftObj.options[i].value+"$0,";
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
 	 changeMs.value+=leftObj.options[i].value+"$0,";
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
   changeMs.value+=rightObj.options[i].value+"$1,";
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
   changeMs.value+=rightObj.options[i].value+"$1,";
   leftObj.options.add(option) ;   
   rightObj.options.remove(i) ; 
   }
}


function parentagentchang(){
  invalidation();
  var childagentselect= document.getElementById("childagentid");
  gpsmsaddplform.childagentid.options.length=1; 
  var parentagent=gpsmsaddplform.pagentid.value;
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
	getEpinfo();
	 
}

function childagentchang(){
      invalidation();
       getEpinfo();
}

function epchang(){
       invalidation();
       document.getElementById("gps_id").value="";
       gpsmsaddplform.selectmsid.options.length=0;
       gpsmsaddplform.selectms.options.length=0;
}

		var a_id=<%=Integer.parseInt(request.getSession().getAttribute("agentId").toString())%>;
        var r_id=<%=Integer.parseInt(request.getSession().getAttribute("roleId").toString())%>;
        var ep_id=<%=Integer.parseInt(request.getSession().getAttribute("epId").toString())%>;	
function getEpinfo(){
        var epselect= document.getElementById("ep_id");
        var pagent=gpsmsaddplform.pagentid.value;
        var cagent=gpsmsaddplform.childagentid.value;
        gpsmsaddplform.ep.options.length=0;
        document.getElementById("gps_id").value="";
        gpsmsaddplform.selectmsid.options.length=0;
        gpsmsaddplform.selectms.options.length=0;
        if(r_id != 3 && r_id != 4){
        ChildAgent.getEpinfoByAId(pagent,cagent,a_id,r_id,function(data){
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
        	ChildAgent.getEpinfoByEId(pagent,cagent,ep_id,r_id,function(data){
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

//<!--改变GPS时-->
function gpschang(){
  invalidation();
  //已经存在的终端
  var czms= document.getElementById("selectmsid");
  //不存的的终端
  var notczms= document.getElementById("selectms");
  var ep=gpsmsaddplform.ep.value;
  var gpsid=gpsmsaddplform.gpsid.value;
   gpsmsaddplform.selectmsid.options.length=0;
   gpsmsaddplform.selectms.options.length=0;
    ChildAgent.getGPSMSinfo_gpsid(gpsid,function(data){
           if(data!=null){
            for(var i=0;i<data.length;i++){
             for(var j=0;j<data[i].length;j++){
              var option = document.createElement("OPTION");
               option.value=data[i][j];
 	           option.text=data[i][++j];
               czms.add(option);
              }
             }
          }  
        }); 
          
       ChildAgent.getGPSMSinfo_notgpsid(ep,gpsid,function(data){
           if(data!=null){
            for(var i=0;i<data.length;i++){
             for(var j=0;j<data[i].length;j++){
              var option = document.createElement("OPTION");
               option.value=data[i][j];
 	           option.text=data[i][++j];
               notczms.add(option);
              }
             }
          }  
        });  
        
      document.getElementById('buttontj').disabled=false; 
}

function invalidation(){
  document.getElementById('buttontj').disabled=true; 
}
</script>



