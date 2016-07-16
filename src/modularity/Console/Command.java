package modularity.Console;

/**
 * Created by Tommaso Garuglieri on 12/07/2016.
 */
public interface Command {

    void execute(String... params);

    String getName();

    String getHelp();

}
