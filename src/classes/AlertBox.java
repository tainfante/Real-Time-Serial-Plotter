package classes;

import javafx.scene.control.Alert;

public class AlertBox extends Alert
{
    public AlertBox(AlertType alertType)
    {
        super(alertType);
    }

    public void showAlertBox(String message)
    {
        showAlertBox(null, message);
    }

    public void showAlertBox(String header, String message)
    {
        setTitle("Real Time Serial Plotter");
        setHeaderText(header);
        setContentText(message);
        showAndWait();
    }
}
