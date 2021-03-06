package za.co.wethinkcode.mastermind;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import za.co.wethinkcode.mastermind.CodeGenerator;
import za.co.wethinkcode.mastermind.Mastermind;
import za.co.wethinkcode.mastermind.Player;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

public class MastermindTest {
    private final PrintStream standardOut = System.out;
    private final InputStream standardIn = System.in;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
        System.setIn(standardIn);
    }

    @Test
    void testQuitGame() {
        Random randomNumberMock = Mockito.mock(Random.class);
        when(randomNumberMock.nextInt(anyInt()))
                .thenReturn(1, 2, 3, 4);

        InputStream mockedInput = new ByteArrayInputStream("quit\n".getBytes());
        Mastermind mastermind = new Mastermind(new CodeGenerator(randomNumberMock), new Player(mockedInput));
        mastermind.runGame();
        assertEquals("4-digit Code has been set. Digits in range 1 to 8. You have 12 turns to break it.\n"+
                "Input 4 digit code:", outputStreamCaptor.toString().trim());
    }

    @Test
    void testWinGame() {
        Random randomNumberMock = Mockito.mock(Random.class);
        when(randomNumberMock.nextInt(anyInt()))
                .thenReturn(1, 2, 3, 4);

        InputStream mockedInput = new ByteArrayInputStream("2345\n".getBytes());
        Mastermind mastermind = new Mastermind(new CodeGenerator(randomNumberMock), new Player(mockedInput));
        mastermind.runGame();

        assertEquals("4-digit Code has been set. Digits in range 1 to 8. You have 12 turns to break it.\n" +
                "Input 4 digit code:\n" +
                "Number of correct digits in correct place: 4\n" +
                "Number of correct digits not in correct place: 0\n" +
                "Congratulations! You are a codebreaker!\n" +
                "The code was: 2345", outputStreamCaptor.toString().trim());
    }

    @Test
    void testDigitsCorrect() {
        Random randomNumberMock = Mockito.mock(Random.class);
        when(randomNumberMock.nextInt(anyInt()))
                .thenReturn(1, 2, 3, 4);

        InputStream mockedInput = new ByteArrayInputStream("8761\n4567\n2367\n2354\nquit\n".getBytes());
        Mastermind mastermind = new Mastermind(new CodeGenerator(randomNumberMock), new Player(mockedInput));
        mastermind.runGame();

        assertEquals("4-digit Code has been set. Digits in range 1 to 8. You have 12 turns to break it.\n" +
                "Input 4 digit code:\n" +
                "Number of correct digits in correct place: 0\n" +
                "Number of correct digits not in correct place: 0\n" +
                "Turns left: 11\n" +
                "Input 4 digit code:\n" +
                "Number of correct digits in correct place: 0\n" +
                "Number of correct digits not in correct place: 2\n" +
                "Turns left: 10\n" +
                "Input 4 digit code:\n" +
                "Number of correct digits in correct place: 2\n" +
                "Number of correct digits not in correct place: 0\n" +
                "Turns left: 9\n" +
                "Input 4 digit code:\n" +
                "Number of correct digits in correct place: 2\n" +
                "Number of correct digits not in correct place: 2\n" +
                "Turns left: 8\n"+
                "Input 4 digit code:", outputStreamCaptor.toString().trim());
    }
}
