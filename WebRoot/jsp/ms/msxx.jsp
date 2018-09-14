<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="com.elegps.tscweb.model.TbMsInfo" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>


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
    <title>终端详细信息</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <body>
  	<c:set value="${requestScope.tbx}" var="tbx"></c:set>
   <form id="form1" name="form1" method="post" action="">
 <td background="images/gw_06.gif">&nbsp;</td>
    <td width="84%" valign="top"><table width="100%" height="363" border="0" cellpadding="0" cellspacing="0"> <tr>
        <td height="5">&nbsp;</td>
        <td valign="top"><table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
          <tr>
            <td width="3%" height="30" align="center" background="images/3_04.gif"><img src="images/arrow3.gif" width="20" height="19"></td>
            <td width="45%" background="images/3_04.gif"><span class="STYLE4">您现在的位置：<span class="STYLE7">终端用户详细信息</span></span></td>
            <td width="52%"><img src="images/3_03.jpg" width="518" height="35"></td>
          </tr>
        </table>
        <br>
        <br>
  <table align="center" width="550"  border="1" bordercolor="#C1DAD7">
  	
    <tr>
      <td colspan="2" align="center"  class="list_header"><span class="STYLE77">终端用户详细信息</span></td>
    </tr>
    <% 
       SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
       TbMsInfo tbmsinfo=(TbMsInfo)request.getAttribute("tbmsinfo");
       String zdlx=null;  //终端用户类型
       if(tbmsinfo.getMsType()==1){
         zdlx="群组管理";
       }else if(tbmsinfo.getMsType()==2){
         zdlx="调度用户";
       }else if(tbmsinfo.getMsType()==5){
         zdlx="下呼调度";
       }else if(tbmsinfo.getMsType()==4){
         zdlx="免费用户";
       }else{
       zdlx="终端用户";
       }
       String zdjb=null;
       if(tbmsinfo.getMsLevel()==0){
         zdjb="无强插";
       }else if(tbmsinfo.getMsLevel()==1){
         zdjb="低强插";
       }else if(tbmsinfo.getMsLevel()==2){
         zdjb="中强插";
       }else{
         zdjb="高强插";
       }
       String zxzt=null;  //终端用户状态
       if(tbmsinfo.getOnlineStatus()==1){
       	zxzt="在线";
       }
       else if(tbmsinfo.getOnlineStatus()==0){
       	zxzt="离线";
       }else{
       	zxzt="关机";
       } 
       String flag=null;     //用户的状态 
       if(tbmsinfo.getFlag()==1){
         flag="正常";
       }
       else{
        flag="失效";
       }
       String df=null;
       if(tbmsinfo.getMsdf()==1){
         df="是";
       }else{
         df="否";
       }
        
       String iscal=null;
       if(tbmsinfo.getMscall()==1){
         iscal="是";
       }else if(tbmsinfo.getMscall()==0){
         iscal="否";
       }   
       String ismobile=null;
       if(tbmsinfo.getC03()=="0"||"0".equals(tbmsinfo.getC03())){
       ismobile="禁止切换";
       }else{
       ismobile="允许切换";
       }
       String cc04="";
       if(tbmsinfo.getC04()=="0"){
       		cc04 ="车机";
       }else{
       		cc04 ="手持机";
       }
       
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
	     	ms_category = "其他"+tbmsinfo.getNetWorkType();
	     }else{
	     	//tbmsinfo.setMsCategory("-1");
	     	ms_category ="";
	     }
	     }
    %>
     <tr>
              <td align="right"><label>企业名称:</label></td>
              <td width="5%"><%=request.getAttribute("epname")%></td>
    </tr>
     <tr>
              <td align="right"><label>终端号码:</label></td>
              <td width="5%"><%=tbmsinfo.getMsId()%></td>
    </tr>
    <tr>
              <td align="right">
                终端别名:</td>
              <td><%=tbmsinfo.getMsName()%></td>
      
    </tr>
	<tr>
      <td width="15%" align="right">用户类型:</td>
      <td width="35%"><%=zdlx%></td>
	</tr>
	<tr>
      <td width="15%" align="right">终端型号:</td>
      <td width="35%">
      <%
      String c05="";
      if(tbmsinfo.getC05()!=null && tbmsinfo.getC05()!=""){
      	if(tbmsinfo.getC05().equals("1")){
	      		c05="BK_PTT";
	      }else if(tbmsinfo.getC05().equals("2")){
	      		c05="BK_GPS";
	      }else if(tbmsinfo.getC05().equals("4")){
	      		c05="BK_803";
	      }else if(tbmsinfo.getC05().equals("8")){
	      		c05="T808";
	      }else if(tbmsinfo.getC05().equals("16")){
	      		c05="HR3000";
	      }else if(tbmsinfo.getC05().equals("32")){
	      		c05="BK_K6";
	      }  
      }
       %>
      <%=c05%></td>
	</tr>
	<tr>
      <td width="15%" align="right">强插级别:</td>
      <td width="35%">
      <%=zdjb%>
      </td>
	</tr>
	<tr>
      <td width="15%" align="right">终端卡号:</td>
      <td width="35%">
       <%=tbmsinfo.getMobileid()%>
      </td>
	</tr>
	<tr>
      <td width="15%" align="right">终端密码:</td>
      <td width="35%">
       <%=tbmsinfo.getPasswd()%>
      </td>
	</tr>
	<tr>
      <td width="15%" align="right">在线状态:</td>
      <td width="35%"><p><%=zxzt%></p></td>
    </tr>
    <tr>
      <td width="15%" align="right">套餐:</td>
      <td width="35%">
       <%=tbmsinfo.getPf().getPfType()%>-<%=tbmsinfo.getPf().getPfHow()%>元-<%=tbmsinfo.getPf().getPfTime()%>个月
      </td>
	</tr>
	
	<tr>
      <td align="right">MS_Id处于通话过程Grp:</td>
      <td><%=tbmsinfo.getGrpCalling()%></td>
	</tr>
	<!--<tr>
      <td  align="right">最后通话日期时间 :</td>
      <td><%=tbmsinfo.getLastCalling()%>
      </td>
	</tr>
	--><tr>
      <td align="right">用户的状态:</td>
      <td><%=flag%></td>
    </tr>
    <tr>
       <td align="right">GPS提交时间:</td>
       <td><% if (tbmsinfo.getLastPostGps_time()==null){
						  out.print("");
						}else{
                          out.print(dateFormat.format(tbmsinfo.getLastPostGps_time()));
						}
			%>
	  </td>
    </tr>
    <tr>
      <td align="right">是否单呼:</td>
      <td>
         <%=df%>
      </td>
    <tr>
     <tr>
      <td align="right">是否能切换到电话模式:</td>
      <td><%=ismobile%></td>
    <tr>
     <tr>
      <td align="right">终端类型:</td>
      <td><%=cc04%></td>
    <tr>
    <tr>
      <td align="right">终端类别:</td>
      <td><%=ms_category%></td>
    <tr>
     <tr>
      <td align="right">通话权限:</td>
      <td>
         <%=iscal%>
      </td>
    <tr><!--
     <tr>
      <td align="right">总里程:</td>
      <td>
         <%=tbmsinfo.getMileageas()%>
      </td>
    <tr>
       --><td align="right">备注信息:</td>
       <td><%=tbmsinfo.getC01()%></td>
    </tr>
    <tr>
       <td align="right">SIM 卡号:</td>
       <td>${tbx.simNum}</td>
    </tr>
    <tr>
       <td align="right">设备号:</td>
       <td>${tbx.deviceNum}</td>
    </tr>
    <tr>
       <td align="right">车牌颜色:</td>
       <td>
       <c:if test="${tbx.carPlateColor ==0}">蓝色</c:if>
       <c:if test="${tbx.carPlateColor ==1}">黄色</c:if>
       <c:if test="${tbx.carPlateColor ==2}">白色</c:if>
       <c:if test="${tbx.carPlateColor ==3}">黑色</c:if>
       </td>
    </tr>
      <tr>
          <td align="right"><label>亲情号码:</label></td>
          <td width="5%"><%=tbmsinfo.getFamilyNumbers()%></td>
      </tr>
    <tr>
    	  <td colspan="2" align="center" class="list_footer">
          <input class=btn type="button" name="button" value="返回" onclick="gomslist()" />
      </td>
    <tr>
  </table>
  </body>
</html>
<script language="javascript">
 function gomslist(){
   document.location.href="javascript: history.back(-1)";  
 //<!-- document.location.href="ms.do?CMD=ms_search";  -->
 }
</script>

