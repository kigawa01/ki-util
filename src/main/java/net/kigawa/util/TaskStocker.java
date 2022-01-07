package net.kigawa.util;

import net.kigawa.function.ThrowRunnable;
import net.kigawa.log.Logger;

public class TaskStocker extends Stocker<ThrowRunnable> {
    private boolean run = true;
    private boolean wait = true;

    public TaskStocker() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public synchronized void run() {
                while (run) {
                    if (wait) {
                        waitHere();
                        continue;
                    }
                    if (!hasNext()) {
                        wait = true;
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
        super.add(runnable);
        if (wait) {
            wait = false;
            notifyAll();
        }
    }

    public synchronized void end() {
        run = false;
        notifyAll();
    }

    private synchronized void waitHere() {
        try {
            wait();
        } catch (InterruptedException e) {
            Logger.getInstance().warning(e);
        }
    }
}
