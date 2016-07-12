package modularity.WeatherStation.Manager.Tasks;


import modularity.WeatherStation.Manager.WeatherStation;
import modularity.WeatherStation.MessageDecoder.WeatherEntry;

/**
 * Created by Tommaso Garuglieri on 16/06/2016.
 */
public interface IStationTask {
    void onEntry(WeatherStation manager, WeatherEntry entry);

    void onError(WeatherStation manager, ErrorType type, Throwable error);

    void onConnected(WeatherStation manager);

    void onDisconnected(WeatherStation manager);

    void onEvent(IStationTask trigger, String event);

    enum ErrorType {
        SERIAL,
        Message, MESSAGE, WEATHER_STATION
    }
}
