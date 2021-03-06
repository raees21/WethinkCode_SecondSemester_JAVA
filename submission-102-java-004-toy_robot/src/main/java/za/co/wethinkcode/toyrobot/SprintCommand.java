package za.co.wethinkcode.toyrobot;

public class SprintCommand extends Command{
    @Override
    public boolean execute(Robot target) {
        int nrSteps = Integer.parseInt(getArgument());
        if (target.sprintPosition(nrSteps)){
            target.setStatus("Moved forward by "+nrSteps+" steps.");
        } else {
            target.setStatus("Sorry, I cannot go outside my safe zone.");
        }
        return true;
    }

    public SprintCommand(String argument) {
        super("sprint", argument);
    }
}
