<%@ page language="java" pageEncoding="UTF-8"%>
<%@page import="java.util.Date"%>

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
	FILTER: progid : DXImageTransform . Microsoft .
		Gradient(GradientType = 0, StartColorStr = #ffffff, EndColorStr =
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
<style>
		#out {
			overflow: hidden;
			width: 100%;
			/* height: 124px;  */
			/* margin: 0px auto; */
		}
		#in {
			overflow: hidden;
			/* width: 640px; */
			/*  height: 124px;  */
		}
		#in img {
			display: block;
			width: 100%;
			float: left;
		}
		body {
			margin: 0px;
			padding: 0px;
		}
	</style>
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

#page{}

/* slide-box */
#slide-box{width:690px;position:relative;height:472px;}
#slide-box .corner{clear:both;border-top:#333 1px solid;display:block;overflow:hidden;height:0;margin:0 1px;}
#slide-box .slide-content{background:#333;padding:10px;}
#slide-box .JQ-slide-content{position:absolute;}
#slide-box .JQ-slide-nav a{display:block;z-index:99;width:37px;color:#b4b4b4;position:absolute;top:205px;height:65px;text-decoration:none;}
#slide-box .JQ-slide-nav span{display:block;background:#4b4b4b;font:700 53px arial;width:37px;cursor:pointer;height:63px;text-align:center;}
#slide-box .JQ-slide-nav .corner{border-color:#4b4b4b;}
#slide-box .JQ-slide-nav .prev{left:-10px;}
#slide-box .JQ-slide-nav .next{right:-10px;}
#slide-box .wrap{overflow:hidden;width:670px;height:450px;position:relative;}
#slide-box ul{width:10000px;}
#slide-box li{float:left;width:340px;height:450px;}
#slide-box li img{width:330px;height:450px;}
#slide-box .JQ-slide-nav a:hover,#slide-box .JQ-slide-nav a:hover span{color:#f43d1e;}
/* temp5 */
#temp5{overflow:auto}
#temp5 .JQ-content-box{width:300px;height:100px;min-height:25px;line-height:25px;border:#ccc 1px solid;overflow:hidden}
#temp5 .JQ-slide-content{}
#temp5 .JQ-slide-content li{height:25px;padding-left:10px;}

</style>

		<link href="css/manage.css" rel="stylesheet" type="text/css">
		<link href="css/list.css" rel="stylesheet" type="text/css">
		<link rel="stylesheet" href="h5file/css/index.css" type="text/css">
		<link rel="stylesheet" href="h5file/css/public.css" type="text/css">
		<script language="javascript" type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
		<script src="h5file/js/public/jquery183.js" type="text/javascript" charset="utf-8"></script>
	<script src="h5file/js/public/tween.js" type="text/javascript" charset="utf-8"></script>
	<script type="text/javascript" src="h5file/js/jq.Slide.js"></script>
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
		<style type="text/css">
<!--
.STYLE9 {
	color: #000000;
	font-weight: bold;
	text-indent: 10px;
}
-->
</style>
		<title>广告预览</title>
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
									<span class="STYLE4">您现在的位置：<span class="STYLE7">广告预览</span>
									</span>
								</td>
								<td width="52%">
									<img src="images/3_03.jpg" width="518" height="35">
								</td>
							</tr>
						</table>
						<br>
						<br>
						<%
							TbAdvInfo adv = (TbAdvInfo)request.getAttribute("adv");
						 %>
						 <form action="" name="advform" method="post">
						 
						 	<input type="hidden" name="advTitle" value="<%=adv.getAdvTitle() %>">
						 <input type="hidden" name="advType" value="<%=adv.getAdvType() %>">
						 <input type="hidden" name="advContent" value="<%=adv.getAdvContent() %>">
						 <input type="hidden" name="fileurl" value="<%=adv.getPicUrl() %>">
						 <input type="hidden" name="advUrl" value="<%=adv.getAdvUrl() %>">
						 <input type="hidden" name="sendSTime" value="<%=adv.getSendSTime() %>">
						 <input type="hidden" name="sendETime" value="<%=adv.getSendETime() %>">
						 </form>
						 <%
						 if(adv.getAdvType()==0){//文字显示
						 	adv.setCreatTime(new Date());
						 %>
						 <div id="page" align="center">	
		<div id="temp5">
			<div class="JQ-content-box">
				<ul class="JQ-slide-content">
					<li><%=adv.getAdvTitle() %><span style="padding-right: 5px;"> <%=adv.getCreatTime() %></span></li>
					<li><%=adv.getAdvTitle() %> <%=adv.getCreatTime() %></li>
					<li><%=adv.getAdvTitle() %> <%=adv.getCreatTime() %></li>
					<li><%=adv.getAdvTitle() %> <%=adv.getCreatTime() %></li>
					<li><%=adv.getAdvTitle() %> <%=adv.getCreatTime() %></li>
					<li><%=adv.getAdvTitle() %> <%=adv.getCreatTime() %></li>
					<li><%=adv.getAdvTitle() %> <%=adv.getCreatTime() %></li>
					<li><%=adv.getAdvTitle() %> <%=adv.getCreatTime() %></li>
					<li><%=adv.getAdvTitle() %> <%=adv.getCreatTime() %></li>
					<li><%=adv.getAdvTitle() %> <%=adv.getCreatTime() %></li>
					<li><%=adv.getAdvTitle() %> <%=adv.getCreatTime() %></li>
					<li><%=adv.getAdvTitle() %> <%=adv.getCreatTime() %></li>
				</ul>
			</div>
		</div><!--temp5 end-->
	</div>
						 <%
						 	}else if(adv.getAdvType()==1){//图片显示			 
						  %>
						 <article class="i_top">
			<div id="out">
				<div id="in">
					<ul></ul>
				</div>
				<div class="ad_btn_wrap"></div>
			</div>
		</article>
						 <%} %>

<br/>
<br/>
<div align="center">
<input class=btn type="button" name="button" value="返回"
											onclick="touserlist()" />
						<input class=btn type="button" name="button" value="提交"
											onclick="yulanadd()" />
											
											</div><br/>
<br/><br/>
<br/>
	</body>
</html>
<script language="javascript">
function touserlist(){
 document.location.href="javascript: history.back(-1)";
}
function yulanadd(){
	advform.action="adv.do?CMD=yulanadd";
	advform.submit();
 //document.location.href="adv.do?CMD=yulanadd";
}
function goload() 
{ 
 var mes="<%=request.getAttribute("message")%>";
  if(mes!="null"){
    alert(mes);
  } 
} 

$(document).ready(function() {
			var lst = [
				'<%=adv.getPicUrl() %>',
				'<%=adv.getPicUrl() %>',
				'<%=adv.getPicUrl() %>',
				'<%=adv.getPicUrl() %>',
				'<%=adv.getPicUrl() %>'
			];
			var htm = '';
			$(lst).each(function(k, v) {
				htm += "<li name='is_tj_r' style='padding-right: 0;'>";
				htm += '<a href="http://<%=adv.getAdvUrl() %>" target="_blank"><img  src="<%=adv.getPicUrl() %>" /></a>';
				htm += '</li>';
				$('.ad_btn_wrap').append('<a href="javascript:;"></a>');
			});
			/*
			 * 注意下面一截代码！
			 * 这些代码一个都不能少，否则css会出问题。
			 * 只需要把lst换成对应的图片集合就ok了
			 */
			$("#in ul").empty().append(htm);
			$("#in ul").css("width", (lst.length * 100) + "%");
			$("#in ul li").css("width", (100 / lst.length) + "%");
			$(lst).each(function(k1, v1) {
				if (k1 != 0)
					$(".ad_btn_wrap a").eq(k1).css("margin-left", "2%");
			});
			bindEvent();
		});
		var adIndex = 0,
			timer,
			autoTimer;
		 //广告图的滚动
		function adMove(el) {
			clearInterval(autoTimer);
			var ind = 0;
			var start = el.scrollLeft;
			var end = el.clientWidth * adIndex;
			var change = end - start;
			var max = $('#in li').length;

			clearInterval(timer);
			timer = setInterval(function() {
				ind++;
				if (ind == 20) {
					$('.ad_btn_wrap a').eq(adIndex).css('background', 'black'); //ff7b23
					clearInterval(timer);
					autoTimer = setInterval(function() {
						adIndex++;
						if (adIndex >= max) {
							adIndex = 0;
						};
						$('.ad_btn_wrap a').css('background', '#888888');
						adMove(document.getElementById('in'));
					}, 3333);
				}
				el.scrollLeft = Tween.Expo.easeOut(ind, start, change, 20);
			}, 33);
		}


		function bindEvent() {
			//广告事件
			$('#in').on('touchstart', 'img', function(event) {
				var wrap = $(this).parent().parent().parent();
				adIndex = $(this).parent().index();
				pageXStart = event.originalEvent.targetTouches[0].pageX;
			});

			$('#in').on('touchend', 'img', function(event) {
				pageXEnd = event.originalEvent.changedTouches[0].pageX;
				if (pageXEnd - pageXStart > 30) {
					// 左移  && adIndex != 0
					if (adIndex <= 0) {
						adIndex = $('#in li').length;
					};
					adIndex--;
					$('.ad_btn_wrap a').css('background', '#888888');
					adMove(document.getElementById('in'));
				} else if (pageXEnd - pageXStart < -30) {
					// 右移 && adIndex + 1 != $('#in li').length
					if (adIndex + 1 >= $('#in li').length) {
						adIndex = -1;
					};
					adIndex++;
					$('.ad_btn_wrap a').css('background', '#888888');
					adMove(document.getElementById('in'));
				}
			});

			autoTimer = setInterval(function() {
				adIndex++;
				if (adIndex >= 4) {
					adIndex = 0;
				}
				$('.ad_btn_wrap a').css('background', '#888888');
				adMove(document.getElementById('in'));
			}, 3333);
		}

</script>
