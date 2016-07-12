package modularity.Logging.Loggers;

import modularity.Networking.smtp.Mail;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;

public class MailUtils {

    public static Mail getErrorReport(String error, String content, String... mails) {
        String title = "Fatal issue - " + error;

        String body = "At " + new Date().toString() + "\n"
                + "Error: " + error + "\n"
                + "Content: \n" + content + "\n";

        return new Mail(title, body, mails);
    }


    public static Mail getErrorReport(Throwable error, String... mails) {
        String title = "Fatal issue - " + error.getMessage();

        String body = "At " + new Date().toString() + "\n"
                + "Error: " + error.getMessage() + "\n"
                + "Stacktrace: \n" + throwableToString(error) + "\n";

        return new Mail(title, body, mails);

    }

    public static Mail getErrorReport(String tag, Throwable error, String... mails) {
        String title = "Fatal issue - " + tag + ": " + error.getMessage();

        String body = "At " + new Date().toString() + "\n"
                + "Error: " + error.getMessage() + "\n"
                + "Stacktrace: \n" + throwableToString(error) + "\n";

        return new Mail(title, body, mails);

    }

    public static Mail getLog(String title, String log, String... mails) {
        return new Mail(title + "'s log", log, mails);
    }


    private static String throwableToString(Throwable throwable) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        throwable.printStackTrace(pw);
        return sw.toString(); // stack trace as a string
    }
}
