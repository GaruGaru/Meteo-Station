package modularity.WeatherStation.Configuration;

import com.google.gson.annotations.SerializedName;
import modularity.Networking.smtp.SmtpAuth;

import java.util.Arrays;

/**
 * Created by Tommaso Garuglieri on 25/06/2016.
 */
public class MeteoConfiguration {

    public static final MeteoConfiguration DEFAULT = new MeteoConfiguration("test-station", "unknown", SmtpAuth.INVALID, "meteostation.bot@gmail.com");

    private String platform;

    private String name;

    private SmtpAuth smtpAuth;

    public SmtpAuth getSmtpAuth() {
        return smtpAuth;
    }

    @SerializedName("admin_mails")
    private String[] adminMails;

    public MeteoConfiguration(String name, String platform, SmtpAuth smtpAuth, String... adminMails) {
        this.name = name;
        this.platform = platform;
        this.smtpAuth = smtpAuth;
        this.adminMails = adminMails;
    }

    public String getName() {
        return name;
    }

    public String getPlatform() {
        return platform;
    }

    public String[] getAdminMails() {
        return adminMails;
    }

    @Override
    public String toString() {
        return "\nPlatform: " + getPlatform() + "\nMails: " + Arrays.asList(adminMails) + "\n";

    }
}
