<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.elegps.tscweb.model.TbMenuInfo"%>
<html>
  <head>
  <style type="text/css">
	 <!--
	 .STYLE3 {font-size: 12px}
	 -->
  </style>
	<link rel="StyleSheet" href="styles/dtree.css" type="text/css" />
	<script type="text/javascript" src="scripts/dtree.js"></script>
	&nbsp;<a href="javascript: d.openAll();"><span class="STYLE3">全部展开</span></a><span class="STYLE3">&nbsp;|&nbsp;</span><a href="javascript: d.closeAll();"><span class="STYLE3">全部关闭</span></a>
	<br>
	<script type="text/javascript">
	    d = new dTree('d');
	    d.add(0,-1,'TSC后台数据管理');
	    <% 
 		 List list =(List)request.getSession().getAttribute("menu");
   			if(list!=null){
   				int aPid = Integer.parseInt(request.getSession().getAttribute("aPId")+"");
   				String rId = request.getSession().getAttribute("roleId")+"";
   				if(rId.equals("2") && aPid > 0){ 
	   				for(int j= 0; j < list.size(); j++){
	   					int mid =((TbMenuInfo)list.get(j)).getMenuId();
	   					if(mid >= 113 && mid <= 133){
	   						list.remove(j);
	   					}
	   					if(mid >= 100 && mid <= 101){
	   						list.remove(j);
	   					}
	   				}
	   			}
   					for (int i= 0; i < list.size(); i++) {
				 		TbMenuInfo tbmenuinfo=new TbMenuInfo();
				  		tbmenuinfo = (TbMenuInfo)list.get(i);
				  		if(tbmenuinfo.getUrl().trim().toString().length()>5){  //如果长度小于5就认为没有
				  		%>
				  		 d.add(<%=tbmenuinfo.getMenuId()%>,<%=tbmenuinfo.getPmenuId()%>,'<%=tbmenuinfo.getMenuName().toString()%>','<%=tbmenuinfo.getUrl().toString()%>','','mainFrame');
						 <%
						}else{
						%>
						 d.add(<%=tbmenuinfo.getMenuId()%>,<%=tbmenuinfo.getPmenuId()%>,'<%=tbmenuinfo.getMenuName().toString()%>');
						<%
						}
				 	 }
				}
	%>
		document.write(d);
		//-->
	</script>

	<meta http-equiv="Content-Type" content="text/html;charset=gb2312">
  </head>
  <body bgcolor="#A0D7DE">
</html>     