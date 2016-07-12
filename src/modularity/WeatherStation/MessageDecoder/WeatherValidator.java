package modularity.WeatherStation.MessageDecoder;


import modularity.Interfaces.Validator;

/**
 * Created by Tommaso Garuglieri on 12/06/2016.
 */
public class WeatherValidator implements Validator<WeatherEntry> {

    private static WeatherValidator instance = null;

    public static WeatherValidator get() {
        if (instance == null)
            instance = new WeatherValidator();
        return instance;
    }

    @Override
    public boolean isValid(WeatherEntry object) {
        return object != null && (object.getTime() >= 0 && object.getTemperature() >= 0 &&
                object.getHumidity() >= 0 && object.getWindDirection() >= 0 &&
                object.getWindSpeed() >= 0 && object.getRain() >= 0 &&
                object.getRainQuantity() >= 0);
    }
}
