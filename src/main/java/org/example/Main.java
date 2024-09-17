package main.java.org.example;

import main.java.org.threadPool.CustomThreadPool;

public class Main {
    public static void main(String[] args) {
        CustomThreadPool customThreadPool = new CustomThreadPool();

        for (int i = 0; i < 10; i++) {
            customThreadPool.submitTask("key1", () -> {
                System.out.println(Thread.currentThread().getName() + "线程开始执行");
                try {
                    // 假装这个任务要执行1秒
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(Thread.currentThread().getName() + ":key1的一个任务执行完毕");
            });
        }
        for (int i = 0; i < 5; i++) {
            customThreadPool.submitTask("key2", () -> {
                System.out.println(Thread.currentThread().getName() + "线程开始执行");
                try {
                    // 假装这个任务要执行2秒
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(Thread.currentThread().getName() + ":key2的一个任务执行完毕");
            });
        }

    }
}