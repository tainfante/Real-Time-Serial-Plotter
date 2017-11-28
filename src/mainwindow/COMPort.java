package mainwindow;

import com.fazecast.jSerialComm.SerialPort;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

import java.net.URL;
import java.util.ResourceBundle;

public class COMPort implements Initializable
{
    @FXML
    Button connectButton;

    @FXML
    ComboBox<String> selectPortComboBox, selectBaudrateComboBox;

    private static volatile COMPort PortINSTANCE;

    private SerialPort serialPort;

    private int baudRate = 115200;

    private boolean stopReading = false;

    public int getBaudRate()
    {
        return baudRate;
    }

    public static COMPort getInstance()
    {
        if (null == PortINSTANCE)
        {
            synchronized (COMPort.class)
            {
                if (null == PortINSTANCE)
                {
                    PortINSTANCE = new COMPort();
                }
            }
        }
        return PortINSTANCE;
    }

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
        selectBaudrateComboBox.getSelectionModel().select(String.valueOf(getBaudRate()));
        // ===========================================================================

    }

    public void setBaudRate(int baudRate)
    {
        this.baudRate = baudRate;

        if( null != serialPort)
            serialPort.setBaudRate(baudRate);
    }


    public boolean open(String serialPortName, int dataBits, int stopBits, int parityBits)
    {
        if(null != serialPortName)
        {
            serialPort = SerialPort.getCommPort(serialPortName);
            serialPort.setComPortParameters(baudRate, dataBits, stopBits, parityBits);

            serialPort.setComPortTimeouts(SerialPort.TIMEOUT_READ_SEMI_BLOCKING, 100, 100);

            // create new thread and listen for every byte and after that send him to display on Received Text Area in DataTabController
//            new Thread(() ->
//            {
//                byte[] oneChar = new byte[1];
//
//                while(!stopReading)
//                {
//                    if ( 0 < serialPort.readBytes(oneChar, 1) )
//                    {
//                        //dataTabInterface.displayByte(oneChar[0]);
//                    }
//                }
//            }).start();

            try
            {
                return (serialPort.openPort());
            }
            catch (Exception e){ return false; }
        }

        return false;
    }

    public boolean close()
    {
        boolean isClosed = false;

        if(null != serialPort)
        {
            isClosed = serialPort.closePort();

            if (!serialPort.isOpen())
            {
                stopReading = true;

                serialPort = null;
            }
        }

        return isClosed;
    }

    public void send(byte[] buffer)
    {
        if(null != serialPort)
        {
            if ( serialPort.isOpen() )
            {
                serialPort.writeBytes(buffer, buffer.length);
            }
        }
    }

    public String getSystemPortName()
    {
        if ( null != serialPort )
        {
            return serialPort.isOpen() ? serialPort.getSystemPortName() : "";
        }
        else
        {
            return "";
        }
    }

    public void onActionSelectPort()
    {

    }

    public void onActionSelectBaudrate()
    {

    }

    public void onActionConnectButton()
    {
        if ( connectButton.getText().equals("Connect"))
        {
            if ( null != selectPortComboBox.getSelectionModel().getSelectedItem() )
            {
                String systemPortName = selectPortComboBox.getSelectionModel().getSelectedItem();
                systemPortName = systemPortName.substring(systemPortName.indexOf("(")+1, systemPortName.indexOf(")"));

                if (open(systemPortName, 8, SerialPort.ONE_STOP_BIT, SerialPort.NO_PARITY))
                {
                    connectButton.setText("Disconnect");
                    //Logger.getInstance().log("Port is opened.");
                    //StatusBar.getInstance().setOpenedPortStatus(true);
                }
            }
        }
        else
        {
            if (close())
            {
                connectButton.setText("Connect");
                //Logger.getInstance().log("Port is opened.");
                //StatusBar.getInstance().setOpenedPortStatus(true);
            }
        }

    }
}