package mainwindow;

import plot.Plot;
import port.PortReader;
import com.fazecast.jSerialComm.SerialPort;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

import java.net.URL;
import java.util.ResourceBundle;

public class PortController implements Initializable
{
    @FXML
    Button connectButton;

    @FXML
    ComboBox<String> selectPortComboBox, selectBaudrateComboBox;

    private String selectedPort;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        for (SerialPort port : SerialPort.getCommPorts())
        {
            selectPortComboBox.getItems().add(port.getDescriptivePortName());
        }

        if(!selectPortComboBox.getItems().isEmpty())
        {
            selectPortComboBox.getSelectionModel().select(0);
            selectedPort = selectPortComboBox.getSelectionModel().getSelectedItem();
        }

        // ============= Baud Rate ComboBox initial values ===========================
        selectBaudrateComboBox.getItems().add("9600");
        selectBaudrateComboBox.getItems().add("14400");
        selectBaudrateComboBox.getItems().add("19200");
        selectBaudrateComboBox.getItems().add("38400");
        selectBaudrateComboBox.getItems().add("56000");
        selectBaudrateComboBox.getItems().add("57600");
        selectBaudrateComboBox.getItems().add("115200");
        selectBaudrateComboBox.getItems().add("128000");
        selectBaudrateComboBox.getItems().add("230400");
        selectBaudrateComboBox.getItems().add("256000");
        selectBaudrateComboBox.getItems().add("460800");
        selectBaudrateComboBox.getItems().add("921600");
        selectBaudrateComboBox.getItems().add("1000000");
        selectBaudrateComboBox.getItems().add("2000000");
        selectBaudrateComboBox.getItems().add("3000000");
        selectBaudrateComboBox.getSelectionModel().select(String.valueOf(PortReader.getInstance().getBaudRate()));
        // ===========================================================================

        selectPortComboBox.setOnShowing(event -> selectedPort = selectPortComboBox.getSelectionModel().getSelectedItem());
        selectPortComboBox.setOnHidden(event -> selectPortComboBox.getSelectionModel().select(selectedPort));
    }

    public void onActionPortComboBox()
    {
        if (null != selectPortComboBox.getSelectionModel().getSelectedItem())
        {
            if (null == selectedPort)
                selectedPort = "";

            if ( !selectedPort.equals(selectPortComboBox.getSelectionModel().getSelectedItem()) )
            {
                selectedPort = selectPortComboBox.getSelectionModel().getSelectedItem();
                Log.getInstance().log("Selected port: " + selectedPort);
            }
        }
    }

    public void onMouseClickedSelectPort()
    {
        selectPortComboBox.getItems().clear();

        for (SerialPort port : SerialPort.getCommPorts())
        {
            selectPortComboBox.getItems().add(port.getDescriptivePortName());
        }
    }

    public void onActionSelectBaudrate()
    {
        try
        {
            int baudRate = Integer.parseInt(selectBaudrateComboBox.getSelectionModel().getSelectedItem());

            if (3000000 < baudRate)
            {
                baudRate = 3000000;
                selectBaudrateComboBox.getSelectionModel().select(String.valueOf(baudRate));
            }

            Log.getInstance().log("Set Baudrate: " + baudRate + " bps.");

            PortReader.getInstance().setBaudRate(baudRate);
        }
        catch (Exception e){ System.out.println(e.getMessage()); }
    }

    public void onActionConnectButton()
    {
        if ( connectButton.getText().equals("Connect"))
        {
            if ( null != selectPortComboBox.getSelectionModel().getSelectedItem() )
            {
                String systemPortName = selectPortComboBox.getSelectionModel().getSelectedItem();
                systemPortName = systemPortName.substring(systemPortName.indexOf("(")+1, systemPortName.indexOf(")"));

                if (PortReader.getInstance().open(systemPortName, 8, SerialPort.ONE_STOP_BIT, SerialPort.NO_PARITY))
                {
                    connectButton.setText("Disconnect");
                    selectPortComboBox.setDisable(true);

                    Log.getInstance().log("Port is opened.");

                    PortReader.getInstance().startReading();
                    Plot.getInstance().startPlotting();
                }
            }
        }
        else
        {
            if (PortReader.getInstance().close())
            {
                connectButton.setText("Connect");
                selectPortComboBox.setDisable(false);
                Plot.getInstance().stopPlotting();

                Log.getInstance().log("Port is closed.");
            }
        }

    }
}