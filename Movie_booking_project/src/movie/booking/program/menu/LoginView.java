package movie.booking.program.menu;

import java.util.Scanner;

import movie.booking.program.controller.LoginManager;

public class LoginView {
	
	private Scanner sc = new Scanner(System.in);
	
	private LoginManager lm = new LoginManager();
	
	String mainString = "\n======= 🖥 Login Menu 🖥 =======\n"
			  + "\t1. 로그인\n"
			  + "\t2. 로그아웃\n"
			  + "\t3. 계정 등록\n"
			  + "\t4. 계정 삭제\n"
			  + "\t9. 종료\n"
			  + "----------------------------------------\n"
			  + "\t"
			  + "➜ 메뉴 선택 : ";
	
	public void mainMenu() {
		outer:
		while(true) {
			System.out.print(mainString);
			String choice = sc.next();
			
			switch(choice) {
			case "1" :
				System.out.println("어서오세요!");
				System.out.print("id를 입력하세요 : ");
				String id = sc.next();
				System.out.print("password를 입력하세요 : ");
				String password = sc.next();
				if(lm.login(id, password)) {
					System.out.println(id + "님! 로그인 되었습니다.");
					break outer;
				}else {
					System.err.println("아이디 또는 비밀번호를 정확히 입력하세요.");
					continue outer;
				}
			case "2" :
				lm.logout();
				break;
			case "3" :
				add:
				while(true) {
					System.out.println("---회원가입 메뉴---");
					System.out.print("id를 입력하세요 : ");
					String addId = sc.next();
					System.out.print("password를 입력하세요 : ");
					String addPassword = sc.next();
					if(lm.addMember(addId, addPassword)) {
						System.out.println(addId + "님! 회원 등록이 완료되었습니다.");
						break;
					} else {
						System.err.println("회원 가입에 실패하였습니다.");
						continue add;
					}
				}
				break;
			case "4" :
				delete:
				while(true) {
					System.out.println("---회원삭제 메뉴---");
					System.out.print("id를 입력하세요 : ");
					String deleteId = sc.next();
					System.out.print("password를 입력하세요 : ");
					String deletePassword = sc.next();
					if(lm.deleteMember(deleteId, deletePassword)) {
						System.out.println(deleteId + "이 계정을 삭제하시겠습니까?");
						break;
					} else {
						System.err.println("아이디 또는 비밀번호를 정확히 입력하세요.");
						continue delete;
					}
				}
				break;
			case "9" : return;
			default : System.err.println("❗️️선택지에 있는 번호만 입력해주세요❗️"); continue;
 			}
		}
	}

}
