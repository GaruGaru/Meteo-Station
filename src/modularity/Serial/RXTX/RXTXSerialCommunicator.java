package modularity.Serial.RXTX;

import gnu.io.*;
import modularity.WeatherStation.Manager.Tasks.IStationTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;

/**
 * Created by Tommaso Garuglieri on 16/06/2016.
 */
public abstract class RXTXSerialCommunicator implements SerialPortEventListener {

    public static final int DEFAULT_BAUDRATE = 9600;
    private static final int TIME_OUT = 2000;
    private CommPortIdentifier port;

    private SerialPort serialPort;

    private BufferedReader inputStream;

    private OutputStream outputStream;

    private int baudRate;

    public RXTXSerialCommunicator(CommPortIdentifier port, int baudRate) {
        this.port = port;
        this.baudRate = baudRate;
    }

    public RXTXSerialCommunicator(CommPortIdentifier port) {
        this(port, DEFAULT_BAUDRATE);
    }

    public CommPortIdentifier getPort() {
        return port;
    }

    public void startCommunication() {

        if (this.port == null)
            throw new RuntimeException("Invalid port provided (null)");

        try {

            this.serialPort = (SerialPort) port.open(this.getClass().getName(), TIME_OUT);

            setupCommunication(serialPort);

            this.inputStream = new BufferedReader(new InputStreamReader(serialPort.getInputStream()));
            this.outputStream = serialPort.getOutputStream();

            serialPort.addEventListener(this);
            serialPort.notifyOnDataAvailable(true);

            this.onConnected();

        } catch (Exception e) {
            this.onError(e, IStationTask.ErrorType.SERIAL);
        }

    }

    private void setupCommunication(SerialPort serialPort) throws UnsupportedCommOperationException {
        serialPort.setSerialPortParams(baudRate,
                SerialPort.DATABITS_8,
                SerialPort.STOPBITS_1,
                SerialPort.PARITY_NONE);
    }

    public synchronized void stopCommunication() {
        if (serialPort != null) {
            serialPort.removeEventListener();
            serialPort.close();
        }
        this.onDisconnected();
    }

    public synchronized void send(String message) {
        if (outputStream != null) {
            try {
                outputStream.write(message.getBytes());
                outputStream.flush();
            } catch (IOException e) {
                this.onError(e, IStationTask.ErrorType.SERIAL);
            }
        }
    }

    public abstract void onSerialMessage(String message);

    protected abstract void onError(Throwable error, IStationTask.ErrorType type);

    protected abstract void onConnected();

    protected abstract void onDisconnected();


    @Override
    public void serialEvent(SerialPortEvent serialPortEvent) {
        if (serialPortEvent.getEventType() == SerialPortEvent.DATA_AVAILABLE) {
            try {
                String message = inputStream.readLine();
                this.onSerialMessage(message);
            } catch (Exception e) {
                this.onError(e, IStationTask.ErrorType.MESSAGE);
            }
        }
    }
}
