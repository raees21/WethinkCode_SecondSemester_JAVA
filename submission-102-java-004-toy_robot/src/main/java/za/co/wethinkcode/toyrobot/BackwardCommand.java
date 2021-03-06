package za.co.wethinkcode.toyrobot;

public class BackwardCommand extends Command{
    @Override
    public boolean execute(Robot target) {
        int nrSteps = Integer.parseInt(getArgument());
        nrSteps = nrSteps *= -1;

        if (target.updatePosition(nrSteps)){
            target.setStatus("Moved backward by "+nrSteps+" steps.");
        } else {
            target.setStatus("Sorry, I cannot go outside my safe zone.");
        }
        return true;
    }

    public BackwardCommand(String argument) {
        super("backward", argument);
    }
}
