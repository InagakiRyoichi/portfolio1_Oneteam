package top;

import bean.Users;
import dao.UsersDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class passwordAction extends Action{
		@SuppressWarnings("unchecked")
		public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
			//セッションからユーザ情報取得
			HttpSession session = request.getSession();
			Users users = (Users)session.getAttribute("users");
			String U_pass = users.getU_pass();
			UsersDAO dao = new UsersDAO();
			//beanにsearchの結果を保存
			//Users user = dao.search(U_name, U_pass);
			//入力したU_passとnewU_pass2をリクエストﾊﾟﾗﾒｰﾀから取得
			String inputU_pass = (String)request.getParameter("U_pass");
			String newU_pass1 = (String)request.getParameter("newU_pass1");
			String newU_pass2 = (String)request.getParameter("newU_pass2");
			int update=0;
			if(!newU_pass1.equals(newU_pass2)) {
				request.setAttribute("passwordError",update);
				return "../top/password.jsp";
			}
			users.setU_pass(newU_pass2);
			
			 //インプットU_passとﾕｰｻﾞpassが同じなら
			if(inputU_pass.equals(U_pass)){
				//ユーザ情報書き換え
				update =dao.updateUsers(users);
				if(update == 1) {
					//変更が成功なら、エラーメッセージを消す
					request.removeAttribute("passwordError");
					//ユーザ情報のセッションの上書き
					session.setAttribute("users", users);
					return "../top/top.jsp";
				}
			}
			//パスワード変更失敗
			users.setU_pass(U_pass);
			request.setAttribute("passwordError",update);
			return  "../top/password.jsp";
		}
}

