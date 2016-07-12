package modularity.WeatherStation.Manager;

import modularity.Serial.ISerial;
import modularity.WeatherStation.Configuration.MeteoConfiguration;
import modularity.WeatherStation.Manager.Tasks.IStationTask;
import modularity.WeatherStation.MessageDecoder.SerialMessageDecoderAbstract;
import modularity.WeatherStation.MessageDecoder.WeatherEntry;

/**
 * Created by Tommaso Garuglieri on 12/07/2016.
 */
public class WeatherStationProvider extends WeatherStation {

    public static WeatherStationProvider create() {
        return new WeatherStationProvider();
    }

    public WeatherStationProvider dataSource(ISerial serial) {
        setSerialCommunicator(serial);
        return this;
    }

    public WeatherStationProvider decodeWith(SerialMessageDecoderAbstract<WeatherEntry> decoder) {
        setMessageDecoder(decoder);
        return this;
    }

    public WeatherStationProvider usingConfiguration(MeteoConfiguration configuration) {
        setConfiguration(configuration);
        return this;
    }

    public WeatherStationProvider task(IStationTask... tasks) {
        addTask(tasks);
        return this;
    }

}
