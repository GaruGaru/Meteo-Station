package modularity.WeatherStation.SerialCommunication;


import gnu.io.CommPortIdentifier;
import modularity.Serial.ISerial;
import modularity.Serial.ISerialListener;
import modularity.Serial.RXTX.RXTXSerialCommunicator;
import modularity.WeatherStation.Manager.Tasks.IStationTask;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Tommaso Garuglieri on 16/06/2016.
 */
public class MeteoSerial extends RXTXSerialCommunicator implements ISerial {

    private List<ISerialListener> listeners;

    private boolean connected;

    public MeteoSerial(CommPortIdentifier port, int baudRate) {
        super(port, baudRate);
        this.listeners = new LinkedList<>();
        this.connected = false;
    }

    @Override
    public void initialize() {
        if (getPort() == null)
            throw new RuntimeException("Invalid port");
    }

    @Override
    public void beginCommunication() {
        if (!connected) {
            this.startCommunication();
            this.connected = true;
        }
    }

    @Override
    public void endCommunication() {
        if (connected) {
            this.stopCommunication();
            connected = false;
        }
    }

    @Override
    public void addListener(ISerialListener... listeners) {
        Collections.addAll(this.listeners, listeners);
    }

    @Override
    public void removeListener(ISerialListener... listeners) {
        for (ISerialListener listener : listeners)
            this.listeners.remove(listener);
    }

    @Override
    public void onSerialMessage(String message) {
        listeners.forEach(l -> l.onMessage(message));
    }

    @Override
    protected void onError(Throwable error, IStationTask.ErrorType errorType) {
        listeners.forEach(l -> l.onError(error, errorType));
    }

    @Override
    protected void onConnected() {
        listeners.forEach(l -> l.onConnected(this));
    }

    @Override
    protected void onDisconnected() {
        listeners.forEach(l -> l.onDisconnected(this));
    }

}
