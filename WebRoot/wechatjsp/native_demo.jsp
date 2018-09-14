<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta name="keywords" content="关键字">
  		<meta name="description" content="描述">
  		<script type="text/javascript" src="wechatjsp/jquery.min.js"></script>
  		 <script  type="text/javascript"  src="wechatjsp/qrcode.js"></script>
		<title>扫码支付前台demo</title>
		
	</head>

	 <body>
	 <div  style="width:100%;height:100%;display: table;" >
	<div align="center" style="width: 100%; display:table-cell; vertical-align: middle;" id="qrcode">
	<font>微信扫码支付<br/>
	支付金额：<%=request.getAttribute("paymoney") %>元
	</font>
	</div>
</div>
 </body>
<script type="text/javascript">
//这个地址是Demo.java生成的code_url,这个很关键
	var url = "<%=request.getAttribute("codeurl") %>";
	//参数1表示图像大小，取值范围1-10；参数2表示质量，取值范围'L','M','Q','H'
	var qr = qrcode(10, 'H');
	qr.addData(url);
	qr.make();
	var dom=document.createElement('DIV');
	dom.innerHTML = qr.createImgTag();
	var element=document.getElementById("qrcode");
	element.appendChild(dom);

 </script>

</html>
