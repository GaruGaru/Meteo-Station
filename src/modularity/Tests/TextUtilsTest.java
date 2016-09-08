package modularity.Tests;

import modularity.Utils.TextUtils;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Tommaso Garuglieri on 08/09/2016.
 */
public class TextUtilsTest {

    @Test
    public void testShorten() {
        assertTrue(TextUtils.shorten("Test", 5).length() == 4);
        assertEquals(TextUtils.shorten(randomString(1000), 100).length(), 100);
    }

    @Test(expected = RuntimeException.class)
    public void testShortenFail() {
        TextUtils.shorten(null, 5);
    }

    @Test(expected = RuntimeException.class)
    public void testShortenInvalidLength() {
        TextUtils.shorten(null, -5);
    }

    @Test
    public void testShortener() {
        for (int i = 0; i < 100; i++)
            assertEquals(i, TextUtils.shorten(randomString(100), i).length());
    }

    private String randomString(int length) {
        String out = "";
        for (int i = 0; i < length; i++)
            out += "a";
        return out;
    }
}
