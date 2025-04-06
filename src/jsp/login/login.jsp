<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>ONE teams</title>
		<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	</head>
	<link rel="stylesheet" href="/ONEteams/static/css/plain0.css">
	<body>
	<%--ロゴマーク中央寄せ表示(フォントにしよう) --%>
		
		<h1>ONE teams</h1>
		<form action="../login/Login.action" method="post">
			<div class="nice-wrap">
				<p><input class="nice-textbox" type="text" name="U_name" maxlength="16" placeholder="ユーザ名" required /></p>
				<p><input class="nice-textbox" type="password" name="U_pass" 
				pattern="^[a-zA-Z0-9]+$" minlength="4" maxlength="16" placeholder="パスワード" required /></p>
			<br>
			<br>
		<c:if test="${loginError == 1}">
			<p style="color:#AA4D53;">ユーザ名もしくはパスワードに誤りがあります。</p>
		</c:if>
		<c:if test="${loginError == 2}">
			<p style="color:#AA4D53;">退職済みユーザのため、ログインできません。</p>
		</c:if>
		<button class="btn btn-malformation" type="submit" >LOGIN</button>
		</div>
		</form>
		<%--フッター全ページ共通 /body直前--%>
			<footer class="footer">
				<p>Copyright © One team All Rights Reserved.</p>
			</footer>
	
	</body>
</html>