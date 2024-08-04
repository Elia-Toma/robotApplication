package it.unicam.cs.mp.robotapplication.api.instructions;

import it.unicam.cs.mp.robotapplication.api.Robot;

/**
 * This interface is used to represent an instruction that can be applied to a {@link Robot}.
 */
public interface RobotInstruction {

    /**
     * Applies the instruction to the given robot.
     *
     * @param robot the robot to apply the instruction to
     */
    void apply(Robot robot);

    /**
     * Creates a new instance of the object with the same values.
     *
     * @return a copy of the object
     */
    RobotInstruction cloneInstruction();

}
