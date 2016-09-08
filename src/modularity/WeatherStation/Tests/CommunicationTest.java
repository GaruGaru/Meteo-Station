package modularity.WeatherStation.Tests;

import modularity.WeatherStation.MessageDecoder.SerialArrayDecoder;
import modularity.WeatherStation.MessageDecoder.SerialMessageDecoderAbstract;
import modularity.WeatherStation.MessageDecoder.WeatherDecoder;
import modularity.WeatherStation.MessageDecoder.WeatherEntry;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Tommaso Garuglieri on 08/09/2016.
 */
public class CommunicationTest {

    @Test
    public void testData() {
        assertTrue(SerialMessageDecoderAbstract.validMessage(TestData.MESSAGE_VALID));
        assertFalse(SerialMessageDecoderAbstract.validMessage(TestData.MESSAGE_INVALID));
        assertFalse(SerialMessageDecoderAbstract.validMessage(null));
    }

    @Test
    public void testMessageDecoderValidator() {
        assertTrue(new SerialMessageDecoderAbstract<Boolean>() {
            @Override
            public Boolean decode(String message) {
                return validMessage(message);
            }
        }.decode(TestData.MESSAGE_VALID));
    }

    @Test
    public void testMessageDecoderValidatorFail() {
        SerialMessageDecoderAbstract<Boolean> decoder = new SerialMessageDecoderAbstract<Boolean>() {
            @Override
            public Boolean decode(String message) {
                return validMessage(message);
            }
        };

        assertFalse(decoder.decode(TestData.MESSAGE_INVALID));
        assertFalse(decoder.decode(null));
    }

    @Test
    public void testWeatherMessageDecoder() {
        WeatherDecoder decoder = new WeatherDecoder();
        WeatherEntry entry = decoder.decode(TestData.MESSAGE_VALID);
        assertNotNull(entry);
        assertNotEquals(entry.getHumidity(), -1);
        assertNotEquals(entry.getWindDirection(), -1);
        assertNotEquals(entry.getPressure(), -1);
        assertNotEquals(entry.getLight(), -1);
        assertNotEquals(entry.getRain(), -1);
        assertNotEquals(entry.getRainQuantity(), -1);
        assertNotEquals(entry.getWindSpeed(), -1);
    }

    @Test
    public void testWeatherArrayDecoder() {
        SerialArrayDecoder decoder = new SerialArrayDecoder();
        float[] array = decoder.decode(TestData.MESSAGE_VALID);
        assertEquals(array.length, SerialMessageDecoderAbstract.MESSAGE_SIZE);
        for (float item : array)
            assertNotEquals(item, -1);
    }


    @Test(expected = RuntimeException.class)
    public void testWeatherMessageDecoderFail() {
        WeatherDecoder decoder = new WeatherDecoder();
        decoder.decode(null);
    }


}
