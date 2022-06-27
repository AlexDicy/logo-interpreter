package it.unicam.cs.pa.ma114763.logo.ui;

import it.unicam.cs.pa.ma114763.logo.io.FileResourceReader;
import it.unicam.cs.pa.ma114763.logo.ui.controller.DataController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

/**
 * @author Lorenzo Lapucci
 */
public class LogoUI extends Application {

    private static LogoUI instance;

    private Stage stage;

    @Override
    public void start(Stage stage) {
        if (instance != null) {
            throw new IllegalStateException("LogoUI is already running");
        }
        instance = this;
        this.stage = stage;

        openRoot("fxml/choose_file.fxml");
        stage.setTitle("Logo Interpreter");
        //noinspection ConstantConditions
        stage.getIcons().add(new Image(getClass().getClassLoader().getResourceAsStream("icons/app-icon.png")));
        stage.show();

        // TODO: remove this test code
        FileResourceReader reader = new FileResourceReader(new File("D:\\Downloads\\logoprogram.txt"));
        try {
            String program = reader.read();
            LogoUI.getInstance().openRoot("fxml/logo_viewer.fxml", program, false);
        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR, "Error while reading file\n\nError: " + e.getMessage()).show();
        }
    }

    public void openRoot(String fxml) {
        try {
            //noinspection ConstantConditions
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource(fxml));
            stage.setScene(new Scene(root));
            stage.centerOnScreen();
        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR, "Error loading FXML file\nCannot show the correct scene").show();
        }
    }

    public <D, C extends DataController<D>> void openRoot(String fxml, D data, boolean newStage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource(fxml));
            Parent root = loader.load();
            C controller = loader.getController();
            controller.setData(data);
            Stage stage = newStage ? new Stage() : this.stage;
            stage.setScene(new Scene(root));
            stage.show();
            stage.centerOnScreen();
        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR, "Error loading FXML file\nCannot show the correct scene").show();
        }
    }

    public static LogoUI getInstance() {
        return instance;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
