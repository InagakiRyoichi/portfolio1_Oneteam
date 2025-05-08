package updatetask;

import java.util.ArrayList;

import bean.Task;
import bean.Users;
import dao.TaskDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class UpdatetaskAction extends Action{
	@SuppressWarnings("unchecked")
	public String execute(HttpServletRequest request,HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		int TSK_ID = 0;
		try {
			TSK_ID = Integer.parseInt(request.getParameter("TSK_ID")); 
			System.out.println(request.getParameter("TSK_IDは" + TSK_ID));
			String TSK_taskname= (String)request.getParameter("TSK_taskname");
			String TSK_term = request.getParameter("TSK_term");
			String [] UID = request.getParameterValues("chargeID");
			ArrayList<Integer> chargeID = new ArrayList<>();
			int T_ID = Integer.parseInt(request.getParameter("T_ID"));
			System.out.println(request.getParameter("T＿IDは"+ T_ID));
			for(String ID : UID) {
				chargeID.add(Integer.parseInt(ID));
			}
			String taskarea = request.getParameter("taskarea");
			TaskDAO taskDao =  new TaskDAO();
			//beanに既存のタスクをとってくる
			Task task = taskDao.getTask(TSK_ID) ;
			
			//beanのタスク内容書換（タスク名、納期、担当者、内容）でDB上書き
			task.setTSK_taskname(TSK_taskname);
			task.setTSK_term(java.sql.Date.valueOf(TSK_term));
			task.setU_ID(chargeID);
			task.setTSK_contents(taskarea);
			taskDao.renewalTask(task);
			
			Users user = new Users();
			user =(Users)session.getAttribute("users");
			//依頼しているタスク一覧をユーザIDを引数にして呼び出して上書きする
			ArrayList<Task> requestTaskList = taskDao.searchRequestTasks(user.getU_ID(), 0);
			//担当タスク一覧をユーザIDを引数にして呼び出して上書きする
			ArrayList<Task> requestedTaskList = taskDao.searchRequestedTasks(user.getU_ID(), 0);
			session.setAttribute("requestTaskList",requestTaskList);
			session.setAttribute("requestedTaskList",requestedTaskList);
			//チーム画面のタスクリストの更新はまだ
		}catch (NumberFormatException e){
			System.out.println(e);
		}
		return "../checktask/checktask.action?T_ID=" + TSK_ID;
	}
}
