package it.unicam.cs.mp.robotapplication.api.instructions;

import it.unicam.cs.mp.robotapplication.api.Robot;

/**
 * Represents an instruction that stops the robot.
 */
public class StopInstruction implements RobotInstruction {
    @Override
    public void apply(Robot robot) {
        robot.updateSpeed(0);
    }

    @Override
    public RobotInstruction cloneInstruction() {
        return new StopInstruction();
    }
}
