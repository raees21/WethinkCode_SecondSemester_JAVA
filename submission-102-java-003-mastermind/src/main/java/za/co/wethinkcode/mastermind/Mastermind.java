package za.co.wethinkcode.mastermind;

public class Mastermind {
    private final String code;
    private final Player player;

    public Mastermind(CodeGenerator generator, Player player){
        this.code = generator.generateCode();
        this.player = player;
    }

    public Mastermind(){
        this(new CodeGenerator(), new Player());
    }


    public void runGame(){
        //TODO: implement the main run loop logic
        boolean get_digits = true;
        String player_input;

        while(this.player.numOfLives() != 0) {
            player_input = this.player.getGuess();
            if (this.player.quitGame()){
                break;
            }
            get_digits = this.player.correctDigits(player_input, this.code);
            if(!get_digits){
                break;
            }
            this.player.minusLife();
            if(this.player.numOfLives() != 0) {
                System.out.println("Turns left: " + this.player.numOfLives());
            }
            else {
                System.out.println("No more turns left.");
                System.out.println("The code was: "+ this.code);
                break;
            }
        }
    }

    public static void main(String[] args){
        Mastermind game = new Mastermind();
        game.runGame();
    }
}
