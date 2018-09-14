<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>公网集群通话系统</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
</head>
 <frameset rows="100,*,25" cols="*" frameborder="no" border="1" framespacing="0">
    <frame src="backdrophread.jsp" name="topFrame" scrolling="no" noresize="noresize" id="topFrame" title="topFrame" />
    <frameset rows="*" cols="192,*" framespacing="0" frameborder="yes" border="1">
     	<frame src="menus.jsp" name="leftFrame" scrolling="Auto" id="leftFrame" title="leftFrame"   bordercolor="#008000" />
    	<frame src="firstmain.jsp" name="mainFrame" id="mainFrame" title="mainFrame" />
    </frameset>
    <frame src="backdropfuld.jsp" name="fotFrame" scrolling="no" noresize="noresize" id="fotFrame" title="fotFrame" />
 </frameset>

  <body>
  </body>
</html>
