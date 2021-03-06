package za.co.wethinkcode.mastermind;

import java.io.InputStream;
import java.util.Scanner;

public class Player {
    private final Scanner inputScanner;
    private int lives = 12;
    private boolean quit = false;

    public Player(){
        this.inputScanner = new Scanner(System.in);
    }

    public Player(InputStream inputStream){
        this.inputScanner = new Scanner(inputStream);
    }

    /**
     * Gets a guess from user via text console.
     * This must prompt the user to re-enter a guess until a valid 4-digit string is entered, or until the user enters `exit` or `quit`.
     *
     * @return the value entered by the user
     */
    public String getGuess(){

        String[] valid_numbers = {"1", "2", "3", "4", "5", "6", "7", "8"};
        String user_input = "";
        int count_valid;

        while (true){
            System.out.print("Input 4 digit code:");
            count_valid = 0;
            user_input = this.inputScanner.nextLine();
            if(user_input.equalsIgnoreCase("quit") || user_input.equalsIgnoreCase("exit")){
               this.quit = true;
               break;
            }
            for(int i = 0; i < valid_numbers.length; i++){
                for(int j = 0; j < user_input.length();j++){
                    if(valid_numbers[i].charAt(0) == user_input.charAt(j)){
                        count_valid++;
                }
                }
            }
            if(count_valid == 4){
                break;
            }
            else{
                System.out.println("\nPlease enter exactly 4 digits (each from 1 to 8).");
            }

        }
        return user_input;
    }

    public boolean correctDigits(String player_input, String gen_code){

        int correct_digits = 0;
        int correct_digits_and_position = 0;

        for(int i = 0; i < gen_code.length(); i++){
            if(gen_code.charAt(i) == player_input.charAt(i)){
                correct_digits_and_position++;
            }
            else if(gen_code.indexOf(player_input.charAt(i)) != -1){
                correct_digits++;
            }
        }
        System.out.println("\nNumber of correct digits in correct place: "+correct_digits_and_position);
        System.out.println("Number of correct digits not in correct place: "+correct_digits);

        if(correct_digits_and_position == 4) {
            System.out.println("Congratulations! You are a codebreaker!");
            System.out.println("The code was: "+gen_code);
            return false;
        }
        else {
            return true;
        }
    }

    public int numOfLives(){
        return this.lives;
    }

    public void minusLife(){
        this.lives -= 1;
    }

    public boolean quitGame(){
        return this.quit;
        }

}
