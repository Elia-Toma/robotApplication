package it.unicam.cs.mp.robotapplication.api;

import it.unicam.cs.mp.robotapplication.api.instructions.FollowInstruction;
import it.unicam.cs.mp.robotapplication.api.instructions.MoveInstruction;
import it.unicam.cs.mp.robotapplication.api.instructions.RobotInstruction;
import it.unicam.cs.mp.robotapplication.api.model.Condition;
import it.unicam.cs.mp.robotapplication.api.model.Coordinates;
import it.unicam.cs.mp.robotapplication.api.model.Environment;
import it.unicam.cs.mp.robotapplication.utilities.ShapeData;

import java.util.Objects;
import java.util.Random;

/**
 * This class represents a Robot that executes instructions in a given environment.
 */
public class Robot {

    private static final String SHAPE_TYPE_CIRCLE = "CIRCLE";
    private static final String SHAPE_TYPE_RECTANGLE = "RECTANGLE";

    private final String id;

    private final Environment surface;
    private Program program;
    private Coordinates location;
    private Coordinates direction;

    /**
     * If the condition is "null", the robot is not signaling any condition.
     */
    private Condition condition;

    private boolean loopStatus;
    private RobotInstruction currentLoop;

    private RobotInstruction lastMovement;

    private double speed;

    public Robot(String id, Environment surface) {
        this.id = id;
        this.surface = surface;

        this.location = generateRandomCoordinates();
        this.direction = new Coordinates(0, 0);

        this.condition = new Condition("null");
        this.speed = 0;

        this.loopStatus = false;
    }

    private static Coordinates generateRandomCoordinates() {
        Random random = new Random();
        double x = random.nextDouble() * 800; // Generate a random number between 0 and 800
        double y = random.nextDouble() * 600; // Generate a random number between 0 and 600
        return new Coordinates(x, y);
    }

    /**
     * Checks if the robot is inside any defined area in the environment.
     * Updates the robot's condition accordingly.
     */
    public void isInsideArea() {
        for (ShapeData s : this.surface.getShapeList()) {
            if (s.shape().equals(SHAPE_TYPE_CIRCLE)) {
                double distance = Math.sqrt(Math.pow(s.args()[0] - this.location.getX(), 2) + Math.pow(s.args()[1] - this.location.getY(), 2));
                if (distance <= s.args()[2]) {
                    this.condition = new Condition(s.label());
                    return;
                }
            } else if (s.shape().equals(SHAPE_TYPE_RECTANGLE)) {
                double distanceX = Math.abs(s.args()[0] - this.location.getX());
                double distanceY = Math.abs(s.args()[1] - this.location.getY());
                if (distanceX <= s.args()[2] / 2 && distanceY <= s.args()[3] / 2)
                    this.condition = new Condition(s.label());
            }
        }
    }

    public void updateDirection(Coordinates direction) {
        this.direction = direction;
    }

    /**
     * Computes the movement of the robot based on the specified movement instruction.
     *
     * @param movement The movement instruction.
     */
    public void computeMovement(RobotInstruction movement) {
        if (this.speed == 0) return;
        if (Double.isNaN(this.direction.getX()) || Double.isNaN(this.direction.getY())) return;

        this.lastMovement = movement;
        if (movement instanceof MoveInstruction) {
            this.location = calculateFutureCoordinates(this.location, this.direction);
        } else if (movement instanceof FollowInstruction) {
            this.location = calculateNextLocation(this.location, this.direction);
        }
    }

    private Coordinates calculateFutureCoordinates(Coordinates currentLocation, Coordinates direction) {
        double futureX = currentLocation.getX() + (direction.getX() * this.speed);
        double futureY = currentLocation.getY() + (direction.getY() * this.speed);
        return new Coordinates(futureX, futureY);
    }

    /**
     * Calculates the next location based on the current and future coordinates.
     *
     * @param currentLocation The current location.
     * @param futureLocation  The future location.
     * @return The calculated next location.
     */
    private Coordinates calculateNextLocation(Coordinates currentLocation, Coordinates futureLocation) {
        double xDifference = futureLocation.getX() - currentLocation.getX();
        double yDifference = futureLocation.getY() - currentLocation.getY();

        double nextX = Math.abs(xDifference) <= this.speed ? currentLocation.getX() + xDifference : currentLocation.getX() + Math.signum(xDifference) * this.speed;
        double nextY = Math.abs(yDifference) <= this.speed ? currentLocation.getY() + yDifference : currentLocation.getY() + Math.signum(yDifference) * this.speed;

        return new Coordinates(nextX, nextY);
    }

    public void updateSpeed(double speed) {
        this.speed = speed;
    }

    public void updateCondition(Condition condition) {
        this.condition = condition;
    }

    public Coordinates getLocation() {
        return location;
    }

    public void setLocation(Coordinates location) {
        this.location = location;
    }

    public Coordinates getDirection() {
        return direction;
    }

    public Condition getCondition() {
        return condition;
    }

    public Environment getSurface() {
        return surface;
    }

    public double getSpeed() {
        return speed;
    }

    public RobotInstruction getLastMovement() {
        return lastMovement;
    }

    public boolean isInLoop() {
        return this.loopStatus;
    }

    public void setLoopStatus(boolean loopStatus) {
        this.loopStatus = loopStatus;
    }

    public RobotInstruction getCurrentLoop() {
        return currentLoop;
    }

    public void setCurrentLoop(RobotInstruction currentLoop) {
        this.currentLoop = currentLoop;
    }

    public Program getProgram() {
        return program;
    }

    public void setProgram(Program program) {
        this.program = program;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Robot robot = (Robot) o;
        return Objects.equals(id, robot.id);
    }
}
