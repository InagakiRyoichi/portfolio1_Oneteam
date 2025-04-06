package team;

import java.util.ArrayList;

import bean.Users;
import dao.TeammembersDAO;
import dao.UsersDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class TeamInviteAction extends Action{
	@SuppressWarnings("unchecked")
	public String execute(HttpServletRequest request,HttpServletResponse response) throws Exception {
		//ユーザをチームに追加する
		HttpSession session = request.getSession();
		String[] newComerU_IDArray = request.getParameterValues("newCommerU_ID");
		int T_ID = (Integer)session.getAttribute("T_ID");
		TeammembersDAO teamMembersDAO = new TeammembersDAO();
		if(newComerU_IDArray != null) {
			for(String s: newComerU_IDArray) {
				teamMembersDAO.addTeam(T_ID, Integer.parseInt(s));
			}
		}
		//チーム内外メンバの更新
		UsersDAO users =new UsersDAO();
		ArrayList<Users> teamMembers =users.getMembers(T_ID);
		ArrayList<Users> otherMembers =users.otherMembers(T_ID,"");
		session.setAttribute("teamMembers",teamMembers);
		session.setAttribute("otherMembers",otherMembers);
		return "../team/team.action?T_ID=" + T_ID;
	}
}
