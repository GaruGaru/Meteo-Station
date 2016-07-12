package modularity.WeatherStation.Manager;


import modularity.Interfaces.IMessageDecoder;
import modularity.Logging.Logger;
import modularity.Serial.ISerial;
import modularity.Serial.ISerialListener;
import modularity.WeatherStation.Configuration.MeteoConfiguration;
import modularity.WeatherStation.Manager.Tasks.IStationTask;
import modularity.WeatherStation.MessageDecoder.WeatherEntry;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Tommaso Garuglieri on 16/06/2016.
 */
public class WeatherStation implements ISerialListener {

    private MeteoConfiguration configuration;

    private ISerial serialCommunicator;
    private IMessageDecoder<String, WeatherEntry> messageDecoder;

    private List<IStationTask> tasks;

    public WeatherStation() {
        this.tasks = new LinkedList<>();
    }

    public void setMessageDecoder(IMessageDecoder<String, WeatherEntry> messageDecoder) {
        this.messageDecoder = messageDecoder;
    }

    public void setSerialCommunicator(ISerial serialCommunicator) {
        this.serialCommunicator = serialCommunicator;
        this.serialCommunicator.initialize();
        this.serialCommunicator.addListener(this);
    }

    public void addTask(IStationTask... tasks) {
        Collections.addAll(this.tasks, tasks);
    }

    public MeteoConfiguration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(MeteoConfiguration configuration) {
        this.configuration = configuration;
    }

    public void begin() {
        this.serialCommunicator.beginCommunication();
        Logger.get().debug("WeatherStation", "Tasks: " + Arrays.asList(tasks.toArray(new IStationTask[tasks.size()])));
    }


    public void stop() {
        this.serialCommunicator.endCommunication();
    }


    @Override
    public void onMessage(String message) {
        WeatherEntry decodedMessage = this.messageDecoder.decode(message);
        tasks.forEach(t -> t.onEntry(this, decodedMessage));
    }

    @Override
    public void onError(Throwable error, IStationTask.ErrorType errorType) {
        tasks.forEach(t -> t.onError(this, errorType, error));
    }

    public void onEvent(IStationTask trigger, String event) {
        tasks.forEach(t -> t.onEvent(trigger, event));
    }

    @Override
    public void onConnected(ISerial serial) {
        tasks.forEach(t -> t.onConnected(this));
    }

    @Override
    public void onDisconnected(ISerial serial) {
        tasks.forEach(t -> t.onDisconnected(this));
    }


}
