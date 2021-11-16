package mms.member.contoller;

import java.util.Scanner;
import mms.member.action.Action;

public class MemberController {
	
	public void processRequest(Action action,Scanner sc){
		try{
		action.excute(sc);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}	

}
		
