package modularity.WeatherStation.Configuration;

import org.junit.Assert;
import org.junit.Test;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;

/**
 * Created by Tommaso Garuglieri on 25/06/2016.
 */
public class ConfigurationTest {

    @Test()
    public void testLoading() {

        MeteoConfiguration load = new LocalMeteoConfigurationLoader().load();

        assertNotNull(load);
        assertEquals(load.getAdminMails().length, 1);
        Assert.assertNotNull(load.getPlatform());


    }


}
