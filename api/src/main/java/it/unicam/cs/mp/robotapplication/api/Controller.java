package it.unicam.cs.mp.robotapplication.api;

import it.unicam.cs.mp.robotapplication.api.model.Environment;
import it.unicam.cs.mp.robotapplication.api.model.ShapeChecker;
import it.unicam.cs.mp.robotapplication.api.model.Surface;
import it.unicam.cs.mp.robotapplication.utilities.FollowMeParser;
import it.unicam.cs.mp.robotapplication.utilities.FollowMeParserException;

import java.io.File;
import java.io.IOException;

/**
 * This class represents the Controller that manages the execution of robot commands
 * in a specific environment.
 */
public class Controller {

    private final Environment currentSurface;
    private final Program currentProgram;
    private final FollowMeParser parser;

    public Controller() {
        this.currentSurface = new Surface();
        this.currentProgram = new Program();
        this.parser = createFollowMeParser();
    }

    private FollowMeParser createFollowMeParser() {
        CommandHandler commandHandler = new CommandHandler(currentProgram);
        return new FollowMeParser(commandHandler, new ShapeChecker());
    }

    /**
     * Initializes the controller with the specified number of robots and parses the environment.
     *
     * @param numRobots The number of robots to initialize.
     * @throws FollowMeParserException If an error occurs during parsing.
     * @throws IOException             If an I/O error occurs.
     */
    public void initialize(int numRobots) throws FollowMeParserException, IOException {
        for (int i = 1; i <= numRobots; i++)
            currentSurface.addRobot(new Robot("robot" + i, currentSurface));

        String environmentFilePath = System.getProperty("user.dir") + "/src/main/resources/EnvironmentData.txt";
        currentSurface.setShapeList(parser.parseEnvironment(new File(environmentFilePath)));
    }

    /**
     * Parses and injects commands for each robot in the current environment.
     *
     * @throws FollowMeParserException If an error occurs during parsing.
     * @throws IOException             If an I/O error occurs.
     */
    public void injectRobotCommands() throws FollowMeParserException, IOException {
        File robotCommandsFile = new File(System.getProperty("user.dir") + "/src/main/resources/RobotCommands.txt");
        parser.parseRobotProgram(robotCommandsFile);

        for (Robot r : this.currentSurface.getRobotList())
            r.setProgram(this.currentProgram.cloneProgram());
    }

    /**
     * Executes the next instruction for each robot in the current environment.
     */
    public void stepForward() {
        currentProgram.executeNextInstruction(currentSurface.getRobotList());
    }

    public Environment getCurrentSurface() {
        return currentSurface;
    }
}
