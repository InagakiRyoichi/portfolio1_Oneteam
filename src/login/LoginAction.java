package login;

import java.util.ArrayList;

import bean.Task;
import bean.Team;
import bean.Users;
import dao.TaskDAO;
import dao.TeamDAO;
import dao.UsersDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

/**
 * @version 2024/12/11
 */
public class LoginAction extends Action{
	@SuppressWarnings("unchecked")
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		
		String U_name = request.getParameter("U_name");
		String U_pass = request.getParameter("U_pass");
		UsersDAO dao = new UsersDAO();
		//beanにsearchの結果を保存
		Users users = dao.search(U_name, U_pass);
		
		TeamDAO teamdao = new TeamDAO();
		TaskDAO taskdao = new TaskDAO();
		
		
		if (users != null) { 
			//退職者ログインエラー
			if (users.getU_admin() == 2) {
				session.setAttribute("loginError", 2);
				return "login.jsp";
			}
			
			//ログイン成功
			//本人所属のチームリストを呼び出すときはteamListで呼び出す
			ArrayList<Team> teamList = teamdao.getTeam(users.getU_ID());
			//依頼しているタスク一覧をユーザIDを引数にして呼び出して格納する
			ArrayList<Task> requestTaskList = taskdao.searchRequestTasks(users.getU_ID(), 0);
			//担当タスク一覧をユーザIDを引数にして呼び出して格納する
			ArrayList<Task> requestedTaskList = taskdao.searchRequestedTasks(users.getU_ID(), 0);
//			session.setAttribute("U_name", U_name);
//			session.setAttribute("U_pass", U_pass);
//			session.setAttribute("U_admin", users.getU_admin());
//			session.setAttribute("U_ID", users.getU_ID());
//			session.setAttribute("U_color", users.getU_color());
			session.setAttribute("users", users);
			session.setAttribute("teamList", teamList);
			session.setAttribute("requestTaskList", requestTaskList);
			session.setAttribute("requestedTaskList", requestedTaskList);
			session.removeAttribute("selectedUsersMap");
			
			//ログインエラーの後にログイン成功の場合、エラー情報のセッションを削除する
			session.removeAttribute("loginError");
			return "../top/top.jsp" ;
		} else { //ログイン失敗
			
			session.setAttribute("loginError",1);
			return "login.jsp" ; 
		}
	}
}
