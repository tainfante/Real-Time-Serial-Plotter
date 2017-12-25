package mainwindow;

import classes.DateAxis;
<<<<<<< HEAD
=======
import javafx.event.ActionEvent;
>>>>>>> d308831
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.*;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
<<<<<<< HEAD
=======
import javafx.scene.control.TextField;
>>>>>>> d308831

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class Chart implements Initializable {
    @FXML
    LineChart<Date, Number> lchart;
    public static XYChart.Series series1 = new XYChart.Series();
    public static XYChart.Series series2 = new XYChart.Series();
    public static XYChart.Series series3 = new XYChart.Series();
    public static XYChart.Series series4 = new XYChart.Series();
    public static XYChart.Series series5 = new XYChart.Series();
    public static XYChart.Series series6 = new XYChart.Series();
    public static XYChart.Series series7 = new XYChart.Series();
    public static XYChart.Series series8 = new XYChart.Series();
    @FXML
    public static DateAxis xAxis = new DateAxis();
    @FXML
    NumberAxis yAxis = new NumberAxis();
    @FXML
    public CheckBox checkOne;
    @FXML
    public CheckBox checkTwo;
    @FXML
    public CheckBox checkThree;
    @FXML
    public CheckBox checkFour;
    @FXML
    public CheckBox checkFive;
    @FXML
    public CheckBox checkSix;
    @FXML
    public CheckBox checkSeven;
    @FXML
    public CheckBox checkEight;
    @FXML
    private ChoiceBox timeBox;
    @FXML
    private TextField minText;
    @FXML
    private TextField maxText;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        xAxis.setLabel("Time");
        lchart.setTitle("Serial Data");
        lchart.getStyleClass().add("title");
        lchart.setCreateSymbols(false);
        series1.setName("Channel 1");
        series2.setName("Channel 2");
        series3.setName("Channel 3");
        series4.setName("Channel 4");
        series5.setName("Channel 5");
        series6.setName("Channel 6");
        series7.setName("Channel 7");
        series8.setName("Channel 8");
        lchart.getData().addAll(series1,series2,series3,series4,series5,series6,series7,series8);
        lchart.setLegendVisible(false);
        timeBox.getItems().add("1 hour");
        timeBox.getItems().add("30 minutes");
        timeBox.getItems().add("10 minutes");
        timeBox.getItems().add("5 minutes");
        timeBox.getItems().add("1 minute");
        yAxis.setForceZeroInRange(false);

    }

<<<<<<< HEAD
=======
    public void onSubmitChanges(ActionEvent actionEvent) {
        String MIN=minText.getText();
        String MAX=maxText.getText();
        double min=0;
        double max=65000;
        try
        {
            min = Double.parseDouble(MIN);
            max = Double.parseDouble(MAX);
        }
        catch(NumberFormatException nfe)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Wrong data type");
            alert.setHeaderText("You have submitted wrong data");
            alert.setContentText("Please, set some numbers");
            alert.showAndWait();
        }
        yAxis.setAutoRanging(false);
        yAxis.setLowerBound(min);
        yAxis.setUpperBound(max);
    }
>>>>>>> d308831
}