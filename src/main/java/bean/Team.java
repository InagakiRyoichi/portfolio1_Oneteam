package bean;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

public class Team implements Serializable {
	private int T_ID;
	private String T_name;
	private Date T_create_date;
	private Timestamp T_update_date;
	public int getT_ID() {
		return T_ID;
	}
	public String getT_name() {
		return T_name;
	}
	public Date getT_create_date() {
		return T_create_date;
	}
	public Timestamp getT_update_date() {
		return T_update_date;
	}
	public void setT_ID(int t_ID) {
		T_ID = t_ID;
	}
	public void setT_name(String t_name) {
		T_name = t_name;
	}
	public void setT_create_date(Date t_create_date) {
		T_create_date = t_create_date;
	}
	public void setT_update_date(Timestamp t_update_date) {
		T_update_date = t_update_date;
	}
	
	
}
