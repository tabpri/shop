<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page isELIgnored="false" %>

<html>
<head>
<title>ショッピングカートオペレータログイン</title>
<style>
#login-box {
	width: 300px;
	padding: 20px;
	margin: 100px auto;
	background: #fff;
	-webkit-border-radius: 2px;
	-moz-border-radius: 2px;
	border: 1px solid #000;
}
</style>
</head>
<body onload='document.loginForm.username.focus();'>

		
		<h3>ショッピングカートオペレータログイン</h3>

		<c:if test="${param.loginFailed}">
			<div style="color:red">ユーザー名かパスワードが無効</div>
			<br>
		</c:if>
		<form name='loginForm'
			action="<c:url value='/j_spring_security_check' />" method='POST'>

			<table>
				<tr>
					<td>Eメール：</td>
					<td><input type='text' name='username'></td>
				</tr>
				<tr>
					<td>パスワード：</td>
					<td><input type='password' name='password' /></td>
				</tr>
				<tr>
					<td colspan='2' align="right"><input name="submit" type="submit"
						value="ログイン" /></td>
				</tr>
			</table>
		</form>
	</div>

</body>
</html>