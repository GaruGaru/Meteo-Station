package modularity.Tests;

import modularity.Threading.IDelayedExecutor;
import modularity.Threading.SyncExecutor;
import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Created by Tommaso Garuglieri on 08/09/2016.
 */
public class PostDelayedTests {

    @Test
    public void testPostDelayed() {

        final int time = 1000;
        long start = System.currentTimeMillis();
        IDelayedExecutor delayedExecutor = new SyncExecutor();
        delayedExecutor.schedule(fakeTask(time), time);
        long result = System.currentTimeMillis() - start;
        assertTrue(result >= time * 2);
    }

    private Runnable fakeTask(int t) {
        return () -> {
            try {
                Thread.sleep(t);
            } catch (InterruptedException e) {
                fail();
            }
        };
    }

}
