package mainwindow;

import classes.AlertBox;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
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

    private String name;
    private String units;

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

            name = nameText.getText();
            updateChannel();
        }
    }

    public void onUnitKeyPressed(KeyEvent keyEvent) {
        if(keyEvent.getCode() == KeyCode.ENTER){

            if(!unitText.getText().isEmpty()){
                units="["+unitText.getText()+"]";
            }

            updateChannel();
        }
    }

    public void onPickColor() {

        Color color = colorPicker.getValue();

        if (isChannelChosen())
        {
            switch (getChosenChannel())
            {
                case "Channel 1":
                    Chart.getInstance().channelUpdate(1, color);
                    break;
                case "Channel 2":
                    Chart.getInstance().channelUpdate(2, color);
                    break;
                case "Channel 3":
                    Chart.getInstance().channelUpdate(3, color);
                    break;
                case "Channel 4":
                    Chart.getInstance().channelUpdate(4, color);
                    break;
                case "Channel 5":
                    Chart.getInstance().channelUpdate(5, color);
                    break;
                case "Channel 6":
                    Chart.getInstance().channelUpdate(6, color);
                    break;
                case "Channel 7":
                    Chart.getInstance().channelUpdate(7, color);
                    break;
                case "Channel 8":
                    Chart.getInstance().channelUpdate(8, color);
                    break;
            }
        }
        else
        {
            new AlertBox(Alert.AlertType.ERROR).showAlertBox("To submit channel settings you have to choose a channel");
        }
    }

    private void updateChannel()
    {
        if (isChannelChosen())
        {
            switch (getChosenChannel()) {
                case "Channel 1":
                    Chart.getInstance().channelUpdate(1, name, units);
                    break;
                case "Channel 2":
                    Chart.getInstance().channelUpdate(2, name, units);
                    break;
                case "Channel 3":
                    Chart.getInstance().channelUpdate(3, name,units);
                    break;
                case "Channel 4":
                    Chart.getInstance().channelUpdate(4, name,units);
                    break;
                case "Channel 5":
                    Chart.getInstance().channelUpdate(5, name,units);
                    break;
                case "Channel 6":
                    Chart.getInstance().channelUpdate(6, name ,units);
                    break;
                case "Channel 7":
                    Chart.getInstance().channelUpdate(7, name,units);
                    break;
                case "Channel 8":
                    Chart.getInstance().channelUpdate(8, name,units);
                    break;
            }
        }
        else
        {
            new AlertBox(Alert.AlertType.ERROR).showAlertBox("To submit channel settings you have to choose a channel");
        }
    }

    private boolean isChannelChosen()
    {
        return null != channelBox.getSelectionModel().getSelectedItem();
    }

    private String getChosenChannel()
    {
        return channelBox.getSelectionModel().getSelectedItem();
    }
}
