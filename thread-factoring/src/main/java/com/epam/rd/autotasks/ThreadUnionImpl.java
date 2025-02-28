package com.epam.rd.autotasks;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class ThreadUnionImpl implements ThreadUnion {
    private ArrayList<Thread> threads = new ArrayList<>();
    private ConcurrentHashMap<Thread, Throwable> throwables = new ConcurrentHashMap<>();
    private volatile boolean isShutdown = false;
    private String name;

    public ThreadUnionImpl(String name) {
        this.name = name;
    }

    @Override
    synchronized public int totalSize() {
        return threads.size();
    }

    @Override
    synchronized public int activeSize() {
        return (int) threads.stream().filter(Thread::isAlive).count();
    }

    @Override
    synchronized public void shutdown() {
        for (Thread thread : threads) {
            thread.interrupt();
        }
        isShutdown = true;
    }

    @Override
    synchronized public boolean isShutdown() {
        return isShutdown;
    }

    @Override
    synchronized public void awaitTermination() {
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    synchronized public boolean isFinished() {
        return isShutdown && activeSize() == 0;
    }

    @Override
    synchronized public List<FinishedThreadResult> results() {
        List<FinishedThreadResult> finishedThreadResult = new ArrayList<>();

        for (Thread thread : threads) {
            if (!thread.isAlive()) {
                finishedThreadResult.add(new FinishedThreadResult(thread.getName(),
                        throwables.getOrDefault(thread, null)));
            }
        }

        return finishedThreadResult;
    }

    @Override
    synchronized public Thread newThread(Runnable r) {
        if (isShutdown) throw new IllegalStateException();

        Thread newThread = new Thread(r);
        newThread.setName(String.format("%s-worker-%d", name, threads.size()));

        newThread.setUncaughtExceptionHandler((t, e) -> {
            throwables.put(t, e);
        });

        threads.add(newThread);
        return newThread;
    }
}
