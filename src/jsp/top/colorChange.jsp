<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>ONEteams</title>
	</head>
	<body>
		<%--メニューバーをインクルードして表示させる CSSも同時に指定完了--%>
		<%@include file="../menu/menu.jsp" %><br>
		<main>
		<br>
		<h2>カラーパターン変更</h2>
		<br>
		<br>
		<br>
		<br>
		<br>
		<p><a class="btn-malformation" style="font-family: 'Ruska Display', 'sans-serif ';" href="../top/ColorChange.action?U_color=0">　　　NORMAL　MODE　　　</a></p>
		<br>
		<br>
		<br>
		<p><a class="btn-malformation" style="font-family: 'Ruska Display', 'sans-serif ';" href="../top/ColorChange.action?U_color=1">　　　DARK　MODE　　　</a></p>
		<br>
		<br>
		<br>
		<br>
		<br>
		<br>
		
		</main>
		<%--フッター全ページ共通 /body直前--%>
			<footer class="footer">
				<p>Copyright © One team All Rights Reserved.</p>
			</footer>
	</body>
</html>