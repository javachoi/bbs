package mms.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import mms.member.db.JdbcUtil;
import mms.member.vo.Member;

public class MemberDAO {
	Connection con;
	
	public MemberDAO (Connection con) {
		this.con = con;
	}
	
	//1. 회원등록
	public int insertNewMember (Member newMember) {
		PreparedStatement pstmt = null;
		int insertCount = 0;
		String sql = "insert into mms_member values (mms_member_id_seq.nextval,?,?,?,?,?)";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, newMember.getName());
			pstmt.setString(2, newMember.getAddr());
			pstmt.setString(3, newMember.getEmail());
			pstmt.setString(4, newMember.getNation());
			pstmt.setInt(5, newMember.getAge());
			
			insertCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt);
		}
		
		return insertCount;
	}

	// 2. 회원목록보기
	public ArrayList<Member> selectMemberList(){
		PreparedStatement psmt = null;
		ResultSet rs = null;
		Member member = null;
		ArrayList<Member> memberList = new ArrayList<Member>();
		String sql = "select * from mms_member";
		try {
			psmt = con.prepareStatement(sql);
			rs = psmt.executeQuery(); 
			while(rs.next()) {
				String name = rs.getString("name");
				String addr = rs.getString("addr");
				String email = rs.getString("email");
				String nation = rs.getString("nation");
				int age = rs.getInt("age");
				member = new Member(name,addr,email,nation,age);
				memberList.add(member);
			}
			
		} catch (SQLException e) {
	
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(psmt);

	}
		return memberList;
	}
	
	// 3. update 할 때 특정 회원 정보보기 
	public Member selectOldMember (String name) {
		String sql = "select * from mms_member where name=?";
		Member member = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
	
		try {
			psmt = con.prepareStatement(sql);
			psmt.setString(1, name);
			rs = psmt.executeQuery(); 
			if(rs.next()) {
				String name2 = rs.getString("name");
				String addr = rs.getString("addr");
				String email = rs.getString("email");
				String nation = rs.getString("nation");
				int age = rs.getInt("age");
				member = new Member(name2,addr,email,nation,age);
			}
			
		} catch (SQLException e) {
	
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(psmt);
	}
		return member;
	}
	
	// 4. 회원정보 수정
	public int updateMember (Member updateMember) {
		int updateCount = 0;
		PreparedStatement pstmt = null;
		String sql = "update mms_member set addr=?,email=?,nation=?,age=? where name=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, updateMember.getName());
			pstmt.setString(2, updateMember.getAddr());
			pstmt.setString(3, updateMember.getEmail());
			pstmt.setString(4, updateMember.getNation());
			pstmt.setInt(5, updateMember.getAge());
			
			updateCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			
		} finally {
			JdbcUtil.close(pstmt);
		}
		return updateCount;
	}
	
	// 5. 회원정보 삭제 
	public int deleteMember (String name) {
		int deleteCount = 0;
		PreparedStatement pstmt = null;
		String sql = "update mms_member where name=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, name);
			
			deleteCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			
		} finally {
			JdbcUtil.close(pstmt);
		}
		return deleteCount;
	}
}

