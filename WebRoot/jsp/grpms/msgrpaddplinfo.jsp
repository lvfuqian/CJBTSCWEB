<%@ page language="java" pageEncoding="UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@ page import="com.elegps.tscweb.model.TbMsInfo"%>
<%@ page import="com.elegps.tscweb.model.TbGrpInfo"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Map"%>
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
	<script type="text/javascript">
	$(function(){
	//键盘按键弹起时执行
	$("#searchName").keyup(function(){
		var index = $.trim($("#searchName").val().toString());//去掉两头空格
		if(index == ""){
			$("#selectgrpid option").removeClass("on");return false;
		}
		var parent = $("#selectgrpid");
		$("#selectgrpid option").removeClass("on");
		//选择包含文本框值的所有加上focus类样式，并把它（们）移到select的最前面
		$("#selectgrpid option:contains('" + index + "')").prependTo(parent).addClass("on");
	});
	//键盘按键弹起时执行
	$("#searchMs").keyup(function(){
		var index = $.trim($("#searchMs").val().toString());//去掉两头空格
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
	<title>根据终端批量增加群组信息</title>
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
								<td width="51%" background="images/3_04.gif">
									<span class="STYLE4">您现在的位置：<span class="STYLE7">根据终端批量增加终端群组信息</span>
									</span>
								</td>
								<td width="47%">
									<img src="images/3_03.jpg" width="505" height="35">
								</td>
							</tr>
						</table>
						<br>
						<br>
						<form name="msaddplform" method="post" action="">
							<table align="center" width="800" border="1"
								bordercolor="#C1DAD7">
								<tr>
									<td colspan="4" align="center" class="list_header">
										<span class="STYLE77">根据终端批量增加终端群组信息</span>
									</td>
								</tr>
								<tr>
									<td colspan="4">
										总部/一级代理商名称:
										<select name="pagentid" id="pagentid"
											style="width: 350; margin: 0; FONT-SIZE: 9pt; FONT-WEIGHT: normal;"
											onchange="parentagentchang()">
											<%
										       List parentagentlist =(List)request.getAttribute("Pagentlist");
										       if(parentagentlist!=null){
										      	for (int i= 0; i < parentagentlist.size(); i++) {
														 TbAgentInfo agentinfo=new TbAgentInfo();
														  agentinfo = (TbAgentInfo)parentagentlist.get(i);
														%>
											<option value="<%=agentinfo.getAgent_Id()%>"><%=agentinfo.getAgent_Name()%></option>
											<%} }%>
										</select>
										<font color="red">&nbsp;<B>*</B>
										</font>
									</td>
								</tr>
								<tr>
									<td colspan="4">
										企业/二级代理商名称:
										<select name="childagentid" id="childagentid"
											style="width: 350; margin: 0; FONT-SIZE: 9pt; FONT-WEIGHT: normal;"
											onchange="childagentchang()">
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
										<font color="red">&nbsp;<B>*</B>
										</font>
									</td>
								</tr>
								<tr>
									<td colspan="4">
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;企业名称:
										<select name="ep" id="ep_id"
											style="width: 350; margin: 0; FONT-SIZE: 9pt; FONT-WEIGHT: normal;"
											onchange="epchang()">
											<option value="">
												-----请选择-----
											</option>
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
										<font color="red">&nbsp;<B>*</B>
										</font>
									</td>
								</tr>
								<tr>
									<td colspan="4">
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;终端号:
										<select name="ms_id" id="ms_id"
											style="width: 350; margin: 0; FONT-SIZE: 9pt; FONT-WEIGHT: normal;"
											onchange="mschang()">
											<option value="">
												请选择
											</option>
											<%
												List listms = (List) request.getAttribute("msList");
												if (listms != null) {
													for (int i = 0; i < listms.size(); i++) {
														TbMsInfo tbmsinfo = new TbMsInfo();
														tbmsinfo = (TbMsInfo) listms.get(i);
														
											%>
											<option value="<%=tbmsinfo.getMsId()%>"><%=tbmsinfo.getMsId()%>—<%=tbmsinfo.getMsName()%></option>
											<%
												}
												}
											%>
										</select>
										<font color="red">&nbsp;<B>*</B>
										</font>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;终端ID: <input type="text" name="mscheck" id="mscheck" value="" maxlength="21"/>
       									<input type="button" name="btncheck" id="btncheck" onclick="mscheckbtn()" value="查询" />
       									
										<!-- &nbsp;&nbsp;<input id="mssearchtext" class="mssearchtext" name="mssearchtext" type="text" value="输入终端号"
										onfocus="this.className='mssearchtext';this.focus();if(this.value=='输入终端号'){this.value='';}" onblur="this.className='mssearchtext';if(this.value==''){this.value='输入终端号';} " />
										&nbsp;<input id="mssearchbtn" class="mssearchbtn" name="mssearchbtn" type="button" value="搜索" />
									 -->
									</td>
								</tr>
								
								  	<tr align="right" >
	</tr>
								
								<tr>
									<td width="48%">
										已经存在的群组号列表:
										<font color="red">&nbsp;<B>*</B>
										</font>&nbsp;<input type="text" name="searchName" id="searchName"  class="topssi"  value="输入关键字" style="width: 120px; height: 24px; line-height: 24px; vertical-align: middle; font-size: 18px; padding-left: 6px; padding-right: 10px;"  onfocus="this.className='topssi';this.focus();if(this.value=='输入关键字'){this.value='';}" onblur="this.className='topssi';if(this.value==''){this.value='输入关键字';} "/>
									</td>
									<td width="30" rowspan="2" align="center">
										<input class=btn name="button" type="button" title="全部右移"
											onclick="leftallToRight()" value=">>" />
										<br>
										<input class=btn name="button2" type="button" title="右移"
											onclick="leftToRight()" value="=>" />
										<br>
										<input class=btn name="button3" type="button" title="左移"
											onclick="rightToLeft()" value="<=" />
										<br>
										<input class=btn name="button4" type="button" title="全部左移"
											onclick="rightallToLeft()" value="&lt;&lt;" />
									</td>
									<td width="48%" >
										所有待选群组号列表:&nbsp;<input type="text" name="searchMs" id="searchMs"  class="topssi"  value="输入关键字" style="width: 120px; height: 24px; line-height: 24px; vertical-align: middle; font-size: 18px; padding-left: 6px; padding-right: 10px;"  onfocus="this.className='topssi';this.focus();if(this.value=='输入关键字'){this.value='';}" onblur="this.className='topssi';if(this.value==''){this.value='输入关键字';} "/>
									</td>
								</tr>
								<tr>
									<td height="106">
										<select name="selectgrpid" id="selectgrpid"
											style="width: 320; margin: 0; FONT-SIZE: 9pt; FONT-WEIGHT: normal;"
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
									<td width="200">
										<select name="selectgrp" id="selectgrp"
											style="width: 320; margin: 0; FONT-SIZE: 9pt; FONT-WEIGHT: normal;"
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
										<input class=btn type="button" id="buttontj" name="buttontj" value="提交"
											onclick="subdoClick(document.forms[0])" />
										&nbsp;
										<input class=btn type="button" name="button" value="返回"
											onclick="togrplist()" />
									</td>
								</tr>
							</table>
						</form>
	</body>
</html>

<script type="text/javascript">

function subdoClick(form){
    if(msaddplform.pagentid.value=="")
	 { 
	      alert("一级代理商名称不能为空，请选择一级代理商名称!");
	      msaddplform.pagentid.focus();
	      return false;
	  }
	if(msaddplform.childagentid.value=="")
	  { 
	      alert("请选择二级代理商!");
	      msaddplform.childagentid.focus();
	      return false;
	  }
    if(msaddplform.ep.value==""){
	     alert("请选择企业!");
	     msaddplform.ep.focus();
	     return false;
     }
	if(msaddplform.ms_id.value==""){
	     alert("请选择终端!");
	     msaddplform.ms_id.focus();
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
  form.action="grpms.do?CMD=plgrpby_grpidadd";
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
  
  if(r_id == 4 || r_id == 2 || r_id == 3){
  	document.getElementById("pagentid").length = 1;
  }
  epchang();
} 

function parentagentchang(){
  invalidation();
  var childagentselect= document.getElementById("childagentid");
  msaddplform.childagentid.options.length=1; 
  var parentagent=msaddplform.pagentid.value;
  cleargrpid();
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
       cleargrpid();
       getEpinfo();
}

		var a_id=<%=Integer.parseInt(request.getSession().getAttribute("agentId").toString())%>;
        var r_id=<%=Integer.parseInt(request.getSession().getAttribute("roleId").toString())%>;
        var ep_id=<%=Integer.parseInt(request.getSession().getAttribute("epId").toString())%>;	
        
function mscheckbtn(){
  	 	var mssearch= document.getElementById("mscheck").value;
  	 	
 		invalidation();
  		var select_msid=document.getElementById("ms_id");
 		var select_epid=document.getElementById("ep_id");
  		 cleargrpid();
		select_msid.length = 0;
		
		//select_epid.length = 0;
   ChildAgent.getGrpMSAllinfo_byms_grpjsp(r_id,a_id,ep_id,mssearch,function(data){
  if(data!=null){
  		if(r_id == 2){
			select_epid.value = 1;
		}
   		if(data.length > 50 ){
   			alert("查询  \""+mssearch+"\" 的数量过多，请输入更加详细的信息！");
   		}else{
   		var option2 = document.createElement("OPTION");
               option2.value="";
 	           option2.text="---请选择---";
 	           select_msid.add(option2);
	      	for(var i=0;i<data.length;i++){
	             //for(var j=0;j<data[i].length;j++){
	              var option = document.createElement("OPTION");
	               option.value=data[i][0];
	 	           option.text=data[i][1];
	 	           //alert(data[i]);
	 	           //String d = option.text;
	 	           //s.
	 	           //alert(data[i][2]);
	 	           //alert(data[i][++j]);
	               select_msid.add(option);
	             // }
	           }
           }
   }else{
   		alert("无此终端！");
   }
  });
   document.getElementById('buttontj').disabled=false;
  	}
        
function getEpinfo(){
        invalidation();
        var epselect= document.getElementById("ep_id");
        var pagent=msaddplform.pagentid.value;
        var cagent=msaddplform.childagentid.value;
        msaddplform.ep.options.length=1;
        msaddplform.ms_id.options.length=0;
        
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

function epchang(){
 invalidation();
 cleargrpid();
 getMsinfo();
 //setTimeout("mschang()",2000);	 
}


function getMsinfo(){
  var msselect =document.getElementById("ms_id");
  var pagent=msaddplform.pagentid.value;
  var childagent=msaddplform.childagentid.value;
  var ep=msaddplform.ep.value;
  msselect.options.length=0;
  ChildAgent.getMsinfoByTypeZero(pagent,childagent,ep,function(data){   //只获取终端类型为0的数据
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
  var notexitgrpid=document.getElementById("selectgrp");
  var msid=msaddplform.ms_id.value;
  if(msid=="" || msid == "null"){
  	cleargrpid();
  return false;
  }
  var mssearch= document.getElementById("mscheck").value;
  cleargrpid();
  var eID=msaddplform.ep.value;
  if(mssearch != "" && mssearch != null){
    ///////////////
	ChildAgent.getMsAllInfoBy_msid(msid,function(data){
   			if(data!=null){
   				eID = data.epId;
   				var aID = data.aId;
   				if(r_id == 2 ||r_id == 3 ||r_id == 4 ){
   					$("#ep_id").val(eID);
   				}else{
   					$("#pagentid").val(aID);
   					getEpinfo();
   					//var e =document.getElementById("ep_id");
					//select_epid.options[eID].selected = true; 
					//$("#ep_id");
   					//e.selectedIndex = eID;
   					setTimeout("$(\"#ep_id\").val("+eID+");" ,100);//延时 
   					
   					//$("#ep_id option:selected").val(eID);

   					var select_msid=document.getElementById("ms_id");
 					var select_epid=document.getElementById("ep_id");
   					ChildAgent.getGrpMSAllinfo_byms_grpjsp(r_id,a_id,ep_id,mssearch,function(data){
						  if(data!=null){

						   		var option2 = document.createElement("OPTION");
						               option2.value="";
						 	           option2.text="---请选择---";
						 	           select_msid.add(option2);
							      	for(var i=0;i<data.length;i++){
							             //for(var j=0;j<data[i].length;j++){
							              var option = document.createElement("OPTION");
							               option.value=data[i][0];
							 	           option.text=data[i][1];
							 	           //alert(data[i]);
							 	           if(msid == option.value){
 	           									option.selected = true;
 	           								}
							               select_msid.add(option);
							             // }
							           }
						           }
						  });
   				}
   			}
   	});
  ////////////
  }
    
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
   ChildAgent.getGrpMSinfo_notmsid(eID,msid,function(data){
   if(data!=null){
      for(var i=0;i<data.length;i++){
             for(var j=0;j<data[i].length;j++){
              var option = document.createElement("OPTION");
               option.value=data[i][j];
 	           option.text=data[i][++j];
               notexitgrpid.add(option);
              }
           }
   }
  }); 
   document.getElementById('buttontj').disabled=false;    
}


function cleargrpid(){
msaddplform.selectgrpid.options.length=0;
msaddplform.selectgrp.options.length=0;
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


