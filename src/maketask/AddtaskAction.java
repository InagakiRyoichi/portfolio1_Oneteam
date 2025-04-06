package maketask;

import java.util.ArrayList;

import bean.Task;
import bean.Users;
import dao.TaskDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class AddtaskAction extends Action{
	@SuppressWarnings("unchecked")
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		String TSK_taskname = request.getParameter("TSK_taskname");
		String TSK_term = request.getParameter("TSK_term");
		int T_ID = (Integer)session.getAttribute("T_ID");
		Users users = (Users)session.getAttribute("users");
		int usersID = users.getU_ID();
		System.out.println(request.getParameterValues("chargeID"));
		String [] UID = request.getParameterValues("chargeID");
		ArrayList<Integer> chargeID = new ArrayList<>();
		for(String ID : UID) {
			chargeID.add(Integer.parseInt(ID));
		}
		
		System.out.println(request.getParameterValues("U_ID"));

		String taskarea = request.getParameter("taskarea");
		Task task = new Task();
		task.setTSK_taskname(TSK_taskname);
		task.setTSK_term(java.sql.Date.valueOf(TSK_term));
		task.setU_ID(chargeID);
		task.setTSK_contents(taskarea);
		task.setU_request_ID(usersID);
		task.setT_ID(T_ID);
		TaskDAO taskdao =new TaskDAO();
		int TSK_ID = taskdao.addTask(task);
		ArrayList<Task> requestedTaskList = taskdao.searchRequestedTasks(usersID,0);
		session.setAttribute("requestedTaskList",requestedTaskList);
		ArrayList<Task> requestTaskList = taskdao.searchRequestTasks(usersID,0);
		session.setAttribute("requestTaskList",requestTaskList);
		
		return "../checktask/checktask.action?TSK_ID=" + TSK_ID;
	}
}
