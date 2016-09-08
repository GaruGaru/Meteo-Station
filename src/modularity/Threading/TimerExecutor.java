package modularity.Threading;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Tommaso Garuglieri on 08/09/2016.
 */
public class TimerExecutor implements IDelayedExecutor {

    @Override
    public void schedule(Runnable runnable, int delay) {
        Timer timer = new Timer(true);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                runnable.run();
            }
        }, delay);
    }

}
