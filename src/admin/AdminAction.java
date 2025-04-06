package admin;

import java.util.ArrayList;

import bean.Users;
import dao.UsersDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

/**
 * 管理者ページへ画面遷移するアクション
 * @author 宇佐美
 * @version 2024/12/11
 */

public class AdminAction extends Action{
	@SuppressWarnings("unchecked")
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();	
		
		UsersDAO dao = new UsersDAO();
		ArrayList<Users> allUsers = dao.allUsers();
		System.out.println(allUsers.size());
		session.setAttribute("allUsers", allUsers);
		return "../admin/admin.jsp";
		
	}
}
