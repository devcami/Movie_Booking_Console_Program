package movie.booking.program.menu;
/**
 * 	로그인 Menu (보류)
 * 	메인 Menu
 * 	영화 스케쥴 Menu - 극장별 ..
 * 	예매 내역 확인서
 *	예매 취소
 */

import java.util.InputMismatchException;
import java.util.Scanner;

import movie.booking.program.controller.MovieManager;
import movie.booking.program.vo.Movie;

public class View {
	private Scanner sc = new Scanner(System.in);
	
	private MovieManager manager = new MovieManager();
	private String choiceMovie;
	private String choiceTheater;
	private String choiceMyBooking;
	private Movie selectedMovie = null;		
	
	private int realIndex = 0;
	
	private char rowChoice;
	private String tempColumn;
	private int columnChoice;
	
	private String mainString = "\n---- Movie Booking Menu ----\n"
							  + "1. 현재 상영 영화 스케쥴 출력\n"
							  + "2. 영화별 예매하기\n"
							  + "3. 극장별 예매하기\n"
							  + "4. 나의 예매내역\n"
							  + "9. 종료\n"
							  + "----------------------------\n"
							  + ">> 메뉴 선택 : ";
	

	
	public void mainMenu() {
		
		while(true) {
			System.out.print(mainString);
			
			String choice = sc.next();
			
			switch(choice) {
			case "1" : 
				manager.printSchedule(theaterMenu());
				break;
			case "2" : 
				selectedMovie = manager.bookingByMovie(movieMenu());
				if(selectedMovie == null) {
					break;
				} else{ 
					seatsMenu(selectedMovie);
				}

				if(checking()) {
					manager.nowBookingPrint();	
				} else {
					manager.deleteBooking(realIndex);
					selectedMovie.getSeats()[rowChoice - 65][columnChoice - 1] = "︎☐ ";
				}
				break;

			case "3" : 
				selectedMovie = manager.bookingByTheater(theaterMenu());
				if(selectedMovie == null) {
					break;
				}
				else{
					seatsMenu(selectedMovie);
				}

				if(checking()) {
					manager.nowBookingPrint();
				} else {
					manager.deleteBooking(realIndex);
					selectedMovie.getSeats()[rowChoice - 65][columnChoice - 1] = "︎☐ ";
				}
				break;

			case "4" : 
				if(manager.myBooking(myBookingMenu())) {
					break;
				}
				//삭제했을 때 realIndex--; 되어야 함!!
				else {
					realIndex--;
					break;
				}

			case "9" : return;

			default : System.err.println("선택지에 있는 번호만 입력해주세요."); continue;
			}
		}

	}
	
	
	//영화선택
	public String movieMenu() {
		
		String movieString = "---- Movie List ----\n"
				   + "1. 더 배트맨\n"
				   + "2. 나이트메어 앨리\n"
				   + "3. 나이트 레이더스\n"
				   + "4. 안터벨룸\n"
				   + "0. 뒤로가기\n"
				   + "--------------------\n"
				   + ">> 영화 선택 : ";
		System.out.print(movieString);
		choiceMovie = sc.next();
		return choiceMovie;
		
	}
	//극장선택
	public String theaterMenu() {
		
		String theaterString = "---- Theater List ----\n"
			     + "1. 용산점\n"
			     + "2. 홍대점\n"
			     + "3. 강남점\n"
			     + "0. 뒤로가기\n"
			     + "--------------------\n"
			     + ">> 극장 선택 : ";
		System.out.print(theaterString);
		choiceTheater = sc.next();
		return choiceTheater;
	}


	//좌석선택
	public void seatsMenu(Movie movie) {
		
		while(true) {
			//좌석 출력
			System.out.println("--------------------");
			System.out.println("  1 2 3 4 5 6");
			
			char column = 'A';
			for(int i = 0; i < movie.getSeats().length; i++) {
				System.out.print((char)(column + i) + " ");
				for(int j = 0; j < movie.getSeats()[i].length; j++) {
					System.out.print(movie.getSeats()[i][j]);
				}
				System.out.println();
			}
			System.out.println("--------------------");
			System.out.println(">> 좌석 선택");
			
			
			while(true) {
				System.out.print(">> 행을 선택해주세요 (A~F) : ");
				try {
					rowChoice = sc.next().charAt(0);
					if(rowChoice < 'A' || rowChoice >'F')
						System.err.println("A~F중에서 선택해 주세요.");
					else
						break;					
				} catch(InputMismatchException e) {
					System.err.println("A~F중에서 선택해 주세요.");
					continue;
				}
			}
			
			while(true) {
				System.out.print(">> 열을 선택해주세요 (1~6) : ");
				try {
					tempColumn = sc.next();
					if(Integer.parseInt(tempColumn) < 1 || Integer.parseInt(tempColumn) > 6)
						System.err.println("1~6중에서 선택해주세요.");
					else
						columnChoice = Integer.parseInt(tempColumn);
						break;					
				} catch(NumberFormatException e) {
					System.err.println("1~6중에서 선택해주세요.");
					continue;
				}
			}

			String selectSeat = Character.toString(rowChoice) + columnChoice;
			
			if(movie.getSeats()[(int)rowChoice-65][columnChoice-1].equals("◼︎ ")){
				System.err.println(selectSeat + "은 이미 선택된 좌석입니다. 다시 선택해주세요.");
			} 
			else {
				System.out.println(selectSeat + "으로 선택하셨습니다.");
				movie.getSeats()[rowChoice - 65][columnChoice - 1] = "◼︎ ";
				manager.takenSeat(selectSeat);
				break;
			}
		
		}
	
	}

	// 최종확인
	public boolean checking() {
		
		while(true) {
			System.out.print(">> 예매 하시겠습니까 ? (y/n) : ");
			char yn = sc.next().charAt(0);
			if(yn == 'y') {
				System.out.println("예매가 정상처리되었습니다.");
				realIndex++;
				return true;
			} else if(yn == 'n') {
				System.err.println("n을 선택하셨습니다. 예매에 실패했습니다.");
				return false;
			} else {
				System.err.println("잘못 입력하셨습니다. y/n중 입력해주세요.");
			}
		}
	}
	
	public String myBookingMenu() {
		
		String myBookingString = "---- 메뉴 선택 ----\n"
				+ "1. 나의 예매 내역 확인\n"
				+ "2. 예매 내역 취소\n"
				+ "0. 뒤로가기\n"
				+ "--------------------\n"
				+ ">> 메뉴 선택 : ";
		
		System.out.print(myBookingString);
		choiceMyBooking = sc.next();
		return choiceMyBooking;
	}
}