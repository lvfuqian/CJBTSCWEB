<%@ page language="java" pageEncoding="UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@ page import="com.elegps.tscweb.model.TbAdvInfo"%>
<%@ page import="java.util.List"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"> 
		<title>图片</title>
		<link href="<%=path %>/h5file/css/jquery.slideBox.css" rel="stylesheet" type="text/css" />
<script src="<%=path %>/h5file/js/jquery-1.7.1.min.js" type="text/javascript"></script>
<script src="<%=path %>/h5file/js/jquery.slideBox.min.js" type="text/javascript"></script>
<script>
$(function($){
	$('#demo3').slideBox({
		duration : 0.3,//滚动持续时间，单位：秒
		easing : 'linear',//swing,linear//滚动特效
		delay : 5,//滚动延迟时间，单位：秒
		hideClickBar : false,//不自动隐藏点选按键
		clickBarRadius : 10
	});
});
</script>
		
	</head>

	<body>
<div id="demo3" class="slideBox">
  <ul class="items">
  <%
					List advlist = (List)request.getAttribute("advlist");
					if(advlist != null){
					
					if(advlist.size()<5){
						int g=0;
						for(int i = 0; i<advlist.size();i++){
							TbAdvInfo adv = (TbAdvInfo)advlist.get(i);
				 %>
					 <li><a href="http://<%=adv.getAdvUrl() %>" target="_blank" title="<%=adv.getAdvTitle()%>"><img src="<%=adv.getPicUrl() %>" width="100%" ></a></li>
				<%
							
							if(g==4){
								break;
							}
							if(g!=4 && i==advlist.size()-1){
								i = -1;
							}
							g++;
						}
					}else{
						for(int i = 0; i<advlist.size();i++){
							TbAdvInfo adv = (TbAdvInfo)advlist.get(i);
				 %>
				 		 <li><a href="http://<%=adv.getAdvUrl() %>" target="_blank" title="<%=adv.getAdvTitle()%>"><img src="<%=adv.getPicUrl() %>"  width="100%"></a></li>
				 <%}}
				 
				 } %>
   
  </ul>
</div>
	</body>
</html>
