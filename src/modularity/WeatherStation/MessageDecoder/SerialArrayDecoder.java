package modularity.WeatherStation.MessageDecoder;

/**
 * Created by Tommaso Garuglieri on 12/06/2016.
 */
public class SerialArrayDecoder extends SerialMessageDecoderAbstract<float[]> {

    @Override
    public float[] decode(String message) {
        return getValues(message);
    }

}
