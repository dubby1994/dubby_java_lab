package cn.dubby.java.lab.timeout.wrapper;

import cn.dubby.java.lab.timeout.method.Method;

import java.util.concurrent.*;

/**
 * Created by yangzheng03 on 2018/1/18.
 */
public class TimeoutWrapperAsync implements TimeoutWrapper {

    private static final ThreadGroup TIMEOUT_CHECK_THREAD_GROUP = new ThreadGroup("timeout-check-thread-group-");

    private static final ScheduledExecutorService TIMEOUT_CHECK_THREAD_POOL = Executors.newScheduledThreadPool(8, new ThreadFactory() {
        @Override
        public Thread newThread(Runnable r) {
            return new Thread(TIMEOUT_CHECK_THREAD_GROUP, r, TIMEOUT_CHECK_THREAD_GROUP.getName() + (TIMEOUT_CHECK_THREAD_GROUP.activeCount() + 1));
        }
    });

    private static final ThreadGroup EXECUTOR_THREAD_GROUP = new ThreadGroup("executor-thread-group-");

    private static final ExecutorService EXECUTOR_SERVICE = Executors.newFixedThreadPool(8, new ThreadFactory() {
        @Override
        public Thread newThread(Runnable r) {
            return new Thread(EXECUTOR_THREAD_GROUP, r, EXECUTOR_THREAD_GROUP.getName() + (EXECUTOR_THREAD_GROUP.activeCount() + 1));
        }
    });


    private Method method;

    public TimeoutWrapperAsync(Method method) {
        this.method = method;
    }

    @Override
    public String doSomeThing(final String something, long timeout) {
        Future<String> future = EXECUTOR_SERVICE.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return method.doSomeThing(something);
            }
        });
        Future timeoutFuture = TIMEOUT_CHECK_THREAD_POOL.scheduleAtFixedRate(new AsyncTimeoutCheckRunnable(future, timeout), 0, timeout, TimeUnit.MILLISECONDS);
        try {
            String result = future.get();
            timeoutFuture.cancel(true);
            return result;
        } catch (Exception e) {
            return "timeout occur:" + something;
        }
    }
}
