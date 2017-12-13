package mainwindow;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        xAxis.setLabel("Time");
        lchart.setTitle("Serial Data");
        series_1.setName("1");
        series_1.setName("2");
        series_1.setName("3");
        series_1.setName("4");
        series_1.setName("5");
        series_1.setName("6");
        series_1.setName("7");
        series_1.setName("8");
        lchart.getData().addAll(series_1,series_2,series_3,series_4,series_5,series_6,series_7,series_8);

    }

}