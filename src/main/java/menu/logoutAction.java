package menu;

import java.util.Collections;
import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class logoutAction extends Action{
	@SuppressWarnings("unchecked")
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//すべてのセッション情報の削除
		HttpSession session = request.getSession();
		List <String> enuNames = Collections.list(session.getAttributeNames());
		for(String name: enuNames) {
			session.removeAttribute(name);
		}
		return "../login/login.jsp";
	}

}
