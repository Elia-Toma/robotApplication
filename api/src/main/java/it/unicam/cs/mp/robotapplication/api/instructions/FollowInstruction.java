package it.unicam.cs.mp.robotapplication.api.instructions;

import it.unicam.cs.mp.robotapplication.api.Robot;
import it.unicam.cs.mp.robotapplication.api.model.Coordinates;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents an instruction that instructs a robot to follow other robots based on a condition and a distance.
 * The robot moves towards the average of the directions of the robots indicating a certain condition.
 */
public class FollowInstruction implements RobotInstruction {

    private final String label;
    private final double distance;
    private final double speed;

    public FollowInstruction(String label, double[] args) {
        this.label = label;
        this.distance = args[0];
        this.speed = args[1];
    }

    @Override
    public void apply(Robot robot) {
        List<Robot> robotList = findCondition(robot);
        robotList = findDistance(robotList, robot);

        robot.updateDirection(computeAveragePosition(robotList));
        robot.updateSpeed(speed);

        robot.computeMovement(this);
    }

    @Override
    public RobotInstruction cloneInstruction() {
        double[] args = {this.distance, this.speed};
        return new FollowInstruction(this.label, args);
    }

    /**
     * Finds robots that have the specified condition.
     *
     * @param robot the robot to search for other robots
     * @return a list of robots that have the specified condition
     */
    private List<Robot> findCondition(Robot robot) {
        List<Robot> robots = new ArrayList<Robot>();
        for (Robot r : robot.getSurface().getRobotList())
            if (!r.equals(robot) && r.getCondition().getLabel().equals(this.label)) robots.add(r);
        return robots;
    }

    /**
     * Filters the robots based on the specified distance from the given robot's location.
     *
     * @param robotList the list of robots to filter
     * @param robot     the reference robot to measure the distance from
     * @return a list of robots within the specified distance
     */
    private List<Robot> findDistance(List<Robot> robotList, Robot robot) {
        List<Robot> robots = new ArrayList<Robot>();
        for (Robot r : robotList)
            if (computeDistance(r.getLocation(), robot.getLocation()) <= this.distance) robots.add(r);
        return robots;
    }

    /**
     * Computes the distance between two coordinates.
     *
     * @param coord1 the first coordinate
     * @param coord2 the second coordinate
     * @return the distance between the two coordinates
     */
    private double computeDistance(Coordinates coord1, Coordinates coord2) {
        double x1 = coord1.getX();
        double y1 = coord1.getY();
        double x2 = coord2.getX();
        double y2 = coord2.getY();

        double deltaX = x2 - x1;
        double deltaY = y2 - y1;

        return Math.sqrt(deltaX * deltaX + deltaY * deltaY);
    }

    /**
     * Computes the average position of the robots in the specified list.
     *
     * @param robotList the list of robots
     * @return the average position as a Coordinates object
     */
    private Coordinates computeAveragePosition(List<Robot> robotList) {
        double x = 0;
        double y = 0;
        for (Robot r : robotList) {
            x += r.getLocation().getX();
            y += r.getLocation().getY();
        }
        x /= robotList.size();
        y /= robotList.size();
        return new Coordinates(x, y);
    }

}
