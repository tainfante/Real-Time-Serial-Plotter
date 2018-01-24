package mainwindow;

import classes.DateAxis;
import classes.Frame;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import plot.Plot;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;

public class Chart implements Initializable {

    private static volatile Chart ChartINSTANCE;
    @FXML
    LineChart<Date, Number> lchart;
    public static XYChart.Series<Date, Number> series1 = new XYChart.Series<>();
    public static XYChart.Series<Date, Number> series2 = new XYChart.Series<>();
    public static XYChart.Series<Date, Number> series3 = new XYChart.Series<>();
    public static XYChart.Series<Date, Number> series4 = new XYChart.Series<>();
    public static XYChart.Series<Date, Number> series5 = new XYChart.Series<>();
    public static XYChart.Series<Date, Number> series6 = new XYChart.Series<>();
    public static XYChart.Series<Date, Number> series7 = new XYChart.Series<>();
    public static XYChart.Series<Date, Number> series8 = new XYChart.Series<>();
    @FXML
    private DateAxis xAxis = new DateAxis();
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
    public TextField sampleText;
    @FXML
    private ComboBox<String> variableBox;
    @FXML
    private TextField minText;
    @FXML
    private TextField maxText;

    private int samples=200;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        sampleText.setPromptText("200");
        lchart.setTitle("Serial Data");
        lchart.getStyleClass().add("title");
        lchart.setCreateSymbols(false);
        checkOne.setSelected(true);
        checkTwo.setSelected(true);
        checkThree.setSelected(true);
        checkFour.setSelected(true);
        checkFive.setSelected(true);
        checkSix.setSelected(true);
        checkSeven.setSelected(true);
        checkEight.setSelected(true);
        lchart.getData().addAll(series1, series2, series3, series4, series5, series6, series7, series8);
        lchart.setLegendVisible(false);
        variableBox.getItems().addAll("8 bits", "16 bits", "32 bits");
        yAxis.setForceZeroInRange(false);
        xAxis.setAutoRanging(true);
        xAxis.setTickLabelsVisible(false);

        //Styling chart with colors//

        Node line1 = series1.getNode().lookup(".chart-series-line");
        Node line2 = series2.getNode().lookup(".chart-series-line");
        Node line3 = series3.getNode().lookup(".chart-series-line");
        Node line4 = series4.getNode().lookup(".chart-series-line");
        Node line5 = series5.getNode().lookup(".chart-series-line");
        Node line6 = series6.getNode().lookup(".chart-series-line");
        Node line7 = series7.getNode().lookup(".chart-series-line");
        Node line8 = series8.getNode().lookup(".chart-series-line");

        Color color1 = Color.RED;
        Color color2 = Color.BLUE;
        Color color3 = Color.BLACK;
        Color color4 = Color.GRAY;
        Color color5 = Color.GREEN;
        Color color6 = Color.DARKBLUE;
        Color color7 = Color.VIOLET;
        Color color8 = Color.ORANGE;

        String rgb1 = String.format("%d, %d, %d",
                (int) (color1.getRed() * 255),
                (int) (color1.getGreen() * 255),
                (int) (color1.getBlue() * 255));
        String rgb2 = String.format("%d, %d, %d",
                (int) (color2.getRed() * 255),
                (int) (color2.getGreen() * 255),
                (int) (color2.getBlue() * 255));
        String rgb3 = String.format("%d, %d, %d",
                (int) (color3.getRed() * 255),
                (int) (color3.getGreen() * 255),
                (int) (color3.getBlue() * 255));
        String rgb4 = String.format("%d, %d, %d",
                (int) (color4.getRed() * 255),
                (int) (color4.getGreen() * 255),
                (int) (color4.getBlue() * 255));
        String rgb5 = String.format("%d, %d, %d",
                (int) (color5.getRed() * 255),
                (int) (color5.getGreen() * 255),
                (int) (color5.getBlue() * 255));
        String rgb6 = String.format("%d, %d, %d",
                (int) (color6.getRed() * 255),
                (int) (color6.getGreen() * 255),
                (int) (color6.getBlue() * 255));
        String rgb7 = String.format("%d, %d, %d",
                (int) (color7.getRed() * 255),
                (int) (color7.getGreen() * 255),
                (int) (color7.getBlue() * 255));
        String rgb8 = String.format("%d, %d, %d",
                (int) (color8.getRed() * 255),
                (int) (color8.getGreen() * 255),
                (int) (color8.getBlue() * 255));

        line1.setStyle("-fx-stroke: rgba(" + rgb1 + ", 1.0);");
        line2.setStyle("-fx-stroke: rgba(" + rgb2 + ", 1.0);");
        line3.setStyle("-fx-stroke: rgba(" + rgb3 + ", 1.0);");
        line4.setStyle("-fx-stroke: rgba(" + rgb4 + ", 1.0);");
        line5.setStyle("-fx-stroke: rgba(" + rgb5 + ", 1.0);");
        line6.setStyle("-fx-stroke: rgba(" + rgb6 + ", 1.0);");
        line7.setStyle("-fx-stroke: rgba(" + rgb7 + ", 1.0);");
        line8.setStyle("-fx-stroke: rgba(" + rgb8 + ", 1.0);");

        checkOne.setStyle("-fx-text-fill:rgb(" + rgb1 + ");");
        checkTwo.setStyle("-fx-text-fill:rgb(" + rgb2 + ");");
        checkThree.setStyle("-fx-text-fill:rgb(" + rgb3 + ");");
        checkFour.setStyle("-fx-text-fill:rgb(" + rgb4 + ");");
        checkFive.setStyle("-fx-text-fill:rgb(" + rgb5 + ");");
        checkSix.setStyle("-fx-text-fill:rgb(" + rgb6 + ");");
        checkSeven.setStyle("-fx-text-fill:rgb(" + rgb7 + ");");
        checkEight.setStyle("-fx-text-fill:rgb(" + rgb8 + ");");

        //////////////////////////////////////////////////////////

    }

    public Chart() {
        ChartINSTANCE = this;
    }

    public static Chart getInstance() {
        return ChartINSTANCE;
    }

    public void onSubmitChanges(ActionEvent actionEvent) {
        String MIN = minText.getText();
        String MAX = maxText.getText();
        double min;
        double max;
        try {
            min = Double.parseDouble(MIN);
            max = Double.parseDouble(MAX);
            if (min > max) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Wrong data");
                alert.setHeaderText("You have submitted wrong data");
                alert.setContentText("Minimum value must be lower then the maximum");
                alert.showAndWait();
            }
            yAxis.setAutoRanging(false);
            yAxis.setLowerBound(min);
            yAxis.setUpperBound(max);
        } catch (NumberFormatException nfe) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Wrong data type");
            alert.setHeaderText("You have submitted wrong data");
            alert.setContentText("Please, set some numbers");
            alert.showAndWait();
        }

    }

    public void onChooseVariable(ActionEvent actionEvent) {
        String variableType = variableBox.getSelectionModel().getSelectedItem();
        if (variableType.equals("8 bits")) {
            yAxis.setAutoRanging(false);
            yAxis.setLowerBound(-128);
            yAxis.setUpperBound(127);

        } else if (variableType.equals("16 bits")) {
            yAxis.setAutoRanging(false);
            yAxis.setLowerBound(-32768);
            yAxis.setUpperBound(32767);
        } else if (variableType.equals("32 bits")) {
            yAxis.setAutoRanging(false);
            yAxis.setLowerBound(-2147483648);
            yAxis.setUpperBound(2147483647);
        } else if (variableType.isEmpty()) {
            yAxis.setAutoRanging(true);
        }
    }

    void channelUpdate(int channelNumber, String name, Color color) {

        String rgb = String.format("%d, %d, %d",
                (int) (color.getRed() * 255),
                (int) (color.getGreen() * 255),
                (int) (color.getBlue() * 255));

        if (null != ChartINSTANCE) {
            switch (channelNumber) {
                case 1:
                    checkOne.setText(name);
                    checkOne.setStyle("-fx-text-fill:rgb(" + rgb + ");");
                    Node line1 = series1.getNode().lookup(".chart-series-line");
                    line1.setStyle("-fx-stroke: rgba(" + rgb + ", 1.0);");
                    break;
                case 2:
                    checkTwo.setText(name);
                    checkTwo.setStyle("-fx-text-fill:rgb(" + rgb + ");");
                    Node line2 = series2.getNode().lookup(".chart-series-line");
                    line2.setStyle("-fx-stroke: rgba(" + rgb + ", 1.0);");
                    break;
                case 3:
                    checkThree.setText(name);
                    checkThree.setStyle("-fx-text-fill:rgb(" + rgb + ");");
                    Node line3 = series3.getNode().lookup(".chart-series-line");
                    line3.setStyle("-fx-stroke: rgba(" + rgb + ", 1.0);");
                    break;
                case 4:
                    checkFour.setText(name);
                    checkFour.setStyle("-fx-text-fill:rgb(" + rgb + ");");
                    Node line4 = series4.getNode().lookup(".chart-series-line");
                    line4.setStyle("-fx-stroke: rgba(" + rgb + ", 1.0);");
                    break;
                case 5:
                    checkFive.setText(name);
                    checkFive.setStyle("-fx-text-fill:rgb(" + rgb + ");");
                    Node line5 = series5.getNode().lookup(".chart-series-line");
                    line5.setStyle("-fx-stroke: rgba(" + rgb + ", 1.0);");
                    break;
                case 6:
                    checkSix.setText(name);
                    checkSix.setStyle("-fx-text-fill:rgb(" + rgb + ");");
                    Node line6 = series6.getNode().lookup(".chart-series-line");
                    line6.setStyle("-fx-stroke: rgba(" + rgb + ", 1.0);");
                    break;
                case 7:
                    checkSeven.setText(name);
                    checkSeven.setStyle("-fx-text-fill:rgb(" + rgb + ");");
                    Node line7 = series7.getNode().lookup(".chart-series-line");
                    line7.setStyle("-fx-stroke: rgba(" + rgb + ", 1.0);");
                    break;
                case 8:
                    checkEight.setText(name);
                    checkEight.setStyle("-fx-text-fill:rgb(" + rgb + ");");
                    Node line8 = series8.getNode().lookup(".chart-series-line");
                    line8.setStyle("-fx-stroke: rgba(" + rgb + ", 1.0);");
                    break;
            }
        }

    }

    public void onCheck1(ActionEvent actionEvent) {
        if (!checkOne.isSelected()) {
            lchart.getData().remove(series1);
        } else {
            if (!lchart.getData().contains(series1)) {
                lchart.getData().add(series1);
                channelUpdate(1, checkOne.getText(), Color.RED);
            }
        }
    }

    public void onCheck2(ActionEvent actionEvent) {
        if (!checkTwo.isSelected()) {
            lchart.getData().remove(series2);
        } else {
            if (!lchart.getData().contains(series2)) {
                lchart.getData().add(series2);
                channelUpdate(2, checkTwo.getText(), Color.BLUE);
            }
        }
    }

    public void onCheck3(ActionEvent actionEvent) {
        if (!checkThree.isSelected()) {
            lchart.getData().remove(series3);
        } else {
            if (!lchart.getData().contains(series3)) {
                lchart.getData().add(series3);
                channelUpdate(3, checkThree.getText(), Color.BLACK);
            }
        }
    }

    public void onCheck4(ActionEvent actionEvent) {
        if (!checkFour.isSelected()) {
            lchart.getData().remove(series4);
        } else {
            if (!lchart.getData().contains(series4)) {
                lchart.getData().add(series4);
                channelUpdate(4, checkFour.getText(), Color.GRAY);
            }
        }
    }

    public void onCheck5(ActionEvent actionEvent) {
        if (!checkFive.isSelected()) {
            lchart.getData().remove(series5);
        } else {
            if (!lchart.getData().contains(series5)) {
                lchart.getData().add(series5);
                channelUpdate(5, checkFive.getText(), Color.GREEN);
            }
        }
    }

    public void onCheck6(ActionEvent actionEvent) {
        if (!checkSix.isSelected()) {
            lchart.getData().remove(series6);
        } else {
            if (!lchart.getData().contains(series6)) {
                lchart.getData().add(series6);
                channelUpdate(6, checkSix.getText(), Color.DARKBLUE);
            }
        }
    }

    public void onCheck7(ActionEvent actionEvent) {
        if (!checkSeven.isSelected()) {
            lchart.getData().remove(series7);
        } else {
            if (!lchart.getData().contains(series7)) {
                lchart.getData().add(series7);
                channelUpdate(7, checkSeven.getText(), Color.VIOLET);
            }
        }
    }

    public void onCheck8(ActionEvent actionEvent) {
        if (!checkEight.isSelected()) {
            lchart.getData().remove(series8);
        } else {
            if (!lchart.getData().contains(series8)) {
                lchart.getData().add(series8);
                channelUpdate(8, checkEight.getText(), Color.ORANGE);
            }
        }
    }

    public int numberOfSamples(){
        return samples;
    }

    public boolean update(){

        if (series1.getData().sorted().size() > samples) {
            return true;
        }
        else{
            return false;
        }
    }

    public void onKeyPressed(KeyEvent keyEvent) {
        if(keyEvent.getCode() == KeyCode.ENTER){
            String selectedTime = sampleText.getText();

            try{
                samples = Integer.parseInt(selectedTime);
            }
            catch(NumberFormatException num){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Wrong data type");
                alert.setHeaderText("You have submitted wrong data");
                alert.setContentText("Please, set some numbers");
                alert.showAndWait();
                samples=200;
            }

            if (series1.getData().sorted().size() > samples) {
                try {
                    series1.getData().remove(0, (series1.getData().size() - samples));
                    series2.getData().remove(0, (series2.getData().size() - samples));
                    series3.getData().remove(0, (series3.getData().size() - samples));
                    series4.getData().remove(0, (series4.getData().size() - samples));
                    series5.getData().remove(0, (series5.getData().size() - samples));
                    series6.getData().remove(0, (series6.getData().size() - samples));
                    series7.getData().remove(0, (series7.getData().size() - samples));
                    series8.getData().remove(0, (series8.getData().size() - samples));
                }
                catch (NullPointerException nul) {
                    System.out.println("No such data in the Series");
                }
            }
        }
    }
}