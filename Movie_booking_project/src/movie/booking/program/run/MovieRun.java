package movie.booking.program.run;

import javax.swing.JOptionPane;

import movie.booking.program.menu.LoginView;
import movie.booking.program.menu.View;

public class MovieRun {
    public static void main(String[] args) {
        
        System.out.println("      πππ μ΄μμ€μΈμ πππ");         
        while(true) {
            int memberNum = new LoginView().mainMenu();
//            if(choice == 1)
//                new View().mainMenu();
            if(memberNum == -1) 
                continue;
            else if(memberNum == 0) {
                int answer = JOptionPane.showConfirmDialog(null, "μ’λ£νμκ² μ΅λκΉ?", "confirm",JOptionPane.YES_NO_OPTION );
                if(answer == JOptionPane.YES_OPTION){
                    //μ¬μ©μκ° yesλ₯Ό λλ μ λ
                    System.out.println("\nμ΄μ©ν΄μ£Όμμ κ°μ¬ν©λλ€.");
                    break;
                } else{
                    //μ¬μ©μκ° Yes μΈ κ° μλ ₯μ
                    System.out.println("\n\tμ’λ£λ₯Ό μ·¨μν©λλ€.");
                }
            }
            else {
                new View().mainMenu(memberNum); 
                break;
            }
        }
        
    }
}