package modularity.Networking.smtp;

/**
 * Created by Tommaso Garuglieri on 10/07/2016.
 */
public class Mail {

    private String title;
    private String body;

    private String[] recipients;

    public Mail(String title, String body, String[] recipients) {
        this.title = title;
        this.body = body;
        this.recipients = recipients;
    }

    public String getBody() {
        return body;
    }

    public String getTitle() {
        return title;
    }

    public String[] getRecipients() {
        return recipients;
    }
}
