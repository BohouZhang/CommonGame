package main.java.org.threadPool.task;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;

public class WorkerThread implements Runnable {

    private final Map<Task, Future<?>> taskMap;

    private final BlockingQueue<Task> taskQueue;

    public WorkerThread() {
        taskMap = new HashMap<>();
        taskQueue = new LinkedBlockingQueue<>();
    }

    public Future<?> submitTask(Task task) {
        FutureTaskWrapper futureTaskWrapper = new FutureTaskWrapper(task);
        taskMap.put(task, futureTaskWrapper);
        taskQueue.add(task);
        return futureTaskWrapper;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Task task = taskQueue.take();
                task.execute();
                taskMap.remove(task);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

}
