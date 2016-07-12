package modularity.Networking.smtp;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Created by Tommaso Garuglieri on 25/06/2016.
 */
public class SmtpUtils {

    public static boolean sendMessage(String subject, String body, String... recipients) {

        if (recipients != null && recipients.length != 0) {
            final String username = "meteostation.bot@gmail.com";
            final String password = "gy2phyn41mvz";

            Properties props = new Properties();
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "587");

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
                message.setSubject(subject);
                message.setText(body);

                Transport.send(message);

                return true;

            } catch (MessagingException e) {
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }

}
