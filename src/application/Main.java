package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import port.PortReader;

public class Main extends Application
{

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        Parent root = FXMLLoader.load(getClass().getResource("/mainwindow/mainWindowLayout.fxml"));
        primaryStage.setTitle("Real Time Serial Plotter");
        primaryStage.setScene(new Scene(root, 640, 480));
        primaryStage.setMinHeight(450.0);
        primaryStage.setMinWidth(620.0);

        primaryStage.setOnCloseRequest(event -> PortReader.getInstance().setStopReading(true));

        primaryStage.show();
    }


    public static void main(String[] args)
    {
        launch(args);
    }
}
