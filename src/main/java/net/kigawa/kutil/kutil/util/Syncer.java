package net.kigawa.kutil.kutil.util;

import net.kigawa.kutil.kutil.interfaces.LoggerInterface;
import net.kigawa.kutil.kutil.interfaces.Module;

import java.util.LinkedList;

/**
 * @deprecated do not need
 */
public class Syncer implements Module {
    private final LinkedList<Runnable> runnableList = new LinkedList<>();
    private final LoggerInterface logger;
    private int size;
    private Thread thread;
    private boolean run = true;
    private boolean clear = false;

    public Syncer(LoggerInterface logger) {
        this(logger, -1);
    }

    public Syncer(LoggerInterface logger, int size) {
        this.logger = logger;
        this.size = size;
        thread = new Thread(this::run);
        thread.start();
    }

    private void run() {
        int index = 0;
        while (run) {
            if (clear) {
                index = 0;
                synchronized (runnableList) {
                    runnableList.clear();
                }
                clear = false;
                continue;
            }
            if (index >= size && size >= 0) {
                return;
            }
            int size;
            synchronized (runnableList) {
                size = runnableList.size();
            }
            synchronized (this) {
                if (size <= index) {
                    wait0();
                    continue;
                }
            }
            Runnable runnable;
            synchronized (runnableList) {
                runnable = runnableList.get(index);
            }
            synchronized (this) {
                if (runnable == null) {
                    wait0();
                    continue;
                }
            }
            synchronized (runnable) {
                runnable.notify();
                try {
                    runnable.wait();
                } catch (InterruptedException e) {
                    logger.warning(e);
                }
            }
            index++;
        }
    }

    private synchronized void wait0() {
        try {
            wait();
        } catch (InterruptedException e) {
            logger.warning(e);
        }
    }

    public synchronized void clear() {
        clear = true;
        notify0();
    }

    private synchronized void notify0() {
        notify();
    }

    public void setTask(Runnable runnable, int order) {
        synchronized (runnableList) {
            while (runnableList.size() <= order) {
                runnableList.add(null);

            }
            runnableList.set(order, runnable);
        }
        notify0();
        synchronized (runnable) {
            try {
                runnable.wait();
                runnable.run();
                runnable.notify();
            } catch (InterruptedException e) {
                logger.warning(e);
            }
        }
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public void enable() {
        thread = new Thread(this::run);
        thread.start();
    }

    @Override
    public synchronized void disable() {
        run = false;
        notify0();
    }
}
