package modularity.WeatherStation.Manager.Tasks;


import modularity.Logging.Logger;
import modularity.Utils.Json;
import modularity.WeatherStation.Manager.WeatherStation;
import modularity.WeatherStation.MessageDecoder.WeatherEntry;

/**
 * Created by Tommaso Garuglieri on 16/06/2016.
 */
public class LoggerTask implements IStationTask {

    private static final String TAG = "Logger";

    public static IStationTask create() {
        return new LoggerTask();
    }

    @Override
    public void onEntry(WeatherStation manager, WeatherEntry entry) {
        Logger.get().info(TAG, "New entry");
        Logger.get().debug(TAG, Json.get().toJson(entry));
    }

    @Override
    public void onError(WeatherStation manager, ErrorType type, Throwable error) {
        Logger.get().error(type.name(), error);
    }


    private void println(String message) {
        Logger.get().debug(TAG, message);
    }


    @Override
    public void onConnected(WeatherStation manager) {
        println("Connected");
    }

    @Override
    public void onDisconnected(WeatherStation manager) {
        println("Disconnected");
    }

    @Override
    public void onEvent(IStationTask trigger, String event) {
        println(trigger.toString() + ": " + event);
    }

    @Override
    public String toString() {
        return "Logger task";
    }
}
