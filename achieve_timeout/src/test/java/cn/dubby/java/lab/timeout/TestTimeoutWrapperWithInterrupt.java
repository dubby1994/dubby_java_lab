package cn.dubby.java.lab.timeout;

import cn.dubby.java.lab.timeout.method.Method;
import cn.dubby.java.lab.timeout.method.MethodAwareInterrupt;
import cn.dubby.java.lab.timeout.method.MethodAwareInterrupt2;
import cn.dubby.java.lab.timeout.wrapper.TimeoutWrapper;
import cn.dubby.java.lab.timeout.wrapper.TimeoutWrapperWithInterrupt;

/**
 * Created by yangzheng03 on 2018/1/18.
 */
public class TestTimeoutWrapperWithInterrupt {

    public static void main(String[] args) {
        Method method = new MethodAwareInterrupt();
        TimeoutWrapper timeoutWrapper = new TimeoutWrapperWithInterrupt(method);
        testAwareInterrupt(timeoutWrapper);

        System.out.println();
        method = new MethodAwareInterrupt2();
        timeoutWrapper = new TimeoutWrapperWithInterrupt(method);
        testAwareInterrupt(timeoutWrapper);
    }

    private static void testAwareInterrupt(TimeoutWrapper timeoutWrapper) {
        for (int i = 5; i <= 15; ++i) {
            long timeout = i * 10;
            long startTime = System.currentTimeMillis();
            String result = timeoutWrapper.doSomeThing("test", timeout);
            long endTime = System.currentTimeMillis();
            System.out.println("设置的超时时间:" + timeout + "\t\t共耗时:" + (endTime - startTime) + "\t\t方法正常运行所需:100");

        }
    }

}
