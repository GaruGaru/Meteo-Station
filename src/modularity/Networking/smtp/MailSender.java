package modularity.Networking.smtp;

import modularity.Logging.Logger;
import modularity.Utils.TextUtils;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Created by Tommaso Garuglieri on 10/07/2016.
 */
public class MailSender implements IMailSender {

    private boolean validAuth(SmtpAuth auth) {
        return !TextUtils.isEmpty(auth.getUser()) && !TextUtils.isEmpty(auth.getPassword())
                && !TextUtils.isEmpty(auth.getSmtpServer()) && !TextUtils.isEmpty(auth.getSmtpPort());
    }

    @Override
    public boolean send(SmtpAuth auth, Mail mail) {
        if (validAuth(auth)) {
            String[] recipients = mail.getRecipients();

            if (recipients != null && recipients.length != 0) {
                final String username = auth.getUser();
                final String password = auth.getPassword();

                Properties props = new Properties();
                props.put("mail.smtp.starttls.enable", "true");
                props.put("mail.smtp.auth", "true");
                props.put("mail.smtp.host", auth.getSmtpServer());
                props.put("mail.smtp.port", auth.getSmtpPort());

                Session session = Session.getInstance(props,
                        new javax.mail.Authenticator() {
                            protected PasswordAuthentication getPasswordAuthentication() {
                                return new PasswordAuthentication(username, password);
                            }
                        });

                try {

                    Message message = new MimeMessage(session);
                    message.setFrom(new InternetAddress(username));

                    for (String rec : recipients)
                        message.setRecipients(Message.RecipientType.TO,
                                InternetAddress.parse(rec));
                    message.setSubject(mail.getTitle());
                    message.setText(mail.getBody());

                    Transport.send(message);

                    return true;

                } catch (MessagingException e) {
                    Logger.get().debug("MailSender", e.getMessage());
                    return false;
                }
            }
        } else {
            Logger.get().debug("MailSender", "Unable to report exception, auth not defined");
        }
        return false;
    }
}
