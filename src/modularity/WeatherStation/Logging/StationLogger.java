package modularity.WeatherStation.Logging;

import modularity.Logging.ILogger;
import modularity.Logging.Logger;
import modularity.Logging.Loggers.ConsoleLogger;
import modularity.Logging.Loggers.MailLogger;
import modularity.WeatherStation.Configuration.MeteoConfiguration;

/**
 * Created by Tommaso Garuglieri on 12/07/2016.
 */
public class StationLogger extends Logger {

    private static ILogger logger = null;

    private StationLogger(MeteoConfiguration config) {
        super(new MailLogger(config.getName(), config.getSmtpAuth(), config.getAdminMails()), new ConsoleLogger());
    }

    public static ILogger create(MeteoConfiguration configuration) {
        logger = new StationLogger(configuration);
        return get();
    }

    public static ILogger get() {
        return logger;
    }
}
