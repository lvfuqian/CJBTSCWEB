<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ page import="java.util.List"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
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
	FILTER: progid :     DXImageTransform.Microsoft.Gradient (    
		GradientType =     0, StartColorStr =     #ffffff, EndColorStr =    
		#cecfde );
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
		<title>添加电召用户信息</title>
	</head>

	<body>
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
									<span class="STYLE4">您现在的位置：<span class="STYLE7">添加电召用户信息</span>
									</span>
								</td>
								<td width="52%">
									<img src="images/3_03.jpg" width="518" height="35">
								</td>
							</tr>
						</table>
						<br>
						<br>
						<form name="phoneuserform" onSubmit="return formcheck();" id="phoneuserform"
							method="post">
							<table align="center" width="450" border="1" class="listtable"
								bordercolor="#C1DAD7">
								<tr>
									<td colspan="2" align="center" class="list_header">
										<span class="STYLE77">添加电召用户信息</span>
									</td>
								</tr>
								<tr>
									<td width="15%" align="right">
										<label>
											姓名:
										</label>
									</td>
									<td width="40%" align="left">
										<div align="left">
											<input type="text" name="userNames" id="userNames" />
											<font color="red">&nbsp;<B>*</B> </font>
										</div>
									</td>
								</tr>
								<tr>
									<td align="right">
										性别:
									</td>
									<td align="left">
										<div align="left">
											<select name="userSexs" id="userSexs"
												style="width: 128; margin: 0; FONT-SIZE: 9pt; FONT-WEIGHT: normal;">
												<option value="0">
													男
												</option>
												<option value="1">
													女
												</option>
											</select>
											<font color="red">&nbsp;<B>*</B> </font>
										</div>
								</tr>
								<tr>
									<td width="15%" align="right">
										<label>
											登录密码:
										</label>
									</td>
									<td width="40%" align="left">
										<div align="left">
											<input type="text" name="userPwd" id="userPwd" value="123456" />
											<font color="red">&nbsp;<B>*</B> </font>
										</div>
									</td>
								</tr>
								<tr>
									<td align="right">
										<label>
											身份证:
										</label>
									</td>
									<td align="left">
										<input type="text" name="userIdCard" id="userIdCard" maxlength="18"/>
										<font color="red">&nbsp;<B>*</B> </font>
									</td>
								</tr>
								<tr>
									<td align="right">
										<label>
											联系电话:
										</label>
									</td>
									<td>
										<input type="text" name="userMobiles" id="userMobiles"  maxlength="15"/>
									</td>
								</tr>
								<tr>
									<td align="right">
										<label>
											联系地址:
										</label>
									</td>
									<td>
										<input type="text" name="userAddress" id="userAddress"
											value="" />
									</td>
								</tr>

								<tr>
									<td colspan="2" align="center" class="list_footer">
										<input class=btn type="submit" name="submit" value="提交" />
										&nbsp;
										<input class=btn type="button" name="button" value="返回"
											onclick="toddmsmslist()" />
										&nbsp;
									</td>
								</tr>
							</table>
						</form>
					</td>
				</tr>
			</table>
		</td>
	</body>
</html>
<script type="text/javascript">
	//<!--提交时表单验证   
	function formcheck() {
		if (phoneuserform.userNames.value == "") {
			alert("姓名不能为空!");
			phoneuserform.userNames.focus();
			return false;
		}
		if (phoneuserform.userPwd.value == "") {
			alert("登录密码不能为空!");
			phoneuserform.userPwd.focus();
			return false;
		}
		if (phoneuserform.userPwd.value != "") {
			if (phoneuserform.userPwd.value.length < 6) {
				alert("登录密码长度必须大于或等于6位!");
				return false;
			} else if (!isCheck(phoneuserform.userPwd.value)) {
				alert("登录密码只能为(a-zA-Z0-9)的字符,请重新输入!");
				return false;
			}
		} else if (phoneuserform.userPwd.value == "") {
			alert("请输入登录密码!");
			phoneuserform.userPwd.focus();
			return false;
		}

		if (phoneuserform.userIdCard.value != "") {
			if (!isIdCard(phoneuserform.userIdCard.value)) {
				alert('输入的身份证号长度不对，或者号码不符合规定！\n15位号码应全为数字，18位号码末位可以为数字或X.');
				return false;
			}
		}
		if (phoneuserform.userMobiles.value != "") {
			if (!isMobile(phoneuserform.userMobiles.value)) {
				alert('输入的电话号码长度不对，或者号码不符合规定！\n11位号码应全为数字,格式为(1(3,5,8)8位数字)!');
				return false;
			}
		}
		
		if(confirm("是否确认添加该信息!"))
			phoneuserform.action = "puser.do?CMD=1";
		//phoneuserform.submit();

	}

	function isCheck(s) {
		var patrn = /^[a-zA-Z0-9]{6,30}$/;
		if (!patrn.exec(s))
			return false;
		return true;
	}
	function isIdCard(s) {
		s = s.toUpperCase();
		var patrn = /(^\d{15}$)|(^\d{17}([0-9]|X))$/;
		if (!patrn.test(s))
			return false;
		return true;
	}
	function isMobile(s) {
		var patrn = /^1[358]\d{9}$/;
		if(!patrn.test(s))
			return false;
		return true;
	}
	function toddmsmslist() {
		url = "puser.do?CMD=0";
		document.location.href = url;
	}

	function rolecheck() {
	}
</script>


