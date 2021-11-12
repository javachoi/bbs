package plsql;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProcedureTest {
	Connection con;

	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException cne) {
			cne.printStackTrace();
		}
	}

	public void connect() {
		try {
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "javalink", "javalink");
			System.out.println("Connection Success!!!");
		} catch (SQLException se) {
			se.printStackTrace();
		}
	}

	public void insert() {
		PreparedStatement pstmt = null;
		connect();
		try {
			String sql = "insert into member3 values(?,?,?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1,"Alpha");
			pstmt.setString(2,"1234");
			pstmt.setString(3,"AI");
			pstmt.setInt(4,20);
			pstmt.setString(5,"LA");
			pstmt.setString(6,"Alpha@aa.com");
			int count = pstmt.executeUpdate();
			if (count > 0)
				System.out.println("insert success!!!");
			else
				System.out.println("insert fail!!!");

		} catch (SQLException e) {

		} finally {
			try {
				pstmt.close();
				con.close();
			} catch (SQLException e) {

			}
		}

	}

	public void select() {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from member3";
		connect();

		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				System.out.println("아이디 : " + rs.getString(1) + ", 비밀번호 : " + rs.getString(2) + ", 이름 : "
						+ rs.getString(3) + ", 나이 : " + rs.getInt(4) + ", 주소 : " + rs.getString(5) + ", 이메일 : "
						+ rs.getString(6));
			}
		} catch (SQLException e) {

		} finally {
			try {
				pstmt.close();
				con.close();
			} catch (SQLException e) {
			}
		}
	}

	public void insertMember() {
		CallableStatement cs = null;
		connect();
		try {
			String sql = "{call user_insert(?,?,?,?,?,?)}";
			cs = con.prepareCall(sql);
			
			cs.setString(1,"Procedure");
			cs.setString(2,"1234");
			cs.setString(3,"홍길동");
			cs.setInt(4, 19);
			cs.setString(5,"강원도");
			cs.setString(6,"hong@aa.com");
			int count = cs.executeUpdate();
			if (count > 0)
				System.out.println("insert success!!!");
			else
				System.out.println("insert fail!!!");

		} catch (SQLException e) {

		} finally {
			try {
				cs.close();
				con.close();
			} catch (SQLException e) {

			}
		}

	}

	public static void main(String[] args) {
		ProcedureTest pt = new ProcedureTest();
		System.out.println("*** 저장 프로시저 호출전 데이터");
		pt.insert();
		pt.select();

		System.out.println("*** 저장 프로시저 호출후 데이터");
		pt.insertMember();
	}
}
