package it.unicam.cs.mp.robotapplication.api.instructions;

import it.unicam.cs.mp.robotapplication.api.Robot;

/**
 * Represents an instruction to continue the robot's movement for a specified duration.
 */
public class ContinueInstruction implements RobotInstruction {

    private final int seconds;

    public ContinueInstruction(int s) {
        this.seconds = s;
    }

    @Override
    public void apply(Robot robot) {
        robot.computeMovement(robot.getLastMovement());
    }

    @Override
    public RobotInstruction cloneInstruction() {
        return new ContinueInstruction(this.seconds);
    }
}
