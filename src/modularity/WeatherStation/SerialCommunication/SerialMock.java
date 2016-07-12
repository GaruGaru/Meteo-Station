package modularity.WeatherStation.SerialCommunication;

import modularity.Serial.ISerial;
import modularity.Serial.ISerialListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Tommaso Garuglieri on 10/07/2016.
 */
public class SerialMock implements ISerial {

    private static final String message = "1/2/3/4/5/6/7/8";

    private List<ISerialListener> listeners;

    private Thread thread;

    @Override
    public void initialize() {
        this.listeners = new ArrayList<>();
    }

    @Override
    public void beginCommunication() {
        this.thread = new Thread(() -> {
            boolean running = true;
            while (running) {
                try {
                    Thread.sleep(1000);
                    listeners.forEach(l -> l.onMessage(message));
                } catch (InterruptedException e) {
                    running = false;
                }
            }
        });

        this.thread.start();

    }

    @Override
    public void endCommunication() {
        if (this.thread != null) {
            this.thread.interrupt();
            this.thread = null;
        }
    }

    @Override
    public void addListener(ISerialListener... listeners) {
        Collections.addAll(this.listeners, listeners);
    }

    @Override
    public void removeListener(ISerialListener... listeners) {

    }
}
