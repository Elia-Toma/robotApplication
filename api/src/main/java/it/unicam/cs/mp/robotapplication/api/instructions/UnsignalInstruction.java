package it.unicam.cs.mp.robotapplication.api.instructions;

import it.unicam.cs.mp.robotapplication.api.Robot;
import it.unicam.cs.mp.robotapplication.api.model.Condition;

/**
 * Represents an instruction that makes a robot stop signaling a condition.
 */
public class UnsignalInstruction implements RobotInstruction {

    private final String label;

    public UnsignalInstruction(String label) {
        this.label = label;
    }

    @Override
    public void apply(Robot robot) {
        if (robot.getCondition().getLabel().equals(this.label)) robot.updateCondition(new Condition("null"));
    }

    @Override
    public RobotInstruction cloneInstruction() {
        return new UnsignalInstruction(this.label);
    }
}
