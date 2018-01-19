package cn.dubby.java.lab.timeout.method;

/**
 * 这个方法对 Thread.interrupt() 无感知，也就是不会响应中断
 * 这里是用的是死循环，类似的还有传统io操作，比如 inputStream.read 操作
 * Created by yangzheng03 on 2018/1/18.
 */
public class MethodUnawareInterrupt implements Method {
    @Override
    public String doSomeThing(String someThing) {
        long startTimestamp = System.currentTimeMillis();
        while (true) {
            if (System.currentTimeMillis() - startTimestamp >= timeout) {
                break;
            }
        }
        return "After sleeping or waiting, now return the result:" + someThing;
    }
}
