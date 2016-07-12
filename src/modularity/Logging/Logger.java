package modularity.Logging;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Tommaso Garuglieri on 08/07/2016.
 */
public class Logger implements ILogger {

    private static ILogger logger = null;

    public static ILogger instance(ILogger instance) {
        logger = instance;
        return get();
    }

    public static ILogger get() {
        return logger;
    }

    private List<ILogger> loggerList;

    public Logger(ILogger... loggers) {
        this.loggerList = new ArrayList<>(3);
        Collections.addAll(this.loggerList, loggers);
    }

    @Override
    public void info(String tag, String info) {
        loggerList.forEach(l -> l.info(tag, info));
    }

    @Override
    public void debug(String tag, String debug) {
        loggerList.forEach(l -> l.debug(tag, debug));
    }

    @Override
    public void error(String tag, String error) {
        loggerList.forEach(l -> l.error(tag, error));
    }

    @Override
    public void error(String tag, Throwable error) {
        loggerList.forEach(l -> l.error(tag, error));
    }
}
