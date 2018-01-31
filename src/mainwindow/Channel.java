package mainwindow;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class Channel implements Initializable{

    @FXML
    private TextField nameText;
    @FXML
    private TextField unitText;
    @FXML
    private ChoiceBox<String> channelBox;
    @FXML
    private ColorPicker colorPicker;
    private String channel;
    private String name;
    private String units;
    private Color color=Color.RED;

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

    public void onNameKeyPressed(KeyEvent keyEvent) {
        if(keyEvent.getCode() == KeyCode.ENTER){
            try{
                if(!nameText.getText().isEmpty()){
                    name=nameText.getText();
                    channel=channelBox.getSelectionModel().getSelectedItem();
                    if(!unitText.getText().isEmpty()){
                        units="["+unitText.getText()+"]";
                    }
                    else{
                        units="";
                    }

                    switch (channel) {
                        case "Channel 1":
                            Chart.getInstance().channelUpdateWithoutColor(1, name, units);
                            break;
                        case "Channel 2":
                            Chart.getInstance().channelUpdateWithoutColor(2, name, units);
                            break;
                        case "Channel 3":
                            Chart.getInstance().channelUpdateWithoutColor(3, name,units);
                            break;
                        case "Channel 4":
                            Chart.getInstance().channelUpdateWithoutColor(4, name,units);
                            break;
                        case "Channel 5":
                            Chart.getInstance().channelUpdateWithoutColor(5, name,units);
                            break;
                        case "Channel 6":
                            Chart.getInstance().channelUpdateWithoutColor(6, name ,units);
                            break;
                        case "Channel 7":
                            Chart.getInstance().channelUpdateWithoutColor(7, name,units);
                            break;
                        case "Channel 8":
                            Chart.getInstance().channelUpdateWithoutColor(8, name,units);
                            break;
                    }
                }

            }
            catch(NullPointerException exe){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Channel has not been chosen");
                alert.setHeaderText("To submit channel settings you have to choose a channel");
                alert.setContentText("Please, choose a channel");
                alert.showAndWait();
            }
        }
    }

    public void onUnitKeyPressed(KeyEvent keyEvent) {
        if(keyEvent.getCode() == KeyCode.ENTER){
            try{
                if(!unitText.getText().isEmpty()){
                    units="["+unitText.getText()+"]";
                    if(!nameText.getText().isEmpty()){
                        name=nameText.getText();
                    }
                    else{
                        name="";
                    }
                }
                channel=channelBox.getSelectionModel().getSelectedItem();
                switch (channel) {
                    case "Channel 1":
                        Chart.getInstance().channelUpdateWithoutColor(1, name, units);
                        break;
                    case "Channel 2":
                        Chart.getInstance().channelUpdateWithoutColor(2, name, units);
                        break;
                    case "Channel 3":
                        Chart.getInstance().channelUpdateWithoutColor(3, name,units);
                        break;
                    case "Channel 4":
                        Chart.getInstance().channelUpdateWithoutColor(4, name,units);
                        break;
                    case "Channel 5":
                        Chart.getInstance().channelUpdateWithoutColor(5, name,units);
                        break;
                    case "Channel 6":
                        Chart.getInstance().channelUpdateWithoutColor(6, name ,units);
                        break;
                    case "Channel 7":
                        Chart.getInstance().channelUpdateWithoutColor(7, name,units);
                        break;
                    case "Channel 8":
                        Chart.getInstance().channelUpdateWithoutColor(8, name,units);
                        break;
                }
            }
            catch(NullPointerException exe){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Channel has not been chosen");
                alert.setHeaderText("To submit channel settings you have to choose a channel");
                alert.setContentText("Please, choose a channel");
                alert.showAndWait();
            }
        }
    }

    public void onPickColor(ActionEvent actionEvent) {
        try{
            color=colorPicker.getValue();
            channel=channelBox.getSelectionModel().getSelectedItem();
            switch (channel) {
                case "Channel 1":
                    Chart.getInstance().channelUpdateColor(1, color);
                    break;
                case "Channel 2":
                    Chart.getInstance().channelUpdateColor(2, color);
                    break;
                case "Channel 3":
                    Chart.getInstance().channelUpdateColor(3, color);
                    break;
                case "Channel 4":
                    Chart.getInstance().channelUpdateColor(4, color);
                    break;
                case "Channel 5":
                    Chart.getInstance().channelUpdateColor(5, color);
                    break;
                case "Channel 6":
                    Chart.getInstance().channelUpdateColor(6, color);
                    break;
                case "Channel 7":
                    Chart.getInstance().channelUpdateColor(7, color);
                    break;
                case "Channel 8":
                    Chart.getInstance().channelUpdateColor(8, color);
                    break;
            }
        }
        catch(NullPointerException exe){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Channel has not been chosen");
            alert.setHeaderText("To submit channel settings you have to choose a channel");
            alert.setContentText("Please, choose a channel");
            alert.showAndWait();
        }
    }
}
