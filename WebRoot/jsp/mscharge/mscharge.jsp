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
    <title>根据群组批量增加终端用户信息</title>
  </head>
  
  <body onload="goload()">
 <td background="images/gw_06.gif">&nbsp;</td>
    <td width="84%" valign="top"><table width="100%" height="363" border="0" cellpadding="0" cellspacing="0"> <tr>
        <td height="5">&nbsp;</td>
        <td valign="top"><table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
          <tr>
            <td width="3%" height="30" align="center" background="images/3_04.gif"><img src="images/arrow3.gif" width="20" height="19"></td>
            <td width="51%" background="images/3_04.gif"><span class="STYLE4">您现在的位置：<span class="STYLE7">终端充值</span></span></td>
            <td width="47%"><img src="images/3_03.jpg" width="505" height="35"></td>
          </tr>
        </table>
        <br>
        <br>                                                      
  <form name="mschargeform"   method="post"  action="">
  <table align="center" width="500"  border="1" bordercolor="#C1DAD7">
  <tr>
    <td colspan="4"  align="center"  class="list_header"><span class="STYLE77">终端充值</span></td>
  </tr>
  
    <tr >
      <td align="left" colspan="4"><label>扣费机构类型:</label>
        <input type="radio" name="rad" id="rad1" value="1" checked="checked" onclick="checktype()"/>总部代理商 &nbsp;
        &nbsp;<input type="radio" name="rad" id="rad2" value="2"onclick="checktype()"/>二级代理商 &nbsp;
        &nbsp;<input type="radio" name="rad" id="rad3" value="3" onclick="checktype()"/>企业 
      </td>
    </tr>
     <tr style="display:inline;" id="pagent" >
          <td width="10%" align="left" colspan="4">
											扣费总部/一级代理商:
											<select name="pagentid" id="pagentid"
												style="width: 150; margin: 0; FONT-SIZE: 9pt; FONT-WEIGHT: normal;"
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
										  <font color="red">&nbsp;<B>*</B></font>
									</td>
	</tr>
	
	<tr style="display:none;" id="cagent" >
                    <td width="10%" align="left" colspan="4">
											扣费二级代理商:
											<select name="childagentid" id="childagentid"
												style="width: 150; margin: 0; FONT-SIZE: 9pt; FONT-WEIGHT: normal;"
												onchange="childagentchang()">
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
										 <font color="red">&nbsp;<B>*</B></font>
									</td>
	</tr>
      <tr style="display:none;" id="ep" >
                    <td width="10%" align="left" colspan="4">
											扣费二级代理商:
											<select name="ep" id="ep_id"
												style="width: 150; margin: 0; FONT-SIZE: 9pt; FONT-WEIGHT: normal;">
												<option value="-1">
													全部企业
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
										 <font color="red">&nbsp;<B>*</B></font>
									</td>
	</tr>
     <tr>
       <td colspan="4">剩余金额:
       <input style="width:100px;" name="spareMoney" type="text" value="" class="checkbox" size="30" readonly=true>&nbsp;元</td>
     </tr>
  
  <tr>
    <td colspan="4">充值企业:
	     <select name="chargeep" id="chargeep_id" style="{width:230;margin:0;FONT-SIZE:9pt;FONT-WEIGHT:normal;}" onchange="epchange(document.forms[0])">
          <% 
          List listep1 =(List)request.getAttribute("epList");
          if(listep1!=null){
           for (int i= 0; i < listep1.size(); i++) {
				 TbEnterpriseInfo epinfo1=new TbEnterpriseInfo();
				  epinfo1 = (TbEnterpriseInfo)listep1.get(i);
				%>
	      <option value="<%=epinfo1.getEp_Id()%>"><%=epinfo1.getEp_Name()%></option>
		  <%} }%>
        </select>
      <font color="red">&nbsp;&nbsp;<B>*</B></font>
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;充值金额:
       <input style="width:100px;" name="charge" type="text" value="" class="checkbox" size="30">&nbsp;元<font color="red"><B>&nbsp;*</B></font>
   	</td>
   	</tr>

  <tr>
    <td width="45%">所有待选充值终端号列表:</td>
    <td width="30" rowspan="2">
       <input class=btn name="button" type="button" title="全部右移" onclick="leftallToRight()" value=">>" />  
       <br>
       <input class=btn name="button2" type="button" title="右移" onclick="leftToRight()" value="=>" />
       <br>      
       <input class=btn name="button3" type="button" title="左移" onclick="rightToLeft()" value="<=" />
       <br>      
       <input class=btn name="button4" type="button" title="全部左移" onclick="rightallToLeft()" value="<<" /></td>
    <td width="200">充值终端号列表:<font color="red">&nbsp;<B>*</B></font></td>
  </tr>
  <tr>
    <td height="106">
	<select name="selectmsid"  style="{width:320;margin:0;FONT-SIZE:9pt;FONT-WEIGHT:normal;}" size="13" multiple>
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
	</td>
    <td width="200">
	<select name="selectms"  style="{width:320;margin:0;FONT-SIZE:9pt;FONT-WEIGHT:normal;}" size="13" multiple>
   
    </select>
	</td>
  </tr>
   <tr>
    <td colspan="4"  align="center" class="list_footer"> 
	    <input class=btn type="button" name="buttontj" value="提交" onclick="subdoClick(document.forms[0])"/>&nbsp;
        <input class=btn type="button" name="button" value="返回" onclick="togmscharlist()" /></td>
  </tr>
</table>
</form>
</body>
</html>

<script type="text/javascript">
function checktype(){
	var pagent=document.getElementById('rad1');
	var cagent=document.getElementById('rad2');
	var ep=document.getElementById('rad3');
	if(pagent.checked==true){
	 	document.getElementById('pagent').style.display="inline";
	 	document.getElementById('cagent').style.display="none";
	 	document.getElementById('ep').style.display="none";
	}else if(cagent.checked==true){
	   document.getElementById('pagent').style.display="inline";
	   document.getElementById('cagent').style.display="inline";
	   document.getElementById('ep').style.display="none";
	}else{
	   document.getElementById('pagent').style.display="inline";
	   document.getElementById('cagent').style.display="inline";
	   document.getElementById('ep').style.display="inline";
	}
}



function subdoClick(form){
  var chargefee="";
  if(document.getElementById('rad1').checked==true){  
     if(mschargeform.pagentid.value==""){
       alert("扣费总部/一级代理商名不能为空，请选择扣费总部/一级代理商名！");
       mschargeform.pagentid.focus();
       return flase;
     }
   }
    if(document.getElementById('rad2').checked==true){  
     if(mschargeform.childagentid.value==""){
       alert("二级代理商名不能为空，请选择扣费总部/一级代理商名！");
       mschargeform.childagentid.focus();
       return flase;
     }
   }
   if(document.getElementById('rad3').checked==true){
     if(mschargeform.ep.value==""){
       alert("扣费企业名不能为空，请选择扣费企业名！");
       mschargeform.ep.focus();
       return flase;
      }
    }
    if(mschargeform.chargeep.value==""){
       alert("充值企业名不能为空，请选择充值的企业名！");
       mschargeform.chargeep.focus();
       return flase;
    }
  if(mschargeform.charge.value==""){
     alert("充值金额不能为空,请输入正确的整数金额充值金额!");
     mschargeform.charge.focus();
     return false;
  }
 if (!isNumber(mschargeform.charge.value))
   { 
    alert("请输入正确的整数金额,只能由数字组成！");
    mschargeform.charge.value ="";
    mschargeform.charge.focus();
    return false;
   }

   if(parseFloat(mschargeform.charge.value)==0)
   {
    alert("充值金额必须大于0，请重新输入");
    mschargeform.charge.value ="";
    mschargeform.charge.focus();
    return false;
   }
   
   chargefee=mschargeform.spareMoney.value;
   if(parseFloat(mschargeform.charge.value)>parseFloat(chargefee)){
    alert("您输入的充值金额大于扣费机构的剩余金额，请重新输入");
    mschargeform.charge.value ="";
    mschargeform.charge.focus();
    return false;
   }
   
   if(document.getElementById("selectms").options.length<1){
      alert("充值终端号码列表不能为空！");
      return false;
   }
   
   var msidlist=document.getElementById("selectms");
   if(msidlist.options.length<1){
       alert("充值终端号码列表不能为空");
       return false;
   }else{
     for(var i=0,j=msidlist.options.length;i<j;i++){
     msidlist[i].setAttribute('selected',true);
     }
   }
if(msidlist.options.length>parseInt(mschargeform.charge.value)){
     if (confirm("你给每个终端充的值小于1元，确认要充吗？"))
		{  	
		form.action="mscharge.do?CMD=mschargeadd";
        form.submit();
	   }
	 }else{
	    form.action="mscharge.do?CMD=mschargeadd";
        form.submit();
	 }  

}




<!--扣费改变代理商时-->
function chargeagentchange(form){
  form.action="mscharge.do?CMD=chargeagentchange";
  form.submit();
}

<!--扣费改变企业时-->
function chargeepchange(form){
  form.action="mscharge.do?CMD=chargeepchange";
  form.submit();
}

<!-- 充值改变企业时 -->
function epchange(form){
   if(document.getElementById('rad1').checked==true){  
     if(mschargeform.agentid.value==""){
       alert("请选择扣费代理商名！");
       mschargeform.agentid.focus();
       return flase;
     }
   }
   if(document.getElementById('rad2').checked==true){
     if(mschargeform.epid.value==""){
       alert("请选择扣费企业名！");
       mschargeform.epid.focus();
       return flase;
      }
   }
    form.action="mscharge.do?CMD=epchange";
    form.submit();
}

function togmscharlist(){
    document.location.href="mscharge.do?CMD=mscharge_search";
}
function goload() 
{ 
  var agent="<%=request.getAttribute("agent")%>";
  var ep="<%=request.getAttribute("ep")%>";
  var type="<%=request.getAttribute("type")%>";
  if(type==2){
    document.getElementById('rad2').checked=true;
    document.getElementById('rad1').checked=false;
  }else{
    document.getElementById('rad1').checked=true;
    document.getElementById('rad2').checked=false;
  }
  checktype();
  document.getElementById("chargeep_id").value="<%=request.getAttribute("ep_id")%>";
  document.getElementById("agent_id").value=agent;
  document.getElementById("ep_id").value=ep;
  if(<%=request.getAttribute("agentbalance")%>==null){
   document.getElementById("agentcharge").value="";
  }else{
   document.getElementById("agentcharge").value="<%=request.getAttribute("agentbalance")%>";
  }
  
  if(<%=request.getAttribute("epbalance")%>==null){
    document.getElementById("epcharge").value="";
  }else{
    document.getElementById("epcharge").value="<%=request.getAttribute("epbalance")%>";
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

function isNumber(yeid)
{
	var charcode;
	for (var i=0; i< yeid.length; i++)	
	{
		charcode = yeid.charCodeAt(i);
		if (charcode < 48 || charcode > 57)	
			return false;
	}
	return true;
}

function parentagentchang(){
  var childagentselect= document.getElementById("childagentid");
  mschargeform.childagentid.options.length=2; 
  var parentagent=mschargeform.pagentid.value;
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
        var pagent=mschargeform.pagentid.value;
        var cagent=mschargeform.childagentid.value;
        mschargeform.ep.options.length=1;
        ChildAgent.getEpinfo(pagent,cagent,function(data){
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


function getEpinfo(){
        var epselect= document.getElementById("ep_id");
        var pagent=mschargeform.pagentid.value;
        var cagent=mschargeform.childagentid.value;
        mschargeform.ep.options.length=1;
        ChildAgent.getEpinfo(pagent,cagent,function(data){
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
</script>


