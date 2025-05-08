package top;

import java.util.ArrayList;

import bean.Task;
import bean.Users;
import dao.TaskDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class PickUpIncompleteAction extends Action{
		@SuppressWarnings("unchecked")
		public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
			//セッションからユーザ情報取得
			HttpSession session = request.getSession();
			Users users = (Users)session.getAttribute("users");
			//
			String strFlag = request.getParameter("onlyIncomplete");
			int intStatus = 0;
			try {
				intStatus = Integer.parseInt(strFlag);
			}catch (Exception e) {
				;
			}
			session.setAttribute("onlyIncomplete", intStatus);
			
			//必要な更新
			TaskDAO taskdao = new TaskDAO();
			//依頼しているタスク一覧をユーザIDを引数にして呼び出して格納する
			ArrayList<Task> requestTaskList = taskdao.searchRequestTasks(users.getU_ID(), intStatus);
			//担当タスク一覧をユーザIDを引数にして呼び出して格納する
			ArrayList<Task> requestedTaskList = taskdao.searchRequestedTasks(users.getU_ID(), intStatus);
			session.setAttribute("requestTaskList", requestTaskList);
			session.setAttribute("requestedTaskList", requestedTaskList);
			return  "../top/top.jsp";
		}
}

