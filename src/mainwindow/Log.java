package mainwindow;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Log
{
    private static volatile Log LogINSTANCE;

    @FXML
    TextArea logTextArea;

    public Log()
    {
        LogINSTANCE = this;
    }

    public static Log getInstance()
    {
        return LogINSTANCE;
    }

    public void log(String text)
    {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();

        if(null != LogINSTANCE)
        {
            Platform.runLater(() ->
            {
                logTextArea.appendText("[" + dateFormat.format(date) +"] " +text);
                logTextArea.appendText("\n");
            });
        }
    }

}
