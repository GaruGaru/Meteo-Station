package modularity.WeatherStation.MessageDecoder;

/**
 * Created by Tommaso Garuglieri on 12/06/2016.
 */
public class WeatherDecoder extends SerialMessageDecoderAbstract<WeatherEntry> {

    public static SerialMessageDecoderAbstract<WeatherEntry> create() {
        return new WeatherDecoder();
    }

    @Override
    public WeatherEntry decode(String message) {
        float[] values = getValues(message);
        return new WeatherEntry(getTemperature(values), getHumidity(values),
                getPressure(values), getWindSpeed(values), getWindDirection(values),
                getRain(values), getRainQuantity(values), getLight(values));
    }

}
