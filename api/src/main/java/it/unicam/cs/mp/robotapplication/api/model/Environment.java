package it.unicam.cs.mp.robotapplication.api.model;

import it.unicam.cs.mp.robotapplication.api.Robot;
import it.unicam.cs.mp.robotapplication.utilities.ShapeData;

import java.util.List;

/**
 * Represents an environment in which robots operate.
 */
public interface Environment {

    /**
     * Adds a robot to the environment.
     *
     * @param robot the robot to add
     */
    void addRobot(Robot robot);

    /**
     * Gets the list of robots in the environment.
     *
     * @return the list of robots
     */
    List<Robot> getRobotList();

    /**
     * Gets the list of shapes in the environment.
     *
     * @return the list of shapes
     */
    List<ShapeData> getShapeList();

    /**
     * Sets the list of shapes in the environment
     *
     * @param shapeList the list of shapes
     */
    void setShapeList(List<ShapeData> shapeList);
}
