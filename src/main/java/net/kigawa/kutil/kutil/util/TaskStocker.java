package net.kigawa.kutil.kutil.util;

import net.kigawa.kutil.kutil.function.ThrowRunnable;
import net.kigawa.log.Logger;

public class TaskStocker extends Stocker<ThrowRunnable> {
    private boolean run = true;
    private boolean wait;

    public TaskStocker() {
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
                    Logger.getInstance().warning(e);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void add(ThrowRunnable runnable) {
        super.add(runnable);
        notifyAll();
    }

    public void end() {
        add(() -> run = false);
    }
}
