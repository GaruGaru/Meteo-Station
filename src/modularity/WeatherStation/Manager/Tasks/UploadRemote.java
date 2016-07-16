package modularity.WeatherStation.Manager.Tasks;

import modularity.WeatherStation.Manager.Tasks.UploadDB.EntryUploader;
import modularity.WeatherStation.Manager.WeatherStation;
import modularity.WeatherStation.MessageDecoder.WeatherEntry;

/**
 * Created by Tommaso Garuglieri on 07/07/2016.
 */
public class UploadRemote implements IStationTask {

    private static final String url = "http://gmeteostationbot.altervista.org/add_meteo_entry.php";

    private EntryUploader remoteUploader;

    public UploadRemote() {
        this.remoteUploader = new EntryUploader(url);
    }

    public static IStationTask create() {
        return new UploadRemote();
    }

    @Override
    public void onEntry(WeatherStation manager, WeatherEntry entry) {
        this.remoteUploader.upload(manager, entry);
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
        return "Upload2Remote";
    }
}
