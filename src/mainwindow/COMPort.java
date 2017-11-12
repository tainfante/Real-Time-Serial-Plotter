package mainwindow;

import javafx.scene.control.Toggle;
import jssc.SerialPort;
import jssc.SerialPortException;
import jssc.SerialPortTimeoutException;

public class COMPort
{
    private static volatile COMPort PortINSTANCE;

    private SerialPort serialPort;

    private int baudRate = 115200;
    private int dataBits;
    private int stopBits;
    private int parityBits;

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

    public void setBaudRate(int baudRate)
    {
        if( null != serialPort)
        {
            this.baudRate = baudRate;

            updateParams();
        }
    }

    public void setParity(Toggle newValue)
    {
        if( null != serialPort )
        {
            this.parityBits = (int) newValue.getUserData();

            updateParams();
        }
    }

    public void setNumDataBits(Toggle newValue)
    {
        if( null != serialPort )
        {
            this.dataBits = (int)newValue.getUserData();

            updateParams();
        }
    }

    public void setNumStopBits(Toggle newValue)
    {
        if( null != serialPort )
        {
            this.stopBits = (int) newValue.getUserData();

            updateParams();
        }
    }

    public boolean open(String serialPortName, int dataBits, int stopBits, int parityBits)
    {
        this.dataBits = dataBits;
        this.stopBits = stopBits;
        this.parityBits = parityBits;

        if(null != serialPortName)
        {
            serialPort = new SerialPort(serialPortName);
            try
            {
                serialPort.setParams(baudRate, dataBits, stopBits, parityBits);
            }
            catch (SerialPortException e)
            {
                e.printStackTrace();
            }

            // create new thread and listen for every byte and after that send him to display on Received Text Area in DataTabController
            new Thread(() ->
            {
                byte[] oneChar;

                while(!stopReading)
                {
                    try
                    {
                        oneChar = serialPort.readBytes(1, 1);
                        //dataTabInterface.displayByte(oneChar[0]);
                    }
                    catch (SerialPortException | SerialPortTimeoutException e)
                    {
                        e.printStackTrace();
                    }
                }
            }).start();

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
        boolean isOpened = false;

        if(null != serialPort)
        {
            try
            {
                isOpened = serialPort.closePort();
            }
            catch (SerialPortException e)
            {
                e.printStackTrace();
            }

            if (!serialPort.isOpened())
            {
                stopReading = true;

                serialPort = null;
            }
        }

        return isOpened;
    }

    public void send(byte[] buffer)
    {
        if(null != serialPort)
        {
            if ( serialPort.isOpened() )
            {
                try
                {
                    serialPort.writeBytes(buffer);
                }
                catch (SerialPortException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }

    public String getSystemPortName()
    {
        if ( null != serialPort )
        {
            return serialPort.isOpened() ? serialPort.getPortName() : "";
        }
        else
        {
            return "";
        }
    }

    private boolean updateParams()
    {
        try
        {
            serialPort.setParams(baudRate, dataBits, stopBits, parityBits);
        }
        catch (SerialPortException e)
        {
            e.printStackTrace();

            return false;
        }

        return true;
    }
}