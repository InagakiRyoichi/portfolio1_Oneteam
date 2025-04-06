<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" session="true"%>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>ONEteams</title>
</head>
<link rel="stylesheet" href="/ONEteams/static/css/plain.css">
<body>
<%--メニューバーをインクルードして表示させる 同時にCSSも指定できる --%>
<p><%@include file="/menu/menu.jsp" %></p>
<main>
	<br>
	<br>
	<br>
	<br>
		<H2>ユーザ情報変更</H2>
	
		<H3>***変更ユーザ***</H3>
	
		<table class="flat-table">
			<tr>
				<th>ユーザID</th>
				<th>ユーザ名</th>
				<th>管理者権限</th>
			</tr>
			<tr>
				<td>${updateuser.getU_ID()}</td>
				<td>${updateuser.getU_name()}</td>
				<td>${updateuser.getU_admin()}</td>
			</tr>
		</table>
	
		<H3>***変更内容***</H3>
		<form action = "Userupdate.action?U_ID=${updateuser.getU_ID()}" merhod="post">
			<p><input class="nice-textbox" type="text" name="newU_name" value="${updateuser.getU_name()}" maxlength="16" required></p>
			<select class="nice-textbox" name="newclassification" value ="権限選択">
					<option value="0">一般ユーザ</option>
					<option value="1">管理者</option>
					<option value="2">退職者</option>
				</select></p>
				<p><button class="btn_23" type="submit" >ユーザ情報変更</button><p>
				<input type="hidden" name="U_pass" value="${updateuser.getU_pass()}" >
				<input type="hidden" name="U_name" value="${updateuser.getU_name()}">
		</form>
		<br>
		<br>
		<a class="btn-malformation" href ="../admin/admin.jsp">管理者ページに戻る</a>
	</main>
		<%--フッター全ページ共通 /body直前--%>
			<footer class="footer">
				<p>Copyright © One team All Rights Reserved.</p>
			</footer>

</body>
</html>