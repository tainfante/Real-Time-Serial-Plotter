package port;

import mainwindow.Log;

import java.util.ArrayList;

public class PortReader extends Port
{
    //constants
    private static final byte START_BYTE = (byte) 0xB4;
    private static final byte STOP_BYTE = (byte) 0xD2;
    private static final byte CHG_BYTE = (byte) 0xA5;

    private static final byte MAX_FRAME_SIZE = 100;

    private static volatile PortReader PortReaderINSTANCE;

    private ArrayList<Byte> bufferIn;

    private PortReader()
    {

    }

    public static PortReader getInstance()
    {
        if (null == PortReaderINSTANCE)
        {
            synchronized (PortReader.class)
            {
                if (null == PortReaderINSTANCE)
                {
                    PortReaderINSTANCE = new PortReader();
                }
            }
        }
        return PortReaderINSTANCE;
    }

    private int readFrame() throws Exception
    {
        byte element = 0;                                           // variable for read one byte
        short cntBytes = 0;
        int readedBytes = 0;

        boolean listening = true;

        while ((START_BYTE & 0xFF) != (element & 0xFF))             // & 0xFF <- to obtain a unsigned value
        {
                element = readByte();
        }

        bufferIn.clear();

        while (listening)
        {
            element = readByte();                                   // read one byte

            if ((STOP_BYTE & 0xFF) == (element & 0xFF))             // if element == STOP byte then decode received frame
            {
                if (0 != bufferIn.size())
                {
                    // ========================== for debug =====================================
                    StringBuilder receivedData = new StringBuilder("Received data: ");

                    for (Byte el : bufferIn)
                    {
                        Short t = (short)(el & 0xFF);

                        receivedData.append(t.toString()).append(" ");
                    }

                    Log.getInstance().log(receivedData.toString());
                    // ============================================================================


                    readedBytes = decodePayload();


                    // place for interface to handle of data
                }

                listening = false;
            }
            else if ((START_BYTE & 0xFF) == (element & 0xFF))       //if element == START byte then start listening again
            {
                bufferIn.clear();
            }
            else                                                    //else save element in buffer
            {
                if (cntBytes < MAX_FRAME_SIZE)
                {
                    bufferIn.add(element);
                    cntBytes++;
                }
                else
                {
                    bufferIn.clear();
                    cntBytes = 0;

                    listening = false;
                }
            }
        }

        return readedBytes;
    }

    private int decodePayload()
    {
        ArrayList<Byte> data = new ArrayList<>();

        for (int i = 0; i < bufferIn.size(); i++)
        {
            if ((CHG_BYTE & 0xFF) == (bufferIn.get(i) & 0xFF))
            {
                data.add((byte)(~bufferIn.get(i + 1) & 0xFF));
                i++;
            }
            else
            {
                data.add(bufferIn.get(i));
            }
        }

        bufferIn.clear();
        bufferIn.addAll(data);

        // ========================== for debug ================================
        StringBuilder decodedData = new StringBuilder("Decoded data: ");

        for (Byte el : bufferIn)
        {
            Short t = (short)(el & 0xFF);

            decodedData.append(t.toString()).append(" ");
        }

        Log.getInstance().log(decodedData.toString());
        // ======================================================================

        return bufferIn.size();
    }

    public void startReading()
    {
        // create a new thread and listen for frame, then display it in Log tab
        Thread thread = new Thread(() ->
        {
            System.out.println("Started reading thread.");

            bufferIn = new ArrayList<>();

            while(!stopReading)
            {
                try
                {
                    Log.getInstance().log("Overall received and decoded number of bytes: " + readFrame());
                }
                catch (Exception e)
                {
                    break;
                }
            }

            System.out.println("Stopped reading thread.");

        });
        thread.setName("Reading Thread");
        thread.start();
    }
}
