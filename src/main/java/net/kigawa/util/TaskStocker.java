package net.kigawa.util;

import net.kigawa.function.ThrowRunnable;

public class TaskStocker extends Stocker<ThrowRunnable> {
    private final Thread thread;
    private boolean run = true;

    public TaskStocker() {
        this.thread = new Thread(new Runnable() {
            @Override
            public synchronized void run() {
                while (run) {
                    if (!hasNext()) {
                        try {
                            wait();
                        } catch (InterruptedException e) {
                            Logger.getInstance().warning(e);
                        }
                        continue;
                    }
                    try {
                        next().run();
                    } catch (Exception e) {
                        Logger.getInstance().warning(e);
                    }
                }
            }
        });
        thread.start();
    }

    public synchronized void add(ThrowRunnable runnable) {
        boolean started = hasNext();
        super.add(runnable);
        if (started) return;
        thread.notifyAll();
    }

    public synchronized void end() {
        run = false;
        notifyAll();
    }
}
