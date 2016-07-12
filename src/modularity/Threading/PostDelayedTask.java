package modularity.Threading;

/**
 * Created by Tommaso Garuglieri on 16/06/2016.
 */
public class PostDelayedTask {
    //TODO implement with native Timer's task
    private Runnable task;
    private long delay;

    private PostDelayedTask(Runnable task, long delay) {
        this.task = task;
        this.delay = delay;
    }

    public static void runDelayed(Runnable task, long delay) {
        PostDelayedTask postDelayedTask = new PostDelayedTask(task, delay);
        postDelayedTask.schedule();
    }

    private void schedule() {
        Thread scheduleThead = new Thread(this::execute);
        scheduleThead.start();
    }

    private void execute() {
        try {
            Thread.sleep(delay);
            this.task.run();
        } catch (InterruptedException ignored) {

        }
    }
}
