package it.unicam.cs.mp.robotapplication.api.instructions;

import it.unicam.cs.mp.robotapplication.api.Robot;

/**
 * Represents an instruction that repeats a sequence of instructions for a specified number of times.
 */
public class RepeatInstruction implements RobotInstruction {

    private final int iterations;
    private int doneIterations;

    public RepeatInstruction(int n) {
        this.iterations = n;
        this.doneIterations = 0;
    }

    @Override
    public void apply(Robot robot) {
        if (robot.isInLoop() && robot.getCurrentLoop().equals(this)) {
            this.doneIterations++;
            return;
        }

        this.doneIterations++;
        robot.setLoopStatus(true);
        robot.setCurrentLoop(this);
    }

    @Override
    public RobotInstruction cloneInstruction() {
        return new RepeatInstruction(this.iterations);
    }

    public int getIterations() {
        return iterations;
    }

    public int getDoneIterations() {
        return doneIterations;
    }
}
