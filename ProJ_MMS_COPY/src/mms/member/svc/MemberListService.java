package mms.member.svc;

import java.sql.Connection;
import java.util.ArrayList;

import mms.member.dao.MemberDAO;
import mms.member.db.JdbcUtil;
import mms.member.vo.Member;

public class MemberListService {
	
	Connection con = null;
	
	public ArrayList<Member> getMemberList() {
		
		con = JdbcUtil.getConnection();
		MemberDAO memberdao = new MemberDAO(con);
		ArrayList<Member> memberList = memberdao.selectMemberList();
		
		JdbcUtil.close(con);
		
		return memberList;
	}

}
