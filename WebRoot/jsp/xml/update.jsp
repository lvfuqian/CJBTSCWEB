<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>TSC 参数配置修改</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link href="css/list.css" rel="stylesheet" type="text/css" />
		<link href="styles/val.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="scripts/boot.js"></script>

		<style type="text/css">
.btn {
	BORDER-RIGHT: #7b9ebd 1px solid;
	PADDING-RIGHT: 2px;
	BORDER-TOP: #7b9ebd 1px solid;
	PADDING-LEFT: 2px;
	FONT-SIZE: 12px;
	FILTER: progid :                         
		   DXImageTransform.Microsoft.Gradient (     
		            GradientType =                             0,
		StartColorStr = 
		       #ffffff, EndColorStr =           
		            #cecfde );
	BORDER-LEFT: #7b9ebd 1px solid;
	CURSOR: hand;
	COLOR: black;
	PADDING-TOP: 2px;
	BORDER-BOTTOM: #7b9ebd 1px solid
}
</style>
	</head>

	<body>
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
								<span class="STYLE4">您现在的位置： <span class="STYLE7">TSC配置参数修改</span>
								</span>
							</td>
							<td width="52%">
								<img src="images/3_03.jpg" width="518" height="35">
							</td>
						</tr>
					</table>
					<form name="xmlForm" id="" method="post">
					<div class="tdiv" id="xmlForm">
						<c:set var="bean" value="${requestScope.xmlBean }" />
						<table align="center" width="450" border="1" bordercolor="#C1DAD7">
							<tr>
								<td align="right">
									参数名称
								</td>
								<td>
									<input type="hidden" name="eName" id="eName" value="${bean.eName }" />
									<input type="text" name="nName" id="nName"
										value="${bean.eName }" class="mini-textbox"
										style="width: 155px;" onvalidation="onEnglishValidation" />
								</td>
							</tr>
							<tr>
								<td align="right">
									参数类型
								</td>
								<td>
									<select name="eType" id="eType" class="mini-combobox"
										style="width: 155px;" textField="text">
										<option>
											请选择类型
										</option>
										<c:forEach items="${requestScope.logList }" var="loglist">
											<c:if test="${loglist.id==bean.eType }">
												<option value="${loglist.id }" selected="selected">
													${loglist.typeText }
												</option>
											</c:if>
											<c:if test="${loglist.id!=bean.eType }">
												<option value="${loglist.id }">
													${loglist.typeText }
												</option>
											</c:if>
										</c:forEach>
									</select>
								</td>
							</tr>
							<tr>
								<td align="right">
									参数值
								</td>
								<td>
									<input type="text" id="eValue" name="eValue"
										value="${bean.eValue }" class="mini-textbox"
										style="width: 155px;" />
								</td>
							</tr>
							<tr>
								<td align="right">
									参数说明
								</td>
								<td>
									<input type="text" id="eComment" name="eComment"
										value="${bean.eComment }" class="mini-textbox"
										style="width: 155px;" />
								</td>
							</tr>
							<tr>
								<td>
									&nbsp;
								</td>
								<td>
									<input type="button" onclick="submitForm()" value="保存" />
									&nbsp;&nbsp;
									<input type="button" value="取消" onclick="tolist()" />
								</td>
							</tr>
						</table>
					</div>
					</form>
				</td>
			</tr>
		</table>
		<script type="text/javascript">
	mini.parse();
	function submitForm() {
		var form = new mini.Form("#xmlForm");
		form.validate();
		if (form.isValid() == false)
			return;
		alert($("#eName").val());
		//提交数据
		var data = form.getData();
		data+=",'eName:'+"+$("#eName").val();
		var json = mini.encode(data);
		$.ajax({
			url : "xml.do?method=update",
			type : "post",
			data : {
				submitData : json
			},
			success : function() {
				alert("修改成功!");
				document.location.href = "xml.do?method=listAction";
			}
		});
	}
	//
	function onEnglishValidation(e) {
		if (e.isValid) {
			if (isEnglish(e.value) == false) {
				e.errorText = "参数名称必须是英文";
				e.isValid = false;
			}
		}
	}
	/* 是否英文 */
	function isEnglish(v) {
		var re = new RegExp("^[a-zA-Z\-]+$");
		if (re.test(v))
			return true;
		return false;
	}
	function isType(e) {
		var t = e.value;
		switch (t) {
		case "1":
			//$("#eValue").attr("disabled",true);
			break;
		case "2":
			break;
		case "3":
			break;
		case "4":
			break;
		case "5":
			break;
		case "6":
			break;
		case "7":
			break;
		default:
			break;
		}
	}
	/* 是否英文或者数字 */
	function isEnglishAndNumber(v) {
		var re = new RegExp("^[(0-9|a-zA-Z\_)]+$");
		if (re.test(v))
			return true;
		return false;
	}
	function tolist() {
		document.location.href = "xml.do?method=listAction";
	}
</script>

	</body>
</html>
