package modularity.Logging.Loggers;

import modularity.Logging.ILogger;

import java.io.PrintStream;

/**
 * Created by Tommaso Garuglieri on 07/07/2016.
 */
public class ConsoleLogger implements ILogger {

    private LogLevel logLevel;

    public ConsoleLogger(LogLevel level) {
        this.logLevel = level;
    }

    public ConsoleLogger() {
        this(LogLevel.ALL);
    }

    private void logConsole(LogLevel requested, String tag, String value) {
        if (logLevel.getValue() >= requested.getValue()) {
            PrintStream stream = getOutputStream(requested);
            stream.println(tag + " - " + value);
        }
    }

    private PrintStream getOutputStream(LogLevel requested) {
        return (requested == LogLevel.ERROR) ? System.err : System.out;
    }

    @Override
    public void info(String tag, String info) {
        logConsole(LogLevel.INFO, tag, info);
    }

    @Override
    public void debug(String tag, String debug) {
        logConsole(LogLevel.DEBUG, tag, debug);
    }

    @Override
    public void error(String tag, String error) {
        logConsole(LogLevel.ERROR, tag, error);
    }

    @Override
    public void error(String tag, Throwable error) {
        this.error(tag, error.getLocalizedMessage());
    }

    public void setLogLevel(LogLevel logLevel) {
        this.logLevel = logLevel;
    }

    public LogLevel getLogLevel() {
        return logLevel;
    }

    enum LogLevel {
        ERROR(0),
        INFO(1),
        DEBUG(2),
        ALL(3);

        final int value;

        LogLevel(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }
}
