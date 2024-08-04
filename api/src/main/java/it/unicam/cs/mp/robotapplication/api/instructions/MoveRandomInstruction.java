package it.unicam.cs.mp.robotapplication.api.instructions;

import it.unicam.cs.mp.robotapplication.api.Robot;

import java.util.Random;

/**
 * Represents an instruction that instructs a robot to move to a random position within a specified range.
 */
public class MoveRandomInstruction implements RobotInstruction {

    private final double x1;
    private final double x2;
    private final double y1;
    private final double y2;
    private final double speed;

    public MoveRandomInstruction(double[] args) {
        this.x1 = args[0];
        this.x2 = args[1];
        this.y1 = args[2];
        this.y2 = args[3];
        this.speed = args[4];
    }

    @Override
    public void apply(Robot robot) {
        double[] args = computeMovement();
        new MoveInstruction(args[0], args[1], args[2]).apply(robot);
    }

    @Override
    public RobotInstruction cloneInstruction() {
        double[] args = {this.x1, this.x2, this.y1, this.y2, this.speed};
        return new MoveRandomInstruction(args);
    }

    private double[] computeMovement() {
        Random r = new Random();
        double xFinale = x1 + (x2 - x1) * r.nextDouble();
        double yFinale = y1 + (y2 - y1) * r.nextDouble();
        double[] xy = {xFinale, yFinale, speed};
        return xy;
    }
}
