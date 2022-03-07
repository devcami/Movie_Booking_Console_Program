package movie.booking.program.menu;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import movie.booking.program.controller.FileUtil;
import movie.booking.program.controller.LoginManager;
import movie.booking.program.vo.Member;

public class LoginView {
	
	Scanner sc = new Scanner(System.in);
	
	private LoginManager lm = new LoginManager();
	
	String filePath = "/Users/camilee/Documents/dev/";
	String fileName = "membersInfo.txt";
	
	List<Member> loginCheck = new ArrayList<>();
	Member deleteCheck = new Member();
			
    String mainString = "\n============= 🖥 Login Menu 🖥 =============\n\n"
    				  + "\t1. 로그인\n"
    				  + "\t2. 계정 등록\n"
    				  + "\t3. 계정 삭제\n"
    				  + "\t4. 등록된 계정 조회\n"
    				  + "\t9. 종료\n"
    				  + "--------------------------------------------\n"
    				  + "\t➜ 메뉴 선택 : ";
    
	public int mainMenu() {
		while(true) {
			System.out.print(mainString);
			String choice = sc.next();
			
			switch(choice) {
			//login
			case "1" :
				System.out.println("\n================== 로그인 ==================");
				System.out.print("\t➜ ID : ");
				String inputId = sc.next();
				System.out.print("\t➜ Password : ");
				String inputPw = sc.next();
				try {
					loginCheck = FileUtil.readFile(new File(filePath, fileName)); 
					for(int i = 0; i < loginCheck.size(); i++) {
						if(inputId.equals(loginCheck.get(i).getId()) &&
								inputPw.equals(loginCheck.get(i).getPassword())){
							System.out.println("\t🎶 로그인 성공 🎶");
							return 1;
						} else {
							System.err.println("\t❗아이디, 비밀번호를 체크하세요❗");
							return 2;
						}
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				}

			//add
			case "2" :
				System.out.println("\n================= 회원 등록 =================");
				lm.addMember(inputInfo());
				try {
					FileUtil.writeFile(filePath, fileName, lm.getMembers());
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
				
			//remove
			case "3" :
				System.out.println("\n================= 회원 삭제 =================");
				System.out.print("\t➜ 삭제할 아이디를 입력해주세요: ");
				String deleteId = sc.next();
				System.out.print("\t➜ 비밀번호를 입력해주세요.");
				String deletepwd = sc.next();
				
				try {
					for(int i = 0; i < lm.getMembers().size(); i++) {
						if(FileUtil.readFile(new File(filePath, fileName)).get(i).getId().equals(deleteId)
							&& FileUtil.readFile(new File(filePath, fileName)).get(i).getPassword().equals(deletepwd)) {
							int deleteNo = FileUtil.readFile(new File(filePath, fileName)).get(i).getMemberNo();
							lm.removeMember(deleteNo);
						}
						else {
							System.out.println("존재하지 않는 회원입니다.");
						}
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
				break; 
				
			//등록된 계정 조회
			case "4" :
				System.out.println("\n============= 등록된 회원 조회 =============");
				System.out.print("\t➜ ID를 입력해주세요 : ");
				String searchId = sc.next();
				try {
					loginCheck = FileUtil.readFile(new File(filePath, fileName));
					if(loginCheck.isEmpty()) {
						System.out.println("\t등록되지 않은 회원입니다.");
					} else{
						for(int i = 0; i < loginCheck.size(); i++) {
							if(searchId.equals(loginCheck.get(i).getId())){
								System.out.println("\n\t" + searchId + "님은 등록된 회원입니다.");
								break;
							} else {
								System.out.println("\t등록되지 않은 회원입니다.");
								break;
							}
						}
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				break;
				
			case "9" : return 0;
			default : System.err.println("❗️️선택지에 있는 번호만 입력해주세요❗️"); continue;
				
			}
		}
	}
	
    public Member inputInfo() {
        Member m = new Member();
        System.out.print("\t➜ 이름 : ");
        m.setName(sc.next());
        id:
        while(true) {
        	System.out.print("\t➜ ID : ");
        	String checkId = sc.next();
        	try {
        		loginCheck = FileUtil.readFile(new File(filePath, fileName));
        		if(loginCheck.isEmpty()) {
        			m.setId(checkId);
        			break id;
        		}
        		else{
        			for(int i = 0; i < loginCheck.size(); i++) {
        				if(checkId.equals(loginCheck.get(i).getId())){
        					System.out.println("\n\t" + checkId + "는 이미 등록된 ID입니다.");
        					System.out.println("\t다른 ID를 입력해주세요.");
        					continue id;
        				} else {
        					m.setId(checkId);
        					break id;
        				}
        			}
        		}
        	} catch (IOException e1) {
        		e1.printStackTrace();
        	}
        }
        System.out.print("\t➜ 비밀번호 : ");
        m.setPassword(sc.next());
        
        return m;
        
    }

		
	

}