package net.kigawa.util;

import net.kigawa.log.Logger;

public class Syncer {
    private boolean stop = false;

    public synchronized void endSync() {
        stop = false;
        notify();
    }

    public synchronized void startSync() {
        try {
            while (stop) {
                wait();
            }
            stop = true;
        } catch (InterruptedException e) {
            Logger.getInstance().warning(e);
        }
    }
}
