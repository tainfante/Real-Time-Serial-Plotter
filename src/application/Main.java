package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import mainwindow.CaptureController;
import plot.Plot;
import port.PortReader;

public class Main extends Application
{
    private static Stage pStage;

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        Parent root = FXMLLoader.load(getClass().getResource("/mainwindow/mainWindowLayout.fxml"));
        primaryStage.setTitle("Real Time Serial Plotter");
        primaryStage.setScene(new Scene(root, 640, 480));
        primaryStage.setMinHeight(450.0);
        primaryStage.setMinWidth(620.0);

        Image applicationIcon = new Image(getClass().getResourceAsStream("icon.png"));
        primaryStage.getIcons().add(applicationIcon);

        primaryStage.setOnCloseRequest(event ->
        {
            CaptureController.isActiveExport = false;
            PortReader.getInstance().setStopReading(true);
            Plot.getInstance().setStopPlotting(true);
        });

        primaryStage.show();
    }


    public static void main(String[] args)
    {
        launch(args);
    }

    public static Stage getPrimaryStage() {
        return pStage;
    }
}
