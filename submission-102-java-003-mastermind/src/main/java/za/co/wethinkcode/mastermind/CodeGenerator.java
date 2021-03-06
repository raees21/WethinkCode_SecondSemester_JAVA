package za.co.wethinkcode.mastermind;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CodeGenerator {
    private final Random random;

    public CodeGenerator(){
        this.random = new Random();
    }

    public CodeGenerator(Random random){
        this.random = random;
    }

    /**
     * Generates a random 4 digit code, using this.random, where each digit is in the range 1 to 8 only.
     * Duplicated digits are allowed.
     * @return the generated 4-digit code
     */
    public String generateCode(){
        //TODO: implement using this.random
        int i = 0;

        StringBuilder s = new StringBuilder();

        String temp = "";
        s.append(Integer.toString(this.random.nextInt(8 - 1) + 1));
        while(i < 3){
            temp = Integer.toString(this.random.nextInt(8-1)+1);
            if (!s.toString().contains(temp)){
                s.append(temp);
                i++;
            }
        }
        System.out.print("4-digit Code has been set. Digits in range 1 to 8. You have 12 turns to break it.\n");

        return s.toString();
    }
}
