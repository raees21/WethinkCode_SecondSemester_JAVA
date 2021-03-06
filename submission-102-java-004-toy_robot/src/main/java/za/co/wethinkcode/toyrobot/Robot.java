package za.co.wethinkcode.toyrobot;

import java.util.Arrays;
import java.util.List;

public class Robot {
    private final Position TOP_LEFT = new Position(-200,100);
    private final Position BOTTOM_RIGHT = new Position(100,-200);

    public static final Position CENTRE = new Position(0,0);

    private Position position;
    private Direction currentDirection;
    private String status;
    private String name;

    public Robot(String name) {
        this.name = name;
        this.status = "Ready";
        this.position = CENTRE;
        this.currentDirection = Direction.NORTH;
    }

    public String getStatus() {
        return this.status;
    }

    public Direction getCurrentDirection() {
        return this.currentDirection;
    }

    public boolean handleCommand(Command command) {
        return command.execute(this);
    }

    public boolean updatePosition(int nrSteps){
        int newX = this.position.getX();
        int newY = this.position.getY();

        if (Direction.NORTH.equals(this.currentDirection)) {
            newY = newY + nrSteps;
        }

        if(Direction.SOUTH.equals(this.currentDirection)){
            newY = newY - nrSteps;
        }

        if (Direction.WEST.equals(this.currentDirection)) {
            newX = newX + nrSteps;
        }

        if (Direction.EAST.equals(this.currentDirection)) {
            newX = newX - nrSteps;
        }


        Position newPosition = new Position(newX, newY);
        if (newPosition.isIn(TOP_LEFT,BOTTOM_RIGHT)){
            this.position = newPosition;
            return true;
        }
        return false;
    }

    public boolean updateDirection(String command) {
        if (Direction.NORTH.equals(this.currentDirection)) {
            if (command.equalsIgnoreCase("left")) {
                this.currentDirection = Direction.EAST;
            }
            if (command.equalsIgnoreCase("right")) {
                this.currentDirection = Direction.WEST;
            }
        }

        if (Direction.WEST.equals(this.currentDirection)) {
            if (command.equalsIgnoreCase("left")) {
                this.currentDirection = Direction.NORTH;
            }
            if (command.equalsIgnoreCase("right")) {
                this.currentDirection = Direction.SOUTH;
            }
        }

        if (Direction.SOUTH.equals(this.currentDirection)) {
            if (command.equalsIgnoreCase("left")) {
                this.currentDirection = Direction.WEST;
            }
            if (command.equalsIgnoreCase("right")) {
                this.currentDirection = Direction.EAST;
            }
        }

        if (Direction.EAST.equals(this.currentDirection)) {
            if (command.equalsIgnoreCase("left")) {
                this.currentDirection = Direction.SOUTH;
            }
            if (command.equalsIgnoreCase("right")) {
                this.currentDirection = Direction.NORTH;
            }
        }
        return true;
    }

    public Boolean sprintPosition(int nrSteps) {
        if (nrSteps < 1) {
            return true;
        }
        if (nrSteps >= 1){
            new SprintCommand(Integer.toString(nrSteps-1));
        }
        return true;
    }

    @Override
    public String toString() {
       return "[" + this.position.getX() + "," + this.position.getY() + "] "
               + this.name + "> " + this.status;
    }

    public Position getPosition() {
        return this.position;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }
}