package movie.booking.program.run;

import movie.booking.program.menu.LoginView;
import movie.booking.program.menu.View;

public class MovieRun {
	public static void main(String[] args) {
		
		new LoginView().mainMenu();
		new View().mainMenu();
		System.out.println("----이용해주셔서 감사합니다.----");
	}
}
