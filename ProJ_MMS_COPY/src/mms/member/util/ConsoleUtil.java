package mms.member.util;
// 사용자 입력

import java.util.Scanner;

import mms.member.vo.Member;

// 키보드 입력
public class ConsoleUtil {
// 1.회원등록
	public Member getMember (Scanner sc) {
		System.out.println("등록할 회원정보를 입력하세요?");
		System.out.print("이름 : ");
		String name = sc.next();
		
		System.out.print("주소 : ");
		String addr = sc.next();
		
		System.out.print("이메일 : ");
		String email = sc.next();
		
		System.out.print("국가 : ");
		String nation = sc.next();
		
		System.out.print("나이 : ");
		int age = sc.nextInt();
		
		return new Member(name,addr,email,nation,age);
		
	}
	public void printAddSuccessMessage(Member newMember) {
		System.out.println(newMember.getName() + "회원정보 추가성공");
	}
	public void printAddFailMessage(Member newMember) {
		System.out.println(newMember.getName() + "회원정보 추가실패");
}
}