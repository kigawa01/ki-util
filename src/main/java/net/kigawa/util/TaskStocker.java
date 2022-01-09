package net.kigawa.util;

import net.kigawa.function.ThrowRunnable;
import net.kigawa.log.Logger;

public class TaskStocker extends Stocker<ThrowRunnable> {
    private boolean run = true;
    private final Syncer syncer = new Syncer();
    private boolean wait;

    public TaskStocker() {
        Thread thread = new Thread(this::loop);
        thread.start();
    }

    public synchronized void loop() {
        try {
            while (true) {
                System.out.println("while");
                if (!run) break;
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
        System.out.println("add");
        super.add(runnable);
        System.out.println("wait");
        notifyAll();
    }

    public void end() {
        add(() -> run = false);
    }
}
