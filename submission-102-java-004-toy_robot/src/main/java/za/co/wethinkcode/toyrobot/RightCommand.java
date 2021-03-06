package za.co.wethinkcode.toyrobot;

public class RightCommand extends Command{
    @Override
    public boolean execute(Robot target) {
        String command = getArgument();
        if (target.updateDirection(command)){
            target.setStatus("Turned right.");
        } else {
            target.setStatus("Sorry, I cannot go outside my safe zone.");
        }
        return true;
    }

    public RightCommand(String argument) {
        super("right");
    }
}
