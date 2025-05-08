package checktask;

import java.util.ArrayList;

import bean.Task;
import bean.Users;
import dao.TaskDAO;
import dao.UsersDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class checktaskAction extends Action{
	@SuppressWarnings("unchecked")
	public String execute(HttpServletRequest request,HttpServletResponse response) throws Exception {
		try {
			int TSK_ID = Integer.parseInt(request.getParameter("TSK_ID")); 
			System.out.println(request.getParameter("TSK_ID"));
			HttpSession session = request.getSession();
			TaskDAO taskDao =  new TaskDAO();
			Task task = taskDao.getTask(TSK_ID) ;
			int T_ID =task.getT_ID();
			session.setAttribute("task", task);
			//TOPから遷移した場合のチームメンバーの取得
			System.out.println(T_ID);
			UsersDAO users =new UsersDAO();
			ArrayList<Users> teamMembers =users.getMembers(T_ID);
			session.setAttribute("teamMembers",teamMembers);
		}catch (NumberFormatException e){
			System.out.println(e);
		}
		return "../checktask/checktask.jsp";
	}
}
