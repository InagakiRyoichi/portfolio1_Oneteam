package admin;

import java.util.ArrayList;

import bean.Users;
import dao.UsersDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class UserupdateAction extends Action{
	@SuppressWarnings("unchecked")
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		UsersDAO dao = new UsersDAO();
		Users user = new Users();
		try {
			//変更前の名前とパスワードを取得
			String U_name = (String)request.getParameter("U_name");
			String U_pass = (String)request.getParameter("U_pass");
			//変更後の名前と権限を取得
			String newU_name = (String)request.getParameter("newU_name");
			int newclassification = Integer.parseInt(request.getParameter("newclassification"));
			//既存の情報取得
			user = dao.search(U_name, U_pass) ;
			//beanの名前と権限上書き
			user.setU_name(newU_name);
			user.setU_admin(newclassification);
			//DB上書き　updateUsers()戻り値が1なら登録成功。2は重複（？）0はエラー
			int update = dao.updateUsers(user);
			if(update==0 || update==2) { 
				//変更失敗
				session.setAttribute("UserupdateError",update);
				return "../change-userinfo/changeuserinfo.jsp";
			}
			//登録が成功なら、エラーメッセージを消す
			session.removeAttribute("UserupdateError");
			//セッション情報のallUsersの書換
			ArrayList<Users> allUsers =dao.allUsers();
			session.setAttribute("allUsers", allUsers);
						
			
		}catch (NumberFormatException e){
			System.out.println(e);
		}
		return "../admin/admin.jsp";
	}
}
