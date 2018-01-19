package cn.dubby.java.lab.timeout.wrapper;

/**
 * Created by yangzheng03 on 2018/1/18.
 */
public interface TimeoutWrapper {
    String doSomeThing(String someThing, long timeout);
}
