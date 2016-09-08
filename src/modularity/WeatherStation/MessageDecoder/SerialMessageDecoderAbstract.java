package modularity.WeatherStation.MessageDecoder;


import modularity.Interfaces.IMessageDecoder;
import modularity.Utils.TextUtils;

import java.util.Objects;

/**
 * Created by Tommaso Garuglieri on 12/06/2016.
 */
public abstract class SerialMessageDecoderAbstract<R> implements IMessageDecoder<String, R> {

    public static final int MESSAGE_SIZE = 8;

    private static final int TEMPERATURE = 0;
    private static final int HUMIDITY = 1;
    private static final int PRESSURE = 2;
    private static final int WIND_SPEED = 3;
    private static final int WIND_DIRECTION = 4;
    private static final int RAIN = 5;
    private static final int RAIN_QUANTITY = 6;
    private static final int LIGHT = 7;

    private static final String REGEX = "/";

    public static boolean validMessage(String message) {
        return message != null && !message.isEmpty() && message.split(REGEX).length == MESSAGE_SIZE;
    }

    public float[] getValues(String message) {
        if (TextUtils.isEmpty(message))
            throw new RuntimeException("Invalid input: " + Objects.toString(message));

        String[] messageArray = message.split(REGEX);

        if (messageArray.length != MESSAGE_SIZE)
            throw new RuntimeException("Invalid message size: " + messageArray.length + " instead of " + MESSAGE_SIZE + " with regex: " + REGEX);

        float[] array = new float[messageArray.length];

        for (int i = 0; i < messageArray.length; i++)
            array[i] = Float.parseFloat(messageArray[i]);

        return array;
    }

    private float getValue(float[] array, int index) {
        if (array.length != MESSAGE_SIZE || index > array.length)
            throw new RuntimeException("Invalid getValue params");
        return array[index];
    }

    public float getTemperature(float[] array) {
        return getValue(array, TEMPERATURE);
    }

    public float getHumidity(float[] array) {
        return getValue(array, HUMIDITY);
    }

    public float getPressure(float[] array) {
        return getValue(array, PRESSURE);
    }

    public float getWindSpeed(float[] array) {
        return getValue(array, WIND_SPEED);
    }

    public float getWindDirection(float[] array) {
        return getValue(array, WIND_DIRECTION);
    }

    public float getRain(float[] array) {
        return getValue(array, RAIN);
    }

    public float getRainQuantity(float[] array) {
        return getValue(array, RAIN_QUANTITY);
    }

    public float getLight(float[] array) {
        return getValue(array, LIGHT);
    }

}
