package cn.dubby.java.lab.timeout.method;

/**
 * 这个方法对 Thread.interrupt() 有感知，也就是会响应中断
 * Created by yangzheng03 on 2018/1/18.
 */
public class MethodAwareInterrupt implements Method {
    @Override
    public String doSomeThing(String someThing) {
        try {
            Thread.sleep(timeout);
        } catch (InterruptedException e) {
            return "timeout occur:" + someThing;
        }
        return "After sleeping or waiting, now return the result:" + someThing;
    }
}
