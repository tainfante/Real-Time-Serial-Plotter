package mainwindow;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
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
        String channel=new String();
        String name=nameText.getText();
        String units=unitText.getText();
        Color color=colorPicker.getValue();
        try{
            channel=(String)channelBox.getSelectionModel().getSelectedItem();
            if(channel.equals("Channel 1")){
                Chart.getInstance().channelUpdate(1,name+" ["+units+"]",color);
            }
            else if(channel.equals("Channel 2")){
                Chart.getInstance().channelUpdate(2,name+" ["+units+"]",color);
            }
            else if(channel.equals("Channel 3")){
                Chart.getInstance().channelUpdate(3,name+" ["+units+"]", color);
            }
            else if(channel.equals("Channel 4")){
                Chart.getInstance().channelUpdate(4,name+" ["+units+"]", color);
            }
            else if(channel.equals("Channel 5")){
                Chart.getInstance().channelUpdate(5,name+" ["+units+"]", color);
            }
            else if(channel.equals("Channel 6")){
                Chart.getInstance().channelUpdate(6,name+" ["+units+"]", color);
            }
            else if(channel.equals("Channel 7")){
                Chart.getInstance().channelUpdate(7,name+" ["+units+"]", color);
            }
            else if(channel.equals("Channel 8")){
                Chart.getInstance().channelUpdate(8,name+" ["+units+"]", color);
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
