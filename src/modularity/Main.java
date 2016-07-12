package modularity;

import gnu.io.CommPortIdentifier;
import modularity.Logging.Logger;
import modularity.Serial.ISerial;
import modularity.Serial.RXTX.RXTXSerialUtils;
import modularity.WeatherStation.Configuration.MeteoConfiguration;
import modularity.WeatherStation.Configuration.MeteoConfigurationLoader;
import modularity.WeatherStation.Logging.StationLogger;
import modularity.WeatherStation.Manager.Tasks.*;
import modularity.WeatherStation.Manager.WeatherStationProvider;
import modularity.WeatherStation.MessageDecoder.WeatherDecoder;
import modularity.WeatherStation.SerialCommunication.MeteoSerial;
import modularity.WeatherStation.SerialCommunication.SerialMock;

public class Main {

    private static final IStationTask[] TASKS = new IStationTask[]{
            LoggerTask.create(),
            RetryOnSerialError.create(),
            EntryStorage.create(),
            MailOnError.create(),
            UploadRemote.create()
    };


    public static void main(String[] args) throws Exception {

        MeteoConfiguration config = loadConfiguration();

        handleConfiguration(config);

        Logger.instance(StationLogger.create(config));

        AutoExceptionHandler.bindOnThread();

        WeatherStationProvider
                .create()
                .dataSource(getSerialMock())
                .decodeWith(WeatherDecoder.create())
                .task(TASKS)
                .usingConfiguration(config)
                .begin();

    }

    private static MeteoConfiguration loadConfiguration() {
        MeteoConfiguration configuration = new MeteoConfigurationLoader().load();
        return (configuration == null) ? MeteoConfiguration.DEFAULT : configuration;
    }

    private static void handleConfiguration(MeteoConfiguration configuration) {
        if (configuration.getPlatform().equals("raspberry"))
            RXTXSerialUtils.raspberryWorkaround();
    }

    private static ISerial getSerialMock() {
        return new SerialMock();
    }

    private static ISerial getSerial() {
        CommPortIdentifier device = RXTXSerialUtils.getDevice();
        return new MeteoSerial(device, 9600);
    }


}
