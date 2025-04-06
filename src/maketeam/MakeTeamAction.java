package maketeam;

import java.util.ArrayList;

import bean.Team;
import bean.Users;
import dao.TeamDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class MakeTeamAction extends Action{
	@SuppressWarnings("unchecked")
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Users user = (Users)session.getAttribute("users");
		String[] registerUserID = request.getParameterValues("registerUserID");
		String teamName = request.getParameter("teamname");
		if(registerUserID==null) System.out.println("String[]registerUserIDがnull");//デバッグ
		session.removeAttribute("selectedUsersMap");
		session.removeAttribute("poolMembersList");
		session.removeAttribute("usersSearchedByName");
		session.removeAttribute("usersMapSearchedByName");
		//チーム作成
		TeamDAO teamdao = new TeamDAO();
		ArrayList<Integer> U_IDList = new ArrayList<>();
		for(String U_ID: registerUserID) {
			U_IDList.add(Integer.parseInt(U_ID));
		}
		int T_ID = teamdao.addTeam(teamName, U_IDList);
		//本人所属のチームリストを更新する
		ArrayList<Team> teamList = teamdao.getTeam(user.getU_ID());
		session.setAttribute("teamList", teamList);
		
		return "../team/team.action?T_ID=" + T_ID;
//		return "../maketeam/make-team.jsp";	
	}
}
