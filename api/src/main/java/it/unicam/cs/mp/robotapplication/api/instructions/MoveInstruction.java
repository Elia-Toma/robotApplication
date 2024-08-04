package it.unicam.cs.mp.robotapplication.api.instructions;

import it.unicam.cs.mp.robotapplication.api.Robot;
import it.unicam.cs.mp.robotapplication.api.model.Coordinates;

/**
 * Represents an instruction that instructs a robot to move towards a specified direction.
 */
public class MoveInstruction implements RobotInstruction {

    private final double x;
    private final double y;
    private final double speed;

    public MoveInstruction(double[] args) throws IllegalArgumentException {
        if (args[0] != 0 && args[1] != 0)
            throw new IllegalArgumentException("Al più una tra x ed y in MOVE deve essere diversa da 0.");
        if (args[0] > 1 || args[0] < -1 || args[1] > 1 || args[1] < -1)
            throw new IllegalArgumentException("x ed y in MOVE devono essere comprese tra -1 e 1.");

        this.x = args[0];
        this.y = args[1];
        this.speed = args[2];
    }

    public MoveInstruction(double x, double y, double speed) {
        this.x = x;
        this.y = y;
        this.speed = speed;
    }

    @Override
    public void apply(Robot robot) {
        robot.updateDirection(new Coordinates(x, y));
        robot.updateSpeed(speed);

        robot.computeMovement(this);
    }

    @Override
    public RobotInstruction cloneInstruction() {
        double[] args = {this.x, this.y, this.speed};
        return new MoveInstruction(args);
    }
}
