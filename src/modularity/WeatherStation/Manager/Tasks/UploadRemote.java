package modularity.WeatherStation.Manager.Tasks;

import modularity.Logging.Logger;
import modularity.Networking.http.Http;
import modularity.Networking.http.HttpRequest;
import modularity.Networking.http.HttpResponse;
import modularity.WeatherStation.Manager.WeatherStation;
import modularity.WeatherStation.MessageDecoder.WeatherEntry;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Tommaso Garuglieri on 07/07/2016.
 */
public class UploadRemote implements IStationTask {

    private static final String URL = "http://meteostationbot.altervista.org/add_meteo_entry.php";

    public static IStationTask create() {
        return new UploadRemote();
    }

    private Map<String, String> getParams(WeatherStation manager, WeatherEntry entry) {
        Map<String, String> map = new LinkedHashMap<>();

        map.put("name", manager.getConfiguration().getName());
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

    @Override
    public void onEntry(WeatherStation manager, WeatherEntry entry) {
        upload(manager, entry);
    }

    private void upload(WeatherStation manager, WeatherEntry entry) {
        Map<String, String> params = getParams(manager, entry);

        HttpRequest request = new HttpRequest(URL, params);

        HttpResponse response = Http.get().POST(request);

        Logger.get().debug("UploadRemote", response.toString());

        if (!response.isSuccess()) {
            Logger.get().error("POST request error", response.toString());
        }

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
