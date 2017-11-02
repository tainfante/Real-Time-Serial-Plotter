package application;

import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main implements SerialPortEventListener {
    SerialPort serialPort;
    // Na windowsie domyślnie posługujemy się portem COM3
    private static final String PORT_NAME = "COM3";

    private BufferedReader input;

    /** Milliseconds to block while waiting for port open */
    private static final int TIME_OUT = 2000;
    /** Default bits per second for COM port. */
    private static final int DATA_RATE = 9600;

    public void initialize() {
        CommPortIdentifier portId;
        try {
            portId = CommPortIdentifier.getPortIdentifier(PORT_NAME);

            // otwieramy i konfigurujemy port
            serialPort = (SerialPort) portId.open(this.getClass().getName(),
                    TIME_OUT);
            serialPort.setSerialPortParams(DATA_RATE, SerialPort.DATABITS_8,
                    SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);

            // strumień wejścia
            input = new BufferedReader(new InputStreamReader(
                    serialPort.getInputStream()));

            // dodajemy słuchaczy zdarzeń
            serialPort.addEventListener(this);
            serialPort.notifyOnDataAvailable(true);
        } catch (Exception e) {
            System.err.println(e.toString());
        }
    }

    public synchronized void close() {
        if (serialPort != null) {
            serialPort.removeEventListener();
            serialPort.close();
        }
    }

    /**
     * Metoda nasłuchuje na dane na wskazanym porcie i wyświetla je w konsoli
     */
    public synchronized void serialEvent(SerialPortEvent oEvent) {
        if (oEvent.getEventType() == SerialPortEvent.DATA_AVAILABLE) {
            try {
                String inputLine = input.readLine();
                System.out.println(inputLine);
            } catch (Exception e) {
                System.err.println(e.toString());
            }
        }
    }

    public static void main(String[] args) throws Exception {
        Main main = new Main();
        main.initialize();
        Thread t = new Thread() {
            public void run() {
                try {
                    Thread.sleep(1000000);
                    main.close();
                } catch (InterruptedException ie) {
                }
            }
        };
        t.start();
        System.out.println("Started");
    }
}