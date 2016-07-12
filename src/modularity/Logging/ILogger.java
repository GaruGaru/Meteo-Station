package modularity.Logging;

/**
 * Created by Tommaso Garuglieri on 07/07/2016.
 */
public interface ILogger {

    void info(String tag, String info);

    void debug(String tag, String debug);

    void error(String tag, String error);

    void error(String tag, Throwable error);

}
