package movie.booking.program.menu;
/**
 * 	로그인 Menu (보류)
 * 	메인 Menu
 * 	영화 스케쥴 Menu - 극장별 ..
 * 	예매 내역 확인서
 *	예매 취소
 */

import java.io.File;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import javax.swing.JOptionPane;

import movie.booking.program.controller.FileUtil;
import movie.booking.program.controller.MovieManager;
import movie.booking.program.vo.Movie;
import movie.booking.program.vo.Seats;

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
	
	
	private String mainString = "\n======= 🎬Movie Booking Menu🎬 =======\n\n"
							  + "\t1. 현재 상영 영화 스케쥴 출력\n"
							  + "\t2. 영화별 예매하기\n"
							  + "\t3. 극장별 예매하기\n"
							  + "\t4. 나의 예매내역\n"
							  + "\t9. 종료\n\n"
							  + "=========================================\n"
							  + "\t➜ 메뉴 선택 : ";
	

	Seats s = new Seats();
	FileUtil fileUtil = new FileUtil();
	
	public void mainMenu(int memberNum) {

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
					seatsMenu(s, selectedMovie);
				}

				if(checking()) {
					manager.movieFile(memberNum);
					manager.nowBookingPrint(memberNum);   
					
					fileUtil.finalwriteSeat(selectedMovie, s);
					
				} else {
					manager.deleteBooking(realIndex);
					s.getSeats()[rowChoice - 65][columnChoice - 1] = "︎☐ ";
				}
				break;

			case "3" : 
				selectedMovie = manager.bookingByTheater(theaterMenu());
				if(selectedMovie == null) {
					break;
				}
				else{
					seatsMenu(s, selectedMovie);
				}

				if(checking()) {
					manager.movieFile(memberNum);
					manager.nowBookingPrint(memberNum);
					
					fileUtil.finalwriteSeat(selectedMovie, s);
					
				} else {
					manager.deleteBooking(realIndex);
					s.getSeats()[rowChoice - 65][columnChoice - 1] = "︎☐ ";
				}
				break;

			case "4" : 
				if(manager.myBooking(myBookingMenu(), memberNum, s, selectedMovie)) {
					break;
				}
				//삭제했을 때 realIndex--; 되어야 함!!
				else {
					realIndex--;
					break;
				}

			case "9" : 
				int answer = JOptionPane.showConfirmDialog(null, "종료하시겠습니까?", "confirm",JOptionPane.YES_NO_OPTION );
				if(answer == JOptionPane.YES_OPTION){
					//사용자가 yes를 눌렀을 떄
					System.out.println("이용해주셔서 감사합니다.");
					return;
				} else{
					//사용자가 Yes 외 값 입력시
					System.out.println("종료를 취소합니다.");
				}

				// 로그아웃
//			case "0" : 
//				return;

			default : 
				JOptionPane.showMessageDialog(null, "선택지에 있는 번호만 입력해주세요",
						"입력 오류", JOptionPane.WARNING_MESSAGE); 
				continue;
			}
		}

	}
	
	
	//영화선택
	public String movieMenu() {
		
		String movieString = "\n------------ 📽 Movie List ------------\n\n"
						   + "\t1. 더 배트맨\n"
						   + "\t2. 나이트메어 앨리\n"
						   + "\t3. 나이트 레이더스\n"
						   + "\t4. 안터벨룸\n"
						   + "\t0. 뒤로가기\n\n"
						   + "----------------------------------------\n"
						   + "\t➜ 영화 선택 : ";
		System.out.print(movieString);
		choiceMovie = sc.next();
		System.out.println();
		return choiceMovie;
		
	}
	//극장선택
	public String theaterMenu() {
		
		String theaterString = "\n----------- 📽 Theater List --------------\n"
							 + " 1. 용산점   2. 홍대점   3. 강남점   0. 뒤로가기 \n " 
							 + "-----------------------------------------\n"
							 + "\t➜ 극장 선택 : ";
		
		System.out.print(theaterString);
		choiceTheater = sc.next();
		System.out.println();
		return choiceTheater;
	}


	//좌석선택
	public void seatsMenu(Seats s, Movie movie) {
		
		while(true) {
			//좌석 출력
			System.out.println("==================================");
			System.out.println("\t 1 2 3 4 5 6");
			try {
				File file = new File("/Users/camilee/Documents/dev/",
						"seats" + movie.getRoom() + ".txt");
				//배열에 좌석 담기 Seats#seats
				FileUtil.readSeat(file, s);
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			System.out.println("==================================\n");
			System.out.println("  ✔좌석 선택✔");
			
			
			while(true) {
				System.out.print("➜ 열을 선택해주세요 (A~F) : ");
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
				System.out.print("➜ 좌석을 선택해주세요 (1~6) : ");
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
			
			if(s.getSeats()[(int)rowChoice-65][columnChoice-1].equals("◼︎")){
				System.err.println(selectSeat + "은 이미 선택된 좌석입니다. 다시 선택해주세요.");
				System.out.println();
			} 
			else {
				System.out.print("✅" + selectSeat + "으로 선택하셨습니다.");
				System.out.println();
				s.getSeats()[rowChoice - 65][columnChoice - 1] = "◼︎";
				manager.takenSeat(selectSeat);
				break;
			}
		
		}
	
	}

	// 최종확인
	   public boolean checking() {
		   while(true) {
			   System.out.print("\n➜ 예매 하시겠습니까 ? (y/n) : ");
			   char yn = sc.next().charAt(0);
			   if(yn == 'y') {
				   System.out.print("\n* * * * * * * * * * * * * * * * * *\n"
						   + " 🎉🎉 예매가 정상처리되었습니다!! 🎉🎉 \n"
						   + "* * * * * * * * * * * * * * * * * *\n");
				   realIndex++;
				   return true;
			   } else if(yn == 'n') {
				   System.err.println("n을 선택하셨습니다. 예매에 실패했습니다.\n");
				   return false;
			   } else {
				   System.err.println("잘못 입력하셨습니다. y/n중 입력해주세요.");
			   }
		   }
		   }
	
	public String myBookingMenu() {
		
		String myBookingString = "\n-------------- 나의 예매 내역 --------------\n"
								+ "\t  1. 나의 예매 내역 확인\n"
								+ "\t  2. 예매 내역 취소\n"
								+ "\t  0. 뒤로가기\n"
								+ "--------------------------------------------\n"
								+ "\t  ➜ 메뉴 선택 : ";
		
		System.out.print(myBookingString);
		choiceMyBooking = sc.next();
		return choiceMyBooking;
	}
}
