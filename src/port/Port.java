package port;

import purejavacomm.CommPortIdentifier;
import purejavacomm.SerialPort;
import purejavacomm.UnsupportedCommOperationException;

import java.io.IOException;

public class Port
{
    private volatile SerialPort port = null;

    private int baudRate = 1000000;

    volatile boolean stopReading = false;
    private volatile boolean isOpen = false;

    public void setStopReading(boolean stopReading)
    {
        this.stopReading = stopReading;
    }

    Port()
    {

    }

    public int getBaudRate()
    {
        return baudRate;
    }

    public void setBaudRate(int baudRate)
    {
        this.baudRate = baudRate;

        if( null != port)
        {
            try
            {
                port.setSerialPortParams(baudRate, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
            }
            catch (UnsupportedCommOperationException e)
            {
                e.printStackTrace();
            }
        }
    }

    public void send(byte[] buffer)
    {
        if(null != port)
        {
            try
            {
                port.getOutputStream().write(buffer);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }

        }
    }

    int readByte()
    {
        if(null != port)
        {
            try
            {
                return port.getInputStream().read();
            }
            catch (IOException e)
            {
                return -1;
            }
        }
        else
        {
            return -1;
        }
    }

    public boolean open(String serialPortName)
    {
        try
        {
            CommPortIdentifier portId = CommPortIdentifier.getPortIdentifier(serialPortName);
            port = (SerialPort) portId.open("RealTimeSerialPlotter", 1000);
            port.setSerialPortParams(baudRate, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);

            stopReading = false;

            isOpen = true;

            return true;
        }
        catch (Throwable thwble)
        {
            return false;
        }
    }

    public boolean close()
    {
        boolean isClosed = false;

        if(null != port)
        {
            port.close();

            stopReading = true;

            port = null;

            isOpen = false;
            isClosed = true;
        }

        return isClosed;
    }

    public boolean isOpen()
    {
        return  isOpen;
    }
}
