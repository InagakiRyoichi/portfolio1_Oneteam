package team;

import java.util.ArrayList;

import bean.Task;
import bean.Team;
import bean.Users;
import dao.TaskDAO;
import dao.UsersDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class teamAction extends Action{
	@SuppressWarnings("unchecked")
	public String execute(HttpServletRequest request,HttpServletResponse response) throws Exception {
		int T_ID = Integer.parseInt(request.getParameter("T_ID")); 
		HttpSession session = request.getSession();
		UsersDAO users =new UsersDAO();
		TaskDAO taskDAO = new TaskDAO();
		ArrayList<Users> teamMembers =users.getMembers(T_ID);
		ArrayList<Users> otherMembers =users.otherMembers(T_ID,"");
		session.setAttribute("teamMembers",teamMembers);
		session.setAttribute("otherMembers",otherMembers);
		session.setAttribute("T_ID", (Integer)T_ID);
		
		ArrayList<Task> taskListOnTeam = taskDAO.searchTasksByTeam(T_ID, 0);
		session.setAttribute("taskListOnTeam", taskListOnTeam);
		for(Team team: (ArrayList<Team>)session.getAttribute("teamList")) {
			if(team.getT_ID() == T_ID) {
				session.setAttribute("selectedTeam", team);
			}
		}
		
		
		return "../team/team.jsp";
		
	}
}
