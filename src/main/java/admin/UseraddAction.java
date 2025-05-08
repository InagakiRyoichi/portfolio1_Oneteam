package admin;

import java.util.ArrayList;

import bean.Users;
import dao.UsersDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class UseraddAction extends Action{
	@SuppressWarnings("unchecked")
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		try{//requestparamのid＝0の場合はユーザ新規登録　
			int id = Integer.parseInt(request.getParameter("ID"));
			System.out.println(id);
			Users user = new Users();
			UsersDAO dao = new UsersDAO();
			ArrayList<Users> allUsers ; //if文でもつかう
			if(id ==0) {
				String name = (String)request.getParameter("name");
				String password =(String)request.getParameter("password");
				int classification =Integer.parseInt(request.getParameter("classification"));
				int insert = 0;
				user.setU_name(name);
				user.setU_pass(password);
				user.setU_admin(classification);
				insert =dao.insertUsers(user) ;
				if(insert == 1) {
					//登録が成功なら、エラーメッセージを消す
					session.removeAttribute("UseraddError");
					//ユーザ情報のセッションの上書き
					allUsers = dao.allUsers();
					session.setAttribute("allUsers",allUsers);
					return "../admin/admin.jsp";
				}else {
					//新規登録失敗
				session.setAttribute("UseraddError",insert);
				return "../admin/admin.jsp";
				}
			}
				//ユーザ更新の場合は、Userbeanに対象ユーザを入れてﾕｰｻﾞ変更画面へ遷移
			System.out.println(request.getParameter("ID"));
			//int updateU_ID = Integer.parseInt(request.getParameter("updateU_ID"));
			allUsers = dao.allUsers();  //既存のユーザ一覧を取得
			for (Users users : allUsers) {//updateU＿IDを探してみつけたらbeanにセット
				if (id ==users.getU_ID()) {
					user =dao.search(users.getU_name(),users.getU_pass());
					request.setAttribute("updateuser",user);
					break; // ループを終了
				}
			}	
		}catch (NumberFormatException e){
				System.out.println(e);
		}
		return "../changeuserinfo/change-userinfo.jsp";
	}
}

