package maketeam;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import bean.Users;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class PoolMembersAction extends Action{
	@SuppressWarnings("unchecked")
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Users user = (Users)session.getAttribute("users");
		request.getParameterValues("selectedUserID");
		String[] selectedUserID = request.getParameterValues("selectedUserID");
		LinkedHashMap<Integer, Users> searchedUsersMap = (LinkedHashMap<Integer, Users>)session.getAttribute("usersMapSearchedByName");
		LinkedHashMap<Integer, Users> selectedUsersMap = (LinkedHashMap<Integer, Users>)session.getAttribute("selectedUsersMap");
		if(selectedUsersMap==null) {
			selectedUsersMap = new LinkedHashMap<>();
			selectedUsersMap.put(user.getU_ID(), user);
		}
		if(selectedUserID !=  null) {
			for(String id: selectedUserID) {
				Integer intId = Integer.parseInt(id);
				selectedUsersMap.put(intId, searchedUsersMap.get(intId));
			}
		}
		ArrayList<Users> poolMembersList = new ArrayList<>(selectedUsersMap.values());
		session.setAttribute("selectedUsersMap", selectedUsersMap);
		session.setAttribute("poolMembersList", poolMembersList);
		
		return "../maketeam/make-team.jsp";	
	}
}
