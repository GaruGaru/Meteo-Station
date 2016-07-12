package modularity.WeatherStation.Manager.Tasks;


import modularity.WeatherStation.Manager.WeatherStation;
import modularity.WeatherStation.MessageDecoder.WeatherEntry;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Tommaso Garuglieri on 17/06/2016.
 */
public class EntryStorage implements IStationTask {

    private List<WeatherEntry> entries;

    public EntryStorage() {
        this.entries = new LinkedList<>();
    }

    public static IStationTask create() {
        return new EntryStorage();
    }

    @Override
    public void onEntry(WeatherStation manager, WeatherEntry entry) {
        if (entries.size() >= 100)
            entries.clear();
        this.entries.add(entry);
    }

    @Override
    public void onError(WeatherStation manager, ErrorType type, Throwable error) {

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
        return "Memory entry storage";
    }
}
