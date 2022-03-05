package movie.booking.program.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import movie.booking.program.vo.Movie;

/**
 * 	Menu에서 선택 시 처리과정
 *
 */
public class MovieManager {
	private Scanner sc = new Scanner(System.in);
	
	private List<Movie> yongSan = new ArrayList<>();
	private List<Movie> hongDae = new ArrayList<>();
	private List<Movie> gangNam = new ArrayList<>();
	
	List<Movie> temp = new ArrayList<>();
	List<String> selectSeat = new ArrayList<>();
	List<Movie> completeMovieList = new ArrayList<>(); 
    int movieIndex = 0;
    int seatIndex = 0;
	
	public MovieManager() {
		yongSan.add(new Movie("더 배트맨", "용산점", 13, 15, "10:35"));
		yongSan.add(new Movie("나이트메어 앨리", "용산점", 2, 15, "13:55"));
		yongSan.add(new Movie("나이트 레이더스", "용산점", 17, 15, "12:15"));
		
		hongDae.add(new Movie("더 배트맨", "홍대점", 4, 15, "11:45"));
		hongDae.add(new Movie("나이트메어 앨리", "홍대점", 6, 15, "11:55"));
		hongDae.add(new Movie("안터벨룸", "홍대점", 3, 15, "21:50"));
		
		gangNam.add(new Movie("더 배트맨", "강남점", 1, 15, "14:50"));
		gangNam.add(new Movie("나이트메어 앨리", "강남점", 6, 15, "21:10"));
		gangNam.add(new Movie("안터벨룸", "강남점", 1, 15, "21:40"));
		
	}

	public MovieManager(List<Movie> yongSan, List<Movie> hongDae, List<Movie> gangNam) {
		this.yongSan = yongSan;
		this.hongDae = hongDae;
		this.gangNam = gangNam;
	}
	
	/**
	 * main 1. 극장별 현재 상영 영화 스케쥴 출력
	 */
	public void printSchedule(int choiceTheater) {
			int j;
			
			switch(choiceTheater) {
			case 1 : 
				j = 1;
				for(int i = 0; i < yongSan.size(); i++) {
					System.out.println(j++ + ". " +yongSan.get(i));
				}
				break;
			case 2 : 
				j = 1;
				for(int i = 0; i < hongDae.size(); i++) {
					System.out.println(j++ + ". " +hongDae.get(i));
				}
				break;
			case 3 :
				j = 1;
				for(int i = 0; i < gangNam.size(); i++) {
					System.out.println(j++ + ". " +gangNam.get(i));
				}
				break;
			case 0 : return;
			default : System.out.println("잘못 입력하셨습니다.");
			}
	}

	/**
	 * main menu 2. 영화별 예매하기
	 */
	public Movie bookingByMovie(int choiceMovie) {
		System.out.println("----- 극장 선택 -----");
		int j;
		
		switch(choiceMovie) {
		
		case 1 :
			j = 1;
			for(int i = 0; i < yongSan.size(); i++) {
				if(yongSan.get(i).getMovieName().equals("더 배트맨")) {
					System.out.println(j++ + ". " + yongSan.get(i));
					temp.add(yongSan.get(i));
				}
				if(hongDae.get(i).getMovieName().equals("더 배트맨")) {
					System.out.println(j++ + ". " + hongDae.get(i));
					temp.add(hongDae.get(i));
				}
				if(gangNam.get(i).getMovieName().equals("더 배트맨")) {
					System.out.println(j++ + ". " + gangNam.get(i));
					temp.add(gangNam.get(i));
				}
			}
			
			break;
			
		case 2 :
			j = 1;
			for(int i = 0; i< yongSan.size(); i++) {
				if(yongSan.get(i).getMovieName().equals("나이트메어 앨리")) {
					System.out.println(j++ + ". " + yongSan.get(i));
					temp.add(yongSan.get(i));
				}
				if(hongDae.get(i).getMovieName().equals("나이트메어 앨리")) {
					System.out.println(j++ + ". " + hongDae.get(i));
					temp.add(hongDae.get(i));
				}
				if(gangNam.get(i).getMovieName().equals("나이트메어 앨리")) {
					System.out.println(j++ + ". " + gangNam.get(i));
					temp.add(gangNam.get(i));

				}
			}
			break;
			
		case 3 :
			j = 1;
			for(int i = 0; i< yongSan.size(); i++) {
				if(yongSan.get(i).getMovieName().equals("나이트 레이더스")) {
					System.out.println(j++ + ". " + yongSan.get(i));
					temp.add(yongSan.get(i));
				}
				if(hongDae.get(i).getMovieName().equals("나이트 레이더스")) {
					System.out.println(j++ + ". " + hongDae.get(i));
					temp.add(hongDae.get(i));
				}
				if(gangNam.get(i).getMovieName().equals("나이트 레이더스")) {
					System.out.println(j++ + ". " + gangNam.get(i));
					temp.add(gangNam.get(i));

				}
			}
			break;
			
		case 4 :
			j = 1;
			for(int i = 0; i< yongSan.size(); i++) {
				if(yongSan.get(i).getMovieName().equals("안터벨룸")) {
					System.out.println(j++ + ". " + yongSan.get(i));
					temp.add(yongSan.get(i));
				}
				if(hongDae.get(i).getMovieName().equals("안터벨룸")) {
					System.out.println(j++ + ". " + hongDae.get(i));
					temp.add(hongDae.get(i));
				}
				if(gangNam.get(i).getMovieName().equals("안터벨룸")) {
					System.out.println(j++ + ". " + gangNam.get(i));
					temp.add(gangNam.get(i));
				}
			}
			break;
			
		case 0 : return null;
		default:
			System.out.println("잘못 입력하셨습니다.");
		}
		afterMovieChoice();
		return completeMovieList.get(movieIndex++);
	}
	
	// 2. 영화 선택 후 극장 선택 
	public void afterMovieChoice() {
		
		outer :
		while(true) {
			System.out.print(">> 극장을 선택해 주세요 : ");
			int lastTheaterChoice = sc.nextInt();
			if(lastTheaterChoice <= temp.size()) {
				switch(lastTheaterChoice) {
				case 1 :
					completeMovieList.add(temp.get(0));
					System.out.println(completeMovieList.get(movieIndex));
					temp.clear();
					break outer;
				case 2 :
					completeMovieList.add(temp.get(1));
					System.out.println(completeMovieList.get(movieIndex));
					temp.clear();
					break outer;
				case 3 :
					completeMovieList.add(temp.get(2));
					System.out.println(completeMovieList.get(movieIndex));
					temp.clear();
					break outer;
				default : 
					System.out.println("잘못 입력하셨습니다.");
					continue outer;
				}
			} else {
				System.out.println("잘못된 입력입니다.");
				continue outer;
			}
		}
	}
	
	/**
	 *  main menu 3. 극장별 예매하기
	 */
	public Movie bookingByTheater(int choiceTheater) {

		int j;
		switch(choiceTheater) {
		//용산
		case 1 : 
			j = 1;
			for(int i = 0; i < yongSan.size(); i++) {
				System.out.println(j++ + ". " +yongSan.get(i));
				temp.add(yongSan.get(i));
			}
			break;

		//홍대
		case 2 : 
			j = 1;
			for(int i = 0; i < hongDae.size(); i++) {
				System.out.println(j++ + ". " +hongDae.get(i));
				temp.add(hongDae.get(i));
			}
			break;
			
		//강남
		case 3 :
			j = 1;
			for(int i = 0; i < gangNam.size(); i++) {
				System.out.println(j++ + ". " +gangNam.get(i));
				temp.add(gangNam.get(i));
			}
			break;
		case 0 : return null;
		}
		
			
		afterTheaterChoice();
		return completeMovieList.get(movieIndex++);

	}
	
	//3. 극장선택 후 영화 선택 
	public Movie afterTheaterChoice() {
		
		outer : 
		while(true) {
			System.out.print(">> 영화를 선택해 주세요 : ");
			int lastMovieChoice = sc.nextInt();
			if(lastMovieChoice <= temp.size()) {
				switch(lastMovieChoice){
				case 1 :
					completeMovieList.add(temp.get(0));
					System.out.println(completeMovieList.get(movieIndex));
					break;
				case 2 :
					completeMovieList.add(temp.get(1));
					System.out.println(completeMovieList.get(movieIndex));
					break;
				case 3 :
					completeMovieList.add(temp.get(2));
					System.out.println(completeMovieList.get(movieIndex));
					break;
				default : System.out.println("잘못 입력하셨습니다.");
					continue outer;
				}
			} else {
				System.out.println("잘못된 입력입니다.");
				continue outer;
			}
			break;
		}
		return completeMovieList.get(movieIndex);
	}
	
	// main 2, 3번 출력용
	public void nowBookingPrint() {
		
		System.out.println("********예매 내역*********");
        for(int i = 0; i < completeMovieList.size(); i++) {
            System.out.println(completeMovieList.get(i));
            System.out.println("좌석 : " + selectSeat.get(i));
        }
		
	}

	public void takenSeat(String selectedSeat) {
		selectSeat.add(selectedSeat);
		seatIndex++;
	}
	//0 A2
	//1 A3
	//2 A4 -> n
	//3
	public void deleteBooking(int realIndex) {
		selectSeat.remove(realIndex);
		seatIndex--;
		completeMovieList.remove(realIndex);
		movieIndex--;
	}
	
	/**
	 * main 4. 나의 예매내역
	 */
	public void myBooking(int choiceMyBooking) {
		outer:
		while(true) {
			switch(choiceMyBooking) {
			case 1:
				if(completeMovieList.isEmpty()) {
					System.out.println("예매 내역이 없습니다.");
					break outer;
				}
				else{
					nowBookingPrint();
					break outer;
				}
			case 2:
				if(completeMovieList.isEmpty()) {
					System.out.println("예매 내역이 없습니다.");
					break outer;
				}
				else{
					nowBookingPrint();
					//삭제할 내역을 선택하세요
					System.out.println("--------------------");
					System.out.print(">> 삭제할 내역을 선택하세요 : ");
					int choiceRemove = sc.nextInt();
					
					//삭제진행
					Movie deleteMovie = completeMovieList.get(choiceRemove-1);
                    char rowSeat = selectSeat.get(choiceRemove-1).charAt(0);
                    int colSeat = Character.getNumericValue(selectSeat.get(choiceRemove-1).charAt(1));
                    deleteMovie.getSeats()[rowSeat - 65][colSeat - 1] = "︎☐ ";
					completeMovieList.remove(choiceRemove - 1);
					selectSeat.remove(choiceRemove-1);
					movieIndex--;
					
					
					break outer;
				}
			case 0: return;
			default : System.out.println("잘못 입력하셨습니다.");
			}
		}
	}
	
	
}