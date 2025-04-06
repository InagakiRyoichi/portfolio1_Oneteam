<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.*" import="java.text.*" %>
<% String userName = (String)session.getAttribute("U_name");%>
<% Integer userID = (Integer)session.getAttribute("U_ID");%>
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
		<h2>タスク作成</h2>
		
				<p>依頼者： ${users.getU_name()} 
				<% Date date = new Date();
					SimpleDateFormat sdf = new SimpleDateFormat("依頼日：yyyy年 MM月 dd日");
					 String formatDate = sdf.format(date);
					out.print(formatDate); %>
				</p>
			<form action= "Addtask.action?id=${task.TSK_ID}" merhod="post">
			<div>
				タスク名
				<input class="nice-textboxshort" type= "text" name="TSK_taskname"size="15" placeholder="タスク名" maxlength ="15" required>
				
				納期
				<input class="nice-textboxshort" type="date" name="TSK_term"  required>
				<br>
		<div class=float>
				担当者選択
			<div class="scroll">
				<c:forEach var="user" items="${teamMembers}">
					<div id="textleft"><input type="checkbox" name="chargeID" class="check" 
					value="${user.getU_ID()}" >${user.getU_ID()}：${user.getU_name()}</div>
				</c:forEach>
			</div>
		</div>
<script>
    (() => {
        // チェックボックスのinputタグを取得
        const checkBoxElements = Array.from(document.getElementsByClassName("check"));

        const errorMessage = "1つ以上の選択肢を選択してください。";
        checkBoxElements
            .forEach(m => {
                // エラーメッセージを、カスタムなものに変更
                m.setCustomValidity(errorMessage);

                // 各チェックボックスのチェックのオン・オフ時に、以下の処理が実行されるようにする
                m.addEventListener("change", () => {
                    // 1つ以上チェックがされているかどうかを判定
                    const isCheckedAtLeastOne = document.querySelector(".check:checked") !== null;

                    // 1つもチェックがされていなかったら、すべてのチェックボックスを required にする
                    // 加えて、エラーメッセージも変更する
                    checkBoxElements.forEach(n => {
                        n.required = !isCheckedAtLeastOne
                        n.setCustomValidity(isCheckedAtLeastOne ? "" : errorMessage);
                    });
                });
            });
    })();
</script>
		<br>
				タスク内容
				<br>
				<textarea class="nice-textarea" name="taskarea"  maxlength ="255" id="textArea"></textarea>
				<div id="charCount"></div>
				<script type="text/javascript">
					document.addEventListener('DOMContentLoaded', function() {
					const textArea = document.getElementById('textArea');
					const charCount = document.getElementById('charCount');

					textArea.addEventListener('input', function() {
					 let count = textArea.value.length;
					 let strCount = String(count);
					 charCount.textContent = strCount + `／255文字`;
    				});
				});
				</script>
				<br>
				<p class="textcenter"><button class="btn_23" type="submit">タスク作成</button></p>
				<br>
		</form>
	</main>
		<%--フッター全ページ共通 /body直前--%>
			<footer class="footer">
				<p>Copyright © One team All Rights Reserved.</p>
			</footer>
		
	</body>
</html>