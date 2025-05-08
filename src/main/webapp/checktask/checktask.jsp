<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session= "true" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>ONEteams</title>
	</head>
	<body>
	<%--メニューバーをインクルードして表示させる 同時にCSSのファイルの指定--%>
		<%@include file="/menu/menu.jsp" %>
	<main>
	<br>
	<br>
		<h2>タスク確認</h2>
		<form action ="../updatetask/updatetask.jsp"  method="post">
			<table class="checktask-table" >
				<tr>
					<th>タスクID</th>
					<td>${task.getTSK_ID()}</td>
				</tr>
				<tr>
					<th>タスク名</th>
					<td>${task.getTSK_taskname()}</td>
				</tr>
				<tr>
					<th>納期</th>
					<td>${task.getTSK_term()}</td>
				</tr>
				<tr>
					<th>依頼者</th>
					<td>${task.getU_request_name()}</td>
				</tr>
				<tr>
					<th>担当者</th>
					<td><c:forEach var="user" items="${task.getU_name()}">
						<input type="hidden" name="requesteduser" value="${user}">${user} /
					</c:forEach></td>
				</tr>
				<tr >
					<th>タスク内容</th>
					<td>${task.getTSK_contents()}</td>
				</tr>
				<tr >
					<th>報告日</th>
					<td>${task.getTSK_report_date()}</td>
				</tr>
				<tr >
					<th>完了日</th>
					<td>${task.getTSK_completion_date()}</td>
				</tr>
			</table>
			<br>
			<br>
			<%--Acionかまずに情報をupdatetask.jspへ送信する 
			<input type="hidden" name="TSK_ID" value="${task.getTSK_ID()}">
			<input type="hidden" name="TSK_taskname" value="${task.getTSK_taskname()}">
			<input type="hidden" name="TSK_term" value="${task.getTSK_term()}">
			<input type="hidden" name="U_request_name" value="${task.getU_request_name()}">
			<input type="hidden" name="TSK_contents" value="${task.getTSK_contents()}">--%>
			
			<c:if test="${empty task.getTSK_completion_date() }">
				<button class="btn_23" type="submit" name="updatetask">タスク編集</button>
			</c:if>
			</form>
			<br>
			<c:choose>
			<%--報告と完了をボタンタグにしました。 --%>
				<c:when  test= "${task.getU_request_ID().equals (users.getU_ID())}">
					<c:choose>
						<c:when test= "${empty task.getTSK_completion_date()}">
						<button class="btn_23" type="submit" name="Status" onclick="location.href='../checktask/ProgressTask.action?Status=2'">完了</button>
					</c:when>
						<c:when test= "${!empty task.getTSK_completion_date()}">
							<p style="color:#FF6633;">タスク完了済みです。　**完了日**${task.getTSK_completion_date() }
							<button class="btn_23" type="submit" name="Status" onclick="location.href='../checktask/ProgressTask.action?Status=4'">完了差戻</button></p>
						</c:when>
					</c:choose>
				</c:when>
				<c:when test= "${task.getU_ID().contains(users.getU_ID())}">
					<c:choose>
						<c:when test= "${empty task.getTSK_report_date() && empty task.getTSK_completion_date()}">
							<button class="btn_23" type="submit" name="Status" onclick="location.href='../checktask/ProgressTask.action?Status=1'">報告</button>
						</c:when>
						<c:when test= "${!empty task.getTSK_report_date()}">
							<p style="color:#FF6633;">タスク報告済みです。　**報告日**${task.getTSK_report_date() }
							<button class="btn_23" type="submit" name="Status" onclick="location.href='../checktask/ProgressTask.action?Status=3'">報告差戻</button></p>
						</c:when>
					</c:choose>
				</c:when>		
			</c:choose>
			
			<br>
			<br>
			<br>
			<%--チームページへ戻るボタン --%>
		<a class="btn-malformation" style="font-family: 'Ruska Display', 'sans-serif ';" href ="../team/team.action?T_ID=${task.getT_ID()}">TEAM PAGE</a>
	</main>
		<%--フッター全ページ共通 /body直前--%>
			<footer class="footer">
				<p>Copyright © One team All Rights Reserved.</p>
			</footer>
		
	</body>
</html>