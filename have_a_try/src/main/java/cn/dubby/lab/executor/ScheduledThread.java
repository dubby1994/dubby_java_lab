package cn.dubby.lab.executor;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * Created by yangzheng03 on 2018/1/18.
 */
public class ScheduledThread {

    public static void main(String[] args) {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(10);

        ScheduledFuture<?> future = scheduledExecutorService.scheduleAtFixedRate(new Runnable() {


            {
                System.out.println(System.currentTimeMillis());
            }

            @Override
            public void run() {
                System.out.println(Thread.currentThread().getId() + "\t:\t" + (System.currentTimeMillis()));
            }
        }, 0, 1, TimeUnit.SECONDS);


        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        future.cancel(false);
    }

}
