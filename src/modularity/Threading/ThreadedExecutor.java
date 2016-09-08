package modularity.Threading;

/**
 * Created by Tommaso Garuglieri on 08/09/2016.
 */
public class ThreadedExecutor implements IDelayedExecutor {
    @Override
    public void schedule(Runnable runnable, int delay) {
        new Thread(() -> {
            try {
                Thread.sleep(delay);
                runnable.run();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
