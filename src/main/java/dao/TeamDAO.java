package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import bean.Team;

public class TeamDAO extends DAO{
	/**
	 * ユーザーIDから所属しているチームリストを作る
	 * @param intU_ID
	 * @return
	 */
	public ArrayList<Team> getTeam(int intU_ID) {
		String sql = "SELECT T_ID, T_name, T_create_date, T_update_date FROM teams "
				+ "WHERE T_ID IN (SELECT T_ID FROM team_members WHERE U_ID = ?) "
				+ "ORDER BY T_update_date DESC";

		
		ArrayList<Team> teamList = new ArrayList<>();
		
		try (Connection con = getConnection()){
			st= con.prepareStatement(sql);
			st.setInt(1, intU_ID);
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				Team team = new Team();
				team.setT_ID(rs.getInt("T_ID"));
				team.setT_name(rs.getString("T_name"));
				team.setT_create_date(rs.getDate("T_create_date"));
				team.setT_update_date(rs.getTimestamp("T_update_date"));
				teamList.add(team);
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
		return teamList;
	}
	
	/**
	 * チームを追加する
	 * @param T_name チーム名
	 * @param U_IDList チームメンバのユーザー7IDのリスト
	 * @return 追加したチームのID、追加に失敗した場合0を返す
	 */
	public int addTeam(String T_name, ArrayList<Integer> U_IDList) {
		//tasksテーブルからT_IDの最大値を取得する
		String selectTID = "SELECT MAX(T_ID) FROM teams";
		//teamsテーブルにチームを追加する
		String insertTeams = "INSERT INTO teams (T_name) values (?)";
		//team_membersテーブルにメンバ構成を追加する
		String insertTeamM = "INSERT INTO team_members (U_ID, T_ID) values";
		for(int i = 0; i < U_IDList.size(); i++) {
			insertTeamM += "(?, ?),";
		}
		//末尾の,を削除
		insertTeamM = insertTeamM.substring(0, insertTeamM.length() - 1);
		int T_ID = 0;
		try (Connection con = getConnection()){
			//テーブルを同時に書き換えるために自動コミットをオフにする
			con.setAutoCommit(false);
			//最初に外部キー参照元のteamsから追加
			st = con.prepareStatement(insertTeams);
			st.setString(1, T_name);
			st.executeUpdate();
			//T_IDがオートインクリメントのため、追加したタスクのT_IDは既存のものの最大値
			st = con.prepareStatement(selectTID);
			ResultSet rs = st.executeQuery();
			rs.next();
			T_ID = rs.getInt(1);
			//次にteam_membersテーブルに追加する
			st = con.prepareStatement(insertTeamM);
			for(int i = 0; i < U_IDList.size(); i++) {
				st.setInt(2 * i + 1, U_IDList.get(i));
				st.setInt(2 * i + 2, T_ID);
			}
			st.executeUpdate();
			//コミットする
			con.commit();
			con.setAutoCommit(true);
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
		return T_ID;
	}
	
	/**
	 * タスクの追加、更新によりチームの更新日を更新する処理
	 * DAOパッケージ内メソッドのtry文の中で使用すること
	 * @param con
	 * @param T_ID 更新日を更新したいチームのID
	 * @return 成功で1
	 * @throws Exception
	 */
	static int teamsUpdate(Connection conectiton, int T_ID) throws Exception {
		String updateTeam = "UPDATE teams SET T_update_date = current_timestamp() WHERE T_ID = ?";
		PreparedStatement state = conectiton.prepareStatement(updateTeam);
		state.setInt(1, T_ID);
		return state.executeUpdate();
	}
}
