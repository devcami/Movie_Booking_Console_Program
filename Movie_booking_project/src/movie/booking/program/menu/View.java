package movie.booking.program.menu;
/**
 * 	로그인 Menu (보류)
 * 	메인 Menu
 * 	영화 스케쥴 Menu - 극장별 ..
 * 	예매 내역 확인서
 *	예매 취소
 */

import java.util.Scanner;

import movie.booking.program.contoller.MovieManager;

public class View {
	private Scanner sc = new Scanner(System.in);
	
	private MovieManager manager = new MovieManager();
	private int choiceMovie;
	private int choiceTheater;
	private String[][] seats = {{"☐ ", "☐ ", "☐ ", "☐ ", "☐ ", "☐ "},
			{"☐ ", "☐ ", "☐ ", "☐ ", "☐ ", "☐ "},
			{"☐ ", "☐ ", "☐ ", "☐ ", "☐ ", "☐ "},
			{"☐ ", "☐ ", "☐ ", "☐ ", "☐ ", "☐ "},
			{"☐ ", "☐ ", "☐ ", "☐ ", "☐ ", "☐ "},
			{"☐ ", "☐ ", "☐ ", "☐ ", "☐ ", "☐ "}};
	
	private String mainString = "---- Movie Booking Menu ----\n"
							  + "1. 현재 상영 영화 스케쥴 출력\n"
							  + "2. 영화별 예매하기\n"
							  + "3. 극장별 예매하기\n"
							  + "4. 나의 예매내역\n"
							  + "9. 종료\n"
							  + "----------------------------\n"
							  + ">> 메뉴 선택 : ";
	
	
	
//	public View() {
//		
//	}
//
//
//	public View(String[][] seats) {
//		this.seats = seats;
//		for(int i = 0; i < seats.length; i++) {
//			for(int j = 0; j < seats[i].length; j++) {
//				seats[i][j] = "☐ ";
//			}
//		}
//	}



	public void mainMenu() {
		while(true) {
			System.out.print(mainString);
			int choice = sc.nextInt();
			
			switch(choice) {
			case 1 : 
				manager.printSchedule(theaterMenu());
				break;
			case 2 : 
				if(manager.bookingByMovie(movieMenu())) {
					seatsMenu();
				} else {
					return;
				}
				if(checking()) {
					manager.myBooking();	
				} else {
					
				}
				
				break;
			case 3 : 
				manager.bookingByTheater(theaterMenu());
				seatsMenu();
				if(checking()) {
					manager.myBooking();
				} else {
					
				}
				break;
			case 4 : manager.myBooking();
				break;
			case 9 : return;
			default : System.out.println("잘못 입력하셨습니다.");
			}
		}
	}
	
	
	//영화선택
	public int movieMenu() {
		String movieString = "---- Movie List ----\n"
				   + "1. 더 배트맨\n"
				   + "2. 나이트메어 앨리\n"
				   + "3. 나이트 레이더스\n"
				   + "4. 안터벨룸\n"
				   + "0. 뒤로가기\n"
				   + "--------------------\n"
				   + ">> 영화 선택 : ";
		System.out.print(movieString);
		choiceMovie = sc.nextInt();
		return choiceMovie;
		
	}
	//극장선택
	public int theaterMenu() {
		String theaterString = "---- Theater List ----\n"
			     + "1. 용산점\n"
			     + "2. 홍대점\n"
			     + "3. 강남점\n"
			     + "0. 뒤로가기\n"
			     + "--------------------\n"
			     + ">> 극장 선택 : ";
		System.out.print(theaterString);
		choiceTheater = sc.nextInt();
		return choiceTheater;
	}
	
	//좌석선택
	public void seatsMenu() {
		while(true) {

			//좌석 출력
			System.out.println("--------------------");
			System.out.println("  1 2 3 4 5 6");

			char column = 'A';
			for(int i = 0; i < seats.length; i++) {
				System.out.print((char)(column + i) + " ");
				for(int j = 0; j < seats[i].length; j++) {
					System.out.print(seats[i][j]);
				}
				System.out.println();
			}
			System.out.println("--------------------");

			System.out.println(">> 좌석 선택");
			System.out.print(">> 행을 선택해주세요 (A~F) : ");
			char rowChoice = sc.next().charAt(0);
			System.out.print(">> 열을 선택해주세요 (1~6) : ");
			int columnChoice = sc.nextInt();

			String selectSeat = Character.toString(rowChoice) + columnChoice;

			if(seats[(int)rowChoice-65][columnChoice-1].equals("◼︎ ")){
				System.out.println(selectSeat + "은 이미 선택된 좌석입니다. 다시 선택해주세요.");
			} 
			else {
				System.out.println(selectSeat + "으로 선택하셨습니다.");
				seats[rowChoice - 65][columnChoice - 1] = "◼︎ ";
				manager.takenSeat(Character.toString(rowChoice), columnChoice);
				break;
			}

			//			manager.takenSeat(Character.toString(rowChoice), columnChoice);

			//		outer:
			//		for(int i = 0; i < seats.length; i++) {
			//			for(int j = 0; j < seats[i].length; j++) {
			//				if(seats[i][j].equals("◼︎ ")){
			//					System.out.println(selectSeat + "은 이미 선택된 좌석입니다.");
			//					break outer;
			//				}else {
			//					System.out.println(selectSeat + "으로 선택하셨습니다.");
			//					break outer;
			//				}
			//			}
			//		}

		
		}
		
		
	}
	// 최종확인
	public boolean checking() {
		while(true) {
			System.out.println(">> 예매 하시겠습니까 ? (y/n) : ");
			char yn = sc.next().charAt(0);
			if(yn == 'y') {
				System.out.println("예매가 정상처리되었습니다.");
				return true;
			} else if(yn == 'n') {
				System.out.println("예매에 실패하였습니다.");
				return false;
			} else {
				System.out.println("잘못 입력하셨습니다. y/n중 입력해주세요.");
			}
		}
	}
}
