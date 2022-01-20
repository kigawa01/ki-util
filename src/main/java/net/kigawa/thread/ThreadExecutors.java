package net.kigawa.thread;

import net.kigawa.interfaces.Module;
import net.kigawa.log.LogSender;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadExecutors implements Module, LogSender {
    public static final ThreadExecutors THREAD_EXECUTORS = new ThreadExecutors();
    private ExecutorService cachedPool;
    private Map<String, ExecutorService> executorServiceMap;

    protected ThreadExecutors() {
    }

    @Override
    public void enable() {
        this.cachedPool = Executors.newCachedThreadPool();
        executorServiceMap = new LinkedHashMap<>();
    }

    @Override
    public void disable() {
        for (ExecutorService service : executorServiceMap.values()) {
            cachedPool.execute(() -> {
                try {
                    service.shutdown();
                } catch (Exception e) {
                    warning(e);
                }
            });
        }
        cachedPool.shutdown();
        cachedPool = null;
    }

    public ExecutorService getCachedPool() {
        return cachedPool;
    }

    public Map<String, ExecutorService> getExecutorServiceMap() {
        return executorServiceMap;
    }
}
