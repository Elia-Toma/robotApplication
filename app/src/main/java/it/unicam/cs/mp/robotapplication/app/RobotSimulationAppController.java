package it.unicam.cs.mp.robotapplication.app;

import it.unicam.cs.mp.robotapplication.api.Controller;
import it.unicam.cs.mp.robotapplication.api.Robot;
import it.unicam.cs.mp.robotapplication.utilities.FollowMeParserException;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.io.IOException;

/**
 * JavaFX Controller for robotApplication
 */
public class RobotSimulationAppController {

    @FXML
    private AnchorPane fieldArea;

    private Controller controller;

    private static final int numRobots = 20;

    // Constructor called by load() method from FXMLLoader
    public RobotSimulationAppController() {
        this.controller = new Controller();
    }

    /**
     * Executes a single command.
     * Updates the simulation, clears the board from previous robots, and draws the updated robots.
     */
    public void executeCommand() {
        this.controller.stepForward();
        clearBoardFromRobots();
        drawRobots();
    }

    /**
     * Executes multiple commands.
     */
    public void executeMultipleCommands() {
        for (int i = 0; i < 10; i++)
            executeCommand();
    }

    /**
     * Draws robots on the field area.
     * Creates circles representing robots and adds them to the field area.
     */
    private void drawRobots() {
        for (Robot r : this.controller.getCurrentSurface().getRobotList()) {
            Circle robot = new Circle(r.getLocation().getX(), r.getLocation().getY(), 3);
            robot.setFill(Color.BLACK);
            if (robot.getCenterX() > 3 && robot.getCenterX() < 797 && robot.getCenterY() > 3 && robot.getCenterY() < 597)
                this.fieldArea.getChildren().add(robot);
        }
    }

    /**
     * Clears the field area from previously drawn robots.
     */
    private void clearBoardFromRobots() {
        this.fieldArea.getChildren().clear();
    }

    /**
     * Resets the simulation to its initial state.
     *
     * @throws FollowMeParserException If parsing of commands fails.
     * @throws IOException             If an I/O error occurs.
     */
    public void reset() throws FollowMeParserException, IOException {
        clearBoardFromRobots();

        this.controller = new Controller();
        this.controller.initialize(numRobots);
        this.controller.injectRobotCommands();
    }

    public Controller getController() {
        return controller;
    }
}
