<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
				<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
		<title>ONE teams</title>
	</head>
	<body>
		<%--メニューバーをインクルードして表示させる CSSも同時に指定完了--%>
		<%@include file = "../menu/menu.jsp" %>
		<main>
		<br>
		<h3>パスワード変更</h3>
			<form action= "password.action?U_ID=${users.getU_ID()}" merhod="post">
				<div class="nice-wrap">
					<p><input class="nice-textbox" type= "password" name="U_pass" 
						pattern="^[a-zA-Z0-9]+$" maxlength="16" placeholder="現在のパスワード"required></p>
						<c:if test="${passwordError == 0}">
							<p style="color:#AA4D53;">パスワードに誤りがあります。</p>
						</c:if>
					<br>
					<p><input class="nice-textbox" type= "password" name="newU_pass1"
						pattern="^[a-zA-Z0-9]+$" minlength="4" maxlength="16" placeholder="新しいパスワード"required></p>
					<p><input class="nice-textbox"  type= "password" name="newU_pass2"
						pattern="^[a-zA-Z0-9]+$" minlength="4" maxlength="16" placeholder="新しいパスワード(２回目)"required></p>
							<c:if test="${passwordError == 2}">
								<p style="color:#AA4D53;">パスワードが重複しています。</p>
							</c:if>
					<br>
							<button class="btn btn-malformation" type="submit" >パスワード変更</button>
					<br>
					<br>
				</div>
			</form>
		</main>
		<%--フッター全ページ共通 /body直前--%>
			<footer class="footer">
				<p>Copyright © One team All Rights Reserved.</p>
			</footer>
	</body>
</html>