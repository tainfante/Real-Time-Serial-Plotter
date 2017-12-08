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

    private Thread readingThread = null;

    private ArrayList<Byte> bufferIn;

    private PortReader()
    {
        bufferIn = new ArrayList<>();
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

    private int readFrame()
    {                                         // variable for read one byte
        short cntBytes = 0;
        int readedBytes = 0;
        byte[] element = new byte[1];

        boolean listening = true;
        boolean wasStarted = false;

        bufferIn.clear();

        while (listening)
        {
            if ( 0 > readByte(element) )                                    // read one byte
            {
                break;
            }

            if ((STOP_BYTE & 0xFF) == (element[0] & 0xFF))             // if element == STOP byte then decode received frame
            {
                if (!bufferIn.isEmpty())
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

                    // store in linkedBlockedQueue

                    listening = false;
                }

                wasStarted = false;
            }
            else if ((START_BYTE & 0xFF) == (element[0] & 0xFF))       //if element == START byte then start listening again
            {
                wasStarted = true;
                bufferIn.clear();
            }
            else if(wasStarted)                                        //else save element in buffer
            {
                if (cntBytes < MAX_FRAME_SIZE)
                {
                    bufferIn.add(element[0]);
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

        readingThread = new Thread(() ->
        {
            System.out.println("Started reading thread.");

            while(!stopReading)
            {
                int readedBytes;

                if ( (readedBytes = readFrame()) > 0)
                {
                    Log.getInstance().log("Overall received and decoded number of bytes: " + readedBytes);
                }
            }

            System.out.println("Stopped reading thread.");
        });
        readingThread.setName("FrameReadingThread");
        readingThread.start();
    }
}
