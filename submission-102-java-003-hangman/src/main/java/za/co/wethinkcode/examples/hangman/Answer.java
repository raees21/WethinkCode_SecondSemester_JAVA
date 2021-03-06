package za.co.wethinkcode.examples.hangman;

import java.util.Random;

public class Answer {
    private static final Random RANDOM = new Random();
    private final String value;
    private Answer solution;

    public Answer(String value, Answer solution){
        this.value = value;
        this.solution = solution;
    }

    public String toString() {
        return this.value;
    }

    public Answer(String value){
        this.value = value;
    }

    public boolean equals(Object obj) {
        Answer otherAnswer = (Answer) obj;
        return this.value.equalsIgnoreCase(otherAnswer.toString());
    }

    public boolean isGoodGuess(Answer wordToGuess, char letter) {
        return wordToGuess.hasLetter(letter) && !this.hasLetter(letter);
    }

    public Answer getHint(Answer guessedAnswer, char guessedLetter){

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < this.value.length(); i++) {
            if (guessedLetter == this.value.charAt(i)) {
                result.append(guessedLetter);
            } else {
                result.append(guessedAnswer.toString().charAt(i));
            }
        }
        return new Answer(result.toString());
    }

    public Answer generateRandomHint(){
        Random random = new Random();
        int randomIndex = random.nextInt(this.value.length() - 1);

        String noLetters = "_".repeat(this.value.length());
        return this.getHint( new Answer(noLetters),
                this.value.charAt(randomIndex));
    }

    public boolean hasLetter(char letter) {
        return this.value.indexOf(letter) >= 0;
    }
}
