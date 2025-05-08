package bean;

import java.io.Serializable;

public class Users implements Serializable{
	private String U_name ;
	private int U_ID;
	private String U_pass ;
	private  int U_admin ;
	private int U_color ;
	
	public String getU_name() {
		return U_name;
	}
	public void setU_name(String u_name) {
		U_name = u_name;
	}
	public int getU_ID() {
		return U_ID;
	}
	public void setU_ID(int u_ID) {
		U_ID = u_ID;
	}
	public String getU_pass() {
		return U_pass;
	}
	public void setU_pass(String u_pass) {
		U_pass = u_pass;
	}
	public int getU_admin() {
		return U_admin;
	}
	public void setU_admin(int u_admin) {
		U_admin = u_admin;
	}
	public int getU_color() {
		return U_color;
	}
	public void setU_color(int u_color) {
		U_color = u_color;
	}
	
	
}
