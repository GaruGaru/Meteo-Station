package modularity.Logging.Loggers;

import modularity.Logging.ILogger;
import modularity.Logging.LogLevel;
import modularity.Networking.smtp.Mail;
import modularity.Networking.smtp.Smtp;
import modularity.Networking.smtp.SmtpAuth;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Tommaso Garuglieri on 07/07/2016.
 */
public class MailLogger implements ILogger {

    private static final int LOG_FLUSH_MAX_SIZE = 50;

    private final String nameTag;

    private String[] mails;
    private List<LogEntry> logEntryList;

    private SmtpAuth auth;

    public MailLogger(String nameTag, SmtpAuth auth, String[] mails) {
        this.nameTag = nameTag;
        this.mails = mails;
        this.logEntryList = new ArrayList<>();
        this.auth = auth;
    }


    private void sendLogReport(List<LogEntry> logEntryList) {
        String logToString = nameTag + " report at " + new Date().toString();

        for (LogEntry entry : logEntryList)
            logToString += "\n" + entry.getTag() + " - " + entry.getLog();

        Smtp.get().send(auth, new Mail(nameTag + " report", logToString, mails));
    }

    @Override
    public void info(String tag, String info) {
    }

    @Override
    public void debug(String tag, String debug) {
    }

    @Override
    public void error(String tag, String error) {
        Mail errorReport = MailUtils.getErrorReport(nameTag + ": " + tag, error, mails);
        Smtp.get().send(auth, errorReport);
    }

    @Override
    public void error(String tag, Throwable error) {
        Mail errorReport = MailUtils.getErrorReport(nameTag + ": " + tag, error, mails);
        Smtp.get().send(auth, errorReport);
    }

    @Override
    public void setLogLevel(LogLevel logLevel) {

    }


    private class LogEntry {
        private final String tag;
        private final String log;

        private LogEntry(String tag, String log) {
            this.tag = tag;
            this.log = log;
        }

        public String getTag() {
            return tag;
        }

        public String getLog() {
            return log;
        }
    }
}
