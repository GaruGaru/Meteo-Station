package modularity.Tests;


import modularity.Interfaces.Validator;
import modularity.WeatherStation.MessageDecoder.WeatherEntry;
import modularity.WeatherStation.MessageDecoder.WeatherValidator;
import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Tommaso Garuglieri on 12/06/2016.
 */

public class ValidatorTest {

    @Test
    public void testWeatherValidator() {

        Validator<WeatherEntry> weatherValidator = new WeatherValidator();

        assertTrue(weatherValidator.isValid(new WeatherEntry(1, 1, 1, 1, 1, 1, 1, 1)));

        assertFalse(weatherValidator.isValid(new WeatherEntry(0, 0, 0, 0, 0, 0, 0, 0)));


    }
}
