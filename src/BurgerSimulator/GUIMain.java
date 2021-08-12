package BurgerSimulator;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * This class creates a GUI to be used with the GUISimulator class and
 * starts execution of the GUI version of this simulation
 *
 * @author Devon Lee
 */
public class GUIMain extends Application
{
    /**
     * Starts the GUI Simulation with a specified (non-resizable) stage
     * size
     *
     * @param primaryStage The main window for the GUI
     * @throws Exception The program could not be executed
     */
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        Parent root = FXMLLoader.load(getClass().getResource("GUI.fxml"));
        primaryStage.setTitle("Welcome to Platteville Burger");
        primaryStage.setScene(new Scene(root, 600, 380));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    /**
     * Main method--used mostly for launching program from places that do
     * not fully support JavaFX
     *
     * @param args Default method for main methods
     */
    public static void main(String[] args)
    {
        launch(args);
    }
}