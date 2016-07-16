package modularity.Console.Commands;

import modularity.Console.Command;

/**
 * Created by Tommaso Garuglieri on 12/07/2016.
 */
public class ForceClose implements Command {

    public static Command get() {
        return new ForceClose();
    }

    @Override
    public void execute(String... params) {
        System.exit(-1);
    }

    @Override
    public String getName() {
        return "panic";
    }

    @Override
    public String getHelp() {
        return "Instant close";
    }
}
