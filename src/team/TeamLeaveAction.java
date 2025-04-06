package team;

import java.util.ArrayList;

import bean.Team;
import bean.Users;
import dao.TeamDAO;
import dao.TeammembersDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class TeamLeaveAction extends Action{
	@SuppressWarnings("unchecked")
	public String execute(HttpServletRequest request,HttpServletResponse response) throws Exception {
		//チームから退出する
		HttpSession session = request.getSession();
		int T_ID = (Integer)session.getAttribute("T_ID");
		Users user = (Users)session.getAttribute("users");
		int U_ID = user.getU_ID();
		TeammembersDAO teamMembersDAO = new TeammembersDAO();
		teamMembersDAO.leaveTeam(T_ID, U_ID);
		
		//本人所属のチームリストを更新する
		TeamDAO teamDAO = new TeamDAO();
		ArrayList<Team> teamList = teamDAO.getTeam(user.getU_ID());
		session.setAttribute("teamList", teamList);
		session.removeAttribute("teamMembers");
		session.removeAttribute("otherMembers");
		session.removeAttribute("T_ID");
		session.removeAttribute("taskListOnTeam");
		session.removeAttribute("selectedTeam");
		return "../top/top.jsp" ;
		
	}
}
