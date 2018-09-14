<%@ page language="java" pageEncoding="UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ page import="com.elegps.tscweb.model.TbMsInfo"%>
<%@ page import="com.elegps.tscweb.model.TbAgentInfo"%>
<%@ page import="com.elegps.tscweb.model.TbEnterpriseInfo"%>
<%@ page import="java.util.List"%>

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
    <title>增加群组信息</title>
  </head>
  
  <body onload="goload()">
<td background="images/gw_06.gif"><br>&nbsp;</td>
    <td width="84%" valign="top"><table width="100%" height="363" border="0" cellpadding="0" cellspacing="0"> <tr>
        <td height="5">&nbsp;</td>
        <td valign="top"><table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
          <tr>
            <td width="3%" height="30" align="center" background="images/3_04.gif"><img src="images/arrow3.gif" width="20" height="19"></td>
            <td width="45%" background="images/3_04.gif"><span class="STYLE4">您现在的位置：<span class="STYLE7">群组添加</span></span></td>
            <td width="52%"><img src="images/3_03.jpg" width="518" height="35"></td>
          </tr>
        </table>
        <br>
        <br>
  <form name="grpaddform" onSubmit="return formcheck();"  method="post"  action="grp.do?CMD=add">
   <table align="center" width="450"  border="1" bordercolor="#C1DAD7">
    <tr>
      <td colspan="2" align="center"  class="list_header"><span class="STYLE77">群组添加</span></td>
    </tr>
    <tr>
     <td align="right">总部/一级代理商名称:</td>
     <td width="10%" align="left">
        <select name="pagentid" id="pagentid"  style="{width:230;margin:0;FONT-SIZE:9pt;FONT-WEIGHT:normal;}" onchange="parentagentchang()"> <!--  -->
		<% 
       List parentagentlist =(List)request.getAttribute("Pagentlist");
       if(parentagentlist!=null){
       int rid = Integer.parseInt(request.getSession().getAttribute("roleId").toString());
		for (int i= 0; i < parentagentlist.size(); i++) {
				 TbAgentInfo agentinfo=new TbAgentInfo();
				  agentinfo = (TbAgentInfo)parentagentlist.get(i);
				%>
	      <option  value="<%=agentinfo.getAgent_Id()%>"><%=agentinfo.getAgent_Name()%></option>
		  <%} }%>
        </select><font color="red">&nbsp;<B>*</B></font>
    </td>
	</tr> 
	  <tr>
     <td align="right">企业/二级代理商名称:</td>
      <td>
        <select name="childagentid" id="childagentid"  style="{width:230;margin:0;FONT-SIZE:9pt;FONT-WEIGHT:normal;}"  onchange="childagentchang()"> 
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
    <td width="15%" align="right"><div align="right">企业名称:</div></td>
    <td width="40%" align="left" >
      <div align="left">
        <select name="ep" id="ep_id" style="{width:230;margin:0;FONT-SIZE:9pt;FONT-WEIGHT:normal;}" onchange="epchang()">
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
      </div></td>
	</tr> 
	<tr> 
	<td width="15%" align="right"><div align="right">上级群组:</div></td>
    <td width="40%" align="left" >
      <div align="left">
        <select name="grp_pid" id="grp_pid" style="{width:230;margin:0;FONT-SIZE:9pt;FONT-WEIGHT:normal;}" >
         <option value=""></option>
         
        </select>
          <font size=2>为空表示一级</font>
      </div></td>
	</tr> 
   <!--  <tr>
      <td align="right"><label>群组号码:</label></td>
      
      <td>
      <input style="width:60px;" class="checkbox" type="text" name="grp_id" value='${requestScope.grpid}' disabled="disabled"/>
        <input style="width:167px;" class="checkbox" type="text" name="grp_id" value='${requestScope.grpid}' maxlength="12" /><font color="red">&nbsp;&nbsp;<B>*</B></font></td>
    </tr>
    --> 
    <tr>
      <td align="right"><label>群组名字:</label></td>
      <td><input style="width:230px;" class="checkbox" type="text" name="grp_name" value='${requestScope.grp_name}' maxlength="60"/><font color="red">&nbsp;&nbsp;<B>*</B></font></td>
    </tr>
   
    <tr>
    <td align="right"><label>创建者终端号码:</label></td>
      <td><select name="ms_id"  id="ms_id" style="{width:230;margin:0;FONT-SIZE:9pt;FONT-WEIGHT:normal;}"> 
      <option value=""></option>  
        <% 
  		  List list =(List)request.getAttribute("msList");
    		 
    		if(list!=null){
    		   for (int i= 0; i < list.size(); i++) {
					TbMsInfo tbmsinfo=new TbMsInfo();
				  	tbmsinfo = (TbMsInfo)list.get(i);
				%>
				<option value="<%=tbmsinfo.getMsId()%>">
				<%=tbmsinfo.getMsId()%>—<%=tbmsinfo.getMsName()%>
				</option>
				<%}}%>
          </select><font color="red">&nbsp;<B>&nbsp;*</B></font>
      </td>
    </tr>
     <tr>
     <td align="right"><label>群组类型:</label></td>
      <td>
      	<select name="grp_type" id="grptype" style="{width:230;margin:0;FONT-SIZE:9pt;FONT-WEIGHT:normal;}">
		  <option  value="2">物流群组</option>
		  <option value="1">电召群组</option>
        </select><font color="red">&nbsp;<B>&nbsp;*</B></font>
      </td>
     </tr>
     <tr>
     <td align="right"><label>组内信息共享:</label></td>
      <td>
      	<select name="c03" id="c03" style="{width:230;margin:0;FONT-SIZE:9pt;FONT-WEIGHT:normal;}">
		  <option  value="1">是</option>
		  <option value="0">否</option>
        </select><font color="red">&nbsp;<B>&nbsp;*</B></font>
      </td>
     </tr>
      <tr>
     <%
								int r_id=Integer.parseInt(request.getSession().getAttribute("roleId").toString());
								if(r_id !=2 && r_id !=3 && r_id != 4)
								{ %>
	<tr>
      <td align="right"><label>单次通话时长:</label></td>
      <td><input style="width:50px;" class="checkbox" type="text" name="talkinglast" value="3" value='${requestScope.talkinglast}'/>分钟<font color="red">&nbsp;<B>&nbsp;*</B></font>&nbsp;&nbsp;注：0表示不限制</td>
    </tr>
    <%} %>
	<tr>
       <td colspan="2" align="center" class="list_footer">
          <input class=btn type="Submit" name="Submit" value="提交" />
        <input class=btn type="button" name="button" value="返回" onclick="togrplist()" />
        <input class=btn type="reset" name="reset" value="重置" />
      </td>
    </tr>
  </table>
</form></body>
</html>

<script language="javascript">
 var grpdata =new Array();
 function formcheck(){
 
 if(trim(grpaddform.pagentid.value)=="")
   { 
      alert("一级代理商名称不能为空，请选择一级代理商名称!");
      grpaddform.pagentid.focus();
      return false;
   }
   
 if(trim(grpaddform.childagentid.value)=="")
   { 
      alert("请选择二级代理商!");
      grpaddform.childagentid.focus();
      return false;
   }
  if(trim(grpaddform.ep.value)==""){
     alert("请选择企业!");
     grpaddform.ep.focus();
     return false;
  }
  
 // if(grpaddform.grp_id.value=="")
  // { 
  ///    alert("群组号码为空，请输入群组号码");
  //    grpaddform.grp_id.focus();
  //    return false;
  // }
  // if(trim(grpaddform.grp_id.value).length!=21){
  //   alert("群组号码必须是21个字符长度");
 //   grpaddform.grp_id.focus();
  //   return false;
  // }
   
  //  if(trim(grpaddform.grp_id.value).length==21){
   //  	var grp_id=trim(grpaddform.grp_id.value);
 	//	for (var i=0; i<grp_id.length; i++)	
	//	{
	//		charcode = grp_id.charCodeAt(i);
	//		if (charcode < 48 || charcode > 57){
	///	    	alert("群组号码只能是由数字组成!请重新输入");
	//	    	grpaddform.grp_id.focus();
	//			return false;
	//		}
	//	}
 //	}

   
 	if(grpaddform.grp_name.value=="")
  	{ 
      alert("群组名称为空，请输入群组名称");
      grpaddform.grp_name.focus();
      return false;
    }
    
    if(grpaddform.ms_id.value=="")
  	{ 
      alert("创建者终端号码为空，请选择创建者终端号码");
      grpaddform.ms_id.focus();
      return false;
    }
    
    if(grpaddform.grp_type.value=="")
  	{ 
      alert("群组类型为空，请选择群组类型");
      grpaddform.grp_type.focus();
      return false;
    }
    
   if(trim(grpaddform.talkinglast.value)=="")
   { 
      alert("单次通话时长为空，请输入单次通话时长");
      grpaddform.talkinglast.focus();
      return false;
   }
   if(trim(grpaddform.talkinglast.value).length>4){
     alert("单次通话时长数据长度大于4位数,请重新输入单次通话时长");
     grpaddform.talkinglast.focus();
     return false;
   }
   
    if(trim(grpaddform.talkinglast.value).length<5){
     	var talking=trim(grpaddform.talkinglast.value);
 		for (var i=0; i<talking.length; i++)	
		{
			charcode = talking.charCodeAt(i);
			if (charcode < 48 || charcode > 57){
		    	alert("单次通话时长只能是由数字组成!请重新输入");
		    	grpaddform.talkinglast.focus();
				return false;
			}
		}
 	}
   return true;
 }
 
function togrplist(){
  document.location.href="javascript: history.back(-1)";  
}



//<!-- 装入上次查询用户类型查询、在线状态查询、用户有效状态查询的装态 ->
function goload() 
{ 
  var pagentid=parseInt(<%=request.getAttribute("pagentid")%>);
  var childagentid=parseInt(<%=request.getAttribute("childagentid")%>);
  var ep="<%=request.getAttribute("ep")%>";
  document.getElementById("pagentid").value=pagentid;
  document.getElementById("childagentid").value=childagentid;
  //document.getElementById("ep_id").value=ep;  
  
      if(r_id == 2 || r_id == 3 || r_id == 4 ){
	 document.getElementById("pagentid").length = 1;
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


function parentagentchang(){
  var childagentselect= document.getElementById("childagentid");
  grpaddform.childagentid.options.length=1; 
  var parentagent=grpaddform.pagentid.value;
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
		var epselect= document.getElementById("ep_id");
        grpaddform.ep.options.length=1;
        getEpinfo();	 
}

		var a_id=<%=Integer.parseInt(request.getSession().getAttribute("agentId").toString())%>;
        var r_id=<%=Integer.parseInt(request.getSession().getAttribute("roleId").toString())%>;
        var ep_id=<%=Integer.parseInt(request.getSession().getAttribute("epId").toString())%>;	
function getEpinfo(){
        var epselect= document.getElementById("ep_id");
        var pagent=grpaddform.pagentid.value;
        var cagent=grpaddform.childagentid.value;
        
        grpaddform.ms_id.options.length=1;
   
       if(r_id != 3 && r_id != 4){
        ChildAgent.getEpinfoByAId(pagent,cagent,a_id,r_id,function(data){
           if(data!=null){
           grpaddform.ep.options.length=1;
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
           grpaddform.ep.options.length=1;
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
       setTimeout("getMsinfo()",500);
       setTimeout("getGrpinfo()",500);
}

function epchang(){
 getGrpinfo();
 getMsinfo();
}


function getMsinfo(){
  var msselect =document.getElementById("ms_id");
  var pagentid=grpaddform.pagentid.value;
  var childagentid=grpaddform.childagentid.value;
  var ep=grpaddform.ep.value;
  msselect.options.length=1;
  ChildAgent.getMsinfo(pagentid,childagentid,ep,function(data){
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
 
  function getGrpinfo(){
  var pagentid=grpaddform.pagentid.value;
  var childagentid=grpaddform.childagentid.value;
  var ep=grpaddform.ep.value;
  var gid,gpid,grpname;
  grpdata.length=0;  
  ChildAgent.getGrpinfoAll(pagentid,childagentid,ep,function(data){
   if(data!=null){ 
       //alert("群组长度"+data.length); 
       grpaddform.grp_pid.options.length=0; 
       for(var i=0;i<data.length;i++){   //data.length
                gid=data[i].grpid;
                gpid=data[i].grppid;
                grpname=data[i].grpname;
                grpdata[i]= {id:gid,pid:gpid,text:grpname}; 
               // grpdata[i]= {id:'2',pid:'-1',text:'中国'};
               // grpdata[0]= {id:'1',pid:'2',text:'河北'}; 
                //alert(grpdata[i]);
              }
           }
  }); 
  setTimeout("showgrppid()",1000); 
}

  function showgrppid(){
     var grpselect =document.getElementById("grp_pid");
     grpselect.options.length=0;
     var option = document.createElement("OPTION");
     option.value='-1';
 	 option.text='  ';
     grpselect.add(option);
     var ts = new TreeSelector(document.getElementById("grp_pid"),grpdata,-1); 
     ts.createTree(); 
  }





</script>
