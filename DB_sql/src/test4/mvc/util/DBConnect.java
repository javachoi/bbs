package test4.mvc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class DBConnect {
	
		private static DBConnect db = new DBConnect();
		private Connection conn = null;
		
		String jdbc_driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:orcl";
		private DBConnect() {
			try {
				Class.forName(jdbc_driver);
					conn = DriverManager.getConnection(url, "javalink", "javalink");
				} catch (ClassNotFoundException e) {
				} catch (SQLException e) {
				
				}
			}
		//Singleton : 메소드를 통한 객체생성을 한번만, 현재 클래스에서만 생성 
		public static DBConnect getInstance() {
			return db;
	}	
		//DB연결이 완료가 된 상태
		public Connection getConnection() { //연결이 된 값을 사용하겠다.
			return conn;
	}
}
