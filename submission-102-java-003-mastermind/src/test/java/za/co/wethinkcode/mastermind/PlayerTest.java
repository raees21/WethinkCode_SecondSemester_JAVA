package za.co.wethinkcode.mastermind;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import za.co.wethinkcode.mastermind.CodeGenerator;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Random;
import java.util.stream.IntStream;

import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.when;

public class PlayerTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

    @Test
    public void testInvalidCodeLength(){
        Player player = new Player(new ByteArrayInputStream("76543\n7658".getBytes()));
        String guess = player.getGuess();
        assertEquals("7658", guess);
    }

    @Test
    public void testInvalidCodeLetters() {
        Player player = new Player(new ByteArrayInputStream("763a\n7658".getBytes()));
        String guess = player.getGuess();
        assertEquals("7658", guess);
    }

    @Test
    public void testInvalidCodeZero() {
        Player player = new Player(new ByteArrayInputStream("7630\n7658".getBytes()));
        String guess = player.getGuess();
        assertEquals("7658", guess);
    }

    @Test
    public void testInvalidCodeNine() {
        Player player = new Player(new ByteArrayInputStream("7639\n7658".getBytes()));
        String guess = player.getGuess();
        assertEquals("7658", guess);
    }

    @Test
    public void testQuitLoop() {
        Player player = new Player(new ByteArrayInputStream("Quit\n7658".getBytes()));
        String guess = player.getGuess();
        assertEquals("Quit", guess);
    }

    @Test
    public void testExitLoop() {
        Player player = new Player(new ByteArrayInputStream("Exit\n7658".getBytes()));
        String guess = player.getGuess();
        assertEquals("Exit", guess);
    }

    @Test
    public void testCorrectDigitsFalse() {
        Player player = new Player();
        boolean guess = player.correctDigits("5678", "5678");
        assertFalse(guess);
    }

    @Test
    public void testCorrectDigitsTrue() {
        Player player = new Player();
        boolean guess = player.correctDigits("5678", "4567");
        assertTrue(guess);
    }

    @Test
    public void testLives() {
        Player player = new Player();
        int guess = player.numOfLives();
        assertEquals(12,guess);
    }

    @Test
    public void testMinusLife() {
        Player player = new Player();
        player.minusLife();
        int guess = player.numOfLives();
        assertEquals(11,guess);
    }

    @Test
    public void testQuit() {
        Player player = new Player();
        boolean guess = player.quitGame();
        assertEquals(false ,guess);
    }


}
