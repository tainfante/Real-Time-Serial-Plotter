package classes;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.*;

public class AlertBox
{
    private AlertType type;

    public AlertBox(AlertType type)
    {
        this.type = type;
    }

    public void showAlertBox(String message)
    {
        showAlertBox(null, message);
    }

    public void showAlertBox(String header, String message)
    {
        Alert alert = new Alert(type);

        alert.setTitle("Real Time Serial Plotter");
        alert.setHeaderText(header);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
