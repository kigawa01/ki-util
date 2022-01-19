package net.kigawa.util;

import net.kigawa.interfaces.Module;
import net.kigawa.log.Logger;

import java.util.LinkedList;

public class Syncer implements Module {
    private final LinkedList<Runnable> runnableList = new LinkedList<>();
    private int size;
    private Thread thread;
    private boolean run = true;
    private boolean clear = false;

    public Syncer() {
        this(-1);
    }

    public Syncer(int size) {
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
                clear=false;
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
                    Logger.getInstance().warning(e);
                }
            }
            index++;
        }
    }

    private synchronized void wait0() {
        try {
            wait();
        } catch (InterruptedException e) {
            Logger.getInstance().warning(e);
        }
    }

    private synchronized void notify0() {
        notify();
    }

    public synchronized void clear() {
        clear = true;
        notify0();
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
                Logger.getInstance().warning(e);
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

class SyncerContainer {

}
