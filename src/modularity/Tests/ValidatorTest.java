package modularity.Tests;

import modularity.WeatherStation.MessageDecoder.WeatherDecoder;
import modularity.WeatherStation.MessageDecoder.WeatherEntry;
import modularity.WeatherStation.MessageDecoder.WeatherValidator;
import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Tommaso Garuglieri on 08/09/2016.
 */
public class ValidatorTest {

    @Test
    public void testWeatherValidator() {
        WeatherEntry entry = WeatherDecoder.create().decode(TestData.MESSAGE_VALID);
        WeatherValidator validator = new WeatherValidator();
        assertTrue(validator.isValid(entry));
        assertFalse(validator.isValid(null));
        assertFalse(validator.isValid(WeatherDecoder.create().decode(TestData.MESSAGE_FAILED)));
    }
}
