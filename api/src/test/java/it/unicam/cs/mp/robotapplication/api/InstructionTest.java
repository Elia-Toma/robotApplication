package it.unicam.cs.mp.robotapplication.api;

import it.unicam.cs.mp.robotapplication.api.instructions.*;
import it.unicam.cs.mp.robotapplication.api.model.Condition;
import it.unicam.cs.mp.robotapplication.api.model.Coordinates;
import it.unicam.cs.mp.robotapplication.api.model.Surface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class InstructionTest {

    private Surface field;
    private List<Robot> robots;

    @BeforeEach
    public void setUp() {
        field = new Surface();
        robots = new ArrayList<>();

        for (int i = 1; i <= 3; i++) {
            Robot r = new Robot("robot" + i, field);
            robots.add(r);
            field.addRobot(r);
        }
    }

    /**
     * Tests the {@link MoveInstruction} class.
     */
    @Test
    public void moveInstructionTest() {
        double[] args = {1, 0, 1}; // Default speed for robots is 0 (third value)
        MoveInstruction movement = new MoveInstruction(args);

        int robotsToApply = 2;
        for (int i = 0; i < robotsToApply; i++)
            movement.apply(robots.get(i));

        assertRobotDirection(robots, robotsToApply, args[0], args[1]);
        assertRobotSpeed(robots, robotsToApply, args[2]);
    }

    /**
     * Tests the {@link MoveRandomInstruction} class.
     */
    @Test
    public void moveRandomInstructionTest() {
        double[] args = {-1, 1, -1, 1, 2}; // Default speed for robots is 0 (fifth value)
        MoveRandomInstruction movement = new MoveRandomInstruction(args);

        Coordinates initialCoord = robots.get(0).getLocation();

        int robotsToApply = 2;
        for (int i = 0; i < robotsToApply; i++)
            movement.apply(robots.get(i));

        assertRandomDirection(robots, robotsToApply, initialCoord.getX(), initialCoord.getY());
        assertRobotSpeed(robots, robotsToApply, args[4]);
    }

    /**
     * Tests the {@link SignalInstruction} class.
     */
    @Test
    public void signalInstructionTest() {
        String label = "condition_1";
        SignalInstruction signal = new SignalInstruction(label);

        int robotsToApply = 2;
        for (int i = 0; i < robotsToApply; i++)
            signal.apply(robots.get(i));

        assertRobotSignal(robots, robotsToApply, label);
    }

    /**
     * Tests the {@link UnsignalInstruction} class.
     */
    @Test
    public void unsignalInstructionTest() {
        for (Robot r : robots)
            new SignalInstruction("condition_1").apply(r);

        UnsignalInstruction unsignal = new UnsignalInstruction("condition_1");

        int robotsToApply = 2;
        for (int i = 0; i < robotsToApply; i++)
            unsignal.apply(robots.get(i));

        assertRobotSignal(robots, robotsToApply, "null");
    }

    /**
     * Tests the {@link FollowInstruction} class.
     */
    @Test
    public void followInstructionTest() {
        for (int i = 0; i < 2; i++) {
            new SignalInstruction("CONDITION_1").apply(robots.get(i));
        }

        double[] args = {5000, 1};
        new FollowInstruction("CONDITION_1", args).apply(robots.get(2));
        double averageX = (robots.get(0).getLocation().getX() + robots.get(1).getLocation().getX()) / 2;
        double averageY = (robots.get(0).getLocation().getY() + robots.get(1).getLocation().getY()) / 2;
        assertEquals(robots.get(2).getDirection(), new Coordinates(averageX, averageY));
        assertEquals(robots.get(2).getSpeed(), args[1]);
    }

    /**
     * Tests the {@link ContinueInstruction} class.
     */
    @Test
    public void continueInstructionTest() {
        Robot robot = new Robot("robot", field);
        Coordinates initialLocation = robot.getLocation();
        double[] args = {1, 0, 1};
        new MoveInstruction(args).apply(robot);
        new ContinueInstruction(1).apply(robot);
        assertEquals(robot.getLocation(), new Coordinates(initialLocation.getX() + 2, initialLocation.getY()));
    }

    /**
     * Tests the {@link StopInstruction} class.
     */
    @Test
    public void stopInstructionTest() {
        for (Robot r : robots)
            r.updateSpeed(1);

        int robotsToApply = 2;
        for (int i = 0; i < robotsToApply; i++)
            new StopInstruction().apply(robots.get(i));

        assertRobotSpeed(robots, robotsToApply, 0);
    }

    // Helper methods

    private void assertRobotDirection(List<Robot> robots, int robotsToApply, double x, double y) {
        Coordinates coord = new Coordinates(x, y);
        for (int i = 0; i < robotsToApply; i++)
            assertEquals(robots.get(i).getDirection(), coord);
        for (int i = robotsToApply; i < robots.size(); i++)
            assertNotEquals(robots.get(i).getDirection(), coord);
    }

    private void assertRandomDirection(List<Robot> robots, int robotsToApply, double x, double y) {
        Coordinates coord = new Coordinates(x, y);
        for (int i = 0; i < robotsToApply; i++)
            assertNotEquals(robots.get(i).getDirection(), coord);
        for (int i = robotsToApply; i < robots.size(); i++)
            assertEquals(robots.get(i).getDirection(), new Coordinates(0, 0));
    }

    private void assertRobotSpeed(List<Robot> robots, int robotsToApply, double expectedSpeed) {
        for (int i = 0; i < robotsToApply; i++)
            assertEquals(robots.get(i).getSpeed(), expectedSpeed);
        for (int i = robotsToApply; i < robots.size(); i++)
            assertNotEquals(robots.get(i).getSpeed(), expectedSpeed);
    }

    private void assertRobotSignal(List<Robot> robots, int robotsToApply, String label) {
        Condition cond = new Condition(label);
        for (int i = 0; i < robotsToApply; i++)
            assertEquals(robots.get(i).getCondition(), cond);
        for (int i = robotsToApply; i < robots.size(); i++)
            assertNotEquals(robots.get(i).getCondition(), cond);
    }
}
