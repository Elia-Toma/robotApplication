package it.unicam.cs.mp.robotapplication.api;

import it.unicam.cs.mp.robotapplication.api.instructions.*;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class CloneInstructionTest {

    // 11 total different instructions

    private static final MoveInstruction move = new MoveInstruction(new double[]{1, 0, 1});
    private static final MoveRandomInstruction moveRandom = new MoveRandomInstruction(new double[]{1, 1, 1, 1, 1});
    private static final SignalInstruction signal = new SignalInstruction("label");
    private static final UnsignalInstruction unsignal = new UnsignalInstruction("label");
    private static final FollowInstruction follow = new FollowInstruction("label", new double[]{1, 1});
    private static final ContinueInstruction continueInstr = new ContinueInstruction(1);
    private static final StopInstruction stop = new StopInstruction();
    private static final RepeatInstruction repeat = new RepeatInstruction(1);
    private static final UntilInstruction until = new UntilInstruction("label");
    private static final DoForeverInstruction doForever = new DoForeverInstruction();
    private static final DoneInstruction done = new DoneInstruction();

    /**
     * Tests the cloneInstruction() method in all {@link RobotInstruction} classes
     */
    @Test
    public void cloneInstructionTest() {
        List<RobotInstruction> list = createList();
        List<RobotInstruction> copyOfList = new LinkedList<>();
        for (RobotInstruction ri : list)
            copyOfList.add(ri.cloneInstruction());
        for (int i = 0; i < 11; i++)
            assertNotEquals(list.get(i), copyOfList.get(i));
    }

    // Helper method

    private List<RobotInstruction> createList() {
        List<RobotInstruction> list = new LinkedList<>();
        list.add(move);
        list.add(moveRandom);
        list.add(signal);
        list.add(unsignal);
        list.add(follow);
        list.add(continueInstr);
        list.add(stop);
        list.add(repeat);
        list.add(until);
        list.add(doForever);
        list.add(done);

        return list;
    }
}
