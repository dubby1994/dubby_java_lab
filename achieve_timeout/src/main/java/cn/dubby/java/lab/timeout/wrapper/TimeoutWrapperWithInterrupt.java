package cn.dubby.java.lab.timeout.wrapper;

import cn.dubby.java.lab.timeout.method.Method;

import java.util.concurrent.*;

/**
 * Created by yangzheng03 on 2018/1/18.
 */
public class TimeoutWrapperWithInterrupt implements TimeoutWrapper {
    private static final ThreadGroup threadGroup = new ThreadGroup("timeout-check-thread-group-");

    private static final ScheduledExecutorService TIMEOUT_CHECK_THREAD_POOL = Executors.newScheduledThreadPool(8, new ThreadFactory() {
        @Override
        public Thread newThread(Runnable r) {
            return new Thread(threadGroup, r, threadGroup.getName() + (threadGroup.activeCount() + 1));
        }
    });


    private Method method;

    public TimeoutWrapperWithInterrupt(Method method) {
        this.method = method;
    }

    @Override
    public String doSomeThing(String someThing, long timeout) {
        InterruptTimeoutCheckRunnable timeoutCheckRunnable = new InterruptTimeoutCheckRunnable(Thread.currentThread(), timeout);
        ScheduledFuture<?> future = TIMEOUT_CHECK_THREAD_POOL.scheduleAtFixedRate(timeoutCheckRunnable, 0, timeout, TimeUnit.MILLISECONDS);
        String result = method.doSomeThing(someThing);
        future.cancel(true);
        return result;
    }
}
