package top;

import bean.Users;
import dao.UsersDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class ColorChangeAction extends Action{
		@SuppressWarnings("unchecked")
		public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
			//セッションからユーザ情報取得
			HttpSession session = request.getSession();
			Users users = (Users)session.getAttribute("users");
			String U_color = request.getParameter("U_color");
			System.out.println("color=" + U_color);
			int intU_color = 0;
			try{
				intU_color = Integer.parseInt(U_color);
			}catch(Exception e) {
				System.out.println("カラーパターンが整数ではありません");
			}
			//DBを更新
			UsersDAO usersDAO = new UsersDAO();
			int line = usersDAO.colorChange(users.getU_ID(), intU_color);
			if(line != 1) {
				System.out.println("DB書き換え失敗line=" + line);
			}
			//セッションを更新
			users.setU_color(intU_color);
			request.setAttribute("users",users);
			return  "../top/colorChange.jsp";
		}
}

