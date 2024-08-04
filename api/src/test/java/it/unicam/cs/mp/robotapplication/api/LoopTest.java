package it.unicam.cs.mp.robotapplication.api;

import it.unicam.cs.mp.robotapplication.api.instructions.*;
import it.unicam.cs.mp.robotapplication.api.model.Surface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class LoopTest {

    private Surface field;
    private List<Robot> robots;
    private static final int robotsToApply = 2;

    @BeforeEach
    public void setUp() {
        this.field = new Surface();
        this.robots = new ArrayList<>();

        for (int i = 1; i <= 3; i++) {
            Robot r = new Robot("robot" + i, field);
            r.setProgram(new Program());
            robots.add(r);
            field.addRobot(r);
        }
    }

    /**
     * Tests the {@link RepeatInstruction} class.
     */
    @Test
    public void repeatInstructionTest() {
        RepeatInstruction repeatInstruction = new RepeatInstruction(1);

        applyLoopInstruction(repeatInstruction);

        assertLoopStatus();
        assertCurrentLoop(repeatInstruction);
    }

    /**
     * Tests the {@link UntilInstruction} class.
     */
    @Test
    public void untilInstructionTest() {
        UntilInstruction untilInstruction = new UntilInstruction("condition");

        applyLoopInstruction(untilInstruction);

        assertLoopStatus();
        assertCurrentLoop(untilInstruction);
    }

    /**
     * Tests the {@link DoForeverInstruction} class.
     */
    @Test
    public void doForeverInstructionTest() {
        DoForeverInstruction doForeverInstruction = new DoForeverInstruction();

        applyLoopInstruction(doForeverInstruction);

        assertLoopStatus();
        assertCurrentLoop(doForeverInstruction);
    }

    /**
     * Tests the {@link DoneInstruction} class.
     */
    @Test
    public void doneInstructionTest() {
        Robot robot1 = new Robot("1", field);
        robot1.setProgram(new Program());
        RepeatInstruction r = new RepeatInstruction(1);
        SignalInstruction s = new SignalInstruction("condition");
        UntilInstruction u = new UntilInstruction("condition");
        DoneInstruction d = new DoneInstruction();
        r.apply(robot1);
        assertTrue(robot1.isInLoop());
        d.apply(robot1);
        assertFalse(robot1.isInLoop());
        s.apply(robot1);
        assertEquals(robot1.getCondition().getLabel(), "condition");
        u.apply(robot1);
        assertTrue(robot1.isInLoop());
        d.apply(robot1);
        assertFalse(robot1.isInLoop());
    }

    // Helper methods

    private void applyLoopInstruction(RobotInstruction instruction) {
        for (int i = 0; i < robotsToApply; i++)
            instruction.apply(robots.get(i));
    }

    private void assertLoopStatus() {
        for (int i = 0; i < LoopTest.robotsToApply; i++)
            assertTrue(this.robots.get(i).isInLoop());
        for (int i = LoopTest.robotsToApply; i < this.robots.size(); i++)
            assertFalse(this.robots.get(i).isInLoop());
    }

    private void assertCurrentLoop(RobotInstruction loop) {
        for (int i = 0; i < LoopTest.robotsToApply; i++)
            assertEquals(this.robots.get(i).getCurrentLoop(), loop);
        for (int i = LoopTest.robotsToApply; i < this.robots.size(); i++)
            assertNull(this.robots.get(i).getCurrentLoop());
    }

}
