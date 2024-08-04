package it.unicam.cs.mp.robotapplication.api;

import it.unicam.cs.mp.robotapplication.api.instructions.DoneInstruction;
import it.unicam.cs.mp.robotapplication.api.instructions.MoveInstruction;
import it.unicam.cs.mp.robotapplication.api.instructions.RepeatInstruction;
import it.unicam.cs.mp.robotapplication.api.model.Surface;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProgramTest {

    private final Program program = new Program();

    /**
     * Tests the addInstruction() method in {@link Program} class
     */
    @Test
    void addInstructionTest() {
        MoveInstruction m = new MoveInstruction(1, 0, 1);
        program.addInstruction(m);
        assertEquals(program.getInstructions().size(), 1);
    }

    /**
     * Tests the executeNextInstruction() method in {@link Program} class
     */
    @Test
    void executeNextInstructionTest() {
        List<Robot> robots = new ArrayList<>();
        robots.add(new Robot("1", new Surface()));
        MoveInstruction m = new MoveInstruction(1, 0, 1);
        program.addInstruction(m);
        robots.get(0).setProgram(program);
        program.executeNextInstruction(robots);
        assertEquals(robots.get(0).getLastMovement(), m);
    }

    /**
     * Tests the exitCycle() method in {@link Program} class
     */
    @Test
    void exitCycleTest() {
        Robot robot = new Robot("1", new Surface());
        RepeatInstruction r = new RepeatInstruction(1);
        DoneInstruction d = new DoneInstruction();
        robot.setProgram(program);
        robot.getProgram().addInstruction(r);
        robot.getProgram().addInstruction(d);
        robot.getProgram().incrementLoopIndex();
        robot.getProgram().incrementLoopIndex();
        robot.getProgram().exitCycle(robot);
        assertEquals(robot.getProgram().getInstructions().size(), 0);
        assertEquals(robot.getProgram().getLOOP_INDEX(), 0);
    }

    /**
     * Tests the clearInstructionQueue() method in {@link Program} class
     */
    @Test
    void clearInstructionQueueTest() {
        MoveInstruction m = new MoveInstruction(1, 0, 1);
        program.addInstruction(m);
        assertEquals(program.getInstructions().size(), 1);
        program.clearInstructionQueue();
        assertEquals(program.getInstructions().size(), 0);
    }

    /**
     * Tests the resetLoopIndex() method in {@link Program} class
     */
    @Test
    void resetLoopIndexTest() {
        program.incrementLoopIndex();
        assertEquals(program.getLOOP_INDEX(), 1);
        program.resetLoopIndex();
        assertEquals(program.getLOOP_INDEX(), 0);
    }
}