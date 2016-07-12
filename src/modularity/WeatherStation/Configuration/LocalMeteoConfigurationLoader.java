package modularity.WeatherStation.Configuration;

import modularity.Configuration.JConfigurationLoader;

import java.io.InputStream;

/**
 * Created by Tommaso Garuglieri on 25/06/2016.
 */
public class LocalMeteoConfigurationLoader extends JConfigurationLoader<MeteoConfiguration> {

    public LocalMeteoConfigurationLoader() {
        super(MeteoConfiguration.class);
    }

    @Override
    protected String getConfigurationContent() {
        InputStream resourceAsStream = getClass().getResourceAsStream(configurationFile);
        return streamToString(resourceAsStream);
    }
}
