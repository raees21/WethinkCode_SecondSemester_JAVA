package za.co.wethinkcode.examples.hangman;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Hangman {

    public static void main(String[] args) throws IOException {
        Random random = new Random();
        Player player = new Player();

        String fileName = player.getWordsFile();

        List<String> words = Files.readAllLines(Path.of(fileName));

        int randomIndex = random.nextInt(words.size());
        String randomWord = words.get(randomIndex).trim();
        Answer wordToGuess = new Answer(randomWord);

        Answer currentAnswer = wordToGuess.generateRandomHint();
        System.out.println("Guess the word: " + currentAnswer);

        while (!currentAnswer.equals(wordToGuess)) {
            String guess = player.getGuess();
            if (player.wantsToQuit()) {
                System.out.println("Bye!");
                break;
            }

            char guessedLetter = guess.charAt(0);
            if (wordToGuess.hasLetter(guessedLetter)
                    && !currentAnswer.hasLetter(guessedLetter)) {
                currentAnswer = wordToGuess.getHint(currentAnswer, guess.charAt(0));
                System.out.println(currentAnswer);
            } else {
                player.lostChance();
                System.out.println("Wrong! Number of guesses left: " + player.getChances());
                if (player.hasNoChances()) {
                    System.out.println("Sorry, you are out of guesses. The word was: " + wordToGuess);
                    break;
                }
            }
        }
        if (currentAnswer.equals(wordToGuess)) {
            System.out.println("That is correct! You escaped the noose .. this time.");
        }
    }

}
