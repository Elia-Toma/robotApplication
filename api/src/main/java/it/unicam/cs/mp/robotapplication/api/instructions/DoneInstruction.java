package it.unicam.cs.mp.robotapplication.api.instructions;

import it.unicam.cs.mp.robotapplication.api.Robot;

/**
 * Represents an instruction that indicates the end of a loop block.
 */
public class DoneInstruction implements RobotInstruction {
    @Override
    public void apply(Robot robot) {
        RobotInstruction cycle = robot.getCurrentLoop();

        if (cycle instanceof RepeatInstruction) {
            RepeatInstruction repeat = (RepeatInstruction) cycle;
            if (repeat.getDoneIterations() == repeat.getIterations()) {
                robot.getProgram().exitCycle(robot);
                return;
            }
            robot.getCurrentLoop().apply(robot);
        } else if (cycle instanceof UntilInstruction) {
            UntilInstruction until = (UntilInstruction) cycle;
            if (until.getLabel().equals(robot.getCondition().getLabel())) {
                robot.getProgram().exitCycle(robot);
                return;
            }
        }

        robot.getProgram().resetLoopIndex();
    }

    @Override
    public RobotInstruction cloneInstruction() {
        return new DoneInstruction();
    }
}
