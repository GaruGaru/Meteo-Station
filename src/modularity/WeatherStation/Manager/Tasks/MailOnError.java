package modularity.WeatherStation.Manager.Tasks;

import modularity.Logging.Logger;
import modularity.WeatherStation.Manager.WeatherStation;
import modularity.WeatherStation.MessageDecoder.WeatherEntry;

/**
 * Created by Tommaso Garuglieri on 25/06/2016.
 */
public class MailOnError implements IStationTask {

    public static IStationTask create() {
        return new MailOnError();
    }

    @Override
    public void onEntry(WeatherStation manager, WeatherEntry entry) {

    }

    @Override
    public void onError(WeatherStation manager, ErrorType type, Throwable error) {
        Logger.get().error(type.name(), error);
    }

    @Override
    public void onConnected(WeatherStation manager) {

    }

    @Override
    public void onDisconnected(WeatherStation manager) {

    }

    @Override
    public void onEvent(IStationTask trigger, String event) {

    }

    @Override
    public String toString() {
        return "Mail on error";
    }
}
