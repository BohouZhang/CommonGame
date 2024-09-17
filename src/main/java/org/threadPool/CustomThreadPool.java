package main.java.org.threadPool;

import main.java.org.threadPool.task.Task;
import main.java.org.threadPool.task.WorkerThread;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CustomThreadPool {

    private final Map<String, WorkerThread> threadMap;

    private final ExecutorService executorService;

    public CustomThreadPool() {
        threadMap = new HashMap<>();
        executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    }

    public Future<?> submitTask(String key, Task task) {
        WorkerThread workerThread;
        if (threadMap.containsKey(key)) {
            workerThread = threadMap.get(key);
        } else {
            workerThread = new WorkerThread();
            threadMap.put(key, workerThread);
            executorService.submit(workerThread);
        }
        return workerThread.submitTask(task);
    }

}
