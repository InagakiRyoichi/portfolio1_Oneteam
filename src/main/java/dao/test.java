package dao;

import java.util.ArrayList;

//import bean.Customer;

public class test extends DAO{
	public static void main(String[] args) {
		TeamDAO teamDAO = new TeamDAO();
		ArrayList<Integer> U_IDList = new ArrayList<>();
		U_IDList.add(2);
		U_IDList.add(1);
//		System.out.println(teamDAO.addTeam("testFromJAVA", U_IDList));
//		System.out.println(task.getTSK_taskname());
		TaskDAO taskDAO = new TaskDAO();
//		taskDAO.progressTask(5, 1);
		System.out.println(java.sql.Date.valueOf("2025-1-1"));
	}
}
