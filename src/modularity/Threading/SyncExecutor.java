package modularity.Threading;

/**
 * Created by Tommaso Garuglieri on 08/09/2016.
 */
public class SyncExecutor implements IDelayedExecutor {

    @Override
    public void schedule(Runnable runnable, int delay) {
        try {
            Thread.sleep(delay);
            runnable.run();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
