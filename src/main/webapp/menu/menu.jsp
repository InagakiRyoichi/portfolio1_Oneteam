<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="true"%>
<!DOCTYPE html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
	<head>
		<meta charset="UTF-8">
		<title>ONE teams</title>
		<link rel="stylesheet" href="/ONEteams/static/css/plain${users.getU_color()}.css">
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">
		
	</head>
	<body>
		<div class="box-wrp">
			<%--ロゴマーク（フォント）　上詰めで中央表示 --%>
			<div class="box3 logo">ONE teams</div>
			<%--左に詰めて表示するTOPボタン --%>
			<div class="box1"><a href ="../top/top.jsp" class="btn btn-malformation">TOP</a></div>
			<%--右に詰めて表示するlogoutボタン --%>
			
			<div class="box2">ユーザ　${users.getU_name() }さん　　<a href ="../menu/logout.action?U_ID=${users.getU_ID()}" 
			class="btn btn-malformation">LOGOUT</a></div>
		
		</div>
	</body>
</html>