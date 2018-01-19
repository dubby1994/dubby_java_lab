package cn.dubby.java.lab.timeout.wrapper;

import cn.dubby.java.lab.timeout.trigger.TimeoutTrigger;

/**
 * Created by yangzheng03 on 2018/1/19.
 */
public class SyncTimeoutCheckRunnable implements Runnable, TimeoutTrigger {

    /**
     * 需要中断的目标线程
     */
    private Thread targetThread;

    /**
     * 超时时间，单位:ms
     */
    private long timeout;

    private long startTimestamp;

    public SyncTimeoutCheckRunnable(Thread targetThread, long timeout) {
        if (timeout <= 0) {
            throw new IllegalArgumentException("timeout value must greater than 0");
        }
        this.targetThread = targetThread;
        this.timeout = timeout;
        this.startTimestamp = System.currentTimeMillis();
    }

    @Override
    public void run() {
        if (System.currentTimeMillis() - startTimestamp >= timeout && !Thread.interrupted()) {
            handleTimeout();
        }
    }

    @Override
    public void handleTimeout() {
        targetThread.interrupt();
    }
}