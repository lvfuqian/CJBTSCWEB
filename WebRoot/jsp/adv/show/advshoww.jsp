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
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"> 
<title>文字 </title>
<meta name="description" content="" />
<style type="text/css">
/* base */
body,div,dl,dt,dd,ul,ol,li,h1,h2,h3,h4,h5,h6,pre,form,fieldset,input,textarea,p,blockquote,th,td{padding:0;margin:0;}
ol,ul{list-style:none}
caption,th{text-align:left}
q:before,q:after{content:''}
abbr,acronym{border:0}
blockquote{color:#666;font-style:italic;}
sup,sub{line-height:0}
abbr,acronym{border-bottom:1px dotted #666}
/*clear clearfix*/
.clearfix:after {content: "\0020"; display: block; height: 0;clear: both; visibility: hidden; }
.clearfix {zoom: 1;} 
.clear{clear:both;}
body{font-family: arial,"Hiragino Sans GB","Microsoft Yahei",sans-serif;} 
#page{}
/* temp5 */
#temp5{overflow:auto}
#temp5 .JQ-content-box{width:100%;height:100px;min-height:25px;line-height:25px;overflow:hidden}
#temp5 .JQ-slide-content{}
#temp5 .JQ-slide-content li{height:25px;padding-left:10px;}

</style>

<script type="text/javascript" src="<%=path %>/js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="<%=path %>/h5file/js/jq.Slide.js"></script>
<script type="text/javascript">
$(function(){	
	$("#temp5").Slide({
		effect : "scroolTxt",
    	speed : "normal",
		timer : 3000,
		steps:4
	});
	
});
</script>
</head>
<body>
	
	<div id="page">	
		<div id="temp5">
			<div class="JQ-content-box">
				<ul class="JQ-slide-content">
				<%
					List advlist = (List)request.getAttribute("advlist");
					if(advlist != null){
					
					if(advlist.size()<5){
						int g=0;
						for(int i = 0; i<advlist.size();i++){
							TbAdvInfo adv = (TbAdvInfo)advlist.get(i);
				 %>
					<li><a href="http://<%=adv.getAdvUrl() %>" target="_blank"><%=adv.getAdvTitle() %> </a></li>
				<%
							g++;
							if(g==4){
								break;
							}
							if(g!=4 && i==advlist.size()-1){
								i = -1;
							}
						}
					}else{
						for(int i = 0; i<advlist.size();i++){
							TbAdvInfo adv = (TbAdvInfo)advlist.get(i);
				 %>
				 		<li><a href="http://<%=adv.getAdvUrl() %>" target="_blank"><%=adv.getAdvTitle() %></a> </li>
				 <%}} }%>
				</ul>
			</div>
		</div><!--temp5 end-->
	</div>
</body>
</html>