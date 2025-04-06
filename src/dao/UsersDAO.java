package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import bean.Users;

public class UsersDAO extends DAO{
	public Users search(String U_name, String U_pass) throws Exception {
		Users users = null ;
		
		Connection con = getConnection();
		try {
			PreparedStatement st ; //DAOにあるからなくてもOK
			String sqlSelect = "SELECT * FROM users WHERE U_name=? AND U_pass=?" ;
			st = con.prepareStatement(sqlSelect);
			st.setString(1, U_name);
			st.setString(2, U_pass);
			ResultSet rs = st.executeQuery();
			
			while (rs.next()) {
				users = new Users();
				users.setU_name(rs.getString("U_name"));
				users.setU_pass(rs.getString("U_pass"));
				users.setU_ID(rs.getInt("U_ID"));
				users.setU_admin(rs.getInt("U_admin"));
				users.setU_color(rs.getInt("U_color"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			} finally  {
	           try {
	        	   //isClosed接続を確認する接続:true 接続X:false
	               if(st!=null && !st.isClosed()) {
	                   st.close();
	               }
	               if(con!=null && !con.isClosed()) {
	                   con.close();
	               }
	           } catch (Exception e2) {
	        	   e2.printStackTrace();
	           }
			}
		
		return users ;
	}
	
	/**
	 * ユーザー全員を返す
	 * @return
	 * @throws Exception
	 */
	public ArrayList<Users> allUsers() throws Exception {
		ArrayList<Users> allUsers = new ArrayList<>();
		
		Connection con = getConnection();
		
		try {
			PreparedStatement st ; //DAOにあるからなくてもOK
			String sqlSelect = "SELECT * FROM users " ;
			st = con.prepareStatement(sqlSelect);
			ResultSet rs = st.executeQuery();
			
			while (rs.next()) {
				Users users = new Users();
				users.setU_name(rs.getString("U_name"));
				users.setU_pass(rs.getString("U_pass"));
				users.setU_ID(rs.getInt("U_ID"));
				users.setU_admin(rs.getInt("U_admin"));
				users.setU_color(rs.getInt("U_color"));
				allUsers.add(users);
			}
		} catch (Exception e) {
			e.printStackTrace();
			} finally  {
	           try {
	        	   //isClosed接続を確認する接続:true 接続X:false
	               if(st!=null && !st.isClosed()) {
	                   st.close();
	               }
	               if(con!=null && !con.isClosed()) {
	                   con.close();
	               }
	           } catch (Exception e2) {
	        	   e2.printStackTrace();
	           }
			}
		
		return allUsers;
	}

	/**
	 * 名前を部分一致で検索すし、該当者を返す
	 * @return
	 * @throws Exception
	 */
	public ArrayList<Users> searchByName(String name) throws Exception {
		ArrayList<Users> allUsers = new ArrayList<>();
		
		Connection con = getConnection();
		
		try {
			PreparedStatement st ; //DAOにあるからなくてもOK
			String sqlSelect = "SELECT * FROM users WHERE U_name LIKE ? AND U_admin !='2'";
			st = con.prepareStatement(sqlSelect);
			st.setString(1,  "%" + name + "%");
			ResultSet rs = st.executeQuery();
			
			while (rs.next()) {
				Users users = new Users();
				users.setU_name(rs.getString("U_name"));
				users.setU_pass(rs.getString("U_pass"));
				users.setU_ID(rs.getInt("U_ID"));
				users.setU_admin(rs.getInt("U_admin"));
				users.setU_color(rs.getInt("U_color"));
				allUsers.add(users);
			}
		} catch (Exception e) {
			e.printStackTrace();
			} finally  {
	           try {
	        	   //isClosed接続を確認する接続:true 接続X:false
	               if(st!=null && !st.isClosed()) {
	                   st.close();
	               }
	               if(con!=null && !con.isClosed()) {
	                   con.close();
	               }
	           } catch (Exception e2) {
	        	   e2.printStackTrace();
	           }
			}
		
		return allUsers;
	}
	
	/*引数チームIDからチームメンバーを返すメソッド*/
	 
	public ArrayList<Users> getMembers(int intT_ID)throws Exception  {
		ArrayList<Users> teamMembers = new ArrayList<>();
		Connection con = getConnection();
		
		try {
			PreparedStatement st ; //DAOにあるからなくてもOK
			String sqlSelect = "SELECT * FROM USERS WHERE U_ID IN(SELECT U_ID  FROM TEAM_MEMBERS WHERE T_ID=?) AND U_admin !='2'";
			st = con.prepareStatement(sqlSelect);
			st.setInt(1, intT_ID);
			ResultSet rs = st.executeQuery();
			
			while (rs.next()) {
				Users users = new Users();
				users.setU_name(rs.getString("U_name"));
				users.setU_pass(rs.getString("U_pass"));
				users.setU_ID(rs.getInt("U_ID"));
				users.setU_admin(rs.getInt("U_admin"));
				users.setU_color(rs.getInt("U_color"));
				teamMembers.add(users);
			}
		} catch (Exception e) {
			e.printStackTrace();
			} finally  {
	           try {
	        	   //isClosed接続を確認する接続:true 接続X:false
	               if(st!=null && !st.isClosed()) {
	                   st.close();
	               }
	               if(con!=null && !con.isClosed()) {
	                   con.close();
	               }
	           } catch (Exception e2) {
	        	   e2.printStackTrace();
	           }
			}
		return teamMembers;
	}
	/*チームメンバー以外のユーザ一覧を取得*/
	public ArrayList<Users> otherMembers(int intT_ID,String strU_name)throws Exception  {
		ArrayList<Users> teamMembers = new ArrayList<>();
		Connection con = getConnection();
		
		try {
			PreparedStatement st ; //DAOにあるからなくてもOK
			String sqlSelect = "SELECT * FROM USERS WHERE U_ID  NOT IN(SELECT U_ID  FROM TEAM_MEMBERS WHERE T_ID=?) AND U_admin !='2'";
			st = con.prepareStatement(sqlSelect);
			st.setInt(1, intT_ID);
			ResultSet rs = st.executeQuery();
			
			while (rs.next()) {
				Users users = new Users();
				users.setU_name(rs.getString("U_name"));
				users.setU_pass(rs.getString("U_pass"));
				users.setU_ID(rs.getInt("U_ID"));
				users.setU_admin(rs.getInt("U_admin"));
				users.setU_color(rs.getInt("U_color"));
				teamMembers.add(users);
			}
		} catch (Exception e) {
			e.printStackTrace();
			} finally  {
	           try {
	        	   //isClosed接続を確認する接続:true 接続X:false
	               if(st!=null && !st.isClosed()) {
	                   st.close();
	               }
	               if(con!=null && !con.isClosed()) {
	                   con.close();
	               }
	           } catch (Exception e2) {
	        	   e2.printStackTrace();
	           }
			}
		return teamMembers;
	}
	
	/**
	 * 会社員登録
	 * パスワードがかぶると登録できない
	 * @param user 登録する社員一人分のビーン
	 * @return 2でパスワードかぶり、1で正常に登録、0でエラー
	 */
	public int insertUsers(Users user) {
		//パスワードかぶりチェック
		String searchSql = "SELECT * FROM users WHERE U_pass = ?";
		//追加用SQL
		String sql = "INSERT INTO users (`U_pass`, `U_name`, `U_admin`) VALUES (?, ?, ?)";
		int line = 0;
		try (Connection con = getConnection()){
			st = con.prepareStatement(searchSql);
			st.setString(1, user.getU_pass());
			ResultSet rs = st.executeQuery();
			if(rs.next()) {
				System.out.println("パスワードが重複しています");
				return 2;
			}
			st= con.prepareStatement(sql);
			st.setString(1, user.getU_pass());
			st.setString(2, user.getU_name());
			st.setInt(3, user.getU_admin());
			line = st.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if (st != null && !st.isClosed()) {
					st.close();
				}
			} catch (Exception e2) {
				;
			}
		}
		return line;
	}
	
	/**
	 * 会社員情報更新
	 * パスワードがかぶると登録できない
	 * @param user 更新する社員一人分のビーン
	 * @return 2でパスワードかぶり、1で正常に登録、0でエラー
	 */
	public int updateUsers(Users user) {
		String searchSql = "SELECT * FROM users WHERE U_pass=? AND U_ID!=?";
		String sql = "UPDATE users "
				+ "SET `U_pass` = ?, `U_name` = ?, `U_admin` = ?, `U_color` = ? "
				+ "WHERE (`U_ID` = ?)";
		int line = 0;
		try (Connection con = getConnection()){
			//
			st = con.prepareStatement(searchSql);
			st.setString(1, user.getU_pass());
			st.setInt(2, user.getU_ID());
			ResultSet rs = st.executeQuery();
			if(rs.next()) {
				System.out.println("パスワードが重複しています");
				return 2;
			}
			
			st= con.prepareStatement(sql);
			st.setString(1, user.getU_pass());
			st.setString(2, user.getU_name());
			st.setInt(3, user.getU_admin());
			st.setInt(4, user.getU_color());
			st.setInt(5,  user.getU_ID());
			line = st.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if (st != null && !st.isClosed()) {
					st.close();
				}
			} catch (Exception e2) {
				;
			}
		}
		return line;
	}
	
	/**
	 * ユーザ毎に設定したカラーパターンを変換する
	 * @param intU_ID 
	 * @param intU_color 
	 * @return DBを更新した行数（正常で1）
	 */
	public int colorChange(int intU_ID, int intU_color) {
		String sql = " UPDATE users SET U_color = ? WHERE U_ID = ?";
		int line = 0;
		try (Connection con = getConnection()){
			//
			st = con.prepareStatement(sql);
			st.setInt(1, intU_color);
			st.setInt(2, intU_ID);
			line = st.executeUpdate();
			if(line != 1) {
				System.out.println("カラーパターン変更時にエラーが発生lenie=" + line);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if (st != null && !st.isClosed()) {
					st.close();
				}
			} catch (Exception e2) {
				;
			}
		}
		return line;
	}
}
