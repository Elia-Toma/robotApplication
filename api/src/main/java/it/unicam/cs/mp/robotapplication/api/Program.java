package it.unicam.cs.mp.robotapplication.api;

import it.unicam.cs.mp.robotapplication.api.instructions.RobotInstruction;

import java.util.LinkedList;
import java.util.List;

/**
 * This class represents the program that each robot must execute.
 */
public class Program {
    private final List<RobotInstruction> instructions;
    private int LOOP_INDEX = 0;

    public Program() {
        this.instructions = new LinkedList<>();
    }

    public void addInstruction(RobotInstruction instruction) {
        this.instructions.add(instruction);
    }

    /**
     * Executes the next instruction in the robot's instruction queue.
     */
    public void executeNextInstruction(List<Robot> robots) {

        for (Robot r : robots) {
            r.isInsideArea();

            if (r.isInLoop()) {
                r.getProgram().incrementLoopIndex();
                r.getProgram().getInstructions().get(r.getProgram().getLOOP_INDEX() - 1).apply(r);
            } else if (!r.getProgram().getInstructions().isEmpty()) {
                r.getProgram().getInstructions().remove(0).apply(r);
            }
        }
    }

    public void exitCycle(Robot r) {
        for (int i = 0; i < r.getProgram().getLOOP_INDEX(); i++)
            this.instructions.remove(0);
        resetLoopIndex();
        r.setLoopStatus(false);
    }

    public List<RobotInstruction> getInstructions() {
        return instructions;
    }

    public void clearInstructionQueue() {
        this.instructions.clear();
    }

    public int getLOOP_INDEX() {
        return LOOP_INDEX;
    }

    public void incrementLoopIndex() {
        this.LOOP_INDEX++;
    }

    public void resetLoopIndex() {
        LOOP_INDEX = 0;
    }

    /**
     * Creates a copy of the program.
     *
     * @return the copy of the program
     */
    public Program cloneProgram() {
        Program newProgram = new Program();
        for (RobotInstruction ri : this.instructions)
            newProgram.addInstruction(ri.cloneInstruction());
        return newProgram;
    }
}
