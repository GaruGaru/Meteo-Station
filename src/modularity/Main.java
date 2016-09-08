package modularity;

import gnu.io.CommPortIdentifier;
import modularity.Console.Commands.ForceClose;
import modularity.Console.Commands.SetLogLevel;
import modularity.Console.Commands.SilenceLogger;
import modularity.Console.Console;
import modularity.Logging.Logger;
import modularity.Serial.ISerial;
import modularity.Serial.RXTX.RXTXSerialUtils;
import modularity.Utils.Json;
import modularity.WeatherStation.Configuration.MeteoConfiguration;
import modularity.WeatherStation.Configuration.MeteoConfigurationLoader;
import modularity.WeatherStation.Logging.StationLogger;
import modularity.WeatherStation.Manager.Tasks.*;
import modularity.WeatherStation.Manager.WeatherStation;
import modularity.WeatherStation.Manager.WeatherStationProvider;
import modularity.WeatherStation.MessageDecoder.WeatherDecoder;
import modularity.WeatherStation.SerialCommunication.MeteoSerial;
import modularity.WeatherStation.SerialCommunication.SerialMock;

public class Main {

    private static final IStationTask[] TASKS = new IStationTask[]{
            LoggerTask.create(),
            RetryOnSerialError.create(),
            MailOnError.create(),
            UploadRemote.create()
    };
    private static MeteoConfiguration configuration;

    public static void main(String[] args) throws Exception {

        init();

        WeatherStation station = WeatherStationProvider
                .create()
                .dataSource((configuration.isDebug() ? getSerialMock() : getSerial()))
                .decodeWith(WeatherDecoder.create())
                .task(TASKS)
                .configuration(configuration)
                .build();

        Logger.get().info("Configuration", Json.get().toJson(configuration));

        station.beginAsync();

        Console console = new Console(
                ForceClose.get(),
                SilenceLogger.get(),
                SetLogLevel.get()
        );

        console.run();

    }

    private static void init() {
        configuration = loadConfiguration();
        Logger.instance(StationLogger.create(configuration));
        AutoExceptionHandler.bindOnThread();
        handleConfiguration(configuration);
    }

    private static MeteoConfiguration loadConfiguration() {
        MeteoConfiguration configuration = new MeteoConfigurationLoader().load();
        return (configuration == null) ? MeteoConfiguration.DEFAULT : configuration;
    }

    private static void handleConfiguration(MeteoConfiguration configuration) {
        if (configuration.getPlatform().equals("raspberry")) {
            RXTXSerialUtils.raspberryWorkaround();
            Logger.get().info("Configuration", "#Raspberry workaround");
        }
    }

    private static ISerial getSerialMock() {
        return new SerialMock();
    }

    private static ISerial getSerial() {
        CommPortIdentifier device = RXTXSerialUtils.getDevice();
        return new MeteoSerial(device, 9600);
    }


}
