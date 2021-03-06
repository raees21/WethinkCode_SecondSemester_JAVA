package za.co.wethinkcode.mastermind;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import za.co.wethinkcode.mastermind.CodeGenerator;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Random;
import java.util.stream.IntStream;

import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.when;


class CodeGeneratorTest {

    @Test
    void testLengthOfCode() {
        Random randomNumberMock = Mockito.mock(Random.class);
        when(randomNumberMock.nextInt(anyInt()))
                .thenReturn(0, 1, 2, 3);

        CodeGenerator generator = new CodeGenerator(randomNumberMock);

        assertEquals(4, generator.generateCode().length());
    }

    @Test
    void testCodeGeneration() {
        Random randomNumberMock = Mockito.mock(Random.class);
        when(randomNumberMock.nextInt(anyInt()))
                .thenReturn(1, 2, 3, 4);

        CodeGenerator generator = new CodeGenerator(randomNumberMock);

        assertEquals("2345", generator.generateCode());
    }

    @Test
    void testGenCodeNoZeroAndNine(){
        CodeGenerator generator = new CodeGenerator();
        boolean test_value = true;
        for(int i = 0; i < 100; i++){
            String code = generator.generateCode();
            for(int j = 0; j < code.length(); j++) {
                if (code.charAt(j) == '0' || code.charAt(j) == '9') {
                    test_value = false;
                }
            }
        }
        assertTrue(test_value);
    }
}
