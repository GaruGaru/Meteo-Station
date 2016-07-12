package modularity.WeatherStation.MessageDecoder;

/**
 * Created by Tommaso Garuglieri on 12/06/2016.
 */

public class WeatherEntry {

    private static final int RAIN_THRESHOLD = 900;

    private long time;
    private float temperature;
    private float humidity;
    private float pressure;
    private float windSpeed;
    private float windDirection;
    private float rain;
    private float rainQuantity;
    private float light;


    public WeatherEntry(float temperature, float humidity, float pressure, float windSpeed, float windDirection, float rain, float rainQuantity, float light) {
        this.time = System.currentTimeMillis();
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        this.windSpeed = windSpeed;
        this.windDirection = windDirection;
        this.rain = rain;
        this.rainQuantity = rainQuantity;
        this.light = light;
    }

    public boolean isRaining() {
        return getRain() <= RAIN_THRESHOLD;
    }

    public float getRain() {
        return rain;
    }

    public float getHumidity() {
        return humidity;
    }

    public WeatherEntry setHumidity(float humidity) {
        this.humidity = humidity;
        return this;
    }

    public float getPressure() {
        return pressure;
    }

    public WeatherEntry setPressure(float pressure) {
        this.pressure = pressure;
        return this;
    }

    public float getTemperature() {
        return temperature;
    }

    public WeatherEntry setTemperature(float temperature) {
        this.temperature = temperature;
        return this;
    }

    public float getWindDirection() {
        return windDirection;
    }

    public WeatherEntry setWindDirection(float windDirection) {
        this.windDirection = windDirection;
        return this;
    }

    public float getWindSpeed() {
        return windSpeed;
    }

    public WeatherEntry setWindSpeed(float windSpeed) {
        this.windSpeed = windSpeed;
        return this;
    }

    public long getTime() {
        return time;
    }

    public WeatherEntry setTime(long time) {
        this.time = time;
        return this;
    }

    public float getLight() {
        return light;
    }

    public float getRainQuantity() {
        return rainQuantity;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (obj == this) return true;
        if (!(obj instanceof WeatherEntry)) return false;
        WeatherEntry instance = (WeatherEntry) obj;
        return (instance.getTime() == time && instance.getTemperature() == temperature && instance.getHumidity() == humidity
                && instance.getWindSpeed() == windSpeed && instance.getWindDirection() == windDirection);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash += Float.floatToIntBits(temperature);
        hash += Float.floatToIntBits(humidity);
        hash += Float.floatToIntBits(pressure);
        hash += Float.floatToIntBits(windDirection);
        hash += Float.floatToIntBits(windSpeed);
        hash += Float.floatToIntBits(windDirection);
        hash += Float.floatToIntBits(rain);
        hash += Float.floatToIntBits(rainQuantity);
        hash += Float.floatToIntBits(light);
        hash += (int) (time ^ (time >>> 32));
        return hash * 31;
    }

    @Override
    public String toString() {
        return "At " + time +
                "\nTemperature: " + temperature
                + "\nHumidity: " + humidity
                + "\nPressure: " + pressure
                + "\nWind Speed: " + windSpeed
                + "\nWind Direction: " + windDirection
                + "\nIs Raining: " + isRaining()
                + "\nRain Quantity: " + rainQuantity
                + "\nLight: " + light;
    }
}
