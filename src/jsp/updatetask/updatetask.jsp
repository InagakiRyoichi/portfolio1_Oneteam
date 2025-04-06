<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.*" import="java.text.*" %>
<% String userName = (String)session.getAttribute("U_name");%>
<% Integer userID = (Integer)session.getAttribute("U_ID");%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>ONE teams</title>
	</head>
	<body>
		<%--メニューバーをインクルードして表示させる CSSも同時に指定完了--%>
	<%@include file = "../menu/menu.jsp" %>
	<main>
	<br>
	<br>
	<br>
	<h3>タスク編集</h3>
		<form action= "Updatetask.action" merhod="post">
			<p>タスクID：${task.getTSK_ID()}　　　依頼者： ${users.getU_name()} 　　
			<% Date date = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("更新日：yyyy年 MM月 dd日");
				 String formatDate = sdf.format(date);
				out.print(formatDate); %>　　　
		<br>
		<input type="hidden" name="TSK_ID" value="${task.getTSK_ID()}">
		<input type="hidden" name="T_ID" value="${task.getT_ID()}">
			
			タスク名<input class="nice-textboxshort" type= "text" name="TSK_taskname" 
				size="16"  maxlength ="16" value="${task.getTSK_taskname()}" required>
			納期<input class="nice-textboxshort" type="date" name="TSK_term" value="${task.getTSK_term()}" required>
			
		<div class=float>
				担当者選択
			<div class="scroll">
				<c:forEach var="user" items="${teamMembers}">
					<c:choose>
						<c:when test="${task.getU_ID().contains(user.getU_ID())}">
							<div id="textleft"><input type="checkbox" name="chargeID" class="check"
							value="${user.getU_ID()}" checked>${user.getU_ID()}：${user.getU_name()}</div>
						</c:when>
						<c:otherwise>
							<div id="textleft"><input type="checkbox" name="chargeID" class="check"
								value="${user.getU_ID()}">${user.getU_ID()}：${user.getU_name()}</div>
						</c:otherwise>
					</c:choose>
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
        //これは編集画面だからロード時には担当者が一名以上選択されている条件は満たしている
        //よって最初にrequiredを外す処理をする
        window.addEventListener('DOMContentLoaded', () => {
            // 1つ以上チェックがされているかどうかを判定
            const isCheckedAtLeastOne = document.querySelector(".check:checked") !== null;

            // 1つもチェックがされていなかったら、すべてのチェックボックスを required にする
            // 加えて、エラーメッセージも変更する
            checkBoxElements.forEach(n => {
                n.required = !isCheckedAtLeastOne
                n.setCustomValidity(isCheckedAtLeastOne ? "" : errorMessage);
            });
        });
    })();
</script>
<%--テキストエリアに文字数表示するため --%>

<%--テキストエリアに文字数表示するためおわり --%>
		<br>
				タスク内容
				<br>
				<textarea class="nice-textarea" name="taskarea"  maxlength ="255" id="textArea">${task.getTSK_contents() }</textarea>
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
				<p class="textcenter"><button class="btn_23" type="submit" id="submi">タスク編集</button></p>
				<br>
			
				<%--ここをチーム作成ページのようにスクロールセット出来るようにしたい --%>
					<%-- <c:forEach var="user" items="${teamMembers}">
						<div class="scroll"><input type="checkbox" name="chargeID" value="${user.getU_ID() }">${user.getU_ID() }:${user.getU_name() }
					</c:forEach>
					</div>--%>
			
			
			
		</form>
		<br>
		
		<a class="btn-malformation" style=" font-family: 'Ruska Display', 'sans-serif ';" href = "../checktask/checktask.jsp">CHECK TASK PAGE</a>
	</main>
	<%--フッター全ページ共通 /body直前--%>
			<footer class="footer">
				<p>Copyright © One team All Rights Reserved.</p>
			</footer>
			
	</body>
</html>