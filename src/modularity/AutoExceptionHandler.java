package modularity;

import modularity.Logging.Logger;

/**
 * Created by Tommaso Garuglieri on 12/07/2016.
 */
public class AutoExceptionHandler implements Thread.UncaughtExceptionHandler {

    public static void bindOnThread() {
        Thread.setDefaultUncaughtExceptionHandler(create());
    }

    public static AutoExceptionHandler create() {
        return new AutoExceptionHandler();
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        e.printStackTrace();
        Logger.get().error("Uncaught Exception", e);
        System.exit(-1);
    }
}
