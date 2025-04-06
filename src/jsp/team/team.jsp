<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import=" java.util.ArrayList"%>
<%@page import="java.util.Optional" %>
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

		<h2>${selectedTeam.getT_name()} TEAM</h2>
		<div class="float">
			<h3>メンバー追加</h3>
		<form action="../team/TeamInvite.action?U_ID=${users.getU_ID()}" method="POST">	
		<%-- <select  name="newCommerU_ID" multiple  required>
		<select  name="U_ID" multiple  required>
					<option value="select">選択してください</option> 
						<c:forEach var="user" items="${otherMembers}">
							<option value="${user.getU_ID()}" >${user.getU_ID()}：${user.getU_name() }</option>
						</c:forEach>
				</select>
				--%>
			<div class="scroll">
				<c:forEach var="user" items="${otherMembers}">
					<div id="textleft"><input type="checkbox" name="newCommerU_ID" value="${user.getU_ID()}">${user.getU_ID()}：${user.getU_name()}</div>
				</c:forEach>
			</div>
				
			<br>
			<button class="btn_23" type="submit" name= "memberadd" value="追加">メンバー追加</button>
			
			<br>
		</form>
		</div>
		<div>
		<br>
			<h3>チームメンバー</h3>
				<table class="flat-table">
					<tr>
						<th >ユーザID</th>
						<th>氏名</th>
					</tr>
					<c:forEach var="teams" items="${teamMembers}">
					<tr>
						<td>${teams.getU_ID()}</td>
						<td>${teams.getU_name()}</td>
					</tr>
					</c:forEach>
				</table>
				<%--チーム退出ボタン --%>
				<p><button class="btn_23" type="submit" name= "exit " onclick="location.href='../team/TeamLeave.action'">チームから退室する</button></p>
		</div>
		
		<br>
		<br>
		<br>
		<br>
		<br>
		<br>
		<div style="clear: both;">
		
		<h3>チームタスク</h3>
		<%--新規タスク作成ボタン --%>
		<p class="textleft"><button class="btn_23" type="submit" name= "maketask" onclick="location.href='../maketask/make-task.jsp'">新規タスク作成</button></p>
		<% Optional<String> currentPage = Optional.ofNullable(request.getParameter("intCurrentPage")); 
		int intCurrentPage = Integer.parseInt(currentPage.orElse("1")); %>
		<c:if test="<%= intCurrentPage > 1 %>">
			<a href="../team/team.jsp?intCurrentPage=<%= intCurrentPage - 1 %>">前</a>
		</c:if>
		<% ArrayList taskList = (ArrayList)pageContext.findAttribute("taskListOnTeam"); 
		int intMaxPage = (taskList.size() - 1) / 5 + 1;%>
		<a><%= intCurrentPage %>/<%=intMaxPage %></a>
		<c:if test="<%= intCurrentPage < intMaxPage %>">
			<a href="../team/team.jsp?intCurrentPage=<%= intCurrentPage + 1 %>">次</a><br>
		</c:if><br>
			<c:if test="${returnMassage == 1}">
				<p style="color:FF6633;">**「${task.getTSK_ID()}:${task.getTSK_taskname() }」の差戻しがありました。確認してください**</p>
			</c:if>
			<table class="flat-table">
				<tr>
					<th >タスク名</th>
					<th>担当者</th>
					<th>依頼者</th>
					<th>納期</th>
					<th>報告日</th>
					<th>完了日</th>
				</tr>
				<tr>
					<c:forEach var="tasks" items="${taskListOnTeam}"  begin="<%= (intCurrentPage - 1) * 5 %>" 
					end="<%= intCurrentPage * 5 - 1 %>">
						<tr>
							<td><button class="cp_link" type="submit" name= "TSK_ID" 
							onclick="location.href='../checktask/checktask.action?TSK_ID=${tasks.getTSK_ID()}'">
							${tasks.getTSK_ID()}：${tasks.getTSK_taskname()}</button></td>
							<td>${tasks.getU_name().toString().replaceAll("^\\[|\\]$", "").replaceAll(",", "<br>")}</td>
							<td>${tasks.getU_request_name()}</td>
							<td>${tasks.getTSK_term()}</td>
							<td>${tasks.getTSK_report_date()}</td>
							<td>${tasks.getTSK_completion_date()}</td>
						</tr>
					</c:forEach>
				</tr>
			</table>
		</div>
		</main>
			<%--フッター全ページ共通 /body直前--%>
				<footer class="footer">
				<p>Copyright © One team All Rights Reserved.</p>
			</footer>
	</body>
</html>