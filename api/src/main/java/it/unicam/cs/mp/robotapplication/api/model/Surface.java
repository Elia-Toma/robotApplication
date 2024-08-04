package it.unicam.cs.mp.robotapplication.api.model;

import it.unicam.cs.mp.robotapplication.api.Robot;
import it.unicam.cs.mp.robotapplication.utilities.ShapeData;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a surface environment in which robots operate.
 */
public class Surface implements Environment {

    private List<ShapeData> shapeList = new ArrayList<>();

    private final List<Robot> robotList = new ArrayList<>();

    @Override
    public void addRobot(Robot robot) {
        this.robotList.add(robot);
    }

    public Surface() {
    }

    @Override
    public List<Robot> getRobotList() {
        return robotList;
    }

    @Override
    public List<ShapeData> getShapeList() {
        return shapeList;
    }

    @Override
    public void setShapeList(List<ShapeData> shapeList) {
        this.shapeList = shapeList;
    }
}
