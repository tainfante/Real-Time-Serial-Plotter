package port;

import classes.Frame;
import mainwindow.Log;

import java.util.ArrayList;
import java.util.concurrent.LinkedBlockingQueue;

public class PortReader extends Port
{
    //constants
    private static final short START_BYTE = 0xB4;
    private static final short STOP_BYTE = 0xD2;
    private static final short CHG_BYTE = 0xA5;

    /* maximum is 8 channels where every channel has 1 int value which value it is contained in 4 bytes,
    so all maximum number of bytes will be 4*8 + one byte of size + all possible bytes CHG  */
    private static final byte MAX_FRAME_SIZE = 68;

    private static volatile PortReader PortReaderINSTANCE;

    private ArrayList<Byte> bufferIn;
    private LinkedBlockingQueue<Frame> frameBuffer;

    private PortReader()
    {
        bufferIn = new ArrayList<>();
        frameBuffer = new LinkedBlockingQueue<>();
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

    private Frame readFrame()
    {
        short cntBytes = 0;
        int data;

        boolean listening = true;
        boolean wasStarted = false;

        Frame frame = null;

        while (listening)
        {
            data = readByte();

            if ( 0 > data )                                             // read one byte
            {
                break;
            }

            switch(data)
            {
                case START_BYTE:                    //if element == START byte then start listening again
                {
                    wasStarted = true;
                    bufferIn.clear();

                    break;
                }

                case STOP_BYTE:                     // if element == STOP byte then decode received frame
                {
                    if (!bufferIn.isEmpty())
                    {
                        bufferIn = decodePayload(bufferIn);

                        if ( CRC16.checksumIsAgree(bufferIn) )
                        {
                            System.out.println("The checksums agrees.");

                            try
                            {
                                frame = mapBufferToFrame(bufferIn);
                                System.out.println("Buffer is mapped to object of Frame type.");
                                listening = false;
                            }
                            catch (Exception e)
                            {
                                e.printStackTrace();
                                wasStarted = false;
                                bufferIn.clear();
                                cntBytes = 0;
                            }
                        }
                        else
                        {
                            Log.getInstance().log("The checksums doesn't agrees.");
                            listening = false;
                        }
                    }
                    else
                    {
                        wasStarted = false;
                    }

                    break;
                }

                default:
                {
                    if(wasStarted)                          //else save element in buffer
                    {
                        if (cntBytes < MAX_FRAME_SIZE)
                        {
                            bufferIn.add((byte)data);
                            cntBytes++;
                        }
                        else
                        {
                            bufferIn.clear();
                            cntBytes = 0;
                            wasStarted = false;
                        }
                    }
                }
            }
        }

        return frame;
    }

    private ArrayList<Byte> decodePayload(ArrayList<Byte> bufferIn)
    {
        ArrayList<Byte> data = new ArrayList<>();

        for (int i = 0; i < bufferIn.size(); i++)
        {
            if (CHG_BYTE == (bufferIn.get(i) & 0xFF))
            {
                data.add((byte)(~bufferIn.get(i + 1) & 0xFF));
                i++;
            }
            else
            {
                data.add(bufferIn.get(i));
            }
        }

        return data;
    }

    private Frame mapBufferToFrame(ArrayList<Byte> bytes) throws Exception
    {
        ArrayList<Integer> mappedValues = new ArrayList<>();

        byte numberOfChannels = bytes.get(0);

        if ( (bytes.size() - 1) < numberOfChannels * 4 )
        {
            throw new Exception("Number of channels in frame is wrong.");
        }

        for (int i = 0, k = 0; i < numberOfChannels; i++)
        {
            mappedValues.add( ((bytes.get(++k) & 0xFF) << 24) | ((bytes.get(++k) & 0xFF) << 16) | ((bytes.get(++k) & 0xFF) << 8) | (bytes.get(++k) & 0xFF) );
        }

        return new Frame(mappedValues);
    }

    public void startReading()
    {
        // create a new thread and listen for frame, then add to frame buffer
        Thread readingThread = new Thread(() ->
        {
            System.out.println("Started reading thread.");

            while (!stopReading)
            {
                try
                {
                    Frame frame = readFrame();

                    if ( null != frame )
                    {
                        frameBuffer.put(frame);
                        System.out.println("New frame is added to the queue.");
                    }
                    else
                    {
                        System.out.println("Read frame is a null.");
                    }
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }

            System.out.println("Stopped reading thread.");
        });
        readingThread.setName("FrameReadingThread");
        readingThread.start();
    }

    public LinkedBlockingQueue<Frame> getFrameBuffer()
    {
        return frameBuffer;
    }
}
