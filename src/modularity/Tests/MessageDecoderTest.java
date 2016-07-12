package modularity.Tests;

import modularity.Interfaces.IMessageDecoder;
import modularity.Interfaces.Validator;
import modularity.WeatherStation.MessageDecoder.*;
import org.junit.Test;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Tommaso Garuglieri on 12/06/2016.
 */
public class MessageDecoderTest {

    @Test(expected = RuntimeException.class)
    public void testArrayDecoderFail() {
        IMessageDecoder<String, float[]> messageDecoder = new SerialArrayDecoder();
        messageDecoder.decode(SerialTestData.TEST_MESSAGE_INVALID);
    }

    @Test(expected = RuntimeException.class)
    public void testObjectDecoderFail() {
        IMessageDecoder<String, float[]> messageDecoder = new SerialArrayDecoder();
        messageDecoder.decode(SerialTestData.TEST_MESSAGE_INVALID);
    }

    @Test
    public void testArrayDecoder() {

        IMessageDecoder<String, float[]> messageDecoder = new SerialArrayDecoder();

        String data = SerialTestData.TEST_MESSAGE_VALID;

        float[] decode = messageDecoder.decode(data);

        assertNotNull(decode);

        assertEquals(SerialMessageDecoderAbstract.MESSAGE_SIZE, decode.length);

    }

    @Test
    public void testEntryDecoder() {

        IMessageDecoder<String, WeatherEntry> messageDecoder = new WeatherDecoder();

        String data = SerialTestData.TEST_MESSAGE_VALID;

        WeatherEntry decoded = messageDecoder.decode(data);

        Validator<WeatherEntry> weatherValidator = WeatherValidator.get();

        assertTrue(weatherValidator.isValid(decoded));

    }


}
