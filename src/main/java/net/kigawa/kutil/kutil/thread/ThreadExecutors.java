package net.kigawa.kutil.kutil.thread;

import net.kigawa.kutil.kutil.interfaces.Logger;
import net.kigawa.kutil.kutil.interfaces.Module;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadExecutors implements Module {
    public static ThreadExecutors THREAD_EXECUTORS;
    private final Logger logger;
    private ExecutorService cachedPool;
    private Map<String, ExecutorService> executorServiceMap;

    public ThreadExecutors(Logger logger) {
        this.logger = logger;
        THREAD_EXECUTORS = this;
    }

    public static void execute(Runnable runnable) {
        THREAD_EXECUTORS.getCachedPool().execute(runnable);
    }

    public ExecutorService getCachedPool() {
        return cachedPool;
    }

    @Override
    public void enable() {
        logger.info("enable thread executor");
        this.cachedPool = Executors.newCachedThreadPool();
        executorServiceMap = new LinkedHashMap<>();
    }

    @Override
    public void disable() {
        logger.info("disable thread executor");
        for (ExecutorService service : executorServiceMap.values()) {
            cachedPool.execute(() -> {
                try {
                    service.shutdown();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
        cachedPool.shutdown();
        cachedPool = null;
    }

    public Map<String, ExecutorService> getExecutorServiceMap() {
        return executorServiceMap;
    }
}
