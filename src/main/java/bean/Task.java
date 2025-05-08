package bean;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;

public class Task implements Serializable {
	private ArrayList<Integer> U_ID;
	private ArrayList<String> U_name;
	private int TSK_ID;
	private String TSK_taskname;
	private String TSK_contents;
	private int U_request_ID;
	private Date TSK_term;
	private int T_ID;
	private Date TSK_report_date;
	private Date TSK_completion_date;
	private Date TSK_requested_date;
	private Timestamp TSK_update_date;
	private String U_request_name;
	
	public String getU_request_name() {
		return U_request_name;
	}
	public void setU_request_name(String u_request_name) {
		U_request_name = u_request_name;
	}
	public ArrayList<Integer> getU_ID() {
		return U_ID;
	}
	public ArrayList<String> getU_name() {
		return U_name;
	}
	public int getTSK_ID() {
		return TSK_ID;
	}
	public String getTSK_taskname() {
		return TSK_taskname;
	}
	public String getTSK_contents() {
		return TSK_contents;
	}
	public int getU_request_ID() {
		return U_request_ID;
	}
	public Date getTSK_term() {
		return TSK_term;
	}
	public int getT_ID() {
		return T_ID;
	}
	public Date getTSK_report_date() {
		return TSK_report_date;
	}
	public Date getTSK_completion_date() {
		return TSK_completion_date;
	}
	public Date getTSK_requested_date() {
		return TSK_requested_date;
	}
	public Timestamp getTSK_update_date() {
		return TSK_update_date;
	}
	public void setU_ID(ArrayList<Integer> u_ID) {
		U_ID = u_ID;
	}
	public void setU_name(ArrayList<String> u_name) {
		U_name = u_name;
	}
	public void setTSK_ID(int tSK_ID) {
		TSK_ID = tSK_ID;
	}
	public void setTSK_taskname(String tSK_taskname) {
		TSK_taskname = tSK_taskname;
	}
	public void setTSK_contents(String tSK_contents) {
		TSK_contents = tSK_contents;
	}
	public void setU_request_ID(int u_request_ID) {
		U_request_ID = u_request_ID;
	}
	public void setTSK_term(Date tSK_term) {
		TSK_term = tSK_term;
	}
	public void setT_ID(int t_ID) {
		T_ID = t_ID;
	}
	public void setTSK_report_date(Date tSK_report_date) {
		TSK_report_date = tSK_report_date;
	}
	public void setTSK_completion_date(Date tSK_completion_date) {
		TSK_completion_date = tSK_completion_date;
	}
	public void setTSK_requested_date(Date tSK_requested_date) {
		TSK_requested_date = tSK_requested_date;
	}
	public void setTSK_update_date(Timestamp tSK_update_date) {
		TSK_update_date = tSK_update_date;
	}
	
	
}
