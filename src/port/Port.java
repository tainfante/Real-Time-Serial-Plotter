package port;

import com.fazecast.jSerialComm.SerialPort;

public class Port
{
    private volatile SerialPort serialPort = null;

    private int baudRate = 115200;

    volatile boolean stopReading = false;

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

        if( null != serialPort)
            serialPort.setBaudRate(baudRate);
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

    int readByte(byte[] oneChar)
    {
        if(null != serialPort)
        {
            if ( serialPort.isOpen() )
            {
                return serialPort.readBytes(oneChar, 1);
            }
            else
            {
                return -1;
            }
        }
        else
        {
            return -1;
        }
    }

    public boolean open(String serialPortName, int dataBits, int stopBits, int parityBits)
    {
        if(null != serialPortName)
        {
            serialPort = SerialPort.getCommPort(serialPortName);
            serialPort.setComPortParameters(baudRate, dataBits, stopBits, parityBits);

            serialPort.setComPortTimeouts(SerialPort.TIMEOUT_READ_BLOCKING, 1, 0);

            stopReading = false;

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
}
