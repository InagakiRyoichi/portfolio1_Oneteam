package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class DAO {
	private Connection con = null; //DBコネクション接続オブジェクトJDBC、ドライバが正常にロードされると、DriverManagerを通じてデータベースと接続されるコネクションオブジェクトを生成します。
	PreparedStatement st = null;
    private static final String USERNAME = "student";//自分のDB ID
    private static final String PASSWORD = "studentpass";//自分のDBパスワード
    private static final String URL = "jdbc:mysql://192.168.0.158/oneteams?serverTimezone=UTC&useSSL=false";//自分のDBアドレス
    
    public Connection getConnection() throws Exception{
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (Exception e) {
            System.out.println("失敗 ");
        }
        return con;
    }
}
