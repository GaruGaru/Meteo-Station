package modularity.Networking.smtp;

/**
 * Created by Tommaso Garuglieri on 10/07/2016.
 */
public class SmtpAuth {

    public static final SmtpAuth INVALID = new SmtpAuth(null, null, null, null);

    private final String user;
    private final String password;
    private final String smtpServer;
    private final String smtpPort;

    public SmtpAuth(String user, String password, String smtpServer, String smtpPort) {
        this.user = user;
        this.password = password;
        this.smtpServer = smtpServer;
        this.smtpPort = smtpPort;
    }

    public String getPassword() {
        return password;
    }

    public String getSmtpServer() {
        return smtpServer;
    }

    public String getUser() {
        return user;
    }

    public String getSmtpPort() {
        return smtpPort;
    }
}
