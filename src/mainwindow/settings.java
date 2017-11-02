package mainwindow;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;

public class settings implements Initializable {
    @FXML
    private ChoiceBox<String> port;
    @FXML
    private ChoiceBox baud;


    public void initialize(URL location, ResourceBundle resources){
        ObservableList<String> list = FXCollections.observableArrayList("COM 1", "COM 2", "COM 3");
        port.setItems(list);
    }
}
