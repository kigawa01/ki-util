package net.kigawa.kutil.kutil.util;

import net.kigawa.kutil.kutil.function.ThrowRunnable;
import net.kigawa.kutil.kutil.interfaces.Logger;

public class TaskStocker extends Stocker<ThrowRunnable> {
    private Logger logger;
    private boolean run = true;
    private boolean wait;

    public TaskStocker(Logger logger) {
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
