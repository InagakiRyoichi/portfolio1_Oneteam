package checktask;

import java.util.ArrayList;

import bean.Task;
import bean.Team;
import bean.Users;
import dao.TaskDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class ProgressTaskAction extends Action{
	@SuppressWarnings("unchecked")
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		int T_ID =0;
		//1なら「報告」、2なら「完了」、3なら「報告差戻」、4なら「完了差戻」
		try {
			Task task = (Task)session.getAttribute("task");
			int status =Integer.parseInt(request.getParameter("Status"));
			System.out.println("選択ステータス:"+ status);
			TaskDAO dao = new TaskDAO();
			int TSK_ID =task.getTSK_ID();
			System.out.println("選択タスクID:" + TSK_ID);
			task = dao.getTask(TSK_ID) ;
			T_ID =task.getT_ID();
			dao. progressTask(TSK_ID, status);
			//タスク情報書換用に再度ゲットタスク
			task = dao.getTask(TSK_ID) ;
			Users user = new Users();
			user =(Users)session.getAttribute("users");
			//依頼しているタスク一覧をユーザIDを引数にして呼び出して上書きする
			ArrayList<Task> requestTaskList = dao.searchRequestTasks(user.getU_ID(), 0);
			//担当タスク一覧をユーザIDを引数にして呼び出して上書きする
			ArrayList<Task> requestedTaskList =dao.searchRequestedTasks(user.getU_ID(), 0);
			session.setAttribute("requestTaskList",requestTaskList);
			session.setAttribute("requestedTaskList",requestedTaskList);
			//チームに紐づいたタスクリストを更新
			ArrayList<Task> taskListOnTeam = dao.searchTasksByTeam(T_ID, 0);
			session.setAttribute("taskListOnTeam", taskListOnTeam);
			for(Team team: (ArrayList<Team>)session.getAttribute("teamList")) {
				if(team.getT_ID() == T_ID) {
					session.setAttribute("selectedTeam", team);
				}
			}
			session.setAttribute("task",task);
			//報告・完了　差戻の際のメッセージ
			if(status>=3) {
				request.setAttribute("returnMassage",1);
				return "../team/team.action?T_ID=" +T_ID;
			}
			}catch (NumberFormatException e){
				System.out.println(e);
			}
		
			return "../checktask/checktask.jsp";
	}
}
