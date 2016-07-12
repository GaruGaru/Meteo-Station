package modularity.Serial;


import modularity.WeatherStation.Manager.Tasks.IStationTask;

/**
 * Created by Tommaso Garuglieri on 15/06/2016.
 */
public interface ISerialListener {
    void onMessage(String message);

    void onError(Throwable error, IStationTask.ErrorType errorType);

    void onConnected(ISerial serial);

    void onDisconnected(ISerial serial);
}
