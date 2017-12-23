package mainwindow;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;

public class Channel implements Initializable{

    @FXML
    private TextField nameText;
    @FXML
    private TextField unitText;
    @FXML
    private ChoiceBox channelBox;
    @FXML
    private ColorPicker colorPicker;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        channelBox.getItems().add("Channel 1");
        channelBox.getItems().add("Channel 2");
        channelBox.getItems().add("Channel 3");
        channelBox.getItems().add("Channel 4");
        channelBox.getItems().add("Channel 5");
        channelBox.getItems().add("Channel 6");
        channelBox.getItems().add("Channel 7");
        channelBox.getItems().add("Channel 8");
    }

    public void onSetAction(ActionEvent actionEvent) {
        String channel=(String)channelBox.getSelectionModel().getSelectedItem();
        String name=nameText.getText();
        String units=unitText.getText();
        Color color=colorPicker.getValue();

    }
}
