<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.Optional" %>
<%@page import=" java.util.ArrayList"%>
<%@page import=" bean.Task"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>ONEteams</title> 
	</head>
	<body>
	<%--メニューバーをインクルードして表示させる CSSも同時に指定完了--%>
		<%@include file="/menu/menu.jsp" %><br>
		<main> 
		<br>
		<br>
		<br>
		<br>
		<span>
		<%--左から色変わるボタン --%>
		<span><button class="btn_23" type="submit" name= "make-team" onclick="location.href='../maketeam/DisplayMakeTeam.action'">チーム作成</button></span>
		<span><button class="btn_23" type="submit" name= "password" onclick="location.href='../top/password.jsp'">パスワード変更</button></span>
			<c:if test= "${users.getU_admin() == 1}">
				<span><button class="btn_23" type="submit" name= "admin" onclick="location.href='../admin/Admin.action'">管理者画面</button></span>
			</c:if>
		</span>
		<span><button class="btn_23" type="submit" name= "make-team" onclick="location.href='../top/colorChange.jsp'">モード選択</button></span>
	<h3>所属チーム一覧</h3>
		<c:forEach var="team" items="${teamList}">
		<span class="space">
		<button class="btn btn-malformation" type="submit" name= "T_ID" onclick="location.href='../team/team.action?T_ID=${team.getT_ID()}'">${team.getT_ID()}：${team.getT_name()}</button>
		</span>
		</c:forEach>
	</form>
	
	<%--担当タスク　担当者でソートかけているものを表示 --%>
	<h3>担当タスク一覧</h3>
	<% int intLines = 5;
	//依頼されたタスク表のための処理
	Optional<String> PageRequested = Optional.ofNullable(request.getParameter("pageRequested")); 
	int intPageRequested = Integer.parseInt(PageRequested.orElse("1"));
	ArrayList requestedTaskList =(ArrayList)pageContext.findAttribute("requestedTaskList"); 
	int intMaxPageRequested = (requestedTaskList.size() - 1) / intLines + 1;
	//依頼したタスク表のの処理
	Optional<String> PageRequest = Optional.ofNullable(request.getParameter("pageRequest")); 
	int intPageRequest = Integer.parseInt(PageRequest.orElse("1"));
	ArrayList requestTaskList = (ArrayList)pageContext.findAttribute("requestTaskList"); 
	int intMaxPageRequest = (requestTaskList.size() - 1) / intLines + 1; %>
	
	<c:if test="<%= intPageRequested > 1 %>">
		<a href="../top/top.jsp?pageRequested=<%= intPageRequested - 1 %>&pageRequest=<%= intPageRequest %>">前</a>
	</c:if>
	<a><%= intPageRequested %>/<%=intMaxPageRequested %></a>
	<c:if test="<%= intPageRequested < intMaxPageRequested %>">
		<a href="../top/top.jsp?pageRequested=<%= intPageRequested + 1 %>&pageRequest=<%= intPageRequest %>">次</a>
	</c:if>
	<!-- 完了を除外 -->
	<% Optional<String> select = Optional.ofNullable(request.getParameter("selectIncomplete")); %>
	<span class="space"><button class="btn btn-malformation" onclick="location.href='../top/PickUpIncomplete.action?onlyIncomplete=1'">未完了のみ表示</button></span>
	<span class="space"><button class="btn btn-malformation" onclick="location.href='../top/PickUpIncomplete.action?onlyIncomplete=0'">全件表示</button></span>
	<br>
	<%--表はすべてフラットテーブルを流用しましょう --%>
		<table class="flat-table">
			<tr>
				<th >タスクID：タスク名</th>
				<th>担当者</th>
				<th>依頼者</th>
				<th>納期</th>
				<th>報告日</th>
				<th>完了日</th>
			</tr>
			<c:forEach var="tasks" items="${requestedTaskList}" begin="<%= (intPageRequested - 1) * intLines %>" 
			end="<%= intPageRequested * intLines - 1 %>">
				<tr> 
					<%--タスク名はホバーで下線が現れるリンク --%>
					<td><button class="cp_link" type="submit" name= "TSK_ID" onclick="location.href='../checktask/checktask.action?TSK_ID=${tasks.getTSK_ID()}'">${tasks.getTSK_ID()}：${tasks.getTSK_taskname()}</button></td>
					<td>${tasks.getU_name().toString().replaceAll("^\\[|\\]$", "").replaceAll(",", "<br>")}</td>
					<td>${tasks.getU_request_name()}</td>
					<td>${tasks.getTSK_term()}</td>
					<td>${tasks.getTSK_report_date()}</td>
					<td>${tasks.getTSK_completion_date()}</td>
				</tr>
			</c:forEach>
		</table>
			
	<%--依頼タスク　依頼者でソートかけているものを表示 --%>
	<h3>依頼タスク一覧</h3>
	<c:if test="<%= intPageRequest > 1 %>">
		<a href="../top/top.jsp?pageRequested=<%= intPageRequested %>&pageRequest=<%= intPageRequest - 1 %>">前</a>
	</c:if>
	<a><%= intPageRequest %>/<%=intMaxPageRequest %></a>
	<c:if test="<%= intPageRequest < intMaxPageRequest %>">
		<a href="../top/top.jsp?pageRequested=<%= intPageRequested %>&pageRequest=<%= intPageRequest + 1 %>">次</a>
	</c:if>
	<br>
		<table class="flat-table">
				<tr>
					<th>タスクID：タスク名</th>
					<th>担当者</th>
					<th>依頼者</th>
					<th>納期</th>
					<th>報告日</th>
					<th>完了日</th>
				</tr>
			<c:forEach var="tasks" items="${requestTaskList}" begin="<%= (intPageRequest - 1) * intLines %>" 
			end="<%= intPageRequest * intLines - 1 %>">
				<tr>
					<td><button class="cp_link" type="submit" name= "TSK_ID" onclick="location.href='../checktask/checktask.action?TSK_ID=${tasks.getTSK_ID()}'">${tasks.getTSK_ID()}：${tasks.getTSK_taskname()}</button></td>
					<td>${tasks.getU_name().toString().replaceAll("^\\[|\\]$", "").replaceAll(",", "<br>")}</td>
					<td>${tasks.getU_request_name()}</td>
					<td>${tasks.getTSK_term()}</td>
					<td>${tasks.getTSK_report_date()}</td>
					<td>${tasks.getTSK_completion_date()}</td>
				</tr>
			</c:forEach>
				
		</table>
		</main>
	<%--フッター全ページ共通 /body直前--%>
			<footer class="footer">
				<p>Copyright © One team All Rights Reserved.</p>
			</footer>
	
	</body>
</html>