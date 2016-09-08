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

    private StationLogger(ILogger... loggers) {
        super(loggers);
    }

    public static ILogger create(MeteoConfiguration configuration) {
        ILogger[] loggers = new ILogger[]{
                new ConsoleLogger(),
                new MailLogger(configuration.getName(), configuration.getSmtpAuth(), configuration.getAdminMails())
        };

        ILogger[] debugLoggers = new ILogger[]{
                new ConsoleLogger(),
                new MailLogger(configuration.getName(), configuration.getSmtpAuth(), configuration.getAdminMails())
        };


        logger = new StationLogger(configuration.isDebug() ? debugLoggers : loggers);
        return get();
    }

    public static ILogger get() {
        return logger;
    }
}
