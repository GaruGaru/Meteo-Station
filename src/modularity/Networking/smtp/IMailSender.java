package modularity.Networking.smtp;

/**
 * Created by Tommaso Garuglieri on 10/07/2016.
 */
public interface IMailSender {

    boolean send(SmtpAuth auth, Mail mail);

}
