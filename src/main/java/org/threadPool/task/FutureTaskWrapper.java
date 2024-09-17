package main.java.org.threadPool.task;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class FutureTaskWrapper implements Future<Void> {

    private final Task task;
    private boolean isDone;

    public FutureTaskWrapper(Task task) {
        this.task = task;
        isDone = false;
    }

    @Override
    public boolean cancel(boolean mayInterruptIfRunning) {
        return false;
    }

    @Override
    public boolean isCancelled() {
        return false;
    }

    @Override
    public boolean isDone() {
        return isDone;
    }

    @Override
    public Void get() throws InterruptedException, ExecutionException {
        task.execute();
        isDone = true;
        return null;
    }

    @Override
    public Void get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        task.execute();
        isDone = true;
        return null;
    }
}