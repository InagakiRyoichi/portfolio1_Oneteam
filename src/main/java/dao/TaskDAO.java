package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

import bean.Task;

public class TaskDAO extends DAO{
	/**
	 * タスク確認用にタスクIDから該当タスクの情報を返す
	 * @param intTaskID タスクID
	 * @return
	 */
	public Task getTask(int intTaskID) {
		String selectTsk = "SELECT * FROM tasks LEFT JOIN users ON tasks.U_request_ID = users.U_ID WHERE tasks.Tsk_ID = ?";
		String selectTM = "SELECT U_name, U_ID FROM users WHERE U_ID IN (SELECT U_ID FROM task_members WHERE Tsk_ID = ?)";

		Task task = new Task();
		
		try (Connection con = getConnection()){
			st= con.prepareStatement(selectTsk);
			st.setInt(1, intTaskID);
			ResultSet rs = st.executeQuery();
			rs.next();
			task.setTSK_ID(intTaskID);
			task.setTSK_taskname(rs.getString("TSK_taskname"));
			task.setTSK_contents(rs.getString("TSK_contents"));
			task.setU_request_ID(rs.getInt("U_request_ID"));
			task.setTSK_term(rs.getDate("TSK_term"));
			task.setT_ID(rs.getInt("T_ID"));
			task.setTSK_report_date(rs.getDate("TSK_report_date"));
			task.setTSK_completion_date(rs.getDate("TSK_completion_date"));
			task.setTSK_requested_date(rs.getDate("TSK_requested_date"));
			task.setTSK_update_date(rs.getTimestamp("TSK_update_date"));
			task.setU_request_name(rs.getString("U_name"));
			st = con.prepareStatement(selectTM);
			st.setInt(1, intTaskID);
			rs = st.executeQuery();
			ArrayList<String> names = new ArrayList<>();
			ArrayList<Integer> ids = new ArrayList<>();
			while(rs.next()) {
				names.add(rs.getString("U_name"));
				ids.add(rs.getInt("U_ID"));
			}
			task.setU_name(names);
			task.setU_ID(ids);
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
		return task;
	}
	
	/**
	 * 自身が依頼した仕事のリストを返す
	 * @param intU_ID 本人のU_ID
	 * @param intStatus 0で全仕事、1で未完の仕事
	 * @return
	 */
	public ArrayList<Task> searchRequestTasks(int intU_ID, int intStatus){
		//String sql = "SELECT TSK_ID FROM tasks WHERE TSK_ID IN (SELECT TSK_ID FROM task_members WHERE U_ID = ?) ORDER BY TSK_term";
		String sql = "SELECT TSK_ID FROM tasks WHERE U_request_ID = ?";
		if(intStatus == 1) {
			sql += " AND TSK_completion_date IS NULL";
		}
		sql += " ORDER BY TSK_term";
		Task task = new Task();
		ArrayList<Task> taskList = new ArrayList<>();
		try (Connection con = getConnection()){
			st= con.prepareStatement(sql);
			st.setInt(1, intU_ID);
			ResultSet rs = st.executeQuery();
			while(rs.next()){
				task = getTask(rs.getInt("TSK_ID"));
				taskList.add(task);
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
		return taskList;
	}
	
	/**
	 * 自分が依頼された仕事のリストを返す
	 * @param intU_ID
	 * @param intStatus 0で全仕事、1で未完の仕事
	 * @return
	 */
	public ArrayList<Task> searchRequestedTasks(int intU_ID, int intStatus){
		String sql = "SELECT TSK_ID FROM tasks WHERE TSK_ID IN (SELECT TSK_ID FROM task_members WHERE U_ID = ?) ORDER BY TSK_term";
		Task task = new Task();
		ArrayList<Task> taskList = new ArrayList<>();
		try (Connection con = getConnection()){
			st= con.prepareStatement(sql);
			st.setInt(1, intU_ID);
			ResultSet rs = st.executeQuery();
			while(rs.next()){
				task = getTask(rs.getInt("TSK_ID"));
				if(intStatus == 1) {
					if(task.getTSK_completion_date() == null) {
						taskList.add(task);
					}
				}else {
					taskList.add(task);
				}
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
		return taskList;
	}
	
	/**
	 * 特定のチームが持つ仕事のリストを返す
	 * @param intT_ID 特定のチームのT_ID
	 * @param intStatus 0で全仕事、1で未完の仕事
	 * @return
	 */
	public ArrayList<Task> searchTasksByTeam(int intT_ID, int intStatus){
		String sql = "SELECT TSK_ID FROM tasks WHERE T_ID = ?";
		if(intStatus == 1) {
			sql += " AND U_completion_date IS NULL";
		}
		sql += " ORDER BY TSK_term";
		Task task = new Task();
		ArrayList<Task> taskList = new ArrayList<>();
		try (Connection con = getConnection()){
			st= con.prepareStatement(sql);
			st.setInt(1, intT_ID);
			ResultSet rs = st.executeQuery();
			while(rs.next()){
				task = getTask(rs.getInt("TSK_ID"));
				taskList.add(task);
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
		return taskList;
	}
	
	/**
	 * タスクを追加する
	 * @param task 追加するタスク
	 * @return 成功した場合、登録したタスクのIDを返す
	 */
	public int addTask(Task task) {
		//tasksテーブルからTSK_IDの最大値を取得する
		String selectTID = "SELECT MAX(TSK_ID) FROM tasks";
		//tasksテーブルに追加するクエリ
		String insertTask = "INSERT INTO tasks "
				+ "(`TSK_taskname`, `TSK_contents`, `U_request_ID`, `TSK_term`, `T_ID`) "
				+ "VALUES (?, ?, ?, ?, ?)";
		//task_membersに登録するクエリ
		String insertTaskM = "INSERT INTO task_members (U_ID, TSK_ID) VALUES";
		int intRequestedMembers = task.getU_ID().size();
		for(int i = 0; i < intRequestedMembers; i++) {
			insertTaskM += "(?, ?),";
		}
		//末尾の,を削除
		insertTaskM = insertTaskM.substring(0, insertTaskM.length() - 1);
		
		//成否判定用
		int TSK_ID = 0;
		try (Connection con = getConnection()){
			//テーブルを同時に書き換えるために自動コミットをオフにする
			con.setAutoCommit(false);
			//最初に外部キー参照元のtasksから追加
			st= con.prepareStatement(insertTask);
			st.setString(1, task.getTSK_taskname());
			st.setString(2, task.getTSK_contents());
			st.setInt(3, task.getU_request_ID());
			st.setString(4, task.getTSK_term().toString());
			st.setInt(5, task.getT_ID());
			st.executeUpdate();
			//T_IDがオートインクリメントのため、追加したタスクのT_IDは最大値
			st = con.prepareStatement(selectTID);
			ResultSet rs = st.executeQuery();
			rs.next();
			TSK_ID = rs.getInt(1);
			//次にtask_membersテーブルに追加する
			st = con.prepareStatement(insertTaskM);
			for(int i = 0; i < intRequestedMembers; i++) {
				st.setInt(2 * i + 1, task.getU_ID().get(i));
				st.setInt(2 * i + 2, TSK_ID);
			}
			st.executeUpdate();
			TeamDAO.teamsUpdate(con, task.getT_ID());
			//コミットする
			con.commit();
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
		return TSK_ID;
	}
	
	/**
	 * タスクの報告完了を行う
	 * 第二引数が1なら報告日、2なら完了日を更新。3なら報告日差戻、4なら完了日差戻
	 * @param intTSK_ID 対象とするタスクのID
	 * @param intStatus 1で報告2で完了を行う
	 * @return 成否判定に関する値にする予定（まだ）
	 */
	public int progressTask(int intTSK_ID, int intStatus) {
		//報告したいタスクの属するチームIDを取得する
		int intT_ID = getTask(intTSK_ID).getT_ID();
		
		String updateTSK = "UPDATE tasks SET ";
		if(intStatus == 1 || intStatus ==3) {
			updateTSK += "TSK_report_date";
		}else if(intStatus == 2 || intStatus ==4) {
			updateTSK += "TSK_completion_date";
		}
		if(intStatus <= 2) {
			updateTSK += " = CURRENT_DATE() WHERE TSK_ID = ?";
		}else if(intStatus >= 3) {
			updateTSK += " = NULL WHERE TSK_ID = ?";
		}
		
		int line = -1;//成否判定用に暫定てきにおいた。意味はまだない
		try (Connection con = getConnection()){
			//テーブルを同時に書き換えるために自動コミットをオフにする
			con.setAutoCommit(false);
			st= con.prepareStatement(updateTSK);
			st.setInt(1, intTSK_ID);
			line = st.executeUpdate();
			//報告したタスクの属するチームの更新日を更新する
			line *= TeamDAO.teamsUpdate(con, intT_ID);
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
		return line;
	}
	
	/**
	 * タスクの更新
	 * @param task 更新したいタスク
	 * @return うまく更新できなかった時だけ0が入る予定
	 */
	public int renewalTask(Task task) {
		int TSK_ID = task.getTSK_ID();
		//tasksの更新
		String updateTask = "UPDATE tasks SET TSK_taskname = ?, TSK_contents = ?, TSK_term = ?"
				+ "WHERE TSK_ID = ?";
		//task_members シートから既存の行を削除する
		String deleteTaskM = "DELETE FROM task_members WHERE TSK_ID = ?";
		//task_members シートに更新後のメンバを追加する
		String insertTaskM = "INSERT INTO task_members (TSK_ID, U_ID) VALUES";
		//追加する担当者数に合わせてSQL文を用意する
		for(int i: task.getU_ID()) {
			insertTaskM += "(?, ?),";
		}
		//末尾の,を削除
		insertTaskM = insertTaskM.substring(0, insertTaskM.length() - 1);
		//成否判定用
		int line = -1;
		try (Connection con = getConnection()){
			//テーブルを同時に書き換えるために自動コミットをオフにする
			con.setAutoCommit(false);
			//task_membersから行削除
			st= con.prepareStatement(deleteTaskM);
			st.setInt(1, TSK_ID);
			line = st.executeUpdate();
			//tasksテーブルをアップデート
			st = con.prepareStatement(updateTask);
			st.setString(1, task.getTSK_taskname());
			st.setString(2, task.getTSK_contents());
			st.setString(3, task.getTSK_term().toString());
			st.setInt(4, TSK_ID);
			//三つのSQL文の中に一つでもテーブルを更新できていない文がないか監視する
			//一つでも更新行数が0ならば更新行数の積は0になる
			line *= st.executeUpdate();
			//task_membersテーブルに追加する
			st = con.prepareStatement(insertTaskM);
			//追加する担当者数に合わせてSQL文を用意する
			//セット先のSQL文にはタスクIDと担当者IDの二つ？があるため、
			//担当者一人あたり二回setIntする
			for(int i = 0; i < task.getU_ID().size(); i++) {	
				st.setInt(2 * i + 1, TSK_ID);
				st.setInt(2 * i + 2, task.getU_ID().get(i));
			}
			line *= st.executeUpdate();
			TeamDAO.teamsUpdate(con, task.getT_ID());
			//コミットする
			con.commit();
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
