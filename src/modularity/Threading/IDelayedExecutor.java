package modularity.Threading;

/**
 * Created by Tommaso Garuglieri on 08/09/2016.
 */
public interface IDelayedExecutor {
    void schedule(Runnable runnable, int delay);
}
