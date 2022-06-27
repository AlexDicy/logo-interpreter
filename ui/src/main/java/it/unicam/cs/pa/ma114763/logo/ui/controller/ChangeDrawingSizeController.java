package it.unicam.cs.pa.ma114763.logo.ui.controller;

import it.unicam.cs.pa.ma114763.logo.ui.CanvasResizeHandler;
import it.unicam.cs.pa.ma114763.logo.ui.controller.DataController.DataController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

/**
 * @author Lorenzo Lapucci
 */
public class ChangeDrawingSizeController implements DataController<CanvasResizeHandler> {
    private CanvasResizeHandler resizeHandler;

    @FXML
    private TextField widthField;

    @FXML
    private TextField heightField;

    @Override
    public void setData(CanvasResizeHandler handler) {
        this.resizeHandler = handler;
        resetFields();
    }

    @FXML
    private void resetFields() {
        widthField.setText(String.valueOf(resizeHandler.getCurrentWidth()));
        heightField.setText(String.valueOf(resizeHandler.getCurrentHeight()));
    }

    @FXML
    private void cancel(ActionEvent event) {
        ((Node) event.getSource()).getScene().getWindow().hide();
    }

    @FXML
    private void apply(ActionEvent event) {
        try {
            int width = tryGetDimension(widthField, "width");
            int height = tryGetDimension(heightField, "height");
            resizeHandler.resize(width, height);
            ((Node) event.getSource()).getScene().getWindow().hide();
        } catch (IllegalArgumentException ignored) {
        }
    }

    private int tryGetDimension(TextField textField, String name) throws IllegalArgumentException {
        try {
            int value = Integer.parseInt(textField.getText());
            if (value < 1) {
                throw new NumberFormatException();
            }
            return value;
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Invalid " + name);
            alert.setContentText("The value must be a positive, natural, number.");
            alert.showAndWait();
            throw new IllegalArgumentException();
        }
    }
}
