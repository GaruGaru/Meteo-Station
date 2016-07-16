package modularity.Console.Commands;

import modularity.Console.Command;
import modularity.Logging.LogLevel;
import modularity.Logging.Logger;

/**
 * Created by Tommaso Garuglieri on 12/07/2016.
 */
public class SilenceLogger implements Command {

    public static Command get() {
        return new SilenceLogger();
    }

    @Override
    public void execute(String... params) {
        Logger.get().setLogLevel(LogLevel.NONE);
        System.out.println("Logger silenced");
    }

    @Override
    public String getName() {
        return "silence";
    }

    @Override
    public String getHelp() {
        return "Silence console";
    }
}
