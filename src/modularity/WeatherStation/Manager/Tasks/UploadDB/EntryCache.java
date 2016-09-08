package modularity.WeatherStation.Manager.Tasks.UploadDB;

import modularity.WeatherStation.MessageDecoder.WeatherEntry;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Tommaso Garuglieri on 16/07/2016.
 */
public class EntryCache {

    private List<WeatherEntry> entryList;

    public EntryCache() {
        this.entryList = new LinkedList<>();
    }

    public void add(WeatherEntry entry) {
        this.entryList.add(entry);
    }

    public void remove(WeatherEntry entry) {
        this.entryList.remove(entry);
    }

    public void clear() {
        this.entryList.clear();
    }

    public List<WeatherEntry> getEntries() {
        return entryList;
    }


}
