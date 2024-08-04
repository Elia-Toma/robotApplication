package it.unicam.cs.mp.robotapplication.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class initializeStage extends Application {

    private static final int numRobots = 20;

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("/fxml/RobotSimulationAPP.fxml")));
        Parent root = loader.load();

        RobotSimulationAppController controller = loader.getController();
        controller.getController().initialize(numRobots);
        controller.getController().injectRobotCommands();

        primaryStage.setTitle("Robot Simulation App");
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.show();

    }
}
