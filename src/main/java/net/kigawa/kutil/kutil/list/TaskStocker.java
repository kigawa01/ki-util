package net.kigawa.kutil.kutil.list;

import net.kigawa.kutil.kutil.function.ThrowRunnable;
import net.kigawa.kutil.kutil.interfaces.LoggerInterface;
import net.kigawa.kutil.kutil.list.Stocker;

/**
 * this class has a bag
 * @deprecated
 */
public class TaskStocker extends Stocker<ThrowRunnable> {
    private LoggerInterface logger;
    private boolean run = true;
    private boolean wait;

    public TaskStocker(LoggerInterface logger) {
        this.logger = logger;
        Thread thread = new Thread(this::loop);
        thread.start();
    }

    public synchronized void loop() {
        try {
            while (run) {
                if (!hasNext()) wait();
                try {
                    next().run();
                } catch (Exception e) {
                    logger.warning(e);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void end() {
        add(() -> run = false);
    }

    public synchronized void add(ThrowRunnable runnable) {
        super.add(runnable);
        notifyAll();
    }
}
