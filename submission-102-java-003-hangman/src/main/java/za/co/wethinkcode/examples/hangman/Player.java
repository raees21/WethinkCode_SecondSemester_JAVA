package za.co.wethinkcode.examples.hangman;

import java.io.InputStream;
import java.util.Scanner;

public class Player {
    private Scanner inputScanner = new Scanner(System.in);;
    //declared as private so that it is not visible outside this class
    private int chances = 5;
    private boolean quit = false;

    public Player(InputStream in) {
        this.inputScanner = new Scanner(in);
    }

    public Player() {
        this.inputScanner = new Scanner(System.in);
    }

    public String getWordsFile(){
        System.out.println("Words file? [leave empty to use short_words.txt]");
        String fileName = inputScanner.nextLine();
        return fileName.isBlank() ? "short_words.txt" : fileName;
    }

    public String getGuess() {
        String text = inputScanner.nextLine();
        this.quit = text.equalsIgnoreCase("quit") || text.equalsIgnoreCase("exit");
        return text;
    }

    //declared as public as this method will need to be visible and called from other classes
    public int getChances() {
        return chances;
    }

    public void lostChance() {
        if (!this.hasNoChances()) {
            this.chances--;
        }
    }

    public boolean hasNoChances() {
        return this.getChances() == 0;
    }

    public boolean wantsToQuit(){
        return this.quit;
    }

}