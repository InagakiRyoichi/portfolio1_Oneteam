package maketeam;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import bean.Users;
import dao.UsersDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class DisplayMakeTeamAction extends Action{
	@SuppressWarnings("unchecked")
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Users user = (Users)session.getAttribute("users");
		LinkedHashMap<Integer, Users> selectedUsersMap = (LinkedHashMap<Integer, Users>)session.getAttribute("selectedUsersMap");
		if(selectedUsersMap==null) {
			selectedUsersMap = new LinkedHashMap<>();
			selectedUsersMap.put(user.getU_ID(), user);
		}
		String keyword = request.getParameter("keyword");
		if(keyword == null) {
			keyword = "";
		}
		UsersDAO usersDAO = new UsersDAO();
		ArrayList<Users> searchedUsers = usersDAO.searchByName(keyword);
		LinkedHashMap<Integer, Users> searchedUsersMap = new LinkedHashMap<>();
		for(Users u: searchedUsers) {
			searchedUsersMap.put(u.getU_ID(), u);
		}
		session.setAttribute("usersSearchedByName", searchedUsers);
		session.setAttribute("usersMapSearchedByName", searchedUsersMap);
		ArrayList<Users> poolMembersList = new ArrayList<>(selectedUsersMap.values());
		session.setAttribute("selectedUsersMap", selectedUsersMap);
		session.setAttribute("poolMembersList", poolMembersList);
//		System.out.println(usersDAO.searchByName(keyword).size());
//		System.out.println(keyword);
		return "../maketeam/make-team.jsp";		
	}
}
