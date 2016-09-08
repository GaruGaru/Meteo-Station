package modularity.WeatherStation.Manager.Tasks.UploadDB;

import modularity.Logging.Logger;
import modularity.Networking.http.Http;
import modularity.Networking.http.HttpRequest;
import modularity.Networking.http.HttpResponse;
import modularity.WeatherStation.Manager.WeatherStation;
import modularity.WeatherStation.MessageDecoder.WeatherEntry;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Tommaso Garuglieri on 16/07/2016.
 */
public class EntryUploader {

    public static final String TAG = "EntryUploader";
    private final String url;
    private EntryCache cache;

    public EntryUploader(String url) {
        this.url = url;
        this.cache = new EntryCache();
    }

    private Map<String, String> createParams(WeatherStation station, WeatherEntry entry) {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("name", station.getConfiguration().getName());
        map.put("temperature", String.valueOf(entry.getTemperature()));
        map.put("humidity", String.valueOf(entry.getHumidity()));
        map.put("pressure", String.valueOf(entry.getPressure()));
        map.put("wind_speed", String.valueOf(entry.getWindSpeed()));
        map.put("wind_direction", String.valueOf(entry.getWindDirection()));
        map.put("rain", String.valueOf(entry.getRain()));
        map.put("rain_quantity", String.valueOf(entry.getRainQuantity()));
        map.put("light", String.valueOf(entry.getLight()));
        return map;
    }

    public void upload(WeatherStation station, WeatherEntry entry) {
        Logger.get().debug(TAG, "Uploading " + entry.getTime());
        Map<String, String> params = createParams(station, entry);
        HttpRequest request = new HttpRequest(url, params);

        HttpResponse response = Http.get().POST(request);

        if (response.isSuccess() && response.getBody().equals("Done")) {
            onSuccess(response, entry);
            retry(station);
        } else
            onError(response, entry);
    }

    private void retry(WeatherStation station) {
        if (getCache().getEntries().size() > 0) {
            Logger.get().info(TAG, "Retrying entries upload...");
            for (int i = 0; i < getCache().getEntries().size(); i++) {
                Logger.get().debug(TAG, "Retry entry upload: " + i + "/" + getCache().getEntries().size());
                WeatherEntry cachedEntry = getCache().getEntries().get(i);
                getCache().getEntries().remove(i);
                upload(station, cachedEntry);
            }
        }
    }

    private void onSuccess(HttpResponse response, WeatherEntry entry) {
        Logger.get().info(TAG, "Upload success: " + response.toString());
    }

    protected void onError(HttpResponse response, WeatherEntry entry) {
        Logger.get().error(TAG, "Unable to upload entry, response: " + response.toString());
        if (!getCache().getEntries().contains(entry)) {
            Logger.get().debug(TAG, "Caching for future retry");
            getCache().add(entry);
        }
    }

    public EntryCache getCache() {
        return cache;
    }
}
