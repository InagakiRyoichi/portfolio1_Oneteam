<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session= "true" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.Optional" %>
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
	<br>
	<br>
	<br>
	<br>
	<H2>管理者画面</H2>
		<form action = "Useradd.action" merhod="post">
		<div class="float_test">
		<H3>ユーザ新規登録</H3>
			<p><input class="nice-textboxshort" type="text" name="name" placeholder="ユーザ名" maxlength="16"required></p>
			<p><input class="nice-textboxshort" type="password" name="password" pattern="^[a-zA-Z0-9]+$" 
						minlength="4" maxlength="16" placeholder="パスワード"required></p>
			<p>
				<select class="nice-textboxshort" name="classification">
					<option value= "0">一般ユーザ</option>
					<option value= "1">管理者</option>
				</select>
			</p>
			
			<input type="hidden" name="ID" value="0">
			<c:if test="${UseraddError == 0}">
				<p style="color:#AA4D53;">**ERROR：再登録してください**</p>
			</c:if>
			<c:if test="${UseraddError == 2}">
				<p style="color:#AA4D53;">**パスワードが重複しています**</p>
			</c:if>
			<p><button class="btn_23" type="submit">ユーザ追加</button></p>
			</form>
		</div>
		
		<br>
		
	<H3>ユーザ一覧</H3>
	<% int intLines = 5;
	Optional<String> pageS = Optional.ofNullable(request.getParameter("page")); 
 	int intPage = Integer.parseInt(pageS.orElse("1"));
	ArrayList usersList = (ArrayList)pageContext.findAttribute("allUsers"); 
	int intMaxPage = (usersList.size() - 1) / intLines + 1; %>
	<c:if test="<%= intPage > 1 %>">
 		<a href="../admin/admin.jsp?page=<%= intPage - 1 %>">前</a>
 	</c:if>
 	<a><%= intPage %>/<%=intMaxPage %></a>
 	<c:if test="<%= intPage < intMaxPage %>">
 		<a href="../admin/admin.jsp?page=<%= intPage + 1 %>">次</a>
 	</c:if>
	<p>※変更したいユーザIDをクリックしてください※
	 <table class="flat-table">
	    <tr>
	      <th>ユーザID</th>
	      <th>ユーザ名</th>
	      <th>ユーザ区分</th>
	    </tr>
	    <c:forEach var="users" items="${allUsers}" begin="<%=(intPage - 1) * intLines %>" end="<%=intPage * intLines - 1 %>">
  		<tr>
  			<td><button class="cp_link" type="submit" name= "updateU_ID" onclick="location.href='../admin/Useradd.action?ID=${users.getU_ID()}'">${users.getU_ID()}</button></td>
		<td>${users.getU_name()}</td>
		<td>${users.getU_admin()}</td>
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