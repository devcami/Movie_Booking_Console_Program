package movie.booking.program.controller;

import java.util.ArrayList;
import java.util.List;

import movie.booking.program.vo.Member;

public class LoginManager {
	
	private List<Member> members = new ArrayList<>();
	int nowMember = -1;
	private int memberNo = 1;
	
	//1. 로그인
	public boolean login(String id, String password) {
		for(int i = 0; i < members.size(); i++) {
			if(members.get(i).isLogin(id, password)) {
				nowMember = i;
				return true;
			}
		}
		return false;
	}
	
	//2. 로그아웃
	public void logout() {
		nowMember = -1;
		System.out.println("로그아웃 되었습니다.");
	}
	
	//3. 회원 등록
	public boolean addMember(String addId, String addPassword) {
		//true : 같은 id가 있을 때
		if(checkId(addId)) {
			System.err.println("중복된 아이디 입니다. 다시 입력해주세요.");
			return false;
		} else {
			members.add(new Member(addId, addPassword));
			memberNo++;
			return true;
		}
	}
	//id중복확인
	private boolean checkId(String id) {
		for(int i = 0; i < members.size(); i++) {
			if(members.get(i).getId().equals(id)) {
				return true;
			}
		}
		return false;
	}
	
	//4. 회원 삭제
	public boolean deleteMember(String deleteId, String deletePassword) {
		for(int i = 0; i < members.size(); i++) {
			if(members.get(i).getId().equals(deleteId) &&
				members.get(i).getPassword().equals(deletePassword)) {
				return true;
			}
		}
		return false;
	}
}
