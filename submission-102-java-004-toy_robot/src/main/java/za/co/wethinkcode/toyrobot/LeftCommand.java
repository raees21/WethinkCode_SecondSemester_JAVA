package za.co.wethinkcode.toyrobot;

public class LeftCommand extends Command{
    @Override
    public boolean execute(Robot target) {
        String command = getArgument();
        if (target.updateDirection(command)){
            target.setStatus("Turned left.");
        } else {
            target.setStatus("Sorry, I cannot go outside my safe zone.");
        }
        return true;
    }

    public LeftCommand(String argument) {
        super("left");
    }
}
