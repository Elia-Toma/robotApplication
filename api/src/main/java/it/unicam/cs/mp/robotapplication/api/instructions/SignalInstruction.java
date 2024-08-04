package it.unicam.cs.mp.robotapplication.api.instructions;

import it.unicam.cs.mp.robotapplication.api.Robot;
import it.unicam.cs.mp.robotapplication.api.model.Condition;

/**
 * Represents ad instruction that makes a robot signal a condition.
 */
public class SignalInstruction implements RobotInstruction {

    private final String label;

    public SignalInstruction(String label) {
        this.label = label;
    }

    @Override
    public void apply(Robot robot) {
        robot.updateCondition(new Condition(label));
    }

    @Override
    public RobotInstruction cloneInstruction() {
        return new SignalInstruction(this.label);
    }
}
