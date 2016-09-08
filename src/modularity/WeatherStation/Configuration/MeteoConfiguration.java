package modularity.WeatherStation.Configuration;

import com.google.gson.annotations.SerializedName;
import modularity.Networking.smtp.SmtpAuth;

import java.util.Arrays;

/**
 * Created by Tommaso Garuglieri on 25/06/2016.
 */
public class MeteoConfiguration {

    public static final MeteoConfiguration DEFAULT = new MeteoConfiguration("default-station", "unknown", SmtpAuth.INVALID, true, "meteostation.bot@gmail.com");

    private String platform;

    private String name;

    private SmtpAuth smtpAuth;
    @SerializedName("admin_mails")
    private String[] adminMails;

    private boolean debug;

    public MeteoConfiguration(String name, String platform, SmtpAuth smtpAuth, boolean debug, String... adminMails) {
        this.name = name;
        this.platform = platform;
        this.smtpAuth = smtpAuth;
        this.debug = debug;
        this.adminMails = adminMails;
    }

    public boolean isDebug() {
        return debug;
    }

    public SmtpAuth getSmtpAuth() {
        return smtpAuth;
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
