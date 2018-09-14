<%@ page language="java" pageEncoding="UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ page import="com.elegps.tscweb.model.TbMsInfo"%>
<%@ page import="com.elegps.tscweb.model.TbGrpInfo"%>
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
.on{
background-color:yellow;
 font-size: 18px;
    font-weight:bold; 
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
  	<script type="text/javascript" >
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
    <title>根据群组批量增加终端用户信息</title>
  </head>
  
  <body onload="goload()">
 <td background="images/gw_06.gif">&nbsp;</td>
    <td width="84%" valign="top"><table width="100%" height="363" border="0" cellpadding="0" cellspacing="0"> <tr>
        <td height="5">&nbsp;</td>
        <td valign="top"><table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
          <tr>
            <td width="3%" height="30" align="center" background="images/3_04.gif"><img src="images/arrow3.gif" width="20" height="19"></td>
            <td width="51%" background="images/3_04.gif"><span class="STYLE4">您现在的位置：<span class="STYLE7">根据群组批量增加终端信息</span></span></td>
            <td width="47%"><img src="images/3_03.jpg" width="505" height="35"></td>
          </tr>
        </table>
        <br>
        <br>                                                      
  <form name="grpaddplform"   method="post"  action="">
  <table align="center" width="800"  border="1" bordercolor="#C1DAD7">
  <tr>
    <td colspan="4"  align="center"  class="list_header"><span class="STYLE77">根据群组批量增加终端用户信息</span></td>
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
    <td colspan="4">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;企业名称:
        <select name="ep" id="ep_id" style="{width:350;margin:0;FONT-SIZE:9pt;FONT-WEIGHT:normal;}" onchange="epchang()">
         <option value="">-----请选择-----</option>
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
    <td colspan="4">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;群组号:
	      <select name="grp_id" id="grp_id" style="{width:350;margin:0;FONT-SIZE:9pt;FONT-WEIGHT:normal;}" onchange="grpchang()">
	        <% 
  			 List listgrp =(List)request.getAttribute("grpList");
  			 if(listgrp!=null){
 			  	for (int i= 0; i < listgrp.size(); i++) {
				 TbGrpInfo tbgrpinfo=new TbGrpInfo();
				  tbgrpinfo = (TbGrpInfo)listgrp.get(i);
				%>
	        <option value="<%=tbgrpinfo.getGrpid()%>"><%=tbgrpinfo.getGrpid()%>—<%=tbgrpinfo.getGrpname()%></option>
	        <%} }%>
      </select><font color="red">&nbsp;&nbsp;<B>*</B></font>	</td>
      <input type="hidden" name="hgrp" id="hgrp" value="<%=listgrp %>"/>
  </tr>
  <tr>
    <td width="48%">已经存在的终端号列表:<font color="red">&nbsp;<B>*</B></font><input type="text" name="searchName" id="searchName"  class="topssi"  value="输入关键字" style="width: 140px; height: 24px; line-height: 24px; vertical-align: middle; font-size: 18px; padding-left: 6px; padding-right: 10px;"  onfocus="this.className='topssi';this.focus();if(this.value=='输入关键字'){this.value='';}" onblur="this.className='topssi';if(this.value==''){this.value='输入关键字';} "/></td>
    <td width="30" rowspan="2" align="center">
       <input class=btn name="button" type="button" title="全部右移" onclick="leftallToRight()" value=">>" />  
       <br>
       <input class=btn name="button2" type="button" title="右移" onclick="leftToRight()" value="=>" />
       <br>      
       <input class=btn name="button3" type="button" title="左移" onclick="rightToLeft()" value="<=" />
       <br>      
       <input class=btn name="button4" type="button" title="全部左移" onclick="rightallToLeft()" value="&lt;&lt;" /></td>
    <td width="48%">所有待选终端号列表: <input type="text" name="searchMs" id="searchMs"  class="topssi"  value="输入关键字" style="width: 140px; height: 24px; line-height: 24px; vertical-align: middle; font-size: 18px; padding-left: 6px; padding-right: 10px;"  onfocus="this.className='topssi';this.focus();if(this.value=='输入关键字'){this.value='';}" onblur="this.className='topssi';if(this.value==''){this.value='输入关键字';} "/></td>
  </tr>
  <tr>
    <td height="106">
	<select name="selectmsid" id="selectmsid" style="{width:320;margin:0;FONT-SIZE:9pt;FONT-WEIGHT:normal;}" size="13" multiple>
    </select>	
	</td>
    <td width="200">
	<select name="selectms" id="selectms" style="{width:320;margin:0;FONT-SIZE:9pt;FONT-WEIGHT:normal;}" size="13" multiple>
    </select>
    <input id="changeMs" type="hidden" name="changeMs" />
	</td>
  </tr>
   <tr>
    <td colspan="4"  align="center" class="list_footer"> 
	    <input class=btn id="buttontj" type="button" name="buttontj" value="提交" onclick="subdoClick(document.forms[0])"/>&nbsp;
        <input class=btn type="button" name="button" value="返回" onclick="togrplist()" /></td>
  </tr>
</table>
</form>
</body>
</html>

<script type="text/javascript">

 var grpdata =new Array();
function subdoClick(form){
    if(grpaddplform.pagentid.value=="")
	  { 
	      alert("一级代理商名称不能为空，请选择一级代理商名称!");
	      grpaddplform.pagentid.focus();
	      return false;
	   }
	 if(grpaddplform.childagentid.value=="")
	   { 
	      alert("请选择二级代理商!");
	      grpaddplform.childagentid.focus();
	      return false;
	   }
	  if(grpaddplform.ep.value==""){
	     alert("请选择企业!");
	     grpaddplform.ep.focus();
	     return false;
	  }
   if(grpaddplform.grp_id.value==""||grpaddplform.grp_id.value=="-1"){
     alert("请选择群组!");
     grpaddplform.grp_id.focus();
     return false;
  }

 var msidlist=document.getElementById("selectmsid");
 if(msidlist.options.length<1){
  if (confirm("确定删除所选群组对应的终端吗?"))
		{  	
		 form.action="grpms.do?CMD=plgrpby_msidadd";
         form.submit();
	  }  
	  else{
	   return;
	  }  
 }else{ 
   for(var i=0,j=msidlist.options.length;i<j;i++){
    msidlist[i].setAttribute('selected',true);
   }
   form.action="grpms.do?CMD=plgrpby_msidadd";
   form.submit();
 }
}

function togrplist(){
  document.location.href="javascript: history.back(-1)";  
}

  		var r_id=<%=Integer.parseInt(request.getSession().getAttribute("roleId").toString())%>;

function goload() 
{ 
  invalidation();
  var pagentid=parseInt(<%=request.getAttribute("pagentid")%>);
  var childagentid=parseInt(<%=request.getAttribute("childagentid")%>);
  document.getElementById("pagentid").value=pagentid;
  document.getElementById("childagentid").value=childagentid;
  
  if(r_id == 4 ||r_id == 3){
  	document.getElementById("childagentid").length = 1;
  }
  if(r_id == 4 || r_id == 2 || r_id == 3){
  	document.getElementById("pagentid").length = 1;
  }
} 

function parentagentchang(){
  invalidation();
  var childagentselect= document.getElementById("childagentid");
  grpaddplform.childagentid.options.length=1; 
  var parentagent=grpaddplform.pagentid.value;
  clearmsid();
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
       clearmsid();
       getEpinfo();
}

		var a_id=<%=Integer.parseInt(request.getSession().getAttribute("agentId").toString())%>;

  		var ep_id=<%=Integer.parseInt(request.getSession().getAttribute("epId").toString())%>;
function getEpinfo(){
        var epselect= document.getElementById("ep_id");
        var pagent=grpaddplform.pagentid.value;
        var cagent=grpaddplform.childagentid.value;
        grpaddplform.ep.options.length=1;
        grpaddplform.grp_id.options.length=1;
        /**ChildAgent.getEpinfo(pagent,cagent,function(data){
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
        });  */
         if(r_id != 3 && r_id !=4){
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
       setTimeout("getGrpinfo()",500);
}

function epchang(){
 invalidation();
 clearmsid();
 getGrpinfo();
 setTimeout("grpchang()",2000);	 
}


function getGrpinfo(){
  /**var grpselect =document.getElementById("grp_id");
  var pagent=grpaddplform.pagentid.value;
  var childagent=grpaddplform.childagentid.value;
  var ep=grpaddplform.ep.value;
  grpselect.options.length=0;
  ChildAgent.getGrpinfo(pagent,childagent,ep,function(data){
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
  });**/
  var pagentid=grpaddplform.pagentid.value;
  var childagentid=grpaddplform.childagentid.value;
  var ep=grpaddplform.ep.value;
  var gid,gpid,grpname;
  grpdata.length=0;  
  ChildAgent.getGrpinfoAll(pagentid,childagentid,ep,function(data){
   if(data!=null){
       for(var i=0;i<data.length;i++){ 
                gid=data[i].grpid;
                gpid=data[i].grppid;
                grpname=data[i].grpid+"-"+data[i].grpname;
                grpdata[i]= {id:gid,pid:gpid,text:grpname}; 
              }
           }
  }); 
  setTimeout("showgrppid()",2000); 
}
 function showgrppid(){
     var grpselect =document.getElementById("grp_id");
     grpselect.options.length=0;
     var option = document.createElement("OPTION");
     option.value='-1';
 	 option.text='  ';
     grpselect.add(option);
     var ts = new TreeSelector(document.getElementById("grp_id"),grpdata,-1); 
     ts.createTree(); 
  }

function grpchang(){
  invalidation();
  var exitmsid=document.getElementById("selectmsid");
  var notexitmsid=document.getElementById("selectms");
  var grpid=grpaddplform.grp_id.value;
  var ep=grpaddplform.ep.value;
  clearmsid();
  ChildAgent.getGrpMSinfo_grpid(grpid,function(data){
   if(data!=null){
      for(var i=0;i<data.length;i++){
             for(var j=0;j<data[i].length;j++){
              var option = document.createElement("OPTION");
               option.value=data[i][j];
 	           option.text=data[i][++j];
               exitmsid.add(option);
              }
           }    
   }
  });
  
   ChildAgent.getGrpMSinfo_notgrpid(ep,grpid,function(data){
   if(data!=null){
      for(var i=0;i<data.length;i++){
             for(var j=0;j<data[i].length;j++){
              var option = document.createElement("OPTION");
               option.value=data[i][j];
 	           option.text=data[i][++j];
               notexitmsid.add(option);
              }
           }
   }
  });
   document.getElementById('buttontj').disabled=false; 
}

function clearmsid(){
grpaddplform.selectmsid.options.length=0;
grpaddplform.selectms.options.length=0;
}


var leftObj = document.getElementById("selectmsid");
var rightObj = document.getElementById("selectms");
var changeMs=document.getElementById("changeMs");
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

function invalidation(){
  document.getElementById('buttontj').disabled=true; 
}

function TreeSelector(item,grpdata,rootId){ 
this._data = grpdata; 
this._item = item; 
this._rootId = rootId; 

} 
TreeSelector.prototype.createTree = function(){ 
var len =this._data.length; 
for( var i= 0;i<len;i++){ 
	if ( this._data[i].pid == this._rootId){ 
		this._item.options.add(new Option(" "+this._data[i].text,this._data[i].id)); 
		for(var j=0;j<len;j++){ 
			this.createSubOption(len,this._data[i],this._data[j]); 
			} 
		} 
	} 
} 

TreeSelector.prototype.createSubOption = function(len,current,next){ 
var blank = "    "; 
if ( next.pid == current.id){ 
intLevel =0; 
var intlvl =this.getLevel(this._data,this._rootId,current); 
for(a=0;a<intlvl;a++) 
blank += "     "; 
blank += "     "; 
this._item.options.add(new Option(blank + next.text,next.id)); 

for(var j=0;j<len;j++){ 
this.createSubOption(len,next,this._data[j]); 

} 

} 
} 

TreeSelector.prototype.getLevel = function(datasources,topId,currentitem){ 

var pid =currentitem.pid; 
if( pid !=topId) 
{ 
for(var i =0 ;i<datasources.length;i++) 
{ 
if( datasources[i].id == pid) 
{ 
intLevel ++; 
this.getLevel(datasources,topId,datasources[i]); 
} 
} 
} 
return intLevel; 
} 
</script>


