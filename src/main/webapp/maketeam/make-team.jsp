<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import=" java.util.ArrayList"%>

<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>ONEteams</title>
	</head>
	<body>
	<%--メニューバーをインクルードして表示させる CSSも同時に指定完了--%>
	<%@include file = "../menu/menu.jsp" %>
	<main>
	<br>
	<h2>チーム作成</h2>
	<div class="float">
			<h3>メンバー選択</h3>
			<form action= "../maketeam/DisplayMakeTeam.action" merhod="post">
				<!-- onclick="location.href='../maketeam/DisplayMakeTeam.action'" -->
				<input class="nice-textboxshort" type="text" name="keyword" placeholder="名前を入力してください">
				<%--検索ボタンアイコン --%>
				<button id="sbtn2" type="submit" name= "keyword"><i class="fa-solid fa-magnifying-glass"></i></button>
			</form>
			<form action= "../maketeam/PoolMembers.action" merhod="post">
			
				<br>
			<div class="scroll">
				<c:forEach var="user" items="${usersSearchedByName}">
					<div id="textleft"><input type="checkbox" name="selectedUserID" value="${user.getU_ID()}">${user.getU_name()}</div>
				</c:forEach>
			</div>
			<%-- 	<c:forEach var="user" items="${poolMemberList}"> --%>
			<%-- 		<div><input type="checkbox" name="selectedUserID" value="${user.getU_ID()}">${user.getU_name()}</input></div> --%>
			<%-- 	</c:forEach> --%>
			
			<br>
				<button class="btn_23" type="submit">1.メンバー追加</button>
				<br>
				<br>
				<br>
				<br>
			</form>
		
	</div>
		<%-- <form action= "maketeam.action?U_ID=${users.getU_ID()}" merhod="post"> --%>
	<br>
	<form action= "../maketeam/MakeTeam.action" merhod="post">
	
		<table class="flat-table">
			<tr>
				<th></th>
				<th >id</th>
				<th>名前</th>
			</tr>
			<c:forEach var="member" items="${poolMembersList}">
				<tr> 
					<%--ここにチェックが入っている人たちをチームに追加 --%>
					<td><c:choose>
						<c:when test="${member.getU_ID()== users.getU_ID()}">
							<input type="checkbox" name="registerUserID" value="${member.getU_ID()}" checked="checked" disabled>
							<input type="hidden" name="registerUserID" value="${member.getU_ID()}"/>
						</c:when>
						<c:otherwise>
							<input type="checkbox" name="registerUserID" value="${member.getU_ID()}" checked>
						</c:otherwise>
					</c:choose></td>
					<td>${member.getU_ID()}</td>
					<td>${member.getU_name()}</td>
				</tr>
			</c:forEach>
		</table>
		<br>
		チーム名入力<input class="nice-textboxshort" type="text" name="teamname" placeholder="チーム名を入力" maxlength ="16" required>
		<br>
		<br>
		<button class="btn_23" type="submit" name= "team" >2.チーム作成</button>
	</form>
	</main>
	
		<%--フッター全ページ共通 /body直前--%>
		<footer class="footer">
			<p>Copyright © One team All Rights Reserved.</p>
		</footer>
	
	</body>
</html>