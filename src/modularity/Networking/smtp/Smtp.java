package modularity.Networking.smtp;

/**
 * Created by Tommaso Garuglieri on 10/07/2016.
 */
public class Smtp {
    private static IMailSender sender;

    public static IMailSender get() {
        if (sender == null)
            sender = new MailSender();
        return sender;
    }

}
