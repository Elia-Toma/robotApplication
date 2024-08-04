package it.unicam.cs.mp.robotapplication.api;

import it.unicam.cs.mp.robotapplication.api.instructions.MoveInstruction;
import it.unicam.cs.mp.robotapplication.api.model.Coordinates;
import it.unicam.cs.mp.robotapplication.api.model.Surface;
import it.unicam.cs.mp.robotapplication.utilities.ShapeData;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RobotTest {

    Surface surface = new Surface();
    Robot robot = new Robot("robot", this.surface);

    /**
     * Tests the isInsideArea() method in {@link Robot} class
     */
    @Test
    void isInsideAreaTest() {
        insideCircle();
        insideRectangle();
    }

    /**
     * Tests the computeMovement() method in {@link Robot} class
     */
    @Test
    void computeMovementTest() {
        MoveInstruction move = new MoveInstruction(1, 0, 1);
        this.robot.setLocation(new Coordinates(1, 1));
        move.apply(this.robot);
        assertEquals(this.robot.getLocation().getX(), 2);
    }

    // Helper methods

    private void insideCircle() {
        List<ShapeData> shapeList = new ArrayList<>();
        ShapeData shape = new ShapeData("cerchio", "CIRCLE", new double[]{1, 1, 50});
        shapeList.add(shape);
        surface.setShapeList(shapeList);
        robot.setLocation(new Coordinates(2, 2));
        robot.isInsideArea();
        assertEquals(robot.getCondition().getLabel(), "cerchio");
    }

    private void insideRectangle() {
        List<ShapeData> shapeList = new ArrayList<>();
        ShapeData shape = new ShapeData("rettangolo", "RECTANGLE", new double[]{1, 1, 50, 50});
        shapeList.add(shape);
        surface.setShapeList(shapeList);
        robot.setLocation(new Coordinates(2, 2));
        robot.isInsideArea();
        assertEquals(robot.getCondition().getLabel(), "rettangolo");
    }
}