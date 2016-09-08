package modularity.WeatherStation.Configuration;

import modularity.Configuration.JConfigurationLoader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Created by Tommaso Garuglieri on 25/06/2016.
 */
public class MeteoConfigurationLoader extends JConfigurationLoader<MeteoConfiguration> {

    public MeteoConfigurationLoader() {
        super(MeteoConfiguration.class);
    }

    @Override
    protected String getConfigurationContent() {
        String path = "./" + configurationFile;
        try {
            FileInputStream fis = new FileInputStream(path);
            return streamToString(fis);
        } catch (FileNotFoundException e) {
            return null;
        }
    }
}
