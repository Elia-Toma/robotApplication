package it.unicam.cs.mp.robotapplication.api.instructions;

import it.unicam.cs.mp.robotapplication.api.Robot;

/**
 * Represents an instruction that executes a sequence of instructions indefinitely.
 */
public class DoForeverInstruction implements RobotInstruction {

    @Override
    public void apply(Robot robot) {
        robot.setLoopStatus(true);
        robot.setCurrentLoop(this);
    }

    @Override
    public RobotInstruction cloneInstruction() {
        return new DoForeverInstruction();
    }
}
