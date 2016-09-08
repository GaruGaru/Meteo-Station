package modularity.WeatherStation.Manager.Tasks;


import modularity.Threading.IDelayedExecutor;
import modularity.Threading.TimerExecutor;
import modularity.WeatherStation.Manager.WeatherStation;
import modularity.WeatherStation.MessageDecoder.WeatherEntry;

/**
 * Created by Tommaso Garuglieri on 16/06/2016.
 */
public class RetryOnSerialError implements IStationTask {

    public static final int MAX_RETRIES = 2;
    private static final int TIMEOUT = 2000;
    private int retries;

    public RetryOnSerialError() {
        this.retries = 0;
    }

    public static IStationTask create() {
        return new RetryOnSerialError();
    }

    @Override
    public void onEntry(WeatherStation manager, WeatherEntry entry) {

    }

    @Override
    public void onError(WeatherStation manager, ErrorType type, Throwable error) {
        if (type == ErrorType.SERIAL)
            if (this.retries < MAX_RETRIES) {
                this.retries++;
                manager.stop();
                IDelayedExecutor delayedExecutor = new TimerExecutor();
                delayedExecutor.schedule(manager::begin, TIMEOUT);
                manager.onEvent(this, "RetryOnSerialError error, scheduling new retry after " + TIMEOUT + " seconds");
            } else {
                manager.onEvent(this, "RetryOnSerialError - Max retries attempts reached");
            }
    }


    @Override
    public void onConnected(WeatherStation manager) {
        this.retries = 0;
    }

    @Override
    public void onDisconnected(WeatherStation manager) {

    }

    @Override
    public void onEvent(IStationTask trigger, String event) {

    }

    @Override
    public String toString() {
        return "Retry on error";
    }
}
