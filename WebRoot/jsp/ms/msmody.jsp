<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ page import="com.elegps.tscweb.model.TbMsInfo" %>
<%@ page import="com.elegps.tscweb.model.TbAgentInfo"%>
<%@ page import="com.elegps.tscweb.model.TbEnterpriseInfo"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
 <%    String mobid="";
       TbMsInfo tbmsinfo=(TbMsInfo)request.getAttribute("tbmsinfo");
        if(tbmsinfo.getMobileid()!=null)
        {
         mobid=tbmsinfo.getMobileid();
        }
        String rid = request.getSession().getAttribute("roleId").toString();//角色id
    %>
<html>
  <head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
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
    <title>修改终端用户信息</title>
  </head>
  
  <body onload="goload()">
 <td background="images/gw_06.gif">&nbsp;</td>
    <td width="84%" valign="top"><table width="100%" height="363" border="0" cellpadding="0" cellspacing="0"> <tr>
        <td height="5">&nbsp;</td>
        <td valign="top"><table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
          <tr>
            <td width="3%" height="30" align="center" background="images/3_04.gif"><img src="images/arrow3.gif" width="20" height="19"></td>
            <td width="45%" background="images/3_04.gif"><span class="STYLE4">您现在的位置：<span class="STYLE7">修改终端用户 </span></span></td>
            <td width="52%"><img src="images/3_03.jpg" width="518" height="35"></td>
          </tr>
        </table>   
        <br>
        <br>       
  <form name="msmodify"  method="post"  action="">
  <table align="center" width="450"  border="1" bordercolor="#C1DAD7">
    <tr>
       <td colspan="2" align="center"  class="list_header"><span class="STYLE77">修改终端用户</span></td>
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
        <select name="childagentid" id="childagentid"  style="{width:180;margin:0;FONT-SIZE:9pt;FONT-WEIGHT:normal;}" onchange="childagentchang()">
          <%
															int eid =Integer.parseInt( request.getSession().getAttribute("epId").toString());
															int roid =Integer.parseInt(rid);
															int apid =Integer.parseInt( request.getSession().getAttribute("aPId").toString());
															if(apid>0){
															}else if ((roid == 3||roid == 4) && eid != 0){
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
        <select name="ep" id="ep_id" style="{width:180;margin:0;FONT-SIZE:9pt;FONT-WEIGHT:normal;}">
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
              <td width="110" align="right"><label>终端号码:</label></td>
              <input type="hidden" name="ms_id" value="<%=tbmsinfo.getMsId()%>"/>
              <td ><input style="width:180px;" name="msId" type="text" class="checkbox" size="30"  value="<%=tbmsinfo.getMsId()%>"  maxlength="21"/><font color="red">&nbsp;<B>*</B></font></td>
    </tr>
   <tr>
              <td align="right">
                终端别名:</td>
              <td><input style="width:180px;" name="ms_name" type="text" class="checkbox" size="30" value="<%=tbmsinfo.getMsName()%>"/><font color="red">&nbsp;<B>*</B></font></td>
      
    </tr>
    <tr>
              <td align="right">
                终端密码:</td>
              <td><input style="width:180px;" name="ms_password" type="text" class="checkbox" size="30" value="<%=tbmsinfo.getPasswd()%>"/><font color="red">&nbsp;<B>*</B></font></td>
      
    </tr>
	<tr>
      <td align="right">用户类型:</td>
      <td>
      <select name="ms_type" id="mstype"  style="{width:180;margin:0;FONT-SIZE:9pt;FONT-WEIGHT:normal;}">
	    <option  value="0">终端用户</option>   
		<option  value="1">群组管理</option>
		<option  value="2">调度用户</option>
		<option  value="5">下呼调度</option>
		<option  value="4">免费用户</option>
        </select><font color="red">&nbsp;<B>*</B></font>
	</tr>
	<tr>
      <td align="right">强插级别:</td>
      <td>
        <select name="ms_level" id="mslevel"  style="{width:180;margin:0;FONT-SIZE:9pt;FONT-WEIGHT:normal;}">
         <option  value="0">无强插</option>   
		 <option  value="1">低强插</option>
		 <option  value="2">中强插</option>
		 <option  value="3">高强插</option>
        </select><font color="red">&nbsp;<B>*</B></font>
       </td>
	</tr>
	<tr>
      <td align="right">是否单呼:</td>
      <td>
        <select name="ms_df" id="msdf"  style="{width:180;margin:0;FONT-SIZE:9pt;FONT-WEIGHT:normal;}">
            <option  value="0">否</option>   
			<option  value="1">是</option>
        </select><font color="red">&nbsp;<B>*</B></font>
       </td>
	</tr>
	<tr>
      <td align="right">通话权限:</td>
      <td>
        <select name="call" id="call"  style="{width:180;margin:0;FONT-SIZE:9pt;FONT-WEIGHT:normal;}">
            <option  value="1">是</option>   
			<option  value="0">否</option>
        </select><font color="red">&nbsp;<B>*</B></font>
       </td>
	</tr>
	<tr>
      <td align="right">费用状态:</td>
      <td>
        <select name="package_fee" id="package_fee"  style="{width:180;margin:0;FONT-SIZE:9pt;FONT-WEIGHT:normal;}">
             <option value="<%=tbmsinfo.getPf().getPfId() %>"><%=tbmsinfo.getPf().getPfType() %> &nbsp;-&nbsp;<%=tbmsinfo.getPf().getPfHow() %></option>
             
        </select><font color="red">&nbsp;<B>*</B></font>
       </td>
	</tr>
	<tr>
	<td align="right">终端状态:</td>
	<td>
	  <select name="ms_flag" id="msflag"  style="{width:180;margin:0;FONT-SIZE:9pt;FONT-WEIGHT:normal;}">
	     <option value="0">冻结</option>
		 <option value="1">正常</option>
		</select><font color="red">&nbsp;<B>*</B></font>
	</td>
	</tr>
	<tr>
	<td align="right">电话权限:</td>
	<td>
	  <select name="c03" id="c03"  style="{width:180;margin:0;FONT-SIZE:9pt;FONT-WEIGHT:normal;}">
	     <option value="0">禁止切换</option>
		 <option value="1">允许切换</option>
		</select><font color="red">&nbsp;<B>*</B></font>
	</td>
	</tr>
	<tr>
	<td align="right">终端类型:</td>
	<td>
	  <select name="c04" id="c04"  style="{width:180;margin:0;FONT-SIZE:9pt;FONT-WEIGHT:normal;}">
	     
	     <%
	       String cc04="";
	   if(tbmsinfo.getC04()!=null){
	     if(tbmsinfo.getC04().equals("1")){
	     	cc04 = "手持机";
	     }
	     if(tbmsinfo.getC04().equals("0")){
	     	cc04 = "车机";
	     }
	     }
	      %>
	     <option value='<%=tbmsinfo.getC04() %>'><%=cc04 %>
	     </option>
		</select><font color="red">&nbsp;<B>*</B></font>
	</td>
	</tr>
	<tr>
	<td align="right">终端类别:</td>
	<td>
	  <select name="ms_category" id="ms_category"  style="{width:180;margin:0;FONT-SIZE:9pt;FONT-WEIGHT:normal;}">
	     
	     <%
	       String ms_category="";
	   if(tbmsinfo.getMsCategory()!=null){
	     if(tbmsinfo.getNetWorkType()==null){
	     	tbmsinfo.setNetWorkType("");
	     }
	     if(tbmsinfo.getMsCategory().equals("0")){
	     	ms_category = "V8"+tbmsinfo.getNetWorkType();
	     }else if(tbmsinfo.getMsCategory().equals("1")){
	     	ms_category = "A18"+tbmsinfo.getNetWorkType();
	     }
	    else if(tbmsinfo.getMsCategory().equals("2")){
	     	ms_category = "A380"+tbmsinfo.getNetWorkType();
	     }
	     else if(tbmsinfo.getMsCategory().equals("3")){
	     	ms_category = "A777"+tbmsinfo.getNetWorkType();
	     }
	     else if(tbmsinfo.getMsCategory().equals("4")){
	     	ms_category = "H1000"+tbmsinfo.getNetWorkType();
	     }
	     else if(tbmsinfo.getMsCategory().equals("5")){
	     	ms_category = "S40"+tbmsinfo.getNetWorkType();
	     }
	     else if(tbmsinfo.getMsCategory().equals("6")){
	     	ms_category = "S60"+tbmsinfo.getNetWorkType();
	     }
	     else if(tbmsinfo.getMsCategory().equals("7")){
	     	ms_category = "S50"+tbmsinfo.getNetWorkType();
	     }
	     else if(tbmsinfo.getMsCategory().equals("8")){
	     	ms_category = "T20"+tbmsinfo.getNetWorkType();
	     }
	     else if(tbmsinfo.getMsCategory().equals("9")){
	     	ms_category = "T25"+tbmsinfo.getNetWorkType();
	     }
	     else if(tbmsinfo.getMsCategory().equals("10")){
	     	ms_category = "G10"+tbmsinfo.getNetWorkType();
	     }
	     else if(tbmsinfo.getMsCategory().equals("11")){
	     	ms_category = "K880"+tbmsinfo.getNetWorkType();
	     }else if(tbmsinfo.getMsCategory().equals("12")){
	     	ms_category = "其他";
	     }
	     }
	      %>
	     <option value='<%=tbmsinfo.getMsCategory() %>'> <%=ms_category %>
	     </option>
		</select><font color="red">&nbsp;<B>*</B></font>
	</td>
	</tr>
		<tr>
	<td align="right">网络类型:</td>
	<td>
	  <select name="nwType" id="nwType"  style="{width:180;margin:0;FONT-SIZE:9pt;FONT-WEIGHT:normal;}">
	  	<option  value="">无网络</option>
         <option  value="C">电信（C）</option>
		 <option  value="W">联通（W）</option>
		 <option  value="M">移动（M）</option>
		</select><font color="red">&nbsp;<B>*</B></font>
	</td>
	</tr>
	<tr>
              <td align="right">
                终端卡号:</td>
              <td><input style="width:180px;" name="ms_modid" type="text" class="checkbox" size="30" value="<%=mobid%>"/></td>
    </tr>
    <!-- <tr>
              <td align="right">
                里程（米）:</td>
              <td><input style="width:180px;" name="mileageas" type="text" class="checkbox" size="11" readonly="true" value="<%=tbmsinfo.getMileageas()%>"/></td>
      
    </tr>
     -->
    <tr>
	<tr>
              <td align="right"><label>备注信息:</label></td>
              <td><input style="width:180px;" name="memo" type="text" class="checkbox" size="30" value="<%=tbmsinfo.getC01()%>"></td>
    </tr>
	<tr>
      <td colspan="2" align="center" class="list_footer">
          <input class=btn  type="button" name="button1" value="提交" onclick="gotos(document.forms[0])"/>&nbsp;
     
        <input class=btn  type="button" name="button" value="返回" onclick="tomslist()" />
      </td>
    </tr>
  </table></form></td></tr></table>
</body>
</html>

<script language="javascript">
 function tomslist(){  
   document.location.href="javascript: history.back(-1)";  
 }
 
function gotos(form){
var oldmstype=<%=tbmsinfo.getMsType()%>;
var flag = window.confirm("确定要修改此记录吗？");
if(flag){

 if(trim(msmodify.pagentid.value)=="")
   { 
      alert("一级代理商名称不能为空，请选择一级代理商名称!");
      msmodify.pagentid.focus();
      return false;
   }
   
 if(trim(msmodify.childagentid.value)=="")
   { 
      alert("请选择二级代理商!");
      msmodify.childagentid.focus();
      return false;
   }
   
   if(trim(msmodify.ep.value)=="")
   { 
      alert("企业名为空，请选择企业名！");
      msmodify.ep.focus();
      return false;
   }

 if(trim(msmodify.ms_name.value)=="")
   { 
      alert("终端名称为空，请输入终端名称！");
      msmodify.ms_name.focus();
      return false;
   } 
   if(trim(msmodify.ms_password.value)==""){
      alert("密码不能为空!");
      msmodify.ms_password.focus();
      return false; 
   } 
   
   if(msmodify.ms_type.value==""){
     alert("终端类型不能为空!");
      msmodify.ms_type.focus();
      return false; 
   } 
   
   if(trim(msmodify.ms_level.value)==""){
      alert("终端级别不能为空!");
      msmodify.ms_level.focus();
      return false; 
   } 
   
   if(msmodify.ms_flag.value==""){
      alert("终端状态不能为空!");
      msmodify.ms_flag.focus();
      return false; 
   } 
  
   if(trim(msmodify.ms_modid.value)!=""){ 
        if (trim(msmodify.ms_modid.value).length!=11)  {   
            alert("卡号长度必须是11位,请输入正确的终端卡号\n\n例如:13800138000！"); 
            msmodify.ms_modid.focus();
            return false;        
        }else{
         var id=msmodify.ms_modid.value;
 	     for (var i=0; i<id.length; i++)	
		  {
		    charcode = id.charCodeAt(i);
		    if (charcode < 48 || charcode > 57){
		    alert("卡号只能是由数字组成!,请重新输入！");
		    msmodify.ms_modid.focus();
			return false;
			}
		 }
        }  
    }
       if(trim(msmodify.msId.value)=="")
   { 
      alert("终端号码为空，请输入终端号码");
      msmodify.msId.focus();
      return false;
   }
    if(trim(msmodify.msId.value).length!=21)
	 {
 	 alert("终端号长度必须为21位,请输入");
 	 msmodify.msId.focus();
 	 return false;
	 }
	 if(trim(msmodify.msId.value).length==21){
	     var msId=msmodify.msId.value;
	 	 for (var i=0; i<msId.length; i++)	
			{
			charcode = msId.charCodeAt(i);
			if (charcode < 48 || charcode > 121){
		    alert("终端号码只能是由[a-zA-Z0-9]组成!,请重新输入");
			    msmodify.msId.focus();
				return false;
				}
			}
		 }
   
if(oldmstype==2){
     if(oldmstype==msmodify.ms_type.value){
    	form.action="ms.do?CMD=msmodify&delddms=1&type=<%=request.getAttribute("type")%>";
		form.submit(); 
     }else{
       var  typeflag=window.confirm("该记录在调度信息中会被删除,确定要把调度终端要改变成其他用户类型吗？");
       if(typeflag){
        form.action="ms.do?CMD=msmodify&delddms=1&type=<%=request.getAttribute("type")%>";
	    form.submit(); 
       }      
     }
   }
else{
     if(msmodify.ms_type.value==2){
       var tsflag1=window.confirm("该记录在调度信息中会被删除,确定要把该终端要类型改变成调度用户类型吗？");
       if(tsflag1){
         form.action="ms.do?CMD=msmodify&delddms=1&type=<%=request.getAttribute("type")%>";
	     form.submit(); 
       }    
     }else{
       form.action="ms.do?CMD=msmodify&delddms=1&type=<%=request.getAttribute("type")%>";
	   form.submit(); 
     }
   }
   
   
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



function goload() 
{ //<!--记录本条要修改的记录终端类型-->
  var mstype=<%=tbmsinfo.getMsType()%>;
  var flag=<%=tbmsinfo.getFlag()%>;
  var ep="<%=request.getAttribute("ep")%>";
  var mslevel=<%=tbmsinfo.getMsLevel()%>;
  var pagentid=parseInt(<%=request.getAttribute("pagentid")%>);
  var childagentid=parseInt(<%=request.getAttribute("childagentid")%>);
  var msdf=<%=tbmsinfo.getMsdf()%>;
  var call=<%=tbmsinfo.getMscall()%>;
  var c03=<%=tbmsinfo.getC03()%>;
  var c04=<%=tbmsinfo.getC04()%>;
  var mc=<%=tbmsinfo.getMsCategory()%>;
  var nwType ="<%=tbmsinfo.getNetWorkType()%>";
  document.getElementById("pagentid").value=pagentid;
  document.getElementById("childagentid").value=childagentid;
  document.getElementById("ep_id").value=ep;
  document.getElementById("mstype").value=mstype;
  document.getElementById("msflag").value=flag;
  document.getElementById("mslevel").value=mslevel;
  //document.getElementById("package_fee").value=package_fee;
  document.getElementById("msdf").value=msdf;
  document.getElementById("call").value=call;
  document.getElementById("c03").value=c03;
  document.getElementById("c04").value=c04;
  document.getElementById("ms_category").value=mc;
  if(nwType != "null" && nwType != ""){
  	  document.getElementById("nwType").value=nwType;
  }

  //只有管理员和超级管理员能修改终端密码
  var rrid = <%=rid%>;
  
  if(rrid == 1 || rrid == 35){

  }else{
  	msmodify.ms_password.readOnly = true;
  	msmodify.ms_type.length = 1;
  	msmodify.c03.length = 1;
  	msmodify.pagentid.length = 1;
  }
    //document.getElementById("carPlateColor").value=color;
}

function parentagentchang(){
  var childagentselect= document.getElementById("childagentid");
  msmodify.childagentid.options.length=1; 
  var parentagent=msmodify.pagentid.value;
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
        var pagent=msmodify.pagentid.value;
        var cagent=msmodify.childagentid.value;
        msmodify.ep.options.length=0;
        getEpinfo();
}

 		var a_id=<%=Integer.parseInt(request.getSession().getAttribute("agentId").toString())%>;
  		var r_id=<%=Integer.parseInt(request.getSession().getAttribute("roleId").toString())%>;
  		var ep_id=<%=Integer.parseInt(request.getSession().getAttribute("epId").toString())%>;
function getEpinfo(){
   var epselect= document.getElementById("ep_id");
        var pagent=msmodify.pagentid.value;
        var cagent=msmodify.childagentid.value;
        msmodify.ep.options.length=0;
        if(r_id != 3 || r_id != 4){
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
</script>