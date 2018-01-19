package cn.dubby.java.lab.timeout.method;

/**
 * Created by yangzheng03 on 2018/1/18.
 */
public interface Method {

    long timeout = 100;

    String doSomeThing(String someThing);

}
