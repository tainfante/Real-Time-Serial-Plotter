package mainwindow;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;

import java.net.URL;
import java.util.ResourceBundle;

public class Chart implements Initializable {
    @FXML
    LineChart<String, Number> lchart;
    public static XYChart.Series series_1 = new XYChart.Series();
    public static XYChart.Series series_2 = new XYChart.Series();
    public static XYChart.Series series_3 = new XYChart.Series();
    public static XYChart.Series series_4 = new XYChart.Series();
    public static XYChart.Series series_5 = new XYChart.Series();
    public static XYChart.Series series_6 = new XYChart.Series();
    public static XYChart.Series series_7 = new XYChart.Series();
    public static XYChart.Series series_8 = new XYChart.Series();
    @FXML
    CategoryAxis xAxis = new CategoryAxis();
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        xAxis.setLabel("Time");
        lchart.setTitle("Serial Data");
        lchart.getStyleClass().add("title");
        series_1.setName("Channel 1");
        series_2.setName("Channel 2");
        series_3.setName("Channel 3");
        series_4.setName("Channel 4");
        series_5.setName("Channel 5");
        series_6.setName("Channel 6");
        series_7.setName("Channel 7");
        series_8.setName("Channel 8");
        lchart.getData().addAll(series_1,series_2,series_3,series_4,series_5,series_6,series_7,series_8);
        lchart.setLegendVisible(false);



    }

}