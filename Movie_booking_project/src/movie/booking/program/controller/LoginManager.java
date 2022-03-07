package movie.booking.program.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import movie.booking.program.vo.Member;

public class LoginManager {
	
	private List<Member> members = new ArrayList<>();
	
	//회원번호
	private int memberNo;
	String filePath = "/Users/camilee/Documents/dev/";
	String fileName = "membersInfo.txt";

	
	//members List getter/setter
	public List<Member> getMembers() {
		return members;
	}
	
	public void setMembers(List<Member> members) {
		this.members = members;
	}
	
	//계정 등록
	public int addMember(Member member) {
		int result = 0; //0정상 1에러
		
		try {
			member.setMemberNo(++this.memberNo);
			members.add(member);
		} catch(Exception e) {
			System.err.println("에러 발생\n" + e.getMessage());
			result = 1;
		}
		return result;
	}
	
    //계정 삭제
    public int removeMember(int memberNo) {
        int result = 0;
        
        try {
            for(int i = 0; i < members.size(); i++) {
                Member temp = members.get(i);
                
                if(memberNo == temp.getMemberNo()) {
                    members.remove(i);
                    
                    try {
                        FileUtil.writeFile(filePath, fileName, getMembers());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                                     
                    break;
                }
                if(i == members.size() - 1) {
                    result = 2;
                }
            }
        } catch(Exception e) {
            System.err.println("에러 발생\n" + e.getMessage());
            result = 1;
        }
        return result;
    }
	
//	public void printInfo() {
//		System.out.println("----------------------------------------");
//		for(int i = 0; i < members.size(); i++) {
//			System.out.println("회원 번호 : " + members.get(i).getMemberNo() +
//							 "\nID : " + members.get(i).getId() +
//							 "\n이름 : " + members.get(i).getName());
//		}
//		System.out.println("----------------------------------------");
//	}
	
	
}