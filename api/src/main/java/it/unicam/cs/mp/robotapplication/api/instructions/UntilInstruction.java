package it.unicam.cs.mp.robotapplication.api.instructions;

import it.unicam.cs.mp.robotapplication.api.Robot;

/**
 * Represents an instruction that executes a sequence of instructions repeatedly until a condition is met.
 */
public class UntilInstruction implements RobotInstruction {

    private final String label;

    public UntilInstruction(String label) {
        this.label = label;
    }

    @Override
    public void apply(Robot robot) {
        robot.setLoopStatus(true);
        robot.setCurrentLoop(this);
    }

    @Override
    public RobotInstruction cloneInstruction() {
        return new UntilInstruction(this.label);
    }

    public String getLabel() {
        return label;
    }
}
