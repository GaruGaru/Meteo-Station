package modularity.Tests;

import modularity.Utils.Json;
import org.junit.Test;

import static junit.framework.TestCase.*;

/**
 * Created by Tommaso Garuglieri on 08/09/2016.
 */
public class JsonTest {

    @Test
    public void testSerializer() {
        String s = Json.get().toJson(getTestObject());
        assertNotNull(s);
        assertFalse(s.isEmpty());
    }

    @Test
    public void testParser() {
        String jObject = Json.get().toJson(getTestObject());
        TestObject object = Json.get().fromJson(jObject, TestObject.class);
        assertNotNull(object);
        assertEquals(object, getTestObject());
    }

    @Test
    public void testInvalidParser() {
        String invalidJson = "{id: null}";
        TestObject object = Json.get().fromJson(invalidJson, TestObject.class);
        assertNull(object.getName());
        assertNull(object.getValue());
    }


    private TestObject getTestObject() {
        return new TestObject("Test", "Value");
    }

    private class TestObject {
        private final String name;
        private final String value;

        TestObject(String name, String value) {
            this.name = name;
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public String getValue() {
            return value;
        }


        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj instanceof TestObject) {
                TestObject tObj = (TestObject) obj;
                return tObj.getName().equals(getName()) && tObj.getValue().equals(getValue());
            }

            return false;
        }
    }
}
