package movie.booking.program.run;

import javax.swing.JOptionPane;

import movie.booking.program.menu.LoginView;
import movie.booking.program.menu.View;

public class MovieRun {
    public static void main(String[] args) {
        
        while(true) {
            int choice = new LoginView().mainMenu();
            if(choice == 1)
                new View().mainMenu();
            if(choice == 2) 
                continue;
            if(choice == 0) {
                int answer = JOptionPane.showConfirmDialog(null, "종료하시겠습니까?", "confirm",JOptionPane.YES_NO_OPTION );
                if(answer == JOptionPane.YES_OPTION){
                    //사용자가 yes를 눌렀을 떄
                	System.out.println("-------------이용해주셔서 감사합니다.-------------");
                    break;
                } else{
                    //사용자가 Yes 외 값 입력시
                    System.out.println("\t종료를 취소합니다.");
                }
            }
            
        }
        
    }
}
