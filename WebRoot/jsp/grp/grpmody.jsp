<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@ page import="com.elegps.tscweb.model.TbGrpInfo"%>
<%@ page import="com.elegps.tscweb.model.TbEnterpriseInfo"%>
<%@ page import="com.elegps.tscweb.model.TbAgentInfo"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%
	String epname = "";
	TbGrpInfo tbgrpinfo = (TbGrpInfo) request.getAttribute("tbgrpinfo");
	TbEnterpriseInfo tbepname = (TbEnterpriseInfo) request
			.getAttribute("epname");
	if (tbepname != null) {
		epname = tbepname.getEp_Name();
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
	FILTER: progid :           DXImageTransform .           Microsoft .     
		
		
		  
		Gradient(GradientType =           0, StartColorStr =           #ffffff,
		EndColorStr =     
		     #cecfde);
	BORDER-LEFT: #7b9ebd 1px solid;
	CURSOR: hand;
	COLOR: black;
	PADDING-TOP: 2px;
	BORDER-BOTTOM: #7b9ebd 1px solid
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
		<title>修改群组信息</title>
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
								<td width="45%" background="images/3_04.gif">
									<span class="STYLE4">您现在的位置：<span class="STYLE7">修改群组信息</span>
									</span>
								</td>
								<td width="52%">
									<img src="images/3_03.jpg" width="518" height="35">
								</td>
							</tr>
						</table>
						<br>
						<br>
						<form name="grpmodify" method="post" action="">
							<table align="center" width="450" border="1"
								bordercolor="#C1DAD7">
								<tr>
									<td colspan="2" align="center" class="list_header">
										<span class="STYLE77"> 修改群组信息 </span>
									</td>
								</tr>
								<tr>
									<td align="right">
										总部/一级代理商名称:
									</td>
									<td width="10%" align="left">
										<select name="pagentid" id="pagentid"
											style="width: 230; margin: 0; FONT-SIZE: 9pt; FONT-WEIGHT: normal;"
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
								</tr>
								<tr>
									<td align="right">
										企业/二级代理商名称:
									</td>
									<td>
										<select name="childagentid" id="childagentid"
											style="width: 230; margin: 0; FONT-SIZE: 9pt; FONT-WEIGHT: normal;"
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
										<font color="red">&nbsp;<B>*</B> </font>
									</td>
								</tr>
								<tr>
									<td width="15%" align="right">
										<div align="right">
											企业名称:
										</div>
									</td>
									<td width="40%" align="left">
										<div align="left">
											<select name="ep" id="ep_id"
												style="width: 230; margin: 0; FONT-SIZE: 9pt; FONT-WEIGHT: normal;">
												<%
													List listep = (List) request.getAttribute("epList");
													if (listep != null) {
															for (int i = 0; i < listep.size(); i++) {
																TbEnterpriseInfo epinfo = new TbEnterpriseInfo();
																epinfo = (TbEnterpriseInfo) listep.get(i);
												%>
												<option selected="selected" value="<%=epinfo.getEp_Id()%>"><%=epinfo.getEp_Name()%></option>
												<%
															}
													}
												%>
											</select>
											<font color="red">&nbsp;<B>*</B> </font>
										</div>
									</td>
								</tr>
								<tr>
									<td width="110" align="right">
										<label>
											群组号码:
										</label>
									</td>
									<td>
										<input class="checkbox" style="width: 230px;" type="text"
											name="grp_id" value="<%=tbgrpinfo.getGrpid()%>" readonly />
									</td>
								</tr>
								<tr>
									<td align="right">
										<label>
											群组名字:
										</label>
									</td>
									<td>
										<input class="checkbox" style="width: 230px;" type="text"
											name="grp_name" value="<%=tbgrpinfo.getGrpname()%>" />
										<font color="red">&nbsp;<B>*</B> </font>
									</td>
								</tr>
								<tr>
									<td align="right">
										<label>
											群组类型:
										</label>
									</td>
									<td>
										<select name="grp_type" id="grptype"
											style="width: 230; margin: 0; FONT-SIZE: 9pt; FONT-WEIGHT: normal;">
											<option value="1">
												电召群组
											</option>
											<option value="2">
												物流群组
											</option>
										</select>
										<font color="red">&nbsp;<B>*</B> </font>
								</tr>
								<tr>
									<td align="right">
										<label>
											群组有效状态:
										</label>
									</td>
									<td>
										<select name="grp_flag" id="grpflag"
											style="width: 230; margin: 0; FONT-SIZE: 9pt; FONT-WEIGHT: normal;">
											<option value="0">
												冻结
											</option>
											<option value="1">
												正常
											</option>
										</select>
										<font color="red">&nbsp;<B>*</B> </font>
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
								<%
								int r_id=Integer.parseInt(request.getSession().getAttribute("roleId").toString());
								if(r_id !=2 && r_id !=3 && r_id != 4)
								{ %>
								<tr>
									<td align="right">
										<label>
											单次通话时长:
										</label>
									</td>
									<td>
										<input style="width: 50px;" class="checkbox" type="text"
											name="talksc" value="<%=tbgrpinfo.getTalkinglast() / 60000%>" />
										分钟
										<font color="red">&nbsp;<B>*</B> </font>&nbsp;&nbsp;注：0表示不限制
									</td>
								</tr>
								<%} %>
								<tr>
									<td colspan="2" align="center" class="list_footer">
										<input class=btn type="button" name="button1" value="提交"
											onclick="goto(document.forms[0])" />
										&nbsp;
										<input class=btn type="button" name="button" value="返回"
											onclick="togrplist()" />
									</td>
								</tr>
							</table>
						</form>
					</td>
				</tr>
			</table>
	</body>
</html>

<script language="javascript">
 function togrplist(){  
   document.location.href="javascript: history.back(-1)";  
   //document.location.href="grp.do?CMD=grp_search";
 }
 
function goto(form){
	var flag = window.confirm("确定要修改此记录吗？");
	if(flag){
	    if(trim(grpmodify.pagentid.value)=="")
         { 
         alert("一级代理商名称不能为空，请选择一级代理商名称!");
         grpmodify.pagentid.focus();
         return false;
        }
   
	    if(trim(grpmodify.childagentid.value)=="")
	    { 
         alert("请选择二级代理商!");
         grpmodify.childagentid.focus();
         return false;
        }
        if(trim(grpmodify.ep.value)==""){
         alert("请选择企业!");
         grpmodify.ep.focus();
         return false;
         }
         
		if(grpmodify.grp_name.value=="")
 		 { 
      		alert("群组名称为空，请输入群组名称！");
     		grpmodify.grp_name.focus();
     		return false;
   		}
if(r_id !=2 && r_id !=3 && r_id != 4){
   	   if(trim(grpmodify.talksc.value)=="")
         { 
          alert("通话时长为空，请输入通话时长！");
          grpmodify.talksc.focus();
          return false;
         }
      if(trim(grpmodify.talksc.value).length>4){
        alert("通话时长数据长度大于4位数,请重新输入通话时长！");
        grpmodify.talksc.focus();
        return false;
       }
   
     if(trim(grpmodify.talksc.value).length<5){
     	var talking=trim(grpmodify.talksc.value);
 		for (var i=0; i<talking.length; i++)	
		{
			charcode = talking.charCodeAt(i);
			if (charcode < 48 || charcode > 57){
		    	alert("通话时长只能是由数字组成!,请重新输入！");
		    	grpmodify.talksc.focus();
				return false;
			}
		}
 	 }
}
		 form.action="grp.do?CMD=grpmodify&type=<%=request.getAttribute("type")%>";
	     form.submit(); 
	}
}


function goload(){ 
<!--记录本条要修改的记录终端类型-->

  var grptype=<%=tbgrpinfo.getGrptype()%>;
  var flag=<%=tbgrpinfo.getFlag()%>;
  var pagentid=parseInt(<%=request.getAttribute("pagentid")%>);
  var childagentid=parseInt(<%=request.getAttribute("childagentid")%>);
  var ep="<%=request.getAttribute("ep")%>";
  var cc03 = "<%=tbgrpinfo.getC03() %>"; 

   document.getElementById("pagentid").value=pagentid;
   document.getElementById("childagentid").value=childagentid;
   document.getElementById("ep_id").value=ep;
   document.getElementById("grptype").value=grptype;
   document.getElementById("grpflag").value=flag;
  // document.getElementById("grplf").value=lf;alert("6");
	if(cc03 == "null"){
		cc03 = "0";
	}
   document.getElementById("c03").value=cc03;
   if(<%=r_id %> == 4){
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
  grpmodify.childagentid.options.length=1; 
  var parentagent=grpmodify.pagentid.value;
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
        getEpinfo();	 
}

var a_id=<%=Integer.parseInt(request.getSession().getAttribute("agentId").toString())%>;
        var r_id=<%=Integer.parseInt(request.getSession().getAttribute("roleId").toString())%>;
        var ep_id=<%=Integer.parseInt(request.getSession().getAttribute("epId").toString())%>;
function getEpinfo(){
        var epselect= document.getElementById("ep_id");
        var pagent=grpmodify.pagentid.value;
        var cagent=grpmodify.childagentid.value;
        grpmodify.ep.options.length=0;
        
         if(r_id != 3){
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