<%@ page language="java" pageEncoding="UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@ page import="com.elegps.tscweb.model.TbMsInfo"%>
<%@ page import="com.elegps.tscweb.model.TbGrpInfo"%>
<%@ page import="com.elegps.tscweb.model.TbGrpMsListInfo"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.lang.String"%>
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

.STYLE8 {
	font-size: 12px
}

a:hover {
	font-size: 12px;
	color: #CC00FF;
	text-decoration: underline;
}
.on{
	background-color:yellow;
 	font-size: 18px;
    font-weight:bold; 
  }
-->
</style>
		<script type='text/javascript' src='dwr/interface/ChildAgent.js'></script>
		<script type='text/javascript' src='dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>
  		<script type="text/javascript" src="scripts/jquery.min.js"></script>
		<script type="text/javascript">
		$(function(){
			//键盘按键弹起时执行
			$("#searchGrpName").keyup(function(){
				var index = $.trim($("#searchGrpName").val().toString());//去掉两头空格
				if(index == ""){
					$("#selectgrpid option").removeClass("on");return false;
				}
				var parent = $("#selectgrpid");
				$("#selectgrpid option").removeClass("on");
				//选择包含文本框值的所有加上focus类样式，并把它（们）移到select的最前面
				$("#selectgrpid option:contains('" + index + "')").prependTo(parent).addClass("on");
			});

		//键盘按键弹起时执行
		$("#searchGrp").keyup(function(){
			var index = $.trim($("#searchGrp").val().toString());//去掉两头空格
			if(index == ""){
				$("#selectgrp option").removeClass("on");return false;
			}
			var parent = $("#selectgrp");
			$("#selectgrp option").removeClass("on");
			//选择包含文本框值的所有加上focus类样式，并把它（们）移到select的最前面
			$("#selectgrp option:contains('" + index + "')").prependTo(parent).addClass("on");
		});
	}); 
</script>
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
		<title>根据终端批量增加群组信息</title>
	</head>

	<body onLoad="goload()">
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
								<td width="51%" background="images/3_04.gif">
									<span class="STYLE4">您现在的位置：<span class="STYLE7">根据终端跨群组批量增加群组信息</span>
									</span>
								</td>
								<td width="47%">
									<img src="images/3_03.jpg" width="505" height="35">
								</td>
							</tr>
						</table>
						<br>
						<br>
						<form name="kymsaddplform" method="post" action="">
							<table width="90%" border="1" align="center"
								bordercolor="#C1DAD7">
								<tr>
									<td colspan="4" align="center" class="list_header">
										<span class="STYLE77">根据终端跨群组批量增加群组信息</span>
									</td>
								</tr>
								<tr>
									<td width="45%">
										目的总部/一级代理商名称:
										<select name="pagentid" id="pagentid"
											style="width: 50%; margin: 0; FONT-SIZE: 9pt; FONT-WEIGHT: normal;"
											onchange="parentagentchang()">
											<%
												List parentagentlist = (List) request.getAttribute("Pagentlist");
												if (parentagentlist != null) {
													for (int i = 0; i < parentagentlist.size(); i++) {
														TbAgentInfo agentinfo = new TbAgentInfo();
														agentinfo = (TbAgentInfo) parentagentlist.get(i);
											%>
											<option value="<%=agentinfo.getAgent_Id()%>"><%=agentinfo.getAgent_Name()%></option>
											<%
												}
												}
											%>
										</select>
										<font color="red">&nbsp;<B>*</B> </font>
									</td>
									<td width="3%" rowspan="4">

									</td>
									<td colspan="2">
										源总部/一级代理商名称:
										<select name="ypagentid" id="ypagentid"
											style="width: 50%; margin: 0; FONT-SIZE: 9pt; FONT-WEIGHT: normal;"
											onchange="yparentagentchang()">
											<%
												List yparentagentlist = (List) request.getAttribute("Pagentlist");
												if (yparentagentlist != null) {
													for (int i = 0; i < yparentagentlist.size(); i++) {
														TbAgentInfo yagentinfo = new TbAgentInfo();
														yagentinfo = (TbAgentInfo) yparentagentlist.get(i);
											%>
											<option value="<%=yagentinfo.getAgent_Id()%>"><%=yagentinfo.getAgent_Name()%></option>
											<%
												}
												}
											%>
										</select>
								</tr>

								<tr>
									<td>
										目的企业/二级代理商名称:
										<select name="childagentid" id="childagentid"
											style="width: 50%; margin: 0; FONT-SIZE: 9pt; FONT-WEIGHT: normal;"
											onchange="childagentchang()">
											<option value="-1">
												直属企业
											</option>
											<%
												List childagentlistid = (List) request.getAttribute("Cagentlist");
												if (childagentlistid != null) {
													for (int i = 0; i < childagentlistid.size(); i++) {
														TbAgentInfo agentinfo = new TbAgentInfo();
														agentinfo = (TbAgentInfo) childagentlistid.get(i);
											%>
											<option value="<%=agentinfo.getAgent_Id()%>"><%=agentinfo.getAgent_Name()%></option>
											<%
												}
												}
											%>
										</select>
										<font color="red">&nbsp;<B>*</B> </font>
									</td>
									<td colspan="2">
										源企业/二级代理商名称:
										<select name="ychildagentid" id="ychildagentid"
											style="width: 50%; margin: 0; FONT-SIZE: 9pt; FONT-WEIGHT: normal;"
											onchange="ychildagentchang()">
											<option value="-1">
												直属企业
											</option>
											<%
												List ychildagentlistid = (List) request.getAttribute("Cagentlist");
												if (ychildagentlistid != null) {
													for (int i = 0; i < ychildagentlistid.size(); i++) {
														TbAgentInfo agentinfo = new TbAgentInfo();
														agentinfo = (TbAgentInfo) ychildagentlistid.get(i);
											%>
											<option value="<%=agentinfo.getAgent_Id()%>"><%=agentinfo.getAgent_Name()%></option>
											<%
												}
												}
											%>
										</select>
								</tr>
								<tr>
									<td>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;目的企业名称:
										<select name="ep" id="ep_id"
											style="width: 50%; margin: 0; FONT-SIZE: 9pt; FONT-WEIGHT: normal;"
											onchange="epchang()">
											<option value="">------请选择------</option>
											<%
												List listep = (List) request.getAttribute("epList");
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
										<font color="red">&nbsp;<B>*</B> </font>
									</td>
									<td colspan="2">
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;源企业名称:
										<select name="yep" id="yep_id"
											style="width: 50%; margin: 0; FONT-SIZE: 9pt; FONT-WEIGHT: normal;"
											onchange="yepchang()">
											<option value="">------请选择------</option>
											<%
												List ylistep = (List) request.getAttribute("epList");
												if (ylistep != null) {
													for (int i = 0; i < ylistep.size(); i++) {
														TbEnterpriseInfo epinfo = new TbEnterpriseInfo();
														epinfo = (TbEnterpriseInfo) ylistep.get(i);
											%>
											<option value="<%=epinfo.getEp_Id()%>"><%=epinfo.getEp_Name()%></option>
											<%
												}
												}
											%>
										</select>
								</tr>
								<tr>
									<td height="26">
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;终端号:
										<select name="ms_id" id="ms_id"
											style="width: 50%; margin: 0; FONT-SIZE: 9pt; FONT-WEIGHT: normal;"
											onChange="mschang(document.forms[0])">

										</select>
										<font color="red">&nbsp;&nbsp;<B>*</B> </font>
									</td>
									<td height="26" colspan="2"></td>
								</tr>
								<tr>
									<td width="297">
										已经存在的群组号列表:
										<font color="red">&nbsp;<B>*</B><input type="text" name="searchGrpName" id="searchGrpName"   class="topssi"  value="输入已选群组名" style="width: 130px; height: 24px; line-height: 24px; vertical-align: middle; font-size: 18px; padding-left: 6px; padding-right: 10px;"  onfocus="this.className='topssi';this.focus();if(this.value=='输入已选群组名'){this.value='';}" onblur="this.className='topssi';if(this.value==''){this.value='输入已选群组名';} "/> </font>
									</td>
									<td width="30" rowspan="2">
										<input class=btn name="button" type="button" title="全部右移"
											onClick="leftallToRight()" value=">>" />
										<br>
										<input class=btn name="button2" type="button" title="右移"
											onClick="leftToRight()" value="=>" />
										<br>
										<input class=btn name="button3" type="button" title="左移"
											onClick="rightToLeft()" value="<=" />
										<br>
										<input class=btn name="button4" type="button" title="全部左移"
											onClick="rightallToLeft()" value="&lt;&lt;" />
									</td>
									<td width="308">
										所有待选群组号列表:<input type="text" name="searchGrp" id="searchGrp"   class="topssi"  value="输入待选群组名" style="width: 130px; height: 24px; line-height: 24px; vertical-align: middle; font-size: 18px; padding-left: 6px; padding-right: 10px;"  onfocus="this.className='topssi';this.focus();if(this.value=='输入待选群组名'){this.value='';}" onblur="this.className='topssi';if(this.value==''){this.value='输入待选群组名';} "/>
									</td>
								</tr>
								<tr>
									<td height="106">
										<select name="selectgrpid" id="selectgrpid"
											style="width: 100%; margin: 0; FONT-SIZE: 9pt; FONT-WEIGHT: normal;"
											size="13" multiple>
											<%
												List grplistms = (List) request.getAttribute("listgrpms");
												if (grplistms != null) {
													for (int i = 0; i < grplistms.size(); i++) {
														Map map = new HashMap();
														map = (Map) grplistms.get(i);
											%>
											<option value="<%=map.get("grpid")%>"><%=map.get("grpid")%>—<%=map.get("grpname")%></option>
											<%
												}
												}
											%>
										</select>
									</td>
									<td width="45%" colspan="2">
										<select name="selectgrp" id="selectgrp"
											style="width: 100%; margin: 0; FONT-SIZE: 9pt; FONT-WEIGHT: normal;"
											size="13" multiple>
											<%
												List listgrp = (List) request.getAttribute("grpList");
												if (listgrp != null) {
													for (int i = 0; i < listgrp.size(); i++) {
														TbGrpInfo tbgrpinfo = new TbGrpInfo();
														tbgrpinfo = (TbGrpInfo) listgrp.get(i);
											%>
											<option value="<%=tbgrpinfo.getGrpid()%>"><%=tbgrpinfo.getGrpid()%>—<%=tbgrpinfo.getGrpname()%></option>
											<%
												}
												}
											%>
										</select>
									</td>
								</tr>
								<tr>
									<td colspan="4" align="center" class="list_footer">
										<input class=btn type="button" id="buttontj" name="buttontj"
											value="提交" onClick="subdoClick(document.forms[0])" />
										&nbsp;
										<input class=btn type="button" name="button" value="返回"
											onClick="togrplist()" />
									</td>
								</tr>
							</table>
						</form>
	</body>
</html>

<script type="text/javascript">
var r_id=<%=Integer.parseInt(request.getSession().getAttribute("roleId").toString())%>;
function subdoClick(form){
  if(kymsaddplform.pagentid.value==""){
     alert("目的一级代理商名称不能为空，请选择一级代理商名称!");
     kymsaddplform.pagentid.focus();
     return false;
  }
  if(kymsaddplform.childagentid.value=="")
	  { 
	    alert("请选择目的二级代理商!");
	    kymsaddplform.childagentid.focus();
	    return false;
	  }
  if(kymsaddplform.ep.value==""){
     alert("请选择目的企业!");
     kymsaddplform.ep.focus();
     return false;
  }
   if(kymsaddplform.ms_id.value==""){
     alert("请选择终端!");
     kymsaddplform.ms_id.focus();
     return false;
  }
  
 var grpidlist=document.getElementById("selectgrpid");
 if(grpidlist.options.length<1){
  	if (confirm("确定删除所选终端对应的群组吗?"))
		{  	
		 form.action="grpms.do?CMD=plgrpby_grpidadd";
         form.submit();
	  }  
	  else{
	   return;
	  }  
 }else{
  for(var i=0,j=grpidlist.options.length;i<j;i++){
    grpidlist[i].setAttribute('selected',true);
  }                         
  form.action="grpms.do?CMD=kyplgrpby_grpidadd";
  form.submit();
  }
}

function togrplist(){
  document.location.href="javascript: history.back(-1)";  
}



function goload() 
{ 
  invalidation();
  var pagentid=parseInt(<%=request.getAttribute("pagentid")%>);
  var childagentid=parseInt(<%=request.getAttribute("childagentid")%>);
  document.getElementById("pagentid").value=pagentid;
  document.getElementById("childagentid").value=childagentid;
  document.getElementById("ypagentid").value=pagentid;
  document.getElementById("ychildagentid").value=childagentid;
  
  if(r_id == 4 || r_id == 3 || r_id == 2){
  	document.getElementById("pagentid").length = 1;
  	document.getElementById("childagentid").length = 1;
  	
  }
  epchang();
} 

function parentagentchang(){
  invalidation();
  var childagentselect= document.getElementById("childagentid");
  kymsaddplform.childagentid.options.length=1; 
  var parentagent=kymsaddplform.pagentid.value;
  kymsaddplform.selectgrpid.options.length=0;
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
       kymsaddplform.selectgrpid.options.length=0;
       getEpinfo();
}


function getEpinfo(){
        var epselect= document.getElementById("ep_id");
        var pagent=kymsaddplform.pagentid.value;
        var cagent=kymsaddplform.childagentid.value;
        kymsaddplform.ep.options.length=1;
        kymsaddplform.ms_id.options.length=0;
        if(r_id != 3 && r_id != 4){
        ChildAgent.getEpinfoByAId(pagent,cagent,pagent,r_id,function(data){
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
        	ChildAgent.getEpinfoByEId(pagent,cagent,epselect.value,r_id,function(data){
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

function epchang(){
 invalidation();
 kymsaddplform.selectgrpid.options.length=0;
 getMsinfo();
 setTimeout("mschang()",2000);	 
}


function getMsinfo(){
  var msselect =document.getElementById("ms_id");
  var pagent=kymsaddplform.pagentid.value;
  var childagent=kymsaddplform.childagentid.value;
  var ep=kymsaddplform.ep.value;
  msselect.options.length=0;
  ChildAgent.getMsinfo(pagent,childagent,ep,function(data){
   if(data!=null){
      for(var i=0;i<data.length;i++){
             for(var j=0;j<data[i].length;j++){
              var option = document.createElement("OPTION");
               option.value=data[i][j];
 	           option.text=data[i][++j];
               msselect.add(option);
              }
           }
   }
  });
}


function mschang(){
  invalidation();
  var exitgrpid=document.getElementById("selectgrpid");
  var msid=kymsaddplform.ms_id.value;
  var ep=kymsaddplform.ep.value;
  kymsaddplform.selectgrpid.options.length=0;
  kymsaddplform.selectgrp.options.length=0;
  ChildAgent.getGrpMSinfo_msid(msid,function(data){
   if(data!=null){
      for(var i=0;i<data.length;i++){
             for(var j=0;j<data[i].length;j++){
              var option = document.createElement("OPTION");
               option.value=data[i][j];
 	           option.text=data[i][++j];
               exitgrpid.add(option);
              }
           }
            
   }
  });
  document.getElementById('buttontj').disabled=false;   
  setTimeout("ygetGrpinfo()",2000);
}


//源js
function yparentagentchang(){
  var childagentselect= document.getElementById("ychildagentid");
  kymsaddplform.ychildagentid.options.length=1; 
  kymsaddplform.selectgrp.options.length=0;
  var parentagent=kymsaddplform.ypagentid.value;
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
	ygetEpinfo();
	 
}

function ychildagentchang(){
       kymsaddplform.selectgrp.options.length=0;
       ygetEpinfo();
}


function ygetEpinfo(){
        var epselect= document.getElementById("yep_id");
        var pagent=kymsaddplform.ypagentid.value;
        var cagent=kymsaddplform.ychildagentid.value;
        epselect.options.length=1;
        if(r_id != 3 && r_id != 4){
        ChildAgent.getEpinfoByAId(pagent,cagent,pagent,r_id,function(data){
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
        	ChildAgent.getEpinfoByEId(pagent,cagent,epselect.value,r_id,function(data){
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

function yepchang(){
 ygetGrpinfo(); 
}


function ygetGrpinfo(){
  var grpselect =document.getElementById("selectgrp");
  var yep=kymsaddplform.yep.value;
  grpselect.options.length=0;
  var msid=kymsaddplform.ms_id.value;
  ChildAgent.getGrmMsinfo(msid,yep,function(data){
   if(data!=null){
      for(var i=0;i<data.length;i++){
             for(var j=0;j<data[i].length;j++){
              var option = document.createElement("OPTION");
               option.value=data[i][j];
 	           option.text=data[i][++j];
               grpselect.add(option);
              }
           }
   }
  });
}






var leftObj = document.getElementById("selectgrpid");
var rightObj = document.getElementById("selectgrp");

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

function invalidation(){
  document.getElementById('buttontj').disabled=true; 
}
</script>


