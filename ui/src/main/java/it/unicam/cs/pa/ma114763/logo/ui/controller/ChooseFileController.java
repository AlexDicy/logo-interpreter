package it.unicam.cs.pa.ma114763.logo.ui.controller;

import it.unicam.cs.pa.ma114763.logo.io.FileResourceReader;
import it.unicam.cs.pa.ma114763.logo.ui.LogoUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;

/**
 * @author Lorenzo Lapucci
 */
public class ChooseFileController {

    @FXML
    private void openProgramFile(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Logo Program File");
        File file = fileChooser.showOpenDialog(((Node) event.getSource()).getScene().getWindow());
        if (file != null) {
            FileResourceReader reader = new FileResourceReader(file);
            try {
                String program = reader.read();
                LogoUI.getInstance().replaceRoot("fxml/logo_viewer.fxml", program, LogoViewerController.class);
            } catch (IOException e) {
                new Alert(Alert.AlertType.ERROR, "Error while reading file\n\nError: " + e.getMessage()).show();
            }
        }
    }
}
