package modularity.Console.Commands;

import modularity.Console.Command;
import modularity.Logging.LogLevel;
import modularity.Logging.Logger;

/**
 * Created by Tommaso Garuglieri on 12/07/2016.
 */
public class SetLogLevel implements Command {

    public static Command get() {
        return new SetLogLevel();
    }

    @Override
    public void execute(String... params) {
        try {
            LogLevel logLevel = LogLevel.valueOf(params[1].toUpperCase());
            Logger.get().setLogLevel(logLevel);
            System.out.println("LogLevel: " + logLevel.name());
        } catch (Exception ex) {
            System.out.println("Invalid param " + params[1]);
        }

    }

    @Override
    public String getName() {
        return "loglevel";
    }


    @Override
    public String getHelp() {
        String loglevels = "";
        for (LogLevel level : LogLevel.values())
            loglevels += level.name() + " / ";
        return "Set log level: " + loglevels;
    }
}
