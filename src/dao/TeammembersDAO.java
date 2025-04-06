package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class TeammembersDAO extends DAO{
	public void addTeam(int intT_ID,int intU_ID) throws Exception {
		try(Connection con = getConnection()) {
			PreparedStatement st ; //DAOにあるからなくてもOK
			String sqlSelect = "INSERT INTO team_members (U_ID,T_ID) values (?,?) " ;
			st = con.prepareStatement(sqlSelect);
			st.setInt(1, intU_ID);
			st.setInt(2, intT_ID);
			st.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally  {
			try {
				//isClosed接続を確認する接続:true 接続X:false
				if(st!=null && !st.isClosed()) {
					st.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	/**
	 * チームから退出する
	 * @param intT_ID 退出したいチームのT_ID
	 * @param intU_ID 自身のU_ID
	 */
	public void leaveTeam(int intT_ID,int intU_ID) {
		String sql = "DELETE FROM team_members WHERE U_ID = ? AND T_ID = ?" ;
		try(Connection con = getConnection()) {
			st = con.prepareStatement(sql);
			st.setInt(1, intU_ID);
			st.setInt(2, intT_ID);
			st.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			} finally  {
	           try {
	        	   //isClosed接続を確認する接続:true 接続X:false
	               if(st!=null && !st.isClosed()) {
	                   st.close();
	               }
	           } catch (Exception e2) {
	        	   e2.printStackTrace();
	           }
			}		
	}
}
